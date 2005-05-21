/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.create.CreateMapCommand;
import urn.URNspec;

/**
 * @author Jordan
 */
public class AddMapAction extends SelectionAction {
    public static final String ADDMAP = "AddMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddMapAction(IWorkbenchPart part) {
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

    private boolean canPerformAction() {
        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);
            return (part.getModel() instanceof URNspec);
        }

        return false;
    }

    private Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);

        URNspec urn = (URNspec) part.getModel();
        CreateMapCommand create = new CreateMapCommand(urn);

        return create;
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
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return ADDMAP;
    }
}