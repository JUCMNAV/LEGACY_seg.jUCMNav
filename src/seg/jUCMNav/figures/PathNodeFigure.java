package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Created on 2005-01-30
 * 
 * This is the figure representing a PathNode.  Extend this class for the figures representing the subclass of PathNode.
 * 
 * @author Etienne Tremblay
 */
public abstract class PathNodeFigure extends Figure {
	
	protected ConnectionAnchor incomingAnchor;
	protected ConnectionAnchor outgoingAnchor;
	protected boolean selected;
	protected XYLayout layout;
	protected Dimension preferredSize = new Dimension(24, 24);

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
	}
}
