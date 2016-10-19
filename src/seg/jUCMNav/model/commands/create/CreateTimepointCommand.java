package seg.jUCMNav.model.commands.create;

import java.util.Date;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * This command adds a Timepoint to the selected TimepointGroup
 * 
 * @author aprajita
 * 
 */
public class CreateTimepointCommand extends Command implements JUCMNavCommand {
	
	private URNspec urn;
    private TimepointGroup group;
    private Date tpDate;
    private Timepoint tp;
    boolean isInCompoundCommand = false;
    boolean aborted = false;

    /**
     *  Constructor
     */
    public CreateTimepointCommand(URNspec urn, TimepointGroup group, Date tpDate) {
        this.urn = urn;
        this.group = group;
        this.tpDate = tpDate;
        setLabel(Messages.getString("CreateTimepointCommand.createTimepoint")); //$NON-NLS-1$
    }
    
    /**
   	 * 
   	 */
     public CreateTimepointCommand(URNspec urn, TimepointGroup group, Date tpDate, boolean isInCompoundCommand) {
    	 this.urn = urn;
         this.group = group;
         this.tpDate = tpDate;
         setLabel(Messages.getString("CreateTimepointCommand.createTimepoint")); //$NON-NLS-1$
    	 this.isInCompoundCommand = isInCompoundCommand;
       }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
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
        
        //If a Timepoint with same date exists in that TimepointGroup, don't create a new Timepoint
        for (Iterator iter = group.getTimepoints().iterator(); iter.hasNext();) {
    		Timepoint selectedTp = (Timepoint) iter.next();
    		if (selectedTp.getTimepoint().equals(tpDate))
    			return;
    		
    	}
        tp = (Timepoint) ModelCreationFactory.getNewObject(urn, Timepoint.class);
        tp.setTimepoint(tpDate);
        group.getTimepoints().add(tp);
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && tp != null && group != null && tpDate != null: "pre something null"; //$NON-NLS-1$
        assert !group.getTimepoints().contains(tp) : "pre timepoint in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && tp != null && group != null && tpDate != null : "post something null"; //$NON-NLS-1$
        assert group.getTimepoints().contains(tp) : "post timepoint not in model"; //$NON-NLS-1$
        assert tp.getTimepoint().equals(tpDate) : "post date not equal";
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
    	if (aborted)
            return;
        testPostConditions();
        group.getTimepoints().remove(tp);
        testPreConditions();
    }

}
