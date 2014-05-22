package seg.jUCMNav.strategies;

import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.IntentionalElement;

import java.util.Iterator;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.util.FeatureUtil;

/**
 * This class implements the evaluation algorithm for Feature Models.
 * 
 * @author Yanji Liu, Yukun Su, gunterm
 * 
 */
public class FeatureModelStrategyAlgorithm extends FormulaBasedGRLStrategyAlgorithm {

    public static final String METADATA_WARNING = "_userSetEvaluationWarning"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);
    	// the quickReturn result is not null when the element does not have any incoming links, has a user-defined value, or has a KPI conversion
    	// contrary to the formula-based algorithm, do not return this result immediately, but compare it against the calculated result from the incoming links
        Integer quickReturn = preGetEvaluation(element, eval);
        
        // if there are incoming links, calculate the result from the incoming links
        int result = 0;
        boolean incomingLinks = false;
        if (element.getLinksDest().size() > 0) {
        	incomingLinks = true;
            int decompositionValue = -10000;
            int dependencyValue = 10000;
            int[] contributionValues = new int[100];
            int contribArrayIt = 0;
            
            // for feature model: automatic setting of contribution values of mandatory and optional links
            int mandatoryLinksNumber = 0;
            if (element instanceof Feature) {
                mandatoryLinksNumber = FeatureUtil.getNumberOfMandatoryDestLinks((Feature) element);
            }
            // the sum of the contributions of mandatory links is always 100
            int remainingMandatoryContribution = 100;

            // return the list of elementlink
            Iterator it = element.getLinksDest().iterator();
            while (it.hasNext()) {
                ElementLink link = (ElementLink) it.next();
                if (link instanceof Decomposition) {
                    decompositionValue = evaluateDecomposition(element, decompositionValue, it, link);
                } else if (link instanceof Dependency) {
                    dependencyValue = evaluateDependency(dependencyValue, link);
                } else if (link instanceof Contribution) {
                    Contribution contrib = (Contribution) link;
                    
                    // update contribution values of mandatory and optional links
                    if (element instanceof Feature && (link instanceof MandatoryFMLink || link instanceof OptionalFMLink)) {
                    	int quantitativeContrib;
                    	if (mandatoryLinksNumber == 0)
                    		// the element contains only optional links --> each optional link's contribution is 100
                    		quantitativeContrib = 100;
                    	else {
                    		// the element contains mixed optional & mandatory links or only mandatory links
                    		// the mandatory links split up evenly the remainingMandatoryContribution (rounding errors are tolerated)
                    		if (link instanceof MandatoryFMLink) {
                    			if (mandatoryLinksNumber == 1)                			
                        			// this is the last link 
                    				quantitativeContrib = remainingMandatoryContribution;
                    			else {
                    				// all other links before the last one 
                    				quantitativeContrib = remainingMandatoryContribution/mandatoryLinksNumber;
                    				remainingMandatoryContribution -= quantitativeContrib;
                    			}
                    			mandatoryLinksNumber--;
                    		} else
                    			// in the mixed case, an optional link's contribution is 0
                    			quantitativeContrib = 0;                		
                    	}
                    	contrib.setQuantitativeContribution(quantitativeContrib);
                    }

                    contribArrayIt = evaluateContribution(contributionValues, contribArrayIt, link, contrib);
                }
            }
            result = ensureEvaluationWithinRange(result, decompositionValue, dependencyValue, contributionValues, contribArrayIt);
            result = postGetEvaluation(element, eval, result);
        }
        
        // compare the results
        if (quickReturn != null && incomingLinks) {
        	if (result != quickReturn.intValue()) {
        		// the user set evaluation does not match the incoming links --> add warning
       			MetadataHelper.addMetaData(element.getGrlspec().getUrnspec(), element, METADATA_WARNING, 
       					Integer.toString(eval.getEvaluation()) + " != " + Integer.toString(result));
        	} else {
        		//user set evaluation is equal to evaluated value --> make sure warning does not exist
        		MetadataHelper.removeMetaData(element, METADATA_WARNING);
        	}
        } else {
        	// nothing to compare --> make sure warning does not exist
    		MetadataHelper.removeMetaData(element, METADATA_WARNING);
        }
        
        if (quickReturn != null)
        	// in any case keep the overridden evaluation value instead of the result from the incoming links
        	return quickReturn.intValue();
        else
        	return result;
    }

}
