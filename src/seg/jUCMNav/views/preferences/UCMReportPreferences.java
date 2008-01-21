package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.RGB;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * General preferences page for UCM reports. 
 * 
 * @author Alain Dessureault
 * 
 */
public class UCMReportPreferences {
    
    public final static boolean DEFAULTUCMSHOWDESC = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWRESPONSIBILITY = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWSTUB = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWORFORK = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWANDFORK = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWSTARTPOINT = true; //$NON-NLS-1$
    public final static boolean DEFAULTUCMSHOWENDPOINT = true; //$NON-NLS-1$


    public final static String PREF_UCMSHOWDESC = "seg.jUCMNav.UCMReportPreference.UCMSHOWDESC"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWRESPONSIBILITY = "seg.jUCMNav.UCMReportPreference.UCMSHOWRESPONSIBILITY"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWSTUB = "seg.jUCMNav.UCMReportPreference.UCMSHOWSTUB"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWORFORK = "seg.jUCMNav.UCMReportPreference.OrFork"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWANDFORK = "seg.jUCMNav.UCMReportPreference.AndFork"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWSTARTPOINT = "seg.jUCMNav.UCMReportPreference.StartPoint"; //$NON-NLS-1$
    public final static String PREF_UCMSHOWENDPOINT = "seg.jUCMNav.UCMReportPreference.EndPoint"; //$NON-NLS-1$


    
    /**
     * 
     * @return Preference store where the properties are stored.
     */
    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }
    
    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWDESC, UCMReportPreferences.DEFAULTUCMSHOWDESC);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWRESPONSIBILITY, UCMReportPreferences.DEFAULTUCMSHOWRESPONSIBILITY);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWSTUB, UCMReportPreferences.DEFAULTUCMSHOWSTUB);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWORFORK, UCMReportPreferences.DEFAULTUCMSHOWORFORK);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWORFORK, UCMReportPreferences.DEFAULTUCMSHOWANDFORK);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWSTARTPOINT, UCMReportPreferences.DEFAULTUCMSHOWSTARTPOINT);
        getPreferenceStore().setDefault(UCMReportPreferences.PREF_UCMSHOWENDPOINT, UCMReportPreferences.DEFAULTUCMSHOWENDPOINT);
     }

   
    /**
     * 
     * @return the show ucm description 
     */
    public static boolean getUCMSHOWDESC() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWDESC);
    }

    /**
     * 
     * @return the show responsibility node
     */
    public static boolean getUCMSHOWRESPONSIBILITY() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWRESPONSIBILITY);
    }

    /**
     * 
     * @return the show stub node
     */
    public static boolean getUCMSHOWSTUB() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWSTUB);
    }

    /**
     * 
     * @return the show OrFork node 
     */
    public static boolean getUCMSHOWORFORK() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWORFORK);
    }

    /**
     * 
     * @return the show AndFork node 
     */
    public static boolean getAndForkOrder() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWANDFORK);
    }

    /**
     * 
     * @return the show StartPoint node
     */
    public static boolean getUCMSHOWSTARTPOINT() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWSTARTPOINT);
    }
    
    /**
     * 
     * @return the show EndPoint node
     */
    public static boolean getUCMSHOWENDPOINT() {
        return getPreferenceStore().getBoolean(PREF_UCMSHOWENDPOINT);
    }

    

    /**
     * 
     * @param bool
     *            show or not the description node type 
     */
    public static void setUCMSHOWDESC(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWDESC, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the responsibility node type 
     */
    public static void setUCMSHOWRESPONSIBILITY(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWRESPONSIBILITY, bool);
    }
    /**
     * 
     * @param bool
     *            show or not the stub node type 
     */
    public static void setUCMSHOWSTUB(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWSTUB, bool);
    }
    
    /**
     * 
     * @param bool
     *            show or not the OrFork node type 
     */
    public static void setOrFork(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWORFORK, bool);
    }
    
    /**
     * 
     * @param bool
     *            show or not the AndFork node type 
     */
    public static void setAndFork(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWANDFORK, bool);
    }
    
    /**
     * 
     * @param bool
     *            tshow or not the SartPoint node type 
     */
    public static void setStartPoint(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWSTARTPOINT, bool);
    }
    
    
    /**
     * 
     * @param bool
     *           show or not the EndPoint node type 
     */
    public static void setEndPoint(boolean bool) {
        getPreferenceStore().setValue(PREF_UCMSHOWENDPOINT, bool);
    }
    
  
    

     
    
}
