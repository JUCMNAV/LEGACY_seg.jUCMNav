package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.InBinding;

/**
 * Creates the CSM representation(inbinding) of the In-Connection object.
 * 
 */
public class InBindingConverter implements AbstractConverter {

    private InBinding inBinding;

    // constructors
    public InBindingConverter(InBinding in_bind) {
        inBinding = in_bind;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // a DummySequence may exist between stub and previous node such that
        // stubEntry.source no longer holds.
        // new "source" nodes (CSMDupNode) are passed as array.
        String previousNode = (String) source.get(0);

        // object attributes
        String Object_attributes = "<InBinding start=\"" + "h" + inBinding.getStartPoint().getId() + "\" " + "in=\"" + previousNode + "\"/>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

        // output to file
        ps.println("                     " + Object_attributes); //$NON-NLS-1$
        ps.flush();
    }
}
