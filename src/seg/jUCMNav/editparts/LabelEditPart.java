/*
 * Created on Mar 27, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelDirectEditPolicy;
import seg.jUCMNav.editpolicies.element.LabelComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.LabelFeedbackEditPolicy;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.util.JUCMNavFigure;
import seg.jUCMNav.views.property.LabelPropertySource;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.ComponentElement;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;

/**
 * Based on Etienne's implementation of PathNodeEditPart
 * 
 * @author Jordan McManus
 */
public class LabelEditPart extends ModelElementEditPart {

    protected static final int LABEL_PADDING_X = 6;
    protected static final int LABEL_PADDING_Y = 4;
    private ComponentElement comp = null;

    protected DirectEditManager manager;
    private EObject modelElement;
    private Responsibility resp = null;
    private NodeConnection nc = null;

    public LabelEditPart(Label model) {
        super();
        setModel(model);
        if (model instanceof NodeLabel)
            modelElement = ((NodeLabel) model).getPathNode();
        else if (model instanceof ComponentLabel)
            modelElement = ((ComponentLabel) model).getCompRef();
        else if (model instanceof Condition) {
            if (((Condition) model).getNodeConnection() != null)
                modelElement = ((Condition) model).getNodeConnection();
            else if (((Condition) model).getStartPoint() != null)
                modelElement = ((Condition) model).getStartPoint();
            else if (((Condition) model).getEndPoint() != null)
                modelElement = ((Condition) model).getEndPoint();

        }

    }

    public LabelEditPart(Label model, EObject modelElement) {
        super();
        setModel(model);
        this.modelElement = modelElement;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            modelElement.eAdapters().add(this);
            if (modelElement instanceof ComponentRef) {
                comp = ((ComponentRef) modelElement).getCompDef();
                if (comp != null)
                    comp.eAdapters().add(this);
            } else if (modelElement instanceof RespRef) {
                resp = ((RespRef) modelElement).getRespDef();
                if (resp != null)
                    resp.eAdapters().add(this);
            } else if (modelElement instanceof Condition && ((Condition) modelElement).getNodeConnection() != null) {
                nc = ((Condition) modelElement).getNodeConnection();
                if (nc != null)
                    nc.eAdapters().add(this);
            }
        }
        super.activate();
    }

    protected Point calculateModelElementPosition(Label label, Dimension labelDimension) {
        Point location;

        if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            location = new Point(node.getX() - labelDimension.width / 2 - label.getDeltaX(), node.getY()
                    - (labelDimension.height + JUCMNavFigure.getDimension(modelElement).height / 2) - label.getDeltaY());
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            location = new Point(component.getX() - label.getDeltaX(), component.getY() - label.getDeltaY() - labelDimension.height);
        } else {
            location = new Point(0, 0);
        }

        return location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LabelComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new LabelFeedbackEditPolicy());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        EditableLabel label = new EditableLabel(""); //$NON-NLS-1$
        return new LabelFigure(label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            modelElement.eAdapters().remove(this);
            if (modelElement instanceof ComponentRef) {
                if (comp != null)
                    comp.eAdapters().remove(this);
                comp = null;
            } else if (modelElement instanceof RespRef) {
                if (resp != null)
                    resp.eAdapters().remove(this);
                resp = null;
            } else if (modelElement instanceof Condition && ((Condition) modelElement).getNodeConnection() != null) {
                nc = ((Condition) modelElement).getNodeConnection();
                if (nc != null)
                    nc.eAdapters().remove(this);
            }
        }
        super.deactivate();
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

    public LabelFigure getLabelFigure() {
        return (LabelFigure) getFigure();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.ModelEditPart#getModelObj()
     */
    public Label getModelObj() {
        return (Label) getModel();
    }

    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new LabelPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.ModelEditPart#getModelObj()
     */
    public EObject getUCMmodelElement() {
        return modelElement;
    }

    /**
     * @param value
     *            the name change during an edit
     */
    public void handleNameChange(String value) {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        EditableLabel label = tableFigure.getLabel();
        label.setVisible(false);
        refreshVisuals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(MapPackage.class);

        if (type == Notification.SET) {
            if (featureId == MapPackage.COMPONENT_REF__COMP_DEF) {
                if (modelElement instanceof ComponentRef) {
                    if (comp != null)
                        comp.eAdapters().remove(this);
                    comp = ((ComponentRef) modelElement).getCompDef();
                    if (comp != null)
                        comp.eAdapters().add(this);
                }
            } else if (featureId == MapPackage.RESP_REF__RESP_DEF)
                if (modelElement instanceof RespRef) {
                    if (resp != null)
                        resp.eAdapters().remove(this);
                    resp = ((RespRef) modelElement).getRespDef();
                    if (resp != null)
                        resp.eAdapters().add(this);
                }

            if (getParent() != null) {

                ((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
                refreshVisuals();
            }
        }

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
                    return ""; //$NON-NLS-1$
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    public void refreshVisuals() {
        if (modelElement != null) {
            eraseTargetFeedback(null);
            LabelFigure labelFigure = getLabelFigure();
            EditableLabel label = labelFigure.getLabel();

            setLabelText();

            //The position of the new figure
            Point location;
            Dimension newLabelDimension;
            if (getParent() != null) {
                Dimension dimEditableLabel = label.getPreferredSize().getCopy();
                newLabelDimension = new Dimension(dimEditableLabel.width + LABEL_PADDING_X, dimEditableLabel.height + LABEL_PADDING_Y);

                location = calculateModelElementPosition(getModelObj(), newLabelDimension);
            } else {
                location = new Point(0, 0);
                newLabelDimension = new Dimension(100, 100);
            }

            Rectangle bounds = new Rectangle(location, newLabelDimension);
            labelFigure.setBounds(bounds);
            label.setBounds(bounds);

            if (modelElement instanceof EmptyPoint) {
                if (getParent() != null) {
                    labelFigure.setVisible(((ConnectionOnBottomRootEditPart) getRoot()).getMode() == 0);
                }
            }
            // notify parent container of changed position & location
            // if this line is removed, the XYLayoutManager used by the parent container
            // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
            // and will not draw it correctly.
            if (getParent() != null) {
                ((GraphicalEditPart) getParent()).setLayoutConstraint(this, labelFigure, bounds);
            }

        }
    }

    /**
     * 
     */
    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();
        EditableLabel label = labelFigure.getLabel();

        if (modelElement instanceof ComponentRef) {
            ComponentElement componentElement = ((ComponentRef) modelElement).getCompDef();
            if (componentElement != null)
                label.setText(componentElement.getName());
        } else if (modelElement instanceof RespRef) {
            Responsibility responsibility = ((RespRef) modelElement).getRespDef();
            if (responsibility != null)
                label.setText(responsibility.getName());
        } else if (modelElement instanceof UCMmodelElement) {
            label.setText(((UCMmodelElement) modelElement).getName());
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
}