package seg.jUCMNav.figures.anchors;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This anchor is used for decomposition. It is at the bottom of an intentionalElement
 * 
 * @author Jean-François Roy
 * 
 */
public class DecompositionAnchor extends AbstractConnectionAnchor {

    public static final int TYPE_SRC = 0;
    public static final int TYPE_TARGET = 1;

    private int type;

    /**
     * @param owner
     */
    public DecompositionAnchor(IFigure owner, int type) {
        super(owner);
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    public Point getLocation(Point reference) {
        Rectangle r = getOwner().getBounds().getCopy();
        Point p = new Point(r.getCenter().x, r.getBottom().y);
        getOwner().translateToAbsolute(p);
        return p;
    }

}
