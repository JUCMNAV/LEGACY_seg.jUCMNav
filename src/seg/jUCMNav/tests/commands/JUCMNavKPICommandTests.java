package seg.jUCMNav.tests.commands;

import grl.Contribution;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.StrategiesGroup;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
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
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationConfigCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.commands.create.CreateIndicatorGroupCommand;
import seg.jUCMNav.model.commands.create.CreateKPIModelLinkCommand;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteIndicatorGroupCommand;
import seg.jUCMNav.model.commands.delete.DeleteKPIInformationElementCommand;
import seg.jUCMNav.model.commands.delete.DeleteKPIModelLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.kpi.AssignIndicatorGroupCommand;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * Test suite to test the KPI commands in the GRL editor
 * 
 * @author pchen
 * 
 */
public class JUCMNavKPICommandTests extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(JUCMNavKPICommandTests.class);
    }

    private UCMNavMultiPageEditor editor;
    private CommandStack cs;

    private URNspec urnspec;
    private GRLGraph graph;

    private IntentionalElementRef indRef;
    private Indicator ind;
    private IndicatorGroup indGroup;
    private KPIInformationElementRef kpiInfoElemRef;
    private KPIInformationElement kpiInfoElem;

    private KPIModelLink kpiModelLink;
    private KPIModelLinkRef kpiModelLinkRef;

    private StrategiesGroup strategiesGroup;
    private EvaluationStrategy strategy;
    private KPIInformationConfig kpiInfoConfig;

    private boolean testBindings;

    protected void setUp() throws Exception {
        super.setUp();

        testBindings = true;

        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IProject testproject = workspaceRoot.getProject("jUCMNav-KPI-tests"); //$NON-NLS-1$
        if (!testproject.exists())
            testproject.create(null);

        if (!testproject.isOpen())
            testproject.open(null);

        IFile testfile = testproject.getFile("jUCMNav-KPI-test.jucm"); //$NON-NLS-1$

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

    public void testAddIndicatorRefCommand() {
        indRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, IntentionalElementType.INDICATOR);

        Command cmd;

        cmd = new AddIntentionalElementRefCommand(graph, indRef);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        ind = (Indicator) indRef.getDef();
    }

    public void testAddKPIInformationConfigCommand() {
        testAddKPIInformationElementRefCommand();
        testCreateStrategyCommand();

        kpiInfoConfig = (KPIInformationConfig) ModelCreationFactory.getNewObject(urnspec, KPIInformationConfig.class);
        kpiInfoConfig.setLevelOfDimension("year"); //$NON-NLS-1$
        kpiInfoConfig.setValueOfDimension("2007"); //$NON-NLS-1$
        Command cmd = new AddKPIInformationConfigCommand(kpiInfoConfig, kpiInfoElem, strategy);

        assertTrue("Can't execute AddKPIInformationConfigCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAddKPIInformationElementRefCommand() {
        kpiInfoElemRef = (KPIInformationElementRef) ModelCreationFactory.getNewObject(urnspec, KPIInformationElementRef.class);

        Command cmd;

        cmd = new AddKPIInformationElementRefCommand(graph, kpiInfoElemRef);
        assertTrue("Can't execute AddKPIInformationElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        kpiInfoElem = kpiInfoElemRef.getDef();
    }

    public void testCreateAllKPIModelLinkRefCommand() {
        // Create 2 diagrams, add 3 elements with references in each them and create link between the elements
        Command cmd = new CreateGrlGraphCommand(urnspec);
        assertTrue("Can't execute CreateGrlGraphCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create 3 elements in the first graph
        IntentionalElementRef ref1 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.TASK);
        cmd = new AddIntentionalElementRefCommand(graph, ref1);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        IntentionalElementRef ref2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.INDICATOR);
        cmd = new AddIntentionalElementRefCommand(graph, ref2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        KPIInformationElementRef ref3 = (KPIInformationElementRef) ModelCreationFactory.getNewObject(urnspec, KPIInformationElementRef.class);
        cmd = new AddKPIInformationElementRefCommand(graph, ref3);
        assertTrue("Can't execute AddKPIInformationElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create links between ref1 and ref2
        Contribution contrib = (Contribution) ModelCreationFactory.getNewObject(urnspec, Contribution.class);

        cmd = new CreateElementLinkCommand(urnspec, ref2.getDef(), contrib);
        ((CreateElementLinkCommand) cmd).setTarget(ref1.getDef());
        assertTrue("Can't execute CreateElementLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create links between ref2 and ref3
        kpiModelLink = (KPIModelLink) ModelCreationFactory.getNewObject(urnspec, KPIModelLink.class);

        cmd = new CreateKPIModelLinkCommand(urnspec, ref3.getDef(), kpiModelLink);
        ((CreateKPIModelLinkCommand) cmd).setTarget(ref2.getDef());
        assertTrue("Can't execute CreateKPIModelLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Create 3 elements in the second graph
        IntentionalElementRef ref1Diag2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.TASK, ref1.getDef());
        cmd = new AddIntentionalElementRefCommand(graph, ref1Diag2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        IntentionalElementRef ref2Diag2 = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class,
                IntentionalElementType.INDICATOR, ref2.getDef());
        cmd = new AddIntentionalElementRefCommand(graph, ref2Diag2);
        assertTrue("Can't execute AddIntentionalElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        KPIInformationElementRef ref3Diag2 = (KPIInformationElementRef) ModelCreationFactory.getNewObject(urnspec, KPIInformationElementRef.class, 0, ref3
                .getDef());
        cmd = new AddKPIInformationElementRefCommand(graph, ref3Diag2);
        assertTrue("Can't execute AddKPIInformationElementRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        /*
         * 
         * // Set the element's definition to the same for both diagrams ref1Diag2.setDef(ref1.getDef()); ref2Diag2.setDef(ref2.getDef());
         * ref3Diag2.setDef(ref3.getDef());
         */

        // Execute the CreateAllLinkRef command
        cmd = new CreateAllLinkRefCommand(ref2Diag2);
        assertTrue("Can't execute CreateAllLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Execute the CreateAllKPIModelLinkRef command
        cmd = new CreateAllKPIModelLinkRefCommand(ref3Diag2);
        assertTrue("Can't execute CreateAllKPIModelLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateIndicatorGroupCommand() {
        indGroup = (IndicatorGroup) ModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indGroup.setName("groupx"); //$NON-NLS-1$

        Command cmd = new CreateIndicatorGroupCommand(urnspec, indGroup);
        assertTrue("Can't execute CreateIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateKPIModelLinkCommand() {
        testAddIndicatorRefCommand();
        testAddKPIInformationElementRefCommand();
        kpiModelLink = (KPIModelLink) ModelCreationFactory.getNewObject(urnspec, KPIModelLink.class);

        Command cmd;

        cmd = new CreateKPIModelLinkCommand(urnspec, kpiInfoElem, kpiModelLink);
        ((CreateKPIModelLinkCommand) cmd).setTarget(ind);
        assertTrue("Can't execute CreateKPIModelLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteAllKPIModelLinkRefCommand() {
        testCreateKPIModelLinkCommand();

        Command cmd = new DeleteAllKPIModelLinkRefCommand(kpiInfoElemRef);
        assertTrue("Can't execute DeleteAllKPIModelLinkRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteIndicatorGroupCommand() {
        testCreateIndicatorGroupCommand();

        Command cmd = new DeleteIndicatorGroupCommand(indGroup);
        assertTrue("Can't execute DeleteIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteKPIInformationElementCommand() {
        testCreateKPIModelLinkCommand();

        Command cmd = new DeleteKPIInformationElementCommand(kpiInfoElem);
        assertTrue("Can't execute DeleteIntentionalElementCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testDeleteKPIModelLinkCommand() {
        testCreateKPIModelLinkCommand();

        Command cmd = new DeleteKPIModelLinkCommand(kpiModelLink);
        assertTrue("Can't execute DeleteKPIModelLinkCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testAssignIndicatorGroupCommand() {
        testAddIndicatorRefCommand();

        // Create three indicator groups
        IndicatorGroup indGroup1 = (IndicatorGroup) ModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indGroup1.setName("group1"); //$NON-NLS-1$
        Command cmd = new CreateIndicatorGroupCommand(urnspec, indGroup1);
        assertTrue("Can't execute CreateIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        IndicatorGroup indGroup2 = (IndicatorGroup) ModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indGroup2.setName("group2"); //$NON-NLS-1$
        cmd = new CreateIndicatorGroupCommand(urnspec, indGroup2);
        assertTrue("Can't execute CreateIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        IndicatorGroup indGroup3 = (IndicatorGroup) ModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indGroup3.setName("group3"); //$NON-NLS-1$
        cmd = new CreateIndicatorGroupCommand(urnspec, indGroup3);
        assertTrue("Can't execute CreateIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Assigning groups to the indicator
        HashSet indGroups = new HashSet();
        indGroups.add(indGroup1);
        indGroups.add(indGroup2);
        indGroups.add(indGroup3);
        cmd = new AssignIndicatorGroupCommand(ind, indGroups);
        assertTrue("Can't execute AssignIndicatorGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
    }

    public void testCreateStrategiesGroupCommand() {
        strategiesGroup = (StrategiesGroup) ModelCreationFactory.getNewObject(urnspec, StrategiesGroup.class);

        Command cmd = new CreateStrategiesGroupCommand(urnspec, strategiesGroup);
        assertTrue("Can't execute CreateStrategiesGroupCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

    }

    public void testCreateStrategyCommand() {
        testCreateStrategiesGroupCommand();

        Command cmd = new CreateStrategyCommand(urnspec, strategiesGroup);
        strategy = ((CreateStrategyCommand) cmd).getStrategy();
        assertTrue("Can't execute CreateStrategyCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
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

                for (Iterator iter2 = graph.getNodes().iterator(); iter2.hasNext();) {
                    GRLNode gn = (GRLNode) iter2.next();
                    assertEquals("GRLNode " + gn.toString() + " is not properly bound.", ParentFinder.getPossibleParent(gn), gn.getContRef()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
    }

}
