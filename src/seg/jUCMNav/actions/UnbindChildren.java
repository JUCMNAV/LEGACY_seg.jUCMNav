package seg.jUCMNav.actions;

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import urncore.IURNContainerRef;

/**
 * Unbinds the selected element with from its parent, if it is bound. For more detail, see calculatedEnabled().
 * 
 * @author jkealey
 */
public class UnbindChildren extends URNSelectionAction {
    public static final String UNBINDCHILDREN = "seg.jUCMNav.UnbindChildren"; //$NON-NLS-1$

    /**
     * @param part
     */
    public UnbindChildren(IWorkbenchPart part) {
        super(part);
        setId(UNBINDCHILDREN);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif")); //$NON-NLS-1$
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
                    if (((IURNContainerRef) p.getModel()).getChildren().size() == 0 && ((IURNContainerRef) p.getModel()).getNodes().size() == 0)
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
     * @return Builds a chained command to unbind all of the selected components's children.
     */
    protected Command getCommand() {
        Command cmd;
        if (getSelectedObjects().isEmpty()) {
            return null;
        } else {
            Vector children = new Vector();
            IURNContainerRef parent;

            parent = (IURNContainerRef) ((EditPart) getSelectedObjects().get(0)).getModel();
            children.addAll(parent.getChildren());
            children.addAll(parent.getNodes());
            cmd = new ContainerRefUnbindChildCommand(parent, children);

            for (int i = 1; i < getSelectedObjects().size(); i++) {
                parent = (IURNContainerRef) ((EditPart) getSelectedObjects().get(i)).getModel();
                children = new Vector();
                children.addAll(parent.getChildren());
                children.addAll(parent.getNodes());
                cmd = cmd.chain(new ContainerRefUnbindChildCommand(parent, children));
            }

            return cmd;
        }
    }

}