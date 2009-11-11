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

public class ChangeNumericalImportanceCommand  extends Command implements JUCMNavCommand
{
	private static int[] values = { 100, 75, 50, 25, 0 };
    private boolean cancelled = false;

	private class IElementState
	{
		IntentionalElementRef intElemRef;
		int oldValue, newValue;
	}
	
	Vector intElementStates;

	public ChangeNumericalImportanceCommand( List intElemRefs, int id )
	{
		int enteredImportance = 0;

		if ( id == 5 ) { // enter value through dialog
    	    String currentValue = ( intElemRefs.size() > 1 ) ? "" : Integer.toString( ((IntentionalElementRef) (intElemRefs.get(0))).getDef().getImportanceQuantitative() );
    		Integer enteredValue = enterImportance( currentValue );
    		if ( enteredValue == null ) {
    			cancelled = true;
    			return;
    		}
    		else
    			enteredImportance = enteredValue.intValue();        		
    	}
    	
		intElementStates = new Vector();
		
		for ( Iterator iter = intElemRefs.iterator(); iter.hasNext(); ) {
			
			IntentionalElementRef currentIERef = (IntentionalElementRef) iter.next();
			IElementState ies = new IElementState();
			
			ies.intElemRef = currentIERef;
			ies.oldValue = currentIERef.getDef().getImportanceQuantitative();
			intElementStates.add( ies );

			if ( id <= 4 ) { // input from sub-menu +100 -> 0
				ies.newValue = values[ id ];
			} else if ( id == 5 ){ // new value entered through dialog
				ies.newValue = enteredImportance;
			} else if ( id == 6 ) { // increase evaluation if possible
    		
				if ( ies.oldValue == 100 )
					cancelled = true; // can't increase from 100
				else
					ies.newValue = ies.oldValue + 1;
    		
			} else if ( id == 7 ) { // decrease evaluation if possible

				if ( ies.oldValue == 0 )
					cancelled = true; // can't decrease from 0
				else
					ies.newValue = ies.oldValue - 1;
			}
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

		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance( ies.intElemRef.getDef(), ies.newValue );
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

		for ( Iterator iter = intElementStates.iterator(); iter.hasNext(); ) {
			IElementState ies = (IElementState) iter.next();
			EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance( ies.intElemRef.getDef(), ies.oldValue );
		}
		
		testPreConditions();
	}

	private Integer enterImportance( String currentValue )
	{	
		Shell shell = new Shell();
	    IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);
	    	
	    return ( dialog.open( "Enter Numerical Importance   (range: [0, 100])", "Enter the new Numerical Importance: ", currentValue, 0, 100 ) );		
	}
}
