/**
 * 
 */
package seg.jUCMNav.actions.scenarios;

import grl.ContributionContextGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextGroupListTreeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateContributionContextGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;

/**
 * Creates a new contribution context group.
 * 
 * @author jkealey
 * 
 */
public class AddContributionContextGroupAction extends URNSelectionAction {

    public static final String ADDCONTRIBUTIONCONTEXTGROUP = "Add Contribution Context Group"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddContributionContextGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDCONTRIBUTIONCONTEXTGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getGRLspec() != null && (getSelectedObjects().size()>0 && getSelectedObjects().get(0) instanceof ContributionContextGroupListTreeEditPart);
    }

    /**
     * Returns a new {@link CreateStrategiesGroupCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ContributionContextGroup group = (ContributionContextGroup) ModelCreationFactory.getNewObject(sel.getUrnspec(), ContributionContextGroup.class);
        CreateContributionContextGroupCommand create = new CreateContributionContextGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
