/*
 * Created on Mar 27, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.emf.EObjectPropertySource;
import seg.jUCMNav.figures.EditableLabel;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.PathNodeProxy;

/**
 * Based on Etienne's implementation of PathNodeEditPart
 * @author Jordan McManus
 */
public class LabelEditPart extends AbstractGraphicalEditPart {
    private IPropertySource propertySource = null;
	private Notifier target;
	
	private PathGraph diagram;
	
	public LabelEditPart(PathNodeProxy model, PathGraph diagram){
		super();
		setModel(model);
		this.diagram = diagram;
	}
	
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelEditPart#getModelObj()
     */
    public PathNodeProxy getModelObj() {
        // TODO Auto-generated method stub
        return (PathNodeProxy) getModel();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        EditableLabel label = new EditableLabel(getModelObj().getLabelText());
        return label;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if(!isActive())
            getModelObj().eAdapters().add(this);
		super.activate();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if(isActive())
            getModelObj().eAdapters().remove(this);
		super.deactivate();
    }
    
    public EditableLabel getLabelFigure(){
		return (EditableLabel) getFigure();
	}

    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        PathNodeProxy node = getModelObj();
        Dimension dim = getLabelFigure().getPreferredSize().getCopy();
        Point location = new Point(node.getLabelX()-(dim.width/2), node.getLabelY()-(dim.height/2));  // The position of the current figure
        Rectangle bounds = new Rectangle(location, dim);
		figure.setBounds(bounds);
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
    }

    /* (non-Javadoc)
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();
    }
    
    /* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type.equals( getModel().getClass() );
	}
	
	protected IPropertySource getPropertySource() {
		if( propertySource == null ) {
			propertySource = new EObjectPropertySource( (PathNode) getModelObj() );
		}
		return propertySource;
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		/* override the default behavior defined in AbstractEditPart
		*  which would expect the model to be a property sourced. 
		*  instead the editpart can provide a property source
		*/
		if (IPropertySource.class == key) {
			return getPropertySource();
		}
		return super.getAdapter( key );
	}
}
