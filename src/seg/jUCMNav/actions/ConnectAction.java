package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import ucm.map.PathNode;

/**
 * Creates synchronous and asynchronous connections between elements.
 * 
 * @author jkealey
 * 
 */
public class ConnectAction extends URNSelectionAction {

    public static final String CONNECT = "seg.jUCMNav.Connect"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ConnectAction(IWorkbenchPart part) {
        super(part);
        setId(CONNECT);
    }

    /**
     * Returns true if selected (end||empty) && (start||wait||timer)
     */
    protected boolean calculateEnabled() {
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

    /**
     * @return a {@link ConnectCommand}
     */
    protected Command getCommand() {
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
}