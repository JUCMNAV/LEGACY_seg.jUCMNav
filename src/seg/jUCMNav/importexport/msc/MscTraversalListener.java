package seg.jUCMNav.importexport.msc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.util.ArrayAndListUtils;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.scenario.ScenarioDef;
import urn.URNspec;

/**
 * A traversal listener that will generate MSCs.
 * 
 * @author jkealey
 * 
 */
public class MscTraversalListener implements ITraversalListener {

	protected CommandStack cs;
	protected UCMmap currentMap;
	protected ScenarioDef currentScenario;
	protected HashMap htScenarioToMap;
	protected HashMap htThreadEnd;
	protected HashMap htThreadStart;
	protected URNspec urnspec;

	public MscTraversalListener() {

		htScenarioToMap = new HashMap();
		urnspec = (URNspec) ModelCreationFactory.getNewURNspec();
		cs = new CommandStack();

	}

	public void codeExecuted(String code, UcmEnvironment env) {
		System.out.println("Executed code: " + code);

	}

	public void conditionEvaluated(String condition, boolean result) {
		System.out.println("Condition (" + condition + ") evaluated to " + result);

	}

	protected PathNode extendPath(int threadID, boolean fromEnd) {
		PathNode pn;

		if (fromEnd) {
			EndPoint end = (EndPoint) htThreadEnd.get(new Integer(threadID));
			ExtendPathCommand cmd = new ExtendPathCommand(currentMap, end, end.getX() + 100, end.getY());
			cs.execute(cmd);
			return end;
		} else {
			StartPoint start= (StartPoint) htThreadStart.get(new Integer(threadID));
			ExtendPathCommand cmd = new ExtendPathCommand(currentMap, start, start.getX() - 100, start.getY());
			cs.execute(cmd);
			return start;
		}
	}

	protected PathNode extendPath(TraversalVisit visit, boolean fromEnd) {
		return extendPath(visit.getThreadID(), fromEnd);
	}

	protected PathNode extendPathAndInsert(int threadID, PathNode pn, boolean fromEnd) {
		PathNode extremity = extendPath(threadID, fromEnd);
		EmptyPoint empty;
		if (fromEnd)
			empty = (EmptyPoint) ((NodeConnection) extremity.getPred().get(0)).getSource();
		else
			empty = (EmptyPoint) ((NodeConnection) extremity.getSucc().get(0)).getTarget();

		ReplaceEmptyPointCommand cmd2 = new ReplaceEmptyPointCommand(empty, pn);
		cs.execute(cmd2);
		return extremity;
	}

	protected PathNode extendPathAndInsert(TraversalVisit visit, PathNode pn, boolean fromEnd) {
		return extendPathAndInsert(visit.getThreadID(), pn, fromEnd);
	}

	public void newThreadStarted(TraversalVisit visit) {
		System.out.println("Started thread " + visit.getThreadID() + " at: " + visit.getVisitedElement());

		CreatePathCommand cmd = new CreatePathCommand(currentMap, 0, visit.getThreadID() * 100);
		cs.execute(cmd);

		htThreadStart.put(new Integer(visit.getThreadID()), cmd.getStart());
		htThreadEnd.put(new Integer(visit.getThreadID()), cmd.getEnd());
	}

	public void pathNodeAborted(TraversalVisit visit) {
		System.out.println("Aborted node in thread " + visit.getThreadID() + ": " + visit.getVisitedElement());

	}

	public void pathNodeAttempted(TraversalVisit visit) {
		System.out.println("Node is being attempted in thread " + visit.getThreadID() + ": " + visit.getVisitedElement());

	}

	public void pathNodeBlocked(TraversalVisit visit) {
		System.out.println("Node could not continue and was blocked in thread " + visit.getThreadID() + ": " + visit.getVisitedElement());

	}

	public void pathNodeVisited(TraversalVisit visit) {
		System.out.println("Node was traversed successfully in thread " + visit.getThreadID() + ": " + visit.getVisitedElement());

		if (!(visit.getVisitedElement() instanceof EmptyPoint || visit.getVisitedElement() instanceof DirectionArrow || visit.getVisitedElement() instanceof OrJoin || visit.getVisitedElement() instanceof OrFork)) {
			RespRef respref = (RespRef) ModelCreationFactory.getNewObject(urnspec, RespRef.class);
	
			extendPathAndInsert(visit, respref, true);
		}
	}

	public void threadDied(int threadId) {
		System.out.println("Thread " + threadId + " died");

	}

	public void threadsMerged(List oldThreadIDs, int newThreadID) {
		System.out.println("Threads " + ArrayAndListUtils.listToString(oldThreadIDs, ",") + " were merged into thread " + newThreadID);

		AndJoin andjoin = (AndJoin) ModelCreationFactory.getNewObject(urnspec, AndJoin.class);
		StartPoint sp = (StartPoint) extendPathAndInsert(newThreadID, andjoin, false);

		for (int i = 0; i < oldThreadIDs.size(); i++) {
			Integer threadID = (Integer) oldThreadIDs.get(i);
			EndPoint ep = (EndPoint) htThreadEnd.get(threadID);

			Command cmd;
			if (i == 0) {
				// reuse existing path.
				cmd = new MergeStartEndCommand(currentMap, sp, ep, sp.getX(), sp.getY());
			} else {
				cmd = new AttachBranchCommand(ep, andjoin);
			}
			cs.execute(cmd);
		}

		htThreadStart.put(new Integer(newThreadID), andjoin);

	}

	public void threadSplit(int oldThreadID, List newThreadIDs) {
		System.out.println("Thread " + oldThreadID + " was split into threads " + ArrayAndListUtils.listToString(newThreadIDs, ","));

		AndFork andfork = (AndFork) ModelCreationFactory.getNewObject(urnspec, AndFork.class);
		EndPoint ep = (EndPoint) extendPathAndInsert(oldThreadID, andfork, true);

		for (int i = 0; i < newThreadIDs.size(); i++) {
			Integer threadID = (Integer) newThreadIDs.get(i);
			StartPoint sp = (StartPoint) htThreadStart.get(threadID);

			Command cmd;
			if (i == 0) {
				// reuse existing path.
				cmd = new MergeStartEndCommand(currentMap, sp, ep, ep.getX(), ep.getY());
			} else {
				cmd = new AttachBranchCommand(sp, andfork);
			}
			cs.execute(cmd);
		}

		htThreadEnd.put(new Integer(oldThreadID), andfork);

	}

	public void timerTimeout(TraversalVisit visit) {
		System.out.println("Timer timeout in thread " + visit.getThreadID() + ": " + visit.getVisitedElement());

	}

	public void traversalEnded(UcmEnvironment env, ScenarioDef scenario) {
		System.out.println("Traversal ended: " + scenario.toString());
		//

		try {
			UrnModelManager manager = new UrnModelManager();

			IPath path = ((FileEditorInput) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput()).getFile()
					.getFullPath().removeLastSegments(1).append("__msctemp.jucm");
			manager.createURNspec(path, urnspec);
			manager.save(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void traversalStarted(UcmEnvironment env, ScenarioDef scenario) {
		System.out.println("Traversal started: " + scenario.toString());

		htThreadEnd = new HashMap();
		htThreadStart = new HashMap();

		currentScenario = scenario;

		// first scenario
		if (htScenarioToMap.size() == 0) {
			htScenarioToMap.put(scenario, (UCMmap) urnspec.getUrndef().getSpecDiagrams().get(0));
		} else if (!htScenarioToMap.containsKey(scenario)) { // new scenario
			CreateMapCommand cmd = new CreateMapCommand(urnspec);
			cs.execute(cmd);

			htScenarioToMap.put(scenario, cmd.getMap());
		}

		currentMap = (UCMmap) htScenarioToMap.get(scenario);
	}

}
