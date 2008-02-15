package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveComponentCommand;
import urn.URNlink;
import urncore.Component;

/**
 * Command to delete a Component. Sould also remove all the URNlink.
 * 
 * @author jkealey, Jean-Francois Roy
 *  
 */
public class DeleteComponentCommand extends CompoundCommand {

    public DeleteComponentCommand(Component cd) {
        
        setLabel(Messages.getString("DeleteComponentCommand.deleteComponent")); //$NON-NLS-1$
        
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
        
        if (cd instanceof Component) {
            Component regular = (Component) cd;
            if (regular.getHost()!=null)
            {
                add(new RemoveResourceFromComponentCommand(regular, regular.getHost()));
            }
        }
        
        add(new RemoveComponentCommand(cd));
        
    }
}