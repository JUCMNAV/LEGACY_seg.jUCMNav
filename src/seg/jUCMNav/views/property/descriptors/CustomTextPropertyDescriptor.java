package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * A textbox proeprty descriptor that can be set to readonly.
 * 
 * jkealey: I beleive there are equivalents in the framework but this class may exist because the visual impact is nicer.
 * 
 * @author TremblaE
 */
public class CustomTextPropertyDescriptor extends TextPropertyDescriptor {

    protected CellEditor editor;
    protected boolean readOnly;

    /**
     * @param id
     * @param displayName
     */
    public CustomTextPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
    }

    public CellEditor createPropertyEditor(Composite parent) {
        editor = new TextCellEditor(parent);
        if (getValidator() != null)
            editor.setValidator(getValidator());
        setReadOnly(readOnly);
        return editor;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        if (editor != null)
            editor.getControl().setEnabled(!readOnly);
    }
}