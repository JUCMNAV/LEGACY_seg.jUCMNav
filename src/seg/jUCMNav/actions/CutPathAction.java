/*
 * Created on 2005-03-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.CutPathCommand;

/**
 * Created 2005-03-21
 * 
 * @author Etienne Tremblay
 */
public class CutPathAction extends SelectionAction {
	
	public static final String
	CUTPATH_REQUEST = "CutPathRequest";  //$NON-NLS-1$

	public static final String
		CUTPATH = "CutPath";   //$NON-NLS-1$
	
	Request request;

	/**
	 * @param part
	 */
	public CutPathAction(IWorkbenchPart part) {
		super(part);
		request = new Request(CUTPATH_REQUEST);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return canPerformAction();
	}
	
	private boolean canPerformAction() {
		if (getSelectedObjects().isEmpty())
			return false;
		List parts = getSelectedObjects();
		if(parts.size() != 0){
			Object o = parts.get(0);
			if (!(o instanceof EditPart))
				return false;
			EditPart part = (EditPart)o;
			
			return CutPathCommand.canExecute(part.getModel());

			
		}
		return true;
	}
	
	private Command getCommand() {
		List editparts = getSelectedObjects();
		CompoundCommand cc = new CompoundCommand();
		for (int i=0; i < editparts.size(); i++) {
			EditPart part = (EditPart)editparts.get(i);
			cc.add(part.getCommand(request));
		}
		return cc;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		execute(getCommand());
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IAction#getId()
	 */
	public String getId() {
		return CUTPATH;
	}
}
