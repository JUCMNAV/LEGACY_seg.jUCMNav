package seg.jUCMNav.model.commands.create;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.FailurePoint;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.Changeable;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.TextChange;
import urncore.Component;
import urncore.Responsibility;

/**
 * This command adds a TextChange to the selected Changeable element and Dynamic Context
 * 
 * @author sluthra
 * 
 */
public class AddTextChangeCommand extends Command implements JUCMNavCommand {

	private Object parent;
	private String selectedChange, affectedProperty;
	private Date startDate;
	private Date endDate;
	boolean aborted = false;
	boolean isInCompoundCommand = false;
	private URNspec urn;
	private TextChange newChange;
	private DynamicContext dyn;
	private String newValue;

	/**
	 * Constructor
	 */
	public AddTextChangeCommand(Object parent, DynamicContext dyn, String selectedChange, String affectedProperty, Date startDate, Date endDate, String newValue, URNspec urn) {
		this.parent = parent;
		this.urn = urn;
		this.dyn = dyn;
		this.selectedChange = selectedChange;
		this.affectedProperty = affectedProperty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.newValue = newValue;
		setLabel(Messages.getString("AddTextChangeCommand.AddTextChange")); //$NON-NLS-1$
	}

	/**
	 * 
	 */
	public AddTextChangeCommand(Object parent, String selectedChange, String affectedProperty, boolean isInCompoundCommand, DynamicContext dyn, Date startDate, Date endDate, String newValue, URNspec urn) {
		this.parent = parent;
		this.urn = urn;
		this.dyn = dyn;
		this.selectedChange = selectedChange;
		this.affectedProperty = affectedProperty;
		this.startDate = startDate;
		this.endDate = endDate;
		this.newValue = newValue;
		setLabel(Messages.getString("AddTextChangeCommand.AddTextChange")); //$NON-NLS-1$
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

		//Add a Text Change
		if (selectedChange.equals("Text Change")) {
			newChange = (TextChange) ModelCreationFactory.getNewObject(urn, TextChange.class);
			Changeable elt = null;

			if (parent instanceof RespRefEditPart)
				elt = ((RespRef)((RespRefEditPart) this.parent).getModel()).getRespDef();
			else if (parent instanceof Responsibility)
				elt = (Responsibility) this.parent;
			else if (parent instanceof ComponentRefEditPart)
				elt = (Component)((ComponentRef)((ComponentRefEditPart) this.parent).getModel()).getContDef();
			else if (parent instanceof Component)
				elt = (Component) this.parent;
			else if (parent instanceof PluginBinding)
				elt = (PluginBinding) this.parent;
			else if (parent instanceof urncore.Condition)
				elt = (urncore.Condition) this.parent;
			else if (parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) parent).getModel()) instanceof StartPoint)
					elt = (StartPoint)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart)this.parent).getModel()) instanceof FailurePoint)
					elt = (FailurePoint)((PathNodeEditPart) this.parent).getModel();
			}
			else if (parent instanceof StartPoint)
				elt = (StartPoint) this.parent;
			else if (parent instanceof FailurePoint)
				elt = (FailurePoint) this.parent;

			newChange.setElement(elt);
			newChange.setAffectedProperty(affectedProperty);
			newChange.setStart(startDate);
			newChange.setEnd(endDate);
			if (affectedProperty.startsWith("Replication factor") || affectedProperty.startsWith("Pre-Condition") || affectedProperty.equals("Responsibility Expression")
					|| affectedProperty.startsWith("Expression") || affectedProperty.equals("Post-Condition") || affectedProperty.equals("Failure List/Start Point Pre-Condition")
					|| affectedProperty.equals("Failure Condition") || affectedProperty.equals("Failure Expression"))
				newChange.setNewValue(newValue);
			newChange.setContext(dyn);
			testPostConditions();
		}
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
	 * @return the added new TextChange
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
