package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.Shape;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ModelElementEditPart;
import seg.jUCMNav.figures.BeliefFigure;

/**
 * On mouse hover on GRLNode (IntentionalElement and Beliefs and KPIInformationElement), the nodes become thicker.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GrlNodeFeedbackEditPolicy extends GraphicalEditPolicy {

    /**
     * 
     * @return the GRLNodeFigure as a RectangleFigure
     */
    private Shape getFigure() {
        return (Shape) ((ModelElementEditPart) this.getHost()).getFigure();
    }

    /**
     * Return to smaller line widths.
     */
    public void eraseTargetFeedback(Request request) {
        if (getFigure() instanceof BeliefFigure) {
            getFigure().setLineWidth(2);
        } else {
            getFigure().setLineWidth(3);
        }
    }

    /**
     * Put larger line widths,.
     */
    public void showTargetFeedback(Request request) {
        if (getFigure() instanceof BeliefFigure) {
            getFigure().setLineWidth(4);
        } else {
            getFigure().setLineWidth(6);
        }
    }
}
