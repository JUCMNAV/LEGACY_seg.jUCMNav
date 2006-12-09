package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;
import urncore.URNmodelElement;

/**
 * A property descriptor that opens the MetadataCellEditor.
 * 
 * @author pchen
 */
public class MetadataPropertyDescriptor extends PropertyDescriptor {
    private URNmodelElement urnelem;

    /**
     * Property descriptor for a urnmodelelement.
     * 
     * @param id
     * @param urnelem
     */
    public MetadataPropertyDescriptor(Object id, URNmodelElement urnelem) {
        super(id, Messages.getString("MetadataPropertyDescriptor.metadata")); //$NON-NLS-1$
        this.urnelem = urnelem;
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
        MetadataCellEditor editor = new MetadataCellEditor(parent);
        if (urnelem != null)
            editor.setURNmodelElement(urnelem);

        return editor;
    }
}
