package seg.jUCMNav.model.commands.transformations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.csm.ExportCSM;
import seg.jUCMNav.importexport.csm.one2one.CsmExportWarning;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.DuplicateCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;

/**
 * 
 * This command will make all maps in a URN well formed, according to QSIC'03.
 * 
 * Intended to be run on the output of the MscTraversalListener.
 * 
 * I have a feeling that it might not work well on very complex ucms because we change the model between the time we look for intermediate flow points and the
 * time we actually try to fix them. (because we fixed the the graph for a previous flowpoint)
 * 
 * This command is not reversible!
 * 
 * @author jkealey
 * 
 */
public class MakeWellFormedCommand extends CompoundCommand {

    private Vector maps;
    private CommandStack cs;
    protected Vector warnings; // For the Problems view

    public static boolean MOVE_TRIGGER_AFTER_JOIN = false;

    /**
     * Will ensure that all maps in the URNspec are well-formed.
     * 
     * @param urn
     *            the URNSpec
     */
    public MakeWellFormedCommand(URNspec urn) {
        setLabel(Messages.getString("MakeWellFormedCommand.MakeUCMWellFormed")); //$NON-NLS-1$
        maps = new Vector();
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram map = (IURNDiagram) iter.next();
            if (map instanceof UCMmap)
                maps.add(map);
        }
    }

    /**
     * Will ensure that the passed map is well-formed
     * 
     * @param map
     *            the map
     */
    public MakeWellFormedCommand(UCMmap map) {
        setLabel(Messages.getString("MakeWellFormedCommand.MakeUCMWellFormed")); //$NON-NLS-1$
        maps = new Vector();
        maps.add(map);
    }

    /**
     * Can this command be executed?
     */
    public boolean canExecute() {
        return true;
    }

    /**
     * Run the command.
     */
    public void execute() {
        cs = new CommandStack();
        warnings = new Vector();
        for (Iterator iter = maps.iterator(); iter.hasNext();) {
            UCMmap map = (UCMmap) iter.next();
            modify(map);
        }
        ExportCSM.refreshProblemsView(warnings);
    }

    /**
     * This command can never be undone.
     */
    public boolean canUndo() {
        return false;
    }

    /**
     * Fix a single map
     * 
     * @param map
     *            the map
     */
    protected void modify(UCMmap map) {
        // find all and-joins
        Vector andjoins = SimplifyForksAndJoinsCommand.findAndJoins(map, false);
        HashSet completedFlowPoints = new HashSet();

        boolean modificationApplied = false;

        // For each and-join,
        for (Iterator iter = andjoins.iterator(); iter.hasNext();) {
            AndJoin andjoin = (AndJoin) iter.next();

            // deleted; not sure if this happens or not... not important: defense in depth.
            if (andjoin.getDiagram() == null)
                continue;

            // on all incoming branches, look at the preceding flow point (fork/join)
            Vector[] flowPoints = SimplifyForksAndJoinsCommand.findUpstreamFlowPoints(andjoin);

            // do they share the same one?
            PathNode commonFlowPoint = SimplifyForksAndJoinsCommand.findCommonSource(flowPoints);

            if (commonFlowPoint != null) {
                // b. If the fork is the same for all branches, graph is well-nested and continues. Marks fork as complete.
                completedFlowPoints.add(commonFlowPoint);
            } else {
                // find the most recent common fork
                PathNode mostRecentForkCommonToAllBranches = findMostRecentCommonSource(andjoin, flowPoints);
                // find all in between
                Vector toBeFixed = findUpstreamIntermediateFlowPoints(andjoin, flowPoints, mostRecentForkCommonToAllBranches);
                // fix the intermediate forks.
                modificationApplied = modificationApplied || fixUpstreamFlowPoints(map, andjoin, toBeFixed, completedFlowPoints);
            }
        }

        if (modificationApplied) {
            String msg = Messages.getString("MakeWellFormedCommand.ConcurrencyModified") + map.getName(); //$NON-NLS-1$
            msg = msg + Messages.getString("MakeWellFormedCommand.ToMakeItWellFormed"); //$NON-NLS-1$
            warnings.add(new CsmExportWarning(msg, IMarker.SEVERITY_WARNING));
        }

        // will restructure forks and joins so that they become well nested
        cs.execute(new SimplifyForksAndJoinsCommand(map));

        if (MOVE_TRIGGER_AFTER_JOIN) {

            // find all and-joins (refresh)
            andjoins = SimplifyForksAndJoinsCommand.findAndJoins(map, false);

            // moves the trigger_end after the
            for (Iterator iter = andjoins.iterator(); iter.hasNext();) {
                AndJoin andjoin = (AndJoin) iter.next();

                for (Iterator iterator = andjoin.getPred().iterator(); iterator.hasNext();) {
                    NodeConnection nc = (NodeConnection) iterator.next();
                    PathNode pn = (PathNode) nc.getSource();
                    if (pn instanceof DirectionArrow) {
                        if (MetadataHelper.getMetaData(pn, "type") != null && MetadataHelper.getMetaData(pn, "type").equalsIgnoreCase("TRIGGER_END")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

                            URNspec urn = pn.getDiagram().getUrndefinition().getUrnspec();
                            DirectionArrow da = (DirectionArrow) ModelCreationFactory.getNewObject(urn, DirectionArrow.class);
                            da.setName(pn.getName());
                            da.setDescription(pn.getDescription());
                            IURNContainerRef ref = pn.getContRef();
                            DuplicateCommand.copyMetadata(urn, da.getMetadata(), pn.getMetadata());

                            cs.execute(new SplitLinkCommand((UCMmap) andjoin.getDiagram(), da, (NodeConnection) andjoin.getSucc().get(0), andjoin.getX() + 10,
                                    andjoin.getY()));
                            cs.execute(new RemovePathNodeCommand(pn, null));

                            da.setContRef(ref);

                            break;

                        }
                    }
                }
            }
        }

    }

    /**
     * Find all intermediate and-forks between mostRecentForkCommonToAllBranches and andjoin.
     * 
     * @param andjoin
     *            the last element
     * @param flowPoints
     *            the set of all forks/joins in between
     * @param mostRecentForkCommonToAllBranches
     *            the first element
     * @return a Vector of and-forks
     */
    private Vector findUpstreamIntermediateFlowPoints(AndJoin andjoin, Vector[] flowPoints, PathNode mostRecentForkCommonToAllBranches) {
        Vector toBeFixed = new Vector();
        for (int i = 0; i < andjoin.getPred().size(); i++) {
            boolean bFound = mostRecentForkCommonToAllBranches == null;
            for (int j = flowPoints[i].size() - 1; j >= 0; j--) {
                // System.out.println(flowPoints[i].get(j));
                if (!bFound) {// ignore stuff before the common fork.
                    if (flowPoints[i].get(j) == mostRecentForkCommonToAllBranches)
                        bFound = true;
                } else if (flowPoints[i].get(j) instanceof AndFork) {
                    toBeFixed.add(flowPoints[i].get(j));
                } else if (flowPoints[i].get(j) instanceof AndJoin) {
                    // System.out.println("TODO fix andjoin" + flowPoints[i].get(j).toString());
                }
            }
        }
        return toBeFixed;
    }

    /**
     * Identifies the and-fork closest to the specified and-join for which it is possible to reach all of the and-join's incoming branches.
     * 
     * @param andjoin
     *            the andjoin
     * @param flowPoints
     *            all forks/joins that can be found by going upstream on all andjoin's incoming branches
     * @return the closest and-fork PathNode
     */
    private PathNode findMostRecentCommonSource(AndJoin andjoin, Vector[] flowPoints) {
        Vector commonForks = new Vector(flowPoints[0]);
        for (int i = 1; i < andjoin.getPred().size(); i++)
            commonForks.retainAll(flowPoints[i]);

        PathNode mostRecentForkCommonToAllBranches = commonForks.size() == 0 ? null : (PathNode) commonForks.lastElement();
        return mostRecentForkCommonToAllBranches;
    }

    /**
     * Fixes intermediate flow points.
     * 
     * @param map
     *            the containing map
     * @param andjoin
     *            the and join
     * @param toBeFixed
     *            the list of intermediate and-forks
     * @param completedFlowPoints
     *            the list of elements we have already processed.
     * @return true is modifications were done to make the map well-formed.
     */
    private boolean fixUpstreamFlowPoints(UCMmap map, AndJoin andjoin, Vector toBeFixed, HashSet completedFlowPoints) {
        boolean modificationApplied = false;

        for (Iterator iterator = toBeFixed.iterator(); iterator.hasNext();) {

            AndFork andfork = (AndFork) iterator.next();

            // System.out.println("Fixing : " + andfork.toString());
            Vector usedBranches = new Vector();
            Vector unusedBranches = new Vector();
            // which branches lead to and-join
            classifyBranches(andjoin, andfork, usedBranches, unusedBranches);

            // System.out.println("Unused branches: " + unusedBranches.size());
            // System.out.println("Used branches: " + usedBranches.size());

            // all of them
            if (unusedBranches.size() == 0) {
                // System.out.println("Fork is okay");
                continue;
            }

            modificationApplied = true;
            NodeConnection source = (NodeConnection) andfork.getPred().get(0);
            NodeConnection target = (NodeConnection) andjoin.getSucc().get(0);

            // delete the intermediate and-fork and attach the used branches before it
            IURNContainerRef compRef = moveUsedBranchesBeforeFork(map, andfork, source, usedBranches, completedFlowPoints);

            // attach the non-used branches after the and-join
            moveUnusedBranchesAfterJoin(map, compRef, target, unusedBranches, completedFlowPoints);
        }

        return modificationApplied;
    }

    /**
     * Delete the intermediate and-fork and attach the used branches before it
     * 
     * @param map
     *            the map
     * @param andfork
     *            the intermediate and-fork
     * @param source
     *            where should the branches be moved
     * @param usedBranches
     *            the branches that should be moved before it
     * @param completedFlowPoints
     *            the set of processed elements
     * @return the fork's container
     */
    private IURNContainerRef moveUsedBranchesBeforeFork(UCMmap map, AndFork andfork, NodeConnection source, Vector usedBranches, HashSet completedFlowPoints) {
        IURNContainerRef compRef = andfork.getContRef();

        // delete intermediate fork
        RemovePathNodeCommand cmd = new RemovePathNodeCommand(andfork, null);
        cs.execute(cmd);

        // merge first usedBranch with old fork input
        EndPoint end = (EndPoint) source.getTarget();
        StartPoint start = (StartPoint) ((NodeConnection) usedBranches.get(0)).getSource();

        MergeStartEndCommand mergecmd = new MergeStartEndCommand(map, start, end, start.getX(), start.getY());
        cs.execute(mergecmd);

        AndFork newAndFork = null;

        // add 2nd branch, creating new fork.
        if (usedBranches.size() > 1) {
            start = (StartPoint) ((NodeConnection) usedBranches.get(1)).getSource();
            DividePathCommand cmd2 = new DividePathCommand(start, mergecmd.getNewEmptyPoint(), false);
            cs.execute(cmd2);
            newAndFork = (AndFork) cmd2.getNewNode();

            assert newAndFork != null;
            newAndFork.setContRef(compRef);

        }

        // attach rest of branches; might not have any
        for (int i = 2; i < usedBranches.size(); i++) {
            start = (StartPoint) ((NodeConnection) usedBranches.get(i)).getSource();
            AttachBranchCommand cmd2 = new AttachBranchCommand(start, newAndFork);
            cs.execute(cmd2);
        }

        completedFlowPoints.add(newAndFork);
        return compRef;
    }

    /**
     * Attach the non-used branches in parallel after the and-join
     * 
     * @param map
     *            the map
     * @param compRef
     *            the new forks's container
     * @param target
     *            where should the branches be moved
     * @param unusedBranches
     *            which branches should be moved
     * @param completedFlowPoints
     *            the set of processed elements
     */
    private void moveUnusedBranchesAfterJoin(UCMmap map, IURNContainerRef compRef, NodeConnection target, Vector unusedBranches, HashSet completedFlowPoints) {

        // attach first branch
        StartPoint start = (StartPoint) ((NodeConnection) unusedBranches.get(0)).getSource();
        DividePathCommand cmd2 = new DividePathCommand(start, target, start.getX(), start.getY(), false);
        cs.execute(cmd2);
        AndFork newAndFork = (AndFork) cmd2.getNewNode();
        assert newAndFork != null;
        newAndFork.setContRef(compRef);

        // attach all other branches
        for (int i = 1; i < unusedBranches.size(); i++) {
            start = (StartPoint) ((NodeConnection) unusedBranches.get(i)).getSource();
            AttachBranchCommand cmd3 = new AttachBranchCommand(start, newAndFork);
            cs.execute(cmd3);
        }

        completedFlowPoints.add(newAndFork);
    }

    /**
     * Mark all branches that were taken that got to the join.
     * 
     * @param andjoin
     *            the target node
     * @param andfork
     *            the source node
     * @param usedBranches
     *            insert the used branches here
     * @param unusedBranches
     *            insert the unused branches here.
     */
    private void classifyBranches(AndJoin andjoin, AndFork andfork, Vector usedBranches, Vector unusedBranches) {
        // ii. For each fork, mark all branches that were taken that got to the join.
        for (int i = 0; i < andfork.getSucc().size(); i++) {
            NodeConnection nc = (NodeConnection) andfork.getSucc().get(i);
            HashSet exclusions = new HashSet();
            exclusions.addAll(andfork.getSucc());
            exclusions.remove(nc);
            exclusions.addAll(andjoin.getSucc()); // stop it from going further.

            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(andfork, exclusions, QFindReachableNodes.DIRECTION_FORWARD);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();
            if (vReachable.contains(andjoin))
                usedBranches.add(nc);
            else
                unusedBranches.add(nc);
        }
    }

}
