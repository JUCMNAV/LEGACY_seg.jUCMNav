package seg.jUCMNav.actions.dynamicContexts;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.TimepointGroupListTreeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateTimepointsGroupCommand;
import urn.dyncontext.TimepointGroup;

/**
 * Creates a new timepoint group.
 * 
 * @author aprajita
 * 
 */
public class AddTimepointsGroupAction extends URNSelectionAction {
	
	public static final String ADDTIMEPOINTSGROUP = "Add Timepoints Group"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddTimepointsGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDTIMEPOINTSGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && (getSelectedObjects().size()>0 && getSelectedObjects().get(0) instanceof TimepointGroupListTreeEditPart);
    }

    /**
     * Returns a new {@link CreateTimepointsGroupCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        TimepointGroup group = (TimepointGroup) ModelCreationFactory.getNewObject(sel.getUrnspec(), TimepointGroup.class);
        CreateTimepointsGroupCommand create = new CreateTimepointsGroupCommand(sel.getUrnspec(), group);

        return create;
    }

}
