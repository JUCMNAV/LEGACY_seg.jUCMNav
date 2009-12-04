package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.DuplicateMapCommand;

/**
 * Duplicates a UCM map / GRL graph and its contents.
 * 
 * @author jkealey
 * 
 */
public class DuplicateMapAction extends URNSelectionAction {

    public static final String DUPLICATEMAP = "seg.jUCMNav.DuplicateMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public DuplicateMapAction(IWorkbenchPart part) {
        super(part);
        setId(DUPLICATEMAP);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/duplicate.gif")); //$NON-NLS-1$
    }

    /**
     * Returns true if selected is a ucm map or grl graph
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
        case SelectionHelper.GRLGRAPH:
            return true;
        default:
            return false;
        }

    }

    /**
     * @return a {@link DuplicateMapCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
            return new DuplicateMapCommand(sel.getMap());
        case SelectionHelper.GRLGRAPH:
            return new DuplicateMapCommand(sel.getGrlgraph());
        default:
            return null;

        }
    }
}