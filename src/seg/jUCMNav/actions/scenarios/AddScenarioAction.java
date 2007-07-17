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
import seg.jUCMNav.model.commands.create.CreateScenarioCommand;

/**
 * Adds a scenario to a scenario group.
 * 
 * @author jkealey
 * 
 */
public class AddScenarioAction extends URNSelectionAction {

    public static final String ADDSCENARIO = "Add Scenario"; //$NON-NLS-1$

    /**
     * Adds a scenario to a scenario group.
     * 
     * @param part
     *            jUCMnav
     */
    public AddScenarioAction(IWorkbenchPart part) {
        super(part);

        setId(ADDSCENARIO);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucmscen16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a Scenario Group selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getScenarioGroup() != null && sel.getScenario() == null;
    }

    /**
     * Returns a {@link seg.jUCMNav.model.commands.create.CreateScenarioCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateScenarioCommand create = new CreateScenarioCommand(sel.getUrnspec(), sel.getScenarioGroup());

        return create;
    }
}
