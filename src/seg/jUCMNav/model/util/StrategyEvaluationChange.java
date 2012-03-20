package seg.jUCMNav.model.util;

import grl.EvaluationStrategy;
import grl.IntentionalElement;

public class StrategyEvaluationChange {
	
	private EvaluationStrategy strategy;
	private IntentionalElement element;
	int oldEvaluation, newEvaluation;
	
	public StrategyEvaluationChange( EvaluationStrategy es, IntentionalElement ie, int newEval, int oldEval ) {
		strategy = es;
		element = ie;
		newEvaluation = newEval;		
		oldEvaluation = oldEval;
	}

	public EvaluationStrategy getStrategy() {
		return strategy;
	}
	
	public IntentionalElement getIntentionalElement() {
		return element;
	}
	
	public int getNewEvaluation() {
		return newEvaluation;
	}

	public int getOldEvaluation() {
		return oldEvaluation;
	}
}
