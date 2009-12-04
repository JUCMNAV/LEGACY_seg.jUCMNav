package seg.jUCMNav.model.util.modelexplore.queries.scenarioTraversal;

import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.scenarios.ScenarioTraversalListenerList;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.model.UcmEnvironment;

/**
 * Query request to start a traversal.
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalQuery extends QueryRequest {
    // EndPoints / ScenarioEndPoints
    protected Vector _EndPathNodes;
    protected UcmEnvironment _env;

    // list of ITraversalListeners
    protected ScenarioTraversalListenerList _listeners;

    // StartPoints / ScenarioStartPoints
    protected Vector _StartPathNodes;

    /**
     * 
     * @param startNodes
     *            the starting point for traversal.
     * @param endNodes
     *            the set of end points that must be reached.
     */
    public ScenarioTraversalQuery(UcmEnvironment env, Vector startNodes, Vector endNodes, ScenarioTraversalListenerList listeners) {
        this._queryType = QueryObject.DEFAULTSCENARIOTRAVERSAL;
        _StartPathNodes = startNodes;
        _EndPathNodes = endNodes;
        _env = env;
        _listeners = listeners;

    }

    /**
     * 
     * @return the end path nodes that are defined in the scenario definition.
     */
    public Vector getEndPathNodes() {
        return _EndPathNodes;
    }

    /**
     * 
     * @return The environment in which the scenario was executed.
     */
    public UcmEnvironment getEnvironment() {
        return _env;
    }

    /**
     * 
     * @return the start path nodes that should be executed.
     */
    public Vector getStartPathNodes() {
        return _StartPathNodes;
    }

    /**
     * 
     * @return the list of {@link ITraversalListener}
     */
    public ScenarioTraversalListenerList getTraversalListeners() {
        return _listeners;
    }
}
