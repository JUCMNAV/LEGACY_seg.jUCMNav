package seg.jUCMNav.extensionpoints;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.draw2d.IFigure;

import urncore.IURNDiagram;

/**
 * Interface used by the UseCaseMapExport extension point. Extension point used to convert instances of the ucm.map.Map and output them in a
 * java.io.FileOutputStream, in the export wizard.
 * 
 * @author jkealey
 * 
 */
public interface IUseCaseMapExport {

    /**
     * Export a diagram instance to a file.
     * 
     * @param diagram
     *            the diagram to be converted
     * @param fos
     *            a file in which the conversion should be written
     */
    public void export(IURNDiagram diagram, FileOutputStream fos) throws InvocationTargetException;

    /**
     * Export the graphical representation of a diagram instance to a file.
     * 
     * @param map
     *            the diagram to be converted
     * @param fos
     *            a file in which the conversion should be written
     */
    public void export(IFigure map, FileOutputStream fos) throws InvocationTargetException;

    /**
     * Export a map instance to a file.
     * 
     * @param diagram
     *            the diagram to be converted
     * @param path
     *            a file in which the conversion should be written
     */
    public void export(IURNDiagram diagram, String path) throws InvocationTargetException;

    /**
     * Export the graphical representation of a map instance to a file.
     * 
     * @param map
     *            the map to be converted
     * @param path
     *            a file in which the conversion should be written
     */
    public void export(IFigure map, String path) throws InvocationTargetException;
}
