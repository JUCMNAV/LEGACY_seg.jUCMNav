package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.performance.PassiveResource;

/**
 * Produces CSM of PassiveResources.
 * 
 */

public class PassiveResourceConverter implements AbstractConverter {

    private PassiveResource passiveRes;

    // constructors
    public PassiveResourceConverter(PassiveResource genRes) {
        passiveRes = genRes;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        String id = "id=\"" + "p" + passiveRes.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String name = "name=\"" + EscapeUtils.escapeXML(passiveRes.getName()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String description;
        String multiplicity;
        String schedPolicy;

        if (passiveRes.getDescription() != null) {
            description = "description=\"" + EscapeUtils.escapeXML(passiveRes.getDescription()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else
            description = ""; //$NON-NLS-1$

        if (passiveRes.getMultiplicity() != null) {
            multiplicity = "multiplicity=\"" + passiveRes.getMultiplicity() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            multiplicity = ""; //$NON-NLS-1$
        }

        if (passiveRes.getSchedPolicy() != null) {
            schedPolicy = "schedPolicy=\"" + EscapeUtils.escapeXML(passiveRes.getSchedPolicy()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            schedPolicy = ""; //$NON-NLS-1$
        }

        String traceabilityLink = "traceabilityLink=\"" + passiveRes.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String resStr = "<PassiveResource " + id + name + description + multiplicity + schedPolicy + traceabilityLink + "/>"; //$NON-NLS-1$ //$NON-NLS-2$

        // output to file
        ps.println("        " + resStr); //$NON-NLS-1$
        ps.flush();
    }
}
