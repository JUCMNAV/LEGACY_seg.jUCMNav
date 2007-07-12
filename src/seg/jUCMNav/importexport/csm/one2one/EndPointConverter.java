package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.EndPoint;

/**
 * Creates the CSM representation(End) of the End-Point object.
 * 
 */
public class EndPointConverter implements AbstractConverter {
    private EndPoint endPointNode;

    // private PathNode source;
    PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public EndPointConverter(EndPoint ep) {
        endPointNode = ep;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        String mandatory_attribute = "<End id=\"" + "h" + endPointNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + endPointNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + mandatory_attribute + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/> <!-- EndPoint " + endPointNode.getName() + " -->"; //$NON-NLS-1$ //$NON-NLS-2$

        // optional attributes
        pathConnAttribs.OptionalAttributes(endPointNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }

}
