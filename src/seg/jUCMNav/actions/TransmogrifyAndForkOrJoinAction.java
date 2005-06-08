/*
 * Created on Jun 6, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.AndFork;
import ucm.map.AndJoin;

/**
 * @author jpdaigle
 * 
 * This action exposes a "Convert" action in Join contextual menus, allowing them to be changed to a Fork.
 *  
 */
public class TransmogrifyAndForkOrJoinAction extends TransmogrifyForkOrJoinAction {
    public static final String TRANSMOGRIFYJOIN = "TransmogrifyJoin";

    /**
     * @param part
     */
    public TransmogrifyAndForkOrJoinAction(IWorkbenchPart part) {
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
            if (part.getModel() instanceof AndJoin) {
            	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrJoin16.gif"));
                return true;
            }
            else if(part.getModel() instanceof AndFork){
            	setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif"));
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
        return TRANSMOGRIFYJOIN;
    }

}