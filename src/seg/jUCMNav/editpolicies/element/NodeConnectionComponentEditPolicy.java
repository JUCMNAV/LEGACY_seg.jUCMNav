package seg.jUCMNav.editpolicies.element;

import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteMultiNodeCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.Stub;
import ucm.map.Timer;

/**
 * Created on 30-May-2005
 * 
 * @author jkealey
 *  
 */
public class NodeConnectionComponentEditPolicy extends ComponentEditPolicy {
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        NodeConnection nc = (NodeConnection) getHost().getModel();
        PathNode source = nc.getSource();
        PathNode target = nc.getTarget();

        CompoundCommand command = new CompoundCommand();
        if (source instanceof Stub || source instanceof OrFork || source instanceof AndFork || (source instanceof Timer && source.getSucc().indexOf(nc) ==1) ) {
            Vector in = new Vector();
            Vector out = new Vector();
            out.add(nc);
            command.add(new DeleteMultiNodeCommand(source, in, out, getHost().getViewer().getEditPartRegistry()));
        }
        if (target instanceof Stub || target instanceof OrJoin || target instanceof AndJoin) {
            Vector in = new Vector();
            Vector out = new Vector();
            in.add(nc);
            command.add(new DeleteMultiNodeCommand(target, in, out, getHost().getViewer().getEditPartRegistry()));
        }

        if (source instanceof EmptyPoint && target instanceof EmptyPoint) {
            Command cmd = new CutPathCommand(nc.getPathGraph(), nc);
            if (cmd.canExecute()) {
                command.add(cmd);
            }
        }

        if (command.size() > 0)
            return command;

        return super.getDeleteCommand(request);
    }
}