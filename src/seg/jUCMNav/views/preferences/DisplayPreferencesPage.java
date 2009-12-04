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
        StringFieldEditor author = new StringFieldEditor(DisplayPreferences.PREF_OUTLINE_FILTER, "Containing text:", getFieldEditorParent());
        addField(author);

        BooleanFieldEditor bef = null;

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWNODENUMBER, "Show node id numbers in outline. For instance: name (1234)",
                getFieldEditorParent());
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWGRLS, "Show GRL-related elements in outline.", getFieldEditorParent());
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWUCMS, "Show UCM-related elements in outline.", getFieldEditorParent());
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWEMPTY, "Show UCM empty points and direction arrows in outline.",
                getFieldEditorParent());
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWFORKJOIN, "Show UCM forks and joins in outline.", getFieldEditorParent());
        addField(bef);
        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWSTARTEND, "Show UCM start and end points in outline.", getFieldEditorParent());
        addField(bef);

        bef = new BooleanFieldEditor(DisplayPreferences.PREF_OUTLINE_SHOWCONCERNS, "Show URN concerns in outline.", getFieldEditorParent());
        addField(bef);

        String[][] values = { { "Default (group by type, sort by name)", DisplayPreferences.SORT_DEFAULT }, { "Identifier", DisplayPreferences.SORT_ID },
                { "Name", DisplayPreferences.SORT_NAME }, };

        RadioGroupFieldEditor orientation = new RadioGroupFieldEditor(DisplayPreferences.PREF_OUTLINE_SORT, "Outline sort order", 1, values,
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
