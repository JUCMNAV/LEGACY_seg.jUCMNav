package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

    public ReportGeneratorPreferencePage() {
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

        ColorFieldEditor reportColor = new ColorFieldEditor(ReportGeneratorPreferences.PREF_REPORT_COLOR, Messages
                .getString("ReportGeneratorPreferencePage.PDFBackgroundColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(reportColor);

        // separator line
        GridData g = new GridData(GridData.FILL_HORIZONTAL);
        g.horizontalSpan = 2;
        Label separator = new Label (getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(g);
        separator.setVisible(true);
        
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
        ucmDisplayOptions.setText("UCM Preferences");
        
        GridLayout ucmLayoutOptions = new GridLayout();
        ucmLayoutOptions.numColumns = 1;
        ucmDisplayOptions.setLayout(ucmLayoutOptions);
        
        GridData ucmDataOptions = new GridData();
        ucmDataOptions.horizontalSpan = 2;
        ucmDisplayOptions.setLayoutData(ucmDataOptions);
        
        Composite ucmCompOptions = new Composite(ucmDisplayOptions, SWT.NONE);
        ucmDataOptions = new GridData();
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

        // Group for GRL Preferences
        Group grlDisplayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        grlDisplayOptions.setText("GRL Preferences");
        
        GridLayout grlLayoutOptions = new GridLayout();
        grlLayoutOptions.numColumns = 1;
        grlDisplayOptions.setLayout(grlLayoutOptions);
        
        GridData grlDataOptions = new GridData();
        grlDataOptions.horizontalSpan = 2;
        grlDisplayOptions.setLayoutData(grlDataOptions);
        
        Composite grlCompOptions = new Composite(grlDisplayOptions, SWT.NONE);
        grlDataOptions = new GridData();
        grlDataOptions.grabExcessHorizontalSpace = true;
        grlDataOptions.horizontalIndent = 20;
        grlDataOptions.horizontalAlignment = SWT.LEFT;
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
        
        /*BooleanFieldEditor ShowGRLEvalStrategyTrend = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_EVAL_STRATEGY_TREND,
        		Messages.getString("ReportGeneratorPreferencePage.ShowGRLEvalTrends"), //$NON-NLS-1$
                grlCompOptions);
        addField(ShowGRLEvalStrategyTrend);
        
        StringFieldEditor GRLEvalStrategyTrend = new StringFieldEditor(ReportGeneratorPreferences.PREF_GRL_EVAL_STRATEGY_TREND,
        		Messages.getString("ReportGeneratorPreferencePage.NumberColsEvalTrends"), //$NON-NLS-1$
                grlCompOptions);
        addField(numberCSVcolumns);*/
    }

    /*public void propertyChange(PropertyChangeEvent event) {
    	
    	String prefName = ((BooleanFieldEditor) event.getSource()).getPreferenceName();
    	if (prefName.equals("seg.jUCMNav.UCMReportPreference.UCMSHOWUCMDIAGRAMS")) {
    		Control[] controls = this.getFieldEditorParent().getChildren();
    		for (int i=0; i < controls.length; i++) {
    			if ((controls[i].toString()).equals("Group {UCM Preferences}")) {
    				Control[] childControls = ((Group) controls[i]).getChildren();
    				for (int j=0; j < childControls.length; j++) {
    					System.out.println(childControls[j].toString());
    				}
    			}
    		}
    	}
    }*/
    public void init(IWorkbench workbench) {

    }

}
