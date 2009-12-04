package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for scenario traversal
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public ScenarioTraversalPreferencePage() {
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

        IntegerFieldEditor hitcount = new IntegerFieldEditor(ScenarioTraversalPreferences.PREF_MAXHITCOUNT, Messages
                .getString("ScenarioTraversalPreferencePage.MaximumHitCount"), getFieldEditorParent()); //$NON-NLS-1$
        addField(hitcount);

        BooleanFieldEditor strategyVariables = new BooleanFieldEditor(ScenarioTraversalPreferences.PREF_INTEGRATESTRATEGYVARIABLES, Messages
                .getString("ScenarioTraversalPreferencePage.IntegrateStrategyVariables"), getFieldEditorParent()); //$NON-NLS-1$
        addField(strategyVariables);

        BooleanFieldEditor patient = new BooleanFieldEditor(ScenarioTraversalPreferences.PREF_ISPATIENTONPRECONDITIONS, Messages
                .getString("ScenarioTraversalPreferencePage.IsPatient"), getFieldEditorParent()); //$NON-NLS-1$
        addField(patient);

        BooleanFieldEditor deterministic = new BooleanFieldEditor(ScenarioTraversalPreferences.PREF_ISDETERMINISTIC, Messages
                .getString("ScenarioTraversalPreferencePage.IsDeterministic"), getFieldEditorParent()); //$NON-NLS-1$
        addField(deterministic);

    }

    public void init(IWorkbench workbench) {

    }

}
