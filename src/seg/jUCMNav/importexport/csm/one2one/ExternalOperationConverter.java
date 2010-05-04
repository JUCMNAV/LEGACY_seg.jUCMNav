package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.performance.ExternalOperation;

/**
 * Producing ExternalOperation declarations.
 * 
 */

public class ExternalOperationConverter implements AbstractConverter {
    ExternalOperation externalOpn;

    // constructors
    public ExternalOperationConverter(ExternalOperation pr) {
        externalOpn = pr;
    }

    // prints XML representation of object to output file -- implement this!!
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        String id = "id=\"" + "e" + externalOpn.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String name = "name=\"" + EscapeUtils.escapeXML(externalOpn.getName()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String description;
        String opTime = "opTime=\"" + externalOpn.getOpTime() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String multiplicity;
        String schedPolicy;

        if (externalOpn.getDescription() != null) {
            description = "description=\"" + EscapeUtils.escapeXML(externalOpn.getDescription()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else
            description = ""; //$NON-NLS-1$

        if (externalOpn.getMultiplicity() != null) {
            multiplicity = "multiplicity=\"" + externalOpn.getMultiplicity() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            multiplicity = ""; //$NON-NLS-1$
        }

        if (externalOpn.getSchedPolicy() != null) {
            schedPolicy = "schedPolicy=\"" + EscapeUtils.escapeXML(externalOpn.getSchedPolicy()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            schedPolicy = ""; //$NON-NLS-1$
        }

        String traceabilityLink = "traceabilityLink=\"" + externalOpn.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String resStr = "<ExternalOperation " + id + name + opTime + description + multiplicity + schedPolicy + traceabilityLink + "/>"; //$NON-NLS-1$ //$NON-NLS-2$
        ps.println("        " + resStr); //$NON-NLS-1$
        ps.flush();

    }
}
