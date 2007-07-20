/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveKPIModelLinkCommand;

/**
 * Delete a KPIModelLink from the urnspec and all the KPIModelLinkRef associate to it
 * 
 * @author pchen
 * 
 */
public class DeleteKPIModelLinkCommand extends CompoundCommand {

    KPIModelLink link;

    /**
     * @param link
     *            the kpiModelLink to delete
     */
    public DeleteKPIModelLinkCommand(KPIModelLink link) {
        setLabel(Messages.getString("DeleteKPIModelLinkCommand.deleteKPIModelLink")); //$NON-NLS-1$
        this.link = link;
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
        for (int i = 0; i < link.getRefs().size(); i++) {
            KPIModelLinkRef ref = (KPIModelLinkRef) link.getRefs().get(i);
            add(new DeleteKPIModelLinkRefCommand(ref));
        }
        add(new RemoveKPIModelLinkCommand(link));
    }

    /**
     * Overwriting undo to set the KPIModelLink to the DeleteKPIModelLinkRefCommand
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        for (int i = getCommands().size() - 1; i >= 0; i--) {
            if (getCommands().get(i) instanceof DeleteKPIModelLinkRefCommand) {
                ((DeleteKPIModelLinkRefCommand) getCommands().get(i)).setKPIModelLink(link);
            }
            ((Command) getCommands().get(i)).undo();
        }
    }

}
