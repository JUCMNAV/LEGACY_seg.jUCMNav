package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;

/**
 * Removes a DynamicContext from its group.
 * 
 * @author aprajita
 * 
 */
public class RemoveDynamicContextCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext dynContext;
    private DynamicContextGroup group;
    private URNspec urn;

    /**
     * Constructor 
     */
    public RemoveDynamicContextCommand(DynamicContext dynContext) {
        this.dynContext = dynContext;
        setLabel("RemoveDynamicContextCommand"); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        group = (DynamicContextGroup) dynContext.getGroups().get(0);
        urn = group.getUrnspec();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        group.getContexts().remove(dynContext);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && dynContext != null && group != null : "pre something null"; //$NON-NLS-1$
        assert group.getContexts().contains(dynContext) : "pre dynamic context in group"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn!= null && dynContext != null && group != null : "post something null"; //$NON-NLS-1$
        assert !group.getContexts().contains(dynContext) : "post dynamic context in group"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getContexts().add(dynContext);

        testPreConditions();
    }

}
