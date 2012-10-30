package seg.jUCMNav.scenarios;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.algorithmInterfaces.IScenarioTraversalAlgorithm;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.evaluator.UcmExpressionEvaluator;
import seg.jUCMNav.scenarios.evaluator.UcmExpressionValue;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.model.jUCMNavType;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;
import ucm.UCMspec;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;

/**
 * Utility class for UCM Scenarios.
 * 
 * @author jkealey
 * 
 */
public class ScenarioUtils {
    private static HashMap activeScenario = new HashMap();
    private static HashMap environments;
    // static reference to jUCMNavParser. can't have more than one reference to
    // the parser in the whole application.
    public static final jUCMNavParser parser = new jUCMNavParser(new StringReader("true")); //$NON-NLS-1$
    public static final String sTypeBoolean = "boolean"; //$NON-NLS-1$
    public static final String sTypeEnumeration = "enumeration"; //$NON-NLS-1$
    public static final String sTypeInteger = "integer"; //$NON-NLS-1$

    public static boolean IS_ELSE_CONDITION_ALLOWED = true;

    private static HashMap traversals = new HashMap();

    /**
     * Flush any traversal results associated with this element.
     * 
     * @param obj
     *            an object in a URNspec
     */
    public static void clearActiveScenario(EObject obj) {
        UcmEnvironment initial = getEnvironment(obj);
        if (activeScenario.containsKey(initial))
            clearTraversalResults(initial);
        activeScenario.remove(initial);

    }

    /**
     * Flush any traversal results associated with this environment.
     * 
     * @param env
     *            the environment
     */
    public static void clearTraversalResults(UcmEnvironment env) {
        if (traversals.containsKey(env)) {
            IScenarioTraversalAlgorithm results = (IScenarioTraversalAlgorithm) traversals.get(env);
            results.clearTraversalResults();
        }
        if (activeScenario.containsKey(env)) {
            activeScenario.remove(env);
        }

    }

    /**
     * Evaluate this condition in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param cond
     *            the condition
     * @param env
     *            the environment
     * @return the boolean result.
     */
    public static Object evaluate(Condition cond, UcmEnvironment env) {
        if (isEmptyCondition(cond)) {
            return Boolean.TRUE;
        } else {
            String toEvaluate = cond.getExpression();
            if (isElseCondition(toEvaluate)) {
                toEvaluate = replaceElseFromCondition(cond);
            }

            Object result = evaluate(toEvaluate, env, false);
            if (result != null) {
                if (result instanceof UcmExpressionValue)   {
                    if (((UcmExpressionValue) result).getBaseValue() instanceof Boolean) {
                        return (Boolean) ((UcmExpressionValue) result).getBaseValue();
                    }
                    else
                        throw new IllegalArgumentException(Messages.getString("ScenarioUtils.ConditionDidNotEvaluateToBoolean")); //$NON-NLS-1$
                }
            }
            return result;
        }
    }

    /**
     * Assuming cond is "else", look at the Condition's parent to figure out what "else" means in this context.
     * 
     * @param cond
     * @return
     */
    private static String replaceElseFromCondition(Condition cond) {
        String result = null;
        if (cond.eContainer() instanceof NodeConnection) {
            NodeConnection nodeConnection = (NodeConnection) cond.eContainer();
            if (nodeConnection.getSource() instanceof OrFork) {
                OrFork orFork = (OrFork) nodeConnection.getSource();
                Vector conditions = new Vector();
                for (Iterator iterator = orFork.getSucc().iterator(); iterator.hasNext();) {
                    NodeConnection nc = (NodeConnection) iterator.next();
                    if (!isEmptyCondition(nc.getCondition()) && !isElseCondition(nc.getCondition().getExpression())) {
                        conditions.add(nc.getCondition().getExpression());
                    }
                }
                result = concatenateConditions(conditions);
            }
        } else if (cond.eContainer() instanceof PluginBinding && cond.eContainer().eContainer() instanceof Stub) {
            Stub stub = (Stub) cond.eContainer().eContainer();
            Vector conditions = new Vector();
            for (Iterator iterator = stub.getBindings().iterator(); iterator.hasNext();) {
                PluginBinding binding = (PluginBinding) iterator.next();
                if (!isEmptyCondition(binding.getPrecondition()) && !isElseCondition(binding.getPrecondition().getExpression())) {
                    conditions.add(binding.getPrecondition().getExpression());
                }
            }
            result = concatenateConditions(conditions);
        } /*
           * added here just in case, but it makes no sense to enable this since all must be true. else if (cond.eContainer() instanceof ScenarioDef) {
           * ScenarioDef def = (ScenarioDef) cond.eContainer(); EList list = null; if (def.getPreconditions().contains(cond)) list = def.getPreconditions();
           * else list = def.getPostconditions(); Vector conditions = new Vector(); for (Iterator iterator = list.iterator(); iterator.hasNext();) { Condition c
           * = (Condition) iterator.next(); if (!isEmptyCondition(c) && !isElseCondition(c.getExpression())) { conditions.add(c.getExpression()); } } }
           */

        // assuming else is true when its the only thing there.
        if (result == null)
            result = "true"; //$NON-NLS-1$
        return result;
    }

    /**
     * Concatenates the other conditions and negates them.
     * 
     * @param conditions
     * @return
     */
    private static String concatenateConditions(Vector conditions) {
        String result = null;
        for (Iterator iterator = conditions.iterator(); iterator.hasNext();) {
            String c = (String) iterator.next();
            if (result == null)
                result = "!(" + c + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            else
                result += " && !(" + c + ") "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return result;
    }

    /**
     * Is this text an "else" to be used in conditions.
     * 
     * @param expression
     * @return true if this is else and else is enabled in jucmnav.
     */
    public static boolean isElseCondition(String expression) {
        if (expression == null)
            return false;
        return IS_ELSE_CONDITION_ALLOWED && "else".equalsIgnoreCase(expression.trim()); //$NON-NLS-1$
    }

    /**
     * Evaluate this responsibility in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param resp
     *            the responsibility
     * @param env
     *            the environment
     * @return the result of the valuation, should be {@link jUCMNavType#VOID}.
     */
    public static Object evaluate(RespRef resp, UcmEnvironment env) {
        if (isEmptyResponsibility(resp)) {
            return null;
        } else
            return evaluate(resp.getRespDef().getExpression(), env, true);
    }

    /**
     * Evaluate this code in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param code
     *            the code
     * @param env
     *            the environment
     * @param isResponsibility
     *            is it a responsibility (true) or a condition (false)
     * @return the result of the evaluation
     */
    public static Object evaluate(String code, UcmEnvironment env, boolean isResponsibility) {
        Object res = parse(code, env, isResponsibility);
        if (res instanceof String)
            throw new IllegalArgumentException(res.toString());
        else if (res instanceof SimpleNode)
            return UcmExpressionEvaluator.evaluate((SimpleNode) res, env);
        else
            return res;
    }

    /**
     * Returns the last run {@link ScenarioDef}, {@link ScenarioGroup} or {@link UCMspec} in the jucm file containing obj.
     * 
     * @param obj
     *            an object for which an environment is associated.
     * @return the last run element.
     */
    public static EObject getActiveScenario(EObject obj) {
        UcmEnvironment initial = getEnvironment(obj);
        if (activeScenario.containsKey(initial))
            return (EObject) activeScenario.get(initial); // can be ScenarioDef, ScenarioGroup or UCMspec
        else
            return null;
    }

    /**
     * Returns a list of all ScenarioDefs in all groups.
     * 
     * @param urn
     *            the root urnspec
     * @return the list of scenarios
     */
    public static List getAllScenarios(URNspec urn) {
        ArrayList list = new ArrayList();
        for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();

            for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef scenario = (ScenarioDef) iterator.next();
                list.add(scenario);
            }
        }
        return list;
    }

    /**
     * Get all {@link ScenarioEndPoint} (recursively) that are related this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @return a List of {@link ScenarioEndPoint}
     */
    public static Vector getDefinedEndPoints(ScenarioDef def) {
        Vector endPoints = new Vector();
        getDefinedEndPoints(def, endPoints);
        return endPoints;
    }

    /**
     * Get all {@link ScenarioEndPoint} (recursively) that are related to this scenario(inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @param endPoints
     *            where to insert the found {@link ScenarioEndPoint}
     */
    private static void getDefinedEndPoints(ScenarioDef def, Vector endPoints) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedEndPoints(scenario, endPoints);
        }

        for (Iterator iter = def.getEndPoints().iterator(); iter.hasNext();) {
            ScenarioEndPoint pt = (ScenarioEndPoint) iter.next();
            if (!endPoints.contains(pt))
                endPoints.add(pt);
        }
    }

    /**
     * Get all the included scenarios (recursively)that are related to this scenario
     * 
     * @param def
     *            the scenario
     * @return the list of {@link ScenarioDef}
     */
    public static Vector getDefinedIncludedScenarios(ScenarioDef def) {
        Vector scenarios = new Vector();
        getDefinedIncludedScenarios(def, scenarios);
        return scenarios;
    }
    
    /**
     * For each ScenarioDef which is an explicit child of def, we return the index inside the vector returned by getDefinedIncludedScenarios
     * 
     * @param def the scenario
     * @return the list of indexes in the getDefinedIncludedScenarios list. 
     */
    public static Vector getIndexesOfPrimaryDefinedIncludedScenarios(ScenarioDef def) {
        Vector all = getDefinedIncludedScenarios(def);
        Vector indexes = new Vector();
        for (int i=0;i<def.getIncludedScenarios().size();i++)
        {
            // add the index of the scenario in this list. 
            // given how we merge included scenarios (to avoid duplication), this list is non-obvious  
            indexes.add(new Integer(all.indexOf(def.getIncludedScenarios().get(i))));
        }
        return indexes;
    }
    
    

    /**
     * Get all the included scenarios (recursively)that are related to this scenario
     * 
     * @param def
     *            the scenario
     * @param scenarios
     *            where to insert the found {@link ScenarioDef}s
     */
    private static void getDefinedIncludedScenarios(ScenarioDef def, Vector scenarios) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedIncludedScenarios(scenario, scenarios);
            if (!scenarios.contains(scenario))
                scenarios.add(scenario);
        }
    }

    /**
     * Gets all the related variable {@link Initialization}s (recursively). Filters out any inherited duplicates to show only the last one.
     * 
     * @param def
     *            the scenario
     * @return the list of {@link Initialization}s
     */
    public static Vector getDefinedInitializations(ScenarioDef def) {

        Vector initializations = new Vector();
        if (def.getGroup() == null)
            return initializations;
        getDefinedInitializations(def, initializations);

        Vector uniqueSubsetInitializations = new Vector();
        for (Iterator iter = def.getGroup().getUcmspec().getVariables().iterator(); iter.hasNext();) {
            Variable var = (Variable) iter.next();
            // only add last occurrence
            for (int i = initializations.size() - 1; i >= 0; i--) {
                Initialization init = (Initialization) initializations.get(i);
                if (init.getVariable() == var) {
                    uniqueSubsetInitializations.add(init);
                    break;
                }
            }

        }
        return uniqueSubsetInitializations;
    }

    /**
     * Gets all the related variable {@link Initialization}s (recursively). Filters out any inherited duplicates to show only the last one.
     * 
     * @param def
     *            the scenario param initializations where to store the found {@link Initialization}s
     */
    private static void getDefinedInitializations(ScenarioDef def, Vector initializations) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedInitializations(scenario, initializations);
        }

        for (Iterator iter = def.getInitializations().iterator(); iter.hasNext();) {
            Initialization cond = (Initialization) iter.next();
            if (!initializations.contains(cond))
                initializations.add(cond);
        }
    }

    /**
     * Gets all the related scenario post conditions (recursively).
     * 
     * @param def
     *            the scenario
     * @return a list of {@link Condition}s
     */
    public static Vector getDefinedPostconditions(ScenarioDef def) {
        Vector postconditions = new Vector();
        getDefinedPostconditions(def, postconditions);
        return postconditions;
    }

    /**
     * Gets all the related scenario post conditions (recursively).
     * 
     * @param def
     *            the scenario param postconditions where to add the found {@link Condition}s
     */
    private static void getDefinedPostconditions(ScenarioDef def, Vector postconditions) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedPostconditions(scenario, postconditions);
        }

        for (Iterator iter = def.getPostconditions().iterator(); iter.hasNext();) {
            Condition cond = (Condition) iter.next();
            if (!postconditions.contains(cond))
                postconditions.add(cond);
        }
    }

    /**
     * Gets all the related scenario pre conditions (recursively).
     * 
     * @param def
     *            the scenario
     * @return a list of {@link Condition}s
     */
    public static Vector getDefinedPreconditions(ScenarioDef def) {
        Vector preconditions = new Vector();
        getDefinedPreconditions(def, preconditions);
        return preconditions;
    }

    /**
     * Gets all the related scenario pre conditions (recursively).
     * 
     * @param def
     *            the scenario param postconditions where to add the found {@link Condition}s
     */
    private static void getDefinedPreconditions(ScenarioDef def, Vector preconditions) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedPreconditions(scenario, preconditions);
        }

        for (Iterator iter = def.getPreconditions().iterator(); iter.hasNext();) {
            Condition cond = (Condition) iter.next();
            if (!preconditions.contains(cond))
                preconditions.add(cond);
        }
    }

    /**
     * Get all {@link ScenarioStartPoint} (recursively) that are related this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @return a List of {@link ScenarioStartPoint}
     */
    public static Vector getDefinedStartPoints(ScenarioDef def) {
        Vector startPoints = new Vector();
        getDefinedStartPoints(def, startPoints);
        return startPoints;
    }

    /**
     * Get all {@link ScenarioStartPoint} (recursively) that are related to this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @param startPoints
     *            where to insert the found {@link ScenarioStartPoint}
     */
    private static void getDefinedStartPoints(ScenarioDef def, Vector startPoints) {
        for (Iterator iter = getDefinedIncludedScenarios(def).iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedStartPoints(scenario, startPoints);
        }

        for (Iterator iter = def.getStartPoints().iterator(); iter.hasNext();) {
            ScenarioStartPoint pt = (ScenarioStartPoint) iter.next();
            if (!startPoints.contains(pt))
                startPoints.add(pt);
        }
    }

    /**
     * Return the UcmEnvironment associated with this object from the global cache.
     * 
     * @param object
     *            an EObject from which we can reach the URNspec via object.eContainer()
     * 
     * @return null if can't reach the URNspec, a UcmEnvironment (newly created or retrieved from cache) otherwise.
     */
    public static synchronized UcmEnvironment getEnvironment(EObject object) {
        if (object == null)
            return null;

        if (object instanceof URNspec) {
            if (!getEnvironments().containsKey(object)) {
                getEnvironments().put(object, new UcmEnvironment((URNspec) object));
            }
            return (UcmEnvironment) getEnvironments().get(object);
        } else
            return getEnvironment(object.eContainer());

    }

    /**
     * Global UcmEnvironment cache.
     * 
     * @return a HashMap of URNspec -> UcmEnvironment
     */
    private static synchronized HashMap getEnvironments() {
        if (environments == null)
            environments = new HashMap();

        return environments;
    }

    /**
     * Returns all scenarios that we may include into the given parent. Will not cause any circular references.
     * 
     * @param parent
     *            the parent scenario definition
     * @return the list of possible children.
     */
    public static List getPossibleIncludedScenarios(ScenarioDef parent) {
        List list = getPossibleIncludedScenariosNonRecursive(parent);

        ArrayList toRemove = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ScenarioDef child = (ScenarioDef) iter.next();
            if (!getPossibleIncludedScenariosNonRecursive(child).contains(parent))
                toRemove.add(child);
        }
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            ScenarioDef element = (ScenarioDef) iter.next();
            if (list.contains(element))
                list.remove(element);
        }
        return list;
    }

    /**
     * Gets the list of scenarios that it would be possible to include, without recursing. Used in a context where recursion would cause an infinite loop.
     * 
     * @param parent
     *            the scenario
     * @return the list of possible {@link ScenarioDef}
     */
    private static List getPossibleIncludedScenariosNonRecursive(ScenarioDef parent) {
        if (parent.getGroup() == null)
            return new ArrayList();
        URNspec urn = parent.getGroup().getUcmspec().getUrnspec();
        List list = getAllScenarios(urn);

        removeIncludedScenarios(list, parent);
        return list;

    }

    /**
     * 
     * @param obj
     *            an object
     * @return the number of times the traversal engine passed through it.
     */
    public static int getTraversalHitCount(EObject obj) {
        TraversalResult res = getTraversalResults(obj);
        if (res != null)
            return res.getExternalHitCount();
        else
            return 0;
    }

    /**
     * 
     * @param obj
     *            an object
     * @return the {@link TraversalResult} of a certain object.
     */
    private static TraversalResult getTraversalResults(EObject obj) {
        UcmEnvironment env = getEnvironment(obj);
        if (traversals.containsKey(env)) {
            IScenarioTraversalAlgorithm results = (IScenarioTraversalAlgorithm) traversals.get(env);
            return results.getTraversalResults(obj);
        } else
            return null;
    }

    /**
     * Is this condition null, empty or a default "true"
     * 
     * @param cond
     *            the condition
     * @return true if it is.
     */
    public static boolean isEmptyCondition(Condition cond) {
        // "true" is the default for most conditions. don't want to load the big infrastructure.
        return cond == null || cond.getExpression() == null || cond.getExpression().length() == 0 || "true".equals(cond.getExpression()); //$NON-NLS-1$
    }

    /**
     * Is this condition null, empty or a default "true"
     * 
     * @param cond
     *            the condition
     * @return true if it is.
     */
    public static boolean isEmptyCondition(String cond) {
        // "true" is the default for most conditions. don't want to load the big infrastructure.
        return cond == null || cond.length() == 0 || "true".equals(cond); //$NON-NLS-1$
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param resp
     *            the responsibility
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(Responsibility resp) {
        if (resp == null)
            return true;
        else
            return isEmptyResponsibility(resp.getExpression());
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param resp
     *            the responsibility
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(RespRef resp) {
        if (resp == null)
            return true;
        else
            return isEmptyResponsibility(resp.getRespDef());
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param code
     *            the code
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(String code) {
        return code == null || code.length() == 0 || code.replace("\n", "").replace("\r", "").trim().length() == 0; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    /**
     * Parses a string and returns an error message if is not valid (as a string) or a SimpleNode AST of the code if it is. Does both syntax checking and type
     * checking.
     * 
     * @param code
     *            the code to be parsed
     * @param env
     *            the environment in which it should be parsed
     * @param isResponsibility
     *            is this a responsibility or a condition?
     * @return a SimpleNode instance if valid, a String otherwise.
     */

    public static Object parse(String code, UcmEnvironment env, boolean isResponsibility) {
        return parse(code, env, isResponsibility, isResponsibility ? null : jUCMNavType.BOOLEAN);
    }

    public static Object parseInteger(String code, UcmEnvironment env) {
        return parse(code, env, false, jUCMNavType.INTEGER);
    }

    /**
     * Parses a string and returns an error message if is not valid (as a string) or a SimpleNode AST of the code if it is. Does both syntax checking and type
     * checking.
     * 
     * @param code
     *            the code to be parsed
     * @param env
     *            the environment in which it should be parsed
     * @param isResponsibility
     *            is this a responsibility or a condition?
     * @param expectedType
     *            the expected type
     * @return a SimpleNode instance if valid, a String otherwise.
     */

    private static Object parse(String code, UcmEnvironment env, boolean isResponsibility, jUCMNavType expectedType) {
        SimpleNode n = null;

        // syntax checking
        try {

            jUCMNavParser.ReInit(new StringReader(code));
            if (isResponsibility)
                n = jUCMNavParser.StartResponsibility();
            else
                n = jUCMNavParser.Start();

        } catch (Throwable t) {
            return Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
        }

        // type checking
        try {
            jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);
            if (isResponsibility) {
                if (!checker.isValidResponsibility()) {
                    return Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
                }
            } else {
                if (expectedType == jUCMNavType.INTEGER) {
                    if (!checker.isValidInteger()) {
                        return Messages.getString("ScenarioUtils.IsNotAValidInteger"); //$NON-NLS-1$
                    }
                } else if (!checker.isValidCondition()) {
                    return Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
                }
            }
        } catch (IllegalArgumentException ex) {
            return Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
        }

        // return the object.
        return n;
    }

    /**
     * Removes the UcmEnvironment associated with the object from the global cache.
     * 
     * @param object
     */
    public static synchronized void releaseEnvironment(EObject object) {
        if (object == null)
            return;

        if (object instanceof URNspec) {
            getEnvironments().remove(object);
        } else
            releaseEnvironment(object.eContainer());
    }

    /**
     * Recursively removes all the included scenarios from the given list.
     * 
     * @param list
     *            list of ScenarioDefs
     * @param parent
     *            the root scenariodef from which we remove the children. we also remove the parent from the list.
     */
    private static void removeIncludedScenarios(List list, ScenarioDef parent) {
        for (Iterator iter = parent.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef child = (ScenarioDef) iter.next();
            removeIncludedScenarios(list, child);
        }

        if (list.contains(parent))
            list.remove(parent);
    }

    /**
     * Set the active scenario, no extra listeners.
     * 
     * @param scenario
     *            the scenario
     */
    public static void setActiveScenario(ScenarioDef scenario) {
        traverse(scenario, null);
    }

    /**
     * Set the active scenario, with the extra listeners passed.
     * 
     * @param scenario
     *            the scenario
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(ScenarioDef scenario, Vector listeners) {
        traverse(scenario, listeners);
    }

    /**
     * Set the active scenario group, no extra listeners.
     * 
     * @param group
     *            the scenario group
     */
    public static void setActiveScenario(ScenarioGroup group) {
        traverse(group, null);
    }

    /**
     * Set the active scenario group, with the extra listeners passed.
     * 
     * @param group
     *            the scenario group
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(ScenarioGroup group, Vector listeners) {
        traverse(group, listeners);
    }

    /**
     * Set the active ucmspec, no extra listeners.
     * 
     * @param ucm
     *            the ucmspec
     */
    public static void setActiveScenario(UCMspec ucm) {
        traverse(ucm, null);
    }

    /**
     * Set the active ucmspec, with the extra listeners passed.
     * 
     * @param ucm
     *            the ucmspec
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(UCMspec ucm, Vector listeners) {
        traverse(ucm, listeners);
    }

    /**
     * Traverse the active secenario.
     * 
     * @param scenario
     *            the {@link ScenarioDef}, {@link ScenarioGroup}, or {@link UCMspec}
     * @param listeners
     *            an optional list of extra {@link ITraversalListener} to add.
     */
    protected static void traverse(EObject scenario, Vector listeners) {
        try {
            if (scenario == null) {
                System.out.println("Use clearActiveScenario instead"); //$NON-NLS-1$
                return;
            }
            UcmEnvironment initial = getEnvironment(scenario);
            
            if (initial ==null) {
                clearActiveScenario(scenario);
                return; 
            }
            if (activeScenario.containsKey(initial))
                clearTraversalResults(initial);
            activeScenario.put(initial, scenario);
            UcmEnvironment forTraversal = (UcmEnvironment) initial.clone();

            IScenarioTraversalAlgorithm algo = new ScenarioTraversalAlgorithm();
            if (scenario instanceof ScenarioDef)
                algo.init(forTraversal, (ScenarioDef) scenario);
            else if (scenario instanceof ScenarioGroup)
                algo.init(forTraversal, (ScenarioGroup) scenario);
            else if (scenario instanceof UCMspec)
                algo.init(forTraversal, (UCMspec) scenario);
            else
                System.out.println("undefined initialization"); //$NON-NLS-1$

            traversals.put(initial, algo);

            // TODO: add listeners from extension point

            if (listeners != null)
                algo.addListeners(listeners);

            algo.traverse();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (TraversalException e) {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", e.getMessage()); //$NON-NLS-1$
        }
    }
    
    public static Vector traverseWarn(EObject scenario, Vector listeners) {//**************
        try {
            if (scenario == null) {
                System.out.println("Use clearActiveScenario instead"); //$NON-NLS-1$
                return (Vector)null;//***********************
            }
            UcmEnvironment initial = getEnvironment(scenario);
            
            if (initial ==null) {
                clearActiveScenario(scenario);
                return (Vector)null; //***********
            }
            if (activeScenario.containsKey(initial))
                clearTraversalResults(initial);
            activeScenario.put(initial, scenario);
            UcmEnvironment forTraversal = (UcmEnvironment) initial.clone();

            IScenarioTraversalAlgorithm algo = new ScenarioTraversalAlgorithm();
            if (scenario instanceof ScenarioDef)
                algo.init(forTraversal, (ScenarioDef) scenario);
            else if (scenario instanceof ScenarioGroup)
                algo.init(forTraversal, (ScenarioGroup) scenario);
            else if (scenario instanceof UCMspec)
                algo.init(forTraversal, (UCMspec) scenario);
            else
                System.out.println("undefined initialization"); //$NON-NLS-1$

            traversals.put(initial, algo);

            // TODO: add listeners from extension point

            if (listeners != null)
                algo.addListeners(listeners);

            algo.traverse();
            return algo.getWarnings(); //********

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return (Vector)null;
        } catch (TraversalException e) {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", e.getMessage()); //$NON-NLS-1$
            return (Vector)null;
        }
    }

}
