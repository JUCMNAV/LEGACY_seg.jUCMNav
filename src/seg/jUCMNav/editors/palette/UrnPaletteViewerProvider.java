package seg.jUCMNav.editors.palette;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.jface.util.TransferDragSourceListener;

public class UrnPaletteViewerProvider extends PaletteViewerProvider {
    public UrnPaletteViewerProvider(EditDomain graphicalViewerDomain) {
        super(graphicalViewerDomain);
    }

    protected UrnDragSourceListener list;

    public void unconfigurePaletteViewer(PaletteViewer viewer) {
        viewer.removeDragSourceListener((TransferDragSourceListener) list);
        list = null;
    }

    public void configurePaletteViewer(PaletteViewer viewer) {
        super.configurePaletteViewer(viewer);
        // create a drag source listener for this palette viewer
        // together with an appropriate transfer drop target listener, this will enable
        // model element creation by dragging a CombinatedTemplateCreationEntries
        // from the palette into the editor
        // @see ShapesEditor#createTransferDropTargetListener()
        list = new UrnDragSourceListener(viewer);
        viewer.addDragSourceListener(list);
    }
}
