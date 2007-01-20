package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.DuplicateMapCommand;

/**
 * Duplicates a map. 
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
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/duplicate.gif")); //$NON-NLS-1$
    }

    /**
     * Returns true if selected (end||empty) && (start||wait||timer)
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