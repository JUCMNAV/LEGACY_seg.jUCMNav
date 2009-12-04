package seg.jUCMNav.extensionpoints;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import urn.URNspec;

/**
 * Interface used by the URNExport extension point. Extension point used to convert instances of the urn.URNspec and output them in a java.io.FileOutputStream,
 * in the export wizard.
 * 
 * Implementers of this interface will be run in thread that is different from the UI thread.
 * 
 * @see IURNExportPrePostHooks for handles on the editor and page.
 * 
 * @author jkealey
 * 
 */
public interface IURNExport {

    /**
     * Export a URNspec instance to a file.
     * 
     * @param urn
     *            the URNspec to be converted
     * @param mapDiagrams
     *            a map of IURNDiagram -> IFigure representing the diagram's image
     * @param fos
     *            a file in which the conversion should be written
     */
    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException;

    /**
     * Export a URNspec instance to a file.
     * 
     * @param urn
     *            the URNspec to be converted
     * @param mapDiagrams
     *            a map of IURNDiagram -> IFigure representing the diagram's image
     * @param filename
     *            a file in which the conversion should be written
     */
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException;

}
