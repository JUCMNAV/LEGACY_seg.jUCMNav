package seg.jUCMNav.importexport.reports;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.StrategiesGroup;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.impl.QualitativeMappingImpl;
import grl.kpimodel.impl.QualitativeMappingsImpl;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.strategies.BatchEvaluationUtil;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.strategies.StrategiesView;
import ucm.UCMspec;
import urn.URNspec;
import urncore.URNdefinition;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
/**
 * implements the creation the the report strategies and evaluation section
 * 
 * @author dessure, amiga
 * 
 */
public class ReportStrategies extends ReportDataDictionary {

	private static int evalValue = 0;
	private static HashMap<GRLLinkableElement, Integer> strategyEvaluations;
	private static HashMap<Indicator, HashMap<String, String>> indicatorEvaluations;
	private static HashMap<Indicator, String> indicatorKpiConversion;
	private Color white = new java.awt.Color(255, 255, 255);
	private Color black = new java.awt.Color(0, 0, 0);
	private final int STRATEGY_CELL_WIDTH = 2;
	private final int STRATEGY_CELL_WIDTH_INDICATORS = 3;
	private int MAX_STRATEGIES_PER_PAGE = 17;
	private int MAX_STRATEGIES_PER_PAGE_INDICATORS = 6;

	private final int TREND_CELL_WIDTH = 4;

	private URNspec urnSpec;

	private static StrategiesView sv = null;
	private static boolean designView = false;

	private HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable = new HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>>();

	private HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>> indicatorTable = new HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>>();

	StringBuffer sb = new StringBuffer(); // debugging

	private boolean prefShowTrend;
	private int prefTrend;
	private boolean prefShowKPI;


	public ReportStrategies() {

	}

	/**
	 * creates the data dictionary section in the report
	 * 
	 * @param document
	 *            the document in which the report is created
	 * @param ucmspec
	 *            the ucm specification used to retrieve elements
	 * @param grlspec
	 *            the grl specification used to retrieve elements
	 * @param urndef
	 *            the urn definition used to retrieve elements
	 * @param pagesize
	 *            the size of the report page
	 * @throws IOException 
	 */
	public void createReportStrategies(Document document, UCMspec ucmspec, GRLspec grlspec, URNdefinition urndef, Rectangle pagesize) throws IOException {

		prefShowTrend = ReportGeneratorPreferences.getShowGRLEvalStrategyTrend();
		prefTrend = Integer.parseInt(ReportGeneratorPreferences.getGRLEvalStrategyTrend());
		prefShowKPI = ReportGeneratorPreferences.getShowKpiShowEvals();

		if( prefShowTrend){
			MAX_STRATEGIES_PER_PAGE = MAX_STRATEGIES_PER_PAGE - TREND_CELL_WIDTH/2;
		}

		designView = false;
		urnSpec = grlspec.getUrnspec();

		final EvaluationStrategy firstStrategy = getFirstStrategy( grlspec );

		if( firstStrategy != null ) { // skip mode switch if design does not contain strategies
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					if( (sv = EvaluationStrategyManager.getInstance(false).getStrategiesView()) != null ) {
						if( !sv.isStrategyView() ) {
							designView = true;
							EvaluationStrategyManager.getInstance(false).setStrategy(firstStrategy);
							sv.setStrategy(firstStrategy);
							sv.showPage(StrategiesView.ID_STRATEGY);
							sv.refreshScenarioIfNeeded();
						}	
					}
				}
			});
		}

		try {
			if (!grlspec.getStrategies().isEmpty()) {
				document.add(Chunk.NEWLINE);
				writeStrategies(document, grlspec, pagesize);
				writeKPIs(document, grlspec, pagesize);

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						EvaluationStrategyManager.getInstance(false).setStrategy(null); // Avoid NPE when adding IEs after reporting
					}
				});

			}
		} catch (Exception e) {
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
			e.printStackTrace();

		}

		if( designView ) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					sv.setStrategy(null);
					sv.showPage(StrategiesView.ID_DESIGN);
					sv.cancelStrategyMode();
				}
			});
		}
	}

	private EvaluationStrategy getFirstStrategy(GRLspec grlspec) {
		for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {
			StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
			for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
				EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
				return strategy;
			}
		}
		return null;
	}

	/**
	 * creates the data dictionary section in the report
	 * 
	 * @param document
	 *            the document in which the report is created
	 * @param strategies
	 *            the strategies we need to evaluate
	 */

	private void writeStrategiesLegend(Document document, HashMap strategies, StrategiesGroup evalGroup ) {

		try {

			document.add(Chunk.NEXTPAGE);
			String title = Messages.getString("ReportStrategies.StrategyLegendForGroupQuote") + evalGroup.getName() + Messages.getString("ReportStrategies.StrategyLegendForGroupEndQuote");  //$NON-NLS-1$ //$NON-NLS-2$
			document.add(new Paragraph(title, header1Font));

			for (int i = 1; i <= strategies.size(); i++) {
				// name and description
				Integer i2 = new Integer(i);
				EvaluationStrategy strategy = (EvaluationStrategy) strategies.get(i2);

				String strategyName = strategy.getName();
				String columnNo = i + ""; //$NON-NLS-1$
				String strategyDesc = strategy.getDescription();



				if (!(strategyDesc == null || strategyDesc.equals(""))){
					ReportUtils.writeLineWithSeparator(document, columnNo, ":", strategyName + " - " + strategyDesc, descriptionFont, true); //$NON-NLS-1$ //$NON-NLS-2$
				}else{
					ReportUtils.writeLineWithSeparator(document, columnNo, ":", strategyName, descriptionFont, true); //$NON-NLS-1$  	
				}


			}

			ReportUtils.writeLineWithSeparator(document, Messages.getString("ReportStrategies.TrendNote"), ":", Messages.getString("ReportStrategies.TrendNote1") + prefTrend + Messages.getString("ReportStrategies.TrendNote2") + "\n\n", descriptionFont, true );   //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		} catch (Exception e) {
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
			e.printStackTrace();

		}
	}

	/**
	 * creates the strategy evaluation table in the report
	 * 
	 * @param document
	 *            the document in which the report is created
	 * @param grlspec
	 *            the grl specification used to retrieve elements
	 * @param pagesize
	 *            the size of the report page
	 */

	private void writeStrategies(Document document, GRLspec grlspec, Rectangle pagesize) {

		HashMap<Integer, EvaluationStrategy> strategies;

		try {
			if (!grlspec.getGroups().isEmpty()) {

				for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

					StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
					evalGroup.sortStrategies();

					if (!evalGroup.getStrategies().isEmpty()) {
						// maximum number of strategies per page is 17
						int nbOfColumns = 40;
						int intElementColumnWidth = 6;

						int strategiesWidth = nbOfColumns - intElementColumnWidth ;//- intTrendColumnWidth; //added  - intTrendColumnWidth

						strategies = new HashMap<Integer, EvaluationStrategy>();

						// create a hashmap containing strategies (one per column), key is column number starting with 1
						int columnNo = 1;
						for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
							EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
							Integer hashKey = new Integer(columnNo);
							strategies.put(hashKey, strategy);
							columnNo++;
						}
						float nbOfStrategies = strategies.size();
						int nbOfPages = (int) (Math.round(nbOfStrategies / MAX_STRATEGIES_PER_PAGE)) + 1;

						//strategies = BatchEvaluationUtil.sortStrategies(strategies, 1);
						
						writeStrategiesLegend(document, strategies, evalGroup );

						this.calculateAllEvaluations( strategies.values(), grlspec ); // build evaluations table

						// process strategies per page
						boolean newpage = false;
						int lastCellOfPage = MAX_STRATEGIES_PER_PAGE;
						int lastCellAdded = 1;
						int pageNo = 1;

						document.add(Chunk.NEXTPAGE);

						for (int i = 1; i <= nbOfPages; i++) {
							if (newpage) {
								document.add(Chunk.NEXTPAGE);
							}
							// create the table for strategy evaluations, there will be one table per strategy group
							Table table = new Table(nbOfColumns);
							table.setBorderWidth(1);
							table.setBorderColor(white);
							table.setPadding(3);
							table.setSpacing(0);
							table.setWidth(100);

							/***************************************************************************************************************************************
							 * Create the header row
							 * 
							 */
							// First Line of Table
							// First line - first cell: empty with the width of the intentional element column
							Cell emptyCell = new Cell(""); //$NON-NLS-1$
							emptyCell.setBorderColor(new java.awt.Color(0, 0, 0));
							emptyCell.setColspan(intElementColumnWidth);
							table.addCell(emptyCell);

							// First line - second cell: contains the strategy evaluation header title. Width equals all strategy columns.
							Cell cell = new Cell(Messages.getString("ReportStrategies.StrategyEvaluations")); //$NON-NLS-1$
							cell.setHeader(true);
							cell.setColspan(strategiesWidth);
							table.addCell(cell);
							table.endHeaders();

							// Second line - first cell: empty with the width of the intentional element column
							Cell emptyCell2 = new Cell(""); //$NON-NLS-1$
							emptyCell2.setBorderColor(white);
							emptyCell2.setColspan(intElementColumnWidth);
							table.addCell(emptyCell2);

							// Second line - strategy number header. This number represents a strategy as documented in the legend. One number per
							// column/strategy.
							for (int column = lastCellAdded; (column <= (MAX_STRATEGIES_PER_PAGE * pageNo) && column <= nbOfStrategies); column++) {
								Cell strategyNo = new Cell(column + ""); //$NON-NLS-1$
								strategyNo.setBorderColor(new java.awt.Color(0, 0, 0));
								strategyNo.setColspan(STRATEGY_CELL_WIDTH);

								table.addCell(strategyNo);
								lastCellOfPage = column;


							}

							// Second line - last cell: contains the strategy evaluation trend header title
							if (prefShowTrend){
								Cell trendHeadCell = new Cell(Messages.getString("ReportStrategies.Trends")); //$NON-NLS-1$
								trendHeadCell.setHeader(true);
								trendHeadCell.setColspan(TREND_CELL_WIDTH);
								table.addCell(trendHeadCell);
							}

							this.fillEmptySpace( table, lastCellOfPage, pageNo );

							// add Actors in first column, one per row

							for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {

								Actor actor = (Actor) iter.next();
								Cell actorCell = new Cell( actor.getName() + " (A)" ); //$NON-NLS-1$
								actorCell.setColspan(intElementColumnWidth);
								table.addCell(actorCell);

								// column 1 contains strategy 1, column 2 -> strategy 2, ...
								for (int column = lastCellAdded; column <= lastCellOfPage; column++) {
									// for each strategy (i.e. bcolumn), get evaluation for the Actor of the row
									EvaluationStrategy currentStrategy = strategies.get(column);
									int evalValue = evalTable.get(currentStrategy).get(actor);
									this.writeEvaluation(table, evalValue );
								}

								//calculate and show  trend if set in preferences
								if (prefShowTrend){
									int trend = calculateTrend(strategies, actor, (int)nbOfStrategies);
									this.writeTrend(table, trend);
								}


								this.fillEmptySpace( table, lastCellOfPage, pageNo );
							}                            

							// add Intentional Elements in first column, one per row
							for (Iterator iter11 = grlspec.getIntElements().iterator(); iter11.hasNext();) {

								IntentionalElement intElement = (IntentionalElement) iter11.next();
								Cell intElementCell = new Cell(intElement.getName());
								intElementCell.setColspan(intElementColumnWidth);
								table.addCell(intElementCell);

								// column 1 contains strategy 1, column 2 -> strategy 2, ...
								for (int column = lastCellAdded; column <= lastCellOfPage; column++) {
									// for each strategy (i.e. bcolumn), get evaluation for the IntentionalElement of the row
									EvaluationStrategy currentStrategy = strategies.get(column);
									int evalValue = evalTable.get(currentStrategy).get(intElement);
									this.writeEvaluation(table, evalValue );
								}

								//calculate and show  trend if set in preferences
								if (prefShowTrend){
									int trend = calculateTrend(strategies, intElement, (int)nbOfStrategies);
									this.writeTrend(table, trend);
								}



								this.fillEmptySpace( table, lastCellOfPage, pageNo );                                
							}
							document.add(table);

							newpage = true;
							lastCellAdded = lastCellOfPage + 1;
							pageNo++;
						}

					}
				}
				document.setPageSize(pagesize);
				document.add(Chunk.NEXTPAGE);
			}
		} catch (Exception e) {
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
			e.printStackTrace();

		}
	}

	private void calculateAllEvaluations( final Collection<EvaluationStrategy> strategies, final GRLspec grlspec ) {

		evalTable.clear();
		indicatorTable.clear();

		Display.getDefault().syncExec(new Runnable() {
			public void run() {

				for( EvaluationStrategy strategy : strategies ) {

					strategyEvaluations = new HashMap<GRLLinkableElement, Integer>();
					EvaluationStrategyManager.getInstance(false).setStrategy(strategy);

					indicatorEvaluations = new HashMap<Indicator, HashMap<String, String>>();
					indicatorKpiConversion = new HashMap<Indicator, String>();

					boolean tempIndicators = false;

					for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {
						IntentionalElement element = (IntentionalElement) iter.next();
						evalValue = EvaluationStrategyManager.getInstance(false).getEvaluation(element);
						strategyEvaluations.put( element, evalValue );


						if(element.getType().getName().compareTo("Indicator") == 0){

							HashMap<String, String> currentEvalKPI = new HashMap<String, String>();
							KPIEvalValueSet currentKpiEvalSet = EvaluationStrategyManager.getInstance(false).getActiveKPIEvalValueSet(element);

							String currentIndicatorKpiConv = null;

							if( currentKpiEvalSet.getKpiConv() != null){
								currentIndicatorKpiConv = (String) currentKpiEvalSet.getKpiConv().getName();
							}else{
								currentIndicatorKpiConv = "none";
							}

							currentEvalKPI.put("Threshold",String.valueOf(currentKpiEvalSet.getThresholdValue()));
							currentEvalKPI.put("Worst",String.valueOf(currentKpiEvalSet.getWorstValue()));
							currentEvalKPI.put("Target",String.valueOf(currentKpiEvalSet.getTargetValue()));
							currentEvalKPI.put("Unit",currentKpiEvalSet.getUnit());
							currentEvalKPI.put("EvaluationValue", String.valueOf(round(currentKpiEvalSet.getEvaluationValue(), 2)));
							currentEvalKPI.put("QualitativeEval", currentKpiEvalSet.getQualitativeEvaluationValue());

							indicatorKpiConversion.put((Indicator)element, currentIndicatorKpiConv);

							indicatorEvaluations.put((Indicator)element, currentEvalKPI);

							tempIndicators = true;
						}

					}

					for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {
						Actor actor = (Actor) iter.next();
						evalValue = EvaluationStrategyManager.getInstance(false).getActorEvaluation(actor);
						strategyEvaluations.put( actor, evalValue );
					}

					if (tempIndicators == true){
						indicatorTable.put(strategy, indicatorEvaluations);
					}

					evalTable.put(strategy, strategyEvaluations); // add map of this strategy's evaluations to the table
				}
			}
		});
	}

	private void fillEmptySpace( Table table, int lastCellOfPage, int pageNo ) {
		// if the number of evaluations/strategies is less than total number of columns, fill in the rest with empty cells
		for (float i2 = (lastCellOfPage) + 1; i2 <= MAX_STRATEGIES_PER_PAGE * pageNo; i2++) {
			// for (float i3 = nbOfStrategies + 1; i3 <= lastCellOfPage; i3++) {
			Cell emptyStrat = new Cell(""); //$NON-NLS-1$//new Cell(String.valueOf(i2));
			emptyStrat.setBorderColor(white);
			emptyStrat.setColspan(STRATEGY_CELL_WIDTH);
			table.addCell(emptyStrat);
		}
	}

	private void fillEmptySpaceIndicators( Table table, int lastCellOfPage, int pageNo ) {
		// if the number of evaluations/strategies is less than total number of columns, fill in the rest with empty cells
		for (float i2 = (lastCellOfPage) + 1; i2 <= MAX_STRATEGIES_PER_PAGE_INDICATORS * pageNo; i2++) {
			// for (float i3 = nbOfStrategies + 1; i3 <= lastCellOfPage; i3++) {
			Cell emptyStrat = new Cell(""); //$NON-NLS-1$//new Cell(String.valueOf(i2));
			emptyStrat.setBorderColor(white);
			emptyStrat.setColspan(STRATEGY_CELL_WIDTH_INDICATORS);
			table.addCell(emptyStrat);
		}
	}

	/**
	 * creates the data dictionary section in the report
	 * 
	 * @param table
	 *            the in which to insert the evaluations
	 */

	protected void writeEvaluation(Table table, int evalValue ) throws IOException {

		//evalValue = StrategyEvaluationPreferences.getValueToVisualize(evalValue);

		// if 0,100, convert back to -100,100 to have the right color. 
		int colorValue = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable( urnSpec,  evalValue );

		Cell evaluationCell = new Cell(evalValue + ""); //$NON-NLS-1$
		evaluationCell.setColspan(STRATEGY_CELL_WIDTH);
		if (colorValue == 0) {
			evaluationCell.setBackgroundColor(new java.awt.Color(255, 255, 151));
		} else if (colorValue == -100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(252, 169, 171));
		} else if (colorValue > -100 && colorValue < 0) {
			evaluationCell.setBackgroundColor(new java.awt.Color(253, 233, 234));
		} else if (colorValue == 100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(210, 249, 172));
		} else if (colorValue > 0 && colorValue < 100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(240, 253, 227));
		}

		table.addCell(evaluationCell);
	}

	/**
	 * creates the KPI evaluation cells
	 * 
	 * @param table
	 *            the in which to insert the evaluations
	 */

	protected void writeKPIEvaluation(Table table, int evalValue, String kpiEvalValue) throws IOException {

		//evalValue = StrategyEvaluationPreferences.getValueToVisualize(evalValue);


		// if 0,100, convert back to -100,100 to have the right color. 
		int colorValue = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable( urnSpec,  evalValue );

		Cell evaluationCell = new Cell( kpiEvalValue );
		evaluationCell.setColspan(STRATEGY_CELL_WIDTH_INDICATORS);
		evaluationCell.setHorizontalAlignment("RIGHT");
		evaluationCell.setBorderWidthLeft(0);

		if (colorValue == 0) {
			evaluationCell.setBackgroundColor(new java.awt.Color(255, 255, 151));
		} else if (colorValue == -100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(252, 169, 171));
		} else if (colorValue > -100 && colorValue < 0) {
			evaluationCell.setBackgroundColor(new java.awt.Color(253, 233, 234));
		} else if (colorValue == 100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(210, 249, 172));
		} else if (colorValue > 0 && colorValue < 100) {
			evaluationCell.setBackgroundColor(new java.awt.Color(240, 253, 227));
		}

		table.addCell(evaluationCell);
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

	private int calculateTrend(HashMap<Integer, EvaluationStrategy> strategies, GRLLinkableElement element, int numStrat) {
		// IMPORTANT! Most code duplicated from the same method in BatchEvaluation.java... 
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

		// To fix annoying behaviour... Sometimes the strategies HashMap has indices from
		// 0 to n-1, and other times from 1 to n... Hack to fix.
		int firstHashmapIndex = 1;
		if (strategies.keySet().contains(0))
			firstHashmapIndex = 0;

		EvaluationStrategy currentStrategy;

		if (numStrat >= prefTrend && prefTrend > 1){//else not enough data to calculate trend

			currentStrategy = strategies.get(numStrat - prefTrend + firstHashmapIndex); 

			lastValue = evalTable.get(currentStrategy).get(element);

			for (int i = numStrat - prefTrend + 1 + firstHashmapIndex; i < numStrat + firstHashmapIndex; i++){
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

	/**
	 * prints the trend cell
	 * 
	 * @param table
	 *            table of strategies    
	 * @param trend
	 *            trend value of the intentional element/actor
	 */
	protected void writeTrend(Table table, int trend) throws IOException {

		try{
			Cell trendCell;    	   	

			Image img;
			int scaleFactor = 50;	// default scaling factor (when page width >= 8.5 inches)
			if (reportWidth < 8.5f) {
				// needed to define a new scale factor when page width < 8.5, otherwise won't fit in table cells
				scaleFactor = 42;
				// to find the proper scaling factor, compute (defaultScalingFactor / defaultPageSizePixels) = (scaleFactor / currentWidthPixels)
				scaleFactor = (int) Math.floor((scaleFactor/(8.5f * 72)) * (reportWidth * 72));
			}

			switch(trend){
			case -1:img = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/down.png"));//$NON-NLS-1$
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(scaleFactor, scaleFactor);
			trendCell = new Cell();
			trendCell.add(img);
			trendCell.setBackgroundColor(new java.awt.Color(252, 169, 171));
			break;
			case 0:img = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/straight.png"));//$NON-NLS-1$
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(scaleFactor, scaleFactor);
			trendCell = new Cell();
			trendCell.add(img);
			trendCell.setBackgroundColor(new java.awt.Color(255, 255, 151));
			break;
			case 1:img = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/up.png"));//$NON-NLS-1$
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(scaleFactor, scaleFactor);
			trendCell = new Cell();
			trendCell.add(img);
			trendCell.setBackgroundColor(new java.awt.Color(210, 249, 172));
			break;
			case -3:img = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/vary.png"));//$NON-NLS-1$
			img.setAlignment(Image.MIDDLE);
			img.scaleToFit(scaleFactor, scaleFactor);
			trendCell = new Cell();
			trendCell.add(img);
			break;
			default:trendCell = new Cell("?"); //$NON-NLS-1$
			break;

			}

			trendCell.setColspan(TREND_CELL_WIDTH);
			trendCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//trendCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			trendCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(trendCell);
		}
		catch(Exception e){
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
			e.printStackTrace();
		}
	}

	private void writeKPIs(Document document, GRLspec grlspec, Rectangle pagesize) {

		if ( prefShowKPI ){
			HashMap<Integer, EvaluationStrategy> strategies;

			try {
				if (!grlspec.getGroups().isEmpty()) {

					for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

						StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
						evalGroup.sortStrategies();

						if (!evalGroup.getStrategies().isEmpty()) {
							// maximum number of strategies per page is 17
							int nbOfColumns = 23;
							int intElementColumnWidth = 5;

							int strategiesWidth = nbOfColumns - intElementColumnWidth ;// - intTrendColumnWidth; added  - intTrendColumnWidth           

							strategies = new HashMap<Integer, EvaluationStrategy>();

							// create a hashmap containing strategies (one per column), key is column number starting with 1
							int columnNo = 1;
							for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
								EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
								Integer hashKey = new Integer(columnNo);
								strategies.put(hashKey, strategy);
								columnNo++;
							}
							float nbOfStrategies = strategies.size();
							int nbOfPages = (int) (Math.round(nbOfStrategies / MAX_STRATEGIES_PER_PAGE_INDICATORS)) + 1;
							
							// strategies = BatchEvaluationUtil.sortStrategies(strategies, 1);

							writeStrategiesLegend(document, strategies, evalGroup );

							this.calculateAllEvaluations( strategies.values(), grlspec );//  build evaluations table


							// process strategies per page
							boolean newpage = false;
							int lastCellOfPage = MAX_STRATEGIES_PER_PAGE_INDICATORS;
							int lastCellAdded = 1;
							int pageNo = 1;

							document.add(Chunk.NEXTPAGE);

							for (int i = 1; i <= nbOfPages; i++) {
								if (newpage) {
									document.add(Chunk.NEXTPAGE);
								}
								// create the table for strategy evaluations, there will be one table per strategy group
								Table table = new Table(nbOfColumns);
								table.setBorderWidth(1);
								table.setBorderColor(white);
								table.setPadding(3);
								table.setSpacing(0);
								table.setWidth(100);

								/***************************************************************************************************************************************
								 * Create the header row
								 * 
								 */
								// First Line of Table

								// First line - first cell: empty with the width of the intentional element column
								Cell emptyCell = new Cell(""); //$NON-NLS-1$
								emptyCell.setBorderColor(new java.awt.Color(0, 0, 0));
								emptyCell.setColspan(intElementColumnWidth);
								table.addCell(emptyCell);

								//First line - second cell: contains the strategy evaluation header title. Width equals all strategy columns.
								Cell cell = new Cell(Messages.getString("ReportStrategies.StrategyKPI")); //$NON-NLS-1$
								cell.setHeader(true);
								cell.setColspan(strategiesWidth);
								table.addCell(cell);


								// Second line - first cell: empty with the width of the intentional element column
								Cell emptyCell2 = new Cell("Indicators"); //$NON-NLS-1$
								emptyCell2.setBorderColor(white);
								emptyCell2.setColspan(intElementColumnWidth);
								table.addCell(emptyCell2);

								// Second line - strategy number header. This number represents a strategy as documented in the legend. One number per
								//  column/strategy.
								for (int column = lastCellAdded; (column <= (MAX_STRATEGIES_PER_PAGE_INDICATORS * pageNo) && column <= nbOfStrategies); column++) {
									Cell strategyNo = new Cell(column + ""); //$NON-NLS-1$
									strategyNo.setBorderColor(new java.awt.Color(0, 0, 0));
									strategyNo.setColspan(STRATEGY_CELL_WIDTH_INDICATORS);

									table.addCell(strategyNo);
									lastCellOfPage = column;


								}

								// Second line - last cell: contains the strategy evaluation trend header title
								if (prefShowTrend){
									Cell trendHeadCell = new Cell(Messages.getString("ReportStrategies.Trends")); //$NON-NLS-1$
									trendHeadCell.setHeader(true);
									trendHeadCell.setColspan(TREND_CELL_WIDTH);
									table.addCell(trendHeadCell);
								}

								this.fillEmptySpaceIndicators( table, lastCellOfPage, pageNo );

								for (Iterator iter11 = grlspec.getIntElements().iterator(); iter11.hasNext();) {

									IntentionalElement intElement = (IntentionalElement) iter11.next();

									if( intElement.getType().getName().compareTo("Indicator") == 0){

										String intElemConv = indicatorKpiConversion.get((Indicator)intElement);
										Cell intElementCell = null;
										boolean kpiConverted = false;

										if( intElemConv.compareTo("none") != 0)
											kpiConverted = true;

										// true if the indicator as a KPI Conversion
										if( kpiConverted ){
											intElementCell = new Cell("\n" + intElement.getName() + "(KPI Conversion : " + indicatorKpiConversion.get((Indicator)intElement) + ")"); 
										}else{
											intElementCell = new Cell("\n" + intElement.getName());
										}

										intElementCell.setBorderWidthTop(1);
										intElementCell.setBorderColorTop(black);
										intElementCell.setColspan(nbOfColumns);
										intElementCell.setBorderWidthBottom(2);
										table.addCell(intElementCell);


										for( int ctr = 0; ctr < 5; ctr++){

											boolean firstIteration = true;

											//column 1 contains strategy 1, column 2 -> strategy 2, ...
											for (int column = lastCellAdded; column <= lastCellOfPage; column++) {
												// for each strategy (i.e. bcolumn), get evaluation for the IntentionalElement of the row
												EvaluationStrategy currentStrategy = strategies.get(column);

												int evalValue = evalTable.get(currentStrategy).get(intElement);

												HashMap<Indicator, HashMap<String, String>> currentIndicatorEval = indicatorTable.get(currentStrategy);
												HashMap<String, String> kpiEvalValue = ((HashMap<String, String>)currentIndicatorEval.get(intElement));


												if( firstIteration ){


													String unit = kpiEvalValue.get("Unit");        

													Cell kpiEvalValueType = null;   


													// the headers of the first column for a intElement is added to the table
													if (kpiConverted && ctr == 0){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.QualitativeEvaluation"));
													}else if( kpiConverted && ctr == 1 ){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.Evaluation"));
														ctr = 4;
													}else if( ctr == 0 ){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.Threshold")+ " (" + unit + ")");
													}else if( ctr == 1){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.Worst") +" (" + unit + ")");
													}else if( ctr == 2){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.Target")+ " (" + unit+ ")");
													}else if (ctr == 3){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.QuantitativeEvaluation") + " (" + unit+ ")" );
													}else if (ctr == 4){
														kpiEvalValueType = new Cell(Messages.getString("KPIViewObjectFigure.Evaluation"));
													}

													kpiEvalValueType.setColspan(intElementColumnWidth);
													kpiEvalValueType.setHorizontalAlignment("RIGHT");
													kpiEvalValueType.setBorderWidthRight(1);
													table.addCell(kpiEvalValueType);
												}

												firstIteration = false;

												// the informations for each strategy is added to the table
												if ( kpiConverted && ctr == 0){
													this.writeKPIEvaluation(table, evalValue, kpiEvalValue.get("QualitativeEval"));
												}else if( kpiConverted && ctr == 1 ){
													this.writeKPIEvaluation(table, evalValue, String.valueOf(evalValue));
													ctr = 4;
												}else if( ctr == 0){
													this.writeKPIEvaluation(table, evalValue, (String)kpiEvalValue.get("Threshold"));
												}else if( ctr == 1){
													this.writeKPIEvaluation(table, evalValue, (String)kpiEvalValue.get("Worst"));
												}else if( ctr == 2){
													this.writeKPIEvaluation(table, evalValue, (String)kpiEvalValue.get("Target"));
												}else if (ctr == 3){
													this.writeKPIEvaluation(table, evalValue, (String)kpiEvalValue.get("EvaluationValue"));
												}else if (ctr == 4){
													this.writeKPIEvaluation(table, evalValue, String.valueOf(evalValue));
												}
											}
											this.fillEmptySpaceIndicators( table, lastCellOfPage, pageNo ); 
										}  
									}
								}
								document.add(table);

								newpage = true;
								lastCellAdded = lastCellOfPage + 1;
								pageNo++;
							}

						}
					}

					if( ! grlspec.getKPIConversion().isEmpty())
						writeKpiConversionTables(document, grlspec, pagesize);

					document.setPageSize(pagesize);
					document.add(Chunk.NEXTPAGE);


				}
			} catch (Exception e) {
				jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
				e.printStackTrace();

			}
		}
	}

	/*
	 * Creates kpi conversion mapping tables
	 * 
	 * @param document
	 * 		the document where to put the table
	 * @grlspec
	 * 		the grlspec where the kpi conversion are found
	 * @pagesize
	 * 		the size of the page in the document where to put the table
	 */

	private void writeKpiConversionTables(Document document, GRLspec grlspec, Rectangle pagesize) {

		try{

			for( Object obj : grlspec.getKPIConversion()){


				QualitativeMappingsImpl currentKpiConv = (QualitativeMappingsImpl) obj;
				int nbOfColumns = currentKpiConv.getMapping().size() + 2;

				String title = " \n\n " +  Messages.getString("ReportStrategies.KpiConversionQuote") + currentKpiConv.getName() + "\" ";  //$NON-NLS-1$ //$NON-NLS-2$
				document.add(new Paragraph(title, header1Font));

				Table kpiTable = new Table(nbOfColumns);
				kpiTable.setBorderWidth(1);
				kpiTable.setPadding(3);
				kpiTable.setSpacing(0);
				kpiTable.setBorderColorBottom(black);
				kpiTable.setWidth(100);


				// First line - first cell
				Cell realWorldHeader = new Cell(Messages.getString("ReportStrategies.RealWorldLabel"));
				realWorldHeader.setColspan(2);
				realWorldHeader.setBorderColorBottom(black);
				kpiTable.addCell(realWorldHeader);

				// First line - RealWorldLabels of mappings

				for ( Object obj2 : currentKpiConv.getMapping()){
					QualitativeMappingImpl currentMapping = (QualitativeMappingImpl) obj2;

					Cell realWorldLabelsCell = new Cell (currentMapping.getRealWorldLabel());
					kpiTable.addCell(realWorldLabelsCell);
				}  

				// Second line - first cell
				Cell evalMappingHeader = new Cell(Messages.getString("ReportStrategies.EvaluationValue"));
				evalMappingHeader.setColspan(2);
				evalMappingHeader.setBorderColorBottom(black);
				kpiTable.addCell(evalMappingHeader);

				// Second line - rest of the cells : evaluation values

				for ( Object obj2 : currentKpiConv.getMapping()){
					QualitativeMappingImpl currentMapping = (QualitativeMappingImpl) obj2;

					Cell evaluationMappingCell = new Cell(String.valueOf(currentMapping.getEvaluation()));
					evaluationMappingCell.setBorderColor(black);
					kpiTable.addCell(evaluationMappingCell);
				}  

				document.add(kpiTable);
			}

		} catch (Exception e) {
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
			e.printStackTrace();
		}


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
}