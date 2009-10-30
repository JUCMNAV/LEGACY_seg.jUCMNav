package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
*
* @author Andrew Miga
*/

public class SetNumericalEvaluationAction extends URNSelectionAction
{
    public static final String SET_NUMERICAL_EVALUATION = "seg.jUCMNav.SET_NUMERICAL_EVALUATION"; //$NON-NLS-1$
    private IntentionalElementRef selection;
    private int id;
    private String[] values = { "+100", "+75", "+50", "+25", "0", "-25", "-50", "-75", "-100", "Other.." };

	public SetNumericalEvaluationAction(IWorkbenchPart part, int id ) {
		super(part);
        setId( SET_NUMERICAL_EVALUATION + id );
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/StrategyView16.gif")); //$NON-NLS-1$
        setText( values[id] );
        this.id = id;
	}

    protected Command getCommand() {
        return new ChangeNumericalEvaluationCommand( selection, id );
    }

    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled()
    {
    	if ( EvaluationStrategyManager.getInstance().getEvaluationStrategy() == null )
    		return false;
    	
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalelementref();
            return true;
        default:
            return false;
        }
    }

	public static String generateId( int id ) {
		return SET_NUMERICAL_EVALUATION + id;
	}
	
}

