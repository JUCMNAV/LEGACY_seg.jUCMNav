/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.Actor;
import grl.ActorRef;
import grl.QualitativeLabel;

import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ActorRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ComponentFeedbackEditPolicy;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.figures.ActorFigure;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.BatchEvaluationUtil;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.property.ContainerPropertySource;
import urn.URNspec;

/**
 * Edit part for the Actor Ref, who listen for changes in both ref and def
 * 
 * @author Jean-Fran�ois Roy, sghanava
 * 
 */
public class ActorRefEditPart extends ModelElementEditPart implements Adapter {

    private Label evaluationLabel;

    private Image evaluationImg;

    /**
     * Constructor of the edit part
     */
    public ActorRefEditPart(ActorRef model) {
        super();
        setModel(model);
    }

    /**
     * Overriding because we also have to listen to the Actor definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getActorRef().getContDef() != null)
            getActorRef().getContDef().eAdapters().add(this);

        // listen to reference
        super.activate();
    }

    /**
     * Creates edit policies
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorRefComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ComponentFeedbackEditPolicy());
    }

    /**
     * Creates the figure for actor refs, and also adds evaluation labels / icons to the background.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        evaluationLabel = new Label();
        evaluationLabel.setForegroundColor(ColorManager.LINKREFLABEL);

        evaluationLabel.setSize(78, 16); // increased from 58,16
        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor16.gif")); //$NON-NLS-1$
        evaluationLabel.setIcon(evaluationImg);
        evaluationLabel.setText(""); //$NON-NLS-1$
        evaluationLabel.setVisible(true);
        try {
            ((ScalableFigure) ((FreeformLayeredPane) ((FreeformViewport) ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0))
                    .getChildren().get(0)).add(evaluationLabel);
        } catch (Exception ex) {
            System.out.println("problem with scaling grl evaluation label"); //$NON-NLS-1$
            // bug 435: old code.. hoping new code is more robust.
            ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().add(evaluationLabel);
        }

        return new ActorFigure();
    }

    /**
     * Overriding because we also have to listen to the Actor definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            // if (evaluationImg != null) {
            // evaluationImg.dispose();
            // evaluationImg = null;
            // }
            // ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().remove(evaluationLabel);
            ((ScalableFigure) ((FreeformLayeredPane) ((FreeformViewport) ((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0))
                    .getChildren().get(0)).remove(evaluationLabel);
            if (getActorRef().getContDef() != null) {
                getActorRef().getContDef().eAdapters().remove(this);
            }
        }

        // stop listening to reference
        super.deactivate();
    }

    /**
     * 
     * @return the actor ref to draw
     */
    private ActorRef getActorRef() {
        return (ActorRef) getModel();
    }

    /**
     * @return The node's figure
     */
    public ActorFigure getActorFigure() {
        return (ActorFigure) getFigure();
    }

    /**
     * @return a ContainerPropertySource
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new ContainerPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /**
     * refresh and notify part.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /**
     * Refreshes {@link ActorFigure} and accompanying labels.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getActorRef().getX(), getActorRef().getY());
        // its size
        Dimension size = new Dimension(getActorRef().getWidth(), getActorRef().getHeight());
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);
        setText();

        IGRLStrategyAlgorithm algo = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm();
        int evalType = algo.getEvaluationType();

        try {
            // set information for specific drawing
            if (getActorRef().getContDef() instanceof Actor) {
                Actor actor = (Actor) getActorRef().getContDef();
                // Set the tool tip
                UrnMetadata.setToolTip(actor, figure);
                if (getParent() != null && !((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()) {
                    ((ActorFigure) figure).setColors(actor.getLineColor(), actor.getFillColor(), actor.isFilled());
                } else {
                    ((ActorFigure) figure).setColors("25,25,25", actor.getFillColor(), actor.isFilled()); //$NON-NLS-1$
                }

            }
            evaluationImg = (JUCMNavPlugin.getImage("icons/Actor16.gif")); //$NON-NLS-1$
            evaluationLabel.setText(""); //$NON-NLS-1$
            if (getParent() != null && ((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()) {
                // Calculate the actor evaluation
                String evaluation = calculateEvaluation();
                if (algo instanceof QuantitativeGRLStrategyAlgorithm || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {
                    if (GeneralPreferencePage.getGrlSatisfactionTextVisible())
                        evaluationLabel.setText(evaluation);
                    else
                        evaluationLabel.setText(""); //$NON-NLS-1$
                }
                evaluationLabel.setLocation(getActorFigure().getLocation());

                if (evalType == IGRLStrategyAlgorithm.EVAL_QUALITATIVE || evalType == IGRLStrategyAlgorithm.EVAL_MIXED
                        || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {

                    if (evalType == IGRLStrategyAlgorithm.EVAL_QUALITATIVE)
                        evaluationLabel.setText(null);
                    int evalInt = Integer.parseInt(evaluation);
                    // Set the label icon

                    URNspec urn = null;
                    if (getActorRef().getContDef() instanceof Actor && ((Actor) getActorRef().getContDef()).getGrlspec() != null)
                        urn = ((Actor) getActorRef().getContDef()).getGrlspec().getUrnspec();

                    QualitativeLabel ql = EvaluationStrategyManager.getQualitativeEvaluationForQuantitativeValue(urn, evalInt);
                    switch (ql.getValue()) {
                    case QualitativeLabel.DENIED:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-D-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.WEAKLY_DENIED:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-WD-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.WEAKLY_SATISFIED:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-WS-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.SATISFIED:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-S-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.CONFLICT:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-C-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.UNKNOWN:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-U-24x16.gif")); //$NON-NLS-1$
                        break;
                    case QualitativeLabel.NONE:
                        evaluationImg = (JUCMNavPlugin.getImage("icons/Actor-N-24x16.gif")); //$NON-NLS-1$
                        break;
                    }
                }
            }
            
            Actor actor = (Actor) getActorRef().getContDef();
            String _trendStr = MetadataHelper.getMetaData(actor, BatchEvaluationUtil.METADATA_TREND);

            if(_trendStr != null && ((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()) {
                int _trend = Integer.parseInt(_trendStr);
                Image icon = BatchEvaluationUtil.getIcon(_trend);
                if(icon != null)
                    evaluationLabel.setIcon(icon);
            }
            else {
                if (GeneralPreferencePage.getGrlSatisfactionIconVisible())
                    evaluationLabel.setIcon(evaluationImg);
                else
                    evaluationLabel.setIcon(null);
            }
            evaluationLabel.setLocation(getActorFigure().getLocation());

        } catch (NullPointerException e) {
        } // if the figure have been deleted, the root edit part is not accessible anymore

        // Make the label recenter itself.
        figure.validate();

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        if (getParent() != null)
            (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }

    /**
     * 
     * @return the evaluation to be displayed in the label.
     */
    public String calculateEvaluation() {
        int val = EvaluationStrategyManager.getInstance().getDisplayActorEvaluation(((Actor) getActorRef().getContDef()));
        // val = StrategyEvaluationPreferences.getValueToVisualize(val);
        if (EvaluationStrategyManager.getInstance().displayDifferenceMode()) {
            return '<' + String.valueOf(val) + '>'; // add angle brackets to signify strategy difference mode
        } else {
            return String.valueOf(val);
        }
    }

    private void setText() {
        if (getActorRef().getContDef() != null && getActorRef().getContDef() instanceof Actor) {
            Actor actor = (Actor) getActorRef().getContDef();

            String name = actor.getName();

            String importance = IntentionalElementEditPart.getImportanceSuffix(actor.getImportanceQuantitative(), actor.getImportance());

            getActorFigure().setEditableText(name + importance);
        }
    }
}
