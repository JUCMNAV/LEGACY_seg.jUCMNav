package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
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
        Command cmd = createDeleteSmallPaths();
        if (getSelectedObjects().size()>0) 
        {
	        if (cmd == null || !cmd.canExecute())
	            execute(createDeleteCommand(getSelectedObjects()));
	        else
	            execute(createDeleteCommand(getSelectedObjects()).chain(cmd));
        }
        else
        {
        	if (cmd != null && cmd.canExecute())
        		execute(cmd);
        }
    }

    /**
     * @return a new {@link DeleteUselessStartNCEndCommand}.  
     */
    private Command createDeleteSmallPaths() {
        UrnEditor editor = ((UCMNavMultiPageEditor) getWorkbenchPart()).getCurrentPage();
        if (editor == null || !(editor.getModel() instanceof UCMmap))
            return null;
        UCMmap map = (UCMmap)editor.getModel();
        return new DeleteUselessStartNCEndCommand(map, editor.getGraphicalViewer().getEditPartRegistry());
    }
}
