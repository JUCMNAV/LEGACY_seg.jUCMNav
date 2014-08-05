package seg.jUCMNav.views.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.figures.ColorManager;

/**
 * Class used to initialize default preference values.
 * 
 * @author jkealey, gunterm, pchen
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_STUBLABELCOLOR, ColorManager.PURPLE.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_CONDITIONLABELCOLOR, ColorManager.VERYDARKGRAY.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_LINKREFLABELCOLOR, ColorManager.BLACK.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_KPIMODELLINKREFLABELCOLOR, ColorManager.DARKGRAY.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_FILLCOLOR, ColorManager.WHITE.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_LINECOLOR, ColorManager.BLACK.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_SELECTEDCOLOR, ColorManager.BLUE.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_HOVERCOLOR, ColorManager.LIGHTGRAY.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_TRAVERSALCOLOR, ColorManager.RED.getRGB());
        PreferenceConverter.setDefault(store, ColorManagementPreferencePage.PREF_POINTCUTBORDERCOLOR, ColorManager.DARKGRAY.getRGB());

        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_AUTHOR, System.getProperty("user.name")); //$NON-NLS-1$
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_DISTRIBUTE_SPACING, GeneralPreferencePage.DEFAULT_DISTRIBUTE_SPACING); //$NON-NLS-1$
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_STRICTCODEEDITOR, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_METADATAINDVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_ANTIALIASING, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_GRLICONVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_GRLTEXTVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_GRLAUTOADDLINKS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_GRLSATISFACTIONICONVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_GRLSATISFACTIONTEXTVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_UCMEMPTYPOINTVISIBLE, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_UCMSTUBLABELVISIBLE, true);
        
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_NEWGRL, false);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_NEWFMD, false);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_NEWUCM, true);

        // done elsewhere
        AutoLayoutPreferences.createPreferences();
        ScenarioTraversalPreferences.createPreferences();
        StrategyEvaluationPreferences.createPreferences();
        ScenarioExportPreferences.createPreferences();
        KPIMonitoringPreferences.createPreferences();
        ReportGeneratorPreferences.createPreferences();
        DeletePreferences.createPreferences();
        DisplayPreferences.createPreferences();
    }

}
