package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.model.commands.cutcopypaste.CopyCommand;

public class CopyAction extends SelectionAction {

    public CopyAction(IWorkbenchPart part) {
        super(part);
        setId(ActionFactory.COPY.getId());
        setText(GEFMessages.CopyAction_Label);
        ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    }

    protected boolean calculateEnabled() {
        return true;
    }

    public void run() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        // Don't need to put this on the stack.
        if (sel.getUrnspec() != null) {
            ImageData screenshot = buildScreenshot(getWorkbenchPart());

            CopyCommand cmd = new CopyCommand(sel.getUrnspec(), getSelectedObjects(), screenshot);
            cmd.execute();
        }
    }

    public static ImageData buildScreenshot(IWorkbenchPart part) {
        ImageData screenshot = null;

        if (part instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor multi = (UCMNavMultiPageEditor) part;
            if (multi.getCurrentPage() != null) {
                UrnEditor editor = (UrnEditor) multi.getCurrentPage();
                LayeredPane pane = ((URNRootEditPart) (editor.getGraphicalViewer().getRootEditPart())).getScaledLayers();

                IFigure figure = pane;
                int w = figure.getSize().width;
                int h = figure.getSize().height;
                Image image = new Image(Display.getDefault(), w, h);

                GC gc = new GC(image);
                SWTGraphics graphics = new SWTGraphics(gc);
                graphics.translate(-pane.getBounds().x, -pane.getBounds().y);
                figure.paint(graphics);

                // TODO: Improve crop to make use of current selection.
                screenshot = ReportUtils.cropImage(image.getImageData());
                
                graphics.dispose();
                gc.dispose();
            }
        }
        return screenshot;
    }

}
