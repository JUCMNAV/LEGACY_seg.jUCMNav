package seg.jUCMNav.views.property.descriptors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import urncore.Condition;
import urncore.Responsibility;

/**
 * A cell editor that presents a button to open the CodeEditor wizard
 * 
 * @author jkealey
 * @see seg.jUCMNav.views.wizards.scenarios.CodeEditor
 */
public class CodeCellEditor extends DialogCellEditor {

    // can either edit a responsibility's code
    private Responsibility resp;

    // or a failure point.
    private FailurePoint failure;

    // or a condition
    private Condition cond;

    // or a nc threshold 
    private NodeConnection nc;
    
    // swt label to be displayed
    private Label defaultLabel;

    /**
     * Creates a code cell editor.
     * 
     * @param parent
     *            container
     */
    public CodeCellEditor(Composite parent) {
        super(parent);
    }

    /**
     * Creates a code cell editor.
     * 
     * @param parent
     *            container
     * @param style
     *            style of editor
     */
    public CodeCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the code wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        CodeEditor wizard = new CodeEditor();

        EObject defaultObj = resp == null ? (EObject) cond : (EObject) resp;
        if (defaultObj == null)
            defaultObj = failure;
        if (defaultObj == null)
            defaultObj = nc;

        // initialize it
        wizard.init(PlatformUI.getWorkbench(), null, defaultObj);

        // open it.
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

        return null;
    }

    /**
     * 
     * @return the responsibility for which we show the code property.
     */
    protected Responsibility getResponsibility() {
        return resp;
    }

    /**
     * 
     * @param resp
     *            the responsibility for which we show the code property.
     */
    public void setResponsibility(Responsibility resp) {
        this.resp = resp;
    }

    /**
     * 
     * @return the condition for which we show the code property.
     */
    protected Condition getCondition() {
        return cond;
    }

    /**
     * 
     * @param cond
     *            the condition for which we show the code property.
     */
    public void setCondition(Condition cond) {
        this.cond = cond;
    }

    public FailurePoint getFailure() {
        return failure;
    }

    public void setFailure(FailurePoint failure) {
        this.failure = failure;
    }
    
    public NodeConnection getNodeConnection() {
        return nc;
    }

    public void setNodeConnection(NodeConnection nc) {
        this.nc = nc;
    }

    /**
     * Initialize the label that invites the user to click the button.
     */
    protected Control createContents(Composite cell) {
        defaultLabel = new Label(cell, SWT.LEFT);
        defaultLabel.setFont(cell.getFont());
        defaultLabel.setBackground(cell.getBackground());
        return defaultLabel;
    }

    /**
     * We always show the same message.
     */
    protected void updateContents(Object value) {
        defaultLabel.setText(Messages.getString("CodeCellEditor.ClickToEdit")); //$NON-NLS-1$
    }

    /**
     * Return the label that invites the user to click the button.
     */
    protected Label getDefaultLabel() {
        return defaultLabel;
    }



}