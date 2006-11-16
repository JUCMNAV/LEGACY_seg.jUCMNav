package seg.jUCMNav.importexport.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.EList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.UCMmap;
import ucm.map.impl.PluginBindingImpl;
import ucm.map.impl.StubImpl;
import urncore.IURNDiagram;

/**
 * The XML parser used to parse the XML menu file and add new menus to the file
 * 
 * @author pchen
 * 
 */
public class HTMLMenuParser {
    private static HTMLMenuParser parser = null;

    private String xmlFullPath = "";
    private Document xmlDocument = null;
    private ArrayList selectedMaps = new ArrayList();

    private static String xmlFileName = "tree.xml";
    private static String TREE = "tree";
    private static String BRANCH = "branch";
    private static String BRANCH_ID = "id";
    private static String BRANCH_LINK = "branchlink";
    private static String BRANCH_TEXT = "branchText";

    private static String LEAF = "leaf";
    private static String LEAF_TEXT = "leafText";
    private static String LINK = "link";
    private static String BASE_X = "baseX";
    private static String BASE_Y = "baseY";

    /**
     * Initialize HTMLMenuParser
     * 
     * @param _xmlPath
     */
    private HTMLMenuParser(String _xmlPath) {
        this.xmlFullPath = _xmlPath + xmlFileName;
    }

    public static HTMLMenuParser getParser(String _xmlPath) {
        if (parser == null) {
            parser = new HTMLMenuParser(_xmlPath);
        } else {
            parser.xmlFullPath = _xmlPath + xmlFileName;
        }

        return parser;
    }

    /**
     * Reset the XML document.
     * 
     */
    public void resetDocument() {
        xmlDocument = null;
        selectedMaps = new ArrayList();
    }

    /**
     * Parse XML menu file
     * 
     */
    public void parseMenu() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // factory.setValidating(false);

            // Turn on validation, and turn off namespaces
            factory.setValidating(false);
            factory.setNamespaceAware(false);

            // Create the menu xml file
            File xmlFile = new File(xmlFullPath);

            String srcFile = "htmltemplates/" + xmlFileName;
            String desFile = xmlFullPath;

            try {
                copy(srcFile, desFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Parse the menu xml file
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(new InputSource(new FileReader(xmlFile)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new menu into the XML menu file
     * 
     * @param htmlMenuItem
     */
    public void addMenu(HTMLMenuItem htmlMenuItem) {
        if (xmlDocument == null) {
            parseMenu();
        }

        NodeList branchList = xmlDocument.getDocumentElement().getChildNodes();
        int len = branchList.getLength();
        Element branch = null;
        for (int i = 0; i < len; i++) {
            Node childNode = branchList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                branch = (Element) childNode;
                
                String bid = branch.getAttribute(BRANCH_ID);
                if (bid.equals(htmlMenuItem.getType())) {
                    break;
                }
            }
        }

        if (branch != null) {
            if (htmlMenuItem.getType().equals(HTMLMenuItem.TYPE_GRL)) {
                Element leaf = xmlDocument.createElement(LEAF);

                Element leafText = xmlDocument.createElement(LEAF_TEXT);
                leafText.setTextContent(htmlMenuItem.getLeafText());

                Element link = xmlDocument.createElement(LINK);
                link.setTextContent(htmlMenuItem.getLink());

                Element baseX = xmlDocument.createElement(BASE_X);
                baseX.setTextContent(String.valueOf(htmlMenuItem.getBaseX()));

                Element baseY = xmlDocument.createElement(BASE_Y);
                baseY.setTextContent(String.valueOf(htmlMenuItem.getBaseY()));

                leaf.appendChild(leafText);
                leaf.appendChild(link);
                leaf.appendChild(baseX);
                leaf.appendChild(baseY);
                branch.appendChild(leaf);
            } else if (htmlMenuItem.getType().equals(HTMLMenuItem.TYPE_UCM)) {
                addUCMMenu(htmlMenuItem, branch);
            }
        }
    }

    /**
     * Add new ucm menu to the given node's children
     * 
     * @param htmlMenuItem
     * @param node
     */
    private void addUCMMenu(HTMLMenuItem htmlMenuItem, Element parent) {
        NodeList branchList = parent.getElementsByTagName(BRANCH);
        NodeList leafList = parent.getElementsByTagName(LEAF);

        boolean isExisting = false;
        int len = branchList.getLength();

        for (int i = 0; i < len; i++) {
            Element element = (Element) branchList.item(i);
            String branchLink = element.getAttribute(BRANCH_LINK);

            if (htmlMenuItem.getLink().equals(branchLink)) {
                element.setAttribute(BASE_X, String.valueOf(htmlMenuItem.getBaseX()));
                element.setAttribute(BASE_Y, String.valueOf(htmlMenuItem.getBaseY()));
                isExisting = true;
                continue;
            }
        }

        if (!isExisting) {
            len = leafList.getLength();
            for (int i = 0; i < len; i++) {
                Element element = (Element) leafList.item(i);
                String link = element.getElementsByTagName(LINK).item(0).getTextContent();

                if (htmlMenuItem.getLink().equals(link)) {
                    element.getElementsByTagName(BASE_X).item(0).setTextContent(String.valueOf(htmlMenuItem.getBaseX()));
                    element.getElementsByTagName(BASE_Y).item(0).setTextContent(String.valueOf(htmlMenuItem.getBaseY()));
                    isExisting = true;
                    continue;
                }
            }
        }

        if (!isExisting) {
            IURNDiagram diagram = htmlMenuItem.getDiagram();
            Iterator nodeIter = null;
            ArrayList childDiagramList = new ArrayList();

            EList nodes = diagram.getNodes();
            if (!nodes.isEmpty()) {
                nodeIter = nodes.iterator();

                while (nodeIter.hasNext()) {
                    Object obj = nodeIter.next();

                    if (obj instanceof StubImpl) {
                        StubImpl stub = (StubImpl) obj;

                        EList bindings = stub.getBindings();
                        Iterator bindIter = null;
                        if (!bindings.isEmpty()) {
                            bindIter = bindings.iterator();

                            int j = 0;
                            int shift = 18;
                            while (bindIter.hasNext()) {
                                obj = bindIter.next();
                                if (obj instanceof PluginBindingImpl) {
                                    PluginBindingImpl pluginBinding = (PluginBindingImpl) obj;
                                    UCMmap childMap = pluginBinding.getPlugin();

                                    childDiagramList.add(childMap);
                                }
                            }
                            // end of while
                        }
                    }
                }
                // end of while
            }

            if (childDiagramList.size() > 0) {
                Element branch = xmlDocument.createElement(BRANCH);
                branch.setAttribute(BRANCH_ID, htmlMenuItem.getLeafText());
                branch.setAttribute(BRANCH_LINK, htmlMenuItem.getLink());
                branch.setAttribute(BASE_X, String.valueOf(htmlMenuItem.getBaseX()));
                branch.setAttribute(BASE_Y, String.valueOf(htmlMenuItem.getBaseY()));

                Element branchText = xmlDocument.createElement(BRANCH_TEXT);
                branchText.setTextContent(htmlMenuItem.getLeafText());

                branch.appendChild(branchText);
                parent.appendChild(branch);

                if (parent.getAttribute(BRANCH_ID).equals(HTMLMenuItem.TYPE_UCM)) {
                    selectedMaps.add(branch);
                } else {
                    for (int n = 0; n < selectedMaps.size(); n++) {
                        Element selectedNode = (Element) selectedMaps.get(n);
                        if (selectedNode.getTagName().equals(BRANCH)) {
                            if (branch.getAttribute(BRANCH_LINK).equals(selectedNode.getAttribute(BRANCH_LINK))) {
                                branch.setAttribute(BASE_X, selectedNode.getAttribute(BASE_X));
                                branch.setAttribute(BASE_Y, selectedNode.getAttribute(BASE_Y));
                                selectedNode.getParentNode().removeChild(selectedNode);
                                break;
                            }
                        } else if (selectedNode.getTagName().equals(LEAF)) {
                            if (branch.getAttribute(BRANCH_LINK).equals(selectedNode.getElementsByTagName(LINK).item(0).getTextContent())) {
                                branch.setAttribute(BASE_X, selectedNode.getElementsByTagName(BASE_X).item(0).getTextContent());
                                branch.setAttribute(BASE_Y, selectedNode.getElementsByTagName(BASE_Y).item(0).getTextContent());
                                selectedNode.getParentNode().removeChild(selectedNode);
                                break;
                            }
                        }
                    }
                }

                for (int i = 0; i < childDiagramList.size(); i++) {
                    IURNDiagram childDiagram = (IURNDiagram) childDiagramList.get(i);

                    // prepare the html menu item
                    htmlMenuItem.reset();

                    String childDiagramName = ExportWizard.getDiagramName(childDiagram);
                    htmlMenuItem.setDiagramName(childDiagramName);
                    htmlMenuItem.setType(HTMLMenuItem.TYPE_UCM);
                    htmlMenuItem.setLeafText(childDiagramName.substring(childDiagramName.lastIndexOf("-") + 1));
                    htmlMenuItem.setLink(childDiagramName + ".html");
                    htmlMenuItem.setBaseX(-1);
                    htmlMenuItem.setBaseY(-1);
                    htmlMenuItem.setDiagram(childDiagram);

                    addUCMMenu(htmlMenuItem, branch);
                }
            } else {
                Element leaf = xmlDocument.createElement(LEAF);

                Element leafText = xmlDocument.createElement(LEAF_TEXT);
                leafText.setTextContent(htmlMenuItem.getLeafText());

                Element link = xmlDocument.createElement(LINK);
                link.setTextContent(htmlMenuItem.getLink());

                Element baseX = xmlDocument.createElement(BASE_X);
                baseX.setTextContent(String.valueOf(htmlMenuItem.getBaseX()));

                Element baseY = xmlDocument.createElement(BASE_Y);
                baseY.setTextContent(String.valueOf(htmlMenuItem.getBaseY()));

                leaf.appendChild(leafText);
                leaf.appendChild(link);
                leaf.appendChild(baseX);
                leaf.appendChild(baseY);
                parent.appendChild(leaf);

                if (parent.getAttribute(BRANCH_ID).equals(HTMLMenuItem.TYPE_UCM)) {
                    selectedMaps.add(leaf);
                } else {
                    for (int n = 0; n < selectedMaps.size(); n++) {
                        Element selectedNode = (Element) selectedMaps.get(n);
                        if (selectedNode.getTagName().equals(BRANCH)) {
                            if (leaf.getElementsByTagName(LINK).item(0).getTextContent().equals(selectedNode.getAttribute(BRANCH_LINK))) {
                                leaf.getElementsByTagName(BASE_X).item(0).setTextContent(selectedNode.getAttribute(BASE_X));
                                leaf.getElementsByTagName(BASE_Y).item(0).setTextContent(selectedNode.getAttribute(BASE_Y));
                                selectedNode.getParentNode().removeChild(selectedNode);
                                break;
                            }
                        } else if (selectedNode.getTagName().equals(LEAF)) {
                            if (leaf.getElementsByTagName(LINK).item(0).getTextContent().equals(
                                    selectedNode.getElementsByTagName(LINK).item(0).getTextContent())) {
                                leaf.getElementsByTagName(BASE_X).item(0).setTextContent(selectedNode.getElementsByTagName(BASE_X).item(0).getTextContent());
                                leaf.getElementsByTagName(BASE_Y).item(0).setTextContent(selectedNode.getElementsByTagName(BASE_Y).item(0).getTextContent());
                                selectedNode.getParentNode().removeChild(selectedNode);
                                break;
                            }
                        }
                    }
                }
                // end if parent is the branch "UCM" and else
            }
        }
        // end if (!existing)
    }

    /**
     * Write the document to XML file
     * 
     */
    public void writeToFile() {
        // Organize Menus
        organizeMenus();
        
        // prepare the DOM document for writing
        Source source = new DOMSource(xmlDocument);

        // prepare the output file
        File file = new File(xmlFullPath);
        Result result = new StreamResult(file);

        try {
            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Organize menus 
     * - Add 'No Maps' item if there is no map under UCM or/and GRL
     * 
     */
    private void organizeMenus() {
        if (xmlDocument == null) {
            parseMenu();
        }
        
        NodeList branchList = xmlDocument.getDocumentElement().getChildNodes();
        int len = branchList.getLength();
        Element branch = null;
        for (int i = 0; i < len; i++) {
            Node childNode = branchList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                branch = (Element) childNode;
                
                String emptyText = "";
                String bid = branch.getAttribute(BRANCH_ID);
                if (bid.equals(HTMLMenuItem.TYPE_UCM)) {
                    emptyText = "(no ucm maps)";
                } else if (bid.equals(HTMLMenuItem.TYPE_GRL)) {
                    emptyText = "(no grl maps)";
                }
                
                if (branch.getElementsByTagName(LEAF).getLength() <= 0 && 
                        branch.getElementsByTagName(BRANCH).getLength() <= 0) {
                    Element leaf = xmlDocument.createElement(LEAF);

                    Element leafText = xmlDocument.createElement(LEAF_TEXT);
                    leafText.setTextContent(emptyText);

                    Element link = xmlDocument.createElement(LINK);
                    link.setTextContent("main.html");

                    Element baseX = xmlDocument.createElement(BASE_X);
                    baseX.setTextContent("0");

                    Element baseY = xmlDocument.createElement(BASE_Y);
                    baseY.setTextContent("0");
                    
                    leaf.appendChild(leafText);
                    leaf.appendChild(link);
                    leaf.appendChild(baseX);
                    leaf.appendChild(baseY);
                    branch.appendChild(leaf);
                }
            }
        }
    }

    /**
     * Copies src file to dst file. If the dst file does not exist, it is created.
     * 
     * @param srcPath
     * @param dstPath
     * @throws IOException
     */
    private void copy(String srcPath, String dstPath) throws IOException {
        Class location = JUCMNavPlugin.class;

        InputStream in = location.getResourceAsStream(srcPath);
        OutputStream out = new FileOutputStream(new File(dstPath));

        // Transfer bytes from in to out
        int bufLen = 1024 * 8;
        byte[] buf = new byte[bufLen];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }
}
