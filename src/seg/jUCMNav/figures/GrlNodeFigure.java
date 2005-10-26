package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;


/**
 * The figure for a GrlNode (could not use PathNodeFigure because it is a RectangleFigure). 
 * 
 * @author Jean-François Roy
 *
 */
public abstract class GrlNodeFigure extends Figure {

    // default sizes
    protected static int DEFAULT_HEIGHT = 50;
    protected static int DEFAULT_WIDTH = 100;

    /**
     * Override this method if you your figure is not of the default size. This method is invoked to know where to insert labels by default.
     * 
     * @return Returns the default dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    // is the figure in hover state
    protected boolean hover;

    protected ConnectionAnchor incomingAnchor;
    protected ConnectionAnchor outgoingAnchor;
    protected Dimension preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    // is the figure in selected state
    protected boolean selected;
    protected XYLayout xylayout;
    
    /**
     * Creates the figure and initializes anchors.
     *  
     */
    public GrlNodeFigure() {
        super();
        xylayout = new XYLayout();
        this.setLayoutManager(xylayout);

        createFigure();

        initAnchor();
    }
    
    /**
     * Initialize all the figures composing this figure.
     */
    protected abstract void createFigure();

    /**
     * @return this figure
     */
    public Figure getFigure() {
        return this;
    }
    
    /**
     * 
     * @see org.eclipse.draw2d.IFigure#getMinimumSize(int, int)
     */
    public Dimension getMinimumSize(int wHint, int hHint) {
        return getPreferredSize();
    }

    /**
     * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return preferredSize;
    }

    /**
     * This return the connection anchor for an incoming connection.
     * 
     * @return The connecction anchor
     */
    public ConnectionAnchor getSourceConnectionAnchor() {
        return outgoingAnchor;
    }

    /**
     * This return the connection anchor for an outgoing connection.
     * 
     * @return The connecction anchor
     */
    public ConnectionAnchor getTargetConnectionAnchor() {
        return incomingAnchor;
    }

    /**
     * Initialize the anchors to this figure.
     */
    protected abstract void initAnchor();

    /**
     * @return Returns the selected.
     */
    public boolean isSelected() {
        return selected;
    }
    /**
     * Set this figure's background color.
     * 
     * @param bg
     *            the background color.
     */
    public void setColor(Color bg) {
        getFigure().setBackgroundColor(bg);
    }

    /**
     * What to do when the Node is hovered over. Default implementation makes background color gray instead of white.
     * 
     * @param hover
     *            is being hovered?
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (selected == false) {
            if (hover)
                setColor(new Color(null, 230, 230, 230));
            else
                setColor(new Color(null, 255, 255, 255));
        }
    }

    /**
     * What to do when the Node is selected. Default implementation makes the background color blue instead of white.
     * 
     * @param selected
     *            The selected to set.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected == true)
            setColor(new Color(null, 0, 102, 204));
        else
            setColor(new Color(null, 255, 255, 255));
    }
    
}
