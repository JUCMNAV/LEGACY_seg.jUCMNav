package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefBindChildCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urncore.UCMmodelElement;

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
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")); //$NON-NLS-1$sa
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

                if ((!(p.getModel() instanceof ComponentRef) || !(((ComponentRef) p.getModel()).getParent() == null))
                        && (!(p.getModel() instanceof PathNode) || !(((PathNode) p.getModel()).getCompRef() == null)))
                    return false; // #3 failed
                else {
                    if (p.getModel() instanceof ComponentRef) {
                        ComponentRef child = (ComponentRef) p.getModel();
                        if (child.getSpecDiagram() == null)
                            return false;
                        else if (null == ParentFinder.findParent((UCMmap)child.getSpecDiagram(), child, child.getX(), child.getY(), child.getWidth(), child.getHeight()))
                            return false; // #4 failed for ComponentRefs
                    } else if (p.getModel() instanceof PathNode) {
                        PathNode child = (PathNode) p.getModel();
                        if (child.getSpecDiagram() == null)
                            return false;
                        else if (null == ParentFinder.findParent((UCMmap)child.getSpecDiagram(), child.getX(), child.getY()))
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
            UCMmodelElement child;
            ComponentRef parent;

            // find the child and parent and create a new command to bind them together
            child = (UCMmodelElement) ((EditPart) getSelectedObjects().get(0)).getModel();
            parent = (ComponentRef)ParentFinder.getPossibleParent(child);
            cmd = new ComponentRefBindChildCommand(parent, child);

            // do the same for all other model elements.
            for (int i = 1; i < getSelectedObjects().size(); i++) {
                child = (ComponentRef) ((EditPart) getSelectedObjects().get(i)).getModel();
                parent = (ComponentRef)ParentFinder.getPossibleParent(child);
                cmd = cmd.chain(new ComponentRefBindChildCommand(parent, child));
            }

            return cmd;
        }
    }

}