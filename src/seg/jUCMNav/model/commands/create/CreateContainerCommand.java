package seg.jUCMNav.model.commands.create;

import grl.Actor;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Component;
import urncore.IURNContainer;

/**
 * This command adds a new IURNContainer to the URNspec.
 * 
 * @author jkealey
 * 
 */
public class CreateContainerCommand extends Command implements JUCMNavCommand {
    private IURNContainer container;
    private URNspec urn;
    private int oldCount;

    public CreateContainerCommand(URNspec urn, IURNContainer container) {
        this.urn = urn;
        this.container = container;
        setLabel(Messages.getString("CreateContainerCommand_CreateContainer")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && container != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (container instanceof Component)
            oldCount = urn.getUrndef().getComponents().size();
        else if (container instanceof Actor)
            oldCount = urn.getGrlspec().getActors().size();
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (container instanceof Component)
            urn.getUrndef().getComponents().add(container);
        else if (container instanceof Actor)
            urn.getGrlspec().getActors().add(container);

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUrndef() != null && container != null : "post not null"; //$NON-NLS-1$
        assert urn.getUrndef().getComponents().contains(container) || urn.getGrlspec().getActors().contains(container) : "post container not in model"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUrndef() != null && container != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUrndef().getComponents().contains(container) && !urn.getGrlspec().getActors().contains(container) : "post container not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (container instanceof Component)
            urn.getUrndef().getComponents().remove(container);
        else if (container instanceof Actor)
            urn.getGrlspec().getActors().remove(container);
        testPreConditions();
    }
}