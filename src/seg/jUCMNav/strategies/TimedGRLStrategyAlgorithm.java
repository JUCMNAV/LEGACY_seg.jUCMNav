package seg.jUCMNav.strategies;

import java.util.ArrayList;
import java.util.Iterator;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * This class implements the Timed GRL evaluation algorithm.
 * 
 * @author aprajita
 * 
 */
public class TimedGRLStrategyAlgorithm extends QuantitativeGRLStrategyAlgorithm {
	
	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_TIMED;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
    	
    	getAggregation(element);
        
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
            
            //Check if link is deactivated
            Boolean deactive = false;
    		Metadata metaDeactStatusLink = MetadataHelper.getMetaDataObj(link, EvaluationStrategyManager.METADATA_DEACTSTATUS);
    		Metadata metaDeactStatusSrc = MetadataHelper.getMetaDataObj(link.getSrc(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
    		if (metaDeactStatusLink != null) {
    			String deactStatus = MetadataHelper.getMetaData(link, EvaluationStrategyManager.METADATA_DEACTSTATUS);
    			if (deactStatus.equalsIgnoreCase("true"))
    				deactive = true;
    		} else if (metaDeactStatusSrc != null) {
    			String deactStatus = MetadataHelper.getMetaData(link.getSrc(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
    			if (deactStatus.equalsIgnoreCase("true"))
    				deactive = true;
    		}
    		//If the link or its source is deactivated
    		if (deactive)
    			continue;
            
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
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
    	int sumEval = 0;
        int sumImportance = 0;
        ArrayList<URNmodelElement> addedElts = new ArrayList<URNmodelElement>();
        Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()) {
            // Parse through the node bound to this actor
            ActorRef ref = (ActorRef) iter.next();
            Iterator iterNode = ref.getNodes().iterator();
            while (iterNode.hasNext()) {
                IURNNode node = (IURNNode) iterNode.next();
                if (node instanceof IntentionalElementRef) {
                	IntentionalElementRef elementRef = (IntentionalElementRef) node;
                    IntentionalElement element = elementRef.getDef();
                    
                    //Skip this element if already added because of more than one references,else add it to the list
                    if (addedElts.contains(element))
                    	continue;
                    else
	                    addedElts.add(element);
                    
                    //Skip this element if it is deactivated
                    Metadata metaDeactStatus = MetadataHelper.getMetaDataObj(element, EvaluationStrategyManager.METADATA_DEACTSTATUS);
        			if (metaDeactStatus != null) {
        				String deactStatus = MetadataHelper.getMetaData(element, EvaluationStrategyManager.METADATA_DEACTSTATUS);
        				if (deactStatus.equalsIgnoreCase("true"))
        					continue;
        			}
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);
                    int importance = element.getImportanceQuantitative();

                    if (importance != 0 && StrategyAlgorithmImplementationHelper.isLegalStereotype(element)) {
                        sumEval += evaluation * importance;
                        sumImportance += importance;
                    }
                }
            }
            
            iterNode = ref.getChildren().iterator();
            while (iterNode.hasNext()) {
                IURNContainerRef node = (IURNContainerRef) iterNode.next();
                if (node instanceof ActorRef) {
                    ActorRef elementRef = (ActorRef) node;
                    Actor element = (Actor) elementRef.getContDef();
                    
                    //Skip this actor if already added because of more than one references,else add it to the list
                    if (addedElts.contains(element))
                    	continue;
                    else
	                    addedElts.add(element);
                    
                  //Skip this actor if it is deactivated
                    Metadata metaDeactStatus = MetadataHelper.getMetaDataObj(element, EvaluationStrategyManager.METADATA_DEACTSTATUS);
        			if (metaDeactStatus != null) {
        				String deactStatus = MetadataHelper.getMetaData(element, EvaluationStrategyManager.METADATA_DEACTSTATUS);
        				if (deactStatus.equalsIgnoreCase("true"))
        					continue;
        			}
                    int evaluation = EvaluationStrategyManager.getInstance().getActorEvaluation(element);
                    int importance = element.getImportanceQuantitative();

                    if (importance != 0 && StrategyAlgorithmImplementationHelper.isLegalStereotype(element)) {
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

}
