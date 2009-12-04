package seg.jUCMNav.model.util.modelexplore.queries.scenarioTraversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.Timer;

/**
 * Data Structure for the Default Scenario Traversal Algorithm.
 * 
 * As an child of {@link AbstractScenarioTraversalDataStructure}, it builds on the given plumbing and defines the basic logic for selecting the next path node
 * to be processed.
 * 
 * Responsibilities: - encapsulate logic of getting the next path node that must be processed - keep track of what has been visited - keep track of different
 * threads
 * 
 * TODO: we are currently losing the source node connection when popping and pushing back.
 * 
 * @author jkealey
 * 
 */
public class DefaultScenarioTraversalDataStructure extends AbstractScenarioTraversalDataStructure {
    // Stack of TraversalVisit
    protected Stack _toVisit;

    // Queue of TraversalVisit
    protected Queue _waitList;

    // last TraversalVisit returned by getNextVisit
    protected TraversalVisit _lastPopped;

    public DefaultScenarioTraversalDataStructure() {
        super();
        _toVisit = new Stack();
        _waitList = new LinkedList();
        _lastPopped = null;
    }

    /**
     * Adds an element to the waiting list. Assuming elements have no memory for simplicity.
     * 
     * @param pn
     */
    protected void addToWaitingList(PathNode pn) {
        // TODO: semantic variation: do we add doubles? does join have memory?
        // our implementation: and-joins do not have memory so doubles are not valid.

        decrementHitCount(pn);
        boolean alreadyExists = false;
        for (Iterator iterator = _waitList.iterator(); iterator.hasNext();) {
            TraversalVisit visit = (TraversalVisit) iterator.next();
            if (visit.getVisitedElement().equals(pn)) {
                // merge context.
                visit.increaseContext(_currentContext);
                alreadyExists = true;

                // assuming only one instance.
                break;
            }
        }

        if (!alreadyExists) {
            TraversalVisit visit = new TraversalVisit(pn, _currentContext, _currentThreadID);
            _waitList.offer(visit);
            _listeners.pathNodeBlocked(visit);
        }

        if (_lastPopped != null && pn == _lastPopped.getVisitedElement())
            _consecutiveReblocks++;

    }

    /**
     * Returns the number of times that we have given a node in {@link #getNextVisit()} that ended up getting pushed back into the waiting list by
     * {@link #addToWaitingList(PathNode)}
     * 
     */
    protected int getConsecutiveReblocks() {
        return _consecutiveReblocks;
    }

    /**
     * Gets the next path node to be processed. This data structure encapsulates the logic behind which path node to process next (waiting lists, threads, etc).
     * 
     * @return the next PathNode
     */
    protected TraversalVisit getNextVisit() throws TraversalException {

        // nothing to do
        if (_toVisit.size() == 0 && _waitList.size() == 0) {
            _lastPopped = null;
            _consecutiveReblocks = 0;
            return null;
        }

        // we have unblocked threads
        if (_toVisit.size() > 0) {
            _lastPopped = (TraversalVisit) _toVisit.pop();
            _currentContext = _lastPopped.getContext();
            _currentThreadID = _lastPopped.getThreadID();

            // removes instances of the same path node from the waiting list since we're processing the node.
            cleanWaitingList();

            _lastPopped.increaseContext(_currentContext);

            // return the unblocked node
            return _lastPopped;
        }

        // no unblocked threads left
        // have we tried all of the waiting list ?
        if (_consecutiveReblocks >= _waitList.size()) {
            // yes, we have tried all of them; abort the next one.

            // TODO: look for timeout paths first, then go for hard abort.
            TraversalVisit visit = (TraversalVisit) _waitList.peek();

            // if it is a timer, force the timeout path.
            if (visit.getVisitedElement() instanceof Timer && ((Timer) visit.getVisitedElement()).getSucc().size() == 2) {
                visit = (TraversalVisit) _waitList.poll();
                _lastPopped = visit;
                NodeConnection nc = (NodeConnection) ((Timer) visit.getVisitedElement()).getSucc().get(1);
                _currentContext = visit.getContext();
                _currentThreadID = visit.getThreadID();
                visitNodeConnection(nc);

                _listeners.timerTimeout(visit, false);
            } else {
                // otherwise (and join for example), kick the element out of our list.

                throw new TraversalException(Messages.getString("DefaultScenarioTraversal.TraversalBlockedOn") + visit.getVisitedElement().toString());//$NON-NLS-1$

                // now done in invoker
                //_warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversal.TraversalBlockedOn") + visit.getVisitedElement().toString(),visit.getVisitedElement(),IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
                // kick out of waiting list
                // _waitList.poll();
            }

            // recurse
            return getNextVisit();
        } else {
            // no, try the next on the waiting list
            _lastPopped = (TraversalVisit) _waitList.poll();
            _currentContext = _lastPopped.getContext();
            _currentThreadID = _lastPopped.getThreadID();

            _listeners.pathNodeUnblocked(_lastPopped);
            return _lastPopped;
        }

    }

    /**
     * Returns the status of a certain thread.
     * 
     * Given a ThreadID, returns: -2 thread sanity check error -1 if the thread never existed 0 if the thread is dead 1 if the thread is alive and running 2 if
     * the thread is blocked in the waiting list
     * 
     * @return the thread status
     */
    protected int getThreadState(int threadID) {
        if (threadID <= 0 || threadID >= _nextThreadID)
            return -1;

        boolean error = false;
        boolean inNormalList = false;
        boolean inWaitList = false;

        for (Iterator iter = _toVisit.iterator(); iter.hasNext();) {
            TraversalVisit element = (TraversalVisit) iter.next();
            if (element.getThreadID() == threadID) {

                if (inNormalList)
                    error = true;

                inNormalList = true;
            }
        }
        for (Iterator iter = _waitList.iterator(); iter.hasNext();) {
            TraversalVisit element = (TraversalVisit) iter.next();
            if (element.getThreadID() == threadID) {

                if (inWaitList || inNormalList) // if we already have it
                    error = true;

                inWaitList = true;
            }
        }

        if (error)
            return -2;
        else if (inNormalList)
            return 1;
        else if (inWaitList)
            return 2;
        else
            return 0;

    }

    protected TraversalVisit forceWaitingListPoll() {
        _consecutiveReblocks = 0;
        TraversalVisit visit = (TraversalVisit) _waitList.poll();
        _listeners.pathNodeAborted(visit);
        return visit;
    }

    /**
     * Removes instances of the same path node from the waiting list since we're processing the node.
     * 
     */
    protected void cleanWaitingList() {
        // TODO: semantic variation. we are removing one instance, implying waiting place has memory.
        // Remove all for no memory.
        PathNode peeking = _lastPopped.getVisitedElement();
        TraversalVisit toRemove = null;
        for (Iterator iter = _waitList.iterator(); iter.hasNext();) {
            TraversalVisit visit = (TraversalVisit) iter.next();
            if (visit.getVisitedElement().equals(peeking)) {
                toRemove = visit;

                break;
            }
        }

        if (toRemove != null) {
            // otherwise we lose what was already queued

            toRemove.increaseContext(_currentContext);
            _currentContext = (Vector) toRemove.getContext().clone();

            _waitList.remove(toRemove);
        }
    }

    /**
     * Push a path node on the to be processed stack. Behaviour might vary if we are asking for a new thread (might be a new stack, depending on the
     * implementation).
     * 
     * @param nc
     *            originating node connection
     * @param pn
     *            the pathnode to process
     * @param newThread
     *            should this be treated in parallel with the other path nodes?
     */
    protected void pushPathNode(NodeConnection nc, PathNode pn, boolean newThread) {
        // TODO: Semantic Variation: "multithreading" in different stacks.
        _consecutiveReblocks = 0;
        if (newThread)
            _currentThreadID = _nextThreadID++;

        TraversalVisit visit = new TraversalVisit(nc, pn, _currentContext, _currentThreadID);

        _toVisit.push(visit);

        if (newThread) {
            _listeners.newThreadStarted(visit);
        }
    }

    /**
     * Push a path node on the to be processed stack. Behaviour might vary if we are asking for a new thread (might be a new stack, depending on the
     * implementation).
     * 
     * @param pn
     *            the pathnode to process
     * @param newThread
     *            should this be treated in parallel with the other path nodes?
     */
    protected void pushPathNode(PathNode pn, boolean newThread) {
        // TODO: Semantic Variation: "multithreading" in different stacks.
        _consecutiveReblocks = 0;

        if (newThread)
            _currentThreadID = _nextThreadID++;

        TraversalVisit visit = new TraversalVisit(pn, _currentContext, _currentThreadID);
        _toVisit.push(visit);

        if (newThread) {
            _listeners.newThreadStarted(visit);
        }
    }

    protected TraversalWarning unblockWaitingPlace(NodeConnection nc) throws TraversalException {
        // TODO: Semantic variation: waiting place has memory?

        incrementHitCount(nc);

        // skip over because processNode doesn't look at incoming
        // connections to see if it can continue must be forced
        boolean alreadyExists = false;
        TraversalVisit toRemove = null;
        for (Iterator iterator = _waitList.iterator(); iterator.hasNext();) {
            TraversalVisit visit = (TraversalVisit) iterator.next();
            if (visit.getVisitedElement().equals(nc.getTarget())) {
                _currentThreadID = visit.getThreadID();
                _consecutiveReblocks = 0;

                incrementHitCount(nc.getTarget());
                NodeConnection nc2 = (NodeConnection) nc.getTarget().getSucc().get(0);

                if (visit.getContext() != null)
                    getCurrentContext().addAll(visit.getContext());

                // technically, this should not be a new thread, but it simplifies the MSC generation algorithm
                visitNodeConnection(nc2, true);

                _listeners.pathNodeUnblocked(visit);
                _listeners.leftWaitingPlace(visit, false);
                _listeners.pathNodeVisited(visit);
                alreadyExists = true;
                toRemove = visit;
                // assuming only one instance.
                break;
            }
        }

        // TODO: semantic variation: do we have multiple instances or not?
        // we prevent duplicates when adding, don't need to remove multiple instances.
        if (toRemove != null)
            _waitList.remove(toRemove);
        else
            return new TraversalWarning(Messages.getString("DefaultScenarioTraversal.RaceConditionSymptom"), nc.getTarget()); //$NON-NLS-1$

        return null;
    }

}
