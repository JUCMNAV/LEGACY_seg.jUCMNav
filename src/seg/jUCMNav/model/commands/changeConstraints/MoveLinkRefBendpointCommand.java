package seg.jUCMNav.model.commands.changeConstraints;

import grl.LinkRef;
import grl.LinkRefBendpoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
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

    // We need to delete the old newBendpoint and create a new one to allow refreshing the connection
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
        // System.out.println("Move Bendpoint: x=" + x + " y=" + y);
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
        if(link.getBendpoints().contains(oldBendpoint))
        {
            link.getBendpoints().remove(oldBendpoint);
            link.getBendpoints().add(index, newBendpoint);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert newBendpoint != null : "pre newBendpoint"; //$NON-NLS-1$
        assert oldBendpoint != null : "pre oldbendpoint"; //$NON-NLS-1$

        assert newBendpoint.getLinkref() != link : "pre newBendpoint link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert newBendpoint != null : "post newBendpoint"; //$NON-NLS-1$
        assert oldBendpoint != null : "post oldbendpoint"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() != link : "post newBendpoint link"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if(link.getBendpoints().contains(newBendpoint))
        {
            link.getBendpoints().remove(newBendpoint);
            link.getBendpoints().add(index, oldBendpoint);
        }
        testPreConditions();
    }
    
    public static List getMoveLinkRefBendpointCommand(LinkRef ref, int deltaX, int deltaY, boolean multipleNodeMoved, boolean isNext)
    {
        List commands = new ArrayList();
        
        if (ref.getBendpoints().size() > 0) {
            EList bendpoints = ref.getBendpoints();

            int index = 0;
            for (Iterator y = bendpoints.iterator(); y.hasNext();) {
                LinkRefBendpoint bendpoint = (LinkRefBendpoint) y.next();
                float weight = 1;

                if (!multipleNodeMoved) {
                    // Calculate how moving the node should affect the bendpoints.
                    // The closer the bendpoint is to the moving node, the more it is affected by the move.
                    // This simulate the behavior of RelativeBendpoint in eclipse.
                    // Also, don't use this when moving multiple nodes at the same time and moving bendpoints because this can mess bendpoints positions
                    weight = (index + 1) / ((float) bendpoints.size() + 1);
                    if (isNext)
                        weight = ((bendpoints.size() - index) + 1) / ((float) bendpoints.size() + 1);

                    // Bendpoints directly next to another node are not affected when we move the other node of the connection
                    if ((index == 0 && !isNext) || (index == (bendpoints.size() - 1) && isNext))
                        weight = 0;
                    else if ((index == (bendpoints.size() - 1) && !isNext) || (index == 0 && isNext))
                        weight = 1;
                }

                // Simlate a translation of the bendpoint location by the delta movement of the node.  Affect the bendpoint by the weight calculated above.
                int newX, newY;
                newX = bendpoint.getX() + Math.round(deltaX * weight);
                newY = bendpoint.getY() + Math.round(deltaY * weight);

                commands.add(new MoveLinkRefBendpointCommand((LinkRefBendpoint) ref.getBendpoints().get(index), newX, newY));

                index++;
            }
        }
        return commands;
    }
}
