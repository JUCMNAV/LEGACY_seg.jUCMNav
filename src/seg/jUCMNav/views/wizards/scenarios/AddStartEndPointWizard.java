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

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.IncludePathNodeInScenarioCommand;
import ucm.map.PathNode;
import ucm.scenario.ScenarioDef;

/**
 * Wizard for including a scenario into another.
 * 
 * @author jkealey
 */
public class AddStartEndPointWizard extends Wizard {
	private AddStartEndPointWizardPage page;
	private ISelection selection;
	private boolean bStartPoint;
	/**
	 * The workbench page in which we are working
	 */
	protected IWorkbenchPage workbenchPage;

	/**
	 * Creates the editor
	 */
	public AddStartEndPointWizard(boolean bStartPoint) {
		super();
		setNeedsProgressMonitor(true);
		this.bStartPoint = bStartPoint;
		if (bStartPoint)
			this.setWindowTitle("Add Start Point");
		else
			this.setWindowTitle("Add End Point");

	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		page = new AddStartEndPointWizardPage(selection, this.bStartPoint);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		 final ScenarioDef parent = page.getParentScenario();
		 final PathNode child = page.getSelectedNode();
				
		
		 CommandStack cs = ((UCMNavMultiPageEditor)workbenchPage.getActiveEditor()).getDelegatingCommandStack();
		
		 // use a command to be undoable.
		 IncludePathNodeInScenarioCommand command = new IncludePathNodeInScenarioCommand(parent, child);
		 
		 if (command.canExecute())
			 cs.execute(command);

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
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
	}
}