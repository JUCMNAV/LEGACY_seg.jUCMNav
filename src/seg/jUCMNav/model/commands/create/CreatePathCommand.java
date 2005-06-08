package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * This command creates a simple path. Given a StartPoint, link it to a empty node and an end point. If you don't give us a StartPoint, we'll create a new one.
 * 
 * Created 2005-02-21
 * 
 * @author Etienne Tremblay, jkealey
 */
public class CreatePathCommand extends Command implements JUCMNavCommand {

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
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    public CreatePathCommand(PathGraph pg, StartPoint sp, int x, int y) {
        this.diagram = pg;
        this.start = sp;
        this.x = x;
        this.y = y;
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    public CreatePathCommand(PathGraph pg, int x, int y) {
        this.diagram = pg;
        this.x = x;
        this.y = y;
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    /**
     * Creates all required elements and invokes redo()
     */
    public void execute() {
        URNspec urn = diagram.getMap().getUcmspec().getUrnspec();
        if (start == null)
            start = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);

        // start-----node-----end
        start.setX(x);
        start.setY(y);

        node = (EmptyPoint) ModelCreationFactory.getNewObject(urn,EmptyPoint.class);
        node.setX(x + 100);
        node.setY(y);
        
        link1 = (NodeConnection) ModelCreationFactory.getNewObject(urn,NodeConnection.class);
        link1.setSource(start);
        link1.setTarget(node);

        end = (EndPoint) ModelCreationFactory.getNewObject(urn,EndPoint.class);
        end.setX(x + 200);
        end.setY(y);

        link2 = (NodeConnection) ModelCreationFactory.getNewObject(urn,NodeConnection.class);
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
        
        start.setCompRef(ParentFinder.findParent(diagram.getMap(), x, y));        
        node.setCompRef(ParentFinder.findParent(diagram.getMap(), x + 100, y));
        end.setCompRef(ParentFinder.findParent(diagram.getMap(), x + 200, y));
        
        
        testPostConditions();
    }

    /**
     * Remove the new elements from the model
     */
    public void undo() {
        testPostConditions();
        start.setCompRef(null);        
        node.setCompRef(null);
        end.setCompRef(null);

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
        assert diagram != null : "pre Diagram"; //$NON-NLS-1$
        assert start != null : "pre Start"; //$NON-NLS-1$
        assert node != null : "pre Node"; //$NON-NLS-1$
        assert end != null : "pre End"; //$NON-NLS-1$
        assert link1 != null : "pre Link1"; //$NON-NLS-1$
        assert link2 != null : "pre Link1"; //$NON-NLS-1$

        assert !diagram.getPathNodes().contains(start) : "pre pathgraph does not contain start"; //$NON-NLS-1$
        assert !diagram.getPathNodes().contains(end) : "pre pathgraph does not contain end"; //$NON-NLS-1$
        assert !diagram.getPathNodes().contains(node) : "pre pathgraph does not contain node"; //$NON-NLS-1$
        assert !diagram.getNodeConnections().contains(link1) : "pre pathgraph does not contain link1"; //$NON-NLS-1$
        assert !diagram.getNodeConnections().contains(link2) : "pre pathgraph does not contain link2"; //$NON-NLS-1$

        assert start.getSucc().contains(link1) : "pre link1 not succ of start"; //$NON-NLS-1$
        assert link1.getTarget() == node : "pre node not target of link1"; //$NON-NLS-1$
        assert node.getSucc().contains(link2) : "pre link2 not succ of node"; //$NON-NLS-1$
        assert link2.getTarget() == end : "pre end not target of link2"; //$NON-NLS-1$

        assert start.getX() == x : "pre start point x"; //$NON-NLS-1$
        assert start.getY() == y : "pre start point y"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert diagram != null : "post Diagram"; //$NON-NLS-1$
        assert start != null : "post Start"; //$NON-NLS-1$
        assert node != null : "post Node"; //$NON-NLS-1$
        assert end != null : "post End"; //$NON-NLS-1$
        assert link1 != null : "post Link1"; //$NON-NLS-1$
        assert link2 != null : "post Link1"; //$NON-NLS-1$

        assert diagram.getPathNodes().contains(start) : "pre pathgraph contains start"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(end) : "pre pathgraph contains end"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(node) : "pre pathgraph contains node"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(link1) : "pre pathgraph contains link1"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(link2) : "pre pathgraph contains link2"; //$NON-NLS-1$

        assert start.getSucc().contains(link1) : "post link1 not succ of start"; //$NON-NLS-1$
        assert link1.getTarget() == node : "post node not target of link1"; //$NON-NLS-1$
        assert node.getSucc().contains(link2) : "post link2 not succ of node"; //$NON-NLS-1$
        assert link2.getTarget() == end : "post end not target of link2"; //$NON-NLS-1$

        assert start.getX() == x : "post start point x"; //$NON-NLS-1$
        assert start.getY() == y : "post start point y"; //$NON-NLS-1$

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