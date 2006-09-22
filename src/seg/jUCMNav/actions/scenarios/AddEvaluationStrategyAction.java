/**
 * 
 */
package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;

/**
 * @author Jean-François Roy
 *
 */
public class AddEvaluationStrategyAction extends URNSelectionAction {

    public static final String ADDEVALUATIONSTRATEGY = "Add Evaluation Strategy"; //$NON-NLS-1$
    
    /**
     * @param part
     */
    public AddEvaluationStrategyAction(IWorkbenchPart part) {
        super(part);

        setId(ADDEVALUATIONSTRATEGY);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a EvaluationGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getStrategiesGroup() != null;
    }

    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        CreateStrategyCommand create = new CreateStrategyCommand(sel.getUrnspec(), sel.getStrategiesGroup());

        return create;
    }
}
