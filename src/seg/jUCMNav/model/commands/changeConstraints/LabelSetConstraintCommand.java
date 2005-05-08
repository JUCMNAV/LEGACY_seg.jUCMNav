/*
 * Created on Mar 31, 2005
 */
package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import urncore.Label;

/**
 * @author Jordan
 */
public class LabelSetConstraintCommand extends Command {
    private static final String Command_Label_Location = "change location command";
	private static final String Command_Label_Resize = "resize command";
	private int newDeltaX;
	private int newDeltaY;
	
	private int oldDeltaX;
	private int oldDeltaY;
	
	private Label label;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldDeltaX = label.getDeltaX();
		oldDeltaY = label.getDeltaY();
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
	    label.setDeltaX(newDeltaX);
	    label.setDeltaY(newDeltaY);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
	    label.setDeltaX(oldDeltaX);
	    label.setDeltaY(oldDeltaY);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	public String getLabel() {
		return Command_Label_Resize;
	}

	/**
	 * @param label The label to set.
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
	/**
	 * @param newPosition The newPosition to set.
	 */
	public void setNewPosition(int newDeltaX, int newDeltaY) {
		this.newDeltaX = newDeltaX;
		this.newDeltaY = newDeltaY;
	}
}
