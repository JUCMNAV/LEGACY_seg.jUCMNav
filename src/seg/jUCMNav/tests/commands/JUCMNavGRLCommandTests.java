package seg.jUCMNav.tests.commands;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import grl.StrategiesGroup;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.change.ModifyUrnLinkCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddBeliefLinkCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddDependencyElementLinkCommand;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.AddLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.create.AddStandardElementLinkCommand;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelThreeCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelTwoCommand;
import seg.jUCMNav.model.commands.delete.DeleteActorCommand;
import seg.jUCMNav.model.commands.delete.DeleteActorRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.delete.DeleteLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.delete.DeleteLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategiesGroupCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import seg.jUCMNav.model.commands.transformations.AddBeliefToIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.transformations.ChangeDecompositionTypeCommand;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.ComponentRef;
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * Test suite to test the commands in the GRL editor
 * 
 * @author Jean-François Roy
 * 
 */
public class JUCMNavGRLCommandTests extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(JUCMNavGRLCommandTests.class);
    }

    private UCMNavMultiPageEditor editor;
    private CommandStack cs;

    private URNspec urnspec;
    private GRLGraph graph;
    private IntentionalElementRef ref;
    private Belief belief;
    // private Actor actor;
    private ActorRef actorref;
    private ElementLink link;
    private StrategiesGroup strategiesgroup;
    private EvaluationStrategy strategy;
    private URNlink urnlink;

    private boolean testBindings;

    protected void setUp() throws Exception {
        super.setUp();

        testBindings = true;

        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IProject testproject = workspaceRoot.getProject("jUCMNav-GRL-tests"); //$NON-NLS-1$
        if (!testproject.exists())
            testproject.create(null);

        if (!testproject.isOpen())
            testproject.open(null);

        IFile testfile = testproject.getFile("jUCMNav-GRL-test.jucm"); //$NON-NLS-1$

        // start with clean file
        if (testfile.exists())
            testfile.delete(true, false, null);

        testfile.create(new ByteArrayInputStream("".getBytes()), false, null); //$NON-NLS-1$

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(testfile.getName());
        editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(testfile), desc.getId());

        // generate a top level model element
        urnspec = editor.getModel();

        // cs = new CommandStack();
        cs = editor.getDelegatingCommandStack();

        // Delete the default UCM map, if present
        Command cmd;
        Object defaultMap = urnspec.getUrndef().getSpecDiagrams().get(0);
        if (defaultMap instanceof UCMmap) {
        	cmd = new DeleteMapCommand((UCMmap) defaultMap);
        	assertTrue("Can't execute DeleteMapCommand.", cmd.canExecute()); //$NON-NLS-1$
        	cs.execute(cmd);
        }

        // Create a new GRLGraph
        cmd = new CreateGrlGraphCommand(urnspec);
        graph = ((CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Set the preferences for deleting the references to ALWAYS
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELDEFINITION, DeletePreferences.PREF_ALWAYS);
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELREFERENCE, DeletePreferences.PREF_ALWAYS);

    }

    protected void tearDown() throws Exception {
        super.tearDown();

        editor.doSave(null);

        // Verify the Actor References binding and executing undo/redo
        if (testBindings) {
            verifyBindings();
        }

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

        if (testBindings) {
            verifyBindings();
        }

        editor.doSave(null);

        editor.closeEditor(false);

    }

    public void testAddBeliefCommand() {
        belief = (Belief) ModelCreationFactory.getNewObject(urnspec, Belief.class);

        Command cmd;

        cmd = new AddBeliefCommand(graph, belief);
        assertTrue("Can't execute AddBeliefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddBeliefLinkCommand() {
        BeliefLink belieflink = (BeliefLink) ModelCreationFactory.getNewObject(urnspec, BeliefLink.class);
        testAddBeliefCommand();
        testAddIntentionalElementRefCommand();

        Command cmd;
        cmd = new AddBeliefLinkCommand(graph, belief, belieflink);
        ((AddBeliefLinkCommand) cmd).setTarget(ref);
        assertTrue("Can't execute AddBeliefLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddBeliefToIntentionalElementRefCommand() {
        testAddIntentionalElementRefCommand();

        Command cmd = new AddBeliefToIntentionalElementRefCommand(ref);
        assertTrue("Can't execute AddBeliefToIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddContainerRefCommand() {
        actorref = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        Command cmd = new AddContainerRefCommand(graph, actorref);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddDependencyElementLinkCommand() {
        testAddIntentionalElementRefCommand();
        IntentionalElementRef destination = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.GOAL);
        Dependency dependency = (Dependency) ModelCreationFactory.getNewObject(urnspec, Dependency.class);
        // Create the second element
        Command cmd = new AddIntentionalElementRefCommand(graph, destination);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Link the 2 elements
        cmd = new AddDependencyElementLinkCommand(urnspec, ref.getDef(), dependency);
        ((AddDependencyElementLinkCommand) cmd).setTarget(destination.getDef());
        assertTrue("Can't execute AddDependencyElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddEvaluationCommand() {
        testAddIntentionalElementRefCommand();
        testCreateStrategiesGroupCommand();
        testCreateStrategyCommand();

        Evaluation eval = (Evaluation) ModelCreationFactory.getNewObject(urnspec, Evaluation.class);
        eval.setEvaluation(21);
        Command cmd = new AddEvaluationCommand(eval, ref.getDef(), strategy);

        assertTrue("Can't execute AddEvaluationCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddIntentionalElementRefCommand() {
        ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, IntentionalElementType.SOFTGOAL);

        Command cmd;

        cmd = new AddIntentionalElementRefCommand(graph, ref);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddLinkRefBendpointCommand() {
        testCreateElementLinkCommand_StandardLink();

        // The ElementLink should have only 1 linkref
        LinkRef linkref = (LinkRef) link.getRefs().get(0);

        Command cmd = new AddLinkRefBendpointCommand(linkref, 0);
        assertTrue("Can't execute AddLinkRefBendpointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddStandardElementLinkCommand() {
        testAddIntentionalElementRefCommand();
        IntentionalElementRef destination = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.GOAL);
        Decomposition decomposition = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);

        // Create the second element
        Command cmd = new AddIntentionalElementRefCommand(graph, destination);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Link the 2 elements

        cmd = new AddStandardElementLinkCommand(urnspec, ref.getDef(), decomposition);
        ((AddStandardElementLinkCommand) cmd).setTarget(destination.getDef());
        assertTrue("Can't execute AddStandardElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testChangeDecompositionTypeCommand() {
        testAddIntentionalElementRefCommand();
        IntentionalElementRef destination = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.GOAL);

        int oldType = destination.getDef().getDecompositionType().getValue();

        // Change the decomposition type (to AND)
        Command cmd = new ChangeDecompositionTypeCommand(destination, 0);
        assertTrue("Can't execute ChangeDecompositionTypeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue("ChangeDecompositionTypeCommand: type has not changed.", destination.getDef().getDecompositionType().getValue() == DecompositionType.AND); //$NON-NLS-1$

        // Change the decomposition type (to XOR)
        cmd = new ChangeDecompositionTypeCommand(destination, 2);
        assertTrue("Can't execute ChangeDecompositionTypeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue("ChangeDecompositionTypeCommand: type has not changed.", destination.getDef().getDecompositionType().getValue() == DecompositionType.XOR); //$NON-NLS-1$

        // Change the decomposition type (to OR)
        cmd = new ChangeDecompositionTypeCommand(destination, 1);
        assertTrue("Can't execute ChangeDecompositionTypeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue("ChangeDecompositionTypeCommand: type has not changed.", destination.getDef().getDecompositionType().getValue() == DecompositionType.OR); //$NON-NLS-1$
    }

    public void testContainerRefBindChildCommand() {
        // This test bind an actor ref and a node to a parent actorref
        testAddContainerRefCommand();
        testAddIntentionalElementRefCommand();

        ActorRef bindactor = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        Command cmd = new AddContainerRefCommand(graph, bindactor);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Set the size of the first parent actor
        cmd = new SetConstraintBoundContainerRefCompoundCommand(actorref, 0, 0, 1000, 1000);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Set the size of the second actor
        cmd = new SetConstraintBoundContainerRefCompoundCommand(bindactor, 0, 0, 500, 500);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Bind the two elements
        cmd = new ContainerRefBindChildCommand(bindactor, ref);
        assertTrue("Can't execute ContainerRefBindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        /*
         * already bound by SetConstraintBoundContainerRefCompoundCommand(bindactor, cmd = new ContainerRefBindChildCommand(actorref, bindactor);
         * assertTrue("Can't execute ContainerRefBindChildCommand.", cmd.canExecute()); //$NON-NLS-1$ cs.execute(cmd);
         */
    }

    public void testContainerRefUnbindChildCommand() {
        testAddContainerRefCommand();
        testAddIntentionalElementRefCommand();

        // Set the size of the actor
        Command cmd = new SetConstraintBoundContainerRefCompoundCommand(actorref, 0, 0, 1000, 1000);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new ContainerRefBindChildCommand(actorref, ref);
        assertTrue("Can't execute ContainerRefBindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new ContainerRefUnbindChildCommand(actorref, ref);
        assertTrue("Can't execute ContainerRefUnbindChildCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        testBindings = false;
    }

    public void testCreateAllLinkRefCommand() {
        // Create 2 diagrams, add 3 elements with references in each them and create link between the elements
        Command cmd = new CreateGrlGraphCommand(urnspec);
        // GRLGraph graph2 = ((CreateGrlGraphCommand)cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create 3 elements in the first graph
        IntentionalElementRef ref1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        IntentionalElementRef ref2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph, ref2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        IntentionalElementRef ref3 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.RESSOURCE);
        cmd = new AddIntentionalElementRefCommand(graph, ref3);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create links between elements
        Contribution contrib = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        // Decomposition decomp = (Decomposition)ModelCreationFactory.getNewObject(urnspec, Decomposition.class);

        cmd = new CreateElementLinkCommand(urnspec, ref1.getDef(), contrib);
        ((CreateElementLinkCommand) cmd).setTarget(ref2.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        /*
         * already done by previous command. cmd = new CreateElementLinkCommand(urnspec, ref3.getDef(), contrib);
         * ((CreateElementLinkCommand)cmd).setTarget(ref1.getDef()); assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
         * cs.execute(cmd);
         */

        // Create 3 IntentionalElement in second diagram
        // Create 3 elements in the first graph
        IntentionalElementRef ref1Diag2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.GOAL, ref1.getDef());
        cmd = new AddIntentionalElementRefCommand(graph, ref1Diag2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        IntentionalElementRef ref2Diag2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.TASK, ref2.getDef());
        cmd = new AddIntentionalElementRefCommand(graph, ref2Diag2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        IntentionalElementRef ref3Diag2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.RESSOURCE, ref3.getDef());
        cmd = new AddIntentionalElementRefCommand(graph, ref3Diag2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        /*
         * //Set the element's definition to the same for both diagrams ref1Diag2.setDef(ref1.getDef()); ref2Diag2.setDef(ref2.getDef());
         * ref3Diag2.setDef(ref3.getDef());
         */
        // Execute the CreateAllLinkRef command
        cmd = new CreateAllLinkRefCommand(ref1Diag2);
        assertTrue("Can't execute CreateAllLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateElementLinkCommand_Dependency() {
        testAddIntentionalElementRefCommand();
        link = (Dependency) ModelCreationFactory.getNewObject(urnspec, Dependency.class);
        IntentionalElementRef destination = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.SOFTGOAL);

        Command cmd;

        cmd = new AddIntentionalElementRefCommand(graph, destination);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new CreateElementLinkCommand(urnspec, ref.getDef(), link);
        ((CreateElementLinkCommand) cmd).setTarget(destination.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateElementLinkCommand_StandardLink() {
        testAddIntentionalElementRefCommand();
        link = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        IntentionalElementRef destination = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.SOFTGOAL);

        Command cmd;

        cmd = new AddIntentionalElementRefCommand(graph, destination);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        cmd = new CreateElementLinkCommand(urnspec, ref.getDef(), link);
        ((CreateElementLinkCommand) cmd).setTarget(destination.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateStrategiesGroupCommand() {
        strategiesgroup = (StrategiesGroup) ModelCreationFactory.getNewObject(urnspec, StrategiesGroup.class);

        Command cmd = new CreateStrategiesGroupCommand(urnspec, strategiesgroup);
        assertTrue("Can't execute CreateStrategiesGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    public void testCreateStrategyCommand() {
        testCreateStrategiesGroupCommand();
        // strategy = (EvaluationStrategy)ModelCreationFactory.getNewObject(urnspec, EvaluationStrategy.class);

        Command cmd = new CreateStrategyCommand(urnspec, strategiesgroup);
        strategy = ((CreateStrategyCommand) cmd).getStrategy();
        assertTrue("Can't execute CreateStrategyCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteActorCommand() {
        testContainerRefBindChildCommand();
        Actor actor = (Actor) actorref.getContDef();

        Command cmd = new DeleteActorCommand(actor);
        assertTrue("Can't execute DeleteActorCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteActorRefCommand() {
        testContainerRefBindChildCommand();

        Command cmd = new DeleteActorRefCommand(actorref);
        assertTrue("Can't execute DeleteActorRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteAllLinkRefCommand() {
        testCreateElementLinkCommand_Dependency();

        Command cmd = new DeleteAllLinkRefCommand(ref);
        assertTrue("Can't execute DeleteAllLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteElementLinkCommand() {
        testCreateElementLinkCommand_Dependency();

        Command cmd = new DeleteElementLinkCommand(link);
        assertTrue("Can't execute DeleteElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteGRLGraphCommand() {
        Command cmd = new DeleteGRLGraphCommand(graph);
        assertTrue("Can't execute DeleteGRLGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteGRLNodeCommand() {
        testCreateElementLinkCommand_Dependency();

        Command cmd = new DeleteGRLNodeCommand(ref);
        assertTrue("Can't execute DeleteGRLNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteIntentionalElementCommand() {
        testCreateElementLinkCommand_Dependency();

        IntentionalElement element = ref.getDef();

        Command cmd = new DeleteIntentionalElementCommand(element);
        assertTrue("Can't execute DeleteIntentionalElementCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteLinkRefBendpointCommand() {
        testAddLinkRefBendpointCommand();

        // The ElementLink should have only 1 linkref
        LinkRef linkref = (LinkRef) link.getRefs().get(0);
        // The bendpoint is the first in the linkref
        LinkRefBendpoint bendpoint = (LinkRefBendpoint) linkref.getBendpoints().get(0);

        Command cmd = new DeleteLinkRefBendpointCommand(bendpoint);
        assertTrue("Can't execute DeleteLinkRefBendpointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteLinkRefCommand() {
        testCreateElementLinkCommand_Dependency();

        // The ElementLink should have only 1 linkref
        LinkRef linkref = (LinkRef) link.getRefs().get(0);

        Command cmd = new DeleteLinkRefCommand(linkref);
        assertTrue("Can't execute DeleteLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteStrategiesGroupCommand() {
        testCreateStrategiesGroupCommand();

        Command cmd = new DeleteStrategiesGroupCommand(strategiesgroup);
        assertTrue("Can't execute DeleteStrategiesGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddUrnLinkCommand() {
        testAddIntentionalElementRefCommand();

        // Create a UCMmap with a component and link it with the element ref
        Command cmd = new CreateMapCommand(urnspec);
        UCMmap map = ((CreateMapCommand) cmd).getMap();
        assertTrue("Can't execute CreateMapCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ComponentRef compRef = (ComponentRef) ModelCreationFactory.getNewObject(urnspec, ComponentRef.class);
        cmd = new AddContainerRefCommand(map, compRef);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        urnlink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);
        cmd = new AddUrnLinkCommand(urnspec, urnlink, ref.getDef(), compRef);
        assertTrue("Can't execute AddUrnLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteURNlinkCommand() {
        testAddUrnLinkCommand();

        Command cmd = new DeleteURNlinkCommand(urnlink);
        assertTrue("Can't execute DeleteURNlinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testModifyURNlinkCommand() {
    	String oldType = "test old URN Link type"; //$NON-NLS-1$
    	String newType = "test new URN Link type"; //$NON-NLS-1$
    	
        testAddUrnLinkCommand();
        urnlink.setType(oldType);
        
        Command cmd = new ModifyUrnLinkCommand(urnlink, newType);
        assertTrue( "Can't execute ModifyURNlinkCommand.", cmd.canExecute() ); //$NON-NLS-1$
        
        cs.execute(cmd);
        assertTrue( "New URN Link type not set.", urnlink.getType().equals(newType)); //$NON-NLS-1$
        
        cs.undo();
        assertTrue( "Can't undo Modify URN Link.", urnlink.getType().equals(oldType)); //$NON-NLS-1$
        
        cs.redo();
        assertTrue( "Can't redo Modify URN Link.", urnlink.getType().equals(newType)); //$NON-NLS-1$
        
    }
    public void testMoveLinkRefBendpointCommand() {
        testAddLinkRefBendpointCommand();

        // The ElementLink should have only 1 linkref
        LinkRef linkref = (LinkRef) link.getRefs().get(0);
        // The bendpoint is the first in the linkref
        LinkRefBendpoint bendpoint = (LinkRefBendpoint) linkref.getBendpoints().get(0);

        Command cmd = new MoveLinkRefBendpointCommand(bendpoint, 100, 100);
        assertTrue("Can't execute MoveLinkRefBendpointCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testSetConstraintCommand() {
        testAddIntentionalElementRefCommand();

        Command cmd = new SetConstraintCommand(ref, 250, 54);
        assertTrue("Can't execute SetConstraintCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testSetConstraintBoundContainerRefCompoundCommand() {
        testAddContainerRefCommand();

        Command cmd = new SetConstraintBoundContainerRefCompoundCommand(actorref, 256, 543, 121, 65);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }
    
    public void testCreateGRLGraph()
    {
        
    }
    
    public void testShowLinkedElementCommand()
    {
        Command cmd = new CreateGrlGraphCommand(urnspec);
        
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef ref1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
              IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef ref2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
  
        IntentionalElementRef ref3 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref3);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef ref4 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef ref5 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph, ref5);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib1 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ref3.getDef(), contrib1);
        ((CreateElementLinkCommand) cmd).setTarget(ref4.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib2 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ref4.getDef(), contrib2);
        ((CreateElementLinkCommand) cmd).setTarget(ref5.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp1 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ref2.getDef(), decomp1);
        ((CreateElementLinkCommand) cmd).setTarget(ref1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp2 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ref3.getDef(), decomp2);
        ((CreateElementLinkCommand) cmd).setTarget(ref1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        assertTrue(graph.getNodes().size() == 5);
               
        cmd = new CreateGrlGraphCommand(urnspec);        
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        GRLGraph graph2 = (GRLGraph)(((CreateGrlGraphCommand)cmd).getDiagram());
        cmd = new AddIntentionalElementRefCommand(graph2, ref1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        cmd = new ShowLinkedElementCommand(urnspec, ref1.getDef(), ref1);
        assertTrue("Can't execute ShowLinkedElementCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 3);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 3);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        
        cmd = new ShowLinkedElementLevelTwoCommand(urnspec, ref1.getDef(), ref1);
        assertTrue("Can't execute ShowLinkedElementLevelTwoCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 4);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 4);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
     
        cmd = new ShowLinkedElementLevelThreeCommand(urnspec, ref1.getDef(), ref1);
        assertTrue("Can't execute ShowLinkedElementLevelThreeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 5);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 5);
    }

    /**
     * This method will go through all of the path nodes and component ref in all the maps and verify that they are all bound as they should be. will be usefull
     * to see if commands that create new elements bind them to their parents.
     */
    public void verifyBindings() {
        for (Iterator iter = urnspec.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof GRLGraph) {
                GRLGraph graph = (GRLGraph) g;

                for (Iterator iter2 = graph.getContRefs().iterator(); iter2.hasNext();) {
                    ActorRef actor = (ActorRef) iter2.next();
                    assertEquals("ActorRef " + actor.toString() + " is not properly bound.", ParentFinder.getPossibleParent(actor), actor.getParent()); //$NON-NLS-1$ //$NON-NLS-2$

                }
                for (Iterator iter2 = graph.getNodes().iterator(); iter2.hasNext();) {
                    GRLNode gn = (GRLNode) iter2.next();
                    assertEquals("GRLNode " + gn.toString() + " is not properly bound.", ParentFinder.getPossibleParent(gn), gn.getContRef()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
    }
}
