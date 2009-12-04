package seg.jUCMNav.model.util.modelexplore.queries.scenarioTraversal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioTraversalListenerList;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * Abstract base class that should reasonably be shared by any implementation of a Scenario Traversal Data Structure.
 * 
 * Represents the "plumbing" for a traversal data structure.
 * 
 * Responsibilities: - encapsulate logic of getting the next path node that must be processed - keep track of what has been visited - keep track of different
 * threads
 * 
 * 
 * @author jkealey
 * 
 */
public abstract class AbstractScenarioTraversalDataStructure {

    protected int _consecutiveReblocks;
    protected Vector _currentContext;
    protected int _currentThreadID;
    protected ScenarioTraversalListenerList _listeners;
    protected int _nextThreadID;
    protected HashMap _results;
    protected Vector _visited;

    public AbstractScenarioTraversalDataStructure() {
        _visited = new Vector();
        _results = new HashMap();
        _consecutiveReblocks = 0;
        _nextThreadID = 1;
        _currentThreadID = 0;
    }

    /**
     * Adds an element to the waiting list. Assuming elements have no memory for simplicity.
     * 
     * @param pn
     */
    protected abstract void addToWaitingList(PathNode pn);

    /**
     * Removes instances of the same path node from the waiting list since we're processing the node.
     * 
     */
    protected abstract void cleanWaitingList();

    /**
     * 
     * @param o
     *            the pathnode, nodeconnection or other eobject.
     * @return decrements the external counter for the number of times the traversal algo. has gone through this element.
     */
    protected int decrementHitCount(EObject o) {
        TraversalResult result;
        if (!_results.containsKey(o))
            _results.put(o, new TraversalResult());

        result = (TraversalResult) _results.get(o);
        result.decrementHitCount();

        return result.getExternalHitCount();
    }

    /**
     * 
     * @return forces an element to be removed from the waiting list
     */
    protected abstract TraversalVisit forceWaitingListPoll();

    /**
     * Removes instances of the same path node from the waiting list since we're processing the node.
     * 
     */
    protected abstract int getConsecutiveReblocks();

    /**
     * Returns the current plug-in binding context.
     * 
     * @return A vector of pluginbindings that were traversed to get to here.
     */
    protected Vector getCurrentContext() {
        return _currentContext;
    }

    /**
     * Returns the current (or last used) threadID.
     * 
     * @return and integer representing the thread number.
     */
    protected int getCurrentThreadID() {
        return _currentThreadID;
    }

    /**
     * 
     * @param o
     *            the pathnode, nodeconnection or other eobject.
     * @return Number of times the traversal algorithm has gone to this element.
     */
    protected int getHitCount(EObject o) {
        TraversalResult result;
        if (!_results.containsKey(o))
            _results.put(o, new TraversalResult());

        result = (TraversalResult) _results.get(o);

        return result.getHitCount();
    }

    /**
     * Returns the next threadID to be used.
     * 
     * @return and integer representing the thread number.
     */
    protected int getNextThreadID() {
        return _nextThreadID;
    }

    /**
     * Gets the next path node to be processed. This data structure encapsulates the logic behind which path node to process next (waiting lists, threads, etc).
     * 
     * @return the next PathNode
     */
    protected abstract TraversalVisit getNextVisit() throws TraversalException;

    /**
     * Find the reached end points.
     * 
     * @return a vector containing the end points that were visited.
     */
    protected Vector getReachedEndPoints() {
        Vector reachedEndPoints = new Vector();
        for (Iterator iter = _visited.iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            if (element instanceof EndPoint) {
                reachedEndPoints.add(element);
            }
        }
        return reachedEndPoints;
    }

    /**
     * The traversal results for each EObject
     * 
     * @return HashMap of EObject -> TraversalResults
     */
    protected HashMap getResults() {
        return _results;
    }

    /**
     * Returns the status of a certain thread.
     * 
     * Given a ThreadID, returns: -2 thread sanity check error -1 if the thread never existed 0 if the thread is dead 1 if the thread is alive and running 2 if
     * the thread is blocked in the waiting list
     * 
     * @return the thread status
     */
    protected abstract int getThreadState(int threadID);

    /**
     * A sequence of visited elements.
     * 
     * @return a sequence of visited pathnodes/nodeconnections
     */
    protected Vector getVisited() {
        return _visited;
    }

    /**
     * 
     * @param o
     *            the pathnode, nodeconnection or other eobject.
     * @return increment the counter for the number of times the traversal algo. has gone through this element.
     */
    protected int incrementHitCount(EObject o) {
        TraversalResult result;
        if (!_results.containsKey(o))
            _results.put(o, new TraversalResult());

        result = (TraversalResult) _results.get(o);
        result.incrementHitCount();

        return result.getHitCount();
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
    protected abstract void pushPathNode(NodeConnection nc, PathNode pn, boolean newThread);

    /**
     * Push a path node on the to be processed stack. Behaviour might vary if we are asking for a new thread (might be a new stack, depending on the
     * implementation).
     * 
     * @param pn
     *            the pathnode to process
     * @param newThread
     *            should this be treated in parallel with the other path nodes?
     */
    protected abstract void pushPathNode(PathNode pn, boolean newThread);

    /**
     * Initialize our stack with the given start points.
     * 
     * @param startPoints
     *            Vector of ScenarioStartPoints or StartPoints
     */
    protected String seedAlgorithm(Vector startPoints) {
        for (int i = startPoints.size() - 1; i >= 0; i--) {
            _currentContext = new Vector();
            if (startPoints.get(i) instanceof StartPoint)
                pushPathNode((StartPoint) startPoints.get(i), true);
            else if (startPoints.get(i) instanceof ScenarioStartPoint) {
                if (((ScenarioStartPoint) startPoints.get(i)).isEnabled())
                    pushPathNode(((ScenarioStartPoint) startPoints.get(i)).getStartPoint(), true);
            } else {
                return Messages.getString("DefaultScenarioTraversal.IllegalUseOfQuery"); //$NON-NLS-1$
            }
        }
        return null;
    }

    /**
     * Sets the current plug-in binding context.
     * 
     * @param ctx
     *            A vector of pluginbindings that were traversed to get to here.
     */
    protected void setCurrentContext(Vector ctx) {
        _currentContext = ctx;
    }

    /**
     * Adds a list of {@link ITraversalListener} to the current internal listener list.
     * 
     * @param listeners
     *            the new listeners
     */
    public void setListeners(ScenarioTraversalListenerList listeners) {
        _listeners = listeners;
    }

    /**
     * Increment the visits counter and add to the list of visited objects.
     * 
     * @param o
     *            the object for which to track visits.
     * @throws TraversalException
     *             if an infinite loop is detected.
     */
    protected void trackVisit(EObject o) throws TraversalException {
        _visited.add(o);

        int count = incrementHitCount(o);

        if (count >= ScenarioTraversalPreferences.getMaxHitCount()) {
            throw new TraversalException(Messages.getString("DefaultScenarioTraversal.InfiniteLoopDetectedOn") + o.toString()); //$NON-NLS-1$
        }
    }

    /**
     * Unblocks a waiting place and traverses the given nc
     * 
     * @param nc
     *            the node connection
     * @return a warning if it was not blocked.
     * @throws TraversalException
     */
    protected abstract TraversalWarning unblockWaitingPlace(NodeConnection nc) throws TraversalException;

    /**
     * Visit all outgoing paths, in parallel.
     * 
     * @param pn
     * @throws TraversalException
     */
    public void visitAllSucc(PathNode pn) throws TraversalException {
        // TODO: semantic variation: maybe we don't want all invocations of this method to run paths in parallel.
        for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            // visitNodeConnection(nc, true);
            visitNodeConnection(nc, pn.getSucc().size() != 1);
        }
    }

    /**
     * Visit the next node connection in the same thread.
     * 
     * @param nc
     * @throws TraversalException
     */
    public void visitNodeConnection(NodeConnection nc) throws TraversalException {
        visitNodeConnection(nc, false);
    }

    /**
     * Visit the next node in the same thread if newThread=false, in a new one otherwise.
     * 
     * We track the visit and push the target pathnode onto the appropriate stack.
     * 
     * @param nc
     * @param newThread
     * @throws TraversalException
     */
    public void visitNodeConnection(NodeConnection nc, boolean newThread) throws TraversalException {
        trackVisit(nc);
        pushPathNode(nc, (PathNode) nc.getTarget(), newThread);
    }

    /**
     * Visit the next path node. Should only have one outgoing link.
     * 
     * @param pn
     * @throws TraversalException
     * @return an error if one occurred, null otherwise.
     */
    public String visitOnlySucc(PathNode pn) throws TraversalException {
        if (pn.getSucc().size() == 1) {
            NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
            visitNodeConnection(nc);
        } else
            return Messages.getString("DefaultScenarioTraversal.TraversalError"); //$NON-NLS-1$

        return null;
    }

    /**
     * If has an outgoing link, visit it.
     * 
     * @param pn
     * @throws TraversalException
     */
    public void visitOnlySuccIfExists(PathNode pn) throws TraversalException {
        if (pn.getSucc().size() == 1) {
            visitOnlySucc(pn);
        }
    }

}