package seg.jUCMNav.importexport.reports;

import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.UCMReportPreferences;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.impl.OrForkImpl;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

/**
 * implements the creation the the UCM elements description for each
 * UCM figure
 * 
 * @author dessure
 * 
 */
public class UCMDiagramSection extends PDFReportDiagram {

    int[] tableParams = { 1, 2, 0, 100 };

    public UCMDiagramSection() {

    }

    /**
     * creates the data dictionary section in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param tableParams
     *            the parameters used for the creation of table (nbOfColumns, padding, spacing, width)
     * @param description
     *            the description of the ucm diagram
     */
    private void insertUCMDiagramSectionHeader(Document document, int[] tableParams, String description) {
        try {

            Table table = ReportUtils.createTable(tableParams[0], tableParams[1], tableParams[2], tableParams[3]);

            Chunk chunk = new Chunk(description, descriptionBoldFont);
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

    
    public void createUCMDiagramDescription(Document document, URNmodelElement element, IURNDiagram diagram) {

        try {
            // sections array contains a list of UCM Types we can report on
            // the order in the array will be the order in which they appear
            // in the report.
            String[] sections = { "RespRef", "Stub", "OrForkImpl", "StartPoint", "EndPoint" };

            // variables used to skip headers for multiple items
            boolean firstOrFork = true;
            boolean firstResp = true;
            boolean firstStartPoint = true;
            boolean firstEndPoint = true;
            boolean firstStub = true;

            // variable needed to decide if we print or not this node type in report
            boolean showRespRefNode = UCMReportPreferences.getUCMSHOWRESPONSIBILITY();
            boolean showStubNode = UCMReportPreferences.getUCMSHOWSTUB();
            boolean showOrForkImplNode = UCMReportPreferences.getUCMSHOWORFORK();
            boolean showStartPointNode = UCMReportPreferences.getUCMSHOWSTARTPOINT();
            boolean showEndPointNode = UCMReportPreferences.getUCMSHOWENDPOINT();

            boolean sectionAlreadyChecked = false; // to see if we already checked, to print or not this section type

            int respRefNo = 1;
            int orForkImplNo = 1;
            int stubNo = 1;
            int startPointNo = 1;
            int endPointNo = 1;

            String nodeType = new String();

            HashMap diagramNodes = new HashMap(); // all of the nodes contained in the diagram
            HashMap sectionsMap = new HashMap(); // list of sections (sectionNo, sectionType)
            HashMap respRefSection = new HashMap(); // list of nodes for RespRef
            HashMap orForkImplSection = new HashMap(); // list of nodes for OrFork type
            HashMap stubSection = new HashMap(); // list of nodes for Stub type
            HashMap startPointSection = new HashMap(); // list of nodes for StartPoint type
            HashMap endPointSection = new HashMap(); // list of nodes for EndPoint type

            // Map Description title, underlined
            if (element.getDescription() != null) {
                insertDiagramDescription(document, element);
            }

            // sections contains the list of node types to report on

            // get all nodes for this type from the diagramNodes
            for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {

                PathNode currentNode = (PathNode) iter.next();

                if (showRespRefNode && currentNode instanceof RespRef) {
                    Integer hashKey = new Integer(respRefNo);
                    respRefSection.put(hashKey, currentNode);
                    respRefNo++;
                } else if (showStubNode && currentNode instanceof Stub) {
                    Integer hashKey = new Integer(stubNo);
                    stubSection.put(hashKey, currentNode);
                    stubNo++;
                } else if (showOrForkImplNode && currentNode instanceof OrForkImpl) {
                    Integer hashKey = new Integer(orForkImplNo);
                    orForkImplSection.put(hashKey, currentNode);
                    orForkImplNo++;
                } else if (showStartPointNode && currentNode instanceof StartPoint) {
                    Integer hashKey = new Integer(startPointNo);
                    startPointSection.put(hashKey, currentNode);
                    startPointNo++;
                } else if (showEndPointNode && currentNode instanceof EndPoint) {
                    Integer hashKey = new Integer(endPointNo);
                    endPointSection.put(hashKey, currentNode);
                    endPointNo++;
                }

            }
            // print sections
            if (stubSection.size() > 0) {
                for (int i4 = 1; i4 <= stubSection.size(); i4++) {

                    if (firstStub == true) {
                        insertStubHeader(document);
                        firstStub = false;
                    }

                    Integer hashKey = new Integer(i4);
                    Stub stub = (Stub) stubSection.get(hashKey);
                    insertStub(document, stub);
                    document.add(Chunk.NEWLINE);

                }
            }

            if (orForkImplSection.size() > 0) {

                for (int i4 = 1; i4 <= orForkImplSection.size(); i4++) {
                    if (firstOrFork == true) {
                        insertOrForkHeader(document);
                        firstOrFork = false;
                    }

                    Integer hashKey = new Integer(i4);
                    OrForkImpl orFork = (OrForkImpl) orForkImplSection.get(hashKey);
                    insertOrForkProbability(document, orFork);
                    document.add(Chunk.NEWLINE);
                }
            }

            if (startPointSection.size() > 0) {
                for (int i4 = 1; i4 <= startPointSection.size(); i4++) {
                    if (firstStartPoint == true) {
                        insertStartPoinHeader(document);
                        firstStartPoint = false;
                    }

                    Integer hashKey = new Integer(i4);
                    StartPoint startPoint = (StartPoint) startPointSection.get(hashKey);
                    insertStartPoint(document, startPoint);
                    document.add(Chunk.NEWLINE);
                }
            }

            if (endPointSection.size() > 0) {
                for (int i4 = 1; i4 <= endPointSection.size(); i4++) {
                    if (firstEndPoint == true) {
                        insertEndPoinHeader(document);
                        firstEndPoint = false;
                    }

                    Integer hashKey = new Integer(i4);
                    EndPoint endPoint = (EndPoint) endPointSection.get(hashKey);
                    insertEndPoint(document, endPoint);
                    document.add(Chunk.NEWLINE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insertOrForkHeader(Document document) {

        String description = "Or Fork Description";
        insertUCMDiagramSectionHeader(document, tableParams, description);
    }

    private void insertRespHeader(Document document) {
        String description = "Responsibilities";
        insertUCMDiagramSectionHeader(document, tableParams, description);
    }

    private void insertStartPoinHeader(Document document) {
        String description = "Start Point ";
        insertUCMDiagramSectionHeader(document, tableParams, description);

    }

    private void insertEndPoinHeader(Document document) {
        String description = "End Point";
        insertUCMDiagramSectionHeader(document, tableParams, description);

    }

    private void insertStubHeader(Document document) {
        String description = "Stub";
        insertUCMDiagramSectionHeader(document, tableParams, description);

    }

    
    private void insertOrForkProbability(Document document, PathNode node) {
        try {
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                NodeConnection element = (NodeConnection) iter.next();

                // find source associated with predecessor
                IURNNode urnNode = element.getTarget();
                double probability = element.getProbability();

                UCMmodelElement ucmElement = (UCMmodelElement) urnNode;

                document.add(new Chunk("Output " + urnNode.getLabel() + ": "));
                document.add(new Chunk(ucmElement.getName()));
                document.add(new Chunk(" probability -> " + probability));
                document.add(Chunk.NEWLINE);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertResponsibility(Document document, RespRef node) {
        try {

            ReportUtils.writeLineWithSeparator(document, node.getRespDef().getName(), ": ", node.getRespDef().getDescription(), descriptionFont, true);
            String expression = node.getRespDef().getExpression();
            if (expression != null) {
                ReportUtils.writeLineWithSeparator(document, "Expression", ": ", expression, descriptionFont, true);
            }

            document.add(Chunk.NEWLINE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertStartPoint(Document document, StartPoint node) {
        try {

            ReportUtils.writeLineWithSeparator(document, node.getName(), ": ", node.getDescription(), descriptionFont, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEndPoint(Document document, EndPoint node) {
        try {

            ReportUtils.writeLineWithSeparator(document, node.getName(), ": ", node.getDescription(), descriptionFont, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void insertSuccessorDescription(Document document, PathNode node) {
        try {
            int i = 1;
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                NodeConnection element = (NodeConnection) iter.next();

                // find source associated with predecessor
                IURNNode urnNode = element.getTarget();
                UCMmodelElement ucmElement = (UCMmodelElement) urnNode;

                document.add(new Chunk("Output " + i + ": "));
                document.add(new Chunk(ucmElement.getName()));
                document.add(Chunk.NEWLINE);

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void insertStub(Document document, Stub node) {
        try {
            // name and description
            String stubType = new String();

            if (node.isDynamic()) {
                stubType = "Dynamic Stub - ";
                ReportUtils.writeLineWithSeparator(document, stubType + node.getName(), ": ", node.getDescription(), descriptionFont, true);

            } else {
                stubType = "Static Stub - ";
                ReportUtils.writeLineWithSeparator(document, stubType + node.getName(), ": ", node.getDescription(), descriptionFont, true);
            }
            // Plugin Bindings
            for (Iterator bindings = node.getBindings().iterator(); bindings.hasNext();) {

                PluginBinding element = (PluginBinding) bindings.next();

                // Plugin map
                Paragraph pluginMapPar = new Paragraph();
                pluginMapPar.setIndentationLeft(10);
                document.add(Chunk.NEWLINE);
                Chunk pluginMap1 = new Chunk("Plugin Map -" + element.getPlugin().getName(), pluginMapTitleFont);
                pluginMapPar.add(pluginMap1);
                document.add(pluginMapPar);

                // Static Stub input bindings
                if (element.getIn().iterator().hasNext()) {
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(20);
                    par.add(new Chunk("Input Bindings:", bindingsHeaderFont));
                    document.add(par);
                }

                for (Iterator ins = element.getIn().iterator(); ins.hasNext();) {
                    InBinding inBinding = (InBinding) ins.next();

                    int stubEntryIndex = 0;
                    if (node.getSucc().indexOf(inBinding.getStubEntry()) == -1) {
                        stubEntryIndex = 1;
                    } else {
                        stubEntryIndex = node.getSucc().indexOf(inBinding.getStubEntry()) + 1;
                    }
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(30);
                    String inItemDescription = "IN " + stubEntryIndex + " <-> " + inBinding.getStartPoint().getName();
                    par.add(new Chunk(inItemDescription, bindingsFont));
                    document.add(par);
                }

                // Static Stub output bindings
                if (element.getOut().iterator().hasNext()) {
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(20);
                    par.add(new Chunk("Output Bindings:", bindingsHeaderFont));
                    document.add(par);

                }
                for (Iterator outs = element.getOut().iterator(); outs.hasNext();) {
                    OutBinding outBinding = (OutBinding) outs.next();

                    int stubExitIndex = 0;
                    stubExitIndex = node.getSucc().indexOf(outBinding.getStubExit()) + 1;

                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(30);
                    String inItemDescription = "OUT " + stubExitIndex + " <-> " + outBinding.getEndPoint().getName();
                    par.add(new Chunk(inItemDescription, bindingsFont));
                    document.add(par);
                }

                // Additional plugin binding information
                Paragraph addlInfo = new Paragraph();
                addlInfo.setIndentationLeft(10);

                if (element.getPrecondition().getLabel() != null) {
                    Chunk details = new Chunk("Precondition: " + element.getPrecondition().getLabel(), pluginMapTitleFont);
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                if (element.getPrecondition().getExpression() != null) {
                    Chunk details = new Chunk("Expression: " + element.getPrecondition().getExpression(), pluginMapTitleFont);
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                if (element.getPrecondition().getDescription() != null) {
                    Chunk details = new Chunk("Description: " + element.getPrecondition().getDescription(), pluginMapTitleFont);
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                if (element.isTransaction()) {
                    Chunk details = new Chunk("Transaction: true", pluginMapTitleFont);
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                } else {
                    Chunk details = new Chunk("Transaction: false", pluginMapTitleFont);
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                Chunk details = new Chunk("Probability: " + element.getProbability() + "", pluginMapTitleFont);
                addlInfo.add(details);

                document.add(addlInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
