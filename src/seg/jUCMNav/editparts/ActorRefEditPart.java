/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.Actor;
import grl.ActorRef;

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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ActorRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ComponentFeedbackEditPolicy;
import seg.jUCMNav.figures.ActorFigure;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.ContainerPropertySource;

/**
 * Edit part for the Actor Ref, who listen for changes in both ref and def
 * @author Jean-François Roy
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
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorRefComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ComponentFeedbackEditPolicy());
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        evaluationLabel = new Label();
        evaluationLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        evaluationLabel.setVisible(false);

        evaluationLabel.setSize(50,16);
        evaluationImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Actor16.gif")).createImage(); //$NON-NLS-1$
        evaluationLabel.setIcon(evaluationImg);
        try {
        	((ScalableFigure)((FreeformLayeredPane)((FreeformViewport)((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0)).getChildren().get(0)).add(evaluationLabel);
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
        if (isActive()){
            if (evaluationImg != null) {
                evaluationImg.dispose();
                evaluationImg = null;
            }
            evaluationLabel.setVisible(false);
            //((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure().remove(evaluationLabel);
            ((ScalableFigure)((FreeformLayeredPane)((FreeformViewport)((GrlConnectionOnBottomRootEditPart) getRoot()).getFigure()).getChildren().get(0)).getChildren().get(0)).remove(evaluationLabel);
            if (getActorRef().getContDef() != null){
                getActorRef().getContDef().eAdapters().remove(this);
            }
        }
         
        //stop listenening to reference
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
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /* 
     * (non-Javadoc)
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

        try{
            // set information for specific drawing
            if (getActorRef().getContDef() instanceof Actor) {
                Actor actor = (Actor) getActorRef().getContDef();
                if (!((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()){
                    ((ActorFigure) figure).setColors(actor.getLineColor(), actor.getFillColor(), actor.isFilled());
                } else { 
                    ((ActorFigure) figure).setColors("75,75,75", actor.getFillColor(), actor.isFilled()); //$NON-NLS-1$
                }
                
            }
            if (!((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()){
                evaluationLabel.setVisible(false);
            } else {
                //Calculate the actor evaluation
                evaluationLabel.setText(calculateEvaluation());
                evaluationLabel.setLocation(getActorFigure().getLocation());
                evaluationLabel.setVisible(true);
            }
        } catch (NullPointerException e){} //if the figure have been deleted, the root edit part is not accessible anymore
        

        //   Make the label recenter itself.
        figure.validate(); 

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        if (getParent() != null)
            (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }
    
    public String calculateEvaluation(){
        return String.valueOf(EvaluationStrategyManager.getInstance().getActorEvaluation(((Actor)getActorRef().getContDef())));
    }

}
