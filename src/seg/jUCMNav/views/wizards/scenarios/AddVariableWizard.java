package seg.jUCMNav.views.wizards.scenarios;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.ScenarioDef;

/**
 * Wizard for adding a new variable to the model.
 * 
 * @author jkealey
 */
public class AddVariableWizard extends Wizard {
    private AddVariableWizardPage page;
    private AddVariableWizardEnumsPage page2;
    private AddVariableWizardInitsPage page3;
    private ISelection selection;
    /*
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public AddVariableWizard() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("AddVariableWizard.NewVariableWizard")); //$NON-NLS-1$

    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        SelectionHelper helper = new SelectionHelper(((StructuredSelection) selection).toList());
        page = new AddVariableWizardPage(helper.getUrnspec());
        page2 = new AddVariableWizardEnumsPage(helper.getUrnspec());
        page3 = new AddVariableWizardInitsPage(helper.getUrnspec());
        addPage(page);
        addPage(page2);
        addPage(page3);
    }

    public boolean canFinish() {
        if (page.isPageComplete() && page3.isPageComplete() && page.getVariableType() != null && !page.getVariableType().equals(ScenarioUtils.sTypeEnumeration))
            return true;
        else
            return super.canFinish();
    }

    public IWizardPage getNextPage(IWizardPage page) {
        if (page == this.page) {
            if (this.page.getVariableType() != null && this.page.getVariableType().equals(ScenarioUtils.sTypeEnumeration))
                return page2;
            else
                return page3;
        } else
            return super.getNextPage(page);
    }

    public IWizardPage getPreviousPage(IWizardPage page) {
        if (page == this.page3) {
            if (this.page.getVariableType().equals(ScenarioUtils.sTypeEnumeration))
                return page2;
            else
                return this.page;
        } else
            return super.getPreviousPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {

        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand command = new CompoundCommand();

        CreateVariableCommand createCmd = new CreateVariableCommand(page.urn, page.getVariableType(), page.getVariableName());
        if (page.getVariableType() == ScenarioUtils.sTypeEnumeration)
            createCmd.setEnumerationType(page2.getSelectedEnumerationType());
        command.add(createCmd);

        for (Iterator iter = page3.getInitializations().keySet().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            command.add(new CreateVariableInitializationCommand(page.getVariableName(), scenario, page3.getInitializations().get(scenario).toString()));
        }

        // use a command to be undoable.
        if (command.canExecute())
            cs.execute(command);

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