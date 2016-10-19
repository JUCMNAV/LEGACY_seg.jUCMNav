package seg.jUCMNav.views.property.descriptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.dynamicContexts.UpdateTimepointEditor;
import urn.dyncontext.Timepoint;

/**
 * A cell editor that presents a button to open the UpdateTimepointEditor wizard
 * 
 * @author aprajita
 * @see seg.jUCMNav.views.wizards.dynamicContexts.UpdateTimepointEditor
 */
public class DateCellEditor extends DialogCellEditor {
	
	private Timepoint tp;
	
	public Timepoint getTimepoint() {
        return tp;
    }
	
	public void setTimepoint(Timepoint tp) {
	        this.tp = tp;
	}


    // swt label to be displayed
    private Label defaultLabel;
    
    /**
     * Creates a date cell editor.
     * 
     * @param parent
     *            container
     */
    public DateCellEditor(Composite parent, Timepoint tp) {
        super(parent);
        this.tp = tp;
    }

    /**
     * Creates a date cell editor.
     * 
     * @param parent
     *            container
     * @param style
     *            style of editor
     */
    public DateCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the update timepoint wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        if (tp == null)
            return null;
        UpdateTimepointEditor editor = new UpdateTimepointEditor();

        // initialize it
        editor.init(PlatformUI.getWorkbench(), tp);

        // open it.
        WizardDialog dialog = new WizardDialog(shell, editor);
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
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	String name = "";
    	if (tp != null)
    		name = df.format(tp.getTimepoint());
        defaultLabel.setText(name + Messages.getString("MetadataPropertyDescriptor.ClickToEdit")); //$NON-NLS-1$
    }

    /**
     * Return the label that invites the user to click the button.
     */
    protected Label getDefaultLabel() {
        return defaultLabel;
    }

}
