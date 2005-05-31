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
 * Created on 31-May-2005
 * 
 * @author jkealey
 *  
 */
public class DividePathOnNodeConnectionCompoundCommand extends CompoundCommand {

    /**
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