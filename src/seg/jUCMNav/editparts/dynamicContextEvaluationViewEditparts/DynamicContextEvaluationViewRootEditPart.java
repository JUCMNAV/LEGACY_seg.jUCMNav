package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * This class is the root of editpart
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewRootEditPart extends AbstractGraphicalEditPart {
	
	public DynamicContextEvaluationViewRootEditPart(Object model) {
        super();
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
