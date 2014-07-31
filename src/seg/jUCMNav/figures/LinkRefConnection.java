package seg.jUCMNav.figures;

import org.eclipse.draw2d.ColorConstants;
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
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.figures.util.NodeConnectionLocator;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This figure represent the connection between 2 GRL Node. It could be a contribution, decomposition, or dependency.
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
    public static final int TYPE_MANDATORY =5;
    public static final int TYPE_OPTIONAL =6;

    private int type;
    private PolylineDecoration contribution;
    private PolylineDecoration line;
    private PolygonDecoration depend;
    private PolylineDecoration mandatory;
    
    private CircleDecoration optional = new CircleDecoration();
    
    private RotatableDecoration middleDec;

    public static final PointList DEPENDENCY_FIG = new PointList();

    public static final PointList LINE = new PointList();
    
    public static final PointList MANDATORY_FIG = new PointList();
    static {
        // The dependency figure is the inverse of the other type of link

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
        DEPENDENCY_FIG.addPoint(0, 0);

        LINE.addPoint(-1, 1);
        LINE.addPoint(-1, -1);
        
        int r = 50;
        for (int j = r; j > 0; j--) {
            for (int alpha = 0; alpha <= 360; alpha += 15)
            {
                    double rad = alpha * Math.PI / 180;
                    MANDATORY_FIG.addPoint((int)Math.round(j * Math.sin(rad)) - j, (int)Math.round(j * Math.cos(rad)));
            }
        }
    }

    /**
     * Constructor. Set the default type to Contribution
     */
    public LinkRefConnection() {
        super();
        setLineWidth(3);
        setAntialias(GeneralPreferencePage.getAntialiasingPref());

        contribution = new PolylineDecoration();
        contribution.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        contribution.setLineWidth(3);
        contribution.setScale(17, 7);
        contribution.setAntialias(GeneralPreferencePage.getAntialiasingPref());
      
        optional.setBackgroundColor(ColorConstants.white);
        optional.setFill(true);
        
        mandatory = new PolylineDecoration();
        mandatory.setTemplate(MANDATORY_FIG);
        mandatory.setLineWidth(2);
        mandatory.setScale(0.1, 0.1);
        mandatory.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        
        line = new PolylineDecoration();
        line.setTemplate(LINE);
        line.setLineWidth(3);
        line.setScale(10, 10);
        line.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        depend = new PolygonDecoration();
        depend.setTemplate(DEPENDENCY_FIG);
        depend.setLineWidth(3);
        depend.setFill(true);
        depend.setForegroundColor(ColorManager.LINE);
        depend.setScale(2, 2);
        depend.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        this.type = TYPE_CONTRIBUTION;
        setConnectionVisual();
    }

    /**
     * @return the type of the connection
     */
    public int getType() {
        return type;
    }

    /**
     * Set the type and modify the connection according to this type
     * 
     * @param type
     *            Set the type of the connection
     */
    public void setType(int type) {
        if (this.type != type) {
            this.type = type;

            setConnectionVisual();
        }
    }

    private void setConnectionVisual() {
        if (type == TYPE_CONTRIBUTION) {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(contribution);
            setMiddleDecoration(null);
            setSourceDecoration(null);
        } else if (type == TYPE_CORRELATION) {
            setLineStyle(SWT.LINE_DASH);
            setTargetDecoration(contribution);
            setMiddleDecoration(null);
            setSourceDecoration(null);
        } else if (type == TYPE_DEPENDENCY) {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(depend);
            setSourceDecoration(null);
        } else if (type == TYPE_DECOMPOSITION_AND || type == TYPE_DECOMPOSITION_OR) {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(null);
            setSourceDecoration(line);
        } else if (type == TYPE_OPTIONAL) {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(null);
            setSourceDecoration(optional);
        } else if (type == TYPE_MANDATORY) {
            setLineStyle(SWT.LINE_SOLID);
            setTargetDecoration(null);
            setMiddleDecoration(null);
            setSourceDecoration(mandatory);
        }
        else {
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
        if (lineColor == null || lineColor.length() == 0) {
            setForegroundColor(ColorManager.LINE);
        } else
            setForegroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(lineColor)));
    }

    /**
     * Sets the decoration to be used at the middle of the {@link Connection}.
     * 
     * @param dec
     *            the new decoration
     */
    public void setMiddleDecoration(RotatableDecoration dec) {
        if (middleDec == dec)
            return;
        if (middleDec != null)
            remove(middleDec);
        middleDec = dec;
        if (middleDec != null) {
            add(middleDec, new NodeConnectionLocator(this, ConnectionLocator.MIDDLE));
        }
    }

}
