package seg.jUCMNav.views.property.descriptors;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.stub.StubBindingsDialog;
import ucm.map.Stub;

/**
 * A cell editor that presents a button to open the StubBindingsDialog.
 * 
 * @author Etienne Tremblay
 * @see seg.jUCMNav.views.stub.StubBindingsDialog
 */
public class StubPluginsCellEditor extends DialogCellEditor {

    private Stub stub;
    private Label defaultLabel;
    private CommandStack cmdStack;

    /**
     * @param parent
     */
    public StubPluginsCellEditor(Composite parent, CommandStack cmdStack) {
        super(parent);
        this.cmdStack = cmdStack;
    }

    /**
     * @param parent
     * @param style
     */
    public StubPluginsCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        StubBindingsDialog d = new StubBindingsDialog(shell, cmdStack);
        d.open(getStub());
        return null;
    }

    protected Stub getStub() {
        return stub;
    }

    public void setStub(Stub stub) {
        this.stub = stub;
    }

    protected Control createContents(Composite cell) {
        defaultLabel = new Label(cell, SWT.LEFT);
        defaultLabel.setFont(cell.getFont());
        defaultLabel.setBackground(cell.getBackground());
        return defaultLabel;
    }

    protected void updateContents(Object value) {
        defaultLabel.setText(""); //$NON-NLS-1$
    }

    protected Label getDefaultLabel() {
        return defaultLabel;
    }
}