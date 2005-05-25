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
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.PathToolEntry;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.AndFork;
import ucm.map.ComponentRef;
import ucm.map.OrFork;
import ucm.map.RespRef;
import ucm.map.Stub;
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
    private static final String PALETTE_DOCK_LOCATION = "jUCMNAVPaletteFactory.Location";

    /** Preference ID used to persist the palette size. */
    private static final String PALETTE_SIZE = "jUCMNAVPaletteFactory.Size";

    /** Preference ID used to persist the flyout palette's state. */
    private static final String PALETTE_STATE = "jUCMNAVPaletteFactory.State";

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
        PaletteGroup controls = new PaletteGroup("Controls");
        add(controls);

        // the selection tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);
        
        ToolEntry entry;
        
        entry = new PathToolEntry("PathTool", "PathTool", getURNspec(), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/pathTool.gif"), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/AndJoin24.gif"));
        controls.add(entry);

        PaletteDrawer componentsDrawer = new PaletteDrawer("Components");

        entry = new CombinedTemplateCreationEntry("Team", "Create a team", ComponentRef.class, new ModelCreationFactory(getURNspec(), ComponentRef.class,
                ComponentKind.TEAM), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), ImageDescriptor.createFromFile(
                JUCMNavPlugin.class, "icons/Component24.gif"));
        componentsDrawer.add(entry);
        //        entry = new CombinedTemplateCreationEntry("Object", "Create an
        // object", null, new ModelCreationFactory(null),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Object16.gif"),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Object24.gif"));
        //		componentsDrawer.add(entry);
        //        entry = new CombinedTemplateCreationEntry("Process", "Create a
        // process", null, new ModelCreationFactory(null),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Process16.gif"),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Process24.gif"));
        //		componentsDrawer.add(entry);
        //        entry = new CombinedTemplateCreationEntry("ISR", "Create an ISR",
        // null, new ModelCreationFactory(null),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/ISR16.gif"),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/ISR24.gif"));
        //		componentsDrawer.add(entry);
        //        entry = new CombinedTemplateCreationEntry("Pool", "Create a pool",
        // null, new ModelCreationFactory(null),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Pool16.gif"),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Pool24.gif"));
        //		componentsDrawer.add(entry);
        //        entry = new CombinedTemplateCreationEntry("Agent", "Create an agent",
        // null, new ModelCreationFactory(null),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Agent16.gif"),
        // ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Agent24.gif"));
        //		componentsDrawer.add(entry);
        entry = new CombinedTemplateCreationEntry("Other", "Create an other component", ComponentRef.class, new ModelCreationFactory(getURNspec(),
                ComponentRef.class, ComponentKind.OTHER), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/Component24.gif"));
        componentsDrawer.add(entry);
        add(componentsDrawer);

        componentsDrawer = new PaletteDrawer("Path");

//        entry = new CombinedTemplateCreationEntry("StartPoint", "Creates a path", StartPoint.class, new ModelCreationFactory(getURNspec(), StartPoint.class),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class,
//                        "icons/Start24.gif"));
//        componentsDrawer.add(entry);
//        entry.setId("StartPoint");
//
//        entry = new CombinedTemplateCreationEntry("Node", "Creates an empty node", EmptyPoint.class, new ModelCreationFactory(getURNspec(), EmptyPoint.class),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class,
//                        "icons/Node24.gif"));
//        entry.setId("EmptyPoint");
//        componentsDrawer.add(entry);
//
//        endPointTool = new CombinedTemplateCreationEntry("End Point", "Creates an end point", EndPoint.class, new ModelCreationFactory(getURNspec(),
//                EndPoint.class), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class,
//                "icons/End24.gif"));
//        endPointTool.setId("EndPoint");
//        componentsDrawer.add(endPointTool);

        entry = new CombinedTemplateCreationEntry("Responsibility", "Creates a responsibility", RespRef.class, new ModelCreationFactory(getURNspec(),
                RespRef.class), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class,
                "icons/Resp24.gif"));
        entry.setId("Responsibility");
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry("Or Fork", "Creates an OR fork", null, new ModelCreationFactory(getURNspec(), OrFork.class), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork24.gif"));
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry("And Fork", "Creates an AND fork", null, new ModelCreationFactory(getURNspec(), AndFork.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class,
                        "icons/AndFork24.gif"));
        componentsDrawer.add(entry);

        entry = new CombinedTemplateCreationEntry("Stub", "Creates a stub", null, new ModelCreationFactory(getURNspec(), Stub.class), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub24.gif"));
        componentsDrawer.add(entry);
        
        entry = new CombinedTemplateCreationEntry("Dynamic Stub", "Creates a dynamic stub", null, new ModelCreationFactory(getURNspec(), Stub.class, 1), ImageDescriptor
                .createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif"), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub24.gif"));
        componentsDrawer.add(entry);

        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Loop",
        //                "Creates a loop",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Loop16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Loop24.gif"));
        //        componentsDrawer.add(entry);
        //		
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Goal Tag",
        //                "Creates a goal tag",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/GoalTag16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/GoalTag24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Failure Point",
        //                "Creates a failure point",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/FailurePoint16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/FailurePoint24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //		add(componentsDrawer);
        //        
        //        componentsDrawer = new PaletteDrawer("Fork");
        //        
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "And Fork",
        //                "Creates an and fork",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/AndFork16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/AndFork24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Or Join",
        //                "Creates an or join",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/OrJoin16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/OrJoin24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "And Join",
        //                "Creates a and join",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/AndJoin16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/AndJoin24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        add(componentsDrawer);
        //        
        //        componentsDrawer = new PaletteDrawer("Timing");
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Timer",
        //                "Creates a timer",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Timer16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Timer24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Wait",
        //                "Creates a wait",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Wait16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/Wait24.gif"));
        //        componentsDrawer.add(entry);
        //        
        //        entry =
        //            new CombinedTemplateCreationEntry(
        //                "Timestamp",
        //                "Creates a timestamp",
        //                null,
        //                new ModelCreationFactory(null),
        //                ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/TimestampPoint16.gif"),
        //				ImageDescriptor.createFromFile(JUCMNavPlugin.class,
        // "icons/TimestampPoint24.gif"));
        //        componentsDrawer.add(entry);

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