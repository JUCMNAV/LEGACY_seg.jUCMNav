package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.Shape;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ModelElementEditPart;
import seg.jUCMNav.figures.ComponentRefFigure;
import urncore.ComponentKind;

/**
 * On mouse hover of components, it becomes thicker so that we can click on it more easily.
 * 
 * Note: Since ComponentKind.AGENTs are already drawn with lines as thick as the usual feedback, we must make them even thicker.
 * 
 * @author jkealey
 */
public class ComponentFeedbackEditPolicy extends GraphicalEditPolicy {

    /**
     * Convenience method to avoid casting.
     * 
     * @return the associated ComponentRefFigure
     */
    private Shape getFigure() {
        return (Shape) ((ModelElementEditPart) this.getHost()).getFigure();
    }

    /**
     * Return to smaller line widths.
     */
    public void eraseTargetFeedback(Request request) {
        if (getFigure() instanceof ComponentRefFigure) {
            ComponentRefFigure fig = (ComponentRefFigure) getFigure();
            if (fig.getKind() == ComponentKind.AGENT) {
                fig.setLineWidth(6);
            } else {
                fig.setLineWidth(3);
            }
        } else { // If it is a GRL Component (actors)
            getFigure().setLineWidth(3);
        }
    }

    /**
     * Put larger line widths,.
     */
    public void showTargetFeedback(Request request) {
        if (getFigure() instanceof ComponentRefFigure) {
            ComponentRefFigure fig = (ComponentRefFigure) getFigure();
            if (fig.getKind() == ComponentKind.AGENT)
                fig.setLineWidth(9);
            else
                fig.setLineWidth(6);
        } else { // If it is a GRL Component (actors)
            getFigure().setLineWidth(6);
        }
    }
}