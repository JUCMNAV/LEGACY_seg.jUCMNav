package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.EnumerationTypeTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.wizards.scenarios.AddVariableWizard;
import seg.jUCMNav.views.wizards.scenarios.EditEnumerationsWizard;

/**
 * Creates a global variable to the UCMspec
 * 
 * @author jkealey
 * 
 */
public class AddVariableAction extends URNSelectionAction {

    public static final String ADDINTVARIABLE = "Add Int Variable"; //$NON-NLS-1$
    public static final String ADDBOOLVARIABLE = "Add Boolean Variable"; //$NON-NLS-1$
    public static final String ADDENUMERATIONVARIABLE = "Add Enumeration Variable"; //$NON-NLS-1$
    public static final String ADDVARIABLEWIZARD = "Add Variable Wizard"; // $NON-NLS-1$ //$NON-NLS-1$

    private String type;
    private boolean wizard = false;

    /**
     * 
     * Creates a global variable to the UCMspec
     * 
     * @param part
     * @param type
     *            a type from the constants defined in {@link seg.jUCMNav.scenarios.ScenarioUtils} to define if this action will add a new boolean, integer,
     *            enumeration variable. If type is unknown, the wizard will prompt for the user to select the type.
     */
    public AddVariableAction(IWorkbenchPart part, String type) {
        super(part);
        this.type = type;

        if (ScenarioUtils.sTypeBoolean.equals(type)) {
            setId(ADDBOOLVARIABLE);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Boolean16.gif")); //$NON-NLS-1$
        } else if (ScenarioUtils.sTypeInteger.equals(type)) {
            setId(ADDINTVARIABLE);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Integer16.gif")); //$NON-NLS-1$
        } else if (ScenarioUtils.sTypeEnumeration.equals(type)) {
            setId(ADDENUMERATIONVARIABLE);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Enumeration16.gif")); //$NON-NLS-1$
            wizard = true;
        } else {
            wizard = true;
            setId(ADDVARIABLEWIZARD);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/plus.gif")); //$NON-NLS-1$
        }

    }

    /**
     * We need to be a VariableListTreeEditPart. (Can't use a model element because ELists don't have listeners)
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (!(sel.getUrnspec() != null && sel.getUCMspec() != null && getSelectedObjects().size() == 1))
            return false;

        if (getSelectedObjects().get(0) instanceof VariableListTreeEditPart) {
            VariableListTreeEditPart part = (VariableListTreeEditPart) getSelectedObjects().get(0);
            return !(getId().equals(ADDENUMERATIONVARIABLE) ^ part.isEnumerations());
        } else
            return (getSelectedObjects().get(0) instanceof EnumerationTypeTreeEditPart) && getId().equals(ADDENUMERATIONVARIABLE);

    }

    /**
     * @return a {@link seg.jUCMNav.model.commands.create.CreateVariableCommand}.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        CreateVariableCommand create = new CreateVariableCommand(sel.getUrnspec(), type);

        return create;
    }

    /**
     * 
     * We can either launch the {@link seg.jUCMNav.views.wizards.scenarios.EditEnumerationsWizard} or the
     * {@link seg.jUCMNav.views.wizards.scenarios.AddVariableWizard}. Executes the command returned by getCommand();
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        if (wizard) {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

            Wizard _wizard;
            StructuredSelection selection = new StructuredSelection(getSelectedObjects());

            if (ScenarioUtils.sTypeEnumeration.equals(type)) {
                _wizard = new EditEnumerationsWizard();
                ((EditEnumerationsWizard) _wizard).init(PlatformUI.getWorkbench(), selection);
            } else {
                _wizard = new AddVariableWizard();

                ((AddVariableWizard) _wizard).init(PlatformUI.getWorkbench(), selection);

            }
            WizardDialog dialog = new WizardDialog(shell, _wizard);
            dialog.open();

        } else
            super.run();
    }
}
