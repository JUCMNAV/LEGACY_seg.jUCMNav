package seg.jUCMNav.importexport.reports.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.importexport.ExportImageGIF;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;

/*
 * Utilities used throughout the report generator classes:
 * returning imageArea, inserting an image, various imaging utilities.
 * 
 * @author dessure
 */
public class ReportUtils {

    /**
     * For setting up resolution. 1.0 = 100% zoom. Export speed slows down by the square of this value...
     * 
     */
    public final static float ZOOMFACTOR = 1.0f;  // changed from 1.5

    /**
     * @param pagesize
     *            the actual size of a report page
     * @param imageWidth
     *            the width of the image
     * @param imageHeight
     *            the height of the image
     * @param img
     *            the Image
     */
    public static void imageSmartScale(Rectangle pagesize, int imageWidth, int imageHeight, Image img) {

        // if the image boundaries are bigger than the page size, resize the image
        float pageWidth = pagesize.getWidth();
        float pageHeight = pagesize.getHeight();

        // scale down from 100% zoom for nice and readable font size on paper
        float newWidth = (float) imageWidth * 0.75f / ZOOMFACTOR;
        float newHeight = (float) imageHeight * 0.75f / ZOOMFACTOR;

        // too wide?
        if (newWidth > pageWidth - 72f) {
            newHeight = newHeight * ((pageWidth - 72f) / newWidth);
            newWidth = pageWidth - 72f; // leave 0.5in margin on each side
        }

        // still too high?
        if (newHeight > pageHeight * 0.70f) {
            newWidth = newWidth * (pageHeight * 0.70f / newHeight);
            newHeight = pageHeight * 0.70f; // no higher than 70% of the page. Leaves room for headings/titles.
        }

        img.scaleToFit(newWidth, newHeight);
        img.setAlignment(com.lowagie.text.Image.MIDDLE);

    }

    /**
     * insert the java.awt image into the document
     * 
     * @param document
     *            the actual document in which we insert the image
     * @param awtImage
     *            the image in AWT format, needed for iText library
     * @param pagesize
     *            the actual size of a report page
     * @param imageWidth
     *            the width of the image
     * @param imageHeight
     *            the height of the image
     * 
     */
    public static void insertImage(Document document, java.awt.Image awtImage, Rectangle pagesize, int imageWidth, int imageHeight) {

        try {

            Image img = Image.getInstance(awtImage, null);

            imageSmartScale(pagesize, imageWidth, imageHeight, img);
            document.add(img);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * insert the gif image into the document
     * 
     * @param document
     *            the actual document in which we insert the image
     * @param pane
     *            the IFigure for the image
     * @param pagesize
     *            the actual size of a report page
     * @param imageWidth
     *            the width of the image
     * @param imageHeight
     *            the height of the image
     * 
     */
    public static void insertRTFImage(Document document, IFigure pane, Rectangle pagesize, int imageWidth, int imageHeight) {

        try {
        	document.add(Chunk.NEWLINE);
            int paneWidth = Math.round(pane.getSize().width * ReportUtils.ZOOMFACTOR);
            int paneHeight = Math.round(pane.getSize().height * ReportUtils.ZOOMFACTOR);
            org.eclipse.swt.graphics.Image image = new org.eclipse.swt.graphics.Image(Display.getCurrent(), paneWidth, paneHeight);

            GC gc = new GC(image);
            SWTGraphics graphics = new SWTGraphics(gc);
            pane.paint(graphics);

            ImageData ideaImageData = ExportImageGIF.downSample(image);
            ImageData croppedImage = ReportUtils.cropImage(ideaImageData);
            
            ImageLoader loader = new ImageLoader();
            loader.data = new ImageData[] { croppedImage };
            loader.save("tmpfile.gif", SWT.IMAGE_GIF); //$NON-NLS-1$

            Image rtfImage = Image.getInstance("tmpfile.gif"); //$NON-NLS-1$

            gc.dispose();
            image.dispose();

            imageSmartScale(pagesize, imageWidth, imageHeight, rtfImage);
            document.add(rtfImage);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * converts a SWT image into a AWT image
     * 
     * @param data
     *            the ImageData from SWT we want to transform
     * 
     * @return BufferedImage the AWT image
     * 
     */
    public static BufferedImage SWTimageToAWTImage(ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
            BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    RGB rgb = palette.getRGB(pixel);
                    pixelArray[0] = rgb.red;
                    pixelArray[1] = rgb.green;
                    pixelArray[2] = rgb.blue;
                    raster.setPixels(x, y, 1, 1, pixelArray);
                }
            }
            return bufferedImage;
        } else {
            RGB[] rgbs = palette.getRGBs();
            byte[] red = new byte[rgbs.length];
            byte[] green = new byte[rgbs.length];
            byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
            }
            BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            return bufferedImage;
        }
    }

    /**
     * removes the white space around the figure image
     * 
     * @param image
     *            the ImageData from SWT we want to transform
     * 
     * @return ImageData the cropped SWT image
     * 
     */
    public static ImageData cropImage(ImageData image) {

        PaletteData palette = image.palette;
        int imageHeight = image.height;
        int imageWidth = image.width;

        // variables used to construct the crop rectangle initialization
        int cropRectangleX = 0;
        int cropRectangleY = 0;
        int cropRectangleWidth = 0;
        int cropRectangleHeight = 0;

        int left = imageWidth;
        int right = 0;
        int bottom = 0;
        int top = imageHeight;
        int backgroundColor = image.getPixel(imageWidth - 1, 0); // Assumes there is nothing on top-right corner
        boolean found = false; // indicates whether the border has been found.

        // find the top limit of crop rectangle, with 0,0 being top left corner of image
        for (int y = 0; y < imageHeight && !found; y++) {
            for (int x = 0; x < imageWidth && !found; x++) {
                if (image.getPixel(x, y) != backgroundColor) {
                    top = y;
                    found = true;
                }
            }
        }

        // find the left limit of crop rectangle, with 0,0 being top left corner of image
        found = false;
        for (int x = 0; x < imageWidth && !found; x++) {
            for (int y = 0; y < imageHeight && !found; y++) {
                if (image.getPixel(x, y) != backgroundColor) {
                    left = x;
                    found = true;
                }
            }

        }

        // find the right limit of crop rectangle, with 0,0 being top left corner of image
        found = false;
        for (int x = imageWidth - 1; x > 0 && !found; x--) {
            for (int y = 0; y < imageHeight && !found; y++) {
                if (image.getPixel(x, y) != backgroundColor) {
                    right = x;
                    found = true;
                }
            }
        }

        // find the bottom limit of crop rectangle, with 0,0 being top left corner of image
        found = false;
        for (int y = imageHeight - 1; y > 0 && !found; y--) {
            for (int x = 0; x < imageWidth && !found; x++) {
                if (image.getPixel(x, y) != backgroundColor) {
                    bottom = y;
                    found = true;
                }
            }
        }

        // variables used to construct the crop rectangle initialization
        cropRectangleX = left;
        cropRectangleY = top;
        cropRectangleWidth = right - left + 1;
        cropRectangleHeight = bottom - top + 1;

        ImageData croppedImage;
        if (cropRectangleWidth < 0 || cropRectangleHeight < 0) {
            // Empty image?
            croppedImage = new ImageData(1, 1, image.depth, image.palette);
        } else {
            croppedImage = new ImageData(cropRectangleWidth, cropRectangleHeight, image.depth, image.palette);

            for (int x = cropRectangleX; x < cropRectangleWidth + cropRectangleX; x++) {
                for (int y = cropRectangleY; y < cropRectangleHeight + cropRectangleY; y++) {
                    int pixelValue = image.getPixel(x, y);
                    int newX = x - left;
                    int newY = y - top;
                    croppedImage.setPixel(newX, newY, pixelValue);
                }
            }
        }
        return croppedImage;
    }

    /**
     * creates a Table for reports
     * 
     * @param nbOfColumns
     *            the number of columns for the table
     * @param padding
     *            the space padding for the table
     * @param spacing
     *            the spacing for the table
     * @param width
     *            the width of the table
     * 
     * @return Table the table ready to use
     * 
     */
    public static Table createTable(int nbOfColumns, int padding, int spacing, int width) {

        try {

            Table table = new Table(nbOfColumns);
            table.setBorderWidth(0f);
            table.setPadding(padding);
            table.setSpacing(spacing);
            table.setWidth(width);

            return table;

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

            return null;
        }

    }

    /**
     * writes the line of text consisting of 2 parts with a separator, the separator will be written if there is an existing right part
     * 
     * @param document
     *            the document in which to insert the line
     * @param leftPart
     *            the left part of the String
     * @param separator
     *            the symbol to separate the 2 strings
     * @param rightPart
     *            the right part of the string
     * @param font
     *            the font to use
     * @param newline
     *            to see if we need to insert a new line at the end
     * 
     */
    public static void writeLineWithSeparator(Document document, String leftPart, String separator, String rightPart, Font font, boolean newline) {

        try {
            if (rightPart != null) {
                if (rightPart.length() == 0) {
                    Chunk chunk1 = new Chunk(leftPart, font);

                    document.add(chunk1);
                    if (newline) {
                        document.add(Chunk.NEWLINE);
                    }
                } else {
                    Chunk chunk1 = new Chunk(leftPart + separator + rightPart, font);
                    document.add(chunk1);
                    if (newline) {
                        document.add(Chunk.NEWLINE);
                    }
                }
            } else {
                Chunk chunk1 = new Chunk(leftPart, font);
                document.add(chunk1);
                if (newline) {
                    document.add(Chunk.NEWLINE);
                }
            }

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * returns a paragraph with a line of text consisting of 2 parts with a separator, the separator will be written if there is an existing right part
     * 
     * @param document
     *            the document in which to insert the line
     * @param leftPart
     *            the left part of the String
     * @param separator
     *            the symbol to separate the 2 strings
     * @param rightPart
     *            the right part of the string
     * @param font
     *            the font to use
     * 
     * @return Paragraph the newly created paragraph with the new line inserted
     */
    public static Paragraph getParagraphWithSeparator(Document document, String leftPart, String separator, String rightPart, Font font) {
        Paragraph para1 = new Paragraph("", font); //$NON-NLS-1$
        try {
            if (rightPart != null) {
                if (rightPart.length() == 0) {
                    para1.add(leftPart);
                } else {
                    para1.add(leftPart + separator + rightPart);
                }
            } else {
                para1.add(leftPart);
            }

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
        return para1;
    }

    /**
     * checks whether the String s exists and is not empty
     * 
     * @param s
     *            the String to check
     * 
     * @return exists indicates whether s is not null and not empty
     */
    public static boolean notEmpty(String s) {
        boolean exists = false;

        if (s != null)
            exists = (s.replaceAll("\\s+", "") != ""); //$NON-NLS-1$
        return exists;
    }
}
