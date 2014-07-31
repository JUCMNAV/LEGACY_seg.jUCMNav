package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
 
/**
 * A decoration for {@link PolylineConnection} endpoints, which draws a circlE
 * whose center is located on the link and is distanced RADIUS (internal parameter)
 * units from the end of the connection.
 */
public class CircleDecoration extends Ellipse implements RotatableDecoration {
 
    private static final double RADIUS = 4.5;
    private Point location = new Point();
 
    public CircleDecoration() {
        super();
    }
 
    @Override public void setLocation(Point p) {
        location = p;
        Rectangle bounds = new Rectangle(location.x-(int)RADIUS, location.y-(int)RADIUS, (int)RADIUS*2, (int)RADIUS*2);
        setBounds(bounds);
    }
 
    @Override public void setReferencePoint(Point p) {
       
        double d = Math.sqrt(Math.pow((location.x-p.x), 2)+Math.pow(location.y-p.y,2));
         
        if(d < RADIUS)
            return;
   
        double k = (d-RADIUS)/d;
        double longx = Math.abs(p.x-location.x);
        double longy = Math.abs(p.y-location.y);
 
        double shortx = k*longx;
        double shorty = k*longy;
        
        int rx, ry;
        if(location.x < p.x) {
            rx = p.x - (int)shortx;
        } else {
            rx = p.x + (int)shortx;
        }
        if(location.y > p.y) {
            ry = p.y + (int)shorty;
        } else {
            ry = p.y - (int)shorty;
        }
         
        setBounds(new Rectangle(rx-(int)RADIUS, ry-(int)RADIUS, (int)(RADIUS*3), (int)(RADIUS*3)));
    }
}
