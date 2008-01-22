package seg.jUCMNav.importexport.reports;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.eclipse.swt.SWT;

import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNspec;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * Exports as a RTF Report
 * 
 * This class contains code specific to RTF generation (headers, footers, ...)
 * 
 * @author Alain Dessureault
 * 
 */
public class RTFReport extends Report {

    /**
     * creates the data dictionary section in the report
     * 
     * @param urn
     *            the urn spec to use in order to retrieve elements
     * @param mapDiagrams
     *            the diagrams to insert and document in the report
     * @param filename
     *            the filename to create the report
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

            super.export(urn, mapDiagrams, filename, document, pagesize);

            if (!urndef.getSpecDiagrams().isEmpty()) {
                RTFReportDiagram reportDiagrams = new RTFReportDiagram();
                reportDiagrams.createRTFReportDiagramsAndDescription(document, urndef, mapDiagrams, pagesize);

            }

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