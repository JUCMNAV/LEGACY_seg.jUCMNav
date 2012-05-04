package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.DeleteAction;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.commands.cutcopypaste.CopyCommand;
import seg.jUCMNav.model.commands.delete.DeletionContext;

public class CutAction extends DeleteAction {

    public CutAction(IWorkbenchPart part) {
        super(part);
        setId(ActionFactory.CUT.getId());
        setText(Messages.getString("CutAction.Cut")); //$NON-NLS-1$
        ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
        setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    }

    public void run() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        // Don't need to put this on the stack.
        if (sel.getUrnspec() != null) {
            ImageData screenshot = CopyAction.buildScreenshot(getWorkbenchPart());

            CopyCommand cmd = new CopyCommand(sel.getUrnspec(), getSelectedObjects(), screenshot);
            cmd.execute();
        }

        try {
            DeletionContext.setPerformingCutAction(true);
            super.run();
        } finally
        {
            DeletionContext.setPerformingCutAction(false);
        }
    }

}
