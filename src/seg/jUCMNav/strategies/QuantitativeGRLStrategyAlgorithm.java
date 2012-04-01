package seg.jUCMNav.strategies;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urncore.IURNNode;

/**
 * This class implement the default GRL evaluation algorithm.
 * 
 * @author sghanava
 * 
 */
public class QuantitativeGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {


    Vector evalReady;
    HashMap evaluationCalculation;
    HashMap evaluations;
    int minRange;

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        evalReady = new Vector();
        evaluationCalculation = new HashMap();
        this.evaluations = evaluations;
        // determines whether -100 or 0 should be used as a minimum scale.
        minRange = -100 * (StrategyEvaluationRangeHelper.getCurrentRange(strategy.getGrlspec().getUrnspec()) ? 0 : 1);

        StrategyAlgorithmImplementationHelper.defaultInit(strategy,  evaluations, evalReady, evaluationCalculation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#hasNextNode()
     */
    public boolean hasNextNode() {
        if (evalReady.size() > 0) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#nextNode()
     */
    public IntentionalElement nextNode() {
        IntentionalElement intElem = (IntentionalElement) evalReady.remove(0);

        for (Iterator j = intElem.getLinksSrc().iterator(); j.hasNext();) {
            // TODO Need to make sure this GRLLinkableElement is really an IntentionalElement
            IntentionalElement temp = (IntentionalElement) ((ElementLink) j.next()).getDest();
            if (evaluationCalculation.containsKey(temp)) {
                EvaluationCalculation calc = (EvaluationCalculation) evaluationCalculation.get(temp);
                calc.linkCalc += 1;
                if (calc.linkCalc >= calc.totalLinkDest) {
                    evaluationCalculation.remove(temp);
                    evalReady.add(calc.element);
                }
            }
        }
        return intElem;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_QUANTITATIVE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null)) {
            return eval.getEvaluation();
        }
        int result = 0;
        int decompositionValue = -10000;
        int dependencyValue = 10000;
        int[] contributionValues = new int[100];
        int contribArrayIt = 0;

        Iterator it = element.getLinksDest().iterator(); // Return the list of elementlink
        while (it.hasNext()) {
            ElementLink link = (ElementLink)it.next();
            if (link instanceof Decomposition){
            	if (element.getDecompositionType().getValue() == DecompositionType.AND){
            		String value = MetadataHelper.getMetaData(link.getSrc(), "ST_Legal"); //$NON-NLS-1$
            		if ("No".equals(value)){ //$NON-NLS-1$
            			if(!it.hasNext() && decompositionValue < minRange)
            				decompositionValue = 0; //case where all sources are tagged "N"
            			else
            				continue; 
            		} 
            		
            		if (decompositionValue < minRange){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    } else if (decompositionValue > ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    }

                } else if (element.getDecompositionType().getValue() == DecompositionType.OR || 
                           element.getDecompositionType().getValue() == DecompositionType.XOR){
                	if (decompositionValue < minRange){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                        
                	} else if (decompositionValue < ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

                    } else if (decompositionValue < ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    }
                }
            } else if (link instanceof Dependency) {
                if (dependencyValue > ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                    dependencyValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                }
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;
                int quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
                int srcNode = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

                double resultContrib;


                resultContrib = (quantitativeContrib * srcNode) / 100;

                if (resultContrib != 0) {
                    contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();
                    contribArrayIt++;
                }
            }
        }
        if (decompositionValue >= minRange) {
            result = decompositionValue;
        }
        if (contributionValues.length > 0) {

            boolean hasSatisfy = (result == 100);
            boolean hasDeny = (result == minRange);
            int contribValue = 0;

            for (int i = 0; i < contribArrayIt; i++) {
                if (contributionValues[i] == 100)
                    hasSatisfy = true;
                if (contributionValues[i] == minRange)
                    hasDeny = true;
                contribValue += contributionValues[i];
            }
            result = result + contribValue;

            if (result > 100) {
            	result = 100;
            }
            else if (result < minRange) {
                result = minRange;
            }

            if (result >= (100 - StrategyEvaluationPreferences.getTolerance()) && !hasSatisfy) {
                if (contribValue > 0)
                    result = Math.max(decompositionValue, 100 - StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.max(result, 100 - StrategyEvaluationPreferences.getTolerance());
            } else if (result <= (minRange + StrategyEvaluationPreferences.getTolerance()) && !hasDeny) {
                if ((contribValue) < 0 && (decompositionValue > 100 * minRange)) // Need to consider that there might be no decomposition
                    result = Math.min(decompositionValue, minRange + StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.min(result, minRange + StrategyEvaluationPreferences.getTolerance());
            }
        }
        if ((dependencyValue <= 100) && (result > dependencyValue)) {
            result = dependencyValue;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
        int sumEval = 0;
        int sumImportance = 0;

        Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()) {
            // Parse through the node bind to this actor
            ActorRef ref = (ActorRef) iter.next();
            Iterator iterNode = ref.getNodes().iterator();
            while (iterNode.hasNext()) {
                IURNNode node = (IURNNode) iterNode.next();
                if (node instanceof IntentionalElementRef) {
                    IntentionalElementRef elementRef = (IntentionalElementRef) node;
                    IntentionalElement element = elementRef.getDef();
                    String value = MetadataHelper.getMetaData(element, "ST_Legal"); //$NON-NLS-1$
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);
                    int importance = element.getImportanceQuantitative();

                    if (importance != 0 && !"No".equals(value)){ //$NON-NLS-1$
                        sumEval += evaluation * importance;
                        sumImportance += importance;
                    }

                }
            }
        }
        if (sumImportance > 0) {
            sumImportance = sumEval / sumImportance;
        }

        return sumImportance;
    }

	@Override
	public boolean isConstraintSolverAlgorithm() {
		// TODO Auto-generated method stub
		return false;
	}

}
