package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveComponentCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.ComponentRef;
import urn.URNlink;
import urncore.Component;

/**
 * Command to delete a Component. Sould also remove all the URNlink.
 * 
 * @author jkealey, Jean-Francois Roy
 * 
 */
public class DeleteComponentCommand extends CompoundCommand {

    private Component cd;

    public DeleteComponentCommand(Component cd) {

        setLabel(Messages.getString("DeleteComponentCommand.deleteComponent")); //$NON-NLS-1$
        this.cd = cd;
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Build the compound command.
     * 
     */
    private void build() {
        // Verify if the definition can be delete.
        if (cd.getContRefs().size() == 0 || DeletePreferences.getDeleteReference(cd)) {
            // Delete all the references
            for (Iterator it = cd.getContRefs().iterator(); it.hasNext();) {
                ComponentRef currentRef = (ComponentRef) it.next();
                add(new PreDeleteUrnModelElementCommand(currentRef));
                add(new RemoveURNmodelElementCommand(currentRef));
            }

            // Remove the URNlinks
            for (Iterator it = cd.getFromLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            for (Iterator it = cd.getToLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }

            if (cd.getResource() != null) {
                add(new RemoveResourceFromComponentCommand(cd, cd.getResource()));
            }

            if (cd instanceof Component) {
                Component regular = (Component) cd;
                if (regular.getHost() != null) {
                    add(new RemoveResourceFromComponentCommand(regular, regular.getHost()));
                }
            }

            add(new RemoveComponentCommand(cd));
        }
    }

}