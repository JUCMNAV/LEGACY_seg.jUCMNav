package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.MakeWellFormedCommand;

/**
 * Transforms concurrency (only considers and-fork/and-join) into a well-formed graph that can be linearized. 
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
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * If you have a URNspec or Map selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null;
    }

    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        MakeWellFormedCommand command = new MakeWellFormedCommand(sel.getUrnspec());

        return command;
    }

}