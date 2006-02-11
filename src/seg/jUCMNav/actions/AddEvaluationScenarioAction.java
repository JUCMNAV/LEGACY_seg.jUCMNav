/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateScenarioCommand;

/**
 * @author Jean-François Roy
 *
 */
public class AddEvaluationScenarioAction extends URNSelectionAction {

    public static final String ADDEVALUATIONSCENARIO = "Add Evaluation Scenario"; //$NON-NLS-1$
    
    /**
     * @param part
     */
    public AddEvaluationScenarioAction(IWorkbenchPart part) {
        super(part);

        setId(ADDEVALUATIONSCENARIO);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a EvaluationGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getEvaluationGroup() != null;
    }

    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        CreateScenarioCommand create = new CreateScenarioCommand(sel.getUrnspec(), sel.getEvaluationGroup());

        return create;
    }
}
