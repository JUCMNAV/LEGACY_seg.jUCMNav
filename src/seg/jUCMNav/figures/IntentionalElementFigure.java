/**
 * 
 */
package seg.jUCMNav.figures;

import grl.IntentionalElementType;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * @author Jean-François Roy
 *
 */
public class IntentionalElementFigure extends GrlNodeFigure {

    // the IntentionalElementType
    private int type;

    /**
     * Default figure is a TEAM.
     *  
     */
    public IntentionalElementFigure() {
        setType(IntentionalElementType.SOFTGOAL);
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.figures.GrlNodeFigure#createFigure()
     */
    protected void createFigure() {
        switch (type) {
        case IntentionalElementType.SOFTGOAL:
            Ellipse ellipse = new Ellipse();
            //ellipse.setBounds(new Rectangle(DEFAULT_WIDTH, DEFAULT_HEIGHT, 2, 2));
            ellipse.setLineWidth(3);
            ellipse.setBounds(new Rectangle(preferredSize.width / 4, preferredSize.height / 4, DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2));
            add(ellipse);
            break;
        case IntentionalElementType.RESSOURCE: //TODO modify RESSOURCE for RESOURCE in the metamodel
            RectangleFigure r = new RectangleFigure();
            r.setBounds(new Rectangle(preferredSize.width / 4, preferredSize.height / 4, DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2));
            add(r);
            break;
        default:
            break;
        }

    }

    public int getType(){
        return type;
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.figures.GrlNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    /**
     * 
     * @param type
     *            A IntentionalElement Type.
     */
    public void setType(int type) {
        this.type = type;
    }
}
