/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmDiagram;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GraphicalEditPartFactory implements EditPartFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof UcmDiagram)
			return new UcmDiagramEditPart((UcmDiagram)model);
		else if(model instanceof Path){
			return new PathEditPart((Path)model);
		}
		else if(model instanceof Component){
			return new ComponentEditPart((Component)model);
		}
		else if(model instanceof Node) {
			UcmNodeEditPart tmp = new UcmNodeEditPart((Node)model);
			return tmp;
		}
		else
			return null;
	}

}
