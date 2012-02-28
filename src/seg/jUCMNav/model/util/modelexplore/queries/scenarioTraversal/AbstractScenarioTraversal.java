package seg.jUCMNav.model.util.modelexplore.queries.scenarioTraversal;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import seg.jUCMNav.scenarios.ScenarioTraversalListenerList;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalVisit;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import ucm.map.EndPoint;
import ucm.scenario.ScenarioEndPoint;
import urncore.Condition;

/**
 * Abstract base class that should reasonably be shared by any implementation of a Scenario Traversal engine.
 * 
 * Represents the "plumbing" for a traversal engine.
 * 
 * 
 * @author jkealey
 * 
 */
public abstract class AbstractScenarioTraversal extends AbstractQueryProcessor {

    protected TraversalVisit _currentVisit;
    protected String _error;
    protected ScenarioTraversalListenerList _listeners;
    protected AbstractScenarioTraversalDataStructure _traversalData;
    protected Vector _warnings;

    /**
     * Query processor representing the sequence of elements traversed by the scenario traversal algorithm.
     * 
     */
    public AbstractScenarioTraversal() {
        this._answerQueryTypes = new String[] { QueryObject.DEFAULTSCENARIOTRAVERSAL };
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
        _error = _traversalData.seedAlgorithm(startPoints);
        if (_error != null)
            return;

        try {
            processAllNodes(env);

            // find reached end points. must create new vector here (or clone _visited) because we are modifying it in verifyEndPoints
            Vector reachedEndPoints = _traversalData.getReachedEndPoints();

            // verify that the reached end points match the expected ones.
            verifyEndPoints(endPoints, reachedEndPoints);
        } catch (Exception e) {
            _error = Messages.getString("DefaultScenarioTraversal.ExceptionOccurred") + e.getMessage(); //$NON-NLS-1$
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
    protected abstract void processAllNodes(UcmEnvironment env) throws TraversalException;

    /**
     * Initialize using {@link ScenarioTraversalQuery} , run {@link #process(UcmEnvironment, Vector, Vector)} and prepare {@link ScenarioTraversalResponse}
     * 
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        ScenarioTraversalQuery query = (ScenarioTraversalQuery) q;
        _error = null;
        _warnings = new Vector();
        _listeners = query.getTraversalListeners();

        // TODO: load from extension point
        _traversalData = new DefaultScenarioTraversalDataStructure();
        _traversalData.setListeners(_listeners);

        if (query.getStartPathNodes() != null) {
            Vector startPoints = query.getStartPathNodes();
            Vector endPoints = query.getEndPathNodes();
            process(query.getEnvironment(), startPoints, endPoints);
        }

        // Return a response containing the visited node list
        ScenarioTraversalResponse r = new ScenarioTraversalResponse();
        r.setVisited(_traversalData.getVisited());
        r.setError(_error);
        r.setWarnings(_warnings);
        r.setResults(_traversalData.getResults());
        return r;
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
        return testCondition(env, cond, expected, errorMessage, false);
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
     * @param isPreCondition
     *            is this a start point's precondition?
     * @return true if is what is expected, false otherwise.
     * @throws TraversalException
     *             if a parsing error occurs
     */
    protected boolean testCondition(UcmEnvironment env, Condition cond, Boolean expected, String errorMessage, boolean isPreCondition)
            throws TraversalException {
        try {
            Object result = ScenarioUtils.evaluate(cond, env);
            
            _listeners.conditionEvaluated(_currentVisit, ScenarioUtils.isEmptyCondition(cond) ? null : cond, Boolean.TRUE.equals(result), isPreCondition);
            if (!expected.equals(result)) {
                TraversalWarning warning = new TraversalWarning(errorMessage, cond.eContainer(), IMarker.SEVERITY_ERROR);
                warning.setCondition(cond);
                _warnings.add(warning);
                return false;
            }
        } catch (IllegalArgumentException e) {
            // throw new TraversalException(e.getMessage(), e);
            _warnings.add(new TraversalWarning(e.getMessage(), cond, IMarker.SEVERITY_ERROR));
        }

        return true;
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
        Collections.sort(expectedEndPoints, new java.util.Comparator() {
            public int compare(Object obj1, Object obj2) {
                if (obj1 instanceof ScenarioEndPoint && obj2 instanceof ScenarioEndPoint) {
                    ScenarioEndPoint end1 = (ScenarioEndPoint) obj1;
                    ScenarioEndPoint end2 = (ScenarioEndPoint) obj2;
                    if (end1.isMandatory() && !end2.isMandatory())
                        return -1;
                    else if (!end1.isMandatory() && end2.isMandatory())
                        return 1;
                    else
                        return 0;
                } else if (obj1 instanceof EndPoint)
                    return -1;
                else if (obj2 instanceof EndPoint)
                    return 1;
                else
                    return 0;
            }
        });

        for (Iterator iter = expectedEndPoints.iterator(); iter.hasNext();) {
            EObject obj = (EObject) iter.next();
            EndPoint pt = null;
            int severity = IMarker.SEVERITY_ERROR;
            ;

            if (obj instanceof EndPoint) {
                pt = (EndPoint) obj;
            } else if (obj instanceof ScenarioEndPoint) {
                if (((ScenarioEndPoint) obj).isEnabled()) {
                    pt = ((ScenarioEndPoint) obj).getEndPoint();
                    if (!((ScenarioEndPoint) obj).isMandatory())
                        severity = IMarker.SEVERITY_INFO;
                }
            }

            if (pt != null && !reachedEndPoints.contains(pt)) {
                _warnings.add(new TraversalWarning(
                        Messages.getString("DefaultScenarioTraversal.ScenariosShouldHaveReachedEndPoint") + pt.toString(), pt, severity)); //$NON-NLS-1$
            } else {
                // so that we can find multiple instances
                reachedEndPoints.remove(pt);
            }
        }
    }

}