package seg.jUCMNav.scenarios.algorithmInterfaces;

import java.util.List;

import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.scenario.ScenarioDef;
import urncore.Condition;

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
     * @param visit
     *            Where did we run the code?
     * @param code
     *            The code that was run.
     */
    public void codeExecuted(TraversalVisit visit, String code);

    /**
     * A condition was evaluated in the scenario's environment.
     * 
     * @param visit
     *            Where did we run the code? If is null, this is a scenario pre/post condition.
     * @param condition
     *            What condition did we evaluate?
     * @param result
     *            Was it true or false?
     * @param isPreCondition
     *            is this a start point's precondition?
     */
    public void conditionEvaluated(TraversalVisit visit, Condition condition, boolean result, boolean isPreCondition);

    /**
     * We are going from a stub to a plugin.
     * 
     * @param visit
     *            which stub in which thread
     * @param inb
     *            which inbinding are we traversing
     */
    public void drillDown(TraversalVisit visit, InBinding inb);

    /**
     * We are going from an end point back up to its parent map.
     * 
     * @param visit
     *            which end point in which thread
     * @param outb
     *            which outbinding are we traversing.
     */
    public void drillUp(TraversalVisit visit, OutBinding outb);

    /**
     * We have left a waiting place / timer. If it was blocked before, it isn't anymore.
     * 
     * @param visit
     *            what did we leave?
     * @param becauseOfCondition
     *            because of a condition (true) or because of a path arrival (false)
     */
    public void leftWaitingPlace(TraversalVisit visit, boolean becauseOfCondition);

    /**
     * When traversing this element, we started a new thread. The ThreadID can be obtained by querying the TraversalVisit.
     * 
     * It might be issued from some merger, see {@link #threadsMerged(List, int)} and {@link #threadSplit(int, List)}
     * 
     * @param visit
     *            Where did we start the thread?
     */
    public void newThreadStarted(TraversalVisit visit);

    /**
     * We aborted a node, kicking it out of the waiting list; it will never be attempted again.
     * 
     * @param visit
     *            The node that was aborted
     */
    public void pathNodeAborted(TraversalVisit visit);

    /**
     * We are attempting a node.
     * 
     * @param visit
     *            The node that is being attempted.
     */
    public void pathNodeAttempted(TraversalVisit visit);

    /**
     * We are blocking a node by pushing it into our waiting list.
     * 
     * @param visit
     *            The node being blocked.
     */
    public void pathNodeBlocked(TraversalVisit visit);

    /**
     * We are unblocking a node, pulling it out of the waiting list. This can either mean it was unblocked by some other traversal element or we are attempting
     * it and might push it back onto the waiting list.
     * 
     * @param visit
     *            The node being unblocked.
     */
    public void pathNodeUnblocked(TraversalVisit visit);

    /**
     * We have successfully traversed this element.
     * 
     * @param visit
     *            The node that was traversed.
     */
    public void pathNodeVisited(TraversalVisit visit);

    /**
     * While traversing, a certain thread died. It might be merged into something else, see {@link #threadsMerged(List, int)} and
     * {@link #threadSplit(int, List)}
     * 
     * @param threadID
     *            which thread?
     */
    public void threadDied(int threadID);

    /**
     * Multiple threads were merged into a single one.
     * 
     * @param oldThreadIDs
     *            a list of Integers, reprensenting dead thread ids.
     * @param newThreadID
     *            the new thread id
     */
    public void threadsMerged(List oldThreadIDs, int newThreadID);

    /**
     * A single thread was split into multiple threads.
     * 
     * @param oldThreadID
     *            the original thread.
     * @param newThreadIDs
     *            a list of integers representing the new threads.
     */
    public void threadSplit(int oldThreadID, List newThreadIDs);

    /**
     * A timer has timed out.
     * 
     * @param visit
     *            which timer?
     * @param becauseOfCondition
     *            because of a condition (true) or because was forced to timeout (false)
     */
    public void timerTimeout(TraversalVisit visit, boolean becauseOfCondition);

    /**
     * All scenarios were traversed and the algorithm will not be sending anything more to this listener.
     */
    public void traversalEnded();

    /**
     * The algorithm has finished traversing a scenario.
     * 
     * @param env
     *            the {@link UcmEnvironment} in which it was traversed.
     * @param scenario
     *            the scenario that was run.
     */
    public void traversalEnded(UcmEnvironment env, ScenarioDef scenario);

    /**
     * The algorithm is starting to traverse a scenario.
     * 
     * @param env
     *            the original {@link UcmEnvironment}
     * @param scenario
     *            the scenario to be ran.
     */
    public void traversalStarted(UcmEnvironment env, ScenarioDef scenario);
}
