package seg.jUCMNav.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Figure for UCM Labels; uses an EditableLabel.
 * 
 * @author Jordan, Jean-François Roy
 */
public class LabelFigure extends Label implements LabelElementFigure {
    public String additionalText = ""; //$NON-NLS-1$
    
    private boolean selected;
    
    /** The inner TextFlow **/
   // protected TextFlow textFlow;
    
    public LabelFigure() {
//        FlowPage flowPage = new FlowPage();
//
//        textFlow = new TextFlow();
//        textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
//                ParagraphTextLayout.WORD_WRAP_SOFT));
//
//        flowPage.add(textFlow);
//
//        setLayoutManager(new StackLayout());
//        add(flowPage);
    }
    
    private Rectangle getSelectionRectangle() {
        Rectangle bounds = getBounds().getCopy();//textFlow.getBounds().getCopy();
        bounds.expand(new Insets(2, 2, 0, 0));
        translateToParent(bounds);
        bounds.intersect(getBounds());
        return bounds;
    }
    
    /** 
     * Use this method instead of getText for LabelFigure
     */
    public String getEditableText() {
        if (!additionalText.equals("")){ //$NON-NLS-1$
            int sub = super.getText().lastIndexOf("{"); //$NON-NLS-1$
            return super.getText().substring(0,sub);
        }
        return super.getText();
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

    public void setAdditionalText(String text){
        String previousText = getEditableText();
        if (additionalText.equals(text)){
            return;
        }
        additionalText = text;
        setEditableText(previousText);
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
    public void setEditableText(String newText) {
        if (! additionalText.equals("")){ //$NON-NLS-1$
            super.setText(newText + "{" + additionalText + "}"); //$NON-NLS-1$ //$NON-NLS-2$
        } else{
            super.setText(newText);
        }
    }

}
