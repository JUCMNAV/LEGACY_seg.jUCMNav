/**
 * 
 */
package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.GRLNode;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import seg.jUCMNav.editparts.GrlNodeEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;

/**
 * Edit policy for direct editing of GrlNode
 * 
 * Based on LabelDirectEditPolicy
 * 
 * @author Jean-François Roy
 *
 */
public class GrlNodeDirectEditPolicy extends DirectEditPolicy {

    private String oldValue;

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {
        GRLNode node = (GRLNode) getHost().getModel();

        ChangeGrlNodeNameCommand cmd = new ChangeGrlNodeNameCommand(node, (String) request.getCellEditor().getValue());
        return cmd;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected void showCurrentEditValue(DirectEditRequest request) {
        String value = (String) request.getCellEditor().getValue();
        GrlNodeEditPart lblPart = (GrlNodeEditPart) getHost();
        lblPart.handleNameChange(value);
    }
    
    /**
     * @param request
     *            Saves the initial text value so that if the user's changes are not committed then
     */
    protected void storeOldEditValue(DirectEditRequest request) {

        CellEditor cellEditor = request.getCellEditor();
        oldValue = (String) cellEditor.getValue();
    }

    /**
     * @param request
     *            reverts to the old value.
     */
    protected void revertOldEditValue(DirectEditRequest request) {
        CellEditor cellEditor = request.getCellEditor();
        cellEditor.setValue(oldValue);
        GrlNodeEditPart lblPart = (GrlNodeEditPart) getHost();
        lblPart.revertNameChange();
    }

}
