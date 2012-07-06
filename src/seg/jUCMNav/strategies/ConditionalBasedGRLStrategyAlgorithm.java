package seg.jUCMNav.strategies;

import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.Metadata;

/**
 * This class implement the conditional GRL evaluation algorithm.
 * 
 * @author Azalia Shamsaei
 * 
 */
public class ConditionalBasedGRLStrategyAlgorithm extends FormulaBasedGRLStrategyAlgorithm {

    List strategyMetaDataValue;
    HashMap acceptStereotypes = new HashMap<String, String>();

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        MetadataHelper.cleanRunTimeMetadata(strategy.getGrlspec().getUrnspec());
        List sMetaData = strategy.getMetadata();
        Metadata acceptStereotype;
        // could be multiple ones, so not using MetadataHelper
        for (int i = 0; i < sMetaData.size(); i++) {
            acceptStereotype = (Metadata) sMetaData.get(i);
            if (acceptStereotype.getName().equalsIgnoreCase(Messages.getString("ConditionalGRLStrategyAlgorithm_acceptStereotype"))) { //$NON-NLS-1$                        
                acceptStereotypes.put(acceptStereotype.getValue().toUpperCase(), acceptStereotype.getValue());
            }
        }

        super.init(strategy, evaluations);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_CONDITION;
    }

    /**
     * This method decides whether an element needs to be ignored or not if an element has ConditionalGRLStrategyAlgorithm_IgnoreNode defined as metadata, it
     * should be ignored regardless if an element does not have any metadata it should never be ignored if an element has stereotype metadata then if the
     * metadata matches the strategy accept stereotype list it should NOT be ignored if the metadata does not match the strategy accept stereotype list it
     * should be ignored
     * 
     * @param element
     * @return
     */
    public boolean checkIgnoreElement(GRLLinkableElement element) {
        List eMetaData = element.getMetadata();
        Metadata elementMetadata;
        boolean foundAcceptacceptStereotype = false;
        int foundStereotype = 0;
        if (MetadataHelper.getMetaData(element, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode")) != null) //$NON-NLS-1$
        {
            return true;
        } else {
            for (int i = 0; i < eMetaData.size(); i++) {
                elementMetadata = (Metadata) eMetaData.get(i);
                if (acceptStereotypes.size() > 0 && !foundAcceptacceptStereotype) {
                    String val = elementMetadata.getValue();
                    if (val != null)
                        val.toUpperCase();
                    boolean isAcceptedStereotype = acceptStereotypes.containsKey(val);
                    if (elementMetadata.getName().toUpperCase().startsWith("ST_")) {
                        foundStereotype++;
                        if (isAcceptedStereotype)
                            foundAcceptacceptStereotype = true;
                    }
                }
            }
        }

        // if there is not stereotype the element won't be ignored
        foundAcceptacceptStereotype = (foundAcceptacceptStereotype || foundStereotype == 0);
        return !foundAcceptacceptStereotype;
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
        /* evaluation values of the nodes connected to this node via contribution links */
        int[] evaluationValues = new int[100];
        /* contribution value of the contribution links connected to the node */
        int[] contributionLinksValues = new int[100];
        /* used to keep a reference to the contribution links to be able to add metadata later on if required */
        ElementLink[] contributionLinks = new ElementLink[100];
        /* used to keep the contribution values that have to be ignored due to unsatisfied dependency */
        int[] ignoredContributionValue = new int[100];
        int contribArrayIt = 0;
        int ignoredContribArrayIt = 0;
        int consideredContribArrayIt = 0;
        int sumConsideredContributionLinks = 0;

        Iterator it = element.getLinksDest().iterator(); // Return the list of elementlink
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Decomposition) {
                decompositionValue = evaluateDecomposition(element, decompositionValue, it, link);
            } else if (link instanceof Dependency) {
                dependencyValue = evaluateDependency(dependencyValue, link);

                IntentionalElement src = (IntentionalElement) link.getSrc();
                if (src.getType().getName().equals("Ressource")) { //$NON-NLS-1$
                    boolean ignoreSrc = false;
                    ignoreSrc = checkIgnoreElement(src);
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
                    if (dependencyValue == 0 && !ignoreSrc) {
                        MetadataHelper.addMetaData(urnSpec, element, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
                    }
                    if (ignoreSrc) {
                        MetadataHelper.addMetaData(urnSpec, src, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
                        dependencyValue = 10000;
                    }
                }
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;

                boolean ignoreSrc = false;
                ignoreSrc = checkIgnoreElement(link.getSrc());

                int quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);

                if (ignoreSrc) {
                    ignoredContributionValue[ignoredContribArrayIt] = quantitativeContrib;
                    ignoredContribArrayIt++;
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
                    MetadataHelper.addMetaData(urnSpec, link.getSrc(), Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
                } else {
                    contributionLinksValues[consideredContribArrayIt] = quantitativeContrib;
                    contributionLinks[consideredContribArrayIt] = link;
                    int srcNodeEvaluationValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    evaluationValues[consideredContribArrayIt] = srcNodeEvaluationValue;

                    sumConsideredContributionLinks = sumConsideredContributionLinks + contributionLinksValues[consideredContribArrayIt];

                    consideredContribArrayIt++;

                    double resultContrib = super.computeContributionResult(link, contrib);

                    if (resultContrib != 0) {
                        contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();
                        contribArrayIt++;
                    }
                }
            }
        }

        if (ignoredContribArrayIt > 0 && consideredContribArrayIt > 0 && sumConsideredContributionLinks > 0) {
            int totalIgnoredContributionValue = 0;
            for (int i = 0; i < ignoredContribArrayIt; i++) {
                totalIgnoredContributionValue = totalIgnoredContributionValue + ignoredContributionValue[i];
            }

            int additionalContributionToRemainingNodes = totalIgnoredContributionValue;

            contributionValues = new int[100];
            contribArrayIt = 0;
            for (int j = 0; j < consideredContribArrayIt; j++) {

                contributionLinksValues[j] = contributionLinksValues[j]
                        + (additionalContributionToRemainingNodes * contributionLinksValues[j] / sumConsideredContributionLinks);

                if (contributionLinksValues[j] > 100) {
                    contributionLinksValues[j] = 100;
                } else if (contributionLinksValues[j] < -100) {
                    contributionLinksValues[j] = -100;
                }

                URNspec urnSpec = element.getGrlspec().getUrnspec();
                MetadataHelper.addMetaData(urnSpec, contributionLinks[j], Messages.getString("ConditionalGRLStrategyAlgorithm_RuntimeContribution"), //$NON-NLS-1$
                        Integer.toString(contributionLinksValues[j]));

                double resultContrib;

                resultContrib = (contributionLinksValues[j] * evaluationValues[j]) / 100;

                if (resultContrib != 0) {

                    contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();

                    contribArrayIt++;
                }
            }

        }

        result = ensureEvaluationWithinRange(result, decompositionValue, dependencyValue, contributionValues, contribArrayIt);

        return result;
    }

}
