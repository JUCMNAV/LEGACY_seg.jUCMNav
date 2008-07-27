package seg.jUCMNav.strategies;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.Priority;
import grl.QualitativeLabel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.DependencyQualitativeLabelComparitor;
import urncore.IURNNode;

/**
 * This class implement the default GRL evaluation algorithm.
 * 
 * @author sghanava
 *
 */
public class QualitativeGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {
    
	private static int D = QualitativeLabel.DENIED;
	private static int WD = QualitativeLabel.WEAKLY_DENIED;
	private static int WS = QualitativeLabel.WEAKLY_SATISFIED;
	private static int S = QualitativeLabel.SATISFIED;
	private static int C = QualitativeLabel.CONFLICT;
	private static int U = QualitativeLabel.UNKNOWN;
	private static int N = QualitativeLabel.NONE;
	
	
	private static int[][] contribTable1 = {
		// M,  H+, s+, u, s-, H-, B
	      {D,  WD, WD, N, WS, WS, S},  //D
	      {WD, WD, WD, N, WS, WS, WS}, //WD
	      {WS, WS, WS, N, WD, WD, WD}, //WS
	      {S,  WS, WS, N, WD, WD, D},  //S
	      {C,  C,  C,  C, C,  C,  C},  //C
	      {U,  U,  U,  U, U,  U,  U},  //U
	      {N,  N,  N,  N, N,  N,  N},  //N
		    };

	 int[][] contribTable2 = {
		      {D,  D,  WD, C,  C,  U, D}, //D
		      {D,  WD, N,  WS, C,  U, WD}, //WD
		      {WD, N,  WS, S,  C,  U, WS}, //WS
		      {C,  WS, S,  S,  C,  U, S}, //S
		      {C,  C,  C,  C,  C,  C, C}, //C
		      {U,  U,  U,  U,  C,  U, U}, //U
		      {D,  WD, WS, S,  C,  U, N}, //N
		    };
	
	 int[] contribMap = { 
			 IGRLStrategyAlgorithm.DENIED, 
			 IGRLStrategyAlgorithm.WDENIED, 
			 IGRLStrategyAlgorithm.WSATISFICED, 
			 IGRLStrategyAlgorithm.SATISFICED, 
			 IGRLStrategyAlgorithm.CONFLICT, 
			 IGRLStrategyAlgorithm.UNDECIDED,
			 IGRLStrategyAlgorithm.NONE,
			};
		
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
    public int getEvaluationType() { return IGRLStrategyAlgorithm.EVAL_QUALITATIVE; }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation)evaluations.get(element);
        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null)){
            return eval.getEvaluation();
        }
        
        int result = 0;              
                
        boolean hasDecomposition = false;
        int decomSums[] = new int[7];
        for(int i = 0; i < 7; i++) { decomSums[i] = 0; }
        
        QualitativeLabel depMinLabel = null;
        DependencyQualitativeLabelComparitor labelComp = new DependencyQualitativeLabelComparitor();
        int numContributions = 0;
        int sums[] = new int[7];
        for(int i = 0; i < 7; i++) { sums[i] = 0; }
        
        Iterator it = element.getLinksDest().iterator(); //Return the list of elementlink
        while (it.hasNext()){
            ElementLink link = (ElementLink)it.next();
            
            //handle decomposition
            if (link instanceof Decomposition){
            	if(!hasDecomposition) hasDecomposition=true;
            	QualitativeLabel decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getQualitativeEvaluation();
            	int qval = decompositionValue.getValue();
            	decomSums[qval]++;
            	
            } else if (link instanceof Dependency){
            	
//            	if(depMinLabel == null)
//            		depMinLabel = ((Evaluation)evaluations.get(element)).getQualitativeEvaluation();
            	QualitativeLabel depValue = ((Evaluation)evaluations.get(link.getSrc())).getQualitativeEvaluation();
            	if(depMinLabel == null) depMinLabel = depValue;
            	else if(labelComp.compare(depValue, depMinLabel) > 0)
        			depMinLabel = depValue;
            } else if (link instanceof Contribution){
                Contribution contrib = (Contribution)link;
                int contValue = contrib.getContribution().getValue();
                QualitativeLabel srcNode = ((Evaluation)evaluations.get(link.getSrc())).getQualitativeEvaluation();
                int qualValue = srcNode.getValue();
                

                int ci = contribTable1[qualValue][contValue];
                sums[ci]++;
                numContributions++;
            }
        }
        
        
        if (hasDecomposition){        	       	
        	int dns = decomSums[S];
        	int dnws = decomSums[WS];
        	int dnn = decomSums[N];
        	int dnwd = decomSums[WD];
        	int dnd = decomSums[D];
        	int dnc = decomSums[C];
        	int dnu = decomSums[U];
        	
        	if (element.getDecompositionType().getValue() == DecompositionType.AND){
        		if (dnd > 0){
        			return contribMap[D];         
        		} else if ((dnc > 0) || (dnu > 0) ){
        			return contribMap[U];
        		} else if (dnwd > 0){
        			return contribMap[WD];
        		} else if (dnn > 0){
        			return contribMap[N];
        		} else if (dnws > 0){
        			return contribMap[WS];
        		} else if (dns > 0){
        			return contribMap[S];
        		}
        	} else if (element.getDecompositionType().getValue() == DecompositionType.OR){
        		if (dns > 0){
        			return contribMap[S];         
        		} else if ((dnc > 0) || (dnu > 0) ){
        			return contribMap[U];
        		} else if (dnws > 0){
        			return contribMap[WS];
        		} else if (dnn > 0){
        			return contribMap[N];
        		} else if (dnwd > 0){
        			return contribMap[WD];
        		} else if (dnd > 0){
        			return contribMap[D];
        		}
        	}
        }
        
        if (numContributions > 0){
        	
        	if(numContributions > 1) {
	        	int ns = sums[S];
	        	int nws = sums[WS];
	        	int nn = sums[N];
	        	int nwd = sums[WD];
	        	int nd = sums[D];
	        	int nc = sums[C];
	        	int nu = sums[U];
	        	
	        	int w1 = getW1(nws,nwd);
	        	int w2 = getW2(ns, nd);
	        	int w3 = getW3(nc, nu);
	        	
	        	int ei = ( w3 == -1 ? contribTable2[w1][w2] : w3);
	        	return contribMap[ei];
        	} else {
        		for(int i = 0; i < sums.length; i++) {
        			if(sums[i] > 0){
        				result= contribMap[i];
        				return result;
        			}
        		}
        	}
        }
        
      
        	
        	
        if (depMinLabel != null){
            result = contribMap[depMinLabel.getValue()];
        }
        return result;
    }
    
    /**
     * Gets the evaluation value of the first sets of evaluations. 
     * @author Sepideh Ghanavati
     *
     */
    private int getW1(int nws, int nwd) {
/**
 * w1 
 	= ws, if nws > nwd
	= wd, if nwd > nws
	= n, otherwise
 */   	
    	if(nws > nwd) return WS;
    	if(nwd > nws) return WD;
    	return N;
    }
    
    /**
     * Get the evaluation value of the second sets of evaluations. 
     * @author Sepideh Ghanavati
     *
     */
    private int getW2(int ns, int nd) {
/**
 * w2
  	= c, if ns >0 && nd >0
	= s, if ns >0 && nd=0
	= d, if nd >0 && ns=0
	= n, if ns =0 && nd=0
 */
    	if(ns > 0 && nd > 0) return C;
    	if(ns > 0 && nd == 0) return S;
    	if(nd > 0 && ns == 0) return D;
    	return N;
    }
    
    
    /**
     * Get the evaluation value of W3. 
     * @author Sepideh Ghanavati
     *
     */
    private int getW3(int nc, int nu) {
/**
 * w3	= u, if nc > 0
 */
    	if(nc > 0 || nu > 0) return U;
    	return -1; //never considered
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor){
        double satisficed = 0;
        double denied = 0;

        int total = 0;
        
        Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()){
            //Parse through the node bind to this actor
            ActorRef ref = (ActorRef)iter.next();
            Iterator iterNode = ref.getNodes().iterator();
            while(iterNode.hasNext()){
                IURNNode node = (IURNNode)iterNode.next();
                if (node instanceof IntentionalElementRef){
                    IntentionalElementRef element = (IntentionalElementRef)node;
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element.getDef());
                    switch (element.getCriticality().getValue()){
                    case Criticality.HIGH:
                        if (evaluation > 0){
                            satisficed = satisficed + (evaluation*1.5);
                        } else {
                            denied = denied + (evaluation*1.5);
                        }
                        total++;
                        break;
                    case Criticality.MEDIUM:
                        if (evaluation > 0){
                            satisficed = satisficed + evaluation;
                        } else {
                            denied = denied + evaluation;
                        }
                        total++;
                        break;
                    case Criticality.LOW:
                        if (evaluation > 0){
                            satisficed = satisficed + (evaluation*0.5);
                        } else {
                            denied = denied + (evaluation*0.5);
                        }
                        total++;
                        break;
                    default:
                        break;
                    }
                    
                    switch (element.getPriority().getValue()){
                    case Priority.HIGH:
                        if (evaluation > 0){
                            satisficed = satisficed + (evaluation*1.5);
                        } else {
                            denied = denied + (evaluation*1.5);
                        }
                        total++;
                        break;
                    case Priority.MEDIUM:
                        if (evaluation > 0){
                            satisficed = satisficed + evaluation;
                        } else {
                            denied = denied + evaluation;
                        }
                        total++;
                        break;
                    case Priority.LOW:
                        if (evaluation > 0){
                            satisficed = satisficed + (evaluation*0.5);
                        } else {
                            denied = denied + (evaluation*0.5);
                        }
                        total++;
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        if (total > 0){
            total = new Double((satisficed +denied)/total).intValue();
        }
        if (Math.abs(total) > 100){
            total = (Math.abs(total)/total)*100;
        }
        return total;
    }

}
