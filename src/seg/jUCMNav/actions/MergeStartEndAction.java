package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.util.SafePathChecker;

/**
 * 
 * Merge a start and end point if doesn't cause anything illegal. They are replaced with an empty point.
 * 
 * @author jkealey
 * @see SafePathChecker
 */
public class MergeStartEndAction extends URNSelectionAction {

    public static final String MERGESTARTEND = "seg.jUCMNav.MergeStartEnd"; //$NON-NLS-1$

    /**
     * @param part
     */
    public MergeStartEndAction(IWorkbenchPart part) {
        super(part);
        setId(MERGESTARTEND);
    }

    /**
     * @return a {@link MergeStartEndCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_ENDPOINT:
            return new MergeStartEndCommand(sel.getMap(), sel.getStartpoint(), sel.getEndpoint(), sel.getStartpoint().getX(), sel.getStartpoint().getY());
        default:
            return null;
        }
    }
}