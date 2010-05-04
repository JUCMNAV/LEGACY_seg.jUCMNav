package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.performance.ProcessingResource;

/**
 * Class currently NOT IMPLEMENTED!
 * 
 */

public class ProcessingResourceConverter implements AbstractConverter {
    ProcessingResource processingRes;

    // constructors
    public ProcessingResourceConverter(ProcessingResource pr) {
        processingRes = pr;
    }

    // prints XML representation of object to output file -- implement this!!
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        String id = "id=\"" + "r" + processingRes.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String name = "name=\"" + EscapeUtils.escapeXML(processingRes.getName()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String description;
        String opTime = "opTime=\"" + processingRes.getOpTime() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String multiplicity;
        String schedPolicy;

        if (processingRes.getDescription() != null) {
            description = "description=\"" + EscapeUtils.escapeXML(processingRes.getDescription()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else
            description = ""; //$NON-NLS-1$

        if (processingRes.getMultiplicity() != null) {
            multiplicity = "multiplicity=\"" + processingRes.getMultiplicity() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            multiplicity = ""; //$NON-NLS-1$
        }

        if (processingRes.getSchedPolicy() != null) {
            schedPolicy = "schedPolicy=\"" + EscapeUtils.escapeXML(processingRes.getSchedPolicy()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            schedPolicy = ""; //$NON-NLS-1$
        }

        String traceabilityLink = "traceabilityLink=\"" + processingRes.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String resStr = "<ProcessingResource " + id + name + opTime + description + multiplicity + schedPolicy + traceabilityLink + "/>"; //$NON-NLS-1$ //$NON-NLS-2$
        ps.println("        " + resStr); //$NON-NLS-1$

        ps.flush();
    }
}
