package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.ChangeNumericalImportanceCommand;

/**
*
* @author Andrew Miga
*/

public class SetNumericalImportanceAction extends URNSelectionAction
{
    public static final String SET_NUMERICAL_IMPORTANCE = "seg.jUCMNav.SET_NUMERICAL_IMPORTANCE"; //$NON-NLS-1$
    private IntentionalElementRef selection;
    private int id;
    private String[] values = { "100", "75", "50", "25", "0", "Other..." };

	public SetNumericalImportanceAction( IWorkbenchPart part, int id ) {
		super(part);
        setId( SET_NUMERICAL_IMPORTANCE + id );
        setText( values[id] );
        this.id = id;
	}

    protected Command getCommand() {
        return new ChangeNumericalImportanceCommand( selection, id );
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

	public static String generateId(int id) {
		return SET_NUMERICAL_IMPORTANCE + id;
	}
	
}
