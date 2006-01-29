package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Compound command that moves/resizes a SpecificationComponentRef and all of its children.
 * 
 * [class wins worst name ever award]
 * 
 * @author jkealey
 *  
 */
public class SetConstraintBoundContainerRefCompoundCommand extends CompoundCommand implements JUCMNavCommand {

    // constraints
    private int newX, newY, newWidth, newHeight;

    private int oldX, oldY, oldWidth, oldHeight;

    // the parent and its children
    private IURNContainerRef compRef;

    /**
     * @param cr
     *            The ComponentRef to move and bind, allong with its children.
     * @param x
     *            the target x
     * @param y
     *            the target y
     * @param width
     *            the target width
     * @param height
     *            the target height
     */
    public SetConstraintBoundContainerRefCompoundCommand(IURNContainerRef cr, int x, int y, int width, int height) {
        // must precede compRef because of factor calculation.
        setConstraints(x, y, width, height);
        setCompRef(cr);
        setLabel(Messages.getString("SetConstraintBoundContainerRefCompoundCommand.changeCompConstraints")); //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // because we can't clear the parent list.
        assert size() == 0 : "pre: trying to add commands even though the children have already been generated. "; //$NON-NLS-1$

        assert oldWidth > 0 && oldHeight > 0 : "pre: invalid constraints; may cause division by zero."; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // nothing useful to check.
    }

    /**
     * @return Returns the compRef.
     */
    public IURNContainerRef getCompRef() {
        return compRef;
    }

    /**
     * Set the component to be moved/resized with its children. This method is private because we ca only generate the children once because we can't clear the
     * command list.
     * 
     * @param compRef
     *            The compRef to set.
     */
    private void setCompRef(IURNContainerRef compRef) {
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
     *            the new x
     * @param y
     *            the new y
     * @param width
     *            the new width
     * @param height
     *            the new height
     */
    private void setConstraints(int x, int y, int width, int height) {
        newX = x;
        newY = y;
        newWidth = width;
        newHeight = height;
    }

    /**
     * Using the compRef's children (Nodes and ComponentRefs), build a set of commands to be executed to move/resize the children with the parent. Note:
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
        SetConstraintContainerRefCommand cmd = new SetConstraintContainerRefCommand(getCompRef(), newX, newY, newWidth, newHeight);
        v.addAll(cmd.getOriginalChildren());

        add(cmd);

        while (v.size() > 0) {

            URNmodelElement elem = (URNmodelElement) v.get(0);
            if (elem instanceof IURNContainerRef) {
                IURNContainerRef child = (IURNContainerRef) elem;
                cmd = new SetConstraintContainerRefCommand(child, newX + (int) ((child.getX() - oldX) * factorW), newY
                        + (int) ((child.getY() - oldY) * factorH), (int) (child.getWidth() * factorW), (int) (child.getHeight() * factorH));
                v.addAll(cmd.getOriginalChildren());
                add(cmd);

            } else if (elem instanceof IURNNode) {
                IURNNode child = (IURNNode) elem;

                Command cmd2 = new SetConstraintCommand(child, newX + (int) ((child.getX() - oldX) * factorW), newY + (int) ((child.getY() - oldY) * factorH));
                add(cmd2);

            } else {
                System.out.println(Messages.getString("SetConstraintBoundContainerRefCompoundCommand.unknownType")); //$NON-NLS-1$
            }

            v.remove(0);
        }

        testPostConditions();
    }

}