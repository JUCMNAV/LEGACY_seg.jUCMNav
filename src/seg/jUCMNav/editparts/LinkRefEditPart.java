package seg.jUCMNav.editparts;

import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.LinkRef;
import grl.LinkRefBendpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.element.LinkRefBendpointEditPolicy;
import seg.jUCMNav.editpolicies.element.LinkRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ConnectionFeedbackEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.LinkRefConnection;
import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.LinkRefPropertySource;
import urncore.IURNDiagram;
import urncore.UrncorePackage;

/**
 * Edit part associate with the LinkRef in GRL diagram
 * 
 * @author Jean-Fran�ois Roy, sghanava
 * 
 */
public class LinkRefEditPart extends AbstractConnectionEditPart {

    Adapter adapter;
    private final IURNDiagram diagram;
    protected IPropertySource propertySource = null;

    private Image img;
    private Label decompLabel, contributionLabel, stereotypeLabel;

    /**
     * The Edit Part for LinkRefs
     */
    public LinkRefEditPart(LinkRef link, final IURNDiagram diagram) {
        super();
        setModel(link);
        this.diagram = diagram;
        final LinkRefEditPart part = this;
        
        adapter = new Adapter()
        {
            @Override
            public void notifyChanged(Notification notification) {
                int type = notification.getEventType();
                int featureId = notification.getFeatureID(GrlPackage.class);
                if (type == Notification.ADD || type == Notification.REMOVE) {
                    if (featureId == GrlPackage.LINK_REF__BENDPOINTS) {
                        refreshVisuals();
                    }
                    if (featureId == GrlPackage.ELEMENT_LINK) {
                        EvaluationStrategyManager.getInstance().calculateEvaluation();
                    }
                } else if (type == Notification.SET) { // If a modification to the properties have been done
                    if (featureId == GrlPackage.ELEMENT_LINK) {
                        EvaluationStrategyManager.getInstance().calculateEvaluation();
                    }
                    refreshVisuals();
                }
                if(featureId == UrncorePackage.IURN_CONNECTION__LABEL) {
                    Object grlGraphEditPart = part.getViewer().getEditPartRegistry().get(diagram);
                    if (grlGraphEditPart != null)
                        ((GrlGraphEditPart) grlGraphEditPart).notifyChanged(notification);
                }
            }

            /**
             * @see org.eclipse.emf.common.notify.Adapter#getTarget()
             */
            public Notifier getTarget() {
                return null;
            }

            /**
             * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
             */
            public boolean isAdapterForType(Object type) {
                return type.equals(getModel().getClass());
            }
            /**
             * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
             */
            public void setTarget(Notifier newTarget) {
            }
        };
    }

    /**
     * We want to listen for the reference and definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            ((EObject) getModel()).eAdapters().add(adapter);

            if (getModel() instanceof LinkRef && ((LinkRef) getModel()).getLink() != null) {
                ((LinkRef) getModel()).getLink().eAdapters().add(adapter);
            }
        }
        super.activate();
    }

    /**
     * Create Edit Policies
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // Remove the ConnectionEndPoints role because we did want user to be able to reconnect Links
        // installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new LinkRefBendpointEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ConnectionFeedbackEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LinkRefComponentEditPolicy());
    }

    /**
     * Creates a Connection and adds appropriate decorations.
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        LinkRefConnection connection = new LinkRefConnection();
        connection.setRoutingConstraint(getLinkRef());

        ConnectionEndpointLocator ce = new ConnectionEndpointLocator(connection, true);
        ce.setUDistance(0);
        ce.setVDistance(0);

        decompLabel = new Label();
        decompLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        decompLabel.setOpaque(true);
        connection.add(decompLabel, ce);
        decompLabel.setVisible(false);

        // Create the contribution label
        ConnectionEndpointLocator contribce = new ConnectionEndpointLocator(connection, true);
        contribce.setUDistance(10);
        contribce.setVDistance(10);

        contributionLabel = new Label();
        contributionLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        connection.add(contributionLabel, contribce);
        contributionLabel.setVisible(false);

        // Create the stereotype label
        ConnectionEndpointLocator stereotypece = new ConnectionEndpointLocator(connection, false);
        stereotypece.setUDistance(20);
        stereotypece.setVDistance(10);

        stereotypeLabel = new Label();
        stereotypeLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        connection.add(stereotypeLabel, stereotypece);
        stereotypeLabel.setVisible(false);

        return connection;
    }
    
    public Connection getConnectionFigure() {
        return (Connection)getFigure();
    }

    /**
     * Removes the adapter.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {

            // if (img != null) {
            // img.dispose();
            // img = null;
            // }
            ((EObject) getModel()).eAdapters().remove(adapter);
            if (getModel() instanceof LinkRef && ((LinkRef) getModel()).getLink() != null) {
                ((LinkRef) getModel()).getLink().eAdapters().remove(adapter);
            }

        }
        super.deactivate();
    }

    /**
     * Returns a URNElementPropertySource
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (IPropertySource.class == adapter) {
            if (propertySource == null) {
                propertySource = new LinkRefPropertySource((EObject) getModel());
            }
            return propertySource;
        }
        return super.getAdapter(adapter);
    }

    /**
     * 
     * @return the diagram containing the connection.
     */
    public IURNDiagram getDiagram() {
        return diagram;
    }

    /**
     * Returns the LinkRef
     * 
     * @return LinkRef.
     */
    public LinkRef getLinkRef() {
        return (LinkRef) getModel();
    }

    /**
     * Returns the Figure associated with this
     * 
     * @return LinkRefFigure.
     */
    private LinkRefConnection getLinkRefFigure() {
        return (LinkRefConnection) getFigure();
    }

    /**
     * Is informed when the model changes.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

    }

    /**
     * Updates the bendpoints, based on the model.
     */
    protected void refreshBendpoints() {
        EList modelConstraint = getLinkRef().getBendpoints();

        List figureConstraint = new ArrayList();
        for (int i = 0; i < modelConstraint.size(); i++) {
            LinkRefBendpoint bendpoint = (LinkRefBendpoint) modelConstraint.get(i);
            AbsoluteBendpoint abp = new AbsoluteBendpoint(bendpoint.getX(), bendpoint.getY());
            figureConstraint.add(abp);
        }
        getConnectionFigure().setRoutingConstraint(figureConstraint);
    }

    /**
     * Refreshes the connections
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        refreshBendpoints();

//       System.out.println("refreshVisuals() called for LinkRef between " + getLinkRef().getLink().getSrc().getName() + " and " +  getLinkRef().getLink().getDest().getName() );
        
        // reset the label colors
        decompLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        contributionLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        stereotypeLabel.setForegroundColor(ColorManager.LINKREFLABEL);
        getLinkRefFigure().setForegroundColor(ColorManager.LINE);
        
        int evalType = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm().getEvaluationType();

        if (getLinkRef().getLink() instanceof Decomposition) {
            Decomposition decomp = (Decomposition) getLinkRef().getLink();
            IntentionalElement elem = (IntentionalElement) decomp.getDest();
            if (elem.getDecompositionType().getValue() == DecompositionType.AND) {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_DECOMPOSITION_AND);
            } else {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_DECOMPOSITION_OR);
            }
            if (decompLabel.getText() != elem.getDecompositionType().getName()) {
                decompLabel.setText(elem.getDecompositionType().getName());
                decompLabel.setVisible(true);
            }
        } else if (getLinkRef().getLink() instanceof Contribution) {
            Contribution contrib = (Contribution) getLinkRef().getLink();
            if (contrib.isCorrelation()) {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_CORRELATION);
            } else if (contrib instanceof OptionalFMLink) {
            	getLinkRefFigure().setType(LinkRefConnection.TYPE_OPTIONAL);
            } else if (contrib instanceof MandatoryFMLink) {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_MANDATORY);
            } else {
            	getLinkRefFigure().setType(LinkRefConnection.TYPE_CONTRIBUTION);
            }
            
            // Set the stereotype Label
            String stereotypeInfo = UrnMetadata.getStereotypes(contrib);
            if (stereotypeLabel.getText() != stereotypeInfo) {
                stereotypeLabel.setText(stereotypeInfo);
                if (stereotypeInfo.equals("")) { //$NON-NLS-1$
                    stereotypeLabel.setVisible(false);
                } else {
                    stereotypeLabel.setVisible(true);
                }
            }
        } else if (getLinkRef().getLink() instanceof Dependency) {
            // Dependency depend = (Dependency)getLinkRef().getLink();
            getLinkRefFigure().setType(LinkRefConnection.TYPE_DEPENDENCY);
        }
        
        // Check if link should be grayed out in strategy view
        if( getRoot()!=null && ((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView() ) {
            if (getLinkRef()!=null && getLinkRef().getLink()!=null) {
        	if( getLinkRef().getLink().getDest() instanceof IntentionalElement || getLinkRef().getLink().getSrc() instanceof IntentionalElement) {
            		if( EvaluationStrategyManager.getInstance().isIgnored( (IntentionalElement) getLinkRef().getLink().getDest() )
            				|| EvaluationStrategyManager.getInstance().isIgnored( (IntentionalElement) getLinkRef().getLink().getSrc()) ) {
            			decompLabel.setForegroundColor(ColorManager.GRAY);
            			contributionLabel.setForegroundColor(ColorManager.GRAY);
            			stereotypeLabel.setForegroundColor(ColorManager.GRAY);
            	        getLinkRefFigure().setForegroundColor(ColorManager.GRAY);
            		}
            	}
            }
        }

        UrnMetadata.setToolTip(getLinkRef().getLink(), figure);

    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        super.refresh();
    }

    @Override
    protected void refreshSourceAnchor() {
        // TODO Auto-generated method stub
        super.refreshSourceAnchor();
    }

    @Override
    protected void refreshTargetAnchor() {
        // TODO Auto-generated method stub
        super.refreshTargetAnchor();
    }
}
