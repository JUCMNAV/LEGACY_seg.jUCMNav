/*
 * Created on Jun 6, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import ucm.map.OrFork;
import ucm.map.OrJoin;

/**
 * @author jpdaigle
 * 
 * This action exposes a "Convert" action in Fork contextual menus, allowing them to be changed to a Join.
 *  
 */
public class TransmogrifyOrForkOrJoinAction extends TransmogrifyForkOrJoinAction {
    public static final String TRANSMOGRIFYFORK = "TransmogrifyFork";

    /**
     * @param part
     */
    public TransmogrifyOrForkOrJoinAction(IWorkbenchPart part) {
        super(part);
        // TODO Auto-generated constructor stub
    }

    protected boolean canPerformAction() {
        /*
         * Conditions for enabling: selection contains exactly 1 item and it's a join.
         */
        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);
            if (part.getModel() instanceof OrJoin || part.getModel() instanceof OrFork) {
                return true;
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return TRANSMOGRIFYFORK;
    }

}