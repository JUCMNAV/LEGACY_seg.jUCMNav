package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.WaitingPlace;

/**
 * Creates the CSM representation(And-Fork) of the WaitingPlace object.
 * 
 */

public class WaitingPlaceConverter implements AbstractConverter {

    private WaitingPlace waitingPlaceNode;

    PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public WaitingPlaceConverter(WaitingPlace af) {
        waitingPlaceNode = af;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String id_attribute = "<Join id=\"" + "h" + waitingPlaceNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + waitingPlaceNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + id_attribute + traceabilityLink); //$NON-NLS-1$

        String closing_attribute = "/> <!-- Waiting Place -->"; //$NON-NLS-1$

        // optional attributes
        pathConnAttribs.OptionalAttributes(waitingPlaceNode, ps, source, target);

        ps.println(closing_attribute);
        ps.flush();
    }

}
