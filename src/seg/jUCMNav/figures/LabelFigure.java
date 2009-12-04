package seg.jUCMNav.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Figure for UCM Labels; uses an EditableLabel.
 * 
 * @author Jordan, Jean-François Roy, jkealey
 */
public class LabelFigure extends Label implements LabelElementFigure {
    public String suffixText = ""; //$NON-NLS-1$
    public String prefixText = ""; //$NON-NLS-1$
    public String editableText = ""; //$NON-NLS-1$
    private boolean selected;

    public LabelFigure() {
    }

    private Rectangle getSelectionRectangle() {
        Rectangle bounds = getBounds().getCopy();// textFlow.getBounds().getCopy();
        bounds.expand(new Insets(2, 2, 0, 0));
        translateToParent(bounds);
        bounds.intersect(getBounds());
        return bounds;
    }

    /**
     * Use this method instead of getText for LabelFigure
     */
    public String getEditableText() {
        // return UrnMetadata.removeStereotypes(super.getText());
        return editableText;
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

    public void setSuffixText(String text) {
        String previousText = getEditableText();
        if (suffixText.equals(text)) {
            return;
        }
        suffixText = text;
        setEditableText(previousText);
    }

    public void setPrefixText(String text) {
        String previousText = getEditableText();
        if (prefixText.equals(text)) {
            return;
        }
        prefixText = text;
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

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.LabelElementFigure#setText(java.lang.String)
     */
    public void setEditableText(String newText) {
        this.editableText = newText;
        super.setText(prefixText + newText + suffixText);
    }

}
