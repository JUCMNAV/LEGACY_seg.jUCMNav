package seg.jUCMNav.metrics;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.rulemanagement.RuleManagementUtil;


/**
 * 
 * @author Anisur Rahman
 *
 */
public class MetricsDefinitionManager extends RuleManagementDefinitionManager {

	private static String RULE_TYPE = "Metrics" ;
	private static String DEFAULT_MATRICS_RULE_FILE = "defaultMetricsRules.xml";
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

	protected RuleManagementDefinitionManager  getDefferManagerInstance(){
		return instance();
	}

	protected String getRuleType() {

		return RULE_TYPE;
	}


	/**
	 * Load default rule definitions from the file "defaultMatricsRules.xml".
	 * @return a list of rules
	 */
	public List getDefaultDefinitions() {
		InputStream rulesDefaultIS = MetricsDefinitionManager.class.getResourceAsStream(DEFAULT_MATRICS_RULE_FILE ); //$NON-NLS-1$

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

		//TODO: add more default groups
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
       // saveShowDescriptonPreference();
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
