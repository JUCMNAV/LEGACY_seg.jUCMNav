/**
 * 
 */
package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.wizards.scenarios.AddVariableWizard;

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
    private boolean wizard=false;
    /**
     * @param part
     */
    public AddVariableAction(IWorkbenchPart part, String type) {
        super(part);
        this.type = type;

        
        if (ScenarioUtils.sTypeBoolean.equals(type)) {
        	setId(ADDBOOLVARIABLE);
        	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")); //$NON-NLS-1$
        }
        else if (ScenarioUtils.sTypeInteger.equals(type)) {
        	setId(ADDINTVARIABLE);
        	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GoalTag16.gif")); //$NON-NLS-1$
        }
        else if (ScenarioUtils.sTypeEnumeration.equals(type)) {
        	setId(ADDENUMERATIONVARIABLE);
        	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ISR16.gif")); //$NON-NLS-1$
        } else
        {
        	wizard=true;
        	setId(ADDVARIABLEWIZARD);
        	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/plus.gif")); //$NON-NLS-1$
        }
        	
        	
    }

    /**
     * We need to be a VariableListTreeEditPart. (Can't use a model element because ELists don't have listeners)
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getUCMspec()!=null && getSelectedObjects().size()==1 && (getSelectedObjects().get(0) instanceof VariableListTreeEditPart);
    }
    
    /**
     * We need to return the command to be execute
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        CreateVariableCommand create = new CreateVariableCommand(sel.getUrnspec(), type);

        return create;
    }
    
    /**
     * Executes the command returned by getCommand();
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
    	if (wizard) {
    		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    		AddVariableWizard wizard = new AddVariableWizard();

    		StructuredSelection selection = new StructuredSelection(getSelectedObjects());
    		wizard.init(PlatformUI.getWorkbench(), selection);
    		WizardDialog dialog = new WizardDialog(shell, wizard);
    		dialog.open();

    	}
    	else
    		super.run();
    }
}