package seg.jUCMNav.editparts.kpiViewEditparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import seg.jUCMNav.figures.kpi.KPIViewObjectFigure;

/**
 * The KPIViewObject edit part
 * 
 * @author pchen
 * 
 */
public class KPIViewObjectEditPart extends AbstractGraphicalEditPart {

    public KPIViewObjectEditPart(KPIViewObject element) {
        super();
        setModel(element);
    }

    protected IFigure createFigure() {
        KPIViewObjectFigure fig = new KPIViewObjectFigure((KPIViewObject) getModel());
        return fig;
    }

    protected void createEditPolicies() {
    }

    /**
     * 
     * @return the KPIViewObject.
     */
    private KPIViewObject getNode() {
        return (KPIViewObject) getModel();
    }

    /**
     * @return The node's figure
     */
    public KPIViewObjectFigure getNodeFigure() {
        return (KPIViewObjectFigure) getFigure();
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
