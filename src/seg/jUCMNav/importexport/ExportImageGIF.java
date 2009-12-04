package seg.jUCMNav.importexport;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Exports as GIF image.
 * 
 * Code to downsample taken from https://bugs.eclipse.org/bugs/show_bug.cgi?id=70949
 * 
 * 
 * @author Nick Edgar, Christophe Cornu, jkealey
 * 
 */
public class ExportImageGIF extends ExportImage {

    /**
     * Given the IFigure, save it to a file.
     * 
     * GIFs can only be saved with an 8 bit color depth.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure pane, FileOutputStream fos) {
        // generate image
        Image image = new Image(Display.getCurrent(), pane.getSize().width, pane.getSize().height);

        GC gc = new GC(image);
        SWTGraphics graphics = new SWTGraphics(gc);
        // if the bounds are in the negative x/y, we don't see them without a
        // translation
        graphics.translate(-pane.getBounds().x, -pane.getBounds().y);
        pane.paint(graphics);

        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { downSample(image) };
        loader.save(fos, getType());

        gc.dispose();
        image.dispose();
    }

    /**
     * Downsample the image to an 8-bit palette, using the 256 most frequently used colors.
     * 
     * @param image
     *            the image to downsample.
     * @return the ImageData containing the 8bit palette.
     */
    public static ImageData downSample(Image image) {
        ImageData data = image.getImageData();
        if (!data.palette.isDirect && data.depth <= 8)
            return data;

        // compute a histogram of color frequencies
        HashMap freq = new HashMap();
        int width = data.width;
        int[] pixels = new int[width];
        int[] maskPixels = new int[width];
        for (int y = 0, height = data.height; y < height; ++y) {
            data.getPixels(0, y, width, pixels, 0);
            for (int x = 0; x < width; ++x) {
                RGB rgb = data.palette.getRGB(pixels[x]);
                ColorCounter counter = (ColorCounter) freq.get(rgb);
                if (counter == null) {
                    counter = new ColorCounter();
                    counter.rgb = rgb;
                    freq.put(rgb, counter);
                }
                counter.count++;
            }
        }

        // sort colors by most frequently used
        ColorCounter[] counters = new ColorCounter[freq.size()];
        freq.values().toArray(counters);
        Arrays.sort(counters);

        // pick the most frequently used 256 (or fewer), and make a palette
        ImageData mask = null;
        if (data.transparentPixel != -1 || data.maskData != null) {
            mask = data.getTransparencyMask();
        }
        int n = Math.min(256, freq.size());
        RGB[] rgbs = new RGB[n + (mask != null ? 1 : 0)];
        for (int i = 0; i < n; ++i)
            rgbs[i] = counters[i].rgb;
        if (mask != null) {
            rgbs[rgbs.length - 1] = data.transparentPixel != -1 ? data.palette.getRGB(data.transparentPixel) : new RGB(255, 255, 255);
        }
        PaletteData palette = new PaletteData(rgbs);

        // create a new image using the new palette:
        // for each pixel in the old image, look up the best matching
        // index in the new palette
        ImageData newData = new ImageData(width, data.height, 8, palette);
        if (mask != null)
            newData.transparentPixel = rgbs.length - 1;
        for (int y = 0, height = data.height; y < height; ++y) {
            data.getPixels(0, y, width, pixels, 0);
            if (mask != null)
                mask.getPixels(0, y, width, maskPixels, 0);
            for (int x = 0; x < width; ++x) {
                if (mask != null && maskPixels[x] == 0) {
                    pixels[x] = rgbs.length - 1;
                } else {
                    RGB rgb = data.palette.getRGB(pixels[x]);
                    pixels[x] = closest(rgbs, n, rgb);
                }
            }
            newData.setPixels(0, y, width, pixels, 0);
        }
        return newData;
    }

    /**
     * Find the closest index to the given color.
     * 
     * @param rgbs
     *            palette
     * @param n
     *            size
     * @param rgb
     *            color to find
     * @return closest index
     */
    protected static int closest(RGB[] rgbs, int n, RGB rgb) {
        int minDist = 256 * 256 * 3;
        int minIndex = 0;
        for (int i = 0; i < n; ++i) {
            RGB rgb2 = rgbs[i];
            int da = rgb2.red - rgb.red;
            int dg = rgb2.green - rgb.green;
            int db = rgb2.blue - rgb.blue;
            int dist = da * da + dg * dg + db * db;
            if (dist < minDist) {
                minDist = dist;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Return SWT.IMAGE_BMP_RLE
     * 
     * @see seg.jUCMNav.importexport.ExportImage#getType()
     */
    public int getType() {
        return SWT.IMAGE_GIF;
    }
}
