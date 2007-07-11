package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Given a timer without a timeout path, adds it.
 * 
 * @author jkealey
 *  
 */
public class AddTimeoutPathAction extends AddBranchAction {

    public static final String ADDTIMEOUTPATH = "seg.jUCMNav.AddTimeoutPath"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddTimeoutPathAction(IWorkbenchPart part) {
        super(part);
        setId(ADDTIMEOUTPATH);
    }

    /**
     * Can add timeout path on a timer that doesn't currently have one. Method also sets the icon.
     */
    protected boolean calculateEnabled() {

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getTimer() != null) {
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/Timer16.gif")); //$NON-NLS-1$
            return sel.getTimer().getSucc().size() == 1;
        } else
            return false;
    }

}