package seg.jUCMNav.strategies;

import grl.Actor;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.QualitativeLabel;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.QualitativeMapping;
import grl.kpimodel.QualitativeMappings;

import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;

/**
 * This class implements the quantitative GRL evaluation algorithm.
 * 
 * @author sghanava, gunterm
 * 
 */
public class QuantitativeGRLStrategyAlgorithm extends PropagationGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {

    int minRange;

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        // determines whether -100 or 0 should be used as a minimum scale.
        minRange = -100 * (StrategyEvaluationRangeHelper.getCurrentRange(strategy.getGrlspec().getUrnspec()) ? 0 : 1);
        super.init(strategy, evaluations);
        
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
        Integer quickReturn = preGetEvaluation(element, eval);
        if (quickReturn != null) {
            return quickReturn.intValue();
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
                decompositionValue = evaluateDecomposition(element, decompositionValue, it, link);
            } else if (link instanceof Dependency) {
                dependencyValue = evaluateDependency(dependencyValue, link);
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;
                contribArrayIt = evaluateContribution(contributionValues, contribArrayIt, link, contrib);
            }
        }
        result = ensureEvaluationWithinRange(result, decompositionValue, dependencyValue, contributionValues, contribArrayIt);
        result = postGetEvaluation(element, eval, result);
        return result;
    }

    protected Integer preGetEvaluation(IntentionalElement element, Evaluation eval) {
        // return immediately if we can
        Integer result = null;

        EvaluationStrategyManager strategyManager = EvaluationStrategyManager.getInstance();
        KPIEvalValueSet set = strategyManager.getActiveKPIEvalValueSet(element);
        // has a qualitative value & a mapping, load it from there instead of looking at current value. 
        if (set.getKpiConv() instanceof QualitativeMappings && set.getQualitativeEvaluationValue()!=null && set.getQualitativeEvaluationValue().length()>0 &&  element.getGrlspec()!=null)
        {
            QualitativeMappings mappings = (QualitativeMappings) set.getKpiConv();
            boolean found = false;
            for (Iterator iterator = mappings.getMapping().iterator(); iterator.hasNext();) {
                QualitativeMapping map = (QualitativeMapping) iterator.next();
                if (set.getQualitativeEvaluationValue().equalsIgnoreCase(map.getRealWorldLabel()))
                {
                    // load the evaluationValue from the conversion. 
                    result = map.getEvaluation(); 
                    eval.setQualitativeEvaluation(map.getQualitativeEvaluation());
                    found = true;
                }
            }
            if (!found) { 
                result = EvaluationStrategyManager.getQuantitativeValueForQualitativeEvaluation(QualitativeLabel.UNKNOWN_LITERAL, element.getGrlspec().getUrnspec(), 0);
                eval.setQualitativeEvaluation(QualitativeLabel.UNKNOWN_LITERAL);
            }
        }
        else  if (element.getLinksDest().size() == 0 || eval.getIntElement() != null) {
            result = eval.getEvaluation();
        }
        
        return result;
    }

    protected int postGetEvaluation(IntentionalElement element, Evaluation eval, int result) {
        // nothing in default implementation.
        return result;
    }

    protected int ensureEvaluationWithinRange(int result, int decompositionValue, int dependencyValue, int[] contributionValues, int contribArrayIt) {
        if (decompositionValue >= minRange) {
            result = decompositionValue;
        }
        if (contribArrayIt > 0) {
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
            } else if (result < minRange) {
                result = minRange;
            }

        	// this if statement was added to support the CORE interface; when jUCMNav is accessed through the CORE interface,
        	// the plugin environment is not defined which causes a null pointer exception here
            int tolerance = 0;
            if (!COREFactory4URN.isCOREInterfaceActive())
            	tolerance = StrategyEvaluationPreferences.getTolerance();
            				
            if (result >= (100 - tolerance) && !hasSatisfy) {
                if (contribValue > 0)
                    result = Math.max(decompositionValue, 100 - tolerance);
                else
                    result = Math.max(result, 100 - tolerance);
            } else if (result <= (minRange + tolerance) && !hasDeny) {
                if ((contribValue) < 0 && (decompositionValue > 100 * minRange)) // Need to consider that there might be no decomposition
                    result = Math.min(decompositionValue, minRange + tolerance);
                else
                    result = Math.min(result, minRange + tolerance);
            }
        }
        if ((dependencyValue <= 100) && (result > dependencyValue)) {
            result = dependencyValue;
        }
        return result;
    }

    protected int evaluateDecomposition(IntentionalElement element, int decompositionValue, Iterator it, ElementLink link) {
        if (element.getDecompositionType().getValue() == DecompositionType.AND) {
            if (!StrategyAlgorithmImplementationHelper.isLegalStereotype(link.getSrc())) {
                if (!it.hasNext() && decompositionValue < minRange)
                    decompositionValue = 0; // case where all sources are tagged "N"
                else
                    return decompositionValue; // use default value.
            }

            if (decompositionValue < minRange) {
                decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
            } else
                decompositionValue = evaluateDependency(decompositionValue, link);

        } else if (element.getDecompositionType().getValue() == DecompositionType.OR || element.getDecompositionType().getValue() == DecompositionType.XOR) {
            if (decompositionValue < minRange) {
                decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

            } else if (decompositionValue < ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
            }
        }
        return decompositionValue;
    }

    protected int evaluateDependency(int dependencyValue, ElementLink link) {
        if (dependencyValue > ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
            dependencyValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
        }
        return dependencyValue;
    }

    protected int evaluateContribution(int[] contributionValues, int contribArrayIt, ElementLink link, Contribution contrib) {
        double resultContrib = computeContributionResult(link, contrib);

        if (resultContrib != 0) {
            contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();
            contribArrayIt++;
        }
        return contribArrayIt;
    }

    protected double computeContributionResult(ElementLink link, Contribution contrib) {
        int quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
        int srcNode = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

        double resultContrib;
        resultContrib = (quantitativeContrib * srcNode) / 100;
        return resultContrib;
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
      return StrategyAlgorithmImplementationHelper.defaultActorEvaluation(actor);
    }

    @Override
    public boolean isConstraintSolverAlgorithm() {
        return false;
    }

}
