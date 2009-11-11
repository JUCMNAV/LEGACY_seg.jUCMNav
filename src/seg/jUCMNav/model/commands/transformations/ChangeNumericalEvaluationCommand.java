package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
	private boolean cancelled = false;
	EvaluationStrategyManager esm;
	
	private int[] values = { 100, 75, 50, 25, 0, -25, -50, -75, -100 };
	
	private class IElementState
	{
		IntentionalElementRef intElemRef;
		int oldEval, newEval;
	}
	
	Vector intElementStates;

	public ChangeNumericalEvaluationCommand( List intElemRefs, int id )
	{
		int enteredEval = 0;
		esm = EvaluationStrategyManager.getInstance();
		
        if ( esm.getEvaluationStrategy() == null ){
        	cancelled = true;
        } else {
        	
        	if ( id == 9 ) { // enter value through dialog
        	    String currentEval = ( intElemRefs.size() > 1 ) ? "" : Integer.toString( esm.getEvaluation( ((IntentionalElementRef) (intElemRefs.get(0))).getDef() ) );
        		Integer enteredValue = enterEvaluation( currentEval );
        		if ( enteredValue == null ) {
        			cancelled = true;
        			return;
        		}
        		else
        			enteredEval = enteredValue.intValue();        		
        	}
        	
    		intElementStates = new Vector();
    		
    		for ( Iterator iter = intElemRefs.iterator(); iter.hasNext(); ) {
    			
    			IntentionalElementRef currentIERef = (IntentionalElementRef) iter.next();
    			IElementState ies = new IElementState();
    			
    			ies.intElemRef = currentIERef;
    			ies.oldEval = esm.getEvaluation( currentIERef.getDef() );
    			intElementStates.add( ies );

    			if ( id <= 8 ) { // input from sub-menu +100 -> -100
    				ies.newEval = values[ id ];
    			} else if ( id == 9 ){ // new value entered through dialog
    				ies.newEval = enteredEval;
    			} else if ( id == 10 ) { // increase evaluation if possible
        		
    				if ( ies.oldEval == 100 )
    					cancelled = true; // can't increase from 100
    				else
    					ies.newEval = ies.oldEval + 1;
        		
    			} else if ( id == 11 ) { // decrease evaluation if possible

    				if ( ies.oldEval == -100 )
    					cancelled = true; // can't decrease from -100
    				else
    					ies.newEval = ies.oldEval - 1;
    			}
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

		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			esm.setIntentionalElementEvaluation( ies.intElemRef.getDef(), ies.newEval );
		}
		
		testPostConditions();
	}

	
	public void testPostConditions()
	{
		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			assert ies.intElemRef != null : "post no element!"; //$NON-NLS-1$
		}
	}

	public void testPreConditions()
	{
		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			assert ies.intElemRef != null : "pre no element!"; //$NON-NLS-1$
		}
	}

	public void undo()
	{
		testPostConditions();

//		if ( oldEval == Evaluation.EVALUATION_UNDEFINED )
//			esm.setEvaluationForElement( intElemRef.getDef(), null );
		
		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			esm.setIntentionalElementEvaluation( ies.intElemRef.getDef(), ies.oldEval );
		}

		testPreConditions();
	}

	private Integer enterEvaluation( String currentEval )
	{	
		Shell shell = new Shell();
	    IntegerInputRangeDialog dialog = new IntegerInputRangeDialog( shell );
	    	
	    return ( dialog.open( "Enter Numerical Evaluation   (range: [-100,+100])", "Enter the new Numerical Evaluation: ", currentEval, -100, 100 ) );
	}
}

