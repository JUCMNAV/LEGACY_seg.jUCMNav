package seg.jUCMNav.views.wizards.performance;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

/**
 * Wizard for editing responsibility's demands (resource usage).
 * 
 * @author jack
 */
public class ManageDemand extends Wizard {

    private ManageDemandPage page1ManageDemandPage;
    private ISelection selection;
    private EObject defaultSelected;

    /**
     * The workbench in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public ManageDemand() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle("Manage Demand"); //$NON-NLS-1$
    }

    /**
     * Adding the page1QueryResType to the wizard.
     */
    public void addPages() {
        page1ManageDemandPage = new ManageDemandPage(workbenchPage, defaultSelected);
        addPage(page1ManageDemandPage);
    }

    /*
     * TODO: cannot finish until can undo all, i.e. compound command _js_
     */
    public boolean canFinish() {
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. But we are never finishing (just cancelling) _js_
     */
    public boolean performFinish() {
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
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, EObject defaultSelected) {
        this.defaultSelected = defaultSelected;
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
    }

}
