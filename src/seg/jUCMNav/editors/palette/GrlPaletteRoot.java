package seg.jUCMNav.editors.palette;

import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.URNElementCreationEntry;
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
        PaletteGroup controls = new PaletteGroup(Messages.getString("GrlPaletteRoot.controls"));  //$NON-NLS-1$
        add(controls);

        // the selection tool; default tool 
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

        ToolEntry entry;

        PaletteDrawer linksDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.links"));  //$NON-NLS-1$

        // Add a decomposition
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.decomposition"), Messages.getString("GrlPaletteRoot.createDecomposition"), new ModelCreationFactory(getURNspec(), Decomposition.class),  //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Decomposition16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Decomposition24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add a contribution
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.contribution"), Messages.getString("GrlPaletteRoot.createContribution"), new ModelCreationFactory(getURNspec(), Contribution.class),  //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Contribution16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add a dependency
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.dependency"), Messages.getString("GrlPaletteRoot.createDependency"), new ModelCreationFactory(getURNspec(), Dependency.class),  //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Dependency16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Dependency24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add belief link
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.beliefLink"), Messages.getString("GrlPaletteRoot.createBeliefLink"), new ModelCreationFactory(getURNspec(), BeliefLink.class),  //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/BeliefLink16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/BeliefLink24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        add(linksDrawer);
        
        PaletteDrawer componentsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.components"));  //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.actor"), Messages.getString("GrlPaletteRoot.createActor"), ActorRef.class, new ModelCreationFactory(getURNspec(), ActorRef.class), //$NON-NLS-1$ //$NON-NLS-2$
                        ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GRLActor16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/GRLActor24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        add(componentsDrawer);
        
        PaletteDrawer elementsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.elements"));  //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.softgoal"), Messages.getString("GrlPaletteRoot.createSoftgoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.SOFTGOAL), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Softgoal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.goal"), Messages.getString("GrlPaletteRoot.createGoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.GOAL), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Goal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Goal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        
        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.task"), Messages.getString("GrlPaletteRoot.createTask"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.TASK), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Task16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Task24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.resource"), Messages.getString("GrlPaletteRoot.createResource"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class,  //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.RESSOURCE), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resource16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Resource24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.belief"), Messages.getString("GrlPaletteRoot.createBelief"), Belief.class, new ModelCreationFactory(getURNspec(), Belief.class), //$NON-NLS-1$ //$NON-NLS-2$
                        ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Belief16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Belief24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        
        add(elementsDrawer);
        
        

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
