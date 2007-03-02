package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveResponsibilityCommand;
import ucm.performance.Demand;
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
        setLabel(Messages.getString("DeleteResponsibilityCommand.deleteResponsibilityCommand")); //$NON-NLS-1$
        
        //Remove the URNlinks
        for (Iterator it = resp.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator it = resp.getToLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator iter = resp.getDemands().iterator(); iter.hasNext();) {
            Demand demand = (Demand) iter.next();
            add(new DeleteDemandCommand(demand));
            
        }
        add(new RemoveResponsibilityCommand(resp));

    }
}