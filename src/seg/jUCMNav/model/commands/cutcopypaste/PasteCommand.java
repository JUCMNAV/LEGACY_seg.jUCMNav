package seg.jUCMNav.model.commands.cutcopypaste;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.Clipboard;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.SyntaxChecker;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.scenario.EnumerationType;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Component;
import urncore.Condition;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class PasteCommand extends CompoundCommand
{
	protected EObject insertionPoint; 
	protected Point nodeConnectionMiddle, cursorLocation; 
	protected List sourceIds;
	protected URNspec sourceUrn; 

	protected UCMmap targetMap;
	protected URNspec targetUrn;
	
	public PasteCommand(EObject insertionPoint, URNspec targetUrn, UCMmap targetMap, Point nodeConnectionMiddle, Point cursorLocation)
	{
		this.insertionPoint = insertionPoint;
		this.targetUrn=targetUrn;
		this.targetMap=targetMap;
		this.nodeConnectionMiddle = nodeConnectionMiddle;
		this.cursorLocation = cursorLocation;
		
		Clipboard clip = Clipboard.getInstance();
		sourceIds = clip.getSelectedIds();
		sourceUrn = clip.getOriginalURNspec();
	}

	public void build()
	{
		if (insertionPoint==null)
			return; 
		else
		{
			
			Responsibility def = getFirstResponsibility();
			PathNode oldPn = getFirstPathNode();
			
			
			// this must only be executed when actually doing the initial
			// insert. otherwise create bogus items that are not used
			ModelCreationFactory factory;
			PathNode newPathNode = null;
			
			if (def != null)
			{
				factory = new ModelCreationFactory(targetUrn, RespRef.class, def);
				newPathNode = (RespRef) factory.getNewObject();
			}
			else if (oldPn != null)
			{
				newPathNode = (PathNode) EcoreUtil.copy(oldPn);
				if (newPathNode instanceof Stub)
				{
					Stub stub = (Stub) newPathNode;
					stub.getBindings().clear();
				}
				resetCloneId(newPathNode);
			} 

			if (newPathNode != null)
			{

				setNewNodePosition(newPathNode);

				if (newPathNode instanceof AndFork || newPathNode instanceof OrFork || newPathNode instanceof AndJoin || newPathNode instanceof OrJoin || newPathNode instanceof Stub || newPathNode instanceof Timer)
				{
					buildDividePath(oldPn, newPathNode);
				}
				else
				{
					buildRegularSplice(oldPn, newPathNode);
				}
				
				if (newPathNode instanceof Stub)
				{
					Stub newStub = (Stub) newPathNode;
					Stub oldStub = (Stub) oldPn;
					
					for (int i=0;i<oldStub.getBindings().size();i++)
					{
						PluginBinding binding = (PluginBinding ) oldStub.getBindings().get(i);
						UCMmap oldMap = binding.getPlugin();
						UCMmap map = (UCMmap) URNElementFinder.findMap(targetUrn, oldMap.getId());
						if (map==null)
						{
							map = (UCMmap) URNElementFinder.findMapByName(targetUrn, oldMap.getName());
						}
						if (map!=null && map!=targetMap) // don't allow plugin to self. 
						{
							Condition condition = (Condition) EcoreUtil.copy(binding.getPrecondition());
							add(new AddPluginCommand(newStub, map, condition));
						}
					}
				}
			}
			else 
			{
				Vector compRefList = getClonedComponentRefs();
				if (compRefList != null)
				{
					for (Iterator iterator = compRefList.iterator(); iterator.hasNext();)
					{
						ComponentRef newCompRef = (ComponentRef) iterator.next();
						add(new AddContainerRefCommand(targetMap, newCompRef));
				        add(new SetConstraintBoundContainerRefCompoundCommand(newCompRef, newCompRef.getX(), newCompRef.getY(), newCompRef.getWidth(), newCompRef.getHeight()));
					}
				}
			}
		}		
	}

	private boolean isFromSameModel()
	{
		if (sourceUrn!=null && targetUrn!=null && sourceUrn.getCreated()!=null && targetUrn.getCreated()!=null && sourceUrn.getCreated().equals(targetUrn.getCreated()))
			return true;//System.out.println("From same model.");
		else
			return false;//System.out.println("From different model.");
	}

	private void buildDividePath(PathNode oldPn, PathNode newPathNode)
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

	private void buildRegularSplice(PathNode oldPn, PathNode newPathNode)
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

	public boolean canExecute()
	{
		boolean found = insertionPoint != null;

		if (found)
		{
			// we've selected something that is copiable. 
			found = (getFirstResponsibility() != null || getFirstPathNode()!=null) && (insertionPoint instanceof NodeConnection || insertionPoint instanceof EmptyPoint || insertionPoint instanceof DirectionArrow);
			if (found) return true;
			
			
			found = getFirstComponent()!=null && insertionPoint instanceof UCMmap;
			return found;
		}
		else
			return false;
	}

	public void execute()
	{
		build();
		super.execute();
		postBuild();
	}
	
	/***
	 * Special case for components. Builds a list of component refs that are ready to be inserted. 
	 * 
	 * @return a list of ComponentRefs  
	 */
	protected Vector getClonedComponentRefs()
	{
		return getComponentList(-1, true);
	}
	
	/**
	 * Special case for components. Builds a list of component refs that are ready to be inserted.
	 * 
	 * @param maxCount the maximum number of answers. limit the number for faster checks. 
	 * 
	 * @return a list of ComponentRefs
	 */
	protected Vector getComponentList(int maxCount, boolean generateRefs )
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			ComponentRef firstPlaced=null;

			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(targetUrn, id); 

				Component def = null;
				ComponentRef ref = null;
				if (obj != null) // found it
				{
					if (obj instanceof ComponentRef) {
						ref = (ComponentRef) obj;
						def = (Component)ref.getContDef();
					}
					else
						obj = null;
				}
				

				if (obj==null) 
				{
					// was deleted since.
					obj = URNElementFinder.find(sourceUrn, id);
					if (obj instanceof ComponentRef)
					{
						ref = (ComponentRef) obj;
						
						String oldDefinition = ((Component)ref.getContDef()).getId();
						// this one is faster... searching for the definition.
						Component comp = URNElementFinder.findComponent(targetUrn, oldDefinition);
						if (comp==null) {
							comp = URNElementFinder.findComponent(sourceUrn, oldDefinition);
							comp = (Component) EcoreUtil.copy(comp); // clone it because it is used to create a new element.
							resetCloneId(comp);
						}
						def = comp;
					}
				}
				
				if (def!=null)
				{
					if (generateRefs) 
					{
						ModelCreationFactory factory = new ModelCreationFactory(targetUrn, ComponentRef.class, def.getKind().getValue(), def);
						ComponentRef newCompRef = (ComponentRef) factory.getNewObject();
						setNewContainerConstraints(newCompRef, ref, firstPlaced);

						if (results.size()==0) // leave null for first added, then set it. 
						{
							firstPlaced=ref;
						}
						
						results.add(newCompRef);
					} 
					else 
					{
						results.add(def);
					}
				}
				
				if (results.size()>=maxCount && maxCount>0)break;
			}
			
			return results;
		}
		return null;
	}
	
	/***
	 * Special case for components.
	 * 
	 * @return the first ComponentRef in our list 
	 */
	protected Component getFirstComponent()
	{
		Vector v=  getComponentList(1, false);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (Component) v.get(0);
	}
	
	protected PathNode getFirstPathNode()
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
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
					obj = URNElementFinder.find(sourceUrn, id);
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
	
	/***
	 * Special case for responsibilities.
	 * 
	 * @return the first Responsibility in our 
	 */
	protected Responsibility getFirstResponsibility()
	{

		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(targetUrn, id); 

				if (obj != null) // found it
				{
					if (obj instanceof RespRef)
						return ((RespRef) obj).getRespDef();
					else
						obj=null;
				}
				
				if (obj==null) 
				{
					// was deleted since.
					obj = URNElementFinder.find(sourceUrn, id);
					if (obj instanceof RespRef)
					{
						String oldDefinition = ((RespRef) obj).getRespDef().getId();
						// this one is faster... searching for the definition.
						Responsibility resp = URNElementFinder.findResponsibility(targetUrn, oldDefinition);
						if (resp==null) {
							resp = URNElementFinder.findResponsibility(sourceUrn, oldDefinition);
							resp = (Responsibility) EcoreUtil.copy(resp); // clone it because it is used to create a new element.
							resetCloneId(resp);
						}
						return resp;
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
	
	protected void postBuild()
	{
		Vector errors = SyntaxChecker.verifySyntax(targetUrn);
		Vector toBeAdded = new Vector(); // variables to create. 
		for (Iterator iterator = errors.iterator(); iterator.hasNext();)
		{
			TraversalWarning warning = (TraversalWarning) iterator.next();
			if (warning.getExpression()!=null)
			{
				for (int i=0;i<sourceUrn.getUcmspec().getVariables().size();i++)
				{
					Variable var = (Variable)sourceUrn.getUcmspec().getVariables().get(i);
					if (warning.getExpression().toLowerCase().indexOf(var.getName().toLowerCase())>=0)
					{
						if (!toBeAdded.contains(var) && !URNNamingHelper.doesVariableNameExist(targetUrn, var.getName()) )
							toBeAdded.add(var);
					}
				}
			}
		}
		
		CompoundCommand cmd = new CompoundCommand();
		HashMap createdEnumerations = new HashMap();
		for (int i=0;i<toBeAdded.size();i++)
		{
			Variable var = (Variable)toBeAdded.get(i);
			Variable newVar = (Variable)EcoreUtil.copy(var);
			resetCloneId(newVar);
			CreateVariableCommand createVar=new CreateVariableCommand(targetUrn, newVar);

			if (var.getType()!=null && var.getType().equals(ScenarioUtils.sTypeEnumeration))
			{
				
				EnumerationType newType = null;
				for (int j=0;j<targetUrn.getUcmspec().getEnumerationTypes().size();j++)
				{
					EnumerationType type = (EnumerationType)targetUrn.getUcmspec().getEnumerationTypes().get(j);
					if (type.getName().equals(var.getEnumerationType().getName()))
						newType = type;
				}
				// we need to create the enumeration type. 
				if (newType==null)
				{
					if (createdEnumerations.containsKey(var.getEnumerationType().getName()))
						newType = (EnumerationType) createdEnumerations.get(var.getEnumerationType().getName());
					else 
					{
						newType = (EnumerationType) EcoreUtil.copy(var.getEnumerationType());
						resetCloneId(newType);
						CreateEnumerationTypeCommand createEnum = new CreateEnumerationTypeCommand(targetUrn);
						createEnum.setEnumerationType(newType);
						createdEnumerations.put(newType.getName(), newType);
						cmd.add(createEnum);
					}
				}
				createVar.setEnumerationType(newType);
			}			
			
			cmd.add(createVar);
		}
		
		
		// chain it for the undo. 
		add(cmd);
		
		// don't execute full command, just this portion. 
		if (cmd.canExecute())
			cmd.execute();
	}
	private void resetCloneId(URNmodelElement clone) {
		String name = clone.getName();
		URNNamingHelper.setElementNameAndID(targetUrn, clone);
		clone.setName(name);
	}

	private void setNewContainerConstraints(ComponentRef newCompRef, ComponentRef oldCompRef, ComponentRef offsetFrom)
	{
		if (cursorLocation!=null) 
		{
			int x=cursorLocation.x,y=cursorLocation.y;
			
			if (offsetFrom!=null)
			{
				x += oldCompRef.getX()-offsetFrom.getX();
				y += oldCompRef.getY()-offsetFrom.getY();
			}
			
			newCompRef.setX(x);
			newCompRef.setY(y);
		}
		
		if (oldCompRef!=null)
		{
			newCompRef.setWidth(oldCompRef.getWidth());
			newCompRef.setHeight(oldCompRef.getHeight());
		}
	}
	
	private void setNewNodePosition(PathNode newPathNode)
	{
		// ensures that created branches are at the right place. 
		if (insertionPoint instanceof PathNode) 
		{
			newPathNode.setX(((PathNode) insertionPoint).getX());
			newPathNode.setY(((PathNode) insertionPoint).getY());
		} 
		else if (insertionPoint instanceof NodeConnection)
		{
			newPathNode.setX(nodeConnectionMiddle.x);
			newPathNode.setY(nodeConnectionMiddle.y);
		}
	}

}
