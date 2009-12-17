package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.StubHelper;
import ucm.map.Stub;

/**
 * TreeEditPart for Stubs. Implement static/dynamic/pointcut icon changing behaviour.
 * 
 * @author jkealey, gunterm
 */
public class StubTreeEditPart extends PathNodeTreeEditPart {
    
    protected static Image[] icons = new Image[] {
        (JUCMNavPlugin.getImage("icons/Stub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/DynStub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/PointcutStub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/PointcutRepStub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/SyncStub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/BlockStub16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/aspectMarker16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/aspectMarkerEntrance16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/aspectMarkerExit16.gif")), //$NON-NLS-1$
        (JUCMNavPlugin.getImage("icons/aspectMarkerCond16.gif")) //$NON-NLS-1$
    };

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
        
        int kind = StubHelper.getStubKind(getStub());
        
        return icons[kind];
    }
}