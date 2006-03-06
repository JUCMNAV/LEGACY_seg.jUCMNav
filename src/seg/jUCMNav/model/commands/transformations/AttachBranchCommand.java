package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.transformations.internal.AttachEndCommand;
import seg.jUCMNav.model.commands.transformations.internal.AttachStartCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * 
 * This command will attach a start/end point onto a stub/join/fork.
 * 
 * @author jkealey
 * 
 */
public class AttachBranchCommand extends CompoundCommand {

    /**
     * @param startEnd
     *            The start/end point to be attached.
     * @param stubJoinFork
     *            The stub/join/fork where startEnd will get merged.
     */
    public AttachBranchCommand(PathNode startEnd, PathNode stubJoinFork) {
        add(new PreDeleteUrnModelElementCommand(startEnd));

        if (startEnd instanceof StartPoint) {
            assert stubJoinFork instanceof Stub || stubJoinFork instanceof AndFork || stubJoinFork instanceof OrFork : "not a stub/fork"; //$NON-NLS-1$
            add(new AttachStartCommand((StartPoint) startEnd, stubJoinFork));

        } else if (startEnd instanceof EndPoint) {
            assert stubJoinFork instanceof Stub || stubJoinFork instanceof AndJoin || stubJoinFork instanceof OrJoin : "not a stub/join"; //$NON-NLS-1$
            add(new AttachEndCommand((EndPoint) startEnd, stubJoinFork));

        } else {
            assert false : "not a start or end point"; //$NON-NLS-1$
        }
        setLabel(Messages.getString("AttachBranchCommand.attachBranch")); //$NON-NLS-1$
    }
}
