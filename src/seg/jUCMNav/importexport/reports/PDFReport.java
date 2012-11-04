package seg.jUCMNav.importexport.reports;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNspec;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Exports as a PDF Report. Implements the export functionality called by the ReportGenerator Wizard. It extends the Report class containing all generic code
 * for all report types.
 * 
 * This class contains code specific to PDF generation (headers, footers, ...)
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
        String sReportWidth = ReportGeneratorPreferences.getWidth();

        try {
			fHeight = Float.parseFloat(sReportHeight) * 72;
			fWidth = Float.parseFloat(sReportWidth) * 72;
		} catch (NumberFormatException e1) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e1.getMessage());
			e1.printStackTrace();
		}
        
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
            
            super.writeScenarioDocumentation(document, ucmspec);
            
            document.close();

        } catch (Exception e) {

            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

}