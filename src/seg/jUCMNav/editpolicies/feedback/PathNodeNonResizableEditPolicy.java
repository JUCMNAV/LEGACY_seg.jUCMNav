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
import seg.jUCMNav.figures.Polygon;

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
        getFeedbackLayer().getChildren().clear();
        getFeedbackLayer().erase();
    }

    public void showTargetFeedback(Request request) {
        Polygon p = new Polygon();
        p.addPoint(getFigure().getBounds().getTopLeft());
        p.addPoint(getFigure().getBounds().getTopRight());
        p.addPoint(getFigure().getBounds().getBottomRight());
        p.addPoint(getFigure().getBounds().getBottomLeft());
        p.setFillXOR(true);
        
        if (getFeedbackLayer().getChildren().size()==0)
        getFeedbackLayer().add(p);
        getFeedbackLayer().invalidate();
    }

}