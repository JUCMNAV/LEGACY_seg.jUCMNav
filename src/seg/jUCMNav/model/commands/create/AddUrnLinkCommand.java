/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNlink;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * This class create a URNLink between a GRL element and a UCM element
 * 
 * @author Jean-Fran�ois Roy
 * 
 */
public class AddUrnLinkCommand extends Command implements JUCMNavCommand {

    private URNlink link;
    private URNmodelElement from;
    private URNmodelElement to;
    private URNspec urn;

    /**
     * @param urn
     *            the URNspec
     * @param link
     *            the URNlink to add
     * @param fromElem
     *            the URNmodelElement in the link
     * @param toElem
     *            the URNmodelElement in the link
     */
    public AddUrnLinkCommand(URNspec urn, URNlink link, URNmodelElement fromElem, URNmodelElement toElem) {
        this.link = link;
        this.from = fromElem;
        this.to = toElem;
        this.urn = urn;
        setLabel(Messages.getString("AddUrnLinkCommand.addUrnLink")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {

        if ((urn != null && link != null && from != null && to != null)) {
            // Restriction below is now removed (DA)
            // && (to.getToLinks().size() == 0)){ //We only support 1 link from the to element
            return true;
        }
        return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        link.setFromElem(from);
        link.setToElem(to);
        urn.getUrnLinks().add(link);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && link != null && from != null && to != null : "pre null"; //$NON-NLS-1$
        assert !urn.getUrnLinks().contains(link) : "pre urn contain link!"; //$NON-NLS-1$
        assert !from.getFromLinks().contains(link) && !to.getToLinks().contains(link) : "pre element contain link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && link != null && from != null && to != null : "post null"; //$NON-NLS-1$
        assert urn.getUrnLinks().contains(link) : "pre urn contain link!"; //$NON-NLS-1$
        assert from.getFromLinks().contains(link) && to.getToLinks().contains(link) : "pre element contain link"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUrnLinks().remove(link);
        link.setFromElem(null);
        link.setToElem(null);
        testPreConditions();
    }
}
