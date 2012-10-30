package seg.jUCMNav.importexport.reports;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.eclipse.swt.SWT;

import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNspec;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * Exports as a RTF Report. Implements the export functionality called by the ReportGenerator Wizard. It extends the Report class containing all generic code
 * for all report types.
 * 
 * This class contains code specific to RTF generation (headers, footers, ...)
 * 
 * @author Alain Dessureault
 * 
 */
public class RTFReport extends Report {

    /**
     * main logic to create the rtf report
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

            RtfWriter2.getInstance(document, new FileOutputStream(filename));

            document.open();
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            super.export(urn, mapDiagrams, filename, document, pagesize);

            if (!urndef.getSpecDiagrams().isEmpty()) {
                RTFReportDiagram reportDiagrams = new RTFReportDiagram();
                reportDiagrams.createRTFReportDiagramsAndDescription(document, urndef, mapDiagrams, pagesize);

            }

            super.writeScenarioDocumentation(document, ucmspec);
            
            document.close();

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

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