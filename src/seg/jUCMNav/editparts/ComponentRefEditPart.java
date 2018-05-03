package seg.jUCMNav.editparts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts.DynamicContextTraversalEvaluation;
import seg.jUCMNav.editpolicies.element.ComponentRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ComponentFeedbackEditPolicy;
import seg.jUCMNav.figures.ComponentRefFigure;
import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import seg.jUCMNav.views.property.ContainerPropertySource;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import urncore.Component;
import urncore.Metadata;

/**
 * EditPart for all ComponentRefs. They listen to changes in both the reference and the definition.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ComponentRefEditPart extends ModelElementEditPart implements Adapter {

	public static DynamicContextTraversalEvaluation te;

    /***
     * 
     * @param model
     *            the component ref to draw
     * @param map
     *            the map in which it is contained.
     */
    public ComponentRefEditPart(ComponentRef model, UCMmap map) {
        super();
        setModel(model);
    }

    /**
     * Overriding because we also have to listen to the Component definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getComponentRef().getContDef() != null)
            getComponentRef().getContDef().eAdapters().add(this);

        // listen to reference
        super.activate();
    }

    /**
     * Installs edit policies.
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentRefComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ComponentFeedbackEditPolicy());
    }

    /**
     * Currently, all components are represented by Rectangles.
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        return new ComponentRefFigure();
    }

    /**
     * Overriding because we also have to listen to the Component definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getComponentRef().getContDef() != null)
            getComponentRef().getContDef().eAdapters().remove(this);

        // stop listening to reference
        super.deactivate();

    }

    /**
     * 
     * @return the component ref to draw
     */
    private ComponentRef getComponentRef() {
        return (ComponentRef) getModel();
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
     * When the model has changed, refresh this reference but also the container.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /**
     * Draws the figure. Currently, all Components are represented by rectangles. The Component Kind is not observed. If no fill color has been defined, leave
     * transparent. If no line color has been defined, use black.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getComponentRef().getX(), getComponentRef().getY());
        // its size
        Dimension size = new Dimension(getComponentRef().getWidth(), getComponentRef().getHeight());
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);

        // set information for specific drawing
        if (getComponentRef().getContDef() instanceof Component) {
            Component comp = (Component) getComponentRef().getContDef();
            ((ComponentRefFigure) figure).setKind(comp.getKind().getValue());
            ((ComponentRefFigure) figure).setColors(comp.getLineColor(), comp.getFillColor(), comp.isFilled());
            // Set the tool tip
            UrnMetadata.setToolTip(comp, figure);

			if (ScenarioUtils.getActiveScenario(comp) != null) {

				List<ComponentRef> includedComponentReferences = ScenarioUtils.getComponentReferencesRecursively(comp);
				for (ComponentRef cr : includedComponentReferences) {

					List<PathNode> nodes = (List<PathNode>) cr.getNodes();
					String hits = null;
					for (PathNode p : nodes) {
						if (p instanceof RespRef) {
							String deactStatus = MetadataHelper.getMetaData(p, EvaluationStrategyManager.METADATA_DEACTSTATUS);
							if (deactStatus != null && deactStatus.equalsIgnoreCase("true") && ScenarioTraversalPreferences.getIsTimedUcmEnabled())
								hits = "0";
							else
								hits = Integer.toString(ScenarioUtils.getTraversalHitCount(p));

							Metadata metaHitCount = MetadataHelper.getMetaDataObj(p, PathNodeEditPart.METADATA_HITS);
							if (metaHitCount != null)
								metaHitCount.setValue(hits);
							else
								MetadataHelper.addMetaData(p.getDiagram().getUrndefinition().getUrnspec(), p, PathNodeEditPart.METADATA_HITS, hits);

							if (DynamicContextsView.te != null && te !=null && te.timePointGroupSelected == true && ScenarioTraversalPreferences.getIsTimedUcmEnabled()) {
								if (p instanceof RespRef) {
									MetadataHelper.removeMetaData(p, PathNodeEditPart.METADATA_HITS);
									MetadataHelper.removeMetaData(((RespRef) p).getRespDef(), PathNodeEditPart.METADATA_HITS);
								}
							}
						}	
					}
				}
			}

            //For TimedUCM
            String deactStatus = MetadataHelper.getMetaData(comp, EvaluationStrategyManager.METADATA_DEACTSTATUS);
            if (deactStatus != null && deactStatus.equalsIgnoreCase("true")&& ScenarioTraversalPreferences.getIsTimedUcmEnabled())
                    ((ComponentRefFigure) figure).setColors("169,169,169", comp.getLineColor(), comp.isFilled());
            else
                    ((ComponentRefFigure) figure).setColors("25,25,25", comp.getLineColor(), comp.isFilled());
            
        }

        // Make the label recenter itself.
        figure.validate();

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        if (getParent() != null && getViewer() != null && getLayer(URNRootEditPart.COMPONENT_LAYER) != null)
            (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);


		if (DynamicContextsView.te != null && te !=null && te.timePointGroupSelected == true && ScenarioTraversalPreferences.getIsTimedUcmEnabled()) {
			Metadata metaHitCount = MetadataHelper.getMetaDataObj((Component) getComponentRef().getContDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
			if (metaHitCount != null)
				MetadataHelper.removeMetaData((Component) getComponentRef().getContDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
		}
    }
}
