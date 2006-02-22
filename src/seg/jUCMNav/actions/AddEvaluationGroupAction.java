/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;

/**
 * @author Jean-François Roy
 *
 */
public class AddEvaluationGroupAction extends URNSelectionAction {

    public static final String ADDEVALUATIONGROUP = "Add Evaluation Group"; //$NON-NLS-1$
    /**
     * @param part
     */
    public AddEvaluationGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDEVALUATIONGROUP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null;
    }
    
    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        CreateStrategiesGroupCommand create = new CreateStrategiesGroupCommand(sel.getUrnspec());

        return create;
    }
}
