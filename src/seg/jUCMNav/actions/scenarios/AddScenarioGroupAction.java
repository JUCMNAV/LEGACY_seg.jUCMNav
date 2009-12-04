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
import seg.jUCMNav.editparts.strategyTreeEditparts.EnumerationTypeTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
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
        return sel.getUrnspec() != null && sel.getUCMspec() != null && sel.getScenarioGroup() == null && getSelectedObjects().size() == 1
                && !(getSelectedObjects().get(0) instanceof VariableListTreeEditPart) && !(getSelectedObjects().get(0) instanceof EnumerationTypeTreeEditPart);
    }

    /**
     * Creates a new scenario group.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ScenarioGroup group = (ScenarioGroup) ModelCreationFactory.getNewObject(sel.getUrnspec(), ScenarioGroup.class);
        CreateScenarioGroupCommand create = new CreateScenarioGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
