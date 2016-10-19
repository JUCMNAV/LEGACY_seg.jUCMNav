package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.ScenarioDef;
import urn.dyncontext.DynamicContext;

/**
 * This command includes a DynamicContext within another Dynamic Context
 * 
 * @author aprajita
 * 
 */
public class IncludeDynamicContextCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext parent, child;
    boolean aborted = false;
    boolean isInCompoundCommand = false;

    /**
	 * Constructor
	 */
    public IncludeDynamicContextCommand(DynamicContext parent, DynamicContext child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeDynamicContextCommand.IncludeDynamicContext")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public IncludeDynamicContextCommand(DynamicContext parent, DynamicContext child, boolean isInCompoundCommand) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeDynamicContextCommand.IncludeDynamicContext")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && (isInCompoundCommand || DynamicContextsUtils.getPossibleIncludedDynamicContexts(parent).contains(child));
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
        
        //Include the child Dynamic Context within the parent Dynamic Context
        this.parent.getIncludedContexts().add(child);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post not null"; //$NON-NLS-1$
        assert parent.getIncludedContexts().contains(child) : "post context not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre not null"; //$NON-NLS-1$
        assert !parent.getIncludedContexts().contains(child) : "pre child not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.getIncludedContexts().remove(child);
        testPreConditions();
    }

}
