package seg.jUCMNav.views.property.descriptors;

import grl.ContributionChange;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;

/**
 * A property descriptor that opens the ContributionRange wizard
 * 
 * @author jkealey
 */
public class ContributionRangePropertyDescriptor extends PropertyDescriptor {
    private ContributionChange change;

    /**
     * Property descriptor for an evaluation range.
     * 
     * @param id
     * @param urnelem
     */
    public ContributionRangePropertyDescriptor(Object id, ContributionChange c) {
        super(id, Messages.getString("ContributionRangePropertyDescriptor.ContributionRange")); //$NON-NLS-1$
        this.change = c;
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
        ContributionRangeCellEditor editor = new ContributionRangeCellEditor(parent);
        if (change != null)
            editor.setContributionChange(change);

        return editor;
    }
}
