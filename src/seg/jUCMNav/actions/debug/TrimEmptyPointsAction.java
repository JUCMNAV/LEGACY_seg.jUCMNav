package seg.jUCMNav.actions.debug;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.SimplifyForksAndJoinsCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;

/**
 * This action deletes all empty points form a map.
 * 
 * @author jkealey
 */
public class TrimEmptyPointsAction extends URNSelectionAction {
    public static final String TRIMEMPTYPOINTS = "seg.jUCMNav.TrimEmptyPointsAction"; //$NON-NLS-1$

    /**
     * @param part
     */
    public TrimEmptyPointsAction(IWorkbenchPart part) {
        super(part);
        setId(TRIMEMPTYPOINTS);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * Always enabled when you have something selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getMap() != null;
    }

    /**
     * @return a {@link SimplifyForksAndJoinsCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        TrimEmptyNodeCommand command = new TrimEmptyNodeCommand(sel.getMap());

        return command;
    }

}