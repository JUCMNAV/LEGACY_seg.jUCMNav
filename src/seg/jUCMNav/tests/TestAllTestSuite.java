package seg.jUCMNav.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import seg.jUCMNav.tests.commands.JUCMNavCommandTests;
import seg.jUCMNav.tests.commands.JUCMNavGRLCommandTests;
import seg.jUCMNav.tests.commands.JUCMNavKPICommandTests;
import seg.jUCMNav.tests.progress.ProgressTests;
import seg.jUCMNav.tests.scenarios.ScenarioTraversalTests;
import seg.jUCMNav.tests.scenarios.jUCMNavParserTest;

/**
 * Central location to test all of our test cases.
 * 
 * @author jkealey, pchen
 * 
 */
public class TestAllTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(JUCMNavCommandTests.class);
        suite.addTestSuite(JUCMNavGRLCommandTests.class);
        suite.addTestSuite(JUCMNavKPICommandTests.class);
        suite.addTestSuite(jUCMNavParserTest.class);
        suite.addTestSuite(ProgressTests.class);
        suite.addTestSuite(ScenarioTraversalTests.class);
        return suite;
    }

}