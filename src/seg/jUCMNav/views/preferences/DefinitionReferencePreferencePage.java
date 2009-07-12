package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;

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
        
        String[][] values = {
                { DeletePreferences.PREF_ALWAYS, DeletePreferences.PREF_ALWAYS },
                { DeletePreferences.PREF_NEVER, DeletePreferences.PREF_NEVER},
                { DeletePreferences.PREF_PROMPT, DeletePreferences.PREF_PROMPT} };
		RadioGroupFieldEditor deleteDefinition= new RadioGroupFieldEditor(DeletePreferences.PREF_DELDEFINITION, "Delete definition when deleting the last reference", 3, values, getFieldEditorParent());
		addField(deleteDefinition);

		RadioGroupFieldEditor deleteReference= new RadioGroupFieldEditor(DeletePreferences.PREF_DELREFERENCE, "Allow to delete referenced definitions", 3, values, getFieldEditorParent());
		addField(deleteReference);
		
    }

    public void init(IWorkbench workbench) {

    }


}
