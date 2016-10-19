package seg.jUCMNav.model.commands.create;

import java.util.List;

import org.eclipse.gef.commands.Command;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.dyncontext.DynamicContext;

/**
 * This command includes a ContributionContext to the selected Dynamic Context
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextContributionContextCommand extends Command implements JUCMNavCommand {
	

	private DynamicContext parent;
	private ContributionContext child;
    boolean aborted = false;
    boolean isInCompoundCommand = false;

    /**
	 * Constructor
	 */
    public AddDynamicContextContributionContextCommand(DynamicContext parent, ContributionContext child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("AddDynamicContextContributionContextCommand.AddDynamicContextContributionContext")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public AddDynamicContextContributionContextCommand(DynamicContext parent, ContributionContext child, boolean isInCompoundCommand) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("AddDynamicContextContributionContextCommand.AddDynamicContextContributionContext")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
    	List contriContexts = getAllContributionContexts(parent);
        return parent != null && child != null && (isInCompoundCommand || contriContexts.contains(child)) 
        		&& (parent.getContributionContext() == null);
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
        if (parent.getContributionContext() == null)
        	this.parent.setContributionContext(child);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post not null"; //$NON-NLS-1$
        assert  parent.getContributionContext() == child : "post context not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre not null"; //$NON-NLS-1$
        assert !( parent.getContributionContext() == child) : "pre context not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.setContributionContext(null);
        testPreConditions();
    }
    
    /**
     * 
     * @param parent
     * 			the dynamic context
     * @return the list of all available contribution contexts 
     */
    private List getAllContributionContexts(DynamicContext parent){
    	List contriGroups = null;
    	if (parent != null)
    		contriGroups = parent.getUrnspec().getGrlspec().getContributionGroups();
    	List contriContexts = null;
    	if (contriGroups.size() > 0) {
	    	for (int i = 0; i < contriGroups.size(); i++){
	    		if (((ContributionContextGroup) contriGroups.get(i)).getContribs().size() > 0) {
	    			if (i == 0)
	    				contriContexts = ((ContributionContextGroup) contriGroups.get(i)).getContribs();
	    			else
	    				contriContexts.addAll(((ContributionContextGroup) contriGroups.get(i)).getContribs());
	    		}
	    			
	    	}
    	}
    	return contriContexts;
    }


}
