package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;

/**
 * Creation tool that doesn't get unloaded after creation, only after invalid mouse click. 
 * 
 * @author jkealey
 *  TODO Modify this class for URNElementCreationTool
 */
public class UCMElementCreationTool extends CreationTool {

    /**
     *  
     */
    public UCMElementCreationTool(CreationFactory factory) {
        super(factory);

        setUnloadWhenFinished(false);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.tools.CreationTool#handleButtonUp(int)
     */
    protected boolean handleButtonUp(int button) {
       
        // unload it if we didn't click with the normal mouse button
        setUnloadWhenFinished(getState() == STATE_INVALID);

        return super.handleButtonUp(button);
    }
}