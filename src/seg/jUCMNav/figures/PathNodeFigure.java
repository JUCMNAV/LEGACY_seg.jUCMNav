package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * Created on 2005-01-30
 * 
 * This is the figure representing a PathNode.  Extend this class for the figures representing the subclass of PathNode.
 * 
 * @author Etienne Tremblay
 */
public abstract class PathNodeFigure extends Figure {
	
	protected static final int DEFAULT_WIDTH = 24;
	protected static final int DEFAULT_HEIGHT = 24;
	
	protected ConnectionAnchor incomingAnchor;
	protected ConnectionAnchor outgoingAnchor;
	protected boolean selected;
	protected XYLayout layout;
	protected Dimension preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	protected boolean hover;

	public PathNodeFigure(){
		super();
		layout = new XYLayout();
		this.setLayoutManager(layout);
		
		createFigure();
		
		initAnchor();
	}
	
	/**
	 * Initialize all the figures composing this figure.
	 */
	protected abstract void createFigure();

	/**
	 * Initialize the anchors to this figure.
	 */
	protected abstract void initAnchor();
	
	
	/**
	 * This return the connection anchor for an incoming connection.
	 * @return The connecction anchor
	 */
	public ConnectionAnchor getSourceConnectionAnchor(){
		return outgoingAnchor;
	}
	
	/**
	 * This return the connection anchor for an outgoing connection.
	 * @return The connecction anchor
	 */
	public ConnectionAnchor getTargetConnectionAnchor(){
		return incomingAnchor;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.IFigure#getMinimumSize(int, int)
	 */
	public Dimension getMinimumSize(int wHint, int hHint) {
		return getPreferredSize();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		return preferredSize;
	}
	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected The selected to set.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		
		if(selected == true)
			setColor(new Color(null, 0, 102, 204));
		else
			setColor(new Color(null, 255, 255, 255));
	}
	
	public void setHover(boolean hover){
		this.hover = hover;
		
		if(selected == false){
			if(hover)
				setColor(new Color(null, 230, 230, 230));
			else
				setColor(new Color(null, 255, 255, 255));
		}
	}
	
	public void setColor(Color bg) {
		getFigure().setBackgroundColor(bg);
	}
	
	public Figure getFigure(){
		return this;
	}
	
	/**
	 * @return Returns the default dimension.
	 */
	public static Dimension getDefaultDimension() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
