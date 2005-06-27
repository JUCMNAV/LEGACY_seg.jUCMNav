/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import ucm.map.Map;
import urn.URNspec;

/**
 * @author Jordan
 */
public class AddMapAction extends SelectionAction {
    public static final String ADDMAP = "seg.jUCMNav.AddMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddMapAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")); //$NON-NLS-1$
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
            return (part.getModel() instanceof URNspec || part.getModel() instanceof Map);
        }

        return false;
    }

    private Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);

        URNspec urn;
        if (part.getModel() instanceof URNspec)
        urn = (URNspec) part.getModel();
        else
            urn = ((Map) part.getModel()).getUcmspec().getUrnspec();
        
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