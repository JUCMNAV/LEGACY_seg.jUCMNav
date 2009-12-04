package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.util.ParentFinder;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Unbinds the selected element with from its parent, if it is unbound. For more details, see calculateEnabled().
 * 
 * @author jkealey
 */
public class UnbindFromParent extends URNSelectionAction {

    public static final String UNBINDFROMPARENT = "seg.jUCMNav.UnbindFromParent"; //$NON-NLS-1$

    /**
     * @param part
     */
    public UnbindFromParent(IWorkbenchPart part) {
        super(part);
        setId(UNBINDFROMPARENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif")); //$NON-NLS-1$
    }

    /**
     * Can perform if:
     * 
     * 1. there is at least 1 selected object
     * 
     * 2. all selected object are EditParts
     * 
     * 3. the model element for all of the selected objects is an _bound_ (ComponentRef or PathNode)
     * 
     * @return should the action be allowed to execute
     */
    protected boolean calculateEnabled() {
        if (getSelectedObjects().isEmpty())
            return false; // #1 failed
        List parts = getSelectedObjects();
        for (int i = 0; i < parts.size(); i++) {

            if (parts.get(i) instanceof EditPart) {
                EditPart p = (EditPart) parts.get(i);

                if (p.getModel() instanceof IURNContainerRef) {
                    if (((IURNContainerRef) p.getModel()).getParent() == null)
                        return false; // #3 failed for ComponentRef
                } else if (p.getModel() instanceof IURNNode) {
                    if (((IURNNode) p.getModel()).getContRef() == null)
                        return false; // #3 failed for PathNode
                } else
                    return false; // #3 failed.

            } else {
                return false; // #2 failed.
            }
        }

        // all tests pass
        return true;
    }

    /**
     * @return Builds a chained command to unbind all selected components from their parent.
     */
    protected Command getCommand() {
        Command cmd;
        if (getSelectedObjects().isEmpty()) {
            return null;
        } else {
            URNmodelElement child;
            IURNContainerRef parent;

            child = (URNmodelElement) ((EditPart) getSelectedObjects().get(0)).getModel();
            parent = ParentFinder.getPossibleParent(child);
            cmd = new ContainerRefUnbindChildCommand(parent, child);

            for (int i = 1; i < getSelectedObjects().size(); i++) {
                child = (URNmodelElement) ((EditPart) getSelectedObjects().get(i)).getModel();
                parent = ParentFinder.getPossibleParent(child);
                cmd = cmd.chain(new ContainerRefUnbindChildCommand(parent, child));
            }

            return cmd;
        }
    }

}