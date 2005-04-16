package seg.jUCMNav.model.commands;

import seg.jUCMNav.emf.ModelCreationFactory;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * This command creates a simple path. Given a StartPoint, link it to a empty node and an end point. If you don't give us a StartPoint, we'll create a new one.
 * 
 * Created 2005-02-21
 * 
 * @author Etienne Tremblay, jkealey
 */
public class CreatePathCommand extends JUCMNavCommand {

    // where to insert the new path
    private PathGraph diagram;

    // the position of the start point
    private int x, y;
    
    // new variables
    private StartPoint start;
    private PathNode node;
    private EndPoint end;
    private NodeConnection link1;
    private NodeConnection link2;

    public CreatePathCommand() {
        super();
        setLabel("Create New Path");
    }

    public CreatePathCommand(PathGraph pg, StartPoint sp, int x, int y) {
        this.diagram = pg;
        this.start = sp;
        this.x = x;
        this.y = y;
        setLabel("Create New Path");
    }

    public CreatePathCommand(PathGraph pg, int x, int y) {
        this.diagram = pg;
        this.x = x;
        this.y = y;
        setLabel("Create New Path");
    }

    /**
     * Creates all required elements and invokes redo()
     */
    public void execute() {
        if (start == null)
            start = (StartPoint) ModelCreationFactory.getNewObject(StartPoint.class);

        // start-----node-----end
        start.setX(x);
        start.setY(y);

        node = (EmptyPoint) ModelCreationFactory.getNewObject(EmptyPoint.class);
        node.setX(x + 100);
        node.setY(y);

        link1 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        link1.setSource(start);
        link1.setTarget(node);

        end = (EndPoint) ModelCreationFactory.getNewObject(EndPoint.class);
        end.setX(x + 200);
        end.setY(y);

        link2 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        link2.setSource(node);
        link2.setTarget(end);

        redo();
    }

    /**
     * Add new elements to model
     */
    public void redo() {
        testPreConditions();
        diagram.getNodeConnections().add(link1);
        diagram.getNodeConnections().add(link2);

        diagram.getPathNodes().add(start);
        diagram.getPathNodes().add(node);
        diagram.getPathNodes().add(end);
        testPostConditions();
    }

    /**
     * Remove the new elements from the model
     */
    public void undo() {
        testPostConditions();

        diagram.getPathNodes().remove(start);
        diagram.getPathNodes().remove(node);
        diagram.getPathNodes().remove(end);

        diagram.getNodeConnections().remove(link2);
        diagram.getNodeConnections().remove(link1);

        testPreConditions();
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

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert diagram != null : "pre Diagram";
        assert start != null: "pre Start";
        assert node != null : "pre Node";
        assert end != null : "pre End";
        assert link1 != null : "pre Link1";
        assert link2 != null : "pre Link1";

        assert !diagram.getPathNodes().contains(start) : "pre pathgraph does not contain start";
        assert !diagram.getPathNodes().contains(end): "pre pathgraph does not contain end";
        assert !diagram.getPathNodes().contains(node): "pre pathgraph does not contain node";
        assert !diagram.getNodeConnections().contains(link1):"pre pathgraph does not contain link1";
        assert !diagram.getNodeConnections().contains(link2):"pre pathgraph does not contain link2";
        
        assert start.getSucc().contains(link1): "pre link1 not succ of start";
        assert link1.getTarget() == node: "pre node not target of link1"; 
        assert node.getSucc().contains(link2): "pre link2 not succ of node";
        assert link2.getTarget() == end: "pre end not target of link2";
        
        assert start.getX() == x : "pre start point x";
        assert start.getY() == y : "pre start point y";
        

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert diagram != null : "post Diagram";
        assert start != null: "post Start";
        assert node != null : "post Node";
        assert end != null : "post End";
        assert link1 != null : "post Link1";
        assert link2 != null : "post Link1";

        assert diagram.getPathNodes().contains(start) : "pre pathgraph contains start";
        assert diagram.getPathNodes().contains(end): "pre pathgraph contains end";
        assert diagram.getPathNodes().contains(node): "pre pathgraph contains node";
        assert diagram.getNodeConnections().contains(link1):"pre pathgraph contains link1";
        assert diagram.getNodeConnections().contains(link2):"pre pathgraph contains link2";

        assert start.getSucc().contains(link1): "post link1 not succ of start";
        assert link1.getTarget() == node: "post node not target of link1"; 
        assert node.getSucc().contains(link2): "post link2 not succ of node";
        assert link2.getTarget() == end: "post end not target of link2";
        
        assert start.getX() == x : "post start point x";
        assert start.getY() == y : "post start point y";     

    }

    /**
     * @return Returns the x.
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     *            The x to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return Returns the y.
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     *            The y to set.
     */
    public void setY(int y) {
        this.y = y;
    }
}