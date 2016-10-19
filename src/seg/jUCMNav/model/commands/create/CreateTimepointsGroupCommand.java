package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.TimepointGroup;

/**
 * This command adds a new TimepointGroup
 * 
 * @author aprajita
 * 
 */
public class CreateTimepointsGroupCommand extends Command implements JUCMNavCommand {
	
	private URNspec urn;
    private TimepointGroup group;

    /**
     * Constructor
     */
    public CreateTimepointsGroupCommand(URNspec urn, TimepointGroup group) {
        this.urn = urn;
        this.group = group;
        setLabel(Messages.getString("CreateTimepointsGroupCommand.createTimepointsGroup")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
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
        urn.getTimepointGroups().add(group);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && group != null : "post not null"; //$NON-NLS-1$
        assert urn.getTimepointGroups().contains(group) : "post group not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getTimepointGroups().contains(group) : "pre groups not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getTimepointGroups().remove(group);
        testPreConditions();
    }

}
