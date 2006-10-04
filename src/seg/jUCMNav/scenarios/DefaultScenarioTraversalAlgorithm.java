package seg.jUCMNav.scenarios;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.DefaultScenarioTraversal;
import seg.jUCMNav.model.util.modelexplore.queries.DefaultScenarioTraversal.QDefaultScenarioTraversal;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import urncore.Condition;

/**
 * This class implements the default UCM Scenario traversal algorithm
 * 
 * @author jkealey
 * 
 */
public class DefaultScenarioTraversalAlgorithm {

	protected HashMap results;
	protected UcmEnvironment env;
	protected ScenarioDef scenario;

	public DefaultScenarioTraversalAlgorithm(UcmEnvironment env, ScenarioDef scenario) {

		init(env, scenario);
	}

	public void init(UcmEnvironment env, ScenarioDef scenario) {
		this.env = env;
		this.scenario = scenario;
		results = new HashMap();
	}

	protected void traverse_Initializations(ScenarioDef root) throws TraversalException {
		if (root==scenario) {
			env.clearValuations();
		}
		for (Iterator iter = root.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			traverse_Initializations(scenario);
		}
		
		for (Iterator iter = root.getInitializations().iterator(); iter.hasNext();) {
			Initialization init = (Initialization) iter.next();
			ScenarioUtils.evaluate(init.getVariable().getName() + "=" + init.getValue() + ";", env, true);
		}
	}
	protected void traverse_Preconditions(ScenarioDef root) throws TraversalException {
		for (Iterator iter = root.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			traverse_Preconditions(scenario);
		}
		
		
		for (Iterator iter = root.getPreconditions().iterator(); iter.hasNext();) {
			Condition cond = (Condition) iter.next();
			
			if (cond.getExpression()!=null && cond.getExpression().length()>0) {
				
				Object res=null;
				try {
				res = (Object) ScenarioUtils.evaluate(cond.getExpression(), env, false);
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
				if (res instanceof Boolean) { 
					if (Boolean.FALSE.equals(res))
						throw new TraversalException("Precondition \"" + cond.getLabel() + "\" is false.\n(\"" + cond.getExpression() + "\" evaluates to false.)\n");
				}
				else
					throw new TraversalException("Unexpected result returned");
				
			}

		}

	}

	protected void traverse_Postconditions(ScenarioDef root) throws TraversalException {
		for (Iterator iter = root.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			traverse_Postconditions(scenario);
		}
		
		for (Iterator iter = root.getPostconditions().iterator(); iter.hasNext();) {
			Condition cond = (Condition) iter.next();
			
			if (cond.getExpression()!=null && cond.getExpression().length()>0) {
				
				Object res=null;
				try {
				res = (Object) ScenarioUtils.evaluate(cond.getExpression(), env, false);
				} catch (IllegalArgumentException e) {
					throw new TraversalException(e.getMessage(), e);
				}
				if (res instanceof Boolean) { 
					if (Boolean.FALSE.equals(res))
						throw new TraversalException("Postcondition \"" + cond.getLabel() + "\" is false.\n(\"" + cond.getExpression() + "\" evaluates to false.)\n");
				}
				else
					throw new TraversalException("Unexpected result returned");
				
			}

		}

	}

	public void traverse() throws TraversalException {
		traverse_Initializations(scenario);
		traverse_Preconditions(scenario);

		QDefaultScenarioTraversal qry = new DefaultScenarioTraversal().new QDefaultScenarioTraversal(env, scenario.getStartPoints());
		DefaultScenarioTraversal.RTraversalSequence resp = (DefaultScenarioTraversal.RTraversalSequence) GraphExplorer.getInstance().run(qry);
		//Vector vReachable = resp.getVisited();
		results = resp.getResults();
		
		if (resp.getError()!=null)
			throw new TraversalException(resp.getError());

		if (resp.getWarnings()!=null && resp.getWarnings().size()>0) {
			StringBuilder b = new StringBuilder();
			b.append("** WARNINGS **\n");
			for (Iterator iter = resp.getWarnings().iterator(); iter.hasNext();) {
				Object o = (Object) iter.next();
				b.append(o.toString() + "\n\n");
			}
			throw new TraversalException(b.toString());
		}
		
//
//		for (Iterator iter = scenario.getStartPoints().iterator(); iter.hasNext();) {
//			ScenarioStartPoint element = (ScenarioStartPoint) iter.next();
//			// TraversalResult res =
//			// createTraversalResults(element.getStartPoint());
//			// res.incrementHitCount();
//
//			// run the query of reachable nodes.
//			QFindReachableNodes qry = new ReachableNodeFinder().new QFindReachableNodes(element.getStartPoint(), null, QFindReachableNodes.DIRECTION_FORWARD);
//			ReachableNodeFinder.RReachableNodes resp = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qry);
//			Vector vReachable = resp.getNodes();
//
//			for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
//				PathNode pn = (PathNode) iterator.next();
//				TraversalResult res = createTraversalResults(pn);
//				res.incrementHitCount();
//			}
//
//			vReachable = resp.getConnections();
//			for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
//				NodeConnection nc = (NodeConnection) iterator.next();
//				TraversalResult res = createTraversalResults(nc);
//				res.incrementHitCount();
//			}
//
//		}

		traverse_Postconditions(scenario);
	}

	protected TraversalResult createTraversalResults(EObject obj) {
		if (results == null) {
			results = new HashMap();
		}

		if (!results.containsKey(obj)) {
			results.put(obj, new TraversalResult());
		}
		return (TraversalResult) results.get(obj);
	}

	protected TraversalResult getTraversalResults(EObject obj) {
		if (results.containsKey(obj)) {
			TraversalResult count = (TraversalResult) results.get(obj);
			return count;
		} else
			return null;
	}

	protected void clearTraversalResults() {
		results.clear();
	}

}
