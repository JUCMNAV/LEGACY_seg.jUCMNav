package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Preference page for scenario export
 * 
 * @author jkealey
 * 
 */
public class ScenarioExportPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public ScenarioExportPreferencePage() {
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

    	String[][] values = { { "Export *.jucmscenarios (to be converted to MSC and others)", "0" }, 
                { "Export linearized *.jucm file" , "1" },
                { "Export well-formed linearized *.jucm file" , "2" } 
        }; 

        RadioGroupFieldEditor type = new RadioGroupFieldEditor(ScenarioExportPreferences.PREF_EXPORTTYPE, "Export type ", 1, values, getFieldEditorParent()); 
        addField(type);
        
    	
        String[][] values2 = { { "All scenarios", "all" }, 
                { "Last run scenario only" , "last" }        }; 
        
        RadioGroupFieldEditor what = new RadioGroupFieldEditor(ScenarioExportPreferences.PREF_EXPORTALL, "Export what? ", 1, values2, getFieldEditorParent());
        addField(what);
        
        
        BooleanFieldEditor openAfterExport = new BooleanFieldEditor(ScenarioExportPreferences.PREF_OPENAFTEREXPORT, "Automatically open editor after export?", getFieldEditorParent());  
        addField(openAfterExport);        
        
    }

    public void init(IWorkbench workbench) {

    }

}
