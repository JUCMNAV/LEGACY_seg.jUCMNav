package seg.jUCMNav.rulemanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * 
 * @author Bo Yan, Anisur Rahman, Daniel Amyot
 * 
 */
public abstract class RuleManagementDefinitionManager {
    private static final String RULE_NAME = "RuleName"; //$NON-NLS-1$
    private static final String RULE_SCHEMA = "ruleschema.xsd"; //$NON-NLS-1$
    private static final String SELECTED_SUFFIX = "Selected"; //$NON-NLS-1$
    private static final String RULE_NUMBER = "RuleNumber"; //$NON-NLS-1$
    private static final String DESCCRIPTION_SUFFIX = "_Desccription"; //$NON-NLS-1$
    private static final String CONSTRAINT_SUFFIX = "_Constraint"; //$NON-NLS-1$
    private static final String WARNING_SUFFIX = "_Warning"; //$NON-NLS-1$
    private static final String CONTEXT_SUFFIX = "_Context"; //$NON-NLS-1$
    private static final String CLASSIFIER_SUFFIX = "_Classifier"; //$NON-NLS-1$
    private static final String NAME_SUFFIX = "_Name"; //$NON-NLS-1$
    private static final String RULE_PREFIX = "Rule"; //$NON-NLS-1$
    private static final String UTILITY_DEFINITIONS = "UtilityDefinitions"; //$NON-NLS-1$
    private static final String UTILITIES_NUMBER = "_UtilitiesNumber"; //$NON-NLS-1$
    private static final String UTILITIES = "Utility"; //$NON-NLS-1$
    private static final String GROUP_NUMBER = "GroupNumber"; //$NON-NLS-1$
    private static final String GROUP_PREFIX = "Group"; //$NON-NLS-1$
    private static final String MEMBER_NUMBER = "MemberNumber"; //$NON-NLS-1$   

    protected List rules;
    protected List groups;

    public abstract List getDefaultDefinitions();

    protected abstract RuleManagementDefinitionManager getDefferManagerInstance();

    protected abstract String getRuleType();

    public abstract boolean isShowDesc();

    public abstract void setShowDesc(boolean bChecked);

    protected abstract void loadShowDescriptonPreference();

    protected abstract void saveShowDescriptonPreference();

    /**
     * Saves all rules in memory into the preference store
     */
    private void saveRules() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        if (rules != null) {
            store.setValue(RULE_NUMBER + getRuleType(), rules.size());
            for (int i = 0; i < rules.size(); ++i) {
                Rule r = (Rule) rules.get(i);
                String name = RULE_PREFIX + getRuleType() + i;
                store.setValue(name + SELECTED_SUFFIX, r.isEnabled());
                store.setValue(name + NAME_SUFFIX, r.getName());
                store.setValue(name + CLASSIFIER_SUFFIX, r.getClassifier());
                store.setValue(name + CONTEXT_SUFFIX, r.getContext());
                store.setValue(name + CONSTRAINT_SUFFIX, r.getQuery());
                store.setValue(name + DESCCRIPTION_SUFFIX, r.getDescription());
                store.setValue(name + WARNING_SUFFIX, r.getWarningOnly());
                int nUtilities = r.getUtilities().size();
                store.setValue(name + UTILITIES_NUMBER, nUtilities);
                for (int j = 0; j < nUtilities; ++j) {
                    store.setValue(name + UTILITIES + j, (String) r.getUtilities().get(j));
                }
            }
        }
    }

    /**
     * Loads all rules from the preference store
     */
    private void loadRules() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setDefault(RULE_NUMBER + getRuleType(), -1);
        int count = store.getInt(RULE_NUMBER + getRuleType());
        if (count == -1) {
            rules = getDefaultDefinitions();
            return;
        }

        rules = new ArrayList();
        for (int i = 0; i < count; ++i) {
            String name = RULE_PREFIX + getRuleType() + i;
            Rule r = new Rule(store.getString(name + NAME_SUFFIX), store.getString(name + CLASSIFIER_SUFFIX), store.getString(name + CONTEXT_SUFFIX), store
                    .getString(name + CONSTRAINT_SUFFIX), store.getBoolean(name + SELECTED_SUFFIX), store.getBoolean(name + WARNING_SUFFIX), store
                    .getString(name + DESCCRIPTION_SUFFIX));
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
     * 
     * @param ruleName
     *            the rule name
     * @return the rule found, if no rule is found, a null is returned.
     */
    public Rule lookupRule(String ruleName) {
        Rule r = null;
        if (rules == null)
            return null;
        for (int i = 0; i < rules.size(); ++i) {
            Rule rr = (Rule) rules.get(i);
            if (rr.getName().compareTo(ruleName) == 0) {
                r = rr;
                break;
            }
        }
        return r;
    }

    /**
     * Returns a list of default groups. Different for metrics and constraints.
     */
    protected abstract List getDefaultGroups();

    /**
     * Returns a group filled with rules from a file.
     */
    protected RuleGroup createDefaultGroup(String name, String srcFile, RuleGroup all, Class directory) {
        InputStream rulesIS = directory.getResourceAsStream(srcFile);
        List rules = RuleManagementUtil.readRules(rulesIS);
        RuleGroup newGroup = new RuleGroup(name);

        all.addRule(rules); // Add the rules to the special "All" group
        newGroup.addRule(rules); // Also add the rules to the new group

        return newGroup;
    }

    /**
     * Put a bunch of rules into the system rules
     * 
     * @param rulesIn
     *            a bunch of rules which are going to be put into the system rules
     */
    private void addRule(List rulesIn) {
        for (int i = 0; i < rulesIn.size(); ++i) {
            Rule r = (Rule) rulesIn.get(i);
            addRule(r);
        }
    }

    /**
     * Imports rules from a specified XML file
     * 
     * @param rulesFile
     *            the path of the XML file that contains rule definitions
     * @param parent
     *            a Shell, which is used as parent when a message box is showed.
     * @return a list of rules
     * @throws FileNotFoundException
     */
    public List importRules(String rulesFile, Shell parent) throws FileNotFoundException {
        List rulesTmp = null;
        if (isValidRuleFile(new FileInputStream(rulesFile), parent)) {

            rulesTmp = RuleManagementUtil.readRules(new FileInputStream(rulesFile));
        }

        if (rulesTmp != null) {
            String renamingMsg = ""; //$NON-NLS-1$
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
                    renamingMsg += "\n" + Messages.getString("RuleManagementDefinitionManager.RuleName") + name + Messages.getString("RuleManagementDefinitionManager.IsRenamed") + name + j; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                }
            }
            RuleGroup g = lookupGroup("Imported"); //$NON-NLS-1$
            if (g == null) {
                g = new RuleGroup("Imported"); //$NON-NLS-1$
                addGroup(g);
            }
            g.addRule(rulesTmp);

            if (renamingMsg.length() != 0) {
                MessageBox msg = new MessageBox(parent, SWT.ICON_WARNING);
                msg.setMessage(renamingMsg);
                msg.setText(Messages.getString("RuleManagementDefinitionManager.RulesRenames")); //$NON-NLS-1$
                msg.open();
            }
            addRule(rulesTmp);
        }
        return rules;
    }

    /**
     * Test if an InputStream contains valid rules
     * 
     * @param rulesIS
     *            an InputStream
     * @param parent
     *            a Shell, which is used as parent when a message box is showed.
     * @return true if it is valid, otherwise false
     */
    private static boolean isValidRuleFile(InputStream rulesIS, Shell parent) {
        boolean bValid = true;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream ruleSchemaIS = RuleManagementDefinitionManager.class.getResourceAsStream(RULE_SCHEMA);
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
            msg.setText(Messages.getString("RuleManagementDefinitionManager.InvalidRuleFile")); //$NON-NLS-1$
            msg.open();
            // e.printStackTrace();
        } catch (IOException e) {
            bValid = false;
            MessageBox msg = new MessageBox(parent, SWT.ICON_ERROR);
            msg.setMessage(e.getMessage());
            msg.setText(Messages.getString("RuleManagementDefinitionManager.ErrorOpening")); //$NON-NLS-1$
            msg.open();
            // e.printStackTrace();
        }
        return bValid;
    }

    /**
     * Test if a rule name exists in a bunch of rules
     * 
     * @param name
     *            the rule name
     * @param rules
     *            a bunch of rules
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
     * Returns all system rules
     */
    public List getRules() {
        return rules;
    }

    /**
     * Save all settings into the preference store
     */
    public void save() {
        saveRules();
        saveGroups();
        saveOthers();
    }

    /**
     * Load all setting from the preference store
     */
    public void load() {
        loadRules();
        loadGroups();
        loadOthers();
    }

    /**
     * Load the setting of switch of showing description in the problem view.
     */
    private void loadOthers() {
        loadShowDescriptonPreference();
    }

    /**
     * Save the setting of switch of showing description in the problem view into the preference store
     */
    private void saveOthers() {
        saveShowDescriptonPreference();
    }

    /**
     * Save all group information into the preference store
     */
    private void saveGroups() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(GROUP_NUMBER + getRuleType(), groups.size() - 1);

        for (int i = 0, k = 0; i < groups.size(); ++i) {
            RuleGroup g = (RuleGroup) groups.get(i);
            if (g.getName().compareTo("All") == 0)continue; //$NON-NLS-1$
            String groupName = GROUP_PREFIX + getRuleType() + k++;
            store.setValue(groupName + NAME_SUFFIX, g.getName());
            store.setValue(groupName + MEMBER_NUMBER, g.getRules().size());
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
        store.setDefault(GROUP_NUMBER + getRuleType(), -1);
        int count = store.getInt(GROUP_NUMBER + getRuleType());
        if (count == -1) {
            groups = getDefaultGroups();

        } else {
            groups = new ArrayList();
            RuleGroup g = new RuleGroup("All"); //$NON-NLS-1$
            g.addRule(rules);
            groups.add(g);

            for (int i = 0; i < count; ++i) {
                String groupName = GROUP_PREFIX + getRuleType() + i;
                g = new RuleGroup(store.getString(groupName + NAME_SUFFIX));
                int nGroup = store.getInt(groupName + MEMBER_NUMBER);
                for (int j = 0; j < nGroup; ++j) {
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
     * Creates a new rule instance with 7 property values. If a rule with the same name exists, return null
     */
    public Rule createRule(String name, String classifier, String context, String query, boolean enabled, boolean warningOnly, String description) {
        Rule r = lookupRule(name);
        if (r == null)
            return new Rule(name, classifier, context, query, enabled, warningOnly, description);
        return null;
    }

    /**
     * Creates a new rule instance with rule name. If a rule with the same name exists, return null.
     */
    public Rule createRule(String name) {
        Rule r = lookupRule(name);
        if (r == null)
            return new Rule(name);
        return null;
    }

    /**
     * Creates a rule group instance with a group name. If a group with the same name exists, return null.
     */
    public RuleGroup createRuleGroup(String groupName) {
        RuleGroup g = lookupGroup(groupName);
        if (g == null)
            return new RuleGroup(groupName);
        return null;
    }

    /**
     * Lookup a RuleGroup with a group name
     * 
     * @return a RuleGroup instance found. If no such group is found, returns null.
     */
    public RuleGroup lookupGroup(String groupName) {
        RuleGroup g = null;
        for (int i = 0; i < groups.size(); ++i) {
            RuleGroup rg = (RuleGroup) groups.get(i);
            if (rg.getName().compareTo(groupName) == 0) {
                g = rg;
                break;
            }
        }
        return g;
    }

    /**
     * Add a rule into system rules. If it is in the system rules, do nothing.
     * 
     * @param rule
     */
    public void addRule(Rule rule) {
        if (lookupRule(rule.getName()) == null) {
            rules.add(rule);
            RuleGroup all = lookupGroup("All"); //$NON-NLS-1$
            all.addRule(rule);
        }

    }

    /**
     * Add a rule group into the system groups. If it is in the system groups, do nothing.
     * 
     * @param group
     */
    public void addGroup(RuleGroup group) {
        if (lookupGroup(group.getName()) == null) {
            groups.add(group);
        }

    }

    /**
     * Remove a rule from the system rules
     */
    public void removeRule(Rule r) {
        rules.remove(r);
        // traverse all groups and then remove r from them
        for (int i = 0; i < groups.size(); ++i) {
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
        groups = getDefaultGroups();
        rules = ((RuleGroup) groups.get(0)).getRules(); // From the All group, which is first
        setShowDesc(true);
    }

}
