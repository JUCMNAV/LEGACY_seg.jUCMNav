package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.ChangeDecompositionTypeCommand;

/**
 * This action adds a "change decomposition" action in intentional element contextual menus, allowing users to change their decomposition type from OR to AND
 * and vice versa.
 * 
 * @author damyot
 */
public class ChangeDecompositionTypeAction extends URNSelectionAction {
    public static final String CHANGEDECOMPOSITIONTYPE = "ChangeDecompositionTypeCommand.ChangeDecompositionType"; //$NON-NLS-1$
    private IntentionalElementRef selection;

    /**
     * @param part
     */
    public ChangeDecompositionTypeAction(IWorkbenchPart part) {
        super(part);
        setId(CHANGEDECOMPOSITIONTYPE);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Decomposition16.gif")); //$NON-NLS-1$
    }

    /**
     * @return a {@link ChangeDecompositionTypeCommand} to change the decomposition type of the intentional element (selection)
     */
    protected Command getCommand() {
        return new ChangeDecompositionTypeCommand(selection);
    }

    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalelementref();
            return true;
        default:
            return false;
        }
    }

}