/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import seg.jUCMNav.figures.ComponentRefFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ComponentRefEditPart;

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
        getFigure().setLineWidth(3);
    }

    public void showTargetFeedback(Request request) {
        getFigure().setLineWidth(6);
    }
}