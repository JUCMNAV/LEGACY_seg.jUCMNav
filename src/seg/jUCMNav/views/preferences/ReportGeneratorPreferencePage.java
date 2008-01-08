package seg.jUCMNav.views.preferences;

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
        
    
        
    }

    public void init(IWorkbench workbench) {

    }


}
