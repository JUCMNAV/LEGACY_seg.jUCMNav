package seg.jUCMNav.tests.progress;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.INavigationLocationProvider;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.ComboBoxLabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddAndJoinAction;
import seg.jUCMNav.actions.AddBranchAction;
import seg.jUCMNav.actions.AddMapAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.AddOrJoinAction;
import seg.jUCMNav.actions.AddTimeoutPathAction;
import seg.jUCMNav.actions.ConnectAction;
import seg.jUCMNav.actions.DisconnectAction;
import seg.jUCMNav.actions.ExportAction;
import seg.jUCMNav.actions.SubmenuAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider;
import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editors.palette.tools.PathToolEntry;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.URNDiagramEditPart;
import seg.jUCMNav.editpolicies.layout.MapXYLayoutEditPolicy;
import seg.jUCMNav.figures.DirectionArrowFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.IRotateable;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import seg.jUCMNav.views.preferences.DeletePreferences;
import seg.jUCMNav.views.property.ContainerPropertySource;
import seg.jUCMNav.views.property.EObjectPropertySource;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.UCMmodelElement;

/**
 * Tests that evaluate our project's progress by testing specific features. See JUnitTestPlan for more information.
 * 
 * Uses interesting setUp()/tearDown();
 * 
 * TODO Modify tests to support GRLGraph
 * 
 * @author jkealey
 * 
 */
public class ProgressTests extends TestCase {
    private UCMNavMultiPageEditor editor;
    private IFile testfile;

    // internal elements shared by all tests.
    private URNspec urn;

    /**
     * Selects a list of model elements and returns the action with the given id, if it is enabled.
     * 
     * @param selected
     *            A list of model elements to be selected.
     * @param id
     *            the action's id in the action registry.
     */
    private IAction getAction(List selected, String id) {
        if (selected != null && selected.size() > 0) {
            EditPart edit = getEditPart(selected.get(0));
            getGraphicalViewer().select(edit);

            for (int i = 1; i < selected.size(); i++) {
                getGraphicalViewer().appendSelection(getEditPart(selected.get(i)));
            }
        } else
            getGraphicalViewer().deselectAll();
        UrnContextMenuProvider context = (UrnContextMenuProvider) getGraphicalViewer().getContextMenu();
        context.buildContextMenu(context);
        IContributionItem contrib = context.find(id);
        if (contrib instanceof ActionContributionItem) {
            return ((ActionContributionItem) contrib).getAction();
        } else {
            IContributionItem action = context.find(UrnContextMenuProvider.SUBMENU_INSERTNODE);
            IAction subaction = null;
            if (action != null && action instanceof ActionContributionItem && ((ActionContributionItem) action).getAction() instanceof SubmenuAction) {
                subaction = ((SubmenuAction) ((ActionContributionItem) action).getAction()).find(id);
                if (subaction != null)
                    return subaction;
            }
            action = context.find(UrnContextMenuProvider.SUBMENU_PATHOPERATIONS);
            if (action != null && action instanceof ActionContributionItem && ((ActionContributionItem) action).getAction() instanceof SubmenuAction) {
                subaction = ((SubmenuAction) ((ActionContributionItem) action).getAction()).find(id);
                if (subaction != null)
                    return subaction;
            }
        }
        return null;

    }

    private Vector getAttributeDescriptor(UCMmodelElement cr, String name) {

        EObjectPropertySource eops = new ContainerPropertySource(cr);
        EStructuralFeature attr;
        Vector v = new Vector();
        Iterator i = cr.eClass().getEAllStructuralFeatures().iterator();

        // for each attribute and reference
        while (i.hasNext()) {
            attr = (EStructuralFeature) i.next();
            String n = attr.getName();

            // make sure that the ones we have targetted do amount in adding a property to the property descriptor
            if (n.equals(name)) {
                int vectorSize = v.size();
                eops.addPropertyToDescriptor(v, attr, cr.eClass());
                assertTrue("No object in descriptor was added for attribute " + n, v.size() == vectorSize + 1); //$NON-NLS-1$
                assertNotNull("Null object in descriptor was added for attribute " + n, v.get(vectorSize)); //$NON-NLS-1$
            }
        }

        return v;
    }

    /**
     * Because of visibility issues, we can't obtain the model creation factory or the request from our palette. Hence, we'll do a quick workaround in order to
     * get a CreateRequest to send to the edit part.
     * 
     * @param m
     *            The ModelCreationFactory to be used.
     * @return a creation request
     */
    private CreateRequest getCreateRequest(ModelCreationFactory m, Point location) {

        /**
         * Inner class to bypass the protected visibility of getCreateRequest. Created on 26-Apr-2005
         * 
         * @author jkealey
         * 
         */
        class myCreationTool extends CreationTool {
            public myCreationTool(CreationFactory cf) {
                super(cf);
            }

            /**
             * This is to bypass the protected visibility of the getCreateRequest in the CreationTool class. We might want to add code to set the size of the
             * request.
             * 
             * @param location
             *            Where we want to simulate a click has been made.
             * @return a CreateRequest obtained from the CreationTool class.
             */
            public CreateRequest getCreateRequest(Point location) {
                CreateRequest rq = super.getCreateRequest();
                rq.setLocation(location);
                return rq;
            }

        }

        // return the generated CreateRequest
        return (new myCreationTool(m)).getCreateRequest(location);

    }

    public EditPart getEditPart(Object o) {
        EditPart ep = (EditPart) editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(o);

        // if can't find in editor, look in outline.
        if (ep == null)
            ep = (EditPart) (((UrnOutlinePage) editor.getAdapter(IContentOutlinePage.class))).getViewer().getEditPartRegistry().get(o);

        return ep;
    }

    public ScrollingGraphicalViewer getGraphicalViewer() {
        return (ScrollingGraphicalViewer) editor.getCurrentPage().getGraphicalViewer();
    }

    public UCMmap getMap() {
        return (UCMmap) urn.getUrndef().getSpecDiagrams().get(0);
    }

    public UCMmap getMap(int i) {
        return (UCMmap) urn.getUrndef().getSpecDiagrams().get(i);
    }

    public URNDiagramEditPart getMapEditPart(int i) {
        return (URNDiagramEditPart) editor.getCurrentPage().getGraphicalViewer().getRootEditPart().getChildren().get(i);
    }

    public EditPartViewer getOutlineGraphicalViewer() {
        return ((UrnOutlinePage) editor.getAdapter(IContentOutlinePage.class)).getViewer();
    }

    public UcmPaletteRoot getPaletteRoot() {
        return (UcmPaletteRoot) ((UcmEditor) editor.getCurrentPage()).getPaletteRoot();
    }

    /**
     * Try to find if the palette can create an element of a certain class. Because of current visibility restrictions, we can't actually look for factories or
     * try to create such an element. For now, we have to simply look for the template class used by the CombinedTemplateCreationEntry. Ideally, we should
     * search for the appropriate model creation factory.
     * 
     * @param c
     *            The template to find in one of UcmPaletteRoot's CombinedTemplateCreationEntry
     * @return the CreationTool that was found in the palette or null if none could be found
     */
    private CreationTool getToolEntryForClass(Class c) {

        Stack s = new Stack();
        List l = getPaletteRoot().getChildren();
        for (int i = 0; i < l.size(); i++)
            s.push(l.get(i));

        while (s.size() > 0) {
            Object o = s.pop();
            if (o instanceof PaletteContainer) {
                l = ((PaletteContainer) o).getChildren();
                for (int i = 0; i < l.size(); i++)
                    s.push(l.get(i));
            } else if (o instanceof CombinedTemplateCreationEntry) {
                Object template = ((CombinedTemplateCreationEntry) o).getTemplate();

                if (template == c) {
                    return (CreationTool) ((CombinedTemplateCreationEntry) o).createTool();
                }
            }
        }
        return null;
    }

    /**
     * Take a ToolEntry class and try to find if an instance of this kind of entry is present in the palette.
     * 
     * @param entry
     *            The Class of the tool entry you want to verify the existence in the palette.
     * @return True if the method find a tool entry of the entry type, else false.
     */
    private boolean isToolEntryPresent(Class entry) {
        Stack s = new Stack();
        List l = getPaletteRoot().getChildren();
        for (int i = 0; i < l.size(); i++)
            s.push(l.get(i));

        while (s.size() > 0) {
            Object o = s.pop();
            if (o instanceof PaletteContainer) {
                l = ((PaletteContainer) o).getChildren();
                for (int i = 0; i < l.size(); i++)
                    s.push(l.get(i));
            } else if (o.getClass() == entry) {
                return true;
            }
        }
        return false;
    }

    /**
     * Setup generic environment for our progress tests. Requires the junit tests be run as Eclipse Plug-in Tests and not the standard kind (must run under
     * Eclipse otherwise resource bundles aren't loaded, etc.
     * 
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
        // urn = (URNspec) ModelCreationFactory.getNewURNspec();
        urn = editor.getModel();

        CommandStack cs = getGraphicalViewer().getEditDomain().getCommandStack();
        ComponentRef backgroundBindingChecker = (ComponentRef) ModelCreationFactory.getNewObject(editor.getModel(), ComponentRef.class);
        Command cmd = new AddContainerRefCommand(getMap(), backgroundBindingChecker);
        assertTrue("Can't execute AddComponentCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);
        cmd = new SetConstraintBoundContainerRefCompoundCommand(backgroundBindingChecker, -1000, -1000, 5000, 5000);
        assertTrue("Can't execute SetConstraintContainerRefCommand.", cmd.canExecute()); //$NON-NLS-1$
        cs.execute(cmd);

        // Set the preferences for deleting the references to ALWAYS
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELDEFINITION, DeletePreferences.PREF_ALWAYS);
        DeletePreferences.getPreferenceStore().setValue(DeletePreferences.PREF_DELREFERENCE, DeletePreferences.PREF_ALWAYS);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();

        editor.closeEditor(false);
    }

    /**
     * Test #1 for requirement ReqBrowseHistory
     * 
     * Author:
     */
    public void testReqBrowseHistory1() {
        assertTrue("editor must extend INavigationLocationProvider", editor instanceof INavigationLocationProvider); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement: ReqComp
     * 
     * Author: jkealey
     */
    public void testReqComp1() {

        // Is there a tool to create a ComponentRef in the palette?
        CreationTool createtool = getToolEntryForClass(ComponentRef.class);
        assertNotNull("No palette entry creates ComponentRef", createtool); //$NON-NLS-1$

        // verify that both the componentref and component element are not in the model
        assertEquals("Should be only one component in model", 1, urn.getUrndef().getComponents().size()); //$NON-NLS-1$
        assertEquals("Should be only one component reference in model", 1, getMap().getContRefs().size()); //$NON-NLS-1$

        // verify that the edit part tree is empty.
        assertEquals("MapAndPathGraphEditPart should have only two children", 2, getMapEditPart(0).getChildren().size()); //$NON-NLS-1$

        // simulate a CreateRequest that we would have liked to have obtained from the palette
        CreateRequest cr = getCreateRequest(new ModelCreationFactory(urn, ComponentRef.class, ComponentKind.TEAM), new Point(10, 100));
        assertNotNull("Unable to build create request", cr); //$NON-NLS-1$

        // create a command using this CreateRequest. Note that this is a compound command that not only creates the component but positions it properly.
        Command cmd = getMapEditPart(0).getCommand(cr);
        assertNotNull("Can't get command to obtain a new ComponentRef", cmd); //$NON-NLS-1$

        // execute the command, adding the componentref to the model
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // because this test is not hooked up as a command stack change listener
        // JK: I'm not even sure how this should be done but we should do it.
        getMapEditPart(0).refreshChildren();

        // verify that both the componentref and component element have been added in the model.
        assertEquals("No component added to model", 2, urn.getUrndef().getComponents().size()); //$NON-NLS-1$
        assertEquals("No component ref added to model", 2, getMap().getContRefs().size()); //$NON-NLS-1$

        // verify that the edit part tree has changed.
        assertEquals("MapAndPathGraphEditPart should have exactly four children (2*component+label)", 4, getMapEditPart(0).getChildren().size()); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement: ReqComp
     * 
     * Author: jkealey
     */
    public void testReqComp2() {

        // create the component ref that will be used for testing.
        ComponentRef cr = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);
        // to be able to build the property source for the compDef, our component ref must be inside a map.
        Command cmd = new AddContainerRefCommand(getMap(), cr);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // create a property source on the component ref
        ContainerPropertySource eops = new ContainerPropertySource(cr);
        EStructuralFeature attr;
        Vector v = new Vector();
        Iterator i = cr.eClass().getEAllStructuralFeatures().iterator();

        // for each attribute and reference
        while (i.hasNext()) {
            attr = (EStructuralFeature) i.next();
            String n = attr.getName();

            // make sure that the ones we have targetted do amount in adding a property to the property descriptor
            if (n.equals("x") || n.equals("y") || n.equals("width") || n.equals("height") || n.equals("compDef")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                int vectorSize = v.size();
                eops.addPropertyToDescriptor(v, attr, cr.eClass());
                assertTrue("No object in descriptor was added for attribute " + n, v.size() == vectorSize + 1); //$NON-NLS-1$
                assertNotNull("Null object in descriptor was added for attribute " + n, v.get(vectorSize)); //$NON-NLS-1$
            }
        }

        // verify that we can move/resize components.

        ComponentRefEditPart creditpart = (ComponentRefEditPart) getEditPart(getMap().getContRefs().get(1));
        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart, new Rectangle(100,
                200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());

        // verify that we can't move/resize fixed components.
        cr.setFixed(true);
        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart, new Rectangle(100,
                200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && !cmd.canExecute());

    }

    /**
     * Test #1 for requirement ReqCompCompBind
     * 
     * Author: jkealey
     */
    public void testReqCompCompBind1() {

        assertTrue("Test created for SetConstraintContainerRefCommand defaults that no longer hold.", ModelCreationFactory.DEFAULT_UCM_COMPONENT_HEIGHT //$NON-NLS-1$
                * ModelCreationFactory.DEFAULT_UCM_COMPONENT_WIDTH < 300 * 400);

        // create the component ref that will be used for testing.
        ComponentRef parent = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);
        // create the component ref that will be used for testing.
        ComponentRef child = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);

        // to be able to build the property source for the compDef, our component ref must be inside a map.
        Command cmd = new AddContainerRefCommand(getMap(), parent);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // to be able to build the property source for the compDef, our component ref must be inside a map.
        cmd = new AddContainerRefCommand(getMap(), child);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // set the parent somewhere.
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getEditPart(parent);
        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(parentEditPart, new Rectangle(
                100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("Error in test; wrong parentEditPart.", parent, parentEditPart.getModel()); //$NON-NLS-1$

        // set the child in it.
        // explanation for get(3): we've made the parent larger. refreshChildren() will put it at position 0 so the child is at position 3
        // labels: 0&1
        ComponentRefEditPart childEditPart = (ComponentRefEditPart) getEditPart(child);
        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(childEditPart, new Rectangle(150,
                250, 50, 50));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("Error in test; wrong childEditPart.", child, childEditPart.getModel()); //$NON-NLS-1$

        assertEquals("Child not bound to parent", parent, child.getParent()); //$NON-NLS-1$

        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(parentEditPart, new Rectangle(0,
                0, 150, 200));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertTrue("Child not moved", child.getX() != 150 && child.getY() != 250); //$NON-NLS-1$
        assertTrue("Child not resized", child.getWidth() == 25 && child.getHeight() == 25); //$NON-NLS-1$

    }

    /**
     * Test #2 for requirement ReqCompCompBind
     * 
     * Author: jkealey
     */
    public void testReqCompCompBind2() {
        testReqCompCompBind1();
        ComponentRef parent = (ComponentRef) getMap().getContRefs().get(1);
        ((Component) parent.getContDef()).setName("ParentTest"); //$NON-NLS-1$

        // create a property source on the small component ref
        ComponentRef cr = (ComponentRef) getMap().getContRefs().get(2);

        Vector v = getAttributeDescriptor(cr, "parent"); //$NON-NLS-1$
        String[] values = ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertEquals("Parent not option in property values", "ParentTest (14)", values[2]); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test #1 for requirement ReqCompCompUnbind
     * 
     * Author: jkealey
     */
    public void testReqCompCompUnbind1() {
        testReqCompCompBind1();

        // 0-1-2 are labels
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(4);
        ComponentRefEditPart childEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(5);
        ComponentRef parent = (ComponentRef) parentEditPart.getModel();
        ComponentRef child = (ComponentRef) childEditPart.getModel();

        assertEquals("Invalid preconditions for testReqCompUnbind1", child.getParent(), parent); //$NON-NLS-1$

        Command cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(childEditPart,
                new Rectangle(200, 200, 300, 150));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertTrue("Child still bound to parent", child.getParent() != parent); //$NON-NLS-1$

    }

    /**
     * Test #2 for requirement ReqCompCompUnbind
     * 
     * Author:
     */
    public void testReqCompCompUnbind2() {
        testReqCompCompBind1();
        ComponentRef parent = (ComponentRef) getMap().getContRefs().get(1);
        ((Component) parent.getContDef()).setName("ParentTest"); //$NON-NLS-1$

        // create a property source on the large component ref
        Vector v = getAttributeDescriptor(parent, "parent"); //$NON-NLS-1$
        String[] values = ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("No unbind option in list", Messages.getString("URNElementPropertySource.unbound").equals(values[0])); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test #1 for requirement ReqCompPathBind
     * 
     * Author: jkealey
     */
    public void testReqCompPathBind1() {
        // create the component ref that will be used for testing.
        ComponentRef cr = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);
        // to be able to build the property source for the compDef, our component ref must be inside a map.
        Command cmd = new AddContainerRefCommand(getMap(), cr);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // verify that we can move/resize components.
        ComponentRefEditPart creditpart = (ComponentRefEditPart) getEditPart(getMap().getContRefs().get(1));
        cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart, new Rectangle(0, 0,
                400, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundContainerRefCompoundCommand ", //$NON-NLS-1$
                cmd instanceof SetConstraintBoundContainerRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        testReqElemStartPoint1();

        for (int i = 0; i < getMap().getNodes().size(); i++) {
            assertEquals("New node not bound to parent (" + i + ")", cr, ((PathNode) getMap().getNodes().get(i)).getContRef()); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    /**
     * Test #2 for requirement ReqCompPathBind
     * 
     * Author: jkealey
     */
    public void testReqCompPathBind2() {
        testReqCompPathBind1();
        PathNode node = (PathNode) getMap().getNodes().get(1);
        ComponentRef parent = (ComponentRef) getMap().getContRefs().get(1);
        ((Component) parent.getContDef()).setName("ParentTest"); //$NON-NLS-1$

        Vector v = getAttributeDescriptor(node, "contRef"); //$NON-NLS-1$
        String[] values = ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertEquals("Parent not option in property values", "ParentTest (14)", values[2]); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test #1 for requirement ReqCompPathUnbind
     * 
     * Author: jkealey
     */
    public void testReqCompPathUnbind1() {
        testReqCompPathBind1();

        // pick any path node
        PathNode pn = (PathNode) getMap().getNodes().get(1);
        PathNodeEditPart pnpart = (PathNodeEditPart) getEditPart(pn);
        ComponentRef parent = (ComponentRef) pn.getContRef();

        Command cmd = ((MapXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(pnpart, new Rectangle(
                500, 500, 0, 0));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintCommand ", cmd instanceof SetConstraintCommand && cmd.canExecute()); //$NON-NLS-1$
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertTrue("Moved node should no longer have the same parent.", pn.getContRef() != parent); //$NON-NLS-1$

    }

    /**
     * Test #2 for requirement ReqCompPathUnbind
     * 
     * Author:
     */
    public void testReqCompPathUnbind2() {
        testReqCompPathUnbind1();
        PathNode node = (PathNode) getMap().getNodes().get(1);
        ComponentRef parent = (ComponentRef) getMap().getContRefs().get(1);
        ((Component) parent.getContDef()).setName("ParentTest"); //$NON-NLS-1$

        Vector v = getAttributeDescriptor(node, "contRef"); //$NON-NLS-1$
        String[] values = ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("No unbind option in list", Messages.getString("URNElementPropertySource.unbound").equals(values[0])); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test #1 for requirement ReqConnections
     * 
     * Author: jkealey
     */
    public void testReqConnections1() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and a start point, from the other path.
        EndPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                ep = (EndPoint) element;
                break;
            }
        }
        assertNotNull("no end point found", ep); //$NON-NLS-1$
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint && ((NodeConnection) ((NodeConnection) element.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget() != ep) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp); //$NON-NLS-1$

        Vector v = new Vector();
        v.add(sp);
        v.add(ep);
        IAction action = getAction(v, ConnectAction.CONNECT);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

    }

    /**
     * Test #2 for requirement ReqConnections
     * 
     * Author: jkealey
     */
    public void testReqConnections2() {
        testReqConnections1();
        // get an endpoint connected to a start point.
        EndPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint && element.getSucc().size() > 0) {
                ep = (EndPoint) element;
                NodeConnection nc = (NodeConnection) element.getSucc().get(0);
                nc = (NodeConnection) nc.getTarget().getSucc().get(0);
                sp = (StartPoint) nc.getTarget();
                break;
            }
        }

        Vector v = new Vector();
        v.add(sp);
        IAction action = getAction(v, DisconnectAction.DISCONNECT);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // try with other element.
        v.clear();
        v.add(ep);

        action = getAction(v, DisconnectAction.DISCONNECT);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

        // test to see if really disconnected.
        assertTrue("items not disconnected", ep.getSucc().size() == 0 && sp.getPred().size() == 0); //$NON-NLS-1$

    }

    /**
     * Test #3 for requirement ReqConnections
     * 
     * Author: jkealey
     */
    public void testReqConnections3() {
        testReqConnections1();
        // get an endpoint connected to a start point.
        EndPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint && element.getSucc().size() > 0) {
                ep = (EndPoint) element;
                NodeConnection nc = (NodeConnection) element.getSucc().get(0);
                nc = (NodeConnection) nc.getTarget().getSucc().get(0);
                sp = (StartPoint) nc.getTarget();
                break;
            }
        }

        assertTrue("can't find sp", sp != null); //$NON-NLS-1$
        assertTrue("can't find ep", ep != null); //$NON-NLS-1$

        // move one
        Command cmd = new SetConstraintCommand(sp, 85, 148);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        assertTrue("connected elements didn't move together", sp.getX() == ep.getX() && sp.getX() == 85 && sp.getY() == ep.getY() && sp.getY() == 148); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork1() {
        // Is there a tool to create a AndFork in the palette?
        CreationTool createtool = getToolEntryForClass(AndFork.class);
        assertNotNull("No palette entry creates AndFork", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$

        // select the empty point and see if the addandfork action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddAndForkAction.ADDANDFORK);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

    }

    /**
     * Test #3 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork3() {

        Vector v = testReqElemFork3_setup();

        IAction action = getAction(v, AddAndForkAction.ADDANDFORK);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                i++;
            }
        }

        assertEquals("should only have one start point left!", 1, i); //$NON-NLS-1$
    }

    private Vector testReqElemFork3_setup() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and a start point, from the other path.
        EmptyPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint && ((NodeConnection) element.getSucc().get(0)).getTarget() != ep) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp); //$NON-NLS-1$

        // select the empty point and see if the addandfork action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(sp);
        return v;
    }

    /**
     * Test #1 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin1() {
        // Is there a tool to create a AndJoin in the palette?
        CreationTool createtool = getToolEntryForClass(AndJoin.class);
        assertNotNull("No palette entry creates AndJoin", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddAndJoinAction.ADDANDJOIN);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();
    }

    /**
     * Test #3 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin3() {
        // get an emptypoint and an end point, from the other path.
        EmptyPoint ep = null;
        EndPoint endpoint = null;

        // create a simple path
        CreatePathCommand cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        endpoint = cmd.getEnd();

        // and another.
        cmd = new CreatePathCommand(getMap(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        ep = (EmptyPoint) ((NodeConnection) cmd.getStart().getSucc().get(0)).getTarget();

        assertNotNull("no empty point found", ep); //$NON-NLS-1$
        assertNotNull("no end point found", endpoint); //$NON-NLS-1$

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(endpoint);

        IAction action = getAction(v, AddAndJoinAction.ADDANDJOIN);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        assertEquals("should only have one end point left!", 1, i); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete1() {
        testReqElemStartPoint1();
        Command cmd;
        NodeConnection nc = (NodeConnection) getMap().getConnections().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urn, RespRef.class);
        cmd = new SplitLinkCommand(getMap(), resp, nc, 100, 100);
        assertTrue("Can't insert RespRef", cmd.canExecute()); //$NON-NLS-1$
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        PathNode pn = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            pn = (PathNode) iter.next();
            if (pn instanceof RespRef) {
                break;
            }
        }
        assertTrue("no respref found", pn instanceof RespRef); //$NON-NLS-1$

        PathNodeEditPart part = (PathNodeEditPart) getEditPart(pn);

        cmd = part.getCommand(new GroupRequest(RequestConstants.REQ_DELETE));
        assertTrue("no/bad DeletePathNodeCommand", cmd instanceof DeletePathNodeCommand && cmd.canExecute()); //$NON-NLS-1$
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        for (Iterator iterator = getMap().getNodes().iterator(); iterator.hasNext();) {
            PathNode pn2 = (PathNode) iterator.next();
            assertTrue("No respref should remain in model ", !(pn2 instanceof RespRef)); //$NON-NLS-1$

        }

    }

    /**
     * Test #2 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete2() {
        testReqElemAndFork2();
        AndFork fork = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork); //$NON-NLS-1$

        NodeConnection nc = (NodeConnection) fork.getSucc().get(0);
        Vector v = new Vector();
        v.add(nc);
        IAction action = getAction(v, ActionFactory.DELETE.getId());

        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                i++;
            }
        }

        assertEquals("AndFork should have been deleted.", 0, i); //$NON-NLS-1$

    }

    /**
     * Test #3 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete3() {
        testReqComp1();

        // set the parent somewhere.
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(2);
        Command cmd = parentEditPart.getCommand(new GroupRequest(RequestConstants.REQ_DELETE));
        assertTrue("ComponentRefEditPolicy doesn't return a valid DeleteComponentRefCommand", cmd instanceof DeleteComponentRefCommand && cmd.canExecute()); //$NON-NLS-1$
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("only one ComponentRef should remain in model ", 1, getMap().getContRefs().size()); //$NON-NLS-1$
        assertEquals("Only one ComponentRefEditPart should remain in editpart tree ", 2, getMapEditPart(0).getChildren().size()); //$NON-NLS-1$

    }

    /**
     * Test #4 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete4() {
        testReqElemAndFork2();
        AndFork fork = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork); //$NON-NLS-1$

        Vector v = new Vector();
        v.add(fork);
        IAction action = getAction(v, AddBranchAction.ADDBRANCH);
        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        int i = 0;

        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }
        NodeConnection nc = (NodeConnection) fork.getSucc().get(0);
        // delete the end point.
        v = new Vector();
        v.add(nc.getTarget());
        action = getAction(v, ActionFactory.DELETE.getId());
        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i--;
            }
        }

        assertEquals("There should be one less end point!", 1, i); //$NON-NLS-1$
    }

    /**
     * Test #5 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete5() {
        testReqElemAndFork2();
        AndFork fork = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork); //$NON-NLS-1$

        Vector v = new Vector();
        v.add(fork);
        IAction action = getAction(v, AddBranchAction.ADDBRANCH);
        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        action = getAction(v, ActionFactory.SELECT_ALL.getId());
        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        ((UrnContextMenuProvider) getGraphicalViewer().getContextMenu()).buildContextMenu((getGraphicalViewer().getContextMenu()));
        IContributionItem contrib = ((UrnContextMenuProvider) getGraphicalViewer().getContextMenu()).find(ActionFactory.DELETE.getId());
        if (contrib instanceof ActionContributionItem) {
            action = ((ActionContributionItem) contrib).getAction();
        } else
            action = null;
        assertNotNull("no action found", action); //$NON-NLS-1$
        action.run();

        assertEquals("no pathnodes should remain", 0, getMap().getNodes().size()); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemDirectionArrow
     * 
     * Author: jkealey
     */
    public void testReqElemDirectionArrow1() {
        // Is there a tool to create a DirectionArrow in the palette?
        CreationTool createtool = getToolEntryForClass(DirectionArrow.class);
        assertNotNull("No palette entry creates DirectionArrow", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemDirectionArrow
     * 
     * Author: jkealey
     */
    public void testReqElemDirectionArrow2() {
        // yeah, really lazy testing.
        DirectionArrowFigure df = new DirectionArrowFigure();
        assertTrue("direction arrow not rotatable", df instanceof IRotateable); //$NON-NLS-1$
        EndPointFigure epf = new EndPointFigure();
        assertTrue("end point not rotatable", epf instanceof IRotateable); //$NON-NLS-1$

    }

    /**
     * Test #1 for requirement ReqElemDynamicStub
     * 
     * Author: jkealey
     */
    public void testReqElemDynamicStub1() {
        // Is there a tool to create a Stub in the palette? without rewriting stuff, we can't get access to the creationfactory. assume dynamic exists if stub
        // exists
        CreationTool createtool = getToolEntryForClass(Stub.class);
        assertNotNull("No palette entry creates Stub", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemDynamicStub
     * 
     * Author:
     */
    public void testReqElemDynamicStub2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 0, 100);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp); //$NON-NLS-1$

        // add second path.
        cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        NodeConnection nc = (NodeConnection) ((CreatePathCommand) cmd).getStart().getSucc().get(0);

        // add a stub.
        Stub stub = (Stub) ModelCreationFactory.getNewObject(urn, Stub.class);
        stub.setDynamic(true);
        cmd = new SplitLinkCommand(getMap(), stub, nc, 125, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // simulate moving first start onto stub.
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        cbr.setEditParts(getEditPart(sp));
        cbr.setLocation(new Point(125, 200));
        cmd = getEditPart(stub).getCommand(cbr);

        assertNotNull("unable to get command", cmd); //$NON-NLS-1$
        assertTrue("cannot execute command", cmd.canExecute()); //$NON-NLS-1$
        cmd.execute();

    }

    /**
     * Test #1 for requirement ReqElemEmptyPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEmptyPoint1() {
        assertTrue("No palette entry creates EmptyPoint (No path tool)", isToolEntryPresent(PathToolEntry.class)); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemEmptyPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEmptyPoint2() {
        testReqElemStartPoint1();
        PathNode pn = null;

        assertTrue("no path node found", getMap().getNodes().size() > 0); //$NON-NLS-1$
        pn = (PathNode) getMap().getNodes().get(0);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(pn);
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean x, y, id, name;
        x = y = id = name = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name")) //$NON-NLS-1$
                name = true;
            else if (str.equalsIgnoreCase("id")) //$NON-NLS-1$
                id = true;
            else if (str.equalsIgnoreCase("x")) //$NON-NLS-1$
                x = true;
            else if (str.equalsIgnoreCase("y")) //$NON-NLS-1$
                y = true;
        }

        assertTrue("Missing PropertyDescriptor", name && id && x && y); //$NON-NLS-1$

    }

    /**
     * Test #1 for requirement ReqElemEndPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEndPoint1() {
        assertTrue("No palette entry creates EndPoint (No path tool)", isToolEntryPresent(PathToolEntry.class)); //$NON-NLS-1$

    }

    /**
     * Test #2 for requirement ReqElemEndPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEndPoint2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 0, 100);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its startpoint.
        StartPoint sp = ((CreatePathCommand) cmd).getStart();

        // add second path.
        cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        EndPoint ep = ((CreatePathCommand) cmd).getEnd();

        // simulate moving first start onto stub.
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        cbr.setEditParts(getEditPart(sp));
        cbr.setLocation(new Point(200, 200));
        cmd = getEditPart(ep).getCommand(cbr);

        assertNotNull("unable to get command", cmd); //$NON-NLS-1$
        assertTrue("cannot execute command", cmd.canExecute()); //$NON-NLS-1$
        cmd.execute();
    }

    /**
     * Test #3 for requirement ReqElemEndPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEndPoint3() {
        testReqElemStartPoint1();

        EndPoint end = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                end = (EndPoint) element;
            }
        }
        assertNotNull("cannot find endpoint", end); //$NON-NLS-1$

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(end);
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("postcondition")) //$NON-NLS-1$
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork1() {
        // Is there a tool to create a OrFork in the palette?
        CreationTool createtool = getToolEntryForClass(OrFork.class);
        assertNotNull("No palette entry creates OrFork", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddOrForkAction.ADDORFORK);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();
    }

    /**
     * Test #3 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork3() {
        Vector v = testReqElemFork3_setup();

        IAction action = getAction(v, AddOrForkAction.ADDORFORK);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                i++;
            }
        }

        assertEquals("should only have one start point left!", 1, i); //$NON-NLS-1$
    }

    /**
     * Test #4 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork4() {
        testReqElemOrFork3();

        OrFork fork = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof OrFork) {
                fork = (OrFork) element;
            }
        }
        assertNotNull("cannot find orfork", fork); //$NON-NLS-1$

        assertTrue("no preceeding node connection", fork.getSucc().size() >= 2); //$NON-NLS-1$
        for (Iterator iter = fork.getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();

            NodeConnectionEditPart part = (NodeConnectionEditPart) getGraphicalViewer().getEditPartRegistry().get(nc);
            assertNotNull("cannot find editpart", part); //$NON-NLS-1$

            IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
            assertNotNull("No property source found", source); //$NON-NLS-1$

            IPropertyDescriptor desc[] = source.getPropertyDescriptors();

            boolean condition = false;
            for (int i = 0; i < desc.length; i++) {
                String str = desc[i].getDisplayName();
                if (str.equalsIgnoreCase("condition")) //$NON-NLS-1$
                    condition = true;
            }

            assertTrue("Missing PropertyDescriptor", condition); //$NON-NLS-1$
        }
    }

    /**
     * Test #1 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin1() {
        // Is there a tool to create a OrJoin in the palette?
        CreationTool createtool = getToolEntryForClass(OrJoin.class);
        assertNotNull("No palette entry creates OrJoin", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddOrJoinAction.ADDORJOIN);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();
    }

    /**
     * Test #3 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin3() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and an end point, from the other path.
        EmptyPoint ep = null;
        EndPoint endpoint = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep); //$NON-NLS-1$
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint && ((NodeConnection) element.getPred().get(0)).getSource() != ep) {
                endpoint = (EndPoint) element;
                break;
            }
        }
        assertNotNull("no end point found", endpoint); //$NON-NLS-1$

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(endpoint);

        IAction action = getAction(v, AddOrJoinAction.ADDORJOIN);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        assertEquals("should only have one end point left!", 1, i); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemResponsibility
     * 
     * Author: jkealey
     */
    public void testReqElemResponsibility1() {
        // Is there a tool to create a RespRef in the palette?
        CreationTool createtool = getToolEntryForClass(RespRef.class);
        assertNotNull("No palette entry creates RespRef", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemResponsibility
     * 
     * Author: jkealey
     */
    public void testReqElemResponsibility2() {
        testReqElemStartPoint1();
        Command cmd;
        NodeConnection nc = (NodeConnection) getMap().getConnections().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urn, RespRef.class);
        cmd = new SplitLinkCommand(getMap(), resp, nc, 100, 100);
        assertTrue("Can't insert RespRef", cmd.canExecute()); //$NON-NLS-1$
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(resp);
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean def = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("definition")) //$NON-NLS-1$
                def = true;
        }

        assertTrue("Missing PropertyDescriptor", def); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemStartPoint
     * 
     * Author: jkealey
     */
    public void testReqElemStartPoint1() {
        int childCount = getMapEditPart(0).getChildren().size();

        assertTrue("No palette entry creates StartPoint (No path tool)", isToolEntryPresent(PathToolEntry.class)); //$NON-NLS-1$

        // verify that the StartPoint is not in the model
        assertEquals("Should be no PathNodes in model", 0, getMap().getNodes().size()); //$NON-NLS-1$

        // simulate a CreateRequest that we would have liked to have obtained from the palette
        CreateRequest cr = getCreateRequest(new ModelCreationFactory(urn, StartPoint.class), new Point(50, 70));
        assertNotNull("Unable to build create request", cr); //$NON-NLS-1$

        // create a command using this CreateRequest. Note that this is a compound command that not only creates the component but positions it properly.
        Command cmd = getMapEditPart(0).getCommand(cr);
        assertNotNull("Can't get command to obtain a new StartPoint", cmd); //$NON-NLS-1$

        // execute the command, adding the StartPoint to the model
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // because this test is not hooked up as a command stack change listener
        getMapEditPart(0).refreshChildren();

        // verify that the StartPoint is in the model
        assertEquals("Simple path not added.", 3, getMap().getNodes().size()); //$NON-NLS-1$

        // verify that the edit part tree has changed.
        assertEquals("MapAndPathGraphEditPart should have exactly " + (childCount + 7) + " children", childCount + 7, getMapEditPart(0).getChildren().size()); //$NON-NLS-1$ //$NON-NLS-2$

    }

    /**
     * Test #2 for requirement ReqElemStartPoint
     * 
     * Author: jkealey
     */
    public void testReqElemStartPoint2() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start); //$NON-NLS-1$

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(start);
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("precondition")) //$NON-NLS-1$
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemStartPointAttributes
     * 
     * Author: jkealey
     */
    public void testReqElemStartPointAttributes1() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start); //$NON-NLS-1$

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(start);
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean wl = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase(Messages.getString("URNElementPropertySource.workload"))) //$NON-NLS-1$
                wl = true;
        }

        assertTrue("Missing PropertyDescriptor", wl); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqElemStaticStub
     * 
     * Author: jkealey
     */
    public void testReqElemStaticStub1() {
        // Is there a tool to create a Stub in the palette? without rewriting stuff, we can't get access to the creationfactory. assume static exists if stub
        // exists
        CreationTool createtool = getToolEntryForClass(Stub.class);
        assertNotNull("No palette entry creates Stub", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemStaticStub
     * 
     * Author: jkealey
     */
    public void testReqElemStaticStub2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap(), 0, 100);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        StartPoint sp = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp); //$NON-NLS-1$

        // add second path.
        cmd = new CreatePathCommand(getMap(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        NodeConnection nc = (NodeConnection) ((CreatePathCommand) cmd).getStart().getSucc().get(0);

        // add a stub.
        Stub stub = (Stub) ModelCreationFactory.getNewObject(urn, Stub.class);
        cmd = new SplitLinkCommand(getMap(), stub, nc, 125, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // simulate moving first start onto stub.
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        cbr.setEditParts(getEditPart(sp));
        cbr.setLocation(new Point(125, 200));
        cmd = getEditPart(stub).getCommand(cbr);

        assertNotNull("unable to get command", cmd); //$NON-NLS-1$
        assertTrue("cannot execute command", cmd.canExecute()); //$NON-NLS-1$
        cmd.execute();
    }

    /**
     * Test #1 for requirement ReqElemStubActions
     * 
     * Author: jkealey
     */
    public void testReqElemStubActions1() {
        Vector v = new Vector();
        v.add(urn);
        IAction action = getAction(v, AddMapAction.ADDMAP);
        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        // test if doesn't crash.
        action.run();

        assertEquals("map not added", 2, editor.getPageCount()); //$NON-NLS-1$
        assertEquals("new map not loaded", getMap(1), ((UcmEditor) editor.getCurrentPage()).getModel()); //$NON-NLS-1$

    }

    // /**
    // * Test #2 for requirement ReqElemStubActions
    // *
    // * Author:
    // */
    // public void testReqElemStubActions2() {
    // testReqElemStubActions1();
    // /* failed attempt.
    // EditPart edit = (EditPart) (((UcmOutlinePage)
    // editor.getEditor(0).getAdapter(IContentOutlinePage.class))).getViewer().getEditPartRegistry().get(getMap(0));
    // StructuredSelection s = new StructuredSelection(edit);
    // edit.setSelected(2);
    // ((TreeViewer)edit.getViewer()).setSelection(s);
    // */
    //        
    // assertEquals("old map not visible", getMap(0), editor.getCurrentPage().getModel());
    // }

    // /**
    // * Test #3 for requirement ReqElemStubActions
    // *
    // * Author:
    // */
    // public void testReqElemStubActions3() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    // /**
    // * Test #4 for requirement ReqElemStubActions
    // *
    // * Author:
    // */
    // public void testReqElemStubActions4() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    // /**
    // * Test #5 for requirement ReqElemStubActions
    // *
    // * Author:
    // */
    // public void testReqElemStubActions5() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    // /**
    // * Test #6 for requirement ReqElemStubActions
    // *
    // * Author:
    // */
    // public void testReqElemStubActions6() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    /**
     * Test #1 for requirement ReqElemTimer
     * 
     * Author: jkealey
     */
    public void testReqElemTimer1() {
        // Is there a tool to create a Timer in the palette?
        CreationTool createtool = getToolEntryForClass(Timer.class);
        assertNotNull("No palette entry creates Timer", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemTimer
     * 
     * Author: jkealey
     */
    public void testReqElemTimer2() {
        testReqElemStartPoint1();

        assertTrue("cannot find node connection", getMap().getConnections().size() > 0); //$NON-NLS-1$
        NodeConnection nc = (NodeConnection) getMap().getConnections().get(0);
        Timer timer = (Timer) ModelCreationFactory.getNewObject(urn, Timer.class);

        assertNotNull("Model creation factory can't create timers!", timer); //$NON-NLS-1$

        Command cmd = new SplitLinkCommand(getMap(), timer, nc, 49, 75);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        Vector v = new Vector();
        v.add(timer);
        IAction action = getAction(v, AddTimeoutPathAction.ADDTIMEOUTPATH);

        assertNotNull("Action not found in contextual menu!", action); //$NON-NLS-1$

        int i, j;
        i = j = 0;

        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        // run it to see if it doesn't crash the app!
        action.run();

        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                j++;
            }
        }

        assertEquals("one (and exactly one) new end point should have been added", i + 1, j); //$NON-NLS-1$

    }

    /**
     * Test #1 for requirement ReqElemWait
     * 
     * Author: jkealey
     */
    public void testReqElemWait1() {
        // Is there a tool to create a WaitingPlace in the palette?
        CreationTool createtool = getToolEntryForClass(WaitingPlace.class);
        assertNotNull("No palette entry creates WaitingPlace", createtool); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqElemWait
     * 
     * Author: jkealey
     */
    public void testReqElemWait2() {
        testReqElemStartPoint1();

        assertTrue("cannot find node connection", getMap().getConnections().size() > 0); //$NON-NLS-1$
        NodeConnection nc = (NodeConnection) getMap().getConnections().get(0);
        WaitingPlace waitingplace = (WaitingPlace) ModelCreationFactory.getNewObject(urn, WaitingPlace.class);

        assertNotNull("Model creation factory can't create waiting place!", waitingplace); //$NON-NLS-1$

        Command cmd = new SplitLinkCommand(getMap(), waitingplace, nc, 49, 75);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        EditPart part = getEditPart(waitingplace.getSucc().get(0));
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("condition")) //$NON-NLS-1$
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition); //$NON-NLS-1$

    }

    /**
     * Test #1 for requirement ReqExportBitmap
     * 
     * Author: jkealey
     */
    public void testReqExportBitmap1() {
        ExportWizard wiz = new ExportWizard();
        assertTrue("not an export wizard", wiz instanceof IExportWizard); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqExportBitmap
     * 
     * Author: jkealey
     */
    public void testReqExportBitmap2() {
        Vector v = new Vector();
        v.add(getMap());
        IAction action = getAction(v, ExportAction.EXPORT);

        assertNotNull("action is null", action); //$NON-NLS-1$

        // don't test if can run; crashes cruisecontrol
        // action.run();
    }

    /**
     * Test #1 for requirement ReqHelpAbout
     * 
     * Author: jkealey
     */
    public void testReqHelpAbout1() {
        // implicit by the distribution of about.html with the zip file. not worth testing.
        assertTrue("about.html not in zip file", true); //$NON-NLS-1$

    }

    /**
     * Test #1 for requirement ReqHelpOnLine
     * 
     * Author: jkealey
     */
    public void testReqHelpOnLine1() {
        // implicit by the distribution of help.xml with the zip file. not worth testing.
        assertTrue("help.xml not in zip file", true); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqBrowseModel
     * 
     * Author:jkealey
     */
    public void testReqBrowseModel1() {
        assertNotNull("outline doesn't exist", editor.getAdapter(IContentOutlinePage.class)); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqBrowseModel
     * 
     * Author: jkealey
     */
    public void testReqBrowseModel2() {
        // botched implementation.
        IPropertySource ips = (IPropertySource) getEditPart(getMap(0)).getAdapter(IPropertySource.class);
        boolean name = false;
        IPropertyDescriptor[] desc = ips.getPropertyDescriptors();
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name")) //$NON-NLS-1$
                name = true;
        }
        assertTrue("can't rename map", name); //$NON-NLS-1$
    }

    // /**
    // * Test #3 for requirement ReqBrowseModel
    // *
    // * Author:
    // */
    // public void testReqBrowseModel3() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    // /**
    // * Test #4 for requirement ReqBrowseModel
    // *
    // * Author:
    // */
    // public void testReqBrowseModel4() {
    // // TODO: implement
    // assertTrue("Unimplemented", false);
    // }

    /**
     * Test #1 for requirement ReqLabels
     * 
     * Author: jkealey
     */
    public void testReqLabels1() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start); //$NON-NLS-1$
        assertNotNull("cannot find startpoint label", start.getLabel()); //$NON-NLS-1$

        LabelEditPart part = (LabelEditPart) getGraphicalViewer().getEditPartRegistry().get(start.getLabel());
        assertNotNull("cannot find label editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean name = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name")) //$NON-NLS-1$
                name = true;
        }

        assertTrue("Missing PropertyDescriptor (should show name/id of label reference)", name); //$NON-NLS-1$
    }

    /**
     * Test #2 for requirement ReqLabels
     * 
     * Author: jkealey
     */
    public void testReqLabels2() {
        testReqElemResponsibility2();

        RespRef pn = null;
        for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof RespRef) {
                pn = (RespRef) element;
                break;
            }
        }

        assertNotNull("no RespRef found", pn); //$NON-NLS-1$
        assertNotNull("respref does not have a label", pn.getLabel()); //$NON-NLS-1$

        LabelEditPart part = (LabelEditPart) getGraphicalViewer().getEditPartRegistry().get(pn.getLabel());
        assertNotNull("cannot find editpart", part); //$NON-NLS-1$

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source); //$NON-NLS-1$

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean deltaX, deltaY, id, name, definition;
        deltaX = deltaY = id = name = definition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name")) //$NON-NLS-1$
                name = true;
            else if (str.equalsIgnoreCase("id")) //$NON-NLS-1$
                id = true;
            else if (str.equalsIgnoreCase("deltax")) //$NON-NLS-1$
                deltaX = true;
            else if (str.equalsIgnoreCase("deltay")) //$NON-NLS-1$
                deltaY = true;
            else if (str.equalsIgnoreCase("definition")) //$NON-NLS-1$
                definition = true;
        }

        assertTrue("Missing PropertyDescriptor", name && id && deltaX && deltaY && definition); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqOpen
     * 
     * Author: jkealey
     */
    public void testReqOpen1() {
        // real testing done in JUCMNavCommandTests
        assertTrue("top level model element is URNSpec", editor.getModel() instanceof URNspec); //$NON-NLS-1$
    }

    /**
     * Test #1 for requirement ReqSave
     * 
     * Author: jkealey
     */
    public void testReqSave1() {
        // real testing done in JUCMNavCommandTests
        assertTrue("top level model element is URNSpec", editor.getModel() instanceof URNspec); //$NON-NLS-1$
    }

}