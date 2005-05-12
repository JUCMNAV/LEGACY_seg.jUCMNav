/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ucm.map.AndFork;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urncore.Label;

/**
 * @author Etienne Tremblay
 *
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
		else if(model instanceof EmptyPoint)
			return new PathNodeEditPart((EmptyPoint)model, root.getPathGraph());
		else if(model instanceof StartPoint)
			return new PathNodeEditPart((StartPoint)model, root.getPathGraph());
		else if(model instanceof EndPoint)
			return new PathNodeEditPart((EndPoint)model, root.getPathGraph());
		else if(model instanceof RespRef)
			return new PathNodeEditPart((RespRef)model, root.getPathGraph());
		else if(model instanceof Label)
			return new LabelEditPart((Label)model);
		else if(model instanceof ComponentRef) 
		    return new ComponentRefEditPart((ComponentRef)model, root);
		else if(model instanceof OrFork)
		    return new PathNodeEditPart((OrFork)model, root.getPathGraph());
		else if(model instanceof AndFork)
		    return new PathNodeEditPart((AndFork)model, root.getPathGraph());
		else if(model instanceof Stub)
			return new StubEditPart((Stub)model, root.getPathGraph());
		else { 	
		    System.out.println("Unknown class in GraphicalEditPartFactory.createEditPart();");
			return null;
		}
	}

}
