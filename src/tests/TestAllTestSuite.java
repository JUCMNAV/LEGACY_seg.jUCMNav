package tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import tests.commands.JUCMNavCommandTests;

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
        return suite;
    }
    

}
