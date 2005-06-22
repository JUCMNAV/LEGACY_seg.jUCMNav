package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urncore.Responsibility;

/**
 * Command to delete a node on a path.
 * 
 * Currently deletes pathnodes that have 1 in, 1 out.
 * 
 * Was intended to work for nodes that have more than 1 in or out, but this code was put in DeleteMultiNodeCommand
 * 
 * @author Etienne Tremblay, jkealey
 *  
 */
public class DeleteNodeCommand extends CompoundCommand implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeletePathNodeCommand"; //$NON-NLS-1$

    /** the node to be removed */
    private PathNode node;

    /** the preceeding node; assuming just one */
    private PathNode previous;

    /** the next node; assuming just one */
    private PathNode next;

    /** our node's targets; right now only one */
    private Vector targets;

    /** the new connection from previous to next */
    private NodeConnection newConn;

    /** the map containing the pathnodes and node connections */
    private Map map;

    /** if we are bound to a component, this is it */
    private ComponentRef compRef;

    /** if we are a RespRef, this is our respDef */
    private Responsibility respDef;

    private boolean aborted = false;

    public DeleteNodeCommand(PathNode node) {
        this.node = node;
        setLabel(DeleteCommand_Label);
    }

    /**
     * Right now, can execute if we have exactly one input and one output.
     * 
     * Furthermore, we must not be start points or end points.
     */
    public boolean canExecute() {

        if (node.getPathGraph() == null || node instanceof StartPoint || node instanceof EndPoint)
            return false;
        else {
            if (node.getPred().size() == 1 && node.getSucc().size() == 1)
                return true;
            else
                return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    public boolean canUndo() {
        // Make sure we can undo even if we don't have any added commands
        if (getCommands().size() == 0)
            return true;
        return super.canUndo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (node instanceof Stub) {
            Stub stub = (Stub) node;
            for (Iterator i = stub.getBindings().iterator(); i.hasNext();) {
                PluginBinding plugin = (PluginBinding) i.next();
                DeletePluginCommand del = new DeletePluginCommand(plugin);
                add(del);
            }
        }

        // could happen if was already deleted by other command
        if (node.getPathGraph() == null || !canExecute()) {
            aborted = true;
            return;
        }
        map = node.getPathGraph().getMap();
        previous = ((NodeConnection) node.getPred().get(0)).getSource();
        next = ((NodeConnection) node.getSucc().get(0)).getTarget();
        compRef = node.getCompRef();
        targets = new Vector();
        targets.addAll(node.getSucc());
        newConn = (NodeConnection) node.getPred().get(0);

        if (node instanceof RespRef) {
            respDef = ((RespRef) node).getRespDef();
        }

        doRedo();

        // Important to call this if we want the added commands to this compound command to execute.
        if (getCommands().size() > 0)
            super.execute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        // ASSUMING ONLY FOR EMPTYNODE - 1 IN, ONE OUT.

        testPreConditions();

        doRedo();

        super.redo();

        testPostConditions();
    }

    /**
     *  
     */
    private void doRedo() {
        node.getSucc().clear();
        node.getPred().clear();

        //((NodeConnection) sources.get(0)).setSource(null);
        ((NodeConnection) targets.get(0)).setTarget(null);

        //map.getPathGraph().getNodeConnections().remove((NodeConnection)
        // sources.get(0));
        map.getPathGraph().getNodeConnections().remove((NodeConnection) targets.get(0));
        //map.getPathGraph().getNodeConnections().add(newConn);

        map.getPathGraph().getPathNodes().remove(node);

        node.setCompRef(null);
        if (node instanceof RespRef) {
            ((RespRef) node).setRespDef(null);
        }

        if (!previous.equals(next)) {
            newConn.setSource(previous);
            newConn.setTarget(next);
        } else {
            newConn.setSource(null);
            map.getPathGraph().getNodeConnections().remove(newConn);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        super.undo();

        node.getSucc().addAll(targets);

        newConn.setSource(previous);
        newConn.setTarget(node);
        if (previous.equals(next)) {
            map.getPathGraph().getNodeConnections().add(newConn);
        }
        ((NodeConnection) targets.get(0)).setTarget(next);

        map.getPathGraph().getNodeConnections().add((NodeConnection) targets.get(0));

        map.getPathGraph().getPathNodes().add(node);

        if (node instanceof RespRef) {
            ((RespRef) node).setRespDef(respDef);
        }
        node.setCompRef(compRef);

        testPreConditions();
    }

    /**
     * @return Returns the PathPathNode.
     */
    public PathNode getPathNode() {
        return node;
    }

    /**
     * @param node
     *            The PathPathNode to set.
     */
    public void setPathNode(PathNode node) {
        this.node = node;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert canExecute() : "pre canExecute"; //$NON-NLS-1$
        assert previous != null && next != null && newConn != null : "pre something is null"; //$NON-NLS-1$
        assert targets.size() == node.getSucc().size() : "pre source/target problem"; //$NON-NLS-1$
        assert node.getPred().contains(newConn) : "pre missing source"; //$NON-NLS-1$
        assert map.getPathGraph().getNodeConnections().contains(newConn) : "pre source not in model"; //$NON-NLS-1$
        for (Iterator iter = targets.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            assert node.getSucc().contains(nc) : "pre missing target"; //$NON-NLS-1$
            assert map.getPathGraph().getNodeConnections().contains(nc) : "pre target not in model"; //$NON-NLS-1$
        }

        assert compRef == node.getCompRef() : "pre parent problem"; //$NON-NLS-1$
        if (node instanceof RespRef) {
            assert respDef != null && respDef == ((RespRef) node).getRespDef() : "pre respref not linked"; //$NON-NLS-1$
        }

        assert map.getPathGraph().getNodeConnections().contains(newConn) : "pre new conn"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert previous != null && next != null : "post something is null"; //$NON-NLS-1$
        assert node.getPred().size() == 0 && 0 == node.getSucc().size() : "post source/target problem"; //$NON-NLS-1$

        for (Iterator iter = targets.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            assert !map.getPathGraph().getNodeConnections().contains(nc) : "post target in model"; //$NON-NLS-1$
        }
        assert null == node.getCompRef() : "post parent problem"; //$NON-NLS-1$
        if (node instanceof RespRef) {
            assert respDef != null && null == ((RespRef) node).getRespDef() : "post respref still linked"; //$NON-NLS-1$
        }

    }
}