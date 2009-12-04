package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * This action exposes a "Convert" action in Join contextual menus, allowing them to be changed to a Fork.
 * 
 * @author jpdaigle
 */
public class TransmogrifyAndForkOrJoinAction extends TransmogrifyForkOrJoinAction {
    public static final String TRANSMOGRIFYJOIN = "seg.jUCMNav.TransmogrifyJoin"; //$NON-NLS-1$

    /**
     * @param part
     */
    public TransmogrifyAndForkOrJoinAction(IWorkbenchPart part) {
        super(part);
        setId(TRANSMOGRIFYJOIN);

    }

    /**
     * Conditions for enabling: selection contains exactly 1 item and it's an and fork or and join.
     * 
     * Sets the image descriptor.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.ANDJOIN:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrJoin16.gif")); //$NON-NLS-1$
            return true;
        case SelectionHelper.ANDFORK:
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrFork16.gif")); //$NON-NLS-1$
            return true;
        default:
            return false;
        }

    }

}