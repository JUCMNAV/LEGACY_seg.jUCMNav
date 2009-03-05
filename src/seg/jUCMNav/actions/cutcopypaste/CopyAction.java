package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.commands.cutcopypaste.CopyCommand;

public class CopyAction extends SelectionAction
{

	public CopyAction(IWorkbenchPart part)
	{
		super(part);
		setId(ActionFactory.COPY.getId());
		setText(GEFMessages.CopyAction_Label);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_COPY_DISABLED));
	}

	protected boolean calculateEnabled()
	{
		return true;
	}

	public void run()
	{
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		// Don't need to put this on the stack. 
		CopyCommand cmd = new CopyCommand(sel.getUrnspec(), getSelectedObjects());
		cmd.execute();
	}
	

}
