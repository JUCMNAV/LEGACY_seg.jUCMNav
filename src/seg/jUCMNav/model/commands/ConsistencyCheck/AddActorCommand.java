package seg.jUCMNav.model.commands.ConsistencyCheck;

import org.eclipse.gef.commands.Command;

import grl.Actor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command create a Actor object in the model
 * This command differs from AddContainerRefCommand in that it does not create an Actor Reference.
 * 
 * @author kshen
 * 
 */
public class AddActorCommand extends Command implements JUCMNavCommand {
	private URNspec urnspec;
	Actor grlActor;

	/**
	 * 
	 * @param urnspec
	 *            The URNspec to which to add the Actor         
	 */
	public AddActorCommand(URNspec urnspec) {
		this.urnspec = urnspec;
		grlActor = (Actor)ModelCreationFactory.getNewObject(urnspec, Actor.class, 0);

		setLabel("Add Actor"); //$NON-NLS-1$
	}

	public Actor getActor() {
		return grlActor;
	}

	/**
	 * 
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

		urnspec.getGrlspec().getActors().add(grlActor);

		testPostConditions();
	}

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		urnspec.getGrlspec().getActors().remove(grlActor);

		testPreConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert urnspec != null : "pre urnspec"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert urnspec != null : "post urnspec"; //$NON-NLS-1$
	}
}