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
        
        StringFieldEditor width = new StringFieldEditor(ReportGeneratorPreferences.PREF_REPORTWIDTH, Messages.getString("ReportGeneratorPreferencePage.width"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(width);

        StringFieldEditor height = new StringFieldEditor(ReportGeneratorPreferences.PREF_REPORTHEIGHT, Messages.getString("ReportGeneratorPreferencePage.height"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(height);

        String[][] values = { { Messages.getString("ReportGeneratorPreferencePage.topdown"), "TB" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("ReportGeneratorPreferencePage.leftright"), "LR" } }; //$NON-NLS-1$ //$NON-NLS-2$
        
        RadioGroupFieldEditor orientation = new RadioGroupFieldEditor(ReportGeneratorPreferences.PREF_REPORTORIENTATION, Messages
                .getString("ReportGeneratorPreferencePage.orientation"), 1, values, getFieldEditorParent()); //$NON-NLS-1$
        addField(orientation);

        ColorFieldEditor reportColor = new ColorFieldEditor(ReportGeneratorPreferences.PREF_REPORTCOLOR, Messages.getString("ReportGeneratorPreferencePage.PDFBackgroundColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(reportColor);
        
      // need to add separator line or label
        
        BooleanFieldEditor showUCMDesc = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWDESC, Messages.getString("ReportGeneratorPreferencePage.descOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMDesc);

        BooleanFieldEditor showUCMResponsibility = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWRESPONSIBILITY, Messages.getString("ReportGeneratorPreferencePage.respOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMResponsibility);

        BooleanFieldEditor showUCMStub = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWSTUB, Messages.getString("ReportGeneratorPreferencePage.stubOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMStub);

        BooleanFieldEditor showUCMOrFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWORFORK, Messages.getString("ReportGeneratorPreferencePage.orFork"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMOrFork);

        BooleanFieldEditor ShowUCMAndFork = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWANDFORK, Messages.getString("ReportGeneratorPreferencePage.andFork"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMAndFork);

        BooleanFieldEditor ShowUCMStartPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWSTARTPOINT, Messages.getString("ReportGeneratorPreferencePage.startPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMStartPoint);

        BooleanFieldEditor ShowUCMEndPoint = new BooleanFieldEditor(ReportGeneratorPreferences.PREF_UCMSHOWENDPOINT, Messages.getString("ReportGeneratorPreferencePage.endPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMEndPoint);


        
    }

    public void init(IWorkbench workbench) {

    }


}
