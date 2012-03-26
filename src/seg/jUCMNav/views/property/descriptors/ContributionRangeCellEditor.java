package seg.jUCMNav.views.property.descriptors;

import grl.ContributionChange;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.strategies.EditContributionRangeWizard;
import urn.URNspec;

/**
 * A cell editor that presents a button to open the ContributionRange wizard
 * 
 * @author jkealey
 */
public class ContributionRangeCellEditor extends DialogCellEditor {

    private ContributionChange change;

    public ContributionChange getEvaluation() {
        return change;
    }

    public void setContributionChange(ContributionChange c) {
        this.change = c;
    }

    private Label defaultLabel;

    /**
     * Creates a code cell editor.
     * 
     * @param parent
     *            container
     */
    public ContributionRangeCellEditor(Composite parent) {
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
    public ContributionRangeCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the metadata wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        if (change == null || change.getContribution() == null || change.getContribution().getGrlspec()  == null)
            return null;
        URNspec urn = change.getContribution().getGrlspec().getUrnspec();
        EditContributionRangeWizard wizard = new EditContributionRangeWizard(urn);

        // initialize it
        wizard.init(PlatformUI.getWorkbench(), change);

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
