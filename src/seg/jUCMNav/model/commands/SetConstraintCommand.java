package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.PathNode;

/**
 * This command is used to resize/move PathNodes.
 * @author Etienne Tremblay
 *
 */
public class SetConstraintCommand extends Command {
	
	private static final String Command_Label_Location = "change location command";
	private static final String Command_Label_Resize = "resize command";
	private Point newPosition;
	
	private Point oldPosition;
	
	private PathNode node;

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldPosition = new Point(node.getX(), node.getY());
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		node.setX(newPosition.x);
		node.setY(newPosition.y);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		node.setX(oldPosition.x);
		node.setY(oldPosition.y);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	public String getLabel() {
		return Command_Label_Resize;
	}
	
	/**
	 * @param node The node to set.
	 */
	public void setNode(PathNode node) {
		this.node = node;
	}
	/**
	 * @return Returns the newPosition.
	 */
	public Point getNewPosition() {
		return newPosition;
	}
	/**
	 * @param newPosition The newPosition to set.
	 */
	public void setNewPosition(Point newPosition) {
		this.newPosition = newPosition;
	}
}
