/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import seg.jUCMNav.editpolicies.PathGraphXYLayoutEditPolicy;
import seg.jUCMNav.figures.router.BSplineConnectionRouter;
import ucm.map.PathGraph;

/**
 * @author Etienne Tremblay
 */
public class PathGraphEditPart extends ModelElementlEditPart {
	
	public PathGraphEditPart(PathGraph diagram){
		setModel(diagram);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		FreeformLayer layer = new FreeformLayer();
		layer.setLayoutManager(new FreeformLayout());
		layer.setBorder(new LineBorder(1));
		return layer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// This install a container policy
//		installEditPolicy(EditPolicy.CONTAINER_ROLE, new UcmEditPolicy());
		// This install the layout edit policy.  Wich commands are used for create/move/resize etc...
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PathGraphXYLayoutEditPolicy());
//		installEditPolicy(EditPolicy.NODE_ROLE, null);
//		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
//		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}
	
	private PathGraph getDiagram(){
		return (PathGraph)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		return getDiagram().getPathNodes();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		int type = notification.getEventType();
		switch(type){
		case Notification.ADD:
		case Notification.ADD_MANY:
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
			refreshChildren();
		break;
		case Notification.SET:
			refreshVisuals();
		break;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
	 */
	protected void registerVisuals() {
		ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		cLayer.setConnectionRouter(new BSplineConnectionRouter(getDiagram()));
		
		super.registerVisuals();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.editparts.ModelElementlEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
	}
}
