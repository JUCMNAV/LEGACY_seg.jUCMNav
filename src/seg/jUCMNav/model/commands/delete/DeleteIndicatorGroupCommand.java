/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.kpimodel.IndicatorGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Delete a indicator group. It should have no indicator in it.
 * 
 * @author pchen
 * 
 */
public class DeleteIndicatorGroupCommand extends Command implements JUCMNavCommand {

    private Object group;

    // the URNspec in which it is contained
    private URNspec urn;

    /**
     * 
     */
    public DeleteIndicatorGroupCommand(IndicatorGroup group) {
        this.group = group;
        setLabel(Messages.getString("DeleteIndicatorGroupCommand.deleteIndicatorGroup")); //$NON-NLS-1$

    }

    private IndicatorGroup getIndicatorGroup() {

        return (IndicatorGroup) group;
    }

    /**
     * Only if no indicator in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return group != null && ((group instanceof IndicatorGroup && getIndicatorGroup().getIndicators().size() == 0));
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (group instanceof IndicatorGroup) {
            urn = getIndicatorGroup().getGrlspec().getUrnspec();
        }

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (group instanceof IndicatorGroup) {
            urn.getGrlspec().getIndicatorGroup().remove(group);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert group != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert (group instanceof IndicatorGroup && getIndicatorGroup().getIndicators().size() == 0) : "pre can't delete if still strategies / scenarios."; //$NON-NLS-1$
        assert urn.getGrlspec().getIndicatorGroup().contains(group) : "pre group in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert group != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert (group instanceof IndicatorGroup && getIndicatorGroup().getIndicators().size() == 0) : "post can't delete if still strategies."; //$NON-NLS-1$
        assert !urn.getGrlspec().getIndicatorGroup().contains(group) : "post group in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add group
        if (group instanceof IndicatorGroup) {
            urn.getGrlspec().getIndicatorGroup().add(group);
        }

        testPreConditions();
    }
}
