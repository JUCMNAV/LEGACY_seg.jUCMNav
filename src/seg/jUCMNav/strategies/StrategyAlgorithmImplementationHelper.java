package seg.jUCMNav.strategies;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class StrategyAlgorithmImplementationHelper {

    // reduce redundant code without using inheritance
    public static void defaultInit(EvaluationStrategy strategy, HashMap evaluations, Vector evalReady, HashMap evaluationCalculation) {
        Iterator it = strategy.getGrlspec().getIntElements().iterator();
        while (it.hasNext()) {
            IntentionalElement element = (IntentionalElement) it.next();
            if (element.getLinksDest().size() == 0 || ((Evaluation) evaluations.get(element)).getStrategies() != null) {
                evalReady.add(element);
            } else {
                EvaluationCalculation calculation = new EvaluationCalculation(element, element.getLinksDest().size());
                evaluationCalculation.put(element, calculation);
            }
        }
    }
}
