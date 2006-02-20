/**
 * 
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.figures.util.NodeConnectionLocator;

/**
 * This figure represent the connection between 2 GRL Node. It could be a contribution, decomposition,
 * or dependency.
 * 
 * @author Jean-François Roy
 *
 */
public class LinkRefConnection extends PolylineConnection {

    public static final int TYPE_CONTRIBUTION = 0;
    public static final int TYPE_CORRELATION = 1;    
    public static final int TYPE_DECOMPOSITION_AND = 2;
    public static final int TYPE_DECOMPOSITION_OR = 3;
    public static final int TYPE_DEPENDENCY = 4;
    
    private int type;
    private PolylineDecoration contribution;
    private PolylineDecoration line;
    //private DependencyFigure depend;
    private PolygonDecoration depend;
    
    private RotatableDecoration middleDec;
    
    public static final PointList DEPENDENCY_FIG = new PointList();

    public static final PointList LINE = new PointList();
    
    static {
        //The dependency figure is the inverse of the other type of link
//        DEPENDENCY_FIG.addPoint(10,0);
//        DEPENDENCY_FIG.addPoint(9,-2);
//        DEPENDENCY_FIG.addPoint(5,-4);
//        DEPENDENCY_FIG.addPoint(0,-5);
//
//          DEPENDENCY_FIG.addPoint(1, -3);
//          DEPENDENCY_FIG.addPoint(2, 0);
//          DEPENDENCY_FIG.addPoint(1, 3);
//
//        DEPENDENCY_FIG.addPoint(0, 5);
//        DEPENDENCY_FIG.addPoint(5, 4);
//        DEPENDENCY_FIG.addPoint(9, 2);
//        DEPENDENCY_FIG.addPoint(10,0);
        
        DEPENDENCY_FIG.addPoint(0, 0);
        DEPENDENCY_FIG.addPoint(1, -2);
        DEPENDENCY_FIG.addPoint(5, -4);
        DEPENDENCY_FIG.addPoint(10, -5);
        
        DEPENDENCY_FIG.addPoint(9, -3);
        DEPENDENCY_FIG.addPoint(8, 0);
        DEPENDENCY_FIG.addPoint(9, 3);
        
        DEPENDENCY_FIG.addPoint(10, 5);
        DEPENDENCY_FIG.addPoint(5, 4);
        DEPENDENCY_FIG.addPoint(1, 2);
        DEPENDENCY_FIG.addPoint(0,0);
        
        LINE.addPoint(-1,1);
        LINE.addPoint(-1,-1);
    }
    
    /**
     * Contructor. Set the default type to Contribution 
     */
    public LinkRefConnection() {
        super();
        setLineWidth(3);
        contribution = new PolylineDecoration();
        contribution.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        contribution.setLineWidth(3);
        contribution.setScale(17,7);
        
        line = new PolylineDecoration();
        line.setTemplate(LINE);
        line.setLineWidth(3);
        line.setScale(10,10);

        depend = new PolygonDecoration();
        depend.setTemplate(DEPENDENCY_FIG);
        depend.setLineWidth(3);
        depend.setFill(true);
        depend.setForegroundColor(new Color(null, 0,0,0));
        depend.setScale(2,2);
        
        this.type = TYPE_CONTRIBUTION; 
        setConnectionVisual();
    }
    
    /**
     * @return the type of the connection
     */
    public int getType(){
        return type;
    }
    

    /**
     * Set the type and modify the connection according to this type
     * 
     * @param type Set the type of the connection
     */
    public void setType(int type){
        if (this.type != type){
            this.type = type;
            
            setConnectionVisual();
        }
    }
    
    private void setConnectionVisual(){
        if (type == TYPE_CONTRIBUTION){
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(contribution);
            setMiddleDecoration(null);
            setSourceDecoration(null);
        } else if (type == TYPE_CORRELATION){
            setLineStyle(SWT.LINE_DASH);
            setTargetDecoration(contribution);
            setMiddleDecoration(null);
            setSourceDecoration(null);
        } else if (type == TYPE_DEPENDENCY){
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(depend);
            setSourceDecoration(null);
        } else if (type == TYPE_DECOMPOSITION_AND || type == TYPE_DECOMPOSITION_OR){
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(null);
            setSourceDecoration(line);
        } else {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(null);
            setSourceDecoration(null);
        }
    }
    
    /**
     * @see IFigure#setForegroundColor(Color)
     */
    public void setForegroundColor(Color fg) {
        super.setForegroundColor(fg);
        depend.setForegroundColor(fg);
    }

    /**
     * Sets the figure's colors. The default line color is black
     * 
     * @param lineColor
     *            outline color
     */
    public void setColors(String lineColor) {
        RGB color;

        if (lineColor == null || lineColor.length() == 0) {
            lineColor = StringConverter.asString(new RGB(0, 0, 0));
        }

        color = StringConverter.asRGB(lineColor);
        setForegroundColor(new Color(Display.getCurrent(), color));
    }
    
    /**
     * Sets the decoration to be used at the middle of the {@link Connection}.
     * @param dec the new decoration
     */
    public void setMiddleDecoration(RotatableDecoration dec) {
        if (middleDec == dec)
            return;
        if (middleDec != null)
            remove(middleDec);
        middleDec = dec;
        if (middleDec != null){
            add(middleDec, new NodeConnectionLocator(this, ConnectionLocator.MIDDLE));
        }
    }

}
