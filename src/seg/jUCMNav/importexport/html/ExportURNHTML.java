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
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.UCMmap;
import ucm.map.impl.PluginBindingImpl;
import ucm.map.impl.StubImpl;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * Export an HTML suite from a URNspec.
 *  
 * @author pchen
 *
 */
public class ExportURNHTML implements IURNExport {
    public static final String PAGES_LOCATION = "pages" + File.separator;
    public static final String IMAGES_LOCATION = PAGES_LOCATION + "img" + File.separator; 
    
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
	            htmlMenuItem.setLeafText(diagramName.substring(diagramName.lastIndexOf("-") + 1));
	            htmlMenuItem.setLink(diagramName + ".html");
	            htmlMenuItem.setBaseX(-pane.getBounds().x);
	            htmlMenuItem.setBaseY(-pane.getBounds().y);
	            htmlMenuItem.setDiagram(diagram);
	            
	            // export the htmlf or this diagram
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
            String srcFile = "htmltemplates/index.html";
            String desFile = htmlPath + "index.html";
            copy(srcFile, desFile);

            // Generate the main page
            srcFile = "htmltemplates/main.html";
            desFile = htmlPath + PAGES_LOCATION + "main.html";
            copy(srcFile, desFile);

            // Generate the menu page
            srcFile = "htmltemplates/menu.html";
            desFile = htmlPath + PAGES_LOCATION + "menu.html";
            copy(srcFile, desFile);

            // Generate the xml tree css file
            srcFile = "htmltemplates/xmlTree.css";
            desFile = htmlPath + PAGES_LOCATION + "xmlTree.css";
            copy(srcFile, desFile);

            // Generate the report css file
            srcFile = "htmltemplates/report.css";
            desFile = htmlPath + PAGES_LOCATION + "report.css";
            copy(srcFile, desFile);
            
            // Generate the menu css file
            srcFile = "htmltemplates/menu.css";
            desFile = htmlPath + PAGES_LOCATION + "menu.css";
            copy(srcFile, desFile);

            // Generate the xml tree java script file
            srcFile = "htmltemplates/xmlTree.js";
            desFile = htmlPath + PAGES_LOCATION + "xmlTree.js";
            copy(srcFile, desFile);
            
            // Generate the menu java script file
            srcFile = "htmltemplates/menu.js";
            desFile = htmlPath + PAGES_LOCATION + "menu.js";
            copy(srcFile, desFile);

            // Generate the tree xsl file
            srcFile = "htmltemplates/xmlTree.xsl";
            desFile = htmlPath + PAGES_LOCATION + "xmlTree.xsl";
            copy(srcFile, desFile);

            // Generate the closed.gif file
            srcFile = "htmltemplates/closed.gif";
            desFile = htmlPath + PAGES_LOCATION + "closed.gif";
            copy(srcFile, desFile);

            // Generate the doc.gif file
            srcFile = "htmltemplates/doc.gif";
            desFile = htmlPath + PAGES_LOCATION + "doc.gif";
            copy(srcFile, desFile);

            // Generate the open.gif file
            srcFile = "htmltemplates/open.gif";
            desFile = htmlPath + PAGES_LOCATION + "open.gif";
            copy(srcFile, desFile);
            
            // Generate the open.gif file
            srcFile = "htmltemplates/LogoFinal.gif";
            desFile = htmlPath + PAGES_LOCATION + "LogoFinal.gif";
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
        String imgFilePath = "";
        String imgDirectoryPath = "";
        
        // Add "\img" into the path
        imgDirectoryPath = getPath(htmlPath) + IMAGES_LOCATION;
        imgFilePath = imgDirectoryPath + diagramName + ".gif";
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
        String directoryPath = "";
        
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
            sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            sb.append("<head>\n");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
            sb.append("<title>URN Map</title>\n");
            sb.append("<script language=JavaScript src=\"menu.js\"></script>\n");
            sb.append("<link href=\"menu.css\" rel=stylesheet>\n");
            sb.append("<style>\n");
            sb.append("body {\n");
            sb.append("font: 11px Arial,Verdana,Helvetica,sans-serif;\n");
            sb.append("}\n");
            sb.append("</style>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");

            boolean hasStub = false;
            Iterator nodeIter = null;

            EList nodes = diagram.getNodes();
            if (!nodes.isEmpty()) {
                nodeIter = nodes.iterator();
                
                int top = 20;
                int left = 30;
                
                sb.append("<div align=\"left\" style=\"position:absolute; top:" + top + "px; left:" + left + "px;\">" + diagramName.substring(diagramName.lastIndexOf("-") + 1) + 
                        "<br/><img src=\"img/" + diagramName + ".gif\" border=\"0\" style=\"position:absolute; top:" + top + "px; left:0px;\" />\n");
                sb.append("<script language=\"JavaScript\">\n");
                sb.append("<!--\n");
                
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
                            
                            sb.append("var stubHierarchy" + numOfStub + " = [\n");
                            sb.append("['', null,\n");

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
                                    
                                    sb.append("[map('" + pluginDiagramName.substring(pluginDiagramName.lastIndexOf("-") + 1) + 
                                            "'), '" + pluginDiagramName + ".html', [thumbnails('" + pluginDiagramName + ".gif')]]\n");
                                    
                                    if (bindIter.hasNext()) {
                                        sb.append(",");
                                    }
                                }
                            }
                            // end while
                            
                            sb.append("]\n");
                            sb.append("]\n");
                            
                            sb.append("var stubConfig" + numOfStub + " = [\n");
                            sb.append("{\n");
                            sb.append("'height' :  " + height + ",\n");
                            sb.append("'width' :  " + width + ",\n");
                            sb.append("'firstX' :  " + (stub.getX() + menuItem.getBaseX() - width/2) + ",\n");
                            sb.append("'firstY' :  " + (stub.getY() + menuItem.getBaseY() + top - height/2) + ",\n");
                            sb.append("'nextX' :  " + "0,\n");
                            sb.append("'hideAfter' :  " +  "200,\n");
                            sb.append("'css' :  " + "'gurtl0',\n");
                            sb.append("'trace' :  " + "true\n");
                            sb.append("},\n");
                            
                            sb.append("{");
                            sb.append("'height' :  " + "23,\n");
                            sb.append("'width' :  " + "120,\n");
                            sb.append("'firstY' :  " + "5,\n");
                            sb.append("'firstX' :  " + "-55,\n");
                            sb.append("'nextY' :  " + "-1,\n");
                            sb.append("'target' :  " + "'_self',\n");
                            sb.append("'css' :  " + "'gurtl1'\n");
                            sb.append("},\n");
                            
                            sb.append("{");
                            sb.append("'width' :  " + "174,\n");
                            sb.append("'height' :  " + "117,\n");
                            sb.append("'firstY' :  " + "0,\n");
                            sb.append("'firstX' :  " + "120,\n");
                            sb.append("'css' :  " + "'gurtl2'\n");
                            sb.append("}\n");
                            sb.append("];\n");
                            
                            sb.append("new menu (stubHierarchy" + numOfStub + ", stubConfig" + numOfStub + ");\n");
                        } else {
                            // System.out.println("No binds existing.");
                        }
                    }
                }
                // end while
                
                sb.append("//-->\n");
                sb.append("</script>\n");
            } else {
                // System.out.println("No nodes existing.");
            }
            
            sb.append("</div>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");

            fos = new FileOutputStream(directory + PAGES_LOCATION + diagramName + ".html");
            bos = new BufferedOutputStream(fos);
            
            bos.write(sb.toString().getBytes(), 0, sb.length());
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
}
