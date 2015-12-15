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
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import java.util.Iterator;

import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.DependencyQualitativeLabelComparitor;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.IURNNode;
import urncore.Metadata;

/**
 * This class implements the qualitative GRL evaluation algorithm.
 * 
 * @author sghanava, gunterm
 * 
 */
public class QualitativeGRLStrategyAlgorithm extends PropagationGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {

    private static int D = QualitativeLabel.DENIED;
    private static int WD = QualitativeLabel.WEAKLY_DENIED;
    private static int WS = QualitativeLabel.WEAKLY_SATISFIED;
    private static int S = QualitativeLabel.SATISFIED;
    private static int C = QualitativeLabel.CONFLICT;
    private static int U = QualitativeLabel.UNKNOWN;
    private static int N = QualitativeLabel.NONE;
    
    private static String ma = ContributionType.MAKE_LITERAL.getName();
    private static String sp = ContributionType.SOME_POSITIVE_LITERAL.getName();
    private static String he = ContributionType.HELP_LITERAL.getName();
    private static String un = ContributionType.UNKNOWN_LITERAL.getName();
    private static String hu = ContributionType.HURT_LITERAL.getName();
    private static String sn = ContributionType.SOME_NEGATIVE_LITERAL.getName();
    private static String br = ContributionType.BREAK_LITERAL.getName();
    
    private static int[][] contribTable1 = {
            // M, H+, s+, u, s-, H-, B
            { D, WD, WD, N, WS, WS, S }, // D
            { WD, WD, WD, N, WS, WS, WS }, // WD
            { WS, WS, WS, N, WD, WD, WD }, // WS
            { S, WS, WS, N, WD, WD, D }, // S
            { U, U, U, U, U, U, U }, // C
            { U, U, U, U, U, U, U }, // U
            { N, N, N, N, N, N, N }, // N
    };

    int[][] contribTable2 = { { D, D, WD, C, C, U, D }, // D
            { D, WD, N, WS, C, U, WD }, // WD
            { WD, N, WS, S, C, U, WS }, // WS
            { C, WS, S, S, C, U, S }, // S
            { C, C, C, C, C, C, C }, // C
            { U, U, U, U, C, U, U }, // U
            { D, WD, WS, S, C, U, N }, // N
    };

    int[] contribMap = { IGRLStrategyAlgorithm.DENIED, IGRLStrategyAlgorithm.WDENIED, IGRLStrategyAlgorithm.WSATISFICED, IGRLStrategyAlgorithm.SATISFICED,
            IGRLStrategyAlgorithm.CONFLICT, IGRLStrategyAlgorithm.UNDECIDED, IGRLStrategyAlgorithm.NONE, };

    int[][] importanceMap = { { D, WD, WS, S, C, U, N }, // high
            { WD, WD, WS, WS, C, U, N }, // med
            { WD, N, N, WS, C, U, N }, // low
            { N, N, N, N, N, N, N }, // none
    };

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_QUALITATIVE;
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
        
        //get Aggregate Contribution for element
        getAggregation(element);
        
        int result = -1;
        int tempResult = 0;

        boolean hasDecomposition = false;
        int decomSums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            decomSums[i] = 0;
        }

        QualitativeLabel depMinLabel = null;
        boolean foundSmallerDependency = false;
        DependencyQualitativeLabelComparitor labelComp = new DependencyQualitativeLabelComparitor();
        int numContributions = 0;
        int sums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sums[i] = 0;
        }

        Iterator it = element.getLinksDest().iterator(); // Return the list of elementlink
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();

            // handle decomposition
            if (link instanceof Decomposition) {
                if (!hasDecomposition)
                    hasDecomposition = true;
                QualitativeLabel decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();
                int qval = decompositionValue.getValue();
                String value = MetadataHelper.getMetaData(link.getSrc(), "ST_Legal"); //$NON-NLS-1$
                if (element.getDecompositionType().getValue() == DecompositionType.AND && "No".equals(value)) //$NON-NLS-1$
                    continue;
                decomSums[qval]++;

            } else if (link instanceof Dependency) {
                if (depMinLabel == null)
                    depMinLabel = ((Evaluation) evaluations.get(element)).getQualitativeEvaluation();
                QualitativeLabel depValue = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();

                if (labelComp.compare(depValue, depMinLabel) > 0) {
                    depMinLabel = depValue;
                    foundSmallerDependency = true;
                }
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;
                int contValue = 0;
                String addAggrValue = "false";
                Metadata metaNumerical = MetadataHelper.getMetaDataObj(link, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
                if(MetadataHelper.getMetaDataObj(link.getSrc(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR) != null){
                	addAggrValue = MetadataHelper.getMetaData(link.getSrc(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
                }
                
                //If "Aggregate Contribution" is enabled and has a value, then 
                //individual contribution of the link is ignored for evaluation
                if((addAggrValue.compareTo("true") == 0) && metaNumerical != null)
                	continue;
                else
                	contValue = EvaluationStrategyManager.getInstance().getActiveContribution(contrib).getValue();
                QualitativeLabel srcNode = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();
                int qualValue = srcNode.getValue();

                int ci = contribTable1[qualValue][contValue];
                sums[ci]++;
                numContributions++;
            }
        }

        if (hasDecomposition) {
            int dns = decomSums[S];
            int dnws = decomSums[WS];
            int dnn = decomSums[N];
            int dnwd = decomSums[WD];
            int dnd = decomSums[D];
            int dnc = decomSums[C];
            int dnu = decomSums[U];

            if (element.getDecompositionType().getValue() == DecompositionType.AND) {
                if (dnd > 0) {
                    result = D;
                } else if ((dnc > 0) || (dnu > 0)) {
                    result = U;
                } else if (dnwd > 0) {
                    result = WD;
                } else if (dnn > 0) {
                    result = N;
                } else if (dnws > 0) {
                    result = WS;
                } else if (dns > 0) {
                    result = S;
                } else {
                    result = N;
                }
            } else if (element.getDecompositionType().getValue() == DecompositionType.OR || element.getDecompositionType().getValue() == DecompositionType.XOR) {
                if (dns > 0) {
                    result = S;
                } else if ((dnc > 0) || (dnu > 0)) {
                    result = U;
                } else if (dnws > 0) {
                    result = WS;
                } else if (dnn > 0) {
                    result = N;
                } else if (dnwd > 0) {
                    result = WD;
                } else if (dnd > 0) {
                    result = D;
                }
            }
        }

        if (numContributions > 0 && result == -1) {
            result = getQualitativeContribution(sums, numContributions);
        } else if (numContributions > 0 && result != -1) {
            tempResult = getQualitativeContribution(sums, numContributions);
            result = contribTable2[result][tempResult];
        }

        if (depMinLabel != null) {
            QualitativeLabel currLabel = null;

            if (result != -1)
                currLabel = QualitativeLabel.get(result);
            else
                currLabel = QualitativeLabel.NONE_LITERAL;

            if (labelComp.compare(depMinLabel, currLabel) > 0)
                result = depMinLabel.getValue();
        }

        return (result != -1 ? contribMap[result] : 0);
    }

    private int getQualitativeContribution(int[] sums, int numRead) {
        int toRet = -1;
        if (numRead > 1) {
            int ns = sums[S];
            int nws = sums[WS];
            int nn = sums[N];
            int nwd = sums[WD];
            int nd = sums[D];
            int nc = sums[C];
            int nu = sums[U];

            int w1 = getW1(nws, nwd);
            int w2 = getW2(ns, nd);
            int w3 = getW3(nc, nu);

            int ei = (w3 == -1 ? contribTable2[w1][w2] : w3);
            toRet = ei;
        } else {
            for (int i = 0; i < sums.length; i++) {
                if (sums[i] > 0)
                    toRet = i;
            }
        }
        return toRet;
    }

    /**
     * Gets the evaluation value of the first sets of evaluations.
     * 
     */
    private int getW1(int nws, int nwd) {
        /**
         * w1 = ws, if nws > nwd = wd, if nwd > nws = n, otherwise
         */
        if (nws > nwd)
            return WS;
        if (nwd > nws)
            return WD;
        return N;
    }

    /**
     * Get the evaluation value of the second sets of evaluations.
     * 
     */
    private int getW2(int ns, int nd) {
        /**
         * w2 = c, if ns >0 && nd >0 = s, if ns >0 && nd=0 = d, if nd >0 && ns=0 = n, if ns =0 && nd=0
         */
        if (ns > 0 && nd > 0)
            return C;
        if (ns > 0 && nd == 0)
            return S;
        if (nd > 0 && ns == 0)
            return D;
        return N;
    }

    /**
     * Get the evaluation value of W3.
     * 
     */
    private int getW3(int nc, int nu) {
        /**
         * w3 = u, if nc > 0
         */
        if (nc > 0 || nu > 0)
            return U;
        return -1; // never considered
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
        double satisficed = 0;
        double denied = 0;

        int total = 0;

        int sums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sums[i] = 0;
        }

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
                    String value = MetadataHelper.getMetaData(element, "ST_Legal"); //$NON-NLS-1$
                    if ("No".equals(value)) { //$NON-NLS-1$
                        continue;
                    }
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);

                    ImportanceType importance = element.getImportance();

                    QualitativeLabel ql = EvaluationStrategyManager.getQualitativeEvaluationForQuantitativeValue(element.getGrlspec().getUrnspec(), evaluation);
                    int ci = importanceMap[importance.getValue()][ql.getValue()];
                    sums[ci]++;
                    total++;
                }
            }
        }

        int result = getQualitativeContribution(sums, total);
        return (result != -1 ? contribMap[result] : 0);

    }

    @Override
    public boolean isConstraintSolverAlgorithm() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Check if Aggregate Contribution needed for element and compute its value
     */
    public void getAggregation(IntentionalElement element){
    	
    	Iterator itSrc = element.getLinksSrc().iterator(); // Return the list of elementlink
        while (itSrc.hasNext()) {
            ElementLink linkSrc = (ElementLink) itSrc.next();
            if (linkSrc instanceof Contribution) {
            	
            	boolean isDecomp = false;
            	
            	//Add Metadata to turn on/off aggregation for intentional elements 
                Metadata metaAddAggr = MetadataHelper.getMetaDataObj(element, QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
            	
                //Set the metadata to default "false" if not added before
                if (metaAddAggr == null){
            		// Add new run-time metadata for this element
                    URNspec urnSpec = element.getGrlspec().getUrnspec();
            		MetadataHelper.addMetaData(urnSpec, element, QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR, "disable");
            	}
                for (Object link1: element.getLinksDest()) {
                    if (link1 instanceof Decomposition){
                    	isDecomp = true;
                    	break;
                    }
                }
            	
            	//If the metadata for aggregation is "true" and link isn't mandatory or optional
            	if(!(linkSrc instanceof MandatoryFMLink) && !(linkSrc instanceof OptionalFMLink) && 
            		(MetadataHelper.getMetaData(element, QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR).compareTo("disable") != 0) && 
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
            	
            	//If aggregation is disabled for the element, remove metadata from the link
            	else if (MetadataHelper.getMetaData(element, QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR).compareTo("disable") == 0){
            		Metadata metaNumerical = MetadataHelper.getMetaDataObj(linkSrc, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
            		if (metaNumerical != null)
                		MetadataHelper.removeMetaData(linkSrc, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
            	}
            }
        }
    }
    
    /**
     * Compute Aggregate Contribution for link provided according to decomposition type
     */
    protected void computeAggregation(ElementLink link, String type){
    	String contriValue = un;
    	int needRangeAnd = 0;
    	
    	//Variables to store the count of contribution values of decomposed parts in following order:
    	//{make, break, sp, sn, help, hurt, unknown}
    	int[] minCount = {0, 0, 0, 0, 0, 0, 0}; 
    	int[] maxCount = {0, 0, 0, 0, 0, 0, 0};     	
    	Iterator it1 = link.getSrc().getLinksDest().iterator(); // Return the list of elementlink
        while (it1.hasNext()) {
        	
            ElementLink link1 = (ElementLink) it1.next();
            if (link1 instanceof Decomposition) {
            	for (Iterator j = link1.getSrc().getLinksSrc().iterator(); j.hasNext();){
            		
            		ElementLink link2 = (ElementLink) j.next();
            		
            		//If the decomposed part of an intentional element is contributing to the same destination
            		if((link2 instanceof Contribution) && (link2.getDest()==link.getDest())){
            			Contribution contrib1 = (Contribution) link2;
            			int minValue = 3, maxValue = 3;
            			Metadata metaAggregate = MetadataHelper.getMetaDataObj(link2, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
            			
            			//If the contribution is aggregate contribution
            			if (metaAggregate != null) {
        					String metaAggreValue = MetadataHelper.getMetaData(link2, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
        					if(metaAggreValue.contains("[")){
        						minValue = ContributionType.getByName(metaAggreValue.substring(1, metaAggreValue.indexOf(","))).getValue();
        						maxValue = ContributionType.getByName(metaAggreValue.
        													substring(metaAggreValue.indexOf(",")+1, metaAggreValue.indexOf("]"))).getValue();
        						
        						//Variable to indicate "And" also needs range
        						needRangeAnd = 1;        						
        					}
        					else{
        						minValue = ContributionType.getByName(metaAggreValue).getValue();
        						maxValue = ContributionType.getByName(metaAggreValue).getValue();
        					}
        					String addAggr = MetadataHelper.getMetaData(link2.getSrc(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
    						if(addAggr.compareTo("false") == 0){
    							minCount = updateCount(EvaluationStrategyManager.getInstance().getActiveContribution(contrib1).getValue(), minCount);
    							maxCount = updateCount(EvaluationStrategyManager.getInstance().getActiveContribution(contrib1).getValue(), maxCount);
    						}
        				}
            			else{
            				minValue = EvaluationStrategyManager.getInstance().getActiveContribution(contrib1).getValue();
        					
        					//In case of normal contribution, no max or min values
        					maxValue = minValue;
            			}
            			
            			minCount = updateCount(minValue, minCount);
            			maxCount = updateCount(maxValue, maxCount);
            			
            		}
            	}
            }           	
        }
        if ((type.compareTo("Or") == 0) || (type.compareTo("Xor") == 0))
        	contriValue = getOrXorAggregate(minCount, maxCount);
        else if (type.compareTo("And") == 0){
        	if (needRangeAnd == 0)
        		contriValue = getAndAggregate(minCount);
        	else if (needRangeAnd == 1)
        		contriValue = "[" + getAndAggregate(minCount) + "," + getAndAggregate(maxCount) + "]";
        }
        
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(link, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL);
        if (metaNumerical != null) {
                // Run-time metadata already exists for this element
                metaNumerical.setValue(contriValue);
            } else {
                // Add new run-time metadata for this element
                URNspec urnSpec = link.getGrlspec().getUrnspec();
                MetadataHelper.addMetaData(urnSpec, link, QuantitativeGRLStrategyAlgorithm.METADATA_AGGREVAL, contriValue);
            }
    }
    
    /**
     * Count contribution values as per their types
     */
    protected int[] updateCount(int val, int[] count){
    	
    	//Increment "Make" count
		if (val == 0)
			count[0] += 1;
		//Increment "Break" count
		else if (val == 6)
			count[1] += 1;
		//Increment "Some +ve" count
		else if (val == 2)
			count[2] += 1;
		//Increment "Some -ve" count
		else if (val == 4)
			count[3] += 1;
		//Increment "Help" count
		else if (val == 1)
			count[4] += 1;
		//Increment "Hurt" count
		else if (val == 5)
			count[5] += 1;
		//Increment "Unknown" count
		else if (val == 3)
			count[6] += 1;
    	return count;
    }
    
    /**
     * Calculate Aggregate Contribution for decomposition types "Or" and "Xor"
     */
    protected String getOrXorAggregate(int[] minCount, int[] maxCount){
    	String contriValue = un;
    	
    	//Initialize min and max to unknown
    	int min = 3, max = 3;
    	if (maxCount[0] > 0)
    		max = 0;
    	else if (maxCount[2] > 0)
    		max = 2;
    	else if (maxCount[4] > 0)
    		max = 1;
    	else if (maxCount[6] > 0)
    		max = 3;
    	else if (maxCount[5] > 0)
    		max = 5;
    	else if (maxCount[3] > 0)
    		max = 4;
    	else if (maxCount[1] > 0)
    		max = 6;
    	
    	if (minCount[1] > 0)
    		min = 6;
    	else if (minCount[3] > 0)
    		min = 4;
    	else if (minCount[5] > 0)
    		min = 5;
    	else if (minCount[6] > 0)
    		min = 3;
    	else if (minCount[4] > 0)
    		min = 1;   	
    	else if (minCount[2] > 0)
    		min = 2;
    	else if (minCount[0] > 0)
    		min = 0;
    	
    	contriValue = "[" + ContributionType.get(min).getName() + "," + ContributionType.get(max).getName() + "]";
    	return contriValue;
    }
    
    /**
     * Calculate Aggregate Contribution for decomposition type "And"
     */
    protected String getAndAggregate(int[] count){
    	String contriValue = un;
    	
    	//Get dominant in each type and initialize the weaker one to 0
    	for (int i = 0; i <= 4; i = i + 2){
	    	if (count[i] > count[i + 1]){
	    		count[i] = count[i] - count[i + 1];
	    		count[i+1] = 0;
	    	}
	    	else if (count[i] < count[i + 1]){
	    		count[i + 1] = count[i + 1] - count[i];
	    		count[i] = 0;
	    	}
	    	else{
	    		count[i] = 0;
	    		count[i + 1] = 0;
	    	}
    	}
    	
    	if(count[0] > 0){
    		int temp = count[0];
    		count[0] -= count[3];
    		count[4] += temp; 
    		count[3] -= temp;
    		if (count[3] < 0)
    			count[4] += count[3];
    		if (count[0] > count[5])
    			return ma;
    		else if (count[0] > 0){
    			count[5] -= count[0];
    			count[4] += count[0];
    		}
    		if (count[3] <= 0){
    			if (count[2] > count[5])
    				return sp;
    			else{
    				count[5] -= count[2];
    				count[4] += count[2];
    			}
    		}
    		else{
    			if (count[3] > count[4])
    				return sn;
    			else{
    				count[4] -= count[3];
    				count[5] += count[3];
    			}
    		}
    	}
    	else if(count[1] > 0){
    		int temp = count[1];
    		count[1] -= count[2];
    		count[5] += temp; 
    		count[2] -= temp;
    		if (count[2] < 0)
    			count[5] += count[2];
    		if (count[1] > count[4])
    			return br;
    		else if (count[1] > 0){
    			count[4] -= count[1];
    			count[5] += count[1];
    		}
    		if (count[2] <= 0){
    			if (count[3] > count[4])
    				return sn;
    			else{
    				count[4] -= count[3];
    				count[5] += count[3];
    			}
    		}
    		else{
    			if (count[2] > count[5])
    				return sp;
    			else{
    				count[5] -= count[2];
    				count[4] += count[2];
    			}
    		}
    	}
    	else if(count[2] > 0){
    		if (count[2] > count[5])
				return sp;
			else{
				count[5] -= count[2];
				count[4] += count[2];
			}
    		
    	}
    	else if(count[3] > 0){
    		if (count[3] > count[4])
				return sn;
			else{
				count[4] -= count[3];
				count[5] += count[3];
			}
    	}
    	if (count[4] > count[5])
			return he;
		else if (count[4] == count[5])
			return un;
		else
			return hu; 
    }
    
}
