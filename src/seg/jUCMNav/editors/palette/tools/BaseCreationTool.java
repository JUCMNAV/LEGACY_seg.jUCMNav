package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.swt.events.KeyEvent;

import seg.jUCMNav.editors.palette.UcmPaletteRoot;

/**
 * Base creation tool. Forwards keystrokes when palette is selected.
 * 
 * 
 * @author jkealey
 * 
 */
public class BaseCreationTool extends CreationTool {
    protected static Request directEditRequest = new Request(RequestConstants.REQ_DIRECT_EDIT);

    public BaseCreationTool() {
    }

    public BaseCreationTool(CreationFactory factory) {
        super(factory);
    }

    protected boolean handleKeyDown(KeyEvent e) {
        boolean s = super.handleKeyDown(e);

        if (getDomain().getPaletteViewer().getPaletteRoot() instanceof UcmPaletteRoot) {
            UcmPaletteRoot root = (UcmPaletteRoot) getDomain().getPaletteViewer().getPaletteRoot();
            ToolEntry entry = root.getAssociatedTool(("" + e.character).toLowerCase()); //$NON-NLS-1$
            if (entry != null && getDomain().getPaletteViewer().getEditPartRegistry()!=null && getDomain().getPaletteViewer().getEditPartRegistry().get(entry)!=null) {
                getDomain().getPaletteViewer().setActiveTool(entry);
                return true;
            }
        }
        return false;
    }

    protected void performCreation(int button) {
        super.performCreation(button);

        EditPartViewer viewer = getCurrentViewer();
        final Object model = getCreateRequest().getNewObject();
        if (model == null || viewer == null)
            return;

        Object editpart = viewer.getEditPartRegistry().get(model);
        if (editpart instanceof EditPart) {
            EditPart part = (EditPart) editpart;
            part.performRequest(directEditRequest);
        }
    }
}
