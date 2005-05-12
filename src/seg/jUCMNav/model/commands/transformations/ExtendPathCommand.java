package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * This command extends a Path by moving the end point.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ExtendPathCommand extends Command implements  JUCMNavCommand {

    private PathGraph diagram; // The UCM diagram

    private EndPoint end; // The end node to be moved

    private NodeConnection lastLink; // The end node's predecessor
    private PathNode lastNode; // The last node before the end node

    private PathNode newNode; // The new node located at the end point's old location
    private NodeConnection newLink; // The new link that has been created, linking the end point's old and new locations

    private int oldX, oldY, newX, newY; // where to move the end point

    public ExtendPathCommand(PathGraph pg, EndPoint end, int x, int y) {
        this.diagram = pg;
        this.end = end;
        this.newX = x;
        this.newY = y;

        setLabel("Extend Path");

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        // get ... ---(lastNode)---[lastLink]---(end)
        lastLink = (NodeConnection) end.getPred().get(0);
        lastNode = (PathNode) lastLink.getSource();

        // create new elements
        newLink = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) diagram.eContainer().eContainer().eContainer(), NodeConnection.class);
        newNode = (EmptyPoint) ModelCreationFactory.getNewObject((URNspec) diagram.eContainer().eContainer().eContainer(), EmptyPoint.class);

        // link new elements
        newNode.getSucc().add(newLink);

        // position the new empty point over the current end point
        this.oldX = end.getX();
        this.oldY = end.getY();
        
        newNode.setX(end.getX());
        newNode.setY(end.getY());

        redo();
    }

    /**
     * @return Returns the diagram.
     */
    public PathGraph getDiagram() {
        return diagram;
    }

    /**
     * @return Returns the end.
     */
    public EndPoint getEnd() {
        return end;
    }

    /**
     * @return Returns the newX.
     */
    public int getNewX() {
        return newX;
    }

    /**
     * @return Returns the newY.
     */
    public int getNewY() {
        return newY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // move end
        end.setX(newX);
        end.setY(newY);

        // reposition last link
        lastLink.setTarget(newNode);
        newLink.setTarget(end);

        // add to model
        diagram.getPathNodes().add(newNode);
        diagram.getNodeConnections().add(newLink);
        
        // bind to parent
        end.setCompRef(ParentFinder.findParent((Map) diagram.eContainer(), newX, newY));
        newNode.setCompRef(ParentFinder.findParent((Map) diagram.eContainer(), oldX, oldY));
        
        
        testPostConditions();
    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(PathGraph diagram) {
        this.diagram = diagram;
    }

    /**
     * @param end
     *            The end to set.
     */
    public void setEnd(EndPoint end) {
        this.end = end;
    }

    /**
     * @param newX
     *            The newX to set.
     */
    public void setNewX(int newX) {
        this.newX = newX;
    }

    /**
     * @param newY
     *            The newY to set.
     */
    public void setNewY(int newY) {
        this.newY = newY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {

        assert diagram != null : "post diagram";
        assert end != null : "post end";
        assert lastNode != null : "post last node";
        assert lastLink != null : "post last link";
        assert newNode != null : "post new node";
        assert newLink != null : "post new link";

        assert newNode.getX() == oldX && newNode.getY() == oldY : "post new Node position";
        assert end.getX() == newX && end.getY() == newY : "post end position";

        assert diagram.getNodeConnections().contains(lastLink) : "post graph contains lastLink";
        assert diagram.getNodeConnections().contains(newLink) : "post graph contains newLink";
        assert diagram.getPathNodes().contains(lastNode) : "post graph contains lastNode";
        assert diagram.getPathNodes().contains(newNode) : "post graph contains newNode";

        assert newLink.getSource() == newNode : "post link1";
        assert newLink.getTarget() == end : "post link2";
        assert lastLink.getSource() == lastNode : "post link3";
        assert lastLink.getTarget() == newNode : "post link4";
        assert newNode.getSucc().get(0) == newLink : "post link5";
        assert newNode.getPred().size() == 1 && newNode.getSucc().size() == 1 : "post newNode 1 in, 1 out";

        // not checking successors to be able to extend connects
        assert end.getPred().size() == 1 && end.getPred().get(0) == newLink : "post end pred";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert diagram != null : "pre diagram";
        assert end != null : "pre end";
        assert lastNode != null : "pre last node";
        assert lastLink != null : "pre last link";
        assert newNode != null : "pre new node";
        assert newLink != null : "pre new link";

        assert newNode.getX() == oldX && newNode.getY() == oldY : "pre new Node position";
        assert end.getX() == oldX && end.getY() == oldY : "pre end position";
        
        assert diagram.getNodeConnections().contains(lastLink) : "pre graph contains lastLink";
        assert !diagram.getNodeConnections().contains(newLink) : "pre graph doesn't contain newLink";
        assert diagram.getPathNodes().contains(lastNode) : "pre graph contains lastNode";
        assert !diagram.getPathNodes().contains(newNode) : "pre graph doesn't contain newNode";

        assert newLink.getSource() == newNode : "pre link1";
        assert newLink.getTarget() == null : "pre link2";
        assert lastLink.getSource() == lastNode : "pre link3";
        assert lastLink.getTarget() == end : "pre link4";
        assert newNode.getSucc().get(0) == newLink : "pre link5";
        
        assert newNode.getPred().size() == 0 && newNode.getSucc().size() == 1 : "pre newNode 0 in, 1 out";

        // not checking successors to be able to extend connects
        assert end.getPred().size() == 1 && end.getPred().get(0) == lastLink : "pre end pred";

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // bind to parent
        end.setCompRef(ParentFinder.findParent((Map) diagram.eContainer(), oldX, oldY));
        newNode.setCompRef(null);

        
        // remove from model
        diagram.getPathNodes().remove(newNode);
        diagram.getNodeConnections().remove(newLink);

        // reposition links
        lastLink.setTarget(end);
        newLink.setTarget(null);

        // move end point back to starting place
        end.setX(newNode.getX());
        end.setY(newNode.getY());
        
        testPreConditions();
    }
}