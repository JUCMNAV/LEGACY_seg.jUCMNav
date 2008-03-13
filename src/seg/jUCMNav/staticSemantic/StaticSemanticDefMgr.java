package seg.jUCMNav.staticSemantic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
/**
 * This class is the control center of all rule defining features:
 * <ul>
 * <li>Rule and RuleGroup manipulatation
 * <li>Rule and RuleGroup persistence
 * <li>Rule imports and exports
 * <li>Switch of showing rule description in the problem view
 * </ul>
 * 
 * @author Byrne Yan
 *
 */
public class StaticSemanticDefMgr {

    private static final String RULE_NAME = "RuleName";
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
    private static final String GROUP_NUMBER = "GroupNumber";
    private static final String GROUP_PREFIX = "Group";
    private static final String MEMBER_NUMBER = "MemberNumber";

    private static StaticSemanticDefMgr instance_ = null;

    private List rules;
    private List groups;
    private boolean bShowDesc;

    /**
     * Prevents the StaticSemanticDefMgr from being created outside the class
     */
    private StaticSemanticDefMgr() {

    }

    /**
     * Returns the singleton instance of StaticSemanticDefMgr
     */
    public static StaticSemanticDefMgr instance() {
        if (instance_ == null) {
            instance_ = new StaticSemanticDefMgr();
        }
        return instance_;
    }

    /**
     * Load default rule definitions from the file "defaultRules.xml".
     * @return a list of rules
     */
    public List getDefaultDefinitions() {
        InputStream rulesDefaultIS = StaticSemanticDefMgr.class.getResourceAsStream("defaultRules.xml");

        return readRules(rulesDefaultIS);
    }

    /**
     * Saves all rules in memory into the preference store
     */
    private void saveRules() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(RULE_NUMBER, rules.size());
        for (int i = 0; i < rules.size(); ++i) {
            Rule r = (Rule) rules.get(i);
            String name = RULE_PREFIX + i;
            store.setValue(name + SELECTED_SUFFIX, r.isEnabled());
            store.setValue(name + NAME_SUFFIX, r.getName());
            store.setValue(name + CLASSIFIER_SUFFIX, r.getClassifier());
            store.setValue(name + CONTEXT_SUFFIX, r.getContext());
            store.setValue(name + CONSTRAINT_SUFFIX, r.getQuery());
            store.setValue(name + DESCCRIPTION_SUFFIX, r.getDescription());
            int nUtilities = r.getUtilities().size();
            store.setValue(name + UTILITIES_NUMBER, nUtilities);
            for (int j = 0; j < nUtilities; ++j) {
                store.setValue(name + UTILITIES + j, (String) r.getUtilities().get(j));
            }
        }

    }

    /**
     * Loads all rules from the preference store
     */
    private void loadRules() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setDefault(RULE_NUMBER, -1);
        int count = store.getInt(RULE_NUMBER);
        if (count == -1) {
            getDefaultDefinitions();
            return;
        }

        rules = new ArrayList();
        for (int i = 0; i < count; ++i) {
            String name = RULE_PREFIX + i;
            Rule r = new Rule(store.getString(name + NAME_SUFFIX),
                        store.getString(name + CLASSIFIER_SUFFIX),
                        store.getString(name + CONTEXT_SUFFIX),
                        store.getString(name + CONSTRAINT_SUFFIX),
                        store.getBoolean(name + SELECTED_SUFFIX),
                        store.getString(name + DESCCRIPTION_SUFFIX)
                    );
            int nUtilities = store.getInt(name + UTILITIES_NUMBER);
            for (int j = 0; j < nUtilities; ++j) {
                r.addUtility(store.getString(name + UTILITIES + j));
            }
            rules.add(r);
        }
    }

    /**
     * Returns all groups
     */
    public List getGroups() {
        return groups;
    }

    /**
     * Lookup a rule with the rule name.
     * @param ruleName  the rule name
     * @return the rule found, if no rule is found, a null is retruned.
     */
    public Rule lookupRule(String ruleName) {
        Rule r = null;
        for (int i = 0; i < rules.size(); ++i) {
            Rule rr = (Rule) rules.get(i);
            if (rr.getName().compareTo(ruleName) == 0)
            {
                r = rr;
                break;
            }
        }
        return r;
    }

    /**
     * Returns a list of default groups.
     */
    private List getDefaultGroups() {
        List dg = new ArrayList();
        RuleGroup g = new RuleGroup("All");
        g.addRule(rules);
        dg.add(g);
        
        dg.add(new RuleGroup("Performance Scenario"));      
        dg.add(new RuleGroup("Aspect"));
        dg.add(new RuleGroup("Performance"));
        dg.add( new RuleGroup("Scenario Aspect"));
        return dg;
    }

    /**
     * Exports rules into an XML file on the specified path.  
     * @param rules the rules are exportes
     * @param path  the location where the rules XML file exists.
     */
    public static void exportRules(List rules, String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Rules");
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
     * @param doc  an XML Document
     * @param r a static checking rule
     * @return an XML element
     */
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

    /**
     * Reads rules from an InputStream.
     * @param rulesIS   an InputStream which contains rule information
     * @return a list of rules
     */
    private static List readRules(InputStream rulesIS) {
        List rules = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(rulesIS);
            Element root = doc.getDocumentElement();
            NodeList utilityNodes = root.getElementsByTagName("Rule");
            rules = new ArrayList();
            for (int i = 0; i < utilityNodes.getLength(); ++i) {
                Rule r = new Rule("");
                Node utilityNode = utilityNodes.item(i);
                Node node = utilityNode.getFirstChild();
                while (node != null) {
                    String nodeName = node.getNodeName();
                    if (nodeName.compareTo("Utilities") == 0) {
                        Node u = node.getFirstChild();
                        while (u != null) {
                            if (u.getNodeName().compareTo("Utility") == 0) {
                               r.addUtility(u.getTextContent());
                            }
                            u = u.getNextSibling();
                        }

                    } else {
                        String value = node.getTextContent();
                        if (nodeName.compareTo("Name") == 0) {
                            r.setName(value);
                        } else if (nodeName.compareTo("Description") == 0) {
                            r.setDescription(value);
                        } else if (nodeName.compareTo("Classification") == 0) {
                            r.setClassifier(value);
                        } else if (nodeName.compareTo("Query") == 0) {
                            r.setContext(value);
                        } else if (nodeName.compareTo("Constraint") == 0) {
                            r.setQuery(value);
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

    /**
     * Imports rules from a specified XML file
     * @param rulesFile the path of the XML file that contains rule definitions
     * @param parent a Shell, which is used as parent when a messgae box is showed.
     * @return a list of rules
     * @throws FileNotFoundException
     */
    public List importRules(String rulesFile, Shell parent) throws FileNotFoundException {
        List rulesTmp = null;
        if (isValidRuleFile(new FileInputStream(rulesFile), parent)) {

            rulesTmp = readRules(new FileInputStream(rulesFile));
        }

        if (rulesTmp != null) {
            String renamingMsg = "";
            for (int i = 0; i < rulesTmp.size(); ++i) {
                Rule r = (Rule) rulesTmp.get(i);
                String name = r.getName();
                int j = 0;
                String tryName = name;
                while (isNameConflict(tryName, rules)) {
                    tryName = name + (++j);
                }
                if (j != 0) {
                    r.setName(name + j);
                    renamingMsg += "\n" + "The rule of " + name + " is renamed to " + name + j;
                }
            }
            RuleGroup g = lookupGroup("Imported");
            if(g==null)
            {
                g = new RuleGroup("Imported");
                addGroup(g);
            }
            g.addRule(rulesTmp);
            
            if (renamingMsg.length() != 0) {
                MessageBox msg = new MessageBox(parent, SWT.ICON_WARNING);
                msg.setMessage(renamingMsg);
                msg.setText("Rule(s)is/are renamed due to the name conflict");
                msg.open();
            }
            addRule(rulesTmp);
        }
        return rules;
    }

    /**
     * Put a bunch of rules into the system rules
     * @param rulesIn a bunch of rules which are going to be put into the system rules
     */
    private void addRule(List rulesIn) {
        for(int i=0;i<rulesIn.size();++i)
        {
            Rule r = (Rule) rulesIn.get(i);
            addRule(r );
        }
        
    }

    /**
     * Test if an InputStream contains valid rules
     * @param rulesIS an InputStream
     * @param parent a Shell, which is used as parent when a messgae box is showed.
     * @return true if it is valid, otherwise false
     */
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

    /**
     * Test if a rule name exists in a bunch of rules
     * @param name the rule name
     * @param rules a bunch of rules
     * @return true if it exists, otherwise false
     */
    private static boolean isNameConflict(String name, List rules) {
        for (int i = 0; i < rules.size(); ++i) {
            Rule r = (Rule) rules.get(i);
            if (r.getName().compareTo(name) == 0)
                return true;
        }
        return false;
    }

    /**
     * Check if the switch of showing description in the problem view is on or off.
     * @return true if it is on, otherwise off.
     */
    public boolean isShowDesc() {
        return bShowDesc;
    }

    /**
     * Set the switch of showing description in the problem view.
     * @param bChecked true to switch on, false to switch off
     */
    public void setShowDesc(boolean bChecked) {
        bShowDesc = bChecked;
    }

    /**
     * Returns all system rules
     */
    public List getRules() {
        return rules;
    }
    /**
     * Save all settings into the preference store
     */
    public void save()
    {
        saveRules();
        saveGroups();
        saveOthers();
    }
    /**
     * Load all setting from the preference store
     */
    public void load()
    {
        loadRules();
        loadGroups();
        loadOthers();
    }
    /**
     * Load the setting of switch of showing description in the problem view.
     */
    private void loadOthers()
    {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        bShowDesc =  store.getBoolean(SHOW_DESCRIPTION);
    }
    /**
     * Save the setting of switch of showing description in the problem view into the preference store
     */
    private void saveOthers()
    {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(SHOW_DESCRIPTION, bShowDesc);  
    }
    /**
     * Save all group information into the preference store
     */
    private void saveGroups()
    {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(GROUP_NUMBER, groups.size()-1);
        
        for(int i=0,k=0;i< groups.size();++i) {
            RuleGroup g = (RuleGroup) groups.get(i);
            if(g.getName().compareTo("All")==0) continue;
            String groupName = GROUP_PREFIX + k++;
            store.setValue(groupName + NAME_SUFFIX,g.getName());
            store.setValue(groupName + MEMBER_NUMBER,g.getRules().size());
            for (int j = 0; j < g.getRules().size(); ++j) {
                Rule r = (Rule) g.getRules().get(j);
                store.setValue(groupName + RULE_NAME + j, r.getName());
            }
        }
    }
    
    /**
     * Load all group information from the preference store
     */
    private void loadGroups() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setDefault(GROUP_NUMBER, -1);
        int count = store.getInt(GROUP_NUMBER);
        if (count == -1) {
            groups = getDefaultGroups();

        } else {

            groups = new ArrayList();
            RuleGroup g = new RuleGroup("All");
            g.addRule(rules);
            groups.add(g);
            
            for (int i = 0; i < count; ++i) {
                String groupName = GROUP_PREFIX + i;
                g = new RuleGroup(store.getString(groupName + NAME_SUFFIX));
                int nGroup = store.getInt(groupName + MEMBER_NUMBER);
                for(int j = 0; j < nGroup; ++j) {
                    String ruleName = store.getString(groupName + RULE_NAME + j);
                    Rule r = lookupRule(ruleName);
                    if (r != null) {
                        g.addRule(r);
                    }                   
                }
                groups.add(g);
            }
        }

    }
    
    
    /**
     * Creates a new rule instance with 6 propertie values. If a rule with the same name exists, return null
     */
    public Rule createRule(String name,String classifier,String context, String query,boolean enabled, String description)
    {
        Rule r = lookupRule(name);
        if(r==null)
            return new Rule(name,classifier,context, query,enabled, description);
        return null;
    }
    
    /**
     * Creates a new rule instance with rule name. If a rule with the same name exists, return null.
     */
    public Rule createRule(String name)
    {
        Rule r = lookupRule(name);
        if(r==null)
            return new Rule(name);
        return null;
    }
    
    /**
     * Creates a rule group instance wiht a group name. If a group with the same name exists, return null.
     */
    public RuleGroup creatRuelGroup(String groupName)
    {
        RuleGroup g = lookupGroup(groupName);
        if(g==null)
            return new RuleGroup(groupName);
        return null;
    }

    /**
     * Lookup a RuleGroup with a group name
     * @return a RuleGroup instance found. If no such group is foudn, returns null.
     */
    public RuleGroup lookupGroup(String groupName) {
        RuleGroup g = null;
        for (int i = 0; i < groups.size(); ++i) {
            RuleGroup gg = (RuleGroup) groups.get(i);
            if (gg.getName().compareTo(groupName) == 0)
            {
                g = gg;
                break;
            }
        }
        return g;
    }

    /**
     * Add a rule into system rules. If it is in the system rules, do nothing. 
     * @param rule
     */
    public void addRule(Rule rule) {
        if(lookupRule(rule.getName())==null)
        {
            rules.add(rule);
            RuleGroup all = lookupGroup("All");
            all.addRule(rule);
        }
        
    }

    /**
     * Add a rule group into the system groups. If it is in the system groups, do nothing.
     * @param group
     */
    public void addGroup(RuleGroup group) {
        if(lookupGroup(group.getName())==null)
        {
            groups.add(group);
        }
        
    }

    /**
     * Remove a rule from the system rules
     */
    public void removeRule(Rule r) {
        rules.remove(r);
        //traverse all groups and then remove r from them
        for(int i=0;i<groups.size();++i)
        {
            RuleGroup g = (RuleGroup) groups.get(i);
            g.removeRule(r);
        }
    }

    /**
     * Remove a group from the system rules.
     */
    public void removeGroup(RuleGroup g) {
        groups.remove(g);
        
    }

    /**
     * Reset all setting into default values.
     */
    public void loadDefault() {
        rules = getDefaultDefinitions();
        groups = getDefaultGroups();
        bShowDesc = true;
    }
}
