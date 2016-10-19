package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;

/**
 * Removes the selected Change from its DynamicContext
 * 
 * @author aprajita
 * 
 */
public class RemoveChangeCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext context;
	private Change changeToDelete;
    private URNspec urn;

    /**
     * Constructor
     */
    public RemoveChangeCommand(Change changeToDelete) {
        this.changeToDelete = changeToDelete;
        setLabel("RemoveChangeCommand.DeleteChange"); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
    	context = changeToDelete.getContext();
        urn = changeToDelete.getContext().getUrnspec();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        changeToDelete.getContext().getChanges().remove(changeToDelete);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && changeToDelete != null && context != null: "pre something null"; //$NON-NLS-1$
        assert context.getChanges().contains(changeToDelete) : "pre change not in context"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn!= null && changeToDelete != null && context != null : "post something null"; //$NON-NLS-1$
        assert !context.getChanges().contains(changeToDelete) : "post change in context"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        context.getChanges().add(changeToDelete);

        testPreConditions();
    }


}
