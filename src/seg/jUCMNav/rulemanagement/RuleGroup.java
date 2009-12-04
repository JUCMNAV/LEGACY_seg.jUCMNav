package seg.jUCMNav.rulemanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rule group. A rule group can contains any number of rules and a rule can be in any rule group.
 * 
 * @author Byrne Yan
 * 
 */
public class RuleGroup {
    /**
     * The group name
     */
    private String name;
    /**
     * All rules in the group
     */
    private List members;

    /**
     * Constructs a rule group with a group name
     */
    public RuleGroup(String name) {
        this.name = name;
        members = new ArrayList();
    }

    /**
     * Put a rule into the group. If the rule is in the group already, do nothing.
     * 
     * @param rule
     *            a static checking rule
     * @see Rule
     */
    public void addRule(Rule rule) {
        if (members.indexOf(rule) == -1)
            members.add(rule);
    }

    /**
     * Remove a rule from the group. If the rule is not in the group, do nothing.
     * 
     * @param rule
     *            a static checking rule
     * @see Rule
     */
    public void removeRule(Rule rule) {
        members.remove(rule);
    }

    /**
     * Put a bunch of rules into the group. If a rule is in the group already, do nothing.
     * 
     * @param rules
     *            a bunch of rules, the content of the list must be type of Rule or its inheritence.
     * @see Rule
     */
    public void addRule(List rules) {
        if (rules != null) {
            for (int i = 0; i < rules.size(); ++i) {
                addRule((Rule) rules.get(i));
            }
        }
    }

    /**
     * Returns all rules in the group
     * 
     * @return the content of the list returned is type of Rule.
     * @see Rule
     */
    public List getRules() {
        return members;
    }

    /**
     * Enable/disable all rules in the group
     * 
     * @param bEnable
     *            true to enable rules, false to disable rules.
     */
    public void Enable(boolean bEnable) {
        for (int i = 0; i < members.size(); ++i) {
            Rule r = (Rule) members.get(i);
            r.setEnabled(bEnable);
        }
    }

    /**
     * Returns the group name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the group name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Test if a rule is in the group or not
     * 
     * @param r
     *            a static checking rule
     * @return true if the rule is in the group, otherwise false
     * @see Rule
     */
    public boolean contain(Rule r) {
        return members.indexOf(r) != -1;
    }

    /**
     * Empty the group
     */
    public void removeAll() {
        members.clear();
    }
}
