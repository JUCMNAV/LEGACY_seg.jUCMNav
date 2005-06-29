/*
 * Created on 2005-01-30
 *
 */
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
	
	Map root;
	
	public GraphicalEditPartFactory(){
		super();
	}
	
	public GraphicalEditPartFactory(Map root){
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
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
			return new PathNodeEditPart((PathNode)model, root.getPathGraph());
		else { 	
		    System.out.println("Unknown class in GraphicalEditPartFactory.createEditPart();"); //$NON-NLS-1$
			return null;
		}
	}

}
