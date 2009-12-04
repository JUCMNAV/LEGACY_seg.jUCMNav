package seg.jUCMNav.model.commands.transformations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeConnectionCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.commands.transformations.internal.SimplifyForkJoinCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * This compound command restructures and forks/joins so that they are closer to a linearizable form.
 * 
 * If you have a join with three in branches, two of which come from the same fork, the command will group these two into a new join that flows into the
 * existing one.
 * 
 * This command can easily be extended to support or-fork/or-joins.
 * 
 * @author jkealey
 * 
 */
public class SimplifyForksAndJoinsCommand extends CompoundCommand {

    /**
     * This compound command restructures and forks/joins so that they are closer to a linearizable form. If you have a join with three in branches, two of
     * which come from the same fork, the command will group these two into a new join that flows into the existing one.
     * 
     * @param map
     *            the map to clean
     */
    public SimplifyForksAndJoinsCommand(UCMmap map) {
        simplifyComplexAndForks(map);
        simplifyComplexAndJoins(map);

    }

    /**
     * Will return true even if no commands are to be run, so it doesn't block a command stack.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Will return true even if no commands are to be run, so it doesn't block a command stack.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Find all and forks in the map.
     * 
     * @param map
     *            the map
     * @param keepOnlyMoreThanTwoOut
     *            should we filter out all and forks that have only two outputs?
     * @return a vector of AndForks
     */
    protected static Vector findAndForks(UCMmap map, boolean keepOnlyMoreThanTwoOut) {
        Vector andforks = new Vector();
        for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof AndFork && (!keepOnlyMoreThanTwoOut || pn.getSucc().size() > 2))
                andforks.add(pn);
        }
        return andforks;
    }

    /**
     * Find all and joins in the map.
     * 
     * @param map
     *            the map
     * @param keepOnlyMoreThanTwoIn
     *            should we filter out all and joins that have only two inputs?
     * @return a vector of AndJoins
     */
    protected static Vector findAndJoins(UCMmap map, boolean keepOnlyMoreThanTwoIn) {
        Vector andjoins = new Vector();
        for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof AndJoin && (!keepOnlyMoreThanTwoIn || pn.getPred().size() > 2))
                andjoins.add(pn);
        }
        return andjoins;
    }

    /**
     * 
     * Find the common flow point
     * 
     * @param flowPoints
     *            an array of vectors, where each vector contains at least one PathNode
     * @return Returns the pathnode that is the first element of each flowPoint vector, or null if they are not the same.
     */
    protected static PathNode findCommonSource(Vector[] flowPoints) {
        PathNode commonJoin = (PathNode) flowPoints[0].firstElement();
        for (int i = 1; i < flowPoints.length; i++) {
            if (flowPoints[i].firstElement() != commonJoin)
                commonJoin = null;
        }
        return commonJoin;
    }

    /**
     * Starting at an and fork, goes down all branches to find all andforks, andjoins and end points along the way.
     * 
     * @param andfork
     *            the starting point.
     * 
     * @return each of the fork's node connections is associated to a vector in the array
     */
    protected static Vector[] findDownstreamFlowPoints(AndFork andfork) {
        Vector[] flowPoints = new Vector[andfork.getSucc().size()];

        for (int i = 0; i < andfork.getSucc().size(); i++) {
            NodeConnection nc = (NodeConnection) andfork.getSucc().get(i);

            HashSet exclusions = new HashSet();
            exclusions.addAll(andfork.getSucc());
            exclusions.remove(nc);

            flowPoints[i] = new Vector();
            // 1) look at all incoming paths and go in forward path direction down to end piont
            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(andfork, exclusions, QFindReachableNodes.DIRECTION_FORWARD);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();

            // keep only forks and start point.
            for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
                PathNode element = (PathNode) iterator.next();
                if (element instanceof EndPoint || element instanceof AndFork || element instanceof AndJoin) {
                    if (element != andfork)
                        flowPoints[i].add(element);
                }
            }

        }
        return flowPoints;
    }

    /**
     * Starting at an and join, goes back up all incoming to find all andforks, andjoins and start points along the way.
     * 
     * @param andjoin
     *            the starting point.
     * 
     * @return each of the join's incoming node connections is associated to a vector in the array
     */
    protected static Vector[] findUpstreamFlowPoints(AndJoin andjoin) {
        Vector[] flowPoints = new Vector[andjoin.getPred().size()];

        for (int i = 0; i < andjoin.getPred().size(); i++) {
            NodeConnection nc = (NodeConnection) andjoin.getPred().get(i);

            HashSet exclusions = new HashSet();
            exclusions.addAll(andjoin.getPred());
            exclusions.remove(nc);

            flowPoints[i] = new Vector();
            // 1) look at all incoming paths and go in inverse path direction back up to start point
            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(andjoin, exclusions, QFindReachableNodes.DIRECTION_REVERSE);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();

            // keep only forks and start point.
            for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
                PathNode element = (PathNode) iterator.next();
                if (element instanceof StartPoint || element instanceof AndFork || element instanceof AndJoin) {
                    if (element != andjoin)
                        flowPoints[i].add(element);
                }
            }

        }
        return flowPoints;
    }

    /**
     * Given a vector of NodeConnections associated to an AndFork, will move the branches onto a new AndFork which will be inserted on the first NodeConnection
     * given in the vector.
     * 
     * @param andfork
     *            the original andfork
     * @param v
     *            the vector of nodeconnections to move off.
     */
    protected void mergeInPathsIntoFork(AndFork andfork, Vector v) {
        NodeConnection nc = (NodeConnection) v.get(1);
        // will detach it
        DeleteNodeConnectionCommand cmd = new DeleteNodeConnectionCommand(nc, null);
        add(cmd);
        DividePathCommand cmd2 = new DividePathCommand((StartPoint) nc.getSource(), (NodeConnection) v.get(0), andfork.getX() + 10, andfork.getY() + 10, false);
        add(cmd2);
        AndFork newAndFork = (AndFork) cmd2.getNewNode();

        for (int i = 2; i < v.size(); i++) {
            nc = (NodeConnection) v.get(i);
            // will detach it
            cmd = new DeleteNodeConnectionCommand(nc, null);
            add(cmd);

            AttachBranchCommand cmd3 = new AttachBranchCommand((StartPoint) nc.getSource(), newAndFork);
            add(cmd3);

        }
    }

    /**
     * Given a vector of NodeConnections associated to an AndJoin, will move the branches onto a new AndJoin which will be inserted on the first NodeConnection
     * given in the vector.
     * 
     * @param andjoin
     *            the original andjoin
     * @param v
     *            the vector of nodeconnections to move off.
     */
    protected void mergeInPathsIntoJoin(AndJoin andjoin, Vector v) {
        NodeConnection nc = (NodeConnection) v.get(1);
        // will detach it
        DeleteNodeConnectionCommand cmd = new DeleteNodeConnectionCommand(nc, null);
        add(cmd);
        DividePathCommand cmd2 = new DividePathCommand((EndPoint) nc.getTarget(), (NodeConnection) v.get(0), andjoin.getX() - 10, andjoin.getY() - 10, false);
        add(cmd2);
        AndJoin newAndJoin = (AndJoin) cmd2.getNewNode();

        for (int i = 2; i < v.size(); i++) {
            nc = (NodeConnection) v.get(i);
            // will detach it
            cmd = new DeleteNodeConnectionCommand(nc, null);
            add(cmd);

            AttachBranchCommand cmd3 = new AttachBranchCommand((EndPoint) nc.getTarget(), newAndJoin);
            add(cmd3);

        }
    }

    /**
     * Simplifies all AndForks in a map by making sure the and fork/joins are well grouped.
     * 
     * @param map
     *            the map containing and forks.
     */
    protected void simplifyComplexAndForks(UCMmap map) {
        Vector complexAndForks = findAndForks(map, true);
        for (Iterator iter = complexAndForks.iterator(); iter.hasNext();) {
            AndFork andfork = (AndFork) iter.next();

            // deleted
            if (andfork.getDiagram() == null)
                continue;

            Vector[] flowPoints = findDownstreamFlowPoints(andfork);

            // if they all share the same source, don't need to do anything
            if (findCommonSource(flowPoints) != null)
                continue;

            HashMap hmForkToNodeConnection = new HashMap();

            for (int i = 0; i < flowPoints.length; i++) {
                Vector v = null;
                if (!hmForkToNodeConnection.containsKey(flowPoints[i].firstElement())) {
                    v = new Vector();
                } else {
                    v = (Vector) hmForkToNodeConnection.get(flowPoints[i].firstElement());
                }
                v.add(andfork.getSucc().get(i));
                hmForkToNodeConnection.put(flowPoints[i].firstElement(), v);
            }

            // we know there is more than one
            assert hmForkToNodeConnection.values().size() >= 2;
            // for (Iterator iterator = hmForkToNodeConnection.values().iterator(); iterator.hasNext();) {
            // Vector v = (Vector) iterator.next();
            //
            // // if only one, skip. otherwise merge others into one
            // if (v.size() > 1) {
            //
            // mergeInPathsIntoFork(andfork, v);
            // }
            //
            // }

            this.add(new RemovePathNodeCommand(andfork, null));
            add(new SimplifyForkJoinCommand(andfork, hmForkToNodeConnection));

        }
    }

    /**
     * Simplifies all AndJoinsin a map by making sure the and fork/joins are well grouped.
     * 
     * @param map
     */
    protected void simplifyComplexAndJoins(UCMmap map) {
        Vector complexAndJoins = findAndJoins(map, true);
        for (Iterator iter = complexAndJoins.iterator(); iter.hasNext();) {
            AndJoin andjoin = (AndJoin) iter.next();

            // deleted
            if (andjoin.getDiagram() == null)
                continue;
            Vector[] flowPoints = findUpstreamFlowPoints(andjoin);

            // if they all share the same source, don't need to do anything
            if (findCommonSource(flowPoints) != null)
                continue;

            HashMap hmForkToNodeConnection = new HashMap();

            for (int i = 0; i < flowPoints.length; i++) {
                Vector v = null;
                if (!hmForkToNodeConnection.containsKey(flowPoints[i].firstElement())) {
                    v = new Vector();
                } else {
                    v = (Vector) hmForkToNodeConnection.get(flowPoints[i].firstElement());
                }
                v.add(andjoin.getPred().get(i));
                hmForkToNodeConnection.put(flowPoints[i].firstElement(), v);
            }

            // we know there is more than one
            assert hmForkToNodeConnection.values().size() >= 2;
            // for (Iterator iterator = hmForkToNodeConnection.values().iterator(); iterator.hasNext();) {
            // Vector v = (Vector) iterator.next();
            //
            // // if only one, skip. otherwise merge others into one
            // if (v.size() > 1) {
            //
            // mergeInPathsIntoJoin(andjoin, v);
            // }
            //
            // }
            this.add(new RemovePathNodeCommand(andjoin, null));
            add(new SimplifyForkJoinCommand(andjoin, hmForkToNodeConnection));

        }
    }
}