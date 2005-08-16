package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * The root preference page for jUCMNav. Has preferences for label colors.
 * 
 * @author jkealey
 * 
 */
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_STUBLABELCOLOR = "PREF_STUBLABELCOLOR";
    public static final String PREF_CONDITIONLABELCOLOR = "PREF_CONDITIONLABELCOLOR";

    public GeneralPreferencePage() {
        super(FieldEditorPreferencePage.GRID);

        // Set the preference store for the preference page.
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        setPreferenceStore(store);
    }

    /**
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    protected void createFieldEditors() {
        ColorFieldEditor stubLabelColor = new ColorFieldEditor(PREF_STUBLABELCOLOR, "Stub Label Color", getFieldEditorParent());
        addField(stubLabelColor);
        ColorFieldEditor conditionLabelColor = new ColorFieldEditor(PREF_CONDITIONLABELCOLOR, "Condition Label Color", getFieldEditorParent());
        addField(conditionLabelColor);

    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // nothing to do
    }

}
