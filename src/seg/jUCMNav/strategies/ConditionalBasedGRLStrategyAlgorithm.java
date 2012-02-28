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
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urn.URNspec;
import urncore.IURNNode;
import urncore.Metadata;

/**
 * This class implement the default GRL evaluation algorithm.
 * 
 * @author Azalia Shamsaei
 * 
 */
public class ConditionalBasedGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {

    /**
     * Data container object used by the propagation mechanism.
     * 
     * @author Azalia Shamsaei
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
        for (int i = 0; i < sMetaData.size(); i++) {
            acceptStereotype = (Metadata) sMetaData.get(i);
            if (acceptStereotype.getName().equals(Messages.getString("ConditionalGRLStrategyAlgorithm_acceptStereotype"))) { //$NON-NLS-1$                        
                acceptStereotypes.put(acceptStereotype.getValue(), acceptStereotype.getValue());
            }
        }

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
        return IGRLStrategyAlgorithm.EVAL_QUANTITATIVE;
    }
    /**
     * This method decides whether an element needs to be ignored or not
     * if an element has ConditionalGRLStrategyAlgorithm_IgnoreNode defined as metadata, it should be ignored regardless
     * if an element does not have any metadata it should never be ignored
     * if an element has stereotype metadata then 
     *          if the metadata matches the strategy accept stereotype list it should NOT be ignored
     *          if the metadata does not match the strategy accept stereotype list it should be ignored  
     * @param element
     * @return
     */
    public boolean checkIgnoreElement(GRLLinkableElement element) {
        List eMetaData = element.getMetadata();
        Metadata elementMetadata;
        boolean ignoreSrc = false;
        boolean foundAcceptacceptStereotype = false;
        int foundStereotype = 0;        
        for (int i = 0; i < eMetaData.size(); i++) {
            elementMetadata = (Metadata) eMetaData.get(i);            
            if (elementMetadata.getName().equals(Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"))) { //$NON-NLS-1$                        
                ignoreSrc = true;
                break;
            }
            if (acceptStereotypes.size() > 0 && !foundAcceptacceptStereotype) {                
                if (elementMetadata.getName().startsWith("ST_") && !acceptStereotypes.containsKey(elementMetadata.getValue())) { //$NON-NLS-1$
                    foundStereotype++;
                } else if (elementMetadata.getName().startsWith("ST_") && acceptStereotypes.containsKey(elementMetadata.getValue())) { //$NON-NLS-1$
                    foundStereotype++;
                    foundAcceptacceptStereotype = true;
                }
            }
        }
        // if there is not stereotype the element won't be ignored 
        foundAcceptacceptStereotype = (foundAcceptacceptStereotype || foundStereotype == 0);
        return (ignoreSrc || !foundAcceptacceptStereotype );
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
                if (element.getDecompositionType().getValue() == DecompositionType.AND) {
                    String value = MetadataHelper.getMetaData(link.getSrc(), "ST_Legal"); //$NON-NLS-1$
                    if ("No".equals(value)) { //$NON-NLS-1$
                        if (!it.hasNext() && decompositionValue < -100)
                            decompositionValue = 0; // case where all sources are tagged "N"
                        else
                            continue;
                    }

                    if (decompositionValue < -100) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    } else if (decompositionValue > ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    }

                } else if (element.getDecompositionType().getValue() == DecompositionType.OR
                        || element.getDecompositionType().getValue() == DecompositionType.XOR) {
                    if (decompositionValue < -100) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

                    } else if (decompositionValue < ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

                    } else if (decompositionValue < ((Evaluation) evaluations.get(link.getSrc())).getEvaluation()) {
                        decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    }
                }
            } else if (link instanceof Dependency) {
                IntentionalElement src = (IntentionalElement) link.getSrc();
                if (dependencyValue > ((Evaluation) evaluations.get(src)).getEvaluation()) {
                    dependencyValue = ((Evaluation) evaluations.get(src)).getEvaluation();

                }
               if (src.getType().getName().equals("Ressource")) { //$NON-NLS-1$
                    boolean ignoreSrc = false;
                    ignoreSrc = checkIgnoreElement(src);
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
                    if (dependencyValue == 0 && !ignoreSrc) {
                        MetadataHelper.addMetaData(urnSpec, element, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ $NON-NLS-2$
                    }
                    if (ignoreSrc) {
                        MetadataHelper.addMetaData(urnSpec, src, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ $NON-NLS-2$
                        dependencyValue = 10000;
                    }
                }
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;

                boolean ignoreSrc = false;
                ignoreSrc = checkIgnoreElement(link.getSrc());

                int quantitativeContrib = contrib.getQuantitativeContribution();

                if (ignoreSrc) {
                    ignoredContributionValue[ignoredContribArrayIt] = quantitativeContrib;
                    ignoredContribArrayIt++;                   
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
                    MetadataHelper.addMetaData(urnSpec, link.getSrc(), Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"), ""); //$NON-NLS-1$ $NON-NLS-2$
                } else {
                    contributionLinksValues[consideredContribArrayIt] = quantitativeContrib;
                    contributionLinks[consideredContribArrayIt] = link;
                    int srcNodeEvaluationValue = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();
                    evaluationValues[consideredContribArrayIt] = srcNodeEvaluationValue;

                    sumConsideredContributionLinks = sumConsideredContributionLinks + contributionLinksValues[consideredContribArrayIt];

                    consideredContribArrayIt++;

                    double resultContrib;
                    resultContrib = (quantitativeContrib * srcNodeEvaluationValue) / 100;

                    if (resultContrib != 0) {
                        contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();
                        contribArrayIt++;
                    }

                }

            }
        }
        if (decompositionValue >= -100) {

            result = decompositionValue;
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
                MetadataHelper.addMetaData(urnSpec, contributionLinks[j], Messages.getString("ConditionalGRLStrategyAlgorithm_RuntimeContribution"),
                        Integer.toString(contributionLinksValues[j]));

                double resultContrib;

                resultContrib = (contributionLinksValues[j] * evaluationValues[j]) / 100;

                if (resultContrib != 0) {

                    contributionValues[contribArrayIt] = (new Double(Math.round(resultContrib))).intValue();

                    contribArrayIt++;
                }
            }

        }
        if (contribArrayIt > 0) {

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
            // Parse through the nodes bound to this actor
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

                    if (importance != 0 && !"No".equals(value)) { //$NON-NLS-1$
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
