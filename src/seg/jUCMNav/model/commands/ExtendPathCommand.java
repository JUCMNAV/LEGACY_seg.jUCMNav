package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * This command extends a Path by replacing the current end point and creating a new end point elsewhere. Note: This does NOT move the current end point, it
 * creates a new one. This might become problematic in the future.
 * 
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class ExtendPathCommand extends Command {

    private PathGraph diagram; // The UCM diagram

    private PathNode lastNode; // The last node before the end node

    private EndPoint newEnd; // The new end node
    private EndPoint lastEnd; // The last end node before the extension

    private PathNode newNode; // The new node required to replace the last end node

    private NodeConnection lastLink; // The last link connection lastNode and lastEnd

    private NodeConnection newLink1; // The new link connecting the lastNode and the newNode
    private NodeConnection newLink2; // The new link connecting the newNode and the newEnd

    private Point location; // Location of the new end.

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        MapFactory factory = MapFactory.eINSTANCE;

        NodeConnection link = (NodeConnection) (diagram.getNodeConnections().get(0));

        lastLink = (NodeConnection) lastEnd.getPred().get(0);

        lastNode = (PathNode) lastLink.getSource();

        newLink1 = factory.createNodeConnection();
        newLink2 = factory.createNodeConnection();

        newNode = factory.createEmptyPoint();
        newNode.setX(lastEnd.getX());
        newNode.setY(lastEnd.getY());

        newEnd.setX(location.x);
        newEnd.setY(location.y);
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {

        //Remove last link
        lastLink.setSource(null);
        lastLink.setTarget(null);
        diagram.getNodeConnections().remove(lastLink);

        // Remove the unused end and add the new node + new end
        diagram.getPathNodes().remove(lastEnd);
        diagram.getPathNodes().add(newNode);
        diagram.getPathNodes().add(newEnd);

        // Setup the new two links connecting the new node + new end
        newLink1.setSource(lastNode);
        newLink1.setTarget(newNode);
        diagram.getNodeConnections().add(newLink1);

        newLink2.setSource(newNode);
        newLink2.setTarget(newEnd);
        diagram.getNodeConnections().add(newLink2);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        // Delete the new two links
        newLink2.setSource(null);
        newLink2.setTarget(null);
        diagram.getNodeConnections().remove(newLink2);

        newLink1.setSource(null);
        newLink1.setTarget(null);
        diagram.getNodeConnections().remove(newLink1);

        // Remove the added nodes and recover the lastEnd
        diagram.getPathNodes().remove(newEnd);
        diagram.getPathNodes().remove(newNode);
        diagram.getPathNodes().add(lastEnd);

        // Recover last link
        lastLink.setSource(lastNode);
        lastLink.setTarget(lastEnd);
        diagram.getNodeConnections().add(lastLink);
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

    /**
     * @return Returns the location.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location
     *            The location to set.
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return Returns the newEnd.
     */
    public EndPoint getNewEnd() {
        return newEnd;
    }

    /**
     * @param newEnd
     *            The newEnd to set.
     */
    public void setNewEnd(EndPoint newEnd) {
        this.newEnd = newEnd;
    }

    /**
     * @param lastEnd
     *            The lastEnd to set.
     */
    public void setLastEnd(EndPoint lastEnd) {
        this.lastEnd = lastEnd;
    }
}