package seg.jUCMNav.model.commands.transformations;

import javax.swing.JOptionPane;

import grl.IntentionalElementRef;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

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
        	else
        		enterEvaluation();
        }
	}
	
	public void execute() {
		if( cancelled )
			cancelled = false;
		else
			redo();
	}

	public void redo() {
		testPreConditions();

		esm.setIntentionalElementEvaluation( intElemRef.getDef(), newEval );

		testPostConditions();
	}

	
	public void testPostConditions() {
		assert intElemRef != null : "post no element!"; //$NON-NLS-1$
	}

	public void testPreConditions() {
		assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
	}

	public void undo() {
		testPostConditions();

		esm.setIntentionalElementEvaluation( intElemRef.getDef(), oldEval );

		testPreConditions();
	}

	private void enterEvaluation()
	{	
		String user_data = JOptionPane.showInputDialog( null, "Enter the Numerical Evaluation. Currently: (" + oldEval + ")" );
		
		if (user_data == null) {
			cancelled = true;
			return;
		}
		
		try {
			newEval = Integer.parseInt( user_data );
		} catch ( NumberFormatException nfe ) {
			JOptionPane.showMessageDialog( null, "Please enter an integer", "Error", JOptionPane.PLAIN_MESSAGE );
			cancelled = true;
		}
	}

}

