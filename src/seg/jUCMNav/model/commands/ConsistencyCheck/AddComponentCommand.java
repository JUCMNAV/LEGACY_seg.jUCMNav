package seg.jUCMNav.model.commands.ConsistencyCheck;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Component;

/**
 * This command create a Component object in the model
 * This command differs from AddContainerRefCommand in that it does not create a Component Reference.
 * @author kshen
 * 
 */
public class AddComponentCommand extends Command implements JUCMNavCommand {
	private URNspec urnspec;
	private Component ucmComponent;

	/**
	 * 
	 * @param urnspec
	 *            The URNspec to which to add the Component
	 * @param type
	 *            The type of the Component           
	 */
	public AddComponentCommand(URNspec urnspec, int type) {
		this.urnspec = urnspec;
		ucmComponent = (Component)ModelCreationFactory.getNewObject(urnspec, Component.class, type);

		setLabel("Add Component"); //$NON-NLS-1$
	}

	public Component getComponent() {
		return ucmComponent;
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

		urnspec.getUrndef().getComponents().add(ucmComponent);

		testPostConditions();
	}

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		urnspec.getUrndef().getComponents().remove(ucmComponent);

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