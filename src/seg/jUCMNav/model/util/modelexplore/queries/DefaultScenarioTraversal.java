package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

/**
 * Query processor representing the sequence of elements traversed by the scenario traversal algorithm.
 * 
 * TODO: timer does not have timer variable.
 * 
 * TODO: resp repetition count
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class DefaultScenarioTraversal extends AbstractQueryProcessor implements IQueryProcessorChain {

	public class QDefaultScenarioTraversal extends QueryRequest {
		// EndPoints / ScenarioEndPoints
		protected Vector _EndPathNodes;
		protected UcmEnvironment _env;

		// StartPoints / ScenarioStartPoints
		protected Vector _StartPathNodes;

		/**
		 * 
		 * @param startNodes
		 *            the starting point for traversal.
		 * @param endNodes
		 *            the set of end points that must be reached.
		 */
		public QDefaultScenarioTraversal(UcmEnvironment env, Vector startNodes, Vector endNodes) {
			this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
			_StartPathNodes = startNodes;
			_EndPathNodes = endNodes;
			_env = env;

		}

		public Vector getEndPathNodes() {
			return _EndPathNodes;
		}

		public UcmEnvironment getEnvironment() {
			return _env;
		}

		public Vector getStartPathNodes() {
			return _StartPathNodes;
		}

	}

	public class RTraversalSequence extends QueryResponse {
		protected String error;
		protected HashMap results;
		/* Data structure (query response) for passing a list of EObjects */
		protected Vector visited;
		protected Vector warnings;

		public RTraversalSequence() {
			this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
		}

		public String getError() {
			return error;
		}

		public HashMap getResults() {
			return results;
		}

		/**
		 * @return Returns the nodes.
		 */
		public Vector getVisited() {
			return visited;
		}

		public Vector getWarnings() {
			return warnings;
		}

		public void setError(String error) {
			this.error = error;
		}

		public void setResults(HashMap results) {
			this.results = results;
		}

		/**
		 * @param visited
		 *            The nodes / node connections to set.
		 */
		public void setVisited(Vector visited) {
			this.visited = visited;
		}

		public void setWarnings(Vector warnings) {
			this.warnings = warnings;
		}

	}

	protected static final int MAXVISITCOUNT = 100;

	// fatal error
	protected String _error;

	// HashMap of EObject -> TraversalResults
	protected HashMap _results;

	// Stack of TraversalVisit
	protected Stack _toVisit;

	// Vector of EObject.
	protected Vector _visited;

	// Queue of TraversalVisit
	protected Queue _waitList;

	// Vector of Strings
	protected Vector _warnings;
	
	// Vector of PluginBindings
	protected Vector _currentContext;
	

	/**
	 * Query processor representing the sequence of elements traversed by the scenario traversal algorithm.
	 * 
	 */
	public DefaultScenarioTraversal() {
		this._answerQueryTypes = new String[] { QueryObject.DEFAULTSCENARIOTRAVERSAL };
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
	 * Executes the traversal.
	 * 
	 * @param env
	 *            the ucm environment in which to execute the code.
	 * @param startPoints
	 *            the list of start points / scenario start points to initialize the traversal.
	 * 
	 * @param endPoints
	 *            the list of end points /scenario end points that must be reached.
	 */
	protected void process(UcmEnvironment env, Vector startPoints, Vector endPoints) {

		// initialize the stacks with the start points.
		seedAlgorithm(startPoints);
		if (_error != null)
			return;

		try {
			processAllNodes(env);

			// find reached end points. must create new vector here (or clone _visited) because we are modifying it in verifyEndPoints
			Vector reachedEndPoints = new Vector();
			for (Iterator iter = _visited.iterator(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element instanceof EndPoint) {
					reachedEndPoints.add(element);
				}
			}

			// verify that the reached end points match the expected ones.
			verifyEndPoints(endPoints, reachedEndPoints);
		} catch (Exception e) {
			_error = "Exception occurred: " + e.getMessage();
		}

	}

	/**
	 * Schedules the nodes according to what is in the stacks.
	 * 
	 * @param env
	 *            the execution environment.
	 * @throws TraversalException
	 *             a fatal error occurred.
	 */
	protected void processAllNodes(UcmEnvironment env) throws TraversalException {
		int lastAttempt = Integer.MAX_VALUE;

		while ((_toVisit.size() > 0 || _waitList.size() > 0) && _error == null) {
			if (_waitList.size() > 0 && _toVisit.size() < lastAttempt) {
				int sz = _waitList.size();

				processNode(env, (TraversalVisit) _waitList.poll());

				if (sz == _waitList.size())
					// was not able to make any progress.
					lastAttempt = _toVisit.size();
				else
					// made progress
					lastAttempt = Integer.MAX_VALUE;

				if (_error != null)
					return;
			}

			if (_toVisit.size() > 0) {

				// TODO: this is invalid. it removes all instances from the stack.
				// if we hit another path that finds a node that is already on the waiting list, process it immediately, and remove it from the to process list.
//				while (_waitList.contains(_toVisit.peek())) {
//				_waitList.remove(_toVisit.peek());
//			}
				
				// TODO: semantic variation. we are removing one instance, implying waiting place has memory.
				// Remove all for no memory. 
				PathNode peeking = (PathNode) ((TraversalVisit)_toVisit.peek()).getVisitedElement();
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
				
				processNode(env, (TraversalVisit) _toVisit.pop());

			} else {
				// TODO: this is invalid. it doesn't check to see any other blocked elements, it boots the next one out, which may still work.
				// if we don't have anything to do in the toVisit queue and our last attempt at processing the _waitList failed, we've got to boot one element
				// out.
				if (lastAttempt == 0 && _waitList.size() > 0) {
					TraversalVisit visit = (TraversalVisit)_waitList.peek();
					
					// if it is a timer, force the timeout path.
					if (visit.getVisitedElement() instanceof Timer && ((Timer) visit.getVisitedElement()).getSucc().size() == 2) {
						visit = (TraversalVisit) _waitList.poll();
						NodeConnection nc = (NodeConnection) ((Timer)visit.getVisitedElement()).getSucc().get(1);
						_currentContext = visit.getContext();
						visitNodeConnection(nc);
					} else { // otherwise (and join for example), kick the element out of our list.
						_warnings.add(new TraversalWarning("Traversal blocked on " + visit.getVisitedElement().toString(),visit.getVisitedElement()));

						// kick out of waiting list because this is just a
						// warning
						_waitList.poll();
					}

				}
			}
		}
	}

	/**
	 * Processes an and fork.
	 * 
	 * @param env
	 * @param andfork
	 * @throws TraversalException
	 */
	protected void processAndFork(UcmEnvironment env, AndFork andfork) throws TraversalException {
		// launches in parellel
		visitAllSucc(andfork);
	}

	/**
	 * Processes an and join.
	 * 
	 * @param env
	 * @param andjoin
	 * @throws TraversalException
	 */
	protected void processAndJoin(UcmEnvironment env, AndJoin andjoin) throws TraversalException {

		// TODO: Semantic variation: and join has memory or not?
		if (andjoin.getSucc().size() == 1) {
			NodeConnection out = (NodeConnection) andjoin.getSucc().get(0);
			boolean blocked = false;
			
//			determines if we have a new token or if this is another branch that is arriving. 
//			code never tested
//			
//			int count=getHitCount(((NodeConnection)_visited.lastElement()));
//			boolean newToken=true;
//			for (Iterator iter = andjoin.getPred().iterator(); iter.hasNext();) {
//				NodeConnection in = (NodeConnection) iter.next();
//				if (count<=getHitCount(in) && !in.equals(_visited.lastElement())))
//					newToken=false;
//			}
			
			for (Iterator iter = andjoin.getPred().iterator(); iter.hasNext() && !blocked;) {
				NodeConnection in = (NodeConnection) iter.next();
				if (getHitCount(out) + 1 > getHitCount(in)) {

					// we have to wait for more branches.  
					
					addToWaitingList(andjoin);
						 

					blocked = true;
				}
			}

			if (!blocked)
				visitNodeConnection(out);

		} else
			_error = "Traversal error.";
	}

	/**
	 * Adds an element to the waiting list. Assuming elements have no memory for simplicity.  
	 *  
	 * @param pn
	 */
	protected void addToWaitingList(PathNode pn) {
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
	}

	/**
	 * Processes a connect element.
	 * 
	 * @param env
	 * @param connect
	 * @throws TraversalException
	 */
	protected void processConnect(UcmEnvironment env, Connect connect) throws TraversalException {
		NodeConnection nc = (NodeConnection) connect.getSucc().get(0);
		if (nc.getTarget() instanceof StartPoint)
			visitNodeConnection(nc);
		else if (nc.getTarget() instanceof WaitingPlace) {
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
					_currentContext.addAll(visit.getContext());

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
				_warnings.add(new TraversalWarning("Race condition symptom: connected path arrived at a non-blocked path.", nc.getTarget()));

		} else
			_error = "Traversal error.";
	}

	/**
	 * Processes an empty point or direction arrow.
	 * 
	 * @param env
	 * @param pn
	 * @throws TraversalException
	 */
	protected void processEmptyPoint(UcmEnvironment env, PathNode pn) throws TraversalException {
		// can be empty point or direction arrow
		// empty points can have asynch conections. 
		visitAllSucc(pn);
	}

	/**
	 * Processes an end point.
	 * 
	 * @param env
	 * @param end
	 * @throws TraversalException
	 */
	protected void processEndPoint(UcmEnvironment env, EndPoint end) throws TraversalException {
		Condition post = end.getPostcondition();

		
		// TODO: semantic variation: if postcondition is false, should we continue processing?
		if (testCondition(env, end.getPostcondition(), Boolean.TRUE, "Postcondition to End Point \"" + end.getName() + " (" + end.getId()
				+ ")\" did not evaluate to true.")) {

			// filter by "instance" that launched the start.
			Vector outbindings = new Vector();
			// first find outbindings we need to fire
			for (Iterator iter = _currentContext.iterator(); iter.hasNext();) {
				PluginBinding binding = (PluginBinding) iter.next();
				for (Iterator iterator = binding.getOut().iterator(); iterator.hasNext();) {
					OutBinding outbinding = (OutBinding) iterator.next();
					if (outbinding.getEndPoint().equals(end))
					{
						outbindings.add(outbinding);
					}
				}
			}

			// remove them from the context
			for (Iterator iter = outbindings.iterator(); iter.hasNext();) {
				OutBinding outbinding = (OutBinding) iter.next();
				if (_currentContext.contains(outbinding.getBinding()))
					_currentContext.remove(outbinding.getBinding());
			}
			
			// fire the bindings with the new context 
			for (Iterator iter = outbindings.iterator(); iter.hasNext();) {
				OutBinding binding = (OutBinding) iter.next();
				if (binding.getStubExit() != null) {
					incrementHitCount(binding.getStubExit());
					pushPathNode((PathNode) binding.getStubExit().getTarget(), outbindings.size() > 1);
				}
			}

			// Connects
			visitOnlySuccIfExists(end);
		}
	}

	/**
	 * Process a path node in the given environment.
	 * 
	 * @param env
	 *            the environment
	 * @param visit
	 *            the path node to process
	 * @throws TraversalException
	 *             fatal error throw exceptions
	 */
	protected void processNode(UcmEnvironment env, TraversalVisit visit) throws TraversalException {
		PathNode pn = (PathNode) visit.getVisitedElement();
		_currentContext = new Vector();
		_currentContext.addAll(visit.getContext());
		
		trackVisit(pn);

		if (pn instanceof StartPoint) {
			StartPoint start = (StartPoint) pn;
			processStartPoint(env, start);
		} else if (pn instanceof EndPoint) {
			EndPoint end = (EndPoint) pn;
			processEndPoint(env, end);
		} else if (pn instanceof OrFork) {
			OrFork orfork = (OrFork) pn;
			processOrFork(env, orfork);
		} else if (pn instanceof AndFork) {
			AndFork andfork = (AndFork) pn;
			processAndFork(env, andfork);
		} else if (pn instanceof OrJoin) {
			OrJoin orjoin = (OrJoin) pn;
			processOrJoin(env, orjoin);
		} else if (pn instanceof AndJoin) {
			AndJoin andjoin = (AndJoin) pn;
			processAndJoin(env, andjoin);
		} else if (pn instanceof RespRef) {
			RespRef resp = (RespRef) pn;
			processRespRef(env, resp);
		} else if (pn instanceof EmptyPoint || pn instanceof DirectionArrow) {
			processEmptyPoint(env, pn);
		} else if (pn instanceof WaitingPlace) { // includes timer
			WaitingPlace waitingPlace = (WaitingPlace) pn;
			processWaitingPlaceAndTimer(env, waitingPlace);
		} else if (pn instanceof Stub) {
			Stub stub = (Stub) pn;
			processStub(env, stub);
		} else if (pn instanceof Connect) {
			Connect connect = (Connect) pn;
			processConnect(env, connect);
		} else {
			if (pn == null)
				_error = "unknown path node!";
			else
				_error = "unknown path node: " + pn.toString();
		}
	}

	/**
	 * Processes an or fork.
	 * 
	 * @param env
	 * @param orfork
	 * @throws TraversalException
	 */
	protected void processOrFork(UcmEnvironment env, OrFork orfork) throws TraversalException {
		// TODO: Semantic variation: All true branches? First only? Error if multiple true? If multiple, in sequence or parallel?

		for (Iterator iter = orfork.getSucc().iterator(); iter.hasNext();) {
			NodeConnection nc = (NodeConnection) iter.next();
			try {
				Object result = ScenarioUtils.evaluate(nc.getCondition(), env);
				if (Boolean.TRUE.equals(result)) {
					visitNodeConnection(nc);
					return;
				} 
			} catch (IllegalArgumentException e) {
				throw new TraversalException(e.getMessage(), e);
			}
		}

		_warnings.add(new TraversalWarning("Traversal blocked at Or Fork \"" + orfork.getName() + " (" + orfork.getId() + ")\", where no fork condition evaluates to true.",orfork));
	}

	/**
	 * Processes an or join
	 * 
	 * @param env
	 * @param orjoin
	 * @throws TraversalException
	 */
	protected void processOrJoin(UcmEnvironment env, OrJoin orjoin) throws TraversalException {
		visitOnlySucc(orjoin);
	}

	/**
	 * Processes a responsibility.
	 * 
	 * @param env
	 * @param resp
	 * @throws TraversalException
	 */
	protected void processRespRef(UcmEnvironment env, RespRef resp) throws TraversalException {
		// TODO: Repetition count

		try {
			ScenarioUtils.evaluate(resp, env);
		} catch (IllegalArgumentException e) {
			throw new TraversalException(e.getMessage(), e);
		}

		visitOnlySucc(resp);
	}

	/**
	 * Proccesses a start point
	 * 
	 * @param env
	 * @param start
	 * @throws TraversalException
	 */
	protected void processStartPoint(UcmEnvironment env, StartPoint start) throws TraversalException {
		// TODO: semantic variation: if the pre-condition is invalid, should we proceed anyways?
		if (testCondition(env, start.getPrecondition(), Boolean.TRUE, "Precondition to Start Point \"" + start.getName() + "\" (" + start.getId()
				+ ") did not evaluate to true.")) {

			visitOnlySucc(start);
		}
	}

	/**
	 * Processes a stub.
	 * 
	 * @param env
	 * @param stub
	 * @throws TraversalException
	 */
	protected void processStub(UcmEnvironment env, Stub stub) throws TraversalException {
		boolean b = false;
		// TODO: Semantic variation: All true branches? First only? Error if multiple true? If multiple, in sequence or parallel?
		for (Iterator iter = stub.getBindings().iterator(); iter.hasNext() && !b;) {
			PluginBinding binding = (PluginBinding) iter.next();

			try {
				Object result = ScenarioUtils.evaluate(binding.getPrecondition(), env);
				if (Boolean.TRUE.equals(result)) {
					for (Iterator iterator = binding.getIn().iterator(); iterator.hasNext();) {
						InBinding inb = (InBinding) iterator.next();
						if (inb.getStartPoint() != null) {
							if (!_currentContext.contains(binding))
								_currentContext.add(binding);
							pushPathNode(inb.getStartPoint(), false);
						}
						b = true;
					}
					if (binding.getIn().size() == 0)
						_warnings.add(new TraversalWarning("No in bindings are defined for the Stub->Plugin relationship \"" + stub.getName() + " (" + stub.getId() + ") -> "
								+ binding.getPlugin().getName() + " (" + binding.getPlugin().getId() + ")\".", stub));
				}
			} catch (IllegalArgumentException e) {
				throw new TraversalException(e.getMessage(), e);
			}
		}

		if (!b) {
			// TODO: semantic variation : no plugins, what do we do?
			// if we don't find any valid plugins, only follow first out if it exists.
			if (stub.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) stub.getSucc().get(0);
				visitNodeConnection(nc);
			} else
				_warnings.add(new TraversalWarning("Unable to navigate to a plugin from Stub \"" + stub.getName() + " (" + stub.getId() + ")\"",stub));
		}
	}

	/**
	 * Processes a waiting place or timer (which is a waiting place)
	 * 
	 * @param env
	 * @param pn
	 * @throws TraversalException
	 */
	protected void processWaitingPlaceAndTimer(UcmEnvironment env, WaitingPlace pn) throws TraversalException {
		// only one out.
		NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
		try {
			Object result = ScenarioUtils.evaluate(nc.getCondition(), env);
			// not using default behaviour. want to make sure we are blocked
			if (nc.getCondition()==null) result=Boolean.FALSE;
			
			if (Boolean.TRUE.equals(result)) {
				visitNodeConnection(nc);
			} else {
				
				if (pn instanceof Timer &&  pn.getSucc().size()==2) {
					
					//TODO: Semantic Variation. What do we do if both conditions are true at the same time?
					nc =  (NodeConnection) pn.getSucc().get(1);
					result = ScenarioUtils.evaluate(nc.getCondition(), env);
					// not using default behaviour. want to make sure we are blocked
					if (nc.getCondition()==null) result=Boolean.FALSE;

					// if we can take the timeout path
					if (Boolean.TRUE.equals(result))
						visitNodeConnection(nc);
					else
						addToWaitingList(pn);
						
				}
				else
					addToWaitingList(pn);
			}
		} catch (IllegalArgumentException e) {
			throw new TraversalException(e.getMessage(), e);
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
		_toVisit.push(new TraversalVisit(pn, _currentContext));
	}
	

	/**
	 * Initialize using {@link QDefaultScenarioTraversal} , run {@link #process(UcmEnvironment, Vector, Vector)} and prepare {@link RTraversalSequence}
	 * 
	 * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
	 */
	public QueryResponse runImpl(QueryRequest q) {
		_visited = new Vector();
		_toVisit = new Stack();
		_results = new HashMap();
		_waitList = new LinkedList();
		_error = null;
		_warnings = new Vector();

		if (((QDefaultScenarioTraversal) q).getStartPathNodes() != null) {
			Vector startPoints = ((QDefaultScenarioTraversal) q).getStartPathNodes();
			Vector endPoints = ((QDefaultScenarioTraversal) q).getEndPathNodes();
			process(((QDefaultScenarioTraversal) q).getEnvironment(), startPoints, endPoints);
		}

		// Return a response containing the visited node list
		RTraversalSequence r = new RTraversalSequence();
		r.setVisited(_visited);
		r.setError(_error);
		r.setWarnings(_warnings);
		r.setResults(_results);
		return r;
	}

	/**
	 * Initialize our stack with the given start points.
	 * 
	 * @param startPoints
	 *            Vector of ScenarioStartPoints or StartPoints
	 */
	protected void seedAlgorithm(Vector startPoints) {
		for (int i = startPoints.size() - 1; i >= 0; i--) {
			_currentContext = new Vector();
			if (startPoints.get(i) instanceof StartPoint)
				pushPathNode((StartPoint) startPoints.get(i), true);
			else if (startPoints.get(i) instanceof ScenarioStartPoint) {
				if (((ScenarioStartPoint) startPoints.get(i)).isEnabled())
					pushPathNode(((ScenarioStartPoint) startPoints.get(i)).getStartPoint(), true);
			} else {
				_error = "Illegal use of query.";
				return;
			}
		}
	}

	/**
	 * Helper method that tests to see if a condition evaluates to what is expected.
	 * 
	 * @param env
	 *            the environment in which the condition is to be evaluated
	 * @param cond
	 *            the condition
	 * @param expected
	 *            the expected result (Boolean.TRUE or Boolean.FALSE)
	 * @param errorMessage
	 *            the error message to be added to the warnings if is not what is expected
	 * @return true if is what is expected, false otherwise.
	 * @throws TraversalException
	 *             if a parsing error occurs
	 */
	protected boolean testCondition(UcmEnvironment env, Condition cond, Boolean expected, String errorMessage) throws TraversalException {
		try {
			Object result = ScenarioUtils.evaluate(cond, env);
			if (!expected.equals(result)) {
				_warnings.add(new TraversalWarning(errorMessage, cond.eContainer()));
				return false;
			}
		} catch (IllegalArgumentException e) {
			throw new TraversalException(e.getMessage(), e);
		}

		return true;
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

		if (count >= MAXVISITCOUNT) {
			throw new TraversalException("Infinite loop detected on " + o.toString());
		}
	}

	/**
	 * Verify that the expected end points match the reached end points.
	 * 
	 * @param expectedEndPoints
	 *            Vector of ScenarioEndPoints or EndPoints
	 * @param reachedEndPoints
	 *            Vector of EndPoints
	 */
	protected void verifyEndPoints(Vector expectedEndPoints, Vector reachedEndPoints) {
		for (Iterator iter = expectedEndPoints.iterator(); iter.hasNext();) {
			EObject obj = (EObject) iter.next();
			EndPoint pt = null;
			if (obj instanceof EndPoint)
				pt = (EndPoint) obj;
			else if (obj instanceof ScenarioEndPoint) {
				if (((ScenarioEndPoint) obj).isEnabled())
					pt = ((ScenarioEndPoint) obj).getEndPoint();
			}

			if (pt != null && !reachedEndPoints.contains(pt)) {
				_warnings.add(new TraversalWarning("Scenario should have reached end point: " + pt.toString(),pt));
			} else {
				// so that we can find multiple instances
				reachedEndPoints.remove(pt);
			}
		}
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
		pushPathNode((PathNode) nc.getTarget(), newThread);
	}

	/**
	 * Visit the next path node. Should only have one outgoing link.
	 * 
	 * @param pn
	 * @throws TraversalException
	 */
	protected void visitOnlySucc(PathNode pn) throws TraversalException {
		if (pn.getSucc().size() == 1) {
			NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
			visitNodeConnection(nc);
		} else
			_error = "Traversal error.";
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