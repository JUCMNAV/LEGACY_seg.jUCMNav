package seg.jUCMNav.scenarios;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.scenario.ScenarioDef;

/**
 * Contains a list of ITraversalListeners and the boilerplate code to notify them all.
 * 
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalListenerList implements ITraversalListener {

	protected Vector _listeners;
	protected Vector _warnings;

	public ScenarioTraversalListenerList(Vector listeners, Vector warnings) {
		this._listeners = listeners;
		this._warnings = warnings;
	}

	public void conditionEvaluated(String condition, boolean result) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.conditionEvaluated(condition, result);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void newThreadStarted(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.newThreadStarted(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void pathNodeAttempted(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.pathNodeAttempted(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void pathNodeBlocked(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.pathNodeBlocked(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}
	}

	public void pathNodeVisited(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.pathNodeVisited(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void threadDied(int threadId) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.threadDied(threadId);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}
	}

	public void threadSplit(int oldThreadID, List newThreadIDs) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.threadSplit(oldThreadID, newThreadIDs);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void threadsMerged(List oldThreadIDs, int newThreadID) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.threadsMerged(oldThreadIDs, newThreadID);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void traversalEnded(UcmEnvironment env, ScenarioDef scenario) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.traversalEnded(env, scenario);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void traversalStarted(UcmEnvironment env, ScenarioDef scenario) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.traversalStarted(env, scenario);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void pathNodeAborted(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.pathNodeAborted(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void timerTimeout(TraversalVisit visit) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.timerTimeout(visit);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}

	}

	public void codeExecuted(String code, UcmEnvironment env) {
		for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			ITraversalListener listener = (ITraversalListener) iter.next();
			try {
				listener.codeExecuted(code, env);

			} catch (Exception ex) {
				_warnings.add(new TraversalWarning(ex.toString()));
				ex.printStackTrace();
			}
		}
		
	}

}
