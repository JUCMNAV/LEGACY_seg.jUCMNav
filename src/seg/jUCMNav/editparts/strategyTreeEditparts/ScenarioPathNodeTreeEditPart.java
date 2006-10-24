package seg.jUCMNav.editparts.strategyTreeEditparts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.ScenarioPathNodeComponentEditPolicy;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.views.property.ScenarioPathNodePropertySource;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * TreeEditPart for PathNodes
 * 
 * @author TremblaE
 * 
 */
public class ScenarioPathNodeTreeEditPart extends StrategyUrnModelElementTreeEditPart {

	/**
	 * @param model
	 *            the pathnode
	 */
	public ScenarioPathNodeTreeEditPart(ScenarioStartPoint model) {
		super(model);
	}

	/**
	 * @param model
	 *            the pathnode
	 */
	public ScenarioPathNodeTreeEditPart(ScenarioEndPoint model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
    	if (!isInherited()) 
    		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ScenarioPathNodeComponentEditPolicy()); // deletion
	}

	public boolean isInherited() {
		return isInheritedStartPoint() || isInheritedEndPoint();
	}

	protected boolean isInheritedEndPoint() {
		return getModel() instanceof ScenarioEndPoint && !((ScenarioDef) getParent().getParent().getModel()).getEndPoints().contains(getModel());
	}

	protected boolean isInheritedStartPoint() {
		return getModel() instanceof ScenarioStartPoint && !((ScenarioDef) getParent().getParent().getModel()).getStartPoints().contains(getModel());
	}
	
    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
    	
        if (!isActive() && getPathNode()!=null)
            getPathNode().eAdapters().add(this);
        super.activate();
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getPathNode()!=null) {
        	getPathNode().eAdapters().remove(this);
        }
        super.deactivate();
    }
    

	/**
	 * When setting widgets, uses a lighter colour for inherited variables.
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#setWidget(org.eclipse.swt.widgets.Widget)
	 */
	public void setWidget(Widget widget) {
		super.setWidget(widget);
		// TODO
		// if (widget instanceof TreeItem) {
		// if (getModel() instanceof EmptyPoint || getModel() instanceof
		// DirectionArrow)
		// ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
		// }

	}

	/**
	 * Returns an image representing the PathNode.
	 */
	protected Image getImage() {

		PathNode node = getPathNode();

		if (super.getImage() == null) {
			if (node instanceof StartPoint || node instanceof WaitingPlace)
				setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage()); //$NON-NLS-1$
			else if (node instanceof EndPoint)
				setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage()); //$NON-NLS-1$
		}

		return super.getImage();
	}
	
    /**
     * Returns a ContainerPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new ScenarioPathNodePropertySource((EObject)getModel());

        return propertySource;
    }
    
    /**
     * Returns the textual string associated with this element.
     * 
     * @see seg.jUCMNav.model.util.EObjectClassNameComparator
     */
    protected String getText() {
    	if (widget!=null && !widget.isDisposed()) {
	    	if (isInherited()) 
	    		((TreeItem) widget).setForeground(DARKGRAY);
	    	else
	    		((TreeItem) widget).setForeground(BLACK);
    	}
    	if (getModel() instanceof ScenarioStartPoint && ((ScenarioStartPoint)getModel()).getStartPoint()!=null)
    		return ((UCMmap)((ScenarioStartPoint)getModel()).getStartPoint().getDiagram()).getName() + Messages.getString("ScenarioPathNodeTreeEditPart.MapNodeSeperator") + EObjectClassNameComparator.getSortableElementName(((ScenarioStartPoint)getModel()).getStartPoint()); //$NON-NLS-1$
    	else if (getModel() instanceof ScenarioEndPoint && ((ScenarioEndPoint)getModel()).getEndPoint()!=null)
    		return  ((UCMmap)((ScenarioEndPoint)getModel()).getEndPoint().getDiagram()).getName() + Messages.getString("ScenarioPathNodeTreeEditPart.MapNodeSeperator") + EObjectClassNameComparator.getSortableElementName(((ScenarioEndPoint)getModel()).getEndPoint()); //$NON-NLS-1$
    	else
    		return ""; //$NON-NLS-1$
    }

	private PathNode getPathNode() {
		PathNode node = null;
		if (getModel() instanceof ScenarioStartPoint)
			node = ((ScenarioStartPoint) getModel()).getStartPoint();
		else if (getModel() instanceof ScenarioEndPoint)
			node = ((ScenarioEndPoint) getModel()).getEndPoint();
		return node;
	}
	
}