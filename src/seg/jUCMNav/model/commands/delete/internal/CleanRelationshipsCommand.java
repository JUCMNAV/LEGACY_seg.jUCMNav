package seg.jUCMNav.model.commands.delete.internal;

import grl.ActorRef;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.delete.DeleteBindingsCommand;
import seg.jUCMNav.model.commands.delete.DeleteScenarioPathNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteVariableCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNlink;

/**
 * Removes all invisible relationships related to a Connection, Node or
 * ContainerRef, .
 * 
 * Performance information, Strategy information, Plugin-Binding information,
 * ContainerRef Binding information.
 * 
 * @author jkealey
 * 
 */
public class CleanRelationshipsCommand extends CompoundCommand {

	private EObject element;

	/**
	 * 
	 * @param map
	 *            the Map to be cleaned.
	 */
	public CleanRelationshipsCommand(UCMmap map) {
		this.element = map;
	}

	/**
	 * 
	 * @param map
	 *            the Map to be cleaned
	 */
	private void build(UCMmap map) {
		add(new RemoveLinkedInfoCommand(map));
		add(new DeleteBindingsCommand(map));
		// Delete URNLinks
		for (Iterator it = map.getToLinks().iterator(); it.hasNext();) {
			URNlink link = (URNlink) it.next();
			add(new DeleteURNlinkCommand(link));
		}
		for (Iterator it = map.getFromLinks().iterator(); it.hasNext();) {
			URNlink link = (URNlink) it.next();
			add(new DeleteURNlinkCommand(link));
		}
	}

	/**
	 * 
	 * @param nc
	 *            the NodeConnection to be cleaned.
	 */
	public CleanRelationshipsCommand(NodeConnection nc) {
		this.element = nc;
	}

	/**
	 * 
	 * @param var
	 *            the Variable to be cleaned.
	 */
	public CleanRelationshipsCommand(Variable var) {
		this.element = var;
	}

	/**
	 * 
	 * @param et
	 *            the EnumerationType to be cleaned.
	 */
	public CleanRelationshipsCommand(EnumerationType et) {
		this.element = et;
	}

	
	/**
	 * 
	 * @param init
	 *            the Initialization to be cleaned.
	 */
	public CleanRelationshipsCommand(Initialization init) {
		this.element = init;
	}
	/**
	 * 
	 * @param pt
	 *            the ScenarioStartPoint to be cleaned.
	 */
	public CleanRelationshipsCommand(ScenarioStartPoint pt) {
		this.element = pt;
	}

	/**
	 * 
	 * @param pt
	 *            the ScenarioEndPoint to be cleaned.
	 */
	public CleanRelationshipsCommand(ScenarioEndPoint pt) {
		this.element = pt;
	}

	/**
	 * 
	 * @param nc
	 *            the NodeConnection to be cleaned.
	 */
	private void build(NodeConnection nc) {
		add(new RemoveLinkedInfoCommand(nc));
		add(new DeleteBindingsCommand(nc));
	}

	/**
	 * 
	 * @param pn
	 *            the PathNode to be cleaned.
	 */
	public CleanRelationshipsCommand(PathNode pn) {
		this.element = pn;
	}

	/**
	 * 
	 * @param pn
	 *            the PathNode to be cleaned.
	 */
	private void build(PathNode pn) {
		add(new RemoveLinkedInfoCommand(pn));

		// get rid of conditions on outgoing connections
		for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
			NodeConnection element = (NodeConnection) iter.next();
			add(new RemoveLinkedInfoCommand(element));
		}
		if (pn.getContRef() != null)
			add(new ContainerRefUnbindChildCommand((ComponentRef) pn.getContRef(), pn));

		if (pn instanceof StartPoint) {
			for (Iterator iter = ((StartPoint) pn).getScenarioStartPoints().iterator(); iter.hasNext();) {
				ScenarioStartPoint pt = (ScenarioStartPoint) iter.next();
				add(new DeleteScenarioPathNodeCommand(pt));
			}
		}
		if (pn instanceof EndPoint) {
			for (Iterator iter = ((EndPoint) pn).getScenarioEndPoints().iterator(); iter.hasNext();) {
				ScenarioEndPoint pt = (ScenarioEndPoint) iter.next();
				add(new DeleteScenarioPathNodeCommand(pt));
			}
		}

		add(new DeleteBindingsCommand(pn));
	}

	/**
	 * 
	 * @param cr
	 *            the ComponentRef to be cleaned.
	 */
	public CleanRelationshipsCommand(ComponentRef cr) {
		this.element = cr;

	}

	/**
	 * 
	 * @param cr
	 *            the ComponentRef to be cleaned.
	 */
	private void build(ComponentRef cr) {
		add(new RemoveLinkedInfoCommand(cr));
		if (cr.getChildren().size() > 0)
			add(new ContainerRefUnbindChildCommand(cr, cr.getChildren()));

		if (cr.getNodes().size() > 0)
			add(new ContainerRefUnbindChildCommand(cr, cr.getNodes()));

		if (cr.getParent() != null)
			add(new ContainerRefUnbindChildCommand((ComponentRef) cr.getParent(), cr));
	}

	/**
	 * 
	 * @param graph
	 *            the graph to be cleaned.
	 */
	public CleanRelationshipsCommand(GRLGraph graph) {
		this.element = graph;
	}

	/**
	 * 
	 * @param graph
	 *            the diagram to be cleaned
	 */
	private void build(GRLGraph graph) {
		add(new RemoveLinkedInfoCommand(graph));
	}

	/**
	 * 
	 * @param cr
	 *            the actor to be cleaned.
	 */
	public CleanRelationshipsCommand(ActorRef cr) {
		this.element = cr;

	}

	/**
	 * 
	 * @param cr
	 *            the actor to be cleaned.
	 */
	private void build(ActorRef cr) {
		if (cr.getChildren().size() > 0)
			add(new ContainerRefUnbindChildCommand(cr, cr.getChildren()));

		if (cr.getNodes().size() > 0)
			add(new ContainerRefUnbindChildCommand(cr, cr.getNodes()));

		if (cr.getParent() != null)
			add(new ContainerRefUnbindChildCommand((ActorRef) cr.getParent(), cr));
	}

	/**
	 * 
	 * @param ref
	 *            the GRLNode to be cleaned.
	 */
	public CleanRelationshipsCommand(GRLNode ref) {
		this.element = ref;

	}

	/**
	 * 
	 * @param ref
	 *            the GRLNode to be cleaned.
	 */
	private void build(GRLNode ref) {
		if (ref.getContRef() != null) {
			add(new ContainerRefUnbindChildCommand((ActorRef) ref.getContRef(), ref));
		}
	}

	/**
	 * 
	 * @param var
	 *            the Variable to be cleaned.
	 */
	private void build(Variable var) {
		// Sadly, metamodel sets Variable - Initialization link as unidirectional. 
		// Delete Variable initializations
		for (Iterator iter = var.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
			ScenarioGroup group = (ScenarioGroup) iter.next();
			for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
				ScenarioDef scenario = (ScenarioDef) iterator.next();
				for (Iterator iterator2 = scenario.getInitializations().iterator(); iterator2.hasNext();) {
					Initialization init = (Initialization) iterator2.next();
					if (init.getVariable()==var)
						build(init);
				}
			}
		}

	}
	
	/**
	 * 
	 * @param et
	 *            the EnumerationType to be cleaned.
	 */
	private void build(EnumerationType et) {

		for (Iterator iter = et.getInstances().iterator(); iter.hasNext();) {
			Variable var = (Variable) iter.next();
			add(new DeleteVariableCommand(var));
		}
	}	

	/**
	 * 
	 * @param init
	 *            the Variable Initialization to be cleaned.
	 */
	private void build(Initialization init) {
		add(new RemoveLinkedInfoCommand(init));
	}

	
	/**
	 * 
	 * @param pt
	 *            the ScenarioStartPoint to be cleaned.
	 */
	private void build(ScenarioStartPoint pt) {
		add(new RemoveLinkedInfoCommand(pt));
	}

	/**
	 * 
	 * @param pt
	 *            the ScenarioEndPoint to be cleaned.
	 */
	private void build(ScenarioEndPoint pt) {
		add(new RemoveLinkedInfoCommand(pt));
	}

	/**
	 * Returns true even if no commands exist.
	 */
	public boolean canExecute() {
		if (getCommands().size() == 0)
			return true;
		else
			return super.canExecute();
	}

	/**
	 * Returns true even if no commands exist.
	 */
	public boolean canUndo() {
		if (getCommands().size() == 0)
			return true;
		else
			return super.canUndo();
	}

	/**
	 * Builds command as late as possible.
	 */
	public void execute() {
		build();
		super.execute();
	}

	/**
	 * redirects to the appropriate build method.
	 */
	private void build() {
		if (element instanceof UCMmap)
			build((UCMmap) element);
		else if (element instanceof ComponentRef)
			build((ComponentRef) element);
		else if (element instanceof ActorRef)
			build((ActorRef) element);
		else if (element instanceof NodeConnection)
			build((NodeConnection) element);
		else if (element instanceof PathNode)
			build((PathNode) element);
		else if (element instanceof IntentionalElementRef)
			build((IntentionalElementRef) element);
		else if (element instanceof Variable)
			build((Variable) element);
		else if (element instanceof ScenarioStartPoint)
			build((ScenarioStartPoint) element);
		else if (element instanceof ScenarioEndPoint)
			build((ScenarioEndPoint) element);
		else if (element instanceof Initialization)
			build((Initialization) element);
		else if (element instanceof EnumerationType)
			build((EnumerationType) element);		

	}

}
