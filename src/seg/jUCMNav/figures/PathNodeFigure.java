/*
 * Created on 2005-01-30
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author Etienne Tremblay
 *
 */
public abstract class PathNodeFigure extends Figure {
	
	protected ConnectionAnchor incomingAnchor;
	protected ConnectionAnchor outgoingAnchor;
	protected boolean selected;
	protected XYLayout layout;
	protected Dimension preferedSize = new Dimension(24, 24);

	public PathNodeFigure(){
		super();
		layout = new XYLayout();
		this.setLayoutManager(layout);
		
		createFigure();
		
		initAnchor();
	}
	
	protected abstract void createFigure();
	
	protected abstract void initAnchor();
	
	public ConnectionAnchor getSourceConnectionAnchor(){
		return outgoingAnchor;
	}
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
		return preferedSize;
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
