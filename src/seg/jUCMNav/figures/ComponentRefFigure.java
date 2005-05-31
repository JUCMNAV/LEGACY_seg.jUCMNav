package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import urncore.ComponentKind;

/**
 * Created on 30-May-2005 
 * 
 * A figure for components refs.
 * 
 * @author jkealey
 *  
 */
public class ComponentRefFigure extends RectangleFigure {

    private int kind;

    public ComponentRefFigure() {
        setLineWidth(3);

        setKind(ComponentKind.TEAM);
    }

    /**
     * Sets the figure's colors. The default line color is black, the default fill color is white.
     * 
     * @param lineColor
     * @param fillColor
     * @param filled
     */
    public void setColors(String lineColor, String fillColor, boolean filled) {
        RGB color;
        setFill(filled);

        if (fillColor == null || fillColor.length() == 0) {
            fillColor = StringConverter.asString(new RGB(255, 255, 255));
        }
        color = StringConverter.asRGB(fillColor);
        setBackgroundColor(new Color(Display.getCurrent(), color));

        if (lineColor == null || lineColor.length() == 0) {
            lineColor = StringConverter.asString(new RGB(0, 0, 0));
        }

        color = StringConverter.asRGB(lineColor);
        setForegroundColor(new Color(Display.getCurrent(), color));
    }

    /**
     * A ComponentKind.
     * 
     * @param type
     */
    public void setKind(int type) {
        this.kind = type;
    }

    /**
     * @see Shape#fillShape(Graphics)
     */
    protected void fillShape(Graphics graphics) {
        //planning on overriding for other types.
        graphics.fillRectangle(getBounds());
    }

    /**
     * @see Shape#outlineShape(Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        // planning on overriding for other types.
        switch (kind) {
        case ComponentKind.TEAM:
        case ComponentKind.OTHER:
            super.outlineShape(graphics);
            break;

        }

    }
}