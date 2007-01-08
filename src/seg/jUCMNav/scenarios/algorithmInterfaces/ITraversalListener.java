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

	/**
	 * Ran some code in the scenario's environment. 
	 * 
	 * @param visit Where did we run the code? 
	 * @param code The code that was run. 
	 */
	public void codeExecuted(TraversalVisit visit, String code);
	
	/**
	 * A condition was evaluated in the scenario's environment. 
	 * 
	 * @param visit Where did we run the code? If is null, this is a scenario pre/post condition. 
	 * @param condition What condition did we evaluate?
	 * @param result Was it true or false?
	 */
	public void conditionEvaluated(TraversalVisit visit, String condition, boolean result );
	
	/**
	 * When traversing this element, we started a new thread. The ThreadID can be obtained by querying the TraversalVisit. 
	 * 
	 * @param visit Where did we start the thread?
	 */
	public void newThreadStarted(TraversalVisit visit);
	
	/**
	 * We aborted a node, kicking it out of the waiting list; it will never be attempted again. 
	 * 
	 * @param visit The node that was aborted
	 */
	public void pathNodeAborted(TraversalVisit visit);
	
	/**
	 * We are attempting a node. 
	 * 
	 * @param visit The node that is being attempted. 
	 */
	public void pathNodeAttempted(TraversalVisit visit);
	
	/**
	 * We are blocking a node by pushing it into our waiting list. 
	 * @param visit The node being blocked. 
	 */
	public void pathNodeBlocked(TraversalVisit visit);
	
	/**
	 * We are unblocking a node, pulling it out of the waiting list. This can either mean it was unblocked by some other traversal element or we are attempting it and might push it back onto the waiting list.   
	 * @param visit The node being unblocked. 
	 */
	public void pathNodeUnblocked(TraversalVisit visit);
	
	/**
	 * We have successfully traversed this element. 
	 * 
	 * @param visit The node that was traversed. 
	 */
	public void pathNodeVisited(TraversalVisit visit);
	public void threadDied(int threadID);
	public void threadsMerged(List oldThreadIDs, int newThreadID );
	public void threadSplit(int oldThreadID, List newThreadIDs);
	public void timerTimeout(TraversalVisit visit);
	public void traversalEnded(UcmEnvironment env, ScenarioDef scenario);
	public void traversalStarted(UcmEnvironment env, ScenarioDef scenario);
}
