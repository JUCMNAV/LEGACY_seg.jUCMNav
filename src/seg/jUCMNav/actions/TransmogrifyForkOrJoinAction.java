/*
 * Created on May 26, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.TransmogrifyForkOrJoinCommand;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * @author jpdaigle
 * 
 * This action exposes a "Transmogrify" action in Fork contextual menus, allowing them to be changed to a Join / Fork.
 * This action is now an abstract base class for TransmogrifyAndForkOrJoinAction and TransmogrifyOrForkOrJoinAction.
 * 
 */
public abstract class TransmogrifyForkOrJoinAction extends SelectionAction {
    public static final String TRANSMOGRIFYFORKORJOIN = "TransmogrifyForkOrJoin"; //$NON-NLS-1$

    /**
     * @param part
     */
    public TransmogrifyForkOrJoinAction(IWorkbenchPart part) {
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

//    private boolean canPerformAction() {
//        /*
//         * Conditions for enabling: selection contains exactly 1 item and it's a fork.
//         */
//        List parts = getSelectedObjects();
//        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
//            EditPart part = (EditPart) parts.get(0);
//            if (part.getModel() instanceof AndFork || part.getModel() instanceof OrFork
//                    || part.getModel() instanceof AndJoin || part.getModel() instanceof OrJoin) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    abstract boolean canPerformAction();
    
    protected Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);
        PathGraph pg = ((PathNode) part.getModel()).getPathGraph();
        TransmogrifyForkOrJoinCommand comm = new TransmogrifyForkOrJoinCommand((PathNode) part.getModel(), pg);
        return comm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return TRANSMOGRIFYFORKORJOIN;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        execute(getCommand());
    }

}