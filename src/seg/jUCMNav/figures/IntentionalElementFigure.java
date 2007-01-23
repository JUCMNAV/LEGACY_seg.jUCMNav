package seg.jUCMNav.figures;

import grl.IntentionalElementType;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.figures.anchors.DecompositionAnchor;



/**
 * Rectangle figure that represent intentional elements:
 * - Softgoal = Cloud
 * - Goal = Ellipse
 * - Resource = Rectangle
 * - Task = Triangular Rectangle
 * 
 * @author Jean-François Roy
 *
 */
public class IntentionalElementFigure extends GrlNodeFigure {

    // the IntentionalElementType
    private int type;
    
    private DecompositionAnchor decompositionTarget;
    
    /**
     * Default figure is a Softgoal.
     *  
     */
    public IntentionalElementFigure() {
        super();
        setType(IntentionalElementType.SOFTGOAL);
        decompositionTarget = new DecompositionAnchor(this, DecompositionAnchor.TYPE_TARGET);
    }
    
    public ConnectionAnchor getDecompositionTarget(){
        return decompositionTarget;
    }
    
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        PointList points = new PointList();
        switch (type) {
        case IntentionalElementType.SOFTGOAL:
            setupBounds(r);
            
//            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
//            points.addPoint(r.getTopRight().x - r.width/15, r.getCenter().y + r.height/10);
//            points.addPoint(r.getTopRight().x, r.getCenter().y);
//            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            
            //Draw the half circle at the left
            graphics.drawArc(r.getTopRight().x - r.width*2/5, r.getTop().y, r.width*2/5, r.height,266, 185);
            //Draw the half circle at the right
            graphics.drawArc(r.getTopLeft().x, r.getTop().y, r.width*2/5, r.height,90, 180);
            //Draw the two half circle in the middle of the figure
            graphics.drawArc(r.getTopLeft().x + r.width*1/5, r.getTop().y - r.height*1/16, r.width*3/5, r.height*2/16, 180, 185);
            //Draw the two half circle in the middle of the figure
            graphics.drawArc(r.getBottomLeft().x + r.width*1/5, r.getBottom().y - r.height*1/16, r.width*3/5, r.height*2/16, 0, 185);
            break;
        case IntentionalElementType.GOAL:
            setupBounds(r);
            graphics.drawRoundRectangle(r, 50, 50);
            break;
        case IntentionalElementType.TASK:
            fillTaskPoints(r, points);
            
            graphics.drawPolygon(points);
            break;
        case IntentionalElementType.RESSOURCE:
            setupBounds(r);
            graphics.drawRectangle(r);
            break;
        default:
            break;

        }
 

    }

    private void setupBounds(Rectangle r) {
        r.x += lineWidth / 2;
        r.y += lineWidth / 2;
        r.width -= lineWidth;
        r.height -= lineWidth;
    }

    private void fillTaskPoints(Rectangle r, PointList points) {
        setupBounds(r);

        points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
        points.addPoint(r.getTopRight().x, r.getCenter().y);
        points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
        points.addPoint(r.getBottomLeft().x + r.width/10, r.getBottomLeft().y);
        points.addPoint(r.getTopLeft().x, r.getCenter().y);
        points.addPoint(r.getTopLeft().x + r.width/10, r.getTopLeft().y);
    }

    protected void fillShape(Graphics graphics){
        Rectangle r = getBounds().getCopy();
        PointList points = new PointList();
        switch (type) {
        case IntentionalElementType.SOFTGOAL:
            setupBounds(r);

//            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
//            points.addPoint(r.getTopRight().x - r.width/15, r.getCenter().y + r.height/10);
//            points.addPoint(r.getTopRight().x, r.getCenter().y);
//            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            
            //Fill the half circle at the left
            graphics.fillArc(r.getTopRight().x - r.width*2/5, r.getTop().y, r.width*2/5, r.height,266, 185);
            //Fill the half circle at the right
            graphics.fillArc(r.getTopLeft().x, r.getTop().y, r.width*2/5, r.height,90, 180);
           
            //Fill the rectangle in the middle of the figure
            graphics.fillRectangle(r.getTopLeft().x + r.width*1/6, r.getTop().y, r.width * 7/10, r.height);
            
            //Fill the two half circle in the middle of the figure
            graphics.fillArc(r.getTopLeft().x + r.width*1/5, r.getTop().y - r.height*1/16, r.width*3/5, r.height*2/16, 180, 185);
            //Fill the two half circle in the middle of the figure
            graphics.fillArc(r.getBottomLeft().x + r.width*1/5, r.getBottom().y - r.height*1/16, r.width*3/5, r.height*2/16, 0, 185);
            break;
        case IntentionalElementType.GOAL:
            setupBounds(r);
            graphics.fillRoundRectangle(r, 50, 50);
            break;
        case IntentionalElementType.TASK:
            fillTaskPoints(r, points);
            
            graphics.fillPolygon(points);
            break;
        case IntentionalElementType.RESSOURCE:
            setupBounds(r);
            graphics.fillRectangle(r);
            break;
        default:
            break;

        }

    }
    
    public int getType(){
        return type;
    }

    /**
     * 
     * @param type
     *            A IntentionalElement Type.
     */
    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
        }
    }
    
}
