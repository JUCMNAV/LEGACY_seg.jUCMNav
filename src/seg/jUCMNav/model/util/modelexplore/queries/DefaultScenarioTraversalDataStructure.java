package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioTraversalListenerList;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.scenario.ScenarioStartPoint;


/**
 * Data Structure for the Default Scenario Traversal Algorithm.
 * 
 * Responsibilities:
 * - encapsulate logic of getting the next path node that must be processed
 * - keep track of what has been visited
 * - keep track of different threads
 * 
 * TODO: refine interface to offer public interface. 
 *   
 * @author jkealey
 *
 */
public class DefaultScenarioTraversalDataStructure {
	// Vector of PluginBindings
	protected Vector _currentContext;

	// HashMap of EObject -> TraversalResults
	protected HashMap _results;

	// Stack of TraversalVisit
	protected Stack _toVisit;

	// Vector of EObject.
	protected Vector _visited;
	
	// Queue of TraversalVisit
	protected Queue _waitList;
	
	// number of consecutive times we have reblocked an item that was on the waiting list
	protected int _consecutiveReblocks;
	
	// last TraversalVisit returned by getNextVisit 
	protected TraversalVisit _lastPopped;
	
	// the next ID that will be given to a new thread. 
	protected int _nextThreadID;
	
	// the thread ID that will be re-used if no new thread is asked. 
	protected int _currentThreadID;
	
	protected ScenarioTraversalListenerList _listeners;
	
	public DefaultScenarioTraversalDataStructure(ScenarioTraversalListenerList listeners) {
		_visited = new Vector();
		_toVisit = new Stack();
		_results = new HashMap();
		_waitList = new LinkedList();
		_consecutiveReblocks =0;
		_lastPopped=null;
		_nextThreadID=1;
		_currentThreadID=0;
		_listeners = listeners;
	}
	
	/**
	 * Adds an element to the waiting list. Assuming elements have no memory for simplicity.  
	 *  
	 * @param pn
	 */
	public void addToWaitingList(PathNode pn) {
		// TODO: semantic variation: do we add doubles? does join have memory?
		// our implementation: and-joins do not have memory so doubles are not valid. 
		boolean alreadyExists=false;
		for (Iterator iterator = _waitList.iterator(); iterator.hasNext();) {
			TraversalVisit visit = (TraversalVisit) iterator.next();
			if (visit.getVisitedElement().equals(pn)) { 
				// merge context. 
				visit.increaseContext(_currentContext);
				alreadyExists=true;
				
				// assuming only one instance. 
				break;
			}
		}
		
		
		if (!alreadyExists) {
			TraversalVisit visit = new TraversalVisit(pn, _currentContext, _currentThreadID);
			_waitList.offer(visit);
			_listeners.pathNodeBlocked(visit);
		}
		
		if (_lastPopped!=null && pn == _lastPopped.getVisitedElement())
			_consecutiveReblocks++;
		
	}
	
	/**
	 * Returns the number of times that we have given a node in {@link #getNextVisit()} that ended up 
	 * getting pushed back into the waiting list by {@link #addToWaitingList(PathNode)}
	 * 
	 */
	protected int getConsecutiveReblocks() {
		return _consecutiveReblocks;
	}
	
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
	 * Returns the next threadID to be used. 
	 * 
	 * @return and integer representing the thread number. 
	 */
	protected int getNextThreadID() {
		return _nextThreadID;
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
	 * Gets the next path node to be processed. This data structure encapsulates the logic behind which path node to process next (waiting lists, threads, etc). 
	 * 
	 * @return the next PathNode
	 */
	protected TraversalVisit getNextVisit() throws TraversalException {
		
		// nothing to do
		if (_toVisit.size()==0 && _waitList.size()==0) {
			_lastPopped=null;
			_consecutiveReblocks=0;
			return null;
		}
		
		
		// we have unblocked threads
		if (_toVisit.size()>0)
		{
			_lastPopped=(TraversalVisit) _toVisit.pop();
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
		if (_consecutiveReblocks>=_waitList.size()){
			// yes, we have tried all of them; abort the next one. 

			// TODO: look for timeout paths first, then go for hard abort.  
			TraversalVisit visit = (TraversalVisit)_waitList.peek();
			
			// if it is a timer, force the timeout path.
			if (visit.getVisitedElement() instanceof Timer && ((Timer) visit.getVisitedElement()).getSucc().size() == 2) {
				visit = (TraversalVisit) _waitList.poll();
				_lastPopped=visit;
				NodeConnection nc = (NodeConnection) ((Timer)visit.getVisitedElement()).getSucc().get(1);
				_currentContext = visit.getContext();
				_currentThreadID = visit.getThreadID();
				visitNodeConnection(nc);

				_listeners.timerTimeout(visit);
			} else { 
				// otherwise (and join for example), kick the element out of our list.

				throw new TraversalException(Messages.getString("DefaultScenarioTraversal.TraversalBlockedOn") + visit.getVisitedElement().toString());//$NON-NLS-1$

				// now done in invoker
				//_warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversal.TraversalBlockedOn") + visit.getVisitedElement().toString(),visit.getVisitedElement(),IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
				// kick out of waiting list 
				//_waitList.poll();
			}
			
			// recurse 
			return getNextVisit();
		}
		else
		{
			// no, try the next on the waiting list
			_lastPopped = (TraversalVisit) _waitList.poll();
			_currentContext = _lastPopped.getContext();
			_currentThreadID = _lastPopped.getThreadID();

			return _lastPopped;
		}
		
	}

	/**
	 * Returns the status of a certain thread. 
	 * 
	 * Given a ThreadID, returns:
	 * -2 thread sanity check error
	 * -1 if the thread never existed
	 * 0 if the thread is dead
	 * 1 if the thread is alive and running
	 * 2 if the thread is blocked in the waiting list
	 * 
	 * @return the thread status
	 */
	protected int getThreadState(int threadID)
	{
		if (threadID<=0 || threadID>=_nextThreadID)
			return -1;
		
		boolean error=false;
		boolean inNormalList=false;
		boolean inWaitList=false;
		
		for (Iterator iter = _toVisit.iterator(); iter.hasNext();) {
			TraversalVisit element = (TraversalVisit) iter.next();
			if (element.getThreadID()==threadID) {
				
				if (inNormalList)
					error=true;
				
				inNormalList=true;
			}
		}
		for (Iterator iter = _waitList.iterator(); iter.hasNext();) {
			TraversalVisit element = (TraversalVisit) iter.next();
			if (element.getThreadID()==threadID) {
				
				if (inWaitList || inNormalList) // if we already have it
					error=true;
				
				inWaitList=true;
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
		_consecutiveReblocks=0;
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
		PathNode peeking = (PathNode) _lastPopped.getVisitedElement();
		TraversalVisit toRemove = null;
		for (Iterator iter = _waitList.iterator(); iter.hasNext();) {
			TraversalVisit visit = (TraversalVisit) iter.next();
			if (visit.getVisitedElement().equals(peeking)) {
				toRemove = visit;
				
				break;
			}
		}

		
		
		if (toRemove!=null) {
			// otherwise we lose what was already queued

			toRemove.increaseContext(_currentContext);
			_currentContext = (Vector) toRemove.getContext().clone();

			_waitList.remove(toRemove);
		}
	}
	
	
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
	 * @return HashMap of EObject -> TraversalResults 
	 */
	protected HashMap getResults() {
		return _results;
	}
	
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
	 * 				originating node connection
	 * @param pn
	 *            the pathnode to process
	 * @param newThread
	 *            should this be treated in parallel with the other path nodes?
	 */
	protected void pushPathNode(NodeConnection nc, PathNode pn, boolean newThread) {
		// TODO: Semantic Variation: "multithreading" in different stacks.
		_consecutiveReblocks=0;
		if (newThread)
			_currentThreadID = _nextThreadID++;

		TraversalVisit visit = new TraversalVisit(nc, pn, _currentContext, _currentThreadID);

		_toVisit.push(visit);
		
		if (newThread)
		{
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
		_consecutiveReblocks=0;
		
		if (newThread) 
			_currentThreadID = _nextThreadID++;

		TraversalVisit visit = new TraversalVisit(pn, _currentContext, _currentThreadID); 
		_toVisit.push(visit);
		
		if (newThread)
		{
			_listeners.newThreadStarted(visit);
		}
	}
	
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
	 * @param ctx A vector of pluginbindings that were traversed to get to here.
	 */
	protected void setCurrentContext(Vector ctx) {
		_currentContext = ctx;
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
	
	
	protected TraversalWarning unblockWaitingPlace(NodeConnection nc) throws TraversalException {
		// TODO: Semantic variation: waiting place has memory?

		incrementHitCount(nc);

		// skip over because processNode doesn't look at incoming
		// connections to see if it can continue must be forced
		boolean alreadyExists=false;
		TraversalVisit toRemove=null;
		for (Iterator iterator = _waitList.iterator(); iterator.hasNext();) {
			TraversalVisit visit = (TraversalVisit) iterator.next();
			if (visit.getVisitedElement().equals(nc.getTarget())) {
				_currentThreadID = visit.getThreadID();
				
				incrementHitCount(nc.getTarget());
				NodeConnection nc2 = (NodeConnection) nc.getTarget().getSucc().get(0);
				
				
				if (visit.getContext()!=null)
					getCurrentContext().addAll(visit.getContext());

				// technically, this should not be a new thread, but it simplifies the MSC generation algorithm
				visitNodeConnection(nc2, true);
				alreadyExists=true;
				toRemove=visit;
				// assuming only one instance. 
				break;
			}
		}
		

		// TODO: semantic variation: do we have multiple instances or not? 
		// we prevent duplicates when adding, don't need to remove multiple instances.
		if (toRemove!=null)
			_waitList.remove(toRemove);
		else 
			return new TraversalWarning(Messages.getString("DefaultScenarioTraversal.RaceConditionSymptom"), nc.getTarget()); //$NON-NLS-1$
		
		return null;
	}

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
			//visitNodeConnection(nc, true);
			visitNodeConnection(nc, pn.getSucc().size()!=1);
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
