package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ComponentRefEditPart;
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
    private ComponentRefFigure getFigure() {
        return (ComponentRefFigure) ((ComponentRefEditPart) this.getHost()).getFigure();
    }

    /**
     * Return to smaller line widths.
     */
    public void eraseTargetFeedback(Request request) {
        if (getFigure().getKind() == ComponentKind.AGENT)
            getFigure().setLineWidth(6);
        else
            getFigure().setLineWidth(3);
    }

    /**
     * Put larger line widths,.
     */
    public void showTargetFeedback(Request request) {
        if (getFigure().getKind() == ComponentKind.AGENT)
            getFigure().setLineWidth(9);
        else
            getFigure().setLineWidth(6);
    }
}