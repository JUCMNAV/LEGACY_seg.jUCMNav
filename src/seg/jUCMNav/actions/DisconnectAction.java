package seg.jUCMNav.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import ucm.map.PathNode;

/**
 * Breaks an existing synchronous or asynchronous connection.
 * 
 * @author jkealey
 * 
 */
public class DisconnectAction extends URNSelectionAction {

    public static final String DISCONNECT = "seg.jUCMNav.Disconnect"; //$NON-NLS-1$

    /**
     * @param part
     */
    public DisconnectAction(IWorkbenchPart part) {
        super(part);
        setId(DISCONNECT);
    }

    /**
     * True if selected object is connected to a connect.
     */
    protected boolean calculateEnabled() {

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT:
            return true;
        default:
            return false;
        }
    }

    /**
     * @return a {@link DisconnectCommand}
     */
    protected Command getCommand() {
        return new DisconnectCommand((PathNode) ((EditPart) getSelectedObjects().get(0)).getModel());
    }

}