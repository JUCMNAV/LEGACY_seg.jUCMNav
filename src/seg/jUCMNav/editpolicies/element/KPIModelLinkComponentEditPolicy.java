package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteKPIModelLinkCommand;

/**
 * ComponentEditPolicy for KPIModelLink. Used in the outline, and return the command to delete an KPIModelLink
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteKPIModelLinkCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof KPIModelLink) {

            KPIModelLink link = (KPIModelLink) elem;
            DeleteKPIModelLinkCommand deleteCommand = new DeleteKPIModelLinkCommand(link);
            return deleteCommand;

        }

        return null;
    }

}
