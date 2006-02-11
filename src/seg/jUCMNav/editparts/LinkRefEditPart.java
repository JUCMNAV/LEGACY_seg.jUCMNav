/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.GrlPackage;
import grl.LinkRef;
import grl.LinkRefBendpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
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
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.LinkRefBendpointEditPolicy;
import seg.jUCMNav.editpolicies.element.LinkRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ConnectionFeedbackEditPolicy;
import seg.jUCMNav.figures.LinkRefConnection;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.property.LinkRefPropertySource;
import urncore.IURNDiagram;


/**
 * Edit part associate with the LinkRef in GRL diagram
 * @author Jean-François Roy
 *
 */
public class LinkRefEditPart extends AbstractConnectionEditPart{

    /**
     * Because GEF's AbstractConnectionEditPart has methods conflicting with EMF's Adapter, we needed an internal class to act as a listener.
     * 
     */
    private class ElementLinkAdapter implements Adapter {
        private Notifier target;

        public ElementLinkAdapter(Notifier target) {
            this.target = target;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        public Notifier getTarget() {
            return target;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
         */
        public boolean isAdapterForType(Object type) {
            return type.equals(getModel().getClass());
        }

        /**
         * When connection's condition is changed, refresh map and path graph.
         */
        public void notifyChanged(Notification notification) {

            int type = notification.getEventType();
            int featureId = notification.getFeatureID(GrlPackage.class);
            if (type == Notification.ADD || type == Notification.REMOVE){
                if (featureId == GrlPackage.LINK_REF__BENDPOINTS) {
                    refreshVisuals();
                }
            } else if (type == Notification.SET){ //If a modification to the properties have been done
                refreshVisuals();
            }
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
         */
        public void setTarget(Notifier newTarget) {
            target = newTarget;
        }
    }
    
    ElementLinkAdapter adapter;
    private IURNDiagram diagram;
    protected IPropertySource propertySource = null;

    Label decompLabel, contributionLabel;
    
    /**
     * 
     */
    public LinkRefEditPart(LinkRef link, IURNDiagram diagram) {
        super();
        setModel(link);
        this.diagram = diagram;

        adapter = new ElementLinkAdapter((Notifier) getModel());
    }

    /**
     * We want to listen for the reference and definition 
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()){
            ((EObject) getModel()).eAdapters().add(adapter);
            
            if(getModel() instanceof LinkRef && ((LinkRef) getModel()).getLink() != null){
                ((LinkRef)getModel()).getLink().eAdapters().add(adapter);
            }
        }
        super.activate();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        //Remove the ConnectionEndPoints role because we did want user to be able to reconnect Links
        //installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
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
       
        ConnectionEndpointLocator ce = new ConnectionEndpointLocator(connection,true);
        ce.setUDistance(0);
        ce.setVDistance(0);
        
        decompLabel = new Label();
        RGB rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_LINKREFLABELCOLOR );
        decompLabel.setForegroundColor(new Color(null, rgb.red, rgb.green, rgb.blue));
        decompLabel.setOpaque(true);
        connection.add(decompLabel,ce);
        decompLabel.setVisible(false);
 
        //Create the contribution label
        ConnectionEndpointLocator contribce = new ConnectionEndpointLocator(connection,true);
        contribce.setUDistance(10);
        contribce.setVDistance(10);

        contributionLabel = new Label();
        contributionLabel.setForegroundColor(new Color(null, rgb.red, rgb.green, rgb.blue));
        connection.add(contributionLabel,contribce);
        contributionLabel.setVisible(false);
        
        return connection;
    }
    
    /**
     * Removes the adapter.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()){
            ((EObject) getModel()).eAdapters().remove(adapter);
            if(getModel() instanceof LinkRef && ((LinkRef) getModel()).getLink() != null){
                ((LinkRef)getModel()).getLink().eAdapters().remove(adapter);
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
     * @return  LinkRef.
     */
    public LinkRef getLinkRef() {
        return (LinkRef) getModel();
    }
    
    /**
     * Returns the Figure associated with this
     *
     * @return  LinkRefFigure.
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
        for (int i=0; i<modelConstraint.size(); i++) {
            LinkRefBendpoint bendpoint = (LinkRefBendpoint)modelConstraint.get(i);
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
    protected void refreshVisuals(){
        refreshBendpoints();
        if (getLinkRef().getLink() instanceof Decomposition){
            Decomposition decomp = (Decomposition)getLinkRef().getLink();
            if (decomp.getDest().getDecompositionType().getValue() == DecompositionType.AND){
                getLinkRefFigure().setType(LinkRefConnection.TYPE_DECOMPOSITION_AND);
            } else {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_DECOMPOSITION_OR);
            }
            if (decompLabel.getText() != decomp.getDest().getDecompositionType().getName()){
                decompLabel.setText(decomp.getDest().getDecompositionType().getName());
                decompLabel.setVisible(true);
            }
        } else if(getLinkRef().getLink() instanceof Contribution){
            Contribution contrib = (Contribution)getLinkRef().getLink();
            if (contrib.isCorrelation()){
                getLinkRefFigure().setType(LinkRefConnection.TYPE_CORRELATION);
            }else {
                getLinkRefFigure().setType(LinkRefConnection.TYPE_CONTRIBUTION);
            }
            
            //Set the contribution Label
            String type = contrib.getContribution().getName();
            if (!type.equals("Unknown")){
                contributionLabel.setText(type);
                
                //Set the icon
                if (type.equals("Make")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Make.gif")).createImage());
                } else if (type.equals("Help")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Help.gif")).createImage());
                } else if (type.equals("SomePositive")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/SomePositive.gif")).createImage());
                } else if (type.equals("SomeNegative")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/SomeNegative.gif")).createImage());
                } else if (type.equals("Hurt")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Hurt.gif")).createImage());
                } else if (type.equals("Break")){
                    contributionLabel.setIcon((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Break.gif")).createImage());
                }
                contributionLabel.setVisible(true);                
            } else {
                contributionLabel.setVisible(false);
            }
        } else if (getLinkRef().getLink() instanceof Dependency){
            Dependency depend = (Dependency)getLinkRef().getLink();
            getLinkRefFigure().setType(LinkRefConnection.TYPE_DEPENDENCY);
        }
        
    }
}
