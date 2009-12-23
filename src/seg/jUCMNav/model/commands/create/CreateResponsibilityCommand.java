package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Responsibility;

/**
 * This command adds a new responsibility to the URNspec.
 * 
 * @author jkealey
 * 
 */
public class CreateResponsibilityCommand extends Command implements JUCMNavCommand {
    private Responsibility resp;
    private URNspec urn;
    private int oldCount;

    public CreateResponsibilityCommand(URNspec urn, Responsibility resp) {
        this.urn = urn;
        this.resp = resp;
        setLabel(Messages.getString("CreateResponsibilityCommand_CreateResponsibility")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && resp != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldCount = urn.getUrndef().getResponsibilities().size();
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUrndef().getResponsibilities().add(resp);
        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUrndef() != null && resp != null : "post not null"; //$NON-NLS-1$
        assert urn.getUrndef().getResponsibilities().contains(resp) : "post resp not in model"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUrndef() != null && resp != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUrndef().getResponsibilities().contains(resp) : "pre resp not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUrndef().getResponsibilities().remove(resp);
        testPreConditions();
    }
}