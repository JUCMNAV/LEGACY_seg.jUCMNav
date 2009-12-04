package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.property.ResponsibilityPropertySource;
import ucm.map.RespRef;

/**
 * EditPart for RespRefs.
 * 
 * @author Etienne Tremblay
 */
public class RespRefTreeEditPart extends PathNodeTreeEditPart {

    /**
     * @param model
     *            the RespRef being edited.
     */
    public RespRefTreeEditPart(RespRef model) {
        super(model);
    }

    /**
     * Listens to both reference and definition.
     */
    public void activate() {
        super.activate();
        if (getRespRef().getRespDef() != null)
            getRespRef().getRespDef().eAdapters().add(this);
    }

    /**
     * Stops listening to both reference and definition.
     */
    public void deactivate() {
        super.deactivate();
        if (getRespRef().getRespDef() != null)
            getRespRef().getRespDef().eAdapters().remove(this);
    }

    /**
     * 
     * @return the RespRef being edited.
     */
    private RespRef getRespRef() {
        return (RespRef) getModel();
    }

    /**
     * @return the icon associated with responsibilities.
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/Resp16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * Returns a ResponsibilityPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new ResponsibilityPropertySource(getRespRef());

        return propertySource;
    }
}