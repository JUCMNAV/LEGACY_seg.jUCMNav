/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateScenarioGroupCommand;
import ucm.scenario.ScenarioGroup;

/**
 * Creates a scenario group.
 * 
 * @author jkealey
 *
 */
public class AddScenarioGroupAction extends URNSelectionAction {

    public static final String ADDSCENARIOGROUP = "Add Scenario Group"; //$NON-NLS-1$
    /**
     * @param part
     */
    public AddScenarioGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDSCENARIOGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getUCMspec()!=null;
    }
    
    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ScenarioGroup group = (ScenarioGroup)ModelCreationFactory.getNewObject(sel.getUrnspec(), ScenarioGroup.class);
        CreateScenarioGroupCommand create = new CreateScenarioGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
