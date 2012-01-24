package seg.jUCMNav.extensionpoints;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.eclipse.swt.widgets.Shell;

import urn.URNspec;

/**
 * Interface used by the UrnReport extension point.
 * 
 * @author dessure
 * 
 */
public interface IURNReport {

    /**
     * Export used for reports.
     * 
     * @param urn
     *            the urn specification to conver
     * @param mapDiagrams
     *            the list of diagrams to include in the report
     * @param filename
     *            the filename of the report
     * @param shell 
     */

    public void export(URNspec urn, HashMap mapDiagrams, String filename, Shell shell) throws InvocationTargetException;

}
