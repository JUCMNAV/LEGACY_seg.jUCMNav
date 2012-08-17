package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * Figure which represent an actor
 * 
 * @author Jean-François Roy
 * 
 */
public class ActorFigure extends GrlNodeFigure {

    /**
     * Constructor of the Actor Figure
     */
    public ActorFigure() {
        super();
    }

    protected boolean shouldHideInnerText()
    {
        return true;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.GrlNodeFigure#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();

        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();

        graphics.setLineStyle(SWT.LINE_DASH);
        int[] dashpattern = { 2, 2 };
        graphics.setLineDash(dashpattern);
        graphics.drawOval(r);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.GrlNodeFigure#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();

        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();

        graphics.setLineStyle(SWT.LINE_DASH);
        int[] dashpattern = { 2, 2 };
        graphics.setLineDash(dashpattern);
        graphics.fillOval(r);

    }

}
