package seg.jUCMNav.tests.scenarios;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.model.commands.create.IncludeConditionInScenarioCommand;
import seg.jUCMNav.model.commands.create.IncludePathNodeInScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteScenarioPathNodeCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.ChangeEnumerationTypeCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.ReorderScenarioChildrenCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder.QFindReachableEndPoints;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder.QFindReachableStartPoints;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.tests.commands.JUCMNavCommandTests;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;

public class ScenarioTraversalTests extends TestCase {

    protected ScenarioGroup grp;
    protected ScenarioDef scenario;
    protected int expectedWarningCount;
    protected boolean run;
    protected JUCMNavCommandTests tester;
    protected CommandStack cs;
    protected URNspec urnspec;

    protected void setUp() throws Exception {
        tester = new JUCMNavCommandTests();
        tester.initjucmnav();
        tester.testBindings = false;

        urnspec = tester.urnspec;
        cs = tester.cs;

        assert urnspec.getUcmspec().getScenarioGroups().size() > 0;
        grp = (ScenarioGroup) urnspec.getUcmspec().getScenarioGroups().get(0);
        assert grp.getScenarios().size() > 0;
        scenario = (ScenarioDef) grp.getScenarios().get(0);
        expectedWarningCount = 0;
        run = true;

    }

    protected void tearDown() throws Exception {
        if (run) {
            runscenario();
        }

        tester.tearDown();
    }

    private void runscenario() {
        ScenarioUtils.setActiveScenario(scenario);
        assertEquals("Unexpected warning count", expectedWarningCount, getWarningCount()); //$NON-NLS-1$
    }

    /**
     * Given SPEP, launch SP, reach EP. Pre/post conditions true.
     * 
     */
    public void testSimple1() {
        tester.testCreatePathCommand();

        tester.start.setPrecondition(getCondition("true")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("true")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
    }

    /**
     * Given SPEP with a few empty points in middle, launch SP, reach EP. Pre/post conditions true.
     * 
     */
    public void testSimple2() {
        tester.testExtendPathCommand();
        tester.start.setPrecondition(getCondition("true")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("true")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
    }

    /**
     * Given SPREP, launch SP, reach EP. R sets boolean condition to false, verified in post condition.
     * 
     */
    public void testSimple3() {

        initialize(addBoolean("b"), "true"); //$NON-NLS-1$ //$NON-NLS-2$

        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("b=false;"); //$NON-NLS-1$

        tester.start.setPrecondition(getCondition("b")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("!b")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("b==true"); //$NON-NLS-1$
        addScenarioPostCondition("b!=true"); //$NON-NLS-1$
    }

    /**
     * Given SPREP, launch SP, reach EP. R sets integer condition to a certain non-zero value, verified in post condition
     * 
     */
    public void testSimple4() {

        initialize(addInteger("i"), "15"); //$NON-NLS-1$ //$NON-NLS-2$

        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("i=50;"); //$NON-NLS-1$

        tester.start.setPrecondition(getCondition("i<25")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("!i<25")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("i==15"); //$NON-NLS-1$
        addScenarioPostCondition("i==50"); //$NON-NLS-1$
    }

    /**
     * Given SPREP, launch SP, reach EP. R increments integer. R has repetition count. Total count verified in post condition
     * 
     */
    public void testSimple5() {

        initialize(addInteger("i"), "15"); //$NON-NLS-1$ //$NON-NLS-2$

        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("i=i+1;"); //$NON-NLS-1$
        tester.resp.setRepetitionCount("10"); //$NON-NLS-1$
        tester.start.setPrecondition(getCondition("i<25")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("i>=25")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("i==15"); //$NON-NLS-1$
        addScenarioPostCondition("i==25"); //$NON-NLS-1$
    }

    /**
     * Given SPREP, launch SP, reach EP. R sets enum condition to a certain value, verified in post condition
     * 
     */
    public void testSimple6() {

        Vector v = new Vector();
        v.add("INITIAL"); //$NON-NLS-1$
        v.add("MIDPOINT"); //$NON-NLS-1$
        v.add("FINAL"); //$NON-NLS-1$

        initialize(addEnumeration(addEnumerationType("POSSIBLESTATES", v), "state"), "INITIAL"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("state=MIDPOINT;"); //$NON-NLS-1$

        tester.start.setPrecondition(getCondition("state!=FINAL")); //$NON-NLS-1$
        tester.end.setPostcondition(getCondition("state!=FINAL")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("state==INITIAL"); //$NON-NLS-1$
        addScenarioPostCondition("state==MIDPOINT"); //$NON-NLS-1$
    }

    /**
     * Given SPOFEP, launch SP, reach appropriate EP given varying boolean condition. (two tests).
     * 
     */
    public void testFork1() {
        tester.testForkPathsCommand();
        initialize(addBoolean("b"), "true"); //$NON-NLS-1$ //$NON-NLS-2$

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("b")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("!b")); //$NON-NLS-1$
        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        DeleteScenarioPathNodeCommand cmd = new DeleteScenarioPathNodeCommand((ScenarioEndPoint) scenario.getEndPoints().get(0));
        assertTrue("Can't execute DeleteScenarioPathNodeCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("!b")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("b")); //$NON-NLS-1$

        HashSet set = new HashSet();
        set.add(((NodeConnection) tester.fork.getSucc().get(0)));

        QFindReachableEndPoints qry = new EndPointFinder.QFindReachableEndPoints(tester.fork, set, QFindReachableNodes.DIRECTION_FORWARD);
        EndPointFinder.RReachableEndPoints resp = (EndPointFinder.RReachableEndPoints) GraphExplorer.run(qry);
        Vector vEndPoints = resp.getNodes();
        assertTrue("Should have found one end point", vEndPoints.size() == 1); //$NON-NLS-1$

        addScenarioNode((EndPoint) vEndPoints.get(0));

    }

    /**
     * Given SPOFEP, launch SP, reach appropriate EP given varying integer condition. (two tests).
     * 
     */
    public void testFork2() {
        tester.testForkPathsCommand();
        initialize(addInteger("i"), "15"); //$NON-NLS-1$ //$NON-NLS-2$

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("i<25")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("i>=25")); //$NON-NLS-1$
        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        DeleteScenarioPathNodeCommand cmd = new DeleteScenarioPathNodeCommand((ScenarioEndPoint) scenario.getEndPoints().get(0));
        assertTrue("Can't execute DeleteScenarioPathNodeCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("i>=25")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("i<25")); //$NON-NLS-1$

        HashSet set = new HashSet();
        set.add(((NodeConnection) tester.fork.getSucc().get(0)));

        QFindReachableEndPoints qry = new EndPointFinder.QFindReachableEndPoints(tester.fork, set, QFindReachableNodes.DIRECTION_FORWARD);
        EndPointFinder.RReachableEndPoints resp = (EndPointFinder.RReachableEndPoints) GraphExplorer.run(qry);
        Vector vEndPoints = resp.getNodes();
        assertTrue("Should have found one end point", vEndPoints.size() == 1); //$NON-NLS-1$

        addScenarioNode((EndPoint) vEndPoints.get(0));

    }

    /**
     * Given SPOFEP, launch SP, reach appropriate EP given varying enumeration condition. (two tests).
     * 
     */
    public void testFork3() {
        tester.testForkPathsCommand();
        Vector v = new Vector();
        v.add("INITIAL"); //$NON-NLS-1$
        v.add("MIDPOINT"); //$NON-NLS-1$
        v.add("FINAL"); //$NON-NLS-1$

        initialize(addEnumeration(addEnumerationType("POSSIBLESTATES", v), "state"), "INITIAL"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("state==INITIAL")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("state==FINAL")); //$NON-NLS-1$
        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        DeleteScenarioPathNodeCommand cmd = new DeleteScenarioPathNodeCommand((ScenarioEndPoint) scenario.getEndPoints().get(0));
        assertTrue("Can't execute DeleteScenarioPathNodeCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ((NodeConnection) tester.fork.getSucc().get(0)).setCondition(getCondition("state==FINAL")); //$NON-NLS-1$
        ((NodeConnection) tester.fork.getSucc().get(1)).setCondition(getCondition("state!=FINAL")); //$NON-NLS-1$

        HashSet set = new HashSet();
        set.add(((NodeConnection) tester.fork.getSucc().get(0)));

        QFindReachableEndPoints qry = new EndPointFinder.QFindReachableEndPoints(tester.fork, set, QFindReachableNodes.DIRECTION_FORWARD);
        EndPointFinder.RReachableEndPoints resp = (EndPointFinder.RReachableEndPoints) GraphExplorer.run(qry);
        Vector vEndPoints = resp.getNodes();
        assertTrue("Should have found one end point", vEndPoints.size() == 1); //$NON-NLS-1$

        addScenarioNode((EndPoint) vEndPoints.get(0));

    }

    /**
     * Given SPAF(EP|EP), launch SP, reach both EP.
     * 
     */
    public void testFork4() {
        tester.testExtendPathCommand();
        AndFork fork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
        Command cmd = new DividePathCommand(fork, (NodeConnection) tester.map.getConnections().get(0), 30, 457);
        assertTrue("Can't execute DividePathCommand with andfork.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        addScenarioNode(tester.start);

        for (Iterator iter = tester.map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof EndPoint)
                addScenarioNode(pn);
        }
    }

    /**
     * Given (SP|SP)AJEP, launch both SP, reach EP.
     * 
     */
    public void testJoin1() {
        tester.testExtendPathCommand();
        AndJoin join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
        Command cmd = new DividePathCommand(join, (NodeConnection) tester.map.getConnections().get(2), 30, 457);
        assertTrue("Can't execute DividePathCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        addScenarioNode(tester.end);
        for (Iterator iter = tester.map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof StartPoint)
                addScenarioNode(pn);
        }
    }

    /**
     * Given (SP|SP)OJEP, launch both sp, reach EP twice.
     * 
     */
    public void testJoin2() {
        tester.testJoinPathsCommand();

        // twice.
        addScenarioNode(tester.end);
        addScenarioNode(tester.end);

        for (Iterator iter = tester.map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof StartPoint)
                addScenarioNode(pn);
        }
    }

    /**
     * Given SPEPCSPEP, launch first sp, reach both EP.
     * 
     */
    public void testSynchConnect1() {
        tester.testConnectCommand();

        // both start and end points.
        for (Iterator iter = tester.map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof StartPoint || pn instanceof EndPoint)
                addScenarioNode(pn);
        }

    }

    /**
     * Given (SPEPC|SP)WPEP where WP is blocked, verify both EP are reached if SPEP is fired after SP.
     * 
     */
    public void testSynchConnect2() {

        tester.testReplaceEmptyPointCommand();
        // wait will be blocked.
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("false")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        // will unblock it.

        testSimple1();

        Command cmd = new ConnectCommand(tester.wait, tester.end);
        assertTrue("Can't execute ConnectCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * Given (SPEPC|SP)TEP where T is blocked, verify both EP are reached if SPEP is fired after SP.
     * 
     */
    public void testSynchConnect3() {
        tester.testReplaceEmptyPointCommand2();
        // will be blocked
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("false")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        // will unblock it.

        testSimple1();

        Command cmd = new ConnectCommand(tester.wait, tester.end);
        assertTrue("Can't execute ConnectCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * Given (SPC|SP)EMPTY EP (asynch connect), verify that EP is fired even if you launch only SPC. Verify that if you launch both, the EP is reached twice.
     * 
     */
    public void testAsynchConnect1() {
        testSimple2();
        EmptyPoint empty = (EmptyPoint) ((NodeConnection) tester.start.getSucc().get(0)).getTarget();

        testSimple1();

        Command cmd = new ConnectCommand(tester.start, empty);
        assertTrue("Can't execute ConnectCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * Given SPWPEP, where WP is released on arrival by condition, EP is reached
     * 
     */
    public void testWp1() {
        tester.testReplaceEmptyPointCommand();

        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("true")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

    }

    /**
     * Given SPTEP, where T is released on arrival by condition, EP is reached.
     * 
     */
    public void testWp2() {
        tester.testReplaceEmptyPointCommand2();

        // timer
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("true")); //$NON-NLS-1$
        ((NodeConnection) tester.wait.getSucc().get(1)).setCondition(getCondition("false")); //$NON-NLS-1$

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

    }

    /**
     * Given SPTEP, where T timeout path is released on arrival by condition, EP is reached
     * 
     */
    public void testWp3() {
        tester.testReplaceEmptyPointCommand2();

        // timer
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("false")); //$NON-NLS-1$
        ((NodeConnection) tester.wait.getSucc().get(1)).setCondition(getCondition("true")); //$NON-NLS-1$

        addScenarioNode(tester.start);

        for (Iterator iter = tester.map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof EndPoint && pn != tester.end)
                addScenarioNode(pn);
        }
    }

    /**
     * Given SPREP and SPWPEP, where R sets a condition that unblocks waiting place, verify both EP fired regardless of start order.
     * 
     */
    public void testWp4() {

        // runs first
        testSimple3();

        tester.testReplaceEmptyPointCommand();
        // wait will not be blocked.
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("!b")); //$NON-NLS-1$

        // 2nd path
        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        runscenario();

        // reorder, now will block, but will get unblocked by path with resp.
        Command cmd = new ReorderScenarioChildrenCommand(scenario, (ScenarioStartPoint) scenario.getStartPoints().get(0), false);
        assertTrue("Can't execute ReorderScenarioChildrenCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * Given SPREP and SPTEP, where R sets a condition that unblocks timer (no timeout path), verify both EP fired regardless of start order.
     * 
     */
    public void testWp5() {

        // runs first
        testSimple3();

        tester.testReplaceEmptyPointCommand2();
        // wait will not be blocked.
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("!b")); //$NON-NLS-1$
        ((NodeConnection) tester.wait.getSucc().get(1)).setCondition(getCondition("false")); //$NON-NLS-1$

        // 2nd path
        addScenarioNode(tester.start);
        addScenarioNode(tester.end);

        runscenario();

        // reorder, now will block, but will get unblocked by path with resp.
        Command cmd = new ReorderScenarioChildrenCommand(scenario, (ScenarioStartPoint) scenario.getStartPoints().get(0), false);
        assertTrue("Can't execute ReorderScenarioChildrenCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    /**
     * Given SPREP and SPTEP, where R sets a condition that unblocks timeout path, verify 2/3 EP fired regardless of start order.
     * 
     */
    public void testWp6() {

        // runs first
        testSimple3();

        tester.testReplaceEmptyPointCommand2();
        // wait will not be blocked.
        ((NodeConnection) tester.wait.getSucc().get(0)).setCondition(getCondition("false")); //$NON-NLS-1$
        ((NodeConnection) tester.wait.getSucc().get(1)).setCondition(getCondition("!b")); //$NON-NLS-1$

        // 2nd path
        addScenarioNode(tester.start);
        HashSet set = new HashSet();
        set.add(tester.wait.getSucc().get(0));
        QFindReachableEndPoints qry = new EndPointFinder.QFindReachableEndPoints(tester.wait, set, QFindReachableNodes.DIRECTION_FORWARD);
        EndPointFinder.RReachableEndPoints resp = (EndPointFinder.RReachableEndPoints) GraphExplorer.run(qry);
        Vector vEndPoints = resp.getNodes();
        assertTrue("Should have found one end point", vEndPoints.size() == 1); //$NON-NLS-1$

        addScenarioNode((EndPoint) vEndPoints.get(0));

        runscenario();

        // reorder, now will block, but will get unblocked by path with resp.
        Command cmd = new ReorderScenarioChildrenCommand(scenario, (ScenarioStartPoint) scenario.getStartPoints().get(0), false);
        assertTrue("Can't execute ReorderScenarioChildrenCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new DeleteScenarioPathNodeCommand((ScenarioEndPoint) scenario.getEndPoints().get(1));
        assertTrue("Can't execute DeleteScenarioPathNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * Given SPSTEP where ST bound to SPEP, fire top SP, verify both EP reached
     * 
     */
    public void testStub1() {
        tester.testAddInBindingCommand();
        Command cmd;

        NodeConnection exit = (NodeConnection) tester.stub.getSucc().get(0);

        cmd = new AddOutBindingCommand(tester.plugin, tester.end, exit);
        assertTrue("Can't execute AddOutBindingCommand with Plugin, EndPoint and exit NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        for (Iterator iter = urnspec.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            UCMmap map = (UCMmap) iter.next();
            for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();

                if (pn instanceof StartPoint || pn instanceof EndPoint)
                    addScenarioNode(pn);
            }
        }

    }

    /**
     * Given SPSTEP where ST bound to SPEP, fire bottom SP, verify only bottom EP reached.
     * 
     */
    public void testStub2() {
        tester.testAddInBindingCommand();
        Command cmd;

        NodeConnection exit = (NodeConnection) tester.stub.getSucc().get(0);

        cmd = new AddOutBindingCommand(tester.plugin, tester.end, exit);
        assertTrue("Can't execute AddOutBindingCommand with Plugin, EndPoint and exit NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // second map only
        for (Iterator iterator = tester.map.getNodes().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();

            if (pn instanceof StartPoint || pn instanceof EndPoint)
                addScenarioNode(pn);
        }

        run = false;
        runscenario();

        UCMmap map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(0);

        for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();

            if (pn instanceof StartPoint || pn instanceof EndPoint) {
                assertEquals("pn was hit but wasn't supposed to!", 0, ScenarioUtils.getTraversalHitCount(pn)); //$NON-NLS-1$
            }
        }
    }

    /**
     * Given (SP|SP)STEP, where ST bound to (SP|SP)AJEP, verify that both EP reached. (don't verify if top level is fired only once, see specific
     * implementation)
     * 
     */
    public void testStub3() {
        testStub1();
        tester.map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(0);

        tester.testCreatePathCommand();

        Command cmd = new AttachBranchCommand(tester.end, tester.stub);
        assertTrue("Can't execute AttachBranchCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        addScenarioNode(tester.start);

        tester.map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(1);
        AndJoin join = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
        cmd = new DividePathCommand(join, (NodeConnection) tester.map.getConnections().get(0), 30, 457);
        assertTrue("Can't execute DividePathCommand with andjoin.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        HashSet set = new HashSet();
        set.add(join.getPred().get(0));

        QFindReachableStartPoints qry = new StartPointFinder.QFindReachableStartPoints(join, set, QFindReachableNodes.DIRECTION_REVERSE);
        StartPointFinder.RReachableStartPoints resp = (StartPointFinder.RReachableStartPoints) GraphExplorer.run(qry);
        Vector vStartPoints = resp.getNodes();
        assertTrue("Should have found one start point", vStartPoints.size() == 1); //$NON-NLS-1$

        StartPoint sp = (StartPoint) vStartPoints.get(0);
        NodeConnection entry = (NodeConnection) tester.stub.getPred().get(1);

        cmd = new AddInBindingCommand(tester.plugin, sp, entry);
        assertTrue("Can't execute AddInBindingCommand with Plugin, StartPoint and entry NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    /**
     * Given SPSTEP where ST bound to two different maps with SPEP, given varying boolean condition, verify the appropriate two EP are reached
     * 
     */
    public void testStub4() {
        testStub1();

        PluginBinding oldpb = tester.plugin;

        tester.stub.setDynamic(true);

        Command cmd = new CreateMapCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // add bogus data to new map; set variables to help other commands access this new map
        tester.map = (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(2);
        tester.start = (StartPoint) ModelCreationFactory.getNewObject(urnspec, StartPoint.class);

        // Then create a new path in this map to test adding an In/Out Binding.
        tester.testCreatePathCommand();

        cmd = new AddPluginCommand(tester.stub, tester.map);
        assertTrue("Can't execute AddPluginCommand with Stub and Map.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        tester.plugin = (PluginBinding) tester.stub.getBindings().get(1);

        cmd = new AddInBindingCommand(tester.plugin, tester.start, ((InBinding) oldpb.getIn().get(0)).getStubEntry());
        assertTrue("Can't execute AddInBindingCommand with Plugin, StartPoint and entry NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new AddOutBindingCommand(tester.plugin, tester.end, ((OutBinding) oldpb.getOut().get(0)).getStubExit());
        assertTrue("Can't execute AddOutBindingCommand with Plugin, EndPoint and exit NodeConnection.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        oldpb.setPrecondition(getCondition("true")); //$NON-NLS-1$
        tester.plugin.setPrecondition(getCondition("false")); //$NON-NLS-1$

        // should have same behaviour as testStub1
        runscenario();

        Vector toDelete = new Vector();
        for (Iterator iter = scenario.getStartPoints().iterator(); iter.hasNext();) {
            ScenarioStartPoint ssp = (ScenarioStartPoint) iter.next();
            if (ssp.getStartPoint().getDiagram() == urnspec.getUrndef().getSpecDiagrams().get(1))
                toDelete.add(ssp);
        }
        for (Iterator iter = scenario.getEndPoints().iterator(); iter.hasNext();) {
            ScenarioEndPoint ssp = (ScenarioEndPoint) iter.next();
            if (ssp.getEndPoint().getDiagram() == urnspec.getUrndef().getSpecDiagrams().get(1))
                toDelete.add(ssp);
        }

        // delete expectations
        for (Iterator iter = toDelete.iterator(); iter.hasNext();) {
            EObject o = (EObject) iter.next();
            if (o instanceof ScenarioStartPoint) {
                cmd = new DeleteScenarioPathNodeCommand((ScenarioStartPoint) o);
            } else
                cmd = new DeleteScenarioPathNodeCommand((ScenarioEndPoint) o);
        }

        addScenarioNode(((InBinding) tester.plugin.getIn().get(0)).getStartPoint());
        addScenarioNode(((OutBinding) tester.plugin.getOut().get(0)).getEndPoint());

    }

    protected Variable addBoolean(String name) {
        CreateVariableCommand cmd = new CreateVariableCommand(urnspec, ScenarioUtils.sTypeBoolean, name);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        return cmd.getVar();
    }

    protected Variable addInteger(String name) {
        CreateVariableCommand cmd = new CreateVariableCommand(urnspec, ScenarioUtils.sTypeInteger, name);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        return cmd.getVar();
    }

    protected EnumerationType addEnumerationType(String name, Vector values) {
        CreateEnumerationTypeCommand cmd = new CreateEnumerationTypeCommand(urnspec);
        assertTrue("Can't execute CreateEnumerationTypeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        EnumerationType type = cmd.getEnumerationType();

        ChangeEnumerationTypeCommand cmd2 = new ChangeEnumerationTypeCommand(type, name, values);
        assertTrue("Can't execute ChangeEnumerationTypeCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        return type;
    }

    protected Variable addEnumeration(EnumerationType type, String name) {
        CreateVariableCommand cmd = new CreateVariableCommand(urnspec, ScenarioUtils.sTypeEnumeration, name);
        cmd.setEnumerationType(type);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        return cmd.getVar();
    }

    protected void initialize(Variable v, String value) {
        CreateVariableInitializationCommand cmd = new CreateVariableInitializationCommand(v, scenario, value);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    protected void addScenarioNode(PathNode pn) {
        Command cmd = new IncludePathNodeInScenarioCommand(scenario, pn);
        assertTrue("Can't execute IncludePathNodeInScenarioCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    protected void addScenarioPreCondition(String exp) {
        Command cmd = new IncludeConditionInScenarioCommand(scenario, true, getCondition(exp));
        assertTrue("Can't execute IncludeConditionInScenarioCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    protected void addScenarioPostCondition(String exp) {
        Command cmd = new IncludeConditionInScenarioCommand(scenario, false, getCondition(exp));
        assertTrue("Can't execute IncludeConditionInScenarioCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    protected Condition getCondition(String exp) {
        Condition cond = (Condition) ModelCreationFactory.getNewObject(urnspec, Condition.class);
        cond.setExpression(exp);
        return cond;
    }

    protected int getWarningCount() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            IFile resource = ((FileEditorInput) editor.getEditorInput()).getFile();
            try {

                IMarker[] existingMarkers = resource.findMarkers(IMarker.PROBLEM, true, 3);
                return existingMarkers.length;
            } catch (CoreException ex) {
                System.out.println(ex);
            }
        }
        return -1;
    }
}
