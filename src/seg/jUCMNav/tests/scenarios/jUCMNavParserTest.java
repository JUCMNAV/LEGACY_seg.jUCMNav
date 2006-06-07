package seg.jUCMNav.tests.scenarios;

import java.io.StringReader;

import junit.framework.TestCase;
import seg.jUCMNav.scenarios.evaluator.UcmExpressionEvaluator;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;

/**
 * Test cases for jucmlogic.jjt
 * 
 * This test suite contains a few simple tests for expression parsing.
 * 
 * Usual test syntax: parse("expression");
 * 
 * If you know this test should fail (because of invalid syntax), set shouldFail to true before invoking parse()
 * 
 */
public class jUCMNavParserTest extends TestCase {
    static jUCMNavParser parser = new jUCMNavParser(new StringReader("true"));
    static int i = 0;
    boolean shouldFail;
    boolean shouldFailTypeCheck;
    boolean result;
    UcmEnvironment env;

    protected void setUp() throws Exception {
        super.setUp();
        shouldFail = false;

        env = new UcmEnvironment();
        env.registerBoolean("jason", true);
        env.registerBoolean("kealey", true);
        env.registerBoolean("daigle", true);
        env.registerBoolean("jasonKealey", true);
        env.registerBoolean("w", false);
        env.registerBoolean("x", false);
        env.registerBoolean("y", false);
        env.registerBoolean("z", false);
        env.registerInteger("iJason", 2);
        env.registerInteger("iKealey", -3);
        env.registerInteger("i", 3);
        env.registerInteger("j", 4);
        env.registerInteger("k", 0);

        env.registerEnumeration("ApplicationStates", new String[] { "INITIAL", "WORKING", "DONE" });
        env.registerEnumeration("AdmissionProcessStates", new String[] { "INITIAL", "REVIEWING", "INVITED", "DENIED" });

        env.registerEnumerationInstance("ApplicationStates", "appState");
        env.registerEnumerationInstance("AdmissionProcessStates", "admissionState");

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected SimpleNode parse(String str) {
        return parse(str, false);
    }

    protected SimpleNode parseResponsibility(String str) {
        return parse(str, true);
    }

    protected SimpleNode parse(String str, boolean isResponsibility) {
        System.out.println("----------(test: " + ++i + ")------------\n\n");
        System.out.println(str);
        SimpleNode n = null;
        try {

            jUCMNavParser.ReInit(new StringReader(str));
            if (isResponsibility)
                n = jUCMNavParser.StartResponsibility();
            else
                n = jUCMNavParser.Start();
            n.dump("");

        } catch (Throwable t) {
            System.out.println("Parser error occurred.");
            System.out.println(t.getMessage());
            // t.printStackTrace();

            // JUnit tests failed, if is not what is predicted
            assertTrue(shouldFail);
        }

        if (!shouldFail) {
            try {
                jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);
                if (isResponsibility)
                    assertEquals("Is not a valid responsibility.", checker.isValidResponsibility(), !shouldFailTypeCheck);
                else
                    assertEquals("Is not a valid condition.", checker.isValidCondition(), !shouldFailTypeCheck);
            } catch (IllegalArgumentException ex) {
                System.out.println("Type checker error occurred.");
                System.out.println(ex.getMessage());

                assertTrue(shouldFailTypeCheck);
            }
        }

        if (!isResponsibility && !shouldFail && !shouldFailTypeCheck) {
            try {
                boolean actualresult = ((Boolean) UcmExpressionEvaluator.evaluate(n, env)).booleanValue();
                System.out.println("Evaluates to: ");
                n.dumpEvaluation("", env);
                assertEquals(result, actualresult);

            } catch (Throwable t) {
                System.out.println("Evaluation error occurred.");
                System.out.println(t.getMessage());
                fail();
            }
        } else if (isResponsibility && !shouldFail && !shouldFailTypeCheck) {
            try {
                System.out.println("Evaluates to: ");
                UcmExpressionEvaluator.evaluate(n, env);
            } catch (Throwable t) {
                System.out.println("Evaluation error occurred.");
                System.out.println(t.getMessage());
                fail();
            }
        }

        return n;
    }

    /*
     * 
     * START OF BOOLEAN ONLY TESTS
     * 
     */
    public void testBoolean() {
        result = true;
        parse("true");

    }

    public void testVariable() {
        result = true;
        parse("jasonKealey");
    }

    public void testNegation() {
        result = false;
        parse("not true");
        parse("! true");
    }

    public void testNegation2() {
        result = false;
        parse("not(true)");
        parse("!(true)");
    }

    public void testNegation3() {
        result = true;
        parse("not not(true)");
        parse("! !(true)");
    }

    public void testConjunction() {
        result = false;
        parse("true and false");
        parse("true && false");
    }

    public void testConjunction2() {
        result = false;
        parse("(true and false ) and x");
        parse("(true && false) && x");
    }

    public void testConjunction3() {
        result = false;
        parse("true and false and x");
        parse("true && false && x");
    }

    public void testConjunction4() {
        result = false;
        parse("true and (not y) and x");
        parse("true && (! y) && x");
    }

    public void testConjunction5() {
        result = false;
        parse("not true and (not y) and x");
        parse("! true && (!y) && x");
    }

    public void testDisjunction() {
        result = true;
        parse("false or true");
        parse("false || true");
    }

    public void testDisjunction2() {
        result = false;
        parse("x and y or z and w");
        parse("x && y || z && w");
    }

    public void testDisjunction3() {
        result = false;
        parse("x and (y or z) and w");
        parse("x && (y || z) && w");
    }

    public void testImplication() {
        result = true;
        parse("x => y");
    }

    public void testImplication2() {
        result = true;
        parse("x => y or z");
        parse("x => y || z");
    }

    public void testImplication3() {
        result = true;
        parse("not x => x => z");
        parse("!x => x => z");
    }

    public void testParenthesis() {
        result = false;
        parse("((((x))))");
    }

    public void testXOR1() {
        result = false;
        parse("x xor y");
        parse("x ^ y");
    }

    public void testXOR2() {
        result = false;
        parse("x xor y xor z");
        parse("x ^ y ^ z");
    }

    public void testXOR3() {
        result = true;
        parse("x and y or z xor w and jason xor kealey or daigle");
        parse("x && y || z ^ w && jason ^ kealey || daigle");
    }

    public void testComments() {
        result = true;
        parse("daigle => /* jason */ kealey");
    }

    public void testComments2() {
        result = true;
        parse("daigle => kealey // kealey\n");
    }

    // public void testComments3() {
    // // doesn't work without EOL right now.
    // parse("daigle => kealey // kealey");
    // }

    public void testEquality() {
        result = true;
        parse("jason=kealey");
        parse("jason==kealey");
    }

    public void testEquality2() {
        result = true;
        parse("(jason=kealey) /= not daigle");
        parse("(jason==kealey) != !daigle");
    }

    public void testEquality3() {
        // do not need need parenthesis
        result = true;
        parse("jason=kealey /= not daigle");
        parse("jason==kealey != ! daigle");
    }

    public void testEquality4() {
        result = false;
        parse("jason==kealey==w!=y");
    }

    public void testEquality5() {
        result = false;
        parse("jason==(kealey==(w!=y))");
    }

    /*
     * 
     * END OF BOOLEAN ONLY TESTS
     * 
     */

    /*
     * 
     * START OF INVALID SYNTAX TESTS
     * 
     */

    public void testInvalidSyntax1() {
        shouldFail = true;
        parse("(x");
    }

    public void testInvalidSyntax2() {
        shouldFail = true;
        parse("=> x");
    }

    public void testInvalidSyntax3() {
        shouldFail = true;
        parse("jason => kealey kealey");
    }

    public void testInvalidSyntax4() {
        shouldFail = true;
        parse("jason => kealey /* kealey");
    }

    public void testInvalidSyntax5() {
        shouldFail = true;
        parse("jason <> kealey");
    }

    public void testInvalidSyntax6() {
        shouldFail = true;
        parse("jason >< kealey");
    }

    public void testInvalidSyntax7() {
        shouldFail = true;
        parse("jason << kealey");
    }

    public void testInvalidSyntax8() {
        shouldFail = true;
        parse("jason >> kealey");
    }

    /*
     * 
     * END OF INVALID SYNTAX TESTS
     * 
     */

    /*
     * 
     * START OF INTEGER ONLY TESTS
     * 
     */

    public void testRelationalExpression1() {
        result = false;
        parse("iJason<iKealey");

    }

    public void testRelationalExpression2() {
        result = true;
        parse("i <= j");
    }

    public void testRelationalExpression3() {
        result = true;
        parse("iJason > iKealey");
    }

    public void testRelationalExpression4() {
        result = true;
        parse("iJason >= iKealey");
    }

    public void testRelationalExpression5() {
        result = false;
        parse("1 >= 8");
    }

    public void testRelationalExpression6() {
        result = true;
        parse("0 < 8");
    }

    public void testRelationalExpression7() {
        result = false;
        parse("-j > 0");
    }

    public void testComparison1() {
        result = true;
        parse("1=(1)");
    }

    public void testComparison2() {
        result = true;
        parse(" -(1+2) != -1 ");
    }

    public void testComparison3() {
        result = true;
        parse(" 1 == 1 == true != false ");
    }

    public void testAddition1() {
        result = true;
        parse("1+1=2");
    }

    public void testAddition2() {
        result = true;
        parse("1-1/=2");
    }

    public void testAddition3() {
        result = false;
        parse("0+-1==3");
    }

    public void testAddition4() {
        result = true;
        parse("0-+1!=5");
    }

    public void testAddition5() {
        result = true;
        parse("2+3+4++5>=0");
    }

    public void testAddition6() {
        result = true;
        parse("-1<2");
    }

    public void testAddition7() {
        result = false;
        parse("3++3--2==i");
    }

    public void testAddition8() {
        result = false;
        parse("-iJason=iKealey");
    }

    public void testAddition9() {
        result = true;
        parse("-iJason+iKealey/=3");
    }

    public void testAddition10() {
        result = false;
        parse("(iJason+iKealey)-3=0");
    }

    public void testMultiplication1() {
        result = true;
        parse("2*3/=3");
    }

    public void testMultiplication2() {
        result = true;
        parse("2*3+2/=3");
    }

    public void testMultiplication3() {
        result = true;
        parse("2+3*2/=3");
    }

    public void testMultiplication4() {
        result = true;
        parse("2*3+2/=3=true");
    }

    public void testMultiplication5() {
        result = true;
        parse("2*-3+2/=3=true");
    }

    /*
     * 
     * END OF INTEGER ONLY TESTS
     * 
     */

    /*
     * 
     * START OF ENUMERATION ONLY TESTS
     * 
     */

    public void testEnum1() {
        result = false;
        parse("appState == DONE");
    }

    public void testEnum2() {
        result = true;
        parse("appState == INITIAL"); // naming conflict
        result = false;
        parse("appState != INITIAL"); // naming conflict

    }

    public void testEnum3() {
        result = false;
        parse("admissionState == DENIED");
        result = true;
        parse("admissionState != DENIED");

    }

    public void testEnum4() {
        result = true;
        parse("admissionState == INITIAL"); // naming conflict
    }

    public void testEnum5() {
        result = true;
        parse("appState == appState");
    }

    public void testEnum6() {
        result = true;
        parse("DONE==DONE");
    }

    public void testEnum7() {
        result = true;
        parse("INITIAL == INITIAL"); // naming conflict
    }

    /*
     * 
     * END OF ENUMERATION ONLY TESTS
     * 
     */

    /*
     * 
     * START OF MIXED TESTS
     * 
     */

    public void testComplex1() {
        result = false;
        parse("(iKealey+1=0)and (iKealey-iJason/=0)");
    }

    public void testComplex2() {
        result = false;
        parse("iKealey+1=0 and iKealey-iJason/=0");
    }

    public void testComplex3() {
        result = false;
        parse("true == false != (1 == 0) ");
    }

    public void testComplex4() {
        result = false;
        parse("i == 213 != (1 == 0) ");
    }

    public void testComplex5() {
        result = false;
        parse("0++i*-j==0 && jason");
    }

    /*
     * 
     * END OF MIXED TESTS
     * 
     */

    /*
     * 
     * START OF TYPE CHECKING TESTS
     * 
     */
    public void testInvalidRelationalExpression1() {
        shouldFail = true;
        parse("jason > false");
    }

    public void testInvalidRelationalExpression2() {
        shouldFail = true;
        parse("true > false");
    }

    public void testInvalidRelationalExpression3() {
        shouldFail = true;
        parse("true > 1");
    }

    public void testInvalidAddition1() {
        shouldFail = true;
        parse("false + 1");
    }

    public void testInvalidAddition2() {
        shouldFail = true;
        parse("1+++++++0---094194++3-kealey/=0");
    }

    public void testInvalidAddition3() {
        shouldFail = true;
        parse("3++-3-2");
    }

    public void testInvalidAddition4() {
        shouldFailTypeCheck = true;
        parse("0+-1");
    }

    public void testInvalidAddition5() {
        shouldFailTypeCheck = true;
        parse("0-+1");
    }

    public void testToBeHandledByTypeChecker1() {
        shouldFailTypeCheck = true;
        parse("(x or y) + 1 == 0");
    }

    public void testToBeHandledByTypeChecker2() {
        shouldFailTypeCheck = true;
        parse("(x + 1) or y == i");
    }

    public void testToBeHandledByTypeChecker3() {
        shouldFailTypeCheck = true;
        parse("1+kealey");
    }

    public void testToBeHandledByTypeChecker4() {
        shouldFailTypeCheck = true;
        parse("0");
    }

    public void testToBeHandledByTypeChecker5() {
        shouldFailTypeCheck = true;
        parse("true == false != 1");
    }

    public void testToBeHandledByTypeChecker6() {
        shouldFailTypeCheck = true;
        parse("1 == 2 != 1");
    }

    public void testToBeHandledByTypeChecker7() {
        shouldFailTypeCheck = true;
        parse("(iKealey+1=0)and (kealey-jason/=0)");
    }

    public void testToBeHandledByTypeChecker8() {
        shouldFailTypeCheck = true;
        parse("true=1");
    }

    public void testToBeHandledByTypeChecker9() {
        shouldFailTypeCheck = true;
        parse("(iKealey+1 and iKealey-iJason)/=0");
    }

    public void testEnumInvalid1() {
        shouldFailTypeCheck = true;
        parse("appState == admissionState");
    }

    public void testEnumInvalid2() {
        shouldFailTypeCheck = true;
        parse("DONE == DENIED");
    }

    /*
     * 
     * END OF TYPE CHECKING TESTS
     * 
     */

    /*
     * 
     * START OF RESPONSIBILITY TESTS
     * 
     */
    public void testAssignment1() {
        parseResponsibility("x:=true;");
        parseResponsibility("x=true;");
        assertEquals(env.getValue("x"), Boolean.TRUE);
    }

    public void testAssignment2() {
        parseResponsibility("iJason=iKealey;");
        assertEquals(env.getValue("iJason"), new Integer(-3));
    }

    public void testAssignment3() {
        parseResponsibility("x=!y;");
        assertEquals(env.getValue("x"), Boolean.TRUE);
    }

    public void testAssignment4() {
        parseResponsibility("x=(true && !(false || x xor y));");
        assertEquals(env.getValue("x"), Boolean.TRUE);
    }

    public void testAssignment5() {
        parseResponsibility("appState=DONE;");
        assertEquals(env.getValue("appState"), env.getValue("DONE"));
    }

    public void testAssignment6() {
        testAssignment5();
        parseResponsibility("appState=INITIAL;"); // naming ambiguity
        assertEquals(env.getValue("appState"), env.getValue("INITIAL"));
    }

    public void testInvalidAssignment1() {
        shouldFail = true;
        parseResponsibility("x=true");
    }

    public void testInvalidAssignment2() {
        shouldFailTypeCheck = true;
        parseResponsibility("x=1;");
    }

    public void testInvalidAssignment3() {
        shouldFail = true;
        parseResponsibility("x==true;");
    }

    public void testInvalidAssignment4() {
        shouldFailTypeCheck = true;
        parseResponsibility("x=iJason;");
    }

    public void testInvalidAssignment5() {
        shouldFailTypeCheck = true;
        parseResponsibility("appState=admissionState;");
    }

    public void testInvalidAssignment6() {
        shouldFailTypeCheck = true;
        parseResponsibility("appState=INVITED;");
    }

    public void testIfStatement1() {
        parseResponsibility("if (iJason==2) i=-iJason;");
        parseResponsibility("if (iJason==2)\n i=-iJason;");
        parseResponsibility("if iJason==2\n i=-iJason;");
        parseResponsibility("if iJason==2 i=-iJason;");
        assertEquals(env.getValue("i"), new Integer(-2));
    }

    public void testIfStatement2() {
        parseResponsibility("if (iJason==3) x=y; else iJason=i;");
        assertEquals(env.getValue("iJason"), new Integer(3));
        
    }

    public void testIfStatement3() {
        parseResponsibility("if (iJason==3) { x=y; } else {iJason=i;}");
        parseResponsibility("if (iJason==3) {\n x=y; \n} else {\n iJason=i;\n}");
        assertEquals(env.getValue("iJason"), new Integer(3));
        
    }

    public void testIfStatement4() {
        // last two assignments are not in if
        parseResponsibility("if (x) i=iJason;iJason=iKealey;iKealey=i;");
        assertEquals(env.getValue("iJason"), new Integer(-3));
        assertEquals(env.getValue("iKealey"), new Integer(3));
        assertEquals(env.getValue("i"), new Integer(3));
    }

    public void testIfStatement5() {
        parseResponsibility("if (x) { i=iJason;iJason=iKealey;iKealey=i; }");
        assertEquals(env.getValue("iJason"), new Integer(2));
        assertEquals(env.getValue("iKealey"), new Integer(-3));
        assertEquals(env.getValue("i"), new Integer(3));        
    }

    public void testIfStatement6() {
        parseResponsibility("if (!x) { i=iJason;iJason=iKealey;iKealey=i; } else x=true;");
        assertEquals(env.getValue("iJason"), new Integer(-3));
        assertEquals(env.getValue("iKealey"), new Integer(2));
        assertEquals(env.getValue("i"), new Integer(2));
        assertEquals(env.getValue("x"), Boolean.FALSE);  
    }

    public void testInvalidIfStatement1() {
        shouldFailTypeCheck = true;
        parseResponsibility("if (iJason==3) x=iJason;");
        parseResponsibility("if (iJason==3)\n x=iJason;");
        parseResponsibility("if iJason==3\n x=iJason;");
        parseResponsibility("if iJason==3 x=iJason;");
    }

    public void testInvalidIfStatement2() {
        shouldFail = true;
        parseResponsibility("if (iJason==3) x=iJason");
    }

    public void testInvalidIfStatement3() {
        shouldFail = true;
        parseResponsibility("if iJason==3 else x=iJason;");
    }

    public void testInvalidIfStatement4() {
        // else not in if
        shouldFail = true;
        parseResponsibility("if (!x) i=iJason;iJason=iKealey;iKealey=i; else i=0;");
    }

    public void testSequence1() {
        parseResponsibility("i=1;j=2;k=3; { iJason=i+j*k; }");
        assertEquals(env.getValue("i"), new Integer(1));  
        assertEquals(env.getValue("j"), new Integer(2));
        assertEquals(env.getValue("k"), new Integer(3));
        assertEquals(env.getValue("iJason"), new Integer(7));
        
    }

    public void testComplexResponsibility1() {
        parseResponsibility("iJason=3;\n if (iJason==3) {\n iJason=iJason+1;\n x=true; \n} else if (iJason=(-4))\n iJason:=0;  \nelse\n iJason=0;");
        assertEquals(env.getValue("iJason"), new Integer(4));
        assertEquals(env.getValue("x"), Boolean.TRUE);
    }
    /*
     * 
     * END OF RESPONSIBILITY TESTS
     * 
     */

}
