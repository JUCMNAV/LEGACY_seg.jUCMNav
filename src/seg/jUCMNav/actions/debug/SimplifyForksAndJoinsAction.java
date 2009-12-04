package seg.jUCMNav.actions.debug;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.SimplifyForksAndJoinsCommand;

/**
 * This action restructures and forks/joins so that they are closer to a linearizable form.
 * 
 * If you have a join with three in branches, two of which come from the same fork, the action will group these two into a new join that flows into the existing
 * one.
 * 
 * This action can easily be extended to support or-fork/or-joins.
 * 
 * Is only meant to be used for debugging purposes for the scenario export.
 * 
 * @author jkealey
 */
public class SimplifyForksAndJoinsAction extends URNSelectionAction {
    public static final String SIMPLIFYFORKSANDJOINS = "seg.jUCMNav.SimplifyForksAndJoinsAction"; //$NON-NLS-1$

    /**
     * @param part
     */
    public SimplifyForksAndJoinsAction(IWorkbenchPart part) {
        super(part);
        setId(SIMPLIFYFORKSANDJOINS);
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

        SimplifyForksAndJoinsCommand command = new SimplifyForksAndJoinsCommand(sel.getMap());

        return command;
    }

}