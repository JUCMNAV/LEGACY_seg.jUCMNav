package seg.jUCMNav.views.preferences;


import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;

import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for scenario export
 * 
 * @author Alain Dessureault
 * 
 */
public class ReportGeneratorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	Label labelWarning;
	boolean isUCMPrefsSelected;
	boolean isGRLPrefsSelected;
	
    public ReportGeneratorPreferencePage() {
        super(FieldEditorPreferencePage.GRID);

        // Initialize isUCMPrefsSelected and isGRLPrefsSelected flags to true.
        isUCMPrefsSelected = true;
        isGRLPrefsSelected = true;
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

    	Label formatPreferencesLabel = new Label(getFieldEditorParent(), SWT.LEFT);
        formatPreferencesLabel.setText(Messages.getString("ReportGeneratorPreferencePage.ReportFormatPreferences")); //$NON-NLS-1$
        
        Label formatPreferencesLabel2 = new Label(getFieldEditorParent(), SWT.LEFT);
        formatPreferencesLabel2.setText(""); //$NON-NLS-1$
        
        StringFieldEditor width = new StringFieldEditor(ReportGeneratorPreferences.PREF_REPORT_WIDTH,
                Messages.getString("ReportGeneratorPreferencePage.width"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(width);

        StringFieldEditor height = new StringFieldEditor(ReportGeneratorPreferences.PREF_REPORT_HEIGHT, Messages
                .getString("ReportGeneratorPreferencePage.height"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(height);
        
        StringFieldEditor numberCSVcolumns = new StringFieldEditor(ReportGeneratorPreferences.PREF_NUMBER_CSV_COLUMNS, Messages
                .getString("ReportGeneratorPreferencePage.numberCSVcolumns"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(numberCSVcolumns);

        String[][] values = { { Messages.getString("ReportGeneratorPreferencePage.topdown"), "TB" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ReportGeneratorPreferencePage.leftright"), "LR" } }; //$NON-NLS-1$ //$NON-NLS-2$

        RadioGroupFieldEditor orientation = new RadioGroupFieldEditor(ReportGeneratorPreferences.PREF_REPORT_ORIENTATION, Messages
                .getString("ReportGeneratorPreferencePage.orientation"), 1, values, getFieldEditorParent()); //$NON-NLS-1$
        addField(orientation);

        // pdf background color not used in the report generation...
        //ColorFieldEditor reportColor = new ColorFieldEditor(ReportGeneratorPreferences.PREF_REPORT_COLOR, Messages
        //        .getString("ReportGeneratorPreferencePage.PDFBackgroundColor"), getFieldEditorParent()); //$NON-NLS-1$
        //addField(reportColor);

        // separator line
        GridData g = new GridData(GridData.FILL_HORIZONTAL);
        g.horizontalSpan = 2;
        Label separator = new Label (getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(g);
        separator.setVisible(true);
        
        Label urnPreferencesLabel = new Label(getFieldEditorParent(), SWT.LEFT);
        urnPreferencesLabel.setText(Messages.getString("ReportGeneratorPreferencePage.URNModelPrefs")); //$NON-NLS-1$
        
        BooleanFieldEditor showUCMDesc = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_DESC, Messages
                .getString("ReportGeneratorPreferencePage.descOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMDesc);

        
        BooleanFieldEditor ShowURNLinks = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_SHOW_URN_LINKS, Messages
                .getString("ReportGeneratorPreferencePage.urnLinks"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowURNLinks);
        
        // Group for UCM Preferences
        Group ucmDisplayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        ucmDisplayOptions.setText(Messages.getString("ReportGeneratorPreferencePage.UCMPreferences")); //$NON-NLS-1$
        GridLayout ucmLayoutOptions = new GridLayout();
        ucmLayoutOptions.numColumns = 1;
        ucmDisplayOptions.setLayout(ucmLayoutOptions);
        
        GridData ucmDataOptions = new GridData(SWT.FILL);
        ucmDataOptions.horizontalSpan = 2;
        ucmDataOptions.horizontalAlignment = GridData.FILL_HORIZONTAL;
        ucmDataOptions.grabExcessHorizontalSpace = true;
        ucmDisplayOptions.setLayoutData(ucmDataOptions);
        
        Composite ucmCompOptions = new Composite(ucmDisplayOptions, SWT.NONE);
        ucmDataOptions = new GridData(SWT.FILL);
        ucmDataOptions.horizontalAlignment = GridData.FILL_HORIZONTAL;
        ucmDataOptions.grabExcessHorizontalSpace = true;
        ucmDataOptions.horizontalIndent = 20;
        ucmCompOptions.setLayoutData(ucmDataOptions);
        
        BooleanFieldEditor showUCMDiagrams = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_UCM_DIAGRAMS, Messages.getString("ReportGeneratorPreferencePage.ShowUCMDiagrams"), //$NON-NLS-1$
        		ucmCompOptions);
        addField(showUCMDiagrams);
        

        BooleanFieldEditor showUCMResponsibility = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_RESPONSIBILITY, Messages
                .getString("ReportGeneratorPreferencePage.respOrder"), //$NON-NLS-1$
                ucmCompOptions);
        addField(showUCMResponsibility);

        BooleanFieldEditor showUCMStub = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_STUB, Messages
                .getString("ReportGeneratorPreferencePage.stubOrder"), //$NON-NLS-1$
                ucmCompOptions);
        addField(showUCMStub);

        BooleanFieldEditor showUCMOrFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_OR_FORK, Messages
                .getString("ReportGeneratorPreferencePage.orFork"), //$NON-NLS-1$
                ucmCompOptions);
        addField(showUCMOrFork);
        
        // disabled as And Forks currently don't appear in any reports
        //BooleanFieldEditor ShowUCMAndFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_AND_FORK, Messages.getString("ReportGeneratorPreferencePage.andFork"), //$NON-NLS-1$
        // ucmCompOptions);
        // addField(ShowUCMAndFork);

        BooleanFieldEditor ShowUCMStartPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_START_POINT, Messages
                .getString("ReportGeneratorPreferencePage.startPoint"), //$NON-NLS-1$
                ucmCompOptions);
        addField(ShowUCMStartPoint);

        BooleanFieldEditor ShowUCMEndPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_END_POINT, Messages
                .getString("ReportGeneratorPreferencePage.endPoint"), //$NON-NLS-1$
                ucmCompOptions);
        addField(ShowUCMEndPoint);

        // Group for Scenario Execution Preferences
        Group scenDisplayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        scenDisplayOptions.setText(Messages.getString("ReportGeneratorPreferencePage.ScenarioExecPrefs")); //$NON-NLS-1$
        
        GridLayout scenLayoutOptions = new GridLayout();
        scenLayoutOptions.numColumns = 1;
        scenDisplayOptions.setLayout(scenLayoutOptions);
        
        GridData scenDataOptions = new GridData(SWT.FILL);
        scenDataOptions.horizontalSpan = 2;
        scenDataOptions.horizontalAlignment = GridData.FILL_HORIZONTAL;
        scenDataOptions.grabExcessHorizontalSpace = true;
        scenDisplayOptions.setLayoutData(scenDataOptions);
        
        Composite scenCompOptions = new Composite(scenDisplayOptions, SWT.NONE);
        scenDataOptions = new GridData(SWT.FILL);
        scenDataOptions.horizontalIndent = 20;
        scenDataOptions.horizontalAlignment = SWT.FILL;
        scenDataOptions.grabExcessHorizontalSpace = true;
        scenDataOptions.verticalAlignment = SWT.BEGINNING;
        scenCompOptions.setLayoutData(scenDataOptions);
        
        BooleanFieldEditor ShowScenarioInfo = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_SCENARIO_INFO, Messages
                .getString("ReportGeneratorPreferencePage.scenarioInfo"), //$NON-NLS-1$
                scenCompOptions);
        addField(ShowScenarioInfo);
        
        BooleanFieldEditor ShowScenarioExec = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_SCENARIO_EXEC, Messages.getString("ReportGeneratorPreferencePage.ShowScenarioExec"), //$NON-NLS-1$
                scenCompOptions);
        addField(ShowScenarioExec);
        
        // Group for GRL Preferences
        Group grlDisplayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        grlDisplayOptions.setText(Messages.getString("ReportGeneratorPreferencePage.GRLPreferences")); //$NON-NLS-1$
        
        GridLayout grlLayoutOptions = new GridLayout();
        grlLayoutOptions.numColumns = 1;
        grlDisplayOptions.setLayout(grlLayoutOptions);
        
        GridData grlDataOptions = new GridData(SWT.FILL);
        grlDataOptions.horizontalSpan = 2;
        grlDataOptions.horizontalAlignment = GridData.FILL_HORIZONTAL;
        grlDataOptions.grabExcessHorizontalSpace = true;
        grlDisplayOptions.setLayoutData(grlDataOptions);
        
        Composite grlCompOptions = new Composite(grlDisplayOptions, SWT.NONE);
        grlDataOptions = new GridData(SWT.FILL);
        grlDataOptions.grabExcessHorizontalSpace = true;
        grlDataOptions.horizontalIndent = 20;
        grlDataOptions.horizontalAlignment = SWT.FILL;
        grlDataOptions.verticalAlignment = SWT.BEGINNING;
        grlCompOptions.setLayoutData(grlDataOptions);
        
        BooleanFieldEditor showGRLDiagrams = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_GRL_DIAGRAMS, Messages.getString("ReportGeneratorPreferencePage.ShowGRLDiagrams"), //$NON-NLS-1$
        		grlCompOptions);
        addField(showGRLDiagrams);


        BooleanFieldEditor ShowGRLIntentionalElements = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_INTENTIONAL_ELEMENTS, Messages
                .getString("ReportGeneratorPreferencePage.intentionalElements"), //$NON-NLS-1$
                grlCompOptions);
        addField(ShowGRLIntentionalElements);

        BooleanFieldEditor ShowGRLBeliefs = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_BELIEFS, Messages
                .getString("ReportGeneratorPreferencePage.beliefs"), //$NON-NLS-1$
                grlCompOptions);
        addField(ShowGRLBeliefs);
  
        
        // Group for GRL Strategy Evaluation Preferences
        Group evalDisplayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        evalDisplayOptions.setText(Messages.getString("ReportGeneratorPreferencePage.StrategyEvalPrefs")); //$NON-NLS-1$
        
        GridLayout evalLayoutOptions = new GridLayout();
        evalLayoutOptions.numColumns = 1;
        evalDisplayOptions.setLayout(evalLayoutOptions);
        
        GridData evalDataOptions = new GridData(SWT.FILL);
        evalDataOptions.horizontalSpan = 2;
        evalDataOptions.horizontalAlignment = GridData.FILL_HORIZONTAL;
        evalDataOptions.grabExcessHorizontalSpace = true;
        evalDisplayOptions.setLayoutData(evalDataOptions);
        
        Composite evalCompOptions = new Composite(evalDisplayOptions, SWT.NONE);
        evalDataOptions = new GridData(SWT.FILL);
        evalDataOptions.grabExcessHorizontalSpace = true;
        evalDataOptions.horizontalIndent = 20;
        evalDataOptions.horizontalAlignment = SWT.FILL;
        evalDataOptions.verticalAlignment = SWT.BEGINNING;
        evalCompOptions.setLayoutData(evalDataOptions);
        
        BooleanFieldEditor ShowGRLEvals = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_EVALS,
        		Messages.getString("ReportGeneratorPreferencePage.ShowStrategyEvals"), //$NON-NLS-1$
        		evalCompOptions);
        addField(ShowGRLEvals);
        
        BooleanFieldEditor ShowKPIsEvals = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_KPI_SHOW_EVALS,
        		Messages.getString("ReportGeneratorPreferencePage.ShowStrategyKPIsEvals"), //$NON-NLS-1$
        		evalCompOptions);
        addField(ShowKPIsEvals);
        
        BooleanFieldEditor ShowGRLEvalStrategyTrend = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_EVAL_STRATEGY_TREND,
        		Messages.getString("ReportGeneratorPreferencePage.ShowGRLEvalTrends"), //$NON-NLS-1$
        		evalCompOptions);
        addField(ShowGRLEvalStrategyTrend);
        
        StringFieldEditor GRLEvalStrategyTrend = new StringFieldEditor(ReportGeneratorPreferences.PREF_GRL_EVAL_STRATEGY_TREND,
        		Messages.getString("ReportGeneratorPreferencePage.NumberColsEvalTrends"), //$NON-NLS-1$
        		evalCompOptions);
        addField(GRLEvalStrategyTrend);

        labelWarning = new Label(getFieldEditorParent(), SWT.LEFT);
        
        // set isUCMPrefsSelected and isGRLPrefsSelected flags depending on the current values of
        // the UCMSHOWUCMDIAGRAMS and GRLSHOWGRLDIAGRAMS preferences
        if (!getPreferenceStore().getBoolean(ReportGeneratorPreferences.PREF_GRL_SHOW_GRL_DIAGRAMS)) {
        	isGRLPrefsSelected = false;
        } else {
        	isGRLPrefsSelected = true;
        }
        if (!getPreferenceStore().getBoolean(ReportGeneratorPreferences.PREF_UCM_SHOW_UCM_DIAGRAMS)) {
        	isUCMPrefsSelected = false;
        } else {
        	isUCMPrefsSelected = true;
        }
        
        // set the labelWarning text to the right warning message, depending on the values of the
        // isUCMPrefsSelected and isGRLPrefsSelected flags
        if ((isUCMPrefsSelected) && (!isGRLPrefsSelected)) {
        	labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningGRL")); //$NON-NLS-1$
        } else if ((!isUCMPrefsSelected) && (!isGRLPrefsSelected)) {
        	labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningGRLUCM")); //$NON-NLS-1$
        } else if ((!isUCMPrefsSelected) && (isGRLPrefsSelected)) {
        	labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningUCM")); //$NON-NLS-1$
        } else {
        	labelWarning.setText("                                                                                                                             "); //$NON-NLS-1$
        }
        
    }


    public void propertyChange(PropertyChangeEvent event) {
    	
    	if (event.getSource() instanceof BooleanFieldEditor) {
    		String prefName = ((BooleanFieldEditor) event.getSource()).getPreferenceName();
    		
    		// change values of isUCMPrefsSelected and isGRLPrefsSelected if the changed preference
    		// is UCMSHOWUCMDIAGRAMS or GRLSHOWGRLDIAGRAMS
    		if (prefName.equals("seg.jUCMNav.UCMReportPreference.UCMSHOWUCMDIAGRAMS")) { //$NON-NLS-1$
    			if ((boolean) (event.getNewValue().equals(true))) {
    				isUCMPrefsSelected = true;
    			} else {
    				isUCMPrefsSelected = false;
    			}
    		} else if (prefName.equals("seg.jUCMNav.UCMReportPreference.GRLSHOWGRLDIAGRAMS")) { //$NON-NLS-1$
    			if ((boolean) (event.getNewValue().equals(true))) {
    				isGRLPrefsSelected = true;
    			} else {
    				isGRLPrefsSelected = false;
    			}
    		}
    		
    		// change warning message depending on the preferences selected
    		if ((isUCMPrefsSelected) && (!isGRLPrefsSelected)) {
    			labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningGRL")); //$NON-NLS-1$
    		} else if ((!isUCMPrefsSelected) && (!isGRLPrefsSelected)) {
    			labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningGRLUCM")); //$NON-NLS-1$
    		} else if ((!isUCMPrefsSelected) && (isGRLPrefsSelected)) {
    			labelWarning.setText(Messages.getString("ReportGeneratorPreferencePage.WarningUCM")); //$NON-NLS-1$
    		} else {
    			labelWarning.setText(""); //$NON-NLS-1$
    		}
    	}
    }

    public void init(IWorkbench workbench) {

    }

}
