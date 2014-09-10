package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import seg.jUCMNav.Messages;
import urn.URNlink;
import urn.URNspec;
import urncore.URNmodelElement;

public class DeleteURNlinkCommandHelper implements Command {

    

    private URNlink link;

    // the URNspec in which it is contained
    private URNspec urn;

    private URNmodelElement from;
    private URNmodelElement to;

    /**
     * 
     */
    public DeleteURNlinkCommandHelper(URNlink link) {
        this.link = link;
   //     setLabel(Messages.getString("DeleteURNlinkCommand.deleteUrnLink")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = link.getUrnspec();
        from = link.getFromElem();
        to = link.getToElem();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        link.setFromElem(null);
        link.setToElem(null);
        urn.getUrnLinks().remove(link);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && link != null && to != null && from != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert from.getFromLinks().contains(link) && to.getToLinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && link != null && to != null && from != null : "pre something is null"; //$NON-NLS-1$
        assert !urn.getUrnLinks().contains(link) : "pre urn contains link."; //$NON-NLS-1$
        assert !from.getFromLinks().contains(link) && !to.getToLinks().contains(link) : "pre element contains link"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        urn.getUrnLinks().add(link);
        link.setFromElem(from);
        link.setToElem(to);

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
