package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveComponentElementCommand;
import urn.URNlink;
import urncore.ComponentElement;
import urncore.ComponentRegular;

/**
 * Command to delete a ComponentElement. Sould also remove all the URNlink.
 * 
 * @author jkealey, Jean-Francois Roy
 *  
 */
public class DeleteComponentElementCommand extends CompoundCommand {

    public DeleteComponentElementCommand(ComponentElement cd) {
        
        setLabel(Messages.getString("DeleteComponentElementCommand.deleteComponentElement")); //$NON-NLS-1$
        
        //Remove the URNlinks
        for (Iterator it = cd.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator it = cd.getToLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }

        if (cd.getResource()!=null) 
        {
            add(new RemoveResourceFromComponentCommand(cd, cd.getResource()));
        }
        
        if (cd instanceof ComponentRegular) {
            ComponentRegular regular = (ComponentRegular) cd;
            if (regular.getHost()!=null)
            {
                add(new RemoveResourceFromComponentCommand(regular, regular.getHost()));
            }
        }
        
        add(new RemoveComponentElementCommand(cd));
        
    }
}