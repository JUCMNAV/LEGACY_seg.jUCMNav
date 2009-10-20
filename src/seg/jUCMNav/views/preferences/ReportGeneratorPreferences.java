package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * General preferences page for the Report Generator. 
 * 
 * @author Alain Dessureault
 * 
 */
public class ReportGeneratorPreferences {
    
    public final static double DEFAULTREPORTHEIGHT = 11;
    public final static String DEFAULTREPORTORIENTATION = "TD"; //$NON-NLS-1$
    //TODO change default color
    public final static String DEFAULTREPORTCOLOR = "white"; //$NON-NLS-1$
    public final static double DEFAULTREPORTWIDTH = 8.5;
    public final static String PREF_REPORTHEIGHT = "seg.jUCMNav.ReportPreference.Height"; //$NON-NLS-1$
    public final static String PREF_REPORTORIENTATION = "seg.jUCMNav.ReportPreference.Orientation"; //$NON-NLS-1$
    public final static String PREF_REPORTCOLOR="seg.jUCMNav.ReportPreference.Color";//$NON-NLS-1$
    public final static String PREF_REPORTWIDTH = "seg.jUCMNav.ReportPreference.Width"; //$NON-NLS-1$
    
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
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORTORIENTATION, ReportGeneratorPreferences.DEFAULTREPORTORIENTATION);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORTWIDTH, ReportGeneratorPreferences.DEFAULTREPORTWIDTH);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORTHEIGHT, ReportGeneratorPreferences.DEFAULTREPORTHEIGHT);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORTCOLOR, ReportGeneratorPreferences.DEFAULTREPORTCOLOR);

        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWDESC, ReportGeneratorPreferences.DEFAULTUCMSHOWDESC);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWRESPONSIBILITY, ReportGeneratorPreferences.DEFAULTUCMSHOWRESPONSIBILITY);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWSTUB, ReportGeneratorPreferences.DEFAULTUCMSHOWSTUB);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWORFORK, ReportGeneratorPreferences.DEFAULTUCMSHOWORFORK);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWORFORK, ReportGeneratorPreferences.DEFAULTUCMSHOWANDFORK);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWSTARTPOINT, ReportGeneratorPreferences.DEFAULTUCMSHOWSTARTPOINT);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCMSHOWENDPOINT, ReportGeneratorPreferences.DEFAULTUCMSHOWENDPOINT);
    }

   
    /**
     * 
     * @return the height parameter to give dot
     */
    public static String getHeight() {
        return getPreferenceStore().getString(PREF_REPORTHEIGHT);
    }

    /**
     * 
     * @return the orientation (TB, LR)
     */
    public static String getOrientation() {
        return getPreferenceStore().getString(PREF_REPORTORIENTATION);
    }

    /**
     * 
     * @return  the color of the report background in the PDF
     */
    public static String getReportColor() {
        return getPreferenceStore().getString(PREF_REPORTCOLOR);
    }
    /**
     * 
     * @return the width parameter to give dot
     */
    public static String getWidth() {
        return getPreferenceStore().getString(PREF_REPORTWIDTH);
    }

    
    /**
     * 
     * @param height
     *            the height parameter to give to report
     */
    public static void setHeight(String height) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(height);
            s = Double.toString(d);
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }

        getPreferenceStore().setValue(PREF_REPORTHEIGHT, s);
    }

    /**
     * 
     * @param str
     *            the orientation (TB, LR)
     */
    public static void setOrientation(String str) {
        getPreferenceStore().setValue(PREF_REPORTORIENTATION, str);
    }

    /**
     * 
     * @param color
     *            the color of the report background in the PDF
     */
    public static void setReportColor(String color) {
        getPreferenceStore().setValue(PREF_REPORTCOLOR, color);
    }
   
    
    /**
     * 
     * @param width
     *            the width parameter to give dot
     */
    public static void setWidth(String width) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(width);
            s = Double.toString(d);
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }

        getPreferenceStore().setValue(PREF_REPORTWIDTH, s);
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
     *            show or not the StartPoint node type 
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
