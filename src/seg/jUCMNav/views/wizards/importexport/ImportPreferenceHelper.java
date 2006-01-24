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
    public final static String DEFAULTPATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
    public final static String PREF_AUTOLAYOUT = "seg.jUCMNav.ImportUCM.AutoLayout"; //$NON-NLS-1$
    public final static String PREF_PATH = "seg.jUCMNav.ImportUCM.Path"; //$NON-NLS-1$
    public final static String PREF_PROJECT = "seg.jUCMNav.ImportUCM.Project"; //$NON-NLS-1$
    public final static String PREF_TYPE = "seg.jUCMNav.ImportUCM.Type"; //$NON-NLS-1$

    /**
     * Sets the default values for the export wizard.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(PREF_PATH, DEFAULTPATH);
    }

    /**
     * 
     * @return should the file be automatically positioned after load?
     */
    public static boolean getAutoLayout() {
        return getPreferenceStore().getBoolean(PREF_AUTOLAYOUT);
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
     * @param type
     *            the type of files to be loaded
     */
    public static void setType(int type) {
        getPreferenceStore().setValue(PREF_TYPE, type);
    }
}
