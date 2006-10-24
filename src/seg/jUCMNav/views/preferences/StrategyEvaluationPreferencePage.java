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
 * Preference page for strategy evaluation
 * 
 * @author jkealey
 * 
 */
public class StrategyEvaluationPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public StrategyEvaluationPreferencePage() {
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
        IntegerFieldEditor tolerance = new IntegerFieldEditor(StrategyEvaluationPreferences.PREF_TOLERANCE, Messages.getString("GeneralPreferencePage.GRLEvaluationAlgorithmTolerance"), getFieldEditorParent());  //$NON-NLS-1$
        addField(tolerance);
        
        BooleanFieldEditor eval_filled = new BooleanFieldEditor(StrategyEvaluationPreferences.PREF_EVALFILLED, Messages.getString("GeneralPreferencePage.GrlStrategiesElementFilled"), getFieldEditorParent());  //$NON-NLS-1$
        addField(eval_filled);
        
    }

    public void init(IWorkbench workbench) {

    }

}
