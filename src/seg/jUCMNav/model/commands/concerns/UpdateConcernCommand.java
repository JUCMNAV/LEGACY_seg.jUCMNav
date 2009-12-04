package seg.jUCMNav.model.commands.concerns;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Concern;

/**
 * This command changes the name and description of a concern
 * 
 * @author gunterm
 */
public class UpdateConcernCommand extends Command implements JUCMNavCommand {

    // the concern to be changed
    private Concern concern;
    // the new info
    private String name;
    private String description;
    // undo information
    private String oldName;
    private String oldDescription;

    /**
     * @param concern
     *            to be changed
     * @param name
     *            of the concern
     * @param description
     *            of the concern
     */
    public UpdateConcernCommand(Concern concern, String name, String description) {
        this.concern = concern;
        this.name = name;
        this.description = description;
        setLabel(Messages.getString("UpdateConcernCommand.UpdateConcern")); //$NON-NLS-1$
    }

    /**
     * checks all conditions of testPreConditions that can be checked before execute()
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return testConditionNotNull();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // remember for undo
        oldName = concern.getName();
        oldDescription = concern.getDescription();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        concern.setName(name);
        concern.setDescription(description);
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        concern.setName(oldName);
        concern.setDescription(oldDescription);
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert concern != null && concern.getName() != null && concern.getDescription() != null : "post not null"; //$NON-NLS-1$
        assert concern.getName().equals(name) && concern.getDescription().equals(description) : "post concern name/description changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert testConditionNotNull() : "pre not null"; //$NON-NLS-1$
        assert concern.getName().equals(oldName) && concern.getDescription().equals(oldDescription) : "pre concern name/description are original"; //$NON-NLS-1$
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNotNull() {
        return concern != null && name != null && description != null;
    }

}