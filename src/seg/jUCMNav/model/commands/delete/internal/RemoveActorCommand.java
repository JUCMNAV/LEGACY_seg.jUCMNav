/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.Actor;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Delete an actor definition. The definition should have no references
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveActorCommand extends Command implements JUCMNavCommand {
    // the actor to delete
    private Actor actor;

    // the URNspec in which it is contained
    private URNspec urn;

    /**
     * 
     */
    public RemoveActorCommand(Actor actor) {
        setActorDef(actor);
        setLabel(Messages.getString("RemoveActorCommand.deleteActor")); //$NON-NLS-1$
    }

    /**
     * Only if not referenced.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getActorDef() != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = getActorDef().getGrlspec().getUrnspec();

        redo();
    }

    /**
     * @return the actor to delete
     */
    public Actor getActorDef() {
        return actor;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // break relations and remove compDef
        urn.getGrlspec().getActors().remove(getActorDef());

        testPostConditions();
    }

    /**
     * 
     * @param actor
     *            the actor to delete.
     */
    public void setActorDef(Actor actor) {
        this.actor = actor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // lists could be empty but not null
        assert getActorDef() != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert getActorDef().getContRefs().size() == 0 : "pre can't delete if still referenced."; //$NON-NLS-1$
        assert urn.getGrlspec().getActors().contains(getActorDef()) : "pre actor in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getActorDef() != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert getActorDef().getContRefs().size() == 0 : "post there are still children"; //$NON-NLS-1$
        assert !urn.getGrlspec().getActors().contains(getActorDef()) : "post actor still in model"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add actor
        urn.getGrlspec().getActors().add(getActorDef());

        testPreConditions();
    }

}
