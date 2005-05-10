/*
 * Created on 8-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.create.AddAndForkCommand;
import ucm.map.EmptyPoint;
import ucm.map.PathGraph;

/**
 * @author jpdaigle
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddAndForkAction extends SelectionAction {

    public static final String ADDANDFORK = "AddAndFork";

    /**
     * @param part
     */
    public AddAndForkAction(IWorkbenchPart part) {
        super(part);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return canPerformAction();
    }

    private boolean canPerformAction() {
        /*
         * Conditions for enabling: selection contains exactly 1 item and it's a
         * path node.
         */
        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);
            if ((part.getModel() instanceof EmptyPoint)) {
                return true;
            }
        }

        return false;
    }

    private Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);
        
        PathGraph pg = (PathGraph)((EmptyPoint)part.getModel()).eContainer();
        AddAndForkCommand comm = new AddAndForkCommand(pg, (EmptyPoint)part.getModel());
        System.out.println("Create AddAndForkCommand");
        return comm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        execute(getCommand());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return ADDANDFORK;
    }

}
