package seg.jUCMNav.model.util;

import java.util.Comparator;

import org.eclipse.draw2d.geometry.Point;

import ucm.map.PathNode;

/**
 * Created on 29-Jun-2005
 * 
 * @author jkealey
 *  
 */
public class PathNodeDistanceComparator implements Comparator {

    Point from;

    /**
     *  
     */
    public PathNodeDistanceComparator(Point from) {
        this.from = from;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        PathNode pn1 = (PathNode) arg0;
        PathNode pn2 = (PathNode) arg1;
        Point p1 = new Point(pn1.getX(), pn1.getY());
        Point p2 = new Point(pn2.getX(), pn2.getY());

        return Math.round((float) from.getDistance(p1) - (float) from.getDistance(p2));

    }
}