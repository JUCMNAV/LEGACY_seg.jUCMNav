package seg.jUCMNav.actions.dynamicContexts;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.URNspecDynamicContextTreeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateDynamicContextsGroupCommand;
import urn.dyncontext.DynamicContextGroup;

/**
 * Creates a new Dynamic Context Group.
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextsGroupAction extends URNSelectionAction {
	
	public static final String ADDDYNAMICCONTEXTSGROUP = "Add Dynamic Contexts Group"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddDynamicContextsGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDDYNAMICCONTEXTSGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && (getSelectedObjects().size()>0 && getSelectedObjects().get(0) instanceof URNspecDynamicContextTreeEditPart);
    }

    /**
     * Returns a new {@link CreateDynamicContextsGroupCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        DynamicContextGroup group = (DynamicContextGroup) ModelCreationFactory.getNewObject(sel.getUrnspec(), DynamicContextGroup.class);
        CreateDynamicContextsGroupCommand create = new CreateDynamicContextsGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
