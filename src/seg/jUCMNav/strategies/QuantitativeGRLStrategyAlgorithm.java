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
import java.util.Vector;

import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urn.URNspec;
import urncore.Metadata;

/**
 * This class implements the quantitative GRL evaluation algorithm.
 * 
 * @author sghanava, gunterm
 * 
 */
public class QuantitativeGRLStrategyAlgorithm extends PropagationGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {
	
	/**
     * Metadata name used to store run-time aggregate evaluations
     */
    public static final String METADATA_AGGREVAL = "_aggrEval"; //$NON-NLS-1$
    public static final String METADATA_ADDAGGR = "_addAggregate"; //$NON-NLS-1$
    
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
        
        //get Aggregate Contribution for element if Quantitative GRL strategy selected
    	if(getEvaluationType() == IGRLStrategyAlgorithm.EVAL_QUANTITATIVE){
        	getAggregation(element);
        }  
    	
    	//get Aggregate Contribution for element from QualitativeGRLStrategyAlgorithm if Mixed GRL strategy selected
    	else if(getEvaluationType() == IGRLStrategyAlgorithm.EVAL_MIXED){
        	QualitativeGRLStrategyAlgorithm qgrl = new QualitativeGRLStrategyAlgorithm(); 
        	qgrl.getAggregation(element);
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

            int tolerance = StrategyEvaluationPreferences.getTolerance();
            				
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
    	int quantitativeContrib = 0;
        String addAggrValue = "false";
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(link, METADATA_AGGREVAL);
        if(MetadataHelper.getMetaDataObj(link.getSrc(), METADATA_ADDAGGR) != null){
        	addAggrValue = MetadataHelper.getMetaData(link.getSrc(), METADATA_ADDAGGR);
        }
        
        //If "Aggregate Contribution" is enabled and has a value, then 
        //individual contribution of the link is ignored for evaluation
        if((addAggrValue.compareTo("true") == 0) && metaNumerical != null){
        	quantitativeContrib = 0;
        	        	                  	
        }else{
        	quantitativeContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
        }
    	int srcNode = ((Evaluation) evaluations.get(link.getSrc())).getEvaluation();

        double resultContrib;
        resultContrib = (quantitativeContrib * srcNode) / 100;
        return resultContrib;
    }
    
    /**
     * Check if Aggregate Contribution needed for element and compute its value
     */
    protected void getAggregation(IntentionalElement element){
    	
    	Iterator itSrc = element.getLinksSrc().iterator(); // Return the list of elementlink
        while (itSrc.hasNext()) {
            ElementLink linkSrc = (ElementLink) itSrc.next();
            if (linkSrc instanceof Contribution) {
            	
            	boolean isDecomp = false;
            	
            	//Add Metadata to turn on/off aggregation for intentional elements 
                Metadata metaAddAggr = MetadataHelper.getMetaDataObj(element, METADATA_ADDAGGR);
            	
                //Set the metadata to default "false" if not added before
                if (metaAddAggr == null){
            		// Add new run-time metadata for this element
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
            		MetadataHelper.addMetaData(urnSpec, element, METADATA_ADDAGGR, "disable");
            	}
                for (Object link1: element.getLinksDest()) {
                    if (link1 instanceof Decomposition){
                    	isDecomp = true;
                    	break;
                    }
                }
            	
            	//If the metadata for aggregation is "true" and link isn't mandatory or optional
            	if(!(linkSrc instanceof MandatoryFMLink) && !(linkSrc instanceof OptionalFMLink) && 
            			(MetadataHelper.getMetaData(element, METADATA_ADDAGGR).compareTo("disable") != 0) &&
            					(isDecomp == true) ){
            		
            		if(element.getDecompositionType().getValue() == DecompositionType.AND){
            			computeAggregation(linkSrc,"And");
                	} 
                	else if(element.getDecompositionType().getValue() == DecompositionType.OR){
                		computeAggregation(linkSrc,"Or");
                	}
                	else if(element.getDecompositionType().getValue() == DecompositionType.XOR){
                		computeAggregation(linkSrc,"Xor");
                	}
            	}
            	else if (MetadataHelper.getMetaData(element, METADATA_ADDAGGR).compareTo("disable") == 0){
            		Metadata metaNumerical = MetadataHelper.getMetaDataObj(linkSrc, METADATA_AGGREVAL);
            		if (metaNumerical != null)
                		MetadataHelper.removeMetaData(linkSrc, METADATA_AGGREVAL);
            	}
            }
        }
    }
    
    /**
     * Compute Aggregate Contribution for link provided according to decomposition type
     */
    protected void computeAggregation(ElementLink link, String type){
    	Integer max = 0;
    	int needRangeAnd = 0;
    	String contriValue = "0";
    	int min = 0, count = 0;
    	Iterator it1 = link.getSrc().getLinksDest().iterator(); // Return the list of elementlink
        while (it1.hasNext()) {
        	
            ElementLink link1 = (ElementLink) it1.next();
            if (link1 instanceof Decomposition) {
            	for (Iterator j = link1.getSrc().getLinksSrc().iterator(); j.hasNext();){
            		
            		ElementLink link2 = (ElementLink) j.next();
            		
            		//If the decomposed part of an intentional element is contributing to the same destination
            		if((link2 instanceof Contribution) && (link2.getDest()==link.getDest())){
            			Contribution contrib1 = (Contribution) link2;
            			int minValue = 0, maxValue = 0;
            			Metadata metaAggregate = MetadataHelper.getMetaDataObj(link2, METADATA_AGGREVAL);
            			
            			//If the contribution is aggregate contribution
            			if (metaAggregate != null) {
            				String metaAddAggre = MetadataHelper.getMetaData(link2.getSrc(), METADATA_ADDAGGR);
        					String metaAggreValue = MetadataHelper.getMetaData(link2, METADATA_AGGREVAL);
        					if(metaAggreValue.contains("[")){
        						minValue = Integer.parseInt(metaAggreValue.substring(1, metaAggreValue.indexOf(",")));
        						maxValue = Integer.parseInt(metaAggreValue.substring(metaAggreValue.indexOf(",")+1, metaAggreValue.indexOf("]"))); 
        						needRangeAnd = 1;
        					}
        					else{
        						minValue = Integer.parseInt(metaAggreValue);
        						maxValue = Integer.parseInt(metaAggreValue);
        					}
        					
        					//If "Set Aggregate Contribution" value is "Show", consider both aggregate and individual contribution
        					if (metaAddAggre.compareTo("false") == 0){
        						Contribution contrib = (Contribution) link2;
        						int contri = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
        						minValue = minValue + contri;
        						maxValue = maxValue + contri;
        					}
        				}
            			if(count == 0){
            				if (metaAggregate != null) {
            					min = minValue;
            					max = maxValue;
            				}
            				else{
            					min = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib1);
            					
            					//max will be same as the min in case of normal contribution
            					max = min;
            				}
            				count = 1;
            			}
            			else{
            				if (metaAggregate == null) {
            					minValue = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib1);
            					
            					//In case of normal contribution, no max or min values
            					maxValue = minValue;
            				}	
            				
            				//Calculate minimum
            				if(type.compareTo("Or") == 0){
            					if (min >= 0 && min >= minValue)
            						min = minValue;
            					else if (min < 0 && minValue <0)
            						min += minValue;
            				}
            				else if(type.compareTo("Xor") == 0){
	            				if(min >= minValue)
	            					min = minValue;
	            			}
            				else{
            					min += minValue;
            				}
            				
            				//Calculate maximum
            				if(type.compareTo("Xor") == 0){
            					if(max <= maxValue)
    	        					max = maxValue;
    	        			}
            				else if(type.compareTo("Or") == 0){
            					if (max >= 0 && maxValue >= 0)
            						max += maxValue;
            					else if (max < 0 && maxValue >= max)
            						max = maxValue;
            				}
            				else{
            					max += maxValue;
            				}
            				          				
            			}
            		}
            	}
            	
            }
        }
        
        //Cap the contribution values to "-100" as minimum and "100" as maximum
        if (min <= -100){
			min = -100;
		}
        if (max <= -100){
			max = -100;
		}
        if (min >= 100){
			min = 100;
		}
		if (max >= 100){
			max = 100;
		}
		if(type.compareTo("Xor") == 0 || type.compareTo("Or") == 0 || (type.compareTo("And") == 0 && needRangeAnd == 1)){
			contriValue = "[" + min + "," + max + "]";
		}
		else{
			contriValue = max.toString();
		}
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(link, METADATA_AGGREVAL);
        if (metaNumerical != null) {
                // Run-time metadata already exists for this element
                metaNumerical.setValue(contriValue);
            } else {
                // Add new run-time metadata for this element
                URNspec urnSpec = link.getGrlspec().getUrnspec();
                MetadataHelper.addMetaData(urnSpec, link, METADATA_AGGREVAL, contriValue);
            }
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
