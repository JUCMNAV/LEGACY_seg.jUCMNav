package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import urncore.Concern;

/**
 * TreeEditPart for the Map.
 * 
 * @author TremblaE
 */
public class ConcernTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the concern
     */
    public ConcernTreeEditPart(Concern model) {
        super(model);
    }

    /**
     * Returns an icon representing a concern
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Concern16.gif")).createImage()); //$NON-NLS-1$
        return super.getImage();
    }

}