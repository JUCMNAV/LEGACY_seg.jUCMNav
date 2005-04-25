package seg.jUCMNav.tests.commands;

import junit.framework.TestCase;

import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * Created on 15-Apr-2005
 * 
 * @author jkealey
 *  
 */
public class JUCMNavCommandTests extends TestCase {

    URNspec urnspec;
    CommandStack cs;
    ComponentRef compRef;
    StartPoint start;
    EndPoint end;
    Map map;
    PathGraph pathgraph;
    UrnModelManager umm;

    public static void main(String[] args) {

        junit.textui.TestRunner.run(JUCMNavCommandTests.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        umm = new UrnModelManager();

        urnspec = (URNspec) ModelCreationFactory.getNewObject(URNspec.class);
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(ComponentRef.class);
        start = (StartPoint) ModelCreationFactory.getNewObject(StartPoint.class);
        map = (Map) urnspec.getUcmspec().getMaps().get(0);
        pathgraph = map.getPathGraph();

        cs = new CommandStack();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();

        int i = cs.getCommands().length;

        if (cs.getCommands().length > 0) {
            assertTrue("Can't undo first command", cs.canUndo());
            cs.undo();
            assertTrue("Can't redo first command", cs.canRedo());
            cs.redo();
        }

        while (i-- > 0) {
            assertTrue("Can't undo a certain command", cs.canUndo());
            cs.undo();
        }

        i = cs.getCommands().length;
        while (i-- > 0) {
            assertTrue("Can't redo a certain command", cs.canRedo());
            cs.redo();
        }

        // serialize using UrnModelManager && getName();
    }

    public void testAddComponentCommand() {

        JUCMNavCommand cmd = new AddComponentRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    public void testSetConstraintComponentRefCommand() {

        testAddComponentCommand();
        JUCMNavCommand cmd = new SetConstraintComponentRefCommand(compRef, 100, 200, 300, 400);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
        cmd = new SetConstraintComponentRefCommand(compRef, 69, 69, 69, 69);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    public void testCreatePathCommand() {
        JUCMNavCommand cmd = new CreatePathCommand(pathgraph, start, 35, 67);
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute());
        cs.execute(cmd);

        // for future use
        end = (EndPoint) ((NodeConnection) ((NodeConnection) start.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget();

    }

    public void testSetConstraintCommand() {
        testCreatePathCommand();
        JUCMNavCommand cmd = new SetConstraintCommand(end, 96, 36);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    public void testExtendPathCommand() {
        testCreatePathCommand();
        JUCMNavCommand cmd;
        for (int i = 0; i < 5; i++) {
            // will have to remove randomness (by seeding) when we start serializing
            cmd = new ExtendPathCommand(pathgraph, end, (int) (Math.random() * 1000), (int) (Math.random() * 1000));
            assertTrue("Can't execute ExtendPathCommand.", cmd.canExecute());
            cs.execute(cmd);
        }

    }

    public void testCutPathCommand() {
        testExtendPathCommand();

        EmptyPoint empty = (EmptyPoint) ((NodeConnection) ((NodeConnection) end.getPred().get(0)).getSource().getPred().get(0)).getSource();
        JUCMNavCommand cmd = new CutPathCommand(pathgraph, empty);
        assertTrue("Can't execute CutPathCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

    public void testSplitLinkCommand() {
        testCutPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        EmptyPoint empty = (EmptyPoint) ModelCreationFactory.getNewObject(EmptyPoint.class);
        JUCMNavCommand cmd = new SplitLinkCommand(pathgraph, empty, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute());
        cs.execute(cmd);

    }

}