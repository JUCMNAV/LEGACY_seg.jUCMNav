package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ResourceComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.wrappers.ComponentTreeWrapper;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urncore.Component;

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
                setImage(getResourceImage((GeneralResource) getResource()));
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
            return (JUCMNavPlugin.getImage("icons/Agent16.gif")); //$NON-NLS-1$
        } else if (resx instanceof ProcessingResource) {
            return (JUCMNavPlugin.getImage("icons/Process16.gif")); //$NON-NLS-1$
        } else { // else if (resx instanceof ExternalOperation) {
            return (JUCMNavPlugin.getImage("icons/Node16.gif")); //$NON-NLS-1$
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
        GeneralResource res = getResource();

        boolean isUsed = false;

        if (res instanceof PassiveResource) {
            PassiveResource passiveResource = (PassiveResource) res;
            isUsed = (passiveResource.getComponent() != null);
        } else if (res instanceof ProcessingResource) {
            ProcessingResource processingResource = (ProcessingResource) res;
            isUsed = (processingResource.getComponents().size() != 0);
        } else if (res instanceof ucm.performance.ExternalOperation) {
            ucm.performance.ExternalOperation externalOperation = (ucm.performance.ExternalOperation) res;
            isUsed = (externalOperation.getDemands().size() != 0);
        }

        if (isUsed)
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        else
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);

        super.refreshVisuals();
    }

    /**
     * Return the sorted list of component or responsibility definitions.
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        GeneralResource res = getResource();

        if (res instanceof PassiveResource) {
            PassiveResource passiveResource = (PassiveResource) res;
            if (passiveResource.getComponent() != null)
                list.add(new ComponentTreeWrapper(passiveResource.getComponent(), passiveResource));

        } else if (res instanceof ProcessingResource) {
            ProcessingResource processingResource = (ProcessingResource) res;
            for (Iterator iter = list.iterator(); iter.hasNext();) {
                Component comp = (Component) iter.next();
                list.add(new ComponentTreeWrapper(comp, processingResource));
            }
        } else if (res instanceof ucm.performance.ExternalOperation) {
            ucm.performance.ExternalOperation externalOperation = (ucm.performance.ExternalOperation) res;
            list.addAll(externalOperation.getDemands());
        }

        return list;
    }
}