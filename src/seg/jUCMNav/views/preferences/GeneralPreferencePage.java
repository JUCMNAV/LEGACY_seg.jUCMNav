package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.figures.ColorManager;

/**
 * The root preference page for jUCMNav. Has preferences for label colors.
 * 
 * @author jkealey, jfroy, gunterm
 * 
 */
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_STUBLABELCOLOR = "PREF_STUBLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_CONDITIONLABELCOLOR = "PREF_CONDITIONLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_LINKREFLABELCOLOR = "PREF_LINKREFLABELCOLOR"; //$NON-NLS-1$
    public static final String PREF_LINECOLOR = "PREF_LINECOLOR"; //$NON-NLS-1$
    public static final String PREF_HOVERCOLOR = "PREF_HOVERCOLOR"; //$NON-NLS-1$
    public static final String PREF_SELECTEDCOLOR = "PREF_SELECTEDCOLOR"; //$NON-NLS-1$
    public static final String PREF_FILLCOLOR = "PREF_FILLCOLOR"; //$NON-NLS-1$
    public static final String PREF_TRAVERSALCOLOR = "PREF_TRAVERSALCOLOR"; //$NON-NLS-1$
    public static final String PREF_POINTCUTBORDERCOLOR = "PREF_POINTCUTBORDERCOLOR";  //$NON-NLS-1$

    public static final String PREF_AUTHOR = "PREF_AUTHOR"; //$NON-NLS-1$
    public static final String PREF_STRICTCODEEDITOR = "PREF_STRICTCODEEDITOR"; //$NON-NLS-1$
    public static final String PREF_GRLTEXTVISIBLE = "PREF_GRLTEXTVISIBLE"; //$NON-NLS-1$
    public static final String PREF_GRLICONVISIBLE = "PREF_GRLICONVISIBLE"; //$NON-NLS-1$
    public static final String PREF_GRLAUTOADDLINKS = "PREF_GRLAUTOADDLINKS"; //$NON-NLS-1$
    // Preferences for new .jurn files
    public static final String PREF_NEWGRL = "PREF_NEWGRL"; //$NON-NLS-1$
    public static final String PREF_NEWUCM = "PREF_NEWUCM"; //$NON-NLS-1$
    
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
        ColorFieldEditor editor = new ColorFieldEditor(PREF_STUBLABELCOLOR, Messages.getString("GeneralPreferencePage.StubLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_CONDITIONLABELCOLOR, Messages.getString("GeneralPreferencePage.ConditionLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_LINKREFLABELCOLOR, Messages.getString("GeneralPreferencePage.GrlLinkLabelColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        
        editor= new ColorFieldEditor(PREF_LINECOLOR, Messages.getString("GeneralPreferencePage.LineColor"), getFieldEditorParent());  //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_FILLCOLOR, Messages.getString("GeneralPreferencePage.FillColor"), getFieldEditorParent());  //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_HOVERCOLOR, Messages.getString("GeneralPreferencePage.HoverColor"), getFieldEditorParent());  //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_SELECTEDCOLOR, Messages.getString("GeneralPreferencePage.SelectedColor"), getFieldEditorParent());  //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_TRAVERSALCOLOR, Messages.getString("GeneralPreferencePage.TraversalColor"), getFieldEditorParent());  //$NON-NLS-1$
        addField(editor);
        editor = new ColorFieldEditor(PREF_POINTCUTBORDERCOLOR, Messages.getString("GeneralPreferencePage.PointcutBorderColor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(editor);
        
        StringFieldEditor author = new StringFieldEditor(PREF_AUTHOR, Messages.getString("GeneralPreferencePage.author"), getFieldEditorParent()); //$NON-NLS-1$
        addField(author);
        
        BooleanFieldEditor grl_iconvisible = new BooleanFieldEditor(PREF_GRLICONVISIBLE, Messages.getString("GeneralPreferencePage.ShowGrlContribIcons"), getFieldEditorParent()); //$NON-NLS-1$
        addField(grl_iconvisible);
        BooleanFieldEditor grl_textvisible = new BooleanFieldEditor(PREF_GRLTEXTVISIBLE, Messages.getString("GeneralPreferencePage.ShowGrlContribText"), getFieldEditorParent()); //$NON-NLS-1$
        addField(grl_textvisible);
        BooleanFieldEditor grl_autoaddlinks = new BooleanFieldEditor(PREF_GRLAUTOADDLINKS, Messages.getString("GeneralPreferencePage.AutoAddLinks"), getFieldEditorParent()); //$NON-NLS-1$
        addField(grl_autoaddlinks);
        BooleanFieldEditor strict_codeeditor = new BooleanFieldEditor(PREF_STRICTCODEEDITOR, Messages.getString("GeneralPreferencePage.StrictPseudoCodeEditor"), getFieldEditorParent()); //$NON-NLS-1$
        addField(strict_codeeditor);

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
        return JUCMNavPlugin.getDefault().getPreferenceStore().getString(PREF_AUTHOR); 
    }
    
    /**
     * @return boolean TRUE if editor should be strict. 
     */
    public static boolean getStrictCodeEditor(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_STRICTCODEEDITOR); 
    }    
    
    /**
     * @return boolean TRUE if GRL contribution icons should be visible. 
     */
    public static boolean getGrlIconVisible(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLICONVISIBLE); 
    }    
    
    /**
     * @return boolean TRUE if GRL contribution text should be visible. 
     */
    public static boolean getGrlTextVisible(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLTEXTVISIBLE); 
    }    

    /**
     * @return boolean TRUE if GRL links should be added automatically when an
     * intentional element is dragged from the Outline and dropped on a model. 
     */
    public static boolean getGrlAutoAddLinks(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLAUTOADDLINKS); 
    }    

    /**
     * @return whether a new GRL diagram should be created in a new .jucm file
     */
    public static boolean getNewGRL(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_NEWGRL); 
    }    

    /**
     * @return whether a new UCM diagram should be created in a new .jucm file
     */
    public static boolean getNewUCM(){
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_NEWUCM); 
    }    

    /**
     * Set whether a new GRL diagram should be created in a new .jucm file
     */
    public static void setNewGRL(boolean value){
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_NEWGRL, value); 
    }    

    /**
     * Set whether a new UCM diagram should be created in a new .jucm file
     */
    public static void setNewUCM(boolean value){
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_NEWUCM, value); 
    }    

    public boolean performOk() {
    	
    	boolean b  = super.performOk();
    	ColorManager.refresh();
    	return b;
    }
}
