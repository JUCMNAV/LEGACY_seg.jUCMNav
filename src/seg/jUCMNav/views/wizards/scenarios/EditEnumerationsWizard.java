package seg.jUCMNav.views.wizards.scenarios;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;

/**
 * Wizard for adding a new variable to the model.
 * 
 * @author jkealey
 */
public class EditEnumerationsWizard extends Wizard {
    private AddVariableWizardEnumsPage page2;
    private ISelection selection;
    /*
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public EditEnumerationsWizard() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("EditEnumerationsWizard.EditEnumerations")); //$NON-NLS-1$

    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        SelectionHelper helper = new SelectionHelper(((StructuredSelection) selection).toList());
        page2 = new AddVariableWizardEnumsPage(helper.getUrnspec());
        addPage(page2);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {

        return true;
    }

    public boolean performCancel() {
        page2.undoAll();
        return super.performCancel();
    }

    /**
     * Throws an error using the message.
     * 
     * @param message
     *            the error message.
     * @throws CoreException
     *             the generated exception.
     */
    private void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.OK, message, null); //$NON-NLS-1$
        throw new CoreException(status);
    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
    }

}