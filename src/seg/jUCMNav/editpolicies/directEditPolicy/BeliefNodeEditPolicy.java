package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.Belief;
import grl.BeliefLink;
import grl.GRLGraph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import seg.jUCMNav.model.commands.create.AddBeliefLinkCommand;

/**
 * Edit policy to create connection between beliefs and IntentionalElementRef or LinkRef
 * 
 * @author Jean-François Roy
 * 
 */
public class BeliefNodeEditPolicy extends GraphicalNodeEditPolicy {

    /**
     * Constructor
     */
    public BeliefNodeEditPolicy() {
        super();
    }

    /*
     * Not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    /**
     * @return a {@link AddBeliefLinkCommand}
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        Belief source = (Belief) getHost().getModel();
        if (source.getSucc().size() == 0 && (request.getNewObject() instanceof BeliefLink)) {
            AddBeliefLinkCommand cmd = new AddBeliefLinkCommand((GRLGraph) source.getDiagram(), source, (BeliefLink) request.getNewObject());
            request.setStartCommand(cmd);
            return cmd;
        }
        return null;
    }

    /*
     * Not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }

    /*
     * Not implemented
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        return null;
    }

}
