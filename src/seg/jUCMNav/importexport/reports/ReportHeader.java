package seg.jUCMNav.importexport.reports;

import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;

/**
 * implements the creation the the report header section
 * 
 * @author dessure
 * 
 */
public class ReportHeader extends Report {

    public ReportHeader() {

    }

    /**
     * creates the data dictionary section in the report
     * 
     * @param document
     *            the document in which the report is created
     * @param filename
     *            the name of the file used to create the report
     */
    public void createReportHeader(Document document, String filename) {
        try {
            Chunk headerText = new Chunk("jUCMNav - " + filename, headerFont);
            HeaderFooter header = new HeaderFooter(new Phrase(headerText), false);
            Chunk footerPage = new Chunk("Page ", footerFont);
            HeaderFooter footer = new HeaderFooter(new Phrase(footerPage), new Phrase("."));
            document.setHeader(header);
            document.setFooter(footer);
        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

}
