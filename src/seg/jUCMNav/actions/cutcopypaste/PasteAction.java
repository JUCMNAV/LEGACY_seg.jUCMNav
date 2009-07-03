package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.cutcopypaste.PasteCommand;
import ucm.map.NodeConnection;
import ucm.map.UCMmap;
import urn.URNspec;

public class PasteAction extends URNSelectionAction
{

	public PasteAction(IWorkbenchPart part)
	{
		super(part);
		setId(ActionFactory.PASTE.getId());
		setText(GEFMessages.PasteAction_Label);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
	}
	
	protected Command getCommand()
	{
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		EObject ep = getSelectedInsertionPoint();
		UCMmap targetMap = sel.getMap();
		URNspec targetUrn = sel.getUrnspec();
		Point nodeConnectionMiddle = null;
		if (ep instanceof NodeConnection) 
			nodeConnectionMiddle = sel.getNodeconnectionMiddle();
		
		return new PasteCommand(ep, targetUrn, targetMap, nodeConnectionMiddle);
	}
	
	protected EObject getSelectedInsertionPoint()
	{
		if (getSelectedObjects().size() == 1)
		{
			SelectionHelper sel = new SelectionHelper(getSelectedObjects());

			switch (sel.getSelectionType())
			{
			case SelectionHelper.EMPTYPOINT:
				return sel.getEmptypoint();
			case SelectionHelper.DIRECTIONARROW:
				return sel.getDirectionarrow();
			case SelectionHelper.NODECONNECTION:
				return sel.getNodeconnection();
			}
		}

		return null;
	}

}
