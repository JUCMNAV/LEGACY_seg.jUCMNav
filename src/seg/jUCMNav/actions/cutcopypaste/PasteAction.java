package seg.jUCMNav.actions.cutcopypaste;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.Clipboard;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.Timer;
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

	protected boolean calculateEnabled()
	{
		boolean found = getSelectedInsertionPoint() != null;

		if (found)
		{
			// we've selected something that is copiable. 
			found = getFirstResponsibility() != null || getFirstPathNode()!=null;
			return found;
		}
		else
			return false;
	}

	protected Command getCommand()
	{
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		
		
		EObject ep = getSelectedInsertionPoint();
		
		if (ep==null)
			return null; 
		else
		{
			Responsibility def = getFirstResponsibility();
			
			
			// this must only be executed when actually doing the initial insert. otherwise create bogus items that are not used
			ModelCreationFactory factory;
			PathNode newPathNode=null;
			PathNode oldPn=null;
			if (def!=null) {
				factory = new ModelCreationFactory(sel.getUrnspec(), RespRef.class, def);
				newPathNode= (RespRef)factory.getNewObject();
			} else
			{
				oldPn = getFirstPathNode();
				//if (oldPn!=null && oldPn.getClass().getInterfaces().length>0) {
					//factory = new ModelCreationFactory(sel.getUrnspec(), oldPn.getClass().getInterfaces()[0], oldPn);
					//newPathNode= (PathNode) factory.getNewObject();
				//}
				if (oldPn!=null)
				{
					newPathNode = (PathNode) EcoreUtil.copy(oldPn); 
				}
			}
			
			if (newPathNode!=null) {
				
				// ensures that created branches are at the right place. 
				if (ep instanceof PathNode) {
					newPathNode.setX(((PathNode) ep).getX());
					newPathNode.setY(((PathNode) ep).getY());
				} else if (ep instanceof NodeConnection)
				{
					newPathNode.setX(sel.getNodeconnectionMiddle().x);
					newPathNode.setY(sel.getNodeconnectionMiddle().y);
				}

				if (newPathNode instanceof AndFork || newPathNode instanceof OrFork || newPathNode instanceof AndJoin || newPathNode instanceof OrJoin || newPathNode instanceof Stub || newPathNode instanceof Timer) 
				{
					DividePathCommand cmd = null;
					if (ep instanceof EmptyPoint) {
						cmd = new DividePathCommand(newPathNode, (EmptyPoint)ep);
					}
					else if (ep instanceof DirectionArrow)
						cmd = new DividePathCommand(newPathNode, (DirectionArrow)ep);
					else if (ep instanceof NodeConnection)
						cmd = new DividePathCommand(newPathNode, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y);
					
					if (cmd!=null && oldPn!=null)
						cmd.cloneBranchesFrom(oldPn, sel.getMap());
					return cmd;
				} 
				else 
				{
					if (ep instanceof PathNode)
						return new ReplaceEmptyPointCommand((PathNode) ep, newPathNode);
					else if (ep instanceof NodeConnection)
			            return new SplitLinkCommand(sel.getMap(), newPathNode, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y ) ;
				}
			}

		}
		
		return null;
	}

	protected PathNode getFirstPathNode()
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

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(urn, id); 

				if (obj != null) // found it
				{
					if (obj instanceof PathNode)
						return (PathNode) obj; // pn in current urn
				}
				else 				// was deleted since.
				{
					obj = URNElementFinder.find(oldurn, id);
					if (obj instanceof PathNode)
					{
						PathNode oldPn =(PathNode) obj;
						return oldPn; // pn from old urn
					}
				}
			}
		}
		return null;
	}
	
	/***
	 * Special case for responsibilities.
	 * 
	 * @return the first Responsibility in our 
	 */
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

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(urn, id); 

				if (obj != null) // found it
				{
					if (obj instanceof RespRef)
						return ((RespRef) obj).getRespDef();
				}
				
				// was deleted since.
				obj = URNElementFinder.find(oldurn, id);
				if (obj instanceof RespRef)
				{
					String oldDefinition = ((RespRef) obj).getRespDef().getId();
					// this one is faster... searching for the definition.
					Responsibility resp = URNElementFinder.findResponsibility(urn, oldDefinition);
					if (resp==null) {
						resp = URNElementFinder.findResponsibility(oldurn, oldDefinition);
						resp = (Responsibility) EcoreUtil.copy(resp); // clone it because it is used to create a new element. 
					}
					return resp;
				}
			}
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

}
