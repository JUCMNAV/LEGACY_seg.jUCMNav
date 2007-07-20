/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteKPIModelLinkRefCommand;

/**
 * ComponentEditPolicy for KPIModelLinkRef. Return the command to delete a KPIModelLinkRef
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkRefComponentEditPolicy extends ComponentEditPolicy {

    /**
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        KPIModelLinkRef kpiModelLinkRef = (KPIModelLinkRef) getHost().getModel();

        return new DeleteKPIModelLinkRefCommand(kpiModelLinkRef);
    }

}
