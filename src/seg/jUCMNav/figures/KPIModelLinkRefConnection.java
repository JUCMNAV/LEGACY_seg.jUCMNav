package seg.jUCMNav.figures;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.figures.util.NodeConnectionLocator;

/**
 * This figure represent the connection between 2 GRL Node. It could be a dimentionLink.
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkRefConnection extends PolylineConnection {

    public static final int TYPE_KPIMODEL_LINK = 0;

    private int type;
    private PolylineDecoration kpiModelLink;

    private RotatableDecoration middleDec;

    /**
     * Constructor. Set the default type to kpiModelLink
     */
    public KPIModelLinkRefConnection() {
        super();
        setLineWidth(3);
        kpiModelLink = new PolylineDecoration();
        kpiModelLink.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        kpiModelLink.setLineWidth(3);
        kpiModelLink.setLineStyle(SWT.LINE_SOLID);
        kpiModelLink.setScale(17, 7);

        this.type = TYPE_KPIMODEL_LINK;
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
        if (type == TYPE_KPIMODEL_LINK) {
            setLineStyle(SWT.LINE_DASH);
            setTargetDecoration(kpiModelLink);
            setMiddleDecoration(null);
            setSourceDecoration(null);
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
