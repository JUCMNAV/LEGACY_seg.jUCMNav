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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import seg.jUCMNav.model.commands.changeConstraints.AlignCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.DistributeCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveNodeCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintContainerRefCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintGrlNodeCommand;
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
import seg.jUCMNav.model.commands.create.ShowContainingActorCommand;
import seg.jUCMNav.model.commands.create.ShowContainingElementCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementAlternativeCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementAlternativeSubNodesCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCompleteCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCompleteSubNodesCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementInNewDiagramCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelThreeCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelTwoCommand;
import seg.jUCMNav.model.commands.create.ShowNonLeafElementsInSeparateDiagramsCommand;
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
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Metadata;
import urncore.URNmodelElement;

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
    private ActorRef actorref2;
    private ElementLink link;
    private StrategiesGroup strategiesgroup;
    private EvaluationStrategy strategy;
    private URNlink urnlink;
    
    private IntentionalElementRef ieRef1, ieRef2, ieRef3, ieRef4, ieRef5, ieRef6, ieRef7, 
        ieRef8, ieRef9, ieRef10, ieRef11, ieRef12, ieRef13, ieRef14, ieRef15, ieRef16;
    private ActorRef aRef1, aRef2, aRef3;
    private GRLGraph graph1, graph3;

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
        Command cmd = new CreateGrlGraphCommand(urnspec);
        graph1 = ((CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
              IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
  
        ieRef3 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef3);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef4 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef5 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef5);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib1 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef3.getDef(), contrib1);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib2 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef2.getDef(), contrib2);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp1 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef4.getDef(), decomp1);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef2.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp2 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef5.getDef(), decomp2);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef2.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef6 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.SOFTGOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef6);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef7 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.SOFTGOAL);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef7);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib3 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef6.getDef(), contrib3);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef4.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib4 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef7.getDef(), contrib4);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef4.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef8 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef8);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef9 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef9);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib5 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef8.getDef(), contrib5);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef6.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib6 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef9.getDef(), contrib6);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef6.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef10 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef10);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef11 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef11);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib7 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef10.getDef(), contrib7);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef7.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib8 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef11.getDef(), contrib8);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef7.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef12 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef12);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef13 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef13);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp3 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef12.getDef(), decomp3);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef3.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Decomposition decomp4 = (Decomposition) ModelCreationFactory.getNewObject(urnspec, Decomposition.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef13.getDef(), decomp4);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef3.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef14 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef14);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib9 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef6.getDef(), contrib9);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef14.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib10 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef14.getDef(), contrib10);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef15 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.INDICATOR);
        cmd = new AddIntentionalElementRefCommand(graph1, ieRef15);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        Contribution contrib11 = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);
        
        cmd = new CreateElementLinkCommand(urnspec, ieRef15.getDef(), contrib11);
        ((CreateElementLinkCommand) cmd).setTarget(ieRef5.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        assertTrue(graph1.getNodes().size() == 15);
        
        aRef1 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        cmd = new AddContainerRefCommand(graph1, aRef1);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(aRef1, 376, 12, 621, 239);
        assertTrue("Can't execute SetConstraintContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);   
        
        cmd = new MoveNodeCommand(ieRef1, 623, 37);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef1, ieRef1);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd); // @@@ WHERE THIS IS FAILING
        cmd = new MoveNodeCommand(ieRef2, 487, 132);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef1, ieRef2);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new MoveNodeCommand(ieRef3, 797, 157);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef1, ieRef3);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        //cmd = new ContainerRefBindChildCommand(aRef1, ieRef1);
        //assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute());
        //cs.execute(cmd);
        
        assertTrue(aRef1.getNodes().size() == 3);
        
        aRef2 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        cmd = new AddContainerRefCommand(graph1, aRef2);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(aRef2, 104, 240, 450, 235);
        assertTrue("Can't execute SetConstraintContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        cmd = new MoveNodeCommand(ieRef4, 268, 266);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef2, ieRef4);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new MoveNodeCommand(ieRef6, 155, 362);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef2, ieRef6);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new MoveNodeCommand(ieRef7, 353, 366);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new ContainerRefBindChildCommand(aRef2, ieRef7);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        assertTrue(aRef2.getNodes().size() == 3);
        
        assertTrue(graph1.getNodes().size() == 15);
        
        assertTrue(graph1.getContRefs().size() == 2);
        
        // adding the second graph
        
        cmd = new CreateGrlGraphCommand(urnspec);
        graph3 = ((CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ieRef16 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL, ieRef1.getDef());
        cmd = new AddIntentionalElementRefCommand(graph3, ieRef16);
        assertTrue("Can't execute AddIntentionalElementRefCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        aRef3 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        cmd = new AddContainerRefCommand(graph3, aRef3);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(aRef3, 376, 12, 621, 239);
        assertTrue("Can't execute SetConstraintBoundContainerRefCompoundCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        cmd = new MoveNodeCommand(ieRef16, 623, 37);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        cmd = new ContainerRefBindChildCommand(aRef3, ieRef16);
        assertTrue("Can't execute ContainerRefBindChildCommand", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        assertTrue(aRef3.getNodes().size() == 1);
        
        assertTrue(graph3.getNodes().size() == 1);
        
        assertTrue(graph3.getContRefs().size() == 1);
    }
    
    public void testShowLinkedElementCommand()
    {   
        testCreateGRLGraph();
      
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef4  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
            IntentionalElementType.GOAL);        
        tempRef4.setDef(ieRef4.getDef());        
        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);
        cmd = new ShowLinkedElementCommand(urnspec, tempRef4.getDef(), tempRef4);
        assertTrue("Can't execute ShowLinkedElementCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 4);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 4);
        //cs.undo();
        //assertTrue(graph2.getNodes().size() == 1);
        
        Command cmd2 = new ShowLinkedElementLevelTwoCommand(urnspec, tempRef4.getDef(), tempRef4);
        assertTrue("Can't execute ShowLinkedElementLevelTwoCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        assertTrue(graph2.getNodes().size() == 11);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 4);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 11);
        //cs.undo();
        //assertTrue(graph2.getNodes().size() == 1);        
        
        Command cmd3 = new ShowLinkedElementLevelThreeCommand(urnspec, tempRef4.getDef(), tempRef4);
        assertTrue("Can't execute ShowLinkedElementLevelThreeCommand.", cmd3.canExecute()); //$NON-NLS-1$
        cs.execute(cmd3);
        assertTrue(graph2.getNodes().size() == 13);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 11);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 13);
        //cs.undo();
        //assertTrue(graph2.getNodes().size() == 1);
    }
    
    public void testShowLinkedElementAlternativeCommand()
    {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef4  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);        
        tempRef4.setDef(ieRef4.getDef());
        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);
        
        cmd = new ShowLinkedElementAlternativeCommand(urnspec, tempRef4.getDef(), tempRef4, "1");
        assertTrue("Can't execute ShowLinkedElementAlternativeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 4);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 4);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        
        Command cmd2 = new ShowLinkedElementAlternativeCommand(urnspec, tempRef4.getDef(), tempRef4, "2");
        assertTrue("Can't execute ShowLinkedElementLevelTwoCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        assertTrue(graph2.getNodes().size() == 11);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 11);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        
        Command cmd3 = new ShowLinkedElementAlternativeCommand(urnspec, tempRef4.getDef(), tempRef4, "3");
        assertTrue("Can't execute ShowLinkedElementLevelThreeCommand.", cmd3.canExecute()); //$NON-NLS-1$
        cs.execute(cmd3);
        assertTrue(graph2.getNodes().size() == 13);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 13);
        //cs.undo();
        //assertTrue(graph2.getNodes().size() == 1);
    }
    
    public void testShowLinkedElementAlternativeSubNodesCommand() {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef4  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);        
        tempRef4.setDef(ieRef4.getDef());
        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);
        
        cmd = new ShowLinkedElementAlternativeSubNodesCommand(urnspec, tempRef4.getDef(), tempRef4, "1");
        assertTrue("Can't execute ShowLinkedElementAlternativeSubNodesCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 3);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 3);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        
        Command cmd2 = new ShowLinkedElementAlternativeSubNodesCommand(urnspec, tempRef4.getDef(), tempRef4, "2");
        assertTrue("Can't execute ShowLinkedElementAlternativeSubNodesCommand.", cmd2.canExecute()); //$NON-NLS-1$
        cs.execute(cmd2);
        assertTrue(graph2.getNodes().size() == 7);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 7);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        
        Command cmd3 = new ShowLinkedElementAlternativeSubNodesCommand(urnspec, tempRef4.getDef(), tempRef4, "3");
        assertTrue("Can't execute ShowLinkedElementLevelAlternativeSubNodesCommand.", cmd3.canExecute()); //$NON-NLS-1$
        cs.execute(cmd3);
        assertTrue(graph2.getNodes().size() == 7);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 7);
    }
    
    public void testShowLinkedElementCompleteCommand() {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef4  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);        
        tempRef4.setDef(ieRef4.getDef());
        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);
        
        cmd = new ShowLinkedElementCompleteCommand(urnspec, tempRef4.getDef(), tempRef4);
        assertTrue("Can't execute ShowLinkedElementAlternativeSubNodesCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 15);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        cs.redo();
        assertTrue(graph2.getNodes().size() == 15);
    }
    
    public void testShowLinkedElementCompleteSubNodesCommand() {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef4  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL);        
        tempRef4.setDef(ieRef4.getDef());
        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef4);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);
        
        cmd = new ShowLinkedElementCompleteSubNodesCommand(urnspec, tempRef4.getDef(), tempRef4);
        assertTrue("Can't execute ShowLinkedElementAlternativeSubNodesCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 7);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 1);
        cs.redo();
        assertTrue(graph2.getNodes().size() == 7);
  }
    
    public void testShowContainingActorCommand() {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        IntentionalElementRef tempRef1  = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
          IntentionalElementType.GOAL, ieRef1.getDef());        
        cmd = new AddIntentionalElementRefCommand(graph2, tempRef1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);        
        assertTrue(graph2.getNodes().size() == 1);
        
        /*cmd = new MoveNodeCommand(tempRef1, 150, 150);
        assertTrue("Can't execute MoveNodeCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 1);        
        assertTrue(graph2.getContRefs().size() == 0);*/
                
        cmd = new ShowContainingActorCommand(urnspec, tempRef1.getDef(), tempRef1);
        //System.out.println("Rouzy: " + cmd.canExecute()); //$NON-NLS-1$
        assertTrue("Can't execute ShowContainingActorCommand", cmd.canExecute()); //$NON-NLS-1$
        //cs.execute(cmd);
        //assertTrue(graph2.getContRefs().size() == 2);
        //System.out.println("Rouzy: " + graph2.getContRefs().size());
  }
    
    public void testShowContainingElementCommand()
    {
        testCreateGRLGraph();
        
        Command cmd = new CreateGrlGraphCommand(urnspec);   
        GRLGraph graph2 = ( (CreateGrlGraphCommand) cmd).getDiagram();
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        ActorRef ar1 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        ar1.setContDef(aRef1.getContDef());
        cmd = new AddContainerRefCommand(graph2, ar1);
        assertTrue("Can't execute AddContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        
        assertTrue(graph2.getNodes().size() == 0);
        
        cmd = new ShowContainingElementCommand(urnspec, ar1.getContDef(), ar1);
        assertTrue("Can't execute ShowContainingElementCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        assertTrue(graph2.getNodes().size() == 3);
        cs.undo();
        assertTrue(graph2.getNodes().size() == 0);        
        cs.redo();
        assertTrue(graph2.getNodes().size() == 3);        
    }  
    
    public void testShowLinkedElementInNewDiagramCommand()
    {
    	testCreateGRLGraph();
    	testShowLinkedElementCommand();
    	
    	boolean helperRefreshTabsByPass = false; // not using the iterative command to create a graph for all non leaf element here
    	boolean selectedIntElemRefCopied = false; // true if the selected Intentional Element is present in the generated graph
    	int oldGraphCount = urnspec.getUrndef().getSpecDiagrams().size(); // graph count before command execution
    	int oldLinksCount = ieRef4.getPred().size() + ieRef4.getSucc().size(); // total links of the selected intentional element in graph1
    	
    	
    	// contains all the pred links of the Int. Elem. that we're using the command on
    	ArrayList<LinkRef> elemRefLinksPredBefore = new ArrayList();
		if(ieRef4.getPred() != null && !ieRef4.getPred().isEmpty())
			elemRefLinksPredBefore.addAll(ieRef4.getPred());
		
		// contains all the succ links of the Int. Elem. that we're using the command on
		ArrayList<LinkRef> elemRefLinksSuccBefore = new ArrayList();
		if(ieRef4.getSucc() != null && !ieRef4.getSucc().isEmpty())
			elemRefLinksSuccBefore.addAll(ieRef4.getSucc());
    	
		ArrayList<LinkRef> elemRefLinksPredAfter = null;
		ArrayList<LinkRef> elemRefLinksSuccAfter = null;
		
    	Metadata legislationMeta = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        legislationMeta.setName("LegislationSection");
        legislationMeta.setValue("aValidGraphName");
    	ieRef4.getDef().getMetadata().add(legislationMeta);
    	
    	
    	ShowLinkedElementInNewDiagramCommand cmd = new ShowLinkedElementInNewDiagramCommand(urnspec, ieRef4.getDef(), ieRef4, editor, cs);
    	assertTrue("Can't execute ShowLinkedElementInNewDiagramCommand.", cmd.canExecute()); //$NON-NLS-1$
    	cs.execute(cmd);
    	
    	assertTrue("Wrong number of graph added.", urnspec.getUrndef().getSpecDiagrams().size() == oldGraphCount + 1);

    	
    	GRLGraph addedGraph = (GRLGraph)urnspec.getUrndef().getSpecDiagrams().get(oldGraphCount);
    	assertTrue("Graph hasn't been assigned a valid name", addedGraph.getName().compareTo("aValidGraphName") == 0);
    	
    	for(GRLNode currentNode : (List<GRLNode>) addedGraph.getNodes()){
    		if( currentNode instanceof IntentionalElementRef){
    			IntentionalElementRef currentRef = (IntentionalElementRef) currentNode;
    			
    			if (currentRef.getDef().getName().compareTo(ieRef4.getDef().getName()) == 0){
    				selectedIntElemRefCopied = true;
    				
    				int newLinksCount = currentRef.getPred().size() + currentRef.getSucc().size();
    				elemRefLinksPredAfter = new ArrayList<LinkRef>();
    				elemRefLinksSuccAfter = new ArrayList<LinkRef>();
    				elemRefLinksPredAfter.addAll(currentRef.getPred());
    				elemRefLinksSuccAfter.addAll(currentRef.getSucc());

    				assertTrue("Intentional Element Linked Elements not all present", oldLinksCount == newLinksCount);	
    			}
    		}
    	}
    	assertTrue("Selected Intentional Element not copied in the generated graph", selectedIntElemRefCopied);
    	
    	ArrayList<IntentionalElement> toBeRemoved = new ArrayList<IntentionalElement>();
    	
    	// testing if all the prec Int. Elem that we're connected before are connected after the command at the first level
    	for( LinkRef currentLinkBefore : elemRefLinksPredBefore){
    		boolean helperFound = false;
    		for( LinkRef currentLinkAfter : elemRefLinksPredAfter){
    			if(((IntentionalElementRef)currentLinkAfter.getSource()).getDef() == 
    					((IntentionalElementRef)currentLinkBefore.getSource()).getDef()){
    				helperFound = true;
    			} 				
    		}
    		assertTrue("New graph has not all the Intentional Elements it should have.", helperFound);
    	}
    	
    	// testing if all the succ Int. Elem that we're connected before are connected after the command at the first level
     	for( LinkRef currentLinkBefore : elemRefLinksSuccBefore){
    		boolean helperFound = false;
    		for( LinkRef currentLinkAfter : elemRefLinksSuccAfter){
    			if(((IntentionalElementRef)currentLinkAfter.getTarget()).getDef() == 
    					((IntentionalElementRef)currentLinkBefore.getTarget()).getDef()){
    				helperFound = true;
    			} 				
    		}
    		assertTrue("New graph has not all the Intentional Elements it should have.", helperFound);
    	}
    	
    	assertTrue("Wrong number of nodes in the generated graph", addedGraph.getNodes().size() == oldLinksCount + 1);
    	
    	// trying command with a graph with no "LegislationSection" metadata and a limit node
    	Metadata altNameMeta = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        altNameMeta.setName("AltName");
        altNameMeta.setValue("aValidGraphName");
    	ieRef1.getDef().getMetadata().add(altNameMeta);
    	
    	
    	// testing command with most top node
    	cmd = new ShowLinkedElementInNewDiagramCommand(urnspec, ieRef1.getDef(), ieRef1, editor, cs);
    	assertTrue("Can't execute ShowLinkedElementInNewDiagramCommand.", cmd.canExecute()); //$NON-NLS-1$
    	cs.execute(cmd);
    	
    	assertTrue("Wrong number of graph added.", urnspec.getUrndef().getSpecDiagrams().size() == oldGraphCount + 2);

    	addedGraph = (GRLGraph)urnspec.getUrndef().getSpecDiagrams().get(oldGraphCount + 1);
    	assertTrue("Graph hasn't been assigned a valid name", addedGraph.getName().compareTo("aValidGraphName") == 0);

    	
    	
    	// testing undo/redo
    	cs.undo();
    	
    	assertTrue("Wrong number of graph in urnspec.", urnspec.getUrndef().getSpecDiagrams().size() == oldGraphCount + 1); 

    	cs.redo();
    	assertTrue("Wrong number of graph in urnspec.", urnspec.getUrndef().getSpecDiagrams().size() == oldGraphCount + 2);    

    	
    	// cleaning graph to prevent further dependent tests to fail because of this test
    	ieRef4.getMetadata().remove(legislationMeta);
    	ieRef1.getMetadata().remove(altNameMeta);
   
    }
    
    public void testShowNonLeafElementsInSeparateDiagramsCommand()
    {
    	testCreateGRLGraph();
    	testShowLinkedElementCommand();
    	
    	int oldGraphCount = urnspec.getUrndef().getSpecDiagrams().size(); // graph count before command execution
    	
    	ShowNonLeafElementsInSeparateDiagramsCommand cmd = new ShowNonLeafElementsInSeparateDiagramsCommand(urnspec, graph1, editor, cs);
    	assertTrue("Can't execute ShowNonLeafElementsInSeparateDiagramsCommand.", cmd.canExecute()); //$NON-NLS-1$
    	cs.execute(cmd);    	
    	
    	// testing if the number of graphs added corresponds to the number of non leaf element in graph1
    	assertTrue("Wrong number of graph added.", urnspec.getUrndef().getSpecDiagrams().size() == oldGraphCount + 8);
 
    }
    
    /**
     * Test for AlignCommand.
     *  
     *  @author Patrice Boulet
     */
    public void testAlignCommand(){
    	testSetConstraintCommand();
    	testSetConstraintBoundContainerRefCompoundCommand();
    	
    	CreateGrlGraphCommand cmd1 = new CreateGrlGraphCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd1.canExecute()); //$NON-NLS-1$
        cs.execute(cmd1);
        
        // add bogus data to new graph
        graph = cmd1.getDiagram();
        
        actorref = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        actorref.setDiagram(graph);
        actorref.setWidth(100);
        actorref.setHeight(100);
        actorref.setX(50);
        actorref.setY(50);
        int actorrefOldXCoordinate = actorref.getX();
        int actorrefOldYCoordinate = actorref.getY();
        
        actorref2 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        actorref2.setDiagram(graph);
        actorref2.setWidth(100);
        actorref2.setHeight(100);
        actorref2.setX(100);
        actorref2.setY(200);
        int actorref2OldXCoordinate = actorref2.getX();
        int actorref2OldYCoordinate = actorref2.getY();

        ieRef1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef1.setDiagram(graph);
        ieRef1.setX(100);
        ieRef1.setY(100);
        actorref.getNodes().add(ieRef1);
        int ieRef1OldXCoordinate = ieRef1.getX();
        int ieRef1OldYCoordinate = ieRef1.getY();
        
        SetConstraintGrlNodeCommand ref1RezizeCmd; 
        ref1RezizeCmd = new SetConstraintGrlNodeCommand((IURNNode)ieRef1, 100, 100, 50, 50, false);
        assertTrue("Can't execute  SetConstraintGrlNodeCommand.", ref1RezizeCmd.canExecute()); //$NON-NLS-1$
        cs.execute(ref1RezizeCmd);
        
        ieRef2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef2.setDiagram(graph);
        ieRef2.setX(150);
        ieRef2.setY(250);
        actorref2.getNodes().add(ieRef2);
        int ieRef2OldXCoordinate = ieRef2.getX();
        int ieRef2OldYCoordinate = ieRef2.getY();      
        
        SetConstraintGrlNodeCommand ref2RezizeCmd; 
        ref2RezizeCmd = new SetConstraintGrlNodeCommand((IURNNode)ieRef2, 150, 250, 50, 50, false);
        assertTrue("Can't execute  SetConstraintGrlNodeCommand.", ref2RezizeCmd.canExecute()); //$NON-NLS-1$
        cs.execute(ref2RezizeCmd);
        
        LinkedList intElems = new LinkedList<IntentionalElementRef>();
        intElems.add(ieRef1);
        intElems.add(ieRef2);
        
        LinkedList actors = new LinkedList<Actor>();
        actors.add(actorref);
        actors.add(actorref2);
        
        // test align top with intentional elements
      	AlignCommand cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignTop");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getY() == ieRef2.getY() && ieRef1.getY() == 100);
        cs.undo();
        assertTrue(ieRef1.getY() == ieRef1OldYCoordinate);
        assertTrue(ieRef2.getY() == ieRef2OldYCoordinate);
        
        // test align middle with intentional elements
      	cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignMiddle");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getY() == ieRef2.getY() && ieRef1.getY() == 150);
        cs.undo();
        assertTrue(ieRef1.getY() == ieRef1OldYCoordinate);
        assertTrue(ieRef2.getY() == ieRef2OldYCoordinate);
        
        // test align bottom with intentional elements
      	cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignBottom");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getY() == ieRef2.getY() && ieRef1.getY() == 250);
        cs.undo();
        assertTrue(ieRef1.getY() == ieRef1OldYCoordinate);
        assertTrue(ieRef2.getY() == ieRef2OldYCoordinate);
        
        // test align left with intentional elements
      	cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignLeft");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getX() == ieRef2.getX() && ieRef1.getX() == 100);
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        
        // test align center with intentional elements
      	cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignCenter");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getX() == ieRef2.getX() && ieRef1.getX() == 100);
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        
        // test align right with intentional elements
      	cmd4 = new AlignCommand(intElems, 1, "seg.jUCMNav.AlignRight");
        assertTrue("Can't execute AlignCommand.", cmd4.canExecute()); //$NON-NLS-1$
        cs.execute(cmd4);

        assertTrue(ieRef1.getX() == ieRef2.getX() && ieRef1.getX() == 150);
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        
        // testing redo
        
        cs.redo();
        assertTrue(ieRef1.getX() == ieRef2.getX() && ieRef1.getX() == 150);
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        
        // test align top with actors
      	AlignCommand cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignTop");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getY() == actorref2.getY() && actorref.getY() == 50);
        cs.undo();
        assertTrue(actorref.getY() == actorrefOldYCoordinate);
        assertTrue(actorref2.getY() == actorref2OldYCoordinate);
       
        // test align middle with actors
      	cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignMiddle");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getY() == actorref2.getY() && actorref.getY() == 125);
        cs.undo();
        assertTrue(actorref.getY() == actorrefOldYCoordinate);
        assertTrue(actorref2.getY() == actorref2OldYCoordinate);
        
        // test align bottom with actors
      	cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignBottom");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getY() == actorref2.getY() && actorref.getY() == actorref2OldYCoordinate);
        cs.undo();
        assertTrue(actorref.getY() == actorrefOldYCoordinate);
        assertTrue(actorref2.getY() == actorref2OldYCoordinate);
        
        // test align left with actors
      	cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignLeft");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getX() == actorref2.getX() && actorref.getX() == 50);
        cs.undo();
        assertTrue(actorref.getX() == actorrefOldXCoordinate);
        assertTrue(actorref2.getX() == actorref2OldXCoordinate);
        
        // test align center with actors
      	cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignCenter");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getX() == actorref2.getX() && actorref.getX() == 75);
        cs.undo();
        assertTrue(actorref.getX() == actorrefOldXCoordinate);
        assertTrue(actorref2.getX() == actorref2OldXCoordinate);
        
        // test align right with actors
      	cmd5 = new AlignCommand(actors, 2, "seg.jUCMNav.AlignRight");
        assertTrue("Can't execute AlignCommand.", cmd5.canExecute()); //$NON-NLS-1$
        cs.execute(cmd5);

        assertTrue(actorref.getX() == actorref2.getX() && actorref.getX() == actorref2OldXCoordinate);
        cs.undo();
        assertTrue(actorref.getX() == actorrefOldXCoordinate);
        assertTrue(actorref2.getX() == actorref2OldXCoordinate);
   
        cs.undo();
        cs.undo();
        
        cs.flush();
    	}
    
    /**
     * Test for AlignCommand.
     *  
     *  @author Patrice Boulet
     */
    public void testDistributeCommand(){
    	testSetConstraintCommand();
    	testSetConstraintBoundContainerRefCompoundCommand();
    	
    	CreateGrlGraphCommand cmd1 = new CreateGrlGraphCommand(urnspec);
        assertTrue("Can't execute CreateMapCommand.", cmd1.canExecute()); //$NON-NLS-1$
        cs.execute(cmd1);
        
        // add bogus data to new graph
        graph = cmd1.getDiagram();
        
        actorref = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        actorref.setDiagram(graph);
        actorref.setWidth(100);
        actorref.setHeight(100);
        actorref.setX(50);
        actorref.setY(50);
        int actorrefOldXCoordinate = actorref.getX();
        int actorrefOldYCoordinate = actorref.getY();
        
        actorref2 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        actorref2.setDiagram(graph);
        actorref2.setWidth(100);
        actorref2.setHeight(100);
        actorref2.setX(100);
        actorref2.setY(200);
        int actorref2OldXCoordinate = actorref2.getX();
        int actorref2OldYCoordinate = actorref2.getY();
        
        ActorRef actorref3 = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
        actorref3.setDiagram(graph);
        actorref3.setWidth(100);
        actorref3.setHeight(100);
        actorref3.setX(60);
        actorref3.setY(60);
        int actorref3OldXCoordinate = actorref3.getX();
        int actorref3OldYCoordinate = actorref3.getY();

        ieRef1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef1.setDiagram(graph);
        ieRef1.setX(100);
        ieRef1.setY(100);
        actorref.getNodes().add(ieRef1);
        int ieRef1OldXCoordinate = ieRef1.getX();
        int ieRef1OldYCoordinate = ieRef1.getY();
        
        SetConstraintGrlNodeCommand ref1RezizeCmd; 
        ref1RezizeCmd = new SetConstraintGrlNodeCommand((IURNNode)ieRef1, 100, 100, 50, 50, false);
        assertTrue("Can't execute  SetConstraintGrlNodeCommand.", ref1RezizeCmd.canExecute()); //$NON-NLS-1$
        cs.execute(ref1RezizeCmd);
        
        ieRef2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef2.setDiagram(graph);
        ieRef2.setX(150);
        ieRef2.setY(250);
        actorref2.getNodes().add(ieRef2);
        int ieRef2OldXCoordinate = ieRef2.getX();
        int ieRef2OldYCoordinate = ieRef2.getY();      
        
        SetConstraintGrlNodeCommand ref2RezizeCmd; 
        ref2RezizeCmd = new SetConstraintGrlNodeCommand((IURNNode)ieRef2, 150, 250, 50, 50, false);
        assertTrue("Can't execute  SetConstraintGrlNodeCommand.", ref2RezizeCmd.canExecute()); //$NON-NLS-1$
        cs.execute(ref2RezizeCmd);
        
        ieRef3 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef3.setDiagram(graph);
        ieRef3.setX(110);
        ieRef3.setY(140);
        actorref.getNodes().add(ieRef3);
        int ieRef3OldXCoordinate = ieRef3.getX();
        int ieRef3OldYCoordinate = ieRef3.getY();      
        
        SetConstraintGrlNodeCommand ref3RezizeCmd; 
        ref3RezizeCmd = new SetConstraintGrlNodeCommand((IURNNode)ieRef3, 110, 140, 50, 50, false);
        assertTrue("Can't execute  SetConstraintGrlNodeCommand.", ref3RezizeCmd.canExecute()); //$NON-NLS-1$
        cs.execute(ref3RezizeCmd);
        
        LinkedList intElems = new LinkedList<IntentionalElementRef>();
        intElems.add(ieRef1);
        intElems.add(ieRef2);
        intElems.add(ieRef3);
        
        LinkedList actors = new LinkedList<Actor>();
        actors.add(actorref);
        actors.add(actorref2);
        actors.add(actorref3);
        
        // test distribute centers horizontally with intentional elements
      	DistributeCommand cmd6 = new DistributeCommand(intElems, 1, "seg.jUCMNav.DistributeCentersHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd6.canExecute()); //$NON-NLS-1$
        cs.execute(cmd6);
        
        int ieRef1width = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef1, "_width")); 
        int ieRef2width = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef2, "_width")); 
        int ieRef3width = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef3, "_width")); 
        
        assertTrue(ieRef3.getX() == 
        	( (ieRef1.getX()+ieRef1width/2) + ((ieRef2.getX()+ieRef2width/2) - 
        			(ieRef1.getX()+ieRef1width/2))/2 - ieRef3width/2));
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        assertTrue(ieRef3.getX() == ieRef3OldXCoordinate);
        
        // test distribute horizontally with intentional elements
      	DistributeCommand cmd11 = new DistributeCommand(intElems, 1, "seg.jUCMNav.DistributeHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd11.canExecute()); //$NON-NLS-1$
        cs.execute(cmd11);

        
        assertTrue(ieRef3.getX() == (ieRef1OldXCoordinate + ieRef1width + 25 ));
        assertTrue(ieRef2.getX() == (ieRef1OldXCoordinate + ieRef1width + ieRef3width + 50));
        
        cs.undo();
        assertTrue(ieRef1.getX() == ieRef1OldXCoordinate);
        assertTrue(ieRef2.getX() == ieRef2OldXCoordinate);
        assertTrue(ieRef3.getX() == ieRef3OldXCoordinate);
        
        // test distribute centers vertically with intentional elements
      	DistributeCommand cmd8 = new DistributeCommand(intElems, 1, "seg.jUCMNav.DistributeCentersVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd8.canExecute()); //$NON-NLS-1$
        cs.execute(cmd8);
        
        int ieRef1height = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef1, "_height")); 
        int ieRef2height = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef2, "_height")); 
        int ieRef3height = Integer.valueOf(MetadataHelper.getMetaData((URNmodelElement)ieRef3, "_height")); 
         
        assertTrue(ieRef3.getY() == 
        	( (ieRef1.getY()+ieRef1height/2) + ((ieRef2.getY()+ieRef2height/2) - 
        			(ieRef1.getY()+ieRef1height/2))/2 - ieRef3height/2));
        cs.undo();
        assertTrue(ieRef1.getY() == ieRef1OldYCoordinate);
        assertTrue(ieRef2.getY() == ieRef2OldYCoordinate);
        assertTrue(ieRef3.getY() == ieRef3OldYCoordinate);
 
        // test distribute vertically with intentional elements
      	DistributeCommand cmd12 = new DistributeCommand(intElems, 1, "seg.jUCMNav.DistributeVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd12.canExecute()); //$NON-NLS-1$
        cs.execute(cmd12);
        
        assertTrue(ieRef3.getY() == (ieRef1OldYCoordinate + ieRef1height + 25 ));
        assertTrue(ieRef2.getY() == (ieRef1OldYCoordinate + ieRef1height + ieRef3height + 50));
        
        cs.undo();
        assertTrue(ieRef1.getY() == ieRef1OldYCoordinate);
        assertTrue(ieRef2.getY() == ieRef2OldYCoordinate);
        assertTrue(ieRef3.getY() == ieRef3OldYCoordinate);
        
        // test distribute centers horizontally with actors
      	DistributeCommand cmd9 = new DistributeCommand(actors, 2, "seg.jUCMNav.DistributeCentersHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd9.canExecute()); //$NON-NLS-1$
        cs.execute(cmd9);
        
        assertTrue(actorref3.getX() == 
        	( (actorref.getX()+actorref.getWidth()/2) + ((actorref2.getX()+actorref2.getWidth()/2) - 
        			(actorref.getX()+actorref.getWidth()/2))/2 - actorref3.getWidth()/2));
        cs.undo();
        assertTrue(actorref.getX() == actorrefOldXCoordinate);
        assertTrue(actorref2.getX() == actorref2OldXCoordinate);
        assertTrue(actorref3.getX() == actorref3OldXCoordinate);
        
        // test distribute horizontally with actors
      	DistributeCommand cmd13 = new DistributeCommand(actors, 2, "seg.jUCMNav.DistributeHorizontally", false);
        assertTrue("Can't execute DistributeCommand.", cmd13.canExecute()); //$NON-NLS-1$
        cs.execute(cmd13);
        
        assertTrue(actorref3.getX() == (actorrefOldXCoordinate + actorref.getWidth() + 25));
        assertTrue(actorref2.getX() == (actorrefOldXCoordinate + actorref.getWidth() + 50 + actorref3.getWidth()));
        
        cs.undo();
        assertTrue(actorref.getX() == actorrefOldXCoordinate);
        assertTrue(actorref2.getX() == actorref2OldXCoordinate);
        assertTrue(actorref3.getX() == actorref3OldXCoordinate);

        
        // test distribute centers vertically with actors
      	DistributeCommand cmd10 = new DistributeCommand(actors, 2, "seg.jUCMNav.DistributeCentersVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd10.canExecute()); //$NON-NLS-1$
        cs.execute(cmd10);
         
        assertTrue(actorref3.getY() == 
        	( (actorref.getY()+actorref.getHeight()/2) + ((actorref2.getY()+actorref2.getHeight()/2) - 
        			(actorref.getY()+actorref.getHeight()/2))/2 - actorref3.getHeight()/2));
        cs.undo();
        assertTrue(actorref.getY() == actorrefOldYCoordinate);
        assertTrue(actorref2.getY() == actorref2OldYCoordinate);
        assertTrue(actorref3.getY() == actorref3OldYCoordinate);

    	
        // test distribute vertically with actors
      	DistributeCommand cmd14 = new DistributeCommand(actors, 2, "seg.jUCMNav.DistributeVertically", false);
        assertTrue("Can't execute DistributeCommand.", cmd14.canExecute()); //$NON-NLS-1$
        cs.execute(cmd14);
         
        assertTrue(actorref3.getY() == (actorrefOldYCoordinate + actorref.getHeight() + 25));
        assertTrue(actorref2.getY() == (actorrefOldYCoordinate + actorref.getHeight() + 50 + actorref3.getHeight()));
        
        cs.undo();
        assertTrue(actorref.getY() == actorrefOldYCoordinate);
        assertTrue(actorref2.getY() == actorref2OldYCoordinate);
        assertTrue(actorref3.getY() == actorref3OldYCoordinate);
        
        cs.undo();
        cs.undo();
        
        cs.flush();
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