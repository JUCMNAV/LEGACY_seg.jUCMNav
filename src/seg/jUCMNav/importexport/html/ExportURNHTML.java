package seg.jUCMNav.importexport.html;

import grl.GRLGraph;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.importexport.ExportImageGIF;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
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
import urn.URNspec;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * Export an HTML suite from a URNspec.
 *  
 * @author pchen
 * @author amiga
 *
 */
public class ExportURNHTML implements IURNExport {
	public static final String PAGES_LOCATION = "pages" + File.separator; //$NON-NLS-1$
	public static final String IMAGES_LOCATION = PAGES_LOCATION + "img" + File.separator;  //$NON-NLS-1$

	public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
		// not used

	}

	/**
	 * Export an HTML suite from this URNspec.  
	 * 
	 */
	public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
		FileOutputStream imgFos = null;

		for (int i=0;i<urn.getUrndef().getSpecDiagrams().size();i++) {
			IURNDiagram diagram = (IURNDiagram) urn.getUrndef().getSpecDiagrams().get(i);
			// get the high level IFigure to be saved.
			IFigure pane = (IFigure) mapDiagrams.get(diagram);
			boolean isLast=i==urn.getUrndef().getSpecDiagrams().size()-1;
			String diagramName = ExportWizard.getDiagramName(diagram);
			String imgPath = createImgPath(filename, diagramName);

			// export the image file
			(new ExportImageGIF()).export(pane, imgPath); 

			// export the index pages 
			String htmlPath = getPath(filename);

			if (isLast) {
				createIndexPages(htmlPath);
			}

			// prepare the html menu item
			HTMLMenuItem htmlMenuItem = new HTMLMenuItem();
			htmlMenuItem.reset();

			htmlMenuItem.setDiagramName(diagramName);
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

			// export the html or this diagram
			export(diagram, htmlPath);

			// create the XML menu content
			HTMLMenuParser htmlMenuParser = HTMLMenuParser.getParser(htmlPath);
			htmlMenuParser.addMenu(htmlMenuItem);

			// write the content of menu to XML file
			if (isLast) {
				htmlMenuParser.writeToFile();
				htmlMenuParser.resetDocument();
			}
		}
	}


	/**
	 * Create index html pages used in exporting UCM/GRL maps to html pages.
	 * 
	 * @param htmlPath the export directory
	 */
	private void createIndexPages(String htmlPath) {
		try {
			// Generate the index page
			String srcFile = "htmltemplates/index.html"; //$NON-NLS-1$
			String desFile = htmlPath + "index.html"; //$NON-NLS-1$
			copy(srcFile, desFile);

			// Generate the main page
			srcFile = "htmltemplates/main.html"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "main.html"; //$NON-NLS-1$
			copy(srcFile, desFile);

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

			// Generate the LogoFinal.gif file
			srcFile = "htmltemplates/LogoFinal.gif"; //$NON-NLS-1$
			desFile = htmlPath + PAGES_LOCATION + "LogoFinal.gif"; //$NON-NLS-1$
			copy(srcFile, desFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Copies src file to dst file. If the dst file does not exist, it is created.
	 * 
	 * @param srcPath source path
	 * @param dstPath destination path
	 * @throws IOException 
	 */
	protected static void copy(String srcPath, String dstPath) throws IOException {
		Class location = ExportURNHTML.class;

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
	 * @param htmlPath the root target directory
	 * @return the path of exported html file
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
			sb.append("<title>URN Model</title>\n"); //$NON-NLS-1$
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
			sb.append("<body>\n"); //$NON-NLS-1$

			boolean hasStub = false;
			Iterator nodeIter = null;

			EList nodes = diagram.getNodes();
			if (!nodes.isEmpty()) {
				nodeIter = nodes.iterator();

				int top = 20;
				int left = 30;

				sb.append("<div align=\"left\" style=\"top:" + top + "px; left:" + left + "px;\"><font size=\"+2\">" + diagramName.substring(diagramName.lastIndexOf("-") + 1) +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						"</font><font size=\"+1\"><i>" + MapType( diagram ) + "</i></font><br/><img src=\"img/" + diagramName + ".gif\" border=\"0\" style=\"top:" + top + "px; left:0px;\" usemap=\"#tooltips\" />\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("<script language=\"JavaScript\">\n"); //$NON-NLS-1$
				sb.append("<!--\n"); //$NON-NLS-1$

				int numOfStub = 0;
				while (nodeIter.hasNext()) {
					Object obj = nodeIter.next();

					if (obj instanceof StubImpl) {
						numOfStub++;
						hasStub = true;
						StubImpl stub = (StubImpl) obj;

						EList bindings = stub.getBindings();
						Iterator bindIter = null;
						if (!bindings.isEmpty()) {

							sb.append("var stubHierarchy" + numOfStub + " = [\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("['', null,\n"); //$NON-NLS-1$

							int height = 15;
							int width = 15;

							bindIter = bindings.iterator();
							int j = 0;
							while (bindIter.hasNext()) {
								obj = bindIter.next();
								if (obj instanceof PluginBindingImpl) {
									PluginBindingImpl pluginBinding = (PluginBindingImpl) obj;
									UCMmap childMap = pluginBinding.getPlugin();

									// get plugin diagram file name
									String pluginDiagramName = ExportWizard.getDiagramName(childMap);

									sb.append("[map('" + pluginDiagramName.substring(pluginDiagramName.lastIndexOf("-") + 1) +  //$NON-NLS-1$ //$NON-NLS-2$
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
							sb.append("'firstX' :  " + (stub.getX() + menuItem.getBaseX() - width/2) + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'firstY' :  " + (stub.getY() + menuItem.getBaseY() + top - height/2) + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'nextX' :  " + "0,\n"); //$NON-NLS-1$ //$NON-NLS-2$
							sb.append("'hideAfter' :  " +  "200,\n"); //$NON-NLS-1$ //$NON-NLS-2$
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

				sb.append("//-->\n"); //$NON-NLS-1$
				sb.append("</script>\n"); //$NON-NLS-1$
			} else {
				// System.out.println("No nodes existing.");
			}

			OutputDescription( diagram, sb );
			OutputResponsibilityReferences( diagram, sb );
			OutputStartPointPreconditions( diagram, sb );
			OutputEndPointPostconditions( diagram, sb );
			OutputOrForkGuards( diagram, sb );
			OutputStubBindings( diagram, sb );
			
			// Add tool tips with an image map     
			if (diagram.getNodes().size() > 0) {
				int height = 10;
				int width = 10;

				sb.append("<map name=\"tooltips\">\n");
				for (Iterator iter1 = diagram.getNodes().iterator(); iter1.hasNext();) {
					IURNNode specNode = (IURNNode) iter1.next();

					if (specNode instanceof RespRef) {
						RespRef respRef = (RespRef) specNode;
						sb.append("<area shape=\"rect\" coords=\""
								+ (respRef.getX() - width/2) + ", "
								+ (respRef.getY() - height/2) + ", "
								+ (respRef.getX() + width/2) + ", "
								+ (respRef.getY() + height/2) + "\" "
								+ "title=\""+ respRef.getRespDef().getName()
								+ ": " + notNull(respRef.getRespDef().getDescription())
								+ "\">\n");
					}
					else if (specNode instanceof StartPoint) {
						StartPoint startPoint = (StartPoint) specNode;
						sb.append("<area shape=\"circle\" coords=\""
								+ startPoint.getX() + ", "
								+ startPoint.getY() + ", 10\" "
								+ "title=\"" + startPoint.getName()
								+ ": " + notNull(startPoint.getDescription())
								+ "\">\n");
					}
					else if (specNode instanceof EndPoint) {
						EndPoint endPoint = (EndPoint) specNode;
						sb.append("<area shape=\"rect\" coords=\""
								+ (endPoint.getX() - width/2) + ", "
								+ (endPoint.getY() - height/2) + ", "
								+ (endPoint.getX() + width/2) + ", "
								+ (endPoint.getY() + height/2) + "\" "
								+ "title=\"" + endPoint.getName()
								+ ": " + notNull(endPoint.getDescription())
								+ "\">\n");
					}

				}

				sb.append("<area shape=\"default\" nohref>\n</map></br>\n");
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

	private void OutputResponsibilityReferences( IURNDiagram diagram, StringBuffer sb )
	{
		// Describe table for responsibility references     
		if ( !hasNodeType(diagram.getNodes(), RespRefImpl.class))
			return;
			
		sb.append("</div>\n<div>\n<h2>Responsibilities</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
		sb.append("<tr><td><b>Name</b></td><td><b>Description</b></td><td><b>Pseudo-code</tr>\n");
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof RespRef) {
				RespRef respRef = (RespRef) specNode;
				sb.append("<tr><td>" + respRef.getRespDef().getName()
						+ "</td><td><i>"
						+ notNull(respRef.getRespDef().getDescription())
						+ "</i>&nbsp;</td><td>"
						+ notNull(respRef.getRespDef().getExpression()).replace("\r\n", "<br/>")
						+ "&nbsp;</td></tr>\n");
			}
		}
		sb.append("</tbody></table></br>\n");
	}
	
	private void OutputStartPointPreconditions( IURNDiagram diagram, StringBuffer sb )
	{
		boolean hasPreconditions = false;
		StartPoint sp;
		
		// determine if any start points have preconditions
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof StartPoint ) {
				sp = (StartPoint) specNode;
				if ( ReportUtils.notEmpty( sp.getPrecondition().getLabel() )) {
					hasPreconditions = true;
					break;
				}
			}
		}
		
		if ( !hasPreconditions )
			return;
		
		sb.append("</div>\n<div>\n<h2>Start Points</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
		sb.append("<tr><td><b>Name</b></td><td><b>Precondition</b></td></tr>\n");
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof StartPoint ) {
				sp = (StartPoint) specNode;
				if ( ReportUtils.notEmpty( sp.getPrecondition().getLabel() )) {
					sb.append("<tr><td>" + sp.getName()
							+ "</td><td><i>"
							+ sp.getPrecondition().getLabel()
							+ "</i>&nbsp;</td></tr>\n" );
				}
			}
		}		
		
		sb.append("</tbody></table></br>\n");		
	}
	
	private void OutputEndPointPostconditions( IURNDiagram diagram, StringBuffer sb )
	{
		boolean hasPostconditions = false;
		EndPoint ep;
		
		// determine if any end points have postconditions
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof EndPoint ) {
				ep = (EndPoint) specNode;
				if ( ReportUtils.notEmpty( ep.getPostcondition().getLabel() )) {
					hasPostconditions = true;
					break;
				}
			}
		}
		
		if ( !hasPostconditions )
			return;
		
		sb.append("</div>\n<div>\n<h2>End Points</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
		sb.append("<tr><td><b>Name</b></td><td><b>Postcondition</b></td></tr>\n");
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof EndPoint ) {
				ep = (EndPoint) specNode;
				if ( ReportUtils.notEmpty( ep.getPostcondition().getLabel() )) {
					sb.append("<tr><td>" + ep.getName()
							+ "</td><td><i>"
							+ ep.getPostcondition().getLabel()
							+ "</i>&nbsp;</td></tr>\n" );
				}
			}
		}		
		
		sb.append("</tbody></table></br>\n");		
	}
	
	private void OutputOrForkGuards( IURNDiagram diagram, StringBuffer sb )
	{
 		boolean hasConditions = false;
		boolean firstCondition = true;
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof OrFork ) {
				firstCondition = true;
				for (Iterator iter1 = specNode.getSucc().iterator(); iter1.hasNext();) {   
					Condition orCondition = ((NodeConnection) iter1.next()).getCondition();
					
					if (orCondition != null) {
						if(ReportUtils.notEmpty( orCondition.getLabel() ) ) {
							if ( !hasConditions ) {
								hasConditions = true;
								sb.append("</div>\n<div>\n<h2>Or Fork Description</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
							}
							if ( firstCondition ) {
								sb.append("<tr><td>" + orCondition.getLabel() + "<br/>" );
								firstCondition = false;
							} else
								sb.append( orCondition.getLabel() + "<br/>" );
						}
					}
				}
			}
		}
		
        if ( hasConditions )
    		sb.append("</tbody></table></br>\n");
	}

	private void OutputStubBindings( IURNDiagram diagram, StringBuffer sb )
	{
		boolean hasBindings = false;
		String stub_type;
		StringBuffer inputBuffer, outputBuffer;
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			IURNNode specNode = (IURNNode) iter.next();
			if (specNode instanceof Stub ) {
				Stub stub = (Stub) specNode;
				
				if ( stub.getBindings().isEmpty() )
					continue;
				
				if ( !hasBindings ) { // output heading for stubs only one time
					sb.append("</div>\n<div>\n<h2>Stubs</h2>\n");
					hasBindings = true;
				}

				if ( stub.isDynamic() )
					stub_type = "Dynamic Stub - ";
				else
					stub_type = "Static Stub - ";
				
				sb.append( "<h3>" + stub_type + stub.getName() + "</h3><hr/>" );
				

				for (Iterator bindings = stub.getBindings().iterator(); bindings.hasNext();) {

					PluginBinding binding = (PluginBinding) bindings.next();

					sb.append("<h4><center>" +  "Plugin Map - " + binding.getPlugin().getName() + "</center></h4>\n" );
					sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n" );

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

						inputBuffer.append( "IN " + stubEntryIndex + " <-> " + inBinding.getStartPoint().getName()+ "<br/>" );
					
					}
					
					for (Iterator outs = binding.getOut().iterator(); outs.hasNext();) {
						OutBinding outBinding = (OutBinding) outs.next();

						int stubExitIndex = 0;
						stubExitIndex = stub.getSucc().indexOf(outBinding.getStubExit()) + 1;
						
						outputBuffer.append( "OUT " + stubExitIndex + " <-> " + outBinding.getEndPoint().getName()+ "<br/>" );

					}
					
					sb.append( "<tr><td>Input Bindings</td><td><i>" + inputBuffer + "</td><td>Output Bindings</td><td>" + outputBuffer + "</td></tr>" );
					sb.append( "</tbody></table></br>\n" );	// end of input / output bindings table		

					sb.append( "<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n" );
					
					sb.append( "<tr><td><table style=\"text-align: left; width: 15%;\" border=\"1\" cellpadding=\"1\" cellspacing=\"2\">\n<tbody>\n" );
					sb.append( "<tr><center><i><b>Precondition</b></i></center></tr>\n" );
					sb.append( "</tbody></table>" );
					
					sb.append( "<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n" );
					
					if ( ReportUtils.notEmpty( binding.getPrecondition().getLabel() ) ) {
						sb.append( "<tr><td>Label: </td><td><i>" + binding.getPrecondition().getLabel() + "</i></td></tr>" );
					}
					
					if ( ReportUtils.notEmpty( binding.getPrecondition().getExpression() ) ) {
						sb.append( "<tr><td>Expression: </td><td><i>" + binding.getPrecondition().getExpression() + "</i></td></tr>" );
					}
					
					if ( ReportUtils.notEmpty( binding.getPrecondition().getDescription() ) ) {
						sb.append( "<tr><td>Description: </td><td><i>" + binding.getPrecondition().getDescription() + "</i></td></tr>" );
					}
					
					sb.append( "<tr><td>Transaction: </td><td><i>" + binding.isTransaction() + "</i></td></tr>" );
					sb.append( "<tr><td>Probability: </td><td><i>" + binding.getProbability() + "</i></td></tr>" );
					sb.append("</tbody></table></td>\n</tbody></table></br>\n");					
				}
			}
		}
			
		inputBuffer = null;
		outputBuffer = null;
	}


	private void OutputDescription( IURNDiagram diagram, StringBuffer sb )
	{
		if ( diagram instanceof UCMmap ) {
			if ( ReportUtils.notEmpty( ((UCMmap) diagram).getDescription() ) )
			{
				sb.append("</div>\n<div>\n<h2>Map Description</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
				sb.append("<tr><td>" + ((UCMmap) diagram).getDescription() + "<br/>" );
				sb.append("</tbody></table></br>\n");
			}
		}
	}

	/**
	 * Determines if the list of URN nodes for a diagram contains a node of type 
	 * Note: often, these are the implementation types (e.g., RespRefImpl instead of RespRef).
	 * @param urnNodes list of IURNNode (non-casted)
	 * @param nodeType type of URN node we look for
	 * @return true if the node list contains a node of type nodeType
	 */
	private boolean hasNodeType (EList urnNodes, Class nodeType)
	{
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
	 * @param s the possibly null String
	 * @return a non-null String
	 */
	private String notNull (String s)
	{
		if (s == null)
			return "";
		else
			return s;
	}
	
	private String MapType( IURNDiagram diagram ){
		if ( ((UCMmap) diagram).getParentStub().size() == 0 )
			return " - Root Map";
		else
			return " - Plugin Map";
	}

}


