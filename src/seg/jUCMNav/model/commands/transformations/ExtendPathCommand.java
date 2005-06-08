package seg.jUCMNav.model.commands.transformations;

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
 * This command extends a Path by moving the end point.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ExtendPathCommand extends Command implements JUCMNavCommand {

    private PathGraph diagram; // The UCM diagram
    private EndPoint end; // The end node to be moved
    private NodeConnection lastLink; // The end node's predecessor
    private PathNode lastNode; // The last node before the end node
    private NodeConnection newLink; // The new link that has been created, linking the end point's old and new locations
    private PathNode newNode; // The new node located at the end point's old location
    private int oldX, oldY, newX, newY; // where to move the end point
    private StartPoint start; // or maybe it is the start point that is moved.

    public ExtendPathCommand(PathGraph pg, EndPoint end, int x, int y) {
        this.diagram = pg;
        this.end = end;
        this.newX = x;
        this.newY = y;

        setLabel(Messages.getString("ExtendPathCommand.extendPath")); //$NON-NLS-1$

    }

    public ExtendPathCommand(PathGraph pg, StartPoint start, int x, int y) {
        this.diagram = pg;
        this.start = start;
        this.newX = x;
        this.newY = y;

        setLabel(Messages.getString("ExtendPathCommand.extendPath")); //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        // create new elements
        URNspec urn = diagram.getMap().getUcmspec().getUrnspec();
        newLink = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        newNode = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);

        // moving the end point
        if (end != null) {
            // get ... ---(lastNode)---[lastLink]---(end)
            lastLink = (NodeConnection) end.getPred().get(0);
            lastNode = (PathNode) lastLink.getSource();

            // link new elements
            newNode.getSucc().add(newLink);

            // position the new empty point over the current end point
            this.oldX = end.getX();
            this.oldY = end.getY();

            newNode.setX(end.getX());
            newNode.setY(end.getY());
        } else {
            // get (start)---[lastLink]---(lastNode)---...
            lastLink = (NodeConnection) start.getSucc().get(0);
            lastNode = (PathNode) lastLink.getTarget();

            // link new elements
            newNode.getPred().add(newLink);

            // position the new empty point over the current end point
            this.oldX = start.getX();
            this.oldY = start.getY();

            newNode.setX(start.getX());
            newNode.setY(start.getY());
        }

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

    public StartPoint getStart() {
        return start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        PathNode moved = end;
        if (moved == null)
            moved = start;

        // move end
        moved.setX(newX);
        moved.setY(newY);

        if (end != null) {
            // reposition last link
            lastLink.setTarget(newNode);
            newLink.setTarget(end);
        } else {
            // reposition last link
            lastLink.setSource(newNode);
            newLink.setSource(start);
        }

        // add to model
        diagram.getPathNodes().add(newNode);
        diagram.getNodeConnections().add(newLink);

        // bind to parent
        moved.setCompRef(ParentFinder.findParent(diagram.getMap(), newX, newY));
        newNode.setCompRef(ParentFinder.findParent(diagram.getMap(), oldX, oldY));

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

    public void setStart(StartPoint start) {
        this.start = start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {

        assert diagram != null : "post diagram"; //$NON-NLS-1$
        assert end != null || start != null : "post end"; //$NON-NLS-1$
        PathNode moved = end;
        if (moved == null)
            moved = start;
        assert lastNode != null : "post last node"; //$NON-NLS-1$
        assert lastLink != null : "post last link"; //$NON-NLS-1$
        assert newNode != null : "post new node"; //$NON-NLS-1$
        assert newLink != null : "post new link"; //$NON-NLS-1$

        assert newNode.getX() == oldX && newNode.getY() == oldY : "post new Node position"; //$NON-NLS-1$
        assert moved.getX() == newX && moved.getY() == newY : "post end position"; //$NON-NLS-1$

        assert diagram.getNodeConnections().contains(lastLink) : "post graph contains lastLink"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(newLink) : "post graph contains newLink"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(lastNode) : "post graph contains lastNode"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(newNode) : "post graph contains newNode"; //$NON-NLS-1$

        if (end != null) {
            assert newLink.getSource() == newNode : "post link1"; //$NON-NLS-1$
            assert newLink.getTarget() == end : "post link2"; //$NON-NLS-1$
            assert lastLink.getSource() == lastNode : "post link3"; //$NON-NLS-1$
            assert lastLink.getTarget() == newNode : "post link4"; //$NON-NLS-1$
            assert newNode.getSucc().get(0) == newLink : "post link5"; //$NON-NLS-1$
            assert newNode.getPred().size() == 1 && newNode.getSucc().size() == 1 : "post newNode 1 in, 1 out"; //$NON-NLS-1$

            // not checking successors to be able to extend connects
            assert end.getPred().size() == 1 && end.getPred().get(0) == newLink : "post end pred"; //$NON-NLS-1$
        } else {
            assert newLink.getTarget() == newNode : "post link1"; //$NON-NLS-1$
            assert newLink.getSource() == start : "post link2"; //$NON-NLS-1$
            assert lastLink.getSource() == newNode : "post link3"; //$NON-NLS-1$
            assert lastLink.getTarget() == lastNode : "post link4"; //$NON-NLS-1$
            assert newNode.getPred().get(0) == newLink : "post link5"; //$NON-NLS-1$
            assert newNode.getPred().size() == 1 && newNode.getSucc().size() == 1 : "post newNode 1 in, 1 out"; //$NON-NLS-1$

            // not checking successors to be able to extend connects
            assert start.getSucc().size() == 1 && start.getSucc().get(0) == newLink : "post start succ"; //$NON-NLS-1$

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert diagram != null : "pre diagram"; //$NON-NLS-1$
        assert end != null || start != null : "pre end"; //$NON-NLS-1$
        PathNode moved = end;
        if (moved == null)
            moved = start;
        assert lastNode != null : "pre last node"; //$NON-NLS-1$
        assert lastLink != null : "pre last link"; //$NON-NLS-1$
        assert newNode != null : "pre new node"; //$NON-NLS-1$
        assert newLink != null : "pre new link"; //$NON-NLS-1$

        assert newNode.getX() == oldX && newNode.getY() == oldY : "pre new Node position"; //$NON-NLS-1$
        assert moved.getX() == oldX && moved.getY() == oldY : "pre end position"; //$NON-NLS-1$

        assert diagram.getNodeConnections().contains(lastLink) : "pre graph contains lastLink"; //$NON-NLS-1$
        assert !diagram.getNodeConnections().contains(newLink) : "pre graph doesn't contain newLink"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(lastNode) : "pre graph contains lastNode"; //$NON-NLS-1$
        assert !diagram.getPathNodes().contains(newNode) : "pre graph doesn't contain newNode"; //$NON-NLS-1$

        if (end != null) {
            assert newLink.getSource() == newNode : "pre link1"; //$NON-NLS-1$
            assert newLink.getTarget() == null : "pre link2"; //$NON-NLS-1$
            assert lastLink.getSource() == lastNode : "pre link3"; //$NON-NLS-1$
            assert lastLink.getTarget() == end : "pre link4"; //$NON-NLS-1$
            assert newNode.getSucc().get(0) == newLink : "pre link5"; //$NON-NLS-1$

            assert newNode.getPred().size() == 0 && newNode.getSucc().size() == 1 : "pre newNode 0 in, 1 out"; //$NON-NLS-1$

            // not checking successors to be able to extend connects
            assert end.getPred().size() == 1 && end.getPred().get(0) == lastLink : "pre end pred"; //$NON-NLS-1$
        } else {
            assert newLink.getSource() == null : "pre link1"; //$NON-NLS-1$
            assert newLink.getTarget() == newNode : "pre link2"; //$NON-NLS-1$
            assert lastLink.getSource() == start : "pre link3"; //$NON-NLS-1$
            assert lastLink.getTarget() == lastNode : "pre link4"; //$NON-NLS-1$
            assert newNode.getPred().get(0) == newLink : "pre link5"; //$NON-NLS-1$

            assert newNode.getPred().size() == 1 && newNode.getSucc().size() == 0 : "pre newNode 1 in, 0 out"; //$NON-NLS-1$

            // not checking successors to be able to extend connects
            assert start.getSucc().size() == 1 && start.getSucc().get(0) == lastLink : "pre start succ"; //$NON-NLS-1$

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        PathNode moved = end;
        if (moved == null)
            moved = start;

        // bind to parent
        moved.setCompRef(ParentFinder.findParent(diagram.getMap(), oldX, oldY));
        newNode.setCompRef(null);

        // remove from model
        diagram.getPathNodes().remove(newNode);
        diagram.getNodeConnections().remove(newLink);

        if (end != null) {
            // reposition links
            lastLink.setTarget(end);
            newLink.setTarget(null);
        } else {
            // reposition links
            lastLink.setSource(start);
            newLink.setSource(null);

        }

        // move end point back to starting place
        moved.setX(newNode.getX());
        moved.setY(newNode.getY());

        testPreConditions();
    }
}