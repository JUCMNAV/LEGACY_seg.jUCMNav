package seg.jUCMNav.model.commands.delete;

import java.util.Map;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.DeleteStartNCEndCommand;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.util.DoesDisconnectImplyDelete;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;

/**
 * Deletes a NodeConnection by disconnecting it from its extremities if they are join/stubs/forks/timer and if not, attempts a CutPathCommand.
 * 
 * @author jkealey
 * 
 */
public class DeleteNodeConnectionCommand extends CompoundCommand {

    public DeleteNodeConnectionCommand(NodeConnection nc, Map editpartregistry) {
        PathNode source = (PathNode) nc.getSource();
        PathNode target = (PathNode) nc.getTarget();

        setLabel(Messages.getString("DeleteNodeConnectionCommand.deleteNodeConnection")); //$NON-NLS-1$

        if (source instanceof Stub || source instanceof OrFork || source instanceof AndFork || (source instanceof Timer && source.getSucc().indexOf(nc) == 1)) {
            Vector in = new Vector();
            Vector out = new Vector();
            out.add(nc);

            DoesDisconnectImplyDelete verification = new DoesDisconnectImplyDelete(source, in, out);
            add(new DeleteBranchesCommand(source, verification, editpartregistry));
            if (target instanceof EndPoint) {
                add(new PreDeleteUrnModelElementCommand(target));
                add(new DeleteStartNCEndCommand((EndPoint) target));
            }

        }
        if (target instanceof Stub || target instanceof OrJoin || target instanceof AndJoin) {
            Vector in = new Vector();
            Vector out = new Vector();
            in.add(nc);
            DoesDisconnectImplyDelete verification = new DoesDisconnectImplyDelete(target, in, out);
            add(new DeleteBranchesCommand(target, verification, editpartregistry));

            if (source instanceof StartPoint) {
                add(new PreDeleteUrnModelElementCommand(source));
                add(new DeleteStartNCEndCommand((StartPoint) source));
            }

        }

        if (getCommands().size() == 0) {
            Command cmd = new CutPathCommand((UCMmap) nc.getDiagram(), nc);
            if (cmd.canExecute()) {
                add(cmd);
            }

        }
    }
}
