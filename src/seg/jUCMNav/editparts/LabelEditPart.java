package seg.jUCMNav.editparts;

import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

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
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.resource.JFaceResources;
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
import ucm.map.EmptyPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.GRLmodelElement;
import urncore.Label;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.SpecificationComponent;
import urncore.SpecificationComponentRef;
import urncore.SpecificationNode;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;
import urncore.UrncorePackage;

/**
 * EditPart associated with urncore.Label
 * 
 * For conditions, see ConditionEditPart.
 * 
 * @author Jordan McManus, jkealey
 */
public class LabelEditPart extends ModelElementEditPart {

    // label padding
    protected static final int LABEL_PADDING_X = 6;
    protected static final int LABEL_PADDING_Y = 4;

    // when referencing comprefs, the label's text is contained in the definition.
    private SpecificationComponent comp = null;

    // for direct edit
    protected DirectEditManager manager;

    // the model element being referenced; might be component, pathnode or even node connection (see ConditionEditPart)
    protected EObject modelElement;

    // when referencing resprefs, the label's text is contained in the definition.
    private Responsibility resp = null;

    // When referencing IntentionalElementRef, the label's text is contained in the definition
    private IntentionalElement intElement = null;
    
    /**
     * Constructor infers the referenced model element for NodeLabels and ComponentLabels.
     * 
     * @param model
     *            the urncore.Label to be represented
     */
    public LabelEditPart(Label model) {
        super();
        setModel(model);
        if (model instanceof NodeLabel)
            modelElement = ((NodeLabel) model).getNode();
        else if (model instanceof ComponentLabel)
            modelElement = ((ComponentLabel) model).getCompRef();

    }

    /**
     * 
     * @param model
     *            the urncore.Label to be represented
     * @param modelElement
     *            the model element is is supposed to be linked to.
     */
    public LabelEditPart(Label model, EObject modelElement) {
        super();
        setModel(model);
        this.modelElement = modelElement;
    }

    /**
     * activates the listeners
     */
    public void activate() {
        if (!isActive()) {
            modelElement.eAdapters().add(this);
            if (modelElement instanceof SpecificationComponentRef && ((SpecificationComponentRef) modelElement).getCompDef() != null) {
                comp = (SpecificationComponent)((SpecificationComponentRef) modelElement).getCompDef();
                comp.eAdapters().add(this);
            } else if (modelElement instanceof RespRef && ((RespRef) modelElement).getRespDef() != null) {
                resp = ((RespRef) modelElement).getRespDef();
                resp.eAdapters().add(this);
            } else if (modelElement instanceof IntentionalElementRef && ((IntentionalElementRef) modelElement).getDef() != null){
                intElement = ((IntentionalElementRef) modelElement).getDef();
                intElement.eAdapters().add(this);
            }
        }
        super.activate();
    }

    /**
     * Places labels on the screen given their size, the model element's position and the deltax/y.
     * 
     * NodeLabels are positioned relative to the center of the Node. ComponentLabels are positioned relative to the top left corner of the ComponentRef.
     * 
     * @param label
     *            the urncore.Label to be drawn.
     * @param labelDimension
     *            its dimensions.
     * @return the point where the label should be located. 
     */
    protected Point calculateModelElementPosition(Label label, Dimension labelDimension) {
        Point location = null;

        if (modelElement instanceof SpecificationNode) {
            SpecificationNode node = (SpecificationNode) modelElement;
            if (node instanceof UCMmodelElement){
                location = new Point(node.getX() - labelDimension.width / 2 - label.getDeltaX(), node.getY()
                    - (labelDimension.height + JUCMNavFigure.getDimension(modelElement).height / 2) - label.getDeltaY());
            }else if (node instanceof GRLmodelElement){
                location = new Point(node.getX() - labelDimension.width / 2, node.getY() - labelDimension.height/2);
            }
        } else if (modelElement instanceof SpecificationComponentRef) {
            SpecificationComponentRef component = (SpecificationComponentRef) modelElement;
            location = new Point(component.getX() - label.getDeltaX(), component.getY() - label.getDeltaY() - labelDimension.height);
        } else {
            // if we don't know how to place this label, use top left corner of screen.
            // not used in practice, simply for debugging.
            location = new Point(0, 0);
        }

        return location;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LabelComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
        //LabelFeedbackPolicy only for UCMmodelElement
        if (modelElement instanceof UCMmodelElement || modelElement instanceof NodeConnection){
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new LabelFeedbackEditPolicy());
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        EditableLabel label = new EditableLabel(""); //$NON-NLS-1$
        return new LabelFigure(label);
    }

    /**
     * removes listeners
     */
    public void deactivate() {
        if (isActive()) {
            modelElement.eAdapters().remove(this);
            if (modelElement instanceof SpecificationComponentRef && comp != null) {
                comp.eAdapters().remove(this);
                comp = null;
            } else if (modelElement instanceof RespRef && resp != null) {
                resp.eAdapters().remove(this);
                resp = null;
            }else if (modelElement instanceof IntentionalElementRef && intElement != null){
                intElement.eAdapters().remove(this);
                intElement = null;
            }
        }
        super.deactivate();
    }

    /**
     * For direct edit, verify location.
     * 
     * @param requestLoc
     * @return true if the label contains the point
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
     * Convenience method to avoid casting.
     * 
     * @return the label's figure.
     */
    public LabelFigure getLabelFigure() {
        return (LabelFigure) getFigure();
    }

    /**
     * Convenience method to avoid casting.
     * 
     * @return the label
     */
    public Label getModelObj() {
        return (Label) getModel();
    }

    /**
     * @return an instance of LabelPropertySource.
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new LabelPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /**
     * 
     * @return the referenced model element.
     */
    public EObject getURNmodelElement() {
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

    /**
     * When the label's model element definition changes, we must change our references.
     *  
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureIdUrn = notification.getFeatureID(UrncorePackage.class);
        int featureIdUcm = notification.getFeatureID(MapPackage.class);
        int featureIdGrl = notification.getFeatureID(GrlPackage.class);

        if (type == Notification.SET) {
            // if changing component definitions
            if (featureIdUrn == UrncorePackage.SPECIFICATION_COMPONENT_REF__COMP_DEF) {
                if (modelElement instanceof SpecificationComponentRef) {
                    if (comp != null)
                        comp.eAdapters().remove(this);
                    comp = (SpecificationComponent)((SpecificationComponentRef) modelElement).getCompDef();
                    if (comp != null)
                        comp.eAdapters().add(this);
                }
            } else if (featureIdUcm == MapPackage.RESP_REF__RESP_DEF){ // if changing responsibility definitions
                if (modelElement instanceof RespRef) {
                    if (resp != null)
                        resp.eAdapters().remove(this);
                    resp = ((RespRef) modelElement).getRespDef();
                    if (resp != null)
                        resp.eAdapters().add(this);
                }
            } else if (featureIdGrl == GrlPackage.INTENTIONAL_ELEMENT_REF__DEF){ // if changing IntentionalElement definitions
                if (modelElement instanceof IntentionalElementRef) {
                    if (intElement != null)
                        intElement.eAdapters().remove(this);
                    intElement = ((IntentionalElementRef) modelElement).getDef();
                    if (intElement != null)
                        intElement.eAdapters().add(this);
                }
            }

            // something changed; inform parent. not sure how useful this is anymore
            if (getParent() != null) {
                ((SpecificationDiagramEditPart) getParent()).notifyChanged(notification);
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

    /**
     * Refresh the label.
     */
    public void refreshVisuals() {
        if (modelElement != null) {
            eraseTargetFeedback(new SelectionRequest());
            LabelFigure labelFigure = getLabelFigure();
            EditableLabel label = labelFigure.getLabel();

            // set the label's text
            setLabelText();

            //The position of the new figure
            Point location;
            Dimension newLabelDimension;
            if (getParent() != null) {
                Dimension dimEditableLabel = label.getPreferredSize().getCopy();
                newLabelDimension = new Dimension(dimEditableLabel.width + LABEL_PADDING_X, dimEditableLabel.height + LABEL_PADDING_Y);
                location = calculateModelElementPosition(getModelObj(), newLabelDimension);
            } else {
                // happens in mass deletions; random unimportant location.
                location = new Point(0, 0);
                newLabelDimension = new Dimension(100, 100);
            }

            Rectangle bounds = new Rectangle(location, newLabelDimension);
            labelFigure.setBounds(bounds);
            label.setBounds(bounds);

            // For UCM, we only show empty points (and their labels) when the mode is 0.
            if (modelElement instanceof EmptyPoint) {
                if (getParent() != null) {
                    labelFigure.setVisible(((UCMConnectionOnBottomRootEditPart) getRoot()).getMode() == 0);
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
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the label value)
     */
    public void revertNameChange() {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        EditableLabel label = tableFigure.getLabel();
        label.setVisible(true);
        refreshVisuals();
    }

    /**
     * Sets the label's text, given its referenced model element.
     */
    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();
        EditableLabel label = labelFigure.getLabel();

        if (modelElement instanceof SpecificationComponentRef) { // use definition
            SpecificationComponent componentElement = (SpecificationComponent)((SpecificationComponentRef) modelElement).getCompDef();
            if (componentElement != null)
                label.setText(((URNmodelElement)componentElement).getName());
            // componentref labels are in bold.
            label.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        } else if (modelElement instanceof RespRef) { // use definition
            Responsibility responsibility = ((RespRef) modelElement).getRespDef();
            if (responsibility != null)
                label.setText(responsibility.getName());
        } else if (modelElement instanceof IntentionalElementRef) { // use definition
            IntentionalElement elem = ((IntentionalElementRef) modelElement).getDef();
            if (elem != null)
                label.setText(elem.getName());
        }else if (modelElement instanceof URNmodelElement) { // use element's name directly.
            label.setText(((URNmodelElement) modelElement).getName());
        }

    }
}