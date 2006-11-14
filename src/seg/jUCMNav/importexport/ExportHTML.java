package seg.jUCMNav.importexport;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.UCMmap;
import ucm.map.impl.PluginBindingImpl;
import ucm.map.impl.StubImpl;
import urncore.IURNDiagram;

public class ExportHTML implements IUseCaseMapExport {
    /**
     * Given IURNDiagram, generate the HTML page for this diagram
     * 
     * @param diagram
     * @param fos
     */
    public void export(IURNDiagram diagram, FileOutputStream fos) throws InvocationTargetException {
        HTMLMenuItem menuItem = new HTMLMenuItem();
        String diagramName = menuItem.getDiagramName();
        BufferedOutputStream bos = null;

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
                        "<br/><img src=\"img/" + diagramName + ".jpg\" border=\"0\" style=\"position:absolute; top:" + top + "px; left:0px;\" />\n");
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
                                    String pluginDiagramName = new ExportWizard().getDiagramName(childMap);
                                    
                                    sb.append("[map('" + pluginDiagramName.substring(pluginDiagramName.lastIndexOf("-") + 1) + 
                                            "'), '" + pluginDiagramName + ".html', [thumbnails('" + pluginDiagramName + ".jpg')]]\n");
                                    
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
        }

    }

    /**
     * Given the IFigure, save it to a file.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure pane, FileOutputStream fos) {
        // generate image
        Image image = new Image(Display.getCurrent(), pane.getSize().width, pane.getSize().height);
        GC gc = new GC(image);
        SWTGraphics graphics = new SWTGraphics(gc);
        // if the bounds are in the negative x/y, we don't see them without a translation
        graphics.translate(-pane.getBounds().x, -pane.getBounds().y);
        pane.paint(graphics);

        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { image.getImageData() };
        loader.save(fos, getType());

        gc.dispose();
        image.dispose();
    }

    public void export(IURNDiagram diagram, String path) throws InvocationTargetException {
    }

    public void export(IFigure map, String path) throws InvocationTargetException {
    }

    /**
     * Returns SWT.IMAGE_JPEG
     * 
     * @see seg.jUCMNav.importexport.ExportImage#getType()
     */
    public int getType() {
        return SWT.IMAGE_JPEG;
    }

}
