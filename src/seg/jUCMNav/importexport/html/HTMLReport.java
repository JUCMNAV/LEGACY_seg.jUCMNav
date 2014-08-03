package seg.jUCMNav.importexport.html;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.impl.QualitativeMappingImpl;
import grl.kpimodel.impl.QualitativeMappingsImpl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.ExportImageGIF;
import seg.jUCMNav.importexport.reports.URNReport;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.importexport.utils.EscapeUtils;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.strategies.BatchEvaluationUtil;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.strategies.StrategiesView;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.UCMspec;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import ucm.map.impl.PluginBindingImpl;
import ucm.map.impl.RespRefImpl;
import ucm.map.impl.StubImpl;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNlink;
import urn.URNspec;
import urncore.Component;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Export an HTML suite from a URNspec.
 * 
 * @author pchen
 * @author amiga
 * @author damyot
 * 
 */
public class HTMLReport extends URNReport {
	public static final String PAGES_LOCATION = "pages" + File.separator; //$NON-NLS-1$
	public static final String IMAGES_LOCATION = PAGES_LOCATION + "img" + File.separator; //$NON-NLS-1$

	protected static String [] excludedMDnames = { "AltName", "AltDescription", "_numEval", "_qualEval" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	protected static HashMap<String, Object> excludedMetadata = null;
	public static final int UCM_DEFINITIONS = 0;
	public static final int UCM_SCENARIOS = 1;
	public static final int GRL_DEFINITIONS = 2;

	private static int evalValue = 0;

	private HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable = new HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>>();
	private HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>> indicatorTable = new HashMap<EvaluationStrategy, HashMap<Indicator, HashMap<String, String>>>();
	
	private static StrategiesView sv = null;
	private static boolean designView = false;

	protected boolean prefShowUCMDiagrams;
	protected boolean prefShowScenarioInfo;
	protected boolean prefShowScenarioExec;
    protected boolean prefShowGRLDiagrams;
    protected boolean prefShowEvals;
    
    private boolean prefShowTrend;
    private int prefTrend;
    
	private static HashMap<GRLLinkableElement, Integer> strategyEvaluations;
	private static HashMap<Indicator, HashMap<String, String>> indicatorEvaluations;
	private static HashMap<Indicator, String> indicatorKpiConversion;
	

    
	static {
		excludedMetadata = new HashMap<String, Object>();

		for( int i = 0; i < excludedMDnames.length; i++ ) {
			excludedMetadata.put( excludedMDnames[i], null );
		}
	}

	public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
		// not used

	}

	/**
	 * Export an HTML suite from this URNspec.
	 * 
	 */
	public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
		IFigure pane;

		// fetch the values of the UCMSHOWUCMDIAGRAMS, UCMSHOWSCENARIOINFO/EXEC, GRLSHOWGRLDIAGRAMS and
		// GRLSHOWEVALS preferences.
    	prefShowUCMDiagrams = ReportGeneratorPreferences.getUCMSHOWUCMDIAGRAMS();
    	prefShowScenarioInfo = ReportGeneratorPreferences.getUCMSHOWSCENARIOINFO();
    	prefShowScenarioExec = ReportGeneratorPreferences.getUCMSHOWSCENARIOEXEC();
    	prefShowGRLDiagrams = ReportGeneratorPreferences.getGRLSHOWGRLDIAGRAMS();
    	prefShowEvals = ReportGeneratorPreferences.getShowGRLShowEvals();
    	
    	prefShowTrend = ReportGeneratorPreferences.getShowGRLEvalStrategyTrend();
    	prefTrend = Integer.parseInt(ReportGeneratorPreferences.getGRLEvalStrategyTrend());
    	
    	if (prefShowEvals) {
    		final EvaluationStrategy firstStrategy = getFirstStrategy( urn.getGrlspec() );

    		if( firstStrategy != null ) { // skip mode switch if design does not contain strategies
    			Display.getDefault().syncExec(new Runnable() {
    				public void run() {
    					if( (sv = EvaluationStrategyManager.getInstance(false).getStrategiesView()) != null ) {
    						if( !sv.isStrategyView() ) {
    							designView = true;
    							EvaluationStrategyManager.getInstance(false).setStrategy(firstStrategy);
    							sv.setStrategy(firstStrategy);
    							sv.showPage(StrategiesView.ID_STRATEGY);
    							sv.refreshScenarioIfNeeded();
    						}	
    					}
    				}
    			});
    		}
    	}
        
    	int i=0;
    	boolean isLast = false;		// determines whether the current diagram (if any diagrams are added) is the last
    								// diagram to be added
    	boolean complete = false;	// determines whether all files of the report have been exported
		
    	for (Iterator iter = mapDiagrams.keySet().iterator(); iter.hasNext();) {
    		IURNDiagram diagram = (IURNDiagram) iter.next();
    		
			isLast = (i == mapDiagrams.size() - 1);
			// get the high level IFigure to be saved
			pane = (IFigure) mapDiagrams.get(diagram);
			String diagramName = ExportWizard.getDiagramName(diagram);
			// export the diagram only if the corresponding preference value is TRUE
			if (((diagramName.contains("-Map")) && prefShowUCMDiagrams) || ((diagramName.contains("-GRLGraph")) && prefShowGRLDiagrams)) { //$NON-NLS-1$ //$NON-NLS-2$
				
				String imgPath = createImgPath(filename, diagramName);
				// export the image file
				(new ExportImageGIF()).export(pane, imgPath);
			
				// export the index pages
				String htmlPath = getPath(filename);
				
				if (isLast) {
					createIndexPages(urn, htmlPath);
				}
				
				// prepare the HTML menu item
				HTMLMenuItem htmlMenuItem = new HTMLMenuItem();
				htmlMenuItem.reset();

				htmlMenuItem.setDiagramName(EscapeUtils.escapeHTML(diagramName));
				if (diagram instanceof GRLGraph) {
					htmlMenuItem.setType(HTMLMenuItem.TYPE_GRL);
				} else {
					htmlMenuItem.setType(HTMLMenuItem.TYPE_UCM);
				}
				htmlMenuItem.setLeafText(diagramName.substring(diagramName.lastIndexOf("-") + 1)); //$NON-NLS-1$
				htmlMenuItem.setLink(diagramName + ".html"); //$NON-NLS-1$
				htmlMenuItem.setBaseX(-pane.getBounds().x);
				htmlMenuItem.setBaseY(-pane.getBounds().y);
				htmlMenuItem.setDiagram(diagram);
			
				// export the HTML or this diagram
				export(diagram, htmlPath);

				// create the XML menu content
				HTMLMenuParser htmlMenuParser = HTMLMenuParser.getParser(htmlPath);
				htmlMenuParser.addMenu(htmlMenuItem);
				
				// write the content of menu to XML file
				if (isLast) {
					exportGlobalDefinitions(urn, htmlPath, HTMLReport.UCM_DEFINITIONS);
					exportGlobalDefinitions(urn, htmlPath, HTMLReport.UCM_SCENARIOS);
					exportGlobalDefinitions(urn, htmlPath, HTMLReport.GRL_DEFINITIONS);
					htmlMenuParser.writeToFile();
					htmlMenuParser.resetDocument();
					complete = true;
				}
		}
		i++;
	}
    	// If no diagrams were added to the report, export the index pages and the report content.
    	if (!complete) {
    		String htmlPath = getPath(filename);
    		File mainDirectory = new File(htmlPath + PAGES_LOCATION);
    		if (!mainDirectory.exists()) {
    			mainDirectory.mkdirs();
    		}
    		try {
	    		File mainFile;
	    		mainFile = new File("main.html"); //$NON-NLS-1$
	    		// create file main.html if it does not exist yet
	    		if(!mainFile.exists()) {
	    			mainFile.createNewFile();
	    		}
	    		createIndexPages(urn, htmlPath);
	    		HTMLMenuParser htmlMenuParser = HTMLMenuParser.getParser(htmlPath);
	    		exportGlobalDefinitions(urn, htmlPath, HTMLReport.UCM_DEFINITIONS);
				exportGlobalDefinitions(urn, htmlPath, HTMLReport.UCM_SCENARIOS);
				exportGlobalDefinitions(urn, htmlPath, HTMLReport.GRL_DEFINITIONS);
				htmlMenuParser.writeToFile();
				htmlMenuParser.resetDocument();
    		}
    		catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}

		if (prefShowEvals) {
			if( designView ) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						sv.setStrategy(null);
						sv.showPage(StrategiesView.ID_DESIGN);
						sv.cancelStrategyMode();
					}
				});
			}
		}
	}

	private EvaluationStrategy getFirstStrategy(GRLspec grlspec) {
		for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {
			StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
			for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
				EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
				return strategy;
			}
		}
		return null;
	}

	/**
	 * Create index HTML pages used in exporting UCM/GRL maps to html pages.
	 * 
	 * @param htmlPath
	 *            the export directory
	 * @param urn
	 *            the URN spec
	 */
	private void createIndexPages(URNspec urn, String htmlPath) {
		try {
			// Generate the index page
			String srcFile = "htmltemplates/index.html"; //$NON-NLS-1$
			String desFile = htmlPath + "index.html"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the main page
			srcFile = "htmltemplates/main.html"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "main.html"; //$NON-NLS-1$
			copy(srcFile, desFile);
			fillMain(urn, desFile);

			// Generate the menu page
			srcFile = "htmltemplates/menu.html"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "menu.html"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the xml tree css file
			srcFile = "htmltemplates/xmlTree.css"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "xmlTree.css"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the report css file
			srcFile = "htmltemplates/report.css"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "report.css"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the menu css file
			srcFile = "htmltemplates/menu.css"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "menu.css"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the xml tree java script file
			srcFile = "htmltemplates/xmlTree.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "xmlTree.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the menu java script file
			srcFile = "htmltemplates/menu.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "menu.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tree xsl file
			srcFile = "htmltemplates/xmlTree.xsl"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "xmlTree.xsl"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// ToolTip files
			// Generate the tooltip chili-1.7.pack.js file
			srcFile = "htmltemplates/chili-1.7.pack.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "chili-1.7.pack.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.bgiframe.js file
			srcFile = "htmltemplates/jquery.bgiframe.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.bgiframe.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.dimensions.js file
			srcFile = "htmltemplates/jquery.dimensions.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.dimensions.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.js file
			srcFile = "htmltemplates/jquery.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.tooltip.css
			srcFile = "htmltemplates/jquery.tooltip.css"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.tooltip.css"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.tooltip.js file
			srcFile = "htmltemplates/jquery.tooltip.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.tooltip.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.tooltip.min.js file
			srcFile = "htmltemplates/jquery.tooltip.min.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.tooltip.min.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the tooltip jquery.tooltip.pack.js file
			srcFile = "htmltemplates/jquery.tooltip.pack.js"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "jquery.tooltip.pack.js"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// GIF files
			// Generate the closed.gif file
			srcFile = "htmltemplates/closed.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "closed.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the doc.gif file
			srcFile = "htmltemplates/doc.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "doc.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the open.gif file
			srcFile = "htmltemplates/open.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "open.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
			
			// Generate the icon16.gif (the jUCMNav logo) file
			srcFile = "htmltemplates/icon16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "icon16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
			
			// Generate the ucm16.gif (the UCM symbol) file
			srcFile = "htmltemplates/ucm16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "ucm16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the grl16.gif (the GRL symbol) file
			srcFile = "htmltemplates/grl16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "grl16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the ucmdef16.gif (the UCMDEF symbol) file
			srcFile = "htmltemplates/ucmdef16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "ucmdef16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
			
			// Generate the ucmscen16.gif (the UCMSCEN symbol) file
			srcFile = "htmltemplates/ucmscen16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "ucmscen16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the grldef16.gif (the GRLDEF symbol) file
			srcFile = "htmltemplates/grldef16.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "grldef16.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/LogoFinal.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "LogoFinal.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
			
			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/up.png"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "up.png"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/down.png"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "down.png"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/vary.png"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "vary.png"; //$NON-NLS-1$
			copy(srcFile, desFile);
						
			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/straight.png"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "straight.png"; //$NON-NLS-1$
			copy(srcFile, desFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Insert the appropriate information in the welcome HTML file.
	 * 
	 * @param mainFile
	 *            the main.html file
	 * @param urn
	 *            the URN spec
	 */
	private void fillMain(URNspec urn, String mainFile) {
		try {
			File file = new File(mainFile); //$NON-NLS-1$
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";  //$NON-NLS-1$  //$NON-NLS-2$
			while((line = reader.readLine()) != null)
			{
				oldtext += line + "\r\n";  //$NON-NLS-1$
			}
			reader.close();

			String newtext = oldtext.replaceAll("URN Model:", Messages.getString("HTMLReport.URNModelName"));  //$NON-NLS-1$ //$NON-NLS-2$
			newtext = newtext.replaceAll("TITLE", urn.getName());  //$NON-NLS-1$
			newtext = newtext.replaceAll("URN Model Description:", Messages.getString("HTMLReport.URNModelDesc")); //$NON-NLS-1$ //$NON-NLS-2$
			if (urn.getDescription() != null) {
				newtext = newtext.replaceAll("DESCRIPTION", urn.getDescription());  //$NON-NLS-1$
			} else {
				newtext = newtext.replaceAll("DESCRIPTION", Messages.getString("HTMLReport.URNModelDescNA"));  //$NON-NLS-1$ //$NON-NLS-2$
			}
			newtext = newtext.replaceAll("Author:", Messages.getString("HTMLReport.ModelAuthor"));	//$NON-NLS-1$ //$NON-NLS-2$
			newtext = newtext.replaceAll("AUTHOR", urn.getAuthor());	//$NON-NLS-1$
			SimpleDateFormat newFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DateFormat")); //$NON-NLS-1$
			// TODO Replace the hard-coded Locale value (to find the language in which dates were generated in the model)
			SimpleDateFormat originalDateFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DefaultDateFormat"), new Locale("en,US")); //$NON-NLS-1$ //$NON-NLS-2$
			newtext = newtext.replaceAll("Creation Date:", Messages.getString("HTMLReport.ModelCreationDate"));	//$NON-NLS-1$ //$NON-NLS-2$
			// verify if the model's creation time corresponds to a PM time
			int indexOfPMString = -1;
			boolean isPM = false;
			
			indexOfPMString = urn.getCreated().indexOf(" PM "); //$NON-NLS-1$
			
			if (indexOfPMString > -1) {
				isPM = true;
			}
			
			Date dateCreated = (Date)originalDateFormat.parse(urn.getCreated());
			String strDateCreated = newFormat.format(dateCreated);
			
			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
			// are replaced by "PM" if isPM is true
			if (isPM) {
				strDateCreated = strDateCreated.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
			}
			newtext = newtext.replaceAll("CREATIONDATE", strDateCreated);  //$NON-NLS-1$
			newtext = newtext.replaceAll("Modification Date:", Messages.getString("HTMLReport.ModelModificationDate"));	//$NON-NLS-1$ //$NON-NLS-2$	
			// verify if the model's modification time corresponds to a PM time
			indexOfPMString = -1;
			isPM = false;
			
			indexOfPMString = urn.getModified().indexOf(" PM "); //$NON-NLS-1$
			
			if (indexOfPMString > -1) {
				isPM = true;
			}
			
			Date dateModified = (Date)originalDateFormat.parse(urn.getModified());
			String strDateModified = newFormat.format(dateModified);
			
			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
			// are replaced by "PM" if isPM is true
			if (isPM) {
				strDateModified = strDateModified.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
			}
			newtext = newtext.replaceAll("MODIFICATIONDATE", strDateModified);  //$NON-NLS-1$
			newtext = newtext.replaceAll("Report Generation Date:",Messages.getString("HTMLReport.ReportGenDate"));	//$NON-NLS-1$ //$NON-NLS-2$
			
			// generate the current date using the standard Locale (English-US) and convert it to the default locale
            String strCurrentDate;
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
            strCurrentDate = df.format(new Date());
            
            // verify if current time corresponds to a PM time
            indexOfPMString = -1;
         	isPM = false;
         	indexOfPMString = strCurrentDate.indexOf(" PM "); //$NON-NLS-1$
         	if (indexOfPMString > -1) {
         		isPM = true;
         	}
         	
         	Date dateGenerated = (Date)originalDateFormat.parse(strCurrentDate);
			String strDateGenerated = newFormat.format(dateGenerated);
			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
			// are replaced by "PM" if isPM is true
			if (isPM) {
				strDateGenerated = strDateGenerated.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
			}
            newtext = newtext.replaceAll("REPORTGENDATE", strDateGenerated);  //$NON-NLS-1$
			newtext = newtext.replaceAll("Model Version:", Messages.getString("HTMLReport.URNModelVersion"));	//$NON-NLS-1$ //$NON-NLS-2$
			newtext = newtext.replaceAll("VERSION", urn.getSpecVersion());  //$NON-NLS-1$
			newtext = newtext.replaceAll("Generated by ", Messages.getString("HTMLReport.GeneratedBy")); //$NON-NLS-1$ //$NON-NLS-2$
			FileWriter writer = new FileWriter(mainFile);
			writer.write(newtext);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Copies src file to dst file. If the dst file does not exist, it is created.
	 * 
	 * @param srcPath
	 *            source path
	 * @param dstPath
	 *            destination path
	 * @throws IOException
	 */
	protected static void copy(String srcPath, String dstPath) throws IOException {
		Class location = HTMLReport.class;

		InputStream in = location.getResourceAsStream(srcPath);
		OutputStream out = new FileOutputStream(new File(dstPath));

		// Transfer bytes from in to out
		int bufLen = 1024 * 8;
		byte[] buf = new byte[bufLen];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}

		in.close();
		out.close();
	}

	/**
	 * Create path of exported image files when exporting UCM/GRL maps to html pages.
	 * 
	 * @param htmlPath
	 *            the root target directory
	 * @return the path of exported HTML file
	 */
	private String createImgPath(String htmlPath, String diagramName) {
		String imgFilePath = ""; //$NON-NLS-1$
		String imgDirectoryPath = ""; //$NON-NLS-1$

		// Add "\img" into the path
		imgDirectoryPath = getPath(htmlPath) + IMAGES_LOCATION;
		imgFilePath = imgDirectoryPath + diagramName + ".gif"; //$NON-NLS-1$
		// Create img directory
		File imgDirectory = new File(imgDirectoryPath);
		if (!imgDirectory.exists()) {
			imgDirectory.mkdirs();
		}

		return imgFilePath;
	}

	/**
	 * Get the directory path from a full path url
	 * 
	 * @param fullPath
	 * @return the directory path
	 */
	private String getPath(String fullPath) {
		String directoryPath = ""; //$NON-NLS-1$

		int indexOfLastSlash = fullPath.lastIndexOf(File.separator);

		directoryPath = fullPath.substring(0, indexOfLastSlash) + File.separator;

		return directoryPath;
	}

	/**
	 * Given IURNDiagram, generate the HTML page for this diagram
	 * 
	 * @param diagram
	 * @param directory
	 */
	private void export(IURNDiagram diagram, String directory) throws InvocationTargetException {
		HTMLMenuItem menuItem = new HTMLMenuItem();
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;

		String diagramName = ExportWizard.getDiagramName(diagram);
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"); //$NON-NLS-1$
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"); //$NON-NLS-1$
			sb.append("<head>\n"); //$NON-NLS-1$
			sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n"); //$NON-NLS-1$
			sb.append("<title>" + Messages.getString("HTMLReport.URNModel") + "</title>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// Dynamic stub menu stuff
			sb.append("<script language=JavaScript src=\"menu.js\"></script>\n"); //$NON-NLS-1$
			sb.append("<link href=\"menu.css\" rel=stylesheet>\n"); //$NON-NLS-1$
			// Library from http://bassistance.de/jquery-plugins/jquery-plugin-tooltip/
			sb.append("<link rel=\"stylesheet\" href=\"jquery.tooltip.css\" />\n"); //$NON-NLS-1$
			sb.append("<script src=\"jquery.js\" type=\"text/javascript\"></script>\n"); //$NON-NLS-1$
			sb.append("<script src=\"jquery.bgiframe.js\" type=\"text/javascript\"></script>\n"); //$NON-NLS-1$
			sb.append("<script src=\"jquery.dimensions.js\" type=\"text/javascript\"></script>\n"); //$NON-NLS-1$
			sb.append("<script src=\"chili-1.7.pack.js\" type=\"text/javascript\"></script>\n"); //$NON-NLS-1$
			sb.append("<script src=\"jquery.tooltip.js\" type=\"text/javascript\"></script>\n"); //$NON-NLS-1$
			sb.append("<script type=\"text/javascript\">\n"); //$NON-NLS-1$
			sb.append("$(function() {\n"); //$NON-NLS-1$
			sb.append("  $(\"map *\").Tooltip({\n"); //$NON-NLS-1$
			sb.append("    delay: 0,\n"); //$NON-NLS-1$
			sb.append("    opacity: 0.80\n"); //$NON-NLS-1$
			sb.append("  });\n"); //$NON-NLS-1$
			sb.append("});\n"); //$NON-NLS-1$
			sb.append("</script>\n"); //$NON-NLS-1$
			// Basic style
			sb.append("<style>\n"); //$NON-NLS-1$
			sb.append("body {\n"); //$NON-NLS-1$
			sb.append("font: 11px Arial,Tahoma,Verdana,Geneva,Helvetica,sans-serif;\n"); //$NON-NLS-1$
			sb.append("}\n"); //$NON-NLS-1$
			sb.append("</style>\n"); //$NON-NLS-1$
			sb.append("</head>\n"); //$NON-NLS-1$
			sb.append("<body style=\"margin: 3px; padding: 3px\">\n"); //$NON-NLS-1$

			Iterator nodeIter = null;

			EList nodes = diagram.getNodes();
			if (!nodes.isEmpty()) {
				nodeIter = nodes.iterator();

				int top = 20;
				int left = 30;

				sb.append("<div align=\"left\" style=\"top:" + top + "px; left:" + left + "px;\"><font size=\"+2\">" + EscapeUtils.escapeHTML(diagramName.substring(diagramName.lastIndexOf("-") + 1)) + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						"</font><font size=\"+1\"><i>" //$NON-NLS-1$
						+ MapType(diagram)
						+ "</i></font></br><img src=\"img/" + diagramName + ".gif\" border=\"0\" style=\"top:" + top + "px; left:0px;\" usemap=\"#tooltips\" />\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("<script language=\"JavaScript\">\n"); //$NON-NLS-1$
				sb.append("<!--\n"); //$NON-NLS-1$

				int numOfStub = 0;
				while (nodeIter.hasNext()) {
					Object obj = nodeIter.next();

					if (obj instanceof StubImpl) {
						numOfStub++;
						StubImpl stub = (StubImpl) obj;

						EList bindings = stub.getBindings();
						Iterator bindIter = null;
						if (!bindings.isEmpty()) {

							sb.append("var stubHierarchy" + numOfStub + " = [\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("['', null,\n"); //$NON-NLS-1$

							int height = 15;
							int width = 15;

							bindIter = bindings.iterator();

							while (bindIter.hasNext()) {
								obj = bindIter.next();
								if (obj instanceof PluginBindingImpl) {
									PluginBindingImpl pluginBinding = (PluginBindingImpl) obj;
									UCMmap childMap = pluginBinding.getPlugin();

									// get plugin diagram file name
									String pluginDiagramName = ExportWizard.getDiagramName(childMap);

									sb.append("[map('" + EscapeUtils.escapeHTML(pluginDiagramName.substring(pluginDiagramName.lastIndexOf("-") + 1)) + //$NON-NLS-1$ //$NON-NLS-2$
											"'), '" + pluginDiagramName + ".html', [thumbnails('" + pluginDiagramName + ".gif')]]\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

									if (bindIter.hasNext()) {
										sb.append(","); //$NON-NLS-1$
									}
								}
							}
							// end while

							sb.append("]\n"); //$NON-NLS-1$
							sb.append("]\n"); //$NON-NLS-1$

							sb.append("var stubConfig" + numOfStub + " = [\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("{\n"); //$NON-NLS-1$
							sb.append("'height' :  " + height + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'width' :  " + width + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstX' :  " + (stub.getX() + menuItem.getBaseX() - width / 2) + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstY' :  " + (stub.getY() + menuItem.getBaseY() + top - height / 2) + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'nextX' :  " + "0,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'hideAfter' :  " + "200,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'css' :  " + "'gurtl0',\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'trace' :  " + "true\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("},\n"); //$NON-NLS-1$

							sb.append("{"); //$NON-NLS-1$
							sb.append("'height' :  " + "23,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'width' :  " + "120,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstY' :  " + "5,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstX' :  " + "-55,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'nextY' :  " + "-1,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'target' :  " + "'_self',\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'css' :  " + "'gurtl1'\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("},\n"); //$NON-NLS-1$

							sb.append("{"); //$NON-NLS-1$
							sb.append("'width' :  " + "174,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'height' :  " + "117,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstY' :  " + "0,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstX' :  " + "120,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'css' :  " + "'gurtl2'\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("}\n"); //$NON-NLS-1$
							sb.append("];\n"); //$NON-NLS-1$

							sb.append("new menu (stubHierarchy" + numOfStub + ", stubConfig" + numOfStub + ");\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						} else {
							// System.out.println("No binds existing.");
						}
					}
				}
				// end while

				sb.append("-->\n"); //$NON-NLS-1$
				sb.append("</script>\n"); //$NON-NLS-1$
			} else {
				// System.out.println("No nodes existing.");
			}

			if (diagram instanceof UCMmap) {
				OutputMapInfo(diagram, sb);
				OutputResponsibilityReferences(diagram, sb);
				OutputStartPointData(diagram, sb);
				OutputEndPointData(diagram, sb);
				OutputOrForkGuards(diagram, sb);
				OutputStubBindings(diagram, sb);
				OutputComponentURNlinks(diagram, sb);
			} else { // GRLGraph
				OutputGRLDiagramInfo(diagram, sb);
				OutputGRLIntentionalElements(diagram, sb);
				OutputGRLBeliefs(diagram, sb);
				OutputIntentionalElementURNlinks(diagram, sb);
				OutputActorURNlinks(diagram, sb);
			}

			// Add tool tips with an image map
			if (diagram.getNodes().size() > 0) {
				int height = 10;
				int width = 10;

				sb.append("<map name=\"tooltips\">\n"); //$NON-NLS-1$
				for (Iterator iter1 = diagram.getNodes().iterator(); iter1.hasNext();) {
					IURNNode specNode = (IURNNode) iter1.next();

					if (specNode instanceof RespRef) {
						RespRef respRef = (RespRef) specNode;
						Responsibility responsibility = respRef.getRespDef();
						sb.append("<area shape=\"rect\" coords=\"" + (respRef.getX() - width / 2) + ", " + (respRef.getY() - height / 2) + ", " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								+ (respRef.getX() + width / 2) + ", " + (respRef.getY() + height / 2) + "\" " + "title=\"" + EscapeUtils.escapeHTML(responsibility.getName()) + ": " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								+ EscapeUtils.escapeHTML(notNull(responsibility.getDescription())) + "\">\n"); //$NON-NLS-1$
					} else if (specNode instanceof StartPoint) {
						StartPoint startPoint = (StartPoint) specNode;
						sb.append("<area shape=\"circle\" coords=\"" + startPoint.getX() + ", " + startPoint.getY() + ", 10\" " + "title=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								+ EscapeUtils.escapeHTML(startPoint.getName()) + ": " + EscapeUtils.escapeHTML(notNull(startPoint.getDescription())) + "\">\n"); //$NON-NLS-1$ //$NON-NLS-2$
					} else if (specNode instanceof EndPoint) {
						EndPoint endPoint = (EndPoint) specNode;
						sb.append("<area shape=\"rect\" coords=\"" + (endPoint.getX() - width / 2) + ", " + (endPoint.getY() - height / 2) + ", " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								+ (endPoint.getX() + width / 2) + ", " + (endPoint.getY() + height / 2) + "\" " + "title=\"" + EscapeUtils.escapeHTML(endPoint.getName()) + ": " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								+ EscapeUtils.escapeHTML(notNull(endPoint.getDescription())) + "\">\n"); //$NON-NLS-1$
					}

				}

				sb.append("<area shape=\"default\" nohref>\n</map></br>\n"); //$NON-NLS-1$
			}

			sb.append("</div>\n"); //$NON-NLS-1$
			sb.append("</body>\n"); //$NON-NLS-1$
			sb.append("</html>\n"); //$NON-NLS-1$

			fos = new FileOutputStream(directory + PAGES_LOCATION + diagramName + ".html"); //$NON-NLS-1$
			bos = new BufferedOutputStream(fos);

			bos.write(sb.toString().getBytes(), 0, sb.length());
			sb = null; // help garbage collector
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	private void exportGlobalDefinitions(URNspec urn, String directory, int type) throws InvocationTargetException {

		BufferedOutputStream bos = null;
		FileOutputStream fos = null;

		String pageName = null;

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"); //$NON-NLS-1$
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"); //$NON-NLS-1$
			sb.append("<head>\n"); //$NON-NLS-1$
			sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n"); //$NON-NLS-1$
			sb.append("<title>" + Messages.getString("HTMLReport.URNModel") + "</title>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// Basic style
			sb.append("<style>\n"); //$NON-NLS-1$
			sb.append("body {\n"); //$NON-NLS-1$
			sb.append("font: 11px Arial,Tahoma,Verdana,Geneva,Helvetica,sans-serif;\n"); //$NON-NLS-1$
			sb.append("}\n"); //$NON-NLS-1$
			sb.append("</style>\n"); //$NON-NLS-1$
			sb.append("</head>\n"); //$NON-NLS-1$
			sb.append("<body style=\"margin: 3px; padding: 3px\">\n"); //$NON-NLS-1$

			if ( type == UCM_DEFINITIONS ) {
				outputUCM_Definitions(urn, sb);
				pageName = "UCM_Definitions"; //$NON-NLS-1$
				
			} else if ( type == UCM_SCENARIOS) {
				outputUCM_Scenarios(urn.getUcmspec(), sb);
				pageName = "UCM_Scenarios"; //$NON-NLS-1$
				
			} else { // GRL_DEFINITIONS
				outputGRL_Definitions(urn, sb);
				pageName = "GRL_Definitions"; //$NON-NLS-1$
			}

			sb.append("</div>\n"); //$NON-NLS-1$
			sb.append("</body>\n"); //$NON-NLS-1$
			sb.append("</html>\n"); //$NON-NLS-1$

			fos = new FileOutputStream(directory + PAGES_LOCATION + pageName + ".html"); //$NON-NLS-1$
			bos = new BufferedOutputStream(fos);

			bos.write(sb.toString().getBytes(), 0, sb.length());
			sb = null; // help garbage collector
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void OutputResponsibilityReferences(IURNDiagram diagram, StringBuffer sb) {
		// Describe table for responsibility references

		if (!ReportGeneratorPreferences.getUCMSHOWRESPONSIBILITY())
			return;

		if (!hasNodeType(diagram.getNodes(), RespRefImpl.class))
			return;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.Responsibilities") + "</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Description") + "</b></td><td><b>" + Messages.getString("HTMLReport.PseudoCode") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td><td><b>" + Messages.getString("HTMLReport.URNLinks") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof RespRef) {
				Responsibility responsibility = ((RespRef) specNode).getRespDef();
				sb.append("<tr><td>" + EscapeUtils.escapeHTML(responsibility.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(responsibility.getDescription())) + "&nbsp;</td><td>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+ EscapeUtils.escapeHTML(notNull(responsibility.getExpression())).replace("\r\n", "<br></br>") + "&nbsp;</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				InsertMetadataInTable(responsibility.getMetadata(), sb);
				InsertURNLinks(responsibility.getToLinks(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}
		}
		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private void InsertMetadataInTable(EList metadata, StringBuffer sb) {

		boolean firstLine = true;

		if (!this.isMetadataMeaningful(metadata)) {
			sb.append("<td></td>"); //$NON-NLS-1$
		} else {
			sb.append("<td>"); //$NON-NLS-1$
			for (Iterator iter = metadata.iterator(); iter.hasNext();) {
				Metadata mdata = (Metadata) iter.next();
				if( isMetadataMeaningful(mdata.getName())) {
					if( !firstLine ) {
						sb.append("<br></br>"); //$NON-NLS-1$
					}
					sb.append("\"" + EscapeUtils.escapeHTML(mdata.getName()) + "\" = \"" + EscapeUtils.escapeHTML(mdata.getValue()) + "\"&nbsp;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					firstLine = false;
				}
			}
			sb.append("&nbsp;</td>\n"); //$NON-NLS-1$
		}
	}

	private void InsertURNLinks(EList urnLinks, StringBuffer sb) {
		if (urnLinks.isEmpty()) {
			sb.append("<td></td>"); //$NON-NLS-1$
		} else {
			sb.append("<td>"); //$NON-NLS-1$
			for (Iterator iter = urnLinks.iterator(); iter.hasNext();) {
				URNlink link = (URNlink) iter.next();
				if (link.getFromElem() instanceof IntentionalElement) {
					IntentionalElement ie = (IntentionalElement) link.getFromElem();
					sb.append(EscapeUtils.escapeHTML(ie.getName()) + " (" + EscapeUtils.escapeHTML(ie.getType().getName()) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				if (iter.hasNext())
					sb.append("<br></br>"); //$NON-NLS-1$
			}
			sb.append("&nbsp;</td>\n"); //$NON-NLS-1$
		}
	}

	private void OutputStartPointData(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;
		StartPoint sp;

		if (!ReportGeneratorPreferences.getUCMSHOWSTARTPOINT())
			return;

		// determine if any start points have preconditions or metadata
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof StartPoint)
				hasData = hasStartPointData((StartPoint) specNode);
		}

		if (!hasData)
			return;

		sb
		.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.StartPoints") + "</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Precondition") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof StartPoint) {
				sp = (StartPoint) specNode;
				if (ReportUtils.notEmpty(sp.getPrecondition().getLabel()) || isMetadataMeaningful( sp.getMetadata() )) {
					sb.append("<tr><td>" + EscapeUtils.escapeHTML(sp.getName()) + "</td><td>[" + EscapeUtils.escapeHTML(sp.getPrecondition().getLabel()) + "] ==&gt; " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ EscapeUtils.escapeHTML(notNull(sp.getPrecondition().getExpression())) + "&nbsp;</td>\n"); //$NON-NLS-1$
					InsertMetadataInTable(sp.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private boolean hasStartPointData(StartPoint sp) {
		return (ReportUtils.notEmpty(sp.getPrecondition().getLabel()) || isMetadataMeaningful( sp.getMetadata() ));
	}

	private void OutputEndPointData(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;
		EndPoint ep;

		if (!ReportGeneratorPreferences.getUCMSHOWENDPOINT())
			return;

		// determine if any end points have postconditions or metadata
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof EndPoint)
				hasData = hasEndPointData((EndPoint) specNode);
		}

		if (!hasData)
			return;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.EndPoints") + "</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Postcondition") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof EndPoint) {
				ep = (EndPoint) specNode;
				if (hasEndPointData(ep)) {
					sb.append("<tr><td>" + EscapeUtils.escapeHTML(ep.getName()) + "</td><td>[" + EscapeUtils.escapeHTML(ep.getPostcondition().getLabel()) + "] ==&gt; " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ EscapeUtils.escapeHTML(notNull(ep.getPostcondition().getExpression())) + "&nbsp;</td>"); //$NON-NLS-1$
					InsertMetadataInTable(ep.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private boolean hasEndPointData(EndPoint ep) {
		return (ReportUtils.notEmpty(ep.getPostcondition().getLabel()) || isMetadataMeaningful( ep.getMetadata() ));
	}

	private void OutputOrForkGuards(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;
		boolean firstCondition = true;

		if (!ReportGeneratorPreferences.getUCMSHOWORFORK())
			return;

		// determine if any or forks have either guard conditions or metadata
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof OrFork) {
				OrFork of = (OrFork) specNode;
				if (hasOrForkData(of)) {
					hasData = true;
					break;
				}
			}
		}

		if (!hasData)
			return;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.OrForkDesc") + "</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<tr><td><b>" + Messages.getString("HTMLReport.GuardConditions") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof OrFork) {
				OrFork orFork = (OrFork) specNode;

				if (!hasOrForkData(orFork))
					continue;

				firstCondition = true;

				sb.append("<tr>"); //$NON-NLS-1$

				for (Iterator iter1 = orFork.getSucc().iterator(); iter1.hasNext();) {
					NodeConnection nc = (NodeConnection) iter1.next();
					Condition orCondition = nc.getCondition();

					if (orCondition != null) {
						if (ReportUtils.notEmpty(orCondition.getLabel())) {
							if (firstCondition) {
								sb.append("<td>"); //$NON-NLS-1$
								firstCondition = false;
							} else
								sb.append("<br></br>"); //$NON-NLS-1$
						}
						sb.append("[" + EscapeUtils.escapeHTML(orCondition.getLabel()) + "] ==&gt; " + EscapeUtils.escapeHTML(notNull(orCondition.getExpression())) + "(" + nc.getProbability() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					}
				}

				if (firstCondition) { // if no conditions were found insert empty column
					sb.append("<td></td>"); //$NON-NLS-1$
				} else { // terminate column
					sb.append("</td>"); //$NON-NLS-1$
				}

				InsertMetadataInTable(orFork.getMetadata(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private boolean hasOrForkData(OrFork orFork) {
		if (isMetadataMeaningful( orFork.getMetadata() )) {
			return true;
		}
		for (Iterator iter1 = orFork.getSucc().iterator(); iter1.hasNext();) {
			Condition orCondition = ((NodeConnection) iter1.next()).getCondition();
			if (orCondition != null) {
				return true;
			}
		}

		return false; // default
	}

	private void OutputStubBindings(IURNDiagram diagram, StringBuffer sb) {
		boolean hasBindings = false;
		String stub_type;
		StringBuffer inputBuffer, outputBuffer;

		if (!ReportGeneratorPreferences.getUCMSHOWSTUB())
			return;

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof Stub) {
				Stub stub = (Stub) specNode;

				if (stub.getBindings().isEmpty())
					continue;

				if (!hasBindings) { // output heading for stubs only one time
					sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.Stubs") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					hasBindings = true;
				}

				if (stub.isDynamic())
					stub_type = Messages.getString("HTMLReport.DynamicStub"); //$NON-NLS-1$
				else
					stub_type = Messages.getString("HTMLReport.StaticStub"); //$NON-NLS-1$

				sb.append("<h3>" + stub_type + EscapeUtils.escapeHTML(stub.getName()) + "</h3><hr></hr>\n"); //$NON-NLS-1$ //$NON-NLS-2$

				for (Iterator bindings = stub.getBindings().iterator(); bindings.hasNext();) {

					PluginBinding binding = (PluginBinding) bindings.next();

					String pluginDiagramName = ExportWizard.getDiagramName(binding.getPlugin());

					sb.append("<h4><center><a href=\"" + pluginDiagramName + ".html\">" + Messages.getString("HTMLReport.PluginMap") + EscapeUtils.escapeHTML(binding.getPlugin().getName()) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ "</a></center></h4>\n"); //$NON-NLS-1$
					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$

					inputBuffer = new StringBuffer();
					outputBuffer = new StringBuffer();

					for (Iterator ins = binding.getIn().iterator(); ins.hasNext();) {
						InBinding inBinding = (InBinding) ins.next();

						int stubEntryIndex = 0;
						if (stub.getSucc().indexOf(inBinding.getStubEntry()) == -1) {
							stubEntryIndex = 1;
						} else {
							stubEntryIndex = stub.getSucc().indexOf(inBinding.getStubEntry()) + 1;
						}

						inputBuffer.append("IN " + stubEntryIndex + " &lt;-&gt; " + EscapeUtils.escapeHTML(inBinding.getStartPoint().getName()) + "<br></br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					}

					for (Iterator outs = binding.getOut().iterator(); outs.hasNext();) {
						OutBinding outBinding = (OutBinding) outs.next();

						int stubExitIndex = 0;
						stubExitIndex = stub.getSucc().indexOf(outBinding.getStubExit()) + 1;

						outputBuffer.append("OUT " + stubExitIndex + " <-> " + EscapeUtils.escapeHTML(outBinding.getEndPoint().getName()) + "<br></br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					}

					sb
					.append("<tr><td><b>" + Messages.getString("HTMLReport.InputBindings") + "</b></td><td>" + inputBuffer + "</td><td><b>" + Messages.getString("HTMLReport.OutputBindings") + "</b></td><td>" + outputBuffer //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
							+ "</td></tr>"); //$NON-NLS-1$
					sb.append("</tbody></table></br>\n"); // end of input / output bindings table //$NON-NLS-1$

					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$

					sb.append("<tr><td><table style=\"text-align: left; width: 15%;\" border=\"0\" cellpadding=\"1\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
					sb.append("<tr><center><b>" + Messages.getString("HTMLReport.Precondition") + "</b></center></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					sb.append("</tbody></table>"); //$NON-NLS-1$

					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$

					if (ReportUtils.notEmpty(binding.getPrecondition().getLabel())) {
						sb.append("<tr><td>" + Messages.getString("HTMLReport.Label") + " </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getLabel()) + "</td></tr>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					}

					if (ReportUtils.notEmpty(binding.getPrecondition().getExpression())) {
						sb.append("<tr><td>" + Messages.getString("HTMLReport.Expression") + " </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getExpression()) + "</td></tr>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					}

					if (ReportUtils.notEmpty(binding.getPrecondition().getDescription())) {
						sb.append("<tr><td>" + Messages.getString("HTMLReport.Description") + ": </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getDescription()) + "</td></tr>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					}

					sb.append("<tr><td>" + Messages.getString("HTMLReport.Transaction") + ": </td><td>" + binding.isTransaction() + "</td></tr>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					sb.append("<tr><td>" + Messages.getString("HTMLReport.Probability") + ": </td><td>" + binding.getProbability() + "</td></tr>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					sb.append("</tbody></table></td>\n</tbody></table></br>\n"); //$NON-NLS-1$
				}

				if (isMetadataMeaningful( stub.getMetadata() )) {
					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
					sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					InsertMetadataInTable(stub.getMetadata(), sb);
					sb.append("</tr></tbody></table></br>\n"); //$NON-NLS-1$
				}
			}
		}

		inputBuffer = null;
		outputBuffer = null;
	}

	private void OutputMapInfo(IURNDiagram diagram, StringBuffer sb) {
		if (!ReportGeneratorPreferences.getUCMSHOWDESC())
			return;

		if (ReportUtils.notEmpty(((UCMmap) diagram).getDescription())) {
			sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.MapDescription") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(((UCMmap) diagram).getDescription())); //$NON-NLS-1$
		}

		EList urnLinks = ((UCMmap) diagram).getToLinks();
		if (!urnLinks.isEmpty()) {

			sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.URNLinks") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			for (Iterator iter = urnLinks.iterator(); iter.hasNext();) {
				URNlink link = (URNlink) iter.next();
				if (link.getFromElem() instanceof IntentionalElement) {
					IntentionalElement ie = (IntentionalElement) link.getFromElem();
					sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(ie.getName()) + " (" + EscapeUtils.escapeHTML(ie.getType().getName()) + ")<br></br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
		}

		InsertMetadata(((UCMmap) diagram).getMetadata(), sb);
	}

	private void OutputGRLDiagramInfo(IURNDiagram diagram, StringBuffer sb) {
		if (ReportUtils.notEmpty(((GRLGraph) diagram).getDescription())) {
			sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.DiagramDesc") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(((GRLGraph) diagram).getDescription()) ); //$NON-NLS-1$
		}

		InsertMetadata(((GRLGraph) diagram).getMetadata(), sb);
	}

	private void InsertMetadata(EList metadata, StringBuffer sb) {

		if (!this.isMetadataMeaningful(metadata))
			return;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.Metadata") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		for (Iterator iter = metadata.iterator(); iter.hasNext();) {
			Metadata mdata = (Metadata) iter.next();
			if( isMetadataMeaningful(mdata.getName())) {
				sb.append("&nbsp;&nbsp;&nbsp;\"" + EscapeUtils.escapeHTML(mdata.getName()) + "\" = \"" + EscapeUtils.escapeHTML(mdata.getValue()) + "\"<br></br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
	}

	private void OutputGRLBeliefs(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;

		if (!ReportGeneratorPreferences.getGRLShowBeliefs())
			return;

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if (currentElement instanceof Belief)
				hasData = hasGRLBeliefData((Belief) currentElement);
		}

		if (!hasData)
			return;

		sb.append("<h2>" + Messages.getString("HTMLReport.Beliefs") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
		sb.append("<tr><td><b>Name</b></td><td><b>" + Messages.getString("HTMLReport.Description") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();

			if (currentElement instanceof Belief) {
				Belief currentBelief = (Belief) currentElement;
				if (hasGRLBeliefData(currentBelief)) {
					sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentBelief.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(currentBelief.getDescription())) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					InsertMetadataInTable(currentBelief.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private boolean hasGRLBeliefData(Belief belief) {
		return (ReportUtils.notEmpty(belief.getDescription()) || isMetadataMeaningful( belief.getMetadata() ));
	}

	private void OutputGRLIntentionalElements(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;

		if (!ReportGeneratorPreferences.getGRLShowIntentionalElements())
			return;

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if (currentElement instanceof IntentionalElementRef) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
				hasData = hasGRLIntentionalElementData(ie);
			}
		}

		if (!hasData)
			return;

		sb.append("<h2>" + Messages.getString("HTMLReport.IntentionalElements") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
		sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Description") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();

			if (currentElement instanceof IntentionalElementRef) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();

				if (hasGRLIntentionalElementData(ie)) {
					sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(ie.getDescription())) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					InsertMetadataInTable(ie.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private boolean hasGRLIntentionalElementData(IntentionalElement ie) {
		return (ReportUtils.notEmpty(ie.getDescription()) || isMetadataMeaningful( ie.getMetadata() ));
	}

	private void OutputIntentionalElementURNlinks(IURNDiagram diagram, StringBuffer sb) {
		boolean hasData = false;

		if (!ReportGeneratorPreferences.getShowURNLinks())
			return;

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if (currentElement instanceof IntentionalElementRef) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
				if (!ie.getFromLinks().isEmpty() || !ie.getToLinks().isEmpty())
					hasData = true;
			}
		}

		if (!hasData)
			return;

		sb.append("<h2>" + Messages.getString("HTMLReport.IntElementsURNLinks") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
		sb
		.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b><i>(" + Messages.getString("HTMLReport.Direction") + ")</i></td><td><b>" + Messages.getString("HTMLReport.LinkType") + "</b></td><td><b>(</b><i> " + Messages.getString("HTMLReport.Direction") + " </i> <b>" + Messages.getString("HTMLReport.ElementType") + ")" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if (currentElement instanceof IntentionalElementRef) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();

				for (Iterator iter1 = ie.getFromLinks().iterator(); iter1.hasNext();) {

					URNlink link = (URNlink) iter1.next();
					String elementType = link.getToElem().getClass().getName();
					elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

					sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "<i>(" + Messages.getString("HTMLReport.From") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.To") + " </i>" + elementType //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
							+ ") " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
					InsertMetadataInTable(link.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}

				for (Iterator iter1 = ie.getToLinks().iterator(); iter1.hasNext();) {

					URNlink link = (URNlink) iter1.next();
					String elementType = link.getFromElem().getClass().getName();
					elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

					sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "<i>(" + Messages.getString("HTMLReport.To") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.From") + " </i>" + elementType //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
							+ ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
					InsertMetadataInTable(link.getMetadata(), sb);
					sb.append("</tr>\n"); //$NON-NLS-1$
				}

			}
		}
		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private void OutputActorURNlinks(IURNDiagram diagram, StringBuffer sb) {
		ActorRef currentActorRef;
		Actor currentActor;
		boolean hasURNlinks = false;

		if (!ReportGeneratorPreferences.getShowURNLinks())
			return;

		for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext() && !hasURNlinks;) {
			currentActorRef = (ActorRef) iter.next();
			currentActor = (Actor) currentActorRef.getContDef();
			if (!currentActor.getFromLinks().isEmpty() || !currentActor.getToLinks().isEmpty())
				hasURNlinks = true;
		}

		if (!hasURNlinks)
			return;

		sb.append("<h2>" + Messages.getString("HTMLReport.ActorURNLinks") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
		sb
		.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b><i>(" + Messages.getString("HTMLReport.Direction") + ")</i></td><td><b>" + Messages.getString("HTMLReport.LinkType") + "</b></td><td><b>(</b><i>" + Messages.getString("HTMLReport.Direction") + "</i> <b>" + Messages.getString("HTMLReport.ElementType") + ")" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$

		for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
			currentActorRef = (ActorRef) iter.next();
			currentActor = (Actor) currentActorRef.getContDef();

			for (Iterator iter1 = currentActor.getFromLinks().iterator(); iter1.hasNext();) {

				URNlink link = (URNlink) iter1.next();
				String elementType = link.getToElem().getClass().getName();
				elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

				sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentActor.getName()) + "<i>(" + Messages.getString("HTMLReport.From") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.To") + " </i>" + elementType //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						+ " ) " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
				InsertMetadataInTable(link.getMetadata(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}

			for (Iterator iter1 = currentActor.getToLinks().iterator(); iter1.hasNext();) {

				URNlink link = (URNlink) iter1.next();
				String elementType = link.getFromElem().getClass().getName();
				elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

				sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentActor.getName()) + "<i>(" + Messages.getString("HTMLReport.To") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.From") + " </i>" + elementType //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						+ ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
				InsertMetadataInTable(link.getMetadata(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	private void OutputComponentURNlinks(IURNDiagram diagram, StringBuffer sb) {
		ComponentRef currentComponentRef;
		Component currentComponent;
		boolean hasURNlinks = false;

		if (!ReportGeneratorPreferences.getShowURNLinks())
			return;

		for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext() && !hasURNlinks;) {
			currentComponentRef = (ComponentRef) iter.next();
			currentComponent = (Component) currentComponentRef.getContDef();
			if (!currentComponent.getFromLinks().isEmpty() || !currentComponent.getToLinks().isEmpty())
				hasURNlinks = true;
		}

		if (!hasURNlinks)
			return;

		sb.append("<h2>" + Messages.getString("HTMLReport.ComponentURNLinks") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
		sb
		.append("<tr><td><b>" + Messages.getString("HTMLReport.Name") + "</b><i>(" + Messages.getString("HTMLReport.Direction") + ")</i></td><td><b>" + Messages.getString("HTMLReport.LinkType") + "</b></td><td><b>(</b><i> " + Messages.getString("HTMLReport.Direction") + " </i> <b>" + Messages.getString("HTMLReport.ElementType") + ")" + Messages.getString("HTMLReport.Name") + "</b></td><td><b>" + Messages.getString("HTMLReport.Metadata") + "</b></td></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$

		for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
			currentComponentRef = (ComponentRef) iter.next();
			currentComponent = (Component) currentComponentRef.getContDef();

			for (Iterator iter1 = currentComponent.getFromLinks().iterator(); iter1.hasNext();) {

				URNlink link = (URNlink) iter1.next();
				String elementType = link.getToElem().getClass().getName();
				elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

				sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentComponent.getName()) + "<i>(" + Messages.getString("HTMLReport.FromCapital") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.To") + " </i>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						+ elementType + " ) " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
				InsertMetadataInTable(link.getMetadata(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}

			for (Iterator iter1 = currentComponent.getToLinks().iterator(); iter1.hasNext();) {

				URNlink link = (URNlink) iter1.next();
				String elementType = link.getFromElem().getClass().getName();
				elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

				sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentComponent.getName()) + "<i>(" + Messages.getString("HTMLReport.ToCapital") + ")</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> " + Messages.getString("HTMLReport.From") + " </i>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						+ elementType + ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
				InsertMetadataInTable(link.getMetadata(), sb);
				sb.append("</tr>\n"); //$NON-NLS-1$
			}
		}

		sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
	}

	/**
	 * Determines if the list of URN nodes for a diagram contains a node of type Note: often, these are the implementation types (e.g., RespRefImpl instead of
	 * RespRef).
	 * 
	 * @param urnNodes
	 *            list of IURNNode (non-casted)
	 * @param nodeType
	 *            type of URN node we look for
	 * @return true if the node list contains a node of type nodeType
	 */
	private boolean hasNodeType(EList urnNodes, Class nodeType) {
		boolean found = false;
		Class currentNodeType;
		for (Iterator iter = urnNodes.iterator(); iter.hasNext();) {
			currentNodeType = iter.next().getClass();
			if (currentNodeType == nodeType) {
				found = true;
				break;
			}
		}
		return found;
	}

	/**
	 * Convert null Strings to empty Strings, leave the other Strings as is.
	 * 
	 * @param s
	 *            the possibly null String
	 * @return a non-null String
	 */
	private String notNull(String s) {
		if (s == null)
			return ""; //$NON-NLS-1$
		else
			return s;
	}

	private String MapType(IURNDiagram diagram) {
		if (diagram instanceof UCMmap) {
			if (((UCMmap) diagram).getParentStub().size() == 0)
				return Messages.getString("HTMLReport.UCMRootMap"); //$NON-NLS-1$
			else
				return Messages.getString("HTMLReport.UCMPluginMap"); //$NON-NLS-1$
		} else
			return Messages.getString("HTMLReport.GRLGraph"); //$NON-NLS-1$
	}

	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected boolean isMetadataMeaningful( EList metadata ) {
		for (Iterator iter = metadata.iterator(); iter.hasNext();) {
			Metadata mdata = (Metadata) iter.next();
			if( isMetadataMeaningful(mdata.getName()) ) {
				return true;
			}
		}        

		return false;
	}

	public static boolean isMetadataMeaningful( String name ) {
		return !excludedMetadata.containsKey( name );
	}

	private void outputUCM_Definitions(URNspec urn, StringBuffer sb) {

		UCMspec ucmspec = urn.getUcmspec();

		sb.append("<h1>" + Messages.getString("HTMLReport.UCMDefinitions") + "</h1>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		outputVariables(ucmspec, sb);    
		outputEnumerationTypes(ucmspec, sb);    
		outputScenarios(ucmspec, sb);    
	}

	private void outputVariables(UCMspec ucmspec, StringBuffer sb) {

		int i = 1;
		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.Variables") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		if ((prefShowUCMDiagrams) || (prefShowScenarioInfo) || (prefShowScenarioExec)) {
			for (Iterator iter = ucmspec.getVariables().iterator(); iter.hasNext();) {

				Variable var = (Variable) iter.next();
				String varType = var.getType();

				sb.append("&nbsp;&nbsp;&nbsp;" + i++ + ". " + EscapeUtils.escapeHTML(var.getName()) ); //$NON-NLS-1$ //$NON-NLS-2$

				if (var.getEnumerationType() != null) { // the variable type is enumeration
					sb.append( " (Enum " + var.getEnumerationType().getName() + ")" ); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					sb.append( " (" + varType + ")" ); //$NON-NLS-1$ //$NON-NLS-2$
				}

				if( ReportUtils.notEmpty(var.getDescription()) ) {
					sb.append( ": " + EscapeUtils.escapeHTML(var.getDescription()) ); //$NON-NLS-1$
				}
				sb.append( "<br></br>\n" ); //$NON-NLS-1$
			}
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;<i>" + Messages.getString("HTMLReport.NoVariablesPrefsDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private void outputEnumerationTypes(UCMspec ucmspec, StringBuffer sb) {

		int i = 1;
		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.EnumerationTypes") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if (prefShowUCMDiagrams) {
			for (Iterator iter = ucmspec.getEnumerationTypes().iterator(); iter.hasNext();) {
				EnumerationType enumType = (EnumerationType) iter.next();

				if (enumType.getValues() != null) {            	
					sb.append("&nbsp;&nbsp;&nbsp;" + i++ + ". " + EscapeUtils.escapeHTML(enumType.getName()) + ": " + enumType.getValues().replace( ",", ", ") ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				}
			}
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;<i>" + Messages.getString("HTMLReport.NoEnumTypesPrefsDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private void outputScenarios(UCMspec ucmspec, StringBuffer sb) {

		int i = 1, j = 1;
		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.UCMScenarioGroups") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if ((prefShowScenarioInfo) || (prefShowScenarioExec)) {
			for (Iterator iter = ucmspec.getScenarioGroups().iterator(); iter.hasNext();) {
				ScenarioGroup group = (ScenarioGroup) iter.next();
				sb.append("</div>\n<div>\n<h3>" + i++ + ". " + group.getName() + ":</h3>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				j = 1;
				for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
					// create a list for the scenario group
					ScenarioDef scenario = (ScenarioDef) iterator.next();
					sb.append("&nbsp;&nbsp;&nbsp;" + j++ + ". " + EscapeUtils.escapeHTML(scenario.getName()) ); //$NON-NLS-1$ //$NON-NLS-2$
					if( ReportUtils.notEmpty(scenario.getDescription()) ) {
						sb.append( ": " + EscapeUtils.escapeHTML(scenario.getDescription()) ); //$NON-NLS-1$
					}
					sb.append( "<br></br>\n" ); //$NON-NLS-1$
				}
			}
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;<i>" + Messages.getString("HTMLReport.NoScenarioPreferencesDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	/**
	 * Writes scenario information in the "UCM Scenarios" page of the report
	 * 
	 * @param ucmSpec
	 *            the UCMspec object from which we will fetch all the necessary information
	 * @param sb
	 *            the StringBuffer object into which we will write all the HTML page content
	 */
	private void outputUCM_Scenarios(UCMspec ucmSpec, StringBuffer sb) {
		
		sb.append("<h1>" + Messages.getString("HTMLReport.UCMScenTitle") + "</h1>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if ((prefShowScenarioInfo) || (prefShowScenarioExec)) {
			if (!ucmSpec.getScenarioGroups().isEmpty()) {
				
				if (prefShowScenarioExec) {
					writeScenarioExecSummary(sb, ucmSpec);
				}
				
				if (prefShowScenarioInfo) {
				// loop through each scenario group and write all data related to this group
				for (Iterator iter = ucmSpec.getScenarioGroups().iterator(); iter.hasNext();) {
					ScenarioGroup scenGroup = (ScenarioGroup) iter.next();
	                writeScenarioGroupInfo(sb, scenGroup);
				}
				}
			} else {
				sb.append("&nbsp;&nbsp;&nbsp;" + "<i>" + Messages.getString("HTMLReport.NoScenario") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;" + "<i>" + Messages.getString("HTMLReport.NoScenarioPreferencesDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
	}
	
	/**
     * this method writes data on all the scenario groups, and calls other 
     * methods that write information specific to each scenario
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenGroup
     *            the ScenarioGroup from which we are getting scenario info
     */
	private void writeScenarioGroupInfo(StringBuffer sb, ScenarioGroup scenGroup) {
		
		if (!scenGroup.getScenarios().isEmpty()) {
			sb.append("<div>\n<h2>" + Messages.getString("HTMLReport.Group") + EscapeUtils.escapeHTML(scenGroup.getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenGroup.getId() + ")" + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
            // write all pertinent data related to each scenario belonging to the scnario group scenGroup		
			for (Iterator iter = scenGroup.getScenarios().iterator(); iter.hasNext();) {
				ScenarioDef scenario = (ScenarioDef) iter.next();
                writeScenarioInfo(sb, scenario);
			}
			sb.append("</div>"); //$NON-NLS-1$
		}
	}

	/**
     * this method writes data related to one scenario, and calls other 
     * methods that write information specific to each scenario component
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeScenarioInfo(StringBuffer sb, ScenarioDef scenario) {
		
		sb.append("<div>\n<h3>" + Messages.getString("HTMLReport.Scenario") + "<a id=\"" + scenario.getId() + "\">" + EscapeUtils.escapeHTML(scenario.getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenario.getId() + ")" + "</a></h3>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
		// write all the data related to all scenarios that are included in the scenario scenario
		
		if (!scenario.getIncludedScenarios().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.IncludedScenarios") + ":</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			for (Iterator iter = scenario.getIncludedScenarios().iterator(); iter.hasNext();) {
				sb.append("<br>\n"); //$NON-NLS-1$
				ScenarioDef includedScenario = (ScenarioDef) iter.next();
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + Messages.getString("HTMLReport.Scenario") + EscapeUtils.escapeHTML(includedScenario.getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + includedScenario.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                if (includedScenario.getDescription() != null) {
                	sb.append(": " + EscapeUtils.escapeHTML(includedScenario.getDescription())); //$NON-NLS-1$
                }
                sb.append("\n"); //$NON-NLS-1$
			}
			sb.append("<br><br>\n"); //$NON-NLS-1$
		}
		// write all other pertinent data related to the scenario
		writeStartPoints(sb, scenario);
		writeInitializations(sb, scenario);
		writePreconditions(sb, scenario);
		writeEndPoints(sb, scenario);
		writePostconditions(sb, scenario);
		sb.append("</div>\n"); //$NON-NLS-1$
	}

	/**
     * this method writes data related to a scenario's start points
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeStartPoints(StringBuffer sb, ScenarioDef scenario) {
		
		if (!scenario.getStartPoints().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.ScenarioStartPoints") + ":</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// write data related to all the starting points of the scenario scenario
			for (Iterator iter = scenario.getStartPoints().iterator(); iter.hasNext();) {
				sb.append("<br>\n"); //$NON-NLS-1$
				ScenarioStartPoint scenStartPoint = (ScenarioStartPoint) iter.next();
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(scenStartPoint.getStartPoint().getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenStartPoint.getStartPoint().getId() + ")\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				if (scenStartPoint.getStartPoint().getDescription() != null) {
                	sb.append(": " + EscapeUtils.escapeHTML(scenStartPoint.getStartPoint().getDescription())); //$NON-NLS-1$
                }
			}
			sb.append("<br><br>\n"); //$NON-NLS-1$
		}
	}
	
	/**
     * this method writes data related to the initialization of a scenario's variable(s)
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeInitializations(StringBuffer sb, ScenarioDef scenario) {
			
		if (!scenario.getInitializations().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.Initializations") + ":</b><br><br>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// write all the data related to the initialization of the scenario's variable(s)
			
			// create the table to store all data related to variable initialization
            sb.append("<table style=\"margin-left:20px; text-align: left; width: 80%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n");   //$NON-NLS-1$
            sb.append("<col width=\"75\"><col width=\"50\"><col width=\"150\"><col width=\"50\"><col width=\"75\">\n"); //$NON-NLS-1$
            //header row
            sb.append("<tr><th>" + Messages.getString("HTMLReport.VarName") + "</th><th>" + Messages.getString("HTMLReport.ID") + "</th><th>" + Messages.getString("HTMLReport.Description") + "</th><th>" + Messages.getString("HTMLReport.Type") + "</th><th>" + Messages.getString("HTMLReport.InitialValue") + "</th></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
			
            for (Iterator iter = scenario.getInitializations().iterator(); iter.hasNext();) {
            	sb.append("<tr>\n"); //$NON-NLS-1$
            	Initialization init = (Initialization) iter.next();
            	sb.append("<td>" + EscapeUtils.escapeHTML(init.getVariable().getName()) + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$
            	sb.append("<td>" + init.getVariable().getId() + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$
            	if (init.getVariable().getDescription() == null) {
            		sb.append("<td>" + "" + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                } else {
                	sb.append("<td>" + EscapeUtils.escapeHTML(init.getVariable().getDescription()) + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$
                }
            	sb.append("<td>" + init.getVariable().getType() + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$
            	sb.append("<td>" + EscapeUtils.escapeHTML(init.getValue()) + "</td>\n"); //$NON-NLS-1$ //$NON-NLS-2$
                
				sb.append("</tr>\n"); //$NON-NLS-1$
            }
            sb.append("</table>\n"); //$NON-NLS-1$
			sb.append("<br>\n"); //$NON-NLS-1$
		}
	}
	
	/**
     * this method writes data related to the precondition(s) of a scenario
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writePreconditions(StringBuffer sb, ScenarioDef scenario) {
		
		if (!scenario.getPreconditions().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.Preconditions") + ":</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// write all the data related to the scenario's preconditions
			for (Iterator iter = scenario.getPreconditions().iterator(); iter.hasNext();) {
				sb.append("<br>\n"); //$NON-NLS-1$
                Condition precondition = (Condition) iter.next();
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(precondition.getLabel())); //$NON-NLS-1$
                if (precondition.getDescription() != null) {
                	sb.append(": " + EscapeUtils.escapeHTML(precondition.getDescription())); //$NON-NLS-1$
                }
                sb.append("\n"); //$NON-NLS-1$
                sb.append("<br>\n"); //$NON-NLS-1$
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + Messages.getString("HTMLReport.PreconditionExpression") + ": " + EscapeUtils.escapeHTML(precondition.getExpression()) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            }
			sb.append("<br><br>\n"); //$NON-NLS-1$
		}
	}

	/**
     * this method writes data related to a scenario's end points
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writeEndPoints(StringBuffer sb, ScenarioDef scenario) {
		
		if (!scenario.getEndPoints().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.ScenarioEndPoints") + ":</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// write all the data related to the scenario's end points
			for (Iterator iter = scenario.getEndPoints().iterator(); iter.hasNext();) {
				sb.append("<br>\n"); //$NON-NLS-1$
				ScenarioEndPoint scenEndPoint = (ScenarioEndPoint) iter.next();
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(scenEndPoint.getEndPoint().getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenEndPoint.getEndPoint().getId() + ")\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				if (scenEndPoint.getEndPoint().getDescription() != null) {
					sb.append(": " + EscapeUtils.escapeHTML(scenEndPoint.getEndPoint().getDescription())); //$NON-NLS-1$
				}
			}
			sb.append("<br><br>\n"); //$NON-NLS-1$
		}
	}

	/**
     * this method writes data related to the postcondition(s) of a scenario
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param scenario
     *            the scenario from which we are getting scenario info
     */
	private void writePostconditions(StringBuffer sb, ScenarioDef scenario) {
		
		if (!scenario.getPreconditions().isEmpty()) {
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + Messages.getString("HTMLReport.Postconditions") + ":</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			// write all the pertinent data related to the scenario's postconditions
			for (Iterator iter = scenario.getPostconditions().iterator(); iter.hasNext();) {
				sb.append("<br>\n"); //$NON-NLS-1$
                Condition postcondition = (Condition) iter.next();
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(postcondition.getLabel())); //$NON-NLS-1$
                if (postcondition.getDescription() != null) {
                	sb.append(": " + EscapeUtils.escapeHTML(postcondition.getDescription())); //$NON-NLS-1$
                }
                sb.append("\n"); //$NON-NLS-1$
                sb.append("<br>\n"); //$NON-NLS-1$
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + Messages.getString("HTMLReport.PostconditionExpression") + ": " + EscapeUtils.escapeHTML(postcondition.getExpression()) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
			sb.append("<br><br>\n"); //$NON-NLS-1$
		}
	}

	
	/**
     * writes the execution summary tables
     * 
     * @param sb
     *            the StringBuffer object into which we will write the HTML page content
     * @param ucmSpec
     *            the UCM specification from which we are getting scenario info
     */
	public void writeScenarioExecSummary(StringBuffer sb, UCMspec ucmSpec){
		 
        try {
            if (!ucmSpec.getScenarioGroups().isEmpty()) {

            	sb.append("<div>\n"); //$NON-NLS-1$
            	sb.append("<h2>" + Messages.getString("HTMLReport.ScenarioExecutionTitle") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            	
                for (Iterator iter1 = ucmSpec.getScenarioGroups().iterator(); iter1.hasNext();) {

                	ScenarioGroup scenGroup = (ScenarioGroup) iter1.next();
                    
                    if (!scenGroup.getScenarios().isEmpty()) {

                     sb.append("<h3>" + Messages.getString("HTMLReport.Group") + EscapeUtils.escapeHTML(scenGroup.getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenGroup.getId() + ")" + "</h3>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                     
                     // create the table for the current scenario group (there will be one table per group)
                     sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n");   //$NON-NLS-1$
                     sb.append("<col width=\"150\"><col width=\"60\">\n"); //$NON-NLS-1$
                     //header row
                     sb.append("<tr><th>" + Messages.getString("HTMLReport.ScenarioName") + "</th><th>" + Messages.getString("HTMLReport.Result") + "</th><th>" + Messages.getString("HTMLReport.Message") + "</th></tr>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                            
                     // add Scenarios in first column, one per row
                     for (Iterator iter = scenGroup.getScenarios().iterator(); iter.hasNext();) {
                            
                    	 ScenarioDef scenario = (ScenarioDef) iter.next();
                            	
                          //get warnings for scenario
	                      Vector warnings = ScenarioUtils.traverseWarn(scenario, null);
	    						
	                      String result; //PASSED or FAILED
	                             
	                      String scenName;
	                      if (prefShowScenarioInfo){//add links if scenario info is available
	                    	  scenName = "<a href=\"#" + scenario.getId() + "\">" + EscapeUtils.escapeHTML(scenario.getName())+  " (" + Messages.getString("HTMLReport.ID") + ": " + scenario.getId() + ")"+ "</a>";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	                      }else{
	                    	   scenName = EscapeUtils.escapeHTML(scenario.getName()) + " (" + Messages.getString("HTMLReport.ID") + ": " + scenario.getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	                      }
	                      
                          if (warnings.size() > 0) {
                        	  result = Messages.getString("HTMLReport.Failed"); //$NON-NLS-1$
                        	  boolean firstWarn = true;
                              //add all warnings one at a time
                              for (Iterator iterWarn = warnings.iterator(); iterWarn.hasNext();) {
                            	  TraversalWarning o = (TraversalWarning) iterWarn.next();
                                  if (firstWarn) {
                                	  sb.append("<tr bgcolor=#FCA9AB><td>" + scenName + "</td><td>" + result + "</td><td>" + o.getMsg() + "</td></tr>\n"); //red row for a failed //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
                                  } else { //second, third, etc warning msg for scenario
                                	  sb.append("<tr bgcolor=#FCA9AB><td>" + "" + "</td><td>" + "" + "</td><td>" + o.getMsg() + "</td></tr>\n"); //red row for a failed //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                                  }
                                        
                                  firstWarn = false;
                              }
                          } else {
                        	  result = Messages.getString("HTMLReport.Passed"); //$NON-NLS-1$
	    					  sb.append("<tr bgcolor=#D2F9AC><td>" + scenName + "</td><td>" + result + "</td><td></td></tr>\n"); //green row for a passed //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                          }                     
                       }  
                       sb.append("</table>\n"); //$NON-NLS-1$
                       sb.append("<br>\n"); //$NON-NLS-1$
                    }
                }
                sb.append("</div>\n"); //$NON-NLS-1$
            }
            
        } catch (Exception e) {
            new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();

        }
    }
	
	
	private void outputGRL_Definitions(URNspec urn, StringBuffer sb) {

		GRLspec grlspec = urn.getGrlspec();

		sb.append("<h1>" + Messages.getString("HTMLReport.GRLDefinitions") + "</h1>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		outputStrategies( grlspec, sb );
		outputStrategiesKPI(grlspec, sb);
	}

	private void outputStrategies(GRLspec grlspec, StringBuffer sb) {
			
		HashMap<Integer, EvaluationStrategy> strategies;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.EvaluationStrategies") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if (prefShowEvals) {
			for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

				StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();

				evalGroup.sortStrategies();
				
				if (!evalGroup.getStrategies().isEmpty()) {

					strategies = new HashMap<Integer, EvaluationStrategy>();

					// create a hashmap containing strategies (one per column), key is column number starting with 1
					int columnNo = 1;
					for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
						EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
						strategies.put(columnNo, strategy);
						columnNo++;
					}

					strategies = BatchEvaluationUtil.sortStrategies(strategies, 0);

					outputStrategiesLegend( strategies, evalGroup, sb );
					this.calculateAllEvaluations( strategies.values(), grlspec ); // build evaluations table

					/***************************************************************************************************************************************
					 * Create the header row
				 	*/
					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$

					sb.append("<tr><td><b>" + Messages.getString("HTMLReport.ElementName") + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					for( Integer index : strategies.keySet() ) {
						sb.append("<td><b>" + index + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$
					}	
					
					if (prefShowTrend){
						sb.append("<td><b>" + Messages.getString("HTMLReport.Trends") + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}

					sb.append("</tr>\n"); //$NON-NLS-1$

					// add Actors in first column, one per row
					for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {

						Actor actor = (Actor) iter.next();
						sb.append("<tr><td>" + actor.getName() + " (A)" + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

						for( Integer index : strategies.keySet() ) {
							EvaluationStrategy currentStrategy = strategies.get(index);
							int evalValue = evalTable.get(currentStrategy).get(actor);
							sb.append("<td bgcolor=" + getBackgroundColor(evalValue, grlspec.getUrnspec()) + "><b>" + evalValue + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}	

						if(prefShowTrend){
							int trend = BatchEvaluationUtil.calculateTrend(strategies, actor, evalTable, prefTrend);
							sb.append(trendContent(trend));
						}
						
						sb.append("</tr>\n");   				 //$NON-NLS-1$
					}     

					// add Intentional Elements in first column, one per row
					for (Iterator iter11 = grlspec.getIntElements().iterator(); iter11.hasNext();) {

						IntentionalElement intElement = (IntentionalElement) iter11.next();
						sb.append("<tr><td>" + intElement.getName() + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$

						for( Integer index : strategies.keySet() ) {
							EvaluationStrategy currentStrategy = strategies.get(index);
							int evalValue = evalTable.get(currentStrategy).get(intElement);
							sb.append("<td bgcolor=" + getBackgroundColor(evalValue, grlspec.getUrnspec()) + "><b>" + evalValue + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}	

						if(prefShowTrend){
							int trend = BatchEvaluationUtil.calculateTrend(strategies, intElement, evalTable, prefTrend);
							sb.append(trendContent(trend));
						}
						
						sb.append("</tr>\n"); //$NON-NLS-1$
					}

					sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
				}
			}
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;<i>" + Messages.getString("HTMLReport.NoEvalsPrefsDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

	}
	
	private void outputStrategiesKPI(GRLspec grlspec, StringBuffer sb) {
		
		HashMap<Integer, EvaluationStrategy> strategies;

		sb.append("</div>\n<div>\n<h2>" + Messages.getString("HTMLReport.EvaluationStrategiesKPI") + "</h2>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if (prefShowEvals) {
			for (Iterator iter1 = grlspec.getGroups().iterator(); iter1.hasNext();) {

				StrategiesGroup evalGroup = (StrategiesGroup) iter1.next();
				evalGroup.sortStrategies();
				
				if (!evalGroup.getStrategies().isEmpty()) {

					strategies = new HashMap<Integer, EvaluationStrategy>();

					// create a hashmap containing strategies (one per column), key is column number starting with 1
					int columnNo = 1;
					for (Iterator iter2 = evalGroup.getStrategies().iterator(); iter2.hasNext();) {
						EvaluationStrategy strategy = (EvaluationStrategy) iter2.next();
						strategies.put(columnNo, strategy);
						columnNo++;
					}
					
					strategies = BatchEvaluationUtil.sortStrategies(strategies, 0);

					outputStrategiesLegend( strategies, evalGroup, sb );
					this.calculateAllEvaluations( strategies.values(), grlspec ); // build evaluations table

					/***************************************************************************************************************************************
					 * Create the header row
				 	*/
					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$

					sb.append("<tr><td><b>" + Messages.getString("HTMLReport.Indicator") + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					for( Integer index : strategies.keySet() ) {
						sb.append("<td><b>" + index + "</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$
					}	

					sb.append("</tr>\n"); //$NON-NLS-1$

					
					// add Intentional Elements in first column, one per row
					for (Iterator iter11 = grlspec.getIntElements().iterator(); iter11.hasNext();) {

						IntentionalElement intElement = (IntentionalElement) iter11.next();
						
						if( intElement.getType().getName().compareTo("Indicator") == 0){
							
							boolean kpiConverted = false;
							boolean firstIteration = true;
							
							String intElemConv = indicatorKpiConversion.get((Indicator)intElement);

                    	
                    	
                    	if( intElemConv.compareTo("none") != 0)
                    		kpiConverted = true;
						
						for( Integer index : strategies.keySet() ) {
							EvaluationStrategy currentStrategy = strategies.get(index);
							int evalValue = evalTable.get(currentStrategy).get(intElement);
					
							HashMap<Indicator, HashMap<String, String>> currentIndicatorEval = indicatorTable.get(currentStrategy);
							HashMap<String, String> kpiEvalValue = ((HashMap<String, String>)currentIndicatorEval.get(intElement));
							
							
							String unit = kpiEvalValue.get("Unit");

							
							if( firstIteration){
								if(kpiConverted){
									sb.append("<tr><td>" + "<b>" + intElement.getName() + "</b>" + "&nbsp;" + "(KPI Conversion : " + indicatorKpiConversion.get((Indicator)intElement) + ")" +
											"<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
											Messages.getString("KPIViewObjectFigure.QualitativeEvaluation") + "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
			                        		Messages.getString("KPIViewObjectFigure.Evaluation") + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$	
								}else {
									sb.append("<tr><td>" + "<b>" + intElement.getName() + "</b>" +  "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
		                        		Messages.getString("KPIViewObjectFigure.Threshold") + "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		                        		Messages.getString("KPIViewObjectFigure.Worst") + "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		                        		Messages.getString("KPIViewObjectFigure.Target") + "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"  +
		                        		Messages.getString("KPIViewObjectFigure.QuantitativeEvaluation") + "<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"  +
		    	                        Messages.getString("KPIViewObjectFigure.Evaluation") + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
								}
							}
							firstIteration = false;
							
							if( kpiConverted){
								sb.append("<td bgcolor=" + getBackgroundColor(evalValue, grlspec.getUrnspec()) + "><b>" + ""+ "<br>" +
						    			kpiEvalValue.get("QualitativeEval") + " " +  "<br>" +
						    			evalValue +"</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							}else{
								sb.append("<td bgcolor=" + getBackgroundColor(evalValue, grlspec.getUrnspec()) + "><b>" + ""+ "<br>" + kpiEvalValue.get("Threshold") + " " + unit + "<br>" + 
						    			kpiEvalValue.get("Worst") + " " + unit  + "<br>" +
						    			kpiEvalValue.get("Target") + " " + unit + "<br>" +
						    			kpiEvalValue.get("EvaluationValue") + " " + unit +  "<br>" +
						    			evalValue + " " +"</b></td>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							}
				
						}

						
						sb.append("</tr>\n"); //$NON-NLS-1$
						
						}
					}

					sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
				}
			}
			writeKpiConversionTables(grlspec, sb);
			
		} else {
			sb.append("&nbsp;&nbsp;&nbsp;<i>" + Messages.getString("HTMLReport.NoEvalsPrefsDisabled") + "</i>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

	}
	
    /*
     * Writes a KPI Conversion association reference table 
     * for each conversion.
     */
    private void writeKpiConversionTables(GRLspec grlspec, StringBuffer sb) {
		
		for( Object obj : grlspec.getKPIConversion()){
    		
			QualitativeMappingsImpl currentKpiConv = (QualitativeMappingsImpl) obj;
			
			/***************************************************************************************************************************************
			 * Create the header row
		 	*/
			sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n"); //$NON-NLS-1$
			sb.append("</div>\n<div>\n<h2>" + " \n\n " +  Messages.getString("ReportStrategies.KpiConversionQuote") + currentKpiConv.getName() + "\" " + "</h2>\n");
			
            // First line - first cell
            sb.append("<tr><td>" + "<b>" + Messages.getString("ReportStrategies.RealWorldLabel") + "</b>" + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$	
            
            // First line - RealWorldLabels of mappings
            
            for ( Object obj2 : currentKpiConv.getMapping()){
            	
            	QualitativeMappingImpl currentMapping = (QualitativeMappingImpl) obj2;
            	sb.append("<td>"  + currentMapping.getRealWorldLabel()  + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$	
            }  
                            
            // Second line - first cell
            sb.append("<tr><td>" + "<b>" + Messages.getString("ReportStrategies.EvaluationValue") + "</b>" + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
            
            // Second line - rest of the cells : evaluation values
            
            for ( Object obj2 : currentKpiConv.getMapping()){
            	
            	QualitativeMappingImpl currentMapping = (QualitativeMappingImpl) obj2;
            	sb.append("<td>"  + String.valueOf(currentMapping.getEvaluation())  + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$	
            }  
	
						sb.append("</tr>\n"); //$NON-NLS-1$

					sb.append("</tbody></table></br>\n"); //$NON-NLS-1$
				}
			
	}

	private String trendContent(int trend) {
    	String content;
    			
    	switch(trend){
    	case -1: content = "<td align=\"center\" bgcolor=#FCA9AB><b><img src=\"down.png\"></b></td>";//$NON-NLS-1$
    			break;
    	case 0: content = "<td align=\"center\" bgcolor=#FFFF97><b><img src=\"straight.png\"></b></td>";//$NON-NLS-1$
				break;
    	case 1: content = "<td align=\"center\" bgcolor=#D2F9AC><b><img src=\"up.png\"></b></td>";//$NON-NLS-1$
				break;
    	case -3: content = "<td align=\"center\" ><b><img src=\"vary.png\"></b></td>";//$NON-NLS-1$
					break;
		default: content = "<td align=\"center\" ><b>?</b></td>";//$NON-NLS-1$
				break;
    	}
    	
    	return content;
    }

	private void outputStrategiesLegend( HashMap<Integer, EvaluationStrategy> strategies, StrategiesGroup evalGroup, StringBuffer sb ) {

		sb.append("</div>\n<div>\n<h3>" + Messages.getString("HTMLReport.StrategyLegendForGroup") + " \"" +  evalGroup.getName() + "\"</h3>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		for( Integer index : strategies.keySet() ) {
			if (strategies.get(index).getDescription() == null || strategies.get(index).getDescription().equals("")){ //$NON-NLS-1$
				sb.append("&nbsp;&nbsp;&nbsp;<b>" + index + ".</b> " + EscapeUtils.escapeHTML(strategies.get(index).getName()) + "<br></br>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				
			}else{
			sb.append("&nbsp;&nbsp;&nbsp;<b>" + index + ".</b> " + EscapeUtils.escapeHTML(strategies.get(index).getName()) + " - " + EscapeUtils.escapeHTML(strategies.get(index).getDescription()) + "<br></br>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		}	
		
		if (prefShowTrend){
			sb.append("&nbsp;&nbsp;&nbsp;<b>"+ Messages.getString("HTMLReport.TrendNote") +":" + Messages.getString("HTMLReport.TrendNote2") + " " + prefTrend + " " + Messages.getString("HTMLReport.TrendNote3") +"</b>\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			
		}
	}

	/*
	 * Upgraded to new version of the method 07/07/2014 (can be deleted if no problem encountered after upgrade)
	 * 
	 * private void calculateAllEvaluations( final Collection<EvaluationStrategy> strategies, final GRLspec grlspec ) {

		evalTable.clear();

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
			    evalTable = BatchEvaluationUtil.calculateAllEvaluations(strategies, grlspec);
			    indicatorTable = BatchEvaluationUtil.calculateAllEvaluationsKPI(strategies, grlspec);
			}
		});
	}*/
	
	private void calculateAllEvaluations( final Collection<EvaluationStrategy> strategies, final GRLspec grlspec ) {

    	evalTable.clear();
    	indicatorTable.clear();

    	Display.getDefault().syncExec(new Runnable() {
    		public void run() {
 			
    			for( EvaluationStrategy strategy : strategies ) {

    				strategyEvaluations = new HashMap<GRLLinkableElement, Integer>();
    				EvaluationStrategyManager.getInstance(false).setStrategy(strategy);
    				
    				indicatorEvaluations = new HashMap<Indicator, HashMap<String, String>>();
    				indicatorKpiConversion = new HashMap<Indicator, String>();
    				
    				boolean tempIndicators = false;

    				for (Iterator iter = grlspec.getIntElements().iterator(); iter.hasNext();) {
    					
    					IntentionalElement element = (IntentionalElement) iter.next();
    					evalValue = EvaluationStrategyManager.getInstance(false).getEvaluation(element);
    					strategyEvaluations.put( element, evalValue );
    					
    					
    					if(element.getType().getName().compareTo("Indicator") == 0){
    						
    						HashMap<String, String> currentEvalKPI = new HashMap<String, String>();
    						KPIEvalValueSet currentKpiEvalSet = EvaluationStrategyManager.getInstance(false).getActiveKPIEvalValueSet(element);
    						
    						String currentIndicatorKpiConv = null;
    						
    						if( currentKpiEvalSet.getKpiConv() != null){
    						currentIndicatorKpiConv = (String) currentKpiEvalSet.getKpiConv().getName();
    						}else{
    						currentIndicatorKpiConv = "none";
    						}
    						
    						currentEvalKPI.put("Threshold",String.valueOf(currentKpiEvalSet.getThresholdValue()));
    						currentEvalKPI.put("Worst",String.valueOf(currentKpiEvalSet.getWorstValue()));
    						currentEvalKPI.put("Target",String.valueOf(currentKpiEvalSet.getTargetValue()));
    						currentEvalKPI.put("Unit",currentKpiEvalSet.getUnit());
    						currentEvalKPI.put("EvaluationValue", String.valueOf(round(currentKpiEvalSet.getEvaluationValue(), 2)));
    						currentEvalKPI.put("QualitativeEval", currentKpiEvalSet.getQualitativeEvaluationValue());
    						
    						indicatorKpiConversion.put((Indicator)element, currentIndicatorKpiConv);
    						
    						indicatorEvaluations.put((Indicator)element, currentEvalKPI);
    						    					
    						tempIndicators = true;
    					}
    					
    				}

    				for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {
    					Actor actor = (Actor) iter.next();
    					evalValue = EvaluationStrategyManager.getInstance(false).getActorEvaluation(actor);
    					strategyEvaluations.put( actor, evalValue );
    				}
    				
    				if (tempIndicators == true){
    					indicatorTable.put(strategy, indicatorEvaluations);
    					    				    				}
    				
    				evalTable.put(strategy, strategyEvaluations); // add map of this strategy's evaluations to the table
    			}
    		}
    	});
    }

	private String getBackgroundColor( int evalValue, URNspec urnSpec ) {

		// if 0,100, convert back to -100,100 to have the right color.
		String hexColor = null;
		int colorValue = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable( urnSpec,  evalValue );
		
		if (colorValue == 0) {
			hexColor = "#FFFF97"; // Color(255, 255, 151) //$NON-NLS-1$
		} else if (colorValue <= -100) {
			hexColor = "#FCA9AB"; // Color(252, 169, 171) //$NON-NLS-1$
		} else if (colorValue > -100 && colorValue < 0) {
			hexColor = "#FDE9EA"; // Color(253, 233, 234) //$NON-NLS-1$
		} else if (colorValue == 100) {
			hexColor = "#D2F9AC"; // Color(210, 249, 172) //$NON-NLS-1$
		} else if (colorValue > 0 && colorValue < 100) {
			hexColor = "F0FDE3"; // Color(240, 253, 227) //$NON-NLS-1$
		}

		return hexColor;
	}
	
	/*
     * Rounds a Double to a number of decimal places
     * 
     * param value
     * 	value to round off
     * param places
     * 	number of decimal places needed
     * return 
     * 	the rounded number
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

