package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import org.eclipse.draw2d.geometry.Point;

import ucm.map.PathNode;

/**
 * Comparator that sorts element by distance from the reference point given in the constructor.
 * 
 * @author jkealey
 * 
 */
public class PathNodeDistanceComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // the reference point
    private Point from;

    // 1 = x, 2 = y, else = both.
    private int axis = 0;

    /**
     * @param from
     *            the reference point
     */
    public PathNodeDistanceComparator(Point from) {
        this.from = from;
        this.axis = 0;
    }

    /**
     * @param from
     *            the reference point
     * @param axis
     *            look at what axis? 1=x, 2=y, else=both.
     */
    public PathNodeDistanceComparator(Point from, int axis) {
        this.from = from;
        this.axis = axis;
    }

    /**
     * @param arg0
     *            a PathNode
     * @param arg1
     *            a PathNode
     * @return zero if equal distance to the reference point, negative if arg0 is closer and positive if arg1 is
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        PathNode pn1 = (PathNode) arg0;
        PathNode pn2 = (PathNode) arg1;
        Point p1 = new Point(pn1.getX(), pn1.getY());
        Point p2 = new Point(pn2.getX(), pn2.getY());

        if (axis == 1)
            return Math.abs((from.x - p1.x) - (from.x - p2.x));
        else if (axis == 2)
            return Math.abs((from.y - p1.y) - (from.y - p2.y));
        else
            return Math.round((float) from.getDistance(p1) - (float) from.getDistance(p2));

    }
}