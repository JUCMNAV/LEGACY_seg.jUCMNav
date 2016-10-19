package seg.jUCMNav.actions.dynamicContexts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.views.wizards.dynamicContexts.AddTimepointEditor;

/**
 * Adds a timepoint in a timepoint group.
 * 
 * @author aprajita
 * 
 */
public class AddTimepointAction extends URNSelectionAction {
	
	public static final String ADDTIMEPOINT = "Add Timepoint"; //$NON-NLS-1$

    /**
     * Adds a timepoint in a timepoint group.
     * 
     * @param part
     *            jucmnav
     */
    public AddTimepointAction(IWorkbenchPart part) {
        super(part);

        setId(ADDTIMEPOINT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grlstrat16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a TimepointGroup.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getTimepontGroup() != null && sel.getTimepoint() == null;
    }

    /**
     * Opens the Timepoint editor.
     * 
     * @see seg.jUCMNav.views.wizards.dynamicContexts.AddTimepointEditor
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
    	SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        AddTimepointEditor wizard = new AddTimepointEditor();

        StructuredSelection selection = new StructuredSelection(sel.getSelection());
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }
}
