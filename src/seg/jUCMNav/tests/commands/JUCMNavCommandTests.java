package seg.jUCMNav.tests.commands;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.lowagie.text.List;

import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.AlignCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.DistributeCommand;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.create.CreateScenarioCommand;
import seg.jUCMNav.model.commands.create.CreateScenarioGroupCommand;
import seg.jUCMNav.model.commands.cutcopypaste.CopyCommand;
import seg.jUCMNav.model.commands.cutcopypaste.PasteCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteLabelCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import seg.jUCMNav.model.commands.delete.internal.DeletePathCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteStartNCEndCommand;
import seg.jUCMNav.model.commands.metadata.ChangeHyperlinkCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import seg.jUCMNav.model.commands.transformations.CutAnyPathCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.MoveScenarioCommand;
import seg.jUCMNav.model.commands.transformations.RefactorIntoStubCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.TransmogrifyForkOrJoinCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.model.util.SafePathChecker;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.Component;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.UCMmodelElement;

/**
 * Tests that run on seg.jUCMNav.model.commands. To be run as PDE JUnit tests. See CommandLinePDEJUnit and DevDocCommand
 * 
 * Uses interesting setUp()/tearDown();
 * 
 * @author jkealey
 * 
 */
public class JUCMNavCommandTests extends TestCase {

    public static void main(String[] args) {

        junit.textui.TestRunner.run(JUCMNavCommandTests.class);
    }
    public UCMmodelElement componentRefWithLabel;
    public ComponentRef compRef;
    public ComponentRef compRef2;
    public CommandStack cs;
    public UCMNavMultiPageEditor editor;
    public EndPoint end;
    public RespRef resp;

    public Connect connect;
    public UCMmap map;
    public UCMmodelElement pathNodeWithLabel;
    public StartPoint start;

    public Stub stub;
    public PluginBinding plugin;
    public PathNode fork;
    public WaitingPlace wait;

    // during teardown, if testBindings==true, call verifyBindings()
    public boolean testBindings;
    public IFile testfile;

    public URNspec urnspec;

    /*
     * @see TestCase#setUp()
     */
    public void setUp() throws Exception {
        super.setUp();

        initjucmnav();

        ComponentRef backgroundBindingChecker = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        Command cmd = new AddContainerRefCommand(map, backgroundBindingChecker);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(backgroundBindingChecker, -1000, -1000, 5000, 5000);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        testBindings = true;

        // Set the preferences for deleting to ALWAYS
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELDEFINITION, DeletePreferences.PREF_ALWAYS);
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELREFERENCE, DeletePreferences.PREF_ALWAYS);

    }

    public void initjucmnav() throws CoreException, PartInitException {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IProject testproject = workspaceRoot.getProject("jUCMNav-tests"); //$NON-NLS-1$
        if (!testproject.exists())
            testproject.create(null);

        if (!testproject.isOpen())
            testproject.open(null);

        testfile = testproject.getFile("jUCMNav-test.jucm"); //$NON-NLS-1$

        // start with clean file
        if (testfile.exists())
            testfile.delete(true, false, null);

        testfile.create(new ByteArrayInputStream("".getBytes()), false, null); //$NON-NLS-1$

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(testfile.getName());
        editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(testfile), desc.getId());

        // generate a top level model element
        urnspec = editor.getModel();
        // urnspec = (URNspec) ModelCreationFactory.getNewURNspec();

        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(0);

        // cs = new CommandStack();
        cs = editor.getDelegatingCommandStack();
    }

    /*
     * @see TestCase#tearDown()
     */
    public void tearDown() throws Exception {
        super.tearDown();

        /**
         * Note: I once had JUnit telling me that I had exceptions thrown in tearDown() when in fact they were from notifications made during the actual tests.
         * 
         * Run in DEBUG to see the true source.
         */

        // Tests for the TrimpEmptyNodeCommand; should work with any model.
        Command cmd;
        for (Iterator iter = urnspec.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            UCMmap map = (UCMmap) iter.next();
            cmd = new TrimEmptyNodeCommand(map);
            assertTrue("Can't execute trim empty node command!", cmd.canExecute()); //$NON-NLS-1$
            cs.execute(cmd);
        }

        try {
            editor.doSave(null);

            // verify the bindings
            if (testBindings)
                verifyBindings();

            int i = cs.getCommands().length;

            if (cs.getCommands().length > 0) {
                assertTrue("Can't undo first command", cs.canUndo()); //$NON-NLS-1$
                cs.undo();
                editor.doSave(null);
                assertTrue("Can't redo first command", cs.canRedo()); //$NON-NLS-1$
                cs.redo();
                editor.doSave(null);
            }

            while (i-- > 0) {
                assertTrue("Can't undo a certain command", cs.canUndo()); //$NON-NLS-1$
                cs.undo();
            }

            editor.doSave(null);

            i = cs.getCommands().length;
            while (i-- > 0) {
                assertTrue("Can't redo a certain command", cs.canRedo()); //$NON-NLS-1$
                cs.redo();
            }

            // verify the bindings
            if (testBindings)
                verifyBindings();

            editor.doSave(null);
            // serialize using UrnModelManager && getName();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        ((ScrollingGraphicalViewer) ((UrnEditor) editor.getActiveEditor()).getGraphicalViewer()).flush();
        editor.closeEditor(false);

        editor = null;
        componentRefWithLabel = null;
        compRef = null;
        cs.dispose();
        cs = null;
        end = null;
        resp = null;
        connect = null;
        map = null;
        pathNodeWithLabel = null;
        start = null;
        stub = null;
        plugin = null;
        fork = null;
        wait = null;
        testfile = null;
        urnspec = null;

        System.out.println("Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 + "kb"); //$NON-NLS-1$ //$NON-NLS-2$

        /*
         * System.out.println("sleeping"); Thread.sleep(10000); System.out.println("next");
         */

    }

    public void testAssertionsEnabled() {
        try {
            assert false;
            fail("Assertions must be enabled via JVM flag -ea or -enableassertions"); //$NON-NLS-1$
        } catch (AssertionError exception) {
            // Ignore
        }
    }

    /**
     * 
     *  
     */
    public void testAddBranchCommand() {
        testAddForkOnConnectionCommand();
        AndFork fork;
        int i = 0;
        // find empty point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof AndFork)) {
            i++;
        }
        assertTrue("No and forks exist for AddBranchCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        fork = (AndFork) map.getNodes().get(i);

        Command cmd = new AddBranchCommand(fork);
        assertTrue("Can't execute AddBranchCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // test adding a timeout path.
        Timer timer = (Timer) ModelCreationFactory.getNewObject(urnspec, Timer.class);
        cmd = new SplitLinkCommand(map, timer, (NodeConnection) map.getConnections().get(0), 149, 875);
        assertTrue("Can't execute SplitLinkCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new AddBranchCommand(timer);
        assertTrue("Can't execute AddBranchCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }
   
    /**
     * Test for AlignCommand.
     *  
     *  @author Patrice Boulet
     */
    public void testAlignCommand(){
    	testSetConstraintCommand();
    	testSetConstraintComponentRefCommand();
    	testSetConstraintBoundComponentRefCompoundCommand();
    	
    	CreateMapCommand cmd1 = new CreateMapCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd1.canExecute()); //$NON-NLS-1$
        cs.execute(cmd1);
        
        assertEquals("map was not added properly in model", urnspec.getUrndef().getSpecDiagrams().size(), 2); //$NON-NLS-1$
        assertEquals("map was not added properly in editor", editor.getPageCount(), 2); //$NON-NLS-1$
        
        // add bogus data to new map
        map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(1);
        
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        compRef.setDiagram(map);
        compRef.setWidth(100);
        compRef.setHeight(100);
        compRef.setX(50);
        compRef.setY(50);
        int compRefOldXCoordinate = compRef.getX();
        int compRefOldYCoordinate = compRef.getY();
        
        compRef2 = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        compRef2.setDiagram(map);
        compRef2.setWidth(100);
        compRef2.setHeight(100);
        compRef2.setX(100);
        compRef2.setY(200);
        int compRef2OldXCoordinate = compRef2.getX();
        int compRef2OldYCoordinate = compRef2.getY();
        
        AddContainerRefCommand cmd2 = new AddContainerRefCommand(map, compRef);
        assertTrue("Can't execute AddContainerRefCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        
        AddContainerRefCommand cmd3 = new AddContainerRefCommand(map, compRef2);
        assertTrue("Can't execute AddContainerRefCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        start.setDiagram(map);
        start.setX(100);
        start.setY(100);
        compRef.getNodes().add(start);
        int startOldXCoordinate = start.getX();
        int startOldYCoordinate = start.getY();
        
        resp = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        resp.setDiagram(map);
        resp.setX(150);
        resp.setY(250);
        compRef2.getNodes().add(resp);
        int respOldXCoordinate = resp.getX();
        int respOldYCoordinate = resp.getY();      
        
        
        LinkedList nodes = new LinkedList<PathNode>();
        nodes.add(start);
        nodes.add(resp);
        
        LinkedList components = new LinkedList<ComponentRef>();
        components.add(compRef);
        components.add(compRef2);
        
        // test align top with nodes
      	AlignCommand cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignTop");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getY() == resp.getY() && start.getY() == 100);
        cs.undo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        
        // test align middle with nodes
      	cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignMiddle");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getY() == resp.getY() && start.getY() == 175);
        cs.undo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        
        // test align bottom with nodes
      	cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignBottom");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getY() == resp.getY() && start.getY() == 250);
        cs.undo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        
        // test align left with nodes
      	cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignLeft");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getX() == resp.getX() && start.getX() == 100);
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        
        // test align center with nodes
      	cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignCenter");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getX() == resp.getX() && start.getX() == 125);
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        
        // test align right with nodes
      	cmd4 = new AlignCommand(nodes, 4, "seg.jUCMNav.AlignRight");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getX() == resp.getX() && start.getX() == 150);
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        
        // testing redo
        
        cs.redo();
        assertTrue(start.getX() == resp.getX() && start.getX() == 150);
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        
        // test align top with components
      	AlignCommand cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignTop");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getY() == compRef2.getY() && compRef.getY() == 50);
        cs.undo();
        assertTrue(compRef.getY() == compRefOldYCoordinate);
        assertTrue(compRef2.getY() == compRef2OldYCoordinate);
       
        // test align middle with components
      	cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignMiddle");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getY() == compRef2.getY() && compRef.getY() == 125);
        cs.undo();
        assertTrue(compRef.getY() == compRefOldYCoordinate);
        assertTrue(compRef2.getY() == compRef2OldYCoordinate);
        
        // test align bottom with components
      	cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignBottom");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getY() == compRef2.getY() && compRef.getY() == compRef2OldYCoordinate);
        cs.undo();
        assertTrue(compRef.getY() == compRefOldYCoordinate);
        assertTrue(compRef2.getY() == compRef2OldYCoordinate);
        
        // test align left with components
      	cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignLeft");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getX() == compRef2.getX() && compRef.getX() == 50);
        cs.undo();
        assertTrue(compRef.getX() == compRefOldXCoordinate);
        assertTrue(compRef2.getX() == compRef2OldXCoordinate);
        
        // test align center with components
      	cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignCenter");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getX() == compRef2.getX() && compRef.getX() == 75);
        cs.undo();
        assertTrue(compRef.getX() == compRefOldXCoordinate);
        assertTrue(compRef2.getX() == compRef2OldXCoordinate);
        
        // test align right with components
      	cmd5 = new AlignCommand(components, 3, "seg.jUCMNav.AlignRight");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(compRef.getX() == compRef2.getX() && compRef.getX() == compRef2OldXCoordinate);
        cs.undo();
        assertTrue(compRef.getX() == compRefOldXCoordinate);
        assertTrue(compRef2.getX() == compRef2OldXCoordinate);
       
    	}
    
    	
    
    /**
     * 
     *  
     */
    public void testAddComponentRefCommand() {

        // This command should not be called directly by anything else than testSetConstraintComponentRefCommand.

        Command cmd = new AddContainerRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        testBindings = false;
    }

    /**
     * 
     *  
     */
    public void testAddForkOnConnectionCommand() {
        testExtendPathCommand();
        Command cmd;
        fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
        cmd = new DividePathCommand(fork, (NodeConnection) map.getConnections().get(0), 150, 39);
        // cmd = new AddForkOrJoinCompoundCommand(fork, map, (NodeConnection) map.getConnections().get(0), 150, 39);
        assertTrue("Can't execute DividePathCommand with orfork.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
        cmd = new DividePathCommand(fork, (NodeConnection) map.getConnections().get(0), 30, 457);
        // cmd = new AddForkOrJoinCompoundCommand(fork, map, (NodeConnection) map.getConnections().get(2), 30, 457);
        assertTrue("Can't execute DividePathCommand with andfork.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testAddForkOnEmptyPointCommand() {
        testExtendPathCommand();
        EmptyPoint pt;
        int i = 0;
        // find empty point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < map.getNodes().size()); //$NON-NLS-1$

        Command cmd;
        fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
        cmd = new DividePathCommand(fork, (EmptyPoint) map.getNodes().get(i));
        // cmd = new AddForkOrJoinCompoundCommand(fork, map, (EmptyPoint) map.getNodes().get(i));
        assertTrue("Can't execute DividePathCommand with orfork.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // find another empty point.
        i = 0;
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < map.getNodes().size()); //$NON-NLS-1$

        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
        cmd = new DividePathCommand(fork, (EmptyPoint) map.getNodes().get(i));
        // cmd = new AddForkOrJoinCompoundCommand(fork, map, (EmptyPoint) map.getNodes().get(i));
        assertTrue("Can't execute AddForkOnEmptyPointCommand with andfork.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddStubCommand() {
        testExtendPathCommand();

        stub = (Stub) ModelCreationFactory.getNewObject(urnspec, Stub.class);

        Command cmd;

        NodeConnection nc = (NodeConnection) ((PathNode) ((NodeConnection) end.getPred().get(0)).getSource()).getPred().get(0);
        cmd = new SplitLinkCommand(map, stub, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddInBindingCommand() {
        testAddPluginCommand();

        Command cmd;

        NodeConnection entry = (NodeConnection) stub.getPred().get(0);

        cmd = new AddInBindingCommand(plugin, start, entry);
        assertTrue("Can't execute AddInBindingCommand with Plugin, StartPoint and entry NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testAddJoinOnConnectionCommand() {
        testExtendPathCommand();
        Command cmd;
        PathNode join = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);
        cmd = new DividePathCommand(join, (NodeConnection) map.getConnections().get(0), 150, 39);
        // cmd = new AddForkOrJoinCompoundCommand(join, map, (NodeConnection) map.getConnections().get(0), 150, 39);
        assertTrue("Can't execute DividePathCommand with orjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
        cmd = new DividePathCommand(join, (NodeConnection) map.getConnections().get(2), 30, 457);
        // cmd = new AddForkOrJoinCompoundCommand(join, map, (NodeConnection) map.getConnections().get(2), 30, 457);
        assertTrue("Can't execute DividePathCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testAddJoinOnEmptyPointCommand() {
        testExtendPathCommand();
        EmptyPoint pt;
        int i = 0;
        // find empty point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for AddJoinOnEmptyPointCommand!", i < map.getNodes().size()); //$NON-NLS-1$

        Command cmd;
        PathNode join = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);
        cmd = new DividePathCommand(join, (EmptyPoint) map.getNodes().get(i));
        // cmd = new AddForkOrJoinCompoundCommand(join, map, (EmptyPoint) map.getNodes().get(i));
        assertTrue("Can't execute DividePathCommand with orjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // find another empty point.
        i = 0;
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for AddJoinOnEmptyPointCommand!", i < map.getNodes().size()); //$NON-NLS-1$

        join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
        cmd = new DividePathCommand(join, (EmptyPoint) map.getNodes().get(i));
        // cmd = new AddForkOrJoinCompoundCommand(join, map, (EmptyPoint) map.getNodes().get(i));
        assertTrue("Can't execute DividePathCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddOutBindingCommand() {
        testAddPluginCommand();

        Command cmd;

        NodeConnection exit = (NodeConnection) stub.getSucc().get(0);

        cmd = new AddOutBindingCommand(plugin, end, exit);
        assertTrue("Can't execute AddOutBindingCommand with Plugin, EndPoint and exit NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testBug491_MergeEndOnSamePath() {

        testAddStubCommand();
        // Then create a new path in this map to test adding an In/Out Binding.
        testCreatePathCommand();

        Command cmd;
        cmd = new AttachBranchCommand(start, stub);
        assertTrue("Can't execute AttachBranchCommand with Stub and StartPoint.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        assertFalse("Should not be able to merge end point with its own path.", SafePathChecker.isSafeFusion(end, (NodeConnection) end.getPred().get(0))); //$NON-NLS-1$
        assertFalse("Should not be able to merge end point with its own path.", SafePathChecker.isSafeFusion(end, (NodeConnection) stub.getSucc().get(1))); //$NON-NLS-1$

    }

    public void testBug511_DisconnectSaveProblem() {

        testSetConstraintComponentRefCommand();
        testConnectCommand();

        Command cmd = new SetConstraintCommand(connect, 100, 100);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // disconnect
        cmd = new DisconnectCommand((PathNode) ((NodeConnection) connect.getSucc().get(0)).getTarget());
        assertTrue("Can't execute DisconnectCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // the bug occurs here. technically this file could not be reloaded when saved because the connect is still bound to the parent.
        assertTrue("Connect should not be bound to parent as it no longer exists.", connect.getContRef() == null); //$NON-NLS-1$

    }

    public void testAddPluginCommand() {
        // Add a Stub in the current Path
        testAddStubCommand();
        // Then add a new map to plugin to
        testCreateMapCommand();
        // Then create a new path in this map to test adding an In/Out Binding.
        testCreatePathCommand();

        Command cmd;
        cmd = new AddPluginCommand(stub, map);
        assertTrue("Can't execute AddPluginCommand with Stub and Map.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        plugin = (PluginBinding) stub.getBindings().get(0);
    }

    /**
     * 
     *  
     */
    public void testChangeLabelNameCommand() {
        testExtendPathCommand();
        StartPoint pt;
        int i = 0;
        // find empty point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof StartPoint)) {
            i++;
        }
        assertTrue("No start points exist for ChangeLabelNameCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pt = (StartPoint) map.getNodes().get(i);

        Command cmd = new ChangeLabelNameCommand(pt.getLabel(), "new start name"); //$NON-NLS-1$
        assertTrue("Can't execute ChangeLabelNameCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testComponentRefBindChildCommand() {

        // add a compref and position it.
        testSetConstraintComponentRefCommand();
        ComponentRef child = compRef;

        // add another one
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        testSetConstraintComponentRefCommand();

        Command cmd = new SetConstraintBoundContainerRefCompoundCommand(compRef, 0, 0, 1000, 1000);
        assertTrue("Can't execute SetConstraintContainerRefCommand in testComponentRefBindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        // the current compRef is now behind the original one.

        cmd = new ContainerRefBindChildCommand(compRef, child);
        assertTrue("Can't execute ContainerRefBindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testComponentRefUnbindChildCommand() {
        testComponentRefBindChildCommand();
        ComponentRef parent = compRef;
        ComponentRef child = (ComponentRef) compRef.getChildren().get(0);

        Command cmd = new ContainerRefUnbindChildCommand(parent, child);
        assertTrue("Can't execute ContainerRefUnbindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // we don't want to test the bindings as we have explicitely changed them.
        testBindings = false;

    }

    /**
     * 
     *  
     */
    public void testConnectCommand() {
        testCreatePathCommand();

        // add a second path
        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        Command cmd = new CreatePathCommand(map, newStart, 654, 17);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        EndPoint newEnd = (EndPoint) ((NodeConnection) ((NodeConnection) newStart.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget();

        cmd = new ConnectCommand(start, newEnd);
        assertTrue("Can't execute ConnectCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        connect = ((ConnectCommand) cmd).getConnect();
    }

    /**
     * 
     *  
     */
    public void testCreateLabelCommand() {
        testSetConstraintBoundComponentRefCompoundCommand();
        testCreatePathCommand();

        pathNodeWithLabel = end;

        CreateLabelCommand cmd = new CreateLabelCommand(pathNodeWithLabel);
        cmd.setDeltaX(50);
        cmd.setDeltaX(50);

        assertTrue("Can't execute CreateLabelCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        componentRefWithLabel = compRef;

        cmd = new CreateLabelCommand(componentRefWithLabel);
        cmd.setDeltaX(0);
        cmd.setDeltaX(0);

        assertTrue("Can't execute CreateLabelCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testCreateMapCommand() {

        CreateMapCommand cmd = new CreateMapCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        assertEquals("map was not added properly in model", urnspec.getUrndef().getSpecDiagrams().size(), 2); //$NON-NLS-1$
        assertEquals("map was not added properly in editor", editor.getPageCount(), 2); //$NON-NLS-1$

        // add bogus data to new map; set variables to help other commands access this new map
        map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(1);
        testSetConstraintCommand();
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        testExtendPathCommand();

    }

    /**
     * 
     *  
     */
    public void testCreatePathCommand() {
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);

        Command cmd = new CreatePathCommand(map, start, 35, 67);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // for future use
        end = (EndPoint) ((NodeConnection) ((NodeConnection) start.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget();

    }

    /**
     * 
     *  
     */
    public void testCutPathCommand() {
        testExtendPathCommand();

        EmptyPoint empty = (EmptyPoint) ((NodeConnection) ((NodeConnection) end.getPred().get(0)).getSource().getPred().get(0)).getSource();
        Command cmd = new CutPathCommand(map, empty);
        assertTrue("Can't execute CutPathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteComponentCommand() {
        testSetConstraintComponentRefCommand();

        Component compDef = (Component) compRef.getContDef();
        Command cmd = new DeleteComponentCommand(compDef);
        assertTrue("Can't execute DeleteComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteComponentRefCommand() {
        testSetConstraintComponentRefCommand();

        Command cmd = new DeleteComponentRefCommand(compRef);
        assertTrue("Can't execute DeleteComponentRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testDeleteInBindingCommand() {
        // FIXME Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", true); //$NON-NLS-1$
    }

    /**
     * 
     *  
     */
    public void testDeleteLabelCommand() {
        testCreateLabelCommand();

        DeleteLabelCommand cmd = new DeleteLabelCommand();
        cmd.setModelElement(pathNodeWithLabel);
        cmd.setLabel(((PathNode) pathNodeWithLabel).getLabel());

        assertTrue("Can't execute testDeleteLabelCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new DeleteLabelCommand();
        cmd.setModelElement(componentRefWithLabel);
        cmd.setLabel(((ComponentRef) componentRefWithLabel).getLabel());

        assertTrue("Can't execute testDeleteLabelCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteMapCommand() {
        testCreateMapCommand();

        DeleteMapCommand cmd = new DeleteMapCommand(map);
        assertTrue("Can't execute DeleteMapCommand.", cmd.canExecute()); //$NON-NLS-1$

        // must run as CompoundCommand because framework refreshes these
        CompoundCommand cmd2 = new CompoundCommand();
        cmd2.add(cmd);
        cs.execute(cmd2);
    }

    /**
     * 
     *  
     */
    public void testDeleteNodeCommand() {
        testSplitLinkCommand();

        PathNode pn;
        int i = 0;
        // find empty point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testDeleteNodeCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pn = (EmptyPoint) map.getNodes().get(i);

        DeletePathNodeCommand cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        assertTrue("Can't execute DeletePathNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        i = 0;
        // find start point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof StartPoint)) {
            i++;
        }
        assertTrue("No start points exist for testDeleteNodeCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pn = (StartPoint) map.getNodes().get(i);

        // cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        //        assertTrue("Should be able to delete start point DeletePathNodeCommand.", !cmd.canExecute()); //$NON-NLS-1$
        // cs.execute(cmd);

        i = 0;
        // find end point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EndPoint)) {
            i++;
        }
        assertTrue("No end points exist for testDeleteNodeCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pn = (EndPoint) map.getNodes().get(i);

        // cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        //        assertTrue("Should not be able to delete end point DeletePathNodeCommand.", !cmd.canExecute()); //$NON-NLS-1$
        // cs.execute(cmd);

        i = 0;
        // find respref .
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof RespRef)) {
            i++;
        }
        assertTrue("No RespRefs exist for testDeleteNodeCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pn = (RespRef) map.getNodes().get(i);

        cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        assertTrue("Can't execute DeletePathNodeCommand on RespRef.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteOutBindingCommand() {
        // FIXME Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", true); //$NON-NLS-1$
    }

    /**
     * 
     *  
     */
    public void testDeletePathCommand() {
        testAddBranchCommand();
        StartPoint start;
        EndPoint end;
        int i = 0;
        // find start point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof StartPoint)) {
            i++;
        }
        assertTrue("No start points exist for DeletePathCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        start = (StartPoint) map.getNodes().get(i);

        Command cmd = new DeletePathCommand(start, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        assertTrue("Can't execute DeletePathCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        i = 0;
        // find end point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EndPoint)) {
            i++;
        }
        assertTrue("No end points exist for DeletePathCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        end = (EndPoint) map.getNodes().get(i);

        cmd = new DeletePathCommand(end, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        assertTrue("Can't execute DeletePathCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteResponsibilityCommand() {
        testSplitLinkCommand();

        RespRef rr;
        int i = 0;
        // find respref .
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof RespRef)) {
            i++;
        }
        assertTrue("No RespRefs exist for testDeleteResponsibilityCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        rr = (RespRef) map.getNodes().get(i);
        Responsibility resp = rr.getRespDef();

        Command cmd = new DeleteResponsibilityCommand(resp);
        assertTrue("Can't execute DeleteResponsibilityCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testDeleteStartNCEndCommand() {
        testCreatePathCommand();

        Command cmd = new DeleteStartNCEndCommand(start);
        assertTrue("Can't execute DeleteStartNCEndCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testDisconnectCommand() {
        testConnectCommand();

        Command cmd = new DisconnectCommand(start);
        assertTrue("Can't execute DisconnectCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testDividePathOnNodeConnectionCompoundCommand() {
        testCreatePathCommand();

        // add a second path
        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        Command cmd = new CreatePathCommand(map, newStart, 654, 17);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new DividePathCommand(start, (NodeConnection) newStart.getSucc().get(0), 459, 148, true);
        assertTrue("Can't execute DividePathOnNodeConnectionCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testExtendPathCommand() {
        testCreatePathCommand();
        Command cmd;
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            // will have to remove randomness (by seeding) when we start serializing

            cmd = new ExtendPathCommand(map, end, r.nextInt(1000), r.nextInt(1000));
            assertTrue("Can't execute ExtendPathCommand with end.", cmd.canExecute()); //$NON-NLS-1$
            cs.execute(cmd);
        }

        // can extend the start point as well
        for (int i = 0; i < 5; i++) {
            // will have to remove randomness (by seeding) when we start serializing
            cmd = new ExtendPathCommand(map, start, r.nextInt(1000), r.nextInt(1000));
            assertTrue("Can't execute ExtendPathCommand with start.", cmd.canExecute()); //$NON-NLS-1$
            cs.execute(cmd);
        }
    }

    /**
     * 
     *  
     */
    public void testForkPathsCommand() {
        testCreatePathCommand();

        // add a second path
        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        Command cmd = new CreatePathCommand(map, newStart, 654, 17);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        EmptyPoint ep = null;
        // OrFork newFork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);

        // This is a hack - I'm not sure it's getting an EmptyPoint from the *correct* path!
        for (int i = 0; (i < map.getNodes().size()) && (ep == null); i++) {
            if (map.getNodes().get(i) instanceof EmptyPoint) {
                ep = (EmptyPoint) map.getNodes().get(i);
            }
        }
        assertTrue("Can't find an EmptyPoint on path", ep != null); //$NON-NLS-1$

        cmd = new DividePathCommand(newStart, ep, true);
        // cmd = new ForkPathsCommand(ep, newStart, newFork);
        //assertTrue("Couldn't create ForkPathsCommand", cmd != null); //$NON-NLS-1$
        assertTrue("DividePathCommand can't execute", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        fork = (OrFork) ((DividePathCommand) cmd).getNewNode();

        boolean isForkInPath = false;
        for (int i = 0; (i < map.getNodes().size()) && (isForkInPath == false); i++) {
            if (map.getNodes().get(i) == fork) {
                isForkInPath = true;
            }
        }

        assertTrue("Can't find new fork on path", isForkInPath); //$NON-NLS-1$

    }

    /**
     * 
     *  
     */
    public void testJoinPathsCommand() {
        testCreatePathCommand();

        // add a second path
        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        Command cmd = new CreatePathCommand(map, newStart, 654, 17);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        EndPoint newEnd = ((CreatePathCommand) cmd).getEnd();

        EmptyPoint ep = null;
        OrJoin newJoin = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);

        // This is a hack - I'm not sure it's getting an EmptyPoint from the *correct* path!
        for (int i = 0; (i < map.getNodes().size()) && (ep == null); i++) {
            if (map.getNodes().get(i) instanceof EmptyPoint) {
                ep = (EmptyPoint) map.getNodes().get(i);
            }
        }
        assertTrue("Can't find an EmptyPoint on 2nd path", ep != null); //$NON-NLS-1$

        cmd = new DividePathCommand(newJoin, ep, newEnd);
        // cmd = new JoinPathsCommand(ep, end, newJoin);
        //assertTrue("Couldn't create JoinPathsCommand", cmd != null); //$NON-NLS-1$
        assertTrue("DividePathCommand can't execute", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testLabelSetConstraintCommand() {
        testCreateLabelCommand();

        LabelSetConstraintCommand cmd = new LabelSetConstraintCommand();
        cmd.setLabel(((PathNode) pathNodeWithLabel).getLabel());
        cmd.setNewPosition(75, 75);

        assertTrue("Can't execute LabelSetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new LabelSetConstraintCommand();
        cmd.setLabel(((ComponentRef) componentRefWithLabel).getLabel());
        cmd.setNewPosition(25, 25);

        assertTrue("Can't execute LabelSetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testMergeStartEndCommand() {
        testCreatePathCommand();

        // add a second path
        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        Command cmd = new CreatePathCommand(map, newStart, 654, 17);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        EndPoint newEnd = (EndPoint) ((NodeConnection) ((NodeConnection) newStart.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget();

        cmd = new MergeStartEndCommand(map, newStart, end, 100, 100);
        assertTrue("Can't execute MergeStartEndCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new MergeStartEndCommand(map, start, newEnd, 100, 100);
        assertTrue("Should not be able to execute MergeStartEndCommand; will cause circular path.", !cmd.canExecute()); //$NON-NLS-1$
        // cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testReplaceEmptyPointCommand() {
        testCreatePathCommand();
        wait = (WaitingPlace) ModelCreationFactory.getNewObject(urnspec, WaitingPlace.class);
        EmptyPoint empty = (EmptyPoint) ((NodeConnection) start.getSucc().get(0)).getTarget();
        Command cmd = new ReplaceEmptyPointCommand(empty, wait);
        assertTrue("Can't execute ReplaceEmptyPointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testReplaceEmptyPointCommand2() {
        testCreatePathCommand();
        wait = (Timer) ModelCreationFactory.getNewObject(urnspec, Timer.class);
        EmptyPoint empty = (EmptyPoint) ((NodeConnection) start.getSucc().get(0)).getTarget();
        Command cmd = new ReplaceEmptyPointCommand(empty, wait);
        assertTrue("Can't execute ReplaceEmptyPointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new AddBranchCommand(wait);
        assertTrue("Can't execute AddBranchCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testReplacePluginCommand() {
        // FIXME Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", true); //$NON-NLS-1$
    }

    /**
     * 
     *  
     */
    public void testSetConstraintBoundComponentRefCompoundCommand() {
        testComponentRefBindChildCommand();

        ComponentRef parent = compRef;
        Command cmd = new SetConstraintBoundContainerRefCompoundCommand(parent, 150, 300, 453, 148);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testSetConstraintCommand() {
        testCreatePathCommand();
        Command cmd = new SetConstraintCommand(end, 96, 36);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testSetConstraintComponentRefCommand() {

        testAddComponentRefCommand();
        Command cmd = new SetConstraintContainerRefCommand(compRef, 100, 200, 300, 400);
        assertTrue("Can't execute SetConstraintContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintContainerRefCommand(compRef, 69, 69, 69, 69);
        assertTrue("Can't execute SetConstraintContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        testBindings = true;
    }

    /**
     * 
     *  
     */
    public void testSplitLinkCommand() {
        // testCutPathCommand();
        testExtendPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        this.resp = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        Command cmd = new SplitLinkCommand(map, this.resp, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testTransmogrifyForkOrJoinCommand() {
        testAddForkOnEmptyPointCommand();

        assertTrue("Initial AndFork not created!", fork instanceof AndFork); //$NON-NLS-1$

        Command cmd = new TransmogrifyForkOrJoinCommand(fork, map);
        assertTrue("Transmogrify can't execute!", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Find the first fork in the map (hack)
        PathNode newFork = null;
        for (int i = 0; (i < map.getNodes().size()) && (newFork == null); i++) {
            if ((map.getNodes().get(i) instanceof OrFork) || (map.getNodes().get(i) instanceof AndFork))
                newFork = (PathNode) map.getNodes().get(i);
        }

        assertTrue("Can't locate a fork in the map", newFork != null); //$NON-NLS-1$
        assertTrue("Transmogrification of Fork failed!", newFork instanceof OrFork); //$NON-NLS-1$
    }

    /**
     * Test the CutAnyPathCommand command on a NodeConnection.
     * 
     * Should begin like this:
     * 
     * EmptyPoint1---------EmptyPoint2
     * 
     * Should finish like this:
     * 
     * ------EndPoint StartPoint------
     */
    public void testCutAnyPathCommandNodeConnection() {
        testExtendPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);

        Command cmd = new CutAnyPathCommand(map, nc, 85, 56);
        assertTrue("Can't execute CutAnyPathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        assertTrue("The old node connection target should now be an EndPoint", nc.getTarget() instanceof EndPoint); //$NON-NLS-1$
        assertTrue("The EndPoint precedent node should now be a StartPoint", ((NodeConnection) end.getPred().get(0)).getSource() instanceof StartPoint); //$NON-NLS-1$

        testExtendPathCommand();
    }

    /**
     * Test the CutAnyPathCommand command on an EmptyPoint.
     * 
     * Should begin like this:
     * 
     * ---------EmptyPoint1---------
     * 
     * Should finish like this:
     * 
     * ------EndPoint StartPoint------
     */
    public void testCutAnyPathCommandEmptyPoint() {
        testExtendPathCommand();

        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        EmptyPoint ep = (EmptyPoint) nc.getSource();
        CutAnyPathCommand cmd = new CutAnyPathCommand(map, ep, 85, 86);

        assertTrue("Can't execute CutAnyPathCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        assertTrue("The node connection target should now be an EndPoint", nc.getTarget() instanceof EndPoint); //$NON-NLS-1$
        assertTrue("The EndPoint precedent node should now be a StartPoint", ((NodeConnection) end.getPred().get(0)).getSource() instanceof StartPoint); //$NON-NLS-1$
    }

    /**
     * This method will go through all of the path nodes and component ref in all the maps and verify that they are all bound as they should be. will be usefull
     * to see if commands that create new elements bind them to their parents.
     */
    public void verifyBindings() {
        for (Iterator iter = urnspec.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;

                for (Iterator iter2 = map.getContRefs().iterator(); iter2.hasNext();) {
                    ComponentRef comp = (ComponentRef) iter2.next();
                    assertEquals("Component " + comp.toString() + " is not properly bound.", ParentFinder.getPossibleParent(comp), comp.getParent()); //$NON-NLS-1$ //$NON-NLS-2$

                }
                for (Iterator iter2 = map.getNodes().iterator(); iter2.hasNext();) {
                    PathNode pn = (PathNode) iter2.next();
                    if (!(pn instanceof Connect))
                        assertEquals("PathNode " + pn.toString() + " is not properly bound.", ParentFinder.getPossibleParent(pn), pn.getContRef()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
    }

    /**
     * 
     *  
     */
    public void testChangeHyperlinkCommand() {
        testCreatePathCommand();

        String url = "http://www.google.com"; //$NON-NLS-1$
        String url2 = "http://www.UseCaseMaps.org"; //$NON-NLS-1$
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK) == null);

        // Add URL
        Command cmd = new ChangeHyperlinkCommand(urnspec, start, url);
        assertTrue("Can't execute ChangeHyperlinkCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url));
        cs.undo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK) == null);
        cs.redo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url));

        // Modify URL
        cmd = new ChangeHyperlinkCommand(urnspec, start, url2);
        assertTrue("Can't execute ChangeHyperlinkCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url2));
        cs.undo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url));
        cs.redo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url2));

        // Delete URL
        cmd = new ChangeHyperlinkCommand(urnspec, start, null);
        assertTrue("Can't execute ChangeHyperlinkCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK) == null);
        cs.undo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK).equals(url2));
        cs.redo();
        assertTrue(MetadataHelper.getMetaData(start, HyperlinkUtils.HYPERLINK) == null);

    }
    
    public void testRefactorIntoStubCommand() {
        testSplitLinkCommand();
        Vector v =new Vector();
        v.add(this.resp);
        
        Responsibility def = this.resp.getRespDef();
        Command cmd = new RefactorIntoStubCommand(urnspec, v);
        assertTrue("Can't execute RefactorIntoStubCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        assertEquals("Should now contain two maps.", urnspec.getUrndef().getSpecDiagrams().size(), 2);//$NON-NLS-1$
        
        assertTrue("RespRef should have been moved.", this.resp.getDiagram()==null); //$NON-NLS-1$
        boolean found=false;
        for (Iterator iterator =  ((UCMmap)urnspec.getUrndef().getSpecDiagrams().get(1)).getNodes().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            if (pn instanceof RespRef)
            {
                RespRef ref = (RespRef) pn;
                if (ref.getRespDef()==def)
                    found=true;
            }
        }
        assertTrue("RespRef should be in new diagram.",found); //$NON-NLS-1$

    }

    public void testCopyPasteCommand() {
        testSplitLinkCommand();
        
        Vector v =new Vector();
        v.add(((UrnEditor)editor.getActiveEditor()).getGraphicalViewer().getEditPartRegistry().get(this.resp));

        Command cmd = new CopyCommand(urnspec, v, null);
        assertTrue("Can't execute CopyCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new PasteCommand(map, urnspec, map, new Point(50,50), new Point(50,50));
        assertTrue("Can't execute PasteCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new PasteCommand((NodeConnection)map.getConnections().get(0), urnspec, map, new Point(50,50), new Point(50,50));
        assertTrue("Can't execute PasteCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        int count=0;
        for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            if (pn instanceof RespRef)
            {
                RespRef ref = (RespRef) pn;
                if (ref.getRespDef()  == this.resp.getRespDef())
                    count++;
            }
        }
        assertEquals("Should have found three copies of RespRef.", 3, count);  //$NON-NLS-1$
    }
    
    public void testMoveScenarioCommand()
    {
        ScenarioGroup group1 = (ScenarioGroup) ModelCreationFactory.getNewObject(urnspec, ScenarioGroup.class);       
        CreateScenarioGroupCommand cmd = new CreateScenarioGroupCommand(urnspec, group1);
        cs.execute(cmd);

        ScenarioGroup group2 = (ScenarioGroup) ModelCreationFactory.getNewObject(urnspec, ScenarioGroup.class);       
        cmd = new CreateScenarioGroupCommand(urnspec, group2);
        cs.execute(cmd);

        assertTrue("New groups are empty", group1.getScenarios().size()==0 && group2.getScenarios().size()==0); //$NON-NLS-1$
        
        CreateScenarioCommand cmd2 = new CreateScenarioCommand(urnspec, group1);
        cs.execute(cmd2);
        
        
        assertNotNull("Scenario should have been created.", cmd2.getScenario()); //$NON-NLS-1$
        assertEquals("New group should have one scenario", 1, group1.getScenarios().size()); //$NON-NLS-1$
        
        // move to 2nd group. 
        MoveScenarioCommand cmd3 = new MoveScenarioCommand(group2, cmd2.getScenario());
        cs.execute(cmd3);
        assertTrue("Scenario should have been moved", group1.getScenarios().size()==0 && group2.getScenarios().size()==1); //$NON-NLS-1$
        
        
        
    }
    
    /**
     * Test for DistributeCommand.
     *  
     *  @author Patrice Boulet
     */
    public void testDistributeCommand(){
    	testSetConstraintCommand();
    	testSetConstraintComponentRefCommand();
    	testSetConstraintBoundComponentRefCompoundCommand();
    	
    	CreateMapCommand cmd1 = new CreateMapCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd1.canExecute()); //$NON-NLS-1$
        cs.execute(cmd1);
        
        assertEquals("map was not added properly in model", urnspec.getUrndef().getSpecDiagrams().size(), 2); //$NON-NLS-1$
        assertEquals("map was not added properly in editor", editor.getPageCount(), 2); //$NON-NLS-1$
        
        // add bogus data to new map
        map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(1);
        
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        compRef.setDiagram(map);
        compRef.setWidth(100);
        compRef.setHeight(100);
        compRef.setX(50);
        compRef.setY(50);
        int compRefOldXCoordinate = compRef.getX();
        int compRefOldYCoordinate = compRef.getY();
        
        compRef2 = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        compRef2.setDiagram(map);
        compRef2.setWidth(100);
        compRef2.setHeight(100);
        compRef2.setX(100);
        compRef2.setY(200);
        int compRef2OldXCoordinate = compRef2.getX();
        int compRef2OldYCoordinate = compRef2.getY();
        
        ComponentRef compRef3 = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        compRef3.setDiagram(map);
        compRef3.setWidth(100);
        compRef3.setHeight(100);
        compRef3.setX(60);
        compRef3.setY(60);
        int compRef3OldXCoordinate = compRef3.getX();
        int compRef3OldYCoordinate = compRef3.getY();
        
        AddContainerRefCommand cmd2 = new AddContainerRefCommand(map, compRef);
        assertTrue("Can't execute AddContainerRefCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        
        AddContainerRefCommand cmd3 = new AddContainerRefCommand(map, compRef2);
        assertTrue("Can't execute AddContainerRefCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        
        AddContainerRefCommand cmd7 = new AddContainerRefCommand(map, compRef3);
        assertTrue("Can't execute AddContainerRefCommand.", cmd7.canExecute()); //$NON-NLS-1$
        cs.execute(cmd7);
        
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        start.setDiagram(map);
        start.setX(100);
        start.setY(100);
        compRef.getNodes().add(start);
        int startOldXCoordinate = start.getX();
        int startOldYCoordinate = start.getY();
        
        resp = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        resp.setDiagram(map);
        resp.setX(150);
        resp.setY(250);
        compRef2.getNodes().add(resp);
        int respOldXCoordinate = resp.getX();
        int respOldYCoordinate = resp.getY();      
        
        RespRef resp2 = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        resp2.setDiagram(map);
        resp2.setX(110);
        resp2.setY(140);
        compRef.getNodes().add(resp2);
        int resp2OldXCoordinate = resp2.getX();
        int resp2OldYCoordinate = resp2.getY();     
        
        LinkedList nodes = new LinkedList<PathNode>();
        nodes.add(start);
        nodes.add(resp);
        nodes.add(resp2);
        
        LinkedList components = new LinkedList<ComponentRef>();
        components.add(compRef);
        components.add(compRef2);
        components.add(compRef3);
        
        // test distribute centers horizontally with nodes
      	DistributeCommand cmd4 = new DistributeCommand(nodes, 4, "seg.jUCMNav.DistributeCentersHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        assertTrue(resp2.getX() == startOldXCoordinate + (respOldXCoordinate - startOldXCoordinate)/2 );
        
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp2.getX() == resp2OldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        
        // test distribute horizontally with nodes
      	DistributeCommand cmd9 = new DistributeCommand(nodes, 4, "seg.jUCMNav.DistributeHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd9.canExecute()); //$NON-NLS-1$
        cs.execute(cmd9);

        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);
        assertTrue(resp2.getX() == startOldXCoordinate + (respOldXCoordinate - startOldXCoordinate)/2 );
        
        cs.undo();
        assertTrue(start.getX() == startOldXCoordinate);
        assertTrue(resp2.getX() == resp2OldXCoordinate);
        assertTrue(resp.getX() == respOldXCoordinate);

        // test distribute centers vertically with nodes
      	DistributeCommand cmd5 = new DistributeCommand(nodes, 4, "seg.jUCMNav.DistributeCentersVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        assertTrue(resp2.getY() == startOldYCoordinate + (respOldYCoordinate - startOldYCoordinate)/2 );
        
        cs.undo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp2.getY() == resp2OldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        
        // test distribute vertically with nodes
      	DistributeCommand cmd10 = new DistributeCommand(nodes, 4, "seg.jUCMNav.DistributeVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd10.canExecute()); //$NON-NLS-1$
        cs.execute(cmd10);

        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        assertTrue(resp2.getY() == startOldYCoordinate + (respOldYCoordinate - startOldYCoordinate)/2 );
        
        cs.undo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp2.getY() == resp2OldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        
        // testing redo
        cs.redo();
        assertTrue(start.getY() == startOldYCoordinate);
        assertTrue(resp.getY() == respOldYCoordinate);
        assertTrue(resp2.getY() == startOldYCoordinate + (respOldYCoordinate - startOldYCoordinate)/2 );
        cs.undo();
        
        // test distribute centers horizontally with components
      	DistributeCommand cmd6 = new DistributeCommand(components, 3, "seg.jUCMNav.DistributeCentersHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd6.canExecute()); //$NON-NLS-1$
        cs.execute(cmd6);
         
        assertTrue(compRef3.getX() == 
        	( (compRef.getX()+compRef.getWidth()/2) + ((compRef2.getX()+compRef2.getWidth()/2) - 
        			(compRef.getX()+compRef.getWidth()/2))/2 - compRef3.getWidth()/2));
        cs.undo();
        assertTrue(compRef.getX() == compRefOldXCoordinate);
        assertTrue(compRef2.getX() == compRef2OldXCoordinate);
        assertTrue(compRef3.getX() == compRef3OldXCoordinate);
        
        // test distribute horizontally with components
      	DistributeCommand cmd11 = new DistributeCommand(components, 3, "seg.jUCMNav.DistributeHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd11.canExecute()); //$NON-NLS-1$
        cs.execute(cmd11);
        
        assertTrue(compRef3.getX() == (compRefOldXCoordinate + compRef.getWidth() + 25));
        assertTrue(compRef2.getX() == (compRefOldXCoordinate + compRef.getWidth() + 50 + compRef3.getWidth()));
        
        cs.undo();
        assertTrue(compRef.getX() == compRefOldXCoordinate);
        assertTrue(compRef2.getX() == compRef2OldXCoordinate);
        assertTrue(compRef3.getX() == compRef3OldXCoordinate);
        
        // test distribute centers vertically with components
      	DistributeCommand cmd8 = new DistributeCommand(components, 3, "seg.jUCMNav.DistributeCentersVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd8.canExecute()); //$NON-NLS-1$
        cs.execute(cmd8);
         
        assertTrue(compRef3.getY() == 
        	( (compRef.getY()+compRef.getHeight()/2) + ((compRef2.getY()+compRef2.getHeight()/2) - 
        			(compRef.getY()+compRef.getHeight()/2))/2 - compRef3.getHeight()/2));
        cs.undo();
        assertTrue(compRef.getY() == compRefOldYCoordinate);
        assertTrue(compRef2.getY() == compRef2OldYCoordinate);
        assertTrue(compRef3.getY() == compRef3OldYCoordinate);
        
        // test distribute vertically with components
      	DistributeCommand cmd12 = new DistributeCommand(components, 3, "seg.jUCMNav.DistributeVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd12.canExecute()); //$NON-NLS-1$
        cs.execute(cmd12);
         
        assertTrue(compRef3.getY() == (compRefOldYCoordinate + compRef.getHeight() + 25));
        assertTrue(compRef2.getY() == (compRefOldYCoordinate + compRef.getHeight() + 50 + compRef3.getHeight()));
        
        cs.undo();
        assertTrue(compRef.getY() == compRefOldYCoordinate);
        assertTrue(compRef2.getY() == compRef2OldYCoordinate);
        assertTrue(compRef3.getY() == compRef3OldYCoordinate);
        
    	}
    
}