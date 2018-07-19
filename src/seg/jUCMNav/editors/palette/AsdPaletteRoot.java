package seg.jUCMNav.editors.palette;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.Disposable;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import asd.Tool;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.URNElementCreationEntry;
import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;

/**
 * This is the main palette of UCMEditor. Elements are added to the palette in buildPalette()
 * 
 * @author Etienne Tremblay, gunterm
 */
public class AsdPaletteRoot extends PaletteRoot implements Disposable {

    /** Default palette size. */
    protected static final int DEFAULT_PALETTE_SIZE = 125;

    /** pinned open */
    protected static final int DEFAULT_PALETTE_STATE = 4;

    /** Preference ID used to persist the palette location. */
    protected static final String PALETTE_DOCK_LOCATION = "jUCMNAVPaletteFactory.Location"; //$NON-NLS-1$

    /** Preference ID used to persist the palette size. */
    protected static final String PALETTE_SIZE = "jUCMNAVPaletteFactory.Size"; //$NON-NLS-1$

    /** Preference ID used to persist the flyout palette's state. */
    protected static final String PALETTE_STATE = "jUCMNAVPaletteFactory.State"; //$NON-NLS-1$

    // to obtain URNspec
    protected UCMNavMultiPageEditor parent;

    /** Map from string to ToolEntry for hotkeys. */
    protected HashMap keyboardMapping;

    protected Vector advancedItems = new Vector();

    /**
     * Creates a new AsdPaletteRoot instance. To be called by the palette or anything else that might not be recreated when a save-as is done.
     */
    public AsdPaletteRoot(UCMNavMultiPageEditor parent) {
        // create root
        super();

        // we need to pass a urnspec to our ModelCreationFactory
        //
        // this constructor is to be called by the palette; since it won't be rebuild when we do a save-as, we need to keep a reference on the actual editor
        // instead of the URNspec directly.
        this.parent = parent;

        buildPalette();
      
    }

    public ToolEntry getAssociatedTool(String key) {
        if (keyboardMapping.containsKey(key))
            return (ToolEntry) keyboardMapping.get(key);
        else
            return null;
    }

    /**
     * Builds the palette entries.
     */
    protected void buildPalette() {
        keyboardMapping = new HashMap();
        // a group of default control tools
        PaletteGroup controls = new PaletteGroup(Messages.getString("AsdPaletteRoot.controls")); //$NON-NLS-1$
        add(controls);

        // the selection tool; default tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

       // ToolEntry entry;
/*
        entry = new PathToolEntry(
                Messages.getString("AsdPaletteRoot.pathTool"), Messages.getString("AsdPaletteRoot.pathTool"), getURNspec(), JUCMNavPlugin.getImageDescriptor("icons/pathTool16.gif"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                JUCMNavPlugin.getImageDescriptor("icons/pathTool24.gif")); //$NON-NLS-1$
        controls.add(entry);
        keyboardMapping.put(" ", entry); //$NON-NLS-1$ 

        entry = new URNElementCreationEntry(
                "Comment", Messages.getString("AsdPaletteRoot.CreateAComment"), Comment.class, new ModelCreationFactory(getURNspec(), Comment.class), JUCMNavPlugin.getImageDescriptor("icons/Comment16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                JUCMNavPlugin.class, "icons/Comment24.gif")); //$NON-NLS-1$
        controls.add(entry);
        keyboardMapping.put("q", entry); //$NON-NLS-1$ */

        PaletteDrawer componentsDrawer = new PaletteDrawer(Messages.getString("AsdPaletteRoot.components")); //$NON-NLS-1$

      /*  entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.team"), Messages.getString("AsdPaletteRoot.createTeam"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentKind.TEAM), JUCMNavPlugin.getImageDescriptor("icons/Component16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Component24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("t", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.object"), Messages.getString("AsdPaletteRoot.createObject"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentKind.OBJECT), JUCMNavPlugin.getImageDescriptor("icons/Object16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Object24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("o", entry); //$NON-NLS-1$
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.process"), Messages.getString("AsdPaletteRoot.createProcess"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, ComponentKind.PROCESS), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$
                                JUCMNavPlugin.class, "icons/Process16.gif"), JUCMNavPlugin.getImageDescriptor("icons/Process24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("p", entry); //$NON-NLS-1$
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.agent"), Messages.getString("AsdPaletteRoot.createAgent"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, ComponentKind.AGENT), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$
                                JUCMNavPlugin.class, "icons/Agent16.gif"), JUCMNavPlugin.getImageDescriptor("icons/Agent24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("i", entry); //$NON-NLS-1$
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.actor"), Messages.getString("AsdPaletteRoot.createActor"), ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentKind.ACTOR), JUCMNavPlugin.getImageDescriptor("icons/Actor16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Actor24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("a", entry); //$NON-NLS-1$
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.other"), Messages.getString("AsdPaletteRoot.createOther"), ComponentRef.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        ComponentRef.class, ComponentKind.OTHER), JUCMNavPlugin.getImageDescriptor("icons/Component16.gif"), ImageDescriptor //$NON-NLS-1$
                        .createFromFile(JUCMNavPlugin.class, "icons/Component24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("u", entry); //$NON-NLS-1$
        add(componentsDrawer);

        componentsDrawer = new PaletteDrawer(Messages.getString("AsdPaletteRoot.path")); //$NON-NLS-1$

       */     
       
       
       ToolEntry entry = new URNElementCreationEntry(
    		   Messages.getString("AsdPaletteRoot.tool"), Messages.getString("AsdPaletteRoot.createTool"), Tool.class, new ModelCreationFactory(getURNspec(), Tool.class), 
    		   JUCMNavPlugin.getImageDescriptor("icons/Resp16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                               JUCMNavPlugin.class, "icons/Resp24.gif")); //$NON-NLS-1$
       controls.add(entry);
       keyboardMapping.put("t", entry); //$NON-NLS-1$
         // add(controls);   
       //add(componentsDrawer);

   /*     entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.timer"), Messages.getString("AsdPaletteRoot.createTimer"), Timer.class, new ModelCreationFactory(getURNspec(), Timer.class), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/Timer16.gif"), JUCMNavPlugin.getImageDescriptor("icons/Timer24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("m", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.waitingPlace"), Messages.getString("AsdPaletteRoot.createWaitingPlace"), WaitingPlace.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        WaitingPlace.class), JUCMNavPlugin.getImageDescriptor("icons/Wait16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Wait24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("w", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.directionArrow"), Messages.getString("AsdPaletteRoot.createArrow"), DirectionArrow.class, new ModelCreationFactory(getURNspec(), //$NON-NLS-1$ //$NON-NLS-2$
                        DirectionArrow.class), JUCMNavPlugin.getImageDescriptor("icons/DirectionArrow16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/DirectionArrow24.gif")); //$NON-NLS-1$
        entry.setId(Messages.getString("AsdPaletteRoot.directionArrowId")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put(">", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.orFork"), Messages.getString("AsdPaletteRoot.createOrFork"), OrFork.class, new ModelCreationFactory(getURNspec(), OrFork.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/OrFork16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/OrFork24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("f", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.andFork"), Messages.getString("AsdPaletteRoot.createAndFork"), AndFork.class, new ModelCreationFactory(getURNspec(), AndFork.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/AndFork16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/AndFork24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("g", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.orJoin"), Messages.getString("AsdPaletteRoot.createOrJoin"), OrJoin.class, new ModelCreationFactory(getURNspec(), OrJoin.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/OrJoin16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/OrJoin24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("j", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.andJoin"), Messages.getString("AsdPaletteRoot.createAndJoin"), AndJoin.class, new ModelCreationFactory(getURNspec(), AndJoin.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/AndJoin16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/AndJoin24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("k", entry); //$NON-NLS-1$

        add(componentsDrawer);

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.FailurePoint"), Messages.getString("AsdPaletteRoot.CreateFailurePoint"), FailurePoint.class, new ModelCreationFactory(getURNspec(), FailurePoint.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/FailurePoint16.gif"), JUCMNavPlugin.getImageDescriptor("icons/FailurePoint24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("l", entry); //$NON-NLS-1$

        componentsDrawer = new PaletteDrawer(Messages.getString("AsdPaletteRoot.Stubs")); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.stub"), Messages.getString("AsdPaletteRoot.createStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif"), JUCMNavPlugin.getImageDescriptor("icons/Stub24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("s", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.dynamicStub"), Messages.getString("AsdPaletteRoot.createDynamicStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 1), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/DynStub16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/DynStub24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("d", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.SyncStub"), Messages.getString("AsdPaletteRoot.CreateSyncStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 4), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/SyncStub16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/SyncStub24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        advancedItems.add(entry);
        keyboardMapping.put("y", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.BlockStub"), Messages.getString("AsdPaletteRoot.CreateBlockStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 5), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/BlockStub16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/BlockStub24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        advancedItems.add(entry);
        keyboardMapping.put("b", entry); //$NON-NLS-1$
        
        add(componentsDrawer);
        
        componentsDrawer = new PaletteDrawer(Messages.getString("AsdPaletteRoot.Aspects")); //$NON-NLS-1$
        advancedItems.add(componentsDrawer);
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.pointcutStub"), Messages.getString("AsdPaletteRoot.createPointcutStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 2), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/PointcutStub16.gif"), JUCMNavPlugin.getImageDescriptor( //$NON-NLS-1$
                        "icons/PointcutStub24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);
        keyboardMapping.put("c", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(Messages.getString("AsdPaletteRoot.PointcutReplacementStub"), Messages.getString("AsdPaletteRoot.CreatePointcutReplacementStub"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 3), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/PointcutRepStub16.gif"), JUCMNavPlugin.getImageDescriptor("icons/PointcutRepStub24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("v", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.AspectMarker"), Messages.getString("AsdPaletteRoot.CreateAspectMarker"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 6), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/aspectMarker16.gif"), JUCMNavPlugin.getImageDescriptor("icons/aspectMarker24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("1", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.EntranceAspectMarker"), Messages.getString("AsdPaletteRoot.CreateEntranceAspectMarker"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 7), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/aspectMarkerEntrance16.gif"), JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerEntrance24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("2", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.ExitAspectMarker"), Messages.getString("AsdPaletteRoot.CreateExitAspectMarker"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 8), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/aspectMarkerExit16.gif"), JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerExit24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("3", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.ConditionAspectMarker"), Messages.getString("AsdPaletteRoot.CreateConditionAspectMarker"), Stub.class, new ModelCreationFactory(getURNspec(), Stub.class, 9), ImageDescriptor //$NON-NLS-1$ //$NON-NLS-2$
                        .createFromFile(JUCMNavPlugin.class, "icons/aspectMarkerCond16.gif"), JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerCond24.gif")); //$NON-NLS-1$ //$NON-NLS-2$
        componentsDrawer.add(entry);
        keyboardMapping.put("4", entry); //$NON-NLS-1$
        
        entry = new URNElementCreationEntry(
                Messages.getString("AsdPaletteRoot.AnythingPointcut"), Messages.getString("AsdPaletteRoot.CreateAnythingPointcut"), Anything.class, new ModelCreationFactory(getURNspec(), Anything.class), JUCMNavPlugin.getImageDescriptor("icons/anything16.gif"), JUCMNavPlugin.getImageDescriptor("icons/anything24.gif")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        componentsDrawer.add(entry);
        keyboardMapping.put("-", entry); //$NON-NLS-1$
        
        add(componentsDrawer);

        // must be last line. 
        showAdvancedStubs(DisplayPreferences.getInstance().isAdvancedControlEnabled() && DisplayPreferences.getInstance().isShowAspect());
*/
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
        assert parent != null;
        return parent.getModel();
    }

    public void dispose() {
        for (Iterator iterator = getChildren().iterator(); iterator.hasNext();) {
            PaletteEntry elem = (PaletteEntry) iterator.next();
            if (elem instanceof PaletteDrawer) {
                PaletteDrawer drawer = (PaletteDrawer) elem;
                Vector v2 = new Vector();
                v2.addAll(drawer.getChildren());

                for (Iterator iterator2 = v2.iterator(); iterator2.hasNext();) {
                    PaletteEntry elem2 = (PaletteEntry) iterator2.next();
                    if (elem2 instanceof URNElementCreationEntry) {
                        URNElementCreationEntry entry = (URNElementCreationEntry) elem2;
                        entry.setFactory(null);
                    }
                    elem2.setParent(null);
                }
            }
        }
        setChildren(new Vector());
        parent = null;
        keyboardMapping.clear();
        keyboardMapping = null;
    }

    public void showAdvancedStubs(boolean show) {
        for (Iterator i = advancedItems.iterator(); i.hasNext();) {
            PaletteEntry t = (PaletteEntry) i.next();
            t.setVisible(show);
        }
    }
}