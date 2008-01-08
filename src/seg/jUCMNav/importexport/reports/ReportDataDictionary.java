package seg.jUCMNav.importexport.reports;

import grl.Actor;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import ucm.UCMspec;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;
import urncore.Component;
import urncore.Responsibility;
import urncore.URNdefinition;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;

/**
 * implements the creation the the report data dictionary section
 * 
 * @author dessure
 * 
 */
public class ReportDataDictionary extends Report {

    public ReportDataDictionary() {
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
     */
    public void createReportDataDictionary(Document document, UCMspec ucmspec, GRLspec grlspec) {

        try {
            // document scenario Groups
            if (!ucmspec.getScenarioGroups().isEmpty()) {
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                writeScenarioGroups(document, ucmspec);
            }

            // document variables contained in ucmspec
            if (!ucmspec.getVariables().isEmpty()) {
                document.add(Chunk.NEWLINE);
                writeVariables(document, ucmspec);
            }

            // document enumeration types and their content
            if (!ucmspec.getEnumerationTypes().isEmpty()) {
                document.add(Chunk.NEWLINE);
                writeEnumerationTypes(document, ucmspec);
            }

            // GRLspec report documentation
            if (grlspec != null) {

                // intElements with id
                // intElements fromLinks
                if (!grlspec.getIntElements().isEmpty()) {
                    document.add(Chunk.NEWLINE);
                    writeIntElements(document, grlspec);
                }

                // actors
                if (!grlspec.getActors().isEmpty()) {
                    document.add(Chunk.NEWLINE);
                    writeActors(document, grlspec);
                }

                // generate report documentation for URN definition
                // if (urndef != null) {

                // responsibilities
                // if (!urndef.getResponsibilities().isEmpty()) {
                // document.add(Chunk.NEWLINE);
                // writeResponsibilities(document, urndef);
            }

            // components
            // if (!urndef.getComponents().isEmpty()) {
            // document.add(Chunk.NEWLINE);
            // writeComponents(document, urndef);
            // }
            // }
            // }
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * creates the scenario groups in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param ucmspec
     *            the ucm specification used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeScenarioGroups(Document document, UCMspec ucmspec) {
        try {
            document.add(new Paragraph("UCM Scenario Groups documentation", header1Font));

            for (Iterator iter = ucmspec.getScenarioGroups().iterator(); iter.hasNext();) {
                ScenarioGroup group = (ScenarioGroup) iter.next();

                // generate report documentation for ScenarioGroup
                if (group != null) {
                    // create list for this scenario group
                    String sScenarioGroupName = new String(group.getName());
                    List list1 = new List(List.ORDERED);
                    list1.add(new ListItem(sScenarioGroupName + ":"));
                    document.add(list1);

                    if (group.getScenarios() != null) {
                        // scenario description
                        List list2 = new List(List.ORDERED);
                        for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                            // create a list for the scenario group
                            ScenarioDef scen = (ScenarioDef) iterator.next();
                            list2.setIndentationLeft(10);
                            list2.add(new ListItem(ReportUtils
                                    .getParagraphWithSeparator(document, scen.getName(), ": ", scen.getDescription(), descriptionFont)));
                        }
                        document.add(list2);
                    }
                }
            }
            // TODO get scenarios diagrams

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the variables in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param ucmspec
     *            the ucm specification used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeVariables(Document document, UCMspec ucmspec) {

        try {
            document.add(new Paragraph("Variables", header1Font));
            List list1 = new List(List.ORDERED);
            for (Iterator iter = ucmspec.getVariables().iterator(); iter.hasNext();) {

                // generate report documentation for variables
                Variable var = (Variable) iter.next();
                if (var != null) {
                    String varName = var.getName();
                    String varType = var.getType();
                    String varDescription = var.getDescription();
                    list1.setIndentationLeft(10);

                    if (var.getEnumerationType() != null) {
                        // the variable type is enumeration
                        String enumType = var.getEnumerationType().getName();
                        list1.add(new ListItem(ReportUtils.getParagraphWithSeparator(document, var.getName() + " (Enum " + enumType + ")", ": ", var
                                .getDescription(), descriptionFont)));
                    } else
                        list1.add(new ListItem(ReportUtils.getParagraphWithSeparator(document, var.getName() + " (" + varType + ")", ": ",
                                var.getDescription(), descriptionFont)));
                }
            }
            document.add(list1);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the enumeration types in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param ucmspec
     *            the ucm specification used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeEnumerationTypes(Document document, UCMspec ucmspec) {

        try {
            document.add(new Paragraph("Enumeration Types", header1Font));
            List list1 = new List(List.ORDERED);
            list1.setIndentationLeft(10);
            for (Iterator iter = ucmspec.getEnumerationTypes().iterator(); iter.hasNext();) {

                // generate report documentation for variables
                EnumerationType enumType = (EnumerationType) iter.next();
                String enumTypeName = enumType.getName();
                String enumString = new String("");

                if (enumType.getValues() != null) {
                    Chunk chunk1 = new Chunk(enumTypeName, descriptionFont);
                    String[] enumValues = enumType.getValues().split(",");
                    if (enumValues[0] != null) {
                        if (enumValues[0].length() == 0) {
                            enumString = enumString + enumTypeName;
                        } else {
                            enumString = enumString + enumTypeName + ": ";
                        }
                    } else {
                        enumString = enumString + enumTypeName;
                    }
                    for (int i = 0; i < enumValues.length; i++) {
                        enumString = enumString + enumValues[i];

                        // checks if last element
                        if (!(i == enumValues.length - 1)) {
                            enumString = enumString + ", ";
                        }
                    }

                }
                list1.add(enumString);
            }
            document.add(list1);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the intentional elements in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param grlspec
     *            the grl specification used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeIntElements(Document document, GRLspec grlspec) {

        try {
            document.add(new Paragraph("Intentional Elements", header1Font));
            List list1 = new List(List.ORDERED);
            list1.setIndentationLeft(10);
            for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {

                // generate report documentation for intentional elements (name, description, criticality, priority)
                IntentionalElement intElement = (IntentionalElement) iter.next();
                list1.add(new ListItem(ReportUtils
                        .getParagraphWithSeparator(document, intElement.getName(), ": ", intElement.getDescription(), descriptionFont)));
            }
            document.add(list1);
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the actors in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param grlspec
     *            the grl specification used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeActors(Document document, GRLspec grlspec) {

        try {
            document.add(new Paragraph("Actors", header1Font));
            List list1 = new List(List.ORDERED);
            list1.setIndentationLeft(10);

            for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {

                // generate report documentation for actors(name, description)
                Actor actor = (Actor) iter.next();
                list1.add(new ListItem(ReportUtils.getParagraphWithSeparator(document, actor.getName(), ": ", actor.getDescription(), descriptionFont)));
            }
            document.add(list1);
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the responsibilities in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param urndef
     *            the urn definition used to retrieve elements
     */

    @SuppressWarnings("unchecked")
    public void writeResponsibilities(Document document, URNdefinition urndef) {

        try {
            document.add(new Paragraph("Responsibilities", header1Font));
            List list1 = new List(List.ORDERED);
            list1.setIndentationLeft(10);

            for (Iterator iter = urndef.getResponsibilities().iterator(); iter.hasNext();) {
                Responsibility responsibility = (Responsibility) iter.next();
                list1.add(new ListItem(ReportUtils.getParagraphWithSeparator(document, responsibility.getName(), ": ", responsibility.getDescription(),
                        descriptionFont)));
            }
            document.add(list1);
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * creates the responsibilities in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param urndef
     *            the urn definition used to retrieve elements
     */
    @SuppressWarnings("unchecked")
    public void writeComponents(Document document, URNdefinition urndef) {

        try {
            document.add(new Paragraph("Components", header1Font));
            List list1 = new List(List.ORDERED);
            list1.setIndentationLeft(10);

            for (Iterator iter = urndef.getComponents().iterator(); iter.hasNext();) {
                Component component = (Component) iter.next();
                list1
                        .add(new ListItem(ReportUtils.getParagraphWithSeparator(document, component.getName(), ": ", component.getDescription(),
                                descriptionFont)));
            }
            document.add(list1);
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

}
