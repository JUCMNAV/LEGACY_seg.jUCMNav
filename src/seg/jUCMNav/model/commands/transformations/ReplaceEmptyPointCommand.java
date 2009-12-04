package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urncore.Condition;

/**
 * CompoundCommand that replaces an unconnected EmptyPoint or DirectionArrow with another path node.
 * 
 * @author jkealey
 * 
 */
public class ReplaceEmptyPointCommand extends CompoundCommand implements ICreateElementCommand {

    private PathNode empty;
    private PathNode newNode;
    private Condition outgoingCondition;

    public Condition getOutgoingCondition() {
        return outgoingCondition;
    }

    public void setOutgoingCondition(Condition outgoingCondition) {
        this.outgoingCondition = outgoingCondition;
    }

    /**
     * @param empty
     *            the empty node or direction arrow to remove
     * @param newNode
     *            the new node to replace it with
     */
    public ReplaceEmptyPointCommand(PathNode empty, PathNode newNode) {
        this.empty = empty;
        this.newNode = newNode;
        setLabel(Messages.getString("ReplaceEmptyPointCommand.replaceEmptyPoint")); //$NON-NLS-1$
    }

    /**
     * @param empty
     *            the empty node or direction arrow to remove
     * @param newNode
     *            the new node to replace it with
     * @param outgoingCondition
     *            the condition to set on the outgoing node connection
     */
    public ReplaceEmptyPointCommand(PathNode empty, PathNode newNode, Condition outgoingCondition) {
        this.empty = empty;
        this.newNode = newNode;
        this.outgoingCondition = outgoingCondition;
        setLabel(Messages.getString("ReplaceEmptyPointCommand.replaceEmptyPoint")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    public void execute() {
        // delay until execution
        build();
        super.execute();
    }

    private void build() {
        assert empty instanceof EmptyPoint || empty instanceof DirectionArrow : "invalid empty point"; //$NON-NLS-1$
        if (empty.getPred().size() == 1 && empty.getSucc().size() == 1) {
            int x = empty.getX();
            int y = empty.getY();
            NodeConnection previous = (NodeConnection) empty.getPred().get(0);
            // I know we won't be using the editpartregistry to replace the empty point or direction arrow.
            add(new RemovePathNodeCommand(empty, null));
            add(new SplitLinkCommand((UCMmap) empty.getDiagram(), newNode, previous, x, y, outgoingCondition));
        }
    }

    public Object getNewModelElement() {
        return newNode;
    }
}