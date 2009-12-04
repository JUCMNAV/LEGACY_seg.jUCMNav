package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * Given an empty node surrounded by empty nodes, cut the path by replacing the previous one with an end point and the next one by a start point. deletes the
 * current empty node and its surrounding connections.
 * 
 * Alternate usage: Pass a NodeConnection to be cut; must still be surrounded by empty nodes.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class CutPathCommand extends Command implements JUCMNavCommand {
    /*
     * Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
     * 
     * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
     */
    private UCMmap diagram;
    private PathNode emptyPoint;
    private PathNode nextPoint;
    private PathNode previousPoint;
    private ComponentRef parentEmpty, parentPrevious, parentNext;
    private StartPoint newStart;
    private EndPoint newEnd;
    private NodeConnection connToPrev1;
    private NodeConnection connToNext1;
    private NodeConnection connToPrev2;
    private NodeConnection connToNext2;
    private NodeConnection targetConn;

    /**
     * 
     * @param pg
     *            the pathgraph containing ep
     * @param ep
     *            the emptypoint to split the connection on
     */
    public CutPathCommand(UCMmap pg, PathNode ep) {
        this.diagram = pg;
        this.emptyPoint = ep;
        setLabel(Messages.getString("CutPathCommand.cutPath")); //$NON-NLS-1$
    }

    /**
     * 
     * @param pg
     *            the pathgraph containing nc
     * 
     * @param nc
     *            the nodeconnection to split the connection on
     */
    public CutPathCommand(UCMmap pg, NodeConnection nc) {
        this.diagram = pg;
        this.targetConn = nc;
        setLabel(Messages.getString("CutPathCommand.cutPath")); //$NON-NLS-1$
    }

    /**
     * 
     * Use this constructor to pass arguments in a delayed fashion.
     * 
     */
    public CutPathCommand() {
        super();
        setLabel(Messages.getString("CutPathCommand.cutPath")); //$NON-NLS-1$
    }

    /**
     * Static method that contains the business logic in knowing if we can execute a cut path command on a certain Object Object must be of type EmptyPoint and
     * must be surrounded by EmptyPoints.
     * 
     * @param p
     *            The EmptyPoint upon which we want to cut the path.
     * @return if you create a command using this object, you will be able to execute it.
     */
    public static boolean canExecute(Object p) {
        boolean b;
        b = p != null && (p instanceof EmptyPoint || p instanceof DirectionArrow);
        if (b) {
            PathNode ep = (PathNode) p;
            if (ep.getSucc().size() > 0 && ep.getPred().size() > 0) {
                PathNode pn = (PathNode) ((NodeConnection) ep.getSucc().get(0)).getTarget();
                b = (pn instanceof EmptyPoint || pn instanceof DirectionArrow);
                // check if no connects
                b = b && pn.getPred().size() == 1 && pn.getSucc().size() == 1;

                pn = (PathNode) ((NodeConnection) ep.getPred().get(0)).getSource();
                b = b && (pn instanceof EmptyPoint || pn instanceof DirectionArrow);
                // check if no connects
                b = b && pn.getPred().size() == 1 && pn.getSucc().size() == 1;

            } else
                return false;
        }
        if (b == false && p instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) p;
            b = nc.getTarget() instanceof EmptyPoint || nc.getTarget() instanceof DirectionArrow;
            // check if no connects
            b = b && nc.getTarget().getSucc().size() == 1 && nc.getTarget().getPred().size() == 1;
            b = b && (nc.getSource() instanceof EmptyPoint || nc.getSource() instanceof DirectionArrow);
            // check if no connects
            b = b && nc.getSource().getSucc().size() == 1 && nc.getSource().getPred().size() == 1;
        }

        return b;

    }

    /**
     * We don't want to execute this command if the target is not between two empty nodes. We might want to generate these automatically later on.
     */
    public boolean canExecute() {

        if (targetConn == null)
            return canExecute(emptyPoint);
        else
            return canExecute(targetConn);

    }

    /**
     * Create the model elements and invoke redo();
     */
    public void execute() {

        /*
         * (targetConn==null) Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * (targetConn!=null) Before: ... ---[connToPrev2]---(previousPoint)--[targetConn]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        URNspec urn = diagram.getUrndefinition().getUrnspec();
        newStart = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);
        newEnd = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);

        if (targetConn == null) {
            connToNext1 = (NodeConnection) emptyPoint.getSucc().get(0);
            nextPoint = (PathNode) connToNext1.getTarget();

            connToPrev1 = (NodeConnection) emptyPoint.getPred().get(0);
            previousPoint = (PathNode) connToPrev1.getSource();
        } else {
            nextPoint = (PathNode) targetConn.getTarget();
            previousPoint = (PathNode) targetConn.getSource();
        }

        connToPrev2 = (NodeConnection) previousPoint.getPred().get(0);
        connToNext2 = (NodeConnection) nextPoint.getSucc().get(0);

        if (emptyPoint != null) {
            parentEmpty = (ComponentRef) emptyPoint.getContRef();
        }
        parentPrevious = (ComponentRef) previousPoint.getContRef();
        parentNext = (ComponentRef) nextPoint.getContRef();

        newStart.setX(nextPoint.getX());
        newStart.setY(nextPoint.getY());
        newEnd.setX(previousPoint.getX());
        newEnd.setY(previousPoint.getY());

        redo();
    }

    /**
     * Change the model
     */
    public void redo() {
        // lazily disconnect the branch
        testPreConditions();
        diagram.getNodes().remove(previousPoint);
        diagram.getNodes().remove(nextPoint);

        if (targetConn == null) {
            diagram.getNodes().remove(emptyPoint);
            diagram.getConnections().remove(connToPrev1);
            diagram.getConnections().remove(connToNext1);
        } else {
            diagram.getConnections().remove(targetConn);
        }

        diagram.getNodes().add(newStart);
        diagram.getNodes().add(newEnd);

        newStart.setContRef(ParentFinder.findParent(diagram, newStart.getX(), newStart.getY()));
        newEnd.setContRef(ParentFinder.findParent(diagram, newEnd.getX(), newEnd.getY()));

        connToPrev2.setTarget(newEnd);
        connToNext2.setSource(newStart);

        if (emptyPoint != null) {
            emptyPoint.setContRef(null);
        }
        previousPoint.setContRef(null);
        nextPoint.setContRef(null);

        testPostConditions();
    }

    /**
     * Undo model changes
     */
    public void undo() {
        // reconnect the lazily disconnected branch
        testPostConditions();
        connToPrev2.setTarget(previousPoint);
        connToNext2.setSource(nextPoint);

        diagram.getNodes().remove(newStart);
        diagram.getNodes().remove(newEnd);

        if (targetConn == null) {
            diagram.getConnections().add(connToPrev1);
            diagram.getConnections().add(connToNext1);
            diagram.getNodes().add(emptyPoint);
        } else
            diagram.getConnections().add(targetConn);

        diagram.getNodes().add(previousPoint);
        diagram.getNodes().add(nextPoint);

        newStart.setContRef(null);
        newEnd.setContRef(null);

        if (emptyPoint != null) {
            emptyPoint.setContRef(parentEmpty);
        }
        previousPoint.setContRef(parentPrevious);
        nextPoint.setContRef(parentNext);

        testPreConditions();
    }

    /**
     * @return Returns the emptyPoint.
     */
    public PathNode getEmptyPoint() {
        return emptyPoint;
    }

    /**
     * @param emptyPoint
     *            The emptyPoint to set.
     */
    public void setEmptyPoint(EmptyPoint emptyPoint) {
        this.emptyPoint = emptyPoint;
    }

    /**
     * @return Returns the diagram.
     */
    public UCMmap getDiagram() {
        return diagram;
    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(UCMmap diagram) {
        this.diagram = diagram;
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        /*
         * Before (targetConn==null): ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * Before (targetConn!=null): ... ---[connToPrev2]---(previousPoint)---[targetConn]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        assert diagram != null : "pre diagram"; //$NON-NLS-1$
        assert canExecute() : "pre canExecute (surrounded by empty nodes?)"; //$NON-NLS-1$

        assert previousPoint != null && diagram.getNodes().contains(previousPoint) : "pre graph contains previousPoint"; //$NON-NLS-1$
        assert nextPoint != null && diagram.getNodes().contains(nextPoint) : "pre graph contains nextPoint"; //$NON-NLS-1$
        assert newStart != null && !diagram.getNodes().contains(newStart) : "pre graph doesn't contain newStart"; //$NON-NLS-1$
        assert newEnd != null && !diagram.getNodes().contains(newEnd) : "pre graph doesn't contain newEnd"; //$NON-NLS-1$

        assert connToPrev2 != null && diagram.getConnections().contains(connToPrev2) : "pre graph contains connToPrev2"; //$NON-NLS-1$
        assert connToNext2 != null && diagram.getConnections().contains(connToNext2) : "pre graph contains connToNext2"; //$NON-NLS-1$

        assert previousPoint.getPred().size() == 1 && previousPoint.getSucc().size() == 1 : "pre previous point has 1 in, 1 out"; //$NON-NLS-1$
        assert nextPoint.getPred().size() == 1 && nextPoint.getSucc().size() == 1 : "pre nextPoint has 1 in, 1 out"; //$NON-NLS-1$
        assert newStart.getPred().size() == 0 && newStart.getSucc().size() == 0 : "pre new start has 0 in, 0 out"; //$NON-NLS-1$
        assert newEnd.getPred().size() == 0 && newEnd.getSucc().size() == 0 : "pre new end has 0 in, 0 out"; //$NON-NLS-1$

        assert connToPrev2.getTarget() == previousPoint : "pre link1"; //$NON-NLS-1$
        assert nextPoint.getSucc().get(0) == connToNext2 : "pre link6"; //$NON-NLS-1$

        assert newStart.getX() == nextPoint.getX() && newStart.getY() == nextPoint.getY() : "pre new start position"; //$NON-NLS-1$
        assert newEnd.getX() == previousPoint.getX() && newEnd.getY() == previousPoint.getY() : "pre new end position"; //$NON-NLS-1$

        // if clicked on empty point
        if (targetConn == null) {
            assert emptyPoint != null : "pre emptyPoint"; //$NON-NLS-1$
            assert diagram.getNodes().contains(emptyPoint) : "pre graph contains emptyPoint"; //$NON-NLS-1$
            assert connToPrev1 != null && diagram.getConnections().contains(connToPrev1) : "pre graph contains connToPrev1"; //$NON-NLS-1$
            assert connToNext1 != null && diagram.getConnections().contains(connToNext1) : "pre graph contains connToNext1"; //$NON-NLS-1$
            assert emptyPoint.getPred().size() == 1 && emptyPoint.getSucc().size() == 1 : "pre empty point has 1 in, 1 out"; //$NON-NLS-1$
            assert previousPoint.getSucc().get(0) == connToPrev1 : "pre link2"; //$NON-NLS-1$
            assert connToPrev1.getTarget() == emptyPoint : "pre link3"; //$NON-NLS-1$
            assert emptyPoint.getSucc().get(0) == connToNext1 : "pre link4"; //$NON-NLS-1$
            assert connToNext1.getTarget() == nextPoint : "pre link5"; //$NON-NLS-1$

        } else { // clicked on node connection
            assert diagram.getConnections().contains(targetConn) : "pre graph contains targetConn"; //$NON-NLS-1$
            assert previousPoint.getSucc().get(0) == targetConn : "pre link 7"; //$NON-NLS-1$
            assert targetConn.getTarget() == nextPoint : "pre link 8"; //$NON-NLS-1$
        }
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        /*
         * Before (targetConn==null): ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * Before (targetConn!=null): ... ---[connToPrev2]---(previousPoint)---[targetConn]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        assert diagram != null : "post diagram"; //$NON-NLS-1$

        assert previousPoint != null && !diagram.getNodes().contains(previousPoint) : "post graph doesn't contain previousPoint"; //$NON-NLS-1$
        assert nextPoint != null && !diagram.getNodes().contains(nextPoint) : "post graph doesn't contain nextPoint"; //$NON-NLS-1$
        assert newStart != null && diagram.getNodes().contains(newStart) : "post graph contains newStart"; //$NON-NLS-1$
        assert newEnd != null && diagram.getNodes().contains(newEnd) : "post graph contains newEnd"; //$NON-NLS-1$

        assert connToPrev2 != null && diagram.getConnections().contains(connToPrev2) : "post graph contains connToPrev2"; //$NON-NLS-1$
        assert connToNext2 != null && diagram.getConnections().contains(connToNext2) : "post graph contains connToNext2"; //$NON-NLS-1$

        assert previousPoint.getPred().size() == 0 && previousPoint.getSucc().size() == 1 : "post previous point has 0 in, 1 out"; //$NON-NLS-1$
        assert nextPoint.getPred().size() == 1 && nextPoint.getSucc().size() == 0 : "post nextPoint has 1 in, 0 out"; //$NON-NLS-1$
        assert newStart.getPred().size() == 0 && newStart.getSucc().size() == 1 : "post new start has 0 in, 1 out"; //$NON-NLS-1$
        assert newEnd.getPred().size() == 1 && newEnd.getSucc().size() == 0 : "post new end has 1 in, 0 out"; //$NON-NLS-1$

        assert connToPrev2.getTarget() == newEnd : "post link1"; //$NON-NLS-1$
        assert newStart.getSucc().get(0) == connToNext2 : "post link2"; //$NON-NLS-1$

        assert newStart.getX() == nextPoint.getX() && newStart.getY() == nextPoint.getY() : "post new start position"; //$NON-NLS-1$
        assert newEnd.getX() == previousPoint.getX() && newEnd.getY() == previousPoint.getY() : "post new end position"; //$NON-NLS-1$

        if (targetConn == null) {
            assert emptyPoint != null : "post emptyPoint"; //$NON-NLS-1$
            assert !diagram.getNodes().contains(emptyPoint) : "post graph doesn't contain emptyPoint"; //$NON-NLS-1$
            assert connToPrev1 != null && !diagram.getConnections().contains(connToPrev1) : "post graph doesn't contain connToPrev1"; //$NON-NLS-1$
            assert connToNext1 != null && !diagram.getConnections().contains(connToNext1) : "post graph doesn't contain connToNext1"; //$NON-NLS-1$
            assert emptyPoint.getPred().size() == 1 && emptyPoint.getSucc().size() == 1 : "post empty point has 1 in, 1 out"; //$NON-NLS-1$
        } else {
            assert !diagram.getConnections().contains(targetConn) : "post graph doesn't contain targetConn"; //$NON-NLS-1$
        }
    }

    /**
     * 
     * @return the newly created end point
     */
    public EndPoint getNewEnd() {
        return newEnd;
    }

    /**
     * @return the newly created start point.
     */
    public StartPoint getNewStart() {
        return newStart;
    }
}