package seg.jUCMNav.model.commands.create;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Stub;
import urn.URNspec;
import urn.dyncontext.BooleanChange;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;
import urncore.Component;
import urncore.URNmodelElement;

/**
 * This command adds a BooleanChange to the selected Changeable element and Dynamic Context
 * 
 * @author sluthra
 * 
 */

public class AddBooleanChangeCommand extends Command implements JUCMNavCommand {

	private Object parent;
	private String selectedChange, affectedProperty;
	private Date startDate;
	private Date endDate;
	boolean aborted = false;
	boolean isInCompoundCommand = false;
	private URNspec urn;
	private BooleanChange newChange;
	private DynamicContext dyn;
	private String newValue;

	/**
	 * Constructor
	 */
	public AddBooleanChangeCommand(Object parent, DynamicContext dyn, String selectedChange, String affectedProperty, Date startDate, Date endDate, String newValue, URNspec urn) {
		this.parent = parent;
		this.urn = urn;
		this.dyn = dyn;
		this.selectedChange = selectedChange;
		this.affectedProperty = affectedProperty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.newValue = newValue;
		setLabel(Messages.getString("AddBooleanChangeCommand.AddBooleanChange")); //$NON-NLS-1$
	}

	/**
	 * 
	 */
	public AddBooleanChangeCommand(Object parent, String selectedChange, String affectedProperty, boolean isInCompoundCommand, DynamicContext dyn, Date startDate, Date endDate, String newValue, URNspec urn) {
		this.parent = parent;
		this.urn = urn;
		this.dyn = dyn;
		this.selectedChange = selectedChange;
		this.affectedProperty = affectedProperty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.newValue = newValue;
		setLabel(Messages.getString("AddBooleanChangeCommand.AddBooleanChange")); //$NON-NLS-1$
		this.isInCompoundCommand = isInCompoundCommand;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return parent != null && selectedChange != null && affectedProperty != null && startDate != null && endDate != null && dyn != null;
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

		//Add a Boolean Change
		if (selectedChange.equals("Boolean Change")) {
			newChange = (BooleanChange) ModelCreationFactory.getNewObject(urn, BooleanChange.class);
			URNmodelElement elt = null;

			if (parent instanceof ComponentRefEditPart)
				elt = (Component)((ComponentRef)((ComponentRefEditPart) this.parent).getModel()).getContDef();
			else if (parent instanceof Component)
				elt = (Component) this.parent;
			else if (parent instanceof Stub)
				elt = (Stub) this.parent;
			else if (parent instanceof StubEditPart)
				elt = (Stub) ((StubEditPart) parent).getModel();

			newChange.setElement(elt);
			newChange.setAffectedProperty(affectedProperty);
			newChange.setStart(startDate);
			newChange.setEnd(endDate);

			if (affectedProperty.equals("Dynamic Stub")) {
				if (newValue.equals("0"))
					newChange.setNewValue(true);
				else if (newValue.equals("1"))
					newChange.setNewValue(false);
			} else if (affectedProperty.equals("Synchronizing Stub")) {
				if (newValue.equals("0"))
					newChange.setNewValue(true);
				else if (newValue.equals("1"))
					newChange.setNewValue(false);
			} else if (affectedProperty.equals("Blocking Stub")) {
				if (newValue.equals("0"))
					newChange.setNewValue(true);
				else if (newValue.equals("1"))
					newChange.setNewValue(false);
			} else if (affectedProperty.equals("Component Protection")) {
				if (newValue.equals("0"))
					newChange.setNewValue(true);
				else if (newValue.equals("1"))
					newChange.setNewValue(false);
			} else if (affectedProperty.equals("Component Context")) {
				if (newValue.equals("0"))
					newChange.setNewValue(true);
				else if (newValue.equals("1"))
					newChange.setNewValue(false);
			}
			newChange.setContext(dyn);

		}

		testPostConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert parent != null && selectedChange != null && affectedProperty != null && urn != null && dyn != null: "post something null"; //$NON-NLS-1$
		assert dyn.getChanges().contains(newChange) : "post child not added"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert parent != null && selectedChange != null && affectedProperty != null && urn != null && dyn != null : "pre something null"; //$NON-NLS-1$
	}

	/**
	 * 
	 * @return the added new BooleanChange
	 */
	public Change getAddedChange() {
		return newChange;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (aborted)
			return;
		testPostConditions();
		dyn.getChanges().remove(newChange);
		testPreConditions();
	}

}