package seg.jUCMNav.actions.scenarios;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioDefTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioLabelTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioPathNodeTreeEditPart;
import seg.jUCMNav.model.commands.transformations.ReorderScenarioChildrenCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * Lets a user reorder the scenario start/end points and inherited elements around.
 * 
 * @author jkealey
 */
public class MoveScenarioAction extends URNSelectionAction {

    public static final String MOVEUPACTION = "seg.jUCMNav.MOVEUPACTION_SCENARIOS"; //$NON-NLS-1$
    public static final String MOVEDOWNACTION = "seg.jUCMNav.MOVEDOWNACTION_SCENARIOS"; //$NON-NLS-1$

    protected ScenarioDef scenario;
    protected EObject obj;
    protected boolean isMoveUp;

    /**
     * @param part
     *            jUCMNav
     * @param isMoveUp
     *            if true, is the move up action, otherwise the move down aciton.
     */
    public MoveScenarioAction(IWorkbenchPart part, boolean isMoveUp) {
        super(part);
        this.isMoveUp = isMoveUp;

        if (this.isMoveUp) {
            setId(MOVEUPACTION);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/move_up.gif")); //$NON-NLS-1$
        } else {
            setId(MOVEDOWNACTION);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/move_down.gif")); //$NON-NLS-1$
        }

    }

    /**
     * Is true if we have a non-inherited scenario start/end point or an included scenario.
     */
    protected boolean calculateEnabled() {
        initScenario();
        List list = getSelectedObjects();

        return scenario != null
                && obj != null
                && list.size() > 0
                && ((list.get(0) instanceof ScenarioPathNodeTreeEditPart && !((ScenarioPathNodeTreeEditPart) list.get(0)).isInherited()) || (list.get(0) instanceof ScenarioDefTreeEditPart && !((ScenarioDefTreeEditPart) list
                        .get(0)).isInherited()));
    }

    /**
     * Figures out what we want to move.
     * 
     * The parent scenario is saved to scenario and the object to be moved is saved to obj.
     * 
     */
    protected void initScenario() {
        List list = getSelectedObjects();
        EditPart part = null;
        obj = null;
        if (list.size() > 0 && list.get(0) instanceof EditPart && ((EditPart) list.get(0)).getModel() instanceof EObject) {
            part = (EditPart) list.get(0);
            obj = (EObject) part.getModel();
            if (!((obj instanceof ScenarioStartPoint || obj instanceof ScenarioEndPoint || obj instanceof ScenarioDef) && part.getParent() instanceof ScenarioLabelTreeEditPart))
                obj = null;
            else if (part.getParent() != null && part.getParent().getParent() != null) {
                scenario = (ScenarioDef) part.getParent().getParent().getModel();
            }
            if (scenario == null || obj == null)
                return;

            if (this.isMoveUp) {
                if (obj instanceof ScenarioStartPoint) {
                    ScenarioStartPoint point = (ScenarioStartPoint) obj;
                    if (scenario.getStartPoints().indexOf(point) <= 0)
                        obj = null;
                } else if (obj instanceof ScenarioEndPoint) {
                    ScenarioEndPoint point = (ScenarioEndPoint) obj;
                    if (scenario.getEndPoints().indexOf(point) <= 0)
                        obj = null;
                } else if (obj instanceof ScenarioDef) {
                    ScenarioDef child = (ScenarioDef) obj;
                    if (scenario.getIncludedScenarios().indexOf(child) <= 0)
                        obj = null;
                } else
                    obj = null;
            } else {
                if (obj instanceof ScenarioStartPoint) {
                    ScenarioStartPoint point = (ScenarioStartPoint) obj;
                    if (scenario.getStartPoints().indexOf(point) == scenario.getStartPoints().size() - 1)
                        obj = null;
                } else if (obj instanceof ScenarioEndPoint) {
                    ScenarioEndPoint point = (ScenarioEndPoint) obj;
                    if (scenario.getEndPoints().indexOf(point) == scenario.getEndPoints().size() - 1)
                        obj = null;
                } else if (obj instanceof ScenarioDef) {
                    ScenarioDef child = (ScenarioDef) obj;
                    if (scenario.getIncludedScenarios().indexOf(child) == scenario.getIncludedScenarios().size() - 1)
                        obj = null;
                } else
                    obj = null;
            }

        }
    }

    /**
     * Creates a {@link seg.jUCMNav.model.commands.transformations.ReorderScenarioChildrenCommand} to re-order the selected object.
     */
    protected Command getCommand() {
        ReorderScenarioChildrenCommand move = new ReorderScenarioChildrenCommand(scenario, obj, isMoveUp);
        return move;
    }

}