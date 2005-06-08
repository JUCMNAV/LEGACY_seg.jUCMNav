/*
 * Created on Mar 31, 2005
 */
package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Label;

/**
 * @author Jordan
 */
public class LabelSetConstraintCommand extends Command implements JUCMNavCommand {
    private static final String Command_Label_Location = Messages.getString("LabelSetConstraintCommand.changeLocation"); //$NON-NLS-1$
	private static final String Command_Label_Resize = Messages.getString("LabelSetConstraintCommand.resizeCommand"); //$NON-NLS-1$
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
		testPreConditions();
		
	    label.setDeltaX(newDeltaX);
	    label.setDeltaY(newDeltaY);
	    
	    testPostConditions();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();
		
	    label.setDeltaX(oldDeltaX);
	    label.setDeltaY(oldDeltaY);
	    
	    testPreConditions();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	public String getLabel() {
		return Command_Label_Resize;
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "post Label"; //$NON-NLS-1$
        assert label.getDeltaX() == newDeltaX && label.getDeltaY() == newDeltaY : "post Label position"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "post Label"; //$NON-NLS-1$
        assert label.getDeltaX() == oldDeltaX && label.getDeltaY() == oldDeltaY : "pre Label position"; //$NON-NLS-1$
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
