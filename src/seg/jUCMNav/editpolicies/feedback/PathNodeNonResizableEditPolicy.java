/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

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

        getFigure().setBackgroundColor(new Color(null, 255, 255, 255));
        getFigure().setSelected(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
     */
    protected void showSelection() {

        getFigure().setBackgroundColor(new Color(null, 0, 102, 204));
        getFigure().setSelected(true);
    }

    public void eraseTargetFeedback(Request request) {
        // TODO: remove feedback 
    }

    public void showTargetFeedback(Request request) {
        // TODO: insert feedback
    }

}