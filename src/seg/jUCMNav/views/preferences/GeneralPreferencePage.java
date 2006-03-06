package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;

/**
 * The root preference page for jUCMNav. Has preferences for label colors.
 * 
 * @author jkealey
 * 
 */
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_STUBLABELCOLOR = "PREF_STUBLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_CONDITIONLABELCOLOR = "PREF_CONDITIONLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_LINKREFLABELCOLOR = "PREF_LINKREFLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_AUTHOR = "PREF_AUTHOR"; //$NON-NLS-1$

    
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
        ColorFieldEditor stubLabelColor = new ColorFieldEditor(PREF_STUBLABELCOLOR, Messages.getString("GeneralPreferencePage.StubLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(stubLabelColor);
        ColorFieldEditor conditionLabelColor = new ColorFieldEditor(PREF_CONDITIONLABELCOLOR, Messages.getString("GeneralPreferencePage.ConditionLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(conditionLabelColor);
        ColorFieldEditor decompositionLabelColor = new ColorFieldEditor(PREF_LINKREFLABELCOLOR, Messages.getString("GeneralPreferencePage.GrlLinkLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(decompositionLabelColor);
        
        StringFieldEditor author = new StringFieldEditor(PREF_AUTHOR, Messages.getString("GeneralPreferencePage.author"), getFieldEditorParent()); //$NON-NLS-1$
        addField(author);
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // nothing to do
    }

    /**
     * 
     * @return the Author
     */
    public static String getAuthor() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getString("PREF_AUTHOR"); //$NON-NLS-1$
    }
}
