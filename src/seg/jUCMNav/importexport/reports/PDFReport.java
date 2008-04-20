package seg.jUCMNav.importexport.reports;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.importexport.ExportImageGIF;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNdefinition;
import urncore.URNmodelElement;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
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

    

}