package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;

/**
 * This command adds a new DynamicContext to the selected Dynamic Context Group
 * 
 * @author aprajita
 * 
 */
public class CreateDynamicContextCommand extends Command implements JUCMNavCommand {
	
	private URNspec urn;
    private DynamicContextGroup group;
    private DynamicContext context;

    /**
     *  Constructor
     */
    public CreateDynamicContextCommand(URNspec urn, DynamicContextGroup group) {
        this.urn = urn;
        this.group = group;
        
        //Create a new Dynamic Context
        context = (DynamicContext) ModelCreationFactory.getNewObject(urn, DynamicContext.class);
        setLabel(Messages.getString("CreateDynamicContextCommand.createDynamicContext")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
    }

    public DynamicContext getDynamicContext() {
        return context;
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
        
        //Add the new Dynamic Context to the URN and DynamicContextGroup
        urn.getDynamicContexts().add(context);
        group.getContexts().add(context);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && context != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !group.getContexts().contains(context) : "pre strategy in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && context != null && group != null : "post not null"; //$NON-NLS-1$
        assert group.getContexts().contains(context) : "post strategy not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getDynamicContexts().remove(context);
        group.getContexts().remove(context);
        testPreConditions();
    }
}
