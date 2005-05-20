/*
 * Created on 2005-02-07
 *
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import urncore.Responsibility;

/**
 * @author jkealey
 */
public class ResponsibilityComponentEditPolicy extends ComponentEditPolicy {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object resp = getHost().getModel();
        if (resp instanceof Responsibility) {

            Responsibility elem = (Responsibility) resp;
            if (elem.getRespRefs().size() == 0) {
                DeleteResponsibilityCommand deleteCommand = new DeleteResponsibilityCommand(elem);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}