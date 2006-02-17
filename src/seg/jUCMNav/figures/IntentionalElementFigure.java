/**
 * 
 */
package seg.jUCMNav.figures;

import grl.IntentionalElementType;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
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
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;
            
            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
            points.addPoint(r.getTopRight().x - r.width/15, r.getCenter().y + r.height/10);
            points.addPoint(r.getTopRight().x, r.getCenter().y);
            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            
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
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;
            graphics.drawRoundRectangle(r, 50, 50);
            break;
        case IntentionalElementType.TASK:
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;

            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
            points.addPoint(r.getTopRight().x, r.getCenter().y);
            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            points.addPoint(r.getBottomLeft().x + r.width/10, r.getBottomLeft().y);
            points.addPoint(r.getTopLeft().x, r.getCenter().y);
            points.addPoint(r.getTopLeft().x + r.width/10, r.getTopLeft().y);
            
            graphics.drawPolygon(points);
            break;
        case IntentionalElementType.RESSOURCE:
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;
            graphics.drawRectangle(r);
            break;
        default:
            break;

        }
 

    }

    protected void fillShape(Graphics graphics){
        Rectangle r = getBounds().getCopy();
        PointList points = new PointList();
        switch (type) {
        case IntentionalElementType.SOFTGOAL:
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;

            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
            points.addPoint(r.getTopRight().x - r.width/15, r.getCenter().y + r.height/10);
            points.addPoint(r.getTopRight().x, r.getCenter().y);
            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            
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
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;
            graphics.fillRoundRectangle(r, 50, 50);
            break;
        case IntentionalElementType.TASK:
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;

            points.addPoint(r.getTopRight().x - r.width/10, r.getTopRight().y);
            points.addPoint(r.getTopRight().x, r.getCenter().y);
            points.addPoint(r.getBottomRight().x - r.width/10, r.getBottomRight().y);
            points.addPoint(r.getBottomLeft().x + r.width/10, r.getBottomLeft().y);
            points.addPoint(r.getTopLeft().x, r.getCenter().y);
            points.addPoint(r.getTopLeft().x + r.width/10, r.getTopLeft().y);
            
            graphics.fillPolygon(points);
            break;
        case IntentionalElementType.RESSOURCE:
            r.x += lineWidth / 2;
            r.y += lineWidth / 2;
            r.width -= lineWidth;
            r.height -= lineWidth;
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
    
    /**
     * Sets the text of the TextFlow to the given value and set the size of the label.
     * 
     * @param newText the new text value.
     */
    public void setText(String newText) {
        textFlow.setText(newText);
        
        //Calculate the size of the label and of the figure
        //Max size available for the label
        int width = getDefaultDimension().width - 2*LABEL_PADDING_X;
        int height = getDefaultDimension().height - 2*LABEL_PADDING_Y;
        
        Dimension dimEditableLabel = flowPage.getPreferredSize().getCopy();

        //Loop until we have good dimension for the labels to fit in the node
        while (dimEditableLabel.width > (width* Math.floor(height/dimEditableLabel.height))){
            height = height + 10;
            width = width + 20;
        }
        
        Rectangle r = new Rectangle();
        r.x = LABEL_PADDING_X;
        r.y = LABEL_PADDING_Y;
        r.width = width;
        r.height = height;
        setConstraint(flowPage,r);
        Point p = new Point(0,0);
        

       
        setSize(width + 2*LABEL_PADDING_X, height + 2*LABEL_PADDING_Y);

    }
}
