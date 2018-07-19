package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import asd.ASDiagram;
import asd.ASDmodelElement;
import asd.ASDspec;
import asd.AsdPackage;
import asd.Community;
import asd.Tool;
import seg.jUCMNav.editpolicies.directEditPolicy.AsdElementCellEditorLocator;
import seg.jUCMNav.editpolicies.directEditPolicy.AutocompleteTextCellEditor;
import seg.jUCMNav.editpolicies.directEditPolicy.CommunityDirectEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.element.CommunityComponentEditPolicy;
import seg.jUCMNav.figures.ASDiagramBackgroundImage;
import seg.jUCMNav.figures.AsdTextEditor;
import seg.jUCMNav.figures.CommunityFigure;
import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.views.property.LabelPropertySource;
import urncore.URNmodelElement;


public class CommunityEditPart extends ModelElementEditPart {
	// label padding
	protected static final int LABEL_PADDING_X = 6;
	protected static final int LABEL_PADDING_Y = 4;
	private ASDiagram asdDiagram;
	private ASDspec asdSpec;
    static int loc=0;
    private int ycoordinate;

	// for direct edit
	protected DirectEditManager manager;

	// the model element being referenced; might be component, pathnode or even node connection (see ConditionEditPart)
	protected EObject modelElement;
	// Image
	private Image iconImg;
	protected AsdTextEditor asdTextEditor;

	public CommunityEditPart(ASDmodelElement model,ASDiagram asdDiagram) {
		super();
		setModel(model);
		modelElement= model;
		this.asdDiagram=asdDiagram;
		this.asdSpec= asdDiagram.getUrndefinition().getUrnspec().getAsdspec();
	}
	public ASDiagramEditPart getASDiagramEditPart()
	{
		return (ASDiagramEditPart) getViewer().getEditPartRegistry().get(asdDiagram);
	}
	public void activate() {
		if (!isActive()) {
			modelElement.eAdapters().add(this);

		}
		super.activate();        
	}
	public ASDiagram getDiagram()
	{
		return asdDiagram;
	}
	 
		public void refresh() 
		{
			refreshVisuals();
		}
		
	protected void createEditPolicies() {

		
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new CommunityDirectEditPolicy(asdDiagram));
		 installEditPolicy(EditPolicy.COMPONENT_ROLE, new CommunityComponentEditPolicy());
		

	}

	protected IFigure createFigure() {
		
		
		return new CommunityFigure(asdSpec,(Community)modelElement,asdDiagram);
		
		
	}

	public void deactivate() {
		if (isActive()) {
			modelElement.eAdapters().remove(this);
			/* if (modelElement instanceof IURNContainerRef && comp != null) {
                comp.eAdapters().remove(this);
                comp = null;
            } else if (modelElement instanceof RespRef && resp != null) {
                resp.eAdapters().remove(this);
                resp = null;
            } else if(modelElement instanceof LinkRef && ((LinkRef)modelElement).getLink() != null) {
                ((LinkRef)modelElement).getLink().eAdapters().remove(this);
            }*/
		}
		super.deactivate();
	}

	private boolean directEditHitTest(Point requestLoc) {
		CommunityFigure figure = (CommunityFigure) getFigure();
		figure.translateToRelative(requestLoc);
		if (figure.containsPoint(requestLoc))
			return true;
		return false;
	}

	public IFigure getNodeFigure() {
		return (IFigure) getFigure();
	}

	public Tool getModelObj() {
		return (Tool) getModel();
	}

	protected IPropertySource getPropertySource() {
		if (propertySource == null) {
			propertySource = new LabelPropertySource((EObject) getModel());
		}
		return propertySource;
	}
	public EObject getURNmodelElement() {
		return modelElement;
	}
	public void handleNameChange(String value) {
		CommunityFigure tableFigure = (CommunityFigure) getFigure();
		tableFigure.setVisible(false);
		refreshVisuals();
	}

	//	private Tool getTool() {
	//		return (Tool) getModel();
	//	}


	public DragTracker getDragTracker(Request request) {
		return new DragPathNodeTracker(this);
	}


	public void notifyChanged(Notification notification) {
		int type = notification.getEventType();
		int featureIdAsd = notification.getFeatureID(AsdPackage.class);
		eraseTargetFeedback(new SelectionRequest());


		// we want the top level editpart to refresh its children so that the largest components are always in the back.
		if ( getParent() != null){
			((URNDiagramEditPart) getParent()).notifyChanged(notification);
			refreshVisuals();}
		else if(type == Notification.ADD || type == Notification.ADD_MANY || type == Notification.REMOVE || type == Notification.REMOVE_MANY) {
			refreshVisuals();
		}
	}
	/**
	 * Opens the direct edit manager.
	 * 
	 */
	 
	   
	   protected void performDirectEdit() {
		openDirectEditor();
	}

	   public void openDirectEditor() {
	        if (manager == null) {
	        	CommunityFigure figure = (CommunityFigure) getFigure();

	            ICellEditorValidator validator = new ICellEditorValidator() {
	                public String isValid(Object value) {
	                    return ""; //$NON-NLS-1$
	                }
	            };

	            manager = new ExtendedDirectEditManager(this, AutocompleteTextCellEditor.class, new AsdElementCellEditorLocator(figure), figure, validator);
	        }
	        manager.show();
	   }
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
			if (request instanceof DirectEditRequest && !directEditHitTest(((DirectEditRequest) request).getLocation().getCopy()))
				return;
			performDirectEdit();
		}
	}
	 
	protected void refreshVisuals() {        
		
		if (modelElement != null) {
			eraseTargetFeedback(new SelectionRequest());
			CommunityFigure labelFigure = (CommunityFigure) getNodeFigure();
			// set the label's text
				
			setLabelText();
			labelFigure.setSuffixText(UrnMetadata.getStereotypes(modelElement));
			int count=0;
			   ASDiagramBackgroundImage aImage=null;
           for(Object el: asdDiagram.getElements())
           {
        	   if(el instanceof Community)
        	  count++;
        	   if(el==modelElement)
        	   {      		   
        	aImage= new ASDiagramBackgroundImage("ASDiagram.gif");
        		   ycoordinate= aImage.getY() +225 + (count*18);
        		
        		   break;
        	   }
           }
           //ASDiagramBackgroundImage aImage= new ASDiagramBackgroundImage("ASDiagram.gif");
			Point location= new Point(aImage.getX()-125,ycoordinate);
            
			Dimension newLabelDimension= new Dimension(125,25);
			Rectangle bounds = new Rectangle(location, newLabelDimension);
			labelFigure.setBounds(bounds);	
			//labelFigure.setSelected(true);
			labelFigure.setVisible(true);


		}

	}
	public void revertNameChange() {
		CommunityFigure tableFigure = (CommunityFigure) getFigure();
		tableFigure.setVisible(true);
		refreshVisuals();
	}

	protected void setLabelText() {
		CommunityFigure labelFigure = (CommunityFigure) getNodeFigure();
	
 	labelFigure.setEditableText(((URNmodelElement) modelElement).getName());
	}

}



