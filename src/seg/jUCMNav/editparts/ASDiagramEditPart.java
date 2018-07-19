package seg.jUCMNav.editparts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import asd.ASDelement;
import asd.ASDiagram;
import asd.Aim;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;
import seg.jUCMNav.editpolicies.layout.DiagramXYLayoutEditPolicy;
import seg.jUCMNav.figures.ASDiagramBackgroundImage;

/**
 * The edit part for the GRL Graph
 * 
 * @author Jean-François Roy
 * 
 */
public class ASDiagramEditPart extends URNDiagramEditPart {

	ASDiagram asdiagram;
    /**
     * 
     * @param asdiagram
     *            the ASD  Diagram 
     */
    public ASDiagramEditPart(ASDiagram asdiagram) {
    	
        super(asdiagram);
        this.asdiagram=asdiagram;
    }

    /**
     * Create edit policies
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

    }
    
   
    /**
     * Returns the GrlGraph's children: IntentionalElementRefs
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
     
    	List list= getElements();  
    	list.addAll(getComments());
    	
        return list;
    }
    


    /**
     * @return the GrlNode
     */
    private List getElements() {
        List list = new ArrayList();

        for (ASDelement element : ((List<ASDelement>) ((ASDiagram) getDiagram()).getElements())) {
            list.add(element);}
            
       return list;
    }

    private List getComments() {
        List list = new ArrayList();
        for (Iterator iterator = getDiagram().getComments().iterator(); iterator.hasNext();) {
            list.add(iterator.next());
        }
        return list;
    }

    protected IFigure createFigure() {
    	File currentDirFile= new File(".");
		String helper=currentDirFile.getAbsolutePath();
		IFigure figure = (IFigure) new ASDiagramBackgroundImage(System.getenv("bb")+"\\ASDiagram.gif");
        return figure;
    }
    
    public void refreshVisuals()
    {        
  
    	
  	  for (Object o : asdiagram.getElements()) {
	        
		   if(o instanceof Tool ){
			   
		   			   
           ToolEditPart refEditPart = (ToolEditPart) getViewer().getEditPartRegistry().get(o);
           if (refEditPart != null) {
               refEditPart.refresh();
           }
		   }
		   
		   if(o instanceof Aim ){
			   
   			   
	           AimEditPart refEditPart = (AimEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
		   
		   if(o instanceof Subject ){
			   
   			   
	           SubjectEditPart refEditPart = (SubjectEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
		   
		   if(o instanceof Rule ){
			   
   			   
	           RuleEditPart refEditPart = (RuleEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
		   
		   if(o instanceof Outcome ){
			   
   			   
	           OutcomeEditPart refEditPart = (OutcomeEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
		   if(o instanceof DivisionOfLabour ){
			   
   			   
	           DivisionOfLabourEditPart refEditPart = (DivisionOfLabourEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
		   
		   if(o instanceof Community ){
			   
   			   
	           CommunityEditPart refEditPart = (CommunityEditPart) getViewer().getEditPartRegistry().get(o);
	           if (refEditPart != null) {
	               refEditPart.refresh();
	           }
			   }
	   }
    }
    
    private List getConnections() {
        List list = new ArrayList();

        for (Iterator i = getDiagram().getConnections().iterator(); i.hasNext();) {
            list.add(i.next());
        }

        return list;
    }

    /**
     * Refresh its children when something changes.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(AsdPackage.class);
        refreshChildren();
       

    }

   
    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
       cLayer.setConnectionRouter(new BendpointConnectionRouter());

        super.registerVisuals();
    }

    protected void unregisterVisuals() {

        super.unregisterVisuals();
    }
  

}
