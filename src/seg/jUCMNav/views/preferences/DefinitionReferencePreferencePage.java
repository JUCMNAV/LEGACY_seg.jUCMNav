package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * Preference page for element definitions and references
 * 
 * @author jsroy
 * 
 */
public class DefinitionReferencePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public DefinitionReferencePreferencePage() {
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

        String[][] values = { { DeletePreferences.PREF_ALWAYS, DeletePreferences.PREF_ALWAYS }, { DeletePreferences.PREF_NEVER, DeletePreferences.PREF_NEVER },
                { DeletePreferences.PREF_PROMPT, DeletePreferences.PREF_PROMPT } };
        RadioGroupFieldEditor deleteDefinition = new RadioGroupFieldEditor(DeletePreferences.PREF_DELDEFINITION,
                Messages.getString("DefinitionReferencePreferencePage_DeleteDefinitionWhenDeletingLast"), 3, values, getFieldEditorParent()); //$NON-NLS-1$
        addField(deleteDefinition);

        RadioGroupFieldEditor deleteReference = new RadioGroupFieldEditor(DeletePreferences.PREF_DELREFERENCE, Messages.getString("DefinitionReferencePreferencePage_AllowDeleteReferencedDefinition"), 3, //$NON-NLS-1$
                values, getFieldEditorParent());
        addField(deleteReference);

    }

    public void init(IWorkbench workbench) {

    }

}
