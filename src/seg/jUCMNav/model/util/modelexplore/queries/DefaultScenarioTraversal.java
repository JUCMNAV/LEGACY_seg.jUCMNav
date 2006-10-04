package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
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
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

/**
 * Query processor representing the sequence of elements traversed by the
 * scenario traversal algorithm.
 * 
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class DefaultScenarioTraversal extends AbstractQueryProcessor implements IQueryProcessorChain {

	private Vector _visited;
	private String _error;
	private Vector _warnings;
	private Stack _toVisit;
	private Queue _waitList;
	private HashMap _results;

	public DefaultScenarioTraversal() {
		this._answerQueryTypes = new String[] { QueryObject.DEFAULTSCENARIOTRAVERSAL };
	}

	/**
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
			EList startPoints = ((QDefaultScenarioTraversal) q).getStartPathNodes();
			process(((QDefaultScenarioTraversal) q).getEnvironment(), startPoints);
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
	 * @param env
	 *            the ucm environment in which to execute the code.
	 * @param startPoints
	 *            the list of start points to initialize the traversal.
	 */
	private void process(UcmEnvironment env, EList startPoints) {

		for (int i = startPoints.size() - 1; i >= 0; i--) {
			if (startPoints.get(i) instanceof StartPoint)
				_toVisit.push(startPoints.get(i));
			else if (startPoints.get(i) instanceof ScenarioStartPoint)
				_toVisit.push(((ScenarioStartPoint) startPoints.get(i)).getStartPoint());
			else {
				_error = "Illegal use of query.";
				return;
			}
		}

		try {
			int lastAttempt = Integer.MAX_VALUE;

			while ((_toVisit.size() > 0 || _waitList.size() > 0) && _error == null) {
				if (_waitList.size() > 0 && _toVisit.size() < lastAttempt) {
					int sz = _waitList.size();

					processNode(env, (PathNode) _waitList.poll());

					if (sz == _waitList.size()) // was not able to make any
						// progress.
						lastAttempt = _toVisit.size();
					else
						// made progress
						lastAttempt = Integer.MAX_VALUE;

					if (_error != null)
						break;
				}

				if (_toVisit.size() > 0) {

					// if we hit another path that finds a node that is already
					// on the waiting list, process it immediately, and remove
					// it from the to process list.
					while (_waitList.contains(_toVisit.peek())) {
						_waitList.remove(_toVisit.peek());
					}

					processNode(env, (PathNode) _toVisit.pop());

				} else {
					if (lastAttempt == 0 && _waitList.size() > 0) {
						if (_waitList.peek() instanceof Timer && ((Timer) _waitList.peek()).getSucc().size() == 2) {
							NodeConnection nc = (NodeConnection) ((Timer) _waitList.poll()).getSucc().get(1);
							incrementHitCount(nc);
							_toVisit.push(nc.getTarget());
						} else {
							_warnings.add("Traversal blocked on " + _waitList.peek().toString());
							
							// kick out of waiting list because this is just a warning
							_waitList.poll();
						}
						
					}
				}
			}
		} catch (Exception e) {
			_error = "Exception occurred:\n" + e.getMessage();
		}

	}

	private void processNode(UcmEnvironment env, PathNode pn) throws TraversalException {
		trackVisit(pn);

		if (pn instanceof StartPoint) {
			StartPoint start = (StartPoint) pn;

			Condition pre = start.getPrecondition();
			if (pre != null && pre.getExpression() != null && pre.getExpression().length() > 0) {
				try {
					Object result = ScenarioUtils.evaluate(pre.getExpression(), env, false);
					if (!Boolean.TRUE.equals(result)) {
						_error = "Precondition to Start Point \"" + pn.getName() + "\" (" + pn.getId() + ") did not evaluate to true.";
					}
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
			}

			if (pn.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			} else
				_error = "Traversal error.";
		} else if (pn instanceof EndPoint) {
			EndPoint end = (EndPoint) pn;

			Condition post = end.getPostcondition();
			if (post != null && post.getExpression() != null && post.getExpression().length() > 0) {
				try {
					Object result = ScenarioUtils.evaluate(post.getExpression(), env, false);
					if (!Boolean.TRUE.equals(result)) {
						_error = "Postcondition to End Point \"" + pn.getName() + " (" + pn.getId() + ")\" did not evaluate to true.";
					}
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
			}

			// TODO: filter by "instance" that launched the start.
			for (Iterator iter = end.getOutBindings().iterator(); iter.hasNext();) {
				OutBinding binding = (OutBinding) iter.next();
				if (binding.getStubExit() != null) {
					incrementHitCount(binding.getStubExit());
					_toVisit.push(binding.getStubExit().getTarget());
				}
			}

			// Connects
			for (Iterator iter = end.getSucc().iterator(); iter.hasNext();) {
				NodeConnection nc = (NodeConnection) iter.next();
				incrementHitCount(nc);
				_toVisit.push(nc.getTarget());
			}
		} else if (pn instanceof OrFork) {
			for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
				NodeConnection nc = (NodeConnection) iter.next();
				if (nc.getCondition() == null || nc.getCondition().getExpression() == null || nc.getCondition().getExpression().length() == 0) {
					trackVisit(nc);
					_toVisit.push(nc.getTarget());
					return;
				} else {
					try {
						Object result = ScenarioUtils.evaluate(nc.getCondition().getExpression(), env, false);
						if (Boolean.TRUE.equals(result)) {
							trackVisit(nc);
							_toVisit.push(nc.getTarget());
							return;
						}
					} catch (IllegalArgumentException e) {
						throw new TraversalException(e.getMessage(), e);
					}
				}
			}

			_warnings.add("Traversal blocked at Or Fork \"" + pn.getName() + " (" + pn.getId() + ")\", where no fork condition evalutes to true.");
		} else if (pn instanceof AndFork) {
			for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
				NodeConnection nc = (NodeConnection) iter.next();
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			}

		} else if (pn instanceof OrJoin) {
			if (pn.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			} else
				_error = "Traversal error.";
		} else if (pn instanceof AndJoin) {

			if (pn.getSucc().size() == 1) {
				NodeConnection out = (NodeConnection) pn.getSucc().get(0);

				for (Iterator iter = pn.getPred().iterator(); iter.hasNext();) {
					NodeConnection in = (NodeConnection) iter.next();
					if (getHitCount(out) + 1 > getHitCount(in)) {

						// Don't add doubles.
						if (!_waitList.contains(pn))
							_waitList.offer(pn);
						return;
					}
				}

				trackVisit(out);
				_toVisit.push(out.getTarget());

			} else
				_error = "Traversal error.";

		} else if (pn instanceof RespRef) {
			RespRef resp = (RespRef) pn;
			// TODO: Repetition count
			if (resp.getRespDef().getExpression() != null && resp.getRespDef().getExpression().length() > 0) {
				try {
					ScenarioUtils.evaluate(resp.getRespDef().getExpression(), env, true);
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
			}

			if (pn.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			} else
				_error = "Traversal error.";
		} else if (pn instanceof EmptyPoint || pn instanceof DirectionArrow) {
			if (pn.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			} else
				_error = "Traversal error.";
		} else if (pn instanceof WaitingPlace) { // includes timer
			// only one out.
			NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
			if (nc.getCondition() == null || nc.getCondition().getExpression() == null || nc.getCondition().getExpression().length() == 0) {
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
				return;
			} else {
				try {
					Object result = ScenarioUtils.evaluate(nc.getCondition().getExpression(), env, false);
					if (Boolean.TRUE.equals(result)) {
						trackVisit(nc);
						_toVisit.push(nc.getTarget());
						return;
					} else {
						_waitList.offer(pn);
						return;
					}
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
			}

		} else if (pn instanceof Stub) {
			Stub stub = (Stub) pn;
			for (Iterator iter = stub.getBindings().iterator(); iter.hasNext();) {
				PluginBinding binding = (PluginBinding) iter.next();

				if (binding.getPrecondition() == null || binding.getPrecondition().getExpression() == null
						|| binding.getPrecondition().getExpression().length() == 0) {
					for (Iterator iterator = binding.getIn().iterator(); iterator.hasNext();) {
						InBinding inb = (InBinding) iterator.next();
						if (inb.getStartPoint() != null)
							_toVisit.push(inb.getStartPoint());
					}
					return;
				} else {
					try {
						Object result = ScenarioUtils.evaluate(binding.getPrecondition().getExpression(), env, false);
						if (Boolean.TRUE.equals(result)) {
							for (Iterator iterator = binding.getIn().iterator(); iterator.hasNext();) {
								InBinding inb = (InBinding) iterator.next();
								if (inb.getStartPoint() != null)
									_toVisit.push(inb.getStartPoint());
							}
							if(binding.getIn().size()==0) 
								_warnings.add("No in bindings are defined for the Stub->Plugin relationship \""+stub.getName() + " (" + stub.getId() + ") -> " + binding.getPlugin().getName() + " (" + binding.getPlugin().getId() + ")\".");
							return;
						}
					} catch (IllegalArgumentException e) {
						throw new TraversalException(e.getMessage(), e);
					}
				}
			}

			// if we don't find any valid plugins, only follow first out if it
			// exists.
			if (stub.getSucc().size() == 1) {
				NodeConnection nc = (NodeConnection) stub.getSucc().get(0);
				trackVisit(nc);
				_toVisit.push(nc.getTarget());
			} else
				_warnings.add("Unable to navigate to a plugin from Stub \"" + stub.getName() + " (" + stub.getId() + ")\"");

		} else if (pn instanceof Connect) {
			NodeConnection nc = (NodeConnection) pn.getSucc().get(0);
			incrementHitCount(nc);
			if (nc.getTarget() instanceof StartPoint)
				_toVisit.push(nc.getTarget());
			else if (nc.getTarget() instanceof WaitingPlace) {
				// skip over because processNode doesn't look at incoming
				// connections to see if it can continue
				// must be forced
				while (_waitList.contains(nc.getTarget())) {
					_waitList.remove(nc.getTarget());
				}
				incrementHitCount(nc.getTarget());
				nc = (NodeConnection) nc.getTarget().getSucc().get(0);
				incrementHitCount(nc);
				_toVisit.push(nc.getTarget());

			} else
				_error = "Traversal error.";

		} else {
			if (pn == null)
				_error = "unknown path node!";
			else
				_error = "unknown path node: " + pn.toString();
		}
	}

	private void trackVisit(EObject o) throws TraversalException {
		_visited.add(o);

		int count = incrementHitCount(o);

		if (count >= 1000) {
			throw new TraversalException("Infinite loop detected on " + o.toString());
		}
	}

	private int incrementHitCount(EObject o) {
		TraversalResult result;
		if (!_results.containsKey(o))
			_results.put(o, new TraversalResult());

		result = (TraversalResult) _results.get(o);
		result.incrementHitCount();

		return result.getHitCount();
	}

	private int getHitCount(EObject o) {
		TraversalResult result;
		if (!_results.containsKey(o))
			_results.put(o, new TraversalResult());

		result = (TraversalResult) _results.get(o);

		return result.getHitCount();
	}

	public class QDefaultScenarioTraversal extends QueryRequest {
		// StartPoints
		private EList _StartPathNodes;
		private UcmEnvironment _env;

		/**
		 * 
		 * @param startNode
		 *            the starting point for traversal.
		 */
		public QDefaultScenarioTraversal(UcmEnvironment env, EList startNode) {
			this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
			_StartPathNodes = startNode;
			_env = env;

		}

		public EList getStartPathNodes() {
			return _StartPathNodes;
		}

		public UcmEnvironment getEnvironment() {
			return _env;
		}

	}

	public class RTraversalSequence extends QueryResponse {
		/* Data structure (query response) for passing a list of EObjects */
		private Vector visited;
		private String error;
		private Vector warnings;
		private HashMap results;

		public RTraversalSequence() {
			this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
		}

		/**
		 * @return Returns the nodes.
		 */
		public Vector getVisited() {
			return visited;
		}

		/**
		 * @param visited
		 *            The nodes / node connections to set.
		 */
		public void setVisited(Vector visited) {
			this.visited = visited;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public HashMap getResults() {
			return results;
		}

		public void setResults(HashMap results) {
			this.results = results;
		}

		public Vector getWarnings() {
			return warnings;
		}

		public void setWarnings(Vector warnings) {
			this.warnings = warnings;
		}

	}

}