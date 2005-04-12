package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.ComponentRef;

/**
 * This command is used to resize/move ComponentRefs
 * 
 * @author jkealey
 *  
 */
public class ComponentSetConstraintCommand extends Command {

    private static final String Command_Label_Location = "change location command";
    private static final String Command_Label_Resize = "resize command";
    private Point newPosition;
    private Point oldPosition;

    private Dimension newDimension;
    private Dimension oldDimension;

    private ComponentRef comp;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldPosition = new Point(comp.getX(), comp.getY());
        oldDimension = new Dimension(comp.getWidth(), comp.getHeight());
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        comp.setX(newPosition.x);
        comp.setY(newPosition.y);
        if(oldDimension.height != newDimension.height || oldDimension.width != newDimension.width){
	        comp.setWidth(newDimension.width);
	        comp.setHeight(newDimension.height);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        comp.setX(oldPosition.x);
        comp.setY(oldPosition.y);
        comp.setWidth(oldDimension.width);
        comp.setHeight(oldDimension.height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#getLabel()
     */
    public String getLabel() {
        return Command_Label_Resize;
    }

    /**
     * @param comp
     *            The component to set.
     */
    public void setComponentRef(ComponentRef comp) {
        this.comp = comp;
    }

    /**
     * @return Returns the newPosition.
     */
    public Point getNewPosition() {
        return newPosition;
    }

    /**
     * @param newPosition
     *            The newPosition to set.
     */
    public void setNewPosition(Point newPosition) {
        this.newPosition = newPosition;
    }

    /**
     * @return Returns the newDimension.
     */
    public Dimension getNewDimension() {
        return newDimension;
    }

    /**
     * @param newDimension
     *            The newDimension to set.
     */
    public void setNewDimension(Dimension newDimension) {
        this.newDimension = newDimension;
    }
    
    /**
     * We can't resize or move fixed components.
     * @return if the command can execute
     */
    public boolean canExecute() {
        return comp!=null && !comp.isFixed();
    }
}