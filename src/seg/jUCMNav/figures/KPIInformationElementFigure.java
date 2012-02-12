package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * Rectangle figure that represent KPI information elements: - Dimension = Dashed Round
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementFigure extends GrlNodeFigure {

    // the KPIInformationElementType
    private int type;

    /**
     * Default figure is a Dimension.
     * 
     */
    public KPIInformationElementFigure() {
        super();
    }

    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();

        int[] dashpattern = { 5, 2 };
        graphics.setLineDash(dashpattern);
        graphics.setLineStyle(SWT.LINE_DASH);

        setupDimensionBounds(r);
        graphics.drawRectangle(r);
    }

    private void setupDimensionBounds(Rectangle r) {
        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();
    }

    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();

        int[] dashpattern = { 5, 2 };
        graphics.setLineDash(dashpattern);
        graphics.setLineStyle(SWT.LINE_DASH);

        setupDimensionBounds(r);
        graphics.fillRectangle(r);
    }

}
