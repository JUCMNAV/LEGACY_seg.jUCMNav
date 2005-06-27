package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import ucm.map.Map;
import urn.URNspec;

/**
 * Adds a new blank use case map in the current editor.
 * 
 * @author jkealey
 */
public class AddMapAction extends UCMSelectionAction {
    public static final String ADDMAP = "seg.jUCMNav.AddMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddMapAction(IWorkbenchPart part) {
        super(part);
        setId(ADDMAP);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")); //$NON-NLS-1$
    }

    /**
     * If you have a URNspec or Map selected.
     */
    protected boolean calculateEnabled() {

        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);
            return (part.getModel() instanceof URNspec || part.getModel() instanceof Map);
        }

        return false;
    }

    protected Command getCommand() {
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

}