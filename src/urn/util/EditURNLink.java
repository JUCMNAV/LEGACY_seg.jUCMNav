package urn.util;

import grl.Actor;
import grl.IntentionalElement;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
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

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.change.ModifyUrnLinkCommand;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import urn.URNlink;
import urn.URNspec;
import urncore.IURNNode;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

public class EditURNLink {
	
	private static URNmodelElement fromElement = null;
	private static URNmodelElement selectedElement = null;
	private static CommandStack commandStack;
    private static URNspec urn; // The urnspec of the current model
    
	
	public void EditLink( CommandStack cmdStack, URNmodelElement element )
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
		
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	    Menu menu = new Menu(shell, SWT.POP_UP);
	
	    MenuItem item = new MenuItem(menu, SWT.PUSH);
	    item.setText("Edit URN Links -> \"" + selectedElement.getName() + "\"");
	    item.setEnabled(false);
	    MenuItem item4 = new MenuItem(menu, SWT.SEPARATOR);
	    MenuItem item2 = new MenuItem(menu, SWT.PUSH);
	    if( fromElement == null )
	    	item2.setText( "Start New Link" );
	    else
	    	item2.setText( "Start New Link - current: \"" + fromElement.getName() + "\"" );
	    
	    item2.addListener( SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	StartNewLink( selectedElement );
	          }
	        });
	    
	    if( fromElement != null && fromElement != selectedElement ){
	    	
		    new MenuItem(menu, SWT.SEPARATOR);
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
	    	MenuItem item3 = new MenuItem(menu, SWT.PUSH);
	    	item3.setText("Outgoing Links");
	    	item3.setEnabled(false);
	    	new MenuItem(menu, SWT.SEPARATOR);

	    	final MenuItem[] ogLinks = new MenuItem[outgoingSize];
	    	final Menu[] pulldownMenus = new Menu[outgoingSize];
	    	final MenuItem[] pei = new MenuItem[outgoingSize];
	    	final MenuItem[] pni = new MenuItem[outgoingSize];
	    	final MenuItem[] pdi = new MenuItem[outgoingSize];
	    	int i = 0;

	    	for (Iterator it = selectedElement.getFromLinks().iterator(); it.hasNext();) {
	    		URNlink link = (URNlink) it.next();
	    		String text = "|> (" + link.getType() + ") to \"" + link.getToElem().getName() + "\"";
	    		ogLinks[i] = new MenuItem(menu, SWT.CASCADE);
	    		ogLinks[i].setText( text );
	    		
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
	    	MenuItem item3 = new MenuItem(menu, SWT.PUSH);
	    	item3.setText( "Incoming Links" );
	    	item3.setEnabled(false);
	    	new MenuItem(menu, SWT.SEPARATOR);

	    	final MenuItem[] icLinks = new MenuItem[incomingSize];
	    	final Menu[] pulldownMenus = new Menu[incomingSize];
	    	final MenuItem[] pei = new MenuItem[incomingSize];
	    	final MenuItem[] pni = new MenuItem[incomingSize];
	    	final MenuItem[] pdi = new MenuItem[incomingSize];
	    	
	    	int i = 0;

	    	for (Iterator it = selectedElement.getToLinks().iterator(); it.hasNext();) {
	    		URNlink link = (URNlink) it.next();
	    		String text = "<| (" + link.getType() + ") from \"" + link.getFromElem().getName() + "\"";
	    		icLinks[i] = new MenuItem(menu, SWT.CASCADE);
	    		icLinks[i].setText( text );
	    		
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

	    		icLinks[i].setMenu(pulldownMenus[i]);
	    		i++;
	    	}	    	    
	    }
	    
	    menu.setVisible(true);
	}
	
	private void StartNewLink( URNmodelElement element )
	{
		final int CANCEL = 0;
		final int OVERWRITE = 1;
		int userChoice = OVERWRITE;

		if( fromElement != null ){
    		final String title = "Start Link Element already exists";
    		final String message = "An element has already been selected as a URN Link start\nCurrent Link Start:\t\""
    		+ fromElement.getName() + "\"";
    		final String[] labels = { "Cancel Operation", "Set New Link Start" };
    		
    		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog md = new MessageDialog( shell, title, null, message, MessageDialog.QUESTION, labels, 1 );
			md.create();
			userChoice = md.open();
		}

		if( userChoice == CANCEL)
			return;

		fromElement = element;
		
        if (fromElement instanceof IntentionalElement) {
            urn = ((IntentionalElement) fromElement).getGrlspec().getUrnspec();
        } else if (fromElement instanceof Actor) {
            urn = ((Actor) fromElement).getGrlspec().getUrnspec();
        } else if( fromElement instanceof IURNNode ){
        	urn = ((IURNNode) fromElement).getDiagram().getUrndefinition().getUrnspec();
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
