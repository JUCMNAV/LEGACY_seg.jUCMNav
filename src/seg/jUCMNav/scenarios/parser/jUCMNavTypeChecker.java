package seg.jUCMNav.scenarios.parser;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.model.jUCMNavType;

public class jUCMNavTypeChecker {

    protected static jUCMNavType verifySemantics(SimpleNode root, UcmEnvironment env) {

        switch (root.getId()) {
        case jUCMNavParserTreeConstants.JJTSTART:
        case jUCMNavParserTreeConstants.JJTSTARTRESPONSIBILITY:
            return verifySemantics(((SimpleNode) root.jjtGetChild(0)), env);
        case jUCMNavParserTreeConstants.JJTCOMPOUNDSTATEMENT: {
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                if (!verifySemantics(node, env).equals(jUCMNavType.VOID)) {
                    throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidStatementsTypeError")); //$NON-NLS-1$
                }
            }

            return jUCMNavType.VOID;
        }
        case jUCMNavParserTreeConstants.JJTIFSTATEMENT: {
            if (!verifySemantics(((SimpleNode) root.jjtGetChild(0)), env).equals(jUCMNavType.BOOLEAN)) {
                throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.ConditionToIfStatementIsNotBoolean")); //$NON-NLS-1$
            }

            for (int i = 1; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                if (!verifySemantics(node, env).equals(jUCMNavType.VOID)) {
                    throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidStatementsTypeError")); //$NON-NLS-1$
                }
            }

            return jUCMNavType.VOID;
        }
        case jUCMNavParserTreeConstants.JJTASSIGNMENT: {
            SimpleNode node = ((SimpleNode) root.jjtGetChild(0));
            SimpleNode node2 = ((SimpleNode) root.jjtGetChild(1));
            jUCMNavType type = verifySemantics(node, env);
            jUCMNavType type2 = verifySemantics(node2, env);
            if (!type.equals(type2)) {
                throw new IllegalArgumentException(
                        Messages.getString("jUCMNavTypeChecker.CannotAssign") + type2.toString() + Messages.getString("jUCMNavTypeChecker.ToVariableOfType") + type.toString()); //$NON-NLS-1$ //$NON-NLS-2$
            }

            return jUCMNavType.VOID;
        }
        case jUCMNavParserTreeConstants.JJTIMPLICATION:
        case jUCMNavParserTreeConstants.JJTDISJUNCTION:
        case jUCMNavParserTreeConstants.JJTCONJUNCTION: {
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTEXCLUSIVEDISJUNCTION:
                case jUCMNavParserTreeConstants.JJTINCLUSIVEDISJUNCTION:
                    break;
                default:
                    if (!verifySemantics(node, env).equals(jUCMNavType.BOOLEAN)) {
                        throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidOperationTypeError")); //$NON-NLS-1$
                    }
                }
            }
            return jUCMNavType.BOOLEAN;
        }
        case jUCMNavParserTreeConstants.JJTCOMPARISON: {

            Object type1 = verifySemantics((SimpleNode) root.jjtGetChild(0), env);
            Object type2 = verifySemantics((SimpleNode) root.jjtGetChild(2), env);

            if (!type1.equals(type2)) {
                throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.EqualityInequalityMustBeAppliedToSameTypes")); //$NON-NLS-1$
            }
            for (int i = 3; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTEQUALITY:
                case jUCMNavParserTreeConstants.JJTINEQUALITY:
                    break;
                default:
                    if (!verifySemantics(node, env).equals(jUCMNavType.BOOLEAN)) {
                        throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidOPerationTypeError")); //$NON-NLS-1$
                    }
                }
            }
            return jUCMNavType.BOOLEAN;

        }
        case jUCMNavParserTreeConstants.JJTNEGATION: {
            if (!verifySemantics((SimpleNode) root.jjtGetChild(0), env).equals(jUCMNavType.BOOLEAN)) {
                throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.NegationCanOnlyBeAppliedToBooleans")); //$NON-NLS-1$
            }
            return jUCMNavType.BOOLEAN;
        }

        case jUCMNavParserTreeConstants.JJTRELATIONALEXPRESSION: {
            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTGREATEROREQUALTO:
                case jUCMNavParserTreeConstants.JJTGREATERTHAN:
                case jUCMNavParserTreeConstants.JJTLOWEROREQUALTO:
                case jUCMNavParserTreeConstants.JJTLOWERTHAN:
                    break;
                default:
                    if (!verifySemantics(node, env).equals(jUCMNavType.INTEGER)) {
                        throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidOperationTypeError")); //$NON-NLS-1$
                    }
                }
            }
            return jUCMNavType.BOOLEAN;
        }
        case jUCMNavParserTreeConstants.JJTADDITIVEEXPRESSION:
        case jUCMNavParserTreeConstants.JJTMULTIPLICATIVEEXPRESSION: {

            for (int i = 0; i < root.jjtGetNumChildren(); i++) {
                SimpleNode node = ((SimpleNode) root.jjtGetChild(i));
                switch (node.getId()) {
                case jUCMNavParserTreeConstants.JJTADDITION:
                case jUCMNavParserTreeConstants.JJTSUBSTRACTION:
                case jUCMNavParserTreeConstants.JJTMULTIPLICATION:
                    break;
                default:
                    if (!verifySemantics(node, env).equals(jUCMNavType.INTEGER)) {
                        throw new IllegalArgumentException(Messages.getString("jUCMNavTypeChecker.InvalidOperationTypeError")); //$NON-NLS-1$
                    }
                }
            }
            return jUCMNavType.INTEGER;
        }
        case jUCMNavParserTreeConstants.JJTBOOLEANCONSTANT:
            return jUCMNavType.BOOLEAN;
        case jUCMNavParserTreeConstants.JJTINTEGERCONSTANT:
            return jUCMNavType.INTEGER;
        case jUCMNavParserTreeConstants.JJTIDENTIFIER:
            return env.checkVariableExists(root);
        default:
            System.out.println(Messages.getString("jUCMNavTypeChecker.ErrorUnimplemented")); //$NON-NLS-1$
            return jUCMNavType.VOID;
        }

    }

    private UcmEnvironment env;

    private SimpleNode tree;
    private Object type;

    public jUCMNavTypeChecker(SimpleNode tree, UcmEnvironment env) {
        this.tree = tree;
        this.env = env;

        type = verifySemantics(tree, env);
    }

    protected boolean isBoolean(SimpleNode root, UcmEnvironment env) {
        return type.equals(jUCMNavType.BOOLEAN);
    }

    protected boolean isInteger(SimpleNode root, UcmEnvironment env) {
        return type.equals(jUCMNavType.INTEGER);
    }

    public boolean isValidInteger() {
        return isInteger(tree, env);
    }

    public boolean isValidCondition() {
        return isBoolean(tree, env);
    }

    public boolean isValidResponsibility() {
        return isVoid(tree, env);
    }

    protected boolean isVoid(SimpleNode root, UcmEnvironment env) {
        return type.equals(jUCMNavType.VOID);
    }
}
