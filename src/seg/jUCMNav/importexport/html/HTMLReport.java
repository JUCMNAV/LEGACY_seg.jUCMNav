package seg.jUCMNav.importexport.html;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

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

import seg.jUCMNav.importexport.ExportImageGIF;
import seg.jUCMNav.importexport.reports.URNReport;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.utils.EscapeUtils;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
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

    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        // not used

    }

    /**
     * Export an HTML suite from this URNspec.
     * 
     */
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
        FileOutputStream imgFos = null;
        IFigure pane;

        for (int i = 0; i < urn.getUrndef().getSpecDiagrams().size(); i++) {
            IURNDiagram diagram = (IURNDiagram) urn.getUrndef().getSpecDiagrams().get(i);

            boolean isLast = (i == urn.getUrndef().getSpecDiagrams().size() - 1);
            // get the high level IFigure to be saved.
            pane = (IFigure) mapDiagrams.get(diagram);
            String diagramName = ExportWizard.getDiagramName(diagram);
            String imgPath = createImgPath(filename, diagramName);

            // export the image file
            (new ExportImageGIF()).export(pane, imgPath);

            // export the index pages
            String htmlPath = getPath(filename);

            if (isLast) {
                createIndexPages(htmlPath);
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
                // exportMSCScenarios( urn, mapDiagrams, filename );
                htmlMenuParser.writeToFile();
                htmlMenuParser.resetDocument();
            }
        }
    }

    // private void exportMSCScenarios( URNspec urn, HashMap mapDiagrams, String filename ) throws InvocationTargetException
    // {
    // ExportMSC mscExporter = new ExportMSC();

    // if ( !mscExporter.scenarioDefExists(urn) )
    // return;

    //	filename = filename.substring( 0, filename.length()-"html".length() ) + "jucmscenarios"; //$NON-NLS-1$ //$NON-NLS-2$

    // mscExporter.export( urn, mapDiagrams, filename );

    // }

    /**
     * Create index HTML pages used in exporting UCM/GRL maps to html pages.
     * 
     * @param htmlPath
     *            the export directory
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

                sb
                        .append("<div align=\"left\" style=\"top:" + top + "px; left:" + left + "px;\"><font size=\"+2\">" + EscapeUtils.escapeHTML(diagramName.substring(diagramName.lastIndexOf("-") + 1)) + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                                "</font><font size=\"+1\"><i>"
                                + MapType(diagram)
                                + "</i></font></br><img src=\"img/" + diagramName + ".gif\" border=\"0\" style=\"top:" + top + "px; left:0px;\" usemap=\"#tooltips\" />\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

                sb.append("<map name=\"tooltips\">\n");
                for (Iterator iter1 = diagram.getNodes().iterator(); iter1.hasNext();) {
                    IURNNode specNode = (IURNNode) iter1.next();

                    if (specNode instanceof RespRef) {
                        RespRef respRef = (RespRef) specNode;
                        Responsibility responsibility = respRef.getRespDef();
                        sb.append("<area shape=\"rect\" coords=\"" + (respRef.getX() - width / 2) + ", " + (respRef.getY() - height / 2) + ", "
                                + (respRef.getX() + width / 2) + ", " + (respRef.getY() + height / 2) + "\" " + "title=\"" + EscapeUtils.escapeHTML(responsibility.getName()) + ": "
                                + EscapeUtils.escapeHTML(notNull(responsibility.getDescription())) + "\">\n");
                    } else if (specNode instanceof StartPoint) {
                        StartPoint startPoint = (StartPoint) specNode;
                        sb.append("<area shape=\"circle\" coords=\"" + startPoint.getX() + ", " + startPoint.getY() + ", 10\" " + "title=\""
                                + EscapeUtils.escapeHTML(startPoint.getName()) + ": " + EscapeUtils.escapeHTML(notNull(startPoint.getDescription())) + "\">\n");
                    } else if (specNode instanceof EndPoint) {
                        EndPoint endPoint = (EndPoint) specNode;
                        sb.append("<area shape=\"rect\" coords=\"" + (endPoint.getX() - width / 2) + ", " + (endPoint.getY() - height / 2) + ", "
                                + (endPoint.getX() + width / 2) + ", " + (endPoint.getY() + height / 2) + "\" " + "title=\"" + EscapeUtils.escapeHTML(endPoint.getName()) + ": "
                                + EscapeUtils.escapeHTML(notNull(endPoint.getDescription())) + "\">\n");
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

    private void OutputResponsibilityReferences(IURNDiagram diagram, StringBuffer sb) {
        // Describe table for responsibility references

        if (!ReportGeneratorPreferences.getUCMSHOWRESPONSIBILITY())
            return;

        if (!hasNodeType(diagram.getNodes(), RespRefImpl.class))
            return;

        sb
                .append("</div>\n<div>\n<h2>Responsibilities</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Name</b></td><td><b>Description</b></td><td><b>Pseudo-code</b></td><td><b>Metadata</b></td><td><b>URN Links</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            IURNNode specNode = (IURNNode) iter.next();
            if (specNode instanceof RespRef) {
                Responsibility responsibility = ((RespRef) specNode).getRespDef();
                sb.append("<tr><td>" + EscapeUtils.escapeHTML(responsibility.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(responsibility.getDescription())) + "&nbsp;</td><td>"
                        + EscapeUtils.escapeHTML(notNull(responsibility.getExpression())).replace("\r\n", "<br></br>") + "&nbsp;</td>\n");
                InsertMetadataInTable(responsibility.getMetadata(), sb);
                InsertURNLinks(responsibility.getToLinks(), sb);
                sb.append("</tr>\n");
            }
        }
        sb.append("</tbody></table></br>\n");
    }

    private void InsertMetadataInTable(EList metadata, StringBuffer sb) {
        if (metadata.isEmpty()) {
            sb.append("<td></td>");
        } else {
            sb.append("<td>");
            for (Iterator iter = metadata.iterator(); iter.hasNext();) {
                Metadata mdata = (Metadata) iter.next();
                sb.append("\"" + EscapeUtils.escapeHTML(mdata.getName()) + "\" = \"" + EscapeUtils.escapeHTML(mdata.getValue()) + "\"&nbsp;");
                if (iter.hasNext())
                    sb.append("<br></br>");
            }
            sb.append("&nbsp;</td>\n");
        }
    }

    private void InsertURNLinks(EList urnLinks, StringBuffer sb) {
        if (urnLinks.isEmpty()) {
            sb.append("<td></td>");
        } else {
            sb.append("<td>");
            for (Iterator iter = urnLinks.iterator(); iter.hasNext();) {
                URNlink link = (URNlink) iter.next();
                if (link.getFromElem() instanceof IntentionalElement) {
                    IntentionalElement ie = (IntentionalElement) link.getFromElem();
                    sb.append(EscapeUtils.escapeHTML(ie.getName()) + " (" + EscapeUtils.escapeHTML(ie.getType().getName()) + ")");
                }
                if (iter.hasNext())
                    sb.append("<br></br>");
            }
            sb.append("&nbsp;</td>\n");
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
                .append("</div>\n<div>\n<h2>Start Points</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Name</b></td><td><b>Precondition</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            IURNNode specNode = (IURNNode) iter.next();
            if (specNode instanceof StartPoint) {
                sp = (StartPoint) specNode;
                if (ReportUtils.notEmpty(sp.getPrecondition().getLabel()) || !sp.getMetadata().isEmpty()) {
                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(sp.getName()) + "</td><td>[" + EscapeUtils.escapeHTML(sp.getPrecondition().getLabel()) + "] ==&gt; "
                            + EscapeUtils.escapeHTML(notNull(sp.getPrecondition().getExpression())) + "&nbsp;</td>\n");
                    InsertMetadataInTable(sp.getMetadata(), sb);
                    sb.append("</tr>\n");
                }
            }
        }

        sb.append("</tbody></table></br>\n");
    }

    private boolean hasStartPointData(StartPoint sp) {
        return (ReportUtils.notEmpty(sp.getPrecondition().getLabel()) || !sp.getMetadata().isEmpty());
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

        sb
                .append("</div>\n<div>\n<h2>End Points</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Name</b></td><td><b>Postcondition</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            IURNNode specNode = (IURNNode) iter.next();
            if (specNode instanceof EndPoint) {
                ep = (EndPoint) specNode;
                if (hasEndPointData(ep)) {
                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(ep.getName()) + "</td><td>[" + EscapeUtils.escapeHTML(ep.getPostcondition().getLabel()) + "] ==&gt; "
                            + EscapeUtils.escapeHTML(notNull(ep.getPostcondition().getExpression())) + "&nbsp;</td>");
                    InsertMetadataInTable(ep.getMetadata(), sb);
                    sb.append("</tr>\n");
                }
            }
        }

        sb.append("</tbody></table></br>\n");
    }

    private boolean hasEndPointData(EndPoint ep) {
        return (ReportUtils.notEmpty(ep.getPostcondition().getLabel()) || !ep.getMetadata().isEmpty());
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

        sb
                .append("</div>\n<div>\n<h2>Or Fork Description</h2>\n<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Guard Conditions</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            IURNNode specNode = (IURNNode) iter.next();
            if (specNode instanceof OrFork) {
                OrFork orFork = (OrFork) specNode;

                if (!hasOrForkData(orFork))
                    continue;

                firstCondition = true;

                sb.append("<tr>");

                for (Iterator iter1 = orFork.getSucc().iterator(); iter1.hasNext();) {
                    NodeConnection nc = (NodeConnection) iter1.next();
                    Condition orCondition = nc.getCondition();

                    if (orCondition != null) {
                        if (ReportUtils.notEmpty(orCondition.getLabel())) {
                            if (firstCondition) {
                                sb.append("<td>");
                                firstCondition = false;
                            } else
                                sb.append("<br></br>");
                        }
                        sb.append("[" + EscapeUtils.escapeHTML(orCondition.getLabel()) + "] ==&gt; " + EscapeUtils.escapeHTML(notNull(orCondition.getExpression())) + "(" + nc.getProbability() + ")");
                    }
                }

                if (firstCondition) { // if no conditions were found insert empty column
                    sb.append("<td></td>");
                } else { // terminate column
                    sb.append("</td>");
                }

                InsertMetadataInTable(orFork.getMetadata(), sb);
                sb.append("</tr>\n");
            }
        }

        sb.append("</tbody></table></br>\n");
    }

    private boolean hasOrForkData(OrFork orFork) {
        if (!orFork.getMetadata().isEmpty()) {
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
                    sb.append("</div>\n<div>\n<h2>Stubs</h2>\n");
                    hasBindings = true;
                }

                if (stub.isDynamic())
                    stub_type = "Dynamic Stub - ";
                else
                    stub_type = "Static Stub - ";

                sb.append("<h3>" + stub_type + EscapeUtils.escapeHTML(stub.getName()) + "</h3><hr></hr>\n");

                for (Iterator bindings = stub.getBindings().iterator(); bindings.hasNext();) {

                    PluginBinding binding = (PluginBinding) bindings.next();

                    String pluginDiagramName = ExportWizard.getDiagramName(binding.getPlugin());

                    sb.append("<h4><center><a href=\"" + pluginDiagramName + ".html\">" + "Plugin Map - " + EscapeUtils.escapeHTML(binding.getPlugin().getName())
                            + "</a></center></h4>\n");
                    sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");

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

                        inputBuffer.append("IN " + stubEntryIndex + " &lt;-&gt; " + EscapeUtils.escapeHTML(inBinding.getStartPoint().getName()) + "<br></br>");

                    }

                    for (Iterator outs = binding.getOut().iterator(); outs.hasNext();) {
                        OutBinding outBinding = (OutBinding) outs.next();

                        int stubExitIndex = 0;
                        stubExitIndex = stub.getSucc().indexOf(outBinding.getStubExit()) + 1;

                        outputBuffer.append("OUT " + stubExitIndex + " <-> " + EscapeUtils.escapeHTML(outBinding.getEndPoint().getName()) + "<br></br>");

                    }

                    sb
                            .append("<tr><td><b>Input Bindings</b></td><td>" + inputBuffer + "</td><td><b>Output Bindings</b></td><td>" + outputBuffer
                                    + "</td></tr>");
                    sb.append("</tbody></table></br>\n"); // end of input / output bindings table

                    sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");

                    sb.append("<tr><td><table style=\"text-align: left; width: 15%;\" border=\"1\" cellpadding=\"1\" cellspacing=\"2\">\n<tbody>\n");
                    sb.append("<tr><center><b>Precondition</b></center></tr>\n");
                    sb.append("</tbody></table>");

                    sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");

                    if (ReportUtils.notEmpty(binding.getPrecondition().getLabel())) {
                        sb.append("<tr><td>Label: </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getLabel()) + "</td></tr>");
                    }

                    if (ReportUtils.notEmpty(binding.getPrecondition().getExpression())) {
                        sb.append("<tr><td>Expression: </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getExpression()) + "</td></tr>");
                    }

                    if (ReportUtils.notEmpty(binding.getPrecondition().getDescription())) {
                        sb.append("<tr><td>Description: </td><td>" + EscapeUtils.escapeHTML(binding.getPrecondition().getDescription()) + "</td></tr>");
                    }

                    sb.append("<tr><td>Transaction: </td><td>" + binding.isTransaction() + "</td></tr>");
                    sb.append("<tr><td>Probability: </td><td>" + binding.getProbability() + "</td></tr>");
                    sb.append("</tbody></table></td>\n</tbody></table></br>\n");
                }

                if (!stub.getMetadata().isEmpty()) {
                    sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
                    sb.append("<tr><td><b>Metadata</b></td>\n");
                    InsertMetadataInTable(stub.getMetadata(), sb);
                    sb.append("</tr></tbody></table></br>\n");
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
            sb.append("</div>\n<div>\n<h2>Map Description</h2>\n");
            sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(((UCMmap) diagram).getDescription()));
        }

        EList urnLinks = ((UCMmap) diagram).getToLinks();
        if (!urnLinks.isEmpty()) {

            sb.append("</div>\n<div>\n<h2>URN Links</h2>\n");

            for (Iterator iter = urnLinks.iterator(); iter.hasNext();) {
                URNlink link = (URNlink) iter.next();
                if (link.getFromElem() instanceof IntentionalElement) {
                    IntentionalElement ie = (IntentionalElement) link.getFromElem();
                    sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(ie.getName()) + " (" + EscapeUtils.escapeHTML(ie.getType().getName()) + ")<br></br>");
                }
            }
        }

        InsertMetadata(((UCMmap) diagram).getMetadata(), sb);
    }

    private void OutputGRLDiagramInfo(IURNDiagram diagram, StringBuffer sb) {
        if (ReportUtils.notEmpty(((GRLGraph) diagram).getDescription())) {
            sb.append("</div>\n<div>\n<h2>Diagram Description</h2>\n");
            sb.append("&nbsp;&nbsp;&nbsp;" + EscapeUtils.escapeHTML(((GRLGraph) diagram).getDescription()) );
        }

        InsertMetadata(((GRLGraph) diagram).getMetadata(), sb);
    }

    private void InsertMetadata(EList metadata, StringBuffer sb) {
        if (metadata.isEmpty())
            return;

        sb.append("</div>\n<div>\n<h2>Metadata</h2>\n");

        for (Iterator iter = metadata.iterator(); iter.hasNext();) {
            Metadata mdata = (Metadata) iter.next();
            sb.append("&nbsp;&nbsp;&nbsp;\"" + EscapeUtils.escapeHTML(mdata.getName()) + "\" = \"" + EscapeUtils.escapeHTML(mdata.getValue()) + "\"<br></br>");
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

        sb.append("<h2>Beliefs</h2>\n");
        sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Name</b></td><td><b>Description</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();

            if (currentElement instanceof Belief) {
                Belief currentBelief = (Belief) currentElement;
                if (hasGRLBeliefData(currentBelief)) {
                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentBelief.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(currentBelief.getDescription())) + "</td>");
                    InsertMetadataInTable(currentBelief.getMetadata(), sb);
                    sb.append("</tr>\n");
                }
            }
        }

        sb.append("</tbody></table></br>\n");
    }

    private boolean hasGRLBeliefData(Belief belief) {
        return (ReportUtils.notEmpty(belief.getDescription()) || !belief.getMetadata().isEmpty());
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

        sb.append("<h2>Intentional Elements</h2>\n");
        sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb.append("<tr><td><b>Name</b></td><td><b>Description</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();

            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();

                if (hasGRLIntentionalElementData(ie)) {
                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "</td><td>" + EscapeUtils.escapeHTML(notNull(ie.getDescription())) + "</td>");
                    InsertMetadataInTable(ie.getMetadata(), sb);
                    sb.append("</tr>\n");
                }
            }
        }

        sb.append("</tbody></table></br>\n");
    }

    private boolean hasGRLIntentionalElementData(IntentionalElement ie) {
        return (ReportUtils.notEmpty(ie.getDescription()) || !ie.getMetadata().isEmpty());
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

        sb.append("<h2>Intentional Element URN Links</h2>\n");
        sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb
                .append("<tr><td><b>Name</b><i>(direction)</i></td><td><b>Link Type</b></td><td><b>(</b><i> direction </i> <b>element type)Name</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();

                for (Iterator iter1 = ie.getFromLinks().iterator(); iter1.hasNext();) {

                    URNlink link = (URNlink) iter1.next();
                    String elementType = link.getToElem().getClass().getName();
                    elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "<i>(from)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> to </i>" + elementType
                            + ") " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>");
                    InsertMetadataInTable(link.getMetadata(), sb);
                    sb.append("</tr>\n");
                }

                for (Iterator iter1 = ie.getToLinks().iterator(); iter1.hasNext();) {

                    URNlink link = (URNlink) iter1.next();
                    String elementType = link.getFromElem().getClass().getName();
                    elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                    sb.append("<tr><td>" + EscapeUtils.escapeHTML(ie.getName()) + "<i>(to)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> from </i>" + elementType
                            + ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>");
                    InsertMetadataInTable(link.getMetadata(), sb);
                    sb.append("</tr>\n");
                }

            }
        }
        sb.append("</tbody></table></br>\n");
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

        sb.append("<h2>Actor URN Links</h2>\n");
        sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb
                .append("<tr><td><b>Name</b><i>(direction)</i></td><td><b>Link Type</b></td><td><b>(</b><i>direction</i> <b>element type)Name</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
            currentActorRef = (ActorRef) iter.next();
            currentActor = (Actor) currentActorRef.getContDef();

            for (Iterator iter1 = currentActor.getFromLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getToElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentActor.getName()) + "<i>(from)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> to </i>" + elementType
                        + " ) " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>");
                InsertMetadataInTable(link.getMetadata(), sb);
                sb.append("</tr>\n");
            }

            for (Iterator iter1 = currentActor.getToLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getFromElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentActor.getName()) + "<i>(to)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> from </i>" + elementType
                        + ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>");
                InsertMetadataInTable(link.getMetadata(), sb);
                sb.append("</tr>\n");
            }
        }

        sb.append("</tbody></table></br>\n");
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

        sb.append("<h2>Component URN Links</h2>\n");
        sb.append("<table style=\"text-align: left; width: 100%;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n");
        sb
                .append("<tr><td><b>Name</b><i>(direction)</i></td><td><b>Link Type</b></td><td><b>(</b><i> direction </i> <b>element type)Name</b></td><td><b>Metadata</b></td></tr>\n");

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
            currentComponentRef = (ComponentRef) iter.next();
            currentComponent = (Component) currentComponentRef.getContDef();

            for (Iterator iter1 = currentComponent.getFromLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getToElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentComponent.getName()) + "<i>(From)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> to </i>"
                        + elementType + " ) " + EscapeUtils.escapeHTML(link.getToElem().getName()) + "</td>");
                InsertMetadataInTable(link.getMetadata(), sb);
                sb.append("</tr>\n");
            }

            for (Iterator iter1 = currentComponent.getToLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getFromElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                sb.append("<tr><td>" + EscapeUtils.escapeHTML(currentComponent.getName()) + "<i>(To)</i></td><td>" + EscapeUtils.escapeHTML(notNull(link.getType())) + "</td><td>(" + "<i> from </i>"
                        + elementType + ") " + EscapeUtils.escapeHTML(link.getFromElem().getName()) + "</td>");
                InsertMetadataInTable(link.getMetadata(), sb);
                sb.append("</tr>\n");
            }
        }

        sb.append("</tbody></table></br>\n");
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
            return "";
        else
            return s;
    }

    private String MapType(IURNDiagram diagram) {
        if (diagram instanceof UCMmap) {
            if (((UCMmap) diagram).getParentStub().size() == 0)
                return " - UCM Root Map";
            else
                return " - UCM Plugin Map";
        } else
            return " - GRL Graph";
    }

    public int getType() {
        // TODO Auto-generated method stub
        return 0;
    }
}

