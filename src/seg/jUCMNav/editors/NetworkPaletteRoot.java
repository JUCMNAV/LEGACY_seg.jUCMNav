/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editors;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.emf.ModelCreationFactory;
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.Responsibility;
import seg.jUCMNav.model.ucm.StartPoint;


public class NetworkPaletteRoot extends PaletteRoot
{
	
	/** Default palette size. */
	private static final int DEFAULT_PALETTE_SIZE = 125;

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "ShapesEditorPaletteFactory.Location";
	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "ShapesEditorPaletteFactory.Size";
	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "ShapesEditorPaletteFactory.State";

    /**
     * Creates a new NetworkPaletteRoot instance.
     * 
     */
    public NetworkPaletteRoot()
    {
        // create root
        super();

        // a group of default control tools
        PaletteGroup controls = new PaletteGroup("Controls");
        add(controls);

        // the selection tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);

        // use selection tool as default entry
        setDefaultEntry(tool);

        // the marquee selection tool
        controls.add(new MarqueeToolEntry());


        CreationToolEntry entry;
        
        PaletteDrawer componentsDrawer; //= new PaletteDrawer("Components");
        
//        entry = new CombinedTemplateCreationEntry("Team", "Create a team", Component.class, new ModelCreationFactory(Component.class), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("Object", "Create an object", null, new ModelCreationFactory(null), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Object16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("Process", "Create a process", null, new ModelCreationFactory(null), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Process16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("ISR", "Create an ISR", null, new ModelCreationFactory(null), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ISR16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("Pool", "Create a pool", null, new ModelCreationFactory(null), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Pool16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("Agent", "Create an agent", null, new ModelCreationFactory(null), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Agent16.gif"), null);
//		componentsDrawer.add(entry);
//        entry = new CombinedTemplateCreationEntry("Other", "Create an other component", Component.class, new ModelCreationFactory(Component.class), ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif"), null);
//		componentsDrawer.add(entry);
//		
//		add(componentsDrawer);
		
		componentsDrawer = new PaletteDrawer("Path");
//		entry =
//			new ConnectionCreationToolEntry(
//				"Link",
//				"Creates a link",
//				new ModelCreationFactory(Link.class),
//				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/connection_s16.gif"), 
//				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/connection_s24.gif"));
//		componentsDrawer.add(entry);
		
		entry =
            new CombinedTemplateCreationEntry(
                "Path",
                "Creates a Path",
                Path.class,
                new ModelCreationFactory(Path.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/connection_s16.gif"), 
				ImageDescriptor.createFromFile(JUCMNavPlugin.class, null));
        componentsDrawer.add(entry);
		
		entry =
            new CombinedTemplateCreationEntry(
                "Node",
                "Creates a node",
                Node.class,
                new ModelCreationFactory(Node.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif"), 
				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ellipse24.gif"));
        componentsDrawer.add(entry);
        
        entry =
            new CombinedTemplateCreationEntry(
                "Start Point",
                "Creates a Start Point",
                StartPoint.class,
                new ModelCreationFactory(StartPoint.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif"), 
				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ellipse24.gif"));
        componentsDrawer.add(entry);
        
        entry =
            new CombinedTemplateCreationEntry(
                "End Point",
                "Creates a End Point",
                EndPoint.class,
                new ModelCreationFactory(EndPoint.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif"), 
				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ellipse24.gif"));
        componentsDrawer.add(entry);
        
        entry =
            new CombinedTemplateCreationEntry(
                "Responsibility",
                "Creates a Responsibility",
                Responsibility.class,
                new ModelCreationFactory(Responsibility.class),
                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif"), 
				ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ellipse24.gif"));
        componentsDrawer.add(entry);
        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Stub",
//                "Creates a stub",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Loop",
//                "Creates a loop",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Loop16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//		
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Goal Tag",
//                "Creates a goal tag",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GoalTag16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Failure Point",
//                "Creates a failure point",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/FailurePoint16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//		add(componentsDrawer);
//        
//        componentsDrawer = new PaletteDrawer("Fork");
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Or Fork",
//                "Creates an or fork",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "And Fork",
//                "Creates an and fork",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Or Join",
//                "Creates an or join",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrJoin16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "And Join",
//                "Creates a and join",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndJoin16.gif"), 
//				null);
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
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Timer16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Wait",
//                "Creates a wait",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Wait16.gif"), 
//				null);
//        componentsDrawer.add(entry);
//        
//        entry =
//            new CombinedTemplateCreationEntry(
//                "Timestamp",
//                "Creates a timestamp",
//                null,
//                new ModelCreationFactory(null),
//                ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/TimestampPoint16.gif"), 
//				null);
//        componentsDrawer.add(entry);
        
        add(componentsDrawer);
    }
    
    /** 
     * Returns the preference store for the ShapesPlugin.
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore() 
     */
    private static IPreferenceStore getPreferenceStore() {
    	return JUCMNavPlugin.getDefault().getPreferenceStore();
    }
    
    /**
     * Return a FlyoutPreferences instance used to save/load the preferences of a flyout palette.
     */
    public static FlyoutPreferences createPalettePreferences() {
    	// set default flyout palette preference values, in case the preference store
    	// does not hold stored values for the given preferences
        getPreferenceStore().setDefault(PALETTE_DOCK_LOCATION, -1);
    	getPreferenceStore().setDefault(PALETTE_STATE, -1);
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
}
