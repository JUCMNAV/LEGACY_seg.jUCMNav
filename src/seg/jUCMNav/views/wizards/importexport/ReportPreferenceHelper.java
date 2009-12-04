package seg.jUCMNav.views.wizards.importexport;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Manages the preference store relating to the report preferences.
 * 
 * @author dessure
 * 
 */
public class ReportPreferenceHelper {
    public final static int DEFAULTREPORTSCOPE = 0;
    public final static int DEFAULTREPORTYPE = 1;
    public final static String DEFAULTPATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
    //public static final String PREF_REPORTSCOPE = "seg.jUCMNav.CreateReport.ReportScope"; //$NON-NLS-1$
    public final static String PREF_REPORTYPE = "seg.jUCMNav.PDFReport"; //$NON-NLS-1$
    public final static String PREF_PATH = "seg.jUCMNav.CreateReport.Path"; //$NON-NLS-1$
    public final static String PREF_FILENAMEPREFIX = "seg.jUCMNav.CreateReport.Filename"; //$NON-NLS-1$

    /**
     * Sets the default values for the report wizard.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(PREF_PATH, DEFAULTPATH);
    }

    /**
     * 
     * @return the index in the current export type's dropdown list
     */
    public static int getReportType() {
        return getPreferenceStore().getInt(PREF_REPORTYPE);
    }

    /**
     * 
     * @return the folder in which to save the files.
     */
    public static String getPath() {
        return getPreferenceStore().getString(PREF_PATH);
    }

    /**
     * 
     * @return the filename prefix in which to save the files.
     */
    public static String getFilenamePrefix() {
        return getPreferenceStore().getString(PREF_FILENAMEPREFIX);
    }

    /**
     * @return a reference to the preference store.
     */
    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * 
     * @param type
     *            the index in the current export type's dropdown list
     */
    public static void setReportType(int type) {
        getPreferenceStore().setValue(PREF_REPORTYPE, type);
    }

    /**
     * 
     * @param path
     *            the folder in which to save the files.
     */
    public static void setPath(String path) {
        getPreferenceStore().setValue(PREF_PATH, path);
    }

    /**
     * 
     * @param filename
     *            the filename to save URN model
     */
    public static void setFilenamePrefix(String filename) {
        getPreferenceStore().setValue(PREF_FILENAMEPREFIX, filename);
    }
}
