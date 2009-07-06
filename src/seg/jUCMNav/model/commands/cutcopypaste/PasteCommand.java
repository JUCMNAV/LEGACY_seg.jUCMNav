package seg.jUCMNav.model.commands.cutcopypaste;

import grl.Actor;
import grl.ActorRef;
import grl.GRLGraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.editparts.CommentEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.create.AddCommentCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.CreateContainerCommand;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.create.CreateResponsibilityCommand;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.model.commands.create.DuplicateCommand;
import seg.jUCMNav.model.commands.create.IncludePathNodeInScenarioCommand;
import seg.jUCMNav.model.commands.create.IncludeScenarioCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.DuplicateMapCommand;
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
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Comment;
import urncore.Component;
import urncore.Condition;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

public class PasteCommand extends CompoundCommand
{
	protected EObject insertionPoint; 
	protected Point nodeConnectionMiddle, cursorLocation; 
	protected List sourceIds;
	protected List sourceSelection;
	protected URNspec sourceUrn; 

	protected IURNDiagram targetDiagram;
	protected UCMmap targetMap;
	protected URNspec targetUrn;
	
	protected boolean alreadyBuilt;
	
	public PasteCommand(EObject insertionPoint, URNspec targetUrn, IURNDiagram targetMap, Point nodeConnectionMiddle, Point cursorLocation)
	{
		this.insertionPoint = insertionPoint;
		this.targetUrn=targetUrn;
		
		this.targetDiagram=targetMap;
		if (targetDiagram instanceof UCMmap)
			this.targetMap=(UCMmap)targetDiagram;
		
		this.nodeConnectionMiddle = nodeConnectionMiddle;
		this.cursorLocation = cursorLocation;
		
		Clipboard clip = Clipboard.getInstance();
		sourceIds = clip.getSelectedIds();
		sourceUrn = clip.getOriginalURNspec();
		sourceSelection = clip.getSelection();
		alreadyBuilt=false;
	}

	public void build()
	{
		if (alreadyBuilt==true) return;
		
		if (insertionPoint==null)
			return; 
		else
		{
			
			Responsibility def = getFirstResponsibility();
			PathNode oldPn = getFirstPathNode();
			
			
			// this must only be executed when actually doing the initial
			// insert. otherwise create bogus items that are not used
			
			// only use the ModelCreationFactory for References. otherwise clone or lose metadata, urnlinks, etc. 
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

			if (newPathNode != null && targetMap!=null)
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
					
					copyStubPlugins(newStub, oldStub);
				}
			}
			
			
			if (targetDiagram!=null) 
			{
				buildAllContainerRefs();
				
				buildAllComments();
			}
			
			if (targetUrn!=null)
			{
				Vector list = getSimpleList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					if(obj instanceof Responsibility)
					{
						Responsibility oldResp=  (Responsibility)obj;
						buildResponsibility(oldResp);
					} else if(obj instanceof Component)
					{
						Component oldComponent = (Component) obj;
						buildComponent(oldComponent);
					} else if(obj instanceof Actor)
					{
						Actor oldActor = (Actor) obj;
						buildActor(oldActor);
					} else if (obj instanceof IURNDiagram)
					{
						IURNDiagram oldDiagram = (IURNDiagram) obj;
						buildDiagram(oldDiagram);
					}
					else
						System.out.println("TODO: Paste " + obj);
				}
				
				list = getScenarioList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					if(obj instanceof ScenarioDef && insertionPoint instanceof ScenarioGroup)
					{
						ScenarioDef oldScenario = (ScenarioDef) obj;
						buildScenario(oldScenario, (ScenarioGroup) insertionPoint );
					}				
				}
				
				list = getScenarioGroupList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					if(obj instanceof ScenarioGroup)
					{
						ScenarioGroup oldScenarioGroup = (ScenarioGroup) obj;
						buildScenarioGroup(oldScenarioGroup);
					}				
				}	
			}
		}
		
		alreadyBuilt=true;
	}

	private void buildActor(Actor oldActor)
	{
		if (URNElementFinder.findActorByName(targetUrn, oldActor.getName())==null)
		{
			Actor newActor = (Actor) EcoreUtil.copy(oldActor);
			resetCloneId(newActor);
			add(new CreateContainerCommand(targetUrn,newActor));
		}
	}

	private void buildAllComments()
	{
		Vector commentList = getClonedComments();
		for (Iterator iterator = commentList.iterator(); iterator.hasNext();)
		{
			Comment comment = (Comment) iterator.next();
			add(new AddCommentCommand(targetDiagram, comment));
		}
	}

	private void buildAllContainerRefs()
	{
		Vector compRefList = getClonedContainerRefs();
		if (compRefList != null)
		{
			for (Iterator iterator = compRefList.iterator(); iterator.hasNext();)
			{
				IURNContainerRef newCompRef = (IURNContainerRef) iterator.next();
				add(new AddContainerRefCommand(targetDiagram, newCompRef));
		        add(new SetConstraintBoundContainerRefCompoundCommand(newCompRef, newCompRef.getX(), newCompRef.getY(), newCompRef.getWidth(), newCompRef.getHeight()));
			}
		}
	}

	private void buildComponent(Component oldComponent)
	{
		if (URNElementFinder.findComponentByName(targetUrn, oldComponent.getName())==null)
		{
			Component newComponent = (Component) EcoreUtil.copy(oldComponent);
			resetCloneId(newComponent);
			add(new CreateContainerCommand(targetUrn,newComponent));
		}
	}

	private void buildDiagram(IURNDiagram oldDiagram)
	{
		String oldName = ((URNmodelElement)oldDiagram).getName();
		IURNDiagram toClone= URNElementFinder.findMapByName(targetUrn, oldName);
		if (toClone instanceof UCMmap)
		{
			add(new DuplicateMapCommand((UCMmap)toClone));
		}
		else if (toClone instanceof GRLGraph)
		{
			add(new DuplicateMapCommand((GRLGraph)toClone));
		}
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

	private void buildResponsibility(Responsibility oldResp)
	{
		if (URNElementFinder.findResponsibilityByName(targetUrn, oldResp.getName())==null) 
		{
			Responsibility newResp = (Responsibility) EcoreUtil.copy(oldResp);
			resetCloneId(newResp);
			add(new CreateResponsibilityCommand(targetUrn,newResp));
		}
	}

	private void buildScenario(ScenarioDef oldScenario, ScenarioGroup targetGroup)
	{
		if (targetGroup!=null)
		{
			
			// create a shallow copy to break references to stuff we don't know exists. 
			ScenarioDef newScenario =(ScenarioDef) EcoreUtil.copy(oldScenario);
			resetCloneId(newScenario);
			newScenario.getStartPoints().clear();
			newScenario.getEndPoints().clear();
			
			DuplicateCommand duplicateCommand = new DuplicateCommand(newScenario, targetGroup, targetUrn);
			add(duplicateCommand);
			
			ScenarioDef duplicate = (ScenarioDef) duplicateCommand.getDuplicate();
			for (int i=0;i<oldScenario.getInitializations().size();i++)
			{
				Initialization init = (Initialization) oldScenario.getInitializations().get(i);
				Variable var = URNElementFinder.findVariable(targetUrn, init.getVariable().getId());
				if (var!=null)
				{
					add(new CreateVariableInitializationCommand(var, duplicate, init.getValue() ));
				}
			}
			for (int i=0;i<oldScenario.getStartPoints().size();i++)
			{
				ScenarioStartPoint start = (ScenarioStartPoint) oldScenario.getStartPoints().get(i);
				Object pn = URNElementFinder.find(targetUrn, start.getStartPoint().getId());
				if (pn!=null && pn instanceof StartPoint)
				{
					IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
					inc.setClone(start);
					add(inc);
				}
			}
			for (int i=0;i<oldScenario.getEndPoints().size();i++)
			{
				ScenarioEndPoint end = (ScenarioEndPoint) oldScenario.getEndPoints().get(i);
				Object pn = URNElementFinder.find(targetUrn, end.getEndPoint().getId());
				if (pn!=null && pn instanceof EndPoint)
				{
					IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
					inc.setClone(end);
					add(inc);
				}
			}		
			for (int i=0;i<oldScenario.getIncludedScenarios().size();i++)
			{
				ScenarioDef oldChild = (ScenarioDef) oldScenario.getIncludedScenarios().get(i);
				ScenarioDef newChild = URNElementFinder.findScenario(targetUrn, oldChild.getId());
				if (newChild!=null)
				{
					IncludeScenarioCommand inc = new IncludeScenarioCommand(duplicate, newChild,true);
					add(inc);
				}
			}			
		}
	}

	private void buildScenarioGroup(ScenarioGroup oldScenarioGroup)
	{
		if (insertionPoint instanceof URNspec) 
		{
			ScenarioGroup newScenarioGroup =(ScenarioGroup) EcoreUtil.copy(oldScenarioGroup);
			resetCloneId(newScenarioGroup);
			
			newScenarioGroup.getScenarios().clear();
			DuplicateCommand duplicateCommand = new DuplicateCommand(newScenarioGroup, targetUrn);
			add(duplicateCommand);
			
			ScenarioGroup duplicate = (ScenarioGroup) duplicateCommand.getDuplicate();
			
			for (Iterator iterator = oldScenarioGroup.getScenarios().iterator(); iterator.hasNext();)
			{
				ScenarioDef oldScenario = (ScenarioDef) iterator.next();
				buildScenario(oldScenario, duplicate);
			}
		}
	}

	
	public boolean canExecute()
	{
		boolean found = insertionPoint != null;

		if (found)
		{
			// we've selected something that is copiable. 
			found = (insertionPoint instanceof NodeConnection || insertionPoint instanceof EmptyPoint || insertionPoint instanceof DirectionArrow) && targetMap!=null && (getFirstResponsibility() != null || getFirstPathNode()!=null);
			if (found) return true;
			
			
			found =  insertionPoint instanceof IURNDiagram && (getFirstContainer()!=null);
			if (found) return true;
			
			found =  insertionPoint instanceof IURNDiagram && getFirstComment()!=null;
			if (found) return true;
			
			found = insertionPoint instanceof ScenarioGroup && getFirstScenario()!=null;
			if (found) return true;
			
			found = insertionPoint instanceof URNspec && getFirstScenarioGroup()!=null;
			if (found) return true;
			
			found = insertionPoint instanceof URNspec && getFirstSimple()!=null; 
			return found;
		}
		else
			return false;
	}

	private void copyStubPlugins(Stub newStub, Stub oldStub)
	{
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

	public void execute()
	{
		build();
		super.execute();
		postBuild();
	}
	
	protected Vector getClonedComments()
	{
		return getCommentList(-1);
	}
	
	/***
	 * Special case for containers. Builds a list of containers refs that are ready to be inserted. 
	 * 
	 * @return a list of containers refs  
	 */
	protected Vector getClonedContainerRefs()
	{
		return getContainerList(-1, true);
	}
	protected Vector getCommentList(int maxCount)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			Comment firstPlaced=null;
			for (Iterator iterator = sourceSelection.iterator(); iterator.hasNext();)
			{
				Object object = (Object) iterator.next();
				if (object instanceof CommentEditPart)
				{
					Comment oldComment = (Comment)((CommentEditPart)object).getModel();
					Comment newComment = (Comment) EcoreUtil.copy(oldComment);
					
					setNewCommentConstraints(newComment, oldComment, firstPlaced);
					
					if (results.size()==0) // leave null for first added, then set it. 
					{
						firstPlaced=oldComment;
					}
					
					results.add(newComment);
				}
				if (results.size()>=maxCount && maxCount>0)break;
			}
			return results;
		}
		return null;
	}
	
	/**
	 * Builds a list of elements that are ready to be inserted on a diagram. 
	 * 
	 * @param maxCount the maximum number of answers. limit the number for faster checks. 
	 * @param generateRefs if true, will return a new ref - otherwise returns the original def 
	 * 
	 * @return a list of containers refs/containers
	 */
	protected Vector getContainerList(int maxCount, boolean generateRefs )
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			IURNContainerRef firstPlaced=null;

			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the new model. 
				Object obj = URNElementFinder.find(targetUrn, id); 

				IURNContainer def = null;
				IURNContainerRef ref = null;
				if (obj != null) // found it
				{
					if (obj instanceof IURNContainerRef)
					{
						ref = (IURNContainerRef)obj;
						def = ref.getContDef();
					}
					else
						obj = null;
				}
				// pasting on wrong map. 
				if ((targetMap == null && obj instanceof ComponentRef) || (targetMap != null && obj instanceof ActorRef)) continue;

				if (obj==null) 
				{
					// was deleted since.
					obj = URNElementFinder.find(sourceUrn, id);
					// pasting on wrong map. 
					if ((targetMap == null && obj instanceof ComponentRef) || (targetMap != null && obj instanceof ActorRef)) continue;

					if (obj instanceof IURNContainerRef)
					{
						ref = (IURNContainerRef) obj;
						
						String oldDefinition = ((URNmodelElement)ref.getContDef()).getId();
						// this one is faster... searching for the definition.
						IURNContainer comp=null;
						if (ref instanceof ComponentRef)
							comp = URNElementFinder.findComponent(targetUrn, oldDefinition);
						else if (ref instanceof ActorRef)
							comp = URNElementFinder.findActor(targetUrn, oldDefinition);
						
						if (comp==null) {
							if (ref instanceof ComponentRef)
								comp = URNElementFinder.findComponent(sourceUrn, oldDefinition);
							else if (ref instanceof ActorRef)
								comp = URNElementFinder.findActor(sourceUrn, oldDefinition);

							comp = (IURNContainer) EcoreUtil.copy(comp); // clone it because it is used to create a new element.
							resetCloneId((URNmodelElement)comp);
						}
						def = comp;
					}
				}
				
				if (def!=null)
				{
					if (generateRefs) 
					{
						ModelCreationFactory factory=null;
						if (ref instanceof ComponentRef)
							factory = new ModelCreationFactory(targetUrn, ComponentRef.class, ((Component)def).getKind().getValue(), def);
						else if (ref instanceof ActorRef)
							factory = new ModelCreationFactory(targetUrn, ActorRef.class, def);
						
						if (factory!=null) 
						{
							IURNContainerRef newCompRef = (IURNContainerRef) factory.getNewObject();
							setNewContainerConstraints(newCompRef, ref, firstPlaced);
	
							if (results.size()==0) // leave null for first added, then set it. 
							{
								firstPlaced=ref;
							}
							results.add(newCompRef);
						}
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
	protected Comment getFirstComment()
	{
		Vector v=  getCommentList(1);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (Comment) v.get(0);
	}
	
	/***
	 * Special case for components.
	 * 
	 * @return the first ComponentRef in our list 
	 */
	protected IURNContainer getFirstContainer()
	{
		Vector v=  getContainerList(1, false);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (IURNContainer) v.get(0);
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
	
	
	protected EObject getFirstSimple()
	{
		Vector v=  getSimpleList(1);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (EObject) v.get(0);
	}

	protected EObject getFirstScenario()
	{
		Vector v=  getScenarioList(1);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (EObject) v.get(0);
	}
	
	protected EObject getFirstScenarioGroup()
	{
		Vector v=  getScenarioGroupList(1);
		if (v==null || v.size()==0) 
			return null;
		else 
			return (EObject) v.get(0);
	}
		
	protected Vector getSimpleList(int maxCount)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the old model. 
				Object obj = URNElementFinder.find(sourceUrn, id);
				
				if (obj instanceof IURNContainer || obj instanceof Responsibility || obj instanceof IURNDiagram)
				{
					results.add(obj);
				}
				if (results.size()>=maxCount && maxCount>0) break;
			}
			return results;
		}
		return null;
	}
	
	protected Vector getScenarioList(int maxCount)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the old model. 
				Object obj = URNElementFinder.find(sourceUrn, id);
				
				if ( obj instanceof ScenarioDef)
				{
					results.add(obj);
				} else if (obj instanceof ScenarioGroup)
				{
					for (int i=0;i<((ScenarioGroup)obj).getScenarios().size();i++)
						results.add(((ScenarioGroup)obj).getScenarios().get(i));
				}
				if (results.size()>=maxCount && maxCount>0) break;
			}
			return results;
		}
		return null;
	}
	
	protected Vector getScenarioGroupList(int maxCount)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				// search for the old id in the old model. 
				Object obj = URNElementFinder.find(sourceUrn, id);
				
				if ( obj instanceof ScenarioGroup)
				{
					results.add(obj);
				}
				if (results.size()>=maxCount && maxCount>0) break;
			}
			return results;
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
		
		
		// don't execute full command, just this portion. 
		if (cmd.canExecute()) {
			// chain it for the undo. 
			add(cmd);
			
			cmd.execute();
		}
	}
	private void resetCloneId(URNmodelElement clone) {
		String name = clone.getName();
		URNNamingHelper.setElementNameAndID(targetUrn, clone);
		clone.setName(name);
	}

	private void setNewCommentConstraints(Comment newComment, Comment oldComment, Comment offsetFrom)
	{
		if (cursorLocation!=null) 
		{
			int x=cursorLocation.x,y=cursorLocation.y;
			
			if (offsetFrom!=null)
			{
				x += oldComment.getX()-offsetFrom.getX();
				y += oldComment.getY()-offsetFrom.getY();
			}
			
			newComment.setX(x);
			newComment.setY(y);
		}
		
		if (oldComment!=null)
		{
			newComment.setWidth(oldComment.getWidth());
			newComment.setHeight(oldComment.getHeight());
		}
	}

	private void setNewContainerConstraints(IURNContainerRef newCompRef, IURNContainerRef oldCompRef, IURNContainerRef offsetFrom)
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
