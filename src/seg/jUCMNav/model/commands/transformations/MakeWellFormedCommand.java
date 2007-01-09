package seg.jUCMNav.model.commands.transformations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

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

/**
 * 
 * This command will make all maps in a URN well formed, according to QSIC'03. 
 * 
 * Intended to be run on the output of the MscTraversalListener.
 *  
 * @author jkealey
 * 
 */
public class MakeWellFormedCommand extends CompoundCommand {

	private Vector maps; 
	private CommandStack cs;
	
	public MakeWellFormedCommand(URNspec urn)
	{
		setLabel("Make a UCM well formed");
		maps = new Vector();
		for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
			UCMmap map = (UCMmap) iter.next();
			maps.add(map);
		}
	}
	
	
	public MakeWellFormedCommand(UCMmap map) 
	{
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
		Vector andjoins = new Vector();
		for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
			PathNode pn = (PathNode) iter.next();
			if (pn instanceof AndJoin)
				andjoins.add(pn);
		}
		
		
		HashSet completedForks = new HashSet();
		
		// For each and-join,
		for (Iterator iter = andjoins.iterator(); iter.hasNext();) {
			AndJoin andjoin = (AndJoin) iter.next();
			
			System.out.println("Testing " + andjoin.toString());
			// deleted.
			if (andjoin.getDiagram()==null)continue;
			
			Vector[] forks = new Vector[andjoin.getPred().size()];
			
			for (int i=0;i<andjoin.getPred().size();i++)  {
				NodeConnection nc = (NodeConnection) andjoin.getPred().get(i);
				
				HashSet exclusions = new HashSet();
				exclusions.addAll(andjoin.getPred());
				exclusions.remove(nc);
				
				forks[i]=new Vector();
				//1)	look at all incoming paths and go in inverse path direction back up to start point 
		        QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(andjoin, exclusions, QFindReachableNodes.DIRECTION_REVERSE);
		        ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qReachableNodes);
		        Vector vReachable = rReachableNodes.getNodes();
		        
		        // keep only forks and start point.
		        for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
					PathNode element = (PathNode) iterator.next();
					if (element instanceof StartPoint || element instanceof AndFork || element instanceof AndJoin)
						forks[i].add(element);
				}
				
			}
			
			PathNode commonFork = (PathNode) forks[0].lastElement();
			for (int i=1;i<andjoin.getPred().size();i++)
			{
				if (forks[i].firstElement() != commonFork)
					commonFork=null;
			}
			
			if (commonFork!=null) {
				System.out.println("same common predecessor ("+commonFork.toString()+"): is wellformed: " + andjoin.toString());
				// b.	If the fork is the same for all branches, graph is well-nested and continues. Marks fork as complete. 
				completedForks.add(commonFork);
			}
			else {
				Vector commonForks = new Vector(forks[0]);
				for (int i=1;i<andjoin.getPred().size();i++)
					commonForks.retainAll(forks[i]);
				
				commonForks.remove(andjoin);
				
//				if (commonForks.size()==0)
//				{ 
//					//d.	If no common fork found: 
//					//i.	If we assume that all start points are launched in parallel by a pseudo-fork, the algorithm doesn’t have to be changed. Two start points that are merged into an and-join are running concurrently. 
//					//ii.	However, this is not really what is happening in the traversal right now… the order is important. The order of launch of the start points is kept in the scenario start point list that was migrated.  TODO: should Algorithm A) generate something different because of this? Something like a start point, split by and fork, with end points connected to original start points. Only have one scenario start point. 
//
//					System.out.println("Independant threads");
//					
//				} else {
					
					PathNode mostRecentForkCommonToAllBranches = commonForks.size()==0?null:(PathNode) commonForks.lastElement();
//					Vector dirtyForks = new Vector(commonForks);
//					// remove completed ones
//					for (Iterator iterator = commonForks.iterator(); iterator.hasNext();) {
//						PathNode pn = (PathNode) iterator.next();
//						if (completedForks.contains(pn))
//							dirtyForks.remove(pn);
//					}
					
					
					Vector toBeFixed = new Vector();
					for (int i=0;i<andjoin.getPred().size();i++)
					{
						boolean bFound=mostRecentForkCommonToAllBranches==null;
						for (int j=forks[i].size()-1;j>=0;j--)
						{
							System.out.println(forks[i].get(j));
							if (!bFound) {// ignore stuff before the common fork. 
								if (forks[i].get(j) == mostRecentForkCommonToAllBranches)
									bFound=true;
							}
							else if (forks[i].get(j) instanceof AndFork) {
								toBeFixed.add(forks[i].get(j));
							} else if (forks[i].get(j) instanceof AndJoin) {
								System.out.println("TODO fix andjoin" + forks[i].get(j).toString());
							}
						}
					}
					
					System.out.println("We will be fixing " + toBeFixed.size() + " forks.");
					
					fixForks(map, andjoin, toBeFixed);
				}
			//}
			

			

	        
		}
		
	}


	private void fixForks(UCMmap map, AndJoin andjoin, Vector toBeFixed) {
		for (Iterator iterator = toBeFixed.iterator(); iterator.hasNext();) {
			
			AndFork andfork = (AndFork) iterator.next();
			
			System.out.println("Fixing : " + andfork.toString());
			Vector usedBranches = new Vector();
			Vector unusedBranches = new Vector();
			// ii.	For each fork, mark all branches that were taken that got to the join.
			for (int i=0;i<andfork.getSucc().size();i++)
			{
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
			
			System.out.println("Unused branches: " + unusedBranches.size());
			System.out.println("Used branches: " + usedBranches.size());
			//If a fork has more than one branch that is marked, we will generate a new fork/join for these sub-branches to ensure we keep as much information as possible. Add these joins to the list of and-joins to clean up (or recurse). We effectively break down a join with multiple branches into two smaller ones
			if (usedBranches.size()>1)
			{
				// TODO: generate new fork/join
			}
			else {
				
			}
			
			
			if (unusedBranches.size()==0) {
				System.out.println("Fork is okay");
				continue;
			}
			
			NodeConnection source = (NodeConnection)andfork.getPred().get(0);
			NodeConnection target = (NodeConnection)andjoin.getSucc().get(0);
			
			DeletePathNodeCommand cmd = new DeletePathNodeCommand(andfork, null);
			cs.execute(cmd);
			EndPoint end = (EndPoint) source.getTarget();
			StartPoint start = (StartPoint) ((NodeConnection)usedBranches.get(0)).getSource();
			
			MergeStartEndCommand mergecmd = new MergeStartEndCommand(map, start, end, start.getX(), start.getY());
			cs.execute(mergecmd);
			
			AndFork newAndFork = null;
			 
			if (usedBranches.size()>1) {
				start = (StartPoint) ((NodeConnection)usedBranches.get(1)).getSource();
				DividePathCommand cmd2 = new DividePathCommand(start, mergecmd.getNewEmptyPoint(), false);
				cs.execute(cmd2);
				newAndFork = (AndFork) cmd2.getNewNode();
				assert newAndFork!=null;
			}
			
			// might not have any
			for (int i=2;i<usedBranches.size();i++)
			{
				start = (StartPoint) ((NodeConnection)usedBranches.get(i)).getSource();
				AttachBranchCommand cmd2 = new AttachBranchCommand(start, newAndFork);
				cs.execute(cmd2);
			}
			
			start = (StartPoint) ((NodeConnection)unusedBranches.get(0)).getSource();
			DividePathCommand cmd2 = new DividePathCommand(start,target, start.getX(), start.getY(), false);
			cs.execute(cmd2);
			newAndFork = (AndFork) cmd2.getNewNode();
			assert newAndFork!=null;
		
			
			for (int i=1;i<unusedBranches.size();i++)
			{
				start = (StartPoint) ((NodeConnection)unusedBranches.get(i)).getSource();
				AttachBranchCommand cmd3 = new AttachBranchCommand(start, newAndFork);
				cs.execute(cmd3);
			}
			
			
			
			
			
			
			
			
									
		}
	}

}
