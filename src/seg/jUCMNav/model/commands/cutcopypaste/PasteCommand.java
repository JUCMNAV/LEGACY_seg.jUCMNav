package seg.jUCMNav.model.commands.cutcopypaste;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

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
import seg.jUCMNav.editpolicies.layout.GrlGraphXYLayoutEditPolicy;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
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
import urncore.GRLmodelElement;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNmodelElement;

/***
 * This command pastes the selection from the clipboard onto the currently
 * selected model elements.
 * 
 * @author jkealey
 * 
 */
public class PasteCommand extends CompoundCommand
{
	// what are we pasting this on
	protected EObject insertionPoint;

	// if pasting on a node connection, we need to know it's middle point.
	// the cursor location is where the mouse is at
	protected Point nodeConnectionMiddle, cursorLocation;

	// the list of ids (string) of the selected elements
	protected List sourceIds;

	// a list of EditParts that were selected
	protected List sourceSelection;

	// the URN model when we did the copy
	protected URNspec sourceUrn;

	// if we are inserting on a diagram, this is either a UCMmap or a GRLGraph
	protected IURNDiagram targetDiagram;

	// convenience to avoid casting
	protected UCMmap targetMap;
	protected GRLGraph targetGraph;

	// the URN model where we are pasting
	protected URNspec targetUrn;

	// have we already executed the build() command (to avoid doing it twice)
	protected boolean alreadyBuilt;

	// the first element we pasted. used to know relative pasting positions for
	// the other elements.
	protected EObject firstPlaced;

	// the position of the first placed element.
	protected int firstPlacedX;
	protected int firstPlacedY;

	/***
	 * Pastes from the clipboard onto the insertion point.
	 * 
	 * @param insertionPoint
	 *            where we are pasting. can be a URNspec, IURNDiagram, PathNode,
	 *            NodeConnection, etc.
	 * @param targetUrn
	 *            the URN where we are pasting. (it contains insertionPoint)
	 * @param targetMap
	 *            the UCMmap or GRLGraph where we are pasting, if relevant.
	 * @param nodeConnectionMiddle
	 *            the middle of the node connection we are pasting on (otherwise
	 *            null)
	 * @param cursorLocation
	 *            the location of the mouse when we pasted.
	 */
	public PasteCommand(EObject insertionPoint, URNspec targetUrn, IURNDiagram targetMap, Point nodeConnectionMiddle, Point cursorLocation)
	{
		this.insertionPoint = insertionPoint;
		this.targetUrn = targetUrn;

		this.targetDiagram = targetMap;
		if (targetDiagram instanceof UCMmap)
			this.targetMap = (UCMmap) targetDiagram;
		else if (targetDiagram instanceof GRLGraph)
			this.targetGraph = (GRLGraph) targetDiagram;

		this.nodeConnectionMiddle = nodeConnectionMiddle;
		this.cursorLocation = cursorLocation;

		// what did we copy?
		Clipboard clip = Clipboard.getInstance();
		sourceIds = clip.getSelectedIds();
		sourceUrn = clip.getOriginalURNspec();
		sourceSelection = clip.getSelection();

		// we have not yet executed build()
		alreadyBuilt = false;
	}

	/***
	 * Adds the commands to this CompoundCommand
	 */
	public void build()
	{
		// already done - don't need to do anything.
		if (alreadyBuilt == true)
			return;

		// we have nowhere to insert - abort.
		if (insertionPoint == null)
			return;
		else
		{
			// Did we copy a pathnode? If we copied a RespRef, this will get the
			// first definition.
			Responsibility def = getFirstResponsibility();
			PathNode oldPn = getFirstPathNode();

			// this must only be executed when actually doing the initial
			// insert. otherwise create bogus items that are not used

			// only use the ModelCreationFactory for References. otherwise clone
			// or lose metadata, urnlinks, etc.
			ModelCreationFactory factory;
			PathNode newPathNode = null;

			// if we have a definition, it means we copied a RespRef.
			if (def != null)
			{
				// build me a new RespRef. we can use the factory because
				// RespRefs don't carry information - their def does.
				factory = new ModelCreationFactory(targetUrn, RespRef.class, def);
				newPathNode = (RespRef) factory.getNewObject();
			}
			else if (oldPn != null) // did we copy a pathnode?
			{
				// clone it but give it a new id.
				newPathNode = (PathNode) EcoreUtil.copy(oldPn);
				resetCloneId(newPathNode);

				// get rid of bindings because they are partially cloned.
				if (newPathNode instanceof Stub)
				{
					Stub stub = (Stub) newPathNode;
					stub.getBindings().clear();
				}
			}

			// do we have a pathnode to insert on a map
			if (newPathNode != null && targetMap != null)
			{
				// set its position.
				setNewNodePosition(newPathNode);

				// does this pathnode imply that we have to split a path.
				if (newPathNode instanceof AndFork || newPathNode instanceof OrFork || newPathNode instanceof AndJoin || newPathNode instanceof OrJoin || newPathNode instanceof Stub || newPathNode instanceof Timer)
				{
					buildDividePath(oldPn, newPathNode);
				}
				else
				{
					// simply inject the new node on the path.
					buildRegularSplice(oldPn, newPathNode);
				}

				// if we have a stub, we must copy its plugins.
				if (newPathNode instanceof Stub)
				{
					Stub newStub = (Stub) newPathNode;
					Stub oldStub = (Stub) oldPn;

					copyStubPlugins(newStub, oldStub);
				}
			}

			// if we have a grl graph and at least one GRL node to paste, do it.
			if (targetGraph != null && getFirstGRLNode() != null)
			{
				buildGrlNodes();
			}

			// if we have a UCM or GRL diagram, paste container refs and
			// comments.
			if (targetDiagram != null)
			{
				buildAllContainerRefs();

				buildAllComments();
			}

			// we also have elements that are inserted directly in the URNspec.
			if (targetUrn != null)
			{
				// simple elements to paste
				Vector list = getSimpleList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					// paste a new global elements.
					if (obj instanceof Responsibility)
					{
						Responsibility oldResp = (Responsibility) obj;
						buildResponsibility(oldResp);
					}
					else if (obj instanceof Component)
					{
						Component oldComponent = (Component) obj;
						buildComponent(oldComponent);
					}
					else if (obj instanceof Actor)
					{
						Actor oldActor = (Actor) obj;
						buildActor(oldActor);
					}
					else if (obj instanceof IURNDiagram)
					{
						IURNDiagram oldDiagram = (IURNDiagram) obj;
						buildDiagram(oldDiagram);
					}
					else
						System.out.println("TODO: Paste " + obj);
				}

				// paste scenarios onto a ScenarioGroup
				list = getScenarioList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					if (obj instanceof ScenarioDef && insertionPoint instanceof ScenarioGroup)
					{
						ScenarioDef oldScenario = (ScenarioDef) obj;
						buildScenario(oldScenario, (ScenarioGroup) insertionPoint);
					}
				}

				// paste scenario groups
				list = getScenarioGroupList(-1);
				for (Iterator iterator = list.iterator(); iterator.hasNext();)
				{
					EObject obj = (EObject) iterator.next();
					if (obj instanceof ScenarioGroup)
					{
						ScenarioGroup oldScenarioGroup = (ScenarioGroup) obj;
						buildScenarioGroup(oldScenarioGroup);
					}
				}
			}
		}

		// we've built it.
		alreadyBuilt = true;
	}

	/***
	 * Paste all GRL nodes.
	 */
	private void buildGrlNodes()
	{
		// get all the reference clones.
		Vector list = getClonedGrlNodeRefs();

		for (Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			EObject obj = (EObject) iterator.next();

			// insert into model.
			if (obj instanceof IntentionalElementRef)
			{
				add(GrlGraphXYLayoutEditPolicy.buildCreateGrlNodeCommand(targetGraph, (IntentionalElementRef) obj));
			}
			else if (obj instanceof Belief)
			{
				add(new AddBeliefCommand(targetGraph, (Belief) obj));
			}
			else if (obj instanceof KPIInformationElementRef)
			{
				add(GrlGraphXYLayoutEditPolicy.buildCreateKPIInformationElementCommand(targetGraph, (KPIInformationElementRef) obj));
			}
		}
	}

	/***
	 * Paste actors if no other actor exists with the same name.
	 * 
	 * @param oldActor
	 *            the actor to be pasted.
	 */
	private void buildActor(Actor oldActor)
	{
		if (URNElementFinder.findActorByName(targetUrn, oldActor.getName()) == null)
		{
			// clone and reset the id.
			Actor newActor = (Actor) EcoreUtil.copy(oldActor);
			resetCloneId(newActor);
			add(new CreateContainerCommand(targetUrn, newActor));
		}
	}

	/***
	 * Paste comments
	 */
	private void buildAllComments()
	{
		// get cloned comments, read to be added.
		Vector commentList = getClonedComments();
		for (Iterator iterator = commentList.iterator(); iterator.hasNext();)
		{
			Comment comment = (Comment) iterator.next();
			add(new AddCommentCommand(targetDiagram, comment));
		}
	}

	/***
	 * Paste container references.
	 */
	private void buildAllContainerRefs()
	{
		// get the reference list, cloned and ready to insert.
		Vector compRefList = getClonedContainerRefs();
		if (compRefList != null)
		{
			for (Iterator iterator = compRefList.iterator(); iterator.hasNext();)
			{
				// add it and position it.
				IURNContainerRef newCompRef = (IURNContainerRef) iterator.next();
				add(new AddContainerRefCommand(targetDiagram, newCompRef));
				add(new SetConstraintBoundContainerRefCompoundCommand(newCompRef, newCompRef.getX(), newCompRef.getY(), newCompRef.getWidth(), newCompRef.getHeight()));
			}
		}
	}

	/***
	 * Paste a component only if no other component shares the same name.
	 * 
	 * @param oldComponent
	 *            the component to be pasted.
	 */
	private void buildComponent(Component oldComponent)
	{
		if (URNElementFinder.findComponentByName(targetUrn, oldComponent.getName()) == null)
		{
			// clone and reset id
			Component newComponent = (Component) EcoreUtil.copy(oldComponent);
			resetCloneId(newComponent);
			add(new CreateContainerCommand(targetUrn, newComponent));
		}
	}

	/***
	 * Paste a diagram only if another one already exists with the same name
	 * (duplicate diagram).
	 * 
	 * @param oldDiagram
	 */
	private void buildDiagram(IURNDiagram oldDiagram)
	{
		String oldName = ((URNmodelElement) oldDiagram).getName();
		IURNDiagram toClone = URNElementFinder.findMapByName(targetUrn, oldName);
		if (toClone instanceof UCMmap)
		{
			add(new DuplicateMapCommand((UCMmap) toClone));
		}
		else if (toClone instanceof GRLGraph)
		{
			add(new DuplicateMapCommand((GRLGraph) toClone));
		}
	}

	/***
	 * Divide the path on insertionPoint and insert newPathNode. Use oldPn to
	 * determine which branches we had.
	 * 
	 * @param oldPn
	 *            the copied path node
	 * @param newPathNode
	 *            the path node ready to be inserted.
	 */
	private void buildDividePath(PathNode oldPn, PathNode newPathNode)
	{
		DividePathCommand cmd = null;
		if (insertionPoint instanceof EmptyPoint)
		{
			cmd = new DividePathCommand(newPathNode, (EmptyPoint) insertionPoint);
		}
		else if (insertionPoint instanceof DirectionArrow)
			cmd = new DividePathCommand(newPathNode, (DirectionArrow) insertionPoint);
		else if (insertionPoint instanceof NodeConnection)
			cmd = new DividePathCommand(newPathNode, (NodeConnection) insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y);
		// clone the branches.
		if (cmd != null && oldPn != null)
			cmd.cloneBranchesFrom(oldPn, targetMap);
		if (cmd != null)
			add(cmd);
	}

	/***
	 * Put newPathNode on the insertionPoint. Use oldPn as a reference to know
	 * what conditions to paste on outgoing Node Connections.
	 * 
	 * @param oldPn
	 *            the copied element.
	 * @param newPathNode
	 *            the pasted element.
	 */
	private void buildRegularSplice(PathNode oldPn, PathNode newPathNode)
	{
		Condition cond = null;
		if (oldPn.getSucc().size() > 0)
		{
			NodeConnection nc = (NodeConnection) oldPn.getSucc().get(0);
			cond = nc.getCondition();
		}

		Command cmd = null;
		if (insertionPoint instanceof PathNode)
			cmd = new ReplaceEmptyPointCommand((PathNode) insertionPoint, newPathNode, cond);
		else if (insertionPoint instanceof NodeConnection)
			cmd = new SplitLinkCommand(targetMap, newPathNode, (NodeConnection) insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y, cond);

		if (cmd != null)
			add(cmd);
	}

	/***
	 * Paste a responsibility only if no other responsibilitiy exists with the
	 * same name.
	 * 
	 * @param oldResp
	 *            the responsibility to be pasted.
	 */
	private void buildResponsibility(Responsibility oldResp)
	{
		if (URNElementFinder.findResponsibilityByName(targetUrn, oldResp.getName()) == null)
		{
			// clone and replace id.
			Responsibility newResp = (Responsibility) EcoreUtil.copy(oldResp);
			resetCloneId(newResp);
			add(new CreateResponsibilityCommand(targetUrn, newResp));
		}
	}

	/***
	 * Paste a copy of oldScenario into targetGroup
	 * 
	 * @param oldScenario
	 *            the copied scenario
	 * @param targetGroup
	 *            the target scenario group
	 */
	private void buildScenario(ScenarioDef oldScenario, ScenarioGroup targetGroup)
	{
		if (targetGroup != null)
		{

			// create a shallow copy to break references to stuff we don't know
			// exists.
			ScenarioDef newScenario = (ScenarioDef) EcoreUtil.copy(oldScenario);
			resetCloneId(newScenario);

			// would include a Scenario(Start|End)Point but no related
			// StartPoint/EndPoint
			newScenario.getStartPoints().clear();
			newScenario.getEndPoints().clear();

			// re-use the duplicate command to simplify our task of inserting
			// the child elements.
			// this will create a duplicate of newScenario. We can't use
			// oldScenario here because of the relationships that need to be
			// broken during the clone.
			DuplicateCommand duplicateCommand = new DuplicateCommand(newScenario, targetGroup, targetUrn);
			add(duplicateCommand);

			// the duplicated scenario.
			ScenarioDef duplicate = (ScenarioDef) duplicateCommand.getDuplicate();

			// copy over the variable initializations if the variable exists.
			for (int i = 0; i < oldScenario.getInitializations().size(); i++)
			{
				Initialization init = (Initialization) oldScenario.getInitializations().get(i);
				Variable var = URNElementFinder.findVariable(targetUrn, init.getVariable().getId());
				if (var != null)
				{
					add(new CreateVariableInitializationCommand(var, duplicate, init.getValue()));
				}
			}

			// if we can find the start points using their ID, recreate the
			// ScenarioStartPoints.
			for (int i = 0; i < oldScenario.getStartPoints().size(); i++)
			{
				ScenarioStartPoint start = (ScenarioStartPoint) oldScenario.getStartPoints().get(i);
				Object pn = URNElementFinder.find(targetUrn, start.getStartPoint().getId());
				if (pn != null && pn instanceof StartPoint)
				{
					IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
					inc.setClone(start);
					add(inc);
				}
			}

			// if we can find the end points using their ID, recreate the
			// ScenarioEndPoints
			for (int i = 0; i < oldScenario.getEndPoints().size(); i++)
			{
				ScenarioEndPoint end = (ScenarioEndPoint) oldScenario.getEndPoints().get(i);
				Object pn = URNElementFinder.find(targetUrn, end.getEndPoint().getId());
				if (pn != null && pn instanceof EndPoint)
				{
					IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
					inc.setClone(end);
					add(inc);
				}
			}

			// if we can find the included scenario using its ID, create it.
			for (int i = 0; i < oldScenario.getIncludedScenarios().size(); i++)
			{
				ScenarioDef oldChild = (ScenarioDef) oldScenario.getIncludedScenarios().get(i);
				ScenarioDef newChild = URNElementFinder.findScenario(targetUrn, oldChild.getId());
				if (newChild != null)
				{
					IncludeScenarioCommand inc = new IncludeScenarioCommand(duplicate, newChild, true);
					add(inc);
				}
			}
		}
	}

	/***
	 * Paste a scenario group
	 * 
	 * @param oldScenarioGroup
	 *            the old scenario group.
	 */
	private void buildScenarioGroup(ScenarioGroup oldScenarioGroup)
	{
		if (insertionPoint instanceof URNspec)
		{
			ScenarioGroup newScenarioGroup = (ScenarioGroup) EcoreUtil.copy(oldScenarioGroup);
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

	/***
	 * Can we execute this command. Must have something to paste on an
	 * appropriate insertionPoint
	 */
	public boolean canExecute()
	{
		boolean found = insertionPoint != null;

		if (found)
		{
			// we've selected something that is copiable.
			found = (insertionPoint instanceof NodeConnection || insertionPoint instanceof EmptyPoint || insertionPoint instanceof DirectionArrow) && targetMap != null && (getFirstResponsibility() != null || getFirstPathNode() != null);
			if (found)
				return true;

			found = insertionPoint instanceof IURNDiagram && (getFirstContainer() != null);
			if (found)
				return true;

			found = insertionPoint instanceof IURNDiagram && getFirstComment() != null;
			if (found)
				return true;

			found = insertionPoint instanceof GRLGraph && getFirstGRLNode() != null;
			if (found)
				return true;

			found = insertionPoint instanceof ScenarioGroup && getFirstScenario() != null;
			if (found)
				return true;

			found = insertionPoint instanceof URNspec && getFirstScenarioGroup() != null;
			if (found)
				return true;

			found = insertionPoint instanceof URNspec && getFirstSimple() != null;
			return found;
		}
		else
			return false;
	}

	/***
	 * Copy the stub plugins. Will recreate the plugin if a map with the same
	 * name exists in the target.
	 * 
	 * @param newStub
	 *            the pasted stub.
	 * @param oldStub
	 *            the original stub.
	 */
	private void copyStubPlugins(Stub newStub, Stub oldStub)
	{
		for (int i = 0; i < oldStub.getBindings().size(); i++)
		{
			PluginBinding binding = (PluginBinding) oldStub.getBindings().get(i);
			UCMmap oldMap = binding.getPlugin();
			UCMmap map = (UCMmap) URNElementFinder.findMap(targetUrn, oldMap.getId());
			if (map == null)
			{
				map = (UCMmap) URNElementFinder.findMapByName(targetUrn, oldMap.getName());
			}
			if (map != null && map != targetMap) // don't allow plugin to self.
			{
				Condition condition = (Condition) EcoreUtil.copy(binding.getPrecondition());
				add(new AddPluginCommand(newStub, map, condition));
			}
		}
	}

	/***
	 * build & execute.
	 */
	public void execute()
	{
		build();
		super.execute();
		postBuild();
	}

	/***
	 * Returns the list of cloned comments.
	 * 
	 * @return a Vector<Comment>
	 */
	protected Vector getClonedComments()
	{
		return getCommentList(-1);
	}

	/***
	 * Special case for containers. Builds a list of containers refs that are
	 * ready to be inserted.
	 * 
	 * @return a list of containers refs
	 */
	protected Vector getClonedContainerRefs()
	{
		return getContainerList(-1, true);
	}

	/***
	 * Get's the cloned GRL nodes.
	 * 
	 * @return a Vector<IURNNode>
	 */
	protected Vector getClonedGrlNodeRefs()
	{
		return getGRLNodeList(-1, true);
	}

	/***
	 * Returns the comment list.
	 * 
	 * @param maxCount
	 *            maximum number of elements to return.
	 * @return a Vector<Comment>
	 */
	protected Vector getCommentList(int maxCount)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();
			for (Iterator iterator = sourceSelection.iterator(); iterator.hasNext();)
			{
				Object object = (Object) iterator.next();
				if (object instanceof CommentEditPart)
				{
					Comment oldComment = (Comment) ((CommentEditPart) object).getModel();
					Comment newComment = (Comment) EcoreUtil.copy(oldComment);

					setNewCommentConstraints(newComment, oldComment);

					if (firstPlaced == null) // leave null for first added, then
												// set it.
					{
						firstPlaced = oldComment;
						firstPlacedX = oldComment.getX();
						firstPlacedY = oldComment.getY();
					}

					results.add(newComment);
				}
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}
			return results;
		}
		return null;
	}

	/**
	 * Builds a list of elements that are ready to be inserted on a diagram.
	 * 
	 * @param maxCount
	 *            the maximum number of answers. limit the number for faster
	 *            checks.
	 * @param generateRefs
	 *            if true, will return a new ref - otherwise returns the
	 *            original def
	 * 
	 * @return a list of containers refs/containers
	 */
	protected Vector getContainerList(int maxCount, boolean generateRefs)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();

			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				IURNContainer def = null;
				IURNContainerRef ref = null;
				Object obj = URNElementFinder.find(sourceUrn, id);
				// pasting on wrong map.
				if ((targetMap == null && obj instanceof ComponentRef) || (targetMap != null && obj instanceof ActorRef))
					continue;

				if (obj instanceof IURNContainerRef)
				{
					ref = (IURNContainerRef) obj;
					IURNContainer comp = null;

					// can we find the definition by name in the new model.
					String oldDefinition = ((URNmodelElement) ref.getContDef()).getName();
					if (ref instanceof ComponentRef)
						comp = URNElementFinder.findComponentByName(targetUrn, oldDefinition);
					else if (ref instanceof ActorRef)
						comp = URNElementFinder.findActorByName(targetUrn, oldDefinition);

					// can't find it.
					if (comp == null)
					{
						// look in the old model and clone it.
						if (ref instanceof ComponentRef)
							comp = URNElementFinder.findComponentByName(sourceUrn, oldDefinition);
						else if (ref instanceof ActorRef)
							comp = URNElementFinder.findActorByName(sourceUrn, oldDefinition);

						comp = (IURNContainer) EcoreUtil.copy(comp); // clone it
																		// because
																		// it is
																		// used
																		// to
																		// create
																		// a new
																		// element.
						resetCloneId((URNmodelElement) comp);
					}
					def = comp;
				}

				if (def != null)
				{
					if (generateRefs)
					{
						// we can use the factory because refs don't contain
						// data, the defs do.
						ModelCreationFactory factory = null;
						if (ref instanceof ComponentRef)
							factory = new ModelCreationFactory(targetUrn, ComponentRef.class, ((Component) def).getKind().getValue(), def);
						else if (ref instanceof ActorRef)
							factory = new ModelCreationFactory(targetUrn, ActorRef.class, def);

						if (factory != null)
						{
							IURNContainerRef newCompRef = (IURNContainerRef) factory.getNewObject();
							// place it on th emap.
							setNewContainerConstraints(newCompRef, ref);

							if (firstPlaced == null) // leave null for first
														// added, then set it.
							{
								firstPlaced = ref;
								firstPlacedX = ref.getX();
								firstPlacedY = ref.getY();
							}
							results.add(newCompRef);
						}
					}
					else
					{
						results.add(def);
					}
				}
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}

			return results;
		}
		return null;
	}

	/**
	 * Builds a list of elements that are ready to be inserted on a diagram.
	 * 
	 * @param maxCount
	 *            the maximum number of answers. limit the number for faster
	 *            checks.
	 * @param generateRefs
	 *            if true, will return a new ref - otherwise returns the
	 *            original def
	 * 
	 * @return a list of refs/defs
	 */
	protected Vector getGRLNodeList(int maxCount, boolean generateRefs)
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			Vector results = new Vector();

			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				GRLmodelElement def = null;
				GRLNode ref = null;
				Object obj = URNElementFinder.find(sourceUrn, id);
				// pasting on wrong graph.
				if (targetGraph == null && obj instanceof GRLNode)
					continue;

				if (obj instanceof IntentionalElementRef)
				{
					IntentionalElementRef intentionalElementRef = (IntentionalElementRef) obj;
					IntentionalElement intentionalElement = null;

					String oldDefinition = ((URNmodelElement) intentionalElementRef.getDef()).getName();

					intentionalElement = URNElementFinder.findIntentionalElementByName(targetUrn, oldDefinition);

					if (intentionalElement == null)
					{
						intentionalElement = URNElementFinder.findIntentionalElementByName(sourceUrn, oldDefinition);

						IntentionalElement oldElement = intentionalElement;
						intentionalElement = (IntentionalElement) EcoreUtil.copy(intentionalElement); // clone
																										// it
																										// because
																										// it
																										// is
																										// used
																										// to
																										// create
																										// a
																										// new
																										// element.
						resetCloneId(intentionalElement);
					}

					def = intentionalElement;
					ref = intentionalElementRef;

				}
				else if (obj instanceof Belief)
				{
					def = null;
					ref = (Belief) obj;
					if (generateRefs)
					{
						Belief newNode = (Belief) EcoreUtil.copy(ref);
						setNewGRLConstraints(newNode, ref);

						if (firstPlaced == null) // leave null for first added,
													// then set it.
						{
							firstPlaced = ref;
							firstPlacedX = ref.getX();
							firstPlacedY = ref.getY();
						}
						results.add(newNode);

					}
					else
						results.add(ref);
				}
				else if (obj instanceof KPIInformationElementRef)
				{
					KPIInformationElementRef informationElementRef = (KPIInformationElementRef) obj;
					KPIInformationElement informationElement = null;

					String oldDefinition = ((URNmodelElement) informationElementRef.getDef()).getName();

					informationElement = URNElementFinder.findKPIInformationElementByName(targetUrn, oldDefinition);

					if (informationElement == null)
					{
						informationElement = URNElementFinder.findKPIInformationElementByName(sourceUrn, oldDefinition);

						informationElement = (KPIInformationElement) EcoreUtil.copy(informationElement); // clone
																											// it
																											// because
																											// it
																											// is
																											// used
																											// to
																											// create
																											// a
																											// new
																											// element.
						resetCloneId(informationElement);
					}

					def = informationElement;
					ref = informationElementRef;
				}

				if (def != null)
				{
					if (generateRefs)
					{
						ModelCreationFactory factory = null;
						if (ref instanceof IntentionalElementRef)
							factory = new ModelCreationFactory(targetUrn, IntentionalElementRef.class, ((IntentionalElement) def).getType().getValue(), def);
						else if (ref instanceof KPIInformationElementRef)
							factory = new ModelCreationFactory(targetUrn, KPIInformationElementRef.class, def);

						if (factory != null)
						{
							GRLNode newNode = (GRLNode) factory.getNewObject();
							setNewGRLConstraints(newNode, ref);

							if (firstPlaced == null) // leave null for first
														// added, then set it.
							{
								firstPlaced = ref;
								firstPlacedX = ref.getX();
								firstPlacedY = ref.getY();
							}
							results.add(newNode);
						}
					}
					else
					{
						results.add(def);
					}
				}
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}

			return results;
		}
		return null;
	}

	/***
	 * The first comment.
	 * 
	 * @return returns the first copied comment.
	 */
	protected Comment getFirstComment()
	{
		Vector v = getCommentList(1);
		if (v == null || v.size() == 0)
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
		Vector v = getContainerList(1, false);
		if (v == null || v.size() == 0)
			return null;
		else
			return (IURNContainer) v.get(0);
	}

	/***
	 * Returns the first GRLmodelElement that can be found.
	 * 
	 * @return the first one to be found.
	 */
	protected GRLmodelElement getFirstGRLNode()
	{
		Vector v = getGRLNodeList(1, false);
		if (v == null || v.size() == 0)
			return null;
		else
			return (GRLmodelElement) v.get(0);
	}

	/***
	 * Returns the first PathNode that can be found.
	 * 
	 * @return the first one to be found
	 */
	protected PathNode getFirstPathNode()
	{
		if (sourceIds != null && sourceUrn != null && targetUrn != null)
		{
			for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();)
			{
				String id = iterator.next().toString();

				Object obj = URNElementFinder.find(sourceUrn, id);
				if (IsPastablePathNode(obj))
				{
					PathNode oldPn = (PathNode) obj;
					return oldPn; // pn from old urn
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

				Object obj = URNElementFinder.find(sourceUrn, id);
				if (obj instanceof RespRef)
				{
					String oldDefinition = ((RespRef) obj).getRespDef().getName();
					// this one is faster... searching for the definition.
					Responsibility resp = URNElementFinder.findResponsibilityByName(targetUrn, oldDefinition);
					if (resp == null)
					{
						resp = URNElementFinder.findResponsibilityByName(sourceUrn, oldDefinition);
						resp = (Responsibility) EcoreUtil.copy(resp); // clone
																		// it
																		// because
																		// it is
																		// used
																		// to
																		// create
																		// a new
																		// element.
						resetCloneId(resp);
					}
					return resp;
				}
			}
		}
		return null;
	}

	/***
	 * Returns the first simple element (definitions, etc.) to be found.
	 * 
	 * @return the first one to be found.
	 */
	protected EObject getFirstSimple()
	{
		Vector v = getSimpleList(1);
		if (v == null || v.size() == 0)
			return null;
		else
			return (EObject) v.get(0);
	}

	/***
	 * Returns the first Scenario to be found.
	 * 
	 * @return the first one to be found.
	 */
	protected EObject getFirstScenario()
	{
		Vector v = getScenarioList(1);
		if (v == null || v.size() == 0)
			return null;
		else
			return (EObject) v.get(0);
	}

	/***
	 * Returns the first ScenarioGroup to be found.
	 * 
	 * @return the first one to be found.
	 */
	protected EObject getFirstScenarioGroup()
	{
		Vector v = getScenarioGroupList(1);
		if (v == null || v.size() == 0)
			return null;
		else
			return (EObject) v.get(0);
	}

	/***
	 * Returns the list of simple elements (definitions and diagrams)
	 * 
	 * @param maxCount
	 *            the maximum number of returned results
	 * @return a Vector<EObject>
	 */
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
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}
			return results;
		}
		return null;
	}

	/***
	 * Returns the list of copied scenarios.
	 * 
	 * @param maxCount
	 *            the maximum number of scenarios to be returned.
	 * @return a Vector<ScenarioDe>
	 */
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

				if (obj instanceof ScenarioDef)
				{
					results.add(obj);
				}
				else if (obj instanceof ScenarioGroup)
				{
					for (int i = 0; i < ((ScenarioGroup) obj).getScenarios().size(); i++)
						results.add(((ScenarioGroup) obj).getScenarios().get(i));
				}
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}
			return results;
		}
		return null;
	}

	/***
	 * Returns the list of copied scenario groups.
	 * 
	 * @param maxCount
	 *            the maximum number of scenario groups to return
	 * @return a Vector<ScenarioGroup>
	 */
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

				if (obj instanceof ScenarioGroup)
				{
					results.add(obj);
				}
				if (results.size() >= maxCount && maxCount > 0)
					break;
			}
			return results;
		}
		return null;
	}

	/***
	 * Is this a pathnode that we can paste?
	 * 
	 * @param obj
	 *            a PathNode
	 * @return can we paste this?
	 */
	protected boolean IsPastablePathNode(Object obj)
	{
		return obj instanceof PathNode && !(obj instanceof EndPoint) && !(obj instanceof StartPoint) && !(obj instanceof Connect);
	}

	/***
	 * Add comments after the execute of the paste. This helps resolve syntax
	 * errors that we cannot determine in advance.
	 */
	protected void postBuild()
	{
		Vector errors = SyntaxChecker.verifySyntax(targetUrn);
		Vector toBeAdded = new Vector(); // variables to create.
		for (Iterator iterator = errors.iterator(); iterator.hasNext();)
		{
			TraversalWarning warning = (TraversalWarning) iterator.next();
			if (warning.getExpression() != null)
			{
				for (int i = 0; i < sourceUrn.getUcmspec().getVariables().size(); i++)
				{
					Variable var = (Variable) sourceUrn.getUcmspec().getVariables().get(i);
					if (warning.getExpression().toLowerCase().indexOf(var.getName().toLowerCase()) >= 0)
					{
						if (!toBeAdded.contains(var) && !URNNamingHelper.doesVariableNameExist(targetUrn, var.getName()))
							toBeAdded.add(var);
					}
				}
			}
		}

		CompoundCommand cmd = new CompoundCommand();
		HashMap createdEnumerations = new HashMap();
		for (int i = 0; i < toBeAdded.size(); i++)
		{
			Variable var = (Variable) toBeAdded.get(i);
			Variable newVar = (Variable) EcoreUtil.copy(var);
			resetCloneId(newVar);
			CreateVariableCommand createVar = new CreateVariableCommand(targetUrn, newVar);

			if (var.getType() != null && var.getType().equals(ScenarioUtils.sTypeEnumeration))
			{

				EnumerationType newType = null;
				for (int j = 0; j < targetUrn.getUcmspec().getEnumerationTypes().size(); j++)
				{
					EnumerationType type = (EnumerationType) targetUrn.getUcmspec().getEnumerationTypes().get(j);
					if (type.getName().equals(var.getEnumerationType().getName()))
						newType = type;
				}
				// we need to create the enumeration type.
				if (newType == null)
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
		if (cmd.canExecute())
		{
			// chain it for the undo.
			add(cmd);

			cmd.execute();
		}
	}

	/***
	 * Given an element, give it a new ID.
	 * 
	 * @param clone
	 *            the element to reset
	 */
	private void resetCloneId(URNmodelElement clone)
	{
		String name = clone.getName();
		URNNamingHelper.setElementNameAndID(targetUrn, clone);
		clone.setName(name);
	}

	/***
	 * Position this element on the diagram. Can either paste at cursor location
	 * or be relative to previously pasted elements.
	 * 
	 * @param newComment
	 *            the pasted element
	 * @param oldComment
	 *            the original element.
	 */
	private void setNewCommentConstraints(Comment newComment, Comment oldComment)
	{
		if (cursorLocation != null)
		{
			int x = cursorLocation.x, y = cursorLocation.y;

			if (firstPlaced != null)
			{
				x += oldComment.getX() - firstPlacedX;
				y += oldComment.getY() - firstPlacedY;
			}

			newComment.setX(x);
			newComment.setY(y);
		}

		if (oldComment != null)
		{
			newComment.setWidth(oldComment.getWidth());
			newComment.setHeight(oldComment.getHeight());
		}
	}

	/***
	 * Position this element on the diagram. Can either paste at cursor location
	 * or be relative to previously pasted elements.
	 * 
	 * @param newCompRef
	 *            the pasted element
	 * @param oldCompRef
	 *            the original element
	 */
	private void setNewContainerConstraints(IURNContainerRef newCompRef, IURNContainerRef oldCompRef)
	{
		if (cursorLocation != null)
		{
			int x = cursorLocation.x, y = cursorLocation.y;

			if (firstPlaced != null)
			{
				x += oldCompRef.getX() - firstPlacedX;
				y += oldCompRef.getY() - firstPlacedY;
			}

			newCompRef.setX(x);
			newCompRef.setY(y);
		}

		if (oldCompRef != null)
		{
			newCompRef.setWidth(oldCompRef.getWidth());
			newCompRef.setHeight(oldCompRef.getHeight());
		}
	}

	/***
	 * Position this element on the diagram. Can either paste at cursor location
	 * or be relative to previously pasted elements.
	 * 
	 * @param newNode
	 *            the pasted node
	 * @param oldNode
	 *            the copied node
	 */
	private void setNewGRLConstraints(GRLNode newNode, GRLNode oldNode)
	{
		if (cursorLocation != null)
		{
			int x = cursorLocation.x, y = cursorLocation.y;

			if (firstPlaced != null)
			{
				x += oldNode.getX() - firstPlacedX;
				y += oldNode.getY() - firstPlacedY;
			}

			newNode.setX(x);
			newNode.setY(y);
		}
	}

	/***
	 * Position this element on the diagram relative to the insertionPoint.
	 * 
	 * @param newPathNode
	 *            the newly created element.
	 */
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
