package seg.jUCMNav.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.AddBranchCommand;
import ucm.map.PathNode;

/**
 * Created on 30-May-2005
 * 
 * @author jkealey
 *  
 */
public class AddTimeoutPathAction extends SelectionAction {

    public static final String ADDTIMEOUTPATH = "Add Timeout Path"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddTimeoutPathAction(IWorkbenchPart part) {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean canPerformAction() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        if (sel.getTimer() != null) {
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Timer16.gif")); //$NON-NLS-1$
            return sel.getTimer().getSucc().size() == 1;
        } else
            return false;

    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        if (sel.getTimer() != null) {
            return new AddBranchCommand((PathNode) ((EditPart) getSelectedObjects().get(0)).getModel());
        } else
            return null;

    } /*
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
     * @see org.eclipse.jface.action.Action#getId()
     */
    public String getId() {
        return ADDTIMEOUTPATH;
    }

}