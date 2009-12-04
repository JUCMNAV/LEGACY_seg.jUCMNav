package seg.jUCMNav.views.wizards.importexport;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Manages the preference store relating to the export preferences.
 * 
 * @author jkealey
 * 
 */
public class ExportPreferenceHelper {
    public final static int DEFAULTEXPORTTYPE = 0;
    public final static int DEFAULTIMAGETYPE = 1;
    public final static String DEFAULTPATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
    public static final String PREF_EXPORTTYPE = "seg.jUCMNav.ExportImage.ExportType"; //$NON-NLS-1$
    public final static String PREF_IMAGETYPE = "seg.jUCMNav.ExportImage.ImageType"; //$NON-NLS-1$
    public final static String PREF_PATH = "seg.jUCMNav.ExportImage.Path"; //$NON-NLS-1$
    public final static String PREF_FILENAMEPREFIX = "seg.jUCMNav.ExportImage.Filename"; //$NON-NLS-1$

    // values of PREF_EXPORTTYPE
    public static final int URN_DIAGRAM = 0;
    public static final int URN = 1;

    /**
     * Sets the default values for the export wizard.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(PREF_PATH, DEFAULTPATH);
        getPreferenceStore().setDefault(PREF_IMAGETYPE, DEFAULTIMAGETYPE);
        getPreferenceStore().setDefault(PREF_EXPORTTYPE, DEFAULTEXPORTTYPE);
    }

    /**
     * @return the current export type, either UCM or URN
     */
    public static int getExportType() {
        return getPreferenceStore().getInt(PREF_EXPORTTYPE);
    }

    /**
     * 
     * @return the index in the current export type's dropdown list
     */
    public static int getImageType() {
        return getPreferenceStore().getInt(PREF_IMAGETYPE);
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
     * @param type
     *            either UCM or URN
     */
    public static void setExportType(int type) {
        getPreferenceStore().setValue(PREF_EXPORTTYPE, type);
    }

    /**
     * 
     * @param type
     *            the index in the current export type's dropdown list
     */
    public static void setImageType(int type) {
        getPreferenceStore().setValue(PREF_IMAGETYPE, type);
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
