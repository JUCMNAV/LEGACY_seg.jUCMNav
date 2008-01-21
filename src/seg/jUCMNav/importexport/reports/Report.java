package seg.jUCMNav.importexport.reports;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import urn.URNspec;

import grl.GRLspec;

import java.io.FileOutputStream;

import ucm.UCMspec;

import urncore.URNdefinition;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;

/**
 * generic Report class creating report elements common to all report types (RTF, HTML, PDF)
 * 
 * @author dessure
 * 
 */
public class Report extends URNReport {

    public float fHeight = 0;
    public float fWidth = 0;
    public Rectangle pagesize;

    private FileOutputStream fos = null;
    protected Font reportTitleFont = new Font(Font.HELVETICA, 24, Font.UNDERLINE);
    protected Font header1Font = new Font(Font.HELVETICA, 14, Font.BOLD);
    protected Font header2Font = new Font(Font.HELVETICA, 12, Font.BOLD);
    protected Font descriptionFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
    protected Font descriptionBoldFont = new Font(Font.HELVETICA, 12, Font.BOLD);
    protected Font diagramHeaderFont = new Font(Font.HELVETICA, 14, Font.BOLD);
    protected Font figureLegendFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font headerFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font footerFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font bindingsFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font bindingsHeaderFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.BOLDITALIC);
    protected Font pluginMapTitleFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
    protected Font pluginMapNameFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.BOLDITALIC);

    public static final String QUOTES = "\""; //$NON-NLS-1$
    public static final String QUOTES_COMMA = "\", "; //$NON-NLS-1$
    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String QUOTES_DOUBLE = QUOTES + QUOTES;
    public static final String END_ELEM = " )\n"; //$NON-NLS-1$
    public static final String QUOTES_END_ELEM = "\" )\n"; //$NON-NLS-1$

    protected UCMspec ucmspec;
    protected GRLspec grlspec;
    protected URNdefinition urndef;

    private String filename;

    
    public int getType() {

        return 0;
    }

    /**
     * export callback from Report generator Wizard, and from Report child classes
     * 
     * @param urn
     *            the urn specification we report on
     * @param mapDiagrams
     *            list of diagrams to be documented
     * @param filename
     *            the report filename
     * @param document
     *            the document we are reporting into
     * @param pagesize
     *            the report page size
     */
    
    public void export(URNspec urn, HashMap mapDiagrams, String filename, Document document, Rectangle pagesize) throws InvocationTargetException {
        // TODO remove all hardcoded preferences, font names and sizes
        // TODO report description strings should be externalized

        try {
            // get UCMSpec from URNSpec and iterate in scenario groups
            if (urn.getUcmspec() != null) {
                ucmspec = urn.getUcmspec();
            }
            if (urn.getGrlspec() != null) {
                grlspec = urn.getGrlspec();
            }
            if (urn.getUrndef() != null) {
                urndef = urn.getUrndef();
            }

            // Report header
            ReportHeader reportHeader = new ReportHeader();
            reportHeader.createReportHeader(document, filename);

            if (ucmspec != null) {

                // new DataDictionary object for elements reporting
                ReportDataDictionary dataDictionary = new ReportDataDictionary();
                dataDictionary.createReportDataDictionary(document, ucmspec, grlspec);

                // Strategies and their evaluations
                ReportStrategies reportStrategies = new ReportStrategies();
                reportStrategies.createReportStrategies(document, ucmspec, grlspec, urndef, pagesize);

            }

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * not used
     * 
     */
    
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

    }

}
