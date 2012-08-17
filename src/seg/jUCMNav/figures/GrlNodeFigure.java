package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.figures.util.UrnMetadata;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This is a figure representing a GRL node. Extend this class to create GrlNode
 * 
 * @author Jean-Fran�ois Roy
 * 
 */
public abstract class GrlNodeFigure extends Shape implements LabelElementFigure {

    // for grl multiline label, space between start of the figure and start of the label
    protected static final int LABEL_PADDING_X = 15;
    protected static final int LABEL_PADDING_Y = 8;

    // default sizes
    protected static final int DEFAULT_HEIGHT = 45;
    protected static final int DEFAULT_WIDTH = 90;

    /** The inner TextFlow **/
    protected TextFlow textFlow;
    protected FlowPage flowPage;

    protected ConnectionAnchor anchor;

    /**
     * Override this method if your figure is not of the default size.
     * 
     * @return Returns the default dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    // is the figure in hover state
    protected boolean hover;

    // is the figure in selected state
    protected boolean selected;

    // automatically resize when changing text.
    protected boolean autoResize;

    protected XYLayout xylayout;

    /**
     * Constructor of the node figure. Set the layout manager and the line width
     */
    public GrlNodeFigure() {
        super();
        autoResize = true;
        setAntialias(GeneralPreferencePage.getAntialiasingPref());

        xylayout = new XYLayout();
        this.setLayoutManager(xylayout);
        setLineWidth(3);

        initAnchor();

        flowPage = new FlowPage();
        // Center text in GRL nodes. Vertical centering not available...
        flowPage.setHorizontalAligment(PositionConstants.CENTER);

        textFlow = new TextFlow();
        // Slightly larger font here used for GRL node labels.
        textFlow.setFont(new Font(Display.getDefault(), new FontData("Tahoma", 9, SWT.NONE))); //$NON-NLS-1$
        textFlow.setVisible(!shouldHideInnerText());
        textFlow.setLayoutManager(new ParagraphTextLayout(textFlow, ParagraphTextLayout.WORD_WRAP_HARD));

        flowPage.add(textFlow);
        add(flowPage);
    }

    protected boolean shouldHideInnerText()
    {
        return false;
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected abstract void outlineShape(Graphics graphics);

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected abstract void fillShape(Graphics graphics);

    /**
     * This return the connection anchor.
     * 
     * @return The connection anchor
     */
    public ConnectionAnchor getConnectionAnchor() {
        return anchor;
    }

    /**
     * @return this figure
     */
    public Figure getNodeFigure() {
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return textFlow.getPreferredSize().getCopy();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.LabelElementFigure#getText()
     */
    public String getEditableText() {
        return UrnMetadata.removeStereotypes(textFlow.getText());
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        anchor = new ChopboxAnchor(this);
    }

    /**
     * Sets the figure's colors. The default line color is black, the default fill color is white.
     * 
     * @param lineColor
     *            outline color
     * @param fillColor
     *            inside color
     * @param filled
     *            should it be filled?
     */
    public void setColors(String lineColor, String fillColor, boolean filled) {
        setFill(filled);

        if (fillColor == null || fillColor.length() == 0) {
            setBackgroundColor(ColorManager.FILL);
        } else {
//        	if( JUCMNavPlugin.isInDebug() ) System.out.println( "fillColor: " + fillColor );
            setBackgroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(fillColor)));
        }
        
        if (lineColor == null || lineColor.length() == 0) {
            setForegroundColor(ColorManager.LINE);
        } else
            setForegroundColor(new Color(Display.getCurrent(), StringConverter.asRGB(lineColor)));
    }

    /**
     * Sets the text of the TextFlow to the given value and set the size of the label.
     * 
     * @param newText
     *            the new text value.
     */
    public void setEditableText(String newText) {
        textFlow.setText(newText);
        resizeAccordingToText();
    }

    protected void resizeAccordingToText() {

        Dimension dimEditableLabel = flowPage.getPreferredSize().getCopy();

        // Calculate the size of the label and of the figure
        // Max size available for the label
        int width = getDefaultDimension().width - 2 * LABEL_PADDING_X;
        int height = dimEditableLabel.height;

        int minWidth = flowPage.getPreferredSize(width, 1).width;

        // Loop until we have good dimension for the labels to fit in the node
        while (dimEditableLabel.width > (width * Math.floor((height - 1) / dimEditableLabel.height)) || width < minWidth) {
            height = height + dimEditableLabel.height;
            width = width + 20;

        }
        if (height < (getDefaultDimension().height - 2 * LABEL_PADDING_Y)) {
            height = getDefaultDimension().height - 2 * LABEL_PADDING_Y;
        }
        Rectangle r = new Rectangle();
        r.x = LABEL_PADDING_X;
        r.y = LABEL_PADDING_Y;
        r.width = width;
        r.height = height;

        if (!autoResize) {
            r.setSize(getSize().width - 2 * LABEL_PADDING_X, getSize().height - 2 * LABEL_PADDING_Y);
            setConstraint(flowPage, r);
        } else {
            setConstraint(flowPage, r);
            setSize(r.width + 2 * LABEL_PADDING_X, r.height + 2 * LABEL_PADDING_Y);
        }
    }

    public boolean isAutoResize() {
        return autoResize;
    }

    public void setAutoResize(boolean autoResize) {
        this.autoResize = autoResize;
    }
}
