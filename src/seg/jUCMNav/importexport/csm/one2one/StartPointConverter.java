package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.StartPoint;

/**
 * Creates the CSM representation(Start) of the Start object.
 * 
 */

public class StartPointConverter implements AbstractConverter {
    private StartPoint startPointNode;

    private PathConnAttributes pathConnAttribs = new PathConnAttributes();

    private WorkLoadAttributes workLoadAttribs = new WorkLoadAttributes();

    // constructors
    public StartPointConverter(StartPoint sp) {
        startPointNode = sp;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String mandatory_attributes = "<Start id=\"h" + startPointNode.getId() + "\" " + "traceabilityLink=\"" + startPointNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        String closing_attribute = "</Start>"; //$NON-NLS-1$

        // common attributes
        ps.print("            " + mandatory_attributes); //$NON-NLS-1$

        // optional attributes
        pathConnAttribs.OptionalAttributes(startPointNode, ps, source, target);

        // processing workload
        if (startPointNode.getWorkload() != null) {

            // decide if workload is open or closed
            if (startPointNode.getWorkload().isClosed()) {
                String close_wload_attributes = "<ClosedWorkload id=\"w" + startPointNode.getWorkload().getId() + "\" " + "population=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + startPointNode.getWorkload().getPopulation() + "\" " + " "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("                " + close_wload_attributes); //$NON-NLS-1$
            } else {
                String open_wload_attributes = "<OpenWorkload id=\"w" + startPointNode.getWorkload().getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print("                " + open_wload_attributes); //$NON-NLS-1$
            }
            // optional workload attributes
            workLoadAttribs.workAttributes(startPointNode.getWorkload(), ps);
            String print_attribute = "/>"; //$NON-NLS-1$
            ps.println(print_attribute);
        }

        // output to file
        ps.println("            " + closing_attribute); //$NON-NLS-1$
        ps.flush();
    }
}
