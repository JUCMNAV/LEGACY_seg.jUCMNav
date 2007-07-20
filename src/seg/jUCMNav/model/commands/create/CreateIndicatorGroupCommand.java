/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.kpimodel.IndicatorGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command add an Indicator Group to the model (for KPI Model)
 * 
 * @author pchen
 * 
 */
public class CreateIndicatorGroupCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private IndicatorGroup group;

    /**
     * 
     */
    public CreateIndicatorGroupCommand(URNspec urn, IndicatorGroup group) {
        this.urn = urn;
        this.group = group;
        setLabel(Messages.getString("CreateIndicatorGroupCommand.createIndicatorGroup")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
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
        urn.getGrlspec().getIndicatorGroup().add(group);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "post not null"; //$NON-NLS-1$
        assert urn.getGrlspec().getIndicatorGroup().contains(group) : "post group not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getGrlspec().getIndicatorGroup().contains(group) : "pre groups not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getIndicatorGroup().remove(group);
        testPreConditions();
    }
}
