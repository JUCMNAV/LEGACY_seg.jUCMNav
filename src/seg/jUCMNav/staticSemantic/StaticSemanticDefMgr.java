package seg.jUCMNav.staticSemantic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;

/**
 * This class is the control center of all rule defining features:
 * <ul>
 * <li>Rule and RuleGroup manipulation
 * <li>Rule and RuleGroup persistence
 * <li>Rule imports and exports
 * <li>Decision of showing rule description in the problem view
 * </ul>
 * 
 * @author Byrne Yan
 * 
 */
public class StaticSemanticDefMgr extends RuleManagementDefinitionManager {

    private static String RULE_TYPE = "StaticSemantic"; //$NON-NLS-1$
    private static final String SHOW_DESCRIPTION = RULE_TYPE + "SHOW_DESCRIPTION"; //$NON-NLS-1$

    private boolean bShowDesc;
    private static StaticSemanticDefMgr instance_ = null;

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
     * Returns a list of default groups populated with default rules from files.
     */
    protected List getDefaultGroups() {
        List defaultGroups = new ArrayList();

        // Special "All" group, always first
        RuleGroup all = new RuleGroup("All"); //$NON-NLS-1$
        defaultGroups.add(all);

        // Add new default groups here!
        defaultGroups.add(createDefaultGroup("GRL Consistency Completeness", "ConsistencyGRL.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("UCM Consistency Completeness", "ConsistencyUCM.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Features for SPL", "FeaturesSPL.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN iStar Profile", "iStarProfile.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Layout and Overlaps", "Layout.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Legal Compliance Profile", "LegalCompliance.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Styles for Names and Descriptions", "StyleNamesDescriptions.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Unused Elements", "UnusedElements.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Value Ranges", "Ranges.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("Z.151 Import and Export", "Z151.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
        defaultGroups.add(createDefaultGroup("URN Measured Compliance Profile", "MeasuredCompliance.xml", all, StaticSemanticDefMgr.class)); //$NON-NLS-1$ //$NON-NLS-2$
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
