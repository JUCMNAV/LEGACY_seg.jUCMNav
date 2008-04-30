package seg.jUCMNav.model.commands.kpi;

import grl.GRLspec;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Assigning groups to the indicator
 * 
 * @author pchen
 * 
 */
public class AssignIndicatorGroupCommand extends Command implements JUCMNavCommand {
    private Indicator indicator;
    private HashSet assignedIndicatorGroups;
    private IndicatorGroup[] oldIndicatorGroups;
    private GRLspec grlSpec;

    public AssignIndicatorGroupCommand(Indicator indicator, HashSet assignedIndGrps) {
        this.indicator = indicator;
        this.assignedIndicatorGroups = assignedIndGrps;
        this.grlSpec = indicator.getGrlspec();

        setLabel(Messages.getString("AssignIndicatorGroupCommand.assignIndicatorGroups")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return indicator != null && assignedIndicatorGroups != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldIndicatorGroups = (IndicatorGroup[]) indicator.getGroups().toArray(new IndicatorGroup[0]);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        EList indicatorGroupList = indicator.getGroups();
        indicatorGroupList.clear();
        indicatorGroupList.addAll(assignedIndicatorGroups);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert indicator != null && assignedIndicatorGroups != null && grlSpec != null : "pre not null"; //$NON-NLS-1$
        assert grlSpec.getIndicatorGroup().containsAll(assignedIndicatorGroups) : "pre indicator in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert indicator != null && assignedIndicatorGroups != null && grlSpec != null : "post not null"; //$NON-NLS-1$
        assert indicator.getGroups().containsAll(assignedIndicatorGroups) : "post indicator in intentionalElement"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        EList indicatorGroupList = indicator.getGroups();
        indicatorGroupList.clear();
        for (int i = 0; i < oldIndicatorGroups.length; i++) {
            indicatorGroupList.add(oldIndicatorGroups[i]);
        }

        testPreConditions();
    }

}
