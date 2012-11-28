package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * This is the figure representing a PathNode. Extend this class for the figures representing the subclass of PathNode.
 * 
 * @author Etienne Tremblay, gunterm
 */
public abstract class PathNodeFigure extends Figure {

    // default sizes
    protected static final int DEFAULT_HEIGHT = 24;
    protected static final int DEFAULT_WIDTH = 24;

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

    // is the point traversed by a scenario.
    protected boolean traversed;

    // is the path node the border of a pointcut expression
    protected boolean isPointcutBorder;

    protected XYLayout xylayout;
    
    // @author: nikiforov
    // use to highlight path by user color
    protected Color userColor;

    /**
     * Creates the figure and initializes anchors.
     * 
     */
    public PathNodeFigure() {
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
     * What to do when the PathNode is hovered over. Default implementation makes background color gray instead of white.
     * 
     * @param hover
     *            is being hovered?
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        setColors();
    }

    /**
     * What to do when the PathNode is selected. Default implementation makes the background color blue instead of white.
     * 
     * @param selected
     *            The selected to set.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;

        setColors();
    }

    public void setTraversed(boolean traversed) {
        this.traversed = traversed;

        setColors();

    }

    /**
     * sets the color scheme to use depending on whether the path node is used as a border on a pointcut map or not (only selected subclasses of
     * {@link PathNodeFigure} will override setColors() so that isPointcutBorder is taken into account)
     * 
     * @param isPointcutBorder
     *            is set to true if the path node is and false if the path node is not the border for a pointcut expression
     */
    public void setIsPointcutBorder(boolean isPointcutBorder) {
        this.isPointcutBorder = isPointcutBorder;
        setColors();
    }

    protected void setColors() {
    	 if(userColor!=null){
             // @author: nikiforov
             // highlight PathNode by user color
             setForegroundColor(userColor);
             setColor(userColor);
         }else if (selected) {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            setForegroundColor(ColorManager.TRAVERSAL);
            setColor(ColorManager.TRAVERSAL);
        } else {
            setForegroundColor(ColorManager.LINE);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        }
    }
    
    /**
     * @author: nikiforov
     * Set user color to highlight connection
     */
    public void setUserColor(Color userColor){
    	this.userColor = userColor;
    }
    
    /**
     * @author: nikiforov
     * Reset user color to remove user highlighting
     */
    public void resetUserColor(){
    	this.userColor = null;
    }
    
}