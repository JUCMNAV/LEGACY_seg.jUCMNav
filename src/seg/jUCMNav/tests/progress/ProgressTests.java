package seg.jUCMNav.tests.progress;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import junit.framework.TestCase;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.ConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.editparts.MapAndPathGraphEditPart;
import seg.jUCMNav.editpolicies.layout.MapAndPathGraphXYLayoutEditPolicy;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.views.EObjectPropertySource;
import ucm.map.ComponentRef;
import ucm.map.Map;
import urn.URNspec;
import urncore.ComponentKind;

/**
 * Created 2005-04-25
 *  
 */
public class ProgressTests extends TestCase {

    // internal elements shared by all tests.
    private UcmPaletteRoot paletteroot;
    private ConnectionOnBottomRootEditPart root;
    private URNspec urn;
    private ScrollingGraphicalViewer viewer;

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

    public Map getMap() {
        return (Map) urn.getUcmspec().getMaps().get(0);
    }

    public MapAndPathGraphEditPart getMapEditPart() {
        return (MapAndPathGraphEditPart) root.getChildren().get(0);
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
        List l = paletteroot.getChildren();
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
     * Setup generic environment for our progress tests. Requires the junit tests be run as Eclipse Plug-in Tests and not the standard kind (must run under
     * Eclipse otherwise resource bundles aren't loaded, etc.
     * 
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        // generate a top level model element
        urn = (URNspec) ModelCreationFactory.getNewObject(URNspec.class);

        // generate the necessary viewers, editparts and palette.
        viewer = (ScrollingGraphicalViewer) new ScrollingGraphicalViewer();
        root = new ConnectionOnBottomRootEditPart();
        paletteroot = new UcmPaletteRoot();

        // link all of the above together
        viewer.setRootEditPart(root);
        viewer.setEditPartFactory(new GraphicalEditPartFactory(getMap()));
        viewer.setEditDomain(new EditDomain());
        viewer.getEditDomain().setPaletteRoot(paletteroot);
        viewer.setContents(getMap());
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
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
        assertEquals("MapAndPathGraphEditPart should not have any children", 0, getMapEditPart().getChildren().size());

        // simulate a CreateRequest that we would have liked to have obtained from the palette
        CreateRequest cr = getCreateRequest(new ModelCreationFactory(ComponentRef.class, ComponentKind.TEAM), new Point(10, 100));
        assertNotNull("Unable to build create request", cr);

        // create a command using this CreateRequest. Note that this is a compound command that not only creates the component but positions it properly.
        Command cmd = (Command) getMapEditPart().getCommand(cr);
        assertNotNull("Can't get command to obtain a new ComponentRef", cmd);

        // execute the command, adding the componentref to the model
        viewer.getEditDomain().getCommandStack().execute(cmd);

        // because this test is not hooked up as a command stack change listener
        // JK: I'm not even sure how this should be done but we should do it.
        getMapEditPart().refreshChildren();

        // verify that both the componentref and component element have been added in the model.
        assertEquals("No component added to model", 1, urn.getUrndef().getComponents().size());
        assertEquals("No component ref added to model", 1, getMap().getCompRefs().size());

        // verify that the edit part tree has changed.
        assertEquals("MapAndPathGraphEditPart should have exactly one child", 1, getMapEditPart().getChildren().size());
    }

    /**
     * Test #2 for requirement: ReqComp
     * 
     * Author: jkealey
     */
    public void testReqComp2() {

        // create the component ref that will be used for testing.
        ComponentRef cr = (ComponentRef) ModelCreationFactory.getNewObject(ComponentRef.class, ComponentKind.TEAM);
        // to be able to build the property source for the compDef, our component ref must be inside a map.
        Command cmd = new AddComponentRefCommand(getMap(), cr);
        viewer.getEditDomain().getCommandStack().execute(cmd);
        // refresh the edit part tree because we aren't hooked up to the command stack
        getMapEditPart().refreshChildren();

        // create a property source on the component ref
        EObjectPropertySource eops = new EObjectPropertySource(cr);
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
        ComponentRefEditPart creditpart = (ComponentRefEditPart) getMapEditPart().getChildren().get(0);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart().getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart,
                new Rectangle(100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintComponentRefCommand ",
                cmd instanceof SetConstraintComponentRefCommand && ((SetConstraintComponentRefCommand) cmd).canExecute());

        // verify that we can't move/resize fixed components.
        cr.setFixed(true);
        cmd = ((MapAndPathGraphXYLayoutEditPolicy) getMapEditPart().getEditPolicy(EditPolicy.LAYOUT_ROLE)).createChangeConstraintCommand(creditpart,
                new Rectangle(100, 200, 300, 400));
        assertTrue("MapAndPathGraphXYLayoutEditPolicy doesn't return a valid SetConstraintComponentRefCommand ",
                cmd instanceof SetConstraintComponentRefCommand && !((SetConstraintComponentRefCommand) cmd).canExecute());

    }

//  /**
//  * Test #1 for requirement ReqCompCompBind
//  * 
//  * Author:
//  */
// public void testReqCompCompBind1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqCompCompBind
//  * 
//  * Author:
//  */
// public void testReqCompCompBind2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqCompCompUnbind
//  * 
//  * Author:
//  */
// public void testReqCompCompUnbind1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqCompCompUnbind
//  * 
//  * Author:
//  */
// public void testReqCompCompUnbind2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqCompPathBind
//  * 
//  * Author:
//  */
// public void testReqCompPathBind1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqCompPathBind
//  * 
//  * Author:
//  */
// public void testReqCompPathBind2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqCompPathUnbind
//  * 
//  * Author:
//  */
// public void testReqCompPathUnbind1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqCompPathUnbind
//  * 
//  * Author:
//  */
// public void testReqCompPathUnbind2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqConnections
//  * 
//  * Author:
//  */
// public void testReqConnections1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqConnections
//  * 
//  * Author:
//  */
// public void testReqConnections2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqConnections
//  * 
//  * Author:
//  */
// public void testReqConnections3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemAndFork
//  * 
//  * Author:
//  */
// public void testReqElemAndFork1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemAndFork
//  * 
//  * Author:
//  */
// public void testReqElemAndFork2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemAndFork
//  * 
//  * Author:
//  */
// public void testReqElemAndFork3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemAndJoin
//  * 
//  * Author:
//  */
// public void testReqElemAndJoin1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemAndJoin
//  * 
//  * Author:
//  */
// public void testReqElemAndJoin2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemAndJoin
//  * 
//  * Author:
//  */
// public void testReqElemAndJoin3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemDelete
//  * 
//  * Author:
//  */
// public void testReqElemDelete1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemDelete
//  * 
//  * Author:
//  */
// public void testReqElemDelete2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemDelete
//  * 
//  * Author:
//  */
// public void testReqElemDelete3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #4 for requirement ReqElemDelete
//  * 
//  * Author:
//  */
// public void testReqElemDelete4() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #5 for requirement ReqElemDelete
//  * 
//  * Author:
//  */
// public void testReqElemDelete5() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemDirectionArrow
//  * 
//  * Author:
//  */
// public void testReqElemDirectionArrow1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemDirectionArrow
//  * 
//  * Author:
//  */
// public void testReqElemDirectionArrow2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemDynamicStub
//  * 
//  * Author:
//  */
// public void testReqElemDynamicStub1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemDynamicStub
//  * 
//  * Author:
//  */
// public void testReqElemDynamicStub2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemEmptyPoint
//  * 
//  * Author:
//  */
// public void testReqElemEmptyPoint1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemEmptyPoint
//  * 
//  * Author:
//  */
// public void testReqElemEmptyPoint2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemEndPoint
//  * 
//  * Author:
//  */
// public void testReqElemEndPoint1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemEndPoint
//  * 
//  * Author:
//  */
// public void testReqElemEndPoint2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemEndPoint
//  * 
//  * Author:
//  */
// public void testReqElemEndPoint3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemOrFork
//  * 
//  * Author:
//  */
// public void testReqElemOrFork1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemOrFork
//  * 
//  * Author:
//  */
// public void testReqElemOrFork2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemOrFork
//  * 
//  * Author:
//  */
// public void testReqElemOrFork3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemOrJoin
//  * 
//  * Author:
//  */
// public void testReqElemOrJoin1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemOrJoin
//  * 
//  * Author:
//  */
// public void testReqElemOrJoin2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemOrJoin
//  * 
//  * Author:
//  */
// public void testReqElemOrJoin3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #4 for requirement ReqElemOrJoin
//  * 
//  * Author:
//  */
// public void testReqElemOrJoin4() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemResponsibility
//  * 
//  * Author:
//  */
// public void testReqElemResponsibility1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemResponsibility
//  * 
//  * Author:
//  */
// public void testReqElemResponsibility2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemStartPoint
//  * 
//  * Author:
//  */
// public void testReqElemStartPoint1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemStartPoint
//  * 
//  * Author:
//  */
// public void testReqElemStartPoint2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemStartPointAttributes
//  * 
//  * Author:
//  */
// public void testReqElemStartPointAttributes1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemStaticStub
//  * 
//  * Author:
//  */
// public void testReqElemStaticStub1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemStaticStub
//  * 
//  * Author:
//  */
// public void testReqElemStaticStub2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #4 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions4() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #5 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions5() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #6 for requirement ReqElemStubActions
//  * 
//  * Author:
//  */
// public void testReqElemStubActions6() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemTimer
//  * 
//  * Author:
//  */
// public void testReqElemTimer1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemTimer
//  * 
//  * Author:
//  */
// public void testReqElemTimer2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqElemWait
//  * 
//  * Author:
//  */
// public void testReqElemWait1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqElemWait
//  * 
//  * Author:
//  */
// public void testReqElemWait2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqExportBitmap
//  * 
//  * Author:
//  */
// public void testReqExportBitmap1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqExportBitmap
//  * 
//  * Author:
//  */
// public void testReqExportBitmap2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqHelpAbout
//  * 
//  * Author:
//  */
// public void testReqHelpAbout1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqHelpOnLine
//  * 
//  * Author:
//  */
// public void testReqHelpOnLine1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqOpen 
//  * 
//  * Author:
//  */
// public void testReqOpen 1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqSave
//  * 
//  * Author:
//  */
// public void testReqSave1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqSaveBackup
//  * 
//  * Author:
//  */
// public void testReqSaveBackup1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqBrowseHistory
//  * 
//  * Author:
//  */
// public void testReqBrowseHistory1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqBrowseModel
//  * 
//  * Author:
//  */
// public void testReqBrowseModel1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqBrowseModel
//  * 
//  * Author:
//  */
// public void testReqBrowseModel2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #3 for requirement ReqBrowseModel
//  * 
//  * Author:
//  */
// public void testReqBrowseModel3() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #4 for requirement ReqBrowseModel
//  * 
//  * Author:
//  */
// public void testReqBrowseModel4() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #1 for requirement ReqLabels
//  * 
//  * Author:
//  */
// public void testReqLabels1() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }

//  /**
//  * Test #2 for requirement ReqLabels
//  * 
//  * Author:
//  */
// public void testReqLabels2() {
//     //  TODO: implement
//     assertTrue("Unimplemented", false);
// }


}