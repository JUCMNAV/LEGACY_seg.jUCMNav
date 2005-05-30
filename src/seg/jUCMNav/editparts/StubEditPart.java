package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;

import seg.jUCMNav.figures.StubFigure;
import ucm.map.Map;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.Stub;

/**
 * Created 2005-05-11
 * 
 * @author Etienne Tremblay
 */
public class StubEditPart extends PathNodeEditPart {
	
	private StubFigure figure;

	/**
	 * 
	 */
	public StubEditPart(PathNode model, PathGraph diagram) {
		super(model, diagram);
	}

	public void refreshVisuals() {
		Stub stub = (Stub)getNode();
		figure.setDynamic(stub.isDynamic());
		
		super.refreshVisuals();
	}
	
	protected IFigure createFigure() {
		Stub stub = (Stub)getModel();
		figure = new StubFigure(stub.isDynamic());
		return figure;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	public void performRequest(Request req) {
		if(req.getType() == REQ_OPEN) {
			Stub stub = (Stub)getModel();
//			PluginBinding bind = (PluginBinding)stub.getBindings().get(0);
			Map map = stub.getPathGraph().getMap();
			
			EditPart mapEdit = (EditPart)getViewer().getEditPartRegistry().get(map);
			getViewer().select(mapEdit);
		}
	}
}
