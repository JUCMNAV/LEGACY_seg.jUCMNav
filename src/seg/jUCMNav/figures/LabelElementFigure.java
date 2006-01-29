package seg.jUCMNav.figures;

import org.eclipse.draw2d.IFigure;


/**
 * Interface shared by element who add text (label, grlnode, ...)
 * 
 * @author Jean-François Roy
 *
 */
public interface LabelElementFigure extends IFigure{

    /**
     * Returns the text inside the TextFlow.
     * 
     * @return the text flow inside the text.
     */
    public String getText();

    /**
     * Sets the text of the TextFlow to the given value.
     * 
     * @param newText the new text value.
     */
    public void setText(String newText);

}