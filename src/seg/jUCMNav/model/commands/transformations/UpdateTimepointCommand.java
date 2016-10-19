package seg.jUCMNav.model.commands.transformations;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * Command to update the selected Timepoint
 * 
 * @author aprajita
 */
public class UpdateTimepointCommand extends Command implements JUCMNavCommand {
	
	private URNspec urn;
    private Timepoint tp;
    private Date tpDate;
    private Date oldDate;
    boolean isInCompoundCommand = false;
    boolean aborted = false;

    /**
     * Constructor
     */
    public UpdateTimepointCommand(URNspec urn, Timepoint tp, Date tpDate) {
        this.urn = urn;
        this.tp = tp;
        this.tpDate = tpDate;
        setLabel(Messages.getString("UpdateTimepointCommand.updateTimepoint")); //$NON-NLS-1$
    }
    
    /**
   	 * 
   	 */
     public UpdateTimepointCommand(URNspec urn, Timepoint tp, Date tpDate, boolean isInCompoundCommand) {
    	 this.urn = urn;
         this.tp = tp;
         this.tpDate = tpDate;
         setLabel(Messages.getString("UpdateTimepointCommand.updateTimepoint")); //$NON-NLS-1$
    	 this.isInCompoundCommand = isInCompoundCommand;
       }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && tp != null;
    }

    public Timepoint getTimepoint() {
        return tp;
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
        oldDate = tp.getTimepoint();
        tp.setTimepoint(tpDate);
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && tp != null && tp.getGroup() != null && tpDate != null: "pre something null"; //$NON-NLS-1$
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && tp != null && tp.getGroup() != null && tpDate != null : "post something null"; //$NON-NLS-1$
        assert tp.getTimepoint().equals(tpDate) : "post date not equal";
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
    	if (aborted)
            return;
        testPostConditions();
        tp.setTimepoint(oldDate);
        testPreConditions();
    }


}
