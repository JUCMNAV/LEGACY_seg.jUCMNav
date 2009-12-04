/**
 * 
 */
package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.figures.ColorManager;

/**
 * This is the preference page to manage the default color used for URN elements.
 * 
 * @author jfroy
 * 
 */
public class ColorManagementPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_STUBLABELCOLOR = "PREF_STUBLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_CONDITIONLABELCOLOR = "PREF_CONDITIONLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_LINKREFLABELCOLOR = "PREF_LINKREFLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_KPIMODELLINKREFLABELCOLOR = "PREF_KPIMODELLINKREFLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_LINECOLOR = "PREF_LINECOLOR"; //$NON-NLS-1$
    public static final String PREF_HOVERCOLOR = "PREF_HOVERCOLOR"; //$NON-NLS-1$
    public static final String PREF_SELECTEDCOLOR = "PREF_SELECTEDCOLOR"; //$NON-NLS-1$
    public static final String PREF_FILLCOLOR = "PREF_FILLCOLOR"; //$NON-NLS-1$
    public static final String PREF_TRAVERSALCOLOR = "PREF_TRAVERSALCOLOR"; //$NON-NLS-1$
    public static final String PREF_POINTCUTBORDERCOLOR = "PREF_POINTCUTBORDERCOLOR"; //$NON-NLS-1$

    public ColorManagementPreferencePage() {
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
        ColorFieldEditor editor = new ColorFieldEditor(PREF_STUBLABELCOLOR, Messages.getString("GeneralPreferencePage.StubLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_CONDITIONLABELCOLOR, Messages.getString("GeneralPreferencePage.ConditionLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_LINKREFLABELCOLOR, Messages.getString("GeneralPreferencePage.GrlLinkLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);

        editor = new ColorFieldEditor(PREF_KPIMODELLINKREFLABELCOLOR, Messages.getString("GeneralPreferencePage.KpiModelLinkRef"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_LINECOLOR, Messages.getString("GeneralPreferencePage.LineColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_FILLCOLOR, Messages.getString("GeneralPreferencePage.FillColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_HOVERCOLOR, Messages.getString("GeneralPreferencePage.HoverColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_SELECTEDCOLOR, Messages.getString("GeneralPreferencePage.SelectedColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_TRAVERSALCOLOR, Messages.getString("GeneralPreferencePage.TraversalColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_POINTCUTBORDERCOLOR, Messages.getString("GeneralPreferencePage.PointcutBorderColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);

    }

    public void init(IWorkbench workbench) {
        // nothing to do
    }

    public boolean performOk() {

        boolean b = super.performOk();
        ColorManager.refresh();
        return b;
    }

}
