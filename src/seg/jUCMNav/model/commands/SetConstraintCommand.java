/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.SizedElement;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetConstraintCommand extends Command {
	
	private static final String Command_Label_Location = "change location command";
	private static final String Command_Label_Resize = "resize command";
	private Rectangle newBounds;
	
	private Rectangle oldBounds;
	
	private SizedElement node;

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldBounds = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		node.setX(newBounds.x);
		node.setY(newBounds.y);
		node.setHeight(newBounds.height);
		node.setWidth(newBounds.width);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		node.setX(oldBounds.x);
		node.setY(oldBounds.y);
		node.setHeight(oldBounds.height);
		node.setWidth(oldBounds.width);
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
	public void setNode(SizedElement node) {
		this.node = node;
	}
	/**
	 * @return Returns the newBounds.
	 */
	public Rectangle getNewBounds() {
		return newBounds;
	}
	/**
	 * @param newBounds The newBounds to set.
	 */
	public void setNewBounds(Rectangle newBounds) {
		this.newBounds = newBounds;
	}
}
