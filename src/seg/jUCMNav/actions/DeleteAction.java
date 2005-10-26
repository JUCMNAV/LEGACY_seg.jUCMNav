package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.model.commands.delete.DeleteUselessStartNCEndCommand;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * DeleteAction overridden from framework to delete small paths created after a mass deletion.
 * 
 * @author jkealey
 * 
 */
public class DeleteAction extends org.eclipse.gef.ui.actions.DeleteAction {

    /**
     * Constructs a <code>DeleteAction</code> using the specified part.
     * 
     * @param part
     *            The part for this action
     */
    public DeleteAction(IWorkbenchPart part) {
        super(part);
        setLazyEnablementCalculation(false);
    }

    /**
     * Performs the delete action on the selected objects.
     */
    public void run() {
        URNspec urn = ((UCMNavMultiPageEditor) getWorkbenchPart()).getModel();
        int nextGlobalID = Integer.parseInt(urn.getNextGlobalID());
        Command cmd = createDeleteSmallPaths(nextGlobalID);
        if (cmd == null || !cmd.canExecute())
            execute(createDeleteCommand(getSelectedObjects()));
        else
            execute(createDeleteCommand(getSelectedObjects()).chain(cmd));
    }

    private Command createDeleteSmallPaths(int nextGlobalID) {
        UcmEditor editor = (UcmEditor)((UCMNavMultiPageEditor) getWorkbenchPart()).getCurrentPage();
        if (editor == null)
            return null;
        UCMmap map = (UCMmap)editor.getModel();
        return new DeleteUselessStartNCEndCommand(map, editor.getGraphicalViewer().getEditPartRegistry());
    }
}
