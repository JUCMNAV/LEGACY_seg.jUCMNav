package seg.jUCMNav.importexport.reports;

import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNlink;
import urncore.Component;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;

/**
 * implements the creation the the UCM elements description for each UCM figure
 * 
 * @author dessure
 * 
 */
public class UCMDiagramSection extends PDFReportDiagram {

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

    public void createUCMDiagramDescription(Document document, URNmodelElement element, IURNDiagram diagram) {

        try {
            // sections array contains a list of UCM Types we can report on
            // the order in the array will be the order in which they appear
            // in the report.
            String[] sections = { "RespRef", "Stub", "OrFork", "StartPoint", "EndPoint" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

            // variables used to skip headers for multiple items
            boolean firstOrFork = true;
            boolean firstResp = true;
            boolean firstStartPoint = true;
            boolean firstEndPoint = true;
            boolean firstStub = true;

            // variable needed to decide if we print or not this node type in report
            boolean showRespRefNode = ReportGeneratorPreferences.getUCMSHOWRESPONSIBILITY();
            boolean showStubNode = ReportGeneratorPreferences.getUCMSHOWSTUB();
            boolean showOrForkNode = ReportGeneratorPreferences.getUCMSHOWORFORK();
            boolean showStartPointNode = ReportGeneratorPreferences.getUCMSHOWSTARTPOINT();
            boolean showEndPointNode = ReportGeneratorPreferences.getUCMSHOWENDPOINT();
            boolean showDiagramDescription = ReportGeneratorPreferences.getUCMSHOWDESC();

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
            HashMap orForkSection = new HashMap(); // list of nodes for OrFork type
            HashMap stubSection = new HashMap(); // list of nodes for Stub type
            HashMap startPointSection = new HashMap(); // list of nodes for StartPoint type
            HashMap endPointSection = new HashMap(); // list of nodes for EndPoint type

            // Map Description title, underlined
            if (showDiagramDescription && element.getDescription() != null) {
                insertDiagramDescription(document, element);
            }

            insertDiagramMetadata(document, element);
            insertMapURNlinks(document, diagram);
            outputComponentURNlinks(document, diagram);

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
                } else if (showOrForkNode && currentNode instanceof OrFork) {
                    Integer hashKey = new Integer(orForkImplNo);
                    orForkSection.put(hashKey, currentNode);
                    orForkImplNo++;
                } else if (showStartPointNode && currentNode instanceof StartPoint) {
                    if (hasStartPointData((StartPoint) currentNode)) {
                        Integer hashKey = new Integer(startPointNo);
                        startPointSection.put(hashKey, currentNode);
                        startPointNo++;
                    }
                } else if (showEndPointNode && currentNode instanceof EndPoint) {
                    if (hasEndPointData((EndPoint) currentNode)) {
                        Integer hashKey = new Integer(endPointNo);
                        endPointSection.put(hashKey, currentNode);
                        endPointNo++;
                    }
                }

            }
            // print sections
            if (respRefSection.size() > 0) {
                for (int i4 = 1; i4 <= respRefSection.size(); i4++) {

                    if (firstResp == true) {
                        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.Responsibilities")); //$NON-NLS-1$
                        firstResp = false;
                    }

                    Integer hashKey = new Integer(i4);
                    RespRef resp = (RespRef) respRefSection.get(hashKey);
                    insertResponsibility(document, resp);
                    document.add(Chunk.NEWLINE);
                }
            }

            if (stubSection.size() > 0) {
                for (int i4 = 1; i4 <= stubSection.size(); i4++) {

                    if (firstStub == true) {
                        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.Stubs")); //$NON-NLS-1$
                        firstStub = false;
                    }

                    Integer hashKey = new Integer(i4);
                    Stub stub = (Stub) stubSection.get(hashKey);
                    insertStub(document, stub);
                    document.add(Chunk.NEWLINE);
                }
            }

            if (orForkSection.size() > 0) {
                for (int i4 = 1; i4 <= orForkSection.size(); i4++) {
                    if (firstOrFork == true) {
                        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.OrFork")); //$NON-NLS-1$
                        firstOrFork = false;
                    }

                    Integer hashKey = new Integer(i4);
                    OrFork orFork = (OrFork) orForkSection.get(hashKey);
                    insertOrForkProbability(document, orFork);
                    document.add(Chunk.NEWLINE);
                }
            }

            if (startPointSection.size() > 0) {
                for (int i4 = 1; i4 <= startPointSection.size(); i4++) {
                    if (firstStartPoint == true) {
                        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.StartPoint")); //$NON-NLS-1$
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
                        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.EndPoint")); //$NON-NLS-1$
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

    private void insertMapURNlinks(Document document, IURNDiagram diagram) {
        if (!(diagram instanceof UCMmap))
            return;

        EList urnLinks = ((UCMmap) diagram).getToLinks();

        if (urnLinks.isEmpty())
            return;

        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.MapURNLinks")); //$NON-NLS-1$

        for (Iterator iter = urnLinks.iterator(); iter.hasNext();) {
            URNlink link = (URNlink) iter.next();
            if (link.getFromElem() instanceof IntentionalElement) {
                IntentionalElement ie = (IntentionalElement) link.getFromElem();
                ReportUtils.writeLineWithSeparator(document, ie.getName() + " (" + ie.getType().getName() + ")", null, null, descriptionFont, false); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

    private void outputComponentURNlinks(Document document, IURNDiagram diagram) {
        ComponentRef currentComponentRef;
        Component currentComponent;
        boolean hasURNlinks = false;

        if (!ReportGeneratorPreferences.getShowURNLinks())
            return;

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext() && !hasURNlinks;) {
            currentComponentRef = (ComponentRef) iter.next();
            currentComponent = (Component) currentComponentRef.getContDef();
            if (!currentComponent.getFromLinks().isEmpty() || !currentComponent.getToLinks().isEmpty())
                hasURNlinks = true;
        }

        if (!hasURNlinks)
            return;

        insertDiagramSectionHeader(document, tableParams, Messages.getString("UCMDiagramSection.CompURNLinks")); //$NON-NLS-1$

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
            currentComponentRef = (ComponentRef) iter.next();
            currentComponent = (Component) currentComponentRef.getContDef();

            for (Iterator iter1 = currentComponent.getFromLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getToElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                ReportUtils.writeLineWithSeparator(document, currentComponent.getName(), " ==> (", elementType + ") " + link.getToElem().getName(), //$NON-NLS-1$ //$NON-NLS-2$
                        descriptionFont, true);
                if (ReportUtils.notEmpty(link.getType()))
                    ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.LinkType"), ": ", link.getType(), descriptionFont, true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

                insertMetadata(document, link.getMetadata());
            }

            for (Iterator iter1 = currentComponent.getToLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getFromElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                ReportUtils.writeLineWithSeparator(document, currentComponent.getName(), " <== (", elementType + ") " + link.getFromElem().getName(), //$NON-NLS-1$ //$NON-NLS-2$
                        descriptionFont, true);
                if (ReportUtils.notEmpty(link.getType()))
                    ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.LinkType2"), ": ", link.getType(), descriptionFont, true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

                insertMetadata(document, link.getMetadata());
            }
        }
    }

    private void insertOrForkProbability(Document document, OrFork orFork) {
        try {

            for (Iterator iter = orFork.getSucc().iterator(); iter.hasNext();) {
                // <BM> <2008-02-21> NodeConnection class - 'probability' attribute
                NodeConnection element = (NodeConnection) iter.next();

                // <BM> <2008-02-23> Extract OrFork attributes(label, expression)
                Condition orCondition = element.getCondition(); // DA: Could be null... needs to be checked

                // <BM> <2008-02-23> Extract probability attribute
                double probability = element.getProbability();

                // <BM> <2008-02-24> check if the label and expression strings are empty
                if (orCondition != null) {
                    if (ReportUtils.notEmpty(orCondition.getLabel())) {

                        document.add(new Chunk("[" + orCondition.getLabel() + "] ==> ", descriptionFont)); // <BM> <2008-02-23> Search for label result //$NON-NLS-1$ //$NON-NLS-2$

                        if (ReportUtils.notEmpty(orCondition.getExpression())) {

                            document.add(new Chunk(orCondition.getExpression(), descriptionFont)); // <BM> <2008-02-23> Search for expression result

                        }
                    }
                }
                // <BM> <2008-02-24> no need to check probability for empty since it always has a default value of 1.0
                document.add(new Chunk(" (" + Messages.getString("UCMDiagramSection.Probability") + ": " + probability + ")", descriptionFont));// <BM> <2008-02-21> Fixed the way probability is output //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                document.add(Chunk.NEWLINE);
            }

            insertMetadata(document, orFork.getMetadata());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertResponsibility(Document document, RespRef respRef) {
        Responsibility responsibility = respRef.getRespDef();

        try {
            ReportUtils.writeLineWithSeparator(document, respRef.getRespDef().getName(), ": ", respRef.getRespDef().getDescription(), descriptionFont, true); //$NON-NLS-1$
            String expression = respRef.getRespDef().getExpression();
            if (ReportUtils.notEmpty(expression)) {
                String[] expression_lines = expression.split("\n"); //$NON-NLS-1$
                if (expression_lines.length == 1) {
                    ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.Expression"), ": ", expression_lines[0], descriptionFont, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                } else {
                    ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.Expression2") + "\n", null, null, descriptionFont, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    for (int i = 0; i < expression_lines.length; i++)
                        ReportUtils.writeLineWithSeparator(document, "          " + expression_lines[i], null, null, descriptionFont, false); //$NON-NLS-1$
                }
                document.add(Chunk.NEWLINE);
            }

            insertMetadata(document, respRef.getRespDef().getMetadata());

            if (!responsibility.getToLinks().isEmpty()) {

                for (Iterator iter1 = responsibility.getToLinks().iterator(); iter1.hasNext();) {

                    URNlink link = (URNlink) iter1.next();
                    if (link.getFromElem() instanceof IntentionalElement) {
                        IntentionalElement ie = (IntentionalElement) link.getFromElem();
                        ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.URNLink"), ": ", ie.getName() + "(" + ie.getType().getName() + ")", descriptionFont, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                                false);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertStartPoint(Document document, StartPoint start) {
        try {
            ReportUtils.writeLineWithSeparator(document, start.getName(), ": ", start.getDescription(), descriptionFont, true); //$NON-NLS-1$
            Condition pc = start.getPrecondition();
            if (ReportUtils.notEmpty(pc.getLabel())) {
                ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.Precondition") + " [" + pc.getLabel(), "] ==> ", notNull(pc.getExpression()), descriptionFont, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                        true);
            }
            insertMetadata(document, start.getMetadata());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hasStartPointData(StartPoint start) {
        if (ReportUtils.notEmpty(start.getDescription()) || ReportUtils.notEmpty(start.getPrecondition().getLabel()) || !start.getMetadata().isEmpty()) {
            return true;
        } else
            return false;
    }

    private void insertEndPoint(Document document, EndPoint end) {
        try {
            ReportUtils.writeLineWithSeparator(document, end.getName(), ": ", end.getDescription(), descriptionFont, true); //$NON-NLS-1$
            Condition pc = end.getPostcondition();
            if (ReportUtils.notEmpty(pc.getLabel())) {
                ReportUtils.writeLineWithSeparator(document, "     " + Messages.getString("UCMDiagramSection.Postcondition") + " [" + pc.getLabel(), "]  ==> ", notNull(pc.getExpression()), descriptionFont, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                        true);
            }
            insertMetadata(document, end.getMetadata());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hasEndPointData(EndPoint end) {
        if (ReportUtils.notEmpty(end.getDescription()) || ReportUtils.notEmpty(end.getPostcondition().getLabel()) || !end.getMetadata().isEmpty()) {
            return true;
        } else
            return false;
    }

    private void insertSuccessorDescription(Document document, PathNode node) {
        try {
            int i = 1;
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                NodeConnection element = (NodeConnection) iter.next();

                // find source associated with predecessor
                IURNNode urnNode = element.getTarget();
                UCMmodelElement ucmElement = (UCMmodelElement) urnNode;

                document.add(new Chunk(Messages.getString("UCMDiagramSection.Output") + " " + i + ": "));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                document.add(new Chunk(ucmElement.getName()));
                document.add(Chunk.NEWLINE);

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertStub(Document document, Stub stub) {
        try {
            // name and description
            String stubType;

            if (stub.isDynamic()) {
                stubType = Messages.getString("UCMDiagramSection.DynamicStub") + " - "; //$NON-NLS-2$ //$NON-NLS-1$
            } else {
                stubType = Messages.getString("UCMDiagramSection.StaticStub") + " - "; //$NON-NLS-2$ //$NON-NLS-1$
            }
            ReportUtils.writeLineWithSeparator(document, stubType + stub.getName(), ": ", stub.getDescription(), descriptionFont, true); //$NON-NLS-1$

            insertMetadata(document, stub.getMetadata());

            // Plugin Bindings
            for (Iterator bindings = stub.getBindings().iterator(); bindings.hasNext();) {

                PluginBinding binding = (PluginBinding) bindings.next();

                // Plugin map
                Paragraph pluginMapPar = new Paragraph();
                pluginMapPar.setIndentationLeft(10);
                document.add(Chunk.NEWLINE);
                Chunk pluginMap1 = new Chunk(Messages.getString("UCMDiagramSection.PluginMap") + " - " + binding.getPlugin().getName(), pluginMapTitleFont); //$NON-NLS-2$ //$NON-NLS-1$
                pluginMapPar.add(pluginMap1);
                document.add(pluginMapPar);

                // Static Stub input bindings
                if (binding.getIn().iterator().hasNext()) {
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(20);
                    par.add(new Chunk(Messages.getString("UCMDiagramSection.InputBinding") + ":", bindingsHeaderFont)); //$NON-NLS-2$ //$NON-NLS-1$
                    document.add(par);
                }

                for (Iterator ins = binding.getIn().iterator(); ins.hasNext();) {
                    InBinding inBinding = (InBinding) ins.next();

                    int stubEntryIndex = 0;
                    if (stub.getSucc().indexOf(inBinding.getStubEntry()) == -1) {
                        stubEntryIndex = 1;
                    } else {
                        stubEntryIndex = stub.getSucc().indexOf(inBinding.getStubEntry()) + 1;
                    }
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(30);
                    String inItemDescription = "IN " + stubEntryIndex + " <-> " + inBinding.getStartPoint().getName(); //$NON-NLS-1$ //$NON-NLS-2$
                    par.add(new Chunk(inItemDescription, bindingsFont));
                    document.add(par);
                }

                // Static Stub output bindings
                if (binding.getOut().iterator().hasNext()) {
                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(20);
                    par.add(new Chunk(Messages.getString("UCMDiagramSection.OutputBinding") + ":", bindingsHeaderFont)); //$NON-NLS-2$ //$NON-NLS-1$
                    document.add(par);

                }
                for (Iterator outs = binding.getOut().iterator(); outs.hasNext();) {
                    OutBinding outBinding = (OutBinding) outs.next();

                    int stubExitIndex = 0;
                    stubExitIndex = stub.getSucc().indexOf(outBinding.getStubExit()) + 1;

                    Paragraph par = new Paragraph();
                    par.setIndentationLeft(30);
                    String inItemDescription = "OUT " + stubExitIndex + " <-> " + outBinding.getEndPoint().getName(); //$NON-NLS-1$ //$NON-NLS-2$
                    par.add(new Chunk(inItemDescription, bindingsFont));
                    document.add(par);
                }

                // Additional plugin binding information
                Paragraph addlInfo = new Paragraph();
                addlInfo.setIndentationLeft(20);
                addlInfo.add(new Chunk(Messages.getString("UCMDiagramSection.Precondition2") + ":", bindingsHeaderFont)); //$NON-NLS-2$ //$NON-NLS-1$
                addlInfo.add(Chunk.NEWLINE);

                if (ReportUtils.notEmpty(binding.getPrecondition().getLabel())) {
                    Chunk details = new Chunk("   " + Messages.getString("UCMDiagramSection.Label") + ": " + binding.getPrecondition().getLabel(), pluginMapTitleFont); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                if (ReportUtils.notEmpty(binding.getPrecondition().getExpression())) {
                    Chunk details = new Chunk("   " + Messages.getString("UCMDiagramSection.Expression3") + ": " + binding.getPrecondition().getExpression(), pluginMapTitleFont); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                if (ReportUtils.notEmpty(binding.getPrecondition().getDescription())) {
                    Chunk details = new Chunk("   " + Messages.getString("UCMDiagramSection.Description") + ": " + binding.getPrecondition().getDescription(), pluginMapTitleFont); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    addlInfo.add(details);
                    addlInfo.add(Chunk.NEWLINE);
                }
                Chunk details = new Chunk(Messages.getString("UCMDiagramSection.Transaction") +": " + binding.isTransaction(), pluginMapTitleFont); //$NON-NLS-2$ //$NON-NLS-1$
                addlInfo.add(details);
                addlInfo.add(Chunk.NEWLINE);

                details = new Chunk(Messages.getString("UCMDiagramSection.Probability2") + ": " + binding.getProbability() + "", pluginMapTitleFont);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                addlInfo.add(details);

                document.add(addlInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
