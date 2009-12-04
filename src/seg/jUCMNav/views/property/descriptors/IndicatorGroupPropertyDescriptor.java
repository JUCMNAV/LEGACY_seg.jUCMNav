package seg.jUCMNav.views.property.descriptors;

import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;

/**
 * A property descriptor that opens the IndicatorGroupCellEditor.
 * 
 * @author pchen
 */
public class IndicatorGroupPropertyDescriptor extends PropertyDescriptor {
    private Indicator indicator;

    /**
     * Property descriptor for a indicator group.
     * 
     * @param id
     * @param intElementRef
     */
    public IndicatorGroupPropertyDescriptor(Object id, IntentionalElementRef intElementRef) {
        super(id, Messages.getString("IndicatorGroupPropertyDescriptor.indicatorGroups")); //$NON-NLS-1$
        this.indicator = (Indicator) intElementRef.getDef();
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return Messages.getString("IndicatorGroupPropertyDescriptor.ClickToEdit"); //$NON-NLS-1$
            }
        });
    }

    /**
     * Property descriptor for a indicator group.
     * 
     * @param id
     * @param indicator
     */
    public IndicatorGroupPropertyDescriptor(Object id, Indicator indicator) {
        super(id, Messages.getString("IndicatorGroupPropertyDescriptor.indicatorGroups")); //$NON-NLS-1$
        this.indicator = (Indicator) indicator;
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return Messages.getString("IndicatorGroupPropertyDescriptor.ClickToEdit"); //$NON-NLS-1$
            }
        });
    }

    /**
     * Creates the cell editor.
     */
    public CellEditor createPropertyEditor(Composite parent) {
        IndicatorGroupCellEditor editor = new IndicatorGroupCellEditor(parent);
        if (indicator != null) {
            editor.setIndicator(indicator);
        }

        return editor;
    }
}
