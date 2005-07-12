package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.figures.SplineConnection;

/**
 * On mouse hover of node connection, it becomes thicker so that we can click on it more easily. Change the connection's color when selected.
 * 
 * @author jkealey
 */
public class NodeConnectionFeedbackEditPolicy extends SelectionEditPolicy {

    /**
     * Convenience method to avoid casting.
     * 
     * @return the figure
     */
    private SplineConnection getFigure() {
        return (SplineConnection) ((NodeConnectionEditPart) this.getHost()).getFigure();
    }

    /**
     * Make line thin again.
     */
    public void eraseTargetFeedback(Request request) {
        getFigure().setLineWidth(3);
    }

    /**
     * Make line thicker.
     */
    public void showTargetFeedback(Request request) {
        getFigure().setLineWidth(6);
    }

    /**
     * Draw black.
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
     */
    protected void hideSelection() {
        getFigure().setForegroundColor(new Color(null, 0, 0, 0));

    }

    /**
     * Draw blue.
     *  
     */
    protected void showSelection() {
        getFigure().setForegroundColor(new Color(null, 0, 102, 204));
    }
}