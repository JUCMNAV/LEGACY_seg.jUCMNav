package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.util.SafePathChecker;

/**
 * @author jkealey
 * 
 * Merge a start and end point.
 */
public class MergeStartEndAction extends SelectionAction {

    public static final String MERGESTARTEND = "MergeStartEnd"; //$NON-NLS-1$

    /**
     * @param part
     */
    public MergeStartEndAction(IWorkbenchPart part) {
        super(part);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return canPerformAction();
    }

    private boolean canPerformAction() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_ENDPOINT:
            return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getEndpoint());
        default:
            return false;
        }
    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {

        case SelectionHelper.STARTPOINT_ENDPOINT:
            return new MergeStartEndCommand(sel.getMap(), sel.getStartpoint(), sel.getEndpoint(), sel.getStartpoint().getX(), sel.getStartpoint().getY());
        default:
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        execute(getCommand());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return MERGESTARTEND;
    }

}