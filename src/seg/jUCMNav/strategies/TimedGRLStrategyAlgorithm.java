package seg.jUCMNav.strategies;

import java.util.Iterator;

import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.IntentionalElement;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import urncore.Metadata;

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

}
