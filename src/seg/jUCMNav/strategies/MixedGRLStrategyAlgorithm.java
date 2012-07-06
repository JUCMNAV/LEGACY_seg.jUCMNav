package seg.jUCMNav.strategies;

import grl.Contribution;
import grl.ContributionType;
import grl.ElementLink;
import grl.Evaluation;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;

/**
 * This class implements the mixed GRL evaluation algorithm.
 * 
 * @author sghanava
 * 
 */
public class MixedGRLStrategyAlgorithm extends FormulaBasedGRLStrategyAlgorithm {
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_MIXED;
    }
    
    /*
     * (non-Javadoc)
     * @see seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm#computeContributionResult(grl.ElementLink, grl.Contribution)
     */
    protected double computeContributionResult(ElementLink link, Contribution contrib) {
        int value = EvaluationStrategyManager.getInstance().getActiveContribution(contrib).getValue();
        double resultContrib = 0;
        if (value != ContributionType.UNKNOWN) {
            int srcNode = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
            if (srcNode != 0) {
                switch (value) {
                case ContributionType.MAKE:
                    resultContrib = srcNode;
                    break;
                case ContributionType.HELP:
                    resultContrib = srcNode * 0.25;
                    break;
                case ContributionType.SOME_POSITIVE:
                    resultContrib = srcNode * 0.75;
                    break;
                case ContributionType.SOME_NEGATIVE:
                    resultContrib = srcNode * -0.75;
                    break;
                case ContributionType.HURT:
                    resultContrib = srcNode * -0.25;
                    break;
                case ContributionType.BREAK:
                    resultContrib = srcNode * -1;
                    break;
                default:
                    resultContrib = 0;
                    break;
                }
            }
        }
        return resultContrib;
    }
}
