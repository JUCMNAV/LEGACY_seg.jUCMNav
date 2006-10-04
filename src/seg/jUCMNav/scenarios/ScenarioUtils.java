package seg.jUCMNav.scenarios;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

/**
 * Utility class for UCM Scenarios.
 * 
 * @author jkealey
 * 
 */
public class ScenarioUtils {
	private static HashMap environments;
	private static HashMap traversals = new HashMap();
	private static HashMap activeScenario = new HashMap();
	// static reference to jUCMNavParser. can't have more than one reference to
	// the parser in the whole application.
	public static jUCMNavParser parser = new jUCMNavParser(new StringReader("true")); //$NON-NLS-1$
	public static final String sTypeBoolean = "boolean";
	public static final String sTypeEnumeration = "enumeration";

	public static final String sTypeInteger = "integer";

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
	
	public static int getTraversalHitCount(EObject obj) {
		TraversalResult res = getTraversalResults(obj);
		if (res!=null)
			return res.getHitCount();
		else
			return 0;
	}
	public static void incrementTraversalHitCount(EObject obj) {
		TraversalResult res = getTraversalResults(obj);
		
		if (res!=null)
			res.incrementHitCount();
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
	
	public static ScenarioDef getActiveScenario(EObject obj)
	{
		UcmEnvironment initial = getEnvironment(obj);
		if (activeScenario.containsKey(initial))
			return (ScenarioDef) activeScenario.get(initial);
		else
			return null;
	}
	public static void setActiveScenario(ScenarioDef scenario)
	{
		try {
			if (scenario==null) {
				System.out.println("Use clearActiveScenario instead");
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
	
	public static void clearActiveScenario(EObject obj)
	{
		UcmEnvironment initial = getEnvironment(obj);
		if (activeScenario.containsKey(initial))
			clearTraversalResults(initial);
		activeScenario.remove(initial);

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
		
	
}
