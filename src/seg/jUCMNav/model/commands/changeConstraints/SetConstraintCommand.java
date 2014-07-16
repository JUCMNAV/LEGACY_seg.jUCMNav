package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import urncore.IURNConnection;
import urncore.IURNNode;

/**
 * This command is used to resize/move Nodes. Is a compound command because might move connected elements.
 * 
 * @author jkealey
 * 
 */
public class SetConstraintCommand extends CompoundCommand {

    /**
     * 
     * @param node
     *            The SpecificationNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     */
    public SetConstraintCommand(IURNNode node, int x, int y) {
        setLabel(Messages.getString("SetConstraintCommand.setNodeConstraints")); //$NON-NLS-1$
        add(new MoveNodeCommand(node, x, y));
        
        

        if (node.getPred().size() > 0) {
            for (Iterator iter = node.getPred().iterator(); iter.hasNext();) {
                IURNConnection nc = (IURNConnection) iter.next();
                if (nc.getSource() instanceof Connect) {
                    add(new MoveNodeCommand(nc.getSource(), x, y));
                    add(new MoveNodeCommand(((NodeConnection) nc.getSource().getPred().get(0)).getSource(), x, y));
                }
            }
        }
        if (node.getSucc().size() > 0) {
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                IURNConnection nc = (IURNConnection) iter.next();
                if (nc.getTarget() instanceof Connect) {
                    add(new MoveNodeCommand(nc.getTarget(), x, y));
                    add(new MoveNodeCommand(((NodeConnection) nc.getTarget().getSucc().get(0)).getTarget(), x, y));
                }
            }
        }
    }

}