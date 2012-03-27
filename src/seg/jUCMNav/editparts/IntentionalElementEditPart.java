package seg.jUCMNav.editparts;

import grl.Decomposition;
import grl.ElementLink;
import grl.Evaluation;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
import grl.kpimodel.Indicator;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.directEditPolicy.GrlNodeDirectEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.IntentionalElementNodeEditPolicy;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.GrlNodeFeedbackEditPolicy;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.GrlNodeFigure;
import seg.jUCMNav.figures.IntentionalElementFigure;
import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.property.IntentionalElementPropertySource;
import urn.URNspec;
import urncore.IURNConnection;
import urncore.IURNNode;

/**
 * EditPart for all IntentialElementRef. It listen for changes in the references and the definitions
 * 
 * @author Jean-Francois Roy, sghanava, jkealey
 * 
 */
public class IntentionalElementEditPart extends GrlNodeEditPart implements NodeEditPart {

    private Label evaluationLabel;

    private Label kpiEvaluationValueLabel;

    private Image evaluationImg;

    /**
     * 
     * @param model
     *            the intentional element ref to draw
     */
    public IntentionalElementEditPart(IURNNode model) {
        super();
        setModel(model);
    }

    /**
     * We need to listen for the reference and the definition for intentionalElement
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getNode() instanceof IntentionalElementRef && (getNode()).getDef() != null) {
            (getNode()).getDef().eAdapters().add(this);
        }

        // listen to reference
        super.activate();
    }

    /**
     * Create the edit policies.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GrlNodeFeedbackEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new IntentionalElementNodeEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new GrlNodeDirectEditPolicy());
    }

    /**
     * Create the GrlNode figure and associated evaluation labels.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        IntentionalElementFigure fig = new IntentionalElementFigure();

        evaluationLabel = new Label();
        evaluationLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        evaluationLabel.setVisible(false);

        evaluationLabel.setSize(100, 16);  // resized from 60, 16
        evaluationLabel.setTextAlignment(PositionConstants.LEFT);
        kpiEvaluationValueLabel = new Label();
        kpiEvaluationValueLabel.setForegroundColor(ColorManager.BLUE);
        kpiEvaluationValueLabel.setVisible(false);
        kpiEvaluationValueLabel.setSize(70, 16);

        try {
            ((ScalableFigure) ((FreeformLayeredPane) ((FreeformViewport) ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0))
                    .getChildren().get(0)).add(evaluationLabel);
            ((ScalableFigure) ((FreeformLayeredPane) ((FreeformViewport) ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0))
                    .getChildren().get(0)).add(kpiEvaluationValueLabel);
        } catch (Exception ex) {
            System.out.println("problem with scaling grl evaluation label"); //$NON-NLS-1$
            // bug 435: old code.. hoping new code is more robust
            ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().add(evaluationLabel);
            ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().add(kpiEvaluationValueLabel);
        }
        return fig;
    }

    /**
     * Overriding because we also have to listen to the Component definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            // bug 435: ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().remove(evaluationLabel);
            ((ScalableFigure) ((FreeformLayeredPane) ((FreeformViewport) ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0))
                    .getChildren().get(0)).remove(evaluationLabel);
            if (getNode() instanceof IntentionalElementRef && (getNode()).getDef() != null)
                (getNode()).getDef().eAdapters().remove(this);
        }
        super.deactivate();

    }

    /**
     * When nodes are dragged in GEF, they explicitly remove connections from being possible drop targets. By overriding DragEditPartsTracker, we allow this
     * behaviour.
     * 
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return new DragPathNodeTracker(this);
    }

    /**
     * 
     * @return the intentional element.
     */
    private IntentionalElementRef getNode() {
        return (IntentionalElementRef) getModel();
    }

    /**
     * @return The node's figure
     */
    public IntentionalElementFigure getNodeFigure() {
        return (IntentionalElementFigure) getFigure();
    }

    /**
     * @return a IntentionalElementPropertySource
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new IntentionalElementPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    protected List getModelSourceConnections() {
        return getNode().getSucc();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    protected List getModelTargetConnections() {
        return getNode().getPred();
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

        if (connection instanceof LinkRefEditPart && (((LinkRefEditPart) connection).getLinkRef().getLink() instanceof Decomposition)) {
            return getNodeFigure().getDecompositionTarget();
        } else {
            return getNodeFigure().getConnectionAnchor();
        }
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
    	if (getParent() == null || getNode().getDef() == null)
    		return;
    	refreshTargetConnections();
    	refreshSourceConnections();
    	refreshVisuals();

    	int featureId = notification.getFeatureID(GrlPackage.class);
    	if (featureId == GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE || featureId == GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY
    			|| featureId == GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY || featureId == GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE) {
    		EvaluationStrategyManager.getInstance().calculateEvaluation();
    	}

    	EList linksDest = getNode().getDef().getLinksDest();
    	if (linksDest != null){
    		for (Iterator iter = linksDest.iterator(); iter.hasNext();) {
    			ElementLink link = (ElementLink) iter.next();
    			for (Iterator iRef = link.getRefs().iterator(); iRef.hasNext();) {
    				LinkRef ref = (LinkRef) iRef.next();
    				LinkRefEditPart refEditPart = (LinkRefEditPart) getViewer().getEditPartRegistry().get(ref);
    				if (refEditPart != null) {
    					refEditPart.refreshVisuals();
    				}
    			}
    		} 
    	}


    	EList linksSrc = getNode().getDef().getLinksSrc();
    	if (linksSrc != null){
    		for (Iterator iter = linksSrc.iterator(); iter.hasNext();) {
    			ElementLink link = (ElementLink) iter.next();
    			for (Iterator iRef = link.getRefs().iterator(); iRef.hasNext();) {
    				LinkRef ref = (LinkRef) iRef.next();
    				LinkRefEditPart refEditPart = (LinkRefEditPart) getViewer().getEditPartRegistry().get(ref);
    				if (refEditPart != null) {
    					refEditPart.refreshVisuals();
    				}
    			}
    		}
    	}

    	// we want the top level editpart to refresh its children so that the largest components are always in the back.
    	if (notification.getEventType() == Notification.SET && getParent() != null)
    		((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /**
     * Refresh the figure and its associated labels.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (evaluationLabel == null)
            return;
        
        evaluationLabel.setForegroundColor(ColorManager.LINE);
        
        int width = MetadataHelper.getIntMetaData(getNode(), "_width", 0);
        int height = MetadataHelper.getIntMetaData(getNode(), "_height", 0);
        
        ((GrlNodeFigure)figure).setAutoResize(width == 0 || height == 0);
        ((GrlNodeFigure)figure).setBounds(new Rectangle(getNode().getX(), getNode().getY(), width, height));

        setText();

        // set information for specific drawing
        if ((getNode()).getDef() != null && ((getNode()).getDef() instanceof IntentionalElement)) {
            IntentionalElement elem = (getNode()).getDef();
            ((IntentionalElementFigure) figure).setType(elem.getType().getValue());

            // Set the tool tip
            UrnMetadata.setToolTip(elem, getNodeFigure());

            // Set the line color and fill color. Option only available in design view
            if (getParent() == null || !((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()) {
                ((IntentionalElementFigure) figure).setColors(getNode().getDef().getLineColor(), getNode().getDef().getFillColor(), getNode().getDef()
                        .isFilled());
                ((IntentionalElementFigure) figure).setLineStyle(SWT.LINE_SOLID);
                if( EvaluationStrategyManager.getInstance().isConditionResource( getNode().getDef() ) ) {
                    figure.setForegroundColor(ColorManager.AQUA);
                }
                ((IntentionalElementPropertySource) getPropertySource()).setEvaluationStrategyView(false);
                if (getNode().getFromLinks().size() + getNode().getToLinks().size() + elem.getFromLinks().size() + elem.getToLinks().size() > 0) {
                    evaluationLabel.setText(""); //$NON-NLS-1$
                    setUrnLinkIcon(getNode(), elem);
                    Point position = getNodeFigure().getLocation();
                    position.y = position.y - 16;
                    position.x = position.x - 8;
                    evaluationLabel.setLocation(position);
                    evaluationLabel.setVisible(true);

                    kpiEvaluationValueLabel.setText("");//$NON-NLS-1$
                    Point kpiEvalPosition = getNodeFigure().getLocation();
                    kpiEvalPosition.y = kpiEvalPosition.y - 28;
                    kpiEvalPosition.x = kpiEvalPosition.x - 8;
                    kpiEvaluationValueLabel.setLocation(kpiEvalPosition);
                    kpiEvaluationValueLabel.setVisible(false);
                } else {
                    evaluationLabel.setIcon(null);
                    evaluationLabel.setVisible(false);
                    kpiEvaluationValueLabel.setVisible(false);
                }

            } else {
                // Set strategy view to true
                ((IntentionalElementPropertySource) getPropertySource()).setEvaluationStrategyView(true);
                // Get the evaluation value
                Evaluation evaluation = EvaluationStrategyManager.getInstance().getDisplayEvaluationObject(getNode().getDef());
                boolean ignored = EvaluationStrategyManager.getInstance().isIgnored(getNode().getDef());
                
                if (evaluation != null) {
                    if (StrategyEvaluationPreferences.getFillElements()) {
                       String color, lineColor;
                        if( ignored ) { // set to light gray color
                        	color = "169,169,169"; //$NON-NLS-1$
                        /*} else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.NONE) {
                            color = "255,255,127"; //$NON-NLS-1$*/
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.CONFLICT) {
                            color = "0,255,255"; //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.UNDECIDED) {
                            color = "192,192,192"; //$NON-NLS-1$
                        } else {
                        	int evalValue = evaluation.getEvaluation();
                            URNspec urn = null;
                            if (getNode()!=null && getNode().getDef()!=null && getNode().getDef().getGrlspec()!=null)
                                urn = getNode().getDef().getGrlspec().getUrnspec();
                            // if 0,100, convert back to -100,100 to have the right color. 
                            evalValue = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable(urn,  evalValue);

                            
                            if( EvaluationStrategyManager.getInstance().displayDifferenceMode() && !StrategyEvaluationPreferences.getVisualizeAsPositiveRange(urn)) {
                            	evalValue /= 2;
                            }
                            int partial = (Math.abs((Math.abs(evalValue) - IGRLStrategyAlgorithm.SATISFICED)) * 160 / IGRLStrategyAlgorithm.SATISFICED) + 96;
                            partial = limit( partial );
                            
                            if (evalValue < IGRLStrategyAlgorithm.NONE) {
                                color = "255," + partial + ",96"; //$NON-NLS-1$ //$NON-NLS-2$
                            } else {
                                color = partial + ",255,96"; //$NON-NLS-1$
                            }
                        }

                        if( EvaluationStrategyManager.getInstance().isConditionResource( getNode().getDef() ) ) {
                        	lineColor = "0,100,100"; //$NON-NLS-1$ Color AQUA
                        } else {
                        	lineColor = "0,0,0"; //$NON-NLS-1$
                        }

                        if (evaluation.getStrategies() != null) {
                            if (!evaluation.getIntElement().getLinksDest().isEmpty()) {
                                // This initial evaluation potentially overrides computed ones
                                // Highlight in a different color, dark red.
                                lineColor = "160,0,0"; //$NON-NLS-1$
                            }
                            ((IntentionalElementFigure) figure).setLineStyle(SWT.LINE_DASH);
                            if (elem.getType() == IntentionalElementType.INDICATOR_LITERAL) {
                                // Special case for indicators... no dashed lines as they are always initialized.
                                ((IntentionalElementFigure) figure).setLineStyle(SWT.LINE_SOLID);
                            }
                            
                        } else {
                            ((IntentionalElementFigure) figure).setLineStyle(SWT.LINE_SOLID);
                        }
                        
                        if( ignored ){
                        	lineColor = "69,69,69"; //$NON-NLS-1$
                            ((IntentionalElementFigure) figure).setLineStyle(SWT.LINE_DOT);
                            evaluationLabel.setForegroundColor(ColorManager.GRAY);
                        }
                        
                        ((IntentionalElementFigure) figure).setColors(lineColor, color, true);
                    }

                    int evalType = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm().getEvaluationType();

                    String text = (evaluation.getStrategies() != null ? "(*)" : ""); //$NON-NLS-1$ //$NON-NLS-2$

                    if (evalType == IGRLStrategyAlgorithm.EVAL_MIXED || evalType == IGRLStrategyAlgorithm.EVAL_QUANTITATIVE
                            || evalType == IGRLStrategyAlgorithm.EVAL_FORMULA || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER || evalType == IGRLStrategyAlgorithm.EVAL_CONDITION ) {
                        int val = evaluation.getEvaluation();
                        //val = StrategyEvaluationPreferences.getValueToVisualize(val);

                        String evalStr = String.valueOf(val);
                        
                        if (evaluation.getEvalRange()!=null && (evaluation.getStrategies() == null || evaluation.getStrategies() == EvaluationStrategyManager.getInstance().getEvaluationStrategy()) )
                        {
                            evalStr = evalStr + " [" + evaluation.getEvalRange().getStart() + ".." + evaluation.getEvalRange().getEnd() + "] ";
                        }

                        text = evalStr + text; //$NON-NLS-1$
                        if( EvaluationStrategyManager.getInstance().displayDifferenceMode() ) {
                        	text = '<' + text + '>'; // add angle brackets to signify strategy difference mode
                        }
                        
                    }
//                    evaluationLabel.setFont(f)
                    if (GeneralPreferencePage.getGrlSatisfactionTextVisible())
                        evaluationLabel.setText(text);
                    else 
                        evaluationLabel.setText("");//$NON-NLS-1$
                    

                    Point position = getNodeFigure().getLocation();
                    position.y = position.y - 16;

                    // Take zoom factor into consideration to fix the label position
                    double zoomLevel = ((ZoomManager) ((ScrollingGraphicalViewer) getViewer()).getProperty(ZoomManager.class.toString())).getZoom();

                    evaluationLabel.setLocation(position);
                    evaluationLabel.setVisible(true);

                    if (evalType == IGRLStrategyAlgorithm.EVAL_FORMULA) {
                        String kpiText = "";//$NON-NLS-1$                       
                        if ((getNode()).getDef() != null && ((getNode()).getDef() instanceof Indicator)) {
                            if (null != evaluation.getKpiEvalValueSet()) {
                                double kpiValue = evaluation.getKpiEvalValueSet().getEvaluationValue();
                                DecimalFormat df = new  DecimalFormat ("0.##"); //$NON-NLS-1$
                                kpiText = df.format(kpiValue) + " " + evaluation.getKpiEvalValueSet().getUnit(); //$NON-NLS-1$
                                kpiEvaluationValueLabel.setText(kpiText);
                                Point kpiEvalPosition = getNodeFigure().getLocation();
                                kpiEvalPosition.y = kpiEvalPosition.y - 28;

                                kpiEvaluationValueLabel.setLocation(kpiEvalPosition);
                                kpiEvaluationValueLabel.setVisible(true);
                            }
                        }
                    }
                    if (evalType == IGRLStrategyAlgorithm.EVAL_CONDITION) {
                    	
                    }

                    if (evalType == IGRLStrategyAlgorithm.EVAL_QUALITATIVE || evalType == IGRLStrategyAlgorithm.EVAL_MIXED || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {
                        // Set the label icon
                        if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.DENIED) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/denied.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() > IGRLStrategyAlgorithm.DENIED && evaluation.getEvaluation() < IGRLStrategyAlgorithm.NONE) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/wdenied.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.NONE) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/none.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() > IGRLStrategyAlgorithm.NONE && evaluation.getEvaluation() < IGRLStrategyAlgorithm.SATISFICED) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/wsatisficed.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.SATISFICED) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/satisficed.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.CONFLICT) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/conflict.gif")); //$NON-NLS-1$
                        } else if (evaluation.getEvaluation() == IGRLStrategyAlgorithm.UNDECIDED) {
                            evaluationImg = (JUCMNavPlugin.getImage("icons/undecided.gif")); //$NON-NLS-1$
                        }
                        if (evaluationImg != null && GeneralPreferencePage.getGrlSatisfactionIconVisible()) {
                            evaluationLabel.setIcon(evaluationImg);
                        }
                        else
                            evaluationLabel.setIcon(null);
                        
                    } else {
                        setUrnLinkIcon(getNode(), elem);

                    }
                }
                refreshConnections();
            }
        }

        // Make the label recenter itself.
        figure.validate();

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        // if (getParent() != null)
        // (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }

    private int limit( int value ) {
    	if( value < 0 ) {
    		value = 0;
    	} else if( value > 255 ) {
    		value = 255;
    	}
    	return value;
    }
    
    /**
     * Set the icon of the intentional element's label in case in has URN links.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
	private void setUrnLinkIcon(IntentionalElementRef ref, IntentionalElement elem) {
		if (elem.getFromLinks().size() + ref.getFromLinks().size() > 0) {
			if (elem.getToLinks().size() + ref.getToLinks().size() > 0)
				evaluationLabel.setIcon(JUCMNavPlugin.getImage("icons/urnlink-bidir.gif")); //$NON-NLS-1$
			else
				evaluationLabel.setIcon(JUCMNavPlugin.getImage("icons/urnlink.gif")); //$NON-NLS-1$
		}
		else 
		{
			if (elem.getToLinks().size() + ref.getToLinks().size() > 0)
				evaluationLabel.setIcon(JUCMNavPlugin.getImage("icons/urnlink-reversed.gif")); //$NON-NLS-1$
			else
				evaluationLabel.setIcon(null);
		}
	}

	
    /**
     * Refresh the successor edit parts.
     */
    private void refreshConnections() {
        for (Iterator iter = getNode().getSucc().iterator(); iter.hasNext();) {
            IURNConnection connect = (IURNConnection) iter.next();
            AbstractConnectionEditPart refEditPart = (AbstractConnectionEditPart) getViewer().getEditPartRegistry().get(connect);
            if (refEditPart != null) {
                refEditPart.refresh();
            }
        }
    }

    /**
     * Sets the label's text, given its referenced model element. Add stereotypes if any.
     */
    private void setText() {
        if (getNode().getDef() != null) {
            String stereotypes = UrnMetadata.getStereotypes(getNode().getDef());
            String name = getNode().getDef().getName();

            // Handle importance annotation
            String importance = ""; //$NON-NLS-1$
            int evalType = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm().getEvaluationType();
            if (evalType == IGRLStrategyAlgorithm.EVAL_MIXED || evalType == IGRLStrategyAlgorithm.EVAL_QUANTITATIVE 
                                                             || evalType == IGRLStrategyAlgorithm.EVAL_FORMULA
                                                             || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {
                if (getNode().getDef().getImportanceQuantitative() > 0) {
                    importance = "  (" + String.valueOf(getNode().getDef().getImportanceQuantitative()) + ")"; //$NON-NLS-1$ //$NON-NLS-2$  $NON-NLS-2$
                }
            } else if (evalType == IGRLStrategyAlgorithm.EVAL_QUALITATIVE) {
                switch (getNode().getDef().getImportance().getValue()) {
                case 0:
                    importance = "  (H)"; //$NON-NLS-1$
                    break;
                case 1:
                    importance = "  (M)"; //$NON-NLS-1$
                    break;
                case 2:
                    importance = "  (L)"; //$NON-NLS-1$
                }
            }
            // Roy's algo does not use importance but priority and criticality... Deprecated.

            getNodeFigure().setEditableText(name + importance + stereotypes);
        }
    }
}
