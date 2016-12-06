package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.TimepointGroup;

/**
 * Edit Part for DynamicContextEvaluationView DynamicContext
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewDCEditPart extends AbstractDynamicContextEvaluationViewEditPart{

	public DynamicContextEvaluationViewDCEditPart(DynamicContext dyn) {
        super(dyn);
    }

    // retrieve and return DynamicContextEvaluationViewObject from the model element
    protected DynamicContextEvaluationViewObject createDynamicContextEvaluationViewObject() {
    	DynamicContext dyn = getNode();
    	TimepointGroup group = null;
    	if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView") != null) { //$NON-NLS-1$
        	DynamicContextsView dv = (DynamicContextsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView").getView(false);
            if (dv != null && dv.getTimepointGroup() != null) {
            	group = dv.getTimepointGroup();
            }
        }
    	int current_y = 0;
    	DynamicContextEvaluationViewObject dynViewObject = new DynamicContextEvaluationViewObject(dyn, group);
        dynViewObject.setY(current_y);
            
        return dynViewObject;
    }

    /**
     * 
     * @return the DynamicContext.
     */
    private DynamicContext getNode() {
        return (DynamicContext) getModel();
    }

}
