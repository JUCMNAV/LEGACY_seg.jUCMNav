package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Preference page for scenario traversal
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public ScenarioTraversalPreferencePage() {
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
    	
    	
        IntegerFieldEditor hitcount = new IntegerFieldEditor(ScenarioTraversalPreferences.PREF_MAXHITCOUNT, "Maximum number of times an element can be hit before it is declared blocked",  getFieldEditorParent());
        addField(hitcount);


        BooleanFieldEditor patient = new BooleanFieldEditor(ScenarioTraversalPreferences.PREF_ISPATIENTONPRECONDITIONS, "Do we continue other processing and come back to the element later if its precondition is false?", getFieldEditorParent()); 
        addField(patient);
        
        BooleanFieldEditor deterministic = new BooleanFieldEditor(ScenarioTraversalPreferences.PREF_ISDETERMINISTIC, "Deterministic algorithm?", getFieldEditorParent()); 
        addField(deterministic);        
        
    }

    public void init(IWorkbench workbench) {

    }

}
