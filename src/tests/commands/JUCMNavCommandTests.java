package tests.commands;

import junit.framework.TestCase;

import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.emf.ModelCreationFactory;
import seg.jUCMNav.model.commands.AddComponentRefCommand;
import seg.jUCMNav.model.commands.CreatePathCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.SetConstraintComponentRefCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
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
    Map map;
    PathGraph pathgraph; 

    public static void main(String[] args) {
        junit.textui.TestRunner.run(JUCMNavCommandTests.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

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
        
        if (cs.getCommands().length>0)
        {
            assertTrue("Can't undo first command", cs.canUndo());
            cs.undo();
            assertTrue("Can't redo first command", cs.canRedo());
            cs.redo();
        }
        
        while(i-->0)
        {
            assertTrue("Can't undo a certain command", cs.canUndo());
            cs.undo();
        }
        
        i=cs.getCommands().length;
        while(i-->0)
        {
            assertTrue("Can't redo a certain command", cs.canRedo());
            cs.redo();
        }        
    }

    public void testAddComponentCommand() {

        JUCMNavCommand cmd = new AddComponentRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute());
        cs.execute(cmd);
    }

    public void testSetConstraintComponentRefCommand() {
        
        testAddComponentCommand();
        JUCMNavCommand cmd = new SetConstraintComponentRefCommand(compRef,100, 200, 300, 400);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
        cmd = new SetConstraintComponentRefCommand(compRef,69, 69, 69, 69);
        assertTrue("Can't execute SetConstraintComponentRefCommand.", cmd.canExecute());
        cs.execute(cmd);
    }
    
    public void testCreatePathCommand() {
        JUCMNavCommand cmd = new CreatePathCommand(pathgraph, start, 35, 67 );
        assertTrue("Can't execute CreatePathCommand.", cmd.canExecute());
        cs.execute(cmd);
    
    }
    

}