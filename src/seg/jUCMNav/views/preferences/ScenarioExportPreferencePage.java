package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for scenario export
 * 
 * @author jkealey
 * 
 */
public class ScenarioExportPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public ScenarioExportPreferencePage() {
        super(FieldEditorPreferencePage.GRID);

        // Set the preference store for the preference page.
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        setPreferenceStore(store);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    protected void createFieldEditors() {
        // Initialize all field editors.

        String[][] values = { { Messages.getString("ScenarioExportPreferencePage.ExportJucmScenarios"), "0" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ScenarioExportPreferencePage.ExportLinearizedJucm"), "1" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ScenarioExportPreferencePage.ExportWellFormedJucm"), "2" } //$NON-NLS-1$ //$NON-NLS-2$
        };

        RadioGroupFieldEditor type = new RadioGroupFieldEditor(ScenarioExportPreferences.PREF_EXPORTTYPE, Messages
                .getString("ScenarioExportPreferencePage.ExportType"), 1, values, getFieldEditorParent()); //$NON-NLS-1$
        addField(type);

        String[][] values2 = { { Messages.getString("ScenarioExportPreferencePage.AllScenarios"), "all" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ScenarioExportPreferencePage.LastRunScenario"), "last" } }; //$NON-NLS-1$ //$NON-NLS-2$

        RadioGroupFieldEditor what = new RadioGroupFieldEditor(ScenarioExportPreferences.PREF_EXPORTALL, Messages
                .getString("ScenarioExportPreferencePage.ExportWhat"), 1, values2, getFieldEditorParent()); //$NON-NLS-1$
        addField(what);

        BooleanFieldEditor openAfterExport = new BooleanFieldEditor(ScenarioExportPreferences.PREF_OPENAFTEREXPORT, Messages
                .getString("ScenarioExportPreferencePage.AutoOpenAfterExport"), getFieldEditorParent()); //$NON-NLS-1$
        addField(openAfterExport);

    }

    public void init(IWorkbench workbench) {

    }

}
