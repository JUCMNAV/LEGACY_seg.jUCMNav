package seg.jUCMNav.figures;

import org.eclipse.draw2d.Figure;

/**
 * Figure for Labels; uses an EditableLabel.
 * 
 * @author Jordan
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
    public EditableLabel getLabel() {
        return label;
    }
}