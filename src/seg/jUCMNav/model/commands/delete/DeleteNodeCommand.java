package seg.jUCMNav.model.commands.delete;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Command to delete a node on a path.
 * Currently unimplemented.
 * @author Etienne Tremblay
 *  
 */
public class DeleteNodeCommand extends Command {

    private static final String DeleteCommand_Label = "DeletePathNodeCommand";

    private PathNode node;
    private PathNode previous;
    private PathNode next;
    private ArrayList sources;
    private ArrayList targets;
    
    private NodeConnection con;

    public DeleteNodeCommand(PathNode node) {
    	this.node = node;
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
        	if(node instanceof PathNode)
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
    	previous = ((NodeConnection)node.getPred().get(0)).getSource();
    	next = ((NodeConnection)node.getSucc().get(0)).getTarget();
    	for (Iterator i = node.getPred().iterator(); i.hasNext();) {
			NodeConnection con = (NodeConnection) i.next();
			sources.add(con.getSource());
		}
    	
    	for (Iterator i = node.getSucc().iterator(); i.hasNext();) {
    		NodeConnection con = (NodeConnection) i.next();
			targets.add(con.getTarget());
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
    	
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#getLabel()
     */
    public String getLabel() {
        return DeleteCommand_Label;
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