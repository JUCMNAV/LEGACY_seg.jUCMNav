package seg.jUCMNav.model.commands.delete;

import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * Command to delete a path, starting with a StartPoint or EndPoint.
 * 
 * General algorithm: Given a StartPoint, traverse path in the proper direction until you find a path node with something else than 1 in / 1 out. This new
 * PathNode is either a EndPoint or a PathNode with multiple connections. If its not an EmptyPoint, disconnect yourself from it using DeleteMultiNodeCommand.
 * You then have a path containing only simple nodes. Remove all of these nodes from the model using DeleteNodeCommand and end by removing the small
 * start->nc->end path with DeleteStartNCEndCommand.
 * 
 * Algorithm can be done in inverse sense for EndPoint.
 * 
 * Special consideration has to be given to removing Connects and InBindings/OutBindings.
 * 
 * Should also get rid of deletion PluginBindings on StartPoints/EndPoints but is not done.
 * 
 * Note: This command should use DeletionPathFinder instead of its custom path traversal algorithm.
 * 
 * @author jkealey
 *  
 */
public class DeletePathCommand extends CompoundCommand {

    // we'll push our commands onto this stack to avoid traversing the path twice.
    private Stack commands;
    private Map editpartregistry;
    private EndPoint end;
    private PathGraph pg;
    private StartPoint start;

    /**
     * Delete the path ending at end.
     * 
     * @param end
     *            delete the path ending here.
     * @param editpartregistry
     *            editpartregistry needed to be propagated to DeleteMultiNodeCommand.
     */
    public DeletePathCommand(EndPoint end, Map editpartregistry) {
        this.end = end;
        this.editpartregistry = editpartregistry;
    }

    /**
     * 
     * @param start
     *            delete the path starting here.
     * @param editpartregistry
     *            editpartregistry needed to be propagated to DeleteMultiNodeCommand.
     */
    public DeletePathCommand(StartPoint start, Map editpartregistry) {
        this.start = start;
        this.editpartregistry = editpartregistry;
    }

    /**
     * Always execute; used because no commands are in the list when canExecute() is first called (as they are added in execute().
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#canExecute()
     */
    public boolean canExecute() {
        return true;
    }

    /**
     * Always allow undo. Even if has already been deleted, so that this command can be located in a GEF compound command.
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#canExecute()
     */
    public boolean canUndo() {
        return true;
    }

    /**
     * Builds the compound from its end.
     */
    private void buildFromEnd() {
        // was already deleted.
        if (this.end.getPathGraph() == null)
            return;

        Command disconnectCmd;

        PathGraph pg = this.end.getPathGraph();
        PathNode node = this.end;
        PathNode next;
        commands = new Stack();

        commands.add(new DeleteStartNCEndCommand(this.end));

        if (this.end.getSucc().size() > 0) {
            disconnectCmd = new DisconnectCommand(this.end);
            if (disconnectCmd.canExecute()) {
                commands.add(disconnectCmd);
            }
        }

        do {
            next = node;
            // was already deleted.
            if (node.getPred().size() == 0)
                break;
            node = (PathNode) ((NodeConnection) node.getPred().get(0)).getSource();

            if (!(node instanceof Stub) && node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
            } else if (!(node instanceof StartPoint) && !(node instanceof EndPoint)) {
                Vector v = new Vector();
                v.add((NodeConnection) next.getPred().get(0));
                commands.add(new DeleteMultiNodeCommand(node, new Vector(), v, this.editpartregistry));
                break;
            }
        } while (node != null && node.getPred().size() == 1 && !(node instanceof StartPoint));

        if (node instanceof StartPoint && node.getPred().size() == 1) {
            disconnectCmd = new DisconnectCommand(node);
            if (disconnectCmd.canExecute()) {
                commands.add(disconnectCmd);
            }
        }

        while (commands.size() != 0)
            add((Command) commands.pop());
    }

    /**
     * Build the compound command from its start.
     */
    private void buildFromStart() {
        // was already deleted.
        if (this.start.getPathGraph() == null)
            return;

        Command disconnectCmd;
        PathGraph pg = this.start.getPathGraph();

        PathNode node = this.start;
        PathNode previous;
        commands = new Stack();

        commands.add(new DeleteStartNCEndCommand(this.start));
        if (this.start.getSucc().size() > 0) {
            disconnectCmd = new DisconnectCommand(this.start);
            if (disconnectCmd.canExecute()) {
                commands.add(disconnectCmd);
            }
        }

        do {
            previous = node;
            // was already deleted.
            if (node.getSucc().size() == 0)
                break;

            node = (PathNode) ((NodeConnection) node.getSucc().get(0)).getTarget();

            if (!(node instanceof Stub) && node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
            } else if (!(node instanceof StartPoint) && !(node instanceof EndPoint)) {
                Vector v = new Vector();
                v.add((NodeConnection) previous.getSucc().get(0));
                commands.add(new DeleteMultiNodeCommand(node, v, new Vector(), this.editpartregistry));
                break;
            }
        } while (node != null && node.getSucc().size() == 1 && !(node instanceof EndPoint));

        if (node instanceof EndPoint && node.getSucc().size() == 1) {
            disconnectCmd = new DisconnectCommand(node);
            if (disconnectCmd.canExecute()) {
                commands.add(disconnectCmd);
            }
        }
        while (commands.size() != 0)
            add((Command) commands.pop());
    }

    /**
     * Adds commands and executes them.
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#execute()
     */
    public void execute() {

        if (start != null)
            buildFromStart();
        else if (end != null)
            buildFromEnd();
        super.execute();
    }

}