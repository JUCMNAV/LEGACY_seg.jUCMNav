package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.Stub;

/**
 * TreeEditPart for Stubs. Implement static/dynamic/pointcut icon changing behaviour.
 * 
 * @author jkealey, gunterm
 */
public class StubTreeEditPart extends PathNodeTreeEditPart {

    protected Image imStaticStub = (JUCMNavPlugin.getImage("icons/Stub16.gif")); //$NON-NLS-1$ 
    protected Image imDynamicStub = (JUCMNavPlugin.getImage("icons/DynStub16.gif")); //$NON-NLS-1$
    protected Image imPointcutStub = (JUCMNavPlugin.getImage("icons/PointcutStub16.gif")); //$NON-NLS-1$

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
    protected Stub getStub() {
        return (Stub) getModel();
    }

    /**
     * returns the icon associated with the stub (dynamic/static/pointcut)
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.PathNodeTreeEditPart#getImage()
     */
    protected Image getImage() {

        if (getStub().isPointcut()) {
            if (imPointcutStub == null)
                imPointcutStub = (JUCMNavPlugin.getImage("icons/PointcutStub16.gif")); //$NON-NLS-1$
            return imPointcutStub;
        } else if (getStub().isDynamic()) {
            if (imDynamicStub == null)
                imDynamicStub = (JUCMNavPlugin.getImage("icons/DynStub16.gif")); //$NON-NLS-1$
            return imDynamicStub;
        } else {
            if (imStaticStub == null)
                imStaticStub = (JUCMNavPlugin.getImage("icons/Stub16.gif")); //$NON-NLS-1$
            return imStaticStub;
        }
    }

    /**
     * Deactivates and disposes stub images.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#deactivate()
     */
    public void deactivate() {
        // if (imStaticStub != null) {
        // imStaticStub.dispose();
        // imStaticStub = null;
        // }
        // if (imDynamicStub != null) {
        // imDynamicStub.dispose();
        // imDynamicStub = null;
        // }
        // if (imPointcutStub != null) {
        // imPointcutStub.dispose();
        // imPointcutStub = null;
        // }
        super.deactivate();
    }
}