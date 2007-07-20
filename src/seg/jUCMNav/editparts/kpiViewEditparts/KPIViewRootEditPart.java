package seg.jUCMNav.editparts.kpiViewEditparts;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * This class is the root of editpart
 * 
 * @author pchen
 * 
 */
public class KPIViewRootEditPart extends AbstractGraphicalEditPart {
    private Object model = null;

    public KPIViewRootEditPart(Object model) {
        super();
        this.model = model;
    }

    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();

        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(1));
        return layer;
    }

    protected void createEditPolicies() {
    }

}
