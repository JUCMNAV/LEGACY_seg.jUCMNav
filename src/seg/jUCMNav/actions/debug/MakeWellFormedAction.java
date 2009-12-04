package seg.jUCMNav.actions.debug;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.MakeWellFormedCommand;

/**
 * Transforms concurrency (only considers and-fork/and-join) into a well-formed graph that can be linearized.
 * 
 * Is only meant to be used for debugging purposes for the scenario export.
 * 
 * @author jkealey
 */
public class MakeWellFormedAction extends URNSelectionAction {
    public static final String MAKEWELLFORMED = "seg.jUCMNav.MakeWellFormed"; //$NON-NLS-1$

    /**
     * @param part
     */
    public MakeWellFormedAction(IWorkbenchPart part) {
        super(part);
        setId(MAKEWELLFORMED);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * Always enabled when you have something selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null;
    }

    /**
     * @return a {@link MakeWellFormedCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        MakeWellFormedCommand command = new MakeWellFormedCommand(sel.getUrnspec());

        return command;
    }

}