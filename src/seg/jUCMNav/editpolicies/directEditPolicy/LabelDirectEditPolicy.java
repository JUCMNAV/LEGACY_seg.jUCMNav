package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import urncore.Label;

/**
 * EditPolicy for the direct editing of labels.
 * 
 * Only a few changes from the original file, an example found on the net.
 * 
 * @author Phil Zoio
 */
public class LabelDirectEditPolicy extends DirectEditPolicy {

    private String oldValue;

    /**
     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {
        Label lbl = (Label) getHost().getModel();

        String value = (String) request.getCellEditor().getValue();
        value = value.trim();
        ChangeLabelNameCommand cmd = new ChangeLabelNameCommand(lbl, value);
        return cmd;
    }

    /**
     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected void showCurrentEditValue(DirectEditRequest request) {
        String value = (String) request.getCellEditor().getValue();
        value = value.trim();
        LabelEditPart lblPart = (LabelEditPart) getHost();
        lblPart.handleNameChange(value);
    }

    /**
     * @param request
     *            Saves the initial text value so that if the user's changes are not committed then
     */
    protected void storeOldEditValue(DirectEditRequest request) {

        CellEditor cellEditor = request.getCellEditor();
        oldValue = (String) cellEditor.getValue();
        oldValue = oldValue.trim();
    }

    /**
     * @param request
     *            reverts to the old value.
     */
    protected void revertOldEditValue(DirectEditRequest request) {
        CellEditor cellEditor = request.getCellEditor();
        cellEditor.setValue(oldValue);
        LabelEditPart lblPart = (LabelEditPart) getHost();
        lblPart.revertNameChange();
    }
}