package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.Stub;

/**
 * TreeEditPart for Stubs. Implement static/dynamic icon changing behaviour.
 * 
 * @author jkealey
 */
public class StubTreeEditPart extends PathNodeTreeEditPart {

    protected Image imStaticStub = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif")).createImage(); //$NON-NLS-1$ 
    protected Image imDynamicStub = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/DynStub16.gif")).createImage(); //$NON-NLS-1$

    /**
     * 
     * @param stub
     *            the stub being edited
     */
    public StubTreeEditPart(Stub stub) {
        super(stub);
    }

    /**
     * @return the stub being edited
     */
    public Stub getStub() {
        return (Stub) getModel();
    }

    /**
     * returns the icon associated with the stub (dynamic/static)
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.PathNodeTreeEditPart#getImage()
     */
    protected Image getImage() {

        if (getStub().isDynamic()) {
            if (imDynamicStub == null)
                imDynamicStub = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/DynStub16.gif")).createImage(); //$NON-NLS-1$
            return imDynamicStub;
        } else {
            if (imStaticStub == null)
                imStaticStub = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif")).createImage(); //$NON-NLS-1$
            return imStaticStub;
        }
    }

    /**
     * Deactivates and disposes stub images.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#deactivate()
     */
    public void deactivate() {
        if (imStaticStub != null) {
            imStaticStub.dispose();
            imStaticStub = null;
        }
        if (imDynamicStub != null) {
            imDynamicStub.dispose();
            imDynamicStub = null;
        }
        super.deactivate();
    }
}