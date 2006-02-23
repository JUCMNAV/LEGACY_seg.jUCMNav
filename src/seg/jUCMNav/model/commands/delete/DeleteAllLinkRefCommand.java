/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

/**
 * This compound command delete all the LinkRef associate with the specified IntentionalElementRef
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteAllLinkRefCommand extends CompoundCommand {

    /**
     * 
     */
    public DeleteAllLinkRefCommand(IntentionalElementRef element) {
        setLabel("Delete All LinkRefs"); 
        for (Iterator iter = element.getPred().iterator(); iter.hasNext();){
            LinkRef linkref = (LinkRef)iter.next();
            add(new DeleteLinkRefCommand(linkref));
        }
        for (Iterator iter = element.getSucc().iterator(); iter.hasNext();){
            LinkRef linkref = (LinkRef)iter.next();
            add(new DeleteLinkRefCommand(linkref));
        }
    }
}
