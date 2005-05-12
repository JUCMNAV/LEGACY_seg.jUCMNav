package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * Given an empty node surrounded by empty nodes, cut the path by replacing the previous one with an end point and the next one by a start point. delete the
 * current empty node. Created 2005-03-21
 * 
 * @author Etienne Tremblay, jkealey
 */
public class CutPathCommand extends Command implements JUCMNavCommand {
    /*
     * Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
     * 
     * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
     */
    private PathGraph diagram;
    private EmptyPoint emptyPoint;

    private PathNode nextPoint;
    private PathNode previousPoint;

    private StartPoint newStart;
    private EndPoint newEnd;

    private NodeConnection connToPrev1;
    private NodeConnection connToNext1;

    private NodeConnection connToPrev2;
    private NodeConnection connToNext2;

    public CutPathCommand(PathGraph pg, EmptyPoint ep) {
        this.diagram = pg;
        this.emptyPoint = ep;
        setLabel("Cut Path");
    }

    public CutPathCommand() {
        super();
        setLabel("Cut Path");
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
        b = p != null && p instanceof EmptyPoint;
        if (b) {
            EmptyPoint ep = (EmptyPoint) p;
            b = ((NodeConnection) ep.getSucc().get(0)).getTarget() instanceof EmptyPoint;

            b = b && ((NodeConnection) ep.getPred().get(0)).getSource() instanceof EmptyPoint;
        }

        return b;

    }

    /**
     * We don't want to execute this command if the target is not between two empty nodes. We might want to generate these automatically later on.
     */
    public boolean canExecute() {

        return canExecute(emptyPoint);

    }

    /**
     * Create the model elements and invoke redo();
     */
    public void execute() {

        /*
         * Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        newStart = (StartPoint) ModelCreationFactory.getNewObject((URNspec)diagram.eContainer().eContainer().eContainer(), StartPoint.class);
        newEnd = (EndPoint) ModelCreationFactory.getNewObject((URNspec)diagram.eContainer().eContainer().eContainer(),EndPoint.class);

        connToNext1 = (NodeConnection) emptyPoint.getSucc().get(0);
        nextPoint = connToNext1.getTarget();

        connToPrev1 = (NodeConnection) emptyPoint.getPred().get(0);
        previousPoint = connToPrev1.getSource();

        connToPrev2 = (NodeConnection) previousPoint.getPred().get(0);
        connToNext2 = (NodeConnection) nextPoint.getSucc().get(0);

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
        diagram.getPathNodes().remove(emptyPoint);
        diagram.getPathNodes().remove(previousPoint);
        diagram.getPathNodes().remove(nextPoint);

        diagram.getNodeConnections().remove(connToPrev1);
        diagram.getNodeConnections().remove(connToNext1);

        diagram.getPathNodes().add(newStart);
        diagram.getPathNodes().add(newEnd);

        connToPrev2.setTarget(newEnd);
        connToNext2.setSource(newStart);
        testPostConditions();
    }

    /**
     * Undo model changes
     */
    public void undo() {
        //      reconnect the lazily disconnected branch
        testPostConditions();
        connToPrev2.setTarget(previousPoint);
        connToNext2.setSource(nextPoint);

        diagram.getPathNodes().remove(newStart);
        diagram.getPathNodes().remove(newEnd);

        diagram.getNodeConnections().add(connToPrev1);
        diagram.getNodeConnections().add(connToNext1);

        diagram.getPathNodes().add(emptyPoint);
        diagram.getPathNodes().add(previousPoint);
        diagram.getPathNodes().add(nextPoint);
        testPreConditions();
    }

    /**
     * @return Returns the emptyPoint.
     */
    public EmptyPoint getEmptyPoint() {
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
    public PathGraph getDiagram() {
        return diagram;
    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(PathGraph diagram) {
        this.diagram = diagram;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        /*
         * Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        assert diagram != null : "pre diagram";
        assert emptyPoint != null : "pre emptyPoint";
        assert canExecute() : "pre canExecute (surrounded by empty nodes?)";

        assert diagram.getPathNodes().contains(emptyPoint) : "pre graph contains emptyPoint";
        assert previousPoint != null && diagram.getPathNodes().contains(previousPoint) : "pre graph contains previousPoint";
        assert nextPoint != null && diagram.getPathNodes().contains(nextPoint) : "pre graph contains nextPoint";
        assert newStart != null && !diagram.getPathNodes().contains(newStart) : "pre graph doesn't contain newStart";
        assert newEnd != null && !diagram.getPathNodes().contains(newEnd) : "pre graph doesn't contain newEnd";

        assert connToPrev2 != null && diagram.getNodeConnections().contains(connToPrev2) : "pre graph contains connToPrev2";
        assert connToPrev1 != null && diagram.getNodeConnections().contains(connToPrev1) : "pre graph contains connToPrev1";
        assert connToNext1 != null && diagram.getNodeConnections().contains(connToNext1) : "pre graph contains connToNext1";
        assert connToNext2 != null && diagram.getNodeConnections().contains(connToNext2) : "pre graph contains connToNext2";

        assert previousPoint.getPred().size() == 1 && previousPoint.getSucc().size() == 1 : "pre previous point has 1 in, 1 out";
        assert emptyPoint.getPred().size() == 1 && emptyPoint.getSucc().size() == 1 : "pre empty point has 1 in, 1 out";
        assert nextPoint.getPred().size() == 1 && nextPoint.getSucc().size() == 1 : "pre nextPoint has 1 in, 1 out";
        assert newStart.getPred().size() == 0 && newStart.getSucc().size() == 0 : "pre new start has 0 in, 0 out";
        assert newEnd.getPred().size() == 0 && newEnd.getSucc().size() == 0 : "pre new end has 0 in, 0 out";

        assert connToPrev2.getTarget() == previousPoint : "pre link1";
        assert previousPoint.getSucc().get(0) == connToPrev1 : "pre link2";
        assert connToPrev1.getTarget() == emptyPoint : "pre link3";
        assert emptyPoint.getSucc().get(0) == connToNext1 : "pre link4";
        assert connToNext1.getTarget() == nextPoint : "pre link5";
        assert nextPoint.getSucc().get(0) == connToNext2 : "pre link6";
        
        assert newStart.getX() == nextPoint.getX() && newStart.getY() == nextPoint.getY() : "pre new start position";
        assert newEnd.getX() == previousPoint.getX() && newEnd.getY() == previousPoint.getY() : "pre new end position";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        /*
         * Before: ... ---[connToPrev2]---(previousPoint)--[connToPrev1]---(emptyPoint)---[connToNext1]---(nextPoint)---[connToNext2]--- ...
         * 
         * After: ... ---[connToPrev2]---(newEnd) (newStart)---[connToNext2]--- ...
         */
        assert diagram != null : "post diagram";
        assert emptyPoint != null : "post emptyPoint";

        assert !diagram.getPathNodes().contains(emptyPoint) : "post graph doesn't contain emptyPoint";
        assert previousPoint != null && !diagram.getPathNodes().contains(previousPoint) : "post graph doesn't contain previousPoint";
        assert nextPoint != null && !diagram.getPathNodes().contains(nextPoint) : "post graph doesn't contain nextPoint";
        assert newStart != null && diagram.getPathNodes().contains(newStart) : "post graph contains newStart";
        assert newEnd != null && diagram.getPathNodes().contains(newEnd) : "post graph contains newEnd";

        assert connToPrev2 != null && diagram.getNodeConnections().contains(connToPrev2) : "post graph contains connToPrev2";
        assert connToPrev1 != null && !diagram.getNodeConnections().contains(connToPrev1) : "post graph doesn't contain connToPrev1";
        assert connToNext1 != null && !diagram.getNodeConnections().contains(connToNext1) : "post graph doesn't contain connToNext1";
        assert connToNext2 != null && diagram.getNodeConnections().contains(connToNext2) : "post graph contains connToNext2";

        assert previousPoint.getPred().size() == 0 && previousPoint.getSucc().size() == 1 : "post previous point has 0 in, 1 out";
        assert emptyPoint.getPred().size() == 1 && emptyPoint.getSucc().size() == 1 : "post empty point has 1 in, 1 out";
        assert nextPoint.getPred().size() == 1 && nextPoint.getSucc().size() == 0 : "post nextPoint has 1 in, 0 out";
        assert newStart.getPred().size() == 0 && newStart.getSucc().size() == 1 : "post new start has 0 in, 1 out";
        assert newEnd.getPred().size() == 1 && newEnd.getSucc().size() == 0 : "post new end has 1 in, 0 out";

        assert connToPrev2.getTarget() == newEnd : "post link1";
        assert newStart.getSucc().get(0) == connToNext2 : "post link2";
        
        assert newStart.getX() == nextPoint.getX() && newStart.getY() == nextPoint.getY() : "post new start position";
        assert newEnd.getX() == previousPoint.getX() && newEnd.getY() == previousPoint.getY() : "post new end position";        

    }
    public EndPoint getNewEnd() {
        return newEnd;
    }
    public StartPoint getNewStart() {
        return newStart;
    }
}