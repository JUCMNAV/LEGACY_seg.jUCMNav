package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ResourceComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;

/**
 * TreeEditPart for resources
 * 
 * @author jkealey
 */
public class GeneralResourceTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the resource
     */
    public GeneralResourceTreeEditPart(GeneralResource model) {
        super(model);
    }

    /**
     * @return the resource
     */
    protected GeneralResource getResource() {
        return (GeneralResource) getModel();
    }

    /**
     * Returns the icon appropriate for this component's kind
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            if (getResource() instanceof GeneralResource) {
                setImage(getResourceImage((GeneralResource)getResource()));
            } else {
                setImage(getResourceImage(null));
            }

        }
        return super.getImage();
    }

    /**
     * Returns an image appropriate for a particular type of resource. 
     * 
     * @param resx
     *            the resource
     * @return the icon associated with the resource
     */
    protected static Image getResourceImage(GeneralResource resx) {
        
        if (resx instanceof PassiveResource) {
            return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Agent16.gif")).createImage(); //$NON-NLS-1$
        } else if (resx instanceof ProcessingResource) {
            return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Process16.gif")).createImage(); //$NON-NLS-1$
        } else { //else if (resx instanceof ExternalOperation) {
            return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage(); //$NON-NLS-1$
        }

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ResourceComponentEditPolicy());
    }

    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getResource().getDemands().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);

        super.refreshVisuals();
    }
    
    /**
     * Return the sorted list of component or responsibility definitions.
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        
        // HACK: GET RID OF THIS. Replace with intermediate object. 
        if (getResource() instanceof PassiveResource) {
            PassiveResource passiveResource = (PassiveResource) getResource();
            if (passiveResource.getComponent()!=null)
                list.add(passiveResource.getComponent());
            
        } else if (getResource() instanceof ProcessingResource) {
            ProcessingResource processingResource = (ProcessingResource) getResource();
            list.addAll(processingResource.getComponents());
        }
        // END OF HACK
        if (getResource()!=null)
            list.addAll(getResource().getDemands());

        return list;
    }
}