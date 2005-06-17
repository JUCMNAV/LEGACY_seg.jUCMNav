package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import ucm.map.PathNode;

/**
 * Created on 16-Jun-05
 * 
 * @author jkealey
 *  
 */
public class ConnectAction extends SelectionAction {

    public static final String CONNECT = "Connect"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ConnectAction(IWorkbenchPart part) {
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
        case SelectionHelper.STARTPOINT_ENDPOINT:
        case SelectionHelper.ENDPOINT_WAITINGPLACE:
        case SelectionHelper.ENDPOINT_TIMER:
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
        case SelectionHelper.EMPTYPOINT_WAITINGPLACE:
        case SelectionHelper.EMPTYPOINT_TIMER:
            return true;
        default:
            return false;
        }

    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_ENDPOINT:
        case SelectionHelper.ENDPOINT_WAITINGPLACE:
        case SelectionHelper.ENDPOINT_TIMER:
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
        case SelectionHelper.EMPTYPOINT_WAITINGPLACE:
        case SelectionHelper.EMPTYPOINT_TIMER:
            return new ConnectCommand((PathNode) ((PathNodeEditPart) getSelectedObjects().get(0)).getModel(),
                    (PathNode) ((PathNodeEditPart) getSelectedObjects().get(1)).getModel());
        default:
            return null;

        }
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
        return CONNECT;
    }

}