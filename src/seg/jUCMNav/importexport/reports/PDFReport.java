package seg.jUCMNav.importexport.reports;

import seg.jUCMNav.importexport.ExportImageGIF;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNdefinition;
import urncore.URNmodelElement;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfLayer;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfWriter;

/**
 * implements the export functionality called by the ReportGenerator Wizard. It extends the Report class containing all generic code for all report types.
 * 
 * This class contains code specific to PDF generation (headers, footers, ...)
 * 
 * 
 * @author dessure
 * 
 */
public class PDFReport extends Report {

    /**
     * main logic to create the pdf report
     * 
     * @param urn
     *            the urn specification we report on
     * @param mapDiagrams
     *            list of diagrams to be documented
     * @param filename
     *            the report filename
     */
    
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

        // Create a report document with page size from preferences, 72 points per inch
        String sReportHeight = ReportGeneratorPreferences.getHeight();
        fHeight = Float.parseFloat(sReportHeight) * 72;
        String sReportWidth = ReportGeneratorPreferences.getWidth();
        fWidth = Float.parseFloat(sReportWidth) * 72;
        pagesize = new Rectangle(fWidth, fHeight);

        Document document = new Document(pagesize);

        try {
            // get an instance of the PDFWriter in order to add content and open the stream
            PdfWriter writer = PdfWriter.getInstance(document, System.out);
            PdfWriter.getInstance(document, new FileOutputStream(filename));

            // set pdf version and open the pdf document stream
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_6);
            writer.setViewerPreferences(PdfWriter.PageModeFullScreen);

            document.open();

            // call Report.export to create the generic sections
            super.export(urn, mapDiagrams, filename, document, pagesize);

            // The diagram creation is specific for PDF files, inserted here
            if (!urndef.getSpecDiagrams().isEmpty()) {
                PDFReportDiagram reportDiagrams = new PDFReportDiagram();
                reportDiagrams.createPDFReportDiagramsAndDescription(document, urndef, mapDiagrams, pagesize);

            }
            
            
            document.close();

        } catch (Exception e) {

            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * process the diagram figure and insert in the pdf report
     * 
     * @param document
     *            the document we are reporting into
     * @param mapDiagrams
     *            list of diagrams to be documented
     * @param diagram
     *            the current diagram
     * @param urndef
     *            the urn definition of the elements
     * @param i
     *            used to check if last diagram
     * @param pagesize
     *            the rectangle with dimensions for the size of page
     * 
     */
    
    public void insertDiagram(Document document, HashMap mapDiagrams, IURNDiagram diagram, URNdefinition urndef, int i, Rectangle pagesize) {
        try {
            // get the high level IFigure to be saved.
            IFigure pane = (IFigure) mapDiagrams.get(diagram);

            Image image = new Image(Display.getCurrent(), pane.getSize().width, pane.getSize().height);

            GC gc = new GC(image);
            SWTGraphics graphics = new SWTGraphics(gc);

            // if the bounds are in the negative x/y, we don't see them without a
            // translation
            graphics.translate(-pane.getBounds().x, -pane.getBounds().y);
            pane.paint(graphics);

            // downSample the image to an 8-bit palette, using the 256 most frequently used color
            ImageData ideaImageData = ExportImageGIF.downSample(image);

            // remove the white space around the diagram
            ImageData croppedImage = ReportUtils.cropImage(ideaImageData);
            java.awt.Image awtImage = ReportUtils.SWTimageToAWTImage(croppedImage);

            // get image width and height from buffered image, then insert in the document
            BufferedImage bufferedImage = (BufferedImage) awtImage;
            int imageHeight = bufferedImage.getHeight();
            int imageWidth = bufferedImage.getWidth();
            ReportUtils.insertImage(document, awtImage, pagesize, imageWidth, imageHeight);

            gc.dispose();
            image.dispose();
            awtImage.flush();

            boolean isLast = i == urndef.getSpecDiagrams().size() - 1;

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * insert the figure number and name under the figure
     * 
     * @param document
     *            the document we are reporting into
     * @param element
     *            URN element documented
     * @param i
     *            figure number
     */
    public void insertDiagramLegend(Document document, URNmodelElement element, int i) {
        try {
            int figureNo = i + 1;
            Table headerTable = ReportUtils.createTable(1, 1, 0, 100);

            Chunk name = new Chunk("Figure " + figureNo + " - " + element.getName(), figureLegendFont);
            Cell nameCell = new Cell(name);
            nameCell.setColspan(1);
            nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            nameCell.setBorderWidthTop(2f);

            headerTable.addCell(nameCell);
            document.add(headerTable);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

    /**
     * insert the diagram description
     * 
     * @param document
     *            the document we are reporting into
     * @param element
     *            URN element documented
     */
    public void insertDiagramDescription(Document document, URNmodelElement element) {
        try {

            Table table = ReportUtils.createTable(1, 2, 0, 100);

            Chunk chunk = new Chunk("Description ", descriptionBoldFont);
            Cell descriptionCell = new Cell(chunk);
            descriptionCell.setColspan(1);
            descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            descriptionCell.setBorderWidthBottom(1.5f);

            Chunk descText = new Chunk(element.getDescription(), descriptionFont);

            table.addCell(descriptionCell);
            document.add(table);
            document.add(descText);

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }

}