/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Delete a strategy group. It should have no strategy in it.
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteStrategiesGroupCommand extends Command implements JUCMNavCommand {

    private StrategiesGroup group;
    
    // the URNspec in which it is contained
    private URNspec urn;
    /**
     * 
     */
    public DeleteStrategiesGroupCommand(StrategiesGroup group) {
        this.group = group;
        setLabel("Delete StrategiesGroup");
    }

    /**
     * Only if no strategy in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return group != null && group.getStrategies().size() == 0;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = group.getGrlspec().getUrnspec();

        redo();
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        urn.getGrlspec().getGroups().remove(group);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert group != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert group.getStrategies().size() == 0 : "pre can't delete if still strategies."; //$NON-NLS-1$
        assert urn.getGrlspec().getGroups().contains(group) : "pre group in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert group != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert group.getStrategies().size() == 0 : "post can't delete if still strategies."; //$NON-NLS-1$
        assert !urn.getGrlspec().getGroups().contains(group) : "post group in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add group
        urn.getGrlspec().getGroups().add(group);

        testPreConditions();
    }
}
