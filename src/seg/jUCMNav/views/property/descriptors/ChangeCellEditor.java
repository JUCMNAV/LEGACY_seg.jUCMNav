package seg.jUCMNav.views.property.descriptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeCustomDialog;
import seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeEditor;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.QuadraticChange;

/**
 * A cell editor that presents a button to open the Manage Change wizard
 * 
 * @author aprajita
 * @see seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeEditor
 */
public class ChangeCellEditor extends DialogCellEditor {
	

	private Change change;
	private String name;
	
	public Change getChange() {
        return change;
    }
	
	public void setChange(Change change) {
	        this.change = change;
	}


    // swt label to be displayed
    private Label defaultLabel;
    
    /**
     * Creates a Change cell editor.
     * 
     * @param parent
     *            container
     */
    public ChangeCellEditor(Composite parent, Change change, String valueToChange) {
        super(parent);
        this.change = change;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    	if (valueToChange.equals("startDate")) {
        	name = df.format(change.getStart());
        } else if (valueToChange.equals("endDate")) {
        	name = df.format(change.getEnd());
        } else if (valueToChange.equals("newValue")) {
        	if (change instanceof EnumChange)
        		name = ((EnumChange) change).getNewValue();
        	else if (change instanceof ConstantChange)
        		name = Integer.toString(((ConstantChange) change).getNewValue());
        	else if (change instanceof LinearChange)
        		name = Integer.toString(((LinearChange) change).getNewValue());
        } else if (valueToChange.equals("quadraticCoefficient")) {
        	name = Float.toString(((QuadraticChange) change).getQuadraticCoefficient());
        } else if (valueToChange.equals("linearCoefficient")) {
        	name = Float.toString(((QuadraticChange) change).getLinearCoefficient());
        } else if (valueToChange.equals("constant")) {
        	name = Float.toString(((QuadraticChange) change).getConstant());
        } else if (valueToChange.equals("formula")) {
        	name = ((FormulaChange) change).getFormula();
        } else 
        	name = "";
        
    }

    /**
     * Creates a Change cell editor.
     * 
     * @param parent
     *            container
     * @param style
     *            style of editor
     */
    public ChangeCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Opens the Manage Change wizard.
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        if (change == null)
            return null;
        ManageChangeEditor editor = new ManageChangeEditor(change.getContext().getUrnspec());
        StructuredSelection selection = (StructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView").getPart(false).getSite().getSelectionProvider().getSelection();
        // initialize it
        editor.init(PlatformUI.getWorkbench(), selection);

        // open it.
        ManageChangeCustomDialog dialog = new ManageChangeCustomDialog(shell, editor);
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
    	if (name == null)
    		name = "";
    	defaultLabel.setText(name + Messages.getString("MetadataPropertyDescriptor.ClickToEdit")); //$NON-NLS-1$
    }

    /**
     * Return the label that invites the user to click the button.
     */
    protected Label getDefaultLabel() {
        return defaultLabel;
    }

}
