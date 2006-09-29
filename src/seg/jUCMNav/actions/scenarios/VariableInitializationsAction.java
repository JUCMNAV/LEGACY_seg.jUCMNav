package seg.jUCMNav.actions.scenarios;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.scenarios.ScenarioInitializations;

/**
 * Opens the variable initialization wizard.  
 * 
 * @author jkealey
 */
public class VariableInitializationsAction extends IncludeScenarioAction {

	public static final String VARIABLEINITIALIZATIONS = "seg.jUCMNav.VariableInitializationsAction"; //$NON-NLS-1$
	private EObject obj;

	/**
	 * @param part
	 */
	public VariableInitializationsAction(IWorkbenchPart part) {
		super(part);
		setId(VARIABLEINITIALIZATIONS);
		setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/properties.gif")); //$NON-NLS-1$
	}

	/**
	 * True if we've selected something with code. 	 */
	protected boolean calculateEnabled() {
		initScenario();
		return scenario!=null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		ScenarioInitializations wizard = new ScenarioInitializations();

		StructuredSelection selection = new StructuredSelection(scenario);
		wizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

}