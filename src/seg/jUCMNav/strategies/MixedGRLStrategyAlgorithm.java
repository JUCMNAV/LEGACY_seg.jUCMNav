package seg.jUCMNav.strategies;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.ContributionType;
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
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urncore.IURNNode;

/**
 * This class implement the default GRL evaluation algorithm.
 * 
 * @author sghanava
 *
 */
public class MixedGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {
    
    /**
     * Data container object used by the propagation mechanism. 
     * @author sghanava
     *
     */
    private static class EvaluationCalculation{
        public IntentionalElement element;
        public int linkCalc;
        public int totalLinkDest;
        
        public EvaluationCalculation(IntentionalElement element, int totalLink){
            this.element = element;
            this.totalLinkDest = totalLink;
            linkCalc = 0;
        }
    }
    
    Vector evalReady; 
    HashMap evaluationCalculation;
    HashMap evaluations;

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        evalReady = new Vector();
        evaluationCalculation = new HashMap();
        this.evaluations = evaluations;
        
        Iterator it = strategy.getGrlspec().getIntElements().iterator();
        while (it.hasNext()){
            IntentionalElement element = (IntentionalElement)it.next();
            if (element.getLinksDest().size() == 0 || ((Evaluation)evaluations.get(element)).getStrategies() != null){
                evalReady.add(element);
            } else{
                EvaluationCalculation calculation = new EvaluationCalculation(element, element.getLinksDest().size());
                evaluationCalculation.put(element,calculation);
            }
        }
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#hasNextNode()
     */
    public boolean hasNextNode() {
        if (evalReady.size() > 0){
            return true;
        } 
        return false;
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#nextNode()
     */
    public IntentionalElement nextNode() {
        IntentionalElement intElem = (IntentionalElement)evalReady.remove(0);

        for (Iterator j=intElem.getLinksSrc().iterator();j.hasNext();){
        	// TODO Need to make sure this GRLLinkableElement is really an IntentionalElement
            IntentionalElement temp = (IntentionalElement) ((ElementLink)j.next()).getDest();
            if (evaluationCalculation.containsKey(temp)){
                EvaluationCalculation calc = (EvaluationCalculation)evaluationCalculation.get(temp);
                calc.linkCalc += 1;
                if (calc.linkCalc >= calc.totalLinkDest){
                    evaluationCalculation.remove(temp);
                    evalReady.add(calc.element);
                }
            }
        }
        return intElem;
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() { return IGRLStrategyAlgorithm.EVAL_MIXED; }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation)evaluations.get(element);
        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null)){
            return eval.getEvaluation();
        }
        int result = 0;
        int decompositionValue = -10000;
        int dependencyValue = 10000;
        int [] contributionValues = new int[100];
        int contribArrayIt = 0;
        
        Iterator it = element.getLinksDest().iterator(); //Return the list of elementlink
        while (it.hasNext()){
            ElementLink link = (ElementLink)it.next();
            if (link instanceof Decomposition){
                if (decompositionValue < -100){
                    decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                } else if (element.getDecompositionType().getValue() == DecompositionType.AND){
                    if (decompositionValue > ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    }
                } else if (element.getDecompositionType().getValue() == DecompositionType.OR){
                    if (decompositionValue < ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    }
                } 
            } else if (link instanceof Dependency){
                if (dependencyValue > ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
                        //&& ((Evaluation)evaluations.get(link.getSrc())).getEvaluation() != 0){
                    dependencyValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                }
            } else if (link instanceof Contribution){
                Contribution contrib = (Contribution)link;
                if (contrib.getContribution().getValue() != ContributionType.UNKNOWN){
                    int srcNode = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
                    if (srcNode != 0){
                       double resultContrib;
                        switch (contrib.getContribution().getValue()){
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
                        if (resultContrib != 0){
                            contributionValues[contribArrayIt] = 
                                (new Double(Math.round(resultContrib))).intValue();
                            contribArrayIt++;
                        }
                    }
                }
            }
        }
        if (decompositionValue >=-100){
            result = decompositionValue;
        }
        if (contributionValues.length > 0){
            
            boolean hasSatisfy = (result==100);
            boolean hasDeny = (result==-100);
            int contribValue = 0;
            
            for (int i=0;i<contribArrayIt;i++){
                if (contributionValues[i] == 100) hasSatisfy = true;
                if (contributionValues[i] == -100) hasDeny = true;
                contribValue += contributionValues[i];
            }
            result = result + contribValue;
            
            if (result > 100 || result<-100){
                result = (result/Math.abs(result))*100;
            }
            
            if (result >= (100 - StrategyEvaluationPreferences.getTolerance()) && !hasSatisfy){
                if(contribValue > 0)
                    result = Math.max(decompositionValue, 100 - StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.max(result, 100 - StrategyEvaluationPreferences.getTolerance());
            } else if (result <= (-100 + StrategyEvaluationPreferences.getTolerance()) && !hasDeny) {
                if( (contribValue) < 0 && (decompositionValue > -10000) ) // Need to consider that there might be no decomposition
                    result = Math.min(decompositionValue, -100 + StrategyEvaluationPreferences.getTolerance());
                else
                    result = Math.min(result, -100 + StrategyEvaluationPreferences.getTolerance());
            }
        }
        if ((dependencyValue <= 100) && (result > dependencyValue)){
            result = dependencyValue;
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor){
        float resultEval = 0;

        int total = 0;
        
        Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()){
            //Parse through the node bind to this actor
            ActorRef ref = (ActorRef)iter.next();
            Iterator iterNode = ref.getNodes().iterator();
            while(iterNode.hasNext()){
                IURNNode node = (IURNNode)iterNode.next();
                if(node instanceof IntentionalElementRef) {
                    IntentionalElementRef elementRef = (IntentionalElementRef)node;
                    IntentionalElement element = elementRef.getDef();
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);
                    int importance = element.getImportanceQuantitative();                    
                    if (importance != 0){
                        float deltaEval = ((float)evaluation)*(((float)importance)/100f);
                        resultEval += deltaEval;
                        total++;
                    }
                    
                       
                }
            }
        }
        if (total > 0){
            total = Math.round(resultEval/total);
        }
        return total;
    }

}
