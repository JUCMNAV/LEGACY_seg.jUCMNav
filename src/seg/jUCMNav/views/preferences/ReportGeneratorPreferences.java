package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.RGB;

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
     * @param color
     *            the color of the report background in the PDF
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

     
    
}
