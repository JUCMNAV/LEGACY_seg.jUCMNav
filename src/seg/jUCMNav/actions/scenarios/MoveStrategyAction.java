package seg.jUCMNav.actions.scenarios;

import grl.EvaluationStrategy;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.EvaluationStrategyTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyLabelTreeEditPart;
import seg.jUCMNav.model.commands.transformations.ReorderStrategyChildrenCommand;

/**
 * Lets a user reorder the strategy inherited elements around.
 * 
 * @author jkealey
 */
public class MoveStrategyAction extends URNSelectionAction {

    public static final String MOVEUPACTION = "seg.jUCMNav.MOVEUPACTION_STRATEGIES"; //$NON-NLS-1$
    public static final String MOVEDOWNACTION = "seg.jUCMNav.MOVEDOWNACTION_STRATEGIES"; //$NON-NLS-1$

    protected EvaluationStrategy strategy;
    protected EObject obj;
    protected boolean isMoveUp;

    /**
     * @param part
     *            jUCMNav
     * @param isMoveUp
     *            if true, is the move up action, otherwise the move down aciton.
     */
    public MoveStrategyAction(IWorkbenchPart part, boolean isMoveUp) {
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
     * Is true if we have a non-inherited included strategy
     */
    protected boolean calculateEnabled() {
        initStrategy();
        List list = getSelectedObjects();

        return strategy != null
                && obj != null
                && list.size() > 0
                && (list.get(0) instanceof EvaluationStrategyTreeEditPart && !((EvaluationStrategyTreeEditPart) list.get(0)).isInherited());
    }

    /**
     * Figures out what we want to move.
     * 
     * The parent strategy is saved to strategy and the object to be moved is saved to obj.
     * 
     */
    protected void initStrategy() {
        List list = getSelectedObjects();
        EditPart part = null;
        obj = null;
        if (list.size() > 0 && list.get(0) instanceof EditPart && ((EditPart) list.get(0)).getModel() instanceof EObject) {
            part = (EditPart) list.get(0);
            obj = (EObject) part.getModel();
            if (!(obj instanceof EvaluationStrategy && part.getParent() instanceof StrategyLabelTreeEditPart))
                obj = null;
            else if (part.getParent() != null && part.getParent().getParent() != null) {
                strategy = (EvaluationStrategy) part.getParent().getParent().getModel();
            }
            if (strategy == null || obj == null)
                return;

            if (this.isMoveUp) {
                if (obj instanceof EvaluationStrategy) {
                    EvaluationStrategy child = (EvaluationStrategy) obj;
                    if (strategy.getIncludedStrategies().indexOf(child) <= 0)
                        obj = null;
                } else
                    obj = null;
            } else {
                if (obj instanceof EvaluationStrategy) {
                    EvaluationStrategy child = (EvaluationStrategy) obj;
                    if (strategy.getIncludedStrategies().indexOf(child) == strategy.getIncludedStrategies().size() - 1)
                        obj = null;
                } else
                    obj = null;
            }

        }
    }

    /**
     * Creates a {@link seg.jUCMNav.model.commands.transformations.ReorderStrategyChildrenCommand} to re-order the selected object.
     */
    protected Command getCommand() {
        ReorderStrategyChildrenCommand move = new ReorderStrategyChildrenCommand(strategy, obj, isMoveUp);
        return move;
    }

}