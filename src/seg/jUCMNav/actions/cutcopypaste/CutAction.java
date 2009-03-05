package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.DeleteAction;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.commands.cutcopypaste.CopyCommand;

public class CutAction extends DeleteAction
{

	public CutAction(IWorkbenchPart part)
	{
		super(part);
		setId(ActionFactory.CUT.getId());
		setText("Cut");
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_COPY_DISABLED));
	}
	

	@Override
	public void run()
	{
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		// Don't need to put this on the stack. 
		CopyCommand cmd = new CopyCommand(sel.getUrnspec(), getSelectedObjects());
		cmd.execute();
		
		super.run();
	}


}
