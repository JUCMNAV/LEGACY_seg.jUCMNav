package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for strategy evaluation
 * 
 * @author jkealey, sghanava, gunterm
 * 
 */
public class StrategyEvaluationPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private BooleanFieldEditor autoSelectMandatoryFeatures;

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
        algos[StrategyEvaluationPreferences.QUALITATIVE_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.QualitativeGRL"), (StrategyEvaluationPreferences.QUALITATIVE_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        algos[StrategyEvaluationPreferences.QUANTITATIVE_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.QuantitativeGRL"), (StrategyEvaluationPreferences.QUANTITATIVE_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        algos[StrategyEvaluationPreferences.MIXED_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.MixedGRL"), (StrategyEvaluationPreferences.MIXED_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        algos[StrategyEvaluationPreferences.FORMULA_BASED_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.FormulaBasedGRL"), (StrategyEvaluationPreferences.FORMULA_BASED_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        algos[StrategyEvaluationPreferences.CONDITIONAL_GRL_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.ConditionalGRLAlgorithm"), (StrategyEvaluationPreferences.CONDITIONAL_GRL_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        algos[StrategyEvaluationPreferences.CONSTRAINT_SOLVER_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.HAO_2011_GRL_ALGORITHM"), (StrategyEvaluationPreferences.CONSTRAINT_SOLVER_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        // Feature Model Diagram specific
        algos[StrategyEvaluationPreferences.FEATURE_MODEL_ALGORITHM] = new String[] {
                Messages.getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm.FeatureModelStrategyAlgorithm"), (StrategyEvaluationPreferences.FEATURE_MODEL_ALGORITHM + "") }; //$NON-NLS-1$ //$NON-NLS-2$
        
        ComboFieldEditor pref_algorithm = new ComboFieldEditor(StrategyEvaluationPreferences.PREF_ALGORITHM, Messages
                .getString("GeneralPreferencePage.GrlStrategiesElementAlgorithm"), algos, getFieldEditorParent()); //$NON-NLS-1$
        addField(pref_algorithm);

        IntegerFieldEditor tolerance = new IntegerFieldEditor(StrategyEvaluationPreferences.PREF_TOLERANCE, Messages
                .getString("GeneralPreferencePage.GRLEvaluationAlgorithmTolerance"), getFieldEditorParent()); //$NON-NLS-1$
        addField(tolerance);

        BooleanFieldEditor eval_filled = new BooleanFieldEditor(StrategyEvaluationPreferences.PREF_EVALFILLED, Messages
                .getString("GeneralPreferencePage.GrlStrategiesElementFilled"), getFieldEditorParent()); //$NON-NLS-1$
        addField(eval_filled);
        
        BooleanFieldEditor visualize_as_positive = new BooleanFieldEditor(StrategyEvaluationPreferences.PREF_VISUALIZEASPOSITIVERANGE, Messages.getString("StrategyEvaluationPreferencePage.VisualizeAsZeroToHundred"), getFieldEditorParent());  //$NON-NLS-1$
        addField(visualize_as_positive);
        
        autoSelectMandatoryFeatures = new BooleanFieldEditor(StrategyEvaluationPreferences.PREF_AUTOSELECTMANDATORYFEATURES, 
        		Messages.getString("StrategyEvaluationPreferencePage.AutoSelectMandatoryFeatures"), getFieldEditorParent()); //$NON-NLS-1$
        addField(autoSelectMandatoryFeatures);
        
    	String algoChoice = StrategyEvaluationPreferences.getAlgorithm();
		if (algoChoice.equals(Integer.toString(StrategyEvaluationPreferences.FEATURE_MODEL_ALGORITHM))) {
			autoSelectMandatoryFeatures.setEnabled(true, getFieldEditorParent());
		} else {
			autoSelectMandatoryFeatures.setEnabled(false, getFieldEditorParent());
		}

    }

    public void init(IWorkbench workbench) {

    }
    
    /**
     * overwrite parent method
     * The field editor preference page implementation of this <code>IPreferencePage</code>
     * (and <code>IPropertyChangeListener</code>) method intercepts <code>IS_VALID</code> 
     * events but passes other events on to its superclass.
     */
    public void propertyChange(PropertyChangeEvent event) {
    	super.propertyChange(event);
    	if (event.getSource() instanceof FieldEditor) {
    		FieldEditor fieldEditor = (FieldEditor) event.getSource();
    		// user changed strategy event
    		if (fieldEditor.getPreferenceName().equals(StrategyEvaluationPreferences.PREF_ALGORITHM)) {
    			if (event.getNewValue().equals(Integer.toString(StrategyEvaluationPreferences.FEATURE_MODEL_ALGORITHM))) {
    				autoSelectMandatoryFeatures.setEnabled(true, getFieldEditorParent());
    			} else {
    				autoSelectMandatoryFeatures.setEnabled(false, getFieldEditorParent());
    			}
    		}
    	}
    }

}
