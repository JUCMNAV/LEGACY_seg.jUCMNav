package seg.jUCMNav.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import seg.jUCMNav.tests.commands.JUCMNavCommandTests;
import seg.jUCMNav.tests.progress.ProgressTests;

/**
 * Created on 16-Apr-2005
 * 
 * @author jkealey
 *
 */
public class TestAllTestSuite {

	public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(JUCMNavCommandTests.class);
        suite.addTestSuite(ProgressTests.class);
        return suite;
    }
    

}
