package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveResponsibilityCommand;
import urn.URNlink;
import urncore.Responsibility;

/**
 * Command to delete a Responsibility and delete URNlink associate to it.
 * 
 * @author jkealey, Jean-Francois Roy
 *  
 */
public class DeleteResponsibilityCommand extends CompoundCommand {

    public DeleteResponsibilityCommand(Responsibility resp) {
        setLabel("DeleteResponsibilityCommand");
        
        //Remove the URNlinks
        for (Iterator it = resp.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator it = resp.getToLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        add(new RemoveResponsibilityCommand(resp));

    }
}