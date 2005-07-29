package seg.jUCMNav.model.commands.delete;

import java.util.Map;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.DeleteStartNCEndCommand;
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

/**
 * Deletes a NodeConnection by disconnecting it from its extremities if they are join/stubs/forks/timer and if not, attempts a CutPathCommand.
 * 
 * @author jkealey
 * 
 */
public class DeleteNodeConnectionCommand extends CompoundCommand {

    private NodeConnection nc;

    public DeleteNodeConnectionCommand(NodeConnection nc, Map editpartregistry) {
        this.nc = nc;
        PathNode source = nc.getSource();
        PathNode target = nc.getTarget();

        if (source instanceof Stub || source instanceof OrFork || source instanceof AndFork || (source instanceof Timer && source.getSucc().indexOf(nc) == 1)) {
            Vector in = new Vector();
            Vector out = new Vector();
            out.add(nc);

            DoesDisconnectImplyDelete verification = new DoesDisconnectImplyDelete(source, in, out);
            add(new DeleteBranchesCommand(source, verification, editpartregistry));
            if (target instanceof EndPoint)
                add(new DeleteStartNCEndCommand((EndPoint) target));

        }
        if (target instanceof Stub || target instanceof OrJoin || target instanceof AndJoin) {
            Vector in = new Vector();
            Vector out = new Vector();
            in.add(nc);
            DoesDisconnectImplyDelete verification = new DoesDisconnectImplyDelete(target, in, out);
            add(new DeleteBranchesCommand(target, verification, editpartregistry));

            if (source instanceof StartPoint)
                add(new DeleteStartNCEndCommand((StartPoint) source));

        }

        if (getCommands().size() == 0) {
            Command cmd = new CutPathCommand(nc.getPathGraph(), nc);
            if (cmd.canExecute()) {
                add(cmd);
            }

        }
    }
}
