package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.CutPathCommand;

/**
 * Tries to cut the selected path by revering to CutPathCommand.
 * 
 * jkealey: Should see if we can get rid of the request. This was Etienne's first command.
 * Furthermore, seems to be incoherence between calculateEnabled && getCommand(); 
 *  
 * @author Etienne Tremblay
 */
public class CutPathAction extends UCMSelectionAction {

    public static final String CUTPATH_REQUEST = "seg.jUCMNav.CutPathRequest"; //$NON-NLS-1$

    public static final String CUTPATH = "seg.jUCMNav.CutPath"; //$NON-NLS-1$

    Request request;

    /**
     * @param part
     */
    public CutPathAction(IWorkbenchPart part) {
        super(part);
        setId(CUTPATH);
        request = new Request(CUTPATH_REQUEST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        if (getSelectedObjects().isEmpty())
            return false;
        List parts = getSelectedObjects();
        if (parts.size() != 0) {
            Object o = parts.get(0);
            if (!(o instanceof EditPart))
                return false;
            EditPart part = (EditPart) o;

            return CutPathCommand.canExecute(part.getModel());

        }
        return true;
    }

    protected Command getCommand() {
        List editparts = getSelectedObjects();
        CompoundCommand cc = new CompoundCommand();
        for (int i = 0; i < editparts.size(); i++) {
            EditPart part = (EditPart) editparts.get(i);
            cc.add(part.getCommand(request));
        }
        return cc;
    }

}