package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.CutAnyPathCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;

/**
 * Tries to cut the selected path by revering to CutPathCommand.
 * 
 * jkealey: Should see if we can get rid of the request. This was Etienne's first command. Furthermore, seems to be incoherence between calculateEnabled &&
 * getCommand();
 * 
 * @author Etienne Tremblay
 */
public class CutPathAction extends URNSelectionAction {

    public static final String CUTPATH_REQUEST = "seg.jUCMNav.CutPathRequest"; //$NON-NLS-1$

    public static final String CUTPATH = "seg.jUCMNav.CutPath"; //$NON-NLS-1$

    /**
     * @param part
     */
    public CutPathAction(IWorkbenchPart part) {
        super(part);
        setId(CUTPATH);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/cut_edit.gif")); //$NON-NLS-1$
    }

    /**
     * Delegates the decision to {@link CutPathCommand#canExecute(Object)}
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

            return CutAnyPathCommand.canExecute(part.getModel());

        }
        return true;
    }

    /**
     * Asks the selected edit parts to return their commands via a request. Cut Path is one of the only actions that follows this model.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        if (sel.getSelectionType() == SelectionHelper.NODECONNECTION)
            return new CutAnyPathCommand(sel.getMap(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y);
        else if (sel.getSelectionType() == SelectionHelper.EMPTYPOINT)
            return new CutAnyPathCommand(sel.getMap(), sel.getEmptypoint(), sel.getEmptypoint().getX(), sel.getEmptypoint().getY());
        else if (sel.getSelectionType() == SelectionHelper.DIRECTIONARROW)
            return new CutAnyPathCommand(sel.getMap(), sel.getDirectionarrow(), sel.getDirectionarrow().getX(), sel.getDirectionarrow().getY());

        return null;
    }

}