package seg.jUCMNav.actions;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import seg.jUCMNav.model.util.ArrayAndListUtils;
import urn.URNspec;
import urncore.Metadata;

/**
 * Adds stereotype definitions to a URN spec.
 * 
 * @author amiga
 */

public class AddStereotypeDefinitionsAction extends URNSelectionAction {

    public static final String ADD_STEREOTYPE_DEFINITIONS = "seg.jUCMNav.AddStereotypeDefinitions"; //$NON-NLS-1$

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
        
        boolean hasExistingDefinitions = this.hasStereotypeDefinitions(urnspec);
        
        if( JUCMNavPlugin.isInDebug() ) System.out.println( "hasExistingDefinitions = " + hasExistingDefinitions );
        
        return ( !hasExistingDefinitions );
    }

     protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        URNspec urnspec = sel.getUrnspec();
        
        if( this.hasStereotypeDefinitions(urnspec))
        	return null;
        
        Metadata [] sdList = this.getStereotypeDefinitions(urnspec);
        Metadata [] mdList = (Metadata[]) urnspec.getMetadata().toArray(new Metadata[0]);
        Metadata[] combinedList = ArrayAndListUtils.concatenateArrays( mdList, sdList );
        
        Command cmd = new ChangeMetadataCommand( urnspec, combinedList, Messages.getString("ActionRegistryManager.addStereotypeDefinitions") );
        
        return cmd;
    }
    
    protected boolean hasStereotypeDefinitions( URNspec urnspec ) {
    	
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
    
    protected Metadata [] getStereotypeDefinitions( URNspec urnspec ) {    	
//    	name="StereotypeDef", value="ST_CLASSTYPE,CLASS1,IntentionalElement"
//    	name="StereotypeDef", value="ST_CLASSTYPE,CLASS2,IntentionalElement"
//    	name="StereotypeDef", value="ST_CLASSTYPE,OTHER,IntentionalElement"
//    	name="StereotypeDef", value="acceptStereotype,CLASS1,EvaluationStrategy"
//    	name="StereotypeDef", value="acceptStereotype,CLASS2,EvaluationStrategy"
//    	name="StereotypeDef", value="acceptStereotype,OTHER,EvaluationStrategy"

    	String [] values = { "ST_CLASSTYPE,CLASS1,IntentionalElement", "ST_CLASSTYPE,CLASS2,IntentionalElement", "ST_CLASSTYPE,OTHER,IntentionalElement",
    			"acceptStereotype,CLASS1,EvaluationStrategy", "acceptStereotype,CLASS2,EvaluationStrategy", "acceptStereotype,OTHER,EvaluationStrategy"
    	};
    	
    	// populate with stereotypes
        Metadata [] mdList = new Metadata[values.length];
        Metadata newMetadata;
        for( int i = 0; i < values.length; i++ ) {
        	newMetadata = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        	newMetadata.setName("StereotypeDef");
        	newMetadata.setValue(values[i]);
        	mdList[i] = newMetadata;
        }
        
        return mdList;
    }

}
