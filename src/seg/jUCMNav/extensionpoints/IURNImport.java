package seg.jUCMNav.extensionpoints;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import urn.URNspec;

/**
 * Interface used by the URNImport extension point. Extension point used to load a file and create an instance of urn.URNspec, in the import file wizard.
 * 
 * @author jkealey, Jean-François Roy
 * 
 */
public interface IURNImport {

    /**
     * Import a URNspec instance from a file.
     * 
     * @param fis
     *            a file from which the model is to be loaded.
     * @param autolayoutDiagrams
     *            vector of diagrams id to autolayout (if option is selected)
     * @return the URNspec that was loaded
     */
    public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams) throws InvocationTargetException;

    /**
     * Import a URNspec instance from a file.
     * 
     * @param filename
     *            a file from which the model is to be loaded.
     * @param autolayoutDiagrams
     *            vector of diagrams id to autolayout (if option is selected)
     * @return the URNspec that was loaded
     */
    public URNspec importURN(String filename, Vector autolayoutDiagrams) throws InvocationTargetException;

    /**
     * Import a URN element from a file and update the URNspec to include these elements.
     * 
     * @param fis
     *            a file from which the model is to be loaded.
     * @param urn
     *            the URNspec to modify
     * @param autolayoutDiagrams
     *            vector of diagrams id to autolayout (if option is selected)
     * @return the URNspec that was loaded
     */
    public URNspec importURN(FileInputStream fis, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException;

    /**
     * Import a URNspec instance from a file and update the URNspec to include these elements.
     * 
     * @param filename
     *            a file from which the model is to be loaded.
     * @param urn
     *            the URNspec to modify
     * @param autolayoutDiagrams
     *            vector of diagrams id to autolayout (if option is selected)
     * @return the URNspec that was loaded
     */
    public URNspec importURN(String filename, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException;

}
