package seg.jUCMNav.model.commands.helpers;

import grl.StrategiesGroup;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import urn.URNspec;

public class CreateStrategiesGroupCommandHelper implements Command {
    private URNspec urn;
    private StrategiesGroup group;

    /**
     * 
     */
    public CreateStrategiesGroupCommandHelper(URNspec urn, StrategiesGroup group) {
        this.urn = urn;
        this.group = group;
      //  setLabel(Messages.getString("CreateStrategiesGroupCommand.createStrategiesGroup")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
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
        urn.getGrlspec().getGroups().add(group);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "post not null"; //$NON-NLS-1$
        assert urn.getGrlspec().getGroups().contains(group) : "post group not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getGrlspec().getGroups().contains(group) : "pre groups not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getGroups().remove(group);
        testPreConditions();
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return true;
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