package seg.jUCMNav.scenarios;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
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
	// static reference to jUCMNavParser. can't have more than one reference to
	// the parser in the whole application.
	public static jUCMNavParser parser = new jUCMNavParser(new StringReader("true")); //$NON-NLS-1$
	public static final String sTypeBoolean = "boolean";
	public static final String sTypeInteger = "integer";
	public static final String sTypeEnumeration = "enumeration";

	private static HashMap environments;

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
	public static UcmEnvironment getEnvironment(EObject object) {
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
	 * Removes the UcmEnvironment associated with the object from the global
	 * cache.
	 * 
	 * @param object
	 */
	public static void releaseEnvironment(EObject object) {
		if (object == null)
			return;

		if (object instanceof URNspec) {
			getEnvironments().remove(object);
		} else
			releaseEnvironment(object.eContainer());
	}

	/**
	 * Global UcmEnvironment cache.
	 * 
	 * @return a HashMap of URNspec -> UcmEnvironment
	 */
	private static HashMap getEnvironments() {
		if (environments == null)
			environments = new HashMap();

		return environments;
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
		URNspec urn = parent.getGroup().getUcmspec().getUrnspec();
		List list = getAllScenarios(urn);

		removeIncludedScenarios(list, parent);
		return list;
	
	}
}
