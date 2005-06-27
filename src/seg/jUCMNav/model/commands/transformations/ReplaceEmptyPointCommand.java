package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * CompoundCommand that replaces an unconnected emptypoint with another path node.
 * 
 * @author jkealey
 *  
 */
public class ReplaceEmptyPointCommand extends CompoundCommand {

    /**
     * 
     * @param empty
     *            the empty node to remove
     * @param newNode
     *            the new node to replace it with
     */
    public ReplaceEmptyPointCommand(EmptyPoint empty, PathNode newNode) {
        if (empty.getPred().size() == 1 && empty.getSucc().size() == 1) {
            int x = empty.getX();
            int y = empty.getY();
            NodeConnection previous = (NodeConnection) empty.getPred().get(0);
            add(new DeleteNodeCommand(empty));
            add(new SplitLinkCommand(empty.getPathGraph(), newNode, previous, x, y));
        }
    }
}