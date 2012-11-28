package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Polyline;
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

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This figure represent a StartPoint and Waiting Place!
 * 
 * @author Etienne Tremblay, jkealey, gunterm
 */
public class StartPointFigure extends PathNodeFigure {
    private Ellipse ellipse;
    private FlowPage flowPage;
    private TextFlow stubTypeText;
    private boolean isFailure = false;
    private boolean isAbort = false;
    private Polyline lightning;
    private Polyline bar;

    /**
     * An ellipse that fills 2/3 of the area.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {

        int width = preferredSize.width;
        int height = preferredSize.height;

        ellipse = new Ellipse();
        ellipse.setBounds(new Rectangle(13, 13, 16, 16));
        ellipse.setBackgroundColor(ColorManager.LINE);
        ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        
        add(ellipse);

        // create the text inside the main figure
        flowPage = new FlowPage();
        stubTypeText = new TextFlow();
        stubTypeText.setLayoutManager(new SimpleTextLayout(stubTypeText));
        // TODO CONCERNS: should use default font?
        stubTypeText.setFont(new Font(null, "Verdana", 12, SWT.BOLD)); //$NON-NLS-1$
        stubTypeText.setText("F"); //$NON-NLS-1$
        stubTypeText.setForegroundColor(ColorManager.WHITE);
        flowPage.add(stubTypeText);
        // TODO CONCERNS: depends on font size!
        flowPage.setBounds(new Rectangle(16, 12, 20, 20));
        flowPage.setVisible(false);

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
        lightning.setVisible(false);
        
        add(lightning);
        
        bar = new Polyline();
        bar.addPoint(new Point(15, 15));
        bar.addPoint(new Point(27, 27));
        bar.setLineWidth(3);
        bar.setVisible(false);
        bar.setForegroundColor(ColorManager.WHITE);
        
        add(bar);
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(ellipse);
        outgoingAnchor = new EllipseAnchor(ellipse);
    }

    public void setColor(Color bg) {
        super.setColor(bg);
        ellipse.setBackgroundColor(bg);
    }

    /**
     * Makes it larger on hover.
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover) {
            ellipse.setLocation(new Point(13 - 2, 13 - 2));
            ellipse.setSize(16 + 4, 16 + 4);
            
            lightning.setStart(new Point(23+2, 27+2));
        } else {
            ellipse.setLocation(new Point(13, 13));
            ellipse.setSize(16, 16);
            
            lightning.setStart(new Point(23, 27));
        }
    }

    /**
     * the color of a start point depends on whether it is selected, traversed, or the border of a pointcut expression
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#setColors()
     */
    protected void setColors() {
   	 	if(userColor!=null){
   	 		// @author: nikiforov
   	 		//highlight PathNode by user color
   	 		setForegroundColor(userColor);
   	 		setColor(userColor);
   	 	}else if (selected) {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            setForegroundColor(ColorManager.TRAVERSAL);
            setColor(ColorManager.TRAVERSAL);
        } else if (isPointcutBorder) {
            setForegroundColor(ColorManager.POINTCUTBORDER);
            setColor(ColorManager.POINTCUTBORDER);
        } else {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.LINE);
        }
    }

    public void setType(int failureKind, boolean isLocal) {
        
        if(isLocal) {
            ellipse.setForegroundColor(ColorManager.DARKGRAY);
            ellipse.setLineWidth(2);
            bar.setVisible(true);
        } else {
            ellipse.setForegroundColor(ColorManager.LINE);
            ellipse.setLineWidth(1);
            bar.setVisible(false);
        }

        switch (failureKind) {
        case 0: // Normal startpoint
            this.isFailure = false;
            this.isAbort = false;
            break;
        case 1: // Failure point
            this.isFailure = true;
            this.isAbort = false;
            break;
        default: // Failure Abort point
            this.isFailure = true;
            this.isAbort = true;
            break;
        }
        flowPage.setVisible(isFailure);
        lightning.setVisible(isAbort);
    }

    /**
     * We need local coordinates when resizing
     * 
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    protected boolean useLocalCoordinates() {

        return true;
    }

    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(42, 42);
    }
}