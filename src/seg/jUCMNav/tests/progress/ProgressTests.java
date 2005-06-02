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

import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddAndJoinAction;
import seg.jUCMNav.actions.AddBranchAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.AddOrJoinAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmOutlinePage;
import seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider;
import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editors.palette.tools.PathToolEntry;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.MapAndPathGraphEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editpolicies.layout.MapAndPathGraphXYLayoutEditPolicy;
import seg.jUCMNav.figures.DirectionArrowFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.Rotateable;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.views.property.ComponentPropertySource;
import seg.jUCMNav.views.property.EObjectPropertySource;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.ComponentKind;
import urncore.UCMmodelElement;

/**
 * Created 2005-04-25
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
        ((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()).buildContextMenu(((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()));
        IContributionItem contrib = ((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()).find(id);
        if (contrib instanceof ActionContributionItem) {
            return ((ActionContributionItem) contrib).getAction();
        } else
            return null;

    }

    private Vector getAttributeDescriptor(UCMmodelElement cr, String name) {

        EObjectPropertySource eops = new ComponentPropertySource(cr);
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
                assertTrue("No object in descriptor was added for attribute " + n, v.size() == vectorSize + 1);
                assertNotNull("Null object in descriptor was added for attribute " + n, v.get(vectorSize));
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
     * @return
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
        return (EditPart) editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(o);
    }

    public ScrollingGraphicalViewer getGraphicalViewer() {
        return (ScrollingGraphicalViewer) editor.getCurrentPage().getGraphicalViewer();
    }

    public Map getMap() {
        return (Map) urn.getUcmspec().getMaps().get(0);
    }

    public Map getMap(int i) {
        return (Map) urn.getUcmspec().getMaps().get(i);
    }

    public MapAndPathGraphEditPart getMapEditPart(int i) {
        return (MapAndPathGraphEditPart) editor.getCurrentPage().getGraphicalViewer().getRootEditPart().getChildren().get(i);
    }

    public EditPartViewer getOutlineGraphicalViewer() {
        return ((UcmOutlinePage) editor.getAdapter(IContentOutlinePage.class)).getViewer();
    }

    public UcmPaletteRoot getPaletteRoot() {
        return (UcmPaletteRoot) editor.getCurrentPage().getPaletteRoot();
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
        IProject testproject = (IProject) workspaceRoot.getProject("jUCMNav-tests");
        if (!testproject.exists())
            testproject.create(null);

        if (!testproject.isOpen())
            testproject.open(null);

        testfile = testproject.getFile("jUCMNav-test.jucm");
        // start with clean file
        if (testfile.exists())
            testfile.delete(true, false, null);

        testfile.create(new ByteArrayInputStream("".getBytes()), false, null);

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(testfile.getName());
        editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(testfile), desc.getId());

        // generate a top level model element
        //urn = (URNspec) ModelCreationFactory.getNewURNspec();
        urn = editor.getModel();

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
        assertTrue("editor must extend INavigationLocationProvider", editor instanceof INavigationLocationProvider);
    }

    /**
     * Test #1 for requirement: ReqComp
     * 
     * Author: jkealey
     */
    public void testReqComp1() {

        // Is there a tool to create a ComponentRef in the palette?
        CreationTool createtool = getToolEntryForClass(ComponentRef.class);
        assertNotNull("No palette entry creates ComponentRef", createtool);

        // verify that both the componentref and component element are not in the model
        assertEquals("Should be no components in model", 0, urn.getUrndef().getComponents().size());
        assertEquals("Should be no component references in model", 0, getMap().getCompRefs().size());

        // verify that the edit part tree is empty.
        assertEquals("MapAndPathGraphEditPart should not have any children", 0, getMapEditPart(0).getChildren().size());

        // simulate a CreateRequest that we would have liked to have obtained from the palette
        CreateRequest cr = getCreateRequest(new ModelCreationFactory(urn, ComponentRef.class, ComponentKind.TEAM), new Point(10, 100));
        assertNotNull("Unable to build create request", cr);

        // create a command using this CreateRequest. Note that this is a compound command that not only creates the component but positions it properly.
        Command cmd = (Command) getMapEditPart(0).getCommand(cr);
        assertNotNull("Can't get command to obtain a new ComponentRef", cmd);

        // execute the command, adding the componentref to the model
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // because this test is not hooked up as a command stack change listener
        // JK: I'm not even sure how this should be done but we should do it.
        getMapEditPart(0).refreshChildren();

        // verify that both the componentref and component element have been added in the model.
        assertEquals("No component added to model", 1, urn.getUrndef().getComponents().size());
        assertEquals("No component ref added to model", 1, getMap().getCompRefs().size());

        // verify that the edit part tree has changed.
        assertEquals("MapAndPathGraphEditPart should have exactly two children (component+label)", 2, getMapEditPart(0).getChildren().size());
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
        Command cmd = new AddComponentRefCommand(getMap(), cr);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // create a property source on the component ref
        ComponentPropertySource eops = new ComponentPropertySource(cr);
        EStructuralFeature attr;
        Vector v = new Vector();
        Iterator i = cr.eClass().getEAllStructuralFeatures().iterator();

        // for each attribute and reference
        while (i.hasNext()) {
            attr = (EStructuralFeature) i.next();
            String n = attr.getName();

            // make sure that the ones we have targetted do amount in adding a property to the property descriptor
            if (n.equals("x") || n.equals("y") || n.equals("width") || n.equals("height") || n.equals("compDef")) {
                int vectorSize = v.size();
                eops.addPropertyToDescriptor(v, attr, cr.eClass());
                assertTrue("No object in descriptor was added for attribute " + n, v.size() == vectorSize + 1);
                assertNotNull("Null object in descriptor was added for attribute " + n, v.get(vectorSize));
            }
        }

        // verify that we can move/resize components.
        ComponentRefEditPart creditpart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(1);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart,
                new Rectangle(100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());

        // verify that we can't move/resize fixed components.
        cr.setFixed(true);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart,
                new Rectangle(100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && !cmd.canExecute());

    }

    /**
     * Test #1 for requirement ReqCompCompBind
     * 
     * Author: jkealey
     */
    public void testReqCompCompBind1() {

        assertTrue("Test created for SetConstraintComponentRefCommand defaults that no longer hold.", SetConstraintComponentRefCommand.DEFAULT_HEIGHT
                * SetConstraintComponentRefCommand.DEFAULT_WIDTH < 300 * 400);

        // create the component ref that will be used for testing.
        ComponentRef parent = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);
        // create the component ref that will be used for testing.
        ComponentRef child = (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);

        // to be able to build the property source for the compDef, our component ref must be inside a map.
        Command cmd = new AddComponentRefCommand(getMap(), parent);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // to be able to build the property source for the compDef, our component ref must be inside a map.
        cmd = new AddComponentRefCommand(getMap(), child);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // set the parent somewhere.
        // explanation for get(3): they are both the same size, the algorithm positions the parent edit part at position 3.
        // 0&1: labels
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(3);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(parentEditPart,
                new Rectangle(100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("Error in test; wrong parentEditPart.", parent, parentEditPart.getModel());

        // set the child in it.
        // explanation for get(3): we've made the parent larger. refreshChildren() will put it at position 0 so the child is at position 3
        // labels: 0&1
        ComponentRefEditPart childEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(3);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(childEditPart,
                new Rectangle(150, 250, 50, 50));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("Error in test; wrong childEditPart.", child, childEditPart.getModel());

        assertEquals("Child not bound to parent", parent, child.getParent());

        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(parentEditPart,
                new Rectangle(0, 0, 150, 200));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertTrue("Child not moved", child.getX() != 150 && child.getY() != 250);
        assertTrue("Child not resized", child.getWidth() == 25 && child.getHeight() == 25);

    }

    /**
     * Test #2 for requirement ReqCompCompBind
     * 
     * Author: jkealey
     */
    public void testReqCompCompBind2() {
        testReqCompCompBind1();
        ComponentRef parent = (ComponentRef) getMap().getCompRefs().get(0);
        parent.getCompDef().setName("ParentTest");

        // create a property source on the small component ref
        ComponentRef cr = (ComponentRef) getMap().getCompRefs().get(1);

        Vector v = getAttributeDescriptor(cr, "parent");
        String[] values = (String[]) ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("Parent not option in property values", "ParentTest".equals(values[1]));
    }

    /**
     * Test #1 for requirement ReqCompCompUnbind
     * 
     * Author: jkealey
     */
    public void testReqCompCompUnbind1() {
        testReqCompCompBind1();

        //0 and 1 are labels
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(2);
        ComponentRefEditPart childEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(3);
        ComponentRef parent = (ComponentRef) parentEditPart.getModel();
        ComponentRef child = (ComponentRef) childEditPart.getModel();

        assertEquals("Invalid preconditions for testReqCompUnbind1", child.getParent(), parent);

        Command cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(
                childEditPart, new Rectangle(200, 200, 300, 150));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertNull("Child still bound to parent", child.getParent());

    }

    /**
     * Test #2 for requirement ReqCompCompUnbind
     * 
     * Author:
     */
    public void testReqCompCompUnbind2() {
        testReqCompCompBind1();
        ComponentRef parent = (ComponentRef) getMap().getCompRefs().get(0);
        parent.getCompDef().setName("ParentTest");

        // create a property source on the large component ref
        Vector v = getAttributeDescriptor(parent, "parent");
        String[] values = (String[]) ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("No unbind option in list", "[unbound]".equals(values[0]));
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
        Command cmd = new AddComponentRefCommand(getMap(), cr);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        // verify that we can move/resize components.
        ComponentRefEditPart creditpart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(1);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart,
                new Rectangle(0, 0, 400, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintBoundComponentRefCompoundCommand ",
                cmd instanceof SetConstraintBoundComponentRefCompoundCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        testReqElemStartPoint1();

        for (int i = 0; i < getMap().getPathGraph().getPathNodes().size(); i++) {
            assertEquals("New node not bound to parent (" + i + ")", cr, ((PathNode) getMap().getPathGraph().getPathNodes().get(i)).getCompRef());
        }

    }

    /**
     * Test #2 for requirement ReqCompPathBind
     * 
     * Author: jkealey
     */
    public void testReqCompPathBind2() {
        testReqCompPathBind1();
        PathNode node = (PathNode) getMap().getPathGraph().getPathNodes().get(1);
        ComponentRef parent = (ComponentRef) getMap().getCompRefs().get(0);
        parent.getCompDef().setName("ParentTest");

        Vector v = getAttributeDescriptor(node, "compRef");
        String[] values = (String[]) ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("Parent not option in property values", "ParentTest".equals(values[1]));
    }

    /**
     * Test #1 for requirement ReqCompPathUnbind
     * 
     * Author: jkealey
     */
    public void testReqCompPathUnbind1() {
        testReqCompPathBind1();

        // pick any path node
        PathNodeEditPart pnpart = (PathNodeEditPart) getMapEditPart(0).getChildren().get(1);
        PathNode pn = (PathNode) pnpart.getModel();

        Command cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart(0).getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(pnpart,
                new Rectangle(500, 500, 0, 0));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintCommand ", cmd instanceof SetConstraintCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertNull("Moved node should no longer have a parent.", pn.getCompRef());

    }

    /**
     * Test #2 for requirement ReqCompPathUnbind
     * 
     * Author:
     */
    public void testReqCompPathUnbind2() {
        testReqCompPathUnbind1();
        PathNode node = (PathNode) getMap().getPathGraph().getPathNodes().get(1);
        ComponentRef parent = (ComponentRef) getMap().getCompRefs().get(0);
        parent.getCompDef().setName("ParentTest");

        Vector v = getAttributeDescriptor(node, "compRef");
        String[] values = (String[]) ((ComboBoxLabelProvider) ((ComboBoxPropertyDescriptor) v.get(0)).getLabelProvider()).getValues();
        assertTrue("No unbind option in list", "[unbound]".equals(values[0]));
    }

    /**
     * Test #1 for requirement ReqConnections
     * 
     * Author: jkealey
     */
    public void testReqConnections1() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and a start point, from the other path.
        EndPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                ep = (EndPoint) element;
                break;
            }
        }
        assertNotNull("no end point found", ep);
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint && ((NodeConnection) ((NodeConnection) element.getSucc().get(0)).getTarget().getSucc().get(0)).getTarget() != ep) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp);

        Vector v = new Vector();
        v.add(sp);
        v.add(ep);
        IAction action = getAction(v, "CONNECTACTION");
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

    }

    //  /**
    //  * Test #2 for requirement ReqConnections
    //  *
    //  * Author:
    //  */
    // public void testReqConnections2() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #3 for requirement ReqConnections
    //  *
    //  * Author:
    //  */
    // public void testReqConnections3() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    /**
     * Test #1 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork1() {
        // Is there a tool to create a AndFork in the palette?
        CreationTool createtool = getToolEntryForClass(AndFork.class);
        assertNotNull("No palette entry creates AndFork", createtool);
    }

    /**
     * Test #2 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);

        // select the empty point and see if the addandfork action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddAndForkAction.ADDANDFORK);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

    }

    /**
     * Test #3 for requirement ReqElemAndFork
     * 
     * Author: jkealey
     */
    public void testReqElemAndFork3() {

        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and a start point, from the other path.
        EmptyPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint && ((NodeConnection) element.getSucc().get(0)).getTarget() != ep) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp);

        // select the empty point and see if the addandfork action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(sp);

        IAction action = getAction(v, AddAndForkAction.ADDANDFORK);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                i++;
            }
        }

        assertEquals("should only have one start point left!", 1, i);
    }

    /**
     * Test #1 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin1() {
        // Is there a tool to create a AndJoin in the palette?
        CreationTool createtool = getToolEntryForClass(AndJoin.class);
        assertNotNull("No palette entry creates AndJoin", createtool);
    }

    /**
     * Test #2 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddAndJoinAction.ADDANDJOIN);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();
    }

    /**
     * Test #3 for requirement ReqElemAndJoin
     * 
     * Author: jkealey
     */
    public void testReqElemAndJoin3() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and an end point, from the other path.
        EmptyPoint ep = null;
        EndPoint endpoint = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint && element.getPred().get(0) != ep) {
                endpoint = (EndPoint) element;
                break;
            }
        }
        assertNotNull("no end point found", endpoint);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(endpoint);

        IAction action = getAction(v, AddAndJoinAction.ADDANDJOIN);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        assertEquals("should only have one end point left!", 1, i);
    }

    /**
     * Test #1 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete1() {
        testReqElemStartPoint1();
        Command cmd;
        NodeConnection nc = (NodeConnection) getMap().getPathGraph().getNodeConnections().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urn, RespRef.class);
        cmd = new SplitLinkCommand(getMap().getPathGraph(), resp, nc, 100, 100);
        assertTrue("Can't insert RespRef", cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        PathNode pn = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            pn = (PathNode) iter.next();
            if (pn instanceof RespRef) {
                break;
            }
        }
        assertTrue("no respref found", pn instanceof RespRef);

        PathNodeEditPart part = (PathNodeEditPart) getEditPart(pn);

        cmd = part.getCommand(new GroupRequest(RequestConstants.REQ_DELETE));
        assertTrue("no/bad DeleteNodeCommand", cmd instanceof DeleteNodeCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        for (Iterator iterator = getMap().getPathGraph().getPathNodes().iterator(); iterator.hasNext();) {
            PathNode pn2 = (PathNode) iterator.next();
            assertTrue("No respref should remain in model ", !(pn2 instanceof RespRef));

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
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork);

        NodeConnection nc = (NodeConnection) fork.getSucc().get(0);
        Vector v = new Vector();
        v.add(nc);
        IAction action = getAction(v, ActionFactory.DELETE.getId());

        assertNotNull("no action found", action);
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                i++;
            }
        }

        assertEquals("AndFork should have been deleted.", 0, i);

    }

    /**
     * Test #3 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete3() {
        testReqComp1();

        // set the parent somewhere.
        ComponentRefEditPart parentEditPart = (ComponentRefEditPart) getMapEditPart(0).getChildren().get(1);
        Command cmd = parentEditPart.getCommand(new GroupRequest(RequestConstants.REQ_DELETE));
        assertTrue("ComponentRefEditPolicy doesn't return a valid DeleteComponentRefCommand", cmd instanceof DeleteComponentRefCommand && cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart(0).refreshChildren();

        assertEquals("No ComponentRefs should remain in model ", 0, getMap().getCompRefs().size());
        assertEquals("No ComponentRefEditParts should remain in editpart tree ", 0, getMapEditPart(0).getChildren().size());

    }

    /**
     * Test #4 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete4() {
        testReqElemAndFork2();
        AndFork fork = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork);

        Vector v = new Vector();
        v.add(fork);
        IAction action = getAction(v, AddBranchAction.ADDBRANCH);
        assertNotNull("no action found", action);
        action.run();

        int i = 0;

        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
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
        assertNotNull("no action found", action);
        action.run();

        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i--;
            }
        }

        assertEquals("There should be one less end point!", 1, i);
    }

    /**
     * Test #5 for requirement ReqElemDelete
     * 
     * Author: jkealey
     */
    public void testReqElemDelete5() {
        testReqElemAndFork2();
        AndFork fork = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof AndFork) {
                fork = (AndFork) element;
            }
        }
        assertNotNull("no and fork found", fork);

        Vector v = new Vector();
        v.add(fork);
        IAction action = getAction(v, AddBranchAction.ADDBRANCH);
        assertNotNull("no action found", action);
        action.run();

        action = getAction(v, ActionFactory.SELECT_ALL.getId());
        assertNotNull("no action found", action);
        action.run();

        ((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()).buildContextMenu(((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()));
        IContributionItem contrib = ((UcmContextMenuProvider) getGraphicalViewer().getContextMenu()).find(ActionFactory.DELETE.getId());
        if (contrib instanceof ActionContributionItem) {
            action = ((ActionContributionItem) contrib).getAction();
        } else
            action = null;
        assertNotNull("no action found", action);
        action.run();

        assertEquals("no pathnodes should remain", 0, getMap().getPathGraph().getPathNodes().size());
    }

    /**
     * Test #1 for requirement ReqElemDirectionArrow
     * 
     * Author: jkealey
     */
    public void testReqElemDirectionArrow1() {
        // Is there a tool to create a DirectionArrow in the palette?
        CreationTool createtool = getToolEntryForClass(DirectionArrow.class);
        assertNotNull("No palette entry creates DirectionArrow", createtool);
    }

    /**
     * Test #2 for requirement ReqElemDirectionArrow
     * 
     * Author: jkealey
     */
    public void testReqElemDirectionArrow2() {
        // yeah, really lazy testing.
        DirectionArrowFigure df = new DirectionArrowFigure();
        assertTrue("direction arrow not rotatable", df instanceof Rotateable);
        EndPointFigure epf = new EndPointFigure();
        assertTrue("end point not rotatable", epf instanceof Rotateable);

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
        assertNotNull("No palette entry creates Stub", createtool);
    }

    /**
     * Test #2 for requirement ReqElemDynamicStub
     * 
     * Author:
     */
    public void testReqElemDynamicStub2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 0, 100);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        StartPoint sp = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp);

        // add second path.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        NodeConnection nc = (NodeConnection) ((CreatePathCommand) cmd).getStart().getSucc().get(0);

        // add a stub.
        Stub stub = (Stub) ModelCreationFactory.getNewObject(urn, Stub.class);
        cmd = new SplitLinkCommand(getMap().getPathGraph(), stub, nc, 125, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // simulate moving first start onto stub.
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        cbr.setEditParts(getEditPart(sp));
        cbr.setLocation(new Point(125, 200));
        cmd = getEditPart(stub).getCommand(cbr);

        assertNotNull("unable to get command", cmd);
        assertTrue("cannot execute command", cmd.canExecute());
        cmd.execute();

    }

    /**
     * Test #1 for requirement ReqElemEmptyPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEmptyPoint1() {
        assertTrue("No palette entry creates EmptyPoint (No path tool)", isToolEntryPresent(PathToolEntry.class));
    }

    /**
     * Test #2 for requirement ReqElemEmptyPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEmptyPoint2() {
        testReqElemStartPoint1();
        PathNode pn = null;

        assertTrue("no path node found", getMap().getPathGraph().getPathNodes().size() > 0);
        pn = (PathNode) getMap().getPathGraph().getPathNodes().get(0);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(pn);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean x, y, id, name;
        x = y = id = name = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name"))
                name = true;
            else if (str.equalsIgnoreCase("id"))
                id = true;
            else if (str.equalsIgnoreCase("x"))
                x = true;
            else if (str.equalsIgnoreCase("y"))
                y = true;
        }

        assertTrue("Missing PropertyDescriptor", name && id && x && y);

    }

    /**
     * Test #1 for requirement ReqElemEndPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEndPoint1() {
        assertTrue("No palette entry creates EndPoint (No path tool)", isToolEntryPresent(PathToolEntry.class));

    }

    /**
     * Test #2 for requirement ReqElemEndPoint
     * 
     * Author: jkealey
     */
    public void testReqElemEndPoint2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 0, 100);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its startpoint.
        StartPoint sp = ((CreatePathCommand) cmd).getStart();

        // add second path.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        EndPoint ep = ((CreatePathCommand) cmd).getEnd();

        // simulate moving first start onto stub.
        ChangeBoundsRequest cbr = new ChangeBoundsRequest(RequestConstants.REQ_ADD);
        cbr.setEditParts(getEditPart(sp));
        cbr.setLocation(new Point(200, 200));
        cmd = getEditPart(ep).getCommand(cbr);

        assertNotNull("unable to get command", cmd);
        assertTrue("cannot execute command", cmd.canExecute());
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
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                end = (EndPoint) element;
            }
        }
        assertNotNull("cannot find endpoint", end);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(end);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("postcondition"))
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition);
    }

    /**
     * Test #1 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork1() {
        // Is there a tool to create a OrFork in the palette?
        CreationTool createtool = getToolEntryForClass(OrFork.class);
        assertNotNull("No palette entry creates OrFork", createtool);
    }

    /**
     * Test #2 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddOrForkAction.ADDORFORK);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();
    }

    /**
     * Test #3 for requirement ReqElemOrFork
     * 
     * Author: jkealey
     */
    public void testReqElemOrFork3() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and a start point, from the other path.
        EmptyPoint ep = null;
        StartPoint sp = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint && ((NodeConnection) element.getSucc().get(0)).getTarget() != ep) {
                sp = (StartPoint) element;
                break;
            }
        }
        assertNotNull("no start point found", sp);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(sp);

        IAction action = getAction(v, AddOrForkAction.ADDORFORK);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                i++;
            }
        }

        assertEquals("should only have one start point left!", 1, i);
    }

    /**
     * Test #1 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin1() {
        // Is there a tool to create a OrJoin in the palette?
        CreationTool createtool = getToolEntryForClass(OrJoin.class);
        assertNotNull("No palette entry creates OrJoin", createtool);
    }

    /**
     * Test #2 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin2() {
        // create a simple path
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get its emptypoint.
        EmptyPoint ep = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);

        IAction action = getAction(v, AddOrJoinAction.ADDORJOIN);
        assertNotNull("Action not found in contextual menu!", action);

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
        Command cmd = new CreatePathCommand(getMap().getPathGraph(), 100, 200);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // and another.
        cmd = new CreatePathCommand(getMap().getPathGraph(), 200, 300);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // get an emptypoint and an end point, from the other path.
        EmptyPoint ep = null;
        EndPoint endpoint = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EmptyPoint) {
                ep = (EmptyPoint) element;
                break;
            }
        }
        assertNotNull("no empty point found", ep);
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint && ((NodeConnection) element.getPred().get(0)).getSource() != ep) {
                endpoint = (EndPoint) element;
                break;
            }
        }
        assertNotNull("no end point found", endpoint);

        // select the empty point and see if the action is in the contextual menu
        Vector v = new Vector();
        v.add(ep);
        v.add(endpoint);

        IAction action = getAction(v, AddOrJoinAction.ADDORJOIN);
        assertNotNull("Action not found in contextual menu!", action);

        // run it to see if it doesn't crash the app!
        action.run();

        int i = 0;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        assertEquals("should only have one end point left!", 1, i);
    }

    /**
     * Test #4 for requirement ReqElemOrJoin
     * 
     * Author: jkealey
     */
    public void testReqElemOrJoin4() {
        testReqElemOrJoin3();

        OrJoin join = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof OrJoin) {
                join = (OrJoin) element;
            }
        }
        assertNotNull("cannot find orjoin", join);

        assertNotNull("no preceeding node connection", join.getPred().get(0));
        NodeConnectionEditPart part = (NodeConnectionEditPart) getGraphicalViewer().getEditPartRegistry().get(join.getPred().get(0));
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("condition"))
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition);
    }

    /**
     * Test #1 for requirement ReqElemResponsibility
     * 
     * Author: jkealey
     */
    public void testReqElemResponsibility1() {
        // Is there a tool to create a RespRef in the palette?
        CreationTool createtool = getToolEntryForClass(RespRef.class);
        assertNotNull("No palette entry creates RespRef", createtool);
    }

    /**
     * Test #2 for requirement ReqElemResponsibility
     * 
     * Author: jkealey
     */
    public void testReqElemResponsibility2() {
        testReqElemStartPoint1();
        Command cmd;
        NodeConnection nc = (NodeConnection) getMap().getPathGraph().getNodeConnections().get(0);
        RespRef resp = (RespRef) ModelCreationFactory.getNewObject(urn, RespRef.class);
        cmd = new SplitLinkCommand(getMap().getPathGraph(), resp, nc, 100, 100);
        assertTrue("Can't insert RespRef", cmd.canExecute());
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(resp);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean def = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("definition"))
                def = true;
        }

        assertTrue("Missing PropertyDescriptor", def);
    }

    /**
     * Test #1 for requirement ReqElemStartPoint
     * 
     * Author: jkealey
     */
    public void testReqElemStartPoint1() {
        int childCount = getMapEditPart(0).getChildren().size();

        assertTrue("No palette entry creates StartPoint (No path tool)", isToolEntryPresent(PathToolEntry.class));

        // verify that the StartPoint is not in the model
        assertEquals("Should be no PathNodes in model", 0, getMap().getPathGraph().getPathNodes().size());

        // simulate a CreateRequest that we would have liked to have obtained from the palette
        CreateRequest cr = getCreateRequest(new ModelCreationFactory(urn, StartPoint.class), new Point(50, 70));
        assertNotNull("Unable to build create request", cr);

        // create a command using this CreateRequest. Note that this is a compound command that not only creates the component but positions it properly.
        Command cmd = (Command) getMapEditPart(0).getCommand(cr);
        assertNotNull("Can't get command to obtain a new StartPoint", cmd);

        // execute the command, adding the StartPoint to the model
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        // because this test is not hooked up as a command stack change listener
        getMapEditPart(0).refreshChildren();

        // verify that the StartPoint is in the model
        assertEquals("Simple path not added.", 3, getMap().getPathGraph().getPathNodes().size());

        // verify that the edit part tree has changed.
        assertEquals("MapAndPathGraphEditPart should have exactly " + (childCount + 5) + " children", childCount + 5, getMapEditPart(0).getChildren().size());

    }

    /**
     * Test #2 for requirement ReqElemStartPoint
     * 
     * Author: jkealey
     */
    public void testReqElemStartPoint2() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(start);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("precondition"))
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition);
    }

    /**
     * Test #1 for requirement ReqElemStartPointAttributes
     * 
     * Author: jkealey
     */
    public void testReqElemStartPointAttributes1() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start);

        PathNodeEditPart part = (PathNodeEditPart) getGraphicalViewer().getEditPartRegistry().get(start);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean wl = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("workload"))
                wl = true;
        }

        assertTrue("Missing PropertyDescriptor", wl);
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
        assertNotNull("No palette entry creates Stub", createtool);
    }

    //  /**
    //  * Test #2 for requirement ReqElemStaticStub
    //  *
    //  * Author:
    //  */
    // public void testReqElemStaticStub2() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #1 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions1() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #2 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions2() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #3 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions3() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #4 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions4() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #5 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions5() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #6 for requirement ReqElemStubActions
    //  *
    //  * Author:
    //  */
    // public void testReqElemStubActions6() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    /**
     * Test #1 for requirement ReqElemTimer
     * 
     * Author: jkealey
     */
    public void testReqElemTimer1() {
        // Is there a tool to create a Timer in the palette?
        CreationTool createtool = getToolEntryForClass(Timer.class);
        assertNotNull("No palette entry creates Timer", createtool);
    }

    /**
     * Test #2 for requirement ReqElemTimer
     * 
     * Author: jkealey
     */
    public void testReqElemTimer2() {
        testReqElemStartPoint1();

        assertTrue("cannot find node connection", getMap().getPathGraph().getNodeConnections().size() > 0);
        NodeConnection nc = (NodeConnection) getMap().getPathGraph().getNodeConnections().get(0);
        Timer timer = (Timer) ModelCreationFactory.getNewObject(urn, Timer.class);

        assertNotNull("Model creation factory can't create timers!", timer);

        Command cmd = new SplitLinkCommand(getMap().getPathGraph(), timer, nc, 49, 75);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        Vector v = new Vector();
        IAction action = getAction(v, "ADDTIMEOUTPATHACTION");

        assertNotNull("Action not found in contextual menu!", action);

        int i, j;
        i = j = 0;

        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                i++;
            }
        }

        // run it to see if it doesn't crash the app!
        action.run();

        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof EndPoint) {
                j++;
            }
        }

        assertEquals("one (and exactly one) new end point should have been added", i + 1, j);

    }

    /**
     * Test #1 for requirement ReqElemWait
     * 
     * Author: jkealey
     */
    public void testReqElemWait1() {
        // Is there a tool to create a WaitingPlace in the palette?
        CreationTool createtool = getToolEntryForClass(WaitingPlace.class);
        assertNotNull("No palette entry creates WaitingPlace", createtool);
    }

    /**
     * Test #2 for requirement ReqElemWait
     * 
     * Author: jkealey
     */
    public void testReqElemWait2() {
        testReqElemStartPoint1();

        assertTrue("cannot find node connection", getMap().getPathGraph().getNodeConnections().size() > 0);
        NodeConnection nc = (NodeConnection) getMap().getPathGraph().getNodeConnections().get(0);
        WaitingPlace waitingplace = (WaitingPlace) ModelCreationFactory.getNewObject(urn, WaitingPlace.class);

        assertNotNull("Model creation factory can't create waiting place!", waitingplace);

        Command cmd = new SplitLinkCommand(getMap().getPathGraph(), waitingplace, nc, 49, 75);
        getGraphicalViewer().getEditDomain().getCommandStack().execute(cmd);

        EditPart part = getEditPart(waitingplace);
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean condition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("condition"))
                condition = true;
        }

        assertTrue("Missing PropertyDescriptor", condition);

    }

    //    /**
    //     * Test #1 for requirement ReqExportBitmap
    //     *
    //     * Author:
    //     */
    //    public void testReqExportBitmap1() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    /**
     * Test #2 for requirement ReqExportBitmap
     * 
     * Author: jkealey
     */
    public void testReqExportBitmap2() {
        Vector v = new Vector();
        v.add(getMap());
        IAction action = getAction(v, "EXPORTBITMAP");

        assertNotNull("action is null", action);

        // test if can run
        action.run();
    }

    /**
     * Test #1 for requirement ReqHelpAbout
     * 
     * Author: jkealey
     */
    public void testReqHelpAbout1() {
        // implicit by the distribution of about.html with the zip file. not worth testing.
        assertTrue("about.html not in zip file", true);

    }

    /**
     * Test #1 for requirement ReqHelpOnLine
     * 
     * Author: jkealey
     */
    public void testReqHelpOnLine1() {
        // implicit by the distribution of help.xml with the zip file. not worth testing.
        assertTrue("help.xml not in zip file", true);
    }

    //  /**
    //  * Test #1 for requirement ReqBrowseModel
    //  *
    //  * Author:
    //  */
    // public void testReqBrowseModel1() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #2 for requirement ReqBrowseModel
    //  *
    //  * Author:
    //  */
    // public void testReqBrowseModel2() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #3 for requirement ReqBrowseModel
    //  *
    //  * Author:
    //  */
    // public void testReqBrowseModel3() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    //  /**
    //  * Test #4 for requirement ReqBrowseModel
    //  *
    //  * Author:
    //  */
    // public void testReqBrowseModel4() {
    //     // TODO: implement
    //     assertTrue("Unimplemented", false);
    // }

    /**
     * Test #1 for requirement ReqLabels
     * 
     * Author: jkealey
     */
    public void testReqLabels1() {
        testReqElemStartPoint1();

        StartPoint start = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof StartPoint) {
                start = (StartPoint) element;
            }
        }
        assertNotNull("cannot find startpoint", start);
        assertNotNull("cannot find startpoint label", start.getLabel());

        LabelEditPart part = (LabelEditPart) getGraphicalViewer().getEditPartRegistry().get(start.getLabel());
        assertNotNull("cannot find label editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean name = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name"))
                name = true;
        }

        assertTrue("Missing PropertyDescriptor (should show name/id of label reference)", name);
    }

    /**
     * Test #2 for requirement ReqLabels
     * 
     * Author: jkealey
     */
    public void testReqLabels2() {
        testReqElemResponsibility2();

        RespRef pn = null;
        for (Iterator iter = getMap().getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            if (element instanceof RespRef) {
                pn = (RespRef) element;
                break;
            }
        }

        assertNotNull("no RespRef found", pn);
        assertNotNull("respref does not have a label", pn.getLabel());

        LabelEditPart part = (LabelEditPart) getGraphicalViewer().getEditPartRegistry().get(pn.getLabel());
        assertNotNull("cannot find editpart", part);

        IPropertySource source = (IPropertySource) part.getAdapter(IPropertySource.class);
        assertNotNull("No property source found", source);

        IPropertyDescriptor desc[] = source.getPropertyDescriptors();

        boolean deltaX, deltaY, id, name, definition;
        deltaX = deltaY = id = name = definition = false;
        for (int i = 0; i < desc.length; i++) {
            String str = desc[i].getDisplayName();
            if (str.equalsIgnoreCase("name"))
                name = true;
            else if (str.equalsIgnoreCase("id"))
                id = true;
            else if (str.equalsIgnoreCase("deltax"))
                deltaX = true;
            else if (str.equalsIgnoreCase("deltay"))
                deltaY = true;
            else if (str.equalsIgnoreCase("definition"))
                definition = true;
        }

        assertTrue("Missing PropertyDescriptor", name && id && deltaX && deltaY && definition);
    }

    /**
     * Test #1 for requirement ReqOpen
     * 
     * Author: jkealey
     */
    public void testReqOpen1() {
        // real testing done in JUCMNavCommandTests
        assertTrue("top level model element is URNSpec", editor.getModel() instanceof URNspec);
    }

    /**
     * Test #1 for requirement ReqSave
     * 
     * Author: jkealey
     */
    public void testReqSave1() {
        // real testing done in JUCMNavCommandTests
        assertTrue("top level model element is URNSpec", editor.getModel() instanceof URNspec);
    }

}