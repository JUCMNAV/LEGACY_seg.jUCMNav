package seg.jUCMNav.model.util.modelexplore.queries.scenarioTraversal;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * Encapsulates traversal results, errors and warnings.
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalResponse extends QueryResponse {
    protected String error;
    protected HashMap results;
    /* Data structure (query response) for passing a list of EObjects */
    protected Vector visited;
    protected Vector warnings;

    /**
     * Create a new traversal response.
     * 
     */
    public ScenarioTraversalResponse() {
        this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
    }

    /**
     * 
     * @return the error message if an error occured, null if no error happened.
     */
    public String getError() {
        return error;
    }

    /**
     * 
     * @return a map between an {@link EObject} and its {@link TraversalResult}
     */
    public HashMap getResults() {
        return results;
    }

    /**
     * @return a list of {@link PathNode}s and {@link NodeConnection}s that were visisted.
     */
    public Vector getVisited() {
        return visited;
    }

    /**
     * 
     * @return a list of {@link TraversalWarning}s
     */
    public Vector getWarnings() {
        return warnings;
    }

    /**
     * 
     * @param error
     *            the error message if an error occured, null if no error happened.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 
     * @param results
     *            a map between an {@link EObject} and its {@link TraversalResult}
     */
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

    /**
     * 
     * @param warnings
     *            a list of {@link TraversalWarning}
     */
    public void setWarnings(Vector warnings) {
        this.warnings = warnings;
    }

}
