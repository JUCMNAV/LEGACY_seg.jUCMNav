package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.util.ParentFinder;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Binds the selected elements with their respective parents, if they are unbound. For more details, see calculateEnabled()
 * 
 * @author jkealey
 */
public class BindWithParent extends URNSelectionAction {

    public static final String BINDWITHPARENT = "seg.jUCMNav.BindWithParent"; //$NON-NLS-1$

    /**
     * @param part
     */
    public BindWithParent(IWorkbenchPart part) {
        super(part);
        setId(BINDWITHPARENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif")); //$NON-NLS-1$sa
    }

    /**
     * Can perform if:
     * 
     * 1. there is at least 1 selected object
     * 
     * 2. all selected object are EditParts
     * 
     * 3. the model element for all of the selected objects is an _unbound_ (ComponentRef or PathNode)
     * 
     * 4. the model element for all of the selected objects have a possible parent.
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

                if ((!(p.getModel() instanceof IURNContainerRef) || !(((IURNContainerRef) p.getModel()).getParent() == null))
                        && (!(p.getModel() instanceof IURNNode) || !(((IURNNode) p.getModel()).getContRef() == null)))
                    return false; // #3 failed
                else {
                    if (p.getModel() instanceof IURNContainerRef) {
                        IURNContainerRef child = (IURNContainerRef) p.getModel();
                        if (child.getDiagram() == null)
                            return false;
                        else if (null == ParentFinder.findParent(child.getDiagram(), child, child.getX(), child.getY(), child.getWidth(), child.getHeight()))
                            return false; // #4 failed for ComponentRefs
                    } else if (p.getModel() instanceof IURNNode) {
                        IURNNode child = (IURNNode) p.getModel();
                        if (child.getDiagram() == null)
                            return false;
                        else if (null == ParentFinder.findParent(child.getDiagram(), child.getX(), child.getY()))
                            return false; // #4 failed for PathNodes
                    }
                }

            } else {
                return false; // #2 failed
            }
        }

        // all tests pass
        return true;
    }

    /**
     * @return A chained command to bind all selected components with their parent.
     */
    protected Command getCommand() {
        Command cmd;
        if (getSelectedObjects().isEmpty()) {
            return null;
        } else {
            URNmodelElement child;
            IURNContainerRef parent;

            // find the child and parent and create a new command to bind them together
            child = (URNmodelElement) ((EditPart) getSelectedObjects().get(0)).getModel();
            parent = ParentFinder.getPossibleParent(child);
            cmd = new ContainerRefBindChildCommand(parent, child);

            // do the same for all other model elements.
            for (int i = 1; i < getSelectedObjects().size(); i++) {
                child = (URNmodelElement) ((EditPart) getSelectedObjects().get(i)).getModel();
                parent = ParentFinder.getPossibleParent(child);

                cmd = cmd.chain(new ContainerRefBindChildCommand(parent, child));
            }

            return cmd;
        }
    }

}