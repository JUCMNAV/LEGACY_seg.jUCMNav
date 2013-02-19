package seg.jUCMNav.strategies;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class BatchEvaluationUtil {
    /**
     * Metadata name used to store runtime trends for GRL strategy groups
     */
    public static final String METADATA_TREND = "_trend"; //$NON-NLS-1$
    
    public static HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> calculateAllEvaluations( final Collection<EvaluationStrategy> strategies, final GRLspec grlspec ) {
        int evalValue;
        HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable = new HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>>();

        for( EvaluationStrategy strategy : strategies ) {

            HashMap<GRLLinkableElement, Integer> strategyEvaluations = new HashMap<GRLLinkableElement, Integer>();
            EvaluationStrategyManager.getInstance(false).setStrategy(strategy);

            for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {
                IntentionalElement element = (IntentionalElement) iter.next();
                evalValue = EvaluationStrategyManager.getInstance(false).getEvaluation(element);
                strategyEvaluations.put( element, evalValue );
            }

            for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {
                Actor actor = (Actor) iter.next();
                evalValue = EvaluationStrategyManager.getInstance(false).getActorEvaluation(actor);
                strategyEvaluations.put( actor, evalValue );
            }

            evalTable.put(strategy, strategyEvaluations); // add map of this strategy's evaluations to the table
        }
        
        return evalTable;
    }
    
    /**
     * calculates a trend in the strategies
     * 
     * @param strategies
     *            hash tables which contains the evaluated strategies     
     * @param element
     *            current element - actor or intentional element
     * @param numStrat
     *            number of strategies in group
     */
    
    public static int calculateTrend(HashMap<Integer, EvaluationStrategy> strategies, GRLLinkableElement element, HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable, int prefTrend) {
        int trend = -2; 
            //no trend = -2
            //varying trend = -3
            //no change = 0
            //negative trend = -1
            //positive trend = 1
        int lastValue;
        int currentValue;
        int numStrat = strategies.size();
        
        EvaluationStrategy currentStrategy;
        
        if (numStrat >= prefTrend && prefTrend > 1){//else not enough data to calculate trend
  
            currentStrategy = strategies.get(numStrat - prefTrend); 
            
            lastValue = evalTable.get(currentStrategy).get(element);
            
            for (int i = (int)numStrat - prefTrend + 1; i < numStrat; i++){
                currentStrategy = strategies.get(i); 
                currentValue = evalTable.get(currentStrategy).get(element);
                
                if (trend == -2){ //no trend calculated yet (first element)
                    if (currentValue > lastValue){
                        trend = 1;
                    }else if(currentValue < lastValue){
                        trend = -1;
                    }else{
                        trend = 0;
                    }
                }
                else{
                    if (trend == 0 && currentValue > lastValue){ //neutral trend changed to positive
                        trend = 1;  
                    }else if(trend == 0 && currentValue < lastValue){//neutral trend changed to negative
                        trend = -1;
                    }else if(!((currentValue >= lastValue && trend == 1) || (currentValue <= lastValue && trend == -1) || (currentValue == lastValue && trend == 0))){ //trend changed
                        trend = -3;
                        break;
                    }
                }
                
                lastValue = currentValue;
            }
        }
        
        return trend;   
    }
}
