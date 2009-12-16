/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ElementLink;
import grl.LinkRef;
import grl.LinkRefBendpoint;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveElementLinkCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveLinkRefCommand;

/**
 * Delete a LinkRef and all the LinkRefBendpoint associate to it. If it is the last linkref in the GRLGraphs, delete also the definition.
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteLinkRefCommand extends CompoundCommand {

    LinkRef linkref;
    ElementLink link;

    /**
     * @param ref
     *            The LinkRef to delete
     * 
     */
    public DeleteLinkRefCommand(LinkRef ref) {
        this.linkref = ref;
        this.link = linkref.getLink();
        setLabel(Messages.getString("DeleteLinkRefCommand.deleteLinkRef")); //$NON-NLS-1$
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
        int size = linkref.getBendpoints().size();
        for (int i = 0; i < size; i++) {
            LinkRefBendpoint bendpoint = (LinkRefBendpoint) linkref.getBendpoints().get(size - 1 - i);
            add(new DeleteLinkRefBendpointCommand(bendpoint));
        }

        add(new RemoveLinkRefCommand(linkref));
        if (link != null && link.getRefs().size() <= 1 && link.getGrlspec()!=null) {
            add(new RemoveElementLinkCommand(link));
        }
    }

    public void setElementLink(ElementLink link) {
        for (int i = 0; i < getCommands().size(); i++) {
            if ((getCommands().get(i) instanceof RemoveLinkRefCommand)) {
                RemoveLinkRefCommand ref = (RemoveLinkRefCommand) getCommands().get(i);
                ref.setElementLink(link);
            }
        }
    }
}
