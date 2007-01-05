package seg.jUCMNav.scenarios.algorithmInterfaces;

import java.util.List;

import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.scenario.ScenarioDef;

/**
 * Interface to be implemented by scenario traversal listeners. 
 *  
 * @author jkealey
 *
 */
public interface ITraversalListener {

	public void codeExecuted(TraversalVisit visit, String code);
	public void conditionEvaluated(TraversalVisit visit, String condition, boolean result );
	public void newThreadStarted(TraversalVisit visit);
	public void pathNodeAborted(TraversalVisit visit);
	public void pathNodeAttempted(TraversalVisit visit);
	public void pathNodeBlocked(TraversalVisit visit);
	public void pathNodeVisited(TraversalVisit visit);
	public void threadDied(int threadID);
	public void threadsMerged(List oldThreadIDs, int newThreadID );
	public void threadSplit(int oldThreadID, List newThreadIDs);
	public void timerTimeout(TraversalVisit visit);
	public void traversalEnded(UcmEnvironment env, ScenarioDef scenario);
	public void traversalStarted(UcmEnvironment env, ScenarioDef scenario);
}
