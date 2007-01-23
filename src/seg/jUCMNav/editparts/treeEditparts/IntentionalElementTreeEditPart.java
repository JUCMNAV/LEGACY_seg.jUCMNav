package seg.jUCMNav.editparts.treeEditparts;

import grl.IntentionalElement;
import grl.IntentionalElementType;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.IntentionalElementComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the intentional elements
 *
 * @author Jean-François Roy
 *
 */
public class IntentionalElementTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *          the intentionalElement definition
     */
    public IntentionalElementTreeEditPart(Object model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new IntentionalElementComponentEditPolicy());
    }

    /**
     * @return the intentional element definition
     */
    protected IntentionalElement getElement() {
        return (IntentionalElement) getModel();
    }
    
    /**
     * Returns the icon appropriate for this element type
     */
    protected Image getImage() {
        if (super.getImage() == null) {       
            if (getElement().getType().getValue() == IntentionalElementType.GOAL)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Goal16.gif")).createImage()); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.SOFTGOAL)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Softgoal16.gif")).createImage()); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.TASK)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Task16.gif")).createImage()); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.RESSOURCE)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resource16.gif")).createImage()); //$NON-NLS-1$
        }

        return super.getImage();
    }

    /**
     * @return the sorted list of maps and the component and responsibility definition labels
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getElement().getLinksSrc());
        list.addAll(getElement().getLinksDest());
        
        return list;

    }
    
    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getElement().getRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
