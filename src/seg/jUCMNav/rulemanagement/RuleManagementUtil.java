package seg.jUCMNav.rulemanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Anisur Rahman
 * 
 */
public class RuleManagementUtil {

    /**
     * Exports rules into an XML file on the specified path.
     * 
     * @param rules
     *            the rules to export
     * @param path
     *            the location where the rules XML file exists.
     */
    public static void exportRules(List rules, String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Rules"); //$NON-NLS-1$
            doc.appendChild(root);

            for (int i = 0; i < rules.size(); ++i) {
                Rule r = (Rule) rules.get(i);
                Element ruleNode = buildRuleNode(doc, r);
                root.appendChild(ruleNode);
            }
            // write to a file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            Result dest = new StreamResult(new File(path));
            aTransformer.transform(src, dest);
        } catch (ParserConfigurationException e) {

        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Create an XML element based on a static checking rule
     * 
     * @param doc
     *            an XML Document
     * @param r
     *            a static checking rule
     * @return an XML element
     */
    private static Element buildRuleNode(Document doc, Rule r) {
        Element root = doc.createElement("Rule"); //$NON-NLS-1$

        // Name
        Element name = doc.createElement("Name"); //$NON-NLS-1$
        name.setTextContent(r.getName());
        root.appendChild(name);
        // Description
        Element desc = doc.createElement("Description"); //$NON-NLS-1$
        desc.setTextContent(r.getDescription());
        root.appendChild(desc);
        // Classification
        Element classifier = doc.createElement("Classification"); //$NON-NLS-1$
        classifier.setTextContent(r.getClassifier());
        root.appendChild(classifier);
        // Query
        Element query = doc.createElement("Query"); //$NON-NLS-1$
        query.setTextContent(r.getContext());
        root.appendChild(query);
        // Constraint
        Element constraint = doc.createElement("Constraint"); //$NON-NLS-1$
        constraint.setTextContent(r.getQuery());
        root.appendChild(constraint);
        // Utilities
        Element utilities = doc.createElement("Utilities"); //$NON-NLS-1$
        for (int i = 0; i < r.getUtilities().size(); ++i) {
            Element utility = doc.createElement("Utility"); //$NON-NLS-1$
            utility.setTextContent((String) r.getUtilities().get(i));
            utilities.appendChild(utility);
        }
        root.appendChild(utilities);
        // WarningOnly
        Element warningOnly = doc.createElement("WarningOnly"); //$NON-NLS-1$
        warningOnly.setTextContent(r.getWarningOnly() ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
        root.appendChild(warningOnly);
        return root;
    }

    /**
     * Reads rules from an InputStream.
     * 
     * @param rulesIS
     *            an InputStream which contains rule information
     * @return a list of rules
     */
    public static List readRules(InputStream rulesIS) {
        List rules = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(rulesIS);
            Element root = doc.getDocumentElement();
            NodeList utilityNodes = root.getElementsByTagName("Rule"); //$NON-NLS-1$
            rules = new ArrayList();
            for (int i = 0; i < utilityNodes.getLength(); ++i) {
                Rule r = new Rule(""); //$NON-NLS-1$
                Node utilityNode = utilityNodes.item(i);
                Node node = utilityNode.getFirstChild();
                while (node != null) {
                    String nodeName = node.getNodeName();
                    if (nodeName.compareTo("Utilities") == 0) { //$NON-NLS-1$
                        Node u = node.getFirstChild();
                        while (u != null) {
                            if (u.getNodeName().compareTo("Utility") == 0) { //$NON-NLS-1$
                                r.addUtility(u.getTextContent());
                            }
                            u = u.getNextSibling();
                        }

                    } else {
                        String value = node.getTextContent();
                        if (nodeName.compareTo("Name") == 0) { //$NON-NLS-1$
                            r.setName(value);
                        } else if (nodeName.compareTo("Description") == 0) { //$NON-NLS-1$
                            r.setDescription(value);
                        } else if (nodeName.compareTo("Classification") == 0) { //$NON-NLS-1$
                            r.setClassifier(value);
                        } else if (nodeName.compareTo("Query") == 0) { //$NON-NLS-1$
                            r.setContext(value);
                        } else if (nodeName.compareTo("Constraint") == 0) { //$NON-NLS-1$
                            r.setQuery(value);
                        } else if (nodeName.compareTo("WarningOnly") == 0) { //$NON-NLS-1$
                            r.setWarningOnly(value.equalsIgnoreCase("true")); //$NON-NLS-1$
                        }

                    }
                    node = node.getNextSibling();
                }
                rules.add(r);
            }

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rules;
    }

}
