package seg.jUCMNav.model.commands.delete.internal;

import grl.Actor;
import grl.ActorRef;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.UCMspec;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urncore.ComponentElement;
import urncore.Condition;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Given a IURNNode or IURNContainerRef, remove it from the map or grlgraph, the
 * component ref binding hierarchy and disconnect it from its definition if it
 * is a responsibility or an intentionalElement.
 * 
 * @author jkealey
 * 
 */
public class RemoveURNmodelElementCommand extends Command implements JUCMNavCommand {
	private EObject element;
	private IURNContainerRef parent;
	private IURNDiagram diagram;
	private URNmodelElement definition;
	private UCMspec ucmspec;
	private boolean aborted = false;
	private ScenarioDef scenario;
	private boolean isPreCondition;

	/**
	 * 
	 * @param pn
	 *            the PathNode to be deleted.
	 */
	public RemoveURNmodelElementCommand(PathNode pn) {
		this.element = pn;
	}

	/**
	 * 
	 * @param cr
	 *            the ComponentRef to be deleted.
	 */
	public RemoveURNmodelElementCommand(ComponentRef cr) {
		this.element = cr;
	}

	/**
	 * 
	 * @param var
	 *            the Variable to be deleted.
	 */
	public RemoveURNmodelElementCommand(Variable var) {
		this.element = var;
	}
	
	/**
	 * 
	 * @param init
	 *            the Initialization to be deleted.
	 */
	public RemoveURNmodelElementCommand(Initialization init) {
		this.element = init;
	}

	/**
	 * 
	 * @param var
	 *            the ScenarioStartPoint to be deleted.
	 */
	public RemoveURNmodelElementCommand(ScenarioStartPoint var) {
		this.element = var;
	}

	/**
	 * 
	 * @param var
	 *            the ScenarioEndPoint to be deleted.
	 */
	public RemoveURNmodelElementCommand(ScenarioEndPoint var) {
		this.element = var;
	}

	/**
	 * 
	 * @param ar
	 *            the ActorRef to be deleted.
	 */
	public RemoveURNmodelElementCommand(ActorRef ar) {
		this.element = ar;
	}

	/**
	 * 
	 * @param cond
	 *            the scenario Pre/Post condition to be deleted.
	 */
	public RemoveURNmodelElementCommand(Condition cond) {
		this.element = cond;
	}
	
	/**
	 * 
	 * @param node
	 *            the GRLNode to be deleted.
	 */
	public RemoveURNmodelElementCommand(GRLNode node) {
		this.element = node;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (element instanceof IURNNode) {
			IURNNode node = (IURNNode) element;
			aborted = node.getDiagram() == null;
			if (aborted)
				return;
			diagram = (IURNDiagram) node.getDiagram();
			parent = (IURNContainerRef) node.getContRef();
			if (node instanceof RespRef) {
				RespRef ref = (RespRef) node;
				definition = ref.getRespDef();
			} else if (node instanceof IntentionalElementRef) {
				IntentionalElementRef ref = (IntentionalElementRef) node;
				definition = ref.getDef();
			}

		} else if (element instanceof IURNContainerRef) {
			IURNContainerRef ref = (IURNContainerRef) element;
			diagram = (IURNDiagram) ref.getDiagram();
			aborted = diagram == null;
			if (aborted)
				return;
			parent = (IURNContainerRef) ref.getParent();
			if (ref.getContDef() instanceof ComponentElement) {
				definition = (ComponentElement) ref.getContDef();
			} else if (ref.getContDef() instanceof Actor) {
				definition = (Actor) ref.getContDef();
			}
		} else if (element instanceof Variable) {
			Variable var = (Variable) element;
			aborted = var.getUcmspec() == null;
			if (aborted)
				return;
			ucmspec = var.getUcmspec();
		} else if (element instanceof ScenarioStartPoint) {
			ScenarioStartPoint pt = (ScenarioStartPoint) element;
			aborted = pt.getScenarioDef() == null;
			if (aborted)
				return;
			scenario = pt.getScenarioDef();
		} else if (element instanceof ScenarioEndPoint) {
			ScenarioEndPoint pt = (ScenarioEndPoint) element;
			aborted = pt.getScenarioDef() == null;
			if (aborted)
				return;
			scenario = pt.getScenarioDef();
		} else if (element instanceof Initialization) {
			Initialization init = (Initialization) element;
			aborted = init.getScenarioDef() == null;
			if (aborted)
				return;
			scenario = init.getScenarioDef();
		} else if (element instanceof Condition) {
			Condition cond = (Condition) element;
			aborted = cond.getScenarioDefPost() == null && cond.getScenarioDefPre() == null;
			if (aborted)
				return;

			if (cond.getScenarioDefPre() != null) {
				scenario = cond.getScenarioDefPre();
				isPreCondition = true;
			}
			else if (cond.getScenarioDefPost() != null) {
				scenario = cond.getScenarioDefPost();
				isPreCondition = false;
			}

		}		

		redo();
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		if (aborted)
			return;

		testPreConditions();

		if (element instanceof IURNNode) {
			IURNNode node = (IURNNode) element;
			diagram.getNodes().remove(element);
			node.setContRef(null);
			if (node instanceof RespRef) {
				RespRef ref = (RespRef) node;
				ref.setRespDef(null);
			} else if (node instanceof IntentionalElementRef) {
				IntentionalElementRef ref = (IntentionalElementRef) node;
				ref.setDef(null);
			}

		} else if (element instanceof IURNContainerRef) {
			IURNContainerRef ref = (IURNContainerRef) element;
			diagram.getContRefs().remove(ref);
			ref.setParent(null);
			ref.setContDef(null);
		} else if (element instanceof Variable) {
			Variable var = (Variable) element;
			var.setUcmspec(null);
		} else if (element instanceof ScenarioStartPoint) {
			ScenarioStartPoint pt = (ScenarioStartPoint) element;
			pt.setScenarioDef(null);
		} else if (element instanceof ScenarioEndPoint) {
			ScenarioEndPoint pt = (ScenarioEndPoint) element;
			pt.setScenarioDef(null);
		} else if (element instanceof Initialization) {
			Initialization init = (Initialization) element;
			init.setScenarioDef(null);
		} else if (element instanceof Condition) {
			Condition cond = (Condition) element;
			if (isPreCondition)
				cond.setScenarioDefPre(null);
			else
				cond.setScenarioDefPost(null);
			
		}

		testPostConditions();
	}

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (aborted)
			return;

		testPostConditions();

		if (element instanceof IURNNode) {
			IURNNode node = (IURNNode) element;
			if (node instanceof RespRef) {
				RespRef ref = (RespRef) node;
				ref.setRespDef((Responsibility) definition);
			} else if (node instanceof IntentionalElementRef) {
				IntentionalElementRef ref = (IntentionalElementRef) node;
				ref.setDef((IntentionalElement) definition);
			}
			diagram.getNodes().add(element);
			node.setContRef(parent);

		} else if (element instanceof IURNContainerRef) {
			IURNContainerRef ref = (IURNContainerRef) element;
			ref.setParent(parent);
			ref.setContDef((IURNContainer) definition);
			diagram.getContRefs().add(ref);
		} else if (element instanceof Variable) {
			Variable var = (Variable) element;
			var.setUcmspec(ucmspec);
		} else if (element instanceof ScenarioStartPoint) {
			ScenarioStartPoint pt = (ScenarioStartPoint) element;
			pt.setScenarioDef(scenario);
		} else if (element instanceof ScenarioEndPoint) {
			ScenarioEndPoint pt = (ScenarioEndPoint) element;
			pt.setScenarioDef(scenario);
		} else if (element instanceof Initialization) {
			Initialization init = (Initialization) element;
			init.setScenarioDef(scenario);			
		} else if (element instanceof Condition) {
			Condition cond = (Condition) element;
			if (isPreCondition)
				cond.setScenarioDefPre(scenario);
			else
				cond.setScenarioDefPost(scenario);
			
		}
		testPreConditions();
	}

	/**
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert element != null && (diagram != null || ucmspec != null || scenario!=null) : "pre something is null"; //$NON-NLS-1$
		if (element instanceof IURNNode)
			assert diagram.getNodes().contains(element) : "pre diagram contains element"; //$NON-NLS-1$
		else if (element instanceof IURNContainerRef)
			assert diagram.getContRefs().contains(element) : "pre diagram contains element"; //$NON-NLS-1$

		if (definition != null) {
			if (element instanceof RespRef) {
				RespRef ref = (RespRef) element;
				assert definition == ref.getRespDef() : "pre resp def"; //$NON-NLS-1$
			} else if (element instanceof IURNContainerRef) {
				IURNContainerRef ref = (IURNContainerRef) element;
				assert definition == ref.getContDef() : "pre comp def"; //$NON-NLS-1$
			} else if (element instanceof IntentionalElementRef) {
				IntentionalElementRef ref = (IntentionalElementRef) element;
				assert definition == ref.getDef() : "pre intentional element def"; //$NON-NLS-1$
			}
		}

		if (parent != null) {
			if (element instanceof IURNNode) {
				IURNNode node = (IURNNode) element;
				assert parent == node.getContRef() : "pre pn parent"; //$NON-NLS-1$

			} else if (element instanceof IURNContainerRef) {
				IURNContainerRef ref = (IURNContainerRef) element;
				assert parent == ref.getParent() : "pre cr parent"; //$NON-NLS-1$
			}
		}

	}

	/**
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert element != null && (diagram != null || ucmspec != null || scenario!=null) : "post something is null"; //$NON-NLS-1$
		if (element instanceof IURNNode)
			assert !diagram.getNodes().contains(element) : "post diagram contains element"; //$NON-NLS-1$
		else if (element instanceof IURNContainerRef)
			assert !diagram.getContRefs().contains(element) : "post diagram contains element"; //$NON-NLS-1$
		if (definition != null) {
			if (element instanceof RespRef) {
				RespRef ref = (RespRef) element;
				assert null == ref.getRespDef() : "post resp def"; //$NON-NLS-1$
			} else if (element instanceof IURNContainerRef) {
				IURNContainerRef ref = (IURNContainerRef) element;
				assert null == ref.getContDef() : "post comp def"; //$NON-NLS-1$
			} else if (element instanceof IntentionalElementRef) {
				IntentionalElementRef ref = (IntentionalElementRef) element;
				assert null == ref.getDef() : "post comp def"; //$NON-NLS-1$
			}
		}

		if (parent != null) {
			if (element instanceof IURNNode) {
				IURNNode node = (IURNNode) element;
				assert null == node.getContRef() : "post pn parent"; //$NON-NLS-1$

			} else if (element instanceof IURNContainerRef) {
				IURNContainerRef ref = (IURNContainerRef) element;
				assert null == ref.getParent() : "post cr parent"; //$NON-NLS-1$
			}
		}
	}

}
