package seg.jUCMNav.model.commands.transformations;

import grl.ImportanceType;
import grl.IntentionalElementRef;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
*
* @author Andrew Miga
*/

public class ChangeQualitativeImportanceCommand   extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private int id;
	private boolean cancelled = false;
	private ImportanceType oldType, newType;

	public ChangeQualitativeImportanceCommand( IntentionalElementRef intElemRef, int id )
	{
		this.intElemRef = intElemRef;
		this.id = id;
		oldType = intElemRef.getDef().getImportance();
		
		if ( id < 4 ) { // input from sub-menu
			newType = ImportanceType.get( id );
    	} else if ( id == 4 ) { // input from key '+', increase importance if possible
    		
    		if ( oldType == ImportanceType.HIGH_LITERAL )
    			cancelled = true; // can't increase from HIGH
    		else {
    			int index = ImportanceType.VALUES.indexOf( oldType);    			
    			newType = ImportanceType.get( index-1 );
    			//System.out.println( "increase old type: " + oldType + " index: " + index + " new type: " + newType );
    		}    		
    	} else if ( id == 5 ) { // input from key '-', decrease importance if possible

    		if ( oldType == ImportanceType.NONE_LITERAL )
    			cancelled = true; // can't decrease from NONE
    		else {
    			int index = ImportanceType.VALUES.indexOf( oldType);
    			newType = ImportanceType.get( index+1 );    			
    			//System.out.println( "decrease old type: " + oldType + " index: " + index + " new type: " + newType );
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

		EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance( intElemRef.getDef(), newType );

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

		EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance( intElemRef.getDef(), oldType );

		testPreConditions();
	}

}
