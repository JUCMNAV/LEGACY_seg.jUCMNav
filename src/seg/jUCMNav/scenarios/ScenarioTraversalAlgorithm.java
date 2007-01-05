package seg.jUCMNav.scenarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.msc.MscTraversalListener;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.DefaultScenarioTraversal;
import seg.jUCMNav.model.util.modelexplore.queries.DefaultScenarioTraversal.QDefaultScenarioTraversal;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import urncore.Condition;

/**
 * This class invokes the default UCM Scenario traversal algorithm. Its only responsibilities are environment initialization, pre/post condition verifications
 * and gathering the traversal results from the actual algorithm.
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalAlgorithm {

	// Environment in which to run scenario.
	protected UcmEnvironment env;

	// HashMap of EObject -> TraversalResult
	protected HashMap results;

	// Scenario to be run.
	protected ScenarioDef scenario;

	// Vector of EObjects that are visited. 
	protected Vector visited;

	// Vector of Strings
	protected Vector warnings;
	
	// list of ITraversalListeners
	protected ScenarioTraversalListenerList traversalListeners;

	/**
	 * Initializes the traversal algorithm. Can be re-used after with {@link #init(UcmEnvironment, ScenarioDef)}
	 * 
	 * @param env
	 *            environment in which to run the scenario
	 * @param scenario
	 *            the scenario to be run.
	 */
	public ScenarioTraversalAlgorithm(UcmEnvironment env, ScenarioDef scenario) {
		init(env, scenario);
	}

	/**
	 * Erase any traversal results we may have obtained.
	 */
	protected void clearTraversalResults() {
		results.clear();
	}

	/**
	 * Add a result to the resullts HashMap for this object.
	 * 
	 * @param obj
	 *            the object for which to create a result
	 * @return the new result or the existing one if a result already exists for this object.
	 */
	protected TraversalResult createTraversalResults(EObject obj) {
		if (results == null) {
			results = new HashMap();
		}

		if (!results.containsKey(obj)) {
			results.put(obj, new TraversalResult());
		}
		return (TraversalResult) results.get(obj);
	}

	/**
	 * Returns the traversal result for a certain element.
	 * 
	 * @param obj
	 *            the elemetn
	 * @return the traversal result or null if it does not exist.
	 */
	protected TraversalResult getTraversalResults(EObject obj) {
		if (results.containsKey(obj)) {
			TraversalResult count = (TraversalResult) results.get(obj);
			return count;
		} else
			return null;
	}

	/**
	 * Initialize the algorithm.
	 * 
	 * @param env
	 *            the environment in which to run the scenario
	 * @param scenario
	 *            the scenario to be executed.
	 */
	public void init(UcmEnvironment env, ScenarioDef scenario) {
		this.env = env;
		this.scenario = scenario;
		results = new HashMap();
		warnings = new Vector();
		
		if (ScenarioUtils.getDefinedStartPoints(scenario).size()==0) {
			warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversalAlgorithm.NoStartPointsDefined"), scenario, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
		}
		

		Vector listeners = new Vector();

		// TODO: Load a list of listeners from extension point and preference store. 
		listeners.add(new MscTraversalListener());
		
		traversalListeners = new ScenarioTraversalListenerList(listeners, warnings);

	}

	/**
	 * Execute the scenario in its environment. - Perform initializations - Verify preconditions - Execute the traversal algorithm - Verify postconditions -
	 * Temporary: Show warnings. Caller should build warnings using {@link #getWarnings()}
	 * 
	 * @throws TraversalException
	 *             fatal errors are returned as traversal exceptions.
	 */
	public void traverse() throws TraversalException {

		// initialize all variables recursively
		traverse_Initializations(scenario);

		traversalListeners.traversalStarted(env, scenario);
		
		// test preconditions in a second step.
		traverse_Preconditions(scenario);

		// TODO: Load proper algorithm from plugin extensions / properties.
		// execute the other algorithm
		QDefaultScenarioTraversal qry = new DefaultScenarioTraversal().new QDefaultScenarioTraversal(env, ScenarioUtils.getDefinedStartPoints(scenario),
				ScenarioUtils.getDefinedEndPoints(scenario), traversalListeners);
		DefaultScenarioTraversal.RTraversalSequence resp = (DefaultScenarioTraversal.RTraversalSequence) GraphExplorer.getInstance().run(qry);

		// memorize our results.
		results = resp.getResults();
		visited = resp.getVisited();

//		if (resp.getError() != null)
//			throw new TraversalException(resp.getError());

		if (resp.getWarnings() != null && resp.getWarnings().size() > 0)
			warnings.addAll(resp.getWarnings());

		traverse_Postconditions(scenario);

		traversalListeners.traversalEnded(env, scenario);
		
		SyntaxChecker.refreshProblemsView(warnings);
		
		if (resp.getError() != null)
			throw new TraversalException(resp.getError());


	}


	/**
	 * Initializes the environment with the given scenario, recursively.
	 *  - Clear default valuations if root == the scenario being executed. - Set values to what we have defined in the included scenarios. - Set values as
	 * defined in this scenario.
	 * 
	 * @param root
	 *            the scenario for which to incorporate the variable initializations.
	 * @throws TraversalException
	 *             fatal errors are returned as traversal exceptions.
	 */
	protected void traverse_Initializations(ScenarioDef root) throws TraversalException {
		if (root == scenario) {
			env.clearValuations();
		}
		
		
		for (Iterator iter = ScenarioUtils.getDefinedInitializations(root).iterator(); iter.hasNext();) {
			Initialization init = (Initialization) iter.next();
			try {
				ScenarioUtils.evaluate(init.getVariable().getName() + "=" + init.getValue() + ";", env, true); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception ex) {
				if (init.getVariable().getEnumerationType()!=null) {
					warnings.add(new TraversalWarning(init.getVariable().getEnumerationType().getName() + Messages.getString("DefaultScenarioTraversalAlgorithm.ColonThisEnumerationDoesNotContainAValueNamed") + init.getValue())); //$NON-NLS-1$
				}
				else {
					warnings.add(new TraversalWarning(ex.getMessage(), init, IMarker.SEVERITY_ERROR));
				}
			}
		}
	}

	/**
	 * Recursively verify the postconditions.
	 * 
	 * @param root
	 *            the scenario for which to verify the postconditions in the environment.
	 * 
	 * @throws TraversalException
	 *             fatal errors are returned as traversal exceptions.
	 */
	protected void traverse_Postconditions(ScenarioDef root) throws TraversalException {

		for (Iterator iter = ScenarioUtils.getDefinedPostconditions(root).iterator(); iter.hasNext();) {
			Condition cond = (Condition) iter.next();

			if (cond.getExpression() != null && cond.getExpression().length() > 0) {

				Object res = null;
				try {
					res = (Object) ScenarioUtils.evaluate(cond.getExpression(), env, false);
					traversalListeners.conditionEvaluated(ScenarioUtils.isEmptyCondition(cond)?"true":cond.getExpression(), Boolean.TRUE.equals(res));
					
					if (res instanceof Boolean) {
						if (Boolean.FALSE.equals(res)) {
							
							TraversalWarning warning = new TraversalWarning(Messages.getString("DefaultScenarioTraversalAlgorithm.Postcondition") + URNNamingHelper.getName(cond) + Messages.getString("DefaultScenarioTraversalAlgorithm.IsFalse") + cond.getExpression() + Messages.getString("DefaultScenarioTraversalAlgorithm.EvaluatesToFalse"), scenario, IMarker.SEVERITY_ERROR); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							warning.setCondition(cond);
							warnings.add(warning);
						}
					} else
						throw new TraversalException(Messages.getString("DefaultScenarioTraversalAlgorithm.UnexpectedResult")); //$NON-NLS-1$
				} catch (IllegalArgumentException e) {
					//throw new TraversalException(e.getMessage(), e);
					warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversalAlgorithm.ScenarioPostcondition") + " " + URNNamingHelper.getName(cond) + ": " + e.getMessage(), cond, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}


			}

		}

	}

	/**
	 * Recursively verify the preconditions.
	 * 
	 * @param root
	 *            the scenario for which to verify the preconditions in the environment.
	 * @throws TraversalException
	 *             fatal errors are returned as traversal exceptions.
	 */
	protected void traverse_Preconditions(ScenarioDef root) throws TraversalException {
		for (Iterator iter = ScenarioUtils.getDefinedPreconditions(root).iterator(); iter.hasNext();) {
			Condition cond = (Condition) iter.next();

			if (cond.getExpression() != null && cond.getExpression().length() > 0) {

				Object res = null;
				try {
					res = (Object) ScenarioUtils.evaluate(cond.getExpression(), env, false);
					traversalListeners.conditionEvaluated(ScenarioUtils.isEmptyCondition(cond)?"true":cond.getExpression(), Boolean.TRUE.equals(res));
					if (res instanceof Boolean) {
						if (Boolean.FALSE.equals(res)) {
							TraversalWarning warning = new TraversalWarning(Messages.getString("DefaultScenarioTraversalAlgorithm.Precondition") + URNNamingHelper.getName(cond) + Messages.getString("DefaultScenarioTraversalAlgorithm.IsFalse") + cond.getExpression() + Messages.getString("DefaultScenarioTraversalAlgorithm.EvaluatesToFalse"), scenario,IMarker.SEVERITY_ERROR); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							warning.setCondition(cond);
							warnings.add(warning);
						}
					} else
						throw new TraversalException(Messages.getString("DefaultScenarioTraversalAlgorithm.UnexpectedResult")); //$NON-NLS-1$
					
				} catch (IllegalArgumentException e) {
					//throw new TraversalException(e.getMessage(), e);
					warnings.add(new TraversalWarning(Messages.getString("DefaultScenarioTraversalAlgorithm.ScenarioPrecondition") + " " + URNNamingHelper.getName(cond) + ": " + e.getMessage(), cond, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}

			}

		}

	}

	/**
	 * The warnings accumulated during the execution.  
	 * @return A vector of String instances.
	 */
	public Vector getWarnings() {
		return warnings;
	}

	/**
	 * The results of the traversal. 
	 * @return A HashMap of EObject -> TraversalResult. 
	 */
	public HashMap getResults() {
		return results;
	}

}
