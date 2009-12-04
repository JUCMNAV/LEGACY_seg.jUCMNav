package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Adds a label to a PathNode or ComponentRef.
 * 
 * @author Jordan
 */
public class AddLabelAction extends URNSelectionAction {
    public static final String ADDLABEL = "seg.jUCMNav.AddLabel"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddLabelAction(IWorkbenchPart part) {
        super(part);
        setId(ADDLABEL);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/label.gif")); //$NON-NLS-1$
    }

    /**
     * can only add labels if none already exist.
     */
    protected boolean calculateEnabled() {
        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);

            if ((part.getModel() instanceof PathNode)) {
                PathNode node = (PathNode) part.getModel();
                return node.getLabel() == null;
            } else if ((part.getModel() instanceof ComponentRef)) {
                ComponentRef component = (ComponentRef) part.getModel();
                return component.getLabel() == null;
            }
        }

        return false;
    }

    /**
     * @return a {@link CreateLabelCommand}
     */
    protected Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);

        UCMmodelElement modelElement = (UCMmodelElement) part.getModel();
        CreateLabelCommand create = new CreateLabelCommand(modelElement);

        return create;
    }

}