/**
 * 
 */
package seg.jUCMNav.views.preferences;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urncore.URNmodelElement;

/**
 * Keep the preferences for the outline view.
 * 
 * @author jfroy, jkealey
 *
 */
public class DisplayPreferences  {

    private static DisplayPreferences instance;
	public static final String PREF_OUTLINE_FILTER = "PREF_OUTLINE_FILTER"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWCONCERNS = "PREF_OUTLINE_SHOWCONCERNS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWEMPTY = "PREF_OUTLINE_SHOWEMPTY"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWFORKJOIN = "PREF_OUTLINE_SHOWFORKJOIN"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWGRLS = "PREF_OUTLINE_SHOWGRLS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWNODENUMBER = "PREF_OUTLINE_SHOWNODENUMBER"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWSTARTEND = "PREF_OUTLINE_SHOWSTARTEND"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWUCMS = "PREF_OUTLINE_SHOWUCMS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SORT = "PREF_OUTLINE_SORT"; //$NON-NLS-1$
    
    
    public static final String SORT_DEFAULT = "SORTDEF"; //$NON-NLS-1$
    public static final String SORT_ID = "SORTID"; //$NON-NLS-1$
    public static final String SORT_NAME = "SORTNAME"; //$NON-NLS-1$
    
    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {
   	
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWNODENUMBER, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWEMPTY, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWFORKJOIN, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWSTARTEND, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWUCMS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWGRLS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWCONCERNS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SORT, SORT_DEFAULT);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_FILTER, "");
    }
    
    public static DisplayPreferences getInstance()
    {
    	if(instance == null)
    	{
    		instance = new DisplayPreferences();
    	}
    	return instance;
    }
    
    private boolean globalFilterEnabled;

	private List listenerViews;

	/**
     * Constructor
     */
    private DisplayPreferences()
    {
    	listenerViews = new ArrayList();
    	setGlobalFilterEnabled(false);
    }
    
    /**
     * Return true if the empty point must be shown in the outline.
     * @return boolean
     */
    public String getFilter()
    {
    	if (getPreferenceStore().getString(PREF_OUTLINE_FILTER)==null)
    		return "";
    	else 
    		return getPreferenceStore().getString(PREF_OUTLINE_FILTER);
    }

   
    public IPreferenceStore getPreferenceStore()
    {
    	return JUCMNavPlugin.getDefault().getPreferenceStore();
    }
    
    /**
     * Return true if the concerns must be shown in the outline.
     * @return boolean
     */
    public boolean getShowConcerns()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWCONCERNS);
    }

    /**
     * Return true if the empty point must be shown in the outline.
     * @return boolean
     */
    public boolean getShowEmptyPoint()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWEMPTY);
    }
    
    /**
     * Return true if forks & joins must be shown in the outline.
     * @return boolean
     */
    public boolean getShowForkJoin()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWFORKJOIN);
    }
    
	/**
     * Return true if the grl elements must be shown in the outline.
     * @return boolean
     */
    public boolean getShowGRLS()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWGRLS);
    }

    /**
     * Return true if the node number must be shown in the outline.
     * @return boolean
     */
    public boolean getShowNodeNumber()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWNODENUMBER);
    }

    /**
     * Return true if the start/end must be shown in the outline.
     * @return boolean
     */
    public boolean getShowStartEnd()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWSTARTEND);
    }
    
    /**
     * Return true if the ucm elements must be shown in the outline.
     * @return boolean
     */
    public boolean getShowUCMS()
    {
    	return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWUCMS);
    }
    
    /**
     * Return true if the empty point must be shown in the outline.
     * @return boolean
     */
    public String getSort()
    {
    	if (getPreferenceStore().getString(PREF_OUTLINE_SORT)==null)
    		return SORT_DEFAULT;
    	else 
    		return getPreferenceStore().getString(PREF_OUTLINE_SORT);
    }
    
    public boolean isGlobalFilterEnabled()
	{
		return globalFilterEnabled;
	}
    
    public void refreshViews()
    {
    	for(Iterator it=listenerViews.iterator(); it.hasNext(); )
    	{
    		JUCMNavRefreshableView currentView = (JUCMNavRefreshableView)it.next();
    		currentView.refreshView();
    	}
    }
    
    public void registerListener(JUCMNavRefreshableView view)
    {
    	listenerViews.add(view);
    }
    
    public void setGlobalFilterEnabled(boolean globalFilterEnabled)
	{
		this.globalFilterEnabled = globalFilterEnabled;
		refreshViews();
	}    
    
    /**
     * Set whether empty point must been shown in the outline.
     */
    public void setShowEmptyPoint(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWEMPTY, value);
        refreshViews();
    }
    /**
     * Should we show GRL elements in the outline.
     * @param value show them?
     */
    public void setShowGRLS(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWGRLS, value);
//      refreshViews();
    }
    
    /**
     * Set whether node number must been shown in the outline.
     */
    public void setShowNodeNumber(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWNODENUMBER, value);
        refreshViews();
        
    }
    
    /**
	 * Should we show UCM elements in the outline. 
	 * @param value show them?
	 */
    public void setShowUCMS(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWUCMS, value);
//      refreshViews();
    }
 
    
    public boolean isElementFiltered(URNmodelElement element)
    {
    	return isElementFiltered(element, true);
    }
    public boolean isElementFiltered(URNmodelElement element, boolean tryName)
    {
    	
    	if (!isGlobalFilterEnabled())
    		return false;
    	
    	if (element instanceof UCMmap && !getShowUCMS())
    		return true;

    	if (element instanceof GRLGraph && !getShowGRLS())
    		return true;

    	if ((element instanceof StartPoint || element instanceof EndPoint) && !getShowStartEnd())
    		return true;

    	if ((element instanceof EmptyPoint || element instanceof DirectionArrow) && !getShowEmptyPoint())
    		return true;

      	if ((element instanceof AndFork || element instanceof OrFork || element instanceof AndJoin || element instanceof OrJoin) && !getShowForkJoin())
    		return true;

    	if (tryName && !URNElementFinder.doesElementMatchPattern(getFilter(), element))
    		return true;
    	
    	return false;
    }
    
    
}
