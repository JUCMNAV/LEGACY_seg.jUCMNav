package seg.jUCMNav.scenarios;

import java.io.StringReader;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;

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
}
