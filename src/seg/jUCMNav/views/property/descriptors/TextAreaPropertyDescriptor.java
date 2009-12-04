package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.editpolicies.directEditPolicy.TextAreaCellEditor;

/**
 * A multline textbox property descriptor
 * 
 * @author jkealey
 */
public class TextAreaPropertyDescriptor extends TextPropertyDescriptor {

    protected CellEditor editor;

    /**
     * @param id
     * @param displayName
     */
    public TextAreaPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
    }

    public CellEditor createPropertyEditor(Composite parent) {
        editor = new TextAreaCellEditor(parent);

        if (getValidator() != null)
            editor.setValidator(getValidator());
        return editor;
    }

}