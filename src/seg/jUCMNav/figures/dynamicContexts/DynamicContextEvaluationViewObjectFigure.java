package seg.jUCMNav.figures.dynamicContexts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GroupBoxBorder;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IAxisTick;
import org.swtchart.ILineSeries;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.LineStyle;
import org.swtchart.Range;
import org.swtchart.internal.series.LineSeries;

import java.util.List;

import grl.Actor;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts.DynamicContextEvaluationViewObject;
import seg.jUCMNav.figures.ColorManager;
import urncore.URNmodelElement;

/**
 * The figure that represent Overall Evaluation diagram in DynamicContextEvaluationView
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewObjectFigure extends Figure {
	
	protected final static int DEFAULT_TEXT_WIDTH = 400;
    protected final static int DEFAULT_TEXT_HEIGHT = 30;

    protected final static int DEFAULT_BAR_WIDTH = 12;
    protected final static int DEFAULT_BAR_HEIGHT = 20;

    protected final static int LABEL_PADDING_X = 10;
    protected final static int LABEL_PADDING_Y = 11;

    protected final static int BORDER_SHIFT_X = 16;
    protected final static int BORDER_SHIFT_Y = 21;

    private DynamicContextEvaluationViewObject dynViewObject;
    private Color[][] colors;

    protected XYLayout xylayout;

    protected FlowPage[] timepointsFlowPage;
    protected TextFlow[] timepointsTextFlow;

    protected FlowPage[] intElemsFlowPage;
    protected TextFlow[] intElemsTextFlow;

    private Rectangle[][] eltsEvalBar = null;
    private Point[] iconLocations = null;
    private int maxIntEltLength;
    
    private Color[] linkColors = {ColorManager.BLACK, ColorManager.BLUE, ColorManager.YELLOW, ColorManager.PURPLE, ColorManager.RED,
    		new Color(null, StringConverter.asRGB("0,102,51")), new Color(null, StringConverter.asRGB("255,51,255")),
    		new Color(null, StringConverter.asRGB("255,128,0")), new Color(null, StringConverter.asRGB("0,255,255")),
    		new Color(null, StringConverter.asRGB("0,255,128")), new Color(null, StringConverter.asRGB("255,204,255"))};
    
    protected boolean done;
    
    public DynamicContextEvaluationViewObjectFigure(DynamicContextEvaluationViewObject object) {
        this.dynViewObject = object;
        colors = dynViewObject.getColors();
        initializeFigure();
    }

    /**
     * 
     * @return Returns the default dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public Dimension getDefaultDimension() {
    	int width = 0;
		int height = 0;
		if (colors != null) {
			width = (colors[0].length * 20) + 500;
    		height = (colors.length * DEFAULT_BAR_HEIGHT) + DEFAULT_TEXT_HEIGHT +300;
    	} 
    	
        return new Dimension(width, height);
    }

    /**
     * 
     * @return Returns the default text dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultTextDimension() {
        return new Dimension(DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT);
    }

    private void initializeFigure() {
        xylayout = new XYLayout();
        this.setLayoutManager(xylayout);
        this.setBackgroundColor(ColorManager.WHITE);
        this.setSize(getDefaultDimension());
        this.setOpaque(true);
        this.setForegroundColor(ColorManager.BLACK);

        GroupBoxBorder border = new GroupBoxBorder();
        border.setLabel("Overall Evaluation"); //$NON-NLS-1$ //$NON-NLS-2$
        border.setTextColor(ColorManager.BLUE);
        //this.setBorder(border);
        if (colors != null) {
	        timepointsFlowPage = new FlowPage[colors[0].length+1];
	        intElemsFlowPage = new FlowPage[colors.length];
	        
	        timepointsTextFlow = new TextFlow[colors[0].length+1];
	        intElemsTextFlow = new TextFlow[colors.length];
	        
	        for (int i = 0; i < colors[0].length+1; i++) {
	        	timepointsTextFlow[i] = new TextFlow();
	        	timepointsTextFlow[i].setLayoutManager(new ParagraphTextLayout(timepointsTextFlow[i], ParagraphTextLayout.WORD_WRAP_HARD));
	        	timepointsFlowPage[i] = new FlowPage();
	        	timepointsFlowPage[i].add(timepointsTextFlow[i]);
	        	add(timepointsFlowPage[i]);
	        }
	        
	        for (int i = 0; i < colors.length; i++) {
		    	intElemsTextFlow[i] = new TextFlow();
	        	intElemsTextFlow[i].setLayoutManager(new ParagraphTextLayout(intElemsTextFlow[i], ParagraphTextLayout.WORD_WRAP_HARD));
	        	intElemsFlowPage[i] = new FlowPage();
	        	intElemsFlowPage[i].add(intElemsTextFlow[i]);
	        	add(intElemsFlowPage[i]);
	        }  
        }
        
    }

    protected void paintFigure(Graphics graphics) {
    	String[] tpNames = dynViewObject.getTimepointNames();
    	int x = maxIntEltLength + 38;
    	int y = LABEL_PADDING_Y;
    	for (int i = 0; i < colors[0].length; i++) {
    		drawTimepointsLabel(graphics, tpNames[i], x, y);
    		x += DEFAULT_BAR_WIDTH;
    		
    	}
    	
    	for (int i = 0; i < colors.length; i++) {
    		for (int j = 0; j < colors[0].length; j++) {
    			int indEltIndex = dynViewObject.getIndependentIndex();
    			//Do not draw any rectangular bar for undefined actor
    			if (i != indEltIndex)
    				drawEvalBar(graphics, colors[i][j], eltsEvalBar[i][j]);
	    	}
    	}
    	
        
    }
    
    private void drawTimepointsLabel(Graphics graphics, String timepointName, int x, int y) {
    	
    	//graphics.drawString(timepointName, x, y);
    	if (!timepointName.equals("")) {
    		Image image = ImageUtilities.createRotatedImageOfString(timepointName, new Font(null, "Consolas", 8, SWT.BOLD), getForegroundColor(), getBackgroundColor());
	    	graphics.drawImage(image, x, y);
    		image.dispose();
    	}
    	
    }
    
    private void drawEvalBar(Graphics graphics, Color color, Rectangle singleBar) {
        Color evalColor = color;
        graphics.setBackgroundColor(ColorManager.BLACK);
        graphics.setBackgroundColor(evalColor);
        graphics.fillRectangle(singleBar);
            	
    }

    public void setupFigure() {
        //setSubjectText();
    	if (colors != null) {
    		int indEltIndex = dynViewObject.getIndependentIndex();
    		setEvalBarText();
	        calculateFigures();
	        
	        Image img = JUCMNavPlugin.getImage("icons/indicator.gif");
	    	
	    	//Draw buttons
	    	Button[] buttons = new Button[iconLocations.length];
	        for (int j = 0; j < iconLocations.length; j++) {
	        	if (j != indEltIndex) {
					buttons[j] = new Button(img);
			    	buttons[j].setLocation(new Point(iconLocations[j].x, iconLocations[j].y));
			    	buttons[j].setSize(img.getBounds().width+2, img.getBounds().height+2);
			    	buttons[j].setToolTip(new Label("Double Click to visualize in Graph"));
			    	buttons[j].setOpaque(false);
			    	this.add(buttons[j]);
			    	buttons[j].addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent me) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mousePressed(MouseEvent me) {
												
						}
						
						@Override
						public void mouseDoubleClicked(MouseEvent me) {
							for (int j = 0; j < iconLocations.length; j++) {
								if ((me.getLocation().x >= iconLocations[j].x-1 && me.getLocation().x <= iconLocations[j].x + 17) && 
										(me.getLocation().y >= iconLocations[j].y-1 && me.getLocation().y <= iconLocations[j].y + 17)) {
									calculateChart(j);
									break;
								}
							}
							
						}
					});
			    	
		        }
	        }
    	}
	    
    }
    
    public void calculateFigures() {
    	
    	//Store the icon locations
    	iconLocations = new Point[colors.length];
    	
    	// calculate the evaluation bar
    	eltsEvalBar = new Rectangle[colors.length][colors[0].length];
    	
    	int y = 0;
    	for (int i = 0; i < eltsEvalBar.length; i++) {
    		if (i == 0)
	        	y = intElemsFlowPage[0].getBounds().y;
        		//y = timepointsFlowPage[0].getBounds().y + timepointsTextFlow[0].getPreferredSize().height + LABEL_PADDING_Y;

	        else 
	        	y = intElemsFlowPage[i].getBounds().y;
	    	for (int j = 0; j < eltsEvalBar[0].length; j++) {
	    		eltsEvalBar[i][j] = new Rectangle();
	    		eltsEvalBar[i][j].height = DEFAULT_BAR_HEIGHT;
	    		eltsEvalBar[i][j].width = DEFAULT_BAR_WIDTH;
			    if (j == 0)
			    	eltsEvalBar[i][j].x = maxIntEltLength + 40;
			    else
			    	eltsEvalBar[i][j].x = eltsEvalBar[i][j-1].x + DEFAULT_BAR_WIDTH;
			        
			    //evalBar[0][i].y = 30;
			    eltsEvalBar[i][j].y = y;
	    	}
	    	iconLocations[i] = new Point();
	    	iconLocations[i].y = y;
	    	iconLocations[i].x = JUCMNavPlugin.getImage("icons/indicator.gif").getBounds().width + intElemsFlowPage[i].getBounds().x -15;
	    	
    	}
    }

   public void setEvalBarText() {
        // draw text boxes
    	ArrayList<URNmodelElement> allElts = dynViewObject.getAllElements();
    	ArrayList<String> hierarchyInfo = dynViewObject.getHierarchyInfo();
    	int height = 0;
    	maxIntEltLength = 0;
    	int moveToRight = 2;
    	for (int i = 0; i < colors.length; i++) {
    		if (i == 0) {
    			intElemsTextFlow[i].setText("System"); //$NON-NLS-1$ //$NON-NLS-2$
	        	height = (DEFAULT_BAR_HEIGHT * 3) + LABEL_PADDING_Y + 6;
	        	setEvalTextFlowPageConstraint(intElemsFlowPage[i], LABEL_PADDING_X * moveToRight, height);
    		}
	        else {
	        	
	        	//Undefined actor for elt refs, which have no parent actor
	        	if (allElts.get(i) == null) {
	        		intElemsTextFlow[i].setText("Undefined Actor"); //$NON-NLS-1$ //$NON-NLS-2$
	        	} else {
		        	intElemsTextFlow[i].setText(allElts.get(i).getName()); //$NON-NLS-1$ //$NON-NLS-2$
		        }
	        	
	        	if (allElts.get(i) instanceof Actor || allElts.get(i) == null) {
	        		height = height + DEFAULT_BAR_HEIGHT + (LABEL_PADDING_Y/2);
	        		
	        		//If undefined actor
	        		if (hierarchyInfo.get(i) == null) {
	        			moveToRight = 3;
	        			setEvalTextFlowPageConstraint(intElemsFlowPage[i], (LABEL_PADDING_X * moveToRight) - (LABEL_PADDING_X/2), height);
	        			moveToRight += 1;
	        		} 
	        		//If parent actor
	        		else if (hierarchyInfo.get(i).equals("parent")) { 
	        			moveToRight = 3;
	        			setEvalTextFlowPageConstraint(intElemsFlowPage[i], LABEL_PADDING_X * moveToRight, height);
	        			moveToRight += 1;
	        		} else {
	        			if (!hierarchyInfo.get(i).contains("_")) {
	        				moveToRight = 4;
	        				setEvalTextFlowPageConstraint(intElemsFlowPage[i], LABEL_PADDING_X * moveToRight, height);
		        			moveToRight += 1;
	        			} else {
	        				//Number of underscores to get depth in hierarchy
	        				String name = hierarchyInfo.get(i);
	        				int underScores = name.length() - name.replace("_", "").length();
	        				moveToRight = moveToRight + (underScores - 1);
	        				setEvalTextFlowPageConstraint(intElemsFlowPage[i], LABEL_PADDING_X * moveToRight, height);
	        				moveToRight += 1;
	        			}
	        		}
	        	} else {
	    			height = height + DEFAULT_BAR_HEIGHT;
			        setEvalTextFlowPageConstraint(intElemsFlowPage[i], LABEL_PADDING_X * moveToRight, height);
			        
			    }
	        }
    		
    		int temp = intElemsFlowPage[i].getBounds().x + intElemsTextFlow[i].getSize().width;
	        
	        //Get the location and width of the element name with max length
	        if (temp > maxIntEltLength)
	        	maxIntEltLength = temp;
    		
    	}
    }

    private void setEvalTextFlowPageConstraint(FlowPage flowPage, int x, int y) {
        flowPage.setFont(JFaceResources.getFontRegistry().get(JFaceResources.DEFAULT_FONT));
        Dimension dimEditableLabel = flowPage.getPreferredSize().getCopy();

        // Calculate the size of the label and of the figure
        // Max size available for the label
        int width = getDefaultTextDimension().width;
        int height = dimEditableLabel.height;

        int minWidth = flowPage.getPreferredSize(width, 1).width;

        // Loop until we have good dimension for the labels to fit in the node
        while (dimEditableLabel.width > (width * Math.floor((height - 1) / dimEditableLabel.height)) || width < minWidth) {
            height = height + dimEditableLabel.height;
            width = width + 20;

        }
        if (height < (getDefaultTextDimension().height)) {
            height = getDefaultTextDimension().height;
        }
        
        Rectangle r = new Rectangle();
        r.x = x;
        r.y = y;
        r.width = width;
        r.height = height;

        setConstraint(flowPage, r);
        flowPage.setLocation(new Point(r.x, r.y));
    }

    public int getPreferredHeight() {
        return DEFAULT_TEXT_HEIGHT + 10;
    }
    
    
    public void calculateChart(int i) {
    	ArrayList<URNmodelElement> allElts = dynViewObject.getAllElements();
    	
    	if (i == 0) {
    		drawSystemEvaluation();
    	} else {
    	
	    	//Draw chart for evaluation and importance of element i
	    	drawChartEvalImp(allElts.get(i), i);
	    	if (allElts.get(i) instanceof IntentionalElement) {
	    		
	    		IntentionalElement elt = (IntentionalElement) allElts.get(i);
	    		
		    	int size = 0;
		    	int graphCount = 0;
		    	double[][] values = null;
		    	
		    	//Draw charts for contribution links to element i
		    	List<Contribution> contribLinks = dynViewObject.getContributionLinks(i);
		    	size = contribLinks.size();
		    	if (size > 0) {
			    	Map<Integer, ArrayList<ArrayList<Double>>> linkValuesMapContri = dynViewObject.getContriLinkValues();
			    	
			    	//To store values related to each contribution link at every timepoint
			    	values = new double[contribLinks.size()][colors[0].length];
			    	
			    	//Get values for all the destination contribution links for that particular element
			    	for (int j = 0; j < colors[0].length; j++) {
			    		ArrayList<ArrayList<Double>> contriLinkValues = linkValuesMapContri.get(j);
			    		ArrayList<Double> eltContriValues = contriLinkValues.get(i);
			    		for (int k = 0; k < contribLinks.size(); k++) {
			    			values[k][j] = eltContriValues.get(k);
			    		}
			    	}
			    	
			    	size = contribLinks.size();
			    	
			    	//Calculate number of graphs needed
		    		if (size % 10 == 0)
		    			graphCount = size/10;
		    		else
		    			graphCount = (size/10) + 1;
			    	
		    		//Different graphs for each group of 10 links
			    	for (int k = 0; k < graphCount; k++) {
			    		List<Contribution> partContribLinks = null;
			    		double[][] partValues = null;
			    		if (k == graphCount - 1) {
			    			partContribLinks = contribLinks.subList((10 * k), contribLinks.size());
			    			partValues = Arrays.copyOfRange(values, (10 * k), contribLinks.size());
			    		} else {
			    			partContribLinks = contribLinks.subList((10 * k), (10*(k+1)));
			    			partValues = Arrays.copyOfRange(values, (10 * k), (10*(k+1)));
			    		}
			    		
			    		drawContribLinksChart(partContribLinks, partValues, elt, i, k, graphCount);
			    	}
		    	}
		    	
		    	//Draw charts for dependency links to element i
		    	List<Dependency> depLinks = dynViewObject.getDependencyLinks(i);
		    	size = depLinks.size();
		    	if (size > 0) {
			    	Map<Integer, ArrayList<ArrayList<Double>>> linkValuesMapDep = dynViewObject.getDepLinkValues();
			    	values = new double[depLinks.size()][colors[0].length];
			    	
			    	//Get values for all the destination dependency links for that particular element
			    	for (int j = 0; j < colors[0].length; j++) {
			    		ArrayList<ArrayList<Double>> depLinkValues = linkValuesMapDep.get(j);
			    		ArrayList<Double> eltDepValues = depLinkValues.get(i);
			    		for (int k = 0; k < depLinks.size(); k++) {
			    			values[k][j] = eltDepValues.get(k);
			    		}
			    	}
			    	graphCount = 0;
		
			    	//Calculate number of graphs needed
		    		if (size % 10 == 0)
		    			graphCount = size/10;
		    		else
		    			graphCount = (size/10) + 1;
			    	
		    		//Different graphs for each group of 10 links
			    	for (int k = 0; k < graphCount; k++) {
			    		List<Dependency> partDepLinks = null;
			    		double[][] partValues = null;
			    		if (k == graphCount - 1) {
			    			partDepLinks = depLinks.subList((10 * k), depLinks.size());
			    			partValues = Arrays.copyOfRange(values, (10 * k), depLinks.size());
			    		} else {
			    			partDepLinks = depLinks.subList((10 * k), (10*(k+1)));
			    			partValues = Arrays.copyOfRange(values, (10 * k), (10*(k+1)));
			    		}
			    		
			    		drawDepLinksChart(partDepLinks, partValues, elt, i, k, graphCount);
			    	}
		    	}
		    	
		    	//Draw charts for decomposition links to element i
		    	List<Decomposition> decompLinks = dynViewObject.getDecompLinks(i);
		    	size = decompLinks.size();
		    	if (size > 0) {
			    	Map<Integer, ArrayList<ArrayList<Double>>> linkValuesMapDecomp = dynViewObject.getDecompLinkValues();
			    	values = new double[decompLinks.size()][colors[0].length];
			    	
			    	//Get values for all the destination decomposition links for that particular element
			    	for (int j = 0; j < colors[0].length; j++) {
			    		ArrayList<ArrayList<Double>> decompLinkValues = linkValuesMapDecomp.get(j);
			    		ArrayList<Double> eltDecompValues = decompLinkValues.get(i);
			    		for (int k = 0; k < decompLinks.size(); k++) {
			    			values[k][j] = eltDecompValues.get(k);
			    		}
			    	}
			    	size = decompLinks.size();
			    	graphCount = 0;
		    		
		    		//Calculate number of graphs needed
		    		if (size % 10 == 0)
		    			graphCount = size/10;
		    		else
		    			graphCount = (size/10) + 1;
			    	
		    		//Different graphs for each group of 10 links
			    	for (int k = 0; k < graphCount; k++) {
			    		List<Decomposition> partDecompLinks = null;
			    		double[][] partValues = null;
			    		if (k == graphCount - 1) {
			    			partDecompLinks = decompLinks.subList((10 * k), decompLinks.size());
			    			partValues = Arrays.copyOfRange(values, (10 * k), decompLinks.size());
			    		} else {
			    			partDecompLinks = decompLinks.subList((10 * k), (10*(k+1)));
			    			partValues = Arrays.copyOfRange(values, (10 * k), (10*(k+1)));
			    		}
			    		
			    		drawDecompLinksChart(partDecompLinks, partValues, elt, i, k, graphCount);
			    	}
		    	}
	    	}
    	}

    }
    
    private void drawContribLinksChart(List<Contribution> contribLinks, double[][] values, IntentionalElement elt, int i, int graphNo, int totalCount) {
    	int count = 0;
    	Display display = Display.getDefault();
    	Shell shell = new Shell(display);
    	shell.setText("Chart for Contribution Links of Element \"" + elt.getName() + "\"");
        shell.setSize(800, 800);
        shell.setLayout(new FillLayout());
        shell.setImage(JUCMNavPlugin.getImage("icons/Change.gif"));
    	Chart chart = new Chart(shell, SWT.NONE);
    	
    	// set titles
        chart.getTitle().setText("Evaluations by Contribution Links of \"" + elt.getName() + "\"(" + (graphNo + 1) + " out of " + totalCount +")");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Timepoints");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Values");
        ArrayList<LineSeries> symSeries = new ArrayList<LineSeries>();
        ArrayList<LineSeries> series = new ArrayList<LineSeries>();
    	for (Iterator iter = contribLinks.iterator(); iter.hasNext();) {
    		Contribution link = (Contribution) iter.next();
    		
    		drawChartLink(chart, values, count, link, symSeries, series);
            count+= 1;
    	}
    	
    	// adjust the axis range
        Range range = new Range(-110, 110);
        chart.getAxisSet().getYAxis(0).setRange(range);
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dynViewObject.startRange());
        //cStart.set(Calendar.YEAR, 1969);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(dynViewObject.endRange());
        cEnd.add(Calendar.DAY_OF_MONTH, 1);
        //cEnd.set(Calendar.YEAR, 1969);
        Range xRange = new Range(cEnd.getTimeInMillis(),cStart.getTimeInMillis());
        chart.getAxisSet().getXAxis(0).setRange(xRange);
        IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        xTick.setFormat(format);
        xTick.setTickLabelAngle(90);
        xTick.setVisible(true);
        xTick.setTickMarkStepHint(70);
        
        chart.getAxisSet().getXAxis(0).adjustRange();
        shell.open();
    	
    }
    
    private void drawDepLinksChart(List<Dependency> depLinks, double[][] values, IntentionalElement elt, int i, int graphNo, int totalCount) {
    	int count = 0;
    	Display display = Display.getDefault();
    	Shell shell = new Shell(display);
    	shell.setText("Chart for Dependency Links of Element \"" + elt.getName() + "\"");
        shell.setSize(800, 800);
        shell.setLayout(new FillLayout());
        shell.setImage(JUCMNavPlugin.getImage("icons/Change.gif"));
    	Chart chart = new Chart(shell, SWT.NONE);
    	
    	// set titles
        chart.getTitle().setText("Evaluations by Dependency Links of \"" + elt.getName() + "\"(" + (graphNo + 1) + " out of " + totalCount +")");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Timepoints");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Values");
        ArrayList<LineSeries> symSeries = new ArrayList<LineSeries>();
        ArrayList<LineSeries> series = new ArrayList<LineSeries>();
    	for (Iterator iter = depLinks.iterator(); iter.hasNext();) {
    		Dependency link = (Dependency) iter.next();
    		
    		drawChartLink(chart, values, count, link, symSeries, series);
            count+= 1;
    	}
    	
    	// adjust the axis range
        Range range = new Range(-110, 110);
        chart.getAxisSet().getYAxis(0).setRange(range);
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dynViewObject.startRange());
        //cStart.set(Calendar.YEAR, 1969);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(dynViewObject.endRange());
        cEnd.add(Calendar.DAY_OF_MONTH, 1);
        //cEnd.set(Calendar.YEAR, 1969);
        Range xRange = new Range(cEnd.getTimeInMillis(),cStart.getTimeInMillis());
        chart.getAxisSet().getXAxis(0).setRange(xRange);
        IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        xTick.setFormat(format);
        xTick.setTickLabelAngle(90);
        xTick.setVisible(true);
        xTick.setTickMarkStepHint(70);
        
        chart.getAxisSet().getXAxis(0).adjustRange();
        shell.open();
    	
    }
    
    private void drawDecompLinksChart(List<Decomposition> decompLinks, double[][] values, IntentionalElement elt, int i, int graphNo, int totalCount) {
    	int count = 0;
    	Display display = Display.getDefault();
    	Shell shell = new Shell(display);
    	shell.setText("Chart for Decomposition Links of Element \"" + elt.getName() + "\"");
        shell.setSize(800, 800);
        shell.setLayout(new FillLayout());
        shell.setImage(JUCMNavPlugin.getImage("icons/Change.gif"));
    	Chart chart = new Chart(shell, SWT.NONE);
    	
    	// set titles
        chart.getTitle().setText("Evaluations by Decomposition Links of \"" + elt.getName() + "\"(" + (graphNo + 1) + " out of " + totalCount +")");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Timepoints");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Values");
        ArrayList<LineSeries> symSeries = new ArrayList<LineSeries>();
        ArrayList<LineSeries> series = new ArrayList<LineSeries>();
    	for (Iterator iter = decompLinks.iterator(); iter.hasNext();) {
    		Decomposition link = (Decomposition) iter.next();
    		
    		drawChartLink(chart, values, count, link, symSeries, series);
            count+= 1;
    	}
    	double[][] overallDecomp = dynViewObject.getOverallDecompValues();
    	drawChartLink1(chart, overallDecomp, i);
    	// adjust the axis range
        Range range = new Range(-110, 110);
        chart.getAxisSet().getYAxis(0).setRange(range);
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dynViewObject.startRange());
        //cStart.set(Calendar.YEAR, 1969);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(dynViewObject.endRange());
        cEnd.add(Calendar.DAY_OF_MONTH, 1);
        //cEnd.set(Calendar.YEAR, 1969);
        Range xRange = new Range(cEnd.getTimeInMillis(),cStart.getTimeInMillis());
        chart.getAxisSet().getXAxis(0).setRange(xRange);
        IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        xTick.setFormat(format);
        xTick.setTickLabelAngle(90);
        xTick.setVisible(true);
        xTick.setTickMarkStepHint(70);
        
        chart.getAxisSet().getXAxis(0).adjustRange();
        shell.open();
    	
    }
    private void drawChartLink1(Chart chart, double[][] evalLevels, int i) {
    	
    	/*Create line graphs only for the parts that aren't deactivated as well as collect only those values which are at timepoints*/
        //Lists to store only the values at timepoints to show as symbols
        List<Double> updXSeries = new ArrayList<Double>();
        List<Double> updYSeries = new ArrayList<Double>();
        List<Color> symbolColors = new ArrayList<Color>();
        String[] tpNames = dynViewObject.getTimepointNames();
        List<Double> xLineSeries = new ArrayList<Double>();
        List<Double> yLineSeries = new ArrayList<Double>();
        int legendCount = 0;
        for (int j = 0; j < evalLevels[i].length; j++) {
        	
        	//collect only those values which are at timepoints in order to plot as a scatter graph
        	if (!tpNames[j].equals("")) {
        		updXSeries.add((double) j);
        		updYSeries.add(evalLevels[i][j]); 
        		
        		if(colors[i][j].equals(new Color(null, StringConverter.asRGB("169,169,169")))) {
            		symbolColors.add(ColorManager.DARKGRAY);
            	} else
            		symbolColors.add(linkColors[10]);
        	}
        	//if element is deactivated don't draw the graph, else do.
        	if(colors[i][j].equals(new Color(null, StringConverter.asRGB("169,169,169")))) {
        		if (xLineSeries.size() != 0) {
        			double[] xLineSeries1 = new double[xLineSeries.size()];
        	        double[] yLineSeries1 = new double[yLineSeries.size()];
        	        for (int k = 0; k < xLineSeries.size(); k++) {
        	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
        	        	yLineSeries1[k] = ((Double) (yLineSeries.get(k))).doubleValue();
        	        }
        	        createLineGraph(chart, xLineSeries1, yLineSeries1, null, legendCount);
        	        legendCount += 1;
        	        xLineSeries.clear();
        	        yLineSeries.clear();
        		}
        		
        	} else {
        		xLineSeries.add((double) j);
	        	yLineSeries.add(evalLevels[i][j]); 
        	}
        	
        	if (j == evalLevels[i].length-1 && xLineSeries.size() != 0) {
        		double[] xLineSeries1 = new double[xLineSeries.size()];
    	        double[] yLineSeries1 = new double[yLineSeries.size()];
    	        for (int k = 0; k < xLineSeries.size(); k++) {
    	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
    	        	yLineSeries1[k] = ((Double) (yLineSeries.get(k))).doubleValue();
    	        }
    	        
    	        createLineGraph(chart, xLineSeries1, yLineSeries1, null, legendCount);
    	        
    	        xLineSeries.clear();
    	        yLineSeries.clear();
        	}
        	
        }
        double[] xSeries1 = new double[updXSeries.size()];
        double[] ySeries1 = new double[updYSeries.size()];
        Color[] symColors = new Color[symbolColors.size()];
        for (int j = 0; j < updXSeries.size(); j++) {
        	xSeries1[j] = ((Double) (updXSeries.get(j))).doubleValue();
        	ySeries1[j] = ((Double) (updYSeries.get(j))).doubleValue();
        	symColors[j] = (Color) symbolColors.get(j);
        }
        
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        Date[] series = new Date[xSeries1.length];
        for (int j = 0; j < xSeries1.length; j++) {
        	cStart.add(Calendar.DAY_OF_MONTH, (int) xSeries1[j]);
        	series[j] = cStart.getTime();
        	cStart.setTime(start);
        }
        
        //Plot symbols at timepoints for Evaluation
        ILineSeries symSeries1 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Evaluation Scatter");
        symSeries1.setVisibleInLegend(false);
        symSeries1.setXDateSeries(series);
        
        //symSeries1.setXSeries(xSeries1);
        symSeries1.setYSeries(ySeries1);
        symSeries1.setLineStyle(LineStyle.NONE);
        symSeries1.setSymbolType(PlotSymbolType.CROSS);
        symSeries1.setSymbolColors(symColors);
        symSeries1.setLineWidth(2);
        symSeries1.setXAxisId(0);
    }
    
    private void drawChartLink(Chart chart, double[][] values, int count, ElementLink link, ArrayList<LineSeries> symSeries, ArrayList<LineSeries> series) {
    	/*Create line graphs only for the parts that aren't deactivated as well as collect only those values which are at timepoints*/
        //Lists to store only the values at timepoints to show as symbols
        List<Double> updXSeries = new ArrayList<Double>();
        List<Double> updYSeries = new ArrayList<Double>();
        List<Color> symbolColors = new ArrayList<Color>();
        String[] tpNames = dynViewObject.getTimepointNames();
        List<Double> xLineSeries = new ArrayList<Double>();
        List<Double> yLineSeries = new ArrayList<Double>();
        int legendCount = 0;
        
        for (int j = 0; j < values[count].length; j++) {
        	
        	//collect only those values which are at timepoints in order to plot as a scatter graph
        	if (!tpNames[j].equals("")) {
        		updXSeries.add((double) j);
        		updYSeries.add(values[count][j]);
        		
        		if (values[count][j] == -120) {
            		symbolColors.add(ColorManager.DARKGRAY);
            	} else {
            		if (link!= null)
            			symbolColors.add(linkColors[count]);
            		else
            			symbolColors.add(linkColors[10]);
            	}
            		
        	}
        	//if element is deactivated don't draw the graph, else do.
        	if (values[count][j] == -120) {
        		if (xLineSeries.size() != 0) {
        			double[] xLineSeries1 = new double[xLineSeries.size()];
        	        double[] yLineSeries1 = new double[yLineSeries.size()];
        	        for (int k = 0; k < xLineSeries.size(); k++) {
        	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
        	        	yLineSeries1[k] = ((Double) (yLineSeries.get(k))).doubleValue();
        	        }
        	        Color linkColor = null;
        	        if (link!= null)
        	        	linkColor = linkColors[count];
            		else
            			linkColor = linkColors[10];
        	        createLineGraph(chart, xLineSeries1, yLineSeries1, legendCount, count, link, linkColor, series);
        	        legendCount += 1;
        	        xLineSeries.clear();
        	        yLineSeries.clear();
        		}
        		
        	} else {
        		xLineSeries.add((double) j);
	        	yLineSeries.add(values[count][j]); 
        	}
        	
        	if (j == values[count].length-1 && xLineSeries.size() != 0) {
        		double[] xLineSeries1 = new double[xLineSeries.size()];
    	        double[] yLineSeries1 = new double[yLineSeries.size()];
    	        for (int k = 0; k < xLineSeries.size(); k++) {
    	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
    	        	yLineSeries1[k] = ((Double) (yLineSeries.get(k))).doubleValue();
    	        }
    	        Color linkColor = null;
    	        if (link!= null)
    	        	linkColor = linkColors[count];
        		else
        			linkColor = linkColors[10];
    	        createLineGraph(chart, xLineSeries1, yLineSeries1, legendCount, count, link, linkColor, series);
    	        
    	        xLineSeries.clear();
    	        yLineSeries.clear();
        	}
        	
        }
        double[] xSeries1 = new double[updXSeries.size()];
        double[] ySeries1 = new double[updYSeries.size()];
        Color[] symColors = new Color[symbolColors.size()];
        for (int j = 0; j < updXSeries.size(); j++) {
        	xSeries1[j] = ((Double) (updXSeries.get(j))).doubleValue();
        	if (updYSeries.get(j).equals(Double.valueOf(-120)))
        		ySeries1[j] = 0;
        	else
        		ySeries1[j] = ((Double) (updYSeries.get(j))).doubleValue();
        	symColors[j] = (Color) symbolColors.get(j);
        }
        
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        Date[] dates = new Date[xSeries1.length];
        for (int j = 0; j < xSeries1.length; j++) {
        	cStart.add(Calendar.DAY_OF_MONTH, (int) xSeries1[j]);
        	dates[j] = cStart.getTime();
        	cStart.setTime(start);
        }
        
        if (count > 0 && symSeries.size() < count) {
    		for (int k = 0; k < count+1-symSeries.size(); k++) {
    			symSeries.add(null);
    		}
    	}
        
        //Plot symbols at timepoints for Evaluation
        symSeries.add((LineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Contribution Evaluation Scatter"+count));
       
        symSeries.get(count).setVisibleInLegend(false);
        symSeries.get(count).setXDateSeries(dates);
        
        symSeries.get(count).setYSeries(ySeries1);
        symSeries.get(count).setLineStyle(LineStyle.NONE);
        symSeries.get(count).setSymbolType(PlotSymbolType.CROSS);
        symSeries.get(count).setSymbolColors(symColors);
        symSeries.get(count).setLineWidth(2);
        symSeries.get(count).setXAxisId(0);
    }
    
    private void drawChartEvalImp(URNmodelElement elt, int i) {
    	double[][] evalLevels = dynViewObject.getEvaluationValues();
    	double[][] importValues = dynViewObject.getImportanceValues();
    	Display display = Display.getDefault();
    	Shell shell = new Shell(display);
    	shell.setText("Chart for \"" + elt.getName() + "\"");
        shell.setSize(800, 800);
        shell.setLayout(new FillLayout());
        shell.setImage(JUCMNavPlugin.getImage("icons/Change.gif"));
    	Chart chart = new Chart(shell, SWT.NONE);
    	
    	// set titles
        chart.getTitle().setText("Evaluation and Importance of \"" + elt.getName() + "\"");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Timepoints");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Values");
        
        /*Create line graphs only for the parts that aren't deactivated as well as collect only those values which are at timepoints*/
        //Lists to store only the values at timepoints to show as symbols
        List<Double> updXSeries = new ArrayList<Double>();
        List<Double> updYSeriesEval = new ArrayList<Double>();
        List<Double> updYSeriesImp = new ArrayList<Double>();
        List<Color> symbolColors = new ArrayList<Color>();
        String[] tpNames = dynViewObject.getTimepointNames();
        List<Double> xLineSeries = new ArrayList<Double>();
        List<Double> yLineSeriesEval = new ArrayList<Double>();
        List<Double> yLineSeriesImp = new ArrayList<Double>();
        int legendCount = 0;
        for (int j = 0; j < evalLevels[i].length; j++) {
        	
        	//collect only those values which are at timepoints in order to plot as a scatter graph
        	if (!tpNames[j].equals("")) {
        		updXSeries.add((double) j);
        		updYSeriesEval.add(evalLevels[i][j]); 
        		updYSeriesImp.add(importValues[i][j]);
        		
        		if(colors[i][j].equals(new Color(null, StringConverter.asRGB("169,169,169")))) {
            		symbolColors.add(ColorManager.DARKGRAY);
            	} else
            		symbolColors.add(ColorManager.BLUE);
        	}
        	//if element is deactivated don't draw the graph, else do.
        	if(colors[i][j].equals(new Color(null, StringConverter.asRGB("169,169,169")))) {
        		if (xLineSeries.size() != 0) {
        			double[] xLineSeries1 = new double[xLineSeries.size()];
        	        double[] yLineSeries1 = new double[yLineSeriesEval.size()];
        	        double[] yLineSeries2 = new double[yLineSeriesImp.size()];
        	        for (int k = 0; k < xLineSeries.size(); k++) {
        	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
        	        	yLineSeries1[k] = ((Double) (yLineSeriesEval.get(k))).doubleValue();
        	        	yLineSeries2[k] = ((Double) (yLineSeriesImp.get(k))).doubleValue();
        	        }
        	        createLineGraph(chart, xLineSeries1, yLineSeries1, yLineSeries2,legendCount);
        	        legendCount += 1;
        	        xLineSeries.clear();
        	        yLineSeriesEval.clear();
        	        yLineSeriesImp.clear();
        		}
        		
        	} else {
        		xLineSeries.add((double) j);
	        	yLineSeriesEval.add(evalLevels[i][j]); 
	        	yLineSeriesImp.add(importValues[i][j]);
        	}
        	
        	if (j == evalLevels[i].length-1 && xLineSeries.size() != 0) {
        		double[] xLineSeries1 = new double[xLineSeries.size()];
    	        double[] yLineSeries1 = new double[yLineSeriesEval.size()];
    	        double[] yLineSeries2 = new double[yLineSeriesImp.size()];
    	        for (int k = 0; k < xLineSeries.size(); k++) {
    	        	xLineSeries1[k] = ((Double) (xLineSeries.get(k))).doubleValue();
    	        	yLineSeries1[k] = ((Double) (yLineSeriesEval.get(k))).doubleValue();
    	        	yLineSeries2[k] = ((Double) (yLineSeriesImp.get(k))).doubleValue();
    	        }
    	        
    	        createLineGraph(chart, xLineSeries1, yLineSeries1, yLineSeries2, legendCount);
    	        
    	        xLineSeries.clear();
    	        yLineSeriesEval.clear();
    	        yLineSeriesImp.clear();
        	}
        	
        }
        double[] xSeries1 = new double[updXSeries.size()];
        double[] ySeries1 = new double[updYSeriesEval.size()];
        double[] ySeries2 = new double[updYSeriesImp.size()];
        Color[] symColors = new Color[symbolColors.size()];
        for (int j = 0; j < updXSeries.size(); j++) {
        	xSeries1[j] = ((Double) (updXSeries.get(j))).doubleValue();
        	ySeries1[j] = ((Double) (updYSeriesEval.get(j))).doubleValue();
        	ySeries2[j] = ((Double) (updYSeriesImp.get(j))).doubleValue();
        	symColors[j] = (Color) symbolColors.get(j);
        }
        
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        Date[] series = new Date[xSeries1.length];
        for (int j = 0; j < xSeries1.length; j++) {
        	cStart.add(Calendar.DAY_OF_MONTH, (int) xSeries1[j]);
        	series[j] = cStart.getTime();
        	cStart.setTime(start);
        }
        
        //Plot symbols at timepoints for Evaluation
        ILineSeries symSeries1 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Evaluation Scatter");
        symSeries1.setVisibleInLegend(false);
        symSeries1.setXDateSeries(series);
        
        //symSeries1.setXSeries(xSeries1);
        symSeries1.setYSeries(ySeries1);
        symSeries1.setLineStyle(LineStyle.NONE);
        symSeries1.setSymbolType(PlotSymbolType.CROSS);
        symSeries1.setSymbolColors(symColors);
        symSeries1.setLineWidth(2);
        symSeries1.setXAxisId(0);
        
        //Plot symbols at timepoints for Importance
        ILineSeries symSeries2 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Importance Scatter");
        symSeries2.setVisibleInLegend(false);
        symSeries2.setXDateSeries(series);
        //symSeries2.setXSeries(xSeries1);
        symSeries2.setYSeries(ySeries2);
        symSeries2.setLineStyle(LineStyle.NONE);
        symSeries2.setSymbolType(PlotSymbolType.CROSS);
        for (int j = 0; j < symColors.length; j++) {
        	if (symColors[j].equals(ColorManager.BLUE)) {
        		symColors[j] = ColorManager.PURPLE;
        	}
        }
        symSeries2.setSymbolColors(symColors);
        symSeries2.setLineWidth(2);
        symSeries2.setXAxisId(0);
        //chart.setLocation(5, evalBar[evalBar.length - 1][0].getLocation().y + 20);
        //chart.setSize(700, 700);
        // adjust the axis range
        Range range = new Range(-110, 110);
        chart.getAxisSet().getYAxis(0).setRange(range);
        cStart.setTime(start);
        //cStart.set(Calendar.YEAR, 1969);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(dynViewObject.endRange());
        cEnd.add(Calendar.DAY_OF_MONTH, 1);
        //cEnd.set(Calendar.YEAR, 1969);
        Range xRange = new Range(cEnd.getTimeInMillis(),cStart.getTimeInMillis());
        chart.getAxisSet().getXAxis(0).setRange(xRange);
        IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        xTick.setFormat(format);
        xTick.setTickLabelAngle(90);
        xTick.setVisible(true);
        xTick.setTickMarkStepHint(70);        
        
        chart.getAxisSet().getXAxis(0).adjustRange();
        shell.open();
    }
    
    private void drawSystemEvaluation() {
    	double[][] evalLevels = dynViewObject.getEvaluationValues();
    	Display display = Display.getDefault();
    	Shell shell = new Shell(display);
    	shell.setText("Overall Evaluation");
        shell.setSize(800, 800);
        shell.setLayout(new FillLayout());
        shell.setImage(JUCMNavPlugin.getImage("icons/Change.gif"));
    	Chart chart = new Chart(shell, SWT.NONE);
    	
    	// set titles
        chart.getTitle().setText("Overall Evaluation");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Timepoints");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Values");
        
        /*Create line graphs only for the parts that aren't deactivated as well as collect only those values which are at timepoints*/
        //Lists to store only the values at timepoints to show as symbols
        List<Double> updXSeries = new ArrayList<Double>();
        List<Double> updYSeriesEval = new ArrayList<Double>();
        String[] tpNames = dynViewObject.getTimepointNames();
        int legendCount = 0;
        for (int j = 0; j < evalLevels[0].length; j++) {
        	
        	//collect only those values which are at timepoints in order to plot as a scatter graph
        	if (!tpNames[j].equals("")) {
        		updXSeries.add((double) j);
        		updYSeriesEval.add(evalLevels[0][j]);
        	}
        	
        	
        }
        double[] xSeries1 = new double[updXSeries.size()];
        double[] ySeries1 = new double[updYSeriesEval.size()];
        for (int j = 0; j < updXSeries.size(); j++) {
        	xSeries1[j] = ((Double) (updXSeries.get(j))).doubleValue();
        	ySeries1[j] = ((Double) (updYSeriesEval.get(j))).doubleValue();
        }
        
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        Date[] series = new Date[tpNames.length];
        for (int i = 0; i < tpNames.length; i++) {
        	if (i != 0) {
        		cStart.add(Calendar.DAY_OF_MONTH, 1);
        	}
        	series[i] = cStart.getTime();	
        }
        
        // create line series for Evaluation
        ILineSeries lineSeries2 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Overall Evaluation");
        lineSeries2.setXDateSeries(series);
        lineSeries2.setYSeries(evalLevels[0]);
        lineSeries2.setLineStyle(LineStyle.SOLID);
        lineSeries2.setLineColor(ColorManager.BLUE);
        lineSeries2.setLineWidth(2);
        lineSeries2.setSymbolType(PlotSymbolType.NONE);
             
        cStart.setTime(start);
        Date[] series1 = new Date[xSeries1.length];
        for (int j = 0; j < xSeries1.length; j++) {
        	cStart.add(Calendar.DAY_OF_MONTH, (int) xSeries1[j]);
        	series1[j] = cStart.getTime();
        	cStart.setTime(start);
        }
        
        //Plot symbols at timepoints for Evaluation
        ILineSeries symSeries1 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Evaluation Scatter");
        symSeries1.setVisibleInLegend(false);
        symSeries1.setXDateSeries(series1);
        
        symSeries1.setYSeries(ySeries1);
        symSeries1.setLineStyle(LineStyle.NONE);
        symSeries1.setSymbolType(PlotSymbolType.CROSS);
        symSeries1.setSymbolColor(ColorManager.BLUE);
        symSeries1.setLineWidth(2);
        symSeries1.setXAxisId(0);
        
        // adjust the axis range
        Range range = new Range(-110, 110);
        chart.getAxisSet().getYAxis(0).setRange(range);
        cStart.setTime(start);
        //cStart.set(Calendar.YEAR, 1969);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(dynViewObject.endRange());
        cEnd.add(Calendar.DAY_OF_MONTH, 1);
        //cEnd.set(Calendar.YEAR, 1969);
        Range xRange = new Range(cEnd.getTimeInMillis(),cStart.getTimeInMillis());
        chart.getAxisSet().getXAxis(0).setRange(xRange);
        IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        xTick.setFormat(format);
        xTick.setTickLabelAngle(90);
        xTick.setVisible(true);
        xTick.setTickMarkStepHint(70);        
        
        chart.getAxisSet().getXAxis(0).adjustRange();
        shell.open();
    }
    
    private void createLineGraph(Chart chart, double[] xLineSeries1, double[] yLineSeries1, double[] yLineSeries2, int legendCount) {
    	
    	String st = null;
    	if (legendCount == 0)
    		st = "";
    	else
    		st = Integer.toString(legendCount);
    	ILineSeries lineSeries;
    	if (yLineSeries2 != null) {
    		// create line series for Evaluation
    		lineSeries = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "Evaluation" + st);
    	} else {
    		// create line series for overall Evaluation due to decomposition links
    		lineSeries = (ILineSeries) chart.getSeriesSet()
                    .createSeries(SeriesType.LINE, "Overall Eval" + st);
    	}
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        cStart.add(Calendar.DAY_OF_MONTH, (int) xLineSeries1[0]); 
        
        Date[] series = new Date[xLineSeries1.length];
        for (int i = 0; i < xLineSeries1.length; i++) {
        	if (i != 0) {
        		cStart.add(Calendar.DAY_OF_MONTH, 1);
        	}
        	series[i] = cStart.getTime();	
        }
        lineSeries.setXDateSeries(series);
        
        lineSeries.setYSeries(yLineSeries1);
        if (yLineSeries2 == null) {
        	lineSeries.setLineStyle(LineStyle.DASH);
            lineSeries.setLineColor(linkColors[10]);
            lineSeries.setLineWidth(3);
        } else {
        	lineSeries.setLineStyle(LineStyle.SOLID);
            lineSeries.setLineColor(ColorManager.BLUE);
            lineSeries.setLineWidth(2);
        }
        
        lineSeries.setSymbolType(PlotSymbolType.NONE);
        
        if (yLineSeries2 != null) {
	        // create line series for Importance
	        ILineSeries lineSeries2 = (ILineSeries) chart.getSeriesSet()
	                .createSeries(SeriesType.LINE, "Importance" + st);
	        lineSeries2.setXDateSeries(series);
	        //lineSeries2.setXSeries(xLineSeries1);
	        lineSeries2.setYSeries(yLineSeries2);
	        lineSeries2.setLineStyle(LineStyle.DASH);
	        lineSeries2.setLineColor(ColorManager.PURPLE);
	        lineSeries2.setLineWidth(2);
	        lineSeries2.setSymbolType(PlotSymbolType.NONE);
	        if (legendCount > 0) {
	        	lineSeries2.setVisibleInLegend(false);
	        }
        }
        if (legendCount > 0) {
        	lineSeries.setVisibleInLegend(false);
        }
        chart.getAxisSet().getXAxis(0).adjustRange();
    }
    
  private void createLineGraph(Chart chart, double[] xLineSeries1, double[] yLineSeries1, int legendCount, int count, ElementLink link, Color color, ArrayList<LineSeries> series) {
    	
    	String st = null;
    	if (legendCount == 0)
    		st = "";
    	else
    		st = Integer.toString(legendCount);
    	if (count > 0 && series.size() < count) {
    		for (int k = 0; k < count+1-series.size(); k++) {
    			series.add(null);
    		}
    	}
    	if (link != null)
    		series.add(count,(LineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, link.getName() + " " + st));
    	else
    		series.add(count,(LineSeries) chart.getSeriesSet()
                    .createSeries(SeriesType.LINE, "Overall" + " " + st));
    	// create line series for Evaluation
        Date start = dynViewObject.startRange();
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        cStart.add(Calendar.DAY_OF_MONTH, (int) xLineSeries1[0]); 
        
        Date[] dates = new Date[xLineSeries1.length];
        for (int i = 0; i < xLineSeries1.length; i++) {
        	if (i != 0) {
        		cStart.add(Calendar.DAY_OF_MONTH, 1);
        	}
        	dates[i] = cStart.getTime();	
        }
        series.get(count).setXDateSeries(dates);
        series.get(count).setYSeries(yLineSeries1);
        if (link != null) {
        	series.get(count).setLineStyle(LineStyle.SOLID);
        	series.get(count).setLineWidth(2);
        } else {
        	series.get(count).setLineStyle(LineStyle.DASH);
        	series.get(count).setLineWidth(3);
        }
        series.get(count).setLineColor(color);
        series.get(count).setSymbolType(PlotSymbolType.NONE);
        if (legendCount > 0) {
        	series.get(count).setVisibleInLegend(false);
        }
        chart.getAxisSet().getXAxis(0).adjustRange();
    }

}
