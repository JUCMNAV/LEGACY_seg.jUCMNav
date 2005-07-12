package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.AddBranchCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * Adds a new fork/join on an existing NodeConnection/EmptyPoint.
 * 
 * @author jpdaigle, jkealey
 *  
 */
public class AddForkOrJoinCompoundCommand extends CompoundCommand {

    public AddForkOrJoinCompoundCommand(PathNode newFork, PathGraph pg, NodeConnection nc, int x, int y) {
        this.setLabel(Messages.getString("AddForkOnConnectionCommand.addFork")); //$NON-NLS-1$

        add(new SplitLinkCommand(pg, newFork, nc, x, y));
        add(new AddBranchCommand(newFork, true));
    }
    
    public AddForkOrJoinCompoundCommand(PathNode newFork, PathGraph pg, PathNode ep) {
        add(new ReplaceEmptyPointCommand(ep, newFork));
        add(new AddBranchCommand(newFork, true));

        setLabel(Messages.getString("AddForkOnEmptyPointCommand.addFork")); //$NON-NLS-1$
    }
}