package seg.jUCMNav.views.wizards.dynamicContexts;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.AddDynamicContextScenarioCommand;
import ucm.scenario.ScenarioDef;
import urn.dyncontext.DynamicContext;

/**
 * Wizard for including a Scenario into a DynamicContext.
 * 
 * @author aprajita
 */
public class AddDynamicContextScenario extends Wizard {
	
	private AddDynamicContextScenarioPage page;
    private ISelection selection;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public AddDynamicContextScenario() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("AddDynamicContextScenario.AddDynamicContextScenario")); //$NON-NLS-1$

    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new AddDynamicContextScenarioPage(selection);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        final DynamicContext parent = page.getParentDynamicContext();
        final ScenarioDef scenario = page.getChildScenario();

        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();

        CompoundCommand cmd = new CompoundCommand();
        
        ScenarioDef child = scenario;
        AddDynamicContextScenarioCommand command = new AddDynamicContextScenarioCommand(parent, child);
        if (command.canExecute())
            cmd.add(command);
        
        // use a command to be undoable.
        if (cmd.canExecute())
            cs.execute(cmd);

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
