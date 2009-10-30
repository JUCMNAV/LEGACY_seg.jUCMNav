package seg.jUCMNav.model.commands.transformations;

import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
*
* @author Andrew Miga
*/

public class ChangeQualitativeEvaluationCommand   extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private boolean cancelled = false;
	private QualitativeLabel oldQeval, newQeval;
	EvaluationStrategyManager esm;
	
	private QualitativeLabel values[] = { QualitativeLabel.SATISFIED_LITERAL, QualitativeLabel.WEAKLY_SATISFIED_LITERAL, QualitativeLabel.NONE_LITERAL,
			QualitativeLabel.WEAKLY_DENIED_LITERAL, QualitativeLabel.DENIED_LITERAL, QualitativeLabel.CONFLICT_LITERAL, QualitativeLabel.UNKNOWN_LITERAL }; 
	
	public ChangeQualitativeEvaluationCommand( IntentionalElementRef intElemRef, int id )
	{
		this.intElemRef = intElemRef;
		esm = EvaluationStrategyManager.getInstance();
		
        if ( esm.getEvaluationStrategy() == null ){
        	cancelled = true;
        } else {
        	oldQeval = esm.getEvaluationObject( intElemRef.getDef() ).getQualitativeEvaluation();
        	newQeval = values[ id ];
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

		esm.setIntentionalElementQualitativeEvaluation( intElemRef.getDef(), newQeval );
		
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

		esm.setIntentionalElementQualitativeEvaluation( intElemRef.getDef(), oldQeval );

		testPreConditions();
	}

}

