package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * This command creates a simple path. Given a StartPoint, link it to a empty node and an end point. If you don't give us a StartPoint, we'll create a new one.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class CreatePathCommand extends Command implements JUCMNavCommand, ICreateElementCommand {

    // where to insert the new path
    private UCMmap diagram;

    // the position of the start point
    private int x, y;

    // new variables
    private StartPoint start;

    private PathNode node;

    private EndPoint end;

    private NodeConnection link1;

    private NodeConnection link2;

    /**
     * Create a new path. Assume arguments will be passed later on.
     * 
     */
    public CreatePathCommand() {
        super();
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    /**
     * Create a new path using this start point and position it at (x,y)
     * 
     * @param pg
     *            the ucmmap in which it should be inserted
     * @param sp
     *            the start point of the new path
     * @param x
     *            the x coordinate of the start point
     * @param y
     *            the y coordinate of the start point
     */
    public CreatePathCommand(UCMmap pg, StartPoint sp, int x, int y) {
        this.diagram = pg;
        this.start = sp;
        this.x = x;
        this.y = y;
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    /**
     * Create a new path at position (x,y), create all elements including the StartPoint.
     * 
     * @param pg
     *            the pathgraph in which it should be inserted
     * @param x
     *            the x coordinate of the start point
     * @param y
     *            the y coordinate of the start point
     */
    public CreatePathCommand(UCMmap pg, int x, int y) {
        this.diagram = pg;
        this.x = x;
        this.y = y;
        setLabel(Messages.getString("CreatePathCommand.createNewPath")); //$NON-NLS-1$
    }

    /**
     * Creates all required elements and invokes redo()
     */
    public void execute() {

        if (end == null)
            createElements();
        redo();
    }

    /**
     * Add new elements to model
     */
    public void redo() {
        testPreConditions();
        diagram.getConnections().add(link1);
        diagram.getConnections().add(link2);

        diagram.getNodes().add(start);
        diagram.getNodes().add(node);
        diagram.getNodes().add(end);

        // we must bind our components with their container
        start.setContRef(ParentFinder.findParent(diagram, x, y));
        node.setContRef(ParentFinder.findParent(diagram, x + 100, y));
        end.setContRef(ParentFinder.findParent(diagram, x + 200, y));

        testPostConditions();
    }

    /**
     * Remove the new elements from the model
     */
    public void undo() {
        testPostConditions();
        start.setContRef(null);
        node.setContRef(null);
        end.setContRef(null);

        diagram.getNodes().remove(start);
        diagram.getNodes().remove(node);
        diagram.getNodes().remove(end);

        diagram.getConnections().remove(link2);
        diagram.getConnections().remove(link1);

        testPreConditions();
    }

    /**
     * @return Returns the PathGraph
     */
    public UCMmap getDiagram() {
        return diagram;
    }

    /**
     * Tells this command which PathGraph to use.
     * 
     * @param diagram
     */
    public void setDiagram(UCMmap diagram) {
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

        assert !diagram.getNodes().contains(start) : "pre pathgraph does not contain start"; //$NON-NLS-1$
        assert !diagram.getNodes().contains(end) : "pre pathgraph does not contain end"; //$NON-NLS-1$
        assert !diagram.getNodes().contains(node) : "pre pathgraph does not contain node"; //$NON-NLS-1$
        assert !diagram.getConnections().contains(link1) : "pre pathgraph does not contain link1"; //$NON-NLS-1$
        assert !diagram.getConnections().contains(link2) : "pre pathgraph does not contain link2"; //$NON-NLS-1$

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

        assert diagram.getNodes().contains(start) : "pre pathgraph contains start"; //$NON-NLS-1$
        assert diagram.getNodes().contains(end) : "pre pathgraph contains end"; //$NON-NLS-1$
        assert diagram.getNodes().contains(node) : "pre pathgraph contains node"; //$NON-NLS-1$
        assert diagram.getConnections().contains(link1) : "pre pathgraph contains link1"; //$NON-NLS-1$
        assert diagram.getConnections().contains(link2) : "pre pathgraph contains link2"; //$NON-NLS-1$

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

    /**
     * Creates the elements if they need to be referenced before executing the command.
     * 
     */
    public void createElements() {
        URNspec urn = diagram.getUrndefinition().getUrnspec();
        if (start == null)
            start = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);

        // start-----node-----end
        start.setX(x);
        start.setY(y);

        node = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        node.setX(x + 100);
        node.setY(y);

        link1 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        link1.setSource(start);
        link1.setTarget(node);

        end = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
        end.setX(x + 200);
        end.setY(y);

        link2 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        link2.setSource(node);
        link2.setTarget(end);

    }

    public Object getNewModelElement() {
        return end;
    }
    
    public PathNode getNewMiddleNode() {
        return node;
    }
    

}