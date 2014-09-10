package seg.jUCMNav.model.commands.helpers;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.Messages;

public class DeleteKPIModelLinkRefCommandHelper extends CompoundCommand {


    KPIModelLinkRef linkref;
    KPIModelLink link;

    /**
     * @param ref
     *            The LinkRef to delete
     * 
     */
    public DeleteKPIModelLinkRefCommandHelper(KPIModelLinkRef ref) {
        this.linkref = ref;
        this.link = linkref.getLink();
        setLabel(Messages.getString("DeleteKPIModelLinkRefCommand.deleteKPIModelLinkRef")); //$NON-NLS-1$
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommandList().size() == 0)
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
        append(new RemoveKPIModelLinkRefCommandHelper(linkref));
        if (link != null && link.getRefs().size() <= 1) {
            append(new RemoveKPIModelLinkCommandHelper(link));
        }
    }

    public void setKPIModelLink(KPIModelLink link) {
        for (int i = 0; i < getCommandList().size(); i++) {
            if ((getCommandList().get(i) instanceof RemoveKPIModelLinkRefCommandHelper)) {
                RemoveKPIModelLinkRefCommandHelper ref = (RemoveKPIModelLinkRefCommandHelper) getCommandList().get(i);
                ref.setKPIModelLink(link);
            }
        }
    }
    
}
