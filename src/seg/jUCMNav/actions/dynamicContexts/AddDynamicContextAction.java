package seg.jUCMNav.actions.dynamicContexts;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.create.CreateDynamicContextCommand;

/**
 * Adds a dynamic context to a dynamic context group.
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextAction extends URNSelectionAction {
	
	public static final String ADDDYNAMICCONTEXT = "Add Dynamic Context"; //$NON-NLS-1$

    /**
     * Adds a dynamic context in a dynamic context group.
     * 
     * @param part
     *            jucmnav
     */
    public AddDynamicContextAction(IWorkbenchPart part) {
        super(part);

        setId(ADDDYNAMICCONTEXT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grlstrat16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a DynamicContextGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getDynamicContextGroup() != null;
    }

    /**
     * We need to return the command to be executed.
     * 
     * @see seg.jUCMNav.model.commands.create.CreateDynamicContextCommand
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateDynamicContextCommand create = new CreateDynamicContextCommand(sel.getUrnspec(), sel.getDynamicContextGroup());

        return create;
    }
}
