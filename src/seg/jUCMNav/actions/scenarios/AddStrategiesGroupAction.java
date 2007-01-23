/**
 * 
 */
package seg.jUCMNav.actions.scenarios;

import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;

/**
 * Creates a new strategies group.
 * 
 * @author Jean-François Roy
 *
 */
public class AddStrategiesGroupAction extends URNSelectionAction {

    public static final String ADDSTRATEGIESGROUP = "Add Strategies Group"; //$NON-NLS-1$
    /**
     * @param part
     */
    public AddStrategiesGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDSTRATEGIESGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getGRLspec()!=null;
    }
    
    /**
     * Returns a new {@link CreateStrategiesGroupCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        StrategiesGroup group = (StrategiesGroup)ModelCreationFactory.getNewObject(sel.getUrnspec(), StrategiesGroup.class);
        CreateStrategiesGroupCommand create = new CreateStrategiesGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
