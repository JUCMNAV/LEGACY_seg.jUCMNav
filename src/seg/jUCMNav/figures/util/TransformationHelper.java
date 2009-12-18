package seg.jUCMNav.figures.util;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

public class TransformationHelper {
    
    // Rotate a PointList of an angle rotated with this center point
    public static PointList rotatePoints(double angle, PointList points, Point center) {
        Transform t = new Transform();
        t.setRotation(angle);

        PointList newEdges = new PointList();

        for (int i = 0; i < points.size(); i++) {
            Point newPoint = t.getTransformed(new Point(points.getPoint(i).x - center.x, points.getPoint(i).y - center.y));
            Point pt = new Point(center.x - newPoint.x, center.y - newPoint.y);
            newEdges.addPoint(pt);
        }
        
        return newEdges;
    }
}
