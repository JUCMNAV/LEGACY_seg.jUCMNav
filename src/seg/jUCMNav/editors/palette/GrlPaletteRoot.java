package seg.jUCMNav.editors.palette;

import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.UCMElementCreationEntry;
import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;

/**
 * This is the GRLEditor palette. 
 * 
 * @author Jean-François Roy
 *
 */
public class GrlPaletteRoot extends PaletteRoot {

    /** Default palette size. */
    private static final int DEFAULT_PALETTE_SIZE = 125;

    /** pinned open */
    private static final int DEFAULT_PALETTE_STATE = 4;

    /** Preference ID used to persist the palette location. */
    private static final String PALETTE_DOCK_LOCATION = "jUCMNAVPaletteFactory.Location"; //$NON-NLS-1$

    /** Preference ID used to persist the palette size. */
    private static final String PALETTE_SIZE = "jUCMNAVPaletteFactory.Size"; //$NON-NLS-1$

    /** Preference ID used to persist the flyout palette's state. */
    private static final String PALETTE_STATE = "jUCMNAVPaletteFactory.State"; //$NON-NLS-1$

    // to obtain URNspec
    private UCMNavMultiPageEditor parent;

    // we need to pass this to ModelCreationFactory
    private URNspec urn;

    /**
     * Creates a new GrlPaletteRoot.
     */
    public GrlPaletteRoot(UCMNavMultiPageEditor parent) {
        super();
        this.parent = parent;
        buildPalette();
    }

    /**
     *  Builds the palette entries. 
     */
    private void buildPalette() {
        // a group of default control tools
        PaletteGroup controls = new PaletteGroup("Controls"); 
        add(controls);

        // the selection tool; default tool 
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

        ToolEntry entry;

        PaletteDrawer componentsDrawer = new PaletteDrawer("Elements"); 

        entry = new UCMElementCreationEntry(
                "Softgoal", "Create a Softgoal", IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class,
                        IntentionalElementType.SOFTGOAL), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Softgoal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new UCMElementCreationEntry(
                "Goal", "Create a Goal", IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class,
                        IntentionalElementType.GOAL), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Goal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Goal24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        
        entry = new UCMElementCreationEntry(
                "Task", "Create a Task", IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class,
                        IntentionalElementType.TASK), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Task16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Task24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new UCMElementCreationEntry(
                "Ressource", "Create a Ressource", IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class,
                        IntentionalElementType.RESSOURCE), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Ressource16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Ressource24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        add(componentsDrawer);
        
        

    }
    
    /**
     * Returns the preference store for the ShapesPlugin.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore()
     */
    private static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Return a FlyoutPreferences instance used to save/load the preferences of a flyout palette.
     * 
     * @return The flyout palette preferences.
     */
    public static FlyoutPreferences createPalettePreferences() {
        // set default flyout palette preference values, in case the preference
        // store
        // does not hold stored values for the given preferences
        getPreferenceStore().setDefault(PALETTE_DOCK_LOCATION, -1);
        getPreferenceStore().setDefault(PALETTE_STATE, DEFAULT_PALETTE_STATE);
        getPreferenceStore().setDefault(PALETTE_SIZE, DEFAULT_PALETTE_SIZE);

        return new FlyoutPreferences() {
            public int getDockLocation() {
                return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
            }

            public int getPaletteState() {
                return getPreferenceStore().getInt(PALETTE_STATE);
            }

            public int getPaletteWidth() {
                return getPreferenceStore().getInt(PALETTE_SIZE);
            }

            public void setDockLocation(int location) {
                getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
            }

            public void setPaletteState(int state) {
                getPreferenceStore().setValue(PALETTE_STATE, state);
            }

            public void setPaletteWidth(int width) {
                getPreferenceStore().setValue(PALETTE_SIZE, width);
            }
        };
    }

    /**
     * @return Returns the pALETTE_DOCK_LOCATION.
     */
    public static String getPALETTE_DOCK_LOCATION() {
        return PALETTE_DOCK_LOCATION;
    }


    /**
     * @return Returns the URNspec associated with this palette. 
     */
    public URNspec getURNspec() {
        if (parent != null)
            return parent.getModel();
        else
            return urn;
    }

}
