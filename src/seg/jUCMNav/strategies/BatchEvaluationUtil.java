package seg.jUCMNav.strategies;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;

public class BatchEvaluationUtil {
    
    public static final int TREND_POSITIVE = 1;
    public static final int TREND_EQUALS = 0;
    public static final int TREND_NEGATIVE = -1;
    public static final int TREND_NOTREND = -2;
    public static final int TREND_VARYING = -3;
    public static final int TREND_CANTCALCULATE = -4;
    
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
            //can't calculate = -4
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
  
            currentStrategy = strategies.get(numStrat - prefTrend+1); 
            
            lastValue = evalTable.get(currentStrategy).get(element);
            
            for (int i = (int)numStrat - prefTrend + 2; i < numStrat; i++){
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
        else
            trend = -4;
        
        return trend;   
    }
    
    public static Image getIcon(int _trend) {
        if(_trend == BatchEvaluationUtil.TREND_NOTREND || _trend == BatchEvaluationUtil.TREND_VARYING) // No trend or varying trend
            return (JUCMNavPlugin.getImage("icons/trend-notrend.gif")); //$NON-NLS-1$
        else if(_trend == BatchEvaluationUtil.TREND_CANTCALCULATE) // Can't calculatate trend
            return (JUCMNavPlugin.getImage("icons/trend-cant.gif")); //$NON-NLS-1$
        else if(_trend == BatchEvaluationUtil.TREND_NEGATIVE) // Negative trend
            return (JUCMNavPlugin.getImage("icons/trend-down.gif")); //$NON-NLS-1$
        else if(_trend == BatchEvaluationUtil.TREND_EQUALS) // Equal trend
            return (JUCMNavPlugin.getImage("icons/trend-equals.gif")); //$NON-NLS-1$
        else if(_trend == BatchEvaluationUtil.TREND_POSITIVE) // Positive trend
            return (JUCMNavPlugin.getImage("icons/trend-up.gif")); //$NON-NLS-1$
        
        return null;
    }
}
