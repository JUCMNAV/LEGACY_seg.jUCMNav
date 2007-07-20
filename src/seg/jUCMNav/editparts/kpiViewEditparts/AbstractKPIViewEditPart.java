package seg.jUCMNav.editparts.kpiViewEditparts;

import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import seg.jUCMNav.figures.ColorManager;

/**
 * The general KPIViewEditPart
 * 
 * @author pchen
 * 
 */
public abstract class AbstractKPIViewEditPart extends AbstractGraphicalEditPart {
    protected List kpiViewObjects = null;

    public AbstractKPIViewEditPart(Object model) {
        setModel(model);
        kpiViewObjects = createKPIViewObjects();
    }

    abstract protected List createKPIViewObjects();

    /**
     * Return children
     */
    protected List getModelChildren() {
        return kpiViewObjects;
    }

    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();

        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(2));
        layer.setBackgroundColor(ColorManager.WHITE);
        layer.setOpaque(true);

        if (kpiViewObjects != null && kpiViewObjects.size() > 0) {
            KPIViewObject theLastFigure = ((KPIViewObject) kpiViewObjects.get(kpiViewObjects.size() - 1));
            layer.getBounds().height = KPIViewObject.SHIFT_Y * 2 + theLastFigure.getY() + theLastFigure.getHeight();
        }

        return layer;
    }

    protected void createEditPolicies() {
    }

    public void refreshChildren() {
        kpiViewObjects = createKPIViewObjects();
        super.refreshChildren();
    }

    public void notifyChanged(Notification notification) {
        if (getParent() == null) {
            return;
        }

        refreshVisuals();
    }

}
