/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;

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
    
    
    private String type; 
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
        else {
        	setId(ADDENUMERATIONVARIABLE);
        	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ISR16.gif")); //$NON-NLS-1$
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
}
