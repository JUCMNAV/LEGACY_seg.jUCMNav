package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.TimepointGroup;

/**
 * Edit Part for DynamicContextEvaluationViewTimepointGroup
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewTimepointGroupEditPart extends AbstractDynamicContextEvaluationViewEditPart{
	 
	 private DynamicContextEvaluationViewObject dynViewObject;
	 
	 public DynamicContextEvaluationViewTimepointGroupEditPart(TimepointGroup tgGroup) {
	        super(tgGroup);
	        dynViewObject = null;
	    }

	    /*public DynamicContextEvaluationViewTimepointGroupEditPart(IntentionalElementRef ref) {
	        super(ref.getDef());
	    }*/

	    // retrieve and return DynamicContextEvaluationViewObject from the model element
	    protected DynamicContextEvaluationViewObject createDynamicContextEvaluationViewObject() {
	    	if (dynViewObject == null) {
		    	TimepointGroup group = getNode();
		    	DynamicContext dyn = null;
		    	if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
	                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView") != null) { //$NON-NLS-1$
	            	DynamicContextsView dv = (DynamicContextsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView").getView(false);
	                if (dv != null && dv.getDynamicContext() != null) {
	                	dyn = dv.getDynamicContext();
	                }
	            }
		    	int current_y = 0;
		    	dynViewObject = new DynamicContextEvaluationViewObject(dyn, group);
		        dynViewObject.setY(current_y);
	    	}
	        return dynViewObject;
	    }

	    /**
	     * 
	     * @return the TimepointGroup.
	     */
	    private TimepointGroup getNode() {
	        return (TimepointGroup) getModel();
	    }

}
