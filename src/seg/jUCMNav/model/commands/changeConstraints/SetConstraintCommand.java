package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urncore.SpecificationConnection;
import urncore.SpecificationNode;

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
    public SetConstraintCommand(SpecificationNode node, int x, int y) {
        add(new MoveSpecificationNodeCommand(node, x, y));

        if (node.getPred().size() > 0) {
            for (Iterator iter = node.getPred().iterator(); iter.hasNext();) {
                SpecificationConnection nc = (SpecificationConnection) iter.next();
                if (nc.getSource() instanceof Connect) {
                    add(new MoveSpecificationNodeCommand((PathNode)nc.getSource(), x, y));
                    add(new MoveSpecificationNodeCommand((PathNode)((NodeConnection) nc.getSource().getPred().get(0)).getSource(), x, y));
                }
            }
        }
        if (node.getSucc().size() > 0) {
            for (Iterator iter = node.getSucc().iterator(); iter.hasNext();) {
                SpecificationConnection nc = (SpecificationConnection) iter.next();
                if (nc.getTarget() instanceof Connect) {
                    add(new MoveSpecificationNodeCommand((PathNode)nc.getTarget(), x, y));
                    add(new MoveSpecificationNodeCommand((PathNode)((NodeConnection) nc.getTarget().getSucc().get(0)).getTarget(), x, y));
                }
            }
        }
    }

}