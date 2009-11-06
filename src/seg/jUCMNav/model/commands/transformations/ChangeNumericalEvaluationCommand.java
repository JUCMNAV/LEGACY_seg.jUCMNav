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

public class ChangeNumericalEvaluationCommand   extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private boolean cancelled = false;
	private int oldEval, newEval;
	EvaluationStrategyManager esm;
	
	private int[] values = { 100, 75, 50, 25, 0, -25, -50, -75, -100 };
	
	public ChangeNumericalEvaluationCommand( IntentionalElementRef intElemRef, int id )
	{
		this.intElemRef = intElemRef;
		esm = EvaluationStrategyManager.getInstance();
		
        if ( esm.getEvaluationStrategy() == null ){
        	cancelled = true;
        } else {
        	oldEval = esm.getEvaluation( intElemRef.getDef() );
        	if ( id < 9 )
        		newEval = values[ id ];
        	else {
        		Integer enteredValue = enterEvaluation();
        		if ( enteredValue == null )
        			cancelled = true;
        		else
        			newEval = enteredValue.intValue();
        	}
        }
	}
	
	public void execute()
	{
		if( cancelled )
			cancelled = false;
		else
			redo();
	}

	public void redo()
	{
		testPreConditions();

		esm.setIntentionalElementEvaluation( intElemRef.getDef(), newEval );

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

		if ( oldEval == Evaluation.EVALUATION_UNDEFINED )
			esm.setEvaluationForElement( intElemRef.getDef(), null );
		else
			esm.setIntentionalElementEvaluation( intElemRef.getDef(), oldEval );

		testPreConditions();
	}

	private Integer enterEvaluation()
	{	
	    String currentEval;
		Shell shell = new Shell();
	    IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);
	    
	    currentEval = ( oldEval == Evaluation.EVALUATION_UNDEFINED ) ? "" : Integer.toString( oldEval );
	
	    return ( dialog.open( "Enter Numerical Evaluation   (range: [-100,+100])", "Enter the new Numerical Evaluation: ", currentEval, -100, 100 ) );
	}
}

