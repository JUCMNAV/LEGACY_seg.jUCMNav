package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Given an empty node surrounded by empty nodes, cut the path by replacing the previous one with an end point and the next one by a start point. delete the
 * current empty node. Created 2005-03-21
 * 
 * @author Etienne Tremblay
 */
public class CutPathCommand extends Command {

    private PathGraph diagram;
    private EmptyPoint emptyPoint;

    private PathNode nextPoint;
    private PathNode previousPoint;

    private StartPoint newStart;
    private EndPoint newEnd;

    private NodeConnection previousToNode;
    private NodeConnection nodeToNext;

    private NodeConnection toPrevious;
    private NodeConnection fromNext;

    public CutPathCommand() {
        super();
    }

    /** 
     * Static method that contains the business logic in knowing if we can execute a cut path command on a certain Object
     * Object must be of type EmptyPoint and must be surrounded by EmptyPoints.
     * 
     * @param p The EmptyPoint upon which we want to cut the path.
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
        MapFactory factory = MapFactory.eINSTANCE;

        newStart = factory.createStartPoint();
        newEnd = factory.createEndPoint();

        nodeToNext = (NodeConnection) emptyPoint.getSucc().get(0);
        nextPoint = nodeToNext.getTarget();

        previousToNode = (NodeConnection) emptyPoint.getPred().get(0);
        previousPoint = previousToNode.getSource();

        toPrevious = (NodeConnection) previousPoint.getPred().get(0);
        fromNext = (NodeConnection) nextPoint.getSucc().get(0);

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
        diagram.getPathNodes().remove(emptyPoint);
        diagram.getPathNodes().remove(previousPoint);
        diagram.getPathNodes().remove(nextPoint);

        diagram.getNodeConnections().remove(previousToNode);
        diagram.getNodeConnections().remove(nodeToNext);

        diagram.getPathNodes().add(newStart);
        diagram.getPathNodes().add(newEnd);

        toPrevious.setTarget(newEnd);
        fromNext.setSource(newStart);
    }

    /**
     * Undo model changes
     */
    public void undo() {
        toPrevious.setTarget(previousPoint);
        fromNext.setSource(nextPoint);

        diagram.getPathNodes().remove(newStart);
        diagram.getPathNodes().remove(newEnd);

        diagram.getNodeConnections().add(previousToNode);
        diagram.getNodeConnections().add(nodeToNext);

        diagram.getPathNodes().add(emptyPoint);
        diagram.getPathNodes().add(previousPoint);
        diagram.getPathNodes().add(nextPoint);
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
}