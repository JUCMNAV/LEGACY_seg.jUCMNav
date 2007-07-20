package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for kpi monitoring
 * 
 * @author pchen
 * 
 */
public class KPIMonitoringPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public KPIMonitoringPreferencePage() {
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

        StringFieldEditor hitcount = new StringFieldEditor(KPIMonitoringPreferences.PREF_WEBSERVICEADDRESS, Messages
                .getString("KPIMonitoringPreferencePage.WebServiceAddress"), getFieldEditorParent()); //$NON-NLS-1$
        addField(hitcount);

    }

    public void init(IWorkbench workbench) {

    }

}
