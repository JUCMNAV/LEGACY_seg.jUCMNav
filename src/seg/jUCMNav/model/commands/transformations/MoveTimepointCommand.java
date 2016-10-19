package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * Moves a timepoint from one group to another. 
 * 
 * @author aprajita
 * 
 */
public class MoveTimepointCommand extends Command implements JUCMNavCommand {
	
	private TimepointGroup group;
    private TimepointGroup oldGroup;
    private Timepoint tp;

    /**
     * Constructor
     */
    public MoveTimepointCommand(TimepointGroup targetGroup, Timepoint tp) {
        this.tp = tp;
        this.group = targetGroup;
        this.oldGroup = (TimepointGroup) tp.getGroup();
        setLabel(Messages.getString("MoveTimepointCommand.MoveTimepoint")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return tp!=null && group != oldGroup;
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
        testPreConditions();
        if (oldGroup!=null)
            oldGroup.getTimepoints().remove(tp);
        if (group!=null)
            group.getTimepoints().add(tp);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert tp != null : "pre not null"; //$NON-NLS-1$
        assert group==null || !group.getTimepoints().contains(tp) : "pre timepoint not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert tp !=null : "post not null"; //$NON-NLS-1$
        assert group == null || group.getTimepoints().contains(tp) : "post timepoint not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (group!=null)
            group.getTimepoints().remove(tp);
        if (oldGroup!=null)
            oldGroup.getTimepoints().add(tp);
        testPreConditions();
    }

    public Timepoint getTimepoint() {
        return tp;
    }

}
