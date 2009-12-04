package seg.jUCMNav.views.wizards.importexport;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Manages the preference store relating to the import preferences.
 * 
 * @author jkealey
 * 
 */
public class ImportPreferenceHelper {
    public final static int DEFAULTIMPORTTYPE = 0;
    public static final String PREF_IMPORTTYPE = "seg.jUCMNav.ExportImage.ExportType"; //$NON-NLS-1$
    public final static String DEFAULTPATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
    public final static String PREF_AUTOLAYOUT = "seg.jUCMNav.ImportUCM.AutoLayout"; //$NON-NLS-1$
    public final static String PREF_PATH = "seg.jUCMNav.ImportUCM.Path"; //$NON-NLS-1$
    public final static String PREF_SAVEPATH = "seg.jUCMNav.ImportUCM.SavePath"; //$NON-NLS-1$    
    public final static String PREF_PROJECT = "seg.jUCMNav.ImportUCM.Project"; //$NON-NLS-1$
    public final static String PREF_TYPE = "seg.jUCMNav.ImportUCM.Type"; //$NON-NLS-1$

    // values of PREF_IMPORTTYPE
    public static final int IMPORT_NEWFILE = 0;
    public static final int IMPORT_URN = 1;

    /**
     * Sets the default values for the export wizard.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(PREF_PATH, DEFAULTPATH);
        getPreferenceStore().setDefault(PREF_IMPORTTYPE, DEFAULTIMPORTTYPE);
    }

    /**
     * 
     * @return should the file be automatically positioned after load?
     */
    public static boolean getAutoLayout() {
        return getPreferenceStore().getBoolean(PREF_AUTOLAYOUT);
    }

    /**
     * @return the current import type
     */
    public static int getImportType() {
        return getPreferenceStore().getInt(PREF_IMPORTTYPE);
    }

    /**
     * 
     * @return the file from which to load the files.
     */
    public static String getPath() {
        return getPreferenceStore().getString(PREF_PATH);
    }

    /**
     * @return a reference to the preference store.
     */
    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * 
     * @return the project in which to load the files
     */
    public static String getProject() {
        return getPreferenceStore().getString(PREF_PROJECT);
    }

    /**
     * 
     * @return the file from which to save the files.
     */
    public static String getSavePath() {
        return getPreferenceStore().getString(PREF_SAVEPATH);
    }

    /**
     * 
     * @return the type of files loaded
     */
    public static int getType() {
        return getPreferenceStore().getInt(PREF_TYPE);
    }

    /**
     * 
     * @param autolayout
     *            should the file be automatically positioned after load?
     */
    public static void setAutoLayout(boolean autolayout) {
        getPreferenceStore().setValue(PREF_AUTOLAYOUT, autolayout);
    }

    /**
     * @param type
     *            either newfile or in urn
     */
    public static void setImportType(int type) {
        getPreferenceStore().setValue(PREF_IMPORTTYPE, type);
    }

    /**
     * 
     * @param path
     *            the file from which to load the files.
     */
    public static void setPath(String path) {
        getPreferenceStore().setValue(PREF_PATH, path);
    }

    /**
     * 
     * @param path
     *            the project in which to load the files
     */
    public static void setProject(String path) {
        getPreferenceStore().setValue(PREF_PROJECT, path);
    }

    /**
     * 
     * @param path
     *            the file from which to load the files.
     */
    public static void setSavePath(String path) {
        getPreferenceStore().setValue(PREF_SAVEPATH, path);
    }

    /**
     * 
     * @param type
     *            the type of files to be loaded
     */
    public static void setType(int type) {
        getPreferenceStore().setValue(PREF_TYPE, type);
    }
}
