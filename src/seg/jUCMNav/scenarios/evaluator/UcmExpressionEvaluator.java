package seg.jUCMNav.scenarios.evaluator;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.model.jUCMNavType;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParserTreeConstants;

/**
 * Evaluates a parsed javacc/jjtree in a certain {@link UcmEnvironment}
 * 
 * @author jkealey
 * 
 */
public class UcmExpressionEvaluator {
    /**
     * Evaluates a parsed javacc/jjtree in a certain {@link UcmEnvironment}
     * 
     * @param root
     *            the result of a javacc/jjtree parsing
     * @param env
     *            the environment
     * @return the parsed result (Boolean, Integer, {@link jUCMNavType}, null)
     */
    public static UcmExpressionValue evaluate(SimpleNode root, UcmEnvironment env) {
        switch (root.getId()) {
        case jUCMNavParserTreeConstants.JJTSTART:
            return evaluate(((SimpleNode) root.jjtGetChild(0)), env);
        case jUCMNavParserTreeConstants.JJTIFSTATEMENT: {
            boolean result = evaluate(((SimpleNode) root.jjtGetChild(0)), env).booleanValue();

            if (result)
                evaluate(((SimpleNode) root.jjtGetChild(1)), env);
            else if (root.jjtGetNumChildren() == 3)
                evaluate(((SimpleNode) root.jjtGetChild(2)), env);

            return null;
        }
        case jUCMNavParserTreeConstants.JJTSTARTRESPONSIBILITY:
        case jUCMNavParserTreeConstants.JJTCOMPOUNDSTATEMENT: {
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                evaluate(node, env);
            }
            return null;
        }
        case jUCMNavParserTreeConstants.JJTASSIGNMENT: {
            SimpleNode node = ((SimpleNode) root.jjtGetChild(0));
            UcmExpressionValue value = evaluate(((SimpleNode) root.jjtGetChild(1)), env);

            // System.out.println("Setting " + node.getText() + " to " + value );
            env.setValue(node.getText(), value);

            return null;
        }
        case jUCMNavParserTreeConstants.JJTIMPLICATION:
        case jUCMNavParserTreeConstants.JJTDISJUNCTION:
        case jUCMNavParserTreeConstants.JJTCONJUNCTION: {

            boolean result = evaluate(((SimpleNode) root.jjtGetChild(0)), env).booleanValue();
            int operation = root.getId();

            for (int i = 1; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));

                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTEXCLUSIVEDISJUNCTION:
                case jUCMNavParserTreeConstants.JJTINCLUSIVEDISJUNCTION: {
                    operation = node.getId();
                    break;
                }
                default:
                    boolean result2 = evaluate(node, env).booleanValue();
                    result = applyBooleanOperation(operation, result, result2);
                }

            }

            return new UcmExpressionValue(result);
        }
        case jUCMNavParserTreeConstants.JJTCOMPARISON: {

            Object type1 = evaluate((SimpleNode) root.jjtGetChild(0), env);
            int operation = ((SimpleNode) root.jjtGetChild(1)).getId();
            Object type2 = evaluate((SimpleNode) root.jjtGetChild(2), env);
            boolean result;

            if (operation == jUCMNavParserTreeConstants.JJTEQUALITY)
                result = type1.equals(type2);
            else
                result = !type1.equals(type2);

            for (int i = 3; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTEQUALITY:
                case jUCMNavParserTreeConstants.JJTINEQUALITY: {
                    operation = node.getId();
                    break;
                }
                default:
                    boolean result2 = evaluate(node, env).booleanValue();
                    if (operation == jUCMNavParserTreeConstants.JJTEQUALITY)
                        result = (result == result2);
                    else
                        result = (result != result2);
                }
            }
            return new UcmExpressionValue(result);
        }
        case jUCMNavParserTreeConstants.JJTNEGATION: {

            boolean result = evaluate(((SimpleNode) root.jjtGetChild(0)), env).booleanValue();
            return new UcmExpressionValue(!result);
        }

        case jUCMNavParserTreeConstants.JJTRELATIONALEXPRESSION: {
            int value1 = evaluate(((SimpleNode) root.jjtGetChild(0)), env).intValue();
            int value2 = evaluate(((SimpleNode) root.jjtGetChild(2)), env).intValue();
            int operation = ((SimpleNode) root.jjtGetChild(1)).getId();
            boolean result = false;
            switch (operation) {
            case jUCMNavParserTreeConstants.JJTGREATEROREQUALTO: {
                result = value1 >= value2;
                break;
            }
            case jUCMNavParserTreeConstants.JJTGREATERTHAN: {
                result = value1 > value2;
                break;
            }
            case jUCMNavParserTreeConstants.JJTLOWEROREQUALTO: {
                result = value1 <= value2;
                break;
            }
            case jUCMNavParserTreeConstants.JJTLOWERTHAN: {
                result = value1 < value2;
                break;
            }
            default:
                throw new IllegalArgumentException(Messages.getString("UcmExpressionEvaluator.UnknownIntegerComparison")); //$NON-NLS-1$
            }

            return new UcmExpressionValue(result);
        }
        case jUCMNavParserTreeConstants.JJTADDITIVEEXPRESSION: {

            int result = 0;
            int operation = -1;
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTADDITION:
                case jUCMNavParserTreeConstants.JJTSUBSTRACTION: {
                    operation = node.getId();
                    break;
                }
                default: {
                    int result2 = evaluate(node, env).intValue();

                    switch (operation) {
                    case -1: {
                        result = result2;
                        break;
                    }
                    case jUCMNavParserTreeConstants.JJTADDITION: {
                        result += result2;
                        break;
                    }
                    case jUCMNavParserTreeConstants.JJTSUBSTRACTION: {
                        result -= result2;
                        break;

                    }
                    }
                }
                }
            }

            return new UcmExpressionValue(result);
        }
        case jUCMNavParserTreeConstants.JJTMULTIPLICATIVEEXPRESSION: {

            int result = 1;
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTADDITION: {
                    break;
                }
                case jUCMNavParserTreeConstants.JJTSUBSTRACTION: {
                    result *= -1;
                    break;
                }
                case jUCMNavParserTreeConstants.JJTMULTIPLICATION: {
                    break;
                }
                default: {
                    int result2 = evaluate(node, env).intValue();

                    result *= result2;
                }
                }
            }

            return new UcmExpressionValue(result);
        }
        case jUCMNavParserTreeConstants.JJTBOOLEANCONSTANT:
            return  new UcmExpressionValue(Boolean.valueOf(root.getText()).booleanValue());
        case jUCMNavParserTreeConstants.JJTINTEGERCONSTANT:
            return  new UcmExpressionValue(Integer.valueOf(root.getText()).intValue());
        case jUCMNavParserTreeConstants.JJTIDENTIFIER:
            return (UcmExpressionValue) env.getValue(root.getText(), true);
        case jUCMNavParserTreeConstants.JJTEXCLUSIVEDISJUNCTION:
        case jUCMNavParserTreeConstants.JJTINCLUSIVEDISJUNCTION:
        case jUCMNavParserTreeConstants.JJTADDITION:
        case jUCMNavParserTreeConstants.JJTSUBSTRACTION:
        case jUCMNavParserTreeConstants.JJTMULTIPLICATION:
        case jUCMNavParserTreeConstants.JJTGREATEROREQUALTO:
        case jUCMNavParserTreeConstants.JJTGREATERTHAN:
        case jUCMNavParserTreeConstants.JJTLOWEROREQUALTO:
        case jUCMNavParserTreeConstants.JJTLOWERTHAN:
        case jUCMNavParserTreeConstants.JJTEQUALITY:
        case jUCMNavParserTreeConstants.JJTINEQUALITY:
            return new UcmExpressionValue(jUCMNavType.VOID);
        default:
            System.out.println(Messages.getString("UcmExpressionEvaluator.ErrorUnimplemented")); //$NON-NLS-1$
            return new UcmExpressionValue(jUCMNavType.VOID);
        }

    }

    /**
     * Applies boolean operations represented in the parsed object tree using Java constructs.
     * 
     * @param operation
     *            a {@link jUCMNavParserTreeConstants} representing a boolean operator
     * @param result
     *            the first parameter
     * @param result2
     *            the second parameter
     * @return the boolean result
     */
    private static boolean applyBooleanOperation(int operation, boolean result, boolean result2) {
        switch (operation) {
        case jUCMNavParserTreeConstants.JJTEXCLUSIVEDISJUNCTION: {
            result = result ^ result2;
            break;
        }
        case jUCMNavParserTreeConstants.JJTINCLUSIVEDISJUNCTION: {
            result = result || result2;
            break;
        }
        case jUCMNavParserTreeConstants.JJTCONJUNCTION: {
            result = result && result2;
            break;
        }
        case jUCMNavParserTreeConstants.JJTIMPLICATION: {
            result = !(result && !result2);
            break;
        }
        default: {
            throw new IllegalArgumentException(Messages.getString("UcmExpressionEvaluator.UnknownOperation")); //$NON-NLS-1$

        }
        }
        return result;
    }
}
