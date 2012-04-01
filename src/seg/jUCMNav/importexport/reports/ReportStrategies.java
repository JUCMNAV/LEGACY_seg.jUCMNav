package seg.jUCMNav.importexport.reports;

import grl.Actor;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.awt.Color;
import java.lang.Math;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.strategies.StrategiesView;
import ucm.UCMspec;
import urn.URNspec;
import urncore.URNdefinition;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
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

	private static Evaluation evaluation = null;
	private static int evalValue = 0;
	private static HashMap<GRLLinkableElement, Integer> strategyEvaluations;
	private Color white = new java.awt.Color(255, 255, 255);
    private final int STRATEGY_CELL_WIDTH = 2;
    private final int MAX_STRATEGIES_PER_PAGE = 17;
    private URNspec urnSpec;
    
	private static StrategiesView sv = null;
	private static boolean designView = false;

    private HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable = new HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>>();
    
    StringBuffer sb = new StringBuffer(); // debugging
    
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

            // document.add(Chunk.NEXTPAGE);
        	String title = "Strategy Legend for Group \"" + evalGroup.getName() + "\""; 
            document.add(new Paragraph(title, descriptionBoldFont));

            for (int i = 1; i <= strategies.size(); i++) {
                // name and description
                Integer i2 = new Integer(i);
                EvaluationStrategy strategy = (EvaluationStrategy) strategies.get(i2);

                String strategyName = strategy.getName();
                String columnNo = i + "";
                ReportUtils.writeLineWithSeparator(document, columnNo, ":", strategyName, descriptionFont, true);
            }

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

        HashMap  evaluations;
        HashMap<Integer, EvaluationStrategy> strategies;
    	
        try {
            if (!grlspec.getGroups().isEmpty()) {

                for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

                    StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();

                    if (!evalGroup.getStrategies().isEmpty()) {
                        // maximum number of strategies per page is 17
                        int nbOfColumns = 40;
                        int intElementColumnWidth = 6;
                        int strategiesWidth = nbOfColumns - intElementColumnWidth;

                        float nbOfStrategyColumns = strategiesWidth / STRATEGY_CELL_WIDTH;

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
                            Cell emptyCell = new Cell("");
                            emptyCell.setBorderColor(new java.awt.Color(0, 0, 0));
                            emptyCell.setColspan(intElementColumnWidth);
                            table.addCell(emptyCell);

                            // First line - second cell: contains the strategy evaluation header title. Width equals all strategy columns.
                            Cell cell = new Cell("Strategy Evaluations");
                            cell.setHeader(true);
                            cell.setColspan(strategiesWidth);
                            table.addCell(cell);

                            // Second line - first cell: empty with the width of the intentional element column
                            Cell emptyCell2 = new Cell("");
                            emptyCell2.setBorderColor(white);
                            emptyCell2.setColspan(intElementColumnWidth);
                            table.addCell(emptyCell2);

                            // Second line - strategy number header. This number represents a strategy as documented in the legend. One number per
                            // column/strategy.
                            for (int column = lastCellAdded; (column <= (MAX_STRATEGIES_PER_PAGE * pageNo) && column <= nbOfStrategies); column++) {
                                Cell strategyNo = new Cell(column + "");
                                strategyNo.setBorderColor(new java.awt.Color(0, 0, 0));
                                strategyNo.setColspan(STRATEGY_CELL_WIDTH);

                                table.addCell(strategyNo);
                                lastCellOfPage = column;
                            }

                            this.fillEmptySpace( table, lastCellOfPage, pageNo );

                            // add Actors in first column, one per row

                            for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {
                            
                            	Actor actor = (Actor) iter.next();
                                Cell actorCell = new Cell( actor.getName() + " (A)" );
                                actorCell.setColspan(intElementColumnWidth);
                                table.addCell(actorCell);
                            	
                                // column 1 contains strategy 1, column 2 -> strategy 2, ...
                                for (int column = lastCellAdded; column <= lastCellOfPage; column++) {
                                    // for each strategy (i.e. bcolumn), get evaluation for the Actor of the row
                                    EvaluationStrategy currentStrategy = strategies.get(column);
                                    int evalValue = evalTable.get(currentStrategy).get(actor);
                                    this.writeEvaluation(table, evalValue );
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

    	Display.getDefault().syncExec(new Runnable() {
    		public void run() {

    			for( EvaluationStrategy strategy : strategies ) {

    				strategyEvaluations = new HashMap<GRLLinkableElement, Integer>();
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
    		}
    	});
    }
    
    private void fillEmptySpace( Table table, int lastCellOfPage, int pageNo ) {
        // if the number of evaluations/strategies is less than total number of columns, fill in the rest with empty cells
        for (float i2 = (lastCellOfPage) + 1; i2 <= MAX_STRATEGIES_PER_PAGE * pageNo; i2++) {
            // for (float i3 = nbOfStrategies + 1; i3 <= lastCellOfPage; i3++) {
            Cell emptyStrat = new Cell("");
            emptyStrat.setBorderColor(white);
            emptyStrat.setColspan(STRATEGY_CELL_WIDTH);
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

    	Cell evaluationCell = new Cell(evalValue + "");
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
    
}
