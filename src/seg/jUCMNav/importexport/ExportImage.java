package seg.jUCMNav.importexport;

import java.io.FileOutputStream;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import ucm.map.Map;

/**
 * Abstract base class to export images using the save functions provided by SWT
 * 
 * @author jkealey
 * 
 */
public abstract class ExportImage implements IUseCaseMapExport {

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(ucm.map.Map, java.io.FileOutputStream)
     */
    public void export(Map map, FileOutputStream fos) {
        // not used.
    }

    /**
     * Given the IFigure, save it to a file.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure pane, FileOutputStream fos) {
        // generate image
        Image image = new Image(Display.getCurrent(), pane.getSize().width, pane.getSize().height);
        GC gc = new GC(image);
        SWTGraphics graphics = new SWTGraphics(gc);
        // if the bounds are in the negative x/y, we don't see them without a translation
        graphics.translate(-pane.getBounds().x, -pane.getBounds().y);
        pane.paint(graphics);

        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };
        loader.save(fos, getType());
    }

    /**
     * 
     * @return an SWT constant representing the image type.
     */
    public abstract int getType();

}
