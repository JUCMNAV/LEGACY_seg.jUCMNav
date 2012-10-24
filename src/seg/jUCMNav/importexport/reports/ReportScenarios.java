package seg.jUCMNav.importexport.reports;

import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
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
import com.lowagie.text.Paragraph;
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
				// write the header of the UCM scenario documentation section
				insertHeader(document, "UCM Scenario Documentation and Execution", header1Font);
				document.add(Chunk.NEWLINE);
				// loop through each scenario group and write all data related to this group
				for (Iterator iter = ucmSpec.getScenarioGroups().iterator(); iter.hasNext();) {
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
				document.add(new Chunk(scenGroup.getName() + " (ID: " + scenGroup.getId() + ")", header2Font));
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
			scenarioTitlePar.add(new Chunk("Scenario " + scenario.getName() + " (ID: " + scenario.getId() + ")", scenTitleFont));
			document.add(scenarioTitlePar);
			document.add(Chunk.NEWLINE);
			// write all the data related to all scenarios that are included in the scenario scenario
			Paragraph inclScenariosTitlePar = new Paragraph();
			inclScenariosTitlePar.setIndentationLeft(30);
			inclScenariosTitlePar.add(new Chunk("Included UCM Scenario(s):", descriptionBoldFont));
			document.add(inclScenariosTitlePar);
			if (!scenario.getIncludedScenarios().isEmpty()) {
				for (Iterator iter = scenario.getIncludedScenarios().iterator(); iter.hasNext();) {
					Paragraph inclScenariosPar = new Paragraph();
					inclScenariosPar.setIndentationLeft(40);
	                ScenarioDef includedScenario = (ScenarioDef) iter.next();
	                inclScenariosPar.add(new Chunk("Scenario " + includedScenario.getName() + " (ID: " + includedScenario.getId() + ")", descriptionFont));
	                if (includedScenario.getDescription() != null) {
	                	inclScenariosPar.add(new Chunk(": " + includedScenario.getDescription(), descriptionFont));
	                }
	                document.add(inclScenariosPar);
	                document.add(Chunk.NEWLINE);
				}
			} else {
				Paragraph inclScenariosPar = new Paragraph();
				inclScenariosPar.setIndentationLeft(40);
				inclScenariosPar.add(new Chunk("None", headerFont));
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
			startPointsTitlePar.add(new Chunk("Scenario Start Point(s):", descriptionBoldFont));
			document.add(startPointsTitlePar);
			if (!scenario.getStartPoints().isEmpty()) {
				// write data related to all the starting points of the scenario scenario
				for (Iterator iter = scenario.getStartPoints().iterator(); iter.hasNext();) {
					Paragraph startPointsPar = new Paragraph();
					startPointsPar.setIndentationLeft(40);
					ScenarioStartPoint scenStartPoint = (ScenarioStartPoint) iter.next();
					startPointsPar.add(new Chunk(scenStartPoint.getStartPoint().getName() + " (ID: " + scenStartPoint.getStartPoint().getId() + ")", descriptionFont));
	                if (scenStartPoint.getStartPoint().getDescription() != null) {
	                	startPointsPar.add(new Chunk(": " + scenStartPoint.getStartPoint().getDescription(), descriptionFont));
	                }
	                document.add(startPointsPar);
	                Paragraph startPointsDescPar = new Paragraph();
	                startPointsDescPar.setIndentationLeft(50);
	                startPointsDescPar.add(new Chunk("Start point is enabled: " + scenStartPoint.isEnabled(), descriptionFont));
	                document.add(startPointsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph startPointsPar = new Paragraph();
				startPointsPar.setIndentationLeft(40);
				startPointsPar.add(new Chunk("No start point(s) defined for this scenario", headerFont));
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
			initTitlePar.add(new Chunk("Initialization(s):", descriptionBoldFont));
			document.add(initTitlePar);
			if (!scenario.getInitializations().isEmpty()) {
				// write all the data related to the initialization of the scenario's variable(s)
				for (Iterator iter = scenario.getInitializations().iterator(); iter.hasNext();) {
					Paragraph initPar = new Paragraph();
					initPar.setIndentationLeft(40);
	                Initialization init = (Initialization) iter.next();
	                if (init.getVariable().getDescription() == null) {
	                	initPar.add(new Chunk(init.getVariable().getName() + " (ID: " + init.getVariable().getId() + ")", descriptionFont));
	                } else {
	                	initPar.add(new Chunk(init.getVariable().getName() + " (ID: " + init.getVariable().getId() + "): " + init.getVariable().getDescription(), descriptionFont));
	                }
	                document.add(initPar);
	                Paragraph initParDesc = new Paragraph();
					initParDesc.setIndentationLeft(50);
					initParDesc.add(new Chunk("Variable type: " + init.getVariable().getType(), descriptionFont));
					initParDesc.add(new Chunk(Chunk.NEWLINE));
					initParDesc.add(new Chunk("Initial value: " + init.getValue(), descriptionFont));
					document.add(initParDesc);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph initPar = new Paragraph();
				initPar.setIndentationLeft(40);
				initPar.add(new Chunk("No initializations", headerFont));
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
			preconditionsTitlePar.add(new Chunk("Precondition(s):", descriptionBoldFont));
			document.add(preconditionsTitlePar);
			if (!scenario.getPreconditions().isEmpty()) {
				// write all the data related to the scenario's preconditions
				for (Iterator iter = scenario.getPreconditions().iterator(); iter.hasNext();) {
					Paragraph preconditionsPar = new Paragraph();
					preconditionsPar.setIndentationLeft(40);
	                Condition precondition = (Condition) iter.next();
	                preconditionsPar.add(new Chunk(precondition.getLabel(), descriptionFont));
	                if (precondition.getDescription() != null) {
	                	preconditionsPar.add(new Chunk(": " + precondition.getDescription(), descriptionFont));
	                }
	                document.add(preconditionsPar);
	                Paragraph preconditionsDescPar = new Paragraph();
	                preconditionsDescPar.setIndentationLeft(50);
	                preconditionsDescPar.add(new Chunk("Precondition expression: " + precondition.getExpression(), descriptionFont));
	                document.add(preconditionsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph preconditionsPar = new Paragraph();
				preconditionsPar.setIndentationLeft(40);
				preconditionsPar.add(new Chunk("None", headerFont));
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
			endPointsTitlePar.add(new Chunk("Scenario End Point(s):", descriptionBoldFont));
			document.add(endPointsTitlePar);
			if (!scenario.getEndPoints().isEmpty()) {
				// write all the data related to the scenario's end points
				for (Iterator iter = scenario.getEndPoints().iterator(); iter.hasNext();) {
					Paragraph endPointsPar = new Paragraph();
					endPointsPar.setIndentationLeft(40);
					ScenarioEndPoint scenEndPoint = (ScenarioEndPoint) iter.next();
					endPointsPar.add(new Chunk(scenEndPoint.getEndPoint().getName() + " (ID: " + scenEndPoint.getEndPoint().getId() + ")", descriptionFont));
	                if (scenEndPoint.getEndPoint().getDescription() != null) {
	                	endPointsPar.add(new Chunk(": " + scenEndPoint.getEndPoint().getDescription(), descriptionFont));
	                }
	                document.add(endPointsPar);
	                Paragraph endPointsDescPar = new Paragraph();
					endPointsDescPar.setIndentationLeft(50);
					endPointsDescPar.add(new Chunk("End point is enabled: " + scenEndPoint.isEnabled(), descriptionFont));
					document.add(endPointsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph endPointsPar = new Paragraph();
				endPointsPar.setIndentationLeft(40);
				endPointsPar.add(new Chunk("No end point(s) defined for this scenario", headerFont));
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
			postconditionsTitlePar.add(new Chunk("Postcondition(s):", descriptionBoldFont));
			document.add(postconditionsTitlePar);
			if (!scenario.getPreconditions().isEmpty()) {
				// write all the pertinent data related to the scenario's postconditions
				for (Iterator iter = scenario.getPostconditions().iterator(); iter.hasNext();) {
					Paragraph postconditionsPar = new Paragraph();
					postconditionsPar.setIndentationLeft(40);
	                Condition postcondition = (Condition) iter.next();
	                postconditionsPar.add(new Chunk(postcondition.getLabel(), descriptionFont));
	                if (postcondition.getDescription() != null) {
	                	postconditionsPar.add(new Chunk(": " + postcondition.getDescription(), descriptionFont));
	                }
	                document.add(postconditionsPar);
	                Paragraph postconditionsDescPar = new Paragraph();
	                postconditionsDescPar.setIndentationLeft(50);
	                postconditionsDescPar.add(new Chunk("Postcondition expression: " + postcondition.getExpression(), descriptionFont));
	                document.add(postconditionsDescPar);
				}
				document.add(Chunk.NEWLINE);
			} else {
				Paragraph postconditionsPar = new Paragraph();
				postconditionsPar.setIndentationLeft(40);
				postconditionsPar.add(new Chunk("None", headerFont));
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
	private void insertHeader(Document document, String message, Font font) {
		
		try {
			Table table = ReportUtils.createTable(tableParams[0], tableParams[1], tableParams[2], tableParams[3]);
			
			Chunk chunk = new Chunk(message, header2Font);
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
}



