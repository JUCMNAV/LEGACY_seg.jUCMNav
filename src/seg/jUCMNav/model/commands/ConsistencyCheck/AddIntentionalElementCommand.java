package seg.jUCMNav.model.commands.ConsistencyCheck;

import org.eclipse.gef.commands.Command;

import grl.IntentionalElement;
import grl.IntentionalElementType;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command create a IntentionalElement object in the model
 * 
 * @author kshen
 * 
 */
public class AddIntentionalElementCommand extends Command implements JUCMNavCommand {
	private URNspec urnspec;
	private IntentionalElement grlIE;

	/**
	 * 
	 * @param urnspec
	 *            The URNspec to which to add the IntentionalElement
	 * @param type
	 *            The type of the IntentionalElement           
	 */
	public AddIntentionalElementCommand(URNspec urnspec, int type) {
		this.urnspec = urnspec;
		grlIE = (IntentionalElement) ModelCreationFactory.getNewObject(urnspec, IntentionalElement.class, 0);
		grlIE.setType(IntentionalElementType.get(type));

		setLabel(Messages.getString("AddIntentionalElementRefCommand.CreateIntentionalElements")); //$NON-NLS-1$
	}

	public IntentionalElement getIntentionalElement() {
		return grlIE;
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

		urnspec.getGrlspec().getIntElements().add(grlIE);

		testPostConditions();
	}

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		urnspec.getGrlspec().getIntElements().remove(grlIE);

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