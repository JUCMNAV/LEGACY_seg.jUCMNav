package seg.jUCMNav.model.commands.transformations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeConnectionCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
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
import urn.URNspec;
import urncore.IURNContainerRef;

/**
 * 
 * This command will make all maps in a URN well formed, according to QSIC'03.
 * 
 * Intended to be run on the output of the MscTraversalListener.
 * 
 * I have a feeling that it might not work well on very complex ucms because we change the model between the time we look for intermediate flow points and the
 * time we actually try to fix them. (because we fixed the the graph for a previous flowpoint)
 * 
 * @author jkealey
 * 
 */
public class MakeWellFormedCommand extends CompoundCommand {

	private Vector maps;
	private CommandStack cs;

	public MakeWellFormedCommand(URNspec urn) {
		setLabel("Make a UCM well formed");
		maps = new Vector();
		for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
			UCMmap map = (UCMmap) iter.next();
			maps.add(map);
		}
	}

	public MakeWellFormedCommand(UCMmap map) {
		setLabel("Make a UCM well formed");
		maps = new Vector();
		maps.add(map);
	}

	public boolean canExecute() {

		return true;
	}

	public void execute() {
		cs = new CommandStack();
		for (Iterator iter = maps.iterator(); iter.hasNext();) {
			UCMmap map = (UCMmap) iter.next();
			modify(map);
		}
	}

	public boolean canUndo() {

		return false;
	}

	protected void modify(UCMmap map) {
		Vector andjoins = findAndJoins(map, false);
		HashSet completedFlowPoints = new HashSet();

		// For each and-join,
		for (Iterator iter = andjoins.iterator(); iter.hasNext();) {
			AndJoin andjoin = (AndJoin) iter.next();

			// deleted; not sure if this happens or not... not important: defense in depth.  
			if (andjoin.getDiagram() == null)
				continue;

			Vector[] flowPoints = findUpstreamFlowPoints(andjoin);

			PathNode commonFlowPoint = findCommonSource(andjoin, flowPoints);

			if (commonFlowPoint != null) {
				// b. If the fork is the same for all branches, graph is well-nested and continues. Marks fork as complete.
				completedFlowPoints.add(commonFlowPoint);
			} else {
				PathNode mostRecentForkCommonToAllBranches = findMostRecentCommonSource(andjoin, flowPoints);
				Vector toBeFixed = findUpstreamIntermediateFlowPoints(andjoin, flowPoints, mostRecentForkCommonToAllBranches);
				fixUpstreamFlowPoints(map, andjoin, toBeFixed, completedFlowPoints);
			}
		}
		
		
		simplifyComplexAndJoins(map);
		simplifyComplexAndForks(map);
	}

	private void simplifyComplexAndJoins(UCMmap map) {
		Vector complexAndJoins = findAndJoins(map, true);
		for (Iterator iter = complexAndJoins.iterator(); iter.hasNext();) {
			AndJoin andjoin = (AndJoin) iter.next();

			Vector[] flowPoints = findUpstreamFlowPoints(andjoin);

			// if they all share the same source, don't need to do anything
			if (findCommonSource(andjoin, flowPoints) != null)
				continue;
			
			HashMap hmForkToNodeConnection = new HashMap();
			
			for (int i=0;i<flowPoints.length;i++)
			{
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
			assert hmForkToNodeConnection.values().size()>=2;
			for (Iterator iterator = hmForkToNodeConnection.values().iterator(); iterator.hasNext();) {
				Vector v = (Vector) iterator.next();
				
				// if only one, skip. otherwise merge others into one
				if (v.size()>1) {
		
					mergeInPathsIntoJoin(andjoin, v);
				}
				
			}
			
		}
	}

	private void mergeInPathsIntoJoin(AndJoin andjoin, Vector v) {
		NodeConnection nc = (NodeConnection)v.get(1);
		// will detach it
		DeleteNodeConnectionCommand cmd = new DeleteNodeConnectionCommand(nc, null);
		cs.execute(cmd);
		DividePathCommand cmd2 = new DividePathCommand((EndPoint)nc.getTarget(), (NodeConnection)v.get(0), andjoin.getX()-10, andjoin.getY()-10, false);
		cs.execute(cmd2);
		AndJoin newAndJoin = (AndJoin) cmd2.getNewNode();
		
		for (int i=2;i<v.size();i++) {
			nc = (NodeConnection)v.get(i);
			// will detach it
			cmd = new DeleteNodeConnectionCommand(nc, null);
			cs.execute(cmd);
			
			AttachBranchCommand cmd3 = new AttachBranchCommand((EndPoint)nc.getTarget(), newAndJoin);
			cs.execute(cmd3);
			
		}
	}
	private void simplifyComplexAndForks(UCMmap map) {
		Vector complexAndForks = findAndForks(map, true);
		for (Iterator iter = complexAndForks.iterator(); iter.hasNext();) {
			AndFork andfork = (AndFork) iter.next();

			Vector[] flowPoints = findDownstreamFlowPoints(andfork);

			// if they all share the same source, don't need to do anything
			if (findCommonSource(andfork, flowPoints) != null)
				continue;
			
			HashMap hmForkToNodeConnection = new HashMap();
			
			for (int i=0;i<flowPoints.length;i++)
			{
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
			assert hmForkToNodeConnection.values().size()>=2;
			for (Iterator iterator = hmForkToNodeConnection.values().iterator(); iterator.hasNext();) {
				Vector v = (Vector) iterator.next();
				
				// if only one, skip. otherwise merge others into one
				if (v.size()>1) {
		
					mergeInPathsIntoFork(andfork, v);
				}
				
			}
			
		}
	}

	private void mergeInPathsIntoFork(AndFork andfork, Vector v) {
		NodeConnection nc = (NodeConnection)v.get(1);
		// will detach it
		DeleteNodeConnectionCommand cmd = new DeleteNodeConnectionCommand(nc, null);
		cs.execute(cmd);
		DividePathCommand cmd2 = new DividePathCommand((StartPoint)nc.getSource(), (NodeConnection)v.get(0), andfork.getX()+10, andfork.getY()+10, false);
		cs.execute(cmd2);
		AndFork newAndFork = (AndFork) cmd2.getNewNode();
		
		for (int i=2;i<v.size();i++) {
			nc = (NodeConnection)v.get(i);
			// will detach it
			cmd = new DeleteNodeConnectionCommand(nc, null);
			cs.execute(cmd);
			
			AttachBranchCommand cmd3 = new AttachBranchCommand((StartPoint)nc.getSource(), newAndFork);
			cs.execute(cmd3);
			
		}
	}

	
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
	
	

	private PathNode findMostRecentCommonSource(AndJoin andjoin, Vector[] flowPoints) {
		Vector commonForks = new Vector(flowPoints[0]);
		for (int i = 1; i < andjoin.getPred().size(); i++)
			commonForks.retainAll(flowPoints[i]);

		PathNode mostRecentForkCommonToAllBranches = commonForks.size() == 0 ? null : (PathNode) commonForks.lastElement();
		return mostRecentForkCommonToAllBranches;
	}

	private PathNode findCommonSource(AndJoin andjoin, Vector[] flowPoints) {
		PathNode commonFork = (PathNode) flowPoints[0].firstElement();
		for (int i = 1; i < andjoin.getPred().size(); i++) {
			if (flowPoints[i].firstElement() != commonFork)
				commonFork = null;
		}
		return commonFork;
	}
	
	private PathNode findCommonSource(AndFork andfork, Vector[] flowPoints) {
		PathNode commonJoin = (PathNode) flowPoints[0].firstElement();
		for (int i = 1; i < andfork.getSucc().size(); i++) {
			if (flowPoints[i].firstElement() != commonJoin)
				commonJoin = null;
		}
		return commonJoin;
	}

	
	private Vector[] findUpstreamFlowPoints(AndJoin andjoin) {
		Vector[] flowPoints = new Vector[andjoin.getPred().size()];

		for (int i = 0; i < andjoin.getPred().size(); i++) {
			NodeConnection nc = (NodeConnection) andjoin.getPred().get(i);

			HashSet exclusions = new HashSet();
			exclusions.addAll(andjoin.getPred());
			exclusions.remove(nc);

			flowPoints[i] = new Vector();
			// 1) look at all incoming paths and go in inverse path direction back up to start point
			QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(andjoin, exclusions, QFindReachableNodes.DIRECTION_REVERSE);
			ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qReachableNodes);
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

	private Vector[] findDownstreamFlowPoints(AndFork andfork) {
		Vector[] flowPoints = new Vector[andfork.getSucc().size()];

		for (int i = 0; i < andfork.getSucc().size(); i++) {
			NodeConnection nc = (NodeConnection) andfork.getSucc().get(i);

			HashSet exclusions = new HashSet();
			exclusions.addAll(andfork.getSucc());
			exclusions.remove(nc);

			flowPoints[i] = new Vector();
			// 1) look at all incoming paths and go in forward path direction down to end piont
			QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(andfork, exclusions, QFindReachableNodes.DIRECTION_FORWARD);
			ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qReachableNodes);
			Vector vReachable = rReachableNodes.getNodes();

			// keep only forks and start point.
			for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
				PathNode element = (PathNode) iterator.next();
				if (element instanceof StartPoint || element instanceof AndFork || element instanceof AndJoin) {
					if (element != andfork)
						flowPoints[i].add(element);
				}
			}

		}
		return flowPoints;
	}
	
	private Vector findAndJoins(UCMmap map, boolean keepOnlyMoreThanTwoIn) {
		Vector andjoins = new Vector();
		for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
			PathNode pn = (PathNode) iter.next();
			if (pn instanceof AndJoin && (!keepOnlyMoreThanTwoIn || pn.getPred().size()>2))
				andjoins.add(pn);
		}
		return andjoins;
	}
	private Vector findAndForks(UCMmap map, boolean keepOnlyMoreThanTwoOut) {
		Vector andforks = new Vector();
		for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
			PathNode pn = (PathNode) iter.next();
			if (pn instanceof AndFork && (!keepOnlyMoreThanTwoOut || pn.getSucc().size()>2))
				andforks.add(pn);
		}
		return andforks;
	}

	
	private void fixUpstreamFlowPoints(UCMmap map, AndJoin andjoin, Vector toBeFixed, HashSet completedFlowPoints) {
		for (Iterator iterator = toBeFixed.iterator(); iterator.hasNext();) {

			AndFork andfork = (AndFork) iterator.next();

			// System.out.println("Fixing : " + andfork.toString());
			Vector usedBranches = new Vector();
			Vector unusedBranches = new Vector();
			classifyBranches(andjoin, andfork, usedBranches, unusedBranches);

			// System.out.println("Unused branches: " + unusedBranches.size());
			// System.out.println("Used branches: " + usedBranches.size());

			if (unusedBranches.size() == 0) {
				// System.out.println("Fork is okay");
				continue;
			}

			NodeConnection source = (NodeConnection) andfork.getPred().get(0);
			NodeConnection target = (NodeConnection) andjoin.getSucc().get(0);

			IURNContainerRef  compRef = moveUsedBranchesBeforeFork(map, andfork, source, usedBranches, completedFlowPoints);

			moveUnusedBranchesAfterJoin(map, compRef, target, unusedBranches, completedFlowPoints);

		}
	}

	private IURNContainerRef moveUsedBranchesBeforeFork(UCMmap map, AndFork andfork, NodeConnection source, Vector usedBranches, HashSet completedFlowPoints) {
		IURNContainerRef compRef = andfork.getContRef();
		DeletePathNodeCommand cmd = new DeletePathNodeCommand(andfork, null);
		cs.execute(cmd);
		EndPoint end = (EndPoint) source.getTarget();
		StartPoint start = (StartPoint) ((NodeConnection) usedBranches.get(0)).getSource();

		MergeStartEndCommand mergecmd = new MergeStartEndCommand(map, start, end, start.getX(), start.getY());
		cs.execute(mergecmd);

		AndFork newAndFork = null;

		if (usedBranches.size() > 1) {
			start = (StartPoint) ((NodeConnection) usedBranches.get(1)).getSource();
			DividePathCommand cmd2 = new DividePathCommand(start, mergecmd.getNewEmptyPoint(), false);
			cs.execute(cmd2);
			newAndFork = (AndFork) cmd2.getNewNode();
			
			assert newAndFork != null;
			newAndFork.setContRef(compRef);
			
		}

		// might not have any
		for (int i = 2; i < usedBranches.size(); i++) {
			start = (StartPoint) ((NodeConnection) usedBranches.get(i)).getSource();
			AttachBranchCommand cmd2 = new AttachBranchCommand(start, newAndFork);
			cs.execute(cmd2);
		}

		completedFlowPoints.add(newAndFork);
		return compRef;
	}

	private void moveUnusedBranchesAfterJoin(UCMmap map, IURNContainerRef compRef, NodeConnection target, Vector unusedBranches, HashSet completedFlowPoints) {
		StartPoint start = (StartPoint) ((NodeConnection) unusedBranches.get(0)).getSource();
		DividePathCommand cmd2 = new DividePathCommand(start, target, start.getX(), start.getY(), false);
		cs.execute(cmd2);
		AndFork newAndFork = (AndFork) cmd2.getNewNode();
		assert newAndFork != null;
		newAndFork.setContRef(compRef);
		
		for (int i = 1; i < unusedBranches.size(); i++) {
			start = (StartPoint) ((NodeConnection) unusedBranches.get(i)).getSource();
			AttachBranchCommand cmd3 = new AttachBranchCommand(start, newAndFork);
			cs.execute(cmd3);
		}

		completedFlowPoints.add(newAndFork);
	}

	private void classifyBranches(AndJoin andjoin, AndFork andfork, Vector usedBranches, Vector unusedBranches) {
		// ii. For each fork, mark all branches that were taken that got to the join.
		for (int i = 0; i < andfork.getSucc().size(); i++) {
			NodeConnection nc = (NodeConnection) andfork.getSucc().get(i);
			HashSet exclusions = new HashSet();
			exclusions.addAll(andfork.getSucc());
			exclusions.remove(nc);
			exclusions.addAll(andjoin.getSucc()); // stop it from going further.

			QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(andfork, exclusions, QFindReachableNodes.DIRECTION_FORWARD);
			ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qReachableNodes);
			Vector vReachable = rReachableNodes.getNodes();
			if (vReachable.contains(andjoin))
				usedBranches.add(nc);
			else
				unusedBranches.add(nc);
		}
	}

}
