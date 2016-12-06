package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import seg.jUCMNav.figures.ColorManager;

/**
 * The general DynamicContextEvaluationViewEditPart
 * 
 * @author aprajita
 * 
 */
public abstract class AbstractDynamicContextEvaluationViewEditPart extends AbstractGraphicalEditPart {
	
	protected List dynViewObjects;

    public AbstractDynamicContextEvaluationViewEditPart(Object model) {
        setModel(model);
        dynViewObjects = new ArrayList();
        dynViewObjects.add(createDynamicContextEvaluationViewObject());
    }

    abstract protected DynamicContextEvaluationViewObject createDynamicContextEvaluationViewObject();

    /**
     * Return children
     */
    protected List getModelChildren() {
        return dynViewObjects;
    }

    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();

        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(2));
        layer.setBackgroundColor(ColorManager.WHITE);
        layer.setOpaque(true);

        if (dynViewObjects != null && dynViewObjects.size() > 0) {
        	DynamicContextEvaluationViewObject theLastFigure = ((DynamicContextEvaluationViewObject) dynViewObjects.get(dynViewObjects.size() - 1));
            layer.getBounds().height = DynamicContextEvaluationViewObject.SHIFT_Y * 2 + theLastFigure.getY() + theLastFigure.getHeight();
        }

        return layer;
    }

    protected void createEditPolicies() {
    }

    public void refreshChildren() {
    	super.refreshChildren();
    }

    public void notifyChanged(Notification notification) {
        if (getParent() == null) {
            return;
        }

        refreshVisuals();
    }


}
