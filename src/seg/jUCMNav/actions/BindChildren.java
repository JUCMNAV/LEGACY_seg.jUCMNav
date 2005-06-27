package seg.jUCMNav.actions;

import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefBindChildCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;

/**
 * Binds the selected elements with their respective parents, if they are unbound. For more details see calculateEnabled.
 * 
 * @author jkealey
 */
public class BindChildren extends UCMSelectionAction {

    public static final String BINDCHILDREN = "seg.jUCMNav.BindChildren"; //$NON-NLS-1$

    /**
     * @param part
     */
    public BindChildren(IWorkbenchPart part) {
        super(part);
        setId(BINDCHILDREN);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")); //$NON-NLS-1$
    }

    /**
     * Can perform if:
     * 
     * 1. there is at least 1 selected object
     * 
     * 2. all selected object are EditParts
     * 
     * 3. the model element for all of the selected objects is a ComponentRef
     * 
     * 4. the selected ComponentRefs all have at least one new possible child.
     * 
     * @return should the action be allowed to execute
     */
    protected boolean calculateEnabled() {
        if (getSelectedObjects().isEmpty())
            return false; // #1 failed
        else {
            // for all selected objects
            for (int i = 0; i < getSelectedObjects().size(); i++) {
                // make sure they are EditParts
                if (getSelectedObjects().get(i) instanceof EditPart) {
                    // make sure they represent a ComponentRef
                    if (!(((EditPart) getSelectedObjects().get(i)).getModel() instanceof ComponentRef)) {
                        return false; // #3 failed
                    } else {
                        // make sure they have children to add.
                        ComponentRef cr = (ComponentRef) ((EditPart) getSelectedObjects().get(i)).getModel();
                        if (cr.getMap() == null)
                            return false;
                        else if (ParentFinder.findNewChildren(cr.getMap(), cr).size() == 0) {
                            return false; // #4 failed
                        }
                    }
                } else
                    return false; // #2 failed
            }
        }
        // all tests pass
        return true;
    }

    /**
     * @return a chained command to bind all selected components with their new children.
     */
    protected Command getCommand() {
        Command cmd;
        if (getSelectedObjects().isEmpty()) {
            return null;
        } else {
            ComponentRef parent;
            Vector children;

            // get the selected parent, find its new children and create a command
            parent = (ComponentRef) ((EditPart) getSelectedObjects().get(0)).getModel();
            children = ParentFinder.findNewChildren(parent.getMap(), parent);
            cmd = new ComponentRefBindChildCommand(parent, children);

            for (int i = 1; i < getSelectedObjects().size(); i++) {
                //get the selected parent, find its new children and create a command
                parent = (ComponentRef) ((EditPart) getSelectedObjects().get(i)).getModel();
                children = ParentFinder.findNewChildren(parent.getMap(), parent);
                cmd = cmd.chain(new ComponentRefBindChildCommand(parent, children));
            }

            return cmd;
        }
    }

}