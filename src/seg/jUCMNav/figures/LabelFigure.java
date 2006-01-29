package seg.jUCMNav.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

/**
 * Figure for Labels; uses an EditableLabel.
 * 
 * @author Jordan
 */
public class LabelFigure extends Figure implements LabelElementFigure {
    private boolean selected;
    
    /** The inner TextFlow **/
    protected TextFlow textFlow;
    
    public LabelFigure() {
        FlowPage flowPage = new FlowPage();

        textFlow = new TextFlow();
        
        textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
                ParagraphTextLayout.WORD_WRAP_SOFT));

        flowPage.add(textFlow);

        setLayoutManager(new StackLayout());
        add(flowPage);
    }

    private Rectangle getSelectionRectangle() {
        Rectangle bounds = textFlow.getBounds().getCopy();
        bounds.expand(new Insets(2, 2, 0, 0));
        translateToParent(bounds);
        bounds.intersect(getBounds());
        return bounds;
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.figures.LabelElementFigure#getText()
     */
    public String getText() {
        return textFlow.getText();
    }
    
    /**
     * paints figure differently depends on the whether the figure has focus or is selected
     */
    protected void paintFigure(Graphics graphics) {
        if (selected) {
            graphics.pushState();
            graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
            graphics.fillRectangle(getSelectionRectangle());
            graphics.popState();
            graphics.setForegroundColor(ColorConstants.white);
            
        }
        super.paintFigure(graphics);
    }

    /**
     * Sets the selection state of this SimpleActivityLabel
     * 
     * @param b
     *            true will cause the label to appear selected
     */
    public void setSelected(boolean b) {
        selected = b;
        repaint();
    }
    /* (non-Javadoc)
     * @see seg.jUCMNav.figures.LabelElementFigure#setText(java.lang.String)
     */
    public void setText(String newText) {
        textFlow.setText(newText);
    }

}
