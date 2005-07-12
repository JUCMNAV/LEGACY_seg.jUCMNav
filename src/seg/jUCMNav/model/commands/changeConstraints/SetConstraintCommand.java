package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * This command is used to resize/move PathNodes. Is a compound command because might move connected elements.
 * 
 * @author jkealey
 *  
 */
public class SetConstraintCommand extends CompoundCommand {

    /**
     * 
     * @param node
     *            The PathNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     */
    public SetConstraintCommand(PathNode node, int x, int y) {
        add(new MovePathNodeCommand(node, x, y));

        if (node.getPred().size() > 0) {
            for (Iterator iter = node.getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                if (nc.getSource() instanceof Connect) {
                    add(new MovePathNodeCommand(nc.getSource(), x, y));
                    add(new MovePathNodeCommand(((NodeConnection) nc.getSource().getPred().get(0)).getSource(), x, y));
                }
            }
        }
        if (node.getSucc().size() > 0) {
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                if (nc.getTarget() instanceof Connect) {
                    add(new MovePathNodeCommand(nc.getTarget(), x, y));
                    add(new MovePathNodeCommand(((NodeConnection) nc.getTarget().getSucc().get(0)).getTarget(), x, y));
                }
            }
        }
    }

}