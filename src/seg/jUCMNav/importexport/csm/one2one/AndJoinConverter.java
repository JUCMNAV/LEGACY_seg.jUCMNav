package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.AndJoin;

/**
 * Creates the CSM representation(Join) of the AndJoin object.
 * 
 */
public class AndJoinConverter implements AbstractConverter {

    private AndJoin andJoinNode;

    private PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public AndJoinConverter(AndJoin aj) {
        andJoinNode = aj;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String object_attributes = "<Join id=\"" + "h" + andJoinNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + andJoinNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        // output to file
        ps.print("			" + object_attributes + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/>"; //$NON-NLS-1$

        // optional attributes
        pathConnAttribs.OptionalAttributes(andJoinNode, ps, source, target);

        ps.println(closing_attribute);
        ps.flush();

    }
}
