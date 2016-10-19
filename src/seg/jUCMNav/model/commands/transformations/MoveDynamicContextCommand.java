package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;

/**
 * Moves a dynamic context from one group to another. 
 * 
 * @author aprajita
 * 
 */
public class MoveDynamicContextCommand extends Command implements JUCMNavCommand {
	
	private DynamicContextGroup group;
    private DynamicContextGroup oldGroup;
    private DynamicContext dynContext;

    /**
     * Constructor
     */
    public MoveDynamicContextCommand(DynamicContextGroup targetGroup, DynamicContext dynContext) {
        this.dynContext = dynContext;
        this.group = targetGroup;
        this.oldGroup = (DynamicContextGroup) dynContext.getGroups().get(0);
        setLabel(Messages.getString("MoveDynamicContextCommand.MoveContext")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return dynContext!=null && group != oldGroup;
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
            oldGroup.getContexts().remove(dynContext);
        if (group!=null)
            group.getContexts().add(dynContext);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert dynContext != null : "pre not null"; //$NON-NLS-1$
        assert group==null || !group.getContexts().contains(dynContext) : "pre dynamic context not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert dynContext !=null : "post not null"; //$NON-NLS-1$
        assert group == null || group.getContexts().contains(dynContext) : "post dynamic context not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (group!=null)
            group.getContexts().remove(dynContext);
        if (oldGroup!=null)
            oldGroup.getContexts().add(dynContext);
        testPreConditions();
    }

    public DynamicContext getDynamicContext() {
        return dynContext;
    }


}
