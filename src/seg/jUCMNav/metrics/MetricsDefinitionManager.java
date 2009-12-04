package seg.jUCMNav.metrics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;

/**
 * 
 * @author Anisur Rahman
 * 
 */
public class MetricsDefinitionManager extends RuleManagementDefinitionManager {

    private static String RULE_TYPE = "Metrics"; //$NON-NLS-1$
    private static final String SHOW_DESCRIPTION = RULE_TYPE + "SHOW_DESCRIPTION"; //$NON-NLS-1$

    private boolean bShowDesc;
    private static MetricsDefinitionManager instance_ = null;

    /**
     * Prevents the StaticSemanticDefMgr from being created outside the class
     */
    private MetricsDefinitionManager() {

    }

    /**
     * Returns the singleton instance of StaticSemanticDefMgr
     */
    public static MetricsDefinitionManager instance() {
        if (instance_ == null) {
            instance_ = new MetricsDefinitionManager();
            instance_.load();
        }
        return instance_;
    }

    protected RuleManagementDefinitionManager getDefferManagerInstance() {
        return instance();
    }

    protected String getRuleType() {
        return RULE_TYPE;
    }

    /**
     * Returns a list of default groups populated with default metrics from files.
     */
    protected List getDefaultGroups() {
        List defaultGroups = new ArrayList();

        // Special "All" group, always first
        RuleGroup all = new RuleGroup("All"); //$NON-NLS-1$
        defaultGroups.add(all);

        // Add new default groups here!
        defaultGroups.add(createDefaultGroup("SizeUCM", "SizeUCM.xml", all, MetricsDefinitionManager.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("SizeGRL", "SizeGRL.xml", all, MetricsDefinitionManager.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("Aspects", "AspectMetrics.xml", all, MetricsDefinitionManager.class)); //$NON-NLS-1$ //$NON-NLS-2$

        return defaultGroups;
    }

    /**
     * @deprecated
     */
    public List getDefaultDefinitions() {
        return new ArrayList();
    }

    /**
     * Check if the switch of showing description in the problem view is on or off.
     * 
     * @return true if it is on, otherwise off.
     */
    public boolean isShowDesc() {
        return bShowDesc;
    }

    /**
     * Set the switch of showing description in the problem view.
     * 
     * @param bChecked
     *            true to switch on, false to switch off
     */
    public void setShowDesc(boolean bChecked) {
        bShowDesc = bChecked;
        // saveShowDescriptonPreference();
    }

    protected void loadShowDescriptonPreference() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        bShowDesc = store.getBoolean(SHOW_DESCRIPTION);
    }

    protected void saveShowDescriptonPreference() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(SHOW_DESCRIPTION, isShowDesc());
    }
}
