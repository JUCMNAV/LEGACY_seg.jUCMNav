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
import seg.jUCMNav.scenarios.evaluator.UcmExpressionEvaluator;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;
import ucm.map.RespRef;
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
	public static jUCMNavParser parser = new jUCMNavParser(new StringReader("true")); //$NON-NLS-1$
	public static final String sTypeBoolean = "boolean"; //$NON-NLS-1$
	public static final String sTypeEnumeration = "enumeration"; //$NON-NLS-1$
	public static final String sTypeInteger = "integer"; //$NON-NLS-1$

	private static HashMap traversals = new HashMap();

	public static void clearActiveScenario(EObject obj)
	{
		UcmEnvironment initial = getEnvironment(obj);
		if (activeScenario.containsKey(initial))
			clearTraversalResults(initial);
		activeScenario.remove(initial);

	}

	public static void clearTraversalResults(UcmEnvironment env) {
		if (traversals.containsKey(env))
		{
			DefaultScenarioTraversalAlgorithm results = (DefaultScenarioTraversalAlgorithm) traversals.get(env);
			results.clearTraversalResults();
		}
		if (activeScenario.containsKey(env)) {
			activeScenario.remove(env);
		}

	}

	public static Object evaluate(String code, UcmEnvironment env, boolean isResponsibility) 
	{
		Object res = parse(code, env, isResponsibility);
		if (res instanceof String)
			throw new IllegalArgumentException(res.toString());
		else if (res instanceof SimpleNode)
			return UcmExpressionEvaluator.evaluate((SimpleNode)res, env);
		else 
			return res;
	}
	
	public static Object evaluate(Condition cond, UcmEnvironment env) 
	{
		if (isEmptyCondition(cond)) {
			return Boolean.TRUE;
		}
		else
			return evaluate(cond.getExpression(), env, false);
	}
	
	public static Object evaluate(RespRef resp, UcmEnvironment env) 
	{
		if (isEmptyResponsibility(resp)) {
			return null;
		}
		else
			return evaluate(resp.getRespDef().getExpression(), env, true);
	}	

	public static boolean isEmptyCondition(Condition cond) {
		//		 "true" is the default for most conditions. don't want to load the big infrastructure.
		return cond == null || cond.getExpression() == null || cond.getExpression().length() == 0 || "true".equals(cond);    //$NON-NLS-1$
	}
	
	public static boolean isEmptyResponsibility(RespRef resp) {
		if (resp==null) 
			return true;
		else
			return isEmptyResponsibility(resp.getRespDef());
	}
	
	public static boolean isEmptyResponsibility(String code) {
		return code == null || code.length() == 0 ||  code.replace("\n", "").replace("\r", "").trim().length()==0;    //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
		
		
	public static boolean isEmptyResponsibility(Responsibility resp) {
		if (resp == null)
			return true;
		else
			return isEmptyResponsibility(resp.getExpression());
	}	
	
	public static ScenarioDef getActiveScenario(EObject obj)
	{
		UcmEnvironment initial = getEnvironment(obj);
		if (activeScenario.containsKey(initial))
			return (ScenarioDef) activeScenario.get(initial);
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
	 * Return the UcmEnvironment associated with this object from the global
	 * cache.
	 * 
	 * @param object
	 *            an EObject from which we can reach the URNspec via
	 *            object.eContainer()
	 * 
	 * @return null if can't reach the URNspec, a UcmEnvironment (newly created
	 *         or retrieved from cache) otherwise.
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
	 * Returns all scenarios that we may include into the given parent. Will not
	 * cause any circular references.
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
			if (list.contains(element)) list.remove(element);
		}
		return list;
	}
	
	private static List getPossibleIncludedScenariosNonRecursive(ScenarioDef parent) {
		if (parent.getGroup()==null)
			return new ArrayList();
		URNspec urn = parent.getGroup().getUcmspec().getUrnspec();
		List list = getAllScenarios(urn);

		removeIncludedScenarios(list, parent);
		return list;
	
	}
	
	public static Vector getDefinedStartPoints(ScenarioDef def)
	{
		Vector startPoints = new Vector();
		getDefinedStartPoints(def, startPoints);
		return startPoints;
	}
	private static void getDefinedStartPoints(ScenarioDef def, Vector startPoints)
	{
		for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			getDefinedStartPoints(scenario, startPoints);
		}
		
		for (Iterator iter = def.getStartPoints().iterator(); iter.hasNext();) {
			ScenarioStartPoint pt = (ScenarioStartPoint) iter.next();
			startPoints.add(pt);
		}
	}
	
	public static Vector getDefinedIncludedScenarios(ScenarioDef def)
	{
		Vector scenarios = new Vector();
		getDefinedIncludedScenarios(def, scenarios);
		return scenarios;
	}
	private static void getDefinedIncludedScenarios(ScenarioDef def, Vector scenarios)
	{
		for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			getDefinedIncludedScenarios(scenario, scenarios);
			scenarios.add(scenario);
		}
		
	}	

	public static Vector getDefinedEndPoints(ScenarioDef def)
	{
		Vector endPoints = new Vector();
		getDefinedEndPoints(def, endPoints);
		return endPoints;
	}
	private static void getDefinedEndPoints(ScenarioDef def, Vector endPoints)
	{
		for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			getDefinedEndPoints(scenario, endPoints);
		}
		
		for (Iterator iter = def.getEndPoints().iterator(); iter.hasNext();) {
			ScenarioEndPoint pt = (ScenarioEndPoint) iter.next();
			endPoints.add(pt);
		}
	}
	
	public static Vector getDefinedPreconditions(ScenarioDef def)
	{
		Vector preconditions = new Vector();
		getDefinedPreconditions(def, preconditions);
		return preconditions;
	}
	private static void getDefinedPreconditions(ScenarioDef def, Vector preconditions)
	{
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
	
	public static Vector getDefinedPostconditions(ScenarioDef def)
	{
		Vector postconditions = new Vector();
		getDefinedPostconditions(def, postconditions);
		return postconditions;
	}
	private static void getDefinedPostconditions(ScenarioDef def, Vector postconditions)
	{
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
	
	public static Vector getDefinedInitializations(ScenarioDef def)
	{
		
		Vector initializations = new Vector();
		if (def.getGroup()==null) return initializations;
		getDefinedInitializations(def, initializations);
		
		Vector uniqueSubsetInitializations = new Vector();
		for (Iterator iter = def.getGroup().getUcmspec().getVariables().iterator(); iter.hasNext();) {
			Variable var = (Variable) iter.next();
			// only add last occurrence
			for (int i=initializations.size()-1;i>=0;i--) {
				Initialization init = (Initialization) initializations.get(i);
				if (init.getVariable() == var) {
					uniqueSubsetInitializations.add(init);
					break;
				}
			}
			
		}
		return uniqueSubsetInitializations;
	}
	private static void getDefinedInitializations(ScenarioDef def, Vector initializations)
	{
		for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef scenario = (ScenarioDef) iter.next();
			getDefinedInitializations(scenario, initializations);
		}
		
		for (Iterator iter = def.getInitializations().iterator(); iter.hasNext();) {
			Initialization cond = (Initialization) iter.next();
			initializations.add(cond);
		}
	}		
	public static int getTraversalHitCount(EObject obj) {
		TraversalResult res = getTraversalResults(obj);
		if (res!=null)
			return res.getHitCount();
		else
			return 0;
	}

	private static TraversalResult getTraversalResults(EObject obj) {
		UcmEnvironment env = getEnvironment(obj);
		if (traversals.containsKey(env))
		{
			DefaultScenarioTraversalAlgorithm results = (DefaultScenarioTraversalAlgorithm) traversals.get(env);
			return results.getTraversalResults(obj);
		}
		else 
			return null;
	}
	
	public static void incrementTraversalHitCount(EObject obj) {
		TraversalResult res = getTraversalResults(obj);
		
		if (res!=null)
			res.incrementHitCount();
	}

	/**
	 * Parses a string and returns an error message if is not valid (as a
	 * string) or a SimpleNode AST of the code if it is. Does both syntax
	 * checking and type checking.
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
				if (!checker.isValidCondition()) {
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
	 * Removes the UcmEnvironment associated with the object from the global
	 * cache.
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
	 *            the root scenariodef from which we remove the children. we
	 *            also remove the parent from the list.
	 */
	private static void removeIncludedScenarios(List list, ScenarioDef parent) {
		for (Iterator iter = parent.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef child = (ScenarioDef) iter.next();
			removeIncludedScenarios(list, child);
		}

		if (list.contains(parent))
			list.remove(parent);
	}
	
	
	public static void setActiveScenario(ScenarioDef scenario)
	{
		try {
			if (scenario==null) {
				System.out.println("Use clearActiveScenario instead"); //$NON-NLS-1$
				return;
			}
			UcmEnvironment initial = getEnvironment(scenario);
			if (activeScenario.containsKey(initial))
				clearTraversalResults(initial);
			activeScenario.put(initial, scenario);
			UcmEnvironment forTraversal = (UcmEnvironment) initial.clone();
			
			DefaultScenarioTraversalAlgorithm algo = new DefaultScenarioTraversalAlgorithm(forTraversal, scenario);
			traversals.put(initial, algo);

			algo.traverse();
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} catch (TraversalException e) {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", e.getMessage()); //$NON-NLS-1$
		}
		
	}
		
	
}
