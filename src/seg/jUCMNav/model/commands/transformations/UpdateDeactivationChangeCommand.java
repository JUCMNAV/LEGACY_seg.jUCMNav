package seg.jUCMNav.model.commands.transformations;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;

/**
 * Command to update the selected Deactivation Change
 * 
 * @author aprajita
 */
public class UpdateDeactivationChangeCommand extends Command implements JUCMNavCommand {
	
	private Object parent;
	private String selectedChange;
	private Date startDate, oldStartDate;
	private Date endDate, oldEndDate;
    boolean aborted = false;
    boolean isInCompoundCommand = false;
    private URNspec urn;
    private DeactivationChange changeToUpdate;
    private DynamicContext dynContext;
    
    /**
	 *  Constructor
	 */
    public UpdateDeactivationChangeCommand(Object parent, DeactivationChange changeToUpdate, String selectedChange, Date startDate, Date endDate, URNspec urn) {
        this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.selectedChange = selectedChange;
        this.startDate = startDate;
        this.endDate = endDate;
        setLabel(Messages.getString("UpdateDeactivationChangeCommand.UpdateDeactivationChange")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public UpdateDeactivationChangeCommand(Object parent, DeactivationChange changeToUpdate, String selectedChange, boolean isInCompoundCommand, Date startDate, Date endDate, URNspec urn) {
    	this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.selectedChange = selectedChange;
        this.startDate = startDate;
        this.endDate = endDate;
        setLabel(Messages.getString("UpdateDeactivationChangeCommand.UpdateDeactivationChange")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && changeToUpdate != null && selectedChange != null && startDate != null && endDate != null;
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
        dynContext = changeToUpdate.getContext();
        
        if (selectedChange.equals("Deactivation Change")){
    		changeToUpdate.setStart(startDate);
    		changeToUpdate.setEnd(endDate);
        	
        }
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && selectedChange != null && urn != null: "post not null"; //$NON-NLS-1$
        assert (dynContext.getChanges().contains(changeToUpdate)) && changeToUpdate.getStart().equals(startDate) 
        && changeToUpdate.getEnd().equals(endDate) : "post child not updated"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && changeToUpdate != null && selectedChange != null && urn != null: "pre not null"; //$NON-NLS-1$
        
        
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
		
        testPreConditions();
    }

}
