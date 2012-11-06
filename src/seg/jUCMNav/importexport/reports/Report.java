package seg.jUCMNav.importexport.reports;

import grl.EvaluationStrategy;
import grl.GRLspec;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import ucm.UCMspec;
import urn.URNspec;
import urncore.URNdefinition;

import com.lowagie.text.Chunk;
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
    protected Font header1Font = new Font(Font.HELVETICA, 12, Font.BOLD);
    protected Font header2Font = new Font(Font.HELVETICA, 11, Font.BOLD);
    protected Font descriptionFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
    protected Font descriptionBoldFont = new Font(Font.HELVETICA, 11, Font.BOLD);
    protected Font diagramHeaderFont = new Font(Font.HELVETICA, 12, Font.BOLD);
    protected Font figureLegendFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font headerFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font footerFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font bindingsFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.ITALIC);
    protected Font bindingsHeaderFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.BOLDITALIC);
    protected Font pluginMapTitleFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
    protected Font pluginMapNameFont = new Font(Font.HELVETICA, 10, Font.NORMAL + Font.BOLDITALIC);
    protected Font scenTitleFont = new Font(Font.HELVETICA, 11, Font.NORMAL + Font.BOLDITALIC);

    public static final String QUOTES = "\""; //$NON-NLS-1$
    public static final String QUOTES_COMMA = "\", "; //$NON-NLS-1$
    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String QUOTES_DOUBLE = QUOTES + QUOTES;
    public static final String END_ELEM = " )\n"; //$NON-NLS-1$
    public static final String QUOTES_END_ELEM = "\" )\n"; //$NON-NLS-1$

    protected UCMspec ucmspec;
    protected GRLspec grlspec;
    protected URNdefinition urndef;

    protected boolean prefShowUCMDiagrams;
    protected boolean prefShowGRLDiagrams;
    protected boolean prefShowScenarioInfo;
    protected boolean prefShowScenarioExec;
    protected boolean prefShowEvals;
    
    protected float reportWidth = Float.parseFloat(ReportGeneratorPreferences.getWidth());
	protected float reportHeight = Float.parseFloat(ReportGeneratorPreferences.getHeight());
    
    private String filename;

    public Report() {

    }

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

        try {
        	// fetch the values of the UCMSHOWUCMDIAGRAMS, UCMSHOWSCENARIOINFO and GRLSHOWGRLDIAGRAMS preferences.
        	prefShowUCMDiagrams = ReportGeneratorPreferences.getUCMSHOWUCMDIAGRAMS();
        	prefShowScenarioInfo = ReportGeneratorPreferences.getUCMSHOWSCENARIOINFO();
        	prefShowScenarioExec = ReportGeneratorPreferences.getUCMSHOWSCENARIOEXEC();
        	prefShowGRLDiagrams = ReportGeneratorPreferences.getGRLSHOWGRLDIAGRAMS();
        	prefShowEvals = ReportGeneratorPreferences.getShowGRLShowEvals();

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

            // <BM> Insert title page for PDF and RTF report
            ReportTitlePage titlePage = new ReportTitlePage();
            titlePage.CreateTitlePage(document, urn);
            // <BM>

            // Report header
            ReportHeader reportHeader = new ReportHeader();
            reportHeader.createReportHeader(document, filename);

            // new DataDictionary object for elements reporting
            ReportDataDictionary dataDictionary = new ReportDataDictionary();
            dataDictionary.createReportDataDictionary(document, ucmspec, grlspec);

            if ((hasStrategies(grlspec)) && prefShowEvals) {
                // Strategies and their evaluations
                ReportStrategies reportStrategies = new ReportStrategies();
                reportStrategies.createReportStrategies(document, ucmspec, grlspec, urndef, pagesize);
            } else {
            	document.add(Chunk.NEXTPAGE);
            }

        } catch (Exception e) {
//        	System.out.println( "In Report.export: " + e.getMessage());
        	final String errorString = e.getMessage();
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(errorString);
            e.printStackTrace();

        }

    }

    /**
     * Indicates whether the GRL spec has any non-empty strategy.
     * 
     * @param grlspec
     * @return true if there is at least one strategy with initializations
     */
    public boolean hasStrategies(GRLspec grlspec) {
        boolean result = false;

        if (grlspec != null)
            if (grlspec.getStrategies().size() > 0)
                for (int i = 0; i < grlspec.getStrategies().size(); i++) {
                    EvaluationStrategy s = (EvaluationStrategy) grlspec.getStrategies().get(i);
                    if (s.getEvaluations().size() > 0)
                        return true;
                }
        return result;
    }
    
    public void writeScenarioDocumentation(Document document, UCMspec ucmspec) {
    	
    	if (prefShowScenarioInfo || prefShowScenarioExec) {
    		ReportScenarios scenarios = new ReportScenarios();
    		// write the header of the UCM scenario documentation section
			scenarios.insertHeader(document, Messages.getString("ReportScenarios.Title"), header1Font); //$NON-NLS-1$
			
			if (prefShowScenarioExec) {
				scenarios.writeSummary(document, ucmspec);
        	}
    		if (prefShowScenarioInfo) {
        		scenarios.writeUCMScenarioInformation(document, ucmspec);
        	}
    	}
    }

    /**
     * not used
     * 
     */

    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

    }

}
