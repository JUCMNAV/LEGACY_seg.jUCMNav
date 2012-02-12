package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Rectangle figure that represents a comment
 * 
 * @author jkealey
 * 
 */
public class CommentFigure extends GrlNodeFigure {

    /**
     * Default figure is a Dimension.
     * 
     */
    public CommentFigure() {
        super();
        autoResize = false;
        flowPage.setHorizontalAligment(PositionConstants.LEFT);
    }

    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();

        graphics.setLineStyle(SWT.LINE_SOLID);

        setupDimensionBounds(r);
        // graphics.drawRectangle(r);
        PointList second = new PointList();
        PointList points = buildPointList(r, second);
        graphics.drawPolyline(points);
        graphics.drawPolyline(second);

    }

    private PointList buildPointList(Rectangle r) {
        return buildPointList(r, new PointList());
    }

    private PointList buildPointList(Rectangle r, PointList second) {
        int x = r.x + getLineWidth() / 2;
        int y = r.y + getLineWidth() / 2;
        int w = r.width - getLineWidth();
        int h = r.height - getLineWidth();
        int kink = 15;
        if (w < 2 * kink)
            w = 2 * kink;
        if (h < 2 * kink)
            h = 2 * kink;

        PointList points = new PointList();
        points.addPoint(x, y);
        points.addPoint(x + w - kink, y);
        points.addPoint(x + w, y + kink);
        points.addPoint(x + w, y + h);
        points.addPoint(x, y + h);
        points.addPoint(x, y);

        second.addPoint(x + w - kink, y);
        second.addPoint(x + w - kink, y + kink);
        second.addPoint(x + w, y + kink);
        return points;
    }

    private void setupDimensionBounds(Rectangle r) {
        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();
    }

    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        graphics.setLineStyle(SWT.LINE_SOLID);

        setupDimensionBounds(r);
        // graphics.fillRectangle(r);
        PointList points = buildPointList(r);
        graphics.fillPolygon(points);
    }

    /**
     * Sets the figure's colors. The default line color is black, the default fill color is yellow.
     * 
     * @param lineColor
     *            outline color
     * @param fillColor
     *            inside color
     * @param filled
     *            should it be filled?
     */
    public void setColors(String lineColor, String fillColor, boolean filled) {
        setFill(filled);

        if (fillColor == null || fillColor.length() == 0) {
            setFill(true);
            setBackgroundColor(ColorManager.FILL_COMMENTS);
        } else
            setBackgroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(fillColor)));

        if (lineColor == null || lineColor.length() == 0) {
            setForegroundColor(ColorManager.LINE);
        } else
            setForegroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(lineColor)));
    }
}
