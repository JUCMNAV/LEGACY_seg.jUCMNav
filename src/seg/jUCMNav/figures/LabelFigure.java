/*
 * Created on Apr 17, 2005
 *
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Figure;

/**
 * @author Jordan
 *
 */
public class LabelFigure extends Figure {
	private EditableLabel label;
    
    public LabelFigure(EditableLabel label) {
        this.label = label;
		
		add(label);
    }
    
    /**
	 * @return returns the label used to edit
	 */
	public EditableLabel getLabel()
	{
		return label;
	}
}
