package urn.util;

import grl.Actor;
import grl.ElementLink;
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
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.editparts.UCMConnectionOnBottomRootEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.change.ModifyUrnLinkCommand;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import urn.URNlink;
import urn.URNspec;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

public class EditURNLink {
	
	private static URNmodelElement fromElement = null;
	private static URNmodelElement selectedElement = null;
	private static EditPart fromEP = null;
	private static EditPart selectedEP = null;
	private static CommandStack commandStack;
    private static URNspec urn; // The urnspec of the current model
    
	
	public void EditLink( CommandStack cmdStack, URNmodelElement element, EditPart ep )
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
			
			DeleteListener( URNlink ul ){	link = ul; }
			
			public void handleEvent(Event event) {
				DeleteLink( link );
			}
		}
		
		class NavigateListener implements Listener {
			private URNlink link;
			
			NavigateListener( URNlink ul ){	link = ul; }
			
			public void handleEvent(Event event) {
				NavigateLink( link );
			}
		}

		commandStack = cmdStack;
		selectedElement = element;
		selectedEP = ep;
		
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	    Menu menu = new Menu(shell, SWT.POP_UP);
	
	    MenuItem item = new MenuItem(menu, SWT.PUSH);
	    item.setText("URN Links for \"" + selectedElement.getName() + "\"");
	    item.setEnabled(false);
	    MenuItem item4 = new MenuItem(menu, SWT.SEPARATOR);
	    MenuItem item2 = new MenuItem(menu, SWT.PUSH);
	    if( fromElement == null )
	    	item2.setText( "Start New Link" );
	    else
	    	item2.setText( "Start New Link *" );
	    
	    item2.addListener( SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	StartNewLink( selectedElement );
	          }
	        });
	    
	    if( fromElement != null && fromElement != selectedElement ){
	    	
		    MenuItem item21 = new MenuItem(menu, SWT.PUSH);
		    item21.setText( "End New Link from \"" + fromElement.getName() + "\"" );
	    	
		    item21.addListener( SWT.Selection, new Listener() {
		        public void handleEvent(Event event) {
		        	EndNewLink( selectedElement );
		          }
		        });	    	
	    }
	    
	    int outgoingSize = selectedElement.getFromLinks().size();
	    
	    if( outgoingSize > 0 ){ 
	    	
	    	new MenuItem(menu, SWT.SEPARATOR);

	    	final MenuItem[] ogLinks = new MenuItem[outgoingSize];
	    	final Menu[] pulldownMenus = new Menu[outgoingSize];
	    	final MenuItem[] pei = new MenuItem[outgoingSize];
	    	final MenuItem[] pni = new MenuItem[outgoingSize];
	    	final MenuItem[] pdi = new MenuItem[outgoingSize];
	    	int i = 0;

	    	for (Iterator it = selectedElement.getFromLinks().iterator(); it.hasNext();) {
	    		URNlink link = (URNlink) it.next();
	    		String text = "(" + link.getType() + ") to \"" + link.getToElem().getName() + "\"";
	    		ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
	    		ogLinks[i].setText( text );
		    	ogLinks[i].setImage(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$
	    		
			    pulldownMenus[i] = new Menu(shell, SWT.DROP_DOWN);
		    	
			    pei[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
		    	pei[i].setText( "Modify Link Type" );
		    	pei[i].addListener( SWT.Selection, new EditListener( link ));
		    	pdi[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
		    	pdi[i].setText( "Delete Link" );
		    	pdi[i].addListener( SWT.Selection, new DeleteListener( link));
		    	pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
		    	pni[i].setText( "Show Target" );
		    	pni[i].addListener( SWT.Selection, new NavigateListener( link));

	    		ogLinks[i].setMenu(pulldownMenus[i]);
	    		i++;
	    	}	    	    
	    }
	    
	    int incomingSize = selectedElement.getToLinks().size();
	    
	    if( incomingSize > 0 ){    
	    	new MenuItem(menu, SWT.SEPARATOR);

	    	final MenuItem[] icLinks = new MenuItem[incomingSize];
	    	final Menu[] pulldownMenus = new Menu[incomingSize];
	    	final MenuItem[] pei = new MenuItem[incomingSize];
	    	final MenuItem[] pdi = new MenuItem[incomingSize];
	    	final MenuItem[] pni = new MenuItem[incomingSize];
	    	
	    	int i = 0;

	    	for (Iterator it = selectedElement.getToLinks().iterator(); it.hasNext();) {
	    		URNlink link = (URNlink) it.next();
	    		String text = "(" + link.getType() + ") from \"" + link.getFromElem().getName() + "\"";
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
		    	
		    	if( this.IsNavigable( link )){
		    		pni[i] = new MenuItem( pulldownMenus[i], SWT.PUSH );
		    		pni[i].setText( "Show Target" );
		    		pni[i].addListener( SWT.Selection, new NavigateListener( link ));
		    	}
		    	
	    		icLinks[i].setMenu(pulldownMenus[i]);
	    		i++;
	    	}	    	    
	    }
	    
	    menu.setVisible(true);
	}
	
	private boolean IsNavigable( URNlink selectedLink )
	{
		if( !(selectedLink.getToElem() instanceof IURNNode || selectedLink.getToElem() instanceof IURNContainerRef) )
			return false;
		
		if( !(selectedLink.getFromElem() instanceof IURNNode || selectedLink.getFromElem() instanceof IURNContainerRef) )
			return false;
		
		return true;
	}
	
	private void StartNewLink( URNmodelElement element )
	{
		fromElement = element;
		
        if (fromElement instanceof IntentionalElement) {
            urn = ((IntentionalElement) fromElement).getGrlspec().getUrnspec();
        } else if (fromElement instanceof Actor) {
            urn = ((Actor) fromElement).getGrlspec().getUrnspec();
        } else if( fromElement instanceof IURNNode ){
        	urn = ((IURNNode) fromElement).getDiagram().getUrndefinition().getUrnspec();
        } else if( fromElement instanceof ElementLink ){
        	urn = ((ElementLink) fromElement).getGrlspec().getUrnspec();
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
	
	private void NavigateLink( URNlink selectedLink )
	{
		//	     if (activeBindings.size() == 1 && getNode() instanceof StartPoint) {
		//             // if only one plugin, open it.
		//             InBinding binding = ((InBinding) activeBindings.get(0));
		//             UCMmap map = (UCMmap) binding.getBinding().getStub().getDiagram();
		//             if (map != null) {
		//                 ((UCMConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor().setActivePage(map);
		//                 GraphicalViewer viewer = ((UcmEditor) ((UCMConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor().getCurrentPage())
		//                         .getGraphicalViewer();
		//                 viewer.select((EditPart) viewer.getEditPartRegistry().get(binding.getBinding().getStub()));
		//             }


		URNmodelElement linkStart, linkEnd;

		linkStart = selectedLink.getFromElem();
		linkEnd = selectedLink.getToElem();

		IURNDiagram startDiagram = null, endDiagram = null;

		if( linkStart instanceof IURNNode ){
			startDiagram = ((IURNNode) linkStart).getDiagram();
		}

		if( linkEnd instanceof IURNNode ){
			endDiagram = ((IURNNode) linkEnd).getDiagram();
		}

		if( startDiagram == null || endDiagram == null ){
			System.err.println( "NavigateLink: diagrams are null" );
			return;
		}

		if( startDiagram != endDiagram ){ // switch diagrams
			

		} // highlight target element
//		EditPart rootPart = 
//		GraphicalViewer viewer = ((UcmEditor) ((UCMConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor().getCurrentPage())
//				.getGraphicalViewer();
//		viewer.select((EditPart) viewer.getEditPartRegistry().get( linkEnd ));

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
