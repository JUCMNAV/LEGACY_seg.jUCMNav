package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.Timer;

/**
 * Creates the CSM representation(Sequence) of the Timer object.
 * 
 */

public class TimerConverter implements AbstractConverter {

    private Timer timerNode;

    private PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public TimerConverter(Timer ep) {
        timerNode = ep;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        String Object_attributes;
        String traceabilityLink;
        String closing_attribute;
        // object attributes vary according to the type of Timer
        // needs a Join
        if (source.size() > 1) {
            // no other need
            if (target.size() == 1) {
                // join
                Object_attributes = "<Join id=\"" + "h" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                traceabilityLink = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
                closing_attribute = "/> <!-- Timer -->"; //$NON-NLS-1$
                pathConnAttribs.OptionalAttributes(timerNode, ps, source, target);

                // output to file
                ps.println(closing_attribute);
                ps.flush();
                // requires Join; DummyStep; Branch
            } else {
                // join
                Object_attributes = "<Join id=\"" + "h" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                traceabilityLink = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
                closing_attribute = "/> <!-- Timer 1/3 -->"; //$NON-NLS-1$
                ArrayList tgt = new ArrayList();
                tgt.add("h" + timerNode.getId() + "_t"); //$NON-NLS-1$ //$NON-NLS-2$
                pathConnAttribs.OptionalAttributes(timerNode, ps, source, tgt);

                // output to file
                ps.println(closing_attribute);
                ps.flush();
                // DummyStep
                ArrayList tgt2 = new ArrayList();
                tgt2.add("h" + timerNode.getId() + "__t"); //$NON-NLS-1$ //$NON-NLS-2$
                String Object_attributes2 = "<Step id=\"" + "h" + timerNode.getId() + "_t\" name=\"Timer\" predecessor=\"" + "h" + timerNode.getId() + "\" successor=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                        + tgt2.get(0) + "\" "; //$NON-NLS-1$
                String traceabilityLink2 = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes2 + traceabilityLink2); //$NON-NLS-1$
                String closing_attribute2 = "/> <!-- Timer 2/3 -->"; //$NON-NLS-1$

                // output to file
                ps.println(closing_attribute2);
                ps.flush();

                // Branch
                String Object_attributes3 = "<Branch id=\"" + "h" + timerNode.getId() + "__t\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String traceabilityLink3 = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes3 + traceabilityLink3); //$NON-NLS-1$
                String closing_attribute3 = "/> <!-- Timer 3/3 -->"; //$NON-NLS-1$
                pathConnAttribs.OptionalAttributes(timerNode, ps, tgt, target);

                // output to file
                ps.println(closing_attribute3);
                ps.flush();
            }
        } else {
            // replace by a sequence
            if (target.size() == 1) {
                // sequence
                Object_attributes = "<Sequence id=\"" + "h" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                traceabilityLink = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
                closing_attribute = "/> <!-- Timer -->"; //$NON-NLS-1$
                pathConnAttribs.OptionalAttributes(timerNode, ps, source, target);

                // output to file
                ps.println(closing_attribute);
                ps.flush();
                // implement as a Branch
            } else {
                // branch
                Object_attributes = "<Branch id=\"" + "h" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                traceabilityLink = "traceabilityLink=\"" + timerNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
                closing_attribute = "/> <!-- Timer -->"; //$NON-NLS-1$
                pathConnAttribs.OptionalAttributes(timerNode, ps, source, target);

                // output to file
                ps.println(closing_attribute);
                ps.flush();
            }
        }

    }
}
