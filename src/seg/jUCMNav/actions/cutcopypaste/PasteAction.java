package seg.jUCMNav.actions.cutcopypaste;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.Clipboard;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Responsibility;

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
		
		if (ep==null)
			return null; 
		else
		{
			// this must only be executed when actually doing the initial insert. otherwise create bogus items that are not used
			ModelCreationFactory factory = new ModelCreationFactory(sel.getUrnspec(), RespRef.class, getFirstResponsibility());
			RespRef ref = (RespRef)factory.getNewObject();
			if (ep instanceof PathNode)
				return new ReplaceEmptyPointCommand((PathNode) ep, ref);
			else if (ep instanceof NodeConnection)
	            return new SplitLinkCommand(sel.getMap(), ref, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y ) ;         

		}
		
		return null;
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

	protected Responsibility getFirstResponsibility()
	{
		Clipboard clip = Clipboard.getInstance();
		List content = clip.getSelectedIds();
		URNspec oldurn = clip.getOriginalURNspec();
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		URNspec urn = sel.getUrnspec();

		if (content != null && oldurn != null && urn != null)
		{
			for (Iterator iterator = content.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				Object obj = URNElementFinder.find(urn, id); // search for the
																// old id in the
																// new model.

				if (obj != null) // found it
				{
					if (obj instanceof RespRef)
						return ((RespRef) obj).getRespDef();
				}
				else
				// was deleted since.
				{
					obj = URNElementFinder.find(oldurn, id);
					if (obj instanceof RespRef)
					{
						String oldDefinition = ((RespRef) obj).getRespDef().getId();
						// this one is faster... searching for the definition.
						return URNElementFinder.findResponsibility(urn, oldDefinition);
					}

				}
			}
		}
		return null;
	}

	protected boolean calculateEnabled()
	{
		boolean found = getFirstResponsibility() != null;
		if (found)
		{
			found = getSelectedInsertionPoint() != null;
			if (found)
			{
				// creates bogus elements. 
				//return getCommand().canExecute();
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

}
