package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.OrFork;

/**
 * Creates the CSM representation(Branch) orForkNode the OrFork object.
 * 
 */
public class OrForkConverter implements AbstractConverter {

    private OrFork orForkNode;

    PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public OrForkConverter(OrFork of) {
        orForkNode = of;
    }

    // prints XML representation orForkNode object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String madatory_attribute = "<Branch id=\"" + "h" + orForkNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + orForkNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + madatory_attribute + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/>"; //$NON-NLS-1$

        // optional attributes
        pathConnAttribs.OptionalAttributes(orForkNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }
}