package seg.jUCMNav.views.dnd;

import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;

import ucm.map.RespRef;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.Responsibility;

public class UrnTemplateTransferDragSourceListener extends TemplateTransferDragSourceListener {

    /**
     * Constructs a new listener for the specified EditPartViewer. The TemplateTransferDragSourceListener will only be enabled when a single EditPart is
     * selected
     * 
     * @param viewer
     *            the EditPartViewer that is the drag source
     */
    public UrnTemplateTransferDragSourceListener(EditPartViewer viewer) {
        super(viewer, UrnTemplateTransfer.getInstance());
    }

    /**
     * A helper method that returns <code>null</code> or the <i>template</i> Object from the currently selected EditPart.
     * 
     * @return the template
     */
    protected Object getTemplate() {
        List selection = getViewer().getSelectedEditParts();
        if (selection.size() == 1) {
            EditPart editpart = (EditPart) getViewer().getSelectedEditParts().get(0);
            Object model = editpart.getModel();
            if (model instanceof IURNContainer || model instanceof Responsibility || model instanceof IntentionalElement)
                return model;
            else if (model instanceof IURNContainerRef)
                return ((IURNContainerRef) model).getContDef();
            else if (model instanceof RespRef)
                return ((RespRef) model).getRespDef();
            else if (model instanceof IntentionalElementRef)
                return ((IntentionalElementRef) model).getDef();
        }
        return null;
    }

}
