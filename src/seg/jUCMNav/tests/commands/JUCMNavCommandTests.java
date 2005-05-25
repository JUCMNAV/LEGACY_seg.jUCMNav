package seg.jUCMNav.tests.commands;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

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
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddForkOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddForkOnEmptyPointCommand;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentElementCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteLabelCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndFork;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Label;
import urncore.Responsibility;
import urncore.UCMmodelElement;

/**
 * Created on 15-Apr-2005
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
    private Map map;
    private PathGraph pathgraph;
    private UCMmodelElement pathNodeWithLabel;
    private StartPoint start;

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
        IProject testproject = (IProject) workspaceRoot.getProject("jUCMNav-tests");
        if (!testproject.exists())
            testproject.create(null);

        if (!testproject.isOpen())
            testproject.open(null);

        testfile = testproject.getFile("jUCMNav-test.ucm");

        // start with clean file
        if (testfile.exists())
            testfile.delete(true, false, null);

        testfile.create(new ByteArrayInputStream("".getBytes()), false, null);

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(testfile.getName());
        editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(testfile), desc.getId());

        // generate a top level model element
        urnspec = editor.getModel();
        //        urnspec = (URNspec) ModelCreationFactory.getNewURNspec();

        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        map = (Map) urnspec.getUcmspec().getMaps().get(0);
        pathgraph = map.getPathGraph();

        //cs = new CommandStack();
        cs = editor.getDelegatingCommandStack();

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
        for (Iterator iter = urnspec.getUcmspec().getMaps().iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();
            cmd = new TrimEmptyNodeCommand(map);
            assertTrue("Can't execute trim empty node command!", cmd.canExecute());
            cs.execute(cmd);
        }

        editor.doSave(null);

        // verify the bindings
        if (testBindings)
            verifyBindings();

        int i = cs.getCommands().length;

        if (cs.getCommands().length > 0) {
            assertTrue("Can't undo first command", cs.canUndo());
            cs.undo();
            editor.doSave(null);
            assertTrue("Can't redo first command", cs.canRedo());
            cs.redo();
            editor.doSave(null);
        }

        while (i-- > 0) {
            assertTrue("Can't undo a certain command", cs.canUndo());
            cs.undo();
        }

        editor.doSave(null);

        i = cs.getCommands().length;
        while (i-- > 0) {
            assertTrue("Can't redo a certain command", cs.canRedo());
            cs.redo();
        }

        // verify the bindings
        if (testBindings)
            verifyBindings();

        editor.doSave(null);
        // serialize using UrnModelManager && getName();

        editor.closeEditor(false);
    }

    /**
     * @author jkealey
     *  
     */
    public void testAddComponentRefCommand() {

        Command cmd = new AddComponentRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testAddForkOnConnectionCommand() {
        testExtendPathCommand();
        Command cmd;
        PathNode fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
        cmd = new AddForkOnConnectionCommand(fork, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(0), 150, 39);
        assertTrue("Can't execute AddForkOnConnectionCommand with orfork.", cmd.canExecute());
        cs.execute(cmd);
        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
        cmd = new AddForkOnConnectionCommand(fork, pathgraph, (NodeConnection) pathgraph.getNodeConnections().get(2), 30, 457);
        assertTrue("Can't execute AddForkOnConnectionCommand with andfork.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testAddForkOnEmptyPointCommand() {
        testExtendPathCommand();
        EmptyPoint pt;
        int i = 0;
        // find empty point.
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < pathgraph.getPathNodes().size());

        Command cmd;
        PathNode fork = (OrFork) ModelCreationFactory.getNewObject(urnspec, OrFork.class);
        cmd = new AddForkOnEmptyPointCommand(fork, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
        assertTrue("Can't execute AddForkOnEmptyPointCommand with orfork.", cmd.canExecute());
        cs.execute(cmd);

        // find another empty point.
        i = 0;
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testAddForkOnEmptyPointCommand!", i < pathgraph.getPathNodes().size());

        fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
        cmd = new AddForkOnEmptyPointCommand(fork, pathgraph, (EmptyPoint) pathgraph.getPathNodes().get(i));
        assertTrue("Can't execute AddForkOnEmptyPointCommand with andfork.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testComponentRefBindChildCommand() {

        // add a compref and position it.
        testSetConstraintComponentRefCommand();
        ComponentRef child = compRef;

        // add another one
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        testAddComponentRefCommand();

        Command cmd = new SetConstraintComponentRefCommand(compRef, 0, 0, 1000, 1000);
        assertTrue("Can't execute SetConstraintComponentRefCommand in testComponentRefBindChildCommand.", cmd.canExecute());
        cs.execute(cmd);
        // the current compRef is now behind the original one.

        cmd = new ComponentRefBindChildCommand(compRef, child);
        assertTrue("Can't execute ComponentRefBindChildCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testComponentRefUnbindChildCommand() {
        testComponentRefBindChildCommand();
        ComponentRef parent = compRef;
        ComponentRef child = (ComponentRef) compRef.getChildren().get(0);

        Command cmd = new ComponentRefUnbindChildCommand(parent, child);
        assertTrue("Can't execute ComponentRefUnbindChildCommand.", cmd.canExecute());
        cs.execute(cmd);

        // we don't want to test the bindings as we have explicitely changed them.
        testBindings = false;

    }

    /**
     * @author jmcmanus
     *  
     */
    public void testCreateLabelCommand() {
        testAddComponentRefCommand();
        testCreatePathCommand();

        pathNodeWithLabel = (UCMmodelElement) end;

        CreateLabelCommand cmd = new CreateLabelCommand(pathNodeWithLabel);
        cmd.setDeltaX(50);
        cmd.setDeltaX(50);

        assertTrue("Can't execute CreateLabelCommand.", cmd.canExecute());
        cs.execute(cmd);

        componentRefWithLabel = (UCMmodelElement) compRef;

        cmd = new CreateLabelCommand(componentRefWithLabel);
        cmd.setDeltaX(0);
        cmd.setDeltaX(0);

        assertTrue("Can't execute CreateLabelCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testCreateMapCommand() {

        CreateMapCommand cmd = new CreateMapCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd.canExecute());
        cs.execute(cmd);

        assertEquals("map was not added properly in model", urnspec.getUcmspec().getMaps().size(), 2);
        assertEquals("map was not added properly in editor", editor.getPageCount(), 2);

        // add bogus data to new map; set variables to help other commands access this new map
        map = (Map) urnspec.getUcmspec().getMaps().get(1);
        pathgraph = map.getPathGraph();
        testSetConstraintCommand();
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);
        testExtendPathCommand();

    }

    /**
     * @author jkealey
     *  
     */
    public void testCreatePathCommand() {
        Command cmd = new CreatePathCommand(pathgraph, start, 35, 67);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute());
        cs.execute(cmd);

        // for future use
        end = (EndPoint) ((NodeConnection) ((NodeConnection) start.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget();

    }

    /**
     * @author jkealey
     *  
     */
    public void testCutPathCommand() {
        testExtendPathCommand();

        EmptyPoint empty = (EmptyPoint) ((NodeConnection) ((NodeConnection) end.getPred().get(0)).getSource().getPred().get(0)).getSource();
        Command cmd = new CutPathCommand(pathgraph, empty);
        assertTrue("Can't execute CutPathCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testDeleteComponentElementCommand() {
        testAddComponentRefCommand();

        Command cmd = new DeleteComponentElementCommand(compRef.getCompDef());
        assertTrue("Should not be able to execute DeleteComponentElementCommand.", !cmd.canExecute());
        cs.execute(cmd);

        ComponentElement compDef = compRef.getCompDef();
        cmd = new DeleteComponentRefCommand(compRef);
        assertTrue("Can't execute DeleteComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);

        cmd = new DeleteComponentElementCommand(compDef);
        assertTrue("Can't execute DeleteComponentElementCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testDeleteComponentRefCommand() {
        testAddComponentRefCommand();

        Command cmd = new DeleteComponentRefCommand(compRef);
        assertTrue("Can't execute DeleteComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jmcmanus
     *  
     */
    public void testDeleteLabelCommand() {
        testCreateLabelCommand();

        DeleteLabelCommand cmd = new DeleteLabelCommand();
        cmd.setModelElement(pathNodeWithLabel);
        cmd.setLabel((Label) ((PathNode) pathNodeWithLabel).getLabel());

        assertTrue("Can't execute testDeleteLabelCommand.", cmd.canExecute());
        cs.execute(cmd);

        cmd = new DeleteLabelCommand();
        cmd.setModelElement(componentRefWithLabel);
        cmd.setLabel((Label) ((ComponentRef) componentRefWithLabel).getLabel());

        assertTrue("Can't execute testDeleteLabelCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testDeleteMapCommand() {
        testCreateMapCommand();

        DeleteMapCommand cmd = new DeleteMapCommand(map);
        assertTrue("Can't execute DeleteMapCommand.", cmd.canExecute());

        // must run as CompoundCommand because framework refreshes these
        CompoundCommand cmd2 = new CompoundCommand();
        cmd2.add(cmd);
        cs.execute(cmd2);
    }

    /**
     * @author jkealey
     *  
     */
    public void testDeleteNodeCommand() {
        testSplitLinkCommand();

        PathNode pn;
        int i = 0;
        // find empty point.
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EmptyPoint)) {
            i++;
        }
        assertTrue("No empty points exist for testDeleteNodeCommand!", i < pathgraph.getPathNodes().size());
        pn = (EmptyPoint) pathgraph.getPathNodes().get(i);

        DeleteNodeCommand cmd = new DeleteNodeCommand(pn);
        assertTrue("Can't execute DeleteNodeCommand.", cmd.canExecute());
        cs.execute(cmd);

        i = 0;
        // find start point.
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof StartPoint)) {
            i++;
        }
        assertTrue("No start points exist for testDeleteNodeCommand!", i < pathgraph.getPathNodes().size());
        pn = (StartPoint) pathgraph.getPathNodes().get(i);

        cmd = new DeleteNodeCommand(pn);
        assertTrue("Should not be able to delete start point DeleteNodeCommand.", !cmd.canExecute());
        cs.execute(cmd);

        i = 0;
        // find end point.
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof EndPoint)) {
            i++;
        }
        assertTrue("No end points exist for testDeleteNodeCommand!", i < pathgraph.getPathNodes().size());
        pn = (EndPoint) pathgraph.getPathNodes().get(i);

        cmd = new DeleteNodeCommand(pn);
        assertTrue("Should not be able to delete end point DeleteNodeCommand.", !cmd.canExecute());
        cs.execute(cmd);

        i = 0;
        // find respref .
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof RespRef)) {
            i++;
        }
        assertTrue("No RespRefs exist for testDeleteNodeCommand!", i < pathgraph.getPathNodes().size());
        pn = (RespRef) pathgraph.getPathNodes().get(i);

        cmd = new DeleteNodeCommand(pn);
        assertTrue("Can't execute DeleteNodeCommand on RespRef.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testDeleteResponsibilityCommand() {
        testSplitLinkCommand();

        RespRef rr;
        int i = 0;
        // find respref .
        while (i < pathgraph.getPathNodes().size() && !(pathgraph.getPathNodes().get(i) instanceof RespRef)) {
            i++;
        }
        assertTrue("No RespRefs exist for testDeleteResponsibilityCommand!", i < pathgraph.getPathNodes().size());
        rr = (RespRef) pathgraph.getPathNodes().get(i);
        Responsibility resp = rr.getRespDef();

        Command cmd = new DeleteResponsibilityCommand(resp);
        assertTrue("Must not be able to delete referenced responsibility.", !cmd.canExecute());
        cs.execute(cmd);

        cmd = new DeleteNodeCommand(rr);
        assertTrue("Can't delete RespRef", cmd.canExecute());
        cs.execute(cmd);

        cmd = new DeleteResponsibilityCommand(resp);
        assertTrue("Can't execute DeleteResponsibilityCommand", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * @author jkealey
     *  
     */
    public void testExtendPathCommand() {
        testCreatePathCommand();
        Command cmd;
        for (int i = 0; i < 5; i++) {
            // will have to remove randomness (by seeding) when we start serializing
            cmd = new ExtendPathCommand(pathgraph, end, (int) (Math.random() * 1000), (int) (Math.random() * 1000));
            assertTrue("Can't execute ExtendPathCommand.", cmd.canExecute());
            cs.execute(cmd);
        }
    }

    /**
     * @author ??
     *  
     */
    public void testJoinPathsCommand() {
        //TODO: implement test
        assertTrue("Jean-Philippe Daigle, do me! (in the implementation sense)", false);
    }

    /**
     * @author jmcmanus
     *  
     */
    public void testLabelSetConstraintCommand() {
        testCreateLabelCommand();

        LabelSetConstraintCommand cmd = new LabelSetConstraintCommand();
        cmd.setLabel((Label) ((PathNode) pathNodeWithLabel).getLabel());
        cmd.setNewPosition(75, 75);

        assertTrue("Can't execute LabelSetConstraintCommand.", cmd.canExecute());
        cs.execute(cmd);

        cmd = new LabelSetConstraintCommand();
        cmd.setLabel((Label) ((ComponentRef) componentRefWithLabel).getLabel());
        cmd.setNewPosition(25, 25);

        assertTrue("Can't execute LabelSetConstraintCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testSetConstraintBoundComponentRefCompoundCommand() {
        testComponentRefBindChildCommand();

        ComponentRef parent = compRef;
        Command cmd = new SetConstraintBoundComponentRefCompoundCommand(parent, 150, 300, 453, 148);
        assertTrue("Can't execute SetConstraintBoundComponentRefCompoundCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testSetConstraintCommand() {
        testCreatePathCommand();
        Command cmd = new SetConstraintCommand(end, 96, 36);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testSetConstraintComponentRefCommand() {

        testAddComponentRefCommand();
        Command cmd = new SetConstraintComponentRefCommand(compRef, 100, 200, 300, 400);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
        cmd = new SetConstraintComponentRefCommand(compRef, 69, 69, 69, 69);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *  
     */
    public void testSplitLinkCommand() {
        testCutPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
        Command cmd = new SplitLinkCommand(pathgraph, resp, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    /**
     * This method will go through all of the path nodes and component ref in all the maps and verify that they are all bound as they should be. will be usefull
     * to see if commands that create new elements bind them to their parents.
     */
    public void verifyBindings() {
        for (Iterator iter = urnspec.getUcmspec().getMaps().iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();

            for (Iterator iter2 = map.getCompRefs().iterator(); iter2.hasNext();) {
                ComponentRef comp = (ComponentRef) iter2.next();
                assertEquals("Component " + comp.toString() + " is not properly bound.", ParentFinder.getPossibleParent(comp), comp.getParent());

            }
            for (Iterator iter2 = map.getPathGraph().getPathNodes().iterator(); iter2.hasNext();) {
                PathNode pn = (PathNode) iter2.next();
                assertEquals("PathNode " + pn.toString() + " is not properly bound.", ParentFinder.getPossibleParent(pn), pn.getCompRef());
            }
        }

    }
}