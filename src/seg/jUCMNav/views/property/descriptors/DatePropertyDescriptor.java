package seg.jUCMNav.views.property.descriptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;
import urn.dyncontext.Timepoint;

/**
 * A property descriptor that opens the DateCellEditor.
 * 
 * @author aprajita
 * @see seg.jUCMNav.views.property.descriptors.DateCellEditor
 */
public class DatePropertyDescriptor extends PropertyDescriptor {
	
	private Timepoint tp;

    /**
     * Property descriptor for date
     * 
     * @param id
     * @param urnelem
     */
    public DatePropertyDescriptor(Object id, Timepoint tp) {
        super(id, Messages.getString("DatePropertyDescriptor.SelectedDate")); //$NON-NLS-1$
        this.tp = tp;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final String name = df.format(tp.getTimepoint());
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return name + Messages.getString("MetadataPropertyDescriptor.ClickToEdit"); //$NON-NLS-1$
            }
        });
    }

    /**
     * Creates the cell editor.
     */
    public CellEditor createPropertyEditor(Composite parent) {
    	DateCellEditor editor = new DateCellEditor(parent, tp);
        if (tp != null)
            editor.setTimepoint(tp);

        return editor;
    }

}
