package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Encapsulates load/save of the scenario export properties.
 * 
 * @author jkealey
 * 
 */
public class ScenarioExportPreferences {

    public final static String DEFAULT_EXPORTTYPE = "0"; //$NON-NLS-1$
    public final static String DEFAULT_EXPORTALL = "all"; //$NON-NLS-1$
    public final static boolean DEFAULT_OPENAFTEREXPORT = true;

    public final static String PREF_EXPORTTYPE = "seg.jUCMNav.ScenarioExport.ExportType"; //$NON-NLS-1$
    public final static String PREF_EXPORTALL = "seg.jUCMNav.ScenarioExport.ExportAll"; //$NON-NLS-1$
    public final static String PREF_OPENAFTEREXPORT = "seg.jUCMNav.ScenarioExport.OpenAfterExport"; // $NON-NLS-1$ //$NON-NLS-1$

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
        getPreferenceStore().setDefault(ScenarioExportPreferences.PREF_EXPORTTYPE, ScenarioExportPreferences.DEFAULT_EXPORTTYPE);
        getPreferenceStore().setDefault(ScenarioExportPreferences.PREF_EXPORTALL, ScenarioExportPreferences.DEFAULT_EXPORTALL);
        getPreferenceStore().setDefault(ScenarioExportPreferences.PREF_OPENAFTEREXPORT, ScenarioExportPreferences.DEFAULT_OPENAFTEREXPORT);
    }

    /**
     * 
     * 0: .jucmnavscenarios file 1: non-well formed linearization 2: well-formed linearization
     * 
     * @return the export type
     */
    public static String getExportType() {
        return getPreferenceStore().getString(PREF_EXPORTTYPE);
    }

    /**
     * 
     * @return should we export all scenarios (all) or the last run (last)
     */
    public static String getExportAll() {
        return getPreferenceStore().getString(PREF_EXPORTALL);
    }

    /**
     * 
     * @return should we open the editor after exporting
     */
    public static boolean getShouldOpenEditorAfterExport() {
        return getPreferenceStore().getBoolean(PREF_OPENAFTEREXPORT);
    }

    /**
     * 0: .jucmnavscenarios file 1: non-well formed linearization 2: well-formed linearization
     * 
     * @param type
     *            the export type
     */
    public static void setExportType(String type) {
        getPreferenceStore().setValue(PREF_EXPORTTYPE, type);
    }

    /**
     * 
     * @param what
     *            should we export all scenarios (all) or the last run (last)
     */
    public static void setExportAll(String what) {
        getPreferenceStore().setValue(PREF_EXPORTALL, what);
    }

    /**
     * 
     * @param b
     *            should we open the editor after exporting
     */
    public static void setShouldOpenEditorAfterExport(boolean b) {
        getPreferenceStore().setValue(PREF_OPENAFTEREXPORT, b);
    }

}
