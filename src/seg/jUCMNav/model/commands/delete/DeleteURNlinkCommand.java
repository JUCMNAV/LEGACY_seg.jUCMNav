/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNlink;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.UCMmodelElement;

/**
 * Delete a URNlink from the model
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteURNlinkCommand extends Command implements JUCMNavCommand {

    private URNlink link;
    
    // the URNspec in which it is contained
    private URNspec urn;
    
    private GRLmodelElement grl;
    private UCMmodelElement ucm;
    
    /**
     * 
     */
    public DeleteURNlinkCommand(URNlink link) {
        this.link = link;
        setLabel("Delete URNlink");
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = link.getUrnspec();
        grl = link.getGrlModelElements();
        ucm = link.getUcmModelElements();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        link.setGrlModelElements(null);
        link.setUcmModelElements(null);
        urn.getUrnLinks().remove(link);
        
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && link != null && ucm != null && grl != null: "pre something is null"; //$NON-NLS-1$
        assert urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert grl.getUrnlinks().contains(link) && ucm.getUrnlinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && link != null && ucm != null && grl != null: "pre something is null"; //$NON-NLS-1$
        assert !urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert !grl.getUrnlinks().contains(link) && !ucm.getUrnlinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        urn.getUrnLinks().add(link);
        link.setGrlModelElements(grl);
        link.setUcmModelElements(ucm);
        
        testPreConditions();
    }
}
