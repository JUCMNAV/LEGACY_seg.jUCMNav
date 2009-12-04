package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.requests.CreationFactory;

/**
 * Creation tool that doesn't get unloaded after creation, only after invalid mouse click.
 * 
 * @author jkealey
 */
public class URNElementCreationTool extends BaseCreationTool {

    /**
     *  
     */
    public URNElementCreationTool(CreationFactory factory) {
        super(factory);

        setUnloadWhenFinished(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.tools.CreationTool#handleButtonUp(int)
     */
    protected boolean handleButtonUp(int button) {

        // unload it if we didn't click with the normal mouse button
        setUnloadWhenFinished(getState() == STATE_INVALID);

        return super.handleButtonUp(button);
    }

}