package seg.jUCMNav.tests.commands;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Random;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentElementCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteLabelCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteStartNCEndCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.ComponentElement;
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
    private UCMmodelElement componentRefWithLabel;
    private ComponentRef compRef;
    private CommandStack cs;
    private UCMNavMultiPageEditor editor;
    private EndPoint end;
    private Connect connect;
    private UCMmap map;
    private UCMmodelElement pathNodeWithLabel;
    private StartPoint start;
    
    private Stub stub;
    private PluginBinding plugin;

    // during teardown, if testBindings==true, call verifyBindings()
    private boolean testBindings;
    private IFile testfile;

    private URNspec urnspec;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

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
        //        urnspec = (URNspec) ModelCreationFactory.getNewURNspec();

        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(0);

        //cs = new CommandStack();
        cs = editor.getDelegatingCommandStack();

        ComponentRef backgroundBindingChecker = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        Command cmd = new AddContainerRefCommand(map, backgroundBindingChecker);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(backgroundBindingChecker, -1000, -1000, 5000, 5000);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        testBindings = true;
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
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

        editor.closeEditor(false);
    }

//    /**
//     * 
//     *  
//     */
//    public void testAddBranchCommand() {
//        testAddForkOnConnectionCommand();
//        AndFork fork;
//        int i = 0;
//        // find empty point.
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof AndFork)) {
//            i++;
//        }
//        assertTrue("No and forks exist for AddBranchCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//        fork = (AndFork) pathgraph.getPathNodes().get(i);
//
//        Command cmd = new AddBranchCommand(fork);
//        assertTrue("Can't execute AddBranchCommand", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        // test adding a timeout path.
//        Timer timer = (Timer) ModelCreationFactory.getNewObject(urnspec, Timer.class);
//        cmd = new SplitLinkCommand(pathgraph, timer, (NodeConnection) pathgraph.getNodeConnections().get(0), 149, 875);
//        assertTrue("Can't execute SplitLinkCommand", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        cmd = new AddBranchCommand(timer);
//        assertTrue("Can't execute AddBranchCommand", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//    }

    /**
     * 
     *  
     */
    public void testAddComponentRefCommand() {

        // This command should not be called directly by anything else that testSetConstraintComponentRefCommand.

        Command cmd = new AddContainerRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        testBindings = false;
    }

//    /**
//     * 
//     *  
//     */
//    public void testAddForkOnConnectionCommand() {
//        testExtendPathCommand();
//        Command cmd;
//        fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
//        cmd = new AddForkOrJoinCompoundCommand(fork, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(0), 150, 39);
//        assertTrue("Can't execute AddForkOnConnectionCommand with orfork.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
//        cmd = new AddForkOrJoinCompoundCommand(fork, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(2), 30, 457);
//        assertTrue("Can't execute AddForkOnConnectionCommand with andfork.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//    }
//
//    /**
//     * 
//     *  
//     */
//    public void testAddForkOnEmptyPointCommand() {
//        testExtendPathCommand();
//        EmptyPoint pt;
//        int i = 0;
//        // find empty point.
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
//            i++;
//        }
//        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//
//        Command cmd;
//        fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
//        cmd = new AddForkOrJoinCompoundCommand(fork, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
//        assertTrue("Can't execute AddForkOnEmptyPointCommand with orfork.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        // find another empty point.
//        i = 0;
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
//            i++;
//        }
//        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//
//        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
//        cmd = new AddForkOrJoinCompoundCommand(fork, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
//        assertTrue("Can't execute AddForkOnEmptyPointCommand with andfork.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//    }
    
    public void testAddStubCommand() {
    	testExtendPathCommand();
    	
    	stub = (Stub) ModelCreationFactory.getNewObject(urnspec, Stub.class);
    	
    	Command cmd;
    	
    	NodeConnection nc = (NodeConnection)((PathNode)((NodeConnection) end.getPred().get(0)).getSource()).getPred().get(0);
        cmd = new SplitLinkCommand(map, stub, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddInBindingCommand() {
    	testAddPluginCommand();
    	
    	Command cmd;
    	
    	NodeConnection entry = (NodeConnection)stub.getPred().get(0);
    	
    	cmd = new AddInBindingCommand(plugin, start, entry);
    	assertTrue("Can't execute AddInBindingCommand with Plugin, StartPoint and entry NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
    	cs.execute(cmd);
    	
    }

//    /**
//     * 
//     *  
//     */
//    public void testAddJoinOnConnectionCommand() {
//        testExtendPathCommand();
//        Command cmd;
//        PathNode join = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);
//        cmd = new AddForkOrJoinCompoundCommand(join, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(0), 150, 39);
//        assertTrue("Can't execute AddJoinOnConnectionCommand with orjoin.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//        join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
//        cmd = new AddForkOrJoinCompoundCommand(join, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(2), 30, 457);
//        assertTrue("Can't execute AddJoinOnConnectionCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//    }

//    /**
//     * 
//     *  
//     */
//    public void testAddJoinOnEmptyPointCommand() {
//        testExtendPathCommand();
//        EmptyPoint pt;
//        int i = 0;
//        // find empty point.
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
//            i++;
//        }
//        assertTrue("No empty points exist for AddJoinOnEmptyPointCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//
//        Command cmd;
//        PathNode join = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);
//        cmd = new AddForkOrJoinCompoundCommand(join, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
//        assertTrue("Can't execute AddJoinOnEmptyPointCommand with orjoin.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        // find another empty point.
//        i = 0;
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
//            i++;
//        }
//        assertTrue("No empty points exist for AddJoinOnEmptyPointCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//
//        join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
//        cmd = new AddForkOrJoinCompoundCommand(join, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
//        assertTrue("Can't execute AddJoinOnEmptyPointCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//    }

    public void testAddOutBindingCommand() {
    	testAddPluginCommand();
    	
    	Command cmd;
    	
    	NodeConnection exit = (NodeConnection)stub.getSucc().get(0);
    	
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
    	
    
    	
    	assertFalse("Should not be able to merge end point with its own path.", SafePathChecker.isSafeFusion(end, (NodeConnection)end.getPred().get(0))); //$NON-NLS-1$
    	assertFalse("Should not be able to merge end point with its own path.", SafePathChecker.isSafeFusion(end, (NodeConnection)stub.getSucc().get(1))); //$NON-NLS-1$

    	
    }
    
    public void testBug511_DisconnectSaveProblem() {
    	
    	testSetConstraintComponentRefCommand();
    	testConnectCommand();
    	
        Command cmd = new SetConstraintCommand(connect, 100, 100);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        // disconnect
        cmd = new DisconnectCommand((PathNode)((NodeConnection)connect.getSucc().get(0)).getTarget());
        assertTrue("Can't execute DisconnectCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    	
        // the bug occurs here. technically this file could not be reloaded when saved because the connect is still bound to the parent. 
        assertTrue("Connect should not be bound to parent as it no longer exists.",connect.getContRef()==null );
    	
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
    	
    	plugin = (PluginBinding)stub.getBindings().get(0);
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

        connect = ((ConnectCommand)cmd).getConnect();
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
    public void testDeleteComponentElementCommand() {
        testSetConstraintComponentRefCommand();

        Command cmd = new DeleteComponentElementCommand((ComponentElement)compRef.getContDef());
        assertTrue("Should not be able to execute DeleteComponentElementCommand.", !cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ComponentElement compDef = (ComponentElement)compRef.getContDef();
        cmd = new DeleteComponentRefCommand(compRef);
        assertTrue("Can't execute DeleteComponentRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new DeleteComponentElementCommand(compDef);
        assertTrue("Can't execute DeleteComponentElementCommand.", cmd.canExecute()); //$NON-NLS-1$
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
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", false); //$NON-NLS-1$
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

//        cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
//        assertTrue("Should be able to delete start point DeletePathNodeCommand.", !cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);

        i = 0;
        // find end point.
        while (i < map.getNodes().size() && !(map.getNodes().get(i) instanceof EndPoint)) {
            i++;
        }
        assertTrue("No end points exist for testDeleteNodeCommand!", i < map.getNodes().size()); //$NON-NLS-1$
        pn = (EndPoint) map.getNodes().get(i);

//        cmd = new DeletePathNodeCommand(pn, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
//        assertTrue("Should not be able to delete end point DeletePathNodeCommand.", !cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);

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
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", false); //$NON-NLS-1$
    }

//    /**
//     * 
//     *  
//     */
//    public void testDeletePathCommand() {
//        testAddBranchCommand();
//        StartPoint start;
//        EndPoint end;
//        int i = 0;
//        // find start point.
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof StartPoint)) {
//            i++;
//        }
//        assertTrue("No start points exist for DeletePathCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//        start = (StartPoint) pathgraph.getPathNodes().get(i);
//
//        Command cmd = new DeletePathCommand(start, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
//        assertTrue("Can't execute DeletePathCommand", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        i = 0;
//        // find end point.
//        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EndPoint)) {
//            i++;
//        }
//        assertTrue("No end points exist for DeletePathCommand!", i < pathgraph.getPathNodes().size()); //$NON-NLS-1$
//        end = (EndPoint) pathgraph.getPathNodes().get(i);
//
//        cmd = new DeletePathCommand(start, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
//        assertTrue("Can't execute DeletePathCommand", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//    }

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
        assertTrue("Must not be able to delete referenced responsibility.", !cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        
        cmd = new DeletePathNodeCommand(rr, editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry());
        assertTrue("Can't delete RespRef", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new DeleteResponsibilityCommand(resp);
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
            cmd = new ExtendPathCommand(map, start,  r.nextInt(1000),  r.nextInt(1000));
            assertTrue("Can't execute ExtendPathCommand with start.", cmd.canExecute()); //$NON-NLS-1$
            cs.execute(cmd);
        }
    }

//    /**
//     * 
//     *  
//     */
//    public void testForkPathsCommand() {
//        testCreatePathCommand();
//
//        // add a second path
//        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
//        Command cmd = new CreatePathCommand(pathgraph, newStart, 654, 17);
//        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        EmptyPoint ep = null;
//        OrFork newFork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
//
//        // This is a hack - I'm not sure it's getting an EmptyPoint from the *correct* path!
//        for (int i = 0; (i < pathgraph.getPathNodes().size()) && (ep == null); i++) {
//            if (pathgraph.getPathNodes().get(i) instanceof EmptyPoint) {
//                ep = (EmptyPoint) pathgraph.getPathNodes().get(i);
//            }
//        }
//        assertTrue("Can't find an EmptyPoint on path", ep != null); //$NON-NLS-1$
//
//        cmd = new ForkPathsCommand(ep, newStart, newFork);
//        assertTrue("Couldn't create ForkPathsCommand", cmd != null); //$NON-NLS-1$
//        assertTrue("ForkPathsCommand can't execute", cmd.canExecute()); //$NON-NLS-1$
//
//        cs.execute(cmd);
//
//        boolean isForkInPath = false;
//        for (int i = 0; (i < pathgraph.getPathNodes().size()) && (isForkInPath == false); i++) {
//            if (pathgraph.getPathNodes().get(i) == newFork) {
//                isForkInPath = true;
//            }
//        }
//
//        assertTrue("Can't find new fork on path", isForkInPath); //$NON-NLS-1$
//
//    }

//    /**
//     * 
//     *  
//     */
//    public void testJoinPathsCommand() {
//        testCreatePathCommand();
//
//        // add a second path
//        StartPoint newStart = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
//        Command cmd = new CreatePathCommand(pathgraph, newStart, 654, 17);
//        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        EmptyPoint ep = null;
//        OrJoin newJoin = (OrJoin) ModelCreationFactory.getNewObject(urnspec, OrJoin.class);
//
//        // This is a hack - I'm not sure it's getting an EmptyPoint from the *correct* path!
//        for (int i = 0; (i < pathgraph.getPathNodes().size()) && (ep == null); i++) {
//            if (pathgraph.getPathNodes().get(i) instanceof EmptyPoint) {
//                ep = (EmptyPoint) pathgraph.getPathNodes().get(i);
//            }
//        }
//        assertTrue("Can't find an EmptyPoint on 2nd path", ep != null); //$NON-NLS-1$
//
//        cmd = new JoinPathsCommand(ep, end, newJoin);
//        assertTrue("Couldn't create JoinPathsCommand", cmd != null); //$NON-NLS-1$
//        assertTrue("JoinPathsCommand can't execute", cmd.canExecute()); //$NON-NLS-1$
//
//        cs.execute(cmd);
//    }


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
        //        cs.execute(cmd);

    }

    /**
     * 
     *  
     */
    public void testReplaceEmptyPointCommand() {
        testCreatePathCommand();
        WaitingPlace wait = (WaitingPlace) ModelCreationFactory.getNewObject(urnspec, WaitingPlace.class);
        EmptyPoint empty = (EmptyPoint) ((NodeConnection) start.getSucc().get(0)).getTarget();
        Command cmd = new ReplaceEmptyPointCommand(empty, wait);
        assertTrue("Can't execute ReplaceEmptyPointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * 
     *  
     */
    public void testReplacePluginCommand() {
        assertTrue("Deletion tests need to be redone using http://cserg0.site.uottawa.ca/twiki/bin/view/ProjetSEG/DevDocDeletionTests", false); //$NON-NLS-1$
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
        testCutPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        Command cmd = new SplitLinkCommand(map, resp, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

//    /**
//     * 
//     *  
//     */
//    public void testTransmogrifyForkOrJoinCommand() {
//        testAddForkOnEmptyPointCommand();
//
//        assertTrue("Initial AndFork not created!", fork instanceof AndFork); //$NON-NLS-1$
//
//        Command cmd = new TransmogrifyForkOrJoinCommand(fork, pathgraph);
//        assertTrue("Transmogrify can't execute!", cmd.canExecute()); //$NON-NLS-1$
//        cs.execute(cmd);
//
//        // Find the first fork in the pathgraph (hack)
//        PathNode newFork = null;
//        for (int i = 0; (i < pathgraph.getPathNodes().size()) && (newFork == null); i++) {
//            if ((pathgraph.getPathNodes().get(i) instanceof OrFork) || (pathgraph.getPathNodes().get(i) instanceof AndFork))
//                newFork = (PathNode) pathgraph.getPathNodes().get(i);
//        }
//
//        assertTrue("Can't locate a fork in the pathgraph", newFork != null); //$NON-NLS-1$
//        assertTrue("Transmogrification of Fork failed!", newFork instanceof OrFork); //$NON-NLS-1$
//    }

    /**
     * This method will go through all of the path nodes and component ref in all the maps and verify that they are all bound as they should be. will be usefull
     * to see if commands that create new elements bind them to their parents.
     */
    public void verifyBindings() {
        for (Iterator iter = urnspec.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof UCMmap){
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

}