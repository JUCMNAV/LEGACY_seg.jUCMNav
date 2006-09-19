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
 * Adds a scenario. 
 * 
 * @author jkealey
 *
 */
public class AddScenarioAction extends URNSelectionAction {

    public static final String ADDSCENARIO = "Add Scenario"; //$NON-NLS-1$
    
    /**
     * @param part
     */
    public AddScenarioAction(IWorkbenchPart part) {
        super(part);

        setId(ADDSCENARIO);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a EvaluationGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getScenarioGroup() != null;
    }

    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        CreateScenarioCommand create = new CreateScenarioCommand(sel.getUrnspec(), sel.getScenarioGroup());

        return create;
    }
}
