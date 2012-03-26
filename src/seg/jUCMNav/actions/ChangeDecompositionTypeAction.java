package seg.jUCMNav.actions;

import grl.Decomposition;
import grl.IntentionalElementRef;

import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.ChangeDecompositionTypeCommand;

/**
 * This action adds a "change decomposition" action in intentional element contextual menus, allowing users to change 
 * their decomposition type to AND, OR, or XOR.
 * 
 * @author damyot
 */
public class ChangeDecompositionTypeAction extends URNSelectionAction {
    public static final String CHANGEDECOMPOSITIONTYPE = "ChangeDecompositionTypeCommand.ChangeDecompositionType"; //$NON-NLS-1$
    private IntentionalElementRef selection;
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "AND", "OR", "XOR"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    /**
     * @param part
     */
    public ChangeDecompositionTypeAction(IWorkbenchPart part, int id) {
        super(part);
        setId(CHANGEDECOMPOSITIONTYPE + id);
        setText(values[id]);
        this.id = id;
    }

    /**
     * @return a {@link ChangeDecompositionTypeCommand} to change the decomposition type of the intentional element (selection)
     */
    protected Command getCommand() {
        return new ChangeDecompositionTypeCommand(selection, id);
    }

    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalElementRef();
            for (Object link: selection.getDef().getLinksDest()) {
                if (link instanceof Decomposition)
                    return true;
            }
        default:
            return false;
        }
    }

    public static String generateId(int id) {
        return CHANGEDECOMPOSITIONTYPE + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return CHANGEDECOMPOSITIONTYPE + index;
        }
        return null;
    }

}