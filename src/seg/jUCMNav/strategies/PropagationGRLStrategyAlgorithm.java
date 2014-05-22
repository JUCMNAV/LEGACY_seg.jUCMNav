package seg.jUCMNav.strategies;

import fm.Feature;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.strategies.util.FeatureUtil;

/**
 * This class contains the common behavior of the qualitative and quantitative GRL evaluation algorithm.
 * 
 * @author gunterm
 * 
 */
public abstract class PropagationGRLStrategyAlgorithm {

    Vector<IntentionalElement> evalReady;
    HashMap<IntentionalElement, EvaluationCalculation> evaluationCalculation;
    HashMap evaluations;
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#init(java.util.Vector)
     */
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        evalReady = new Vector<IntentionalElement>();
        Vector<IntentionalElement> evalReadyFMLeafs = new Vector<IntentionalElement>();
        Vector<IntentionalElement> evalReadyUserDefined = new Vector<IntentionalElement>();
        evaluationCalculation = new HashMap<IntentionalElement, EvaluationCalculation>();
        this.evaluations = evaluations;

        // for the evaluation algorithm of Feature Models, the order of the evalReady elements is important! other algorithms do not care.
        // first leaf nodes, then leaf nodes in the Feature Model, and then nodes with user defined evaluation values
        Iterator it = strategy.getGrlspec().getIntElements().iterator();
		while (it.hasNext()) {
		    IntentionalElement element = (IntentionalElement) it.next();
		    if (element.getLinksDest().size() == 0) {
		        evalReady.add(element);
		    } else if (element instanceof Feature && FeatureUtil.isLeafFeature((Feature) element)) {
		    	evalReadyFMLeafs.add(element);
		    }
		    else if (((Evaluation) evaluations.get(element)).getStrategies() != null) {
		    	evalReadyUserDefined.add(element);
		    }
		    else {
		        EvaluationCalculation calculation = new EvaluationCalculation(element, element.getLinksDest().size());
		        evaluationCalculation.put(element, calculation);
		    }
		}
		// this ensures that all leaf nodes are handled before feature leaf nodes, and the user defined nodes are handled last
		evalReady.addAll(evalReadyFMLeafs);
		evalReady.addAll(evalReadyUserDefined);
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
                calc.incrementLinkCalc();
                if (calc.hasReachedTotalLink()) {
                    evaluationCalculation.remove(temp);
                    // add this new element into the first position of the vector, so that the original order of the evalReady elements is respected 
                    // (first all leaf nodes plus all nodes that can be reached from them, then feature model leaf nodes...) 
                    evalReady.add(0, calc.getElement());
                }
            }
        }
        return intElem;
    }

}
