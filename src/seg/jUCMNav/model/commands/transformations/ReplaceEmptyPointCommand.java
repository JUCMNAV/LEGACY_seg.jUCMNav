package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * CompoundCommand that replaces an unconnected EmptyPoint or DirectionArrow with another path node.
 * 
 * @author jkealey
 *  
 */
public class ReplaceEmptyPointCommand extends CompoundCommand {

    /**
     * @param empty
     *            the empty node or direction arrow to remove
     * @param newNode
     *            the new node to replace it with
     */
    public ReplaceEmptyPointCommand(PathNode empty, PathNode newNode) {
        assert empty instanceof EmptyPoint || empty instanceof DirectionArrow : "invalid empty point"; //$NON-NLS-1$
        if (empty.getPred().size() == 1 && empty.getSucc().size() == 1) {
            int x = empty.getX();
            int y = empty.getY();
            NodeConnection previous = (NodeConnection) empty.getPred().get(0);
            add(new DeleteNodeCommand(empty));
            add(new SplitLinkCommand(empty.getPathGraph(), newNode, previous, x, y));
        }
    }
}