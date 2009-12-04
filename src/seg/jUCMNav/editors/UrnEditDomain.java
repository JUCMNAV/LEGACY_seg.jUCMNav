package seg.jUCMNav.editors;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.SWTException;
import org.eclipse.ui.IEditorPart;

public class UrnEditDomain extends DefaultEditDomain implements Disposable {

    protected Vector viewers = new Vector();

    public UrnEditDomain(IEditorPart editorPart) {
        super(editorPart);
    }

    public void addViewer(EditPartViewer viewer) {
        if (!viewers.contains(viewer))
            viewers.add(viewer);

        super.addViewer(viewer);
    }

    public void removeViewer(EditPartViewer viewer) {
        viewers.remove(viewer);
        super.removeViewer(viewer);
    }

    public void dispose() {
        setPaletteRoot(null);
        setPaletteViewer(null);
        setEditorPart(null);
        for (Iterator iterator = viewers.iterator(); iterator.hasNext();) {
            EditPartViewer viewer = (EditPartViewer) iterator.next();
            try {
                super.removeViewer(viewer);
            } catch (SWTException ex) {

            }
        }
        viewers.clear();

        if (getActiveTool() != null)
            getActiveTool().deactivate();
        if (getDefaultTool() != null)
            getDefaultTool().deactivate();

        // getCommandStack()
        setCommandStack(null);

    }
}
