/**
 * 
 */
package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Keep the preferences for the outline view.
 * 
 * @author jfroy, jkealey
 * 
 */
public class DisplayPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Constructor
     */
    public DisplayPreferencesPage() {
        super(FieldEditorPreferencePage.GRID);

        // Set the preference store for the preference page.
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        setPreferenceStore(store);
    }

    /**
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    protected void createFieldEditors() {
        StringFieldEditor author = new StringFieldEditor(DisplayPreferences.PREF_OUTLINE_FILTER, Messages.getString("DisplayPreferencesPage_ContainingText"), getFieldEditorParent()); //$NON-NLS-1$
        addField(author);

        BooleanFieldEditor bef = null;

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWNODENUMBER, Messages.getString("DisplayPreferencesPage_ShowNodeID"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWGRLS, Messages.getString("DisplayPreferencesPage_ShowGRL"), getFieldEditorParent()); //$NON-NLS-1$
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWUCMS, Messages.getString("DisplayPreferencesPage_ShowUCM"), getFieldEditorParent()); //$NON-NLS-1$
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWEMPTY, Messages.getString("DisplayPreferencesPage_ShowEmptyPoint"), //$NON-NLS-1$
                getFieldEditorParent());
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWFORKJOIN, Messages.getString("DisplayPreferencesPage_ShowForkJoin"), getFieldEditorParent()); //$NON-NLS-1$
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWSTARTEND, Messages.getString("DisplayPreferencesPage_ShowStartEnd"), getFieldEditorParent()); //$NON-NLS-1$
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWCONCERNS, Messages.getString("DisplayPreferencesPage_ShowConcern"), getFieldEditorParent()); //$NON-NLS-1$
        addField(bef);

        String[][] values = { { Messages.getString("DisplayPreferencesPage_Default"), DisplayPreferences.SORT_DEFAULT }, { Messages.getString("DisplayPreferencesPage_Identifier"), DisplayPreferences.SORT_ID }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("DisplayPreferencesPage_Name"), DisplayPreferences.SORT_NAME }, }; //$NON-NLS-1$

        RadioGroupFieldEditor orientation = new RadioGroupFieldEditor(DisplayPreferences.PREF_OUTLINE_SORT, Messages.getString("DisplayPreferencesPage_SortOrder"), 1, values, //$NON-NLS-1$
                getFieldEditorParent());
        addField(orientation);
    }

    public boolean performOk() {
        boolean b = super.performOk();
        DisplayPreferences.getInstance().refreshViews();
        return b;
    }

    public void init(IWorkbench arg0) {
        // TODO Auto-generated method stub
    }
}
