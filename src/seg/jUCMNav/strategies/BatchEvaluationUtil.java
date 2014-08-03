package seg.jUCMNav.strategies;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIEvalValueSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

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

	public static HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>> calculateAllEvaluationsKPI( final Collection<EvaluationStrategy> strategies, final GRLspec grlspec) {

		int evalValue;

		HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>> indicatorTable = new HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>>() ;

		for( EvaluationStrategy strategy : strategies ) {
			HashMap<GRLLinkableElement, Integer> strategyEvaluations = new HashMap<GRLLinkableElement, Integer>();
			EvaluationStrategyManager.getInstance(false).setStrategy(strategy);
			HashMap<Indicator, HashMap<String, String>> indicatorEvaluations = new HashMap<Indicator, HashMap<String, String>>();


			boolean tempIndicators = false;

			for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {

				IntentionalElement element = (IntentionalElement) iter.next();
				evalValue = EvaluationStrategyManager.getInstance(false).getEvaluation(element);
				strategyEvaluations.put( element, evalValue );

				if(element.getType().getName().compareTo("Indicator") == 0){

					HashMap<String, String> currentEvalKPI = new HashMap<String, String>();
					KPIEvalValueSet currentKpiEvalSet = EvaluationStrategyManager.getInstance(false).getActiveKPIEvalValueSet(element);


					currentEvalKPI.put("Threshold",String.valueOf(currentKpiEvalSet.getThresholdValue()));
					currentEvalKPI.put("Worst",String.valueOf(currentKpiEvalSet.getWorstValue()));
					currentEvalKPI.put("Target",String.valueOf(currentKpiEvalSet.getTargetValue()));
					currentEvalKPI.put("Unit",currentKpiEvalSet.getUnit());
					currentEvalKPI.put("EvaluationValue", String.valueOf(round(currentKpiEvalSet.getEvaluationValue(), 2)));
					currentEvalKPI.put("QualitativeEval", currentKpiEvalSet.getQualitativeEvaluationValue());
					currentEvalKPI.put("Evaluation", String.valueOf(evalValue));

					if( currentKpiEvalSet.getKpiConv() != null){
						currentEvalKPI.put("KpiConv", currentKpiEvalSet.getKpiConv().getName());
					}

					indicatorEvaluations.put((Indicator)element, currentEvalKPI);			

					tempIndicators = true;
				}

			}

			if (tempIndicators == true){
				indicatorTable.put(strategy, indicatorEvaluations);
			}  
		}

		return indicatorTable;
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

	public static int calculateTrend(HashMap<Integer, EvaluationStrategy> unsortedstrategies, GRLLinkableElement element, HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable, int prefTrend) {
		// IMPORTANT! Most code duplicated from the same method in ReportStrategies.java... 
		// Change in both places!

		int trend = BatchEvaluationUtil.TREND_NOTREND; 
		//can't calculate = -4
		//no trend = -2
		//varying trend = -3
		//no change = 0
		//negative trend = -1
		//positive trend = 1
		int lastValue;
		int currentValue;
		int numStrat = unsortedstrategies.size(); // not in duplicated method

		HashMap<Integer, EvaluationStrategy> strategies = sortStrategies(unsortedstrategies, 0);
		int firstHashmapIndex = 0;
				
		EvaluationStrategy currentStrategy;

		if (numStrat >= prefTrend && prefTrend > 1){//else not enough data to calculate trend

			currentStrategy = strategies.get (numStrat - prefTrend); 

			lastValue = evalTable.get(currentStrategy).get(element);

			for (int i = numStrat - prefTrend + 1 + firstHashmapIndex; i < numStrat; i++){
				currentStrategy = strategies.get(i); 
				currentValue = evalTable.get(currentStrategy).get(element);

				if (trend == BatchEvaluationUtil.TREND_NOTREND){ //no trend calculated yet (second element compared to first)
					if (currentValue > lastValue){
						trend = BatchEvaluationUtil.TREND_POSITIVE;
					}else if(currentValue < lastValue){
						trend = BatchEvaluationUtil.TREND_NEGATIVE;
					}else{
						trend = BatchEvaluationUtil.TREND_EQUALS;
					}
				}
				else{
					if (trend == BatchEvaluationUtil.TREND_EQUALS && currentValue > lastValue){ //neutral trend changed to positive
						trend = BatchEvaluationUtil.TREND_POSITIVE;  
					}else if(trend == BatchEvaluationUtil.TREND_EQUALS && currentValue < lastValue){//neutral trend changed to negative
						trend = BatchEvaluationUtil.TREND_NEGATIVE;
					}else if(!((currentValue >= lastValue && trend == BatchEvaluationUtil.TREND_POSITIVE) || (currentValue <= lastValue && trend == BatchEvaluationUtil.TREND_NEGATIVE) || (currentValue == lastValue && trend == BatchEvaluationUtil.TREND_EQUALS))){ //trend changed
						trend = BatchEvaluationUtil.TREND_VARYING;
						break;
					}
				}

				lastValue = currentValue;
			}
		}
		else
			trend = BatchEvaluationUtil.TREND_CANTCALCULATE;

		return trend;   
	}

	public static Image getIcon(int _trend) {
		if(_trend == BatchEvaluationUtil.TREND_NOTREND || _trend == BatchEvaluationUtil.TREND_VARYING) // No trend or varying trend
			return (JUCMNavPlugin.getImage("icons/trend-notrend.gif")); //$NON-NLS-1$
		else if(_trend == BatchEvaluationUtil.TREND_CANTCALCULATE) // Can't calculate trend
			return (JUCMNavPlugin.getImage("icons/trend-cant.gif")); //$NON-NLS-1$
		else if(_trend == BatchEvaluationUtil.TREND_NEGATIVE) // Negative trend
			return (JUCMNavPlugin.getImage("icons/trend-down.gif")); //$NON-NLS-1$
		else if(_trend == BatchEvaluationUtil.TREND_EQUALS) // Equal trend
			return (JUCMNavPlugin.getImage("icons/trend-equals.gif")); //$NON-NLS-1$
		else if(_trend == BatchEvaluationUtil.TREND_POSITIVE) // Positive trend
			return (JUCMNavPlugin.getImage("icons/trend-up.gif")); //$NON-NLS-1$

		return null;
	}

	/*
	 * Rounds a Double to a number of decimal places
	 * 
	 * param value
	 * 	value to round off
	 * param places
	 * 	number of decimal places needed
	 * return 
	 * 	the rounded number
	 */
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static HashMap<Integer, EvaluationStrategy> sortStrategies(HashMap<Integer, EvaluationStrategy> unsortedstrategies, int from) { 
		// To fix annoying behaviour... Sometimes the strategies HashMap has indices from
		// 0 to n-1, and other times from 1 to n... Hack to fix.
		int firstHashmapIndex = 1;
		if (unsortedstrategies.keySet().contains(0))
			firstHashmapIndex = 0;

		// Sort strategies by name
		int size = unsortedstrategies.size();
		List<EvaluationStrategy> strategies = new ArrayList<EvaluationStrategy>();
		for(int i=firstHashmapIndex; i<size+firstHashmapIndex; i++)
		{
			strategies.add(unsortedstrategies.get(i));
		}

		// Sort strategies by name in the List
		Collections.sort(strategies, new Comparator(){
			public int compare(Object arg0, Object arg1) {
				EvaluationStrategy strat0 = (EvaluationStrategy)arg0;
				EvaluationStrategy strat1 = (EvaluationStrategy)arg1;

				return strat0.getName().compareToIgnoreCase(strat1.getName());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		// starts at 0 or 1 (from)
		HashMap <Integer, EvaluationStrategy> sortedHashMap = new LinkedHashMap<Integer, EvaluationStrategy>();
		for (int i=from; i<size+from; i++) {
			sortedHashMap.put(i, strategies.get(i));
		} 
		return sortedHashMap;
	}

}

