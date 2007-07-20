/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveKPIModelLinkCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveKPIModelLinkRefCommand;

/**
 * Delete a KPIModelLinkRef. If it is the last KPIModel linkref in the GRLGraphs, delete also the definition.
 * 
 * @author pchen
 * 
 */
public class DeleteKPIModelLinkRefCommand extends CompoundCommand {

    KPIModelLinkRef linkref;
    KPIModelLink link;

    /**
     * @param ref
     *            The LinkRef to delete
     * 
     */
    public DeleteKPIModelLinkRefCommand(KPIModelLinkRef ref) {
        this.linkref = ref;
        this.link = linkref.getLink();
        setLabel(Messages.getString("DeleteKPIModelLinkRefCommand.deleteKPIModelLinkRef")); //$NON-NLS-1$
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
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Builds a sequence of DeletePathNodeCommands
     * 
     */
    private void build() {
        add(new RemoveKPIModelLinkRefCommand(linkref));
        if (link != null && link.getRefs().size() <= 1) {
            add(new RemoveKPIModelLinkCommand(link));
        }
    }

    public void setKPIModelLink(KPIModelLink link) {
        for (int i = 0; i < getCommands().size(); i++) {
            if ((getCommands().get(i) instanceof RemoveKPIModelLinkRefCommand)) {
                RemoveKPIModelLinkRefCommand ref = (RemoveKPIModelLinkRefCommand) getCommands().get(i);
                ref.setKPIModelLink(link);
            }
        }
    }
}
