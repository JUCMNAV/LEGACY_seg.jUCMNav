package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.swt.events.KeyEvent;

import seg.jUCMNav.editors.palette.UcmPaletteRoot;

public class BaseConnectionCreationTool extends ConnectionCreationTool {
    public BaseConnectionCreationTool() {
        super();
    }

    public BaseConnectionCreationTool(CreationFactory factory) {
        super(factory);
    }

    protected boolean handleKeyDown(KeyEvent e) {
        boolean s = super.handleKeyDown(e);

        if (getDomain().getPaletteViewer().getPaletteRoot() instanceof UcmPaletteRoot) {
            UcmPaletteRoot root = (UcmPaletteRoot) getDomain().getPaletteViewer().getPaletteRoot();
            ToolEntry entry = root.getAssociatedTool(("" + e.character).toLowerCase()); //$NON-NLS-1$
            if (entry != null) {
                getDomain().getPaletteViewer().setActiveTool(entry);
                return true;
            }
        }
        return false;
    }

}
