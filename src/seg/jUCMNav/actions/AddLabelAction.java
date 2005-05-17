/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

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
			if ((part.getModel() instanceof PathNode)) {
			    PathNode node = (PathNode) part.getModel();
			    if(node.getLabel() == null) {
			        return true;
			    }
			} else if((part.getModel() instanceof ComponentRef)) {
				ComponentRef component = (ComponentRef) part.getModel();
			    if(component.getLabel() == null) {
			        return true;
			    }
			}
		}
		
		return false;
	}
    
    private Command getCommand() {
        List parts = getSelectedObjects();
        AbstractGraphicalEditPart part = (AbstractGraphicalEditPart) parts.get(0);
        
        Dimension dim = part.getFigure().getPreferredSize().getCopy();

        UCMmodelElement modelElement = (UCMmodelElement) part.getModel();
        CreateLabelCommand create = new CreateLabelCommand(modelElement);
        /*
        if ((part.getModel() instanceof PathNode)) {
        	create.setDeltaY(dim.height);
        }
        */
	    
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