package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Interface AbstractConverter provides a uniform conversion method for all explicit mappings from UCM to CSM.
 * 
 * @see seg.ucm2csm.one2one
 */
public interface AbstractConverter {
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings);
}
