package seg.jUCMNav.importexport.reports.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

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
     * return the area in which the image will be inserted in the report
     * 
     * @param pagesize
     *            the actual size of a report page
     * @param margin
     *            the space we leave for margins
     * @param pageRatioImageHeight
     *            the actual pagesize ratio we want the image Height to occupy
     * 
     */
    public static Rectangle getImageArea(Rectangle pagesize, int margin, float pageRatioImageHeight) {

        float imageAreaHeight = pagesize.getHeight() * pageRatioImageHeight - margin;
        float imageAreaWidth = pagesize.getWidth() - 2 * margin;
        Rectangle imageArea = new Rectangle(imageAreaWidth, imageAreaHeight);
        return imageArea;
    }

    /**
     * insert the java.awt image into the document
     * 
     * @param document
     *            the actual document in which we insert the image
     * @param awtImage
     *              the image in AWT format, needed for iText library
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

            Image img1 = Image.getInstance(awtImage, null);

            // if the image boundaries are bigger than the pagesize, resize the image
            float pageWidth = pagesize.getWidth();
            float pageHeight = pagesize.getHeight();
            float ratioLargeImage = 0.5f;
            float ratioSmallImage = 0.25f;
            if (imageWidth > pageWidth + 20 || imageHeight > pageHeight + 20) {
                // resize the image, default margin size is used, same value as the one used in document constructor
                // we use half of the page of image as well
                // TODO these values should be obtained dynamically
                Rectangle imageArea = getImageArea(pagesize, 36, ratioLargeImage);
                img1.scaleToFit(imageArea.getWidth(), imageArea.getHeight());
            } else {
                Rectangle imageArea = getImageArea(pagesize, 36, ratioSmallImage);
                img1.scaleToFit(imageArea.getWidth(), imageArea.getHeight());
            }

            img1.setAlignment(com.lowagie.text.Image.MIDDLE);
              document.add(img1);

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
     *              the IFigure for the image
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

            ExportImageGIF gifImage = new ExportImageGIF();
            gifImage.export(pane, "tmpfile.gif");
            
           Image rtfImage = Image.getInstance("tmpfile.gif");
           
            // TODO refactor same code as above
            // if the image boundaries are bigger than the pagesize, resize the image
            float pageWidth = pagesize.getWidth();
            float pageHeight = pagesize.getHeight();
            float ratioLargeImage = 0.5f;
            float ratioSmallImage = 0.25f;
            if (imageWidth > pageWidth + 20 || imageHeight > pageHeight + 20) {
                // resize the image, default margin size is used, same value as the one used in document constructor
                // we use half of the page of image as well
                // TODO these values should be obtained dynamically
                Rectangle imageArea = getImageArea(pagesize, 36, ratioLargeImage);
                rtfImage.scaleToFit(imageArea.getWidth(), imageArea.getHeight());
            } else {
                Rectangle imageArea = getImageArea(pagesize, 36, ratioSmallImage);
                rtfImage.scaleToFit(imageArea.getWidth(), imageArea.getHeight());
            }

            rtfImage.setAlignment(com.lowagie.text.Image.MIDDLE);
           
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
     *  @return BufferedImage
     *            the AWT image 
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
     *  @return ImageData
     *            the cropped SWT image 
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
        int backgroundColor = 248;

        for (int x = 0; x < imageWidth - 1; x++) {
            // find the top limit of crop rectangle, with 0,0 being top left corner of image
            for (int y = 0; y < imageHeight; y++) {
                int pixel = image.getPixel(x, y);
                RGB rgb = palette.getRGB(pixel);
                // if pixel is not white then keep y value
                // TODO check preferred values for background color
                if (rgb.blue != backgroundColor || rgb.green != backgroundColor || rgb.red != backgroundColor) {
                    if (y < top) {
                        if (y > 0) {
                            top = y - 1;
                        } else {
                            top = y;
                        }
                    }
                }
            }
        }

        for (int y = 0; y < imageHeight; y++) {
            // find the left limit of crop rectangle, with 0,0 being top left corner of image
            for (int x = 0; x < imageWidth; x++) {
                int pixel = image.getPixel(x, y);
                RGB rgb = palette.getRGB(pixel);
                // if pixel is not white then keep y value
                // TODO check prefered values for bground color
                if (rgb.blue != backgroundColor || rgb.green != backgroundColor || rgb.red != backgroundColor) {
                    if (x < left) {
                        if (x > 1)
                            left = x - 2;
                        else
                            left = x;
                    }
                }
            }

        }

        for (int y = 0; y < imageHeight; y++) {
            // find the right limit of crop rectangle, with 0,0 being top left corner of image
            for (int x = imageWidth - 1; x > 0; x--) {
                int pixel = image.getPixel(x, y);
                RGB rgb = palette.getRGB(pixel);
                // if pixel is not white then keep y value
                // TODO check prefered values for bground color
                if (rgb.blue != backgroundColor || rgb.green != backgroundColor || rgb.red != backgroundColor) {
                    if (x > right) {
                        right = x;
                    }
                }
            }
        }

        for (int x = 0; x < imageWidth; x++) {
            // find the bottom limit of crop rectangle, with 0,0 being top left corner of image
            for (int y = imageHeight - 1; y > 0; y--) {
                int pixel = image.getPixel(x, y);
                RGB rgb = palette.getRGB(pixel);
                // if pixel is not white then keep y value
                if (rgb.blue != backgroundColor || rgb.green != backgroundColor || rgb.red != backgroundColor) {
                    if (y > bottom)
                        bottom = y;
                }
            }
        }

        // variables used to construct the crop rectangle initialization
        cropRectangleX = left;
        cropRectangleY = top;
        cropRectangleWidth = right - left + 1;
        cropRectangleHeight = bottom - top + 1;

        ImageData croppedImage = new ImageData(cropRectangleWidth, cropRectangleHeight, 8, palette);

        for (int x = cropRectangleX; x < cropRectangleWidth + cropRectangleX; x++) {
            for (int y = cropRectangleY; y < cropRectangleHeight + cropRectangleY; y++) {
                int pixelValue = image.getPixel(x, y);
                int newX = x - left;
                int newY = y - top;
                croppedImage.setPixel(newX, newY, pixelValue);
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
     *  @return Table
     *            the table ready to use 
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
     * writes the line of text consisting of 2 parts with a separator,
     *  the separator will be written if there is an existing right part
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
     * returns a paragraph with a line of text consisting of 2 parts with a separator,
     *  the separator will be written if there is an existing right part
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
     *  @return Paragraph
     *            the newly created paragraph with the new line inserted
     */
    public static Paragraph getParagraphWithSeparator(Document document, String leftPart, String separator, String rightPart, Font font) {
        Paragraph para1 = new Paragraph("", font);
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

}
