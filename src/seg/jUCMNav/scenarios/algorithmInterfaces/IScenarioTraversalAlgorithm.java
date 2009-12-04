package seg.jUCMNav.scenarios.algorithmInterfaces;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

public interface IScenarioTraversalAlgorithm {

    /**
     * Initialize the algorithm.
     * 
     * @param env
     *            the environment in which to run the scenario
     * @param scenario
     *            the scenario to be executed.
     */
    public abstract void init(UcmEnvironment env, ScenarioDef scenario);

    /**
     * Initialize the algorithm.
     * 
     * @param env
     *            the environment in which to run the scenario
     * @param group
     *            the scenario group to be executed.
     */
    public abstract void init(UcmEnvironment env, ScenarioGroup group);

    /**
     * Initialize the algorithm.
     * 
     * @param env
     *            the environment in which to run the scenario
     * @param ucmspec
     *            run all scenarios in ucmspec
     */
    public abstract void init(UcmEnvironment env, UCMspec ucmspec);

    /**
     * Adds a list of {@link ITraversalListener} to the current internal listener list.
     * 
     * @param newListeners
     *            the new listeners
     */
    public abstract void addListeners(Vector newListeners);

    /**
     * Erase any traversal results we may have obtained.
     */
    public void clearTraversalResults();

    /**
     * Returns the traversal result for a certain element.
     * 
     * @param obj
     *            the elemetn
     * @return the traversal result or null if it does not exist.
     */
    public TraversalResult getTraversalResults(EObject obj);

    /**
     * Execute the scenario in its environment. - Perform initializations - Verify preconditions - Execute the traversal algorithm - Verify postconditions -
     * Temporary: Show warnings. Caller should build warnings using {@link #getWarnings()}
     * 
     * @throws TraversalException
     *             fatal errors are returned as traversal exceptions.
     */
    public abstract void traverse() throws TraversalException;

    /**
     * The warnings accumulated during the execution.
     * 
     * @return A vector of String instances.
     */
    public abstract Vector getWarnings();

    /**
     * The results of the traversal.
     * 
     * @return A HashMap of EObject -> TraversalResult.
     */
    public abstract HashMap getResults();

}