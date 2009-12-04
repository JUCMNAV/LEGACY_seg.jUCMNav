/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import urn.URNspec;

/**
 * This command includes a start/end point inside a scenario.
 * 
 * @author jkealey
 * 
 */
public class IncludePathNodeInScenarioCommand extends Command implements JUCMNavCommand {

    private ScenarioDef parent;
    private PathNode child;
    private ScenarioStartPoint startPoint;
    private ScenarioEndPoint endPoint;
    private URNspec urn;

    private EObject clone;

    /**
	 * 
	 */
    public IncludePathNodeInScenarioCommand(ScenarioDef parent, PathNode child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludePathNodeInScenarioCommand.IncludeStartEndPointInScenario")); //$NON-NLS-1$
        urn = parent.getGroup().getUcmspec().getUrnspec();
    }

    public IncludePathNodeInScenarioCommand(ScenarioDef parent, PathNode child, URNspec urn) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludePathNodeInScenarioCommand.IncludeStartEndPointInScenario")); //$NON-NLS-1$
        this.urn = urn;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && (child instanceof StartPoint || child instanceof EndPoint) && urn != null;
    }

    public EObject getClone() {
        return clone;
    }

    public void setClone(EObject clone) {
        this.clone = clone;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (child instanceof StartPoint) {
            startPoint = (ScenarioStartPoint) ModelCreationFactory.getNewObject(urn, ScenarioStartPoint.class);
            if (clone instanceof ScenarioStartPoint) {
                ScenarioStartPoint scenarioStartPoint = (ScenarioStartPoint) clone;
                startPoint.setEnabled(scenarioStartPoint.isEnabled());
            }
        } else if (child instanceof EndPoint) {
            endPoint = (ScenarioEndPoint) ModelCreationFactory.getNewObject(urn, ScenarioEndPoint.class);
            if (clone instanceof ScenarioEndPoint) {
                ScenarioEndPoint scenarioEndPoint = (ScenarioEndPoint) clone;
                endPoint.setEnabled(scenarioEndPoint.isEnabled());
                endPoint.setMandatory(scenarioEndPoint.isMandatory());
            }
        }

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (child instanceof StartPoint) {
            startPoint.setStartPoint((StartPoint) child);
            this.parent.getStartPoints().add(startPoint);
        } else {
            endPoint.setEndPoint((EndPoint) child);
            this.parent.getEndPoints().add(endPoint);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null && urn != null : "post not null"; //$NON-NLS-1$
        assert (parent.getStartPoints().contains(startPoint) || parent.getEndPoints().contains(endPoint)) : "post scenario not updated"; //$NON-NLS-1$
        assert (startPoint != null && startPoint.getStartPoint() == child) || (endPoint != null && endPoint.getEndPoint() == child) : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null && urn != null : "pre not null"; //$NON-NLS-1$
        assert (startPoint != null && startPoint.getStartPoint() == null) || (endPoint != null && endPoint.getEndPoint() == null) : "pre child not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (child instanceof StartPoint) {
            this.parent.getStartPoints().remove(startPoint);
            startPoint.setStartPoint(null);
        } else {
            this.parent.getEndPoints().remove(endPoint);
            endPoint.setEndPoint(null);
        }

        testPreConditions();
    }
}
