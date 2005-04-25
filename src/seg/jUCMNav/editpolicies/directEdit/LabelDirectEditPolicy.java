/*
 * Created on Apr 1, 2005
 */
package seg.jUCMNav.editpolicies.directEdit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import urncore.NodeLabel;

/**
 * @author Jordan
 */
public class LabelDirectEditPolicy extends DirectEditPolicy {

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {
        NodeLabel label = (NodeLabel) getHost().getModel();
		LabelEditPart labelPart = (LabelEditPart) getHost();
		
        ChangeLabelNameCommand cmd = new ChangeLabelNameCommand();
		
		cmd.setPathNode(label.getPathNode());
		cmd.setOldName(((EditableLabel) labelPart.getFigure()).getText());
		
		CellEditor cellEditor = request.getCellEditor();
		cmd.setName((String) cellEditor.getValue());
		return cmd;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected void showCurrentEditValue(DirectEditRequest request) {
        //No direct Editting happening yet
    }

}
