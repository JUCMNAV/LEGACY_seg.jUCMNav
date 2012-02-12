package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.ComponentKind;

/**
 * A figure for components refs. Changes appearance depending on ComponentKind. Bounds still stay square.
 * 
 * @author jkealey
 * 
 */
public class ComponentRefFigure extends RectangleFigure {

    // the ComponentKind.
    private int kind;

    /**
     * Default figure is a TEAM.
     * 
     */
    public ComponentRefFigure() {
        setLineWidth(3);
        setKind(ComponentKind.TEAM);
        setAntialias(GeneralPreferencePage.getAntialiasingPref()); // recommended by @pushmatrix :)
    }

    /**
     * Fills the interior of the ComponentRef.
     * 
     * @see Shape#fillShape(Graphics)
     */
    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        switch (kind) {
        case ComponentKind.OBJECT:
            r.x += getLineWidth() / 2;
            r.y += getLineWidth() / 2;
            r.width -= getLineWidth();
            r.height -= getLineWidth();
            graphics.fillRoundRectangle(r, 50, 50);
            break;
        case ComponentKind.PROCESS:
            r.x += getLineWidth() / 2;
            r.y += getLineWidth() / 2;
            r.width -= getLineWidth();
            r.height -= getLineWidth();
            PointList points = new PointList();
            points.addPoint(r.getTopRight());
            points.addPoint(r.getBottomRight().x - r.height / 10, r.getBottomRight().y);
            points.addPoint(r.getBottomLeft());
            points.addPoint(r.getTopLeft().x + r.height / 10, r.getTopLeft().y);

            graphics.fillPolygon(points);
            break;
        case ComponentKind.TEAM:
        case ComponentKind.OTHER:
        default:
            super.fillShape(graphics);
            break;

        }

    }

    /**
     * 
     * @return the ComponentKind.
     */
    public int getKind() {
        return kind;
    }

    /**
     * Defines the outline of the shape.
     * 
     * Object: Rounded Rectangle, Process: Parallelogram, Team/Other: Rectangle, Actor: Rectangle with stickman
     * 
     * 
     * @see Shape#outlineShape(Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        PointList points = new PointList();
        switch (kind) {
        case ComponentKind.OBJECT:
            r.x += getLineWidth() / 2;
            r.y += getLineWidth() / 2;
            r.width -= getLineWidth();
            r.height -= getLineWidth();
            graphics.drawRoundRectangle(r, 50, 50);
            break;
        case ComponentKind.PROCESS:
            r.x += getLineWidth() / 2;
            r.y += getLineWidth() / 2;
            r.width -= getLineWidth();
            r.height -= getLineWidth();

            points.addPoint(r.getTopRight());
            points.addPoint(r.getBottomRight().x - r.height / 10, r.getBottomRight().y);
            points.addPoint(r.getBottomLeft());
            points.addPoint(r.getTopLeft().x + r.height / 10, r.getTopLeft().y);

            graphics.drawPolygon(points);
            break;
        case ComponentKind.ACTOR:
            // draw rectangle
            int x = r.x + getLineWidth() / 2;
            int y = r.y + getLineWidth() / 2;
            int w = r.width - getLineWidth();
            int h = r.height - getLineWidth();
            graphics.drawRectangle(x, y, w, h);

            // offset figure
            x += 5;
            y -= 5;

            // paint stickman
            graphics.setLineWidth(2);
            points.addPoint(x + 10, y + 20);
            points.addPoint(x + 10, y + 30);
            points.addPoint(x + 5, y + 35);
            graphics.drawPolyline(points);

            points.removeAllPoints();
            points.addPoint(x + 10, y + 30);
            points.addPoint(x + 15, y + 35);
            graphics.drawPolyline(points);

            points.removeAllPoints();
            points.addPoint(x + 5, y + 24);
            points.addPoint(x + 15, y + 24);
            graphics.drawPolyline(points);

            // draw manly head.
            graphics.drawOval(x + 7, y + 14, 6, 6);

            break;
        case ComponentKind.TEAM:
        case ComponentKind.OTHER:
        default:
            super.outlineShape(graphics);
            break;

        }

    }

    /**
     * Sets the figure's colors. The default line color is black, the default fill color is white.
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
            setBackgroundColor(ColorManager.FILL);
        } else
            setBackgroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(fillColor)));

        if (lineColor == null || lineColor.length() == 0) {
            setForegroundColor(ColorManager.LINE);
        } else
            setForegroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(lineColor)));
    }

    /**
     * 
     * @param type
     *            A ComponentKind.
     */
    public void setKind(int type) {
        if (type != kind) {
            if (type == ComponentKind.AGENT)
                this.setLineWidth(this.getLineWidth() + 3);
            else if (kind == ComponentKind.AGENT)
                this.setLineWidth(this.getLineWidth() - 3);

            this.kind = type;
        }
    }
}