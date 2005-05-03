/*
 * Created on Mar 27, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;

import seg.jUCMNav.editpolicies.directEdit.LabelDirectEditPolicy;
import seg.jUCMNav.editpolicies.element.LabelComponentEditPolicy;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.figures.LabelFigure;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urncore.NodeLabel;


/**
 * Based on Etienne's implementation of PathNodeEditPart
 * @author Jordan McManus
 */
public class LabelEditPart extends ModelElementEditPart {
	
	private PathGraph diagram;
	private Point nodePosition;
	
	public LabelEditPart(NodeLabel model, PathGraph diagram){
		super();
		setModel(model);
		this.diagram = diagram;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		if(!isActive())
		    ((NodeLabel) getModel()).getPathNode().eAdapters().add(this);
		super.activate();
	}
	
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelEditPart#getModelObj()
     */
    public NodeLabel getModelObj() {
        return (NodeLabel) getModel();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NodeLabel nodeLabel = getModelObj();
        PathNode node = nodeLabel.getPathNode();
        String name = node.getName();
        
        EditableLabel label;
        if(name != null) {
            label = new EditableLabel(node.getName());
        } else {
            String[] fullClassName = node.getClass().getName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            className = className.substring(0, className.length()-4);
            label = new EditableLabel(className);
        }
        
        nodePosition = new Point(node.getX(), node.getY());
        
        return new LabelFigure(label);
    }
    
    public boolean nodeMoving() {
    	PathNode node = getModelObj().getPathNode();
    	if(nodePosition.x != node.getX() || nodePosition.y != node.getY()) {
    		return true;
    	}
    	
    	return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LabelComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }
    
    public LabelFigure getLabelFigure(){
		return (LabelFigure) getFigure();
	}

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
    	NodeLabel nodeLabel = getModelObj();
        PathNode node = nodeLabel.getPathNode();
        if(node != null) {
        	LabelFigure labelFigure = getLabelFigure();
            EditableLabel label = labelFigure.getLabel();
            
            label.setText(nodeLabel.getPathNode().getName());
            
            Dimension dim1 = labelFigure.getPreferredSize().getCopy();
            Dimension dim2 = labelFigure.getLabel().getPreferredSize().getCopy();
            Dimension dim = new Dimension(dim2.width + 10, dim2.height + 4); 
            Point location = new Point(node.getX() - nodeLabel.getDeltaX()-(dim.width/2), node.getY() - nodeLabel.getDeltaY()-(dim.height/2));  // The position of the current figure
            Rectangle bounds = new Rectangle(location, dim);
    		figure.setBounds(bounds);
    		label.setBounds(bounds);
    		// notify parent container of changed position & location
    		// if this line is removed, the XYLayoutManager used by the parent container 
    		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
    		// and will not draw it correctly.
    		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
        }
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
    	if (getParent()!=null) {
    		((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
    	}
    	
        refreshVisuals();
        /*
        int featureId = notification.getFeatureID( UcmPackage.class );
		switch( featureId ) {
		case MapPackage.PATH_NODE__NAME:
			((MapAndPathGraphEditPart)getParent()).notifyChanged(notification);
		break;
		default:
			//refreshVisuals();
		break;
		}
        refreshVisuals();
        */
    }
}
