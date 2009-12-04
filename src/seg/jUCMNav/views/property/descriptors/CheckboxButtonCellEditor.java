package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import seg.jUCMNav.Messages;

/**
 * Cell editor that presents a checkbox and can be set to true/false.
 * 
 * @author Etienne Tremblay
 */
public class CheckboxButtonCellEditor extends CheckboxCellEditor implements SelectionListener, MouseListener {

    protected Button check;
    protected Label label;

    /**
     *  
     */
    public CheckboxButtonCellEditor() {
        super();
    }

    /**
     * @param parent
     */
    public CheckboxButtonCellEditor(Composite parent) {
        super(parent);
        // createControl(parent);
    }

    /**
     * @param parent
     * @param style
     */
    public CheckboxButtonCellEditor(Composite parent, int style) {
        super(parent, style);
        // createControl(parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.CellEditor#activate()
     */
    public void activate() {
        this.doSetValue(Boolean.valueOf(check.getSelection()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
     */
    protected Control createControl(Composite parent) {
        Composite comp = new Composite(parent, 0);
        RowLayout layout = new RowLayout();
        layout.marginTop = 0;
        comp.setLayout(layout);
        check = new Button(comp, SWT.CHECK);
        check.setBounds(0, 0, 10, 10);

        label = new Label(comp, 0);
        label.setAlignment(SWT.LEFT);
        label.addMouseListener(this);

        check.addSelectionListener(this);
        return comp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
     */
    protected void doSetValue(Object value) {
        if (value instanceof String)
            value = Boolean.valueOf(Boolean.parseBoolean((String) value));
        super.doSetValue(value);

        Boolean b = (Boolean) value;
        check.setSelection(b.booleanValue());
        if (b.booleanValue())
            label.setText(Messages.getString("CheckboxButtonCellEditor.true")); //$NON-NLS-1$
        else
            label.setText(Messages.getString("CheckboxButtonCellEditor.false")); //$NON-NLS-1$
    }

    public void mouseDoubleClick(MouseEvent e) {
    }

    public void mouseDown(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseUp(MouseEvent e) {
        doSetValue(Boolean.valueOf(!check.getSelection()));
        fireApplyEditorValue();
    }

    public void setReadOnly(boolean b) {
        check.setEnabled(!b);
    }

    // Unused implemented methods
    public void widgetDefaultSelected(SelectionEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        doSetValue(Boolean.valueOf(check.getSelection()));
        fireApplyEditorValue();
    }
}