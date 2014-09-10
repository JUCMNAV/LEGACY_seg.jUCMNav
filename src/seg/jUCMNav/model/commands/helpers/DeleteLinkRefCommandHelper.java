package seg.jUCMNav.model.commands.helpers;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ElementLink;
import grl.LinkRef;
import grl.LinkRefBendpoint;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.DeletionContext;
import urncore.ConnectionLabel;

public class DeleteLinkRefCommandHelper extends CompoundCommand  {
    LinkRef linkref;
    ElementLink link;
    ConnectionLabel label;

    /**
     * @param ref
     *            The LinkRef to delete
     * 
     */
    public DeleteLinkRefCommandHelper(LinkRef ref) {
        this.linkref = ref;
        this.link = linkref.getLink();
        setLabel(Messages.getString("DeleteLinkRefCommand.deleteLinkRef")); //$NON-NLS-1$
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
        int size = linkref.getBendpoints().size();
        for (int i = 0; i < size; i++) {
            LinkRefBendpoint bendpoint = (LinkRefBendpoint) linkref.getBendpoints().get(size - 1 - i);
            append(new DeleteLinkRefBendpointCommandHelper(bendpoint));
        }

        append(new RemoveLinkRefCommandHelper(linkref));
        if (!DeletionContext.isPerformingCutAction() && link != null && link.getRefs().size() <= 1 && link.getGrlspec() != null) {
            if (link instanceof Contribution) {
                for (Iterator iterator = link.getGrlspec().getContributionContexts().iterator(); iterator.hasNext();) {
                    ContributionContext context = (ContributionContext) iterator.next();
                    for (Iterator iterator2 = context.getChanges().iterator(); iterator2.hasNext();) {
                        ContributionChange change = (ContributionChange) iterator2.next();
                        if (change.getContribution() == (Contribution) link)
                            append(new DeleteContributionChangeCommandHelper(change));
                    }
                }
            }
            append(new RemoveElementLinkCommandHelper(link));
        }
    }

    public void setElementLink(ElementLink link) {
        for (int i = 0; i < getCommandList().size(); i++) {
            if ((getCommandList().get(i) instanceof RemoveLinkRefCommandHelper)) {
                RemoveLinkRefCommandHelper ref = (RemoveLinkRefCommandHelper) getCommandList().get(i);
                ref.setElementLink(link);
            }
        }
    }
}
