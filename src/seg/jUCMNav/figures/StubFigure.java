package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.SimpleTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import seg.jUCMNav.Messages;

/**
 * Figure for stubs.
 * 
 * @author Etienne Tremblay, gunterm
 */
public class StubFigure extends PathNodeFigure {
    // is of a larger size.
    private static final int DEFAULT_HEIGHT = 34;
    private static final int DEFAULT_WIDTH = 34;

    /**
     * Overriden to allow automatic label placement.
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    // the lozenge.
    private Polygon mainFigure;
   // the letter(s) indicating the stub type, displayed inside the stub
    private TextFlow stubTypeText;
    private FlowPage flowPage;
    
    /**
    * Creates the stub's figure. 
     */
    public StubFigure() {
        super();
    }

    /**
     * Is a lozenge, dotted if dynamic, straight line otherwise. There may be text inside of the figure. 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        mainFigure = new Polygon();
        PointList edges = new PointList();
        preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        edges.addPoint(DEFAULT_WIDTH / 2, 1);
        edges.addPoint(1, DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT - 1);
        edges.addPoint(DEFAULT_WIDTH - 1, DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, 1);
        mainFigure.setLineWidth(2);
        mainFigure.setPoints(edges);
        mainFigure.setBackgroundColor(ColorManager.FILL);
        mainFigure.setFill(true);
        add(mainFigure);
        // create the text inside the main figure
        flowPage = new FlowPage();
        stubTypeText = new TextFlow();
        stubTypeText.setLayoutManager(new SimpleTextLayout(stubTypeText));
        // TODO CONCERNS: should use default font?
        stubTypeText.setFont(new Font(null, "Verdana", 15, 0)); //$NON-NLS-1$
        stubTypeText.setText(""); //$NON-NLS-1$
        flowPage.add(stubTypeText);
        // TODO CONCERNS: depends on font size!
        flowPage.setBounds(new Rectangle(DEFAULT_WIDTH/2-5, DEFAULT_HEIGHT/2-12, 20,20));
        add(flowPage);
    }

    /**
     * Returns the lozenge.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#getFigure()
     */
    public Figure getFigure() {
        return mainFigure;
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(mainFigure);
        outgoingAnchor = new ChopboxAnchor(mainFigure);
    }

    /**
     * Updates the appearance of a stub depending on its type (dynamic/static, pointcut)
     * @param dynamic indicates whether the stub is dynamic (true) or static (false)
     * @param pointcut indicates whether the stub is a pointcut stub (true) or not (false)
     */
    public void setStubType(boolean dynamic, boolean pointcut) {
        if (pointcut)
        	stubTypeText.setText(Messages.getString("StubFigure.pointcutStubText")); //$NON-NLS-1$
        else
        	stubTypeText.setText(""); //$NON-NLS-1$
        if (dynamic == true) {
            // Line width to 2 only works under platform 3.0.2 or above: https://bugs.eclipse.org/bugs/show_bug.cgi?id=4853
            // Previously set to 1.
            mainFigure.setLineWidth(2);
            mainFigure.setLineStyle(SWT.LINE_DASH);
        } else {
            mainFigure.setLineWidth(2);
            mainFigure.setLineStyle(SWT.LINE_SOLID);
        }
    }
    
    /**
     * We need to use local coordinates for our edge manipulation.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }
    
	protected void setColors() {
		if (selected) {
    		mainFigure.setForegroundColor(ColorManager.LINE);
    		setColor(ColorManager.SELECTED);
    	}
    	else if (traversed) {
    		mainFigure.setForegroundColor(ColorManager.TRAVERSAL);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);  	
    	}
        else {
    		mainFigure.setForegroundColor(ColorManager.LINE);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);    		
        }
	}    
}