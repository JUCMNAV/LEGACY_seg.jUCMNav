package seg.jUCMNav.model.commands.metadata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Changes the metadata associated with a urn model element.
 * 
 * @author pchen
 */
public class ChangeMetadataCommand extends Command implements JUCMNavCommand {
    private URNmodelElement urnelem = null;
    private URNspec urnspec = null;
    private Metadata[] oldMetadataArray;
    private Metadata[] newMetadataArray;

    public ChangeMetadataCommand(EObject obj, Metadata[] metadataArray, String label) {
        if ( (obj instanceof URNmodelElement) || (obj instanceof URNspec) ) {
        	
        	if(obj instanceof URNmodelElement)
        		this.urnelem = (URNmodelElement) obj;
        	else
        		this.urnspec = (URNspec) obj;
        	
            this.newMetadataArray = metadataArray;
            
            if( label != null)
            	setLabel( label );
            else
            	setLabel(Messages.getString("ChangeMetadataCommand.ChangeURNmodelElementMetadata")); //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        EList metadata = this.getMetadata();
        oldMetadataArray = (Metadata[]) metadata.toArray(new Metadata[0]);
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        EList metadata = this.getMetadata();
        metadata.clear();
        for (int i = 0; i < newMetadataArray.length; i++) {
            metadata.add(newMetadataArray[i]);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urnelem != null || urnspec != null : "post no element to name!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urnelem != null || urnspec != null : "pre no element to name!"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        EList metadata = this.getMetadata();
        metadata.clear();
        for (int i = 0; i < oldMetadataArray.length; i++) {
            metadata.add(oldMetadataArray[i]);
        }

        testPreConditions();
    }
    
    private EList getMetadata() {
    	if( urnelem != null )
    		return( urnelem.getMetadata() );
    	else
    		return( urnspec.getMetadata() );
    }
}
