/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.PathNodeFigure;

/**
 * Created 2005-03-04
 * 
 * @author Etienne Tremblay
 */
public class PathNodeNonResizableEditPolicy extends SelectionEditPolicy {

    /**
     *  
     */
    public PathNodeNonResizableEditPolicy() {
        super();
    }

    private PathNodeFigure getFigure() {
        return (PathNodeFigure) ((PathNodeEditPart) this.getHost()).getFigure();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
     */
    protected void hideSelection() {
        getFigure().setSelected(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
     */
    protected void showSelection() {
        getFigure().setSelected(true);
    }

    public void eraseTargetFeedback(Request request) {
    	if(request.getType() == RequestConstants.REQ_ADD) {
    		getFigure().setSelected(false);
    	}
    	getFigure().setHover(false);
    }

    public void showTargetFeedback(Request request) {
        // TODO: insert feedback
    	if(request.getType() == RequestConstants.REQ_ADD) {
    		getFigure().setSelected(true);
    	}
    	getFigure().setHover(true);
    }

}