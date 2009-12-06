package seg.jUCMNav.model.commands.delete.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.DoesDisconnectImplyDelete;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * Given a PathNode, disconnect all relevant branches. Timer: Timeout path. Fork/join/stub: all paths. Other PathNode: none.
 * 
 * You can also pass in one particular branch to disconnect and the class will determine if it impacts any other branches.
 * 
 * Undo restores original branch order.
 * 
 * Does not handle getting rid of any special properties such as conditions on the deleted branches nor does it remove the PathNode from any other
 * relationships. Simply disconnects NodeConnections.
 * 
 * Especially assumes that synchronous/asynchronous connections have broken if necessary prior to this operation.
 * 
 * WARNING: MIGHT LEAVE MODEL IN INCONSISTENT STATE. Intended to be followed by RemoveUcmModelElementCommand if shouldDeleteNode==true
 * 
 * Originally in DeleteMultiNodeCommand. The code that checks to see if the PathNode should be deleted when a subset of its connections are removed is located
 * in DoesDisconnectImplyDelete.
 * 
 * @author jkealey
 * 
 */
public class DisconnectBranchesCommand extends Command implements JUCMNavCommand {
    // if the node has already been deleted, this will prevent the command from
    // being done/undone without breaking the stack.
    private boolean aborted = false;

    // needed to get access to the SplineConnection of incoming / outgoing node
    // connections.
    private Map editpartregistry;

    // list of incoming and outgoing node connections
    private Vector ncInBefore, ncOutBefore;
    private Vector ncInToRemove, ncOutToRemove;

    // list of new start points and end points.
    private List newStart, newEnd;

    // the pathgraph that contains the node.
    private UCMmap pg;

    // the pathnode to be deleted.
    private PathNode toDelete;

    // the URNspec which contains all the elements
    private URNspec urn;

    private boolean shouldDeleteNode = false;

    /**
     * Disconnects the specified node connections from the PathNode. Inserts new StartPoints/EndPoints on the middle of these node connections.
     * 
     * The node connection lists will not be respected if they imply the node must be completely removed.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param ncIn
     *            The node connections that enter this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * @param ncOut
     *            The node connections that exist this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * 
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     * 
     */
    public DisconnectBranchesCommand(PathNode toDelete, List ncIn, List ncOut, Map editpartregistry) {
        this.toDelete = toDelete;
        this.ncInToRemove = new Vector(ncIn);
        this.ncOutToRemove = new Vector(ncOut);
        this.editpartregistry = editpartregistry;
    }

    /**
     * Disconnects all node connections from the PathNode. Inserts new StartPoints/EndPoints on the middle of these node connections.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     * 
     */
    public DisconnectBranchesCommand(PathNode toDelete, Map editpartregistry) {
        this.toDelete = toDelete;
        this.editpartregistry = editpartregistry;
        this.shouldDeleteNode = true;
        // not saving node connections immediately, wait until execution.

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        initialize();
        redo();
    }

    /**
     * Given the list of incoming node connections, create end points and set them at the right place (mid node connection).
     */
    private void initEndPoints() {
        // create and initialize all new start points.
        newEnd = new Vector();
        for (Iterator iter = ncInToRemove.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            EndPoint ep = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);

            NodeConnectionEditPart nodePart = null;
            if (editpartregistry != null)
                nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                midPoint = nodePart.getMiddlePoint();
            } else {

                int x = toDelete.getX() - (toDelete.getX() - nc.getSource().getX()) / 2;
                int y = toDelete.getY() - (toDelete.getY() - nc.getSource().getY()) / 2;
                midPoint = new Point(x, y);
            }

            ep.setX(midPoint.x);
            ep.setY(midPoint.y);
            newEnd.add(ep);
        }
    }

    /**
     * Initializes all the internal variables, given a properly defined list of in/outs to be deleted.
     */
    private void initialize() {
        initVariables();
        if (aborted)
            return;
        if (!(toDelete instanceof Stub) && ncInBefore.size() == 1 && ncOutBefore.size() == 1) {
            ncOutToRemove.clear();
            ncInToRemove.clear();
        }

        initStartPoints();
        initEndPoints();

    }

    /**
     * Given the list of outgoing node connections, create start points and set them at the right place (mid node connection).
     */
    private void initStartPoints() {
        // create and initialize all new start points.
        newStart = new Vector();
        for (Iterator iter = ncOutToRemove.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            StartPoint sp = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);

            NodeConnectionEditPart nodePart = null;
            if (editpartregistry != null)
                nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                midPoint = nodePart.getMiddlePoint();
            } else {
                int x = toDelete.getX() - (toDelete.getX() - nc.getTarget().getX()) / 2;
                int y = toDelete.getY() - (toDelete.getY() - nc.getTarget().getY()) / 2;
                midPoint = new Point(x, y);
            }

            sp.setX(midPoint.x);
            sp.setY(midPoint.y);
            newStart.add(sp);
        }
    }

    /**
     * save pathgraph/urn and refresh affected node connections.
     */
    private void initVariables() {
        pg = (UCMmap) toDelete.getDiagram();

        this.ncInBefore = new Vector(toDelete.getPred());
        this.ncOutBefore = new Vector(toDelete.getSucc());

        if (shouldDeleteNode) {
            this.ncInToRemove = (Vector) this.ncInBefore.clone();
            this.ncOutToRemove = (Vector) this.ncOutBefore.clone();
        }

        DoesDisconnectImplyDelete.trimConnectNodeConnections(this.ncInBefore);
        DoesDisconnectImplyDelete.trimConnectNodeConnections(this.ncOutBefore);
        DoesDisconnectImplyDelete.trimConnectNodeConnections(this.ncInToRemove);
        DoesDisconnectImplyDelete.trimConnectNodeConnections(this.ncOutToRemove);

        if (pg == null || toDelete.getDiagram().getUrndefinition() == null) {
            aborted = true;
        } else
            urn = toDelete.getDiagram().getUrndefinition().getUrnspec();

    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();

        // rewire incoming and outgoing links.
        for (int i = 0; i < ncInToRemove.size(); i++) {
            NodeConnection nc = (NodeConnection) ncInToRemove.get(i);
            PathNode pn = (PathNode) newEnd.get(i);
            nc.setTarget(pn);
            pg.getNodes().add(pn);
            pn.setContRef(ParentFinder.getPossibleParent(pn));
        }

        for (int i = 0; i < ncOutToRemove.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOutToRemove.get(i);
            PathNode pn = (PathNode) newStart.get(i);
            nc.setSource(pn);
            pg.getNodes().add(pn);
            pn.setContRef(ParentFinder.getPossibleParent(pn));
        }

        testPostConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && pg != null && toDelete != null && ncInBefore != null && ncOutBefore != null && ncInToRemove != null && ncOutToRemove != null : "post something is null"; //$NON-NLS-1$

        for (int i = 0; i < ncInToRemove.size(); i++) {
            assert !toDelete.getPred().contains(ncInToRemove.get(i)) : "post pred nc not in pred " + i; //$NON-NLS-1$
            assert pg.getNodes().contains(newEnd.get(i)) : "post new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOutToRemove.size(); i++) {
            assert !toDelete.getSucc().contains(ncOutToRemove.get(i)) : "post succ nc not in succ " + i; //$NON-NLS-1$
            assert pg.getNodes().contains(newStart.get(i)) : "post new start not in graph " + i; //$NON-NLS-1$
        }

        // if (shouldDeleteNode) {
        // assert newStart != null && newEnd != null : "post start/end is null";
        // assert ncInToRemove.size() == newEnd.size() && ncOutToRemove.size() == newStart.size() : "post invalid sizes"; //$NON-NLS-1$
        //
        // int i = ncInBefore.size() + ncOutBefore.size() + ncInToRemove.size() + ncOutToRemove.size();
        // trimConnectNodeConnections(ncInBefore);
        // trimConnectNodeConnections(ncOutBefore);
        // trimConnectNodeConnections(ncInToRemove);
        // trimConnectNodeConnections(ncOutToRemove);
        // int j = ncInBefore.size() + ncOutBefore.size() + ncInToRemove.size() + ncOutToRemove.size();
        // assert i == j : "post it is assumed that when this command is run, the path node is not connected to anything if it is to be deleted!";
        // }
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && pg != null && toDelete != null && ncInBefore != null && ncOutBefore != null && ncInToRemove != null && ncOutToRemove != null : "pre something is null"; //$NON-NLS-1$
        for (int i = 0; i < ncInToRemove.size(); i++) {
            assert toDelete.getPred().contains(ncInToRemove.get(i)) : "pre pred nc not in pred " + i; //$NON-NLS-1$
            assert !pg.getNodes().contains(newEnd.get(i)) : "pre new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOutToRemove.size(); i++) {
            assert toDelete.getSucc().contains(ncOutToRemove.get(i)) : "pre succ nc not in succ " + i; //$NON-NLS-1$
            assert !pg.getNodes().contains(newStart.get(i)) : "pre new start not in graph " + i; //$NON-NLS-1$
        }

        // if (shouldDeleteNode) {
        // assert newStart != null && newEnd != null : "pre start/end null";
        // assert ncInToRemove.size() == newEnd.size() && ncOutToRemove.size() == newStart.size() : "pre invalid sizes"; //$NON-NLS-1$
        //
        // int i = ncInBefore.size() + ncOutBefore.size() + ncInToRemove.size() + ncOutToRemove.size();
        // trimConnectNodeConnections(ncInBefore);
        // trimConnectNodeConnections(ncOutBefore);
        // trimConnectNodeConnections(ncInToRemove);
        // trimConnectNodeConnections(ncOutToRemove);
        // int j = ncInBefore.size() + ncOutBefore.size() + ncInToRemove.size() + ncOutToRemove.size();
        // assert i == j : "pre it is assumed that when this command is run, the path node is not connected to anything if it is to be deleted!";
        // }

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        // rewire the incoming and outgoing connections.
        for (int i = 0; i < ncInBefore.size(); i++) {
            NodeConnection nc = (NodeConnection) ncInBefore.get(i);
            if (toDelete.getPred().indexOf(nc) != i) {
                // nc.setTarget(toDelete);
                // preserve order
                toDelete.getPred().add(i, nc);
                PathNode pn = (PathNode) newEnd.get(ncInToRemove.indexOf(nc));
                pg.getNodes().remove(pn);
                pn.setContRef(null);
            }
        }

        for (int i = 0; i < ncOutBefore.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOutBefore.get(i);
            if (toDelete.getSucc().indexOf(nc) != i) {
                // nc.setSource(toDelete);
                // preserve order
                toDelete.getSucc().add(i, nc);
                PathNode pn = (PathNode) newStart.get(ncOutToRemove.indexOf(nc));
                pg.getNodes().remove(pn);
                pn.setContRef(null);
            }
        }

        testPreConditions();
    }

}
