/*
 * Created on 2005-02-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.emf.EObjectPropertySource;
import ucm.map.ComponentRef;
import ucm.map.Map;
import urncore.ComponentElement;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class ComponentRefEditPart extends AbstractGraphicalEditPart implements Adapter {

    private IPropertySource propertySource = null;
    private Notifier target;
    private Map map;

    /**
     *  
     */
    public ComponentRefEditPart(ComponentRef model, Map map) {
        super();
        this.map = map;
        setModel(model);
        if (model.getCompDef()!=null)
            model.getCompDef().eAdapters().add(this);
    }

    private ComponentRef getComponentRef() {
        return (ComponentRef) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            getComponentRef().eAdapters().add(this);
        super.activate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive())
            getComponentRef().eAdapters().remove(this);
        super.deactivate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        RectangleFigure rect = new RectangleFigure();

        return rect;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();
        
        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        ((MapAndPathGraphEditPart)getParent()).notifyChanged(notification);        
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new EObjectPropertySource(getComponentRef());
        }
        return propertySource;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class key) {
        /*
         * override the default behavior defined in AbstractEditPart which would expect the model to be a property sourced. instead the editpart can provide a
         * property source
         */
        if (IPropertySource.class == key) {
            return getPropertySource();
        }
        return super.getAdapter(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        Point location = new Point(getComponentRef().getX(), getComponentRef().getY()); // The position of the current figure
        Dimension size = new Dimension(getComponentRef().getWidth(), getComponentRef().getHeight());
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.validate(); // Make the label recenter itself.
        RectangleFigure rect = (RectangleFigure) figure;
        // set the colors
        RGB color;

        ComponentElement comp = getComponentRef().getCompDef();
        if (comp != null) {
            if (comp.getFillColor() == null || comp.getFillColor().length() == 0)
                rect.setFill(false);
            else {
                color = StringConverter.asRGB(comp.getFillColor());
                rect.setFill(true);
                rect.setBackgroundColor(new Color(Display.getCurrent(), color));
            }

            if (comp.getLineColor() == null || comp.getLineColor().length() == 0) {
                color = new RGB(0, 0, 0);
            } else {
                color = StringConverter.asRGB(comp.getLineColor());
            }
            rect.setForegroundColor(new Color(Display.getCurrent(), color));
        } else
            rect.setFill(false);

        figure.validate(); // Make the label recenter itself.
        
        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
    }

}