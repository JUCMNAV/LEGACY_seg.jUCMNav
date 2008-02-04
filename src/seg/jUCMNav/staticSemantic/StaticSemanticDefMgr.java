package seg.jUCMNav.staticSemantic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import seg.jUCMNav.JUCMNavPlugin;

public class StaticSemanticDefMgr {

    private static final String SHOW_DESCRIPTION = "SHOW_DESCRIPTION";
    private static final String RULE_SCHEMA = "ruleschema.xsd";
    private static final String SELECTED_SUFFIX = "Selected";
    private static final String RULE_NUMBER = "RuleNumber";
    private static final String DESCCRIPTION_SUFFIX = "_Desccription";
    private static final String CONSTRAINT_SUFFIX = "_Constraint";
    private static final String CONTEXT_SUFFIX = "_Context";
    private static final String CLASSIFIER_SUFFIX = "_Classifier";
    private static final String NAME_SUFFIX = "_Name";
    private static final String RULE_PREFIX = "Rule";
    private static final String UTILITY_DEFINITIONS = "UtilityDefinitions";
    private static final String UTILITIES_NUMBER = "_UtilitiesNumber";
    private static final String UTILITIES = "Utility";

    static public Rule[] getDefaultDefinitions() {
        InputStream rulesDefaultIS = StaticSemanticDefMgr.class.getResourceAsStream("defaultRules.xml");

        return readRules(rulesDefaultIS, null);
    }

    public static void saveDefinitions(Rule[] rules) {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(RULE_NUMBER, rules.length);
        for (int i = 0; i < rules.length; ++i) {
            String name = RULE_PREFIX + i;
            store.setValue(name + SELECTED_SUFFIX, rules[i].isEnabled());
            store.setValue(name + NAME_SUFFIX, rules[i].getName());
            store.setValue(name + CLASSIFIER_SUFFIX, rules[i].getClassifier());
            store.setValue(name + CONTEXT_SUFFIX, rules[i].getContext());
            store.setValue(name + CONSTRAINT_SUFFIX, rules[i].getQuery());
            store.setValue(name + DESCCRIPTION_SUFFIX, rules[i].getDescription());
            int nUtilities = rules[i].getUtilities().size();
            store.setValue(name + UTILITIES_NUMBER, nUtilities);
            for (int j = 0; j < nUtilities; ++j) {
                store.setValue(name + UTILITIES + j, (String) rules[i].getUtilities().get(j));
            }
        }

    }

    public static Rule[] loadDefinitions() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setDefault(RULE_NUMBER, -1);
        int count = store.getInt(RULE_NUMBER);
        if (count == -1)
            return getDefaultDefinitions();

        Rule[] rules = new Rule[count];
        for (int i = 0; i < count; ++i) {
            rules[i] = new Rule();
            String name = RULE_PREFIX + i;
            rules[i].setEnabled(store.getBoolean(name + SELECTED_SUFFIX));
            rules[i].setName(store.getString(name + NAME_SUFFIX));
            rules[i].setClassifier(store.getString(name + CLASSIFIER_SUFFIX));
            rules[i].setContext(store.getString(name + CONTEXT_SUFFIX));
            rules[i].setQuery(store.getString(name + CONSTRAINT_SUFFIX));
            rules[i].setDescription(store.getString(name + DESCCRIPTION_SUFFIX));
            int nUtilities = store.getInt(name + UTILITIES_NUMBER);
            for (int j = 0; j < nUtilities; ++j) {
                rules[i].addUtility(store.getString(name + UTILITIES + j));
            }
        }
        return rules;
    }

    public static void exportRules(Rule[] rules, String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Rules");
            doc.appendChild(root);

            for (int i = 0; i < rules.length; ++i) {
                Rule r = rules[i];
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

    private static Element buildRuleNode(Document doc, Rule r) {
        Element root = doc.createElement("Rule");

        // Name
        Element name = doc.createElement("Name");
        name.setTextContent(r.getName());
        root.appendChild(name);
        // Description
        Element desc = doc.createElement("Description");
        desc.setTextContent(r.getDescription());
        root.appendChild(desc);
        // Classification
        Element classifier = doc.createElement("Classification");
        classifier.setTextContent(r.getClassifier());
        root.appendChild(classifier);
        // Query
        Element query = doc.createElement("Query");
        query.setTextContent(r.getContext());
        root.appendChild(query);
        // Constraint
        Element constraint = doc.createElement("Constraint");
        constraint.setTextContent(r.getQuery());
        root.appendChild(constraint);
        // Utilities
        Element utilities = doc.createElement("Utilities");
        for (int i = 0; i < r.getUtilities().size(); ++i) {
            Element utility = doc.createElement("Utility");
            utility.setTextContent((String) r.getUtilities().get(i));
            utilities.appendChild(utility);
        }
        root.appendChild(utilities);
        return root;
    }

    private static Rule[] readRules(InputStream rulesIS, Shell parent) {
        Rule[] rules = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(rulesIS);
            Element root = doc.getDocumentElement();
            NodeList utilityNodes = root.getElementsByTagName("Rule");
            rules = new Rule[utilityNodes.getLength()];
            for (int i = 0; i < utilityNodes.getLength(); ++i) {
                rules[i] = new Rule();
                Node utilityNode = utilityNodes.item(i);
                Node node = utilityNode.getFirstChild();
                while (node != null) {
                    String nodeName = node.getNodeName();
                    if (nodeName.compareTo("Utilities") == 0) {
                        Node u = node.getFirstChild();
                        while (u != null) {
                            if (u.getNodeName().compareTo("Utility") == 0) {
                                rules[i].addUtility(u.getTextContent());
                            }
                            u = u.getNextSibling();
                        }

                    } else {
                        String value = node.getTextContent();
                        if (nodeName.compareTo("Name") == 0) {
                            rules[i].setName(value);
                        } else if (nodeName.compareTo("Description") == 0) {
                            rules[i].setDescription(value);
                        } else if (nodeName.compareTo("Classification") == 0) {
                            rules[i].setClassifier(value);
                        } else if (nodeName.compareTo("Query") == 0) {
                            rules[i].setContext(value);
                        } else if (nodeName.compareTo("Constraint") == 0) {
                            rules[i].setQuery(value);
                        }
                    }
                    node = node.getNextSibling();
                }
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

    public static Rule[] importRules(String rulesFile, Shell parent) throws FileNotFoundException {
        Rule[] rules = null;
        if (isValidRuleFile(new FileInputStream(rulesFile), parent)) {

            rules = readRules(new FileInputStream(rulesFile), parent);
        }

        if (rules != null) {
            String renamingMsg = "";
            Rule[] oldRules = loadDefinitions();
            for (int i = 0; i < rules.length; ++i) {
                Rule r = rules[i];
                String name = r.getName();
                int j = 0;
                String tryName = name;
                while (isNameConflict(tryName, oldRules)) {
                    tryName = name + (++j);
                }
                if (j != 0) {
                    r.setName(name + j);
                    renamingMsg += "\n" + "The rule of " + name + " is renamed to " + name + j;
                }
            }
            if (renamingMsg.length()!=0) {
                MessageBox msg = new MessageBox(parent, SWT.ICON_WARNING);
                msg.setMessage(renamingMsg);
                msg.setText("Rule(s)is/are renamed due to the name conflict");
                msg.open();
            }

        }
        return rules;
    }

    private static boolean isValidRuleFile(InputStream rulesIS, Shell parent) {
        boolean bValid = true;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream ruleSchemaIS = StaticSemanticDefMgr.class.getResourceAsStream(RULE_SCHEMA);
        Source schemaSource = new StreamSource(ruleSchemaIS);
        Schema schema;
        try {
            schema = schemaFactory.newSchema(schemaSource);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(rulesIS));
        } catch (SAXException e) {
            bValid = false;
            MessageBox msg = new MessageBox(parent, SWT.ICON_ERROR);
            msg.setMessage(e.getMessage());
            msg.setText("Invalidated rule file");
            msg.open();
            // e.printStackTrace();
        } catch (IOException e) {
            bValid = false;
            MessageBox msg = new MessageBox(parent, SWT.ICON_ERROR);
            msg.setMessage(e.getMessage());
            msg.setText("Error when opening a rule file");
            msg.open();
            // e.printStackTrace();
        }
        return bValid;
    }

    private static boolean isNameConflict(String name, Rule[] rules) {
        for (int i = 0; i < rules.length; ++i) {
            Rule r = rules[i];
            if (r.getName().compareTo(name) == 0)
                return true;
        }
        return false;
    }

    public static boolean isShowDesc() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        return store.getBoolean(SHOW_DESCRIPTION);
    }

    public static void setShowDesc(boolean bChecked) {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(SHOW_DESCRIPTION, bChecked);

    }

}
