package seg.jUCMNav.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.Stub;
import urncore.Condition;
import urncore.Label;

/**
 * This is where all EditParts are created.
 * Once GEF detects the creation of a model object, the method createEditPart is called with this object
 * 
 * @author Etienne Tremblay
 */
public class GraphicalEditPartFactory implements EditPartFactory {
	// the root map for this factory. 
	private Map root;
	
	public GraphicalEditPartFactory(){
		super();
	}
	
	public GraphicalEditPartFactory(Map root){
		this.root = root;
	}


	/**
	 * Creates a new instance of an editpart given the model element. 
	 * If we forget to add code here when creating new model elements, an assertion will fail. 
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof Map)
			return new MapAndPathGraphEditPart((Map)model);
		else if(model instanceof NodeConnection)
			return new NodeConnectionEditPart((NodeConnection)model, root.getPathGraph());
		else if(model instanceof Condition)
			return new ConditionEditPart((Condition)model, root.getPathGraph());
		else if(model instanceof Label)
			return new LabelEditPart((Label)model);
		else if(model instanceof ComponentRef) 
		    return new ComponentRefEditPart((ComponentRef)model, root);
		else if(model instanceof Stub)
			return new StubEditPart((Stub)model, root.getPathGraph());
		else if (model instanceof AndFork || model instanceof AndJoin)
		    return new AndForkJoinEditPart((PathNode) model, root.getPathGraph());
		else if(model instanceof PathNode)
		    // all other pathnodes share the same editpart. 
			return new PathNodeEditPart((PathNode)model, root.getPathGraph());
		else { 	
		    System.out.println("Unknown class in GraphicalEditPartFactory.createEditPart();"); //$NON-NLS-1$
		    assert false : "Unknown class in GraphicalEditPartFactory.createEditPart();"; //$NON-NLS-1$
			return null;
		}
	}

}
