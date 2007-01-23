package seg.jUCMNav.actions.scenarios;

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

    /**
     * @param part
     */
    public VariableInitializationsAction(IWorkbenchPart part) {
        super(part);
        setId(VARIABLEINITIALIZATIONS);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/properties.gif")); //$NON-NLS-1$
    }

    /**
     * Can change variable initializations if we've selected a scenario or one of its children.
     */
    protected boolean calculateEnabled() {
        initScenario();
        return scenario != null;
    }

    /**
     * Opens the {@link seg.jUCMNav.views.wizards.scenarios.ScenarioInitializations} wizard.
     * 
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