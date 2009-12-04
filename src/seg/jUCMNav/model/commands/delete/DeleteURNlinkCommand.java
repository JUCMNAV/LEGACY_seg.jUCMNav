/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNlink;
import urn.URNspec;
import urncore.URNmodelElement;

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

    private URNmodelElement from;
    private URNmodelElement to;

    /**
     * 
     */
    public DeleteURNlinkCommand(URNlink link) {
        this.link = link;
        setLabel(Messages.getString("DeleteURNlinkCommand.deleteUrnLink")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = link.getUrnspec();
        from = link.getFromElem();
        to = link.getToElem();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        link.setFromElem(null);
        link.setToElem(null);
        urn.getUrnLinks().remove(link);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && link != null && to != null && from != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert from.getFromLinks().contains(link) && to.getToLinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && link != null && to != null && from != null : "pre something is null"; //$NON-NLS-1$
        assert !urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert !from.getFromLinks().contains(link) && !to.getToLinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        urn.getUrnLinks().add(link);
        link.setFromElem(from);
        link.setToElem(to);

        testPreConditions();
    }
}
