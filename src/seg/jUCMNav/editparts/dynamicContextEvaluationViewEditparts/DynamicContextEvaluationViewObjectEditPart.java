package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import seg.jUCMNav.figures.dynamicContexts.DynamicContextEvaluationViewObjectFigure;

/**
 * The DynamicContextEvaluationViewObject EditPart
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewObjectEditPart extends AbstractGraphicalEditPart {
	
	public DynamicContextEvaluationViewObjectEditPart(DynamicContextEvaluationViewObject element) {
        super();
        setModel(element);
    }

    protected IFigure createFigure() {
    	DynamicContextEvaluationViewObjectFigure fig = new DynamicContextEvaluationViewObjectFigure((DynamicContextEvaluationViewObject) getModel());
        return fig;
    }

    protected void createEditPolicies() {
    }

    /**
     * 
     * @return the DynamicContextEvaluationViewObject.
     */
    private DynamicContextEvaluationViewObject getNode() {
        return (DynamicContextEvaluationViewObject) getModel();
    }

    /**
     * @return The node's figure
     */
    public DynamicContextEvaluationViewObjectFigure getNodeFigure() {
        return (DynamicContextEvaluationViewObjectFigure) getFigure();
    }

    public void notifyChanged(Notification notification) {
        if (getParent() == null) {
            return;
        }

        refreshVisuals();
    }

    /**
     * Refresh the figure and its associated labels.
     * 
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getNode().getX(), getNode().getY());

        // The size of the current figure
        Dimension size = getNodeFigure().getSize().getCopy();

        Rectangle bounds = new Rectangle(location, size);
        getNodeFigure().setBounds(bounds);
        getNodeFigure().setupFigure();

        getNodeFigure().validate();
    }

}
