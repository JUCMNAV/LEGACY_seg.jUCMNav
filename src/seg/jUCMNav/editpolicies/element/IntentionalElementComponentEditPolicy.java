package seg.jUCMNav.editpolicies.element;

import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;

/**
 * ComponentEditPolicy for IntentionalElement. return the delete command
 * 
 * @author Jean-François Roy
 * 
 */
public class IntentionalElementComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteIntentionalElementCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof IntentionalElement) {

            IntentionalElement intentional = (IntentionalElement) elem;
            DeleteIntentionalElementCommand deleteCommand = new DeleteIntentionalElementCommand(intentional);
            return deleteCommand;
        }

        return null;
    }

}
