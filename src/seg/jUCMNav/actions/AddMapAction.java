package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateMapCommand;

/**
 * Adds a new blank use case map in the current editor.
 * 
 * @author jkealey
 */
public class AddMapAction extends UCMSelectionAction {
    public static final String ADDMAP = "seg.jUCMNav.AddMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddMapAction(IWorkbenchPart part) {
        super(part);
        setId(ADDMAP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")); //$NON-NLS-1$
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

        CreateMapCommand create = new CreateMapCommand(sel.getUrnspec());

        return create;
    }

}