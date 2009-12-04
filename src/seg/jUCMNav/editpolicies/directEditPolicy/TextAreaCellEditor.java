package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TextAreaCellEditor extends TextCellEditor {

    public TextAreaCellEditor(Composite parent) {

        super(parent, SWT.MULTI);

    }

    protected Control createControl(Composite parent) {
        Control c = super.createControl(parent);
        text.setSize(200, 300);
        c.setSize(200, 300);
        return c;
    }
}
