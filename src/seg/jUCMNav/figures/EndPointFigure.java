package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-02-15
 * 
 * This figure represent and EndPoint
 * 
 * @author Etienne Tremblay
 */
public class EndPointFigure extends PathNodeFigure {

    private RectangleFigure rect;
    private Polyline line;
    //TODO Make the EndPoint draw depending on the orientation of the spline.
    private Ray entryVect;

    /**
     *  
     */
    public EndPointFigure() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.NodeFigure#createFigure()
     */
    protected void createFigure() {
        rect = new RectangleFigure();
        rect.setBounds(new Rectangle(10, 0, 4, 24));
        rect.setBackgroundColor(new Color(null, 0, 0, 0));
        add(rect);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.NodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(rect);
        outgoingAnchor = new ChopboxAnchor(rect);
    }
}