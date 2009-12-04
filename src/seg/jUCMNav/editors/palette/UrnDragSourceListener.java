package seg.jUCMNav.editors.palette;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteTemplateEntry;
import org.eclipse.gef.tools.CreationTool;

public class UrnDragSourceListener extends TemplateTransferDragSourceListener {
    public UrnDragSourceListener(EditPartViewer viewer) {
        super(viewer);
    }

    protected Object getTemplate() {
        List selection = getViewer().getSelectedEditParts();
        if (selection.size() == 1) {
            EditPart editpart = (EditPart) getViewer().getSelectedEditParts().get(0);
            Object model = editpart.getModel();
            if (model instanceof PaletteTemplateEntry)
                return ((PaletteTemplateEntry) model).getTemplate();
            if (model instanceof CombinedTemplateCreationEntry)
                return ((CombinedTemplateCreationEntry) model).getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
        }
        return null;
    }
}
