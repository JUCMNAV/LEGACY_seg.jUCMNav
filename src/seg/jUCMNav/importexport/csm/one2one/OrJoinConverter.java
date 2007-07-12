package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.OrJoin;

/**
 * Creates the CSM representation(Merge) of the OrJoin object.
 * 
 */
public class OrJoinConverter implements AbstractConverter {

    private OrJoin orJoinNode;

    PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public OrJoinConverter(OrJoin oj) {
        orJoinNode = oj;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String madatory_attribute = "<Merge id=\"" + "h" + orJoinNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + orJoinNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + madatory_attribute + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/>"; //$NON-NLS-1$

        // optional attributes
        pathConnAttribs.OptionalAttributes(orJoinNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }
}
