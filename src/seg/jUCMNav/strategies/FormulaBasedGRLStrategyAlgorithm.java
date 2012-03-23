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
import java.util.List;
import java.util.Vector;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urncore.IURNNode;
import urncore.Metadata;

import com.primalworld.math.MathEvaluator;

/**
 * This class implement the default GRL quantitative evaluation algorithm, with KPI aggregation.
 * 
 * @author alireza pourshahid
 * 
 */
public class FormulaBasedGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {

    /**
     * Data container object used by the propagation mechanism.
     * 
     * @author Alireza Pourshahid
     * 
     */
    private static class EvaluationCalculation {
        public IntentionalElement element;
        public int linkCalc;
        public int totalLinkDest;

        public EvaluationCalculation(IntentionalElement element, int totalLink) {
            this.element = element;
            this.totalLinkDest = totalLink;
            linkCalc = 0;
        }
    }

    Vector evalReady;
    HashMap evaluationCalculation;
    HashMap evaluations;    

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        evalReady = new Vector();
        evaluationCalculation = new HashMap();
        this.evaluations = evaluations;

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
        return IGRLStrategyAlgorithm.EVAL_FORMULA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);

        List eMetaData = element.getMetadata();
        String formula=""; //$NON-NLS-1$
        Metadata formulaMetaData;
        MathEvaluator mathEvaluator = null;
        
        for (int i = 0; i < eMetaData.size(); i++) {
            formulaMetaData = (Metadata) eMetaData.get(i);
            if (formulaMetaData.getName().equals(Messages.getString("FormulaBasedGRLStrategyAlgorithm_Formula"))) { //$NON-NLS-1$
                formula = formulaMetaData.getValue();
                mathEvaluator = new MathEvaluator(formula);
                break;
            }
        }
        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null && mathEvaluator == null)) {
            return eval.getEvaluation();
        }
        int result = 0;
        int decompositionValue = -10000;
        int dependencyValue = 10000;
        int[] contributionValues = new int[100];
        int contribArrayIt = 0;

        Iterator it = element.getLinksDest().iterator(); // Return the list of elementlink
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Decomposition) {
                if (decompositionValue < -100) {
                    decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                } else if (element.getDecompositionType().getValue() == DecompositionType.AND) {
                    if (decompositionValue > ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    }
                } else if (element.getDecompositionType().getValue() == DecompositionType.OR || 
                           element.getDecompositionType().getValue() == DecompositionType.XOR) {
                    if (decompositionValue < ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    }
                }
            } else if (link instanceof Dependency) {
                if (dependencyValue > ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                    // && ((Evaluation)evaluations.get(link.getSrc())).getEvaluation() != 0){
                    dependencyValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                }
            } else if (link instanceof Contribution && null == mathEvaluator) {
                Contribution contrib = (Contribution) link;
                //int quantitativeContrib = contrib.getQuantitativeContribution();
                int quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
                int srcNode = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

                double resultContrib;

                resultContrib = (quantitativeContrib * srcNode) / 100;

                if (resultContrib != 0) {
                    contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();
                    contribArrayIt++;
                }
            } else if (link instanceof Contribution && null != mathEvaluator) {
                Contribution contrib = (Contribution) link;
                //int quantitativeContrib = contrib.getQuantitativeContribution();
                int quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
                double srcNodeEvaluationValue = ((Evaluation) evaluations.get(link.getSrc())).getKpiEvalValueSet().getEvaluationValue();
                // TODO: it might be better if we change this to use the name of the source node as opposed to link
                // TODO: I have noticed if the name of the link does not match the variables used in the formula it can cause errors.
                //       This is partially caught now and displayed on Ststem.err, to prevent crashing (Daniel)
                mathEvaluator.addVariable(contrib.getName(), srcNodeEvaluationValue);

            }
        }
        if (decompositionValue >= -100) {
            result = decompositionValue;
        }
        if (contributionValues.length > 0) {

            boolean hasSatisfy = (result == 100);
            boolean hasDeny = (result == -100);
            int contribValue = 0;

            for (int i = 0; i < contribArrayIt; i++) {
                if (contributionValues[i] == 100)
                    hasSatisfy = true;
                if (contributionValues[i] == -100)
                    hasDeny = true;
                contribValue += contributionValues[i];
            }
            result = result + contribValue;

            if (result > 100 || result < -100) {
                result = (result / Math.abs(result)) * 100;
            }

            if (result >= (100 - StrategyEvaluationPreferences.getTolerance()) && !hasSatisfy) {
                if (contribValue > 0)
                    result = Math.max(decompositionValue, 100 - StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.max(result, 100 - StrategyEvaluationPreferences.getTolerance());
            } else if (result <= (-100 + StrategyEvaluationPreferences.getTolerance()) && !hasDeny) {
                if ((contribValue) < 0 && (decompositionValue > -10000)) // Need to consider that there might be no decomposition
                    result = Math.min(decompositionValue, -100 + StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.min(result, -100 + StrategyEvaluationPreferences.getTolerance());
            }
        }
        if ((dependencyValue <= 100) && (result > dependencyValue)) {
            result = dependencyValue;
        }
        if (null != mathEvaluator) {
            double resultContrib;
            Double srcAfterFormula;
            srcAfterFormula = mathEvaluator.getValue();
            
            if (srcAfterFormula != null) {
                resultContrib = srcAfterFormula.doubleValue();
            }
            else {
                resultContrib = 0.0;
                System.err.println("Incorrect formula '" + formula + "' in KPI '" + element.getName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            
            eval.getKpiEvalValueSet().setEvaluationValue(resultContrib);
            EvaluationStrategyManager strategyManager = EvaluationStrategyManager.getInstance();
            strategyManager.calculateIndicatorEvalLevel(eval);
            result = eval.getEvaluation();
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
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);
                    int importance = element.getImportanceQuantitative();
                    if (importance != 0) {
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
