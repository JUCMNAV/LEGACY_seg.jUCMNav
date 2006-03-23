package seg.jUCMNav.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import seg.jUCMNav.tests.commands.JUCMNavCommandTests;
import seg.jUCMNav.tests.commands.JUCMNavGRLCommandTests;
import seg.jUCMNav.tests.progress.ProgressTests;

/**
 * Central location to test all of our test cases.
 * 
 * @author jkealey
 *  
 */
public class TestAllTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(JUCMNavCommandTests.class);
        suite.addTestSuite(JUCMNavGRLCommandTests.class);
        suite.addTestSuite(ProgressTests.class);
        return suite;
    }

}