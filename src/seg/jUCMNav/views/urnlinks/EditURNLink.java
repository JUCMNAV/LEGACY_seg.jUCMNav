package seg.jUCMNav.views.urnlinks;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.GrlEditor;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.editparts.GrlConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.UCMConnectionOnBottomRootEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.change.ModifyUrnLinkCommand;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import seg.jUCMNav.views.strategies.StrategiesView;
import seg.jUCMNav.views.wizards.URNlinkTypeSelectionDialog;
import ucm.map.UCMmap;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNlink;
import urn.URNspec;
import urncore.Component;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class EditURNLink {
	
	private static URNmodelElement fromElement = null;
	private static URNmodelElement selectedElement = null;
	private static URNmodelElement selectedElementParent = null;
	private static EditPart fromEditPart = null;
	private static EditPart selectedEditPart = null;
	private static CommandStack commandStack;
    private static URNspec urnspec; // The urnspec of the current model
    
	
    public void editLink( CommandStack cmdStack, URNmodelElement element, URNmodelElement parentElement, EditPart ep )
    {

    	class EditListener implements Listener {
    		private URNlink link;

    		EditListener( URNlink ul ){	link = ul; }

    		public void handleEvent(Event event) {
    			editLinkType( link );
    		}
    	}

    	class DeleteListener implements Listener {
    		private URNlink link;

    		DeleteListener( URNlink ul ){ link = ul; }

    		public void handleEvent(Event event) {
    			deleteLink( link );
    		}
    	}

    	class NavigateListener implements Listener {
    		private URNlink link;
    		private boolean outgoing;

    		NavigateListener( URNlink ul, boolean og ){	link = ul; outgoing = og; }

    		public void handleEvent(Event event) {
    			navigateLink( link, outgoing );
    		}
    	}

    	commandStack = cmdStack;
    	selectedElement = element;
    	selectedElementParent = parentElement;
    	selectedEditPart = ep;

    	if( selectedElement != null )
    		urnspec = this.getURNspec( selectedElement );
    	else
    		urnspec = this.getURNspec( selectedElementParent );
    	
    	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	Menu menu = new Menu(shell, SWT.POP_UP);

    	MenuItem item = new MenuItem(menu, SWT.PUSH);

    	String name;
    	if( selectedElementParent == null || selectedElement instanceof EvaluationStrategy )
    		name = selectedElement.getName();
    	else
    		name = selectedElementParent.getName();

    	item.setText(Messages.getString("EditURNLink.UrnLinksFor") + name + "\""); //$NON-NLS-1$ //$NON-NLS-2$
    	item.setEnabled(false);
    	MenuItem item4 = new MenuItem(menu, SWT.SEPARATOR);

    	if( selectedElement != null ){
    		// menu item for Start Link from selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements
    		MenuItem item2 = new MenuItem(menu, SWT.PUSH);
    		item2.setText( Messages.getString("EditURNLink.StartNewLinkFrom") + this.className( selectedElement ) + " \"" + URNElementFinder.getParentElement( selectedElement ).getName() + "\"" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    		item2.addListener( SWT.Selection, new Listener() {
    			public void handleEvent(Event event) {
    				startNewLink( selectedElement );
    			}
    		});
    	}
    	
    	// if applicable, menu item for Start Link from parent URN object Actor, Component, Responsibility, IntentionalElement, ...
    	if( selectedElementParent != null ){
    		MenuItem item22 = new MenuItem(menu, SWT.PUSH);
     		item22.setText( Messages.getString("EditURNLink.StartNewLinkFrom") + this.className( selectedElementParent ) + " \"" + selectedElementParent.getName() + "\"" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    		item22.addListener( SWT.Selection, new Listener() {
    			public void handleEvent(Event event) {
    				startNewLink( selectedElementParent );
    			}
    		});

    	}
    	// add menu items for End Link if a previous element has been selected as a link start
    	if( (fromElement != null) && (fromElement != selectedElement) && (this.getURNspec( fromElement ) == urnspec) ){

    		if( selectedElement != null ){

    			MenuItem item21 = new MenuItem(menu, SWT.PUSH);
    			String endText = Messages.getString("EditURNLink.EndNewLinkFrom") + this.className( fromElement ) + " \"" + URNElementFinder.getParentElement( fromElement ).getName()  //$NON-NLS-1$ //$NON-NLS-2$
    					+ Messages.getString("EditURNLink.QuoteTo") + this.className( selectedElement ); //$NON-NLS-1$

    			if( selectedElement instanceof EvaluationStrategy )
    				item21.setText( endText + " \"" +  selectedElement.getName() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
    			else
    				item21.setText( endText );

    			item21.addListener( SWT.Selection, new Listener() {
    				public void handleEvent(Event event) {
    					endNewLink( selectedElement );
    				}
    			});
    		}
    		
    		if( selectedElementParent != null ){

    			MenuItem item23 = new MenuItem(menu, SWT.PUSH);
    			String endText = Messages.getString("EditURNLink.EndNewLinkFrom") + this.className( fromElement ) + " \"" + URNElementFinder.getParentElement( fromElement ).getName() + Messages.getString("EditURNLink.QuoteTo") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    					+ this.className( selectedElementParent );
    			
    			if( selectedElementParent instanceof StrategiesGroup )
    				item23.setText( endText + " \"" +  selectedElementParent.getName() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
    			else
    				item23.setText( endText );

    			item23.addListener( SWT.Selection, new Listener() {
    				public void handleEvent(Event event) {
    					endNewLink( selectedElementParent );
    				}
    			});
    		}
    	}
    	// determine number of outgoing links
    	
    	int outgoingSize = 0;
    	
    	if( selectedElement != null ) {
    		outgoingSize = selectedElement.getFromLinks().size();
    	}
    	
    	if( selectedElementParent != null ){
    		outgoingSize += selectedElementParent.getFromLinks().size();
    	}
    	// add menu items for outgoing links
    	if( outgoingSize > 0 ){ 

    		new MenuItem(menu, SWT.SEPARATOR);

    		final MenuItem[] ogLinks = new MenuItem[outgoingSize];
    		final Menu[] pulldownMenus = new Menu[outgoingSize];
    		final MenuItem[] pei = new MenuItem[outgoingSize];
    		final MenuItem[] pdi = new MenuItem[outgoingSize];
    		final MenuItem[] pni = new MenuItem[outgoingSize];
    		int i = 0;

    		if( selectedElement != null ){
    			// add outgoing links from selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements  
    			for (Iterator it = selectedElement.getFromLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + Messages.getString("EditURNLink.CloseParenthesisTo") + this.className( link.getToElem() )+ " \"" + URNElementFinder.getParentElement( link.getToElem() ).getName() +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    						Messages.getString("EditURNLink.QuoteFrom") + this.className( selectedElement ) + " \"" + URNElementFinder.getParentElement( selectedElement ).getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    				ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				ogLinks[i].setText( text );
    				ogLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( Messages.getString("EditURNLink.ModifyLinkType") ); //$NON-NLS-1$
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( Messages.getString("EditURNLink.DeleteLink") ); //$NON-NLS-1$
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link ));

    				if( this.isNavigable( link.getToElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( Messages.getString("EditURNLink.ShowTarget") ); //$NON-NLS-1$
    					pni[i].addListener( SWT.Selection, new NavigateListener( link, true ));
    				}
    				ogLinks[i].setMenu(pulldownMenus[i]);
    				i++;
    			}
    		}
        	
    		if( selectedElementParent != null ){
    			// if applicable, add outgoing links from parent URN objects Actor, Component, Responsibility, IntentionalElement, ...
    			for (Iterator it = selectedElementParent.getFromLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + Messages.getString("EditURNLink.CloseParenthesisTo") + this.className( link.getToElem() )+ " \"" + URNElementFinder.getParentElement( link.getToElem() ).getName()  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    						+ Messages.getString("EditURNLink.QuoteFrom") + this.className( selectedElementParent ) + " \"" + selectedElementParent.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    				ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				ogLinks[i].setText( text );
    				ogLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( Messages.getString("EditURNLink.ModifyLinkType") ); //$NON-NLS-1$
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( Messages.getString("EditURNLink.DeleteLink") ); //$NON-NLS-1$
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link ));

    				if( this.isNavigable( link.getToElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( Messages.getString("EditURNLink.ShowTarget") ); //$NON-NLS-1$
    					pni[i].addListener( SWT.Selection, new NavigateListener( link, true ));
    				}
    				ogLinks[i].setMenu(pulldownMenus[i]);
    				i++;
    			}
    		}
    	}

    	// determine number of incoming links
    	int incomingSize = 0;

    	if( selectedElement != null ) {
    		incomingSize = selectedElement.getToLinks().size();
    	}
    	
    	if( selectedElementParent != null ){
    		incomingSize += selectedElementParent.getToLinks().size();
    	}

    	// add menu items for incoming links
    	if( incomingSize > 0 ){    
    		new MenuItem(menu, SWT.SEPARATOR);

    		final MenuItem[] icLinks = new MenuItem[incomingSize];
    		final Menu[] pulldownMenus = new Menu[incomingSize];
    		final MenuItem[] pei = new MenuItem[incomingSize];
    		final MenuItem[] pdi = new MenuItem[incomingSize];
    		final MenuItem[] pni = new MenuItem[incomingSize];

    		int i = 0;

    		if( selectedElement != null ){
    			// add incoming links to selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements  
    			for (Iterator it = selectedElement.getToLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + Messages.getString("EditURNLink.CloseParenthesisFrom") + this.className( link.getFromElem() )+ " \"" + URNElementFinder.getParentElement( link.getFromElem() ).getName() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    						+ Messages.getString("EditURNLink.QuoteTo") + this.className( selectedElement ) + " \"" + URNElementFinder.getParentElement( selectedElement ).getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    				icLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				icLinks[i].setText( text );
    				icLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink-reversed.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( Messages.getString("EditURNLink.ModifyLinkType") ); //$NON-NLS-1$
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( Messages.getString("EditURNLink.DeleteLink") ); //$NON-NLS-1$
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link));

    				if( this.isNavigable( link.getFromElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( Messages.getString("EditURNLink.ShowSource") ); //$NON-NLS-1$
    					pni[i].addListener( SWT.Selection, new NavigateListener( link, false ));
    				}

    				icLinks[i].setMenu(pulldownMenus[i]);
    				i++;
    			}
    		}
    		
    		if( selectedElementParent != null ){
    			// if applicable, add incoming links to parent URN objects Actor, Component, Responsibility, IntentionalElement, ...
    			for (Iterator it = selectedElementParent.getToLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + Messages.getString("EditURNLink.CloseParenthesisFrom") + this.className( link.getFromElem() )+ " \"" +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    				URNElementFinder.getParentElement( link.getFromElem() ).getName() + Messages.getString("EditURNLink.QuoteTo") + this.className( selectedElementParent ) + " \"" //$NON-NLS-1$ //$NON-NLS-2$
    				+ selectedElementParent.getName() + "\""; //$NON-NLS-1$
    				icLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				icLinks[i].setText( text );
    				icLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink-reversed.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( Messages.getString("EditURNLink.ModifyLinkType") ); //$NON-NLS-1$
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( Messages.getString("EditURNLink.DeleteLink") ); //$NON-NLS-1$
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link));

    				if( this.isNavigable( link.getFromElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( Messages.getString("EditURNLink.ShowSource") ); //$NON-NLS-1$
    					pni[i].addListener( SWT.Selection, new NavigateListener( link, false ));
    				}

    				icLinks[i].setMenu(pulldownMenus[i]);
    				i++;
    			}
    		}
    	}
    	menu.setVisible(true);
    }
	
	private String className( URNmodelElement element )
	{
	    String className = element.getClass().getSimpleName();
	    return className.substring( 0, className.length()-4 );  // strip suffix 'Impl' from class name
	}
	
	private boolean isNavigable( URNmodelElement endpoint )
	{
		Class [] navigableClasses = { IURNNode.class, IURNContainerRef.class, IntentionalElement.class, Actor.class, Component.class, Responsibility.class,
				IURNDiagram.class , EvaluationStrategy.class, StrategiesGroup.class, ScenarioDef.class, ScenarioGroup.class };
		
		return(this.includesClass( endpoint, navigableClasses ));
	}
	
	private boolean includesClass( URNmodelElement element, Class [] classList )
	{
		for( int i = 0; i < classList.length; i++ ) {
			if( classList[i].isAssignableFrom( element.getClass() ) )
				return true;
		}
		
		return false;
	}
	
	private void startNewLink( URNmodelElement element )
	{
		fromElement = element;
	}
	
	private URNspec getURNspec( URNmodelElement element )
	{
		URNspec urnspec = null;
		
        if (element instanceof IntentionalElement) {
            urnspec = ((IntentionalElement) element).getGrlspec().getUrnspec();
        } else if (element instanceof Actor) {
            urnspec = ((Actor) element).getGrlspec().getUrnspec();
        } else if( element instanceof IURNNode ){ // handles UCM, GRL Nodes
        	urnspec = ((IURNNode) element).getDiagram().getUrndefinition().getUrnspec();
        } else if(  element instanceof IURNContainerRef ){ // handles ActorRef, ComponentRef
        	urnspec = ((IURNContainerRef) element).getDiagram().getUrndefinition().getUrnspec();
        } else if( element instanceof ElementLink ){
        	urnspec = ((ElementLink) element).getGrlspec().getUrnspec();
        } else if( element instanceof Responsibility ){
        	urnspec = ((Responsibility) element).getUrndefinition().getUrnspec();
        }  else if( element instanceof Component ){
        	urnspec = ((Component) element).getUrndefinition().getUrnspec();
        }  else if (element instanceof EvaluationStrategy) {
            urnspec = ((EvaluationStrategy) element).getGroup().getGrlspec().getUrnspec();
        } else if (element instanceof StrategiesGroup) {
            urnspec = ((StrategiesGroup) element).getGrlspec().getUrnspec();
        } else if( element instanceof UCMmap ) {
        	urnspec = ((UCMmap) element).getUrndefinition().getUrnspec();
        } else if( element instanceof GRLGraph ) {
        	urnspec = ((GRLGraph) element).getUrndefinition().getUrnspec();
        } else if (element instanceof ScenarioDef) {
            urnspec = ((ScenarioDef) element).getGroup().getUcmspec().getUrnspec();
        } else if (element instanceof ScenarioGroup) {
            urnspec = ((ScenarioGroup) element).getUcmspec().getUrnspec();
        }
        
        return urnspec;
	}
	
	private void endNewLink( URNmodelElement element )
	{
		String response;
		URNmodelElement toElement = element;

        if (toElement != null) {
    		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        	String title = Messages.getString("EditURNLink.EnterLinkType"); //$NON-NLS-1$
        	String message = Messages.getString("EditURNLink.CreatingURNLinkFrom") + this.className( fromElement ) + " \"" + URNElementFinder.getParentElement( fromElement ).getName() + //$NON-NLS-1$ //$NON-NLS-2$
        			Messages.getString("EditURNLink.QuoteTo") + this.className( toElement ) + " \"" + URNElementFinder.getParentElement( toElement ).getName() //$NON-NLS-1$ //$NON-NLS-2$
        			+ Messages.getString("EditURNLink.PleaseEnterLinkType"); //$NON-NLS-1$

        	URNlinkTypeSelectionDialog typeInput = new URNlinkTypeSelectionDialog( shell, urnspec, title, message, "", Messages.getString("EditURNLink.CreateNewURNLink"), Messages.getString("EditURNLink.CancelURNLinkCreation") );  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		
        	if( (response = typeInput.open()) == null )
        		return;
       	
        	URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
        	newLink.setType( new String(response) );
            AddUrnLinkCommand cmd = new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement);
            if (cmd.canExecute()) {
                execute(cmd);
            }
        }
		
		fromElement = null; // clear start link
	}
	
	private void editLinkType( URNlink selectedLink )
	{
		String response;
		
		String oldType = selectedLink.getType();
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	String title = Messages.getString("EditURNLink.ModifyURNLinkType"); //$NON-NLS-1$
    	String message = Messages.getString("EditURNLink.PleaseModifyLinkType"); //$NON-NLS-1$
    	
    	URNlinkTypeSelectionDialog typeInput = new URNlinkTypeSelectionDialog( shell, urnspec, title, message, (oldType != null) ? oldType : "", //$NON-NLS-1$
    			Messages.getString("EditURNLink.Accept"), Messages.getString("EditURNLink.CancelModifyLink") );  //$NON-NLS-1$ //$NON-NLS-2$
		
    	if( (response = typeInput.open()) == null )
    		return;
    	
		if( (oldType != null) && (response.compareTo( oldType ) == 0) )
			return;
		
        ModifyUrnLinkCommand cmd = new ModifyUrnLinkCommand( selectedLink, response );
        if (cmd.canExecute()) {
            execute(cmd);
        }
	}

	private void deleteLink( URNlink selectedLink )
	{
		final int CANCEL = 0;
		final int DELETE = 1;
		int userChoice = DELETE;

		final String title = Messages.getString("EditURNLink.DeleteURNLink"); //$NON-NLS-1$
		final String message = Messages.getString("EditURNLink.AreYouSureYouWantToDelete") + selectedLink.getFromElem().getName() //$NON-NLS-1$
				+ Messages.getString("EditURNLink.QuoteAndQuote") + selectedLink.getToElem().getName() + "\" ?"; //$NON-NLS-1$ //$NON-NLS-2$
		final String[] labels = { Messages.getString("EditURNLink.Cancel"), Messages.getString("EditURNLink.DeleteLink") }; //$NON-NLS-1$ //$NON-NLS-2$

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog md = new MessageDialog( shell, title, null, message, MessageDialog.QUESTION, labels, DELETE );
		md.create();
		userChoice = md.open();

		if( userChoice == CANCEL)
			return;

		DeleteURNlinkCommand cmd = new DeleteURNlinkCommand( selectedLink );
		if (cmd.canExecute()) {
			execute(cmd);
		}
	}
	
	private void navigateLink( URNlink selectedLink, boolean outgoing )
	{
		URNmodelElement linkStart, linkEnd, oppositeEnd;
		UCMNavMultiPageEditor editor;
		IViewPart strategyVP;
		
		Class [] outlineTreeClasses = { IntentionalElement.class, Actor.class, Component.class, Responsibility.class };

		Class [] strategyTreeClasses = { EvaluationStrategy.class, StrategiesGroup.class, ScenarioDef.class, ScenarioGroup.class };

		linkStart = selectedLink.getFromElem();
		linkEnd = selectedLink.getToElem();

		IURNDiagram startDiagram = null, endDiagram = null, oppositeDiagram;

		if( linkStart instanceof IURNNode ){
			startDiagram = ((IURNNode) linkStart).getDiagram();
		} else if( linkStart instanceof IURNContainerRef ){
			startDiagram = ((IURNContainerRef) linkStart).getDiagram();
		}

		if( linkEnd instanceof IURNNode ){
			endDiagram = ((IURNNode) linkEnd).getDiagram();
		} else if( linkEnd instanceof IURNContainerRef ){
			endDiagram = ((IURNContainerRef) linkEnd).getDiagram();
		}

		if( outgoing ) { // show target of an outgoing link
			oppositeEnd = linkEnd;
			oppositeDiagram = endDiagram;
		} else { // show source of an incoming link
			oppositeEnd = linkStart;
			oppositeDiagram = startDiagram;
		}

		if( oppositeEnd instanceof IURNDiagram ) {

			if( (editor = this.getActiveEditor()) == null ) {
				System.err.println( "UCMNavMultiPageEditor not found. Aborting URN Link Navigation" ); //$NON-NLS-1$
				return;
			}
			
			editor.setActivePage( (IURNDiagram) oppositeEnd );

		} else if( this.includesClass( oppositeEnd, outlineTreeClasses ) ) { // highlight elements in URN Outline tree view

			if( (editor = this.getActiveEditor()) == null ) {
				System.err.println( "UCMNavMultiPageEditor not found. Aborting URN Link Navigation" ); //$NON-NLS-1$
				return;
			}

			UrnOutlinePage outline = (UrnOutlinePage) editor.getAdapter ( IContentOutlinePage.class );
			TreeViewer outlineViewer = (TreeViewer) outline.getViewer();
			EditPart oppositeEnd_EP = (EditPart) outlineViewer.getEditPartRegistry().get( oppositeEnd );

			if( oppositeEnd_EP != null )
				outlineViewer.select( oppositeEnd_EP );
			else
				System.err.println( "navigateLink: EditPart oppositeEnd_EP not found." ); //$NON-NLS-1$

			return;

		} else if( this.includesClass( oppositeEnd, strategyTreeClasses ) ) { // highlight elements in Strategies tree view

			if( (editor = this.getActiveEditor()) == null ) {
				System.err.println( "UCMNavMultiPageEditor not found. Aborting URN Link Navigation" ); //$NON-NLS-1$
				return;
			}

			if( (strategyVP = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView( "seg.jUCMNav.views.StrategiesView" )) == null ) { //$NON-NLS-1$
				System.err.println( "IViewPart for strategies view not found. Aborting URN Link Navigation" ); //$NON-NLS-1$
				return;				
			}
			
			if( strategyVP instanceof StrategiesView ) {
				((StrategiesView) strategyVP).highlightTreeElement( oppositeEnd );
			}

		} else if( oppositeDiagram == null ){
			System.err.println( "navigateLink: Target diagram is null" ); //$NON-NLS-1$
			return;
		}		

		GraphicalViewer viewer = null;

		if( selectedEditPart.getRoot() instanceof UCMConnectionOnBottomRootEditPart ) {
			editor = ((UCMConnectionOnBottomRootEditPart) selectedEditPart.getRoot()).getMultiPageEditor();
		} else if( selectedEditPart.getRoot() instanceof GrlConnectionOnBottomRootEditPart ) {
			editor = ((GrlConnectionOnBottomRootEditPart) selectedEditPart.getRoot()).getMultiPageEditor();
		} else {
			editor = this.getActiveEditor();
			if( JUCMNavPlugin.isInDebug() ) System.out.println( "EditPart not graphical class: " + selectedEditPart.getClass().getSimpleName() ); //$NON-NLS-1$
		}

		if( editor == null ) {
			System.err.println( "UCMNavMultiPageEditor not found. Aborting URN Link Navigation" ); //$NON-NLS-1$
			return;
		}

		if( startDiagram != endDiagram ){ // switch diagrams
			editor.setActivePage( oppositeDiagram );
		} 

		// highlight target element
		if( (viewer = this.getViewer(oppositeDiagram, editor)) != null ) {
			viewer.select((EditPart) viewer.getEditPartRegistry().get( oppositeEnd ));
		}
	}

	private GraphicalViewer getViewer( IURNDiagram diagram, UCMNavMultiPageEditor editor )
	{
		if( diagram instanceof UCMmap ){
			return ((UcmEditor) editor.getCurrentPage()).getGraphicalViewer();			
		} else if( diagram instanceof GRLGraph ){
			return ((GrlEditor) editor.getCurrentPage()).getGraphicalViewer();			
		}
		return null;
	}
	
	private UCMNavMultiPageEditor getActiveEditor()
	{
        UCMNavMultiPageEditor editor = null;
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        }
        return editor;
    }

    /**
     * Take a command and execute it in the command stack of the editor.
     * 
     * @param command
     *            The command we want to execute.
     */
    protected void execute(Command command) {
        if (command == null || !command.canExecute())
            return;
        commandStack.execute(command); // Execute the command
    }

    protected void undo() {
        commandStack.undo(); // Undo the last command
    }

    protected void redo() {
        commandStack.redo(); // Redo the last command
    }

}
