package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.Shape;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.ToolEditPart;
import seg.jUCMNav.figures.ToolFigure;

/**
 * On mouse hover of labels, draw line from label to referenced part.
 * 
 * @author jkealey
 */
public class ToolFeedbackEditPolicy extends GraphicalEditPolicy {
	    private ToolFigure getFigure() {
	        return (ToolFigure) ((ToolEditPart) this.getHost()).getFigure();
	    }

	    /**
	     * Return to smaller line widths.
	     */
	    public void eraseTargetFeedback(Request request) {
	      
	         
	        
	    }

	    /**
	     * Put larger line widths,.
	     */
	    public void showTargetFeedback(Request request) {
	   
	     
	        
	    }
}