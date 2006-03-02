package seg.jUCMNav.editparts;

import grl.ActorRef;
import grl.GrlPackage;
import grl.IntentionalElement;

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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelDirectEditPolicy;
import seg.jUCMNav.editpolicies.element.LabelComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.LabelFeedbackEditPolicy;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.util.JUCMNavFigure;
import seg.jUCMNav.model.util.EvaluationStrategyManager;
import seg.jUCMNav.views.property.LabelPropertySource;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.MapPackage;
import ucm.map.RespRef;
import urn.URNlink;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;
import urncore.NodeLabel;
import urncore.Responsibility;
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
    private IURNContainer comp = null;

    // for direct edit
    protected DirectEditManager manager;

    // the model element being referenced; might be component, pathnode or even node connection (see ConditionEditPart)
    protected EObject modelElement;

    // when referencing resprefs, the label's text is contained in the definition.
    private Responsibility resp = null;
    
    //Image
    private Image iconImg;
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
            modelElement = ((ComponentLabel) model).getContRef();

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
            if (modelElement instanceof IURNContainerRef && ((IURNContainerRef) modelElement).getContDef() != null) {
                comp = (IURNContainer) ((IURNContainerRef) modelElement).getContDef();
                comp.eAdapters().add(this);
            } else if (modelElement instanceof RespRef && ((RespRef) modelElement).getRespDef() != null) {
                resp = ((RespRef) modelElement).getRespDef();
                resp.eAdapters().add(this);
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

        if (modelElement instanceof IURNNode) {
            IURNNode node = (IURNNode) modelElement;
            location = new Point(node.getX() - labelDimension.width / 2 - label.getDeltaX(), node.getY()
                    - (labelDimension.height + JUCMNavFigure.getDimension(modelElement).height / 2) - label.getDeltaY());
        } else if (modelElement instanceof IURNContainerRef) {
            IURNContainerRef component = (IURNContainerRef) modelElement;
            if (modelElement instanceof ActorRef) {
                location = new Point(component.getX() + component.getWidth() / 2 - label.getDeltaX() - labelDimension.width / 2, component.getY()
                        - label.getDeltaY() - labelDimension.height);
            } else {
                location = new Point(component.getX() - label.getDeltaX(), component.getY() - label.getDeltaY() - labelDimension.height);
            }
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
        if ((getModelObj() instanceof NodeLabel && ((NodeLabel) getModelObj()).getNode() instanceof UCMmodelElement)
                || (getModelObj() instanceof ComponentLabel && ((ComponentLabel) getModelObj()).getContRef() instanceof UCMmodelElement)
                || (getModelObj() instanceof Condition)) {
            installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new LabelFeedbackEditPolicy());
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        return new LabelFigure();
    }

    /**
     * removes listeners
     */
    public void deactivate() {
        if (isActive()) {
            modelElement.eAdapters().remove(this);
            if (modelElement instanceof IURNContainerRef && comp != null) {
                comp.eAdapters().remove(this);
                comp = null;
            } else if (modelElement instanceof RespRef && resp != null) {
                resp.eAdapters().remove(this);
                resp = null;
            }
        }
        if (iconImg != null) {
            iconImg.dispose();
            iconImg = null;
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
        figure.translateToRelative(requestLoc);
        if (figure.containsPoint(requestLoc))
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
        tableFigure.setVisible(false);
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
            if (featureIdUrn == UrncorePackage.IURN_CONTAINER_REF__CONT_DEF) {
                if (modelElement instanceof IURNContainerRef) {
                    if (comp != null)
                        comp.eAdapters().remove(this);
                    comp = (IURNContainer) ((IURNContainerRef) modelElement).getContDef();
                    if (comp != null)
                        comp.eAdapters().add(this);
                }
            } else if (featureIdUcm == MapPackage.RESP_REF__RESP_DEF) { // if changing responsibility definitions
                if (modelElement instanceof RespRef) {
                    if (resp != null)
                        resp.eAdapters().remove(this);
                    resp = ((RespRef) modelElement).getRespDef();
                    if (resp != null)
                        resp.eAdapters().add(this);
                }
            }

            // something changed; inform parent. not sure how useful this is anymore
            if (getParent() != null) {
                ((URNDiagramEditPart) getParent()).notifyChanged(notification);
                refreshVisuals();
            }
        } else if (type == Notification.ADD || type == Notification.ADD_MANY || type == Notification.REMOVE || type == Notification.REMOVE_MANY){
            refreshVisuals();
        }

    }

    /**
     * Opens the direct edit manager.
     * 
     */
    protected void performDirectEdit() {
        if (manager == null) {
            LabelFigure figure = (LabelFigure) getFigure();

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return ""; //$NON-NLS-1$
                }
            };

            manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(figure), figure, validator);
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

            // set the label's text
            setLabelText();

            //If the element is a Responsability or a Component and has a URNlink associate, set the background gray
            if (modelElement instanceof UCMmodelElement){
                UCMmodelElement ucmElem;
                if (modelElement instanceof RespRef){
                    ucmElem = ((RespRef)modelElement).getRespDef();
                } else if (modelElement instanceof ComponentRef){
                    ucmElem = (UCMmodelElement)((ComponentRef)modelElement).getContDef();
                } else {
                    ucmElem = (UCMmodelElement)modelElement;
                }
                
                if (ucmElem.getToLinks().size() > 0){
                    //If there is a link, add the icon if not already added
                    if (iconImg == null) {
                        iconImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/urnlink.gif")).createImage();
                        labelFigure.setIcon(iconImg);
                    }
                    //Verify if there is an evaluation level if it is an IntentionalElement
                    //We only support 1 link from UCM element
                    
                    URNmodelElement grlElem = ((URNlink)ucmElem.getToLinks().get(0)).getFromElem();
                    
                    if ( grlElem instanceof IntentionalElement){
                        if (EvaluationStrategyManager.getInstance().getEvaluationStrategy() != null){
                            int eval = EvaluationStrategyManager.getInstance().getEvaluation((IntentionalElement)grlElem);
                            labelFigure.setAdditionalText(String.valueOf(eval));
                        } else {
                            labelFigure.setAdditionalText("");
                        }
                    } else {
                        labelFigure.setAdditionalText("");
                    }
                } else {
                    //Remove the icon and remove the additional text
                    if (iconImg != null) {
                        iconImg.dispose();
                        iconImg = null; 
                        labelFigure.setAdditionalText("");
                    }
                    
                }
            } 
            
            // The position of the new figure
            Point location;
            Dimension newLabelDimension;
            if (getParent() != null) {
                Dimension dimEditableLabel = labelFigure.getPreferredSize().getCopy();

                // If the associate element is a GRL node, we want to fix size of this element based on
                // the size of the label text
                /*
                 * if (modelElement instanceof GRLNode){ GRLNode grlnode = (GRLNode)modelElement;
                 * 
                 * //Set the size of the elements based on the size of the text
                 * 
                 * //Max size available for the label int width = ModelCreationFactory.DEFAULT_GRL_NODE_WIDTH - LABEL_GRL_PADDING_X - LABEL_PADDING_X; int
                 * height = ModelCreationFactory.DEFAULT_GRL_NODE_HEIGHT - LABEL_GRL_PADDING_Y - LABEL_PADDING_Y;
                 * 
                 * //Loop until we have good dimension for the labels to fit in the node while (dimEditableLabel.width > (width*
                 * Math.floor(height/dimEditableLabel.height))){ height = height + 10; width = width + 20; } newLabelDimension = new Dimension(width +
                 * LABEL_PADDING_X, height + LABEL_PADDING_Y);
                 * 
                 * //Define size of the node to have the label int nodewidth = newLabelDimension.width + LABEL_GRL_PADDING_X; int nodeheight =
                 * newLabelDimension.height + LABEL_GRL_PADDING_Y;
                 * 
                 * if(grlnode.getWidth() != nodewidth){ grlnode.setWidth(nodewidth); } if (grlnode.getHeight() != nodeheight){ grlnode.setHeight(nodeheight); } }
                 * else{
                 */
                newLabelDimension = new Dimension(dimEditableLabel.width + LABEL_PADDING_X, dimEditableLabel.height + LABEL_PADDING_Y);
                location = calculateModelElementPosition(getModelObj(), newLabelDimension);
            } else {
                // happens in mass deletions; random unimportant location.
                location = new Point(0, 0);
                newLabelDimension = new Dimension(100, 100);
            }

            Rectangle bounds = new Rectangle(location, newLabelDimension);
            labelFigure.setBounds(bounds);

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
        tableFigure.setVisible(true);
        refreshVisuals();
    }

    /**
     * Sets the label's text, given its referenced model element.
     */
    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();

        if (modelElement instanceof IURNContainerRef) { // use definition
            IURNContainer componentElement = (IURNContainer) ((IURNContainerRef) modelElement).getContDef();
            if (componentElement != null)
                labelFigure.setEditableText(((URNmodelElement) componentElement).getName());
            // componentref labels are in bold.
            labelFigure.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        } else if (modelElement instanceof RespRef) { // use definition
            Responsibility responsibility = ((RespRef) modelElement).getRespDef();
            if (responsibility != null)
                labelFigure.setEditableText(responsibility.getName());
        } else if (modelElement instanceof URNmodelElement) { // use element's name directly.
            labelFigure.setEditableText(((URNmodelElement) modelElement).getName());
        }

    }
}