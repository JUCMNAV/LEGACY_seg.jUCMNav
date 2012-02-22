package urn.util;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import urn.URNspec;
import urn.impl.URNlinkImpl;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Adds or removes stereotype tags to/from URN model elements.
 * 
 * @author amiga
 */

public class TagURNElements {

	private URNmodelElement element;
	private URNmodelElement parentElement;
	private CommandStack commandStack;
	private URNspec urnspec;
	
	class AddTagListener implements Listener {
		private String name, value;
		
		AddTagListener( String stn, String stv ) { name = stn; value = stv; }
		
		public void handleEvent(Event event) {
			addTag( name, value );
		}
	}
	
	class RemoveTagListener implements Listener {
		private String name, value;
		
		RemoveTagListener( String stn, String stv ) { name = stn; value = stv; }
		
		public void handleEvent(Event event) {
			removeTag( name, value );
		}
	}

	public void tagElement( CommandStack cmdStack, URNmodelElement element, URNspec urnspec )
	{
		if( JUCMNavPlugin.isInDebug() ) System.out.println( "Tag Element ... selected for element: " + element.getName());

		this.element = element;
		this.parentElement = URNlinkImpl.getParentElement( element );
		this.urnspec = urnspec;
    	commandStack = cmdStack;

		String className = this.className( parentElement );

    	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	Menu menu = new Menu(shell, SWT.POP_UP);

    	MenuItem item = new MenuItem(menu, SWT.PUSH);
    	item.setText("Tag Element \"" + parentElement.getName() + "\"");
    	item.setEnabled(false);
    	MenuItem item2 = new MenuItem(menu, SWT.SEPARATOR);

		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();	
			if(md.getName().equalsIgnoreCase( "StereotypeDef" )){
				if( md.getValue().contains(className)) { // temporary using strings, need comparison using instanceof
					
					int index = md.getValue().indexOf( ',' );
					String stName = md.getValue().substring( 0, index );
					String stValue = md.getValue().substring( index+1, md.getValue().indexOf( ',', index+1));
					this.processStDefinition( stName, stValue, menu);
					if( JUCMNavPlugin.isInDebug() ) System.out.println( "name: \"" + stName + "\" value:  \"" + stValue + "\"" );
					
				}
			}
		}

    	menu.setVisible(true);

	}
	
	private void processStDefinition( String name, String value, Menu menu )
	{
		MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		
		if( this.hasExistingStereotype(name, value)) { // add menu item to remove stereotype
			menuItem.setText( "Remove " + value );
			menuItem.addListener( SWT.Selection, new RemoveTagListener( name, value ));
		} else { // add menu item to add stereotype
			menuItem.setText( "Add " + value );
			menuItem.addListener( SWT.Selection, new AddTagListener( name, value ));
		}
	}
	
	private boolean hasExistingStereotype( String name, String value )
	{
		for( Iterator iter = parentElement.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();	
			if(md.getName().equalsIgnoreCase( name )){
				if( md.getValue().equalsIgnoreCase( value ))
					return true;
			}
		}
				
		return false;
	}
	
	private void addTag( String name, String value )
	{
        Metadata newMetadata;
        
        EList mdList = parentElement.getMetadata();
        int newSize = mdList.size() + 1;
        Metadata [] mdArray = (Metadata[]) mdList.toArray(new Metadata[newSize]);
        
        newMetadata = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        newMetadata.setName( name );
        newMetadata.setValue( value );
        mdArray[newSize-1] = newMetadata;
        
        Command cmd = new ChangeMetadataCommand( parentElement, mdArray, Messages.getString("TagURNElements.addTag") );
	
        if (cmd.canExecute()) {
        	commandStack.execute(cmd);
        }
	}
	
	private void removeTag( String name, String value )
	{
		int newSize, i = 0;

        EList mdList = parentElement.getMetadata();
        newSize = mdList.size() - 1;
        Metadata [] mdArray = new Metadata[newSize];
        
		for( Iterator iter = mdList.iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();
			boolean tagMatches = false;
			if(md.getName().equalsIgnoreCase( name )){
				if( md.getValue().equalsIgnoreCase( value )) {
					tagMatches = true;
				}
			}
			if( !tagMatches ) {
				mdArray[i++] = md;
			}
		}
				
        Command cmd = new ChangeMetadataCommand( parentElement, mdArray, Messages.getString("TagURNElements.removeTag") );
    	
        if (cmd.canExecute()) {
        	commandStack.execute(cmd);
        }		
	}
	
	private String className( URNmodelElement element )
	{
	    String className = element.getClass().getSimpleName();
	    return className.substring( 0, className.length()-4 );  // strip suffix 'Impl' from class name
	}
}
