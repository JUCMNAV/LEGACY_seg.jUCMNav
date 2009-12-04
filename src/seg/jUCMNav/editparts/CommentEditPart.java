package seg.jUCMNav.editparts;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.directEditPolicy.CommentDirectEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.editpolicies.directEditPolicy.TextAreaCellEditor;
import seg.jUCMNav.editpolicies.element.CommentComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.GrlNodeFeedbackEditPolicy;
import seg.jUCMNav.figures.CommentFigure;
import seg.jUCMNav.views.property.URNElementPropertySource;
import urncore.Comment;

/**
 * EditPart for comments. Duplicates a bit of code because is used for both GRL and UCM.
 * 
 * @author jkealey
 * 
 */
public class CommentEditPart extends GrlNodeEditPart implements NodeEditPart {

    // for direct edit
    protected DirectEditManager manager;

    /**
     * 
     * @param model
     *            the comment to draw
     */
    public CommentEditPart(Comment model) {
        super();
        setModel(model);
    }

    /**
     * Create the edit policies.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new CommentComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new CommentDirectEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GrlNodeFeedbackEditPolicy());
    }

    /**
     * Create the figure.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        CommentFigure fig = new CommentFigure();

        return fig;
    }

    /**
     * For direct edit, verify location.
     * 
     * @param requestLoc
     * @return true if the figure contains the point
     */
    private boolean directEditHitTest(Point requestLoc) {
        CommentFigure figure = (CommentFigure) getFigure();
        figure.translateToRelative(requestLoc);
        if (figure.containsPoint(requestLoc))
            return true;
        return false;

    }

    /**
     * When nodes are dragged in GEF, they explictly remove connections from being possible drop targets. By overriding DragEditPartsTracker, we allow this
     * behaviour.
     * 
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return new DragPathNodeTracker(this);
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    protected List getModelSourceConnections() {
        return null;
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    protected List getModelTargetConnections() {
        return null;
    }

    /**
     * 
     * @return the comment
     */
    private Comment getNode() {
        return (Comment) getModel();
    }

    /**
     * @return The node's figure
     */
    public CommentFigure getNodeFigure() {
        return (CommentFigure) getFigure();
    }

    /**
     * @return a URNElementPropertySource
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new URNElementPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {

        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * @param value
     *            the name change during an edit
     */
    public void handleNameChange(String value) {
        CommentFigure tableFigure = (CommentFigure) getFigure();
        tableFigure.setVisible(false);
        refreshVisuals();
    }

    /**
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        if (getParent() == null)
            return;
        refreshTargetConnections();
        refreshSourceConnections();
        refreshVisuals();

        int featureId = notification.getFeatureID(Comment.class);

        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /**
     * Opens the direct edit manager.
     * 
     */
    protected void performDirectEdit() {
        if (manager == null) {
            CommentFigure figure = (CommentFigure) getFigure();

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return ""; //$NON-NLS-1$
                }
            };

            manager = new ExtendedDirectEditManager(this, TextAreaCellEditor.class, new LabelCellEditorLocator(figure), figure, validator);
        }
        manager.show();
    }

    /**
     * Show direct edit on element on double click, f2 or delay.
     */
    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if (request instanceof DirectEditRequest && !directEditHitTest(((DirectEditRequest) request).getLocation().getCopy()))
                return;
            performDirectEdit();
        }
    }

    /**
     * Refresh the figure and its associated labels.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getNode().getX(), getNode().getY());
        // its size (the width of the elements should always be 2 the height of them
        Dimension size = new Dimension(getNode().getWidth(), getNode().getHeight());
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);

        setText();

        ((CommentFigure) figure).setColors(null, getNode().getFillColor(), getNode().getFillColor() != null);

        // Make the label recenter itself.
        figure.validate();

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        // if (getParent() != null)
        // (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the element value)
     */
    public void revertNameChange() {
        CommentFigure tableFigure = (CommentFigure) getFigure();
        tableFigure.setVisible(true);
        refreshVisuals();
    }

    /**
     * Sets the label's text, given its referenced model element.
     */
    private void setText() {
        if (getNode() != null) {
            getNodeFigure().setEditableText(getNode().getDescription());
        }
    }

}
