package seg.jUCMNav.scenarios.parser;

import seg.jUCMNav.scenarios.evaluator.UcmExpressionEvaluator;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.model.jUCMNavType;

/**
 * This file contains what has been changed in SimpleNode, in case something happens.
 * 
 * @author jkealey
 * 
 */
public class jUCMNavNode extends SimpleNode {

    public jUCMNavNode(int i) {
        super(i);
    }

    String m_text;

    /**
     * 
     * @param text
     *            the token
     */
    public void setText(String text) {
        m_text = text;
    }

    /**
     * 
     * @return the actual token
     */
    public String getText() {
        return m_text;
    }

    public void dump(String prefix) {
        if (m_text != null && m_text.length() > 0)
            System.out.println(toString(prefix) + "[" + m_text + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        else
            System.out.println(toString(prefix));
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode) children[i];
                if (n != null) {
                    n.dump(prefix + " "); //$NON-NLS-1$
                }
            }
        }
    }

    public void dumpEvaluation(String prefix, UcmEnvironment env) {
        Object eval = UcmExpressionEvaluator.evaluate(this, env);
        if (eval == jUCMNavType.VOID)
            eval = ""; //$NON-NLS-1$
        if (m_text != null && m_text.length() > 0)
            System.out.println(toString(prefix) + "[" + m_text + "] == " + eval); //$NON-NLS-1$ //$NON-NLS-2$
        else
            System.out.println(toString(prefix) + " == " + eval); //$NON-NLS-1$
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode) children[i];
                if (n != null) {
                    n.dumpEvaluation(prefix + " ", env); //$NON-NLS-1$
                }
            }
        }
    }
}
