package seg.jUCMNav.editors.palette;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
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
import seg.jUCMNav.editors.palette.tools.PathToolEntry;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.ComponentKind;

/**
 * Created on 2005-01-30
 * 
 * This is the main palette of UCMEditor.
 * 
 * @author Etienne Tremblay
 */
public class UcmPaletteRoot extends PaletteRoot {

    /** Default palette size. */
    private static final int DEFAULT_PALETTE_SIZE = 125;

    /* pinned open */
    private static final int DEFAULT_PALETTE_STATE = 4;

    /** Preference ID used to persist the palette location. */
    private static final String PALETTE_DOCK_LOCATION = "jUCMNAVPaletteFactory.Location"; //$NON-NLS-1$

    /** Preference ID used to persist the palette size. */
    private static final String PALETTE_SIZE = "jUCMNAVPaletteFactory.Size"; //$NON-NLS-1$

    /** Preference ID used to persist the flyout palette's state. */
    private static final String PALETTE_STATE = "jUCMNAVPaletteFactory.State"; //$NON-NLS-1$

    private ToolEntry endPointTool;

    // for palette purposes
    private UCMNavMultiPageEditor parent;

    // for other purposes
    private URNspec urn;

    /**
     * Creates a new UcmPaletteRoot instance. To be called by the palette or anything else that might not be recreated when a save-as is done.
     */
    public UcmPaletteRoot(UCMNavMultiPageEditor parent) {
        // create root
        super();

        // we need to pass a urnspec to our ModelCreationFactory
        //
        // this constructor is to be called by the palette; since it won't be rebuild when we do a save-as, we need to keep a reference on the actual editor
        // instead of the URNspec directly.
        this.parent = parent;

        buildPalette();
    }

    /**
     * Creates a new UcmPaletteRoot instance.
     */
    public UcmPaletteRoot(URNspec urn) {
        // create root
        super();

        // we need to pass a urnspec to our ModelCreationFactory
        this.urn = urn;

        buildPalette();
    }

    /**
     *  
     */
    private void buildPalette() {
        // a group of default control tools
        PaletteGroup controls = new PaletteGroup(Messages.getString("UcmPaletteRoot.controls")); //$NON-NLS-1$
        add(controls);

        // the selection tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

        ToolEntry entry;

        entry = new PathToolEntry(
                Messages.getString("UcmPaletteRoot.pathTool"), Messages.getString("UcmPaletteRoot.pathTool"), getURNspec(), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/pathTool16.gif"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/pathTool24.gif")); //$NON-NLS-1$
        controls.add(entry);

        PaletteDrawer componentsDrawer = new PaletteDrawer(Messages.getString("UcmPaletteRoot.components")); //$NON-NLS-1$

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.team"), Messages.getString("UcmPaletteRoot.createTeam"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentKind.TEAM), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Component24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.object"), Messages.getString("UcmPaletteRoot.createObject"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentKind.OBJECT), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Object16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Object24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.process"), Messages.getString("UcmPaletteRoot.createProcess"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, ComponentKind.PROCESS), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$
                                JUCMNavPlugin.class, "icons/Process16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Process24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.agent"), Messages.getString("UcmPaletteRoot.createAgent"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, ComponentKind.AGENT), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$
                                JUCMNavPlugin.class, "icons/Agent16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Agent24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        entry = new CombinedTemplateCreationEntry("Actor", "Create an actor", ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class,
                ComponentKind.ACTOR), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Actor16.gif"), ImageDescriptor.createFromFile(
                JUCMNavPlugin.class, "icons/Actor24.gif"));
        componentsDrawer.add(entry);
        
        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.other"), Messages.getString("UcmPaletteRoot.createOther"), ComponentRef.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentRef.class, ComponentKind.OTHER), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), ImageDescriptor //$NON-NLS-1$
                        .createFromFile(JUCMNavPlugin.class, "icons/Component24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        add(componentsDrawer);

        componentsDrawer = new PaletteDrawer(Messages.getString("UcmPaletteRoot.path")); //$NON-NLS-1$

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.responsibility"), Messages.getString("UcmPaletteRoot.createResponsibility"), RespRef.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        RespRef.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/Resp24.gif")); //$NON-NLS-1$
        entry.setId(Messages.getString("UcmPaletteRoot.responsibility")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.stub"), Messages.getString("UcmPaletteRoot.createStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.dynamicStub"), Messages.getString("UcmPaletteRoot.createDynamicStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 1), //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/DynStub16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/DynStub24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry("Timer", "Creates a timer", Timer.class, new ModelCreationFactory(getURNspec(), Timer.class), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/Timer16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Timer24.gif"));
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry("Waiting Place", "Creates a waiting place", WaitingPlace.class, new ModelCreationFactory(getURNspec(),
                WaitingPlace.class), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Wait16.gif"), ImageDescriptor.createFromFile(
                JUCMNavPlugin.class, "icons/Wait24.gif"));
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.dirrectionArrow"), Messages.getString("UcmPaletteRoot.createArrow"), DirectionArrow.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        DirectionArrow.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/DirectionArrow16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/DirectionArrow24.gif")); //$NON-NLS-1$
        entry.setId(Messages.getString("UcmPaletteRoot.dirrectionArrowId")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.orFork"), Messages.getString("UcmPaletteRoot.createOrFork"), OrFork.class, new ModelCreationFactory(getURNspec(), OrFork.class), //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/OrFork24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.andFork"), Messages.getString("UcmPaletteRoot.createAndFork"), AndFork.class, new ModelCreationFactory(getURNspec(), AndFork.class), //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/AndFork24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.orJoin"), Messages.getString("UcmPaletteRoot.createOrJoin"), OrJoin.class, new ModelCreationFactory(getURNspec(), OrJoin.class), //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrJoin16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/OrJoin24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry(
                Messages.getString("UcmPaletteRoot.andJoin"), Messages.getString("UcmPaletteRoot.createAndJoin"), AndJoin.class, new ModelCreationFactory(getURNspec(), AndJoin.class), //$NON-NLS-1$ //$NON-NLS-2$
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndJoin16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, //$NON-NLS-1$
                        "icons/AndJoin24.gif")); //$NON-NLS-1$
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
     * @return Returns the endPointTool.
     */
    public ToolEntry getEndPointTool() {
        return endPointTool;
    }

    /**
     * @param endPointTool
     *            The endPointTool to set.
     */
    public void setEndPointTool(ToolEntry endPointTool) {
        this.endPointTool = endPointTool;
    }

    public URNspec getURNspec() {
        if (parent != null)
            return parent.getModel();
        else
            return urn;
    }

}