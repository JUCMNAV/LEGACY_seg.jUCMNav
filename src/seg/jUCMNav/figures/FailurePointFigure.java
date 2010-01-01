package seg.jUCMNav.figures;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

public class FailurePointFigure extends PathNodeFigure implements IRotateable {

    private Polyline rect1;
    private Polyline rect2;
    private Polyline rect3;
    
    private PointList line1;
    private PointList line2;
    private PointList line3;
    
    protected Vector lines;
    protected Vector rects;
    
    public FailurePointFigure() {
        super();
    }

    @Override
    protected void createFigure() {
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;
        
        lines = new Vector();
        rects = new Vector();

        rect1 = new Polyline();
        line1 = new PointList();
        line1.addPoint(new Point((width - 20) / 2, 18));
        line1.addPoint(new Point((width - 20) / 2 + 20, 18));
        rect1.setPoints(line1);
        rect1.setBackgroundColor(ColorManager.BLACK);
        rect1.setLineWidth(2);
        rect1.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        lines.add(line1);
        rects.add(rect1);
        add(rect1);
        
        rect2 = new Polyline();
        line2 = new PointList();
        line2.addPoint(new Point((width - 14) / 2, 13));
        line2.addPoint(new Point((width - 14) / 2 + 14, 13));
        rect2.setPoints(line2);
        rect2.setBackgroundColor(ColorManager.BLACK);
        rect2.setLineWidth(2);
        rect2.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        lines.add(line2);
        rects.add(rect2);
        add(rect2);
        
        rect3 = new Polyline();
        line3 = new PointList();
        line3.addPoint(new Point((width - 8) / 2, 8));
        line3.addPoint(new Point((width - 8) / 2 + 8, 8));
        rect3.setPoints(line3);
        rect3.setBackgroundColor(ColorManager.BLACK);
        rect3.setLineWidth(2);
        rect3.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        lines.add(line3);
        rects.add(rect3);
        add(rect3);
    }

    public void setColor(Color bg) {
        super.setColor(bg);
        
        for (Iterator i = rects.iterator(); i.hasNext();) {
            Polyline l = (Polyline) i.next();
            l.setBackgroundColor(bg);
        }
    }

    @Override
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(rect1);
        outgoingAnchor = new EllipseAnchor(rect1);
    }

    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    @Override
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover) {
            rect1.setBackgroundColor(ColorManager.BLACK);
        } else {
            rect1.setBackgroundColor(ColorManager.BLACK);
        }
    }

    @Override
    protected void setColors() {
        super.setColors();
        
        setColor(ColorManager.BLACK);
    }

    public void rotate(double angle) {
        // make it always point towards bottom
        if (Math.cos(angle)>0)
            angle -= Math.PI;

        Transform t = new Transform();
        t.setRotation(angle);
        
        Point center = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);
        
        for (int j = 0; j < lines.size(); j++) {
            PointList points = (PointList) lines.get(j);
            Polyline line = (Polyline) rects.get(j);
            
            PointList newPoints = new PointList();
            for (int i = 0; i < points.size(); i++) {
                Point newPoint = t.getTransformed(new Point(points.getPoint(i).x - center.x, points.getPoint(i).y - center.y));
                //Point pt = new Point(newPoint.x - center.x, newPoint.y - center.y);
                
                newPoints.addPoint(newPoint);
            }
            
            newPoints.translate(center.x, center.y);
            
            line.setPoints(newPoints);
        }
        
        ((EllipseAnchor)outgoingAnchor).ancestorMoved(this);
    }

    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(48, 48);
    }
}
