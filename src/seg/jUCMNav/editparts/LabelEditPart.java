/*
 * Created on Mar 27, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.LabelComponentEditPolicy;
import seg.jUCMNav.editpolicies.LabelDirectEditPolicy;
import seg.jUCMNav.figures.EditableLabel;
import ucm.UcmPackage;
import ucm.map.MapPackage;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urncore.NodeLabel;


/**
 * Based on Etienne's implementation of PathNodeEditPart
 * @author Jordan McManus
 */
public class LabelEditPart extends ModelElementEditPart {
    private IPropertySource propertySource = null;
	private Notifier target;
	
	private PathGraph diagram;
	
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
        
        return label;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LabelComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }
    
    public EditableLabel getLabelFigure(){
		return (EditableLabel) getFigure();
	}

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        NodeLabel nodeLabel = getModelObj();
        EditableLabel label = getLabelFigure();
        
        label.setText(nodeLabel.getPathNode().getName());
        
        Dimension dim = label.getPreferredSize().getCopy();
        Point location = new Point(nodeLabel.getDeltaX()-(dim.width/2), nodeLabel.getDeltaY()-(dim.height/2));  // The position of the current figure
        Rectangle bounds = new Rectangle(location, dim);
		figure.setBounds(bounds);
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureId = notification.getFeatureID( UcmPackage.class );
		switch( featureId ) {
		case MapPackage.PATH_NODE__NAME:
			((MapAndPathGraphEditPart)getParent()).notifyChanged(notification);
		break;
		default:
			refreshVisuals();
		break;
		}
        refreshVisuals();
    }
}
