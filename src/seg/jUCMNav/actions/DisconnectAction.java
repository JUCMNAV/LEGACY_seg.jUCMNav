package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import ucm.map.PathNode;

/**
 * Created on 16-Jun-05
 * 
 * @author jkealey
 *  
 */
public class DisconnectAction extends SelectionAction {

    public static final String DISCONNECT = "Disconnect"; //$NON-NLS-1$

    /**
     * @param part
     */
    public DisconnectAction(IWorkbenchPart part) {
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
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT:
            return true;
        default:
            return false;
        }
    }

    private Command getCommand() {
        return new DisconnectCommand((PathNode) ((PathNodeEditPart) getSelectedObjects().get(0)).getModel());
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
     * @see org.eclipse.jface.action.Action#getId()
     */
    public String getId() {
        return DISCONNECT;
    }

}