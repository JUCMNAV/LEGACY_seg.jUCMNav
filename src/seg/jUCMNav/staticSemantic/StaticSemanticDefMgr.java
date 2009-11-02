package seg.jUCMNav.staticSemantic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.rulemanagement.RuleManagementUtil;
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
public class StaticSemanticDefMgr extends RuleManagementDefinitionManager{
	 
	 private static String RULE_TYPE = "StaticSemantic" ; //$NON-NLS-1$
	 private static String DEFAULT_STATIC_SEMANTIC_RULE_FILE = "defaultRules.xml"; //$NON-NLS-1$
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
    
    protected RuleManagementDefinitionManager getDefferManagerInstance(){
    	return instance();
    }
    
    protected String getRuleType() {
		
		return RULE_TYPE;
	}
	
    
    /**
     * Load default rule definitions from the file "defaultRules.xml".
     * @return a list of rules
     */
    public List getDefaultDefinitions() {
        InputStream rulesDefaultIS = StaticSemanticDefMgr.class.getResourceAsStream(DEFAULT_STATIC_SEMANTIC_RULE_FILE); //$NON-NLS-1$

        return RuleManagementUtil.readRules(rulesDefaultIS);
    }

	
	 /**
     * Returns a list of default groups.
     */
    protected List getDefaultGroups() {
        List dg = new ArrayList();
        RuleGroup g = new RuleGroup("All"); //$NON-NLS-1$
        g.addRule(rules);
        dg.add(g);
        
        dg.add(new RuleGroup("Aspect")); //$NON-NLS-1$
        dg.add(new RuleGroup("Consistency")); //$NON-NLS-1$
        dg.add(new RuleGroup("Performance")); //$NON-NLS-1$
        dg.add(new RuleGroup("Standard URN (Z.151)")); //$NON-NLS-1$
        dg.add(new RuleGroup("Style")); //$NON-NLS-1$
        return dg;
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
        //saveShowDescriptonPreference();
    }
    
    protected void loadShowDescriptonPreference(){
    	IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        bShowDesc =  store.getBoolean(SHOW_DESCRIPTION);     	
    }
    
    protected void saveShowDescriptonPreference(){
		IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        store.setValue(SHOW_DESCRIPTION, isShowDesc()); 
	}

}
