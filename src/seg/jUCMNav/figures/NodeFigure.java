/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeFigure extends Ellipse {
	
	protected EllipseAnchor incomingAnchor;
	protected EllipseAnchor outgoingAnchor;
//	protected Label label;
//	protected XYLayout layout;

	public NodeFigure(){
		super();
//		layout = new XYLayout();
//		this.setLayoutManager(layout);
//		this.setBackgroundColor(new Color(null, 0, 0, 0));
		
		incomingAnchor = new EllipseAnchor(this);
		outgoingAnchor = new EllipseAnchor(this);
//		label = new Label("");
//		label.setForegroundColor(new Color(null, 255, 255, 255));
//		add(label);
//		label.setVisible(false);
	}
	
	public void setId(String id){
//		label.setText(id);
	}
	
//	public void validate(){
//		int diam = Math.max(label.getSize().width, label.getSize().height) + 2;
//		int width = diam - label.getSize().width;
//		width = width/2;
//		int height = diam - label.getSize().height;
//		height = height/2;
//		layout.setConstraint(label, new Rectangle(width,height,-1,-1));
//		super.validate();
//	}
	
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
//		int diam = Math.max(label.getSize().width, label.getSize().height) + 2;
		return new Dimension(12, 12);
	}
}
