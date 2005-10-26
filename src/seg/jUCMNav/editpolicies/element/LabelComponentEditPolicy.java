package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.model.commands.delete.DeleteLabelCommand;
import urncore.Label;

/**
 * ComponentEditPolicy for UCM labels. Returns delete commands. See ConditionComponentEditPolicy for conditions.
 * 
 * @author Jordan
 */
public class LabelComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteLabelCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        DeleteLabelCommand deleteCommand = new DeleteLabelCommand();
        deleteCommand.setLabel((Label) getHost().getModel());
        deleteCommand.setModelElement(((LabelEditPart) getHost()).getURNmodelElement());
        return deleteCommand;
    }

}