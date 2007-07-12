package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.DirectionArrow;

/**
 * Creates the CSM representation(Sequence) of the DirectionArrow object.
 * 
 * Direction Arrows are replaced by a CSM Dummy Sequence
 * 
 * @author jack
 */

public class DirectionArrowConverter implements AbstractConverter {

    private DirectionArrow directionArrowNode;

    private PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public DirectionArrowConverter(DirectionArrow ep) {
        directionArrowNode = ep;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String Object_attributes = "<Sequence id=\"" + "h" + directionArrowNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + directionArrowNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/> <!-- DirectionArrow -->"; //$NON-NLS-1$

        pathConnAttribs.OptionalAttributes(directionArrowNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }
}
