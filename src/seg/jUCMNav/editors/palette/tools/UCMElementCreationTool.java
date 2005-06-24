package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;

/**
 * Created on 24-Jun-2005
 * 
 * Creation tool that doesn't get unloaded after creation, only after invalid mouse click. 
 * 
 * @author jkealey
 *  
 */
public class UCMElementCreationTool extends CreationTool {

    /**
     *  
     */
    public UCMElementCreationTool(CreationFactory factory) {
        super(factory);

        // see AbstractTool
        int FLAG_UNLOAD = 4;

        // we don't want our tool to be unloaded once used.
        setFlag(FLAG_UNLOAD, false);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.tools.CreationTool#handleButtonUp(int)
     */
    protected boolean handleButtonUp(int button) {
        
        // see AbstractTool
        int FLAG_UNLOAD = 4;
        
        // unload it if we didn't click with the normal mouse button
        setFlag(FLAG_UNLOAD, getState() == STATE_INVALID);

        return super.handleButtonUp(button);
    }
}