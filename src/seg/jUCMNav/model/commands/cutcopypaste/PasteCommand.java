package seg.jUCMNav.model.commands.cutcopypaste;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.Clipboard;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;

public class PasteCommand extends CompoundCommand
{
	protected EObject insertionPoint; 
	protected UCMmap targetMap; 
	protected URNspec targetUrn;
	protected Point nodeConnectionMiddle; 
	
	public PasteCommand(EObject insertionPoint, URNspec targetUrn, UCMmap targetMap, Point nodeConnectionMiddle)
	{
		this.insertionPoint = insertionPoint;
		this.targetUrn=targetUrn;
		this.targetMap=targetMap;
		this.nodeConnectionMiddle = nodeConnectionMiddle;
	}

	public boolean canExecute()
	{
		boolean found = insertionPoint != null;

		if (found)
		{
			// we've selected something that is copiable. 
			found = getFirstResponsibility() != null || getFirstPathNode()!=null;
			return found;
		}
		else
			return false;
	}
	
	public void execute()
	{
		build();
		super.execute();
	}
	
	
	protected PathNode getFirstPathNode()
	{
		Clipboard clip = Clipboard.getInstance();
		List content = clip.getSelectedIds();
		URNspec oldurn = clip.getOriginalURNspec();
		//SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		//URNspec urn = sel.getUrnspec();

		if (content != null && oldurn != null && targetUrn != null)
		{
			for (Iterator iterator = content.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(targetUrn, id); 

				if (obj != null) // found it
				{
					if (IsPastablePathNode(obj))
						return (PathNode) obj; // pn in current urn
				}
				else 				// was deleted since.
				{
					obj = URNElementFinder.find(oldurn, id);
					if (IsPastablePathNode(obj))
					{
						PathNode oldPn =(PathNode) obj;
						return oldPn; // pn from old urn
					}
				}
			}
		}
		return null;
	}
	
	protected boolean IsPastablePathNode(Object obj)
	{
		return obj instanceof PathNode && !(obj instanceof EndPoint) && !(obj instanceof StartPoint) && !(obj instanceof Connect);   
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


		if (content != null && oldurn != null && targetUrn != null)
		{
			for (Iterator iterator = content.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(targetUrn, id); 

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
					Responsibility resp = URNElementFinder.findResponsibility(targetUrn, oldDefinition);
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

	

	
	public void build()
	{
		if (insertionPoint==null)
			return; 
		else
		{
			Responsibility def = getFirstResponsibility();
			PathNode oldPn = getFirstPathNode();

			// this must only be executed when actually doing the initial insert. otherwise create bogus items that are not used
			ModelCreationFactory factory;
			PathNode newPathNode=null;
			if (def!=null) {
				factory = new ModelCreationFactory(targetUrn, RespRef.class, def);
				newPathNode= (RespRef)factory.getNewObject();
			} else
			{
				if (oldPn!=null)
				{
					newPathNode = (PathNode) EcoreUtil.copy(oldPn); 
				}
			}
			
			if (newPathNode!=null) {
				
				// ensures that created branches are at the right place. 
				if (insertionPoint instanceof PathNode) {
					newPathNode.setX(((PathNode) insertionPoint).getX());
					newPathNode.setY(((PathNode) insertionPoint).getY());
				} else if (insertionPoint instanceof NodeConnection)
				{
					newPathNode.setX(nodeConnectionMiddle.x);
					newPathNode.setY(nodeConnectionMiddle.y);
				}

				if (newPathNode instanceof AndFork || newPathNode instanceof OrFork || newPathNode instanceof AndJoin || newPathNode instanceof OrJoin || newPathNode instanceof Stub || newPathNode instanceof Timer) 
				{
					DividePathCommand cmd = null;
					if (insertionPoint instanceof EmptyPoint) {
						cmd = new DividePathCommand(newPathNode, (EmptyPoint)insertionPoint);
					}
					else if (insertionPoint instanceof DirectionArrow)
						cmd = new DividePathCommand(newPathNode, (DirectionArrow)insertionPoint);
					else if (insertionPoint instanceof NodeConnection)
						cmd = new DividePathCommand(newPathNode, (NodeConnection)insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y);
					
					if (cmd!=null && oldPn!=null)
						cmd.cloneBranchesFrom(oldPn, targetMap);
					if (cmd!=null)
						add(cmd);
				} 
				else 
				{
					Condition cond= null;
					if (oldPn.getSucc().size()>0)
					{
						NodeConnection nc = (NodeConnection)oldPn.getSucc().get(0);
						cond = nc.getCondition();
					}
					
					Command cmd=null;
					if (insertionPoint instanceof PathNode)
						cmd = new ReplaceEmptyPointCommand((PathNode) insertionPoint, newPathNode, cond );
					else if (insertionPoint instanceof NodeConnection)
			            cmd = new SplitLinkCommand(targetMap, newPathNode, (NodeConnection)insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y, cond) ;
					
					if (cmd!=null)
						add(cmd);
				}
			}

		}		
	}
	

}
