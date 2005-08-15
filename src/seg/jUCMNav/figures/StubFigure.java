package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * Figure for stubs.
 * 
 * @author Etienne Tremblay
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

    // if dynamic, used dotted line.
    private boolean dynamic = false;

    // the lozenge.
    private Polygon mainFigure;

    /**
     * @param dynamic
     *            is this stub dynamic? if so, use a dotted line.
     */
    public StubFigure(boolean dynamic) {
        super();
        this.dynamic = dynamic;
    }

    /**
     * Is a lozenge, dotted if dynamic, straight line otherwise.
     * 
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
        mainFigure.setBackgroundColor(new Color(null, 255, 255, 255));
        mainFigure.setFill(true);
        add(mainFigure);
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
     * Set if is dynamic; we the state changes, we must update its line style.
     * 
     * @param dynamic
     */
    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
        if (dynamic == true) {
            /*
             * Line width to 2 only works under platform 3.0.2 or above: https://bugs.eclipse.org/bugs/show_bug.cgi?id=4853
             * 
             * Previously set to 1.
             */
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
}