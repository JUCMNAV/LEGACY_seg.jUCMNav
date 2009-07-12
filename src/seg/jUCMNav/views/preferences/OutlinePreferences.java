/**
 * 
 */
package seg.jUCMNav.views.preferences;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Keep the preferences for the outline view.
 * 
 * @author jfroy
 *
 */
public class OutlinePreferences {

    public static final String PREF_SHOWEMPTYPOINT = "PREF_SHOWEMPTYPOINT"; //$NON-NLS-1$
    public static final String PREF_SHOWNODENUMBER = "PREF_SHOWNODENUMBER"; //$NON-NLS-1$
    
    /**
     * Return true if the empty point must be shown in the outline.
     * @return boolean
     */
    public static boolean getShowEmptyPoint()
    {
    	return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_SHOWEMPTYPOINT);
    }
    
    /**
     * Set whether empty point must been shown in the outline.
     */
    public static void setShowEmptyPoint(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_SHOWEMPTYPOINT, value);
    }

    /**
     * Return true if the node number must be shown in the outline.
     * @return boolean
     */
    public static boolean getShowNodeNumber()
    {
    	return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_SHOWNODENUMBER);
    }
    
    /**
     * Set whether node number must been shown in the outline.
     */
    public static void setShowNodeNumber(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_SHOWNODENUMBER, value);
    }
    
}
