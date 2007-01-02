package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
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
	
	public DefaultScenarioTraversalDataStructure() {
		_visited = new Vector();
		_toVisit = new Stack();
		_results = new HashMap();
		_waitList = new LinkedList();
		_consecutiveReblocks =0;
		_lastPopped=null;
		
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
		
		if (!alreadyExists)
			_waitList.offer(new TraversalVisit(pn, _currentContext));
		
		if (_lastPopped!=null && pn == _lastPopped.getVisitedElement())
			_consecutiveReblocks++;
		
	}
	
	/**
	 * Returns the current plug-in binding context.
	 *  
	 * @return A vector of pluginbindings that were traversed to get to here. 
	 */
	public Vector getCurrentContext() {
		return _currentContext;
	}
	
	/**
	 * 
	 * @param o
	 *            the pathnode, nodeconnection or other eobject.
	 * @return Number of times the traversal algorithm has gone to this element.
	 */
	public int getHitCount(EObject o) {
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
	public TraversalVisit getNextVisit() throws TraversalException {
		
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
			
			
			// removes instances of the same path node from the waiting list since we're processing the node. 
			cleanWaitingList();
			
			// return the unblocked node
			return _lastPopped;
		}
		

		// no unblocked threads left
		// have we tried all of the waiting list ?
		if (_consecutiveReblocks>=_waitList.size()){
			// yes, we have tried all of them; abort the next one. 

			TraversalVisit visit = (TraversalVisit)_waitList.peek();
			
			// if it is a timer, force the timeout path.
			if (visit.getVisitedElement() instanceof Timer && ((Timer) visit.getVisitedElement()).getSucc().size() == 2) {
				visit = (TraversalVisit) _waitList.poll();
				_lastPopped=visit;
				NodeConnection nc = (NodeConnection) ((Timer)visit.getVisitedElement()).getSucc().get(1);
				_currentContext = visit.getContext();
				visitNodeConnection(nc);
				
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
			return _lastPopped;
		}
		
	
//		
//		
//		// TODO: rewrite completely. 
//		int lastAttempt = Integer.MAX_VALUE;
//
//		while ((_toVisit.size() > 0 || _waitList.size() > 0)) {
//			if (_waitList.size() > 0 && _toVisit.size() < lastAttempt) {
//				int sz = _waitList.size();
//
//				
//				processNode(env, (TraversalVisit) _waitList.poll());
//
//				if (sz == _waitList.size())
//					// was not able to make any progress.
//					lastAttempt = _toVisit.size();
//				else
//					// made progress
//					lastAttempt = Integer.MAX_VALUE;
//			}
//
//			if (_toVisit.size() > 0) {
//
//				// TODO: this is invalid. it removes all instances from the stack.
//				// if we hit another path that finds a node that is already on the waiting list, process it immediately, and remove it from the to process list.
////				while (_waitList.contains(_toVisit.peek())) {
////				_waitList.remove(_toVisit.peek());
////			}
//				
//				// TODO: semantic variation. we are removing one instance, implying waiting place has memory.
//				// Remove all for no memory. 
//				PathNode peeking = (PathNode) ((TraversalVisit)_toVisit.peek()).getVisitedElement();
//				TraversalVisit toRemove = null;
//				for (Iterator iter = _waitList.iterator(); iter.hasNext();) {
//					TraversalVisit visit = (TraversalVisit) iter.next();
//					if (visit.getVisitedElement().equals(peeking)) {
//						toRemove = visit;
//						break;
//					}
//				}
//
//				if (toRemove!=null)
//					_waitList.remove(toRemove);
//				
//				processNode(env, (TraversalVisit) _toVisit.pop());
//
//			} else {
//				// TODO: this is invalid. it doesn't check to see any other blocked elements, it boots the next one out, which may still work.
//				// if we don't have anything to do in the toVisit queue and our last attempt at processing the _waitList failed, we've got to boot one element
//				// out.
//				if (lastAttempt == 0 && _waitList.size() > 0) {
//					TraversalVisit visit = (TraversalVisit)_waitList.peek();
//					
//					// if it is a timer, force the timeout path.
//					if (visit.getVisitedElement() instanceof Timer && ((Timer) visit.getVisitedElement()).getSucc().size() == 2) {
//						visit = (TraversalVisit) _waitList.poll();
//						NodeConnection nc = (NodeConnection) ((Timer)visit.getVisitedElement()).getSucc().get(1);
//						_currentContext = visit.getContext();
//						visitNodeConnection(nc);
//					} else { // otherwise (and join for example), kick the element out of our list.
//						_warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversal.TraversalBlockedOn") + visit.getVisitedElement().toString(),visit.getVisitedElement(),IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
//
//						// kick out of waiting list because this is just a
//						// warning
//						_waitList.poll();
//					}
//
//				}
//			}
//		}
//		
		
	}
	
	
	protected TraversalVisit forceWaitingListPoll() {
		_consecutiveReblocks=0;
		return (TraversalVisit) _waitList.poll();
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

		if (toRemove!=null)
			_waitList.remove(toRemove);
	}
	
	
	/**
	 * Find the reached end points. 
	 * 
	 * @return a vector containing the end points that were visited. 
	 */
	public Vector getReachedEndPoints() {
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
	public HashMap getResults() {
		return _results;
	}
	
	/**
	 * A sequence of visited elements. 
	 * 
	 * @return a sequence of visited pathnodes/nodeconnections
	 */
	public Vector getVisited() {
		return _visited;
	}

	/**
	 * 
	 * @param o
	 *            the pathnode, nodeconnection or other eobject.
	 * @return increment the counter for the number of times the traversal algo. has gone through this element.
	 */
	public int incrementHitCount(EObject o) {
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
		_toVisit.push(new TraversalVisit(nc, pn, _currentContext));
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
		_toVisit.push(new TraversalVisit(pn, _currentContext));
	}
	
	/**
	 * Initialize our stack with the given start points.
	 * 
	 * @param startPoints
	 *            Vector of ScenarioStartPoints or StartPoints
	 */
	public String seedAlgorithm(Vector startPoints) {
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
	public void setCurrentContext(Vector ctx) {
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
	public void trackVisit(EObject o) throws TraversalException {
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
				incrementHitCount(nc.getTarget());
				NodeConnection nc2 = (NodeConnection) nc.getTarget().getSucc().get(0);
				
				
				if (visit.getContext()!=null)
					getCurrentContext().addAll(visit.getContext());

				visitNodeConnection(nc2);
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
	protected void visitAllSucc(PathNode pn) throws TraversalException {
		// TODO: semantic variation: maybe we don't want all invocations of this method to run paths in parallel.
		for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
			NodeConnection nc = (NodeConnection) iter.next();
			visitNodeConnection(nc, true);
		}
	}

	/**
	 * Visit the next node connection in the same thread.
	 * 
	 * @param nc
	 * @throws TraversalException
	 */
	protected void visitNodeConnection(NodeConnection nc) throws TraversalException {
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
	protected void visitNodeConnection(NodeConnection nc, boolean newThread) throws TraversalException {
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
	protected String visitOnlySucc(PathNode pn) throws TraversalException {
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
	protected void visitOnlySuccIfExists(PathNode pn) throws TraversalException {
		if (pn.getSucc().size() == 1) {
			visitOnlySucc(pn);
		}
	}
	
}
