/*
 * Created on Mar 31, 2005
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import urncore.NodeLabel;

/**
 * @author Jordan
 */
public class LabelSetConstraintCommand extends Command {
    private static final String Command_Label_Location = "change location command";
	private static final String Command_Label_Resize = "resize command";
	private Point newPosition;
	
	private Point oldPosition;
	
	private NodeLabel label;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldPosition = new Point(label.getDeltaX(), label.getDeltaY());
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
	    label.setDeltaX(newPosition.x);
	    label.setDeltaY(newPosition.y);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
	    label.setDeltaX(oldPosition.x);
	    label.setDeltaY(oldPosition.y);
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
	public void setNode(NodeLabel label) {
		this.label = label;
	}

	/**
	 * @param newPosition The newPosition to set.
	 */
	public void setNewPosition(Point newPosition) {
		this.newPosition = newPosition;
	}
}
