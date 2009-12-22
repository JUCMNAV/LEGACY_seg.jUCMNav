package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.DeleteRespBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveResponsibilityCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import ucm.performance.Demand;
import urn.URNlink;
import urncore.Responsibility;

/**
 * Command to delete a Responsibility and delete URNlink associate to it.
 * 
 * @author jkealey, Jean-Francois Roy
 * 
 */
public class DeleteResponsibilityCommand extends CompoundCommand {

    private Responsibility resp;

    public DeleteResponsibilityCommand(Responsibility resp) {
        setLabel(Messages.getString("DeleteResponsibilityCommand.deleteResponsibilityCommand")); //$NON-NLS-1$
        this.resp = resp;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
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
     * Builds a sequence of DeleteGRLNodeCommands
     * 
     */
    private void build() {

        // Verify if the definition can be delete.
        if (resp.getRespRefs().size() == 0 || DeletePreferences.getDeleteReference(resp)) {
            // Remove the URNlinks
            for (Iterator it = resp.getFromLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            for (Iterator it = resp.getToLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            for (Iterator iter = resp.getDemands().iterator(); iter.hasNext();) {
                Demand demand = (Demand) iter.next();
                add(new DeleteDemandCommand(demand));

            }

            // Delete all references
            for (Iterator it = resp.getRespRefs().iterator(); it.hasNext();) {
                RespRef reference = (RespRef) it.next();
                // Edit part registry map is not necessary here.
                add(new RemovePathNodeCommand(reference, null));
            }
            
            // Delete all responsibility bindings
            for (Iterator it = resp.getParentBindings().iterator(); it.hasNext();) {
                ResponsibilityBinding binding = (ResponsibilityBinding) it.next();
                add(new DeleteRespBindingCommand(binding));
            }

            add(new RemoveResponsibilityCommand(resp));
        }
    }
}