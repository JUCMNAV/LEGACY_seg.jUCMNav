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

import seg.jUCMNav.Messages;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * Wizard for editing resources.
 * 
 * Can be called from component (ref) or - for now - responsibility (ref).
 * 
 * @author jack
 */
public class ManageResources extends Wizard {

    // first page1QueryResType: chose between active & passive
    private ManageResourcesPage page1QueryResType;

    private ISelection selection;

    private EObject defaultSelected;

    // for when selection is componentRef (or similar)
    private Component component = null;

    // for when selection is responsibility
    private Responsibility responsibility = null;

    // to initialize the ManageResources(Wizzard)Page
    private URNspec urn;

    /**
     * The workbench page1QueryResType in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public ManageResources(URNspec urn) {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("ManageResources.ManageResources")); //$NON-NLS-1$
        this.urn = urn;
    }

    /**
     * Adding the page1QueryResType to the wizard.
     */
    public void addPages() {
        page1QueryResType = new ManageResourcesPage(workbenchPage, urn, selection, defaultSelected);
        addPage(page1QueryResType);
    }

    public boolean canFinish() {
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
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
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, EObject defaultSelected) {
        this.defaultSelected = defaultSelected;
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
    }

}
