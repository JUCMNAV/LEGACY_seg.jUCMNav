package seg.jUCMNav.importexport.reports;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import ucm.UCMspec;
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
 * @author dessure
 * 
 */
public class ReportStrategies extends ReportDataDictionary {

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
     */
    public void createReportStrategies(Document document, UCMspec ucmspec, GRLspec grlspec, URNdefinition urndef, Rectangle pagesize) {

        try {
            if (!grlspec.getStrategies().isEmpty()) {
                document.add(Chunk.NEWLINE);
                writeStrategies(document, grlspec, pagesize);
            }
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * creates the data dictionary section in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param strategies
     *            the strategies we need to evaluate
     */
    @SuppressWarnings("unchecked")
    private void writeStrategiesLegend(Document document, HashMap strategies) {

        try {

            // document.add(Chunk.NEXTPAGE);
            document.add(new Paragraph("Strategy Legend", descriptionBoldFont));

            for (int i = 1; i <= strategies.size(); i++) {
                // name and description
                EvaluationStrategy strategy = (EvaluationStrategy) strategies.get(i);

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
     * creates the data dictionary section in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param grlspec
     *            the grl specification used to retrieve elements
     * @param pagesize
     *            the size of the report page
     */
    @SuppressWarnings("unchecked")
    private void writeStrategies(Document document, GRLspec grlspec, Rectangle pagesize) {

        try {
            HashMap strategyGroups, strategies, evaluations;

            if (!grlspec.getGroups().isEmpty()) {

                strategyGroups = new HashMap();
                for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

                    StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
                    strategyGroups.put(evalGroup.getId(), evalGroup);

                    if (!evalGroup.getStrategies().isEmpty()) {
                        // maximum number of strategies per page is 17
                        int nbOfColumns = 40;
                        int maxStratPerPage = 17;
                        int intElementColumnWidth = 6;
                        int strategiesWidth = nbOfColumns - intElementColumnWidth;

                        int strategyCellWidth = 2;
                        float nbOfStrategyColumns = strategiesWidth / strategyCellWidth;

                        strategies = new HashMap();

                        // create a hashmap containing strategies (one per column), key is column number starting with 1
                        int columnNo = 1;
                        for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
                            EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
                            strategies.put(columnNo, strategy);
                            columnNo++;
                        }
                        float nbOfStrategies = strategies.size();
                        int nbOfPages = (int) (java.lang.Math.round(nbOfStrategies / maxStratPerPage)) + 1;

                        writeStrategiesLegend(document, strategies);

                        // process strategies per page
                        boolean newpage = false;
                        int lastCellOfPage = maxStratPerPage;
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
                            table.setBorderColor(new java.awt.Color(255, 255, 255));
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
                            emptyCell2.setBorderColor(new java.awt.Color(255, 255, 255));
                            emptyCell2.setColspan(intElementColumnWidth);
                            table.addCell(emptyCell2);

                            // Second line - strategy number header. This number represent a srategy as documented in the legend. One number per column/strategy.
                            for (int column = lastCellAdded; (column <= (maxStratPerPage * pageNo) && column <= nbOfStrategies); column++) {
                                Cell strategyNo = new Cell(column + "");
                                strategyNo.setBorderColor(new java.awt.Color(0, 0, 0));
                                strategyNo.setColspan(strategyCellWidth);

                                table.addCell(strategyNo);
                                lastCellOfPage = column;
                            }

                            // if the number of strategies is less than total number of columns, fill in the rest with empty cells
                            for (float i2 = (lastCellOfPage) + 1; i2 <= maxStratPerPage * pageNo; i2++) {
                                Cell emptyStrat = new Cell("");
                                emptyStrat.setBorderColor(new java.awt.Color(255, 255, 255));
                                emptyStrat.setColspan(strategyCellWidth);

                                table.addCell(emptyStrat);
                            }

                            // add intentional elements in first column, one per row
                            for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {

                                int stratEvaluation = 0;

                                IntentionalElement intElement = (IntentionalElement) iter.next();
                                Cell intElementCell = new Cell(intElement.getName());
                                intElementCell.setColspan(intElementColumnWidth);
                                table.addCell(intElementCell);

                                // column 1 contains strategy 1, column 2 -> strategy 2, ...
                                for (int column = lastCellAdded; column <= lastCellOfPage; column++) {
                                    // for each strategy (i.e. bcolumn), get evaluation for the intentionalElement of the row
                                    EvaluationStrategy currentStrategy = (EvaluationStrategy) strategies.get(column);
                                    writeEvaluation(table, currentStrategy, intElement, strategyCellWidth);

                                }

                                // if the number of evaluations/strategies is less than total number of columns, fill in the rest with empty cells
                                for (float i2 = (lastCellOfPage) + 1; i2 <= maxStratPerPage * pageNo; i2++) {
                                    // for (float i3 = nbOfStrategies + 1; i3 <= lastCellOfPage; i3++) {
                                    Cell emptyStrat = new Cell("");
                                    emptyStrat.setBorderColor(new java.awt.Color(255, 255, 255));
                                    emptyStrat.setColspan(strategyCellWidth);
                                    table.addCell(emptyStrat);
                                }

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

    /**
     * creates the data dictionary section in the report
     * 
     * @param table
     *            the in which to insert the evaluations
     * @param strategy
     *            the strategy we are evaluating
     * @param intentionalElement
     *            the intentionalElement we are referencing
     * @param strategyCellWidth
     *            the number of cells the strategy has to fill
     */

    @SuppressWarnings("unchecked")
    protected void writeEvaluation(Table table, EvaluationStrategy strategy, IntentionalElement intentionalElement, int strategyCellWidth) throws IOException {
        EvaluationStrategyManager.getInstance(false).setStrategy(strategy);
        EvaluationStrategyManager.getInstance(false).calculateEvaluation();

        // Write evaluation for intentional elements
        boolean evaluationFound = false;
        if (evaluationFound == false) {

            for (Iterator iter = strategy.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
                IntentionalElement element = (IntentionalElement) iter.next();

                if (element.getId() == intentionalElement.getId()) {
                    Evaluation evaluation = EvaluationStrategyManager.getInstance(false).getEvaluationObject(element);

                    int evalValue = evaluation.getEvaluation();
                    Cell evaluationCell = new Cell(evalValue + "");
                    evaluationCell.setColspan(strategyCellWidth);
                    if (evalValue == 0) {
                        evaluationCell.setBackgroundColor(new java.awt.Color(255, 255, 151));
                    } else if (evalValue == -100) {
                        evaluationCell.setBackgroundColor(new java.awt.Color(252, 169, 171));
                    } else if (evalValue > -100 && evalValue < 0) {
                        evaluationCell.setBackgroundColor(new java.awt.Color(253, 233, 234));
                    } else if (evalValue == 100) {
                        evaluationCell.setBackgroundColor(new java.awt.Color(210, 249, 172));
                    } else if (evalValue > 0 && evalValue < 100) {
                        evaluationCell.setBackgroundColor(new java.awt.Color(240, 253, 227));
                    }

                    table.addCell(evaluationCell);
                    evaluationFound = true;

                }
            }
        }
    }
}
