/**
 * 
 */
package seg.jUCMNav.views.preferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.JUCMNavRefreshableView;

/**
 * Keep the preferences for the outline view.
 * 
 * @author jfroy
 *
 */
public class DisplayPreferences {

    public static final String PREF_SHOWEMPTYPOINT = "PREF_SHOWEMPTYPOINT"; //$NON-NLS-1$
    public static final String PREF_SHOWNODENUMBER = "PREF_SHOWNODENUMBER"; //$NON-NLS-1$
    
    private List listenerViews;
    
    private static DisplayPreferences instance;
    
    public static DisplayPreferences getInstance()
    {
    	if(instance == null)
    	{
    		instance = new DisplayPreferences();
    	}
    	return instance;
    }
    
    /**
     * Constrcutor
     */
    private DisplayPreferences()
    {
    	listenerViews = new ArrayList();
    }
    
    /**
     * Return true if the empty point must be shown in the outline.
     * @return boolean
     */
    public boolean getShowEmptyPoint()
    {
    	return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_SHOWEMPTYPOINT);
    }
    
    /**
     * Set whether empty point must been shown in the outline.
     */
    public void setShowEmptyPoint(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_SHOWEMPTYPOINT, value);
        refreshViews();
    }

    /**
     * Return true if the node number must be shown in the outline.
     * @return boolean
     */
    public boolean getShowNodeNumber()
    {
    	return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_SHOWNODENUMBER);
    }
    
    /**
     * Set whether node number must been shown in the outline.
     */
    public void setShowNodeNumber(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_SHOWNODENUMBER, value);
        refreshViews();
        
    }
    
    public void registerListener(JUCMNavRefreshableView view)
    {
    	listenerViews.add(view);
    }
    
    private void refreshViews()
    {
    	for(Iterator it=listenerViews.iterator(); it.hasNext(); )
    	{
    		JUCMNavRefreshableView currentView = (JUCMNavRefreshableView)it.next();
    		currentView.refreshView();
    	}
    }
    
}
