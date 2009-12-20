package seg.jUCMNav.views.property.descriptors;

import grl.kpimodel.Indicator;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.property.StackHelper;
import seg.jUCMNav.views.wizards.kpi.IndicatorGroupDialog;

/**
 * A cell editor that presents a button to open the IndicatorGroupEditor wizard
 * 
 * @author pchen
 */
public class IndicatorGroupCellEditor extends DialogCellEditor {

    // edit an indicator's groups
    private Indicator indicator;

    // swt label to be displayed
    private Label defaultLabel;

    /**
     * Creates a cell editor.
     * 
     * @param parent
     *            container
     */
    public IndicatorGroupCellEditor(Composite parent) {
        super(parent);
    }

    /**
     * Creates a cell editor.
     * 
     * @param parent
     *            container
     * @param style
     *            style of editor
     */
    public IndicatorGroupCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the indicator group dialog.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        IndicatorGroupDialog indDialog = new IndicatorGroupDialog(StackHelper.getDelegatingStack(), indicator);

        return null;
    }

    /**
     * 
     * @return the indicator for which we show the groups property.
     */
    protected Indicator getIndicator() {
        return indicator;
    }

    /**
     * 
     * @param indicator
     *            the indicator for which we show the groups property.
     */
    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
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
        defaultLabel.setText(Messages.getString("IndicatorGroupPropertyDescriptor.ClickToEdit")); //$NON-NLS-1$
    }

    /**
     * Return the label that invites the user to click the button.
     */
    protected Label getDefaultLabel() {
        return defaultLabel;
    }
}
