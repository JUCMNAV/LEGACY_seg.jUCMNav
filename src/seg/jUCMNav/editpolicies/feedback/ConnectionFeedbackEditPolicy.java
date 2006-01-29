package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editparts.BeliefLinkEditPart;

/**
 * On mouse hover of node connection, it becomes thicker so that we can click on it more easily. Change the connection's color when selected.
 * 
 * @author jkealey
 */
public class ConnectionFeedbackEditPolicy extends SelectionEditPolicy {

    /**
     * Convenience method to avoid casting.
     * 
     * @return the figure
     */
    private PolylineConnection getFigure() {
        return (PolylineConnection) ((AbstractConnectionEditPart) this.getHost()).getFigure();
    }

    /**
     * Make line thin again.
     */
    public void eraseTargetFeedback(Request request) {
        if (this.getHost() instanceof BeliefLinkEditPart){
            getFigure().setLineWidth(2);
        } else {           
            getFigure().setLineWidth(3);
        }
    }

    /**
     * Make line thicker.
     */
    public void showTargetFeedback(Request request) {
        if (this.getHost() instanceof BeliefLinkEditPart){
            getFigure().setLineWidth(4);
        } else {  
            getFigure().setLineWidth(6);
        }
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