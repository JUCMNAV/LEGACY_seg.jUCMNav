package seg.jUCMNav.views.preferences.metrics;

import seg.jUCMNav.metrics.MetricsDefinitionManager;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.views.preferences.rulemanagement.RuleEditDialog;
import seg.jUCMNav.views.preferences.rulemanagement.RuleManagementPreferencePage;

/**
 * @author Anisur Rahman
 */
public class MetricsPreferencePage extends RuleManagementPreferencePage {

    /**
     * Fill contents of all GUI components
     */
    protected void initializeValues() {
        MetricsDefinitionManager.instance().load();
        btnShowDescription.setSelection(MetricsDefinitionManager.instance().isShowDesc());
        populateTree();
        tree.pack();
    }

    protected RuleManagementDefinitionManager getDefinitionManager() {
        return MetricsDefinitionManager.instance();
    }

    protected RuleEditDialog createRuleEditDialog() {
        return new MetricsRuleEditDialog(getControl().getShell(), getDefinitionManager());

    }

    // @Override
    protected void performShowDescriptionChanged() {
        MetricsDefinitionManager.instance().setShowDesc(btnShowDescription.getSelection());

    }

}
