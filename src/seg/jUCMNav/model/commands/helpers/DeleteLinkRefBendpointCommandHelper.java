package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.LinkRef;
import grl.LinkRefBendpoint;
import seg.jUCMNav.Messages;

public class DeleteLinkRefBendpointCommandHelper implements Command {

    
    LinkRefBendpoint oldBendpoint;
    LinkRef link;

    /**
     * Constructor
     * 
     * @param oldBendpoint
     */
    public DeleteLinkRefBendpointCommandHelper(LinkRefBendpoint oldBendpoint) {
        this.oldBendpoint = oldBendpoint;
        link = oldBendpoint.getLinkref();

      //  setLabel(Messages.getString("DeleteLinkRefBendpointCommand.DeleteLinkRefBendpoint")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        oldBendpoint.setLinkref(null);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldBendpoint != null : "Pre bendpoint is null"; //$NON-NLS-1$
        assert link != null : "Pre link is null"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() == link : "Pre linkref"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldBendpoint != null : "Post bendpoint is null"; //$NON-NLS-1$
        assert link != null : "Post link is null"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() != link : "Pre linkref"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        oldBendpoint.setLinkref(link);
        testPreConditions();
    }

    @Override
    public boolean canExecute() {
        // TODO Auto-generated method stub
        return false;
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
