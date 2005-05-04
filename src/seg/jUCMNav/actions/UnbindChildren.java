package seg.jUCMNav.actions;

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.changeConstraints.ComponentRefUnbindChildCommand;
import ucm.map.ComponentRef;

/**
 * Created 2005-05-02.
 * 
 * Unbinds the selected element with from its parent, if it is bound. For more detail, see canPerformAction().
 * 
 * @author jkealey
 */
public class UnbindChildren extends SelectionAction {
    public static final String UNBINDCHILDREN = "UnbindChildren"; //$NON-NLS-1$

    /**
     * @param part
     */
    public UnbindChildren(IWorkbenchPart part) {
        super(part);
        setId(UNBINDCHILDREN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return canPerformAction();
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
     * 4. the selected ComponentRefs all have at least one child.
     * 
     * @return
     */
    private boolean canPerformAction() {
        if (getSelectedObjects().isEmpty())
            return false; // #1 failed
        List parts = getSelectedObjects();

        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) instanceof EditPart) {

                EditPart p = (EditPart) parts.get(i);

                if (p.getModel() instanceof ComponentRef) {
                    if (((ComponentRef) p.getModel()).getChildren().size() == 0 && ((ComponentRef) p.getModel()).getPathNodes().size() == 0)
                        return false; // #4 failed.
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
     * Builds a chained command to unbind all of the selected components's children. 
     * 
     * @return
     */
    private Command getCommand() {
        Command cmd;
        if (getSelectedObjects().isEmpty()) {
            return null;
        } else {
            Vector children = new Vector();
            ComponentRef parent;

            parent = (ComponentRef) ((EditPart) getSelectedObjects().get(0)).getModel();
            children.addAll(parent.getChildren());
            children.addAll(parent.getPathNodes());
            cmd = new ComponentRefUnbindChildCommand(parent, children);

            for (int i = 1; i < getSelectedObjects().size(); i++) {
                parent = (ComponentRef) ((EditPart) getSelectedObjects().get(i)).getModel();
                children = new Vector();
                children.addAll(parent.getChildren());
                children.addAll(parent.getPathNodes());
                cmd = cmd.chain(new ComponentRefUnbindChildCommand(parent, children));
            }

            return cmd;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        execute(getCommand());
    }

}