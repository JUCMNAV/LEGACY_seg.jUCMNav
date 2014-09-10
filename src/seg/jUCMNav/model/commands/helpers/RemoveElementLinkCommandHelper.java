package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.ElementLink;
import grl.GRLLinkableElement;
import seg.jUCMNav.Messages;
import urn.URNspec;

public class RemoveElementLinkCommandHelper implements Command {
    ElementLink link;
    URNspec urn;
    GRLLinkableElement src, dest;
    boolean aborted = false;

    /**
     * 
     */
    public RemoveElementLinkCommandHelper(ElementLink link) {
        this.link = link;
        urn = link.getGrlspec().getUrnspec();
  //      setLabel(Messages.getString("RemoveElementLinkCommand.removeElementLink")); //$NON-NLS-1$
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

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
}
