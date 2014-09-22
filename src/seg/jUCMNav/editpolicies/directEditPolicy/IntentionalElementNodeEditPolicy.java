/**
 * 
 */
package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.ElementLink;
import grl.IntentionalElementRef;
import grl.Reuse;

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
    		CreateElementLinkCommand cmd;
    		// is this a reuse situation? (i.e., source definition is located in another URN model)
    		boolean reuseSituation = !source.getDef().getGrlspec().getUrnspec().getUrndef().equals(source.getDiagram().getUrndefinition());
    		if (request.getNewObject() instanceof Reuse && reuseSituation) {
    			// reuse case (pass in the sourceRef to be able create the LinkRef)
    			cmd = new CreateElementLinkCommand(source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(), source, 
    						(ElementLink) request.getNewObject(), null);
    		} else if (!(request.getNewObject() instanceof Reuse) && !reuseSituation) {
    			// not reuse case (use the original command)
    			cmd = new CreateElementLinkCommand(source.getDiagram().getUrndefinition().getUrnspec(), source.getDef(), 
    					(ElementLink) request.getNewObject(), null);
    		} else {
    			return null;
    		}
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
