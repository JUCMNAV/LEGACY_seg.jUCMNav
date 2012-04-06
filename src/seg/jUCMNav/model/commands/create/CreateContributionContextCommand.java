/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.ContributionContext;
import grl.ContributionContextGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Creates a new contribution context.
 * 
 * @author jkealey
 * 
 */
public class CreateContributionContextCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private ContributionContextGroup group;
    private ContributionContext contrib;

    /**
     * 
     */
    public CreateContributionContextCommand(URNspec urn, ContributionContextGroup group) {
        this.urn = urn;
        this.group = group;
        contrib = (ContributionContext) ModelCreationFactory.getNewObject(urn, ContributionContext.class);
        setLabel(Messages.getString("CreateContributionContextCommand.CreateContributionContext")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
    }

    public ContributionContext getContributionContext() {
        return contrib;
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
        urn.getGrlspec().getContributionContexts().add(contrib);
        group.getContribs().add(contrib);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && contrib != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !group.getContribs().contains(contrib) : "pre contribution context not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && contrib != null && group != null : "post not null"; //$NON-NLS-1$
        assert group.getContribs().contains(contrib) : "post contribution context not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getContributionContexts().remove(contrib);
        group.getContribs().remove(contrib);
        testPreConditions();
    }
}
