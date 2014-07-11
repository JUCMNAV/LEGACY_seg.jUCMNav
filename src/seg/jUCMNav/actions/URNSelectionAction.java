package seg.jUCMNav.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.ICreateElementCommand;
import urn.URNspec;

/**
 * Superclass for our selection actions to remove redundant code.
 * 
 * @author jkealey
 * 
 */
public abstract class URNSelectionAction extends SelectionAction {

    protected static Request directEditRequest = new Request(RequestConstants.REQ_DIRECT_EDIT);

    /**
     * 
     * @param part
     *            The editor
     */
    public URNSelectionAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * Framework queries the action to know if the action can be executed.
     */
    protected boolean calculateEnabled() {
        Command command = getCommand();
        return command != null && command.canExecute();
    }

    /**
     * Subclasses should override this method to return a command to be executed when the action is performed.
     * 
     * @return the command to be executed
     */
    protected Command getCommand() {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Executes the command returned by getCommand();
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Command cmd = getCommand();

        execute(cmd);

        autoDirectEdit(cmd);
    }

    protected void autoDirectEdit(Command cmd) {
        if (cmd instanceof ICreateElementCommand) {
            UCMNavMultiPageEditor editor = getEditor();
            EditPartViewer viewer = editor.getCurrentPage().getGraphicalViewer();
            Object part = (EditPart) viewer.getEditPartRegistry().get(((ICreateElementCommand) cmd).getNewModelElement());
            
            if (part instanceof EditPart)
                ((EditPart) part).performRequest(directEditRequest);
        }
    }

    protected UCMNavMultiPageEditor getEditor() {
        if (this.getWorkbenchPart()!=null && this.getWorkbenchPart().getSite()!=null && this.getWorkbenchPart().getSite().getPage()!=null && this.getWorkbenchPart().getSite().getPage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) this.getWorkbenchPart().getSite().getPage().getActiveEditor();
            return editor;
        }
        return null;
    }

    protected URNspec getUrnspec() {
        if (getEditor() != null) {
            return getEditor().getModel();
        }
        return null;
    }
}