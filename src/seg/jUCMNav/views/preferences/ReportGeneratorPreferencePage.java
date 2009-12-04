package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
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

        String[][] values = { { Messages.getString("ReportGeneratorPreferencePage.topdown"), "TB" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ReportGeneratorPreferencePage.leftright"), "LR" } }; //$NON-NLS-1$ //$NON-NLS-2$

        RadioGroupFieldEditor orientation = new RadioGroupFieldEditor(ReportGeneratorPreferences.PREF_REPORT_ORIENTATION, Messages
                .getString("ReportGeneratorPreferencePage.orientation"), 1, values, getFieldEditorParent()); //$NON-NLS-1$
        addField(orientation);

        ColorFieldEditor reportColor = new ColorFieldEditor(ReportGeneratorPreferences.PREF_REPORT_COLOR, Messages
                .getString("ReportGeneratorPreferencePage.PDFBackgroundColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(reportColor);

        // need to add separator line or label

        BooleanFieldEditor showUCMDesc = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_DESC, Messages
                .getString("ReportGeneratorPreferencePage.descOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMDesc);

        BooleanFieldEditor showUCMResponsibility = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_RESPONSIBILITY, Messages
                .getString("ReportGeneratorPreferencePage.respOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMResponsibility);

        BooleanFieldEditor showUCMStub = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_STUB, Messages
                .getString("ReportGeneratorPreferencePage.stubOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMStub);

        BooleanFieldEditor showUCMOrFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_OR_FORK, Messages
                .getString("ReportGeneratorPreferencePage.orFork"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMOrFork);

        // disabled as And Forks currently don't appear in any reports
        //BooleanFieldEditor ShowUCMAndFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_AND_FORK, Messages.getString("ReportGeneratorPreferencePage.andFork"), //$NON-NLS-1$
        // getFieldEditorParent());
        // addField(ShowUCMAndFork);

        BooleanFieldEditor ShowUCMStartPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_START_POINT, Messages
                .getString("ReportGeneratorPreferencePage.startPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMStartPoint);

        BooleanFieldEditor ShowUCMEndPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCM_SHOW_END_POINT, Messages
                .getString("ReportGeneratorPreferencePage.endPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMEndPoint);

        BooleanFieldEditor ShowURNLinks = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_SHOW_URN_LINKS, Messages
                .getString("ReportGeneratorPreferencePage.urnLinks"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowURNLinks);

        BooleanFieldEditor ShowGRLIntentionalElements = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_INTENTIONAL_ELEMENTS, Messages
                .getString("ReportGeneratorPreferencePage.intentionalElements"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowGRLIntentionalElements);

        BooleanFieldEditor ShowGRLBeliefs = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_GRL_SHOW_BELIEFS, Messages
                .getString("ReportGeneratorPreferencePage.beliefs"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowGRLBeliefs);
    }

    public void init(IWorkbench workbench) {

    }

}
