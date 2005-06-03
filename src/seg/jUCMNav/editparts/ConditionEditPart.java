/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;

import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.editpolicies.element.ConditionComponentEditPolicy;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.SplineConnection;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import urncore.Condition;

/**
 * @author Jordan
 */
public class ConditionEditPart extends ModelElementEditPart {
	private PathGraph diagram;
	private NodeConnection connection;
	protected DirectEditManager manager;
	
	private static final int LABEL_PADDING_X = 6;
    private static final int LABEL_PADDING_Y = 4;
    
	public ConditionEditPart(Condition model, PathGraph diagram) {
		super();
		setModel(model);
		connection = model.getNodeConnection();
		this.diagram = diagram;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
        	connection.eAdapters().add(this);
        }
        super.activate();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
        	connection.eAdapters().remove(this);
        }
        super.deactivate();
    }
	
	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        EditableLabel label = new EditableLabel("");
        return new LabelFigure(label);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    public void refreshVisuals() {
        if (connection != null && connection.getCondition() != null) {
        	LabelFigure labelFigure = getLabelFigure();
            EditableLabel label = labelFigure.getLabel();
            
            Condition condition = (Condition) getModel();
            
            if(condition != null) {
            	if(condition.getLabel() != null) {
                	label.setText(condition.getLabel());
                } else {
                	label.setText("Condition");
                }
                
                
                Dimension dimEditableLabel = labelFigure.getLabel().getPreferredSize().getCopy();
                Dimension newLabelDimension = new Dimension(dimEditableLabel.width + LABEL_PADDING_X, dimEditableLabel.height + LABEL_PADDING_Y);
                
                Point location = calculateConditionPosition();
                
                Rectangle bounds = new Rectangle(location, newLabelDimension);
                figure.setBounds(bounds);
                label.setBounds(bounds);
                
                if (getParent() != null) {
                    ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
                }
            }
        }
    }
    
    private Point calculateConditionPosition() {
        Point location = new Point(0,0);

        NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(connection);
        if (nc != null) {
        	SplineConnection sp = (SplineConnection) nc.getFigure();
            if (sp != null) {
                PointList list = sp.getPoints();
                if (list != null) {
                	Point mid = list.getMidpoint();
                	location = new Point(mid.x - ((Condition) getModel()).getDeltaX(), mid.y - ((Condition) getModel()).getDeltaY());
                }
            }
        }

        return location;
    }
    
    public LabelFigure getLabelFigure() {
        return (LabelFigure) getFigure();
    }
    
    public NodeConnection getNodeConnection() {
    	return connection;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConditionComponentEditPolicy());
    }
    
    /**
     * For direct edit, verify location.
     * 
     * @param requestLoc
     * @return
     */
    private boolean directEditHitTest(Point requestLoc) {
        LabelFigure figure = (LabelFigure) getFigure();
        EditableLabel nameLabel = figure.getLabel();
        nameLabel.translateToRelative(requestLoc);
        if (nameLabel.containsPoint(requestLoc))
            return true;
        return false;
    }
    
    /**
     * @param handles
     *            the name change during an edit
     */
    public void handleNameChange(String value) {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        EditableLabel label = tableFigure.getLabel();
        label.setVisible(false);
        refreshVisuals();
    }
    
    /**
     * Opens the direct edit manager.
     *  
     */
    protected void performDirectEdit() {
        if (manager == null) {
            LabelFigure figure = (LabelFigure) getFigure();
            EditableLabel nameLabel = figure.getLabel();

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return "";
                }
            };

            manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(nameLabel), nameLabel, validator);
        }
        manager.show();
    }

    /**
     * Show direct edit on label on double click, f2 or delay.
     */
    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if (request instanceof DirectEditRequest && !directEditHitTest(((DirectEditRequest) request).getLocation().getCopy()))
                return;
            performDirectEdit();
        }
    }
    
    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the label value)
     */
    public void revertNameChange() {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        EditableLabel label = tableFigure.getLabel();
        label.setVisible(true);
        refreshVisuals();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
    	if(getModel() != null) {
    		if (getParent() != null) {
                ((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
                refreshVisuals();
            }
    	}
    }
}
