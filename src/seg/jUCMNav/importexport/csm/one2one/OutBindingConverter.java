package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.OutBinding;

/**
 * Creates the CSM representation(outbinding) of the Out-Connection object
 * 
 */
public class OutBindingConverter implements AbstractConverter {

    private OutBinding outBinding;

    // constructors
    public OutBindingConverter(OutBinding out_bind) {
        outBinding = out_bind;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // a DummySequence may now exist between stub and next node suche that
        // stubEntry.target no longer holds.
        // new "next" nodes (CSMDupNode) are passed as array.
        String nextNode = (String) target.get(0);

        // object attributes
        String Object_attributes = "<OutBinding end=\"" + "h" + outBinding.getEndPoint().getId() + "\" " + "out=\"" + nextNode + "\"/>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

        // output to file
        ps.println("                     " + Object_attributes); //$NON-NLS-1$
        ps.flush();
    }
}
