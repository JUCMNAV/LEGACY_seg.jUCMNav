package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * This command creates a simple path. Given a StartPoint, link it to a empty node and an end point. If you don't give us a StartPoint, we'll create a new one.
 * 
 * Created 2005-02-21
 * 
 * @author Etienne Tremblay
 */
public class CreatePathCommand extends Command {

    private PathGraph diagram;
    private Point position;
    private StartPoint start;
    private PathNode node;
    private EndPoint end;
    private NodeConnection link1;
    private NodeConnection link2;

    public CreatePathCommand() {
        super();
    }

    /**
     * Creates all required elements and invokes redo()
     */
    public void execute() {
        MapFactory factory = MapFactory.eINSTANCE;

        if (start == null)
            start = factory.createStartPoint();

        // start-----node-----end
        start.setX(position.x);
        start.setY(position.y);

        node = factory.createEmptyPoint();
        node.setX(position.x + 100);
        node.setY(position.y);

        link1 = factory.createNodeConnection();
        link1.setSource(start);
        link1.setTarget(node);

        end = factory.createEndPoint();
        end.setX(position.x + 200);
        end.setY(position.y);

        link2 = factory.createNodeConnection();
        link2.setSource(node);
        link2.setTarget(end);

        redo();
    }

    /**
     * Add new elements to model
     */
    public void redo() {
        diagram.getNodeConnections().add(link1);
        diagram.getNodeConnections().add(link2);

        diagram.getPathNodes().add(start);
        diagram.getPathNodes().add(node);
        diagram.getPathNodes().add(end);
    }

    /**
     * Remove the new elements from the model
     */
    public void undo() {
        diagram.getPathNodes().remove(start);
        diagram.getPathNodes().remove(node);
        diagram.getPathNodes().remove(end);

        diagram.getNodeConnections().remove(link2);
        diagram.getNodeConnections().remove(link1);
    }

    /**
     * @return Returns the PathGraph
     */
    public PathGraph getDiagram() {
        return diagram;
    }

    /**
     * Tells this command which PathGraph to use.
     * 
     * @param diagram
     */
    public void setDiagram(PathGraph diagram) {
        this.diagram = diagram;
    }

    /**
     * @return The Point position of where to insert the StartPoint
     */
    public Point getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *            The Point position of where to insert the StartPoint
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @return Returns the start.
     */
    public StartPoint getStart() {
        return start;
    }

    /**
     * @param start
     *            The start to set.
     */
    public void setStart(StartPoint start) {
        this.start = start;
    }

    /**
     * @return the EndPoint that was created by this command.
     */
    public EndPoint getEnd() {
        return end;
    }
}