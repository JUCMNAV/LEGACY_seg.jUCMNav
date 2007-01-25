package seg.jUCMNav.tests.scenarios;

import java.util.Vector;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.model.commands.create.IncludeConditionInScenarioCommand;
import seg.jUCMNav.model.commands.create.IncludePathNodeInScenarioCommand;
import seg.jUCMNav.model.commands.transformations.ChangeEnumerationTypeCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.tests.commands.JUCMNavCommandTests;
import ucm.map.PathNode;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
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
        tester.testBindings=false;
        
        urnspec = tester.urnspec;
        cs = tester.cs;
        
        assert urnspec.getUcmspec().getScenarioGroups().size() > 0;
        grp = (ScenarioGroup) urnspec.getUcmspec().getScenarioGroups().get(0);
        assert grp.getScenarios().size() > 0;
        scenario = (ScenarioDef) grp.getScenarios().get(0);
        expectedWarningCount=0;
        run=true;

    }

    protected void tearDown() throws Exception {
        if (run) {
            ScenarioUtils.setActiveScenario(scenario);
            assertEquals("Unexpected warning count", expectedWarningCount, getWarningCount());
        }
     
        tester.tearDown();
    }
    
 

    public void testSimple1() {
        tester.testCreatePathCommand();

        tester.start.setPrecondition(getCondition("true"));
        tester.end.setPostcondition(getCondition("true"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
    }
    
    public void testSimple2() {
        tester.testExtendPathCommand();
        tester.start.setPrecondition(getCondition("true"));
        tester.end.setPostcondition(getCondition("true"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
    }

    public void testSimple3() {

        initialize(addBoolean("b"), "true");
        
        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("b=false;");
        
        tester.start.setPrecondition(getCondition("b"));
        tester.end.setPostcondition(getCondition("!b"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("b==true");
        addScenarioPostCondition("b!=true");
    }
    
    public void testSimple4() {

        initialize(addInteger("i"), "15");
        
        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("i=50;");
        
        tester.start.setPrecondition(getCondition("i<25"));
        tester.end.setPostcondition(getCondition("!i<25"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("i==15");
        addScenarioPostCondition("i==50");
    }
    
    public void testSimple5() {

        initialize(addInteger("i"), "15");
        
        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("i=i+1;");
        tester.resp.setRepetitionCount(10);
        tester.start.setPrecondition(getCondition("i<25"));
        tester.end.setPostcondition(getCondition("i>=25"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("i==15");
        addScenarioPostCondition("i==25");
    }   
    
    public void testSimple6() {

        Vector v = new Vector();
        v.add("INITIAL");
        v.add("MIDPOINT");
        v.add("FINAL");
        
        initialize(addEnumeration(addEnumerationType("POSSIBLESTATES", v), "state"), "INITIAL");
        
        tester.testSplitLinkCommand();
        tester.resp.getRespDef().setExpression("state=MIDPOINT;");
        
        tester.start.setPrecondition(getCondition("state!=FINAL"));
        tester.end.setPostcondition(getCondition("state!=FINAL"));

        addScenarioNode(tester.start);
        addScenarioNode(tester.end);
        addScenarioPreCondition("state==INITIAL");
        addScenarioPostCondition("state==MIDPOINT");
    }

    protected Variable addBoolean(String name)
    {
        CreateVariableCommand cmd = new CreateVariableCommand(urnspec, ScenarioUtils.sTypeBoolean, name);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        return cmd.getVar();
    }
    protected Variable addInteger(String name)
    {
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
    protected Variable addEnumeration(EnumerationType type, String name)
    {
        CreateVariableCommand cmd = new CreateVariableCommand(urnspec, ScenarioUtils.sTypeEnumeration, name);
        cmd.setEnumerationType(type);
        assertTrue("Can't execute CreateVariableCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        return cmd.getVar();
    }    
    protected void initialize(Variable v, String value)
    {
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
    
    protected int getWarningCount()
    {
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
