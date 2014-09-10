package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import seg.jUCMNav.Messages;

public class DeleteContributionChangeCommandHelper implements Command {


    private ContributionChange change;
    private Contribution contrib;
    private ContributionContext context;

    public DeleteContributionChangeCommandHelper(ContributionChange change) {
        this.change = change;
   //     setLabel(Messages.getString("DeleteContributionChangeCommand.DeleteContributionChange")); //$NON-NLS-1$
    }

    /**
     *  
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return change != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        context = change.getContext();
        contrib = change.getContribution();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        change.setContribution(null);
        change.setContext(null);
        if (contrib!=null) contrib.setContribution(contrib.getContribution()); // force UI refreshes. 

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert change!=null && context!=null && contrib != null : "post something is null"; //$NON-NLS-1$
        assert change.getContribution() == null || change.getContext() == null : "post link not broken"; //$NON-NLS-1$ 
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert change!=null && context!=null && contrib != null : "pre something is null"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        change.setContribution(contrib);
        change.setContext(context);
        if (contrib!=null) contrib.setContribution(contrib.getContribution()); // force UI refreshes. 

        testPreConditions();
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
