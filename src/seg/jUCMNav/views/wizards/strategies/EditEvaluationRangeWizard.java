package seg.jUCMNav.views.wizards.strategies;

import grl.Evaluation;

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
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.model.commands.transformations.SetEvaluationRangeCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Wizard for editing an EvaluationRange
 * 
 * @author jkealey
 */
public class EditEvaluationRangeWizard extends Wizard {

    private EditEvaluationRangePage page;

    private Evaluation evaluation = null;

    private URNspec urn;

    /**
     * The workbench page1QueryResType in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public EditEvaluationRangeWizard(URNspec urn) {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("EditEvaluationRangeWizard.EditEvaluationRange")); //$NON-NLS-1$
        this.urn = urn;
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new EditEvaluationRangePage(workbenchPage, urn, evaluation);
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
        SetEvaluationRangeCommand cmd2 = new SetEvaluationRangeCommand(urn, page.getEvaluation(), page.getStartValue(), page.getEndValue(), page.getStepValue());
        if (cmd2.canExecute()) cmd.add(cmd2);

        if ( page.getEvaluation().getIntElement().getRefs().size() == 0 )
            EvaluationStrategyManager.getInstance().setIntentionalElementEvaluation(page.getEvaluation().getIntElement(), page.getStartValue());
        else
        {
            Vector v = new Vector();
            v.add(page.getEvaluation().getIntElement().getRefs().get(0));
            ChangeNumericalEvaluationCommand cmd3 = new ChangeNumericalEvaluationCommand(v, ChangeNumericalEvaluationCommand.USER_ENTRY, page.getStartValue(), cs);
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
    public void init(IWorkbench workbench, Evaluation evaluation) {
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
        this.evaluation = evaluation;
    }

}
