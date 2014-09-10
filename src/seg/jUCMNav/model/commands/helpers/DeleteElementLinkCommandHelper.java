package seg.jUCMNav.model.commands.helpers;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ElementLink;
import grl.LinkRef;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.Messages;

public class DeleteElementLinkCommandHelper extends CompoundCommand {

    
    ElementLink link;

    /**
     * @param link
     *            the elementLink to delete
     */
    public DeleteElementLinkCommandHelper(ElementLink link) {
        setLabel(Messages.getString("DeleteElementLinkCommand.deleteElementLink")); //$NON-NLS-1$
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
            LinkRef ref = (LinkRef) link.getRefs().get(i);
            append(new DeleteLinkRefCommandHelper(ref));
        }
        
        if (link instanceof Contribution && link.getGrlspec() != null) {
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

    /**
     * Overwriting undo to set the ElementLink to the DeleteLinkRefCommand
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        for (int i = getCommandList().size() - 1; i >= 0; i--) {
            if (getCommandList().get(i) instanceof DeleteLinkRefCommandHelper) {
                ((DeleteLinkRefCommandHelper) getCommandList().get(i)).setElementLink(link);
            }
            ((Command) getCommandList().get(i)).undo();

        }
    }

    
    
}
