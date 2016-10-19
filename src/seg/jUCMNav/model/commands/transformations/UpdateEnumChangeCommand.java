package seg.jUCMNav.model.commands.transformations;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.EnumChange;

/**
 * Command to update the selected Enum Change
 * 
 * @author aprajita
 */
public class UpdateEnumChangeCommand extends Command implements JUCMNavCommand {
	
	private Object parent;
	private Date startDate, oldStartDate;
	private Date endDate, oldEndDate;
    boolean aborted = false;
    boolean isInCompoundCommand = false;
    private URNspec urn;
    private EnumChange changeToUpdate;
    private String newValue, oldValue;
    private DynamicContext dynContext;
    
    /**
	 * Constructor
	 */
    public UpdateEnumChangeCommand(Object parent, EnumChange changeToUpdate, Date startDate, Date endDate, String newValue, URNspec urn) {
        this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        setLabel(Messages.getString("UpdateEnumChangeCommand.UpdateEnumChange")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public UpdateEnumChangeCommand(Object parent, EnumChange changeToUpdate, boolean isInCompoundCommand,  Date startDate, Date endDate, String newValue, URNspec urn) {
    	this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        setLabel(Messages.getString("UpdateEnumChangeCommand.UpdateEnumChange")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && changeToUpdate != null && startDate != null && endDate != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (!canExecute()) {
            aborted = true; // another command in same compound command invalidated our preconditions
            return;
        }
        testPreConditions();
        oldStartDate = changeToUpdate.getStart();
        oldEndDate = changeToUpdate.getEnd();
        oldValue = changeToUpdate.getNewValue();
        dynContext = changeToUpdate.getContext();
        
        changeToUpdate.setStart(startDate);
    	changeToUpdate.setEnd(endDate);
    	if (changeToUpdate.getAffectedProperty().equals("Decomposition Type")) {
    		if (newValue.equals("0"))
    			changeToUpdate.setNewValue("AND");
    		else if (newValue.equals("1"))
    			changeToUpdate.setNewValue("OR");
    		else if (newValue.equals("2"))
    			changeToUpdate.setNewValue("XOR");
    		else
    			changeToUpdate.setNewValue(newValue);
    	} else
    		changeToUpdate.setNewValue(newValue);
        	
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && urn != null: "post not null"; //$NON-NLS-1$
        assert (dynContext.getChanges().contains(changeToUpdate)) && changeToUpdate.getStart().equals(startDate) 
        && changeToUpdate.getEnd().equals(endDate) : "post child not updated"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && changeToUpdate != null && urn != null: "pre not null"; //$NON-NLS-1$
        
        
    }
    
    public Change getUpdatedChange(){
    	return changeToUpdate;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        changeToUpdate.setStart(oldStartDate);
		changeToUpdate.setEnd(oldEndDate);
		changeToUpdate.setNewValue(oldValue);
		
        testPreConditions();
    }

}
