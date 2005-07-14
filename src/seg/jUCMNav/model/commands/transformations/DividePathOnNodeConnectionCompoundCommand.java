package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * This command divides a NodeConnection by splicing it with a StartPoint or EndPoint. The end result is the creation of a new fork or join.
 * 
 * @author jkealey
 *  
 */
public class DividePathOnNodeConnectionCompoundCommand extends CompoundCommand {

    /**
     * This command divides a NodeConnection by splicing it with a StartPoint or EndPoint. The end result is the creation of a new fork or join.
     * 
     * @param startOrEnd
     *            the StartPoint or EndPoint to merge with ncTarget
     * @param ncTarget
     *            on which NodeConnection the fork/join should be added.
     * @param x
     *            where the fork/join should be added
     * @param y
     *            where the fork/join should be added
     * @param isOrDivision
     *            true for or fork/join, false for and fork/join.
     *  
     */
    public DividePathOnNodeConnectionCompoundCommand(PathNode startOrEnd, NodeConnection ncTarget, int x, int y, boolean isOrDivision) {
        URNspec urn = startOrEnd.getPathGraph().getMap().getUcmspec().getUrnspec();
        EmptyPoint empty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);

        if (startOrEnd instanceof StartPoint) {
            PathNode newFork;
            if (isOrDivision)
                newFork = (PathNode) ModelCreationFactory.getNewObject(urn, OrFork.class);
            else
                newFork = (PathNode) ModelCreationFactory.getNewObject(urn, AndFork.class);

            add(new SplitLinkCommand(startOrEnd.getPathGraph(), empty, ncTarget, x, y));
            add(new ForkPathsCommand(empty, (StartPoint) startOrEnd, newFork));
        } else if (startOrEnd instanceof EndPoint) {
            PathNode newJoin;

            if (isOrDivision)
                newJoin = (PathNode) ModelCreationFactory.getNewObject(urn, OrJoin.class);
            else
                newJoin = (PathNode) ModelCreationFactory.getNewObject(urn, AndJoin.class);

            add(new SplitLinkCommand(startOrEnd.getPathGraph(), empty, ncTarget, x, y));
            add(new JoinPathsCommand(empty, (EndPoint) startOrEnd, newJoin));
        }
    }

}