/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.figures.ComponentRefFigure;
import urncore.ComponentKind;

/**
 * Created 2005-03-04
 * 
 * On mouse hover of components, it becomes thicker so that we can click on it more easily.
 * 
 * @author jkealey
 */
public class ComponentFeedbackEditPolicy extends GraphicalEditPolicy {

    /**
     *  
     */
    public ComponentFeedbackEditPolicy() {
        super();
    }

    private ComponentRefFigure getFigure() {
        return (ComponentRefFigure) ((ComponentRefEditPart) this.getHost()).getFigure();
    }

    public void eraseTargetFeedback(Request request) {
        if (getFigure().getKind() == ComponentKind.AGENT)
            getFigure().setLineWidth(6);
        else
            getFigure().setLineWidth(3);
    }

    public void showTargetFeedback(Request request) {
        if (getFigure().getKind() == ComponentKind.AGENT)
            getFigure().setLineWidth(9);
        else
            getFigure().setLineWidth(6);
    }
}