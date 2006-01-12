package seg.jUCMNav.extensionpoints;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import urn.URNspec;

/**
 * Interface used by the URNExport extension point. Extension point used to convert instances of the urn.URNspec and output them in a java.io.FileOutputStream,
 * in the export wizard.
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
     * @param fos
     *            a file in which the conversion should be written
     */
    public void export(URNspec urn, FileOutputStream fos) throws InvocationTargetException;

    /**
     * Export a URNspec instance to a file.
     * 
     * @param urn
     *            the URNspec to be converted
     * @param filename
     *            a file in which the conversion should be written
     */
    public void export(URNspec urn, String filename) throws InvocationTargetException;

}
