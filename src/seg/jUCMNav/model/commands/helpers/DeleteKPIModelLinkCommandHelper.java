package seg.jUCMNav.model.commands.helpers;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.Messages;

public class DeleteKPIModelLinkCommandHelper extends CompoundCommand {
    

    KPIModelLink link;

    /**
     * @param link
     *            the kpiModelLink to delete
     */
    public DeleteKPIModelLinkCommandHelper(KPIModelLink link) {
        setLabel(Messages.getString("DeleteKPIModelLinkCommand.deleteKPIModelLink")); //$NON-NLS-1$
        this.link = link;
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
        for (int i = 0; i < link.getRefs().size(); i++) {
            KPIModelLinkRef ref = (KPIModelLinkRef) link.getRefs().get(i);
            append(new DeleteKPIModelLinkRefCommandHelper(ref));
        }
        append(new RemoveKPIModelLinkCommandHelper(link));
    }

    /**
     * Overwriting undo to set the KPIModelLink to the DeleteKPIModelLinkRefCommand
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        for (int i = getCommandList().size() - 1; i >= 0; i--) {
            if (getCommandList().get(i) instanceof DeleteKPIModelLinkRefCommandHelper) {
                ((DeleteKPIModelLinkRefCommandHelper) getCommandList().get(i)).setKPIModelLink(link);
            }
            ((Command) getCommandList().get(i)).undo();
        }
    }

    
    
}
