/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.ElementLink;
import grl.GRLLinkableElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Removes an ElementLink from a GRLGraph.
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveElementLinkCommand extends Command implements JUCMNavCommand {

    ElementLink link;
    URNspec urn;
    GRLLinkableElement src, dest;
    boolean aborted = false;

    /**
     * 
     */
    public RemoveElementLinkCommand(ElementLink link) {
        this.link = link;
        urn = link.getGrlspec().getUrnspec();
        setLabel(Messages.getString("RemoveElementLinkCommand.removeElementLink")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        dest = link.getDest();
        src = link.getSrc();
        redo();
    }

    public boolean canExecute() {
        return urn != null && urn.getGrlspec() != null && urn.getGrlspec().getLinks().contains(link);
    }

    public ElementLink getElementLink() {
        return link;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        aborted = !canExecute();
        if (aborted)
            return;
        testPreConditions();
        urn.getGrlspec().getLinks().remove(link);

        link.setDest(null);
        link.setSrc(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null && urn != null : "Pre something is null"; //$NON-NLS-1$

        assert urn.getGrlspec().getLinks().contains(link) : "Pre urn contain link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null && urn != null : "Post something is null"; //$NON-NLS-1$

        assert !urn.getGrlspec().getLinks().contains(link) : "Post urn contain link"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        urn.getGrlspec().getLinks().add(link);

        link.setDest(dest);
        link.setSrc(src);
        testPreConditions();
    }
}
