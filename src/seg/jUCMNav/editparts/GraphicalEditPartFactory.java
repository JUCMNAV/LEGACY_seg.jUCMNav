/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.RespRef;
import ucm.map.StartPoint;

/**
 * @author Etienne Tremblay
 *
 */
public class GraphicalEditPartFactory implements EditPartFactory {
	
	PathGraph root;
	
	public GraphicalEditPartFactory(){
		super();
	}
	
	public GraphicalEditPartFactory(PathGraph root){
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof PathGraph)
			return new PathGraphEditPart((PathGraph)model);
		else if(model instanceof NodeConnection)
			return new NodeConnectionEditPart((NodeConnection)model, root);
		else if(model instanceof EmptyPoint)
			return new PathNodeEditPart((EmptyPoint)model, root);
		else if(model instanceof StartPoint)
			return new PathNodeEditPart((StartPoint)model, root);
		else if(model instanceof EndPoint)
			return new PathNodeEditPart((EndPoint)model, root);
		else if(model instanceof RespRef)
			return new PathNodeEditPart((RespRef)model, root);
		else
			return null;
	}

}
