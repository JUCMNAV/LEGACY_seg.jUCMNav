/*
 * Created on Jun 6, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.OrFork;
import ucm.map.OrJoin;

/**
 * @author jpdaigle
 * 
 * This action exposes a "Convert" action in Fork contextual menus, allowing them to be changed to a Join.
 *  
 */
public class TransmogrifyOrForkOrJoinAction extends TransmogrifyForkOrJoinAction {
    public static final String TRANSMOGRIFYFORK = "TransmogrifyFork"; //$NON-NLS-1$

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
            if (part.getModel() instanceof OrJoin) {
            	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndJoin16.gif")); //$NON-NLS-1$
                return true;
            }
            else if(part.getModel() instanceof OrFork) {
            	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif")); //$NON-NLS-1$
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