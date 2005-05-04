package seg.jUCMNav.model.commands.delete;

import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Command to delete a node on a path.
 * Currently unimplemented. 
 * 
 * jkealey: Just put some quick code here to show as example and stop it from crashing.
 * @author Etienne Tremblay, jkealey
 *  
 */
public class DeleteNodeCommand extends Command {

    private static final String DeleteCommand_Label = "DeletePathNodeCommand";

    private PathNode node;
    private PathNode previous;
    private PathNode next;
    private Vector sources;
    private Vector targets;
    private NodeConnection newConn;
    private Map map;
    private ComponentRef compRef;
    
    public DeleteNodeCommand(PathNode node) {
    	this.node = node;
    	setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {

        if (node instanceof StartPoint || node instanceof EndPoint)
            return false;
        else {
        	if(node instanceof EmptyPoint)
        		return true;
        	else
        		return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        map = (Map)node.eContainer().eContainer();
    	previous = ((NodeConnection)node.getPred().get(0)).getSource();
    	next = ((NodeConnection)node.getSucc().get(0)).getTarget();
    	compRef = node.getCompRef();
    	sources = new Vector();
    	targets = new Vector();
    	sources.addAll(node.getPred());
    	targets.addAll(node.getSucc());
    	newConn = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
    	
    	redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        // ASSUMING ONLY FOR EMPTYNODE - 1 IN, ONE OUT.
        
        node.getSucc().clear();
        node.getPred().clear();
        
        ((NodeConnection)sources.get(0)).setSource(null);
        ((NodeConnection)targets.get(0)).setTarget(null);
        
        map.getPathGraph().getNodeConnections().remove((NodeConnection)sources.get(0));
        map.getPathGraph().getNodeConnections().remove((NodeConnection)targets.get(0));
        map.getPathGraph().getNodeConnections().add(newConn);
        
        map.getPathGraph().getPathNodes().remove(node);
        
        node.setCompRef(null);
        
        newConn.setSource(previous);
        newConn.setTarget(next);
        

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {

        node.getSucc().addAll(targets);
        node.getPred().addAll(sources);

        ((NodeConnection)sources.get(0)).setSource(previous);
        ((NodeConnection)targets.get(0)).setTarget(next);
        
        map.getPathGraph().getNodeConnections().add((NodeConnection)sources.get(0));
        map.getPathGraph().getNodeConnections().add((NodeConnection)targets.get(0));
        map.getPathGraph().getNodeConnections().remove(newConn);
        
        map.getPathGraph().getPathNodes().add(node);
        
        node.setCompRef(compRef);
        newConn.setSource(null);
        newConn.setTarget(null);
        
        
    }


    /**
     * @return Returns the PathPathNode.
     */
    public PathNode getPathNode() {
        return node;
    }

    /**
     * @param PathPathNode
     *            The PathPathNode to set.
     */
    public void setPathNode(PathNode node) {
        this.node = node;
    }
}