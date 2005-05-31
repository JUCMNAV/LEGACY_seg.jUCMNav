package seg.jUCMNav.model.commands.delete;

import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * Created on 29-May-2005
 * 
 * @author jkealey
 *  
 */
public class DeletePathCommand extends CompoundCommand {
    private Stack commands;
    private Map editpartregistry;
    private EndPoint end;

    private StartPoint start;

    public DeletePathCommand(EndPoint end, Map editpartregistry) {
        this.end = end;
        this.editpartregistry = editpartregistry;
    }

    public DeletePathCommand(StartPoint start, Map editpartregistry) {
        this.start = start;
        this.editpartregistry = editpartregistry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#canExecute()
     */
    public boolean canExecute() {
        return true;
    }

    /*
     * (non-Javadoc)
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

        PathNode node = this.end;
        PathNode next;
        commands = new Stack();

        commands.add(new DeleteStartNCEndCommand(this.end));

        do {
            next = node;
            // was already deleted.
            if (node.getPred().size() == 0)
                break;
            node = (PathNode) ((NodeConnection) node.getPred().get(0)).getSource();

            if (!(node instanceof Stub) && node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
            } else if (!(node instanceof StartPoint)&&!(node instanceof EndPoint)) {
                Vector v = new Vector();
                v.add((NodeConnection) next.getPred().get(0));
                commands.add(new DeleteMultiNodeCommand(node, new Vector(), v, this.editpartregistry));
                break;
            }
        } while (node != null && node.getPred().size() == 1);

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

        PathNode node = this.start;
        PathNode previous;
        commands = new Stack();

        commands.add(new DeleteStartNCEndCommand(this.start));

        do {
            previous = node;
            // was already deleted.
            if (node.getSucc().size() == 0)
                break;

            node = (PathNode) ((NodeConnection) node.getSucc().get(0)).getTarget();

            if (!(node instanceof Stub) && node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
            } else if (!(node instanceof StartPoint)&&!(node instanceof EndPoint)) {
                Vector v = new Vector();
                v.add((NodeConnection) previous.getSucc().get(0));
                commands.add(new DeleteMultiNodeCommand(node, v, new Vector(), this.editpartregistry));
                break;
            }
        } while (node != null && node.getSucc().size() == 1);

        while (commands.size() != 0)
            add((Command) commands.pop());
    }

    /*
     * (non-Javadoc)
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