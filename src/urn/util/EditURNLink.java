package urn.util;

import grl.Actor;
import grl.ElementLink;
import grl.GRLGraph;
import grl.IntentionalElement;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

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
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urn.impl.URNlinkImpl;
import urncore.Component;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

public class EditURNLink {
	
	private static URNmodelElement fromElement = null;
	private static URNmodelElement selectedElement = null;
	private static URNmodelElement selectedElementParent = null;
	private static EditPart fromEditPart = null;
	private static EditPart selectedEditPart = null;
	private static CommandStack commandStack;
    private static URNspec urn; // The urnspec of the current model
    
	
    public void EditLink( CommandStack cmdStack, URNmodelElement element, URNmodelElement parentElement, EditPart ep )
    {

    	class EditListener implements Listener {
    		private URNlink link;

    		EditListener( URNlink ul ){	link = ul; }

    		public void handleEvent(Event event) {
    			EditLinkType( link );
    		}
    	}

    	class DeleteListener implements Listener {
    		private URNlink link;

    		DeleteListener( URNlink ul ){ link = ul; }

    		public void handleEvent(Event event) {
    			DeleteLink( link );
    		}
    	}

    	class NavigateListener implements Listener {
    		private URNlink link;
    		private boolean outgoing;

    		NavigateListener( URNlink ul, boolean og ){	link = ul; outgoing = og; }

    		public void handleEvent(Event event) {
    			NavigateLink( link, outgoing );
    		}
    	}

    	commandStack = cmdStack;
    	selectedElement = element;
    	selectedElementParent = parentElement;
    	selectedEditPart = ep;

    	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	Menu menu = new Menu(shell, SWT.POP_UP);

    	MenuItem item = new MenuItem(menu, SWT.PUSH);

    	String name;
    	if( parentElement == null)
    		name = selectedElement.getName();
    	else
    		name = selectedElementParent.getName();

    	item.setText("URN Links for \"" + name + "\"");
    	item.setEnabled(false);
    	MenuItem item4 = new MenuItem(menu, SWT.SEPARATOR);

    	// menu item for Start Link from selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements
    	MenuItem item2 = new MenuItem(menu, SWT.PUSH);

    	String startText;
    	if( selectedElementParent != null )
    		startText = "Start New Link from " + className( selectedElement );
    	else
    		startText = "Start New Link";

    	if( fromElement == null )
    		item2.setText( startText );
    	else
    		item2.setText( startText + " *" );

    	item2.addListener( SWT.Selection, new Listener() {
    		public void handleEvent(Event event) {
    			StartNewLink( selectedElement );
    		}
    	});

    	// if applicable, menu item for Start Link from parent URN object Actor, Component, Responsibility, IntentionalElement, ...
    	if( selectedElementParent != null ){
    		MenuItem item22 = new MenuItem(menu, SWT.PUSH);
    		String startText1 = "Start New Link from " + className( selectedElementParent ) + " \"" + selectedElementParent.getName() + "\"";
    		if( fromElement == null )
    			item22.setText( startText1 );
    		else
    			item22.setText( startText1 + " *" );

    		item22.addListener( SWT.Selection, new Listener() {
    			public void handleEvent(Event event) {
    				StartNewLink( selectedElementParent );
    			}
    		});

    	}
    	// add menu items for End Link if a previous element has been selected as a link start
    	if( fromElement != null && fromElement != selectedElement ){

    		MenuItem item21 = new MenuItem(menu, SWT.PUSH);
    		String endText = "End New Link from \"" + URNlinkImpl.getParentElement( fromElement ).getName() + "\"";

    		if( selectedElementParent != null )
    			item21.setText( endText + " to " + this.className( selectedElement ) );
    		else
    			item21.setText( endText );

    		item21.addListener( SWT.Selection, new Listener() {
    			public void handleEvent(Event event) {
    				EndNewLink( selectedElement );
    			}
    		});

    		if( selectedElementParent != null ){

    			MenuItem item23 = new MenuItem(menu, SWT.PUSH);
    			item23.setText( "End New Link from \"" + URNlinkImpl.getParentElement( fromElement ).getName() + "\" to "
    					+ this.className( selectedElementParent ) );

    			item23.addListener( SWT.Selection, new Listener() {
    				public void handleEvent(Event event) {
    					EndNewLink( selectedElementParent );
    				}
    			});
    		}
    	}
    	// determine number of outgoing links
    	int outgoingSize = selectedElement.getFromLinks().size();

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

    		// add outgoing links from selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements  
    		for (Iterator it = selectedElement.getFromLinks().iterator(); it.hasNext();) {
    			URNlink link = (URNlink) it.next();
    			String text = "(" + link.getType() + ") to " + this.className( link.getToElem() )+ " \"" + link.getParentToElem().getName() + 
    					"\" from " + this.className( selectedElement ) + " \"" + URNlinkImpl.getParentElement( selectedElement ).getName() + "\"";
    			ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
    			ogLinks[i].setText( text );
    			ogLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$

    			pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    			pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    			pei[i].setText( "Modify Link Type" );
    			pei[i].addListener( SWT.Selection, new EditListener( link ));
    			pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    			pdi[i].setText( "Delete Link" );
    			pdi[i].addListener( SWT.Selection, new DeleteListener( link ));

    			if( this.IsNavigable( link.getToElem() )){
    				pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pni[i].setText( "Show Target" );
    				pni[i].addListener( SWT.Selection, new NavigateListener( link, true ));
    			}
    			ogLinks[i].setMenu(pulldownMenus[i]);
    			i++;
    		}

    		if( selectedElementParent != null ){
    			// if applicable, add outgoing links from parent URN objects Actor, Component, Responsibility, IntentionalElement, ...
    			for (Iterator it = selectedElementParent.getFromLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + ") to " + this.className( link.getToElem() )+ " \"" + link.getParentToElem().getName() 
    						+ "\" from " + this.className( selectedElementParent ) + " \"" + selectedElementParent.getName() + "\"";
    				ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				ogLinks[i].setText( text );
    				ogLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( "Modify Link Type" );
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( "Delete Link" );
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link ));

    				if( this.IsNavigable( link.getToElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( "Show Target" );
    					pni[i].addListener( SWT.Selection, new NavigateListener( link, true ));
    				}
    				ogLinks[i].setMenu(pulldownMenus[i]);
    				i++;
    			}
    		}
    	}

    	// determine number of incoming links
    	int incomingSize = selectedElement.getToLinks().size();

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

    		// add incoming links to selected diagram element, RespRef, ActorRef, ComponentRef, IntentionalElementRef, and all UCM map elements  
    		for (Iterator it = selectedElement.getToLinks().iterator(); it.hasNext();) {
    			URNlink link = (URNlink) it.next();
    			String text = "(" + link.getType() + ") from " + this.className( link.getFromElem() )+ " \"" + link.getParentFromElem().getName()
    					+ "\" to " + this.className( selectedElement ) + " \"" + URNlinkImpl.getParentElement( selectedElement ).getName() + "\"";
    			icLinks[i] = new MenuItem(menu, SWT.CASCADE);
    			icLinks[i].setText( text );
    			icLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink-reversed.gif")); //$NON-NLS-1$

    			pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    			pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    			pei[i].setText( "Modify Link Type" );
    			pei[i].addListener( SWT.Selection, new EditListener( link ));
    			pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    			pdi[i].setText( "Delete Link" );
    			pdi[i].addListener( SWT.Selection, new DeleteListener( link));

    			if( this.IsNavigable( link.getFromElem() )){
    				pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pni[i].setText( "Show Source" );
    				pni[i].addListener( SWT.Selection, new NavigateListener( link, false ));
    			}

    			icLinks[i].setMenu(pulldownMenus[i]);
    			i++;
    		}

    		if( selectedElementParent != null ){
    			// if applicable, add incoming links to parent URN objects Actor, Component, Responsibility, IntentionalElement, ...
    			for (Iterator it = selectedElementParent.getToLinks().iterator(); it.hasNext();) {
    				URNlink link = (URNlink) it.next();
    				String text = "(" + link.getType() + ") from " + this.className( link.getFromElem() )+ " \"" + 
    				link.getParentFromElem().getName() + "\" to " + this.className( selectedElementParent ) + " \""
    				+ selectedElementParent.getName() + "\"";
    				icLinks[i] = new MenuItem(menu, SWT.CASCADE);
    				icLinks[i].setText( text );
    				icLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink-reversed.gif")); //$NON-NLS-1$

    				pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);

    				pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pei[i].setText( "Modify Link Type" );
    				pei[i].addListener( SWT.Selection, new EditListener( link ));
    				pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    				pdi[i].setText( "Delete Link" );
    				pdi[i].addListener( SWT.Selection, new DeleteListener( link));

    				if( this.IsNavigable( link.getFromElem() )){
    					pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
    					pni[i].setText( "Show Source" );
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
	
	private boolean IsNavigable( URNmodelElement endpoint )
	{
		if( endpoint instanceof IURNNode || endpoint instanceof IURNContainerRef )
			return true;
		
		return false;
	}
	
	private void StartNewLink( URNmodelElement element )
	{
		fromElement = element;
		
        if (fromElement instanceof IntentionalElement) {
            urn = ((IntentionalElement) fromElement).getGrlspec().getUrnspec();
        } else if (fromElement instanceof Actor) {
            urn = ((Actor) fromElement).getGrlspec().getUrnspec();
        } else if( fromElement instanceof IURNNode ){ // handles UCM, GRL Nodes
        	urn = ((IURNNode) fromElement).getDiagram().getUrndefinition().getUrnspec();
        } else if(  fromElement instanceof IURNContainerRef ){ // handles ActorRef, ComponentRef
        	urn = ((IURNContainerRef) fromElement).getDiagram().getUrndefinition().getUrnspec();
        } else if( fromElement instanceof ElementLink ){
        	urn = ((ElementLink) fromElement).getGrlspec().getUrnspec();
        } else if( fromElement instanceof Responsibility ){
        	urn = ((Responsibility) fromElement).getUrndefinition().getUrnspec();
        }  else if( fromElement instanceof Component ){
        	urn = ((Component) fromElement).getUrndefinition().getUrnspec();
        }

	}
	
	private void EndNewLink( URNmodelElement element )
	{
		URNmodelElement toElement = element;

        if (toElement != null) {
        	URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urn, URNlink.class);
    		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        	String title = "Enter URN Link Type";
        	String message = "Creating URN Link from \"" + fromElement.getName() + "\" to \"" + toElement.getName()
        			+ "\".\nPlease enter the URN Link Type.";
        	InputDialog typeInput = new InputDialog( shell, title, message, "", null);
        	if( typeInput.open() == SWT.CANCEL )
        		return;
        	String response = typeInput.getValue();
        	newLink.setType( new String(response) );
            AddUrnLinkCommand cmd = new AddUrnLinkCommand(urn, newLink, fromElement, toElement);
            if (cmd.canExecute()) {
                execute(cmd);
            }
        }
		
		fromElement = null; // clear start link
	}
	
	private void EditLinkType( URNlink selectedLink )
	{
		String oldType = selectedLink.getType();
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	String title = "Modify URN Link Type";
    	String message =  "Please modify the URN Link Type.";
    	InputDialog typeInput = new InputDialog( shell, title, message, oldType, null);

    	if( typeInput.open() == SWT.CANCEL )
    		return;
    	
    	String response = typeInput.getValue();
		
		if( response.compareTo( oldType ) == 0 )
			return;
		
        ModifyUrnLinkCommand cmd = new ModifyUrnLinkCommand( selectedLink, response );
        if (cmd.canExecute()) {
            execute(cmd);
        }
	}

	private void DeleteLink( URNlink selectedLink )
	{
		final int CANCEL = 0;
		final int DELETE = 1;
		int userChoice = DELETE;

		final String title = "Delete URN Link";
		final String message = "Are you sure you want to delete the URN Link between \"" + selectedLink.getFromElem().getName()
				+ "\" and \"" + selectedLink.getToElem().getName() + "\" ?";
		final String[] labels = { "Cancel", "Delete Link" };

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
	
	private void NavigateLink( URNlink selectedLink, boolean outgoing )
	{
		URNmodelElement linkStart, linkEnd, oppositeEnd;

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
		
		if( oppositeDiagram == null ){
			System.err.println( "NavigateLink: Target diagram is null" );
			return;
		}		
		
		UCMNavMultiPageEditor editor;
		GraphicalViewer viewer = null;
		
		if( selectedEditPart.getRoot() instanceof UCMConnectionOnBottomRootEditPart ) {
			editor = ((UCMConnectionOnBottomRootEditPart) selectedEditPart.getRoot()).getMultiPageEditor();
		} else if( selectedEditPart.getRoot() instanceof GrlConnectionOnBottomRootEditPart ) {
			editor = ((GrlConnectionOnBottomRootEditPart) selectedEditPart.getRoot()).getMultiPageEditor();
		} else {
			System.err.println( "EditPart not understood." );
			return;			
		}
		
		if( startDiagram != endDiagram ){ // switch diagrams
			editor.setActivePage( oppositeDiagram );
		} 
		
		if( oppositeDiagram instanceof UCMmap ){
			viewer = ((UcmEditor) editor.getCurrentPage()).getGraphicalViewer();			
		} else if( oppositeDiagram instanceof GRLGraph ){
			viewer = ((GrlEditor) editor.getCurrentPage()).getGraphicalViewer();			
		}
		
		// highlight target element
		if( viewer != null )
			viewer.select((EditPart) viewer.getEditPartRegistry().get( oppositeEnd ));
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
