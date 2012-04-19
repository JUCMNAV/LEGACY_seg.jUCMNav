package seg.jUCMNav.actions.metadata;

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
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
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
	
	final int FALSE = 0;
	final int TRUE = 1;
	
	class AddTagListener implements Listener {
		private String name, value;
		private URNmodelElement element;
		
		AddTagListener( String stn, String stv, URNmodelElement elem ) { name = stn; value = stv; element = elem; }
		
		public void handleEvent(Event event) {
			addTag( name, value, element );
		}
	}
	
	class RemoveTagListener implements Listener {
		private String name, value;
		private URNmodelElement element;
		
		RemoveTagListener( String stn, String stv, URNmodelElement elem ) { name = stn; value = stv; element = elem; }
		
		public void handleEvent(Event event) {
			removeTag( name, value, element );
		}
	}

	public void tagElement( CommandStack cmdStack, URNmodelElement selectedElement, URNspec urnspec )
	{
    	int reference = FALSE;
		boolean hasParent = false;
    	String className, parentClassName = null;
    	URNmodelElement tagElement = null;
    	
		this.element = selectedElement;
		className = this.className( element );

		this.parentElement = URNElementFinder.getParentElement( element );
		this.urnspec = urnspec;
    	commandStack = cmdStack;

		if( element != parentElement ) {
			hasParent = true;
			parentClassName = this.className( parentElement );
		}
		
    	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	Menu menu = new Menu(shell, SWT.POP_UP);

    	MenuItem item = new MenuItem(menu, SWT.PUSH);
    	item.setText(Messages.getString("TagURNElements.TagElementQuote") + parentElement.getName() + Messages.getString("TagURNElements.TagElementEndQuote")); //$NON-NLS-1$ //$NON-NLS-2$
    	item.setEnabled(false);
    	MenuItem item2 = new MenuItem(menu, SWT.SEPARATOR);

		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();	
			if(md.getName().equalsIgnoreCase( "StereotypeDef" )){ //$NON-NLS-1$
				tagElement = null;
				String tagClassName = this.getTagClassName( md.getValue() );
				if( JUCMNavPlugin.isInDebug() ) System.out.println( "In TagURNElements tagClassName: " + tagClassName + " className: " + className + " parentClassName: " + parentClassName ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				if( tagClassName.equals(className) ) {
					tagElement = element;
					reference = TRUE;
				} else if(  hasParent && tagClassName.equals(parentClassName) ) {
					tagElement = parentElement;
					reference = FALSE;
				}
				
				if( tagElement != null ) { // temporary using strings, need comparison using instanceof
					int index = md.getValue().indexOf( ',' );
					String stName = md.getValue().substring( 0, index );
					String stValue = md.getValue().substring( index+1, md.getValue().indexOf( ',', index+1));
					this.processStDefinition( stName, stValue, tagElement, menu, reference );
					if( JUCMNavPlugin.isInDebug() ) System.out.println( "name: \"" + stName + "\" value:  \"" + stValue + "\"" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
		}

    	menu.setVisible(true);
	}
	
	private String getTagClassName( String value ) {
		return( value.substring( value.lastIndexOf(',')+1 ));
	}
	
	private void processStDefinition( String name, String value, URNmodelElement element, Menu menu, int reference )
	{
		String [] suffixes = { "Definition", "Reference" };
		MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		
		if( this.hasExistingStereotype( name, value, element )) { // add menu item to remove stereotype
			menuItem.setText( Messages.getString("TagURNElements.Remove") + value + " from " + suffixes[reference] ); //$NON-NLS-1$
			menuItem.addListener( SWT.Selection, new RemoveTagListener( name, value, element ));
		} else { // add menu item to add stereotype
			menuItem.setText( Messages.getString("TagURNElements.Add") + value + " to " + suffixes[reference] ); //$NON-NLS-1$
			menuItem.addListener( SWT.Selection, new AddTagListener( name, value, element ));
		}
	}
	
	private boolean hasExistingStereotype( String name, String value, URNmodelElement element )
	{
		for( Iterator iter = element.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();	
			if(md.getName().equalsIgnoreCase( name )){
				if( md.getValue().equalsIgnoreCase( value ))
					return true;
			}
		}
				
		return false;
	}
	
	private void addTag( String name, String value, URNmodelElement element )
	{
        Metadata newMetadata;
        
        EList mdList = element.getMetadata();
        int newSize = mdList.size() + 1;
        Metadata [] mdArray = (Metadata[]) mdList.toArray(new Metadata[newSize]);
        
        newMetadata = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        newMetadata.setName( name );
        newMetadata.setValue( value );
        mdArray[newSize-1] = newMetadata;
        
        Command cmd = new ChangeMetadataCommand( element, mdArray, Messages.getString("TagURNElements.addTag") ); //$NON-NLS-1$
	
        if (cmd.canExecute()) {
        	commandStack.execute(cmd);
        }
	}
	
	private void removeTag( String name, String value, URNmodelElement element )
	{
		int newSize, i = 0;

        EList mdList = element.getMetadata();
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
				
        Command cmd = new ChangeMetadataCommand( element, mdArray, Messages.getString("TagURNElements.removeTag") ); //$NON-NLS-1$
    	
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
