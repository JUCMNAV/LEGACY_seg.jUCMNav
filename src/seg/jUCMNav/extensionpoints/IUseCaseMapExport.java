package seg.jUCMNav.extensionpoints;

import java.io.FileOutputStream;

import org.eclipse.draw2d.IFigure;

import ucm.map.UCMmap;

/**
 * Interface used by the UseCaseMapExport extension point. Extension point used to convert instances of the ucm.map.Map and output them in a java.io.FileOutputStream, in the export wizard. 
 * 
 * @author jkealey
 * 
 */
public interface IUseCaseMapExport {

    /**
     * Export a map instance to a file. 
     * 
     * @param map the map to be converted
     * @param fos a file in which the conversion should be written
     */
    public void export(UCMmap map, FileOutputStream fos);

    /**
     * Export the graphical representation of a map instance to a file.
     * 
     * @param map the map to be converted
     * @param fos a file in which the conversion should be written
     */
    public void export(IFigure map, FileOutputStream fos);
}
