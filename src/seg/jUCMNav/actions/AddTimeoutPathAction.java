package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import ucm.map.NodeConnection;
import ucm.map.Timer;

/**
 * Given a timer without a timeout path, adds it.
 * 
 * @author jkealey
 * 
 */
public class AddTimeoutPathAction extends AddBranchAction {

    public static final String ADDTIMEOUTPATH = "seg.jUCMNav.AddTimeoutPath"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddTimeoutPathAction(IWorkbenchPart part) {
        super(part);
        setId(ADDTIMEOUTPATH);
    }

    /**
     * Can add timeout path on a timer that doesn't currently have one. Method also sets the icon.
     */
    protected boolean calculateEnabled() {

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT: {
            if (sel.getConnect().getSucc().size() > 0 && ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget() instanceof Timer) {
                Timer timer = (Timer) ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget();
                return timer.getSucc().size() == 1;
            }
            break;
        }
        case SelectionHelper.TIMER: {
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Timer16.gif")); //$NON-NLS-1$
            return sel.getTimer().getSucc().size() == 1;
        }
        }
        return false;
    }

    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONNECT: {
            if (sel.getConnect().getSucc().size() > 0 && ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget() instanceof Timer) {
                Timer timer = (Timer) ((NodeConnection) sel.getConnect().getSucc().get(0)).getTarget();
                return new AddBranchCommand(timer);
            }
            break;
        }
        case SelectionHelper.TIMER: {
            return new AddBranchCommand(sel.getTimer());
        }
        }
        return null;
    }

}