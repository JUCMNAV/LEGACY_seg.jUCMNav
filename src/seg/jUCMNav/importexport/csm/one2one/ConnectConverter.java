package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.map.Connect;

/**
 * Creates the CSM representation(Sequence) of the Connect object.
 * 
 */

public class ConnectConverter implements AbstractConverter {

    private Connect connectNode;

    // constructors
    public ConnectConverter(Connect ep) {
        connectNode = ep;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        String name = "Connect"; //$NON-NLS-1$
        String successor = (String) target.get(0);
        String predecessor = (String) source.get(0);
        String hostDemand = "hostDemand=\"0\" "; //$NON-NLS-1$
        // at the moment, jUCMNav does not permit to set the description of a
        // Connect
        String description = ((connectNode.getDescription() != null)) ? EscapeUtils.escapeXML("description=\"" + connectNode.getDescription()) + "\" " : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        // object attributes
        String Object_attributes = "<Step id=\"" + "h" + connectNode.getId() + "\" name=\"" + name + "\" " + "predecessor=\"" + predecessor + "\" " + "successor=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                + successor + "\" " + hostDemand + description; //$NON-NLS-1$
        String traceabilityLink = "traceabilityLink=\"" + connectNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/> <!-- Connect -->"; //$NON-NLS-1$

        // Connect is converted to a Dummy Step pathConnAttribs it's no longer considered a
        // connection
        // patthConnAttribs.OptionalAttributes(connectNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }
}
