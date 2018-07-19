package seg.jUCMNav.figures;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.SimpleTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

public class AsdTextEditor  extends Shape implements LabelElementFigure
{
	
	// default sizes
	protected static final int DEFAULT_HEIGHT = 45;
	protected static final int DEFAULT_WIDTH = 90;
	protected ConnectionAnchor anchor;
	private Ellipse ellipse;
	private Polyline lightning;
	protected TextFlow stubTypeText;
	protected Dimension preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	protected FlowPage flowPage;
	protected static final int LABEL_PADDING_X = 15;
	protected static final int LABEL_PADDING_Y = 8;
	protected boolean hover;
	private Polyline bar;

	// is the figure in selected state
	protected boolean selected;

	// automatically resize when changing text.
	protected boolean autoResize;

	protected XYLayout xylayout;



	public AsdTextEditor()
	{
		super();
		xylayout = new XYLayout();
		this.setLayoutManager(xylayout);

		createFigure();

		initAnchor();
	}

	public void setColor(Color bg) {
		getFigure().setBackgroundColor(bg);
	}

	public Figure getFigure() {
		return this;
	}
	protected void createFigure() {

		int width = preferredSize.width;
		int height = preferredSize.height;

		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(13, 13, 16, 16));
		ellipse.setBackgroundColor(ColorManager.LINE);
		ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());


		ellipse.setForegroundColor(ColorManager.DARKGRAY);
		ellipse.setLineWidth(2);
		

		add(ellipse);

		// create the text inside the main figure
		flowPage = new FlowPage();
		stubTypeText = new TextFlow();
		 flowPage.setHorizontalAligment(PositionConstants.LEFT);
		stubTypeText.setLayoutManager(new SimpleTextLayout(stubTypeText));
		// TODO CONCERNS: should use default font?
		stubTypeText.setFont(new Font(null, "Verdana", 12, SWT.BOLD)); //$NON-NLS-1$
		stubTypeText.setText("F"); //$NON-NLS-1$
		stubTypeText.setForegroundColor(ColorManager.WHITE);
		flowPage.add(stubTypeText);
		// TODO CONCERNS: depends on font size!
		flowPage.setBounds(new Rectangle(16, 12, 20, 20));
		flowPage.setVisible(true);
		

		add(flowPage);

		// The lightning for an abort failure point
		PointList pts = new PointList();
		pts.addPoint(23, 27);
		pts.addPoint(27, 33);
		pts.addPoint(20, 32);
		pts.addPoint(28, 42);
		pts.addPoint(28, 37);
		pts.addPoint(28, 42);
		pts.addPoint(23, 41);

		lightning = new Polyline();
		lightning.setLineWidth(2);
		lightning.setAntialias(GeneralPreferencePage.getAntialiasingPref());
		lightning.setPoints(pts);
		lightning.setVisible(true);

		add(lightning);

		bar = new Polyline();
		bar.addPoint(new Point(15, 15));
		bar.addPoint(new Point(27, 27));
		bar.setLineWidth(3);
		bar.setVisible(true);
		bar.setForegroundColor(ColorManager.WHITE);

		add(bar);
	}
	
	  protected boolean useLocalCoordinates() {

	        return true;
	    }

	  
	protected void initAnchor() {
		anchor = new ChopboxAnchor(this);
	}
	public static Dimension getDefaultDimension() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	public void setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
	}

	protected void outlineShape(Graphics graphics) {
//		Rectangle r = getBounds().getCopy();
//
//		r.x += getLineWidth() / 2;
//		r.y += getLineWidth() / 2;
//		r.width -= getLineWidth();
//		r.height -= getLineWidth();
		Point location= new Point(ASDiagramBackgroundImage.toolx,ASDiagramBackgroundImage.tooly);
		Dimension dim= new Dimension(50,50);
		Rectangle bounds = new Rectangle(location, dim);

		graphics.setLineStyle(SWT.LINE_DASH);
		int[] dashpattern = { 2, 2 };
		graphics.setLineDash(dashpattern);
		graphics.drawOval(bounds);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.figures.GrlNodeFigure#fillShape(org.eclipse.draw2d.Graphics)
	 */
	protected void fillShape(Graphics graphics) {
//		Rectangle r = getBounds().getCopy();
//
//		r.x += getLineWidth() / 2;
//		r.y += getLineWidth() / 2;
//		r.width -= getLineWidth();
//		r.height -= getLineWidth();
		Point location= new Point(ASDiagramBackgroundImage.toolx,ASDiagramBackgroundImage.tooly);
		Dimension dim= new Dimension(50,50);
		Rectangle bounds = new Rectangle(location, dim);

		graphics.setLineStyle(SWT.LINE_DASH);
		int[] dashpattern = { 2, 2 };
		graphics.setLineDash(dashpattern);
		graphics.fillOval(bounds);

	}

	protected boolean shouldHideInnerText()
	{
		return false;
	}

	@Override
	public String getEditableText() {
		return UrnMetadata.removeStereotypes(stubTypeText.getText());

	}

	@Override
	public void setEditableText(String newText) {
		// TODO Auto-generated method stub

	}





}
