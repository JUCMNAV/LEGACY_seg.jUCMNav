package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;


/**
 * Preference page for UCM Reports
 * 
 * @author dessure
 * 
 */
public class UCMReportPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage  {

        
    public UCMReportPreferencePage() {
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
        
       
        BooleanFieldEditor showUCMDesc = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWDESC, Messages.getString("UCMReportPreferencePage.descOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMDesc);

        BooleanFieldEditor showUCMResponsibility = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWRESPONSIBILITY, Messages.getString("UCMReportPreferencePage.respOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMResponsibility);

        BooleanFieldEditor showUCMStub = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWSTUB, Messages.getString("UCMReportPreferencePage.stubOrder"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMStub);

        BooleanFieldEditor showUCMOrFork = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWORFORK, Messages.getString("UCMReportPreferencePage.orFork"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(showUCMOrFork);

        BooleanFieldEditor ShowUCMAndFork = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWANDFORK, Messages.getString("UCMReportPreferencePage.andFork"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMAndFork);

        BooleanFieldEditor ShowUCMStartPoint = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWSTARTPOINT, Messages.getString("UCMReportPreferencePage.startPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMStartPoint);

        BooleanFieldEditor ShowUCMEndPoint = new BooleanFieldEditor(UCMReportPreferences.PREF_UCMSHOWENDPOINT, Messages.getString("UCMReportPreferencePage.endPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(ShowUCMEndPoint);

       
          
        
    }
        
    

    public void init(IWorkbench workbench) {
        // TODO Auto-generated method stub
        
    }

 



}
