/*
 * Created on 2005-01-30
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * @author Etienne Tremblay
 *
 */
public class NodeFigure extends Ellipse {
	
	protected EllipseAnchor incomingAnchor;
	protected EllipseAnchor outgoingAnchor;
	protected boolean selected;

	public NodeFigure(){
		super();
		incomingAnchor = new EllipseAnchor(this);
		outgoingAnchor = new EllipseAnchor(this);
	}
	
	public EllipseAnchor getSourceConnectionAnchor(){
		return outgoingAnchor;
	}
	public EllipseAnchor getTargetConnectionAnchor(){
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
		return new Dimension(24, 24);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics graphics) {
//		super.paintFigure(graphics);
		Rectangle rec = this.getBounds();
		if(selected)
			graphics.setBackgroundColor(this.getBackgroundColor());
		else
			graphics.setBackgroundColor(new Color(null, 255, 255, 255));
		graphics.fillOval(6+rec.x, 6+rec.y, 12, 12);
		graphics.setLineWidth(2);
		graphics.setBackgroundColor(new Color(null, 0, 0, 0));
		graphics.drawOval(6+rec.x, 6+rec.y, 12, 12);
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
