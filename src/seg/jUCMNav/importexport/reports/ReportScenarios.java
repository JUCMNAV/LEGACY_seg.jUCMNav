package seg.jUCMNav.importexport.reports;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.scenarios.ScenarioTraversalAlgorithm;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.algorithmInterfaces.IScenarioTraversalAlgorithm;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.UCMspec;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * Implements the creation of the UCM scenario documentation section
 * 
 * @author jsegu025
 * 
 */

public class ReportScenarios extends Report {

	// The table parameters used to create the section header
	protected int[] tableParams = { 1, 2, 0, 100 };
	
		
	//vector which contains warnings for current scenario
	private Vector warnings; 
	
	private final int scenario_width = 6;
	private final int result_width = 4;
	
	private Color white = new java.awt.Color(255, 255, 255);
	
	public ReportScenarios() {
		
	}
	
	/**
     * entry point of the UCM scenario documentation section
     * (this method calls every other method that writes information into this section)
     * 
     * @param document
     *            the document we are reporting into
     * @param ucmSpec
     *            the UCM specification from which we are getting scenario info
     */
	public void writeUCMScenarioInformation(Document document, UCMspec ucmSpec) {
		
		try {
			if (!ucmSpec.getScenarioGroups().isEmpty()) {
				// loop through each scenario group and write all data related to this group
				for (Iterator iter = ucmSpec.getScenarioGroups().iterator(); iter.hasNext();) {
					document.add(Chunk.NEWLINE);
	                ScenarioGroup scenGroup = (ScenarioGroup) iter.next();
	                writeScenarioGroupInfo(document, scenGroup);
				}
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}
	
	/**
     * this method writes data on all the scenario groups, and calls other 
     * methods that write information specific to each scenario
     * 
     * @param document
     *            the document we are reporting into
     * @param scenGroup
     *            the ScenarioGroup from which we are getting scenario info
     */
	private void writeScenarioGroupInfo(Document document, ScenarioGroup scenGroup) {
		
		try {
			if (!scenGroup.getScenarios().isEmpty()) {
				document.add(new Chunk(scenGroup.getName() + " (" + "ID" + ": " + scenGroup.getId() + ")", header2Font)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	            document.add(Chunk.NEWLINE);
	            // write all pertinent data related to each scenario belonging to the scnario group scenGroup
				for (Iterator iter = scenGroup.getScenarios().iterator(); iter.hasNext();) {
	                document.add(Chunk.NEWLINE);
					ScenarioDef scenario = (ScenarioDef) iter.next();
					Paragraph scenarioGroupPar = new Paragraph();
					scenarioGroupPar.setIndentationLeft(10);
	                writeScenarioInfo(document, scenario);
				}
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}

	/**
     * this method writes data related to one scenario, and calls other 
     * methods that write information specific to each scenario component
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeScenarioInfo(Document document, ScenarioDef scenario) {
		
		try {	
			Paragraph scenarioTitlePar = new Paragraph();
			scenarioTitlePar.setIndentationLeft(20);
			scenarioTitlePar.add(new Chunk(Messages.getString("ReportScenarios.Scenario") + scenario.getName() + " (" + "ID" + ": " + scenario.getId() + ")", scenTitleFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			document.add(scenarioTitlePar);
			document.add(Chunk.NEWLINE);
			// write all the data related to all scenarios that are included in the scenario scenario
			Paragraph inclScenariosTitlePar = new Paragraph();
			inclScenariosTitlePar.setIndentationLeft(30);
			inclScenariosTitlePar.add(new Chunk(Messages.getString("ReportScenarios.IncludedScenarios") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(inclScenariosTitlePar);
			if (!scenario.getIncludedScenarios().isEmpty()) {
				for (Iterator iter = scenario.getIncludedScenarios().iterator(); iter.hasNext();) {
					Paragraph inclScenariosPar = new Paragraph();
					inclScenariosPar.setIndentationLeft(40);
	                ScenarioDef includedScenario = (ScenarioDef) iter.next();
	                inclScenariosPar.add(new Chunk(Messages.getString("ReportScenarios.Scenario") + includedScenario.getName() + " (" + "ID" + ": " + includedScenario.getId() + ")", descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	                if (includedScenario.getDescription() != null) {
	                	inclScenariosPar.add(new Chunk(": " + includedScenario.getDescription(), descriptionFont)); //$NON-NLS-1$
	                }
	                document.add(inclScenariosPar);
	                document.add(Chunk.NEWLINE);
				}
			} else {
				Paragraph inclScenariosPar = new Paragraph();
				inclScenariosPar.setIndentationLeft(40);
				inclScenariosPar.add(new Chunk(Messages.getString("ReportScenarios.None"), headerFont)); //$NON-NLS-1$
				document.add(inclScenariosPar);
				document.add(Chunk.NEWLINE);
			}
			// write all other pertinent data related to the scenario
			writeStartPoints(document, scenario);
			writeInitializations(document, scenario);
			writePreconditions(document, scenario);
			writeEndPoints(document, scenario);
			writePostconditions(document, scenario);
			document.add(Chunk.NEXTPAGE);
			
			
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}
	
	

	
	
	
	
	

	/**
     * this method writes data related to a scenario's start points
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeStartPoints(Document document, ScenarioDef scenario) {
		
		try {	
			Paragraph startPointsTitlePar = new Paragraph();
			startPointsTitlePar.setIndentationLeft(30);
			startPointsTitlePar.add(new Chunk(Messages.getString("ReportScenarios.ScenarioStartPoints") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(startPointsTitlePar);
			if (!scenario.getStartPoints().isEmpty()) {
				// write data related to all the starting points of the scenario scenario
				for (Iterator iter = scenario.getStartPoints().iterator(); iter.hasNext();) {
					Paragraph startPointsPar = new Paragraph();
					startPointsPar.setIndentationLeft(40);
					ScenarioStartPoint scenStartPoint = (ScenarioStartPoint) iter.next();
					startPointsPar.add(new Chunk(scenStartPoint.getStartPoint().getName() + " (" + "ID" + ": " + scenStartPoint.getStartPoint().getId() + ")", descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	                if (scenStartPoint.getStartPoint().getDescription() != null) {
	                	startPointsPar.add(new Chunk(": " + scenStartPoint.getStartPoint().getDescription(), descriptionFont)); //$NON-NLS-1$
	                }
	                document.add(startPointsPar);
	                Paragraph startPointsDescPar = new Paragraph();
	                startPointsDescPar.setIndentationLeft(50);
	                if (scenStartPoint.isEnabled()) {
	                	startPointsDescPar.add(new Chunk(Messages.getString("ReportScenarios.StartPointIsEnabled") + ": " + Messages.getString("ReportScenarios.True"), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                } else {
	                	startPointsDescPar.add(new Chunk(Messages.getString("ReportScenarios.StartPointIsEnabled") + ": " + Messages.getString("ReportScenarios.False"), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                }
	                document.add(startPointsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph startPointsPar = new Paragraph();
				startPointsPar.setIndentationLeft(40);
				startPointsPar.add(new Chunk(Messages.getString("ReportScenarios.NoStartPointDefined"), headerFont)); //$NON-NLS-1$
				document.add(startPointsPar);
				document.add(Chunk.NEWLINE);
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}
	
	/**
     * this method writes data related to the initialization of a scenario's variable(s)
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeInitializations(Document document, ScenarioDef scenario) {
		
		try {	
			Paragraph initTitlePar = new Paragraph();
			initTitlePar.setIndentationLeft(30);
			initTitlePar.add(new Chunk(Messages.getString("ReportScenarios.Initializations") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(initTitlePar);
			if (!scenario.getInitializations().isEmpty()) {
				// write all the data related to the initialization of the scenario's variable(s)
				for (Iterator iter = scenario.getInitializations().iterator(); iter.hasNext();) {
					Paragraph initPar = new Paragraph();
					initPar.setIndentationLeft(40);
	                Initialization init = (Initialization) iter.next();
	                if (init.getVariable().getDescription() == null) {
	                	initPar.add(new Chunk(init.getVariable().getName() + " (" + "ID" + ": " + init.getVariable().getId() + ")", descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	                } else {
	                	initPar.add(new Chunk(init.getVariable().getName() + " (" + "ID" + ": " + init.getVariable().getId() + "): " + init.getVariable().getDescription(), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	                }
	                document.add(initPar);
	                Paragraph initParDesc = new Paragraph();
					initParDesc.setIndentationLeft(50);
					initParDesc.add(new Chunk(Messages.getString("ReportScenarios.VariableType") + ": " + init.getVariable().getType(), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$
					initParDesc.add(new Chunk(Chunk.NEWLINE));
					initParDesc.add(new Chunk(Messages.getString("ReportScenarios.InitialValue") + ": " + init.getValue(), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$
					document.add(initParDesc);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph initPar = new Paragraph();
				initPar.setIndentationLeft(40);
				initPar.add(new Chunk(Messages.getString("ReportScenarios.NoInitialization"), headerFont)); //$NON-NLS-1$
				document.add(initPar);
				document.add(Chunk.NEWLINE);
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}
	
	/**
     * this method writes data related to the precondition(s) of a scenario
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writePreconditions(Document document, ScenarioDef scenario) {
		
		try {	
			Paragraph preconditionsTitlePar = new Paragraph();
			preconditionsTitlePar.setIndentationLeft(30);
			preconditionsTitlePar.add(new Chunk(Messages.getString("ReportScenarios.Preconditions") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(preconditionsTitlePar);
			if (!scenario.getPreconditions().isEmpty()) {
				// write all the data related to the scenario's preconditions
				for (Iterator iter = scenario.getPreconditions().iterator(); iter.hasNext();) {
					Paragraph preconditionsPar = new Paragraph();
					preconditionsPar.setIndentationLeft(40);
	                Condition precondition = (Condition) iter.next();
	                preconditionsPar.add(new Chunk(precondition.getLabel(), descriptionFont));
	                if (precondition.getDescription() != null) {
	                	preconditionsPar.add(new Chunk(": " + precondition.getDescription(), descriptionFont)); //$NON-NLS-1$
	                }
	                document.add(preconditionsPar);
	                Paragraph preconditionsDescPar = new Paragraph();
	                preconditionsDescPar.setIndentationLeft(50);
	                preconditionsDescPar.add(new Chunk(Messages.getString("ReportScenarios.PreconditionExpression") + ": " + precondition.getExpression(), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$
	                document.add(preconditionsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph preconditionsPar = new Paragraph();
				preconditionsPar.setIndentationLeft(40);
				preconditionsPar.add(new Chunk(Messages.getString("ReportScenarios.None2"), headerFont)); //$NON-NLS-1$
				document.add(preconditionsPar);
				document.add(Chunk.NEWLINE);
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}

	/**
     * this method writes data related to a scenario's end points
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeEndPoints(Document document, ScenarioDef scenario) {
		
		try {	
			Paragraph endPointsTitlePar = new Paragraph();
			endPointsTitlePar.setIndentationLeft(30);
			endPointsTitlePar.add(new Chunk(Messages.getString("ReportScenarios.ScenarioEndPoints") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(endPointsTitlePar);
			if (!scenario.getEndPoints().isEmpty()) {
				// write all the data related to the scenario's end points
				for (Iterator iter = scenario.getEndPoints().iterator(); iter.hasNext();) {
					Paragraph endPointsPar = new Paragraph();
					endPointsPar.setIndentationLeft(40);
					ScenarioEndPoint scenEndPoint = (ScenarioEndPoint) iter.next();
					endPointsPar.add(new Chunk(scenEndPoint.getEndPoint().getName() + " (" + "ID" + ": " + scenEndPoint.getEndPoint().getId() + ")", descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	                if (scenEndPoint.getEndPoint().getDescription() != null) {
	                	endPointsPar.add(new Chunk(": " + scenEndPoint.getEndPoint().getDescription(), descriptionFont)); //$NON-NLS-1$
	                }
	                document.add(endPointsPar);
	                Paragraph endPointsDescPar = new Paragraph();
					endPointsDescPar.setIndentationLeft(50);
					if (scenEndPoint.isEnabled()) {
						endPointsDescPar.add(new Chunk(Messages.getString("ReportScenarios.EndPointIsEnabled") + ": " + Messages.getString("ReportScenarios.True"), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					} else {
						endPointsDescPar.add(new Chunk(Messages.getString("ReportScenarios.EndPointIsEnabled") + ": " + Messages.getString("ReportScenarios.False"), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					document.add(endPointsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph endPointsPar = new Paragraph();
				endPointsPar.setIndentationLeft(40);
				endPointsPar.add(new Chunk(Messages.getString("ReportScenarios.NoEndPointDefined"), headerFont)); //$NON-NLS-1$
				document.add(endPointsPar);
				document.add(Chunk.NEWLINE);
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}

	/**
     * this method writes data related to the postcondition(s) of a scenario
     * 
     * @param document
     *            the document we are reporting into
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writePostconditions(Document document, ScenarioDef scenario) {
		
		try {
			Paragraph postconditionsTitlePar = new Paragraph();
			postconditionsTitlePar.setIndentationLeft(30);
			postconditionsTitlePar.add(new Chunk(Messages.getString("ReportScenarios.Postconditions") + ":", descriptionBoldFont)); //$NON-NLS-1$ //$NON-NLS-2$
			document.add(postconditionsTitlePar);
			if (!scenario.getPreconditions().isEmpty()) {
				// write all the pertinent data related to the scenario's postconditions
				for (Iterator iter = scenario.getPostconditions().iterator(); iter.hasNext();) {
					Paragraph postconditionsPar = new Paragraph();
					postconditionsPar.setIndentationLeft(40);
	                Condition postcondition = (Condition) iter.next();
	                postconditionsPar.add(new Chunk(postcondition.getLabel(), descriptionFont));
	                if (postcondition.getDescription() != null) {
	                	postconditionsPar.add(new Chunk(": " + postcondition.getDescription(), descriptionFont)); //$NON-NLS-1$
	                }
	                document.add(postconditionsPar);
	                Paragraph postconditionsDescPar = new Paragraph();
	                postconditionsDescPar.setIndentationLeft(50);
	                postconditionsDescPar.add(new Chunk(Messages.getString("ReportScenarios.PostconditionExpression") + ": " + postcondition.getExpression(), descriptionFont)); //$NON-NLS-1$ //$NON-NLS-2$
	                document.add(postconditionsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph postconditionsPar = new Paragraph();
				postconditionsPar.setIndentationLeft(40);
				postconditionsPar.add(new Chunk(Messages.getString("ReportScenarios.None2"), headerFont)); //$NON-NLS-1$
				document.add(postconditionsPar);
				document.add(Chunk.NEWLINE);
			}
		} catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
	}

	/**
     * this helper method writes the header of the UCM scenario documentation section
     * 
     * @param document
     *            the document we are reporting into
     * @param message
     *            the string to be displayed in the header
     * @param font
     *            the font we will use to display the header
     */
	public void insertHeader(Document document, String message, Font font) {
		
		try {
			Table table = ReportUtils.createTable(tableParams[0], tableParams[1], tableParams[2], tableParams[3]);
			
			Chunk chunk = new Chunk(message, font);
			Cell descriptionCell = new Cell(chunk);
			descriptionCell.setColspan(1);
			descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			descriptionCell.setBorderWidthBottom(1.5f);
			
			table.addCell(descriptionCell);
			
			document.add(table);
		} catch (Exception e) {
			jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();
		}
	}
	
	
	
	/**
     * writes the execution summary tables
     * 
     * @param document
     *            the document we are reporting into
     * @param ucmSpec
     *            the UCM specification from which we are getting scenario info
     */
	public void writeSummary(Document document, UCMspec ucmSpec){
		 
        try {
            if (!ucmSpec.getScenarioGroups().isEmpty()) {
            	//document.add(Chunk.NEXTPAGE); 

            	document.add(Chunk.NEWLINE);
            	document.add(new Chunk(Messages.getString("ReportScenarios.ExecutionSummaryTitle"), header2Font));  //$NON-NLS-1$
            	
                for (Iterator iter1 = ucmSpec.getScenarioGroups().iterator(); iter1.hasNext();) {

                	ScenarioGroup scenGroup = (ScenarioGroup) iter1.next();
                    
                    if (!scenGroup.getScenarios().isEmpty()) {
                        int nbOfColumns = 40;
                        
                        int warnMsg_width = nbOfColumns - scenario_width - result_width;


            		 document.add(Chunk.NEWLINE);
                        
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

                            // First line: contains the scenario group's name. Width equals width of table
                            Cell cell = new Cell( new Phrase(scenGroup.getName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
                            cell.setHeader(true);
                            cell.setColspan(nbOfColumns);
                            //cell.
                            table.addCell(cell);
                            
                            // Second line - first cell: Cell which contains the Scenario header
                            Cell cell2 = new Cell(new Phrase(Messages.getString("ReportScenarios.ScenarioName"), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));  //$NON-NLS-1$
                            cell2.setColspan(scenario_width);
                            table.addCell(cell2);
                            
                            // Second line - second cell: Cell which contains the Result header
                            Cell cell3 = new Cell(new Phrase(Messages.getString("ReportScenarios.Result"), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));  //$NON-NLS-1$
                            cell3.setColspan(result_width);
                            table.addCell(cell3);
                            
	                         // Second line - third cell: Cell which contains the Messages header
                            Cell cell4 = new Cell(new Phrase(Messages.getString("ReportScenarios.Messages"), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));  //$NON-NLS-1$
                            cell4.setColspan(warnMsg_width);
                            table.addCell(cell4);


                            // add Scenarios in first column, one per row
                            for (Iterator iter = scenGroup.getScenarios().iterator(); iter.hasNext();) {
                            
                            	ScenarioDef scenario = (ScenarioDef) iter.next();
                            	
                            	//get warnings for scenario
	                            Vector warnings = ScenarioUtils.traverseWarn(scenario, null);
	    						
	                            String result; //PASS or FAIL
	                            
	                            //Cell which contains name of the scenario
                                Cell scenarioCell = new Cell( scenario.getName());
                                scenarioCell.setColspan(scenario_width);
                                
                                if (warnings.size() > 0) {
                                	scenarioCell.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
	    							result = "FAIL"; //$NON-NLS-1$
	    						}else{
                                	scenarioCell.setBackgroundColor(new java.awt.Color(210, 249, 172)); //green
	    							result = "PASS"; //$NON-NLS-1$
	    						}
                               
                                table.addCell(scenarioCell);

                                
                                //no warnings
                                if (warnings.size() <= 0){
                                	Cell resultCell = new Cell(result);
	                                resultCell.setColspan(result_width);
	                                resultCell.setBackgroundColor(new java.awt.Color(210, 249, 172)); //green
	                                table.addCell(resultCell);
	                                
                                	//add empty msg cell
                                	Cell emptyMsgCell = new Cell(""); //$NON-NLS-1$
                                	emptyMsgCell.setColspan(warnMsg_width);
                                	emptyMsgCell.setBackgroundColor(new java.awt.Color(210, 249, 172)); //green
	                                table.addCell(emptyMsgCell);
                                }else{
                                	
                                	Cell resultCell = new Cell(result);
	                                resultCell.setColspan(result_width);
	                                resultCell.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
	                                table.addCell(resultCell);
	                                
	                                
                                	boolean firstWarn = true;
                                	//add all warnings one at a time
                                	 for (Iterator iterWarn = warnings.iterator(); iterWarn.hasNext();) {
                                         TraversalWarning o = (TraversalWarning) iterWarn.next();
                                         
                                        if(firstWarn){
                                        Cell warnCell = new Cell(o.getMsg());
                                        warnCell.setColspan(warnMsg_width);
                                        warnCell.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
     	                                table.addCell(warnCell);
                                        }else{
                                        	Cell emptyCell = new Cell(""); //$NON-NLS-1$
                                        	emptyCell.setColspan(scenario_width);
                                        	emptyCell.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
	     	                                table.addCell(emptyCell);
                                        	
		     	                            Cell emptyCell2 = new Cell(""); //$NON-NLS-1$
		     	                            emptyCell2.setColspan(result_width);
                                        	emptyCell2.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
		     	                            table.addCell(emptyCell2);
                                        	
                                        	Cell warnCell = new Cell(o.getMsg());
	                                        warnCell.setColspan(warnMsg_width);
	                                        warnCell.setBackgroundColor(new java.awt.Color(252, 169, 171)); //red
	     	                                table.addCell(warnCell);
                                        }
                                        
                                        firstWarn = false;
                                	 }
                                }
                                
                            }                            


                            document.add(table);

                        }

                    }
                }
                document.add(Chunk.NEXTPAGE);
            
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }
}



