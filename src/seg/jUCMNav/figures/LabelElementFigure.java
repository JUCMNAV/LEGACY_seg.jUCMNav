package seg.jUCMNav.figures;

import org.eclipse.draw2d.IFigure;

/**
 * Interface shared by element who add text (label, grlnode, ...)
 * 
 * @author Jean-François Roy
 * 
 */
public interface LabelElementFigure extends IFigure {

    /**
     * Returns the text inside the TextFlow.
     * 
     * Use this method instead of getText()
     * 
     * @return the text inside the label
     */
    public String getEditableText();

    /**
     * Sets the text of the TextFlow to the given value.
     * 
     * @param newText
     *            the new text value.
     */
    public void setEditableText(String newText);

}