package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
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
 * @author jkealey, sghanava
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
    	String[][] algos = new String[StrategyEvaluationPreferences.NUM_ALGORITHMS][1];
    	algos[StrategyEvaluationPreferences.ROY_2006_GRL_ALGORITHM] = new String[]{Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.ROY_2006_GRL_ALGORITHM"), (StrategyEvaluationPreferences.ROY_2006_GRL_ALGORITHM+"")};
        algos[StrategyEvaluationPreferences.QUALITATIVE_ALGORITHM] = new String[]{Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.QualitativeGRL"), (StrategyEvaluationPreferences.QUALITATIVE_ALGORITHM+"")};
        algos[StrategyEvaluationPreferences.QUANTITATIVE_ALGORITHM] = new String[]{Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.QuantitativeGRL"), (StrategyEvaluationPreferences.QUANTITATIVE_ALGORITHM+"")};
        algos[StrategyEvaluationPreferences.MIXED_ALGORITHM] = new String[]{Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.MixedGRL"), (StrategyEvaluationPreferences.MIXED_ALGORITHM+"")};
    	
    	ComboFieldEditor pref_algorithm = new ComboFieldEditor(StrategyEvaluationPreferences.PREF_ALGORITHM, Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm"), algos, getFieldEditorParent());  //$NON-NLS-1$
        addField(pref_algorithm);

        IntegerFieldEditor tolerance = new IntegerFieldEditor(StrategyEvaluationPreferences.PREF_TOLERANCE, Messages.getString("GeneralPreferencePage.GRLEvaluationAlgorithmTolerance"), getFieldEditorParent());  //$NON-NLS-1$
        addField(tolerance);
        
        BooleanFieldEditor eval_filled = new BooleanFieldEditor(StrategyEvaluationPreferences.PREF_EVALFILLED, Messages.getString("GeneralPreferencePage.GrlStrategiesElementFilled"), getFieldEditorParent());  //$NON-NLS-1$
        addField(eval_filled);
        
    }

    public void init(IWorkbench workbench) {

    }

}
