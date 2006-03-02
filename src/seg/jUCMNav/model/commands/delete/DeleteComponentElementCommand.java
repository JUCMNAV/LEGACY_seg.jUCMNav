package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveComponentElementCommand;
import urn.URNlink;
import urncore.ComponentElement;

/**
 * Command to delete a ComponentElement. Sould also remove all the URNlink.
 * 
 * @author jkealey, Jean-Francois Roy
 *  
 */
public class DeleteComponentElementCommand extends CompoundCommand {

    public DeleteComponentElementCommand(ComponentElement cd) {
        
        setLabel("Delete ComponentElement");
        
        //Remove the URNlinks
        for (Iterator it = cd.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator it = cd.getToLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        add(new RemoveComponentElementCommand(cd));
        
    }
}