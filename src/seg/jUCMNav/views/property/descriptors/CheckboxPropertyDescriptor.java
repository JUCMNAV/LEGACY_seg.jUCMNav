package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author TremblaE
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CheckboxPropertyDescriptor extends PropertyDescriptor {
    private CheckboxButtonCellEditor editor;

    private boolean readonly;

    /**
     * Creates an property descriptor with the given id and display name.
     * 
     * @param id
     *            the id of the property
     * @param displayName
     *            the name to display for the property
     */
    public CheckboxPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
    }

    public CellEditor createPropertyEditor(Composite parent) {
        if (editor == null) {
            editor = new CheckboxButtonCellEditor(parent);
            if (getValidator() != null)
                editor.setValidator(getValidator());
        }

        editor.setReadOnly(readonly);
        
        return editor;
    }

    public void setReadOnly(boolean b) {
        readonly = b;
    }
}