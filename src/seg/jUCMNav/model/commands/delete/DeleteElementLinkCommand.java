/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ElementLink;
import grl.LinkRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveElementLinkCommand;

/**
 * Delete an ElementLink from the urnspec and all the LinkRef associate to it
 * @author Jean-François Roy
 *
 */
public class DeleteElementLinkCommand extends CompoundCommand {

    ElementLink link;
    
    /**
     * @param link 
     *          the elementLink to delete
     */
    public DeleteElementLinkCommand(ElementLink link) {
        setLabel(Messages.getString("DeleteElementLinkCommand.deleteElementLink")); //$NON-NLS-1$
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
        for (int i=0; i<link.getRefs().size(); i++){
            LinkRef ref = (LinkRef)link.getRefs().get(i);
            add(new DeleteLinkRefCommand(ref));
        }
        add(new RemoveElementLinkCommand(link));
    }

    
    /**
     * Overwriting undo to set the ElementLink to the DeleteLinkRefCommand
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        for (int i = getCommands().size() - 1; i >= 0; i--){
            if (getCommands().get(i) instanceof DeleteLinkRefCommand){
                ((DeleteLinkRefCommand)getCommands().get(i)).setElementLink(link);
            }
            ((Command) getCommands().get(i))
                .undo();
            
        }
    }

}
