package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;

/**
 * Adds an evaluation strategy in a strategy group.
 * 
 * @author Jean-François Roy
 * 
 */
public class AddEvaluationStrategyAction extends URNSelectionAction {

    public static final String ADDEVALUATIONSTRATEGY = "Add Evaluation Strategy"; //$NON-NLS-1$

    /**
     * Adds an evaluation strategy in a strategy group.
     * 
     * @param part
     *            jucmnav
     */
    public AddEvaluationStrategyAction(IWorkbenchPart part) {
        super(part);

        setId(ADDEVALUATIONSTRATEGY);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grlstrat16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a EvaluationGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getStrategiesGroup() != null;
    }

    /**
     * We need to return the command to be executed.
     * 
     * @see seg.jUCMNav.model.commands.create.CreateStrategyCommand
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateStrategyCommand create = new CreateStrategyCommand(sel.getUrnspec(), sel.getStrategiesGroup());

        return create;
    }
}
