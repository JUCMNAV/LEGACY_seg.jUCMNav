/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.GRLspec;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Removes an contribution context from its group.
 * 
 * @author jkealey
 * 
 */
public class RemoveContributionContextCommand extends Command implements JUCMNavCommand {

    private ContributionContext context;
    private ContributionContextGroup group;
    private GRLspec grl;

    /**
     * 
     */
    public RemoveContributionContextCommand(ContributionContext strategy) {
        this.context = strategy;
        setLabel(Messages.getString("RemoveContributionContextCommand.RemoveContributionContext")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        grl = context.getGrlspec();
        group = (ContributionContextGroup) context.getGroups().get(0);  
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        group.getContribs().remove(context);

        grl.getContributionContexts().remove(context);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert grl != null && context != null && group != null : "pre something null"; //$NON-NLS-1$
        assert grl.getContributionContexts().contains(context) : "pre context in grl"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert grl != null && context != null && group != null : "post something null"; //$NON-NLS-1$
        assert !grl.getContributionContexts().contains(context) : "post context in grl"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getContribs().add(context);
        grl.getContributionContexts().add(context);

        testPreConditions();
    }
}
