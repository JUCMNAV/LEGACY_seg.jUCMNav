package seg.jUCMNav.model.commands.transformations;

import grl.ContributionContext;
import grl.ContributionContextGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Moves a contribution context from one group to another. 
 * 
 * @author jkealey
 * 
 */
public class MoveContributionContextCommand extends Command implements JUCMNavCommand {

    private ContributionContextGroup group;
    private ContributionContextGroup oldGroup;
    private ContributionContext contrib;

    /**
     * 
     */
    public MoveContributionContextCommand(ContributionContextGroup targetGroup, ContributionContext contrib) {
        this.contrib = contrib;
        this.group = targetGroup;
        this.oldGroup = (ContributionContextGroup) contrib.getGroups().get(0); // TODO: Should metamodel be changed? 
        setLabel(Messages.getString("MoveContributionContextCommand.MoveContributionCommand")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return contrib!=null && group != oldGroup;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (oldGroup!=null)
            oldGroup.getContribs().remove(contrib);
        if (group!=null)
            group.getContribs().add(contrib);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert contrib != null : "pre not null"; //$NON-NLS-1$
        assert group==null || !group.getContribs().contains(contrib) : "pre contrib not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert contrib !=null : "post not null"; //$NON-NLS-1$
        assert group == null || group.getContribs().contains(contrib) : "post contrib not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (group!=null)
            group.getContribs().remove(contrib);
        if (oldGroup!=null)
            oldGroup.getContribs().add(contrib);
        testPreConditions();
    }

    public ContributionContext getContrib() {
        return contrib;
    }

}
