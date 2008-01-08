package seg.jUCMNav.extensionpoints;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

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
     * @param diagrams
     *            the list of diagrams to include in the report
     * @param filename 
     *            the filename of the report
     */

    @SuppressWarnings("unchecked")
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException;

}
