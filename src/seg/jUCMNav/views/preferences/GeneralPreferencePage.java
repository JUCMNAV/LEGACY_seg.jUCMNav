package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import ca.mcgill.sel.core.COREInterface;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.figures.ColorManager;

/**
 * The root preference page for jUCMNav. Has preferences for label colors.
 * 
 * @author jkealey, jfroy, gunterm, pchen
 * 
 */
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_AUTHOR = "PREF_AUTHOR"; //$NON-NLS-1$
    public static final String PREF_DISTRIBUTE_SPACING = "PREF_DISTRIBUTE_SPACING"; //$NON-NLS-1$
    public static final int DEFAULT_DISTRIBUTE_SPACING = 25; //$NON-NLS-1$
    public static final String PREF_STRICTCODEEDITOR = "PREF_STRICTCODEEDITOR"; //$NON-NLS-1$
    public static final String PREF_METADATAINDVISIBLE = "PREF_METADATAINDVISIBLE"; //$NON-NLS-1$
    public static final String PREF_ANTIALIASING = "PREF_ANTIALIASING"; //$NON-NLS-1$
    public static final String PREF_GRLTEXTVISIBLE = "PREF_GRLTEXTVISIBLE"; //$NON-NLS-1$
    public static final String PREF_GRLICONVISIBLE = "PREF_GRLICONVISIBLE"; //$NON-NLS-1$
    public static final String PREF_GRLAUTOADDLINKS = "PREF_GRLAUTOADDLINKS"; //$NON-NLS-1$
    public static final String PREF_GRLSATISFACTIONICONVISIBLE = "PREF_GRLSATISFACTIONICONVISIBLE"; //$NON-NLS-1$
    public static final String PREF_GRLSATISFACTIONTEXTVISIBLE = "PREF_GRLSATISFACTIONTEXTVISIBLE"; //$NON-NLS-1$
    public static final String PREF_UCMEMPTYPOINTVISIBLE = "PREF_UCMEMPTYPOINTVISIBLE"; //$NON-NLS-1$
    public static final String PREF_UCMSTUBLABELVISIBLE = "PREF_UCMSTUBLABELVISIBLE"; //$NON-NLS-1$
    
    // Preferences for new .jucm files
    public static final String PREF_NEWGRL = "PREF_NEWGRL"; //$NON-NLS-1$
    public static final String PREF_NEWUCM = "PREF_NEWUCM"; //$NON-NLS-1$
    public static final String PREF_NEWFMD = "PREF_NEWFMD"; //$NON-NLS-1$
    
    // To accelerate access to preferences...
    private static int antialising_pref = SWT.ON;

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
        StringFieldEditor author = new StringFieldEditor(PREF_AUTHOR, Messages.getString("GeneralPreferencePage.author"), getFieldEditorParent()); //$NON-NLS-1$
        addField(author);
        
       
        StringFieldEditor distributeSpacing = new StringFieldEditor(PREF_DISTRIBUTE_SPACING, Messages.getString("GeneralPreferencePage.distributeSpacing"), getFieldEditorParent()); //$NON-NLS-1$
        addField(distributeSpacing);
        
        // Group for display options
        Group displayOptions = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        displayOptions.setText(Messages.getString("GeneralPreferencePage.DisplayOptions")); //$NON-NLS-1$
        
        GridLayout layoutOptions = new GridLayout();
        layoutOptions.numColumns = 1;
        displayOptions.setLayout(layoutOptions);
        
        GridData dataOptions = new GridData();
        displayOptions.setLayoutData(dataOptions);
        
        Composite cOptions = new Composite(displayOptions, SWT.NONE);
        dataOptions = new GridData();
        dataOptions.grabExcessHorizontalSpace = true;
        dataOptions.horizontalIndent = 20;
        cOptions.setLayoutData(dataOptions);
        
        Label label1 = new Label(cOptions, SWT.LEFT);
        label1.setText(Messages.getString("GeneralPreferencePage.DisplayOptionsLabel")); //$NON-NLS-1$

        BooleanFieldEditor field  = new BooleanFieldEditor(PREF_METADATAINDVISIBLE,
                Messages.getString("GeneralPreferencePage.ShowMetadataIndicator"), cOptions); //$NON-NLS-1$
        addField(field);


        field = new BooleanFieldEditor(PREF_GRLICONVISIBLE,
                Messages.getString("GeneralPreferencePage.ShowGrlContribIcons"), cOptions); //$NON-NLS-1$
        addField(field);
        field = new BooleanFieldEditor(PREF_GRLTEXTVISIBLE,
                Messages.getString("GeneralPreferencePage.ShowGrlContribText"), cOptions); //$NON-NLS-1$
        addField(field);
        
        field = new BooleanFieldEditor(PREF_GRLSATISFACTIONICONVISIBLE, Messages.getString("GeneralPreferencePage.ViewSatisfactionLevelIcons"), cOptions); //$NON-NLS-1$
        addField(field);
        field = new BooleanFieldEditor(PREF_GRLSATISFACTIONTEXTVISIBLE, Messages.getString("GeneralPreferencePage.ViewSatisfactionLevelText"), cOptions); //$NON-NLS-1$
        addField(field);        
        field = new BooleanFieldEditor(PREF_UCMEMPTYPOINTVISIBLE, Messages.getString("GeneralPreferencePage.ViewEmptyPoints"), cOptions); //$NON-NLS-1$
        addField(field);        
        field = new BooleanFieldEditor(PREF_UCMSTUBLABELVISIBLE, Messages.getString("GeneralPreferencePage.ViewStubLabels"), cOptions); //$NON-NLS-1$
        addField(field);        
     
        // Group for advanced mode        
        Group g = new Group(getFieldEditorParent(), SWT.SHADOW_ETCHED_IN);
        g.setText(Messages.getString("GeneralPreferencePage.AdvancedMode")); //$NON-NLS-1$
        
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        g.setLayout(layout);
        
        GridData data = new GridData();
        g.setLayoutData(data);
        
        Composite c = new Composite(g, SWT.NONE);

        BooleanFieldEditor advanced_jucmnav = new BooleanFieldEditor(DisplayPreferences.PREF_ADVANCEDUCM, Messages.getString("GeneralPreferencePage.EnableTheFollowing"), c); //$NON-NLS-1$
        addField(advanced_jucmnav);
        
        c = new Composite(g, SWT.NONE);
        data = new GridData();
        data.grabExcessHorizontalSpace = true;
        data.horizontalIndent = 20;
        c.setLayoutData(data);
        
        BooleanFieldEditor advanced_KPI = new BooleanFieldEditor(DisplayPreferences.PREF_ADVANCEDUCMKPI, Messages.getString("GeneralPreferencePage.KPI"), c); //$NON-NLS-1$
        addField(advanced_KPI);
        
        BooleanFieldEditor advanced_Perf = new BooleanFieldEditor(DisplayPreferences.PREF_ADVANCEDUCMPERF, Messages.getString("GeneralPreferencePage.PerfAnalysis"), c); //$NON-NLS-1$
        addField(advanced_Perf);
        
        BooleanFieldEditor advanced_Aspects = new BooleanFieldEditor(DisplayPreferences.PREF_ADVANCEDUCMASPECTS, Messages.getString("GeneralPreferencePage.Aspects"), c); //$NON-NLS-1$
        addField(advanced_Aspects);
        
        
        
        // MISC
        
        field = new BooleanFieldEditor(PREF_ANTIALIASING,
                Messages.getString("GeneralPreferencePage.UseAntiAliasing"), getFieldEditorParent()); //$NON-NLS-1$
        addField(field);
        
        BooleanFieldEditor grl_autoaddlinks = new BooleanFieldEditor(PREF_GRLAUTOADDLINKS,
                Messages.getString("GeneralPreferencePage.AutoAddLinks"), getFieldEditorParent()); //$NON-NLS-1$
        addField(grl_autoaddlinks);

        // UCM Strict Editor        
        BooleanFieldEditor strict_codeeditor = new BooleanFieldEditor(PREF_STRICTCODEEDITOR,
                Messages.getString("GeneralPreferencePage.StrictPseudoCodeEditor"), getFieldEditorParent()); //$NON-NLS-1$
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
    	// this if statement was added to support the CORE interface; when jUCMNav is accessed through the CORE interface,
    	// the plugin environment is not defined which causes a null pointer exception here
    	if (COREFactory4URN.isCOREInterfaceActive())
    		return COREFactory4URN.AUTHOR_NAME;
        return JUCMNavPlugin.getDefault().getPreferenceStore().getString(PREF_AUTHOR);
    }
    
     
    /**
     * 
     * @return the distribute spacing value
     */
    public static String getDistributeSpacing() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getString(PREF_DISTRIBUTE_SPACING);
    }
    
    /**
     * 
     * @return the distribute spacing value
     */
    public static void setDistributeSpacing(int value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_DISTRIBUTE_SPACING, value);
    }

    /**
     * @return boolean TRUE if editor should be strict.
     */
    public static boolean getStrictCodeEditor() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_STRICTCODEEDITOR);
    }

    /**
     * @return boolean TRUE if metadata indicator should be visible.
     */
    public static boolean getMetadataIndVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_METADATAINDVISIBLE);
    }

    /**
     * @return integer SWT.ON if anti-aliasing should be used (else returns SWT.OFF).
     */
    public static int getAntialiasingPref() {
        return antialising_pref;
    }

    /**
     * @return boolean TRUE if GRL contribution icons should be visible.
     */
    public static boolean getGrlIconVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLICONVISIBLE);
    }

    /**
     * @return boolean TRUE if GRL contribution text should be visible.
     */
    public static boolean getGrlTextVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLTEXTVISIBLE);
    }

    /**
     * @return boolean TRUE if GRL satisfaction icons should be visible.
     */
    public static boolean getGrlSatisfactionIconVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLSATISFACTIONICONVISIBLE);
    }
    
    /**
     * @return boolean TRUE if GRL satisfaction text should be visible.
     */
    public static boolean getGrlSatisfactionTextVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLSATISFACTIONTEXTVISIBLE);
    }
    
    /**
     * @return boolean TRUE if UCM empty points should be visible.
     */
    public static boolean getUcmEmptyPointVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_UCMEMPTYPOINTVISIBLE);
    }
    
    /**
     * @return boolean TRUE if UCM direction arrows should be visible.
     */
    public static boolean getUcmStubLabelVisible() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_UCMSTUBLABELVISIBLE);
    }
    
    
    /**
     * @return boolean TRUE if GRL links should be added automatically when an intentional element is dragged from the Outline and dropped on a model.
     */
    public static boolean getGrlAutoAddLinks() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_GRLAUTOADDLINKS);
    }

    /**
     * @return whether a new GRL diagram should be created in a new .jucm file
     */
    public static boolean getNewGRL() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_NEWGRL);
    }
    
    /**
     * @return whether a new feature model diagram should be created in new .jucm file
     */
    public static boolean getNewFMD() {
    	return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_NEWFMD);
    }

    /**
     * @return whether a new UCM diagram should be created in a new .jucm file
     */
    public static boolean getNewUCM() {
        return JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_NEWUCM);
    }

    /**
     * Set whether a new GRL diagram should be created in a new .jucm file
     */
    public static void setNewGRL(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_NEWGRL, value);
    }
    
    /**
     * Set whether a new feature model diagram should be created in a new .jucm file
     */
    public static void setNewFMD(boolean value) {
    	JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_NEWFMD, value);
    }

    /**
     * Set whether a new UCM diagram should be created in a new .jucm file
     */
    public static void setNewUCM(boolean value) {
        JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_NEWUCM, value);
    }

    public boolean performOk() {

        boolean b = super.performOk();

        if (!getGrlIconVisible()&& !getGrlTextVisible())
            JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_GRLICONVISIBLE, true); // bug 507 wants at least one visible. 
        
        if (!getGrlSatisfactionIconVisible()&& !getGrlSatisfactionTextVisible())
            JUCMNavPlugin.getDefault().getPreferenceStore().setValue(PREF_GRLSATISFACTIONICONVISIBLE, true); // bug 507 wants at least one visible. 
            
        ColorManager.refresh();
        if (JUCMNavPlugin.getDefault().getPreferenceStore().getBoolean(PREF_ANTIALIASING))
            antialising_pref = SWT.ON;
        else
            antialising_pref = SWT.OFF;

        try {
            IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (part instanceof UCMNavMultiPageEditor) {
                UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) part;
                for (int i = 0; i < editor.getPageCount(); i++) {
                    UrnEditor u = (UrnEditor) editor.getEditor(i);
                    URNRootEditPart root = ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart());
                    root.setMode(root.getMode()); // forces refresh using old mode.
                }
            }
        } catch (Exception ex) {
                // don't care if no editor open.  
        }
        return b;
    }
}
