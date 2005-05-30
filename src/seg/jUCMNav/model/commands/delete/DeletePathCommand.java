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

/**
 * Created on 29-May-2005
 * 
 * @author jkealey
 *  
 */
public class DeletePathCommand extends CompoundCommand {

    private StartPoint start;
    private EndPoint end;
    private Stack commands;
    private Map editpartregistry;

    public DeletePathCommand(StartPoint start, Map editpartregistry) {
        // was already deleted.
        if (start.getPathGraph() == null)
            return;
        this.start = start;
        this.editpartregistry = editpartregistry;

        PathNode node = start;
        PathNode previous;
        commands = new Stack();

       commands.add(new DeleteStartNCEndCommand(start));

        do {
            previous = node;
            // was already deleted.
            if (node.getSucc().size() == 0)
                break;

            node = (PathNode) ((NodeConnection) node.getSucc().get(0)).getTarget();

            if (node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
            } else {
                Vector v = new Vector();
                v.add((NodeConnection) previous.getSucc().get(0));
                commands.add(new DeleteMultiNodeCommand(node, v, new Vector(), this.editpartregistry));
                break;
            }
        } while (node != null && node.getSucc().size() == 1);

        while (commands.size() != 0)
            add((Command) commands.pop());
    }

    public DeletePathCommand(EndPoint end, Map editpartregistry) {
        // was already deleted.
        if (end.getPathGraph() == null)
            return;

        this.end = end;
        this.editpartregistry = editpartregistry;

        PathNode node = end;
        PathNode next;
        commands = new Stack();

        commands.add(new DeleteStartNCEndCommand(end));

        do {
            next = node;
            // was already deleted.
            if (node.getPred().size() == 0)
                break;
            node = (PathNode) ((NodeConnection) node.getPred().get(0)).getSource();

            if (node.getSucc().size() == 1 && node.getPred().size() == 1) {
                commands.add(new DeleteNodeCommand(node));
     
            } else {
                Vector v = new Vector();
                v.add((NodeConnection) next.getPred().get(0));
                commands.add(new DeleteMultiNodeCommand(node, new Vector(), v, this.editpartregistry));
                break;
            }
        } while (node != null && node.getPred().size() == 1);

        while (commands.size() != 0)
            add((Command) commands.pop());
    }

}