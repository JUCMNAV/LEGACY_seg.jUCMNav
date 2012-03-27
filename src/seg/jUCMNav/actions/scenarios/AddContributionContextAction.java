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
import seg.jUCMNav.model.commands.create.CreateContributionContextCommand;

/**
 * Adds a contribution context to a contribution context group.
 * 
 * @author jkealey
 * 
 */
public class AddContributionContextAction extends URNSelectionAction {

    public static final String ADDCONTRIBUTIONCONTEXT = "Add Contribution Context"; //$NON-NLS-1$

    /**
     * Adds a contribution context to a contribution context group.
     * 
     * @param part
     *            jUCMnav
     */
    public AddContributionContextAction(IWorkbenchPart part) {
        super(part);

        setId(ADDCONTRIBUTIONCONTEXT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ContributionContext16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a Scenario Group selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getContributionContextGroup() != null && sel.getContributionContext() == null;
    }

    /**
     * Returns a {@link seg.jUCMNav.model.commands.create.CreateScenarioCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateContributionContextCommand create = new CreateContributionContextCommand(sel.getUrnspec(), sel.getContributionContextGroup());

        return create;
    }
}
