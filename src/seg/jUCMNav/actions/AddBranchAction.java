package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.PathNodeEditPart;
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
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ORFORK:
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ANDJOIN:
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndJoin16.gif")); //$NON-NLS-1$
            break;
        case SelectionHelper.ORJOIN:
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrJoin16.gif")); //$NON-NLS-1$
            break;
        default:
            return false;
        }
        return true;
    }

    protected Command getCommand() {
        // PathNode not necessarily one of the above; might be different in subclass (timeout path)
        return new AddBranchCommand((PathNode) ((PathNodeEditPart) getSelectedObjects().get(0)).getModel());
    }
}