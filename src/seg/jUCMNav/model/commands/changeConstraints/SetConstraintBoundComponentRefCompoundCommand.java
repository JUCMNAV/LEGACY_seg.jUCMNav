package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Created on 1-May-2005 Compound command that moves/resizes a ComponentRef and all of its children.
 * 
 * @author jkealey
 *  
 */
public class SetConstraintBoundComponentRefCompoundCommand extends CompoundCommand implements JUCMNavCommand {

    // constraints
    private int newX, newY, newWidth, newHeight;
    private int oldX, oldY, oldWidth, oldHeight;

    // the parent and its children
    private ComponentRef compRef;

    public SetConstraintBoundComponentRefCompoundCommand(ComponentRef cr, int x, int y, int width, int height) {
        // must precede compRef because of factor calculation.
        setConstraints(x, y, width, height);
        setCompRef(cr);
        setLabel("Change Component Constraints");
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // because we can't clear the parent list.
        assert size() == 0 : "pre: trying to add commands even though the children have already been generated. ";

        assert oldWidth > 0 && oldHeight > 0 : "pre: invalid constraints; may cause division by zero.";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // nothing to check.
    }

    /**
     * @return Returns the compRef.
     */
    public ComponentRef getCompRef() {
        return compRef;
    }

    /**
     * Set the component to be moved/resized with its children. This method is private because we ca only generate the children once because we can't clear the command list.
     * @param compRef
     *            The compRef to set.
     */
    private void setCompRef(ComponentRef compRef) {
        this.compRef = compRef;
        oldX = compRef.getX();
        oldY = compRef.getY();
        oldHeight = compRef.getHeight();
        oldWidth = compRef.getWidth();

        buildChildCommands();
    }

    /**
     * Set the new constraints. This method is private because we ca only generate the children once because we can't clear the command list.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setConstraints(int x, int y, int width, int height) {
        newX = x;
        newY = y;
        newWidth = width;
        newHeight = height;
    }

    /**
     * Using the compRef's children (PathNodes and ComponentRefs), build a set of commands to be executed to move/resize the children with the parent. Note:
     * when resizing the parent, children are moved inside the parent so they retain the same relative position inside the parent. if they kept the same
     * absolute position, they could potentially become outside the parent and they would thus no longer be children of the parnet.
     *  
     */
    private void buildChildCommands() {
        testPreConditions();

        // needed to be able to move elements on component resize.
        double factorW, factorH;
        factorW = ((double) newWidth) / oldWidth;
        factorH = ((double) newHeight) / oldHeight;
        
        Vector v = new Vector();
        SetConstraintComponentRefCommand cmd = new SetConstraintComponentRefCommand(getCompRef(), newX, newY, newWidth, newHeight);
        v.addAll(cmd.getOriginalChildren());

        add(cmd);

        while (v.size() > 0) {

            UCMmodelElement elem = (UCMmodelElement) v.get(0);
            if (elem instanceof ComponentRef) {
                ComponentRef child = (ComponentRef) elem;
                cmd = new SetConstraintComponentRefCommand(child, newX + (int) ((child.getX() - oldX) * factorW), newY
                        + (int) ((child.getY() - oldY) * factorH), (int) (child.getWidth() * factorW), (int) (child.getHeight() * factorH));
                v.addAll(cmd.getOriginalChildren());
                add(cmd);

            } else if (elem instanceof PathNode) {
                PathNode child = (PathNode) elem;

                Command cmd2 = new SetConstraintCommand(child, newX + (int) ((child.getX() - oldX) * factorW), newY + (int) ((child.getY() - oldY) * factorH));
                add(cmd2);

            } else {
                System.out.println("Error: unknown type in SetConstraintBoundComponentRefCompoundCommand");
            }

            v.remove(0);
        }

        testPostConditions();
    }

}