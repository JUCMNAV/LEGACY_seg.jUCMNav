package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import seg.jUCMNav.model.util.ArrayAndListUtils;
import urn.URNspec;
import urncore.Metadata;

/**
 * Adds default stereotype definitions to a URN spec.
 * 
 * @author amiga
 */

public class AddStereotypeDefinitionsAction extends URNSelectionAction {

    public static final String ADD_STEREOTYPE_DEFINITIONS = "seg.jUCMNav.AddStereotypeDefinitions"; //$NON-NLS-1$

    // Do not externalize these strings. 
    private String [] values = { 
    		"ST_CLASSTYPE,CLASS1,IntentionalElement", //$NON-NLS-1$
    		"ST_CLASSTYPE,CLASS2,IntentionalElement", //$NON-NLS-1$
    		"ST_CLASSTYPE,OTHER,IntentionalElement", //$NON-NLS-1$ 
    		"acceptStereotype,CLASS1,EvaluationStrategy", //$NON-NLS-1$
    		"acceptStereotype,CLASS2,EvaluationStrategy", //$NON-NLS-1$
    		"acceptStereotype,OTHER,EvaluationStrategy" //$NON-NLS-1$
    };

    // Do not externalize these strings. 
    private String [] linkTypes = { "Traceability", "Refinement", "Compliance" }; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
    
	public AddStereotypeDefinitionsAction(IWorkbenchPart part) {
		super(part);
        setId(ADD_STEREOTYPE_DEFINITIONS);
	}

    /**
     * If you have a URNspec selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        URNspec urnspec = sel.getUrnspec();

        if( urnspec == null )
        	return false;
        else
        	return true;
    }

     protected Command getCommand()
     {
 		String title, message, commandLabel;
 		Metadata [] mdList, sdList;
 		
		final int CANCEL = 0;
		final int UPDATE = 1;
		int userChoice;
		
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        URNspec urnspec = sel.getUrnspec();
        
        if( urnspec == null )
        	return null;
        
        if( this.hasStereotypeDefinitions(urnspec)) { // inform user of existing definitions and provide option to cancel 
        
            // remove previous definitions by making a list of all other URNspec metadata
            Vector<Metadata> otherMetadata = new Vector<Metadata>();
            Vector<Metadata> existingMetadata = new Vector<Metadata>();
            
        	if( urnspec.getMetadata().size() > 0 ) {
        		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
        			Metadata md = (Metadata) iter.next();	
        			if( md.getName().equalsIgnoreCase( "StereotypeDef" )){ //$NON-NLS-1$
        				existingMetadata.add(md);
        			} else {
        				otherMetadata.add(md);        				
        			}
        		}    		
        	}
        	
			StringBuilder messageBuf = new StringBuilder();
			messageBuf.append( Messages.getString("AddStereotypeDefinitionsAction.DefinitionsAlreadyExist") ); //$NON-NLS-1$
        
			for( Metadata em : existingMetadata ) {
				messageBuf.append( "\t" + em.getValue() + "\n" ); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			messageBuf.append( Messages.getString("AddStereotypeDefinitionsAction.DoYouWishToUpdate")); //$NON-NLS-1$
			
			title = Messages.getString("AddStereotypeDefinitionsAction.StereotypeDefinitionsExist"); //$NON-NLS-1$
			message = messageBuf.toString();
			
			final String[] labels = { Messages.getString("AddStereotypeDefinitionsAction.Cancel"), Messages.getString("AddStereotypeDefinitionsAction.UpdateStereotypeDefinitions") }; //$NON-NLS-1$ //$NON-NLS-2$

			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog md = new MessageDialog( shell, title, null, message, MessageDialog.QUESTION, labels, UPDATE );
			md.create();
			userChoice = md.open();

			if( userChoice == CANCEL) {
				return null;
			}

            mdList = (Metadata[]) otherMetadata.toArray(new Metadata[0]);
        	commandLabel = Messages.getString("ActionRegistryManager.updateStereotypeDefinitions"); //$NON-NLS-1$
        } else {
        	mdList = (Metadata[]) urnspec.getMetadata().toArray(new Metadata[0]);
        	commandLabel = Messages.getString("ActionRegistryManager.addStereotypeDefinitions"); //$NON-NLS-1$
        }
        
        sdList = this.getStereotypeDefinitions(urnspec);        
        
        Metadata[] combinedList = ArrayAndListUtils.concatenateArrays( mdList, sdList );
        
        Command cmd = new ChangeMetadataCommand( urnspec, combinedList, commandLabel );
        
        return cmd;
    }
    
    protected boolean hasStereotypeDefinitions( URNspec urnspec )
    {	
    	if( urnspec.getMetadata().size() > 0 ) {
    		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
    			Metadata md = (Metadata) iter.next();	
    			if(md.getName().equalsIgnoreCase( "StereotypeDef" )){ //$NON-NLS-1$
    					return true;
    			}
    		}    		
    	}
    	
    	return false;
    }
    
    protected Metadata [] getStereotypeDefinitions( URNspec urnspec )
    {    		
    	// populate new list with stereotypes
        Metadata [] mdList = new Metadata[values.length];
        Metadata newMetadata;
        for( int i = 0; i < values.length; i++ ) {
        	newMetadata = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        	newMetadata.setName("StereotypeDef"); //$NON-NLS-1$
        	newMetadata.setValue(values[i]);
        	mdList[i] = newMetadata;
        }
        
        return mdList;
    }
    
    protected void addUrnLinkTypes( URNspec urnspec )
    {
        for( int i = 0; i < linkTypes.length; i++ ) {

        	
        	
        }
    }
}
