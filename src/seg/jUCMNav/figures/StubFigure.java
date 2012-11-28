package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.SimpleTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import seg.jUCMNav.Messages;
import seg.jUCMNav.figures.util.TransformationHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Figure for stubs.
 * 
 * @author Etienne Tremblay, gunterm
 */
public class StubFigure extends PathNodeFigure implements IRotateable {
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

    private FlowPage blockPage;

    private FlowPage replicationPage;
    private Polyline line1;
    private Polyline line2;
    private FreeformLayer xPanel;
    
    private PointList edges; // Point list for the mainFigure when not an Aspect Marker
    private PointList aspectEdges; // Point list for the mainFigure when is Aspect Marker

    // Related to the type of the stub
    private int aspect = 0;
    private int pointcutKind;
    
    private FreeformLayer exitPanel; // Contains the figures for an Exit Aspect Marker exept the lozange
    private FreeformLayer entrancePanel;  // Contains the figures for an Entrance Aspect Marker exept the lozange
    private FreeformLayer condPanel; // Contains the figures for a Conditional Aspect Marker exept the lozange
    
    private Polygon lineEnt; // White triangle for aspect entrance symbol.
    private Polygon rectEntrance; // Black rectangle for aspect entrance symbol.
    private Polygon rectExit; // Black rectangle for aspect exit symbol.
    private Polygon lineExit; // White triangle for aspect exit symbol.
    private Polygon rectCond;// Black rectangle for aspect conditional symbol.

    // All point list of figures so that they can easily be rotated afterwards
    private PointList rectEntPoints;
    private PointList rectExitPoints;
    private PointList lineEntPoints;
    private PointList lineExitPoints;
    private PointList rectCondPoints;
    
    private double angle; // Current rotated angle

    /**
     * Creates the stub's figure.
     */
    public StubFigure() {
        super();

    }

    /**
     * Is a lozenge, dotted if dynamic, straight line otherwise. There may be text inside of the figure.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {

        exitPanel = new FreeformLayer();
        exitPanel.setBounds(new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));

        rectEntrance = new Polygon();
        rectEntPoints = new PointList();
        rectEntPoints.addPoint(2, 8);
        rectEntPoints.addPoint(2, 8+17);
        rectEntPoints.addPoint(2+2, 8+17);
        rectEntPoints.addPoint(2+2, 8);
        rectEntPoints.addPoint(2, 8);
        rectEntrance.setFill(true);
        rectEntrance.setBackgroundColor(ColorManager.LINE);
        rectEntrance.setPoints(rectEntPoints);

        lineEnt = new Polygon();
        lineEntPoints = new PointList();
        lineEntPoints.addPoint(new Point(2, 8));
        lineEntPoints.addPoint(new Point(10, 16));
        lineEntPoints.addPoint(new Point(2, 24));
        lineEntPoints.addPoint(new Point(2, 8));
        lineEnt.setPoints(lineEntPoints);
        lineEnt.setFill(true);
        lineEnt.setBackgroundColor(ColorManager.WHITE);

        exitPanel.add(lineEnt);
        exitPanel.add(rectEntrance);
        exitPanel.setVisible(false);

        add(exitPanel);

        entrancePanel = new FreeformLayer();
        entrancePanel.setBounds(new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));

        rectExit = new Polygon();
        rectExitPoints = new PointList();
        rectExitPoints.addPoint(DEFAULT_WIDTH - 10, 8);
        rectExitPoints.addPoint(DEFAULT_WIDTH - 10, 8+17);
        rectExitPoints.addPoint(DEFAULT_WIDTH - 10+2, 8+17);
        rectExitPoints.addPoint(DEFAULT_WIDTH - 10+2, 8);
        rectExitPoints.addPoint(DEFAULT_WIDTH - 10, 8);
        rectExit.setFill(true);
        rectExit.setBackgroundColor(ColorManager.LINE);
        rectExit.setPoints(rectExitPoints);

        lineExit = new Polygon();
        lineExitPoints = new PointList();
        lineExitPoints.addPoint(new Point(DEFAULT_WIDTH - 8, 8));
        lineExitPoints.addPoint(new Point(DEFAULT_WIDTH, 16));
        lineExitPoints.addPoint(new Point(DEFAULT_WIDTH - 8, 24));
        lineExitPoints.addPoint(new Point(DEFAULT_WIDTH - 8, 8));
        lineExit.setPoints(lineExitPoints);
        lineExit.setFill(true);
        lineExit.setBackgroundColor(ColorManager.WHITE);

        entrancePanel.add(lineExit);
        entrancePanel.add(rectExit);
        entrancePanel.setVisible(false);

        add(entrancePanel);

        condPanel = new FreeformLayer();
        condPanel.setBounds(new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));

        rectCond = new Polygon();
        rectCondPoints = new PointList();
        rectCondPoints.addPoint(9, DEFAULT_HEIGHT / 2 + DEFAULT_HEIGHT / 4);
        rectCondPoints.addPoint(9, DEFAULT_HEIGHT / 2 + DEFAULT_HEIGHT / 4 + 4);
        rectCondPoints.addPoint(9+17, DEFAULT_HEIGHT / 2 + DEFAULT_HEIGHT / 4+4);
        rectCondPoints.addPoint(9+17, DEFAULT_HEIGHT / 2 + DEFAULT_HEIGHT / 4);
        rectCondPoints.addPoint(9, DEFAULT_HEIGHT / 2 + DEFAULT_HEIGHT / 4);
        rectCond.setPoints(rectCondPoints);
        rectCond.setFill(true);
        rectCond.setBackgroundColor(ColorManager.LINE);

        condPanel.add(rectCond);
        condPanel.setVisible(false);

        add(condPanel);
        
        preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        mainFigure = new Polygon();
        edges = new PointList();
        edges.addPoint(DEFAULT_WIDTH / 2, 1);
        edges.addPoint(1, DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT - 1);
        edges.addPoint(DEFAULT_WIDTH - 1, DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, 1);
        mainFigure.setLineWidth(2);
        mainFigure.setPoints(edges);
        mainFigure.setBackgroundColor(ColorManager.FILL);
        mainFigure.setFill(true);
        mainFigure.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        
        int width = (DEFAULT_HEIGHT / 4);
        
        aspectEdges = new PointList();
        aspectEdges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2 - width);
        aspectEdges.addPoint(DEFAULT_WIDTH / 2 - width, DEFAULT_HEIGHT / 2);
        aspectEdges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2 + width);
        aspectEdges.addPoint(DEFAULT_WIDTH / 2 + width, DEFAULT_HEIGHT / 2);
        aspectEdges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2 - width);

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
        flowPage.setBounds(new Rectangle(DEFAULT_WIDTH / 2 - 5, DEFAULT_HEIGHT / 2 - 12, 20, 20));
        add(flowPage);

        // create the text block for the small b of blocking inside the main figure
        blockPage = new FlowPage();
        TextFlow stubSubTypeText = new TextFlow();
        stubSubTypeText.setLayoutManager(new SimpleTextLayout(stubSubTypeText));
        // TODO CONCERNS: should use default font?
        stubSubTypeText.setFont(new Font(null, "Verdana", 6, 0)); //$NON-NLS-1$
        stubSubTypeText.setText("B"); //$NON-NLS-1$
        blockPage.add(stubSubTypeText);
        // TODO CONCERNS: depends on font size!
        blockPage.setBounds(new Rectangle(19, 17, 10, 10));

        add(blockPage);

        // create the text block for the small X inside the main figure
        replicationPage = new FlowPage();
        TextFlow stubRepText = new TextFlow();
        stubRepText.setLayoutManager(new SimpleTextLayout(stubRepText));
        // TODO CONCERNS: should use default font?
        stubRepText.setFont(new Font(null, "Verdana", 6, 0)); //$NON-NLS-1$
        stubRepText.setText("X"); //$NON-NLS-1$
        replicationPage.add(stubRepText);
        // TODO CONCERNS: depends on font size!
        replicationPage.setBounds(new Rectangle(19, 7, 10, 10));

        add(replicationPage);

        xPanel = new FreeformLayer();
        xPanel.setBounds(new Rectangle(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4, DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2));
        xPanel.setVisible(true);
        xPanel.setOpaque(false);

        line1 = new Polyline();
        line1.addPoint(new Point(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4));
        line1.addPoint(new Point(DEFAULT_WIDTH / 4 * 3, DEFAULT_HEIGHT / 4 * 3));
        line1.setForegroundColor(ColorManager.RED);
        line1.setLineWidth(3);

        xPanel.add(line1);

        line2 = new Polyline();
        line2.addPoint(new Point(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4 * 3));
        line2.addPoint(new Point(DEFAULT_WIDTH / 4 * 3, DEFAULT_HEIGHT / 4));
        line2.setForegroundColor(ColorManager.RED);
        line2.setLineWidth(3);

        xPanel.add(line2);

        add(xPanel);
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
     * 
     * @param dynamic
     *            indicates whether the stub is dynamic (true) or static (false)
     * @param pointcut
     *            indicates whether the stub is a pointcut stub (true) or not (false)
     */
    public void setStubType(boolean dynamic, int pointcutKind, int aspect, boolean synch, boolean blocking, String repetitionCount) {

        blockPage.setVisible(false);
        replicationPage.setVisible(false);
        xPanel.setVisible(false);

        entrancePanel.setVisible(false);
        exitPanel.setVisible(false);
        condPanel.setVisible(false);

        this.pointcutKind = pointcutKind;
        this.aspect = aspect;

        if (aspect == 0) {
            edges = new PointList();
            edges.addPoint(DEFAULT_WIDTH / 2, 1);
            edges.addPoint(1, DEFAULT_HEIGHT / 2);
            edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT - 1);
            edges.addPoint(DEFAULT_WIDTH - 1, DEFAULT_HEIGHT / 2);
            edges.addPoint(DEFAULT_WIDTH / 2, 1);
            mainFigure.setPoints(edges);

            if (pointcutKind == 1 && dynamic) {
                stubTypeText.setText(Messages.getString("StubFigure.pointcutStubText")); //$NON-NLS-1$
                flowPage.setBounds(new Rectangle(DEFAULT_WIDTH / 2 - 5, DEFAULT_HEIGHT / 2 - 12, 20, 20));
            } else if (pointcutKind == 2 && dynamic) {
                stubTypeText.setText(Messages.getString("StubFigure.pointcutStubText")); //$NON-NLS-1$
                flowPage.setBounds(new Rectangle(DEFAULT_WIDTH / 2 - 5, DEFAULT_HEIGHT / 2 - 12, 20, 20));
                xPanel.setVisible(true);
            } else if (synch && dynamic) {
                stubTypeText.setText("S"); //$NON-NLS-1$
                Integer repCount = new Integer(repetitionCount);

                if (blocking || repCount.intValue() > 1) {
                    stubTypeText.setFont(new Font(null, "Verdana", 14, 0)); //$NON-NLS-1$
                    flowPage.setBounds(new Rectangle(DEFAULT_WIDTH / 2 - 10, DEFAULT_HEIGHT / 2 - 13, 25, 25));

                } else {
                    stubTypeText.setFont(new Font(null, "Verdana", 15, 0)); //$NON-NLS-1$
                    flowPage.setBounds(new Rectangle(DEFAULT_WIDTH / 2 - 7, DEFAULT_HEIGHT / 2 - 14, 25, 25));
                }

                if (blocking)
                    blockPage.setVisible(true);

                if (repCount.intValue() > 1) {
                    replicationPage.setVisible(true);
                } else {

                }
            } else
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
        } else {
            mainFigure.setBackgroundColor(ColorManager.LINE);
            mainFigure.setLineStyle(SWT.LINE_SOLID);

            if (aspect == 2) { // Entrance Aspect marker
                entrancePanel.setVisible(true);
            } else if (aspect == 3) { // Exit Aspect marker
                exitPanel.setVisible(true);
            } else if (aspect == 4) { // Conditional Aspect marker
                condPanel.setVisible(true);
            }
        }
    }

    /**
     * We need to use local coordinates for our edge manipulation.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }

    protected void setColors() {
   	 	if(userColor!=null){
   	 		// @author: nikiforov
   	 		// highlight PathNode by user color
   	 		mainFigure.setBackgroundColor(userColor);
   	 	}else if (selected) {
            mainFigure.setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            mainFigure.setForegroundColor(ColorManager.TRAVERSAL);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        } else {
            mainFigure.setForegroundColor(ColorManager.LINE);
            if (hover)
                setColor(ColorManager.HOVER);
            else {
                if (aspect != 0)
                    setColor(ColorManager.LINE);
                else
                    setColor(ColorManager.FILL);
            }
        }
    }

    public void rotate(double angle) {
        
        // make it always point towards bottom
        if (aspect==4 && Math.cos(angle)>0)
            angle -= Math.PI;
        
        if (aspect != 0) {
            this.angle = angle;
            Point center = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);
            
            PointList newPoints = TransformationHelper.rotatePoints(angle, aspectEdges, center);
            mainFigure.setPoints(newPoints);
            
            // Entrance Figures
           newPoints = TransformationHelper.rotatePoints(angle, rectEntPoints, center);
           rectEntrance.setPoints(newPoints);

           newPoints = TransformationHelper.rotatePoints(angle, lineEntPoints, center);
           lineEnt.setPoints(newPoints);
           
           // Exit Figures
           newPoints = TransformationHelper.rotatePoints(angle, rectExitPoints, center);
           rectExit.setPoints(newPoints);

           newPoints = TransformationHelper.rotatePoints(angle, lineExitPoints, center);
           lineExit.setPoints(newPoints);
           
           // Conditional Figures
           newPoints = TransformationHelper.rotatePoints(angle, rectCondPoints, center);
           rectCond.setPoints(newPoints);
        }
    }
}