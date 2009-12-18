package seg.jUCMNav.figures;

import java.util.Vector;

import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.figures.util.TransformationHelper;

public class AnythingFigure extends PathNodeFigure implements IRotateable {

    private FreeformLayer layer;
    private Vector pointList;
    private Vector lines;

    @Override
    protected void createFigure() {
        pointList = new Vector();
        lines = new Vector();
        
        layer = new FreeformLayer();
        layer.setBounds(new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
        
        Polyline r1 = new Polyline();
        r1.addPoint(new Point(3,5));
        r1.addPoint(new Point(3,19));
        r1.setForegroundColor(ColorManager.FILL);
        r1.setLineWidth(3);
        layer.add(r1);
        lines.add(r1);
        pointList.add(r1.getPoints());
        
        r1 = new Polyline();
        r1.addPoint(new Point(9,5));
        r1.addPoint(new Point(9,19));
        r1.setForegroundColor(ColorManager.FILL);
        r1.setLineWidth(3);
        layer.add(r1);
        lines.add(r1);
        pointList.add(r1.getPoints());
        
        r1 = new Polyline();
        r1.addPoint(new Point(15,5));
        r1.addPoint(new Point(15,19));
        r1.setForegroundColor(ColorManager.FILL);
        r1.setLineWidth(3);
        layer.add(r1);
        lines.add(r1);
        pointList.add(r1.getPoints());
        
        r1 = new Polyline();
        r1.addPoint(new Point(21,5));
        r1.addPoint(new Point(21,19));
        r1.setForegroundColor(ColorManager.FILL);
        r1.setLineWidth(3);
        layer.add(r1);
        lines.add(r1);
        pointList.add(r1.getPoints());
        
//        r1 = new Polyline();
//        r1.addPoint(new Point(23,7));
//        r1.addPoint(new Point(23,17));
//        r1.setForegroundColor(ColorManager.WHITE);
//        r1.setLineWidth(2);
//        layer.add(r1);
//        lines.add(r1);
//        pointList.add(r1.getPoints());
        
        add(layer);
    }

    @Override
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(layer);
        outgoingAnchor = new EllipseAnchor(layer);
    }

    @Override
    public void setColor(Color bg) {
        // TODO Auto-generated method stub
        super.setColor(bg);
    }

    @Override
    protected void setColors() {
        // TODO Auto-generated method stub
        super.setColors();
    }

    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    public void rotate(double angle) {
        Point center = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);

        for (int i = 0; i < pointList.size(); i++) {
            PointList newEdges = TransformationHelper.rotatePoints(angle, (PointList)pointList.get(i), center);
            
            ((Polyline)lines.get(i)).setPoints(newEdges);
        }
    }

    @Override
    public void setHover(boolean hover) {
        int width = 3;
        
        if(hover)
            width = 4;

        for (int i = 0; i < lines.size(); i++) {
            Polyline line = (Polyline)lines.get(i);
            line.setLineWidth(width);
        }
    }
}
