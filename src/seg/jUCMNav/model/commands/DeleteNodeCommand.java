/*
 * Created on 2005-02-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.StartPoint;

/**
 * @author Etienne Tremblay
 *
 */
public class DeleteNodeCommand extends Command {
	
	private static final String	DeleteCommand_Label = "DeleteNodeCommand";
	
	private Node node;
	private Link source;
	private Link target;
	
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
	 * @return Returns the node.
	 */
	public Node getNode() {
		return node;
	}
	/**
	 * @param node The node to set.
	 */
	public void setNode(Node node) {
		this.node = node;
	}
}
