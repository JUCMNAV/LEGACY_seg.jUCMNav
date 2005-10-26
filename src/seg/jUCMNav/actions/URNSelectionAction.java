package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Superclass for our selection actions to remove redundant code.
 * 
 * @author jkealey
 *  
 */
public abstract class URNSelectionAction extends SelectionAction {

    /**
     * 
     * @param part The editor 
     */
    public URNSelectionAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * Framework queries the action to know if the action can be executed.
     */
    protected boolean calculateEnabled() {
        Command command = getCommand();
        return command!=null && command.canExecute();
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
        execute(getCommand());
    }

}