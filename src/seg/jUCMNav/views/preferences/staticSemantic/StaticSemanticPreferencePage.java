package seg.jUCMNav.views.preferences.staticSemantic;

import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.staticSemantic.StaticSemanticDefMgr;
import seg.jUCMNav.views.preferences.rulemanagement.RuleManagementPreferencePage;

/**
 * This class provides an Eclipse preference page for the static checking settings. The settings include:
 * <ul>
 * <li>The switch of showing the rule description on the problem view when reporting the checking result.
 * <li>Enable/disable a rule or rules by individual rule or groups
 * <li>Open a rule group creating/editing dialog
 * <li>Open a rule creating/editing dialog
 * <li>Load default settings
 * <li>Save all setting to the preference store
 * </ul>
 * 
 * @author Byrne Yan
 * 
 */
public class StaticSemanticPreferencePage extends RuleManagementPreferencePage {
    /**
     * Fill contents of all GUI components
     */
    protected void initializeValues() {
        StaticSemanticDefMgr.instance().load();
        btnShowDescription.setSelection(StaticSemanticDefMgr.instance().isShowDesc());
        populateTree();
        tree.pack();
    }

    protected RuleManagementDefinitionManager getDefinitionManager() {
        return StaticSemanticDefMgr.instance();
    }

    // @Override
    protected void performShowDescriptionChanged() {
        StaticSemanticDefMgr.instance().setShowDesc(btnShowDescription.getSelection());
    }

}
