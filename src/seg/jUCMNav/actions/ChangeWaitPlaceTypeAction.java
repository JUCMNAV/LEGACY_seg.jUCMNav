package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeWaitPlaceTypeCommand;
import ucm.map.NodeConnection;
import ucm.map.WaitKind;
import ucm.map.WaitingPlace;

/**
 * Changes the waiting place type.
 * 
 * @author jkealey
 */
public class ChangeWaitPlaceTypeAction extends URNSelectionAction {
    public static final String CHANGE_WAITINGPLACE_TYPE = "seg.jUCMNav.CHANGE_WAITINGPLACE_TYPE"; //$NON-NLS-1$

    public static final String[] WAITINGPLACE_TYPES = new String[] {
            Messages.getString("ChangeWaitPlaceTypeAction.Transient"), Messages.getString("ChangeWaitPlaceTypeAction.Persistent") }; //$NON-NLS-1$ //$NON-NLS-2$
    public static final WaitKind[] WAITINGPLACE_KINDS = new WaitKind[] { WaitKind.TRANSIENT_LITERAL, WaitKind.PERSISTENT_LITERAL };
    protected int waitPlaceType;

    /**
     * @param part
     */
    public ChangeWaitPlaceTypeAction(IWorkbenchPart part, int waitPlaceType) {
        super(part);
        setId(generateId(waitPlaceType));
        this.waitPlaceType = waitPlaceType;

        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Wait16.gif")); //$NON-NLS-1$

        setText(Messages.getString("ChangeWaitPlaceTypeAction.DefineAs") + WAITINGPLACE_TYPES[waitPlaceType]); //$NON-NLS-1$
    }

    public static String generateId(int waitPlaceType) {
        return CHANGE_WAITINGPLACE_TYPE + waitPlaceType;
    }

    /**
     * True if we've selected an empty point or a node connection.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT: {
            if (sel.getConnect().getSucc().size() > 0 && ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget() instanceof WaitingPlace) {
                WaitingPlace wp = (WaitingPlace) ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget();
                return !WAITINGPLACE_KINDS[waitPlaceType].equals(wp.getWaitType());
            }
            break;
        }
        case SelectionHelper.WAITINGPLACE:
            if (WAITINGPLACE_KINDS[waitPlaceType].equals(sel.getWaitingPlace().getWaitType()))
                return false;
            else
                return true;

        case SelectionHelper.TIMER:
            if (WAITINGPLACE_KINDS[waitPlaceType].equals(sel.getTimer().getWaitType()))
                return false;
            else
                return true;
        }
        return false;
    }

    /**
     * Returns the appropriate convert command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT: {
            if (sel.getConnect().getSucc().size() > 0 && ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget() instanceof WaitingPlace) {
                WaitingPlace wp = (WaitingPlace) ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget();
                comm = new ChangeWaitPlaceTypeCommand(wp, WAITINGPLACE_KINDS[waitPlaceType]);
                return comm;
            }
        }
        case SelectionHelper.WAITINGPLACE:
            comm = new ChangeWaitPlaceTypeCommand(sel.getWaitingPlace(), WAITINGPLACE_KINDS[waitPlaceType]);
            return comm;
        case SelectionHelper.TIMER:
            comm = new ChangeWaitPlaceTypeCommand(sel.getTimer(), WAITINGPLACE_KINDS[waitPlaceType]);
            return comm;
        default:
            return null;
        }
    }
}