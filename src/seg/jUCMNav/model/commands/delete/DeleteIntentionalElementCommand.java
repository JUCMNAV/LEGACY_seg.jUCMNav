/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ElementLink;
import grl.IntentionalElement;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveIntentionalElementCommand;

/**
 * Delete an IntentionalElement (including all the ElementLink associate to it)
 * @author Jean-François Roy
 *
 */
public class DeleteIntentionalElementCommand extends CompoundCommand {

    IntentionalElement element;
    /**
     * @param element
     *          The intentionalElement to delete
     */
    public DeleteIntentionalElementCommand(IntentionalElement element) {
        this.element = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0){
            return true;
        }
        return super.canExecute();        
    }

    private void deleteElementLink(){
        if (element.getLinksDest().size()>0 || 
                element.getLinksSrc().size()>0){
            for (int i=0; i<element.getLinksDest().size(); i++){
                ElementLink link = (ElementLink)element.getLinksDest().get(i);
                add(new DeleteElementLinkCommand(link));
            }
            for (int i=0; i<element.getLinksSrc().size(); i++){
                ElementLink link = (ElementLink)element.getLinksSrc().get(i);
                add(new DeleteElementLinkCommand(link));
            }
        }
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
        //Delete all the ElementLink associate with the IntentionalElement
        deleteElementLink();
        add(new RemoveIntentionalElementCommand(element));
    }
}
