package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;

import seg.jUCMNav.figures.StubFigure;
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
}
