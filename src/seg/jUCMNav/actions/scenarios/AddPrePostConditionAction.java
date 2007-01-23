package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.IncludeConditionInScenarioCommand;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;

/**
 * Adds a pre/post condition to a given scenario and opens the editor.
 * 
 * @author jkealey
 */
public class AddPrePostConditionAction extends IncludeScenarioAction {

    public static final String ADDPRECONDITIONACTION = "seg.jUCMNav.AddPreConditionAction"; //$NON-NLS-1$
    public static final String ADDPOSTCONDITIONACTION = "seg.jUCMNav.AddPostConditionAction"; //$NON-NLS-1$
    private boolean isPreCondition;

    /**
     * Creates a pre/post condition and opens the code editor.
     * 
     * @param part
     *            jUCMNav
     */
    public AddPrePostConditionAction(IWorkbenchPart part, boolean isPreCondition) {
        super(part);
        this.isPreCondition = isPreCondition;
        if (isPreCondition)
            setId(ADDPRECONDITIONACTION);
        else
            setId(ADDPOSTCONDITIONACTION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/condition.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with code.
     */
    protected boolean calculateEnabled() {
        initScenario();
        return scenario != null;
    }

    /**
     * Creates a pre/post condition and opens the code editor.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        CodeEditor wizard = new CodeEditor();

        UCMNavMultiPageEditor editor = ((UCMNavMultiPageEditor) getWorkbenchPart());
        CommandStack cs = editor.getDelegatingCommandStack();
        IncludeConditionInScenarioCommand command = new IncludeConditionInScenarioCommand(scenario, isPreCondition);
        cs.execute(command);

        wizard.init(PlatformUI.getWorkbench(), null, command.getCondition());
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }

}