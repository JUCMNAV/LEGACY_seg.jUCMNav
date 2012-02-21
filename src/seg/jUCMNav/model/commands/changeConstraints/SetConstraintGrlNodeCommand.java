package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urncore.IURNNode;

/**
 * This command is used to resize/move Nodes. Is a compound command because might move connected elements.
 * 
 * @author jkealey
 * 
 */
public class SetConstraintGrlNodeCommand extends CompoundCommand {

    /**
     * 
     * @param node
     *            The SpecificationNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     */
    public SetConstraintGrlNodeCommand(IURNNode node, int x, int y, List moveBendpointCommands) {
        setLabel(Messages.getString("SetConstraintCommand.setNodeConstraints")); //$NON-NLS-1$

        add(new SetConstraintCommand(node, x, y));
        for (Iterator i = moveBendpointCommands.iterator(); i.hasNext();) {
            add((Command) i.next());
        }
    }
}