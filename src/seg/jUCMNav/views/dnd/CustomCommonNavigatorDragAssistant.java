package seg.jUCMNav.views.dnd;

import grl.ContributionContext;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.navigator.CommonDragAdapterAssistant;

import seg.jUCMNav.editparts.treeEditparts.UrnAbstractTreeEditPart;
import ucm.map.RespRef;
import ucm.scenario.ScenarioDef;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class CustomCommonNavigatorDragAssistant extends
		CommonDragAdapterAssistant {


	@Override
	public Transfer[] getSupportedTransferTypes() {
		return new Transfer[] { UrnTemplateTransfer.getInstance() };
	}

	@Override
	public boolean setDragData(DragSourceEvent anEvent,
			IStructuredSelection aSelection) {
		
		Object template = getTemplate(aSelection);
		anEvent.data = template;
		
		if( template == null)
			return false;
		else
			return true;
	
	}
	
	/**
	 * 
	 * Allows the drag assistant indicate it wants to participate in the drag operation.
	 * This is called at {@link DragSourceListener#dragStart(DragSourceEvent)} 
	 * time.
	 * 
	 * @param anEvent
	 *            The event object should return doit = true if it wants to participate
	 *            in the drag and set doit = false if it does not want to further 
	 *            participate.
	 * @param aSelection
	 *            The current selection from the viewer.
	 * 
	 * @since 3.4
	 */
	@Override
	public void dragStart(DragSourceEvent anEvent,
			IStructuredSelection aSelection) {
		Object template = getTemplate(aSelection);
		if (template == null)
			anEvent.doit = false;
		TemplateTransfer.getInstance().setTemplate(template);
	}
	
	/**
	 * 
	 * Allows the drag assistant to do any necessary cleanup after the drop operation
	 * is done. This is called at {@link DragSourceListener#dragFinished(DragSourceEvent)} 
	 * time.  This is called on the same assistant that was called for the set data.
	 * 
	 * @param anEvent
	 *            The event object should have its {@link Event#data} field set
	 *            to a value that matches a supported {@link TransferData} type.
	 * @param aSelection
	 *            The current selection from the viewer.
	 * 
	 * @since 3.4
	 */
	@Override
	public void dragFinished(DragSourceEvent anEvent,
			IStructuredSelection aSelection) {
		TemplateTransfer.getInstance().setTemplate(null);
		
		UrnTemplateTransferDragSourceListener urnTemplateTransferListener = new UrnTemplateTransferDragSourceListener(null);
		urnTemplateTransferListener.dragFinished(anEvent);
	}
	
	/**
     * A helper method that returns <code>null</code> or the <i>template</i> Object from the currently selected EditPart.
     * 
     * @return the template
     */
    protected Object getTemplate(IStructuredSelection aSelection) {
    	
        if (aSelection.size() == 1) {
        	
        	Object model = aSelection.getFirstElement();
        	
            if (model instanceof IURNContainer || model instanceof Responsibility || model instanceof IntentionalElement
                    || model instanceof KPIInformationElement)
                return model;
            else if (model instanceof IURNContainerRef)
                return ((IURNContainerRef) model).getContDef();
            else if (model instanceof RespRef)
                return ((RespRef) model).getRespDef();
            else if (model instanceof IntentionalElementRef)
                return ((IntentionalElementRef) model).getDef();
            else if (model instanceof KPIInformationElementRef)
                return ((KPIInformationElementRef) model).getDef();
            else if (model instanceof ScenarioDef || model instanceof EvaluationStrategy || model instanceof ContributionContext)
                return model;
        }
        return null;
    }

}
