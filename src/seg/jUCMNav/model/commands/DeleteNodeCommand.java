/*
 * Created on 2005-02-07
 *
 */
package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * @author Etienne Tremblay
 *
 */
public class DeleteNodeCommand extends Command {
	
	private static final String	DeleteCommand_Label = "DeletePathNodeCommand";
	
	private PathNode node;
	private NodeConnection source;
	private NodeConnection target;
	
	public DeleteNodeCommand(){
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if( node instanceof StartPoint ||
			node instanceof EndPoint)
			return false;
		else
			return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {

	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		
	}
	
	/* (non-Javadoc)
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
	 * @param PathPathNode The PathPathNode to set.
	 */
	public void setPathNode(PathNode node) {
		this.node = node;
	}
}
