package seg.jUCMNav.views.property.descriptors;

import grl.Evaluation;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.strategies.EditEvaluationRangeWizard;
import urn.URNspec;

/**
 * A cell editor that presents a button to open the EvaluationRange wizard
 * 
 * @author jkealey
 */
public class EvaluationRangeCellEditor extends DialogCellEditor {

    private Evaluation evaluation;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    private Label defaultLabel;

    /**
     * Creates a code cell editor.
     * 
     * @param parent
     *            container
     */
    public EvaluationRangeCellEditor(Composite parent) {
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
    public EvaluationRangeCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the metadata wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        if (evaluation == null || evaluation.getStrategies() == null || evaluation.getStrategies().getGrlspec() == null)
            return null;
        URNspec urn = evaluation.getStrategies().getGrlspec().getUrnspec();
        EditEvaluationRangeWizard wizard = new EditEvaluationRangeWizard(urn);

        // initialize it
        wizard.init(PlatformUI.getWorkbench(), evaluation);

        // open it.
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

        return null;
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
        defaultLabel.setText(Messages.getString("MetadataPropertyDescriptor.ClickToEdit")); //$NON-NLS-1$
    }

    /**
     * Return the label that invites the user to click the button.
     */
    protected Label getDefaultLabel() {
        return defaultLabel;
    }
}
