/*
 * Created on 2005-01-30
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Etienne Tremblay
 *
 */
public class NodeFigure extends Figure {
	
	protected ConnectionAnchor incomingAnchor;
	protected ConnectionAnchor outgoingAnchor;
	protected boolean selected;
	protected XYLayout layout;
	private Ellipse ellipse;
	protected Dimension preferedSize = new Dimension(24, 24);

	public NodeFigure(){
		super();
		layout = new XYLayout();
		this.setLayoutManager(layout);
		
		createFigure();
		
		initAnchor();
	}
	
	protected void createFigure(){
		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(preferedSize.width/4,preferedSize.height/4, 12,12));
		add(ellipse);
	}
	
	protected void initAnchor(){
		incomingAnchor = new EllipseAnchor(ellipse);
		outgoingAnchor = new EllipseAnchor(ellipse);
	}
	
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
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
//		Rectangle rec = this.getBounds();
//		if(selected)
//			graphics.setBackgroundColor(this.getBackgroundColor());
//		else
//			graphics.setBackgroundColor(new Color(null, 255, 255, 255));
//		graphics.fillOval(6+rec.x, 6+rec.y, 12, 12);
//		graphics.setLineWidth(2);
//		graphics.setBackgroundColor(new Color(null, 0, 0, 0));
//		graphics.drawOval(6+rec.x, 6+rec.y, 12, 12);
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
