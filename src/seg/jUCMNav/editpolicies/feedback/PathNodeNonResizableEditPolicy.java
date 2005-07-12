package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.PathNodeFigure;

/**
 * SelectionEditPolicy that delegates hover/selection feedback to the actual PathNodeFigures.
 * 
 * @author Etienne Tremblay
 */
public class PathNodeNonResizableEditPolicy extends SelectionEditPolicy {

    /**
     * Convenience method to avoid casting.
     * 
     * @return the figure being edited.
     */
    private PathNodeFigure getFigure() {
        return (PathNodeFigure) ((PathNodeEditPart) this.getHost()).getFigure();
    }

    /**
     * Ask figure to show feedback when no longer selected.
     */
    protected void hideSelection() {
        getFigure().setSelected(false);
    }

    /**
     * Ask figure to show feedback when selected.
     */
    protected void showSelection() {
        getFigure().setSelected(true);
    }

    /**
     * Asks figure to hide any hover/selection feedback that was added by showTargetFeedback()
     */
    public void eraseTargetFeedback(Request request) {
        if (request.getType() == RequestConstants.REQ_ADD) {
            getFigure().setSelected(false);
        }
        getFigure().setHover(false);
    }

    /**
     * Asks figure to show hover feedback when mouse flies over. Furthermore, ask them to appear selected if we're drag&dropping elements over each other. We
     * don't know if it is legal to drop here but still show feedback.
     */
    public void showTargetFeedback(Request request) {
        if (request.getType() == RequestConstants.REQ_ADD) {
            getFigure().setSelected(true);
        }
        getFigure().setHover(true);
    }

}