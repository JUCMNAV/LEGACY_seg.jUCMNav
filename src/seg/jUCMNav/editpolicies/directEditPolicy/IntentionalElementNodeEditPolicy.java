/**
 * 
 */
package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.ElementLink;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import seg.jUCMNav.model.commands.create.AddBeliefLinkCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.create.CreateKPIModelLinkCommand;

/**
 * Edit policy to create connection between Intentional Element references
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class IntentionalElementNodeEditPolicy extends GraphicalNodeEditPolicy {

    /**
     * Constructor
     */
    public IntentionalElementNodeEditPolicy() {
        super();
    }

    /**
     * @return an {@link AddBeliefLinkCommand} or a {@link CreateElementLinkCommand}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        Command cmd = request.getStartCommand();
        if (cmd instanceof AddBeliefLinkCommand) {
            ((AddBeliefLinkCommand) cmd).setTarget((IntentionalElementRef) getHost().getModel());
        } else if (cmd instanceof CreateKPIModelLinkCommand) {
            ((CreateKPIModelLinkCommand) cmd).setTarget(((IntentionalElementRef) getHost().getModel()).getDef());
        } else {
            ((CreateElementLinkCommand) cmd).setTarget(((IntentionalElementRef) getHost().getModel()).getDef());
        }
        return cmd;
    }

    /**
     * @return a {@link CreateElementLinkCommand}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        if (request.getNewObject() instanceof ElementLink) {
            IntentionalElementRef source = (IntentionalElementRef) getHost().getModel();
            CreateElementLinkCommand cmd = new CreateElementLinkCommand(source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(),
                    (ElementLink) request.getNewObject());
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
        // IntentionalElementRef source = (IntentionalElementRef) getHost().getModel();
        // CreateElementLinkCommand cmd = new CreateElementLinkCommand (source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(),
        // (ElementLink)request.getTarget());
        // request.setStartCommand(cmd);
        return null;

    }

    /*
     * not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        // IntentionalElementRef source = (IntentionalElementRef) getHost().getModel();
        // CreateElementLinkCommand cmd = new CreateElementLinkCommand (source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(),
        // (ElementLink)request.getTarget());
        // request.setStartCommand(cmd);
        return null;

    }

}
