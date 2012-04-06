package seg.jUCMNav.views.wizards.strategies;

import grl.ContributionChange;

import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalContributionCommand;
import seg.jUCMNav.model.commands.transformations.SetContributionRangeCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Wizard for editing an ContributionRange
 * 
 * @author jkealey
 */
public class EditContributionRangeWizard extends Wizard {

    private EditContributionRangePage page;

    private ContributionChange change = null;

    private URNspec urn;

    /**
     * The workbench page1QueryResType in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public EditContributionRangeWizard(URNspec urn) {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("EditContributionRangeWizard.EditContributionRange")); //$NON-NLS-1$
        this.urn = urn;
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new EditContributionRangePage(workbenchPage, urn, change);
        addPage(page);
    }

    public boolean canFinish() {
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();

        CompoundCommand cmd = new CompoundCommand();
        SetContributionRangeCommand cmd2 = new SetContributionRangeCommand(urn, page.getContributionChange(), page.getStartValue(), page.getEndValue(), page.getStepValue());
        if (cmd2.canExecute()) cmd.add(cmd2);

        if ( page.getContributionChange().getContribution().getRefs().size() != 0 )
        {
            Vector v = new Vector();
            v.add(page.getContributionChange().getContribution().getRefs().get(0));
            ChangeNumericalContributionCommand cmd3 = new ChangeNumericalContributionCommand(v, ChangeNumericalContributionCommand.USER_ENTRY, page.getStartValue(), cs);
            if (cmd3.canExecute()) cmd.add(cmd3);
        }
         
        // use a command to be undoable.
        if (cmd.canExecute())
            cs.execute(cmd);
        
        
        // force recompute
        EvaluationStrategyManager.getInstance().calculateEvaluations(urn, cmd2.getRange());
        
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
    public void init(IWorkbench workbench, ContributionChange change) {
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
        this.change = change;
    }

}
