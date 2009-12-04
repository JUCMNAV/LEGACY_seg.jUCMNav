package seg.jUCMNav.views.wizards.scenarios;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Scenario initializations wizard.
 * 
 * @author jkealey
 */
public class ScenarioInitializations extends Wizard {
    private ScenarioInitializationsPage page;
    private ISelection selection;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public ScenarioInitializations() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("ScenarioInitializations.IncludeScenario")); //$NON-NLS-1$
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new ScenarioInitializationsPage(selection);
        addPage(page);
    }

    public CommandStack getCommandStack() {
        return ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        // Page modifies model during progressively... don't need to finish anything.

        return true;
    }

    public boolean performCancel() {
        try {
            for (int i = 0; i < page.getCommandCount(); i++)
                getCommandStack().undo();

            return true;
        } catch (Exception ex) {
            return false;
        }

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