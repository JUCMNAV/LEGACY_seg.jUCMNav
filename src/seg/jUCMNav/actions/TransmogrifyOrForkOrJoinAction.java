package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * This action exposes a "Convert" action in Fork contextual menus, allowing them to be changed to a Join.
 * 
 * @author jpdaigle
 */
public class TransmogrifyOrForkOrJoinAction extends TransmogrifyForkOrJoinAction {
    public static final String TRANSMOGRIFYFORK = "seg.jUCMNav.TransmogrifyFork"; //$NON-NLS-1$

    /**
     * @param part
     */
    public TransmogrifyOrForkOrJoinAction(IWorkbenchPart part) {
        super(part);
        setId(TRANSMOGRIFYFORK);
    }

    /**
     * Conditions for enabling: selection contains exactly 1 item and it's an or fork or an or join.
     * 
     * Sets the image descriptor.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.ORJOIN:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndJoin16.gif")); //$NON-NLS-1$
            return true;
        case SelectionHelper.ORFORK:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndFork16.gif")); //$NON-NLS-1$
            return true;
        default:
            return false;
        }
    }

}