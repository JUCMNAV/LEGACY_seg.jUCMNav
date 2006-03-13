/**
 * 
 */
package seg.jUCMNav.model.commands.changeConstraints;

import grl.LinkRef;
import grl.LinkRefBendpoint;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command is used to move newBendpoint on linkref
 * 
 * @author Jean-François Roy
 *
 */
public class MoveLinkRefBendpointCommand extends Command implements JUCMNavCommand {

    //We need to delete the old newBendpoint and create a new one to allow refreshing the connection
    LinkRefBendpoint newBendpoint, oldBendpoint;
    LinkRef link;
    int index;
    int newX, newY;
    
    /**
     * 
     */
    public MoveLinkRefBendpointCommand(LinkRefBendpoint bendpoint, int x, int y) {
        this.oldBendpoint = bendpoint;
        this.newX = x;
        this.newY = y;
        this.link = oldBendpoint.getLinkref();
        this.index = link.getBendpoints().indexOf(oldBendpoint);
        setLabel(Messages.getString("MoveLinkRefBendpointCommand.moveLinkRefBendpoint")); //$NON-NLS-1$
        //System.out.println("Move Bendpoint: x=" + x + " y=" + y);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        newBendpoint = (LinkRefBendpoint) ModelCreationFactory.getNewObject(link.getDiagram().getUrndefinition().getUrnspec(), LinkRefBendpoint.class);
        newBendpoint.setX(newX);
        newBendpoint.setY(newY);
        redo();
    }
    
    /**
     * @return Returns the newX.
     */
    public int getNewX() {
        return newX;
    }

    /**
     * @return Returns the newY.
     */
    public int getNewY() {
        return newY;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        link.getBendpoints().remove(oldBendpoint);
        link.getBendpoints().add(index, newBendpoint);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert newBendpoint != null : "pre newBendpoint"; //$NON-NLS-1$
        assert oldBendpoint != null : "pre oldbendpoint"; //$NON-NLS-1$

        assert newBendpoint.getLinkref() != link: "pre newBendpoint link"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert newBendpoint != null : "post newBendpoint"; //$NON-NLS-1$
        assert oldBendpoint != null : "post oldbendpoint"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() != link: "post newBendpoint link"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        link.getBendpoints().remove(newBendpoint);
        link.getBendpoints().add(index, oldBendpoint);
        testPreConditions();
    }
}
