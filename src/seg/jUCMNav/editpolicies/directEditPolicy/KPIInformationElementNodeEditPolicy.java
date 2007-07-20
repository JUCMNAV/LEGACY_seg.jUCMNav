/**
 * 
 */
package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import seg.jUCMNav.model.commands.create.CreateKPIModelLinkCommand;

/**
 * Edit policy to create connection between KPIInformationElement reference and Intentional element reference
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementNodeEditPolicy extends GraphicalNodeEditPolicy {

    /**
     * Constructor
     */
    public KPIInformationElementNodeEditPolicy() {
        super();
    }

    /**
     * @return a {@link CreateKPIModelLinkCommand}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    /**
     * @return a {@link CreateKPIModelLinkCommand}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        if (request.getNewObject() instanceof KPIModelLink) {
            KPIInformationElementRef source = (KPIInformationElementRef) getHost().getModel();
            CreateKPIModelLinkCommand cmd = new CreateKPIModelLinkCommand(source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(),
                    (KPIModelLink) request.getNewObject());
            request.setStartCommand(cmd);

            return cmd;
        }
        return null;
    }

    /*
     * not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;

    }

    /*
     * not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        return null;

    }

}
