/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.CreateLabelCommand;
import ucm.map.PathNode;

/**
 * @author Jordan
 */
public class AddLabelAction extends SelectionAction {
    public static final String ADDLABEL = "AddLabel";   //$NON-NLS-1$

	/**
	 * @param part
	 */
	public AddLabelAction(IWorkbenchPart part) {
		super(part);
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return canPerformAction();
    }
    
    private boolean canPerformAction() {
		List parts = getSelectedObjects();
		if(parts.size() == 1 && parts.get(0) instanceof EditPart){
			EditPart part = (EditPart) parts.get(0);
			if ((part.getModel() instanceof PathNode))
				return true;
		}
		return false;
	}
    
    private Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);
        
        CreateLabelCommand create = new CreateLabelCommand();
	    create.setNode((PathNode) part.getModel());
		return create;
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
		return ADDLABEL;
	}
}
