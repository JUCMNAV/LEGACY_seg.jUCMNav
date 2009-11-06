package seg.jUCMNav.model.commands.transformations;

import grl.Evaluation;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.IntegerInputRangeDialog;

/**
 *
 * @author Andrew Miga
 */

public class ChangeNumericalImportanceCommand  extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private int id, oldValue, newValue;
	private static int[] values = { 100, 75, 50, 25, 0 };
    private boolean cancelled = false;

	public ChangeNumericalImportanceCommand( IntentionalElementRef intElemRef, int id )
	{
		this.intElemRef = intElemRef;
		this.id = id;

		oldValue = intElemRef.getDef().getImportanceQuantitative();
		
		if ( id < 5 )
			newValue = values[id];
		else {
    		Integer enteredValue = enterImportance();
    		if ( enteredValue == null )
    			cancelled = true;
    		else
    			newValue = enteredValue.intValue();
		}
	}
	
	public void execute()
	{
		if ( cancelled )
			cancelled = false;
		else
			redo();
	}

	public void redo()
	{
		testPreConditions();

		EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance( intElemRef.getDef(), newValue );

		testPostConditions();
	}

	public void testPostConditions()
	{
		assert intElemRef != null : "post no element!"; //$NON-NLS-1$
	}

	public void testPreConditions()
	{
		assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
	}

	public void undo()
	{
		testPostConditions();

		EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance( intElemRef.getDef(), oldValue );

		testPreConditions();
	}

	private Integer enterImportance()
	{	
	    String currentValue;
		Shell shell = new Shell();
	    IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);
	    
	    currentValue = ( oldValue == Evaluation.EVALUATION_UNDEFINED ) ? "" : Integer.toString( oldValue );
	
	    return ( dialog.open( "Enter Numerical Importance   (range: [0, 100])", "Enter the new Numerical Importance: ", currentValue, 0, 100 ) );		
	}
}
