package seg.jUCMNav.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import ucm.map.PathNode;

/**
 * Action to add an additional branch to an existing fork or join.
 * 
 * @author jkealey
 * 
 */
public class AddBranchAction extends URNSelectionAction {

    public static final String ADDBRANCH = "seg.jUCMNav.AddBranch"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddBranchAction(IWorkbenchPart part) {
        super(part);
        setId(ADDBRANCH);
    }

    /**
     * Returns true if the selection is a fork/join. Also sets the image dynamically.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        switch (sel.getSelectionType()) {
        case SelectionHelper.ANDFORK:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndFork16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ORFORK:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrFork16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ANDJOIN:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndJoin16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ORJOIN:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrJoin16.gif")); //$NON-NLS-1$
            break;
        default:
            return false;
        }
        return true;
    }

    protected Command getCommand() {
        // PathNode not necessarily one of the above; might be different in subclass (timeout path)
        return new AddBranchCommand((PathNode) ((EditPart) getSelectedObjects().get(0)).getModel());
    }
}