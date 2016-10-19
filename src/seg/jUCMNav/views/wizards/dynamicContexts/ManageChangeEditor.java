package seg.jUCMNav.views.wizards.dynamicContexts;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import urn.URNspec;

/**
 * Wizard for managing Changes.
 * 
 * @author aprajita
 */
public class ManageChangeEditor extends Wizard {

	private ManageChangeEditorPage page;
	private ISelection selection;
	private URNspec urn;

	/**
	 * The workbench page in which we are working
	 */
	protected IWorkbenchPage workbenchPage;

	/**
	 * Creates the editor
	 */
	public ManageChangeEditor(URNspec urn) {
		super();
		this.urn = urn;
		setNeedsProgressMonitor(true);
		this.setWindowTitle(Messages.getString("ManageChange.ManageChange")); //$NON-NLS-1$

	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		page = new ManageChangeEditorPage(selection, urn, workbenchPage);
		addPage(page);
		
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		page.resetPage();

		return false;
	}
	
	@Override
	public boolean canFinish() {
        return true;
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
