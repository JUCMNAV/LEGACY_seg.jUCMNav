package seg.jUCMNav.actions;

import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
*
* @author Andrew Miga
*/

public class SetQualitativeEvaluationAction extends URNSelectionAction
{
    public static final String SET_QUALITATIVE_EVALUATION = "seg.jUCMNav.SET_QUALITATIVE_EVALUATION"; //$NON-NLS-1$
    private IntentionalElementRef selection;
    private int id;
    private static String[] values = { "Satisfied", "Weakly Satisfied", "None", "Weakly Denied", "Denied", "Conflict", "Unknown", "Increase    (h)", "Decrease   (n)" };

	public SetQualitativeEvaluationAction(IWorkbenchPart part, int id ) {
		super(part);
        setId( SET_QUALITATIVE_EVALUATION + id );
        this.id = id;

        if ( id == 0)
        	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/satisficed.gif")); //$NON-NLS-1$
        else if ( id == 1 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/wsatisficed.gif")); //$NON-NLS-1$
        else if ( id == 2 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/none.gif")); //$NON-NLS-1$
        else if ( id == 3 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/wdenied.gif")); //$NON-NLS-1$
        else if ( id == 4 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/denied.gif")); //$NON-NLS-1$
        else if ( id == 5 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/conflict.gif")); //$NON-NLS-1$
        else if ( id == 6 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/undecided.gif")); //$NON-NLS-1$
        else if ( id == 7 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_up.gif")); //$NON-NLS-1$
        else if ( id == 8 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_down.gif")); //$NON-NLS-1$

        setText( values[id] );
	}

    protected Command getCommand()
    {
        return new ChangeQualitativeEvaluationCommand( selection, id );
    }

    /**
     * We need to have an intentional element reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled()
    {
    	EvaluationStrategyManager esm = EvaluationStrategyManager.getInstance();
    	
    	if ( esm.getEvaluationStrategy() == null )
    		return false;
    	
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalelementref();
            QualitativeLabel oldQeval = esm.getEvaluationObject( selection.getDef() ).getQualitativeEvaluation();
            
            if ( id < 7 )
            	return true;
            else if ( id == 7 ) { // increase operation, verify if possible
            	if ( oldQeval == QualitativeLabel.SATISFIED_LITERAL )
            		return false;  // can't increase from SATISFIED
            } else if ( id == 8 ) { // decrease operation, verify if possible
            	if ( oldQeval == QualitativeLabel.UNKNOWN_LITERAL )
            		return false; // can't decrease from UNKNOWN
            }
            
            return true;
        default:
            return false;
        }
    }

	public static String generateId( int id )
	{
		return SET_QUALITATIVE_EVALUATION + id;
	}
	
	public static String getId( String operation )
	{	
		for ( int index = 0; index < values.length; index++ ){
			if( values[index].contains( operation ) )
				return SET_QUALITATIVE_EVALUATION + index;
		}
		return null;
	}
	
}

