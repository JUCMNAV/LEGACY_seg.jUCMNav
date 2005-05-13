package seg.jUCMNav.tests.commands;

import junit.framework.TestCase;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ComponentRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteLabelCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;
import urncore.Label;
import urncore.UCMmodelElement;

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
    UCMmodelElement componentRefWithLabel;
    UCMmodelElement pathNodeWithLabel;


    public static void main(String[] args) {

        junit.textui.TestRunner.run(JUCMNavCommandTests.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        umm = new UrnModelManager();

        urnspec = (URNspec) ModelCreationFactory.getNewURNspec();
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec,ComponentRef.class);
        start = (StartPoint) ModelCreationFactory.getNewObject(urnspec,StartPoint.class);
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

    /**
     * @author jkealey
     *
     */
    public void testAddComponentCommand() {

        Command cmd = new AddComponentRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    /**
     * @author jkealey
     *
     */
    public void testSetConstraintComponentRefCommand() {

        testAddComponentCommand();
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
    public void testSplitLinkCommand() {
        testCutPathCommand();
        NodeConnection nc = (NodeConnection) end.getPred().get(0);
        EmptyPoint empty = (EmptyPoint) ModelCreationFactory.getNewObject(urnspec,EmptyPoint.class);
        Command cmd = new SplitLinkCommand(pathgraph, empty, nc, 55, 86);
        assertTrue("Can't execute SplitLinkCommand.", cmd.canExecute());
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
        compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec,ComponentRef.class);
        testAddComponentCommand();
        
        Command cmd  = new SetConstraintComponentRefCommand(compRef, 0,0,100,100);
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
    public void testDeleteComponentRefCommand() {
        testAddComponentCommand();
        
        Command cmd = new DeleteComponentRefCommand(compRef);
        assertTrue("Can't execute DeleteComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);        
    }

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

    public void testCreateLabelCommand() {
    	testCreatePathCommand();
    	testAddComponentCommand();
    	
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

    //    public void testDeleteNodeCommand()
    //    {
    //            assert false : "not yet implemented";
    //    }

}