package seg.jUCMNav.views.property.descriptors;

import grl.Evaluation;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;

/**
 * A property descriptor that opens the EvaluationRange wizard
 * 
 * @author jkealey
 */
public class EvaluationRangePropertyDescriptor extends PropertyDescriptor {
    private Evaluation evaluation;

    /**
     * Property descriptor for an evaluation range.
     * 
     * @param id
     * @param urnelem
     */
    public EvaluationRangePropertyDescriptor(Object id, Evaluation ev) {
        super(id, Messages.getString("EvaluationRangePropertyDescriptor.EvaluationRange")); //$NON-NLS-1$
        this.evaluation = ev;
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return Messages.getString("MetadataPropertyDescriptor.ClickToEdit"); //$NON-NLS-1$
            }
        });
    }

    /**
     * Creates the cell editor.
     */
    public CellEditor createPropertyEditor(Composite parent) {
        EvaluationRangeCellEditor editor = new EvaluationRangeCellEditor(parent);
        if (evaluation != null)
            editor.setEvaluation(evaluation);

        return editor;
    }
}
