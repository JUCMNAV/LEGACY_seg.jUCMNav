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
import seg.jUCMNav.views.wizards.metadata.MetadataEditor;
import urncore.URNmodelElement;

/**
 * A cell editor that presents a button to open the MetadataEditor wizard
 * 
 * @author pchen
 */
public class MetadataCellEditor extends DialogCellEditor {

    // edit a urn model element's metadata
    private URNmodelElement urnelem;

    // swt label to be displayed
    private Label defaultLabel;

    /**
     * Creates a code cell editor.
     * 
     * @param parent
     *            container
     */
    public MetadataCellEditor(Composite parent) {
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
    public MetadataCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the metadata wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        MetadataEditor wizard = new MetadataEditor();

        EObject defaultObj = urnelem == null ? null : (EObject) urnelem;

        // initialize it
        wizard.init(PlatformUI.getWorkbench(), null, defaultObj);

        // open it.
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

        return null;
    }

    /**
     * 
     * @return the urn model element for which we show the metadata property.
     */
    protected URNmodelElement getURNmodelElement() {
        return urnelem;
    }

    /**
     * 
     * @param urnelem
     *            the urn model element for which we show the metadata property.
     */
    public void setURNmodelElement(URNmodelElement urnelem) {
        this.urnelem = urnelem;
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
